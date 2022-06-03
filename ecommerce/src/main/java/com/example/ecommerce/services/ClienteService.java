package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.ClienteDTO;
import com.example.ecommerce.dtos.EnderecoDTO;
import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	public List<Cliente> findAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
	}

	public ClienteDTO findClienteDTOById(Integer id) {
		Cliente cliente = clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
		ClienteDTO clienteDTO = new ClienteDTO();
		if (cliente != null) {
			clienteDTO = converterEntidadeParaDTO(cliente);
		}
		return clienteDTO;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente = converterDTOParaEntidade(clienteDTO);
		Cliente novoCliente = clienteRepository.save(cliente);

		return converterEntidadeParaDTO(novoCliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public ClienteDTO updateClienteDTO(ClienteDTO clienteDTO) {
		Cliente cliente = converterDTOParaEntidade(clienteDTO);
		Cliente novoCliente = clienteRepository.save(cliente);

		return converterEntidadeParaDTO(novoCliente);
	}

	public void deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
	}

	public ClienteDTO converterEntidadeParaDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCpfCliente(cliente.getCpfCliente());
		clienteDTO.setEmailCliente(cliente.getEmailCliente());
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setNomeCliente(cliente.getNomeCliente());
		clienteDTO.setTelefoneCliente(cliente.getTelefoneCliente());
		EnderecoDTO enderecoDTO = enderecoService.findEnderecoDTOById(cliente.getEndereco().getIdEndereco());
		clienteDTO.setEndereco(enderecoDTO);

		return clienteDTO;
	}

	public Cliente converterDTOParaEntidade(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCpfCliente(clienteDTO.getCpfCliente());
		cliente.setEmailCliente(clienteDTO.getEmailCliente());
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setNomeCliente(clienteDTO.getNomeCliente());
		cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
		Endereco endereco = enderecoService.findEnderecoById(clienteDTO.getEnderecoDTO().getIdEndereco());
		cliente.setEndereco(endereco);

		return cliente;
	}

}
