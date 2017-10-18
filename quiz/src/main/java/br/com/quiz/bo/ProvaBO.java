package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.ProvaDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Prova;
import br.com.quiz.model.filter.ProvaFilter;
import br.com.quiz.util.MyLazyDataModel;

public class ProvaBO implements Serializable, Buscador<Prova> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProvaDAO dao;

	public void salvar(Prova prova) {
		validar(prova);
		dao.salvar(prova);
	}

	public void atualizar(Prova prova) {
		validar(prova);
		dao.atualizar(prova);

	}

	public void deletar(Prova prova) {
		dao.excluir(prova);
	}

	public List<Prova> listar() {
		return dao.listar(true);
	}

	public void validar(Prova prova) {

	}

	@Override
	public Prova buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Prova> filtrar(ProvaFilter provaFilter) {
		return dao.filtrar(dao, provaFilter);
	}

	public List<Prova> autoCompletar(String seq) {
		return dao.listarAutocompletar("nome", true, "nome", seq);
	}

}
