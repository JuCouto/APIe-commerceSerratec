package com.example.ecommerce.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.CategoriaDTO;
import com.example.ecommerce.dtos.CategoriaListaProdutoDTO;
import com.example.ecommerce.dtos.ProdutoLimitadoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.exceptions.InvalidDescriptionException;
import com.example.ecommerce.exceptions.InvalidNomeIgualException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	MailService mailService;

	@Autowired
	ArquivoService arquivoService;

	public List<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}

	public Categoria findCategoriaById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
	}
	
	public CategoriaDTO findCategoriaDTOById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		if (categoria != null) {
			categoriaDTO = converterEntidadeParaDTO(categoria);
			return categoriaDTO;
		}
		return null;
	}
	
	public CategoriaListaProdutoDTO findCategoriaListaProdutoDTOById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
		CategoriaListaProdutoDTO categoriaListaProdutoDTO = new CategoriaListaProdutoDTO();
		if (categoria != null) {
			categoriaListaProdutoDTO = converterEntidadeParaDTOListProduto(categoria);
			return categoriaListaProdutoDTO;
		}
		return null;
	}
	
	public CategoriaListaProdutoDTO findCategoriaListaProdutoDTOByNome(String nome) {
		Categoria categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nome).isPresent() ? categoriaRepository.findByNomeCategoriaIgnoreCase(nome).get() : null;
		CategoriaListaProdutoDTO categoriaListaProdutoDTO = new CategoriaListaProdutoDTO();
		if (categoria != null) {
			categoriaListaProdutoDTO = converterEntidadeParaDTOListProduto(categoria);
			return categoriaListaProdutoDTO;
		}
		return null;
	}

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		validarCategoriaNome(categoria.getNomeCategoria());
		validarCategoriaDescricao(categoria.getDescricaoCategoria());
		Categoria novaCategoria = categoriaRepository.save(categoria);
		return converterEntidadeParaDTO(novaCategoria);
	}

	public CategoriaDTO updateCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		validarCategoriaNome(categoria.getNomeCategoria());
		validarCategoriaDescricao(categoria.getDescricaoCategoria());
		Categoria novaCategoria = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(novaCategoria);
	}
	
	public CategoriaDTO updateCategoriaPatchDTO(@Valid Integer id, Map<Object, Object> object) {
		Categoria categoria = findCategoriaById(id);
		if (categoria == null) {
			throw new NoSuchElementFoundException("Não existe nenhum Categoria com o ID: " + id + ".");
		}
		object.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Categoria.class, (String)key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, categoria, value);
		});
		
		Categoria novoCategoria = categoriaRepository.save(categoria);
		
		return converterEntidadeParaDTO(novoCategoria);
	}

	public void deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
	}

	public CategoriaDTO converterEntidadeParaDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(categoria.getIdCategoria());
        categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
        categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());

        return categoriaDTO;
    }

    public Categoria converterDTOParaEntidade(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(categoriaDTO.getIdCategoria());
        categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
        categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());

        return categoria;
    }
    
    public CategoriaListaProdutoDTO converterEntidadeParaDTOListProduto(Categoria categoria) {
    	CategoriaListaProdutoDTO categoriaListaProdutoDTO = new CategoriaListaProdutoDTO();
    	categoriaListaProdutoDTO.setNomeCategoria(categoria.getNomeCategoria());
    	
    	List<ProdutoLimitadoDTO> listaProdutoDTO = new ArrayList<>();
		if (categoriaListaProdutoDTO.getProdutoList() == null) {

			for (Produto produto : categoria.getProdutoList()) {
				ProdutoLimitadoDTO produtoDTO = new ProdutoLimitadoDTO();
				produtoDTO.setNomeProduto(produto.getNomeProduto());
				produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
				produtoDTO.setIdProduto(produto.getIdProduto());
				produtoDTO.setQtdEstoque(produto.getQtdEstoque());
				produtoDTO.setValorUnitario(produto.getValorUnitario());
				produtoDTO.setImagemProduto(produto.getImagemProduto());

				listaProdutoDTO.add(produtoDTO);
			}
			categoriaListaProdutoDTO.setProdutoList(listaProdutoDTO);
		}
		return categoriaListaProdutoDTO;
}
    public void validarCategoriaNome(String nome) {
		var categoria = categoriaRepository.findByNomeCategoriaIgnoreCase(nome);
		if (categoria.isPresent()) {
			throw new InvalidNomeIgualException("Existe uma categoria com esse nome.");
		}
	}
    
    public void validarCategoriaDescricao(String descricaoCategoria) {
		var categoria = categoriaRepository.findByDescricaoCategoria(descricaoCategoria);
		if (categoria.isPresent()) {
			throw new InvalidDescriptionException("Existe uma categoria com essa descrição.");
		}
	}

	
}
