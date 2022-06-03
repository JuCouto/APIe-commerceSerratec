package com.example.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.ItemPedido;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.services.ItemPedidoService;
import com.example.ecommerce.services.PedidoService;
import com.example.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido) {
		Pedido pedido = pedidoService.savePedido(itemPedido.getPedido());
		Produto produto = produtoService.findProdutoById(itemPedido.getProduto().getIdProduto());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		ItemPedido novoItemPedido = itemPedidoService.saveItemPedido(itemPedido);
		return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
	}
}
