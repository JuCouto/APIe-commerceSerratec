package com.example.ecommerce.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "categoria")
@JsonIdentityInfo(scope = Categoria.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCategoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")	
	private Integer idCategoria;

	@NotBlank(message = "O nome da categoria precisa ser preenchido.")
	@Schema(example = "Esporte")
	@Column(name = "nome")
	private String nomeCategoria;

	@NotBlank(message = "A descrição da categoria precisa ser preenchida.")
	@Schema(example = "Artigos esportivos")
	@Column(name = "descricao")
	private String descricaoCategoria;

	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtoList;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", descricaoCategoria="
				+ descricaoCategoria + ", produtoList=" + produtoList + "]";
	}

}
