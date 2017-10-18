package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.QuestaoDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Questao;
import br.com.quiz.model.filter.QuestaoFilter;
import br.com.quiz.util.MyLazyDataModel;

public class QuestaoBO implements Serializable, Buscador<Questao> {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuestaoDAO dao;

	public void salvar(Questao questao) {
		validar(questao);
		dao.salvar(questao);
	}

	public void atualizar(Questao questao) {
		validar(questao);
		dao.atualizar(questao);

	}

	public void deletar(Questao questao) {
		dao.excluir(questao);
	}

	public List<Questao> listar() {
		return dao.listar(true);
	}

	public void validar(Questao questao) {

	}

	@Override
	public Questao buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Questao> filtrar(QuestaoFilter questaoFilter) {
		return dao.filtrar(dao, questaoFilter);
	}

}
