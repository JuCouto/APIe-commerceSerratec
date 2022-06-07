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

import com.example.ecommerce.dtos.EnderecoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/endereco")
@Validated
@Tag(name = "Endereços", description = "Endpoints")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

	@Operation(summary = "Listar todos os endereços", responses = {
	@ApiResponse(responseCode = "200", description = "Listar todos", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco() {
		List<Endereco> enderecoList = enderecoService.findAllEndereco();
		if (enderecoList.isEmpty()) {
			throw new EmptyListException("A lista de endereço está vazia");
		} else {
			return new ResponseEntity<>(enderecoList, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "Consultar um CEP via api externa", responses = {
	@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> consultarDadosPorCep(String cep) {
		EnderecoDTO cadastroCepDTO = enderecoService.consultarCep(cep).converterEntidadeParaDTO();
		return new ResponseEntity<>(cadastroCepDTO, HttpStatus.OK);

	}

	@Operation(summary = "Listar um endereço", responses = {
	@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/dto/{id}")
	public ResponseEntity<EnderecoDTO> findEnderecoDTOById(@PathVariable Integer id) {
		EnderecoDTO enderecoDTO = enderecoService.findEnderecoDTOById(id);
		if (enderecoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum endereço com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		}

	}

	@Operation(summary = "Inserir os dados de endereço", responses = {
	@ApiResponse(responseCode = "200", description = "Salvo com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })			
	@PostMapping("/dto")
	public ResponseEntity<EnderecoDTO> saveEnderecoDTO(@Valid @RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO novoEnderecoDTO = enderecoService.saveEnderecoDTO(enderecoDTO);
		return new ResponseEntity<>(novoEnderecoDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar os dados de endereço", responses = {
	@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PutMapping("/dto")
	public ResponseEntity<EnderecoDTO> updateEnderecoDTO(@Valid @RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO novoEnderecoDTO = enderecoService.updateEnderecoDTO(enderecoDTO);
		return new ResponseEntity<>(novoEnderecoDTO, HttpStatus.OK);
	}

	@Operation(summary = "Remover um endereço", responses = {
	@ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) {
		if (enderecoService.findEnderecoById(id) == null) {
			throw new NoSuchElementFoundException("O endereço com o ID: " + id + " não existe.");
		} else {
			enderecoService.deleteEndereco(id);
			return new ResponseEntity<>("Endereço deletado com sucesso", HttpStatus.OK);
		}
	}
}
