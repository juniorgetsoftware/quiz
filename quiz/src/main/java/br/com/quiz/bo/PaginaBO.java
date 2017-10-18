package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.PaginaDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Pagina;
import br.com.quiz.model.filter.PaginaFilter;
import br.com.quiz.util.MyLazyDataModel;

public class PaginaBO implements Serializable, Buscador<Pagina> {

	private static final long serialVersionUID = 1L;

	@Inject
	private PaginaDAO dao;

	public void salvar(Pagina pagina) {
		validar(pagina);
		dao.salvar(pagina);
	}

	public void atualizar(Pagina pagina) {
		validar(pagina);
		dao.atualizar(pagina);

	}

	public void deletar(Pagina pagina) {
		dao.excluir(pagina);
	}

	public List<Pagina> listar() {
		return dao.listar(true);
	}

	public void validar(Pagina pagina) {

	}

	@Override
	public Pagina buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Pagina> filtrar(PaginaFilter paginaFilter) {
		return dao.filtrar(dao, paginaFilter);
	}

}
