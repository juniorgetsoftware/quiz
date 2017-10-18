package br.com.quiz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

@Entity(name = "ALTERNATIVA")
@Table(name = "ALTERNATIVA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alternativa extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "DESCRICAO", nullable = false, unique = true)
	private String descricao;

	@Column(name = "EXPLICACAO", nullable = false)
	private String explicacao;

	@Type(type = "true_false")
	@Column(name = "ESTA_CORRETA", nullable = false)
	private Boolean estaCorreta = false;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "QUESTAO_ID", nullable=false)
	private Questao questao;

	//

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getEstaCorreta() {
		return estaCorreta;
	}

	public void setEstaCorreta(Boolean estaCorreta) {
		this.estaCorreta = estaCorreta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getExplicacao() {
		return explicacao;
	}

	public void setExplicacao(String explicacao) {
		this.explicacao = explicacao;
	}

	@Override
	public String toString() {
		return "Alternativa [descricao=" + descricao + "]";
	}
}
