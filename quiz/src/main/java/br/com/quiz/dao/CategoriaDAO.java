package br.com.quiz.dao;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Categoria;

public class CategoriaDAO extends DAO<Categoria> {

	public CategoriaDAO() {
		super(Categoria.class);
	}

	private static final long serialVersionUID = 1L;

}
