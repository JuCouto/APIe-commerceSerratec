package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.CategoriaDTO;
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
	
	public CategoriaDTO findCategoriaDTOById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		if (categoria != null) {
			categoriaDTO = converterEntidadeParaDTO(categoria);
			return categoriaDTO;
		}
		return null;
	}

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		Categoria novaCategoria = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(novaCategoria);
	}

	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public CategoriaDTO updateCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterDTOParaEntidade(categoriaDTO);
		Categoria novaCategoria = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(novaCategoria);
	}

	public void deleteCategoria(Integer id) {
		categoriaRepository.deleteById(id);
	}

	public CategoriaDTO converterEntidadeParaDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());

		return categoriaDTO;
	}

	public Categoria converterDTOParaEntidade(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());

		return categoria;
	}

}
