package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
