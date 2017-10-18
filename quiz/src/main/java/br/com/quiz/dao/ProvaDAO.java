package br.com.quiz.dao;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Prova;

public class ProvaDAO extends DAO<Prova> {

	public ProvaDAO() {
		super(Prova.class);
	}

	private static final long serialVersionUID = 1L;

}
