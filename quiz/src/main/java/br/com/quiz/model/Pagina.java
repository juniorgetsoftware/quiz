package br.com.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "PAGINA")
@Table(name = "PAGINA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pagina extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", nullable = false, unique = true)
	private String nome;

	@Column(name = "NOME_IDENTIFICADOR", nullable = false, unique = true)
	private String nomeIdentificador;

	@Column(name = "DESCRICAO")
	private String descricao;

	// GETTERS AND SETTERS

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeIdentificador() {
		return nomeIdentificador;
	}

	public void setNomeIdentificador(String nomeIdentificador) {
		this.nomeIdentificador = nomeIdentificador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "{Nome= " + nome + ", Nome Identificador= " + nomeIdentificador + ", Descrição= " + descricao + "}";
	}

}
