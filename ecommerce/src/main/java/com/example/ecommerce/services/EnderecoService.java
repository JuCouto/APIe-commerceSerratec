package com.example.ecommerce.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ecommerce.dtos.CadastroCepDTO;
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
		}
		return enderecoDTO;
	}

	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public EnderecoDTO saveEnderecoDTO(EnderecoDTO enderecoDTO) {
		Endereco endereco = enderecoRepository.save(enderecoDTO.converterDTOParaEntidade());
		return endereco.converterEntidadeParaDTO();
	}

	public Endereco updateEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Endereco saveEnderecoCep(String cep) {
		Endereco novoEndereco = consultarCep(cep);
		return enderecoRepository.save(novoEndereco);
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

	
	  public CadastroCepDTO consultarDadosPorCep(String cep) { RestTemplate rt =
	  new RestTemplate(); String uri = "viacep.com.br/ws/{cep}/json/"; Map<String,
	  String> params = new HashMap<String, String>(); params.put("cep", cep);
	  CadastroCepDTO ccd = rt.getForObject(uri, CadastroCepDTO.class, params);
	  
	  return ccd; }
	  
	 /* public Endereco enderecoCep(String cep) { CadastroCepDTO cert =
	  consultarDadosPorCep(cep); Endereco endereco = new Endereco();
	  BeanUtils.copyProperties(cert, endereco); return endereco; */
	 
}
