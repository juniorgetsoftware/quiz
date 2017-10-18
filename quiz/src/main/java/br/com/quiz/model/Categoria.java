package br.com.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "CATEGORIA")
@Table(name = "CATEGORIA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Categoria extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", nullable = false, unique = true)
	private String nome;

	@Lob()
	@Column(name = "DESCRICAO")
	private String descricao;

	//

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
