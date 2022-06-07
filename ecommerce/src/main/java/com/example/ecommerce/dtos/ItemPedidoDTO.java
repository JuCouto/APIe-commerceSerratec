package com.example.ecommerce.dtos;

public class ItemPedidoDTO {

	private Integer idItemPedido;

	private Integer qtdItemPedido;

	private Double precoVenda;

	private Double percentualDesconto;

	private Double valorBruto;

	private Double valorLiquido;

	private ProdutoDTO produtoDTO;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQtdItemPedido() {
		return qtdItemPedido;
	}

	public void setQtdItemPedido(Integer qtdItemPedido) {
		this.qtdItemPedido = qtdItemPedido;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public ProdutoDTO getProdutoDTO() {
		return produtoDTO;
	}

	public void setProdutoDTO(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	@Override
	public String toString() {
		return  "\n \n Informações do produto: " + produtoDTO+"\n Quantidade: " + qtdItemPedido + "\n Valor Unitário: R$"
				+ precoVenda +"\n Valor Bruto: R$" + valorBruto
				+  "\n Desconto Aplicado: R$" + percentualDesconto + "\n Valor Final: R$" + valorLiquido;
	}

}
