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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.services.PedidoService;


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

	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> findPedidoDTOById(@PathVariable Integer id) {
		PedidoDTO pedidoDTO = pedidoService.findPedidoDTOById(id);
		if (pedidoDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
		}

	}
	
	@PostMapping("/dto")
	public ResponseEntity<PedidoDTO> savePedidoDTO(@RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.savePedidoDTO(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.CREATED);
	}


	@PutMapping("/dto")
	public ResponseEntity<PedidoDTO> updatePedidoDTO(@RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.updatePedidoDTO(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.OK);
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
