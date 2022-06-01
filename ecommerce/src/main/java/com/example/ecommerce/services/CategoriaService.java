package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ArquivoService arquivoService;
	
	public List<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findCategoriaById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
}
