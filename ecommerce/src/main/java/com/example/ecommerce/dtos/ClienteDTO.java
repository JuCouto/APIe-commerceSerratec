package com.example.ecommerce.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO {

	private Integer idCliente;

	private String emailCliente;

	private String nomeCliente;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private String cpfCliente;

	private String telefoneCliente;

	private EnderecoDTO enderecoDTO;

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
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	@Override
	public String toString() {
		return "\n Nome: " + nomeCliente+ " \n Email: " + emailCliente +  "\n Cpf: " + cpfCliente +
				"\n Telefone: " + telefoneCliente + enderecoDTO;
	}

}
