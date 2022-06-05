package com.example.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto() {
		List<Produto> produtoList = produtoService.findAllProduto();
		if (produtoList.isEmpty()) {
			throw new EmptyListException("A lista de produto está vazia.");
		} else {
			return new ResponseEntity<>(produtoList, HttpStatus.OK);
		}
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> findProdutoDTOById(@PathVariable Integer id) {
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(id);
		if (produtoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum produto com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@Valid @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}

	@PostMapping(value = "/dto/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@Valid @RequestPart("produtoDTO") String produtoDTO,
			@RequestPart("file") MultipartFile file) throws Exception {
		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoComFotoDTO(produtoDTO, file);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/dto")
	public ResponseEntity<ProdutoDTO> updateProdutoDTO(@Valid @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO novoProdutoDTO = produtoService.updateProdutoDTO(produtoDTO);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable Integer id) {
		if (produtoService.findProdutoById(id) == null) {
			throw new NoSuchElementFoundException("O produto com o ID: " + id + " não existe.");
		} else {
			produtoService.deleteProduto(id);
			return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
		}
	}
}
