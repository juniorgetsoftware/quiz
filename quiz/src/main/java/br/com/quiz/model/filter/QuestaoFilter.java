package br.com.quiz.model.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;

import br.com.quiz.generic.dao.Alias;

public class QuestaoFilter implements Filter {

	private String enunciado;

	@Override
	public List<Criterion> restricoes() {
		ArrayList<Criterion> restricoes = new ArrayList<>();

		if (StringUtils.isNotBlank(this.getEnunciado())) {
			restricoes.add(Restrictions.like("enunciado", this.getEnunciado(), MatchMode.ANYWHERE));
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

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

}
