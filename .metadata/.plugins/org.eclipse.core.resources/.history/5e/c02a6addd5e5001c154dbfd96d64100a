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
import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.exceptions.EmptyListException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
import com.example.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
@Validated
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco() {
		List<Endereco> enderecoList = enderecoService.findAllEndereco();
		if (enderecoList.isEmpty()) {
			throw new EmptyListException("A lista de endereço está vazia");
		} else {
			return new ResponseEntity<>(enderecoList, HttpStatus.OK);
		}
	}

	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> consultarDadosPorCep(String cep) {
		EnderecoDTO cadastroCepDTO = enderecoService.consultarCep(cep).converterEntidadeParaDTO();
		return new ResponseEntity<>(cadastroCepDTO, HttpStatus.OK);

	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<EnderecoDTO> findEnderecoDTOById(@PathVariable Integer id) {
		EnderecoDTO enderecoDTO = enderecoService.findEnderecoDTOById(id);
		if (enderecoDTO == null) {
			throw new NoSuchElementFoundException("Não existe nenhum endereço com o ID: " + id + ".");
		} else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
		}

	}

	@PostMapping("/dto")
	public ResponseEntity<EnderecoDTO> saveEnderecoDTO(@Valid @RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO novoEnderecoDTO = enderecoService.saveEnderecoDTO(enderecoDTO);
		return new ResponseEntity<>(novoEnderecoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/dto")
	public ResponseEntity<EnderecoDTO> updateEnderecoDTO(@Valid @RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO novoEnderecoDTO = enderecoService.updateEnderecoDTO(enderecoDTO);
		return new ResponseEntity<>(novoEnderecoDTO, HttpStatus.OK);
	}

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
