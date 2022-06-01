package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAllClientes() {
		return clienteRepository.findAll();
	}
	
	public Cliente findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
	}
	
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public void deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
