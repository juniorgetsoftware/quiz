package br.com.quiz.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "USUARIO")
@Table(name = "USUARIO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Usuario extends EntidadeGenerica implements Serializable {

	private static final long serialVersionUID = 1L;

	public Usuario() {
	}

	public Usuario(String nomeCompleto, String email) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.email = email;
	}

	@NotEmpty
	@NotBlank
	@Column(name = "NOME_COMPLETO", nullable = false)
	private String nomeCompleto;

	@Email
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@OneToOne
	@JoinColumn(name = "PERFIL_ID", nullable = false)
	private Perfil perfil;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "USUARIO_PAGINA", joinColumns = { @JoinColumn(name = "USUARIO_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PAGINA_ID") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Pagina> paginas;

	// GETTERS AND SETTERS

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nomeCompleto=" + nomeCompleto + ", email=" + email + "]";
	}

}
