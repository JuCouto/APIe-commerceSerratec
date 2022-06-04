package com.example.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findByCpfCliente (String cpf);
	
	Optional<Cliente> findByEmailCliente (String email);
}
