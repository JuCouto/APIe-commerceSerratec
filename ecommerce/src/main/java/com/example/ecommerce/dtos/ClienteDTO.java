package com.example.ecommerce.dtos;

public class ClienteDTO {

	private Integer idCliente;

	private String emailCliente;

	private String nomeCliente;

	private String cpfCliente;

	private String telefoneCliente;

	private EnderecoDTO enderecoDTO;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Integer idCliente, String emailCliente, String nomeCliente, String cpfCliente,
			String telefoneCliente, EnderecoDTO enderecoDTO) {
		super();
		this.idCliente = idCliente;
		this.emailCliente = emailCliente;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
		this.telefoneCliente = telefoneCliente;
		this.enderecoDTO = enderecoDTO;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEndereco(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

}
