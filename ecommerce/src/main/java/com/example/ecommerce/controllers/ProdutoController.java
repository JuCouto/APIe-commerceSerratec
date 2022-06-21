package com.example.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.dtos.ProdutoDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.Produto;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Validated
@CrossOrigin
@Tag(name = "Produtos", description = "Endpoints")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@Operation(summary = "Listar todos os produtos", responses = {
			@ApiResponse(responseCode = "200", description = "Listar todos", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "Não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/page")
	public ResponseEntity<Page<Produto>> findAllProdutoPage(Pageable pageable) {
		Page<Produto> produtoList = produtoService.findAllProdutoPage(pageable);
		if (produtoList.isEmpty()) {
			throw new EmptyListException("A lista de produto está vazia.");
		} else {
			return new ResponseEntity<>(produtoList, HttpStatus.OK);
		}
	}
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto() {
		List<Produto> produtoList = produtoService.findAllProduto();
		if (produtoList.isEmpty()) {
			throw new EmptyListException("A lista de produto está vazia.");
		} else {
			return new ResponseEntity<>(produtoList, HttpStatus.OK);
		}
	}

	@GetMapping("/filtro")
	@ResponseBody
	public List<Produto> filtro(@RequestParam String palavraChave) {
		List<Produto> listaProdutos = produtoService.listAll(palavraChave);
		return listaProdutos;
	}

	@GetMapping("/filtroTeste")
	@ResponseBody
	public List<Produto> filtroTeste(@RequestParam String palavraChave) {
		List<Produto> listaProdutos = produtoService.listAllContains(palavraChave);
		return listaProdutos;
	}

	@Operation(summary = "Listar um produto", responses = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@GetMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> findProdutoDTOById(@PathVariable Integer id) {
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(id);
		if (produtoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum produto com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/dto/nome/{nome}")
	public ResponseEntity<ProdutoDTO> findProdutoDTOByNome(@PathVariable String nome) {
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOByNome(nome);
		if (produtoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum produto com o nome: " + nome + ".");
		} else {
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/dto/descricao/{descricao}")
	public ResponseEntity<ProdutoDTO> findProdutoDTOByDescricao(@PathVariable String descricao) {
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOByDescricao(descricao);
		if (produtoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum produto com a descricao: " + descricao + ".");
		} else {
			return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
		}
	}

	@Operation(summary = "Inserir os dados de produto", responses = {
			@ApiResponse(responseCode = "200", description = "Salvo com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@Valid @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Inserir os dados de produto com foto", responses = {
			@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PostMapping(value = "/dto/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@Valid @RequestPart("produtoDTO") String produtoDTO,
			@RequestPart("file") MultipartFile file) throws Exception {
		ProdutoDTO novoProdutoDTO = produtoService.saveProdutoComFotoDTO(produtoDTO, file);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar os dados de produto com foto", responses = {
			@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PutMapping(value = "/dto/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ProdutoDTO> updateProdutoDTOComFoto(@Valid @RequestPart("produtoDTO") String produtoDTO,
			@RequestPart("file") MultipartFile file) throws Exception {
		ProdutoDTO novoProdutoDTO = produtoService.updateProdutoComFotoDTO(produtoDTO, file);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualizar os dados de produto", responses = {
			@ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@PutMapping("/dto")
	public ResponseEntity<ProdutoDTO> updateProdutoDTO(@Valid @RequestBody ProdutoDTO produtoDTO) {
		ProdutoDTO novoProdutoDTO = produtoService.updateProdutoDTO(produtoDTO);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.OK);
	}
	
	@PatchMapping("/dto/{id}")
	public ResponseEntity<ProdutoDTO> updateProdutoPatchDTO(@Valid @PathVariable Integer id, @RequestBody Map<Object, Object> object) {
		ProdutoDTO novoProdutoDTO = produtoService.updateProdutoPacthDTO(id, object);
		return new ResponseEntity<>(novoProdutoDTO, HttpStatus.OK);
	}

	@Operation(summary = "Remover um produto", responses = {
			@ApiResponse(responseCode = "200", description = "Deletado com sucesso", content = {
					@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) }) })
	@ApiResponse(responseCode = "400", description = "ID Inválido", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
	@ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = {
			@Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Categoria.class)) })
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
