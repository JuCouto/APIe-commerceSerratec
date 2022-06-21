package com.example.ecommerce.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dtos.ClienteDTO;
import com.example.ecommerce.dtos.EnderecoDTO;
import com.example.ecommerce.entities.Cliente;
import com.example.ecommerce.entities.Endereco;
import com.example.ecommerce.exceptions.InvalidCpfException;
import com.example.ecommerce.exceptions.InvalidEmailException;
import com.example.ecommerce.exceptions.NoSuchElementFoundException;
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
			return clienteDTO;
		}
		return null;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public ClienteDTO saveClienteDTO(ClienteDTO clienteDTO) {
		validarCPF(clienteDTO.getCpfCliente());
		validarEmail(clienteDTO.getEmailCliente());
		Endereco endereco = new Endereco();
		endereco.setCep(clienteDTO.getEnderecoDTO().getCep());
		endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
		endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
		endereco.setBairro(clienteDTO.getEnderecoDTO().getBairro());
		endereco.setCidade(clienteDTO.getEnderecoDTO().getCidade());
		endereco.setRua(clienteDTO.getEnderecoDTO().getRua());
		endereco.setUf(clienteDTO.getEnderecoDTO().getUf());
		Endereco saveEndereco = enderecoService.saveEndereco(endereco);
		clienteDTO.setEnderecoDTO(saveEndereco.converterEntidadeParaDTO());
		Cliente cliente = converterDTOParaEntidade(clienteDTO);
		
		Cliente novoCliente = clienteRepository.save(cliente);
		
		return converterEntidadeParaDTO(novoCliente);
	}

	public ClienteDTO updateClienteDTO(ClienteDTO clienteDTO) {
		validarEmail(clienteDTO.getEmailCliente());
		Endereco endereco = enderecoService.consultarCep(clienteDTO.getEnderecoDTO().getCep());
		endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
		endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
		Endereco saveEndereco = enderecoService.saveEndereco(endereco);
		clienteDTO.setEnderecoDTO(saveEndereco.converterEntidadeParaDTO());
		Cliente cliente = converterDTOParaEntidade(clienteDTO);
		
		Cliente novoCliente = clienteRepository.save(cliente);
		
		return converterEntidadeParaDTO(novoCliente);
	}
	
	public ClienteDTO updateClientePatchDTO(Integer id,  Map<Object, Object> object) {
		Cliente cliente = findClienteById(id);
		if (cliente == null) {
			throw new NoSuchElementFoundException("Não existe nenhum cliente com o ID: " + id + ".");
		}
		object.forEach((key, value) -> {
			Field field = ReflectionUtils.findRequiredField(Cliente.class, (String)key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, cliente, value);
		});
		
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
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setAdmin(cliente.getAdmin() == null ? Boolean.FALSE : Boolean.TRUE);
		clienteDTO.setSenha(cliente.getSenha());
		EnderecoDTO enderecoDTO = enderecoService.findEnderecoDTOById(cliente.getEndereco().getIdEndereco());
		clienteDTO.setEnderecoDTO(enderecoDTO);

		return clienteDTO;
	}

	public Cliente converterDTOParaEntidade(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCpfCliente(clienteDTO.getCpfCliente());
		cliente.setEmailCliente(clienteDTO.getEmailCliente());
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setNomeCliente(clienteDTO.getNomeCliente());
		cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setAdmin(clienteDTO.getAdmin());
		cliente.setSenha(clienteDTO.getSenha());
		Endereco enderecoNovo= new Endereco();
		enderecoNovo.converterEntidadeParaDTO();
		cliente.setEndereco(clienteDTO.getEnderecoDTO().converterDTOParaEntidade());

		return cliente;
	}
	
	private void validarCPF(String cpf) {
		var cliente = clienteRepository.findByCpfCliente(cpf);
		if (cliente.isPresent()) {
			throw new InvalidCpfException("Esse cpf já existe no bando de dados");
		}
	}
	
	private void validarEmail(String email) {
		var cliente = clienteRepository.findByEmailCliente(email);
		if (cliente.isPresent()) {
			throw new InvalidEmailException("Esse E-mail já existe no bando de dados");
		}
	}
}