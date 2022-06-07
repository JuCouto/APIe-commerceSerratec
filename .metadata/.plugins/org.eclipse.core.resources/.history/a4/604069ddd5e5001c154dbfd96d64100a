package com.example.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@Validated
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAllPedido() {
		List<Pedido> pedidoList = pedidoService.findAllPedido();
		if (pedidoList.isEmpty()) {
			throw new EmptyListException("A lista de pedido está vazia.");
		} else {
			return new ResponseEntity<>(pedidoList, HttpStatus.OK);
		}
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> findPedidoDTOById(@PathVariable Integer id) {
		PedidoDTO pedidoDTO = pedidoService.findPedidoDTOById(id);
		if (pedidoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum pedido com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/dto")
	public ResponseEntity<PedidoDTO> savePedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.savePedido(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/dto")
	public ResponseEntity<PedidoDTO> updatePedidoDTO(@Valid @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.updatePedidoDTO(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedido(@PathVariable Integer id) {
		if (pedidoService.findPedidoById(id) == null) {
			throw new NoSuchElementFoundException("O pedido com o ID: " + id + " não existe.");
		} else {
			pedidoService.deletePedido(id);
			return new ResponseEntity<>("Pedido deletado com sucesso", HttpStatus.OK);
		}
	}
}
