package br.com.quiz.dao;

import java.io.Serializable;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Pagina;

public class PaginaDAO extends DAO<Pagina> implements Serializable {

	private static final long serialVersionUID = 1L;

	public PaginaDAO() {
		super(Pagina.class);
	}

}
