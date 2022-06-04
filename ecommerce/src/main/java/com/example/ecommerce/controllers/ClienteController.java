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

import com.example.ecommerce.dtos.ClienteDTO;
import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.services.ClienteService;

@RestController
@RequestMapping("/cliente")
@Validated
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

	@GetMapping("/dto/{id}")
	public ResponseEntity<ClienteDTO> findClienteDTOById(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.findClienteDTOById(id);
		if (clienteDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/dto")
	public ResponseEntity<ClienteDTO> saveClienteDTO(@Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO novoClienteDTO = clienteService.saveClienteDTO(clienteDTO);
		return new ResponseEntity<>(novoClienteDTO, HttpStatus.CREATED);
	}

	@PutMapping("/dto")
	public ResponseEntity<ClienteDTO> updateClienteDTO(@Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO novoClienteDTO = clienteService.updateClienteDTO(clienteDTO);
		return new ResponseEntity<>(novoClienteDTO, HttpStatus.OK);
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
