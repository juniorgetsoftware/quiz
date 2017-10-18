package br.com.quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "PROVA")
@Table(name = "PROVA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prova extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME", nullable = false, unique = true)
	private String nome;

	@Lob
	@Basic
	@Column(name = "DESCRICAO")
	private String descricao;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "prova", cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.DETACH }, orphanRemoval = true, targetEntity = Questao.class)
	@Fetch(FetchMode.SUBSELECT)
	private List<Questao> questoes = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "COR")
	private Cor cor = Cor.GREEN;

	//

	public void adicionar(Questao p) {
		this.questoes.add(p);
	}

	public void remover(Questao p) {
		int i = questoes.indexOf(p);
		this.questoes.remove(i);
	}

	public void atualizar(Questao p) {
		int i = questoes.indexOf(p);
		this.questoes.set(i, p);
	}

	// GETTERS AND SETTERS

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

	@Override
	public String toString() {
		return "Nome = " + nome;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

}
