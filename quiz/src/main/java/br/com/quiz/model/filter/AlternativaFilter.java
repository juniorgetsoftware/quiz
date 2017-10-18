package br.com.quiz.model.filter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.quiz.generic.dao.Alias;

public class AlternativaFilter implements Filter {

	private String descricao;

	@Override
	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		restricoes.add(Restrictions.like("descricao", descricao));
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
