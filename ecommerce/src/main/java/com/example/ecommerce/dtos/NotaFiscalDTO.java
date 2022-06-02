package com.example.ecommerce.dtos;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDTO {

	private ClienteDTO cliente;
	
	private List<ItemPedidoDTO> itemPedidoDTO = new ArrayList<>();

	public NotaFiscalDTO() {
		super();
	}

	public NotaFiscalDTO(ClienteDTO cliente, List<ItemPedidoDTO> itemPedidoDTO) {
		super();
		this.cliente = cliente;
		this.itemPedidoDTO = itemPedidoDTO;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO> getItemPedidoDTO() {
		return itemPedidoDTO;
	}

	public void setItemPedidoDTO(List<ItemPedidoDTO> itemPedidoDTO) {
		this.itemPedidoDTO = itemPedidoDTO;
	}
	
	
}
