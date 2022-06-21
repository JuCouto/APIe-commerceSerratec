package com.example.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dtos.PedidoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Pedido;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")
@Validated
@CrossOrigin
@Tag(name = "Pedidos", description = "Endpoints")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@Operation(summary = "Listar todos os pedidos", responses = {
	@ApiResponse(responseCode = "200", description = "Listar todos", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping
	public ResponseEntity<List<Pedido>> findAllPedido() {
		List<Pedido> pedidoList = pedidoService.findAllPedido();
		if (pedidoList.isEmpty()) {
			throw new EmptyListException("A lista de pedido está vazia.");
		} else {
			return new ResponseEntity<>(pedidoList, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "Listar um pedido", responses = {
	@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> findPedidoDTOById(@PathVariable Integer id) {
		PedidoDTO pedidoDTO = pedidoService.findPedidoDTOById(id);
		if (pedidoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum pedido com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "Inserir os dados de pedido", responses = {
	@ApiResponse(responseCode = "200", description = "Salvo com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })			
	@PostMapping("/dto")
	public ResponseEntity<PedidoDTO> savePedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.savePedido(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualizar os dados de pedido", responses = {
	@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PutMapping("/dto")
	public ResponseEntity<PedidoDTO> updatePedidoDTO(@Valid @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO novoPedidoDTO = pedidoService.updatePedidoDTO(pedidoDTO);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.OK);
	}

	@PatchMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> updatePedidoPatchDTO(@Valid @PathVariable Integer id, @RequestBody Map<Object, Object> object) {
		PedidoDTO novoPedidoDTO = pedidoService.updatePedidoPatchDTO(id, object);
		return new ResponseEntity<>(novoPedidoDTO, HttpStatus.OK);
	}
	
	@Operation(summary = "Remover um pedido", responses = {
	@ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
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
