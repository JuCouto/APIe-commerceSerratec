package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> findAllEndereco() {
		return enderecoRepository.findAll();
	}
	
	public Endereco findEnderecoById(Integer id) {
		return enderecoRepository.findById(id).isPresent() ? enderecoRepository.findById(id).get() : null;
	}
	
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public void deleteEndereco(Integer id) {
		enderecoRepository.deleteById(id);
	}
}
