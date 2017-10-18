package br.com.quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "QUESTAO")
@Table(name = "QUESTAO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Questao extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ENUNCIADO", nullable = false, unique = true)
	private String enunciado;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "PROVA_ID")
	private Prova prova = new Prova();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questao", cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.DETACH }, orphanRemoval = true, targetEntity = Alternativa.class)
	@Fetch(FetchMode.SUBSELECT)
	private List<Alternativa> alternativas = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "CATEGORIA_ID", insertable = true, updatable = true)
	private Categoria categoria = new Categoria();

	@Lob
	@Column(name = "DESCRICAO")
	private String descricao;

	//

	public void adicionar(Alternativa p) {
		this.alternativas.add(p);
	}

	public void remover(Alternativa p) {
		int i = alternativas.indexOf(p);
		this.alternativas.remove(i);
	}

	public void atualizar(Alternativa p) {
		int i = alternativas.indexOf(p);
		this.alternativas.set(i, p);
	}

	//

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Questao [enunciado=" + enunciado + ", descricao=" + descricao + "]";
	}
}
