package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
