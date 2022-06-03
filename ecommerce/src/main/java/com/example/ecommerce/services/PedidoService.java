package com.example.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.ItemPedidoDTO;
import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.ItemPedido;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoService produtoService;

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

	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = converterDTOParaEntidade(pedidoDTO);
		Pedido novoPedido = pedidoRepository.save(pedido);
		return converterEntidadeParaDTO(novoPedido);
	}

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

		List<ItemPedidoDTO> listaItemPedidoDTO = new ArrayList<>();
		if (pedido.getListaItemPedido() == null) {

			for (ItemPedido itemPedido : pedido.getListaItemPedido()) {
				ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
				itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
				itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
				itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda() * itemPedido.getQtdItemPedido());
				itemPedidoDTO.setQtdItemPedido(itemPedido.getIdItemPedido());
				itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
				itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
				ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(itemPedidoDTO.getPedido().getIdPedido());
				itemPedidoDTO.setProduto(produtoDTO);

				listaItemPedidoDTO.add(itemPedidoDTO);
			}
			pedidoDTO.setItemPedidoDTO(listaItemPedidoDTO);
		}
		return pedidoDTO;
	}

	public Pedido converterDTOParaEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());

		List<ItemPedido> listaItemPedido = new ArrayList<>();
		if (pedidoDTO.getItemPedidoDTO() == null) {

			for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItemPedidoDTO()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
				itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
				itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda() * itemPedido.getQtdItemPedido());
				itemPedido.setQtdItemPedido(itemPedidoDTO.getIdItemPedido());
				itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
				itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
				Produto produto = produtoService.findProdutoById(itemPedido.getPedido().getIdPedido());
				itemPedido.setProduto(produto);

				listaItemPedido.add(itemPedido);
			}
			pedido.setListaItemPedido(listaItemPedido);
		}
		return pedido;
	}
}
