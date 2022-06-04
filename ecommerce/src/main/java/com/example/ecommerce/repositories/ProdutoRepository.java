package com.example.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Optional<Produto> findByDescricaoProduto(String descricaoProduto);
}
