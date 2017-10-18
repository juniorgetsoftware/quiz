package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.CategoriaDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Categoria;
import br.com.quiz.model.filter.CategoriaFilter;
import br.com.quiz.util.MyLazyDataModel;

public class CategoriaBO implements Serializable, Buscador<Categoria> {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriaDAO dao;

	public void salvar(Categoria categoria) {
		validar(categoria);
		dao.salvar(categoria);
	}

	public void atualizar(Categoria categoria) {
		validar(categoria);
		dao.atualizar(categoria);
	}
	
	public List<Categoria> autoCompletar(String seq) {
		return dao.listarAutocompletar("nome", true, "nome", seq);
	}

	public void deletar(Categoria categoria) {
		dao.excluir(categoria);
	}

	public List<Categoria> listar() {
		return dao.listar(true);
	}

	public void validar(Categoria categoria) {

	}

	@Override
	public Categoria buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Categoria> filtrar(CategoriaFilter categoriaFilter) {
		return dao.filtrar(dao, categoriaFilter);
	}

}
