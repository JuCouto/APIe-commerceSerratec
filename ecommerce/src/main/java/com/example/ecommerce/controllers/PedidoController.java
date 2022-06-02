package com.example.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.services.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAllPedido() {
		List<Pedido> pedidoList = pedidoService.findAllPedido();
		if (pedidoList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(pedidoList, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findPedidoById(id);
		if (pedido == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido) {
		Pedido novoPedido = pedidoService.savePedido(pedido);
		return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {
		Pedido novoPedido = pedidoService.updatePedido(pedido);
		return new ResponseEntity<>(novoPedido, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedido(@PathVariable Integer id) {
		if (pedidoService.findPedidoById(id) == null) {
			return new ResponseEntity<>("Esse pedido não foi encontrado", HttpStatus.NO_CONTENT);
		} else {
			pedidoService.deletePedido(id);
			return new ResponseEntity<>("Pedido deletado com sucesso", HttpStatus.OK);
		}
	}
}