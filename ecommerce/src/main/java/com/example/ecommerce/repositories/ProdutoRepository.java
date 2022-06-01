package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
