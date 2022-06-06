package com.example.ecommerce.dtos;

import java.util.List;

public class CategoriaListaProdutoDTO {

	private String nomeCategoria;
	
	private List<ProdutoLimitadoDTO> produtoList;

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public List<ProdutoLimitadoDTO> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<ProdutoLimitadoDTO> produtoList) {
		this.produtoList = produtoList;
	}
}
