package com.example.ecommerce.dtos;

import java.util.Date;
import java.util.List;

public class PedidoDTO {

	private Integer idPedido;

	private Date dataPedido;

	private Date dataEntrega;

	private Date dataEnvio;

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

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
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
}
