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

import com.example.ecommerce.dtos.CategoriaDTO;
import com.example.ecommerce.dtos.CategoriaListaProdutoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Validated
@Tag(name = "Categorias", description = "Endpoints")

public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@Operation(summary = "Listar todas as categorias", responses = {
	@ApiResponse(responseCode = "200", description = "Listar todas", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		if (categoriaList.isEmpty()) {
			throw new EmptyListException("A lista de categoria está vazia.");
		} else {
			return new ResponseEntity<>(categoriaList, HttpStatus.OK);
		}
	}

	@Operation(summary = "Listar uma categoria", responses = {
	@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/dto/{id}")
	public ResponseEntity<CategoriaDTO> findCategoriaDTOById(@PathVariable Integer id) {
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(id);
		if (categoriaDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhuma categoria com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
		}

	}
	
	@Operation(summary = "Listar uma categoria e sua lista de produto", responses = {
	@ApiResponse(responseCode = "200", description = "Buscado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })			
	@GetMapping("/produto/dto/{id}")
	public ResponseEntity<CategoriaListaProdutoDTO> findCategoriaListProdutoDTOById(@PathVariable Integer id) {
		CategoriaListaProdutoDTO categoriaDTO = categoriaService.findCategoriaListaProdutoDTOById(id);
		if (categoriaDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhuma categoria com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
		}
	}

	@Operation(summary = "Inserir os dados de categoria", responses = {
	@ApiResponse(responseCode = "200", description = "Salvo com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })					
	@PostMapping("/dto")
	public ResponseEntity<CategoriaDTO> saveCategoriaDTO(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDTO novaCategoriaDTO = categoriaService.saveCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(novaCategoriaDTO, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Atualizar os dados de categoria", responses = {
	@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })							
	@PutMapping("/dto")
	public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDTO novoCategoriaDTO = categoriaService.updateCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(novoCategoriaDTO, HttpStatus.OK);
	}
	
	@Operation(summary = "Remover uma categoria", responses = {
	@ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })										
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Integer id) {
		if (categoriaService.findCategoriaById(id) == null) {
			throw new NoSuchElementFoundException("A categoria com o ID: " + id + " não existe.");
		} else {
			categoriaService.deleteCategoria(id);
			return new ResponseEntity<>("Categoria deletada com sucesso.", HttpStatus.OK);
		}
	}
}
