package com.example.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.ClienteDTO;
import com.example.ecommerce.dtos.ItemPedidoDTO;
import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.Cliente;
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

	@Autowired
	ItemPedidoService itemPedidoService;

	@Autowired
	ClienteService clienteService;

	@Autowired
	MailService mailService;

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

	@Transactional
	public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
		Pedido pedido = converterDTOParaEntidade(pedidoDTO);
		pedidoRepository.save(pedido);
		List<ItemPedido> listaItemPedido = pedido.getListaItemPedido();

		for (ItemPedido itemPedido : listaItemPedido) {
			itemPedido.setPedido(pedido);
			Produto produto = produtoService.findProdutoById(itemPedido.getProduto().getIdProduto());
			itemPedido.setProduto(produto);

		}
		List<ItemPedido> novoPedido = itemPedidoService.saveListaItemPedido(listaItemPedido);

		List<ItemPedidoDTO> converterEntidadeParaDTO = itemPedidoService.converterEntidadeParaDTO(novoPedido);
		pedidoDTO.setItemPedidoDTO(converterEntidadeParaDTO);
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setClienteDTO(clienteService.findClienteDTOById(pedidoDTO.getClienteDTO().getIdCliente()));
		return pedidoDTO;
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
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());
		ClienteDTO clienteDTO = clienteService.findClienteDTOById(pedido.getCliente().getIdCliente());
		pedidoDTO.setClienteDTO(clienteDTO);

		List<ItemPedidoDTO> listaItemPedidoDTO = new ArrayList<>();
		if (pedido.getListaItemPedido() != null) {

			for (ItemPedido itemPedido : pedido.getListaItemPedido()) {
				ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
				itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
				itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
				itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
				itemPedidoDTO.setQtdItemPedido(itemPedido.getQtdItemPedido());
				itemPedidoDTO.setValorBruto(itemPedidoDTO.getPrecoVenda() * itemPedidoDTO.getQtdItemPedido());
				itemPedidoDTO.setValorLiquido(itemPedidoDTO.getValorBruto() - itemPedido.getPercentualDesconto());
				ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(itemPedido.getProduto().getIdProduto());
				itemPedidoDTO.setProdutoDTO(produtoDTO);

				listaItemPedidoDTO.add(itemPedidoDTO);
			}
			pedidoDTO.setItemPedidoDTO(listaItemPedidoDTO);
		}
		return pedidoDTO;
	}

	public Pedido converterDTOParaEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());
		Cliente cliente = clienteService.findClienteById(pedidoDTO.getClienteDTO().getIdCliente());
		pedido.setCliente(cliente);

		List<ItemPedido> listaItemPedido = new ArrayList<>();
		if (pedidoDTO.getItemPedidoDTO() != null) {

			for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItemPedidoDTO()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
				itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
				itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
				itemPedido.setQtdItemPedido(itemPedidoDTO.getQtdItemPedido());
				itemPedido.setValorBruto(itemPedido.getPrecoVenda() * itemPedido.getQtdItemPedido());
				itemPedido.setValorLiquido(itemPedido.getValorBruto() - itemPedido.getPercentualDesconto());
				Produto produto = produtoService.findProdutoById(itemPedidoDTO.getProdutoDTO().getIdProduto());
				itemPedido.setProduto(produto);

				listaItemPedido.add(itemPedido);
			}
			pedido.setListaItemPedido(listaItemPedido);
		}
		return pedido;

	}

}
