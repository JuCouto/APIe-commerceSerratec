package com.example.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.CategoriaDTO;
import com.example.ecommerce.dtos.CategoriaListaProdutoDTO;
import com.example.ecommerce.dtos.ProdutoLimitadoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Produto;
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

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		Categoria novaCategoria = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(novaCategoria);
	}

	public CategoriaDTO updateCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		Categoria novaCategoria = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(novaCategoria);
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

				listaProdutoDTO.add(produtoDTO);
			}
			categoriaListaProdutoDTO.setProdutoList(listaProdutoDTO);
		}
		return categoriaListaProdutoDTO;
}
}
