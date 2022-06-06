package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ecommerce.dtos.EnderecoDTO;
import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> findAllEndereco() {
		return enderecoRepository.findAll();
	}

	public Endereco findEnderecoById(Integer id) {
		return enderecoRepository.findById(id).isPresent() ? enderecoRepository.findById(id).get() : null;
	}

	public EnderecoDTO findEnderecoDTOById(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).isPresent() ? enderecoRepository.findById(id).get() : null;
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		if (endereco != null) {
			enderecoDTO = endereco.converterEntidadeParaDTO();
			return enderecoDTO;
		}
		return null;
	}

	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public EnderecoDTO saveEnderecoDTO(EnderecoDTO enderecoDTO) {
		Endereco endereco = enderecoRepository.save(enderecoDTO.converterDTOParaEntidade());
		return endereco.converterEntidadeParaDTO();
	}

	public EnderecoDTO updateEnderecoDTO(EnderecoDTO enderecoDTO) {
		Endereco endereco = enderecoRepository.save(enderecoDTO.converterDTOParaEntidade());
		return endereco.converterEntidadeParaDTO();
	}

	public void deleteEndereco(Integer id) {
		enderecoRepository.deleteById(id);
	}

	public Endereco consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject("https://viacep.com.br/ws/" + cep + "/json/", Endereco.class);
        return endereco;

    }
	 
}
