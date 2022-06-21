package com.example.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ecommerce.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	Optional<Produto> findByNomeProdutoIgnoreCase(String descricaoProduto);
	
	Optional<Produto> findByDescricaoProdutoIgnoreCase(String descricaoProduto);
	
	   @Query("SELECT p FROM Produto p WHERE p.nomeProduto LIKE  %?1%"
	            + " OR p.descricaoProduto LIKE  %?1%")
	    public List<Produto> pesquisaIgonereCase(String palavraChave);
	   
	   @Query("SELECT p FROM Produto p WHERE LOWER(UNACCENT(p.nomeProduto)) LIKE LOWER(UNACCENT(CONCAT('%', :nome, '%')))")
	    List<Produto> findByNomeProdutoContainingIgnoreCase(String nome);  
	    
	    Page<Produto> findAll(Pageable pageable);
}

