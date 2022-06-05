package com.example.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "item_pedido")
@JsonIdentityInfo(scope = ItemPedido.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "idItemPedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;

	@Column(name = "quantidade")
	private Integer qtdItemPedido;

	@Column(name = "preco_venda")
	private Double precoVenda;

	@Column(name = "percentual_desconto")
	private Double percentualDesconto;

	@Column(name = "valor_bruto")
	private Double valorBruto;

	@Column(name = "valor_liquido")
	private Double valorLiquido;

	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedido [idItemPedido=" + idItemPedido + ", qtdItemPedido=" + qtdItemPedido + ", precoVenda="
				+ precoVenda + ", percentualDesconto=" + percentualDesconto + ", valorBruto=" + valorBruto
				+ ", valorLiquido=" + valorLiquido + ", pedido=" + pedido + ", produto=" + produto + "]";
	}

}
