package com.example.ecommerce.dtos;

import com.example.ecommerce.entities.Endereco;

public class EnderecoDTO {
	private Integer idEndereco;

	private String cep;

	private String rua;

	private String bairro;

	private String cidade;

	private String numero;

	private String complemento;

	private String uf;

	//private List<ClienteDTO> clienteList;

	public EnderecoDTO() {
		super();
	}

	public EnderecoDTO(Integer idEndereco, String cep, String rua, String bairro, String cidade, String numero,
			String complemento, String uf) {
		super();
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.numero = numero;
		this.complemento = complemento;
		this.uf = uf;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Endereco converterDTOParaEntidade() {

		return new Endereco(idEndereco, cep, rua, bairro, cidade, numero, complemento, uf);
	}

}
