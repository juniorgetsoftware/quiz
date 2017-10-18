package br.com.quiz.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity(name = "PROGRESSO")
@Table(name = "PROGRESSO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Progresso extends EntidadeGenerica {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Max(value = 100)
	@Min(value = 0)
	@Column(name = "PERCENTUAL", nullable = false, scale = 2, precision = 9, length = 3)
	private BigDecimal percentual;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "PROVA_ID")
	private Prova prova;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	// GETTERS AND SETTERS

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
