package br.com.quiz.model.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.quiz.generic.dao.Alias;

public class UsuarioFilter implements Filter {

	private String nome;
	private String nomeIdentificador;

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

	@Override
	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();

		if (StringUtils.isNotBlank(this.getNome())) {
			restricoes.add(Restrictions.like("nomeCompleto", this.getNome(), MatchMode.ANYWHERE));
		}

		return restricoes;
	}

	@Override
	public List<Projection> projecoes() {
		return null;
	}

	@Override
	public List<Alias> aliases() {
		return null;
	}

	@Override
	public Boolean usarDistinct() {
		return null;
	}

}
