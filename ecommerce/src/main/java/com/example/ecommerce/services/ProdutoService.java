package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.CategoriaDTO;
import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaService categoriaService;
	
	public List<Produto> findAllProduto() {
		return produtoRepository.findAll();
	}
	
	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}
	
	public ProdutoDTO findProdutoDTOById(Integer id) {
		Produto produto =  produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if (produto != null) {
			produtoDTO = converterEntidadeParaDTO(produto);
		}
		return produtoDTO;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = converterDTOParaEntidade(produtoDTO);
		Produto novoProduto = produtoRepository.save(produto);
		return converterEntidadeParaDTO(novoProduto);
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deleteProduto(Integer id) {
		produtoRepository.deleteById(id);
	}
	
	public ProdutoDTO converterEntidadeParaDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setImagemProduto(produto.getImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setQtdEstoque(produto.getQtdEstoque());
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(produtoDTO.getCategoria().getIdCategoria());
		produtoDTO.setCategoria(categoriaDTO);

		return produtoDTO;
	}
	
	public Produto converterDTOParaEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		Categoria categoria = categoriaService.findCategoriaById(produto.getCategoria().getIdCategoria());
		produto.setCategoria(categoria);
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setImagemProduto(produtoDTO.getImagemProduto());
		produto.setQtdEstoque(produtoDTO.getQtdEstoque());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		produto.setImagemProduto(produtoDTO.getImagemProduto());
		
		return produto;
	}
}
