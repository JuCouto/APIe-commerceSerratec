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
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
@Validated
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		if (categoriaList.isEmpty()) {
			throw new EmptyListException("A lista de categoria está vazia.");
		} else {
			return new ResponseEntity<>(categoriaList, HttpStatus.OK);
		}
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<CategoriaDTO> findCategoriaDTOById(@PathVariable Integer id) {
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(id);
		if (categoriaDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhuma categoria com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/dto")
	public ResponseEntity<CategoriaDTO> saveCategoriaDTO(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDTO novaCategoriaDTO = categoriaService.saveCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(novaCategoriaDTO, HttpStatus.CREATED);
	}

	@PutMapping("/dto")
	public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDTO novoCategoriaDTO = categoriaService.updateCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(novoCategoriaDTO, HttpStatus.OK);
	}

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
