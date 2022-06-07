package com.example.ecommerce.dtos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoDTO {

	private Integer idPedido;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPedido;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEntrega;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEnvio;

	private Boolean statusPedido;

	private Double total;

	private ClienteDTO clienteDTO;

	private List<ItemPedidoDTO> itemPedidoDTO;

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

	public List<ItemPedidoDTO> getItemPedidoDTO() {
		return itemPedidoDTO;
	}

	public void setItemPedidoDTO(List<ItemPedidoDTO> itemPedidoDTO) {
		this.itemPedidoDTO = itemPedidoDTO;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {
		Double soma = 0.0;
		List<ItemPedidoDTO> itemPedidos = getItemPedidoDTO();
		for (ItemPedidoDTO op : itemPedidos) {
			soma += op.getValorLiquido();
		}
		total = soma;
		return total;
	}

	@Override
	public String toString() {
		return " \n \n Número do pedido: " + idPedido +"\n Data pedido: " + dataPedido + "\n Data entrega: " + dataEntrega
				+ "\n Data envio: " + dataEnvio + "\n Status pedido: " + statusPedido + "\n Total: " + getTotal()
				+ "\n \n Informações do cliente: \n" + clienteDTO + "\n \n Pedido(s): " + itemPedidoDTO;
	}

}
