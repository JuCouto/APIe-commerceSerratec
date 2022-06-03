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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco() {
		List<Endereco> enderecoList = enderecoService.findAllEndereco();
		if (enderecoList.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(enderecoList, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findEnderecoById(@PathVariable Integer id) {
		Endereco endereco = enderecoService.findEnderecoById(id);
		if (endereco == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(endereco, HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
		Endereco novoEndereco = enderecoService.saveEndereco(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}
	@PostMapping("/{cep}")
	public ResponseEntity<Endereco> saveEnderecoCep(@PathVariable String cep){
		Endereco novoEndereco = enderecoService.saveEnderecoCep(cep);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco) {
		Endereco novoEndereco = enderecoService.updateEndereco(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) {
		if (enderecoService.findEnderecoById(id) == null) {
			return new ResponseEntity<>("Esse endereco não foi encontrado", HttpStatus.NO_CONTENT);
		} else {
			enderecoService.deleteEndereco(id);
			return new ResponseEntity<>("Endereco deletado com sucesso", HttpStatus.OK);
		}
	}
}
