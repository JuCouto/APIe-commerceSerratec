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

import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.services.ClienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAllCliente() {
		List<Cliente> clienteList = clienteService.findAllClientes();
		if (clienteList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(clienteList, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findClienteById(id);
		if (cliente == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.saveCliente(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.updateCliente(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
		if (clienteService.findClienteById(id) == null) {
			return new ResponseEntity<>("Esse cliente não foi encontrado", HttpStatus.NO_CONTENT);
		} else {
			clienteService.deleteCliente(id);
			return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
		}
	}
}
