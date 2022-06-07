package com.example.ecommerce.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedido")
@JsonIdentityInfo(scope = Pedido.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@FutureOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@FutureOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_envio")
	private LocalDate dataEnvio;

	@Column(name = "status")
	private Boolean statusPedido;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;

	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> listaItemPedido;

	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(Boolean statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", dataEntrega=" + dataEntrega
				+ ", dataEnvio=" + dataEnvio + ", statusPedido=" + statusPedido + ", cliente=" + cliente
				+ ", listaItemPedido=" + listaItemPedido + "]";
	}

}
