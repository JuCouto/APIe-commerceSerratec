package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ItemPedidoService itemPedidoService;

	public List<Pedido> findAllPedido() {
		return pedidoRepository.findAll();
	}

	public Pedido findPedidoById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ? pedidoRepository.findById(id).get() : null;
	}

	public PedidoDTO findPedidoDTOById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).isPresent() ? pedidoRepository.findById(id).get() : null;
		PedidoDTO pedidoDTO = new PedidoDTO();
		if (pedido != null) {
			pedidoDTO = converterEntidadeParaDTO(pedido);
		}
		return pedidoDTO;
	}

	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	/*
	 * public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) { Pedido pedido =
	 * converterDTOParaEntidade(pedidoDTO); Pedido novoPedido =
	 * pedidoRepository.save(pedido); ItemPedido itemPedido =
	 * pedido.getListaItemPedido().get(); itemPedido.setPedido(novoPedido);
	 * 
	 * itemPedidoService.saveItemPedido(null); return
	 * converterEntidadeParaDTO(novoPedido); }
	 */
	
	public PedidoDTO updatePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = converterDTOParaEntidade(pedidoDTO);
		Pedido novoPedido = pedidoRepository.save(pedido);
		return converterEntidadeParaDTO(novoPedido);
	}

	public Pedido updatePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public void deletePedido(Integer id) {
		pedidoRepository.deleteById(id);
	}

	public PedidoDTO converterEntidadeParaDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());

		return pedidoDTO;
	}

	public Pedido converterDTOParaEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());

		return pedido;
	}
}
