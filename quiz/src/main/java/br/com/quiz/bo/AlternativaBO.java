package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.AlternativaDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.filter.AlternativaFilter;
import br.com.quiz.util.MyLazyDataModel;

public class AlternativaBO implements Serializable, Buscador<Alternativa> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlternativaDAO dao;

	public void salvar(Alternativa alternativa) {
		validar(alternativa);
		dao.salvar(alternativa);
	}

	public void atualizar(Alternativa alternativa) {
		validar(alternativa);
		dao.atualizar(alternativa);

	}

	public void deletar(Alternativa alternativa) {
		dao.excluir(alternativa);
	}

	public List<Alternativa> listar() {
		return dao.listar(true);
	}

	public void validar(Alternativa alternativa) {

	}

	@Override
	public Alternativa buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Alternativa> filtrar(AlternativaFilter alternativaFilter) {
		return dao.filtrar(dao, alternativaFilter);
	}

}
