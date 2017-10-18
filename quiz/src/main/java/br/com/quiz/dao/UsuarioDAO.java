package br.com.quiz.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Status;
import br.com.quiz.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> implements Serializable {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	private static final long serialVersionUID = 1L;

	public Usuario porEmail(String email) {
		ArrayList<Criterion> restricoes = new ArrayList<>();
		restricoes.add(Restrictions.eq("status", Status.ATIVO));
		restricoes.add(Restrictions.eq("email", email));
		return buscarPorAtributosERestricoes(Usuario.class, restricoes, null);
	}

}
