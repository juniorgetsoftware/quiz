package br.com.quiz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "PERFIL")
@Table(name = "PERFIL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Perfil extends EntidadeGenerica {

	private static final long serialVersionUID = 8683670150324870932L;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH })
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "PERFIL_PAGINA", joinColumns = { @JoinColumn(name = "PERFIL_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PAGINA_ID") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Pagina> paginas;

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

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

}
