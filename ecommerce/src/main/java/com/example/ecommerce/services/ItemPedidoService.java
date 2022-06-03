package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.ItemPedido;
import com.example.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAllItemPedido() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findItemPedidoById(Integer id) {
		return itemPedidoRepository.findById(id).isPresent() ? itemPedidoRepository.findById(id).get() : null;
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public void deleteItemPedido(Integer id) {
		itemPedidoRepository.deleteById(id);
	}

	public List<ItemPedido> saveItemPedido(List<ItemPedido> listaItemPedido) {
		return itemPedidoRepository.saveAll(listaItemPedido);
	}
	
	/*
	 * public ItemPedidoDTO converterEntidadeParaDTO(ItemPedido itemPedido) {
	 * ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
	 * itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
	 * itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
	 * itemPedidoDTO.setQtdItemPedido(itemPedido.getQtdItemPedido());
	 * itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
	 * itemPedidoDTO.setValorBruto(itemPedidoDTO.getPrecoVenda() *
	 * itemPedidoDTO.getQtdItemPedido());
	 * itemPedidoDTO.setValorLiquido(itemPedidoDTO.getValorBruto() -
	 * itemPedidoDTO.getPercentualDesconto()); ProdutoDTO produtoDTO =
	 * produtoService.findProdutoDTOById(itemPedido.getProduto().getIdProduto());
	 * itemPedidoDTO.setProduto(produtoDTO);
	 * 
	 * returnitem PedidoDTO; }
	 */
}
