package com.example.ecommerce.dtos;

import java.util.Date;

public class ProdutoDTO {
	private Integer idProduto;

	private String nomeProduto;

	private String descricaoProduto;

	private Integer qtdEstoque;

	private Date dataCadastro;

	private Double valorUnitario;

	private String imagemProduto;

	private CategoriaDTO categoriaDTO;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", descricaoProduto="
				+ descricaoProduto + ", qtdEstoque=" + qtdEstoque + ", dataCadastro=" + dataCadastro
				+ ", imagemProduto=" + imagemProduto + ", categoriaDTO=" + categoriaDTO + "]";
	}


}
