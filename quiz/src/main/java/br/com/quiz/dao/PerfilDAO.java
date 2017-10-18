package br.com.quiz.dao;

import java.io.Serializable;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Perfil;

public class PerfilDAO extends DAO<Perfil> implements Serializable {

	public PerfilDAO() {
		super(Perfil.class);
	}

	private static final long serialVersionUID = 1L;

}
