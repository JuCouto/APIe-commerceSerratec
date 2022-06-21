package com.example.ecommerce.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.dtos.CategoriaDTO;
import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.exceptions.InvalidDescriptionException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.repositories.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	ArquivoService arquivoService;

	public Page<Produto> findAllProdutoPage(Pageable pageable) {
		
		return produtoRepository.findAll(pageable);
	}
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}

	public List<Produto> listAll(String palavraChave) {
		if (palavraChave != null) {
			return produtoRepository.pesquisaIgonereCase(palavraChave);
		}
		return produtoRepository.findAll();
	}

	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public ProdutoDTO findProdutoDTOById(Integer id) {
		Produto produto = produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if (produto != null) {
			produtoDTO = converterEntidadeParaDTO(produto);
			return produtoDTO;
		}
		return null;
	}

	public ProdutoDTO findProdutoDTOByNome(String nome) {
		Produto produto = produtoRepository.findByNomeProdutoIgnoreCase(nome).isPresent()
				? produtoRepository.findByNomeProdutoIgnoreCase(nome).get()
				: null;
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if (produto != null) {
			produtoDTO = converterEntidadeParaDTO(produto);
			return produtoDTO;
		}
		return null;
	}

	public ProdutoDTO findProdutoDTOByDescricao(String descricao) {
		Produto produto = produtoRepository.findByDescricaoProdutoIgnoreCase(descricao).isPresent()
				? produtoRepository.findByDescricaoProdutoIgnoreCase(descricao).get()
				: null;
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if (produto != null) {
			produtoDTO = converterEntidadeParaDTO(produto);
			return produtoDTO;
		}
		return null;
	}
	
	public List<Produto> listAllContains(String palavraChave) {
		if (palavraChave != null) {
			return produtoRepository.findByNomeProdutoContainingIgnoreCase(removerAcentos(palavraChave));
		}
		return produtoRepository.findAll();
	}


	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		validarDescricao(produtoDTO.getDescricaoProduto());
		Produto produto = converterDTOParaEntidade(produtoDTO);
		Produto novoProduto = produtoRepository.save(produto);
		return converterEntidadeParaDTO(novoProduto);
	}

	public ProdutoDTO updateProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = converterDTOParaEntidade(produtoDTO);
		Produto novoProduto = produtoRepository.save(produto);
		return converterEntidadeParaDTO(novoProduto);
	}
	
	public ProdutoDTO updateProdutoPacthDTO(Integer id,  Map<Object, Object> object) {
		Produto produto = findProdutoById(id);
		if (produto == null) {
			throw new NoSuchElementFoundException("Não existe nenhum cliente com o ID: " + id + ".");
		}
		object.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Produto.class, (String)key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, produto, value);
		});
		
		Produto novoProduto = produtoRepository.save(produto);
		
		return converterEntidadeParaDTO(novoProduto);
	}

	public ProdutoDTO saveProdutoComFotoDTO(String produtoStringDTO, MultipartFile file) throws Exception {
		ProdutoDTO produtoConvertidoDTO = new ProdutoDTO();
		try {
			ObjectMapper objMapper = new ObjectMapper();
			produtoConvertidoDTO = objMapper.readValue(produtoStringDTO, ProdutoDTO.class);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar imagem");
		}
		validarDescricao(produtoConvertidoDTO.getDescricaoProduto());
		Produto produto = converterDTOParaEntidade(produtoConvertidoDTO);
		Produto produtoBD = produtoRepository.save(produto);
		produtoBD.setImagemProduto(produtoBD.getIdProduto() + "_" + file.getOriginalFilename());
		Produto produtoAtualizado = produtoRepository.save(produtoBD);
		try {
			arquivoService.criarArquivo(produtoBD.getIdProduto() + "_" + file.getOriginalFilename(), file);

		} catch (Exception e) {
			throw new Exception("Não foi possível mover o arquivo.-" + e.getStackTrace());

		}

		return converterEntidadeParaDTO(produtoAtualizado);
	}

	public ProdutoDTO updateProdutoComFotoDTO(String produtoStringDTO, MultipartFile file) throws Exception {
		ProdutoDTO produtoConvertidoDTO = new ProdutoDTO();
		try {
			ObjectMapper objMapper = new ObjectMapper();
			produtoConvertidoDTO = objMapper.readValue(produtoStringDTO, ProdutoDTO.class);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao salvar imagem");
		}
		validarDescricao(produtoConvertidoDTO.getDescricaoProduto());
		Produto produto = converterDTOParaEntidade(produtoConvertidoDTO);
		Produto produtoBD = produtoRepository.save(produto);
		produtoBD.setImagemProduto(produtoBD.getIdProduto() + "_" + file.getOriginalFilename());
		Produto produtoAtualizado = produtoRepository.save(produtoBD);
		try {
			arquivoService.criarArquivo(produtoBD.getIdProduto() + "_" + file.getOriginalFilename(), file);

		} catch (Exception e) {
			throw new Exception("Não foi possível mover o arquivo.-" + e.getStackTrace());

		}
		return converterEntidadeParaDTO(produtoAtualizado);
	}

	public void deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
	}

	public ProdutoDTO converterEntidadeParaDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setImagemProduto(produto.getImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(produto.getCategoria().getIdCategoria());
		produtoDTO.setCategoriaDTO(categoriaDTO);

		return produtoDTO;
	}

	public Produto converterDTOParaEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setImagemProduto(produtoDTO.getImagemProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		Categoria categoria = categoriaService.findCategoriaById(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setCategoria(categoria);

		return produto;
	}

	public void validarDescricao(String descricaoProduto) {
		var produto = produtoRepository.findByDescricaoProdutoIgnoreCase(descricaoProduto);
		if (produto.isPresent()) {
			throw new InvalidDescriptionException("Existe um produto com essa descrição.");
		}
	}
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	
}
