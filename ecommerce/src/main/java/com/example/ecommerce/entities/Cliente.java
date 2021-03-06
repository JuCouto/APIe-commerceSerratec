package com.example.ecommerce.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "cliente")
@JsonIdentityInfo(scope = Cliente.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Email(message = "E-mail inválido")
    //@NotBlank(message = "E-mail não pode estar em branco.")
    @Schema(example = "exemplo@exemplo.com.br")
    @Column(name = "email")
    private String emailCliente;

	@Column(name = "nome_completo")
	//@NotBlank(message = "O nome não pode estar em branco.")
	private String nomeCliente;
	
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@CPF(message = "CPF inválido.")
    @NotBlank(message = "CPF não pode estar em branco.")
    @Schema(example = "XXX.XXX.XXX-XX")
    @Column(name = "cpf")
    private String cpfCliente;

	@Size(min = 11, max = 15, message = "O telefone deve ter entre 11 e 15 caracteres.")
    @Schema(example = "(DDD) 90000-0000")
    @Column(name = "telefone")
    private String telefoneCliente;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> listaPedido;
	
	@NotBlank(message = "A senha não pode estar em branco.")
	@Column(name= "senha")
	private String senha;
	
	@Column(name = "admin")
	private Boolean admin;

	@ManyToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	public Integer getIdCliente() {
		return idCliente;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", emailCliente=" + emailCliente + ", nomeCliente=" + nomeCliente
				+ ", cpfCliente=" + cpfCliente + ", telefoneCliente=" + telefoneCliente + ", listaPedido=" + listaPedido
				+ ", endereco=" + endereco + "]";
	}

}
