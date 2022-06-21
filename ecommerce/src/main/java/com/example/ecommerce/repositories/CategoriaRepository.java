package com.example.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByNomeCategoriaIgnoreCase(String nome);
	
	Optional<Categoria> findByDescricaoCategoria(String descricao);

	

}
