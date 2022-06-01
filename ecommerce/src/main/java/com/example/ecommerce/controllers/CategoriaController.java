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

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.CategoriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		if (categoriaList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(categoriaList, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoriaById(id);
		if (categoria == null) {
			throw new NoSuchElementFoundException("O " + id + " não existe");
		} else {
			return new ResponseEntity<>(categoria, HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.saveCategoria(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.updateCategoria(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable Integer id) {
		if (categoriaService.findCategoriaById(id) == null) {
			return new ResponseEntity<>("Esse categoria não foi encontrado", HttpStatus.NO_CONTENT);
		} else {
			categoriaService.deleteCategoria(id);
			return new ResponseEntity<>("Categoria deletado com sucesso", HttpStatus.OK);
		}
	}
}
