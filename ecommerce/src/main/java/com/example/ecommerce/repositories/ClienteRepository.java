package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
