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
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Validated
@Tag(name = "Clientes", description = "Endpoints")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Operation(summary = "Listar todos os clientes", responses = {
	@ApiResponse(responseCode = "200", description = "Listar todos", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping
	public ResponseEntity<List<Cliente>> findAllCliente() {
		List<Cliente> clienteList = clienteService.findAllClientes();
		if (clienteList.isEmpty()) {
			throw new EmptyListException("A lista de cliente está vazia.");
		} else {
			return new ResponseEntity<>(clienteList, HttpStatus.OK);
		}
	}

	@Operation(summary = "Listar um cliente", responses = {
	@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/dto/{id}")
	public ResponseEntity<ClienteDTO> findClienteDTOById(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.findClienteDTOById(id);
		if (clienteDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum cliente com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
		}

	}

	@Operation(summary = "Inserir os dados de cliente", responses = {
	@ApiResponse(responseCode = "200", description = "Salvo com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })			
	@PostMapping("/dto")
	public ResponseEntity<ClienteDTO> saveClienteDTO(@Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO novoClienteDTO = clienteService.saveClienteDTO(clienteDTO);
		return new ResponseEntity<>(novoClienteDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar os dados de cliente", responses = {
	@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PutMapping("/dto")
	public ResponseEntity<ClienteDTO> updateClienteDTO(@Valid @RequestBody ClienteDTO clienteDTO) {
		ClienteDTO novoClienteDTO = clienteService.updateClienteDTO(clienteDTO);
		return new ResponseEntity<>(novoClienteDTO, HttpStatus.OK);
	}

	@Operation(summary = "Remover um cliente", responses = {
	@ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
		if (clienteService.findClienteById(id) == null) {
			throw new NoSuchElementFoundException("O cliente com o ID: " + id + " não existe.");
		} else {
			clienteService.deleteCliente(id);
			return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
		}
	}
}
