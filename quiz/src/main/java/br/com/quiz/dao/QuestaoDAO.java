package br.com.quiz.dao;

import br.com.quiz.generic.dao.DAO;
import br.com.quiz.model.Questao;

public class QuestaoDAO extends DAO<Questao> {

	public QuestaoDAO() {
		super(Questao.class);
	}

	private static final long serialVersionUID = 1L;

}
