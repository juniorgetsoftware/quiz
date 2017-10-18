package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.PerfilDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Perfil;
import br.com.quiz.model.filter.PerfilFilter;
import br.com.quiz.util.MyLazyDataModel;

public class PerfilBO implements Serializable, Buscador<Perfil> {

	private static final long serialVersionUID = 1L;

	@Inject
	PerfilDAO dao;

	public void salvar(Perfil perfil) {
		validar(perfil);
		dao.salvar(perfil);
	}

	public void atualizar(Perfil perfil) {
		validar(perfil);
		dao.atualizar(perfil);

	}

	public void deletar(Perfil perfil) {
		dao.excluir(perfil);
	}

	public List<Perfil> listar() {
		return dao.listar(true);
	}

	public void validar(Perfil perfil) {

	}

	@Override
	public Perfil buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Perfil> filtrar(PerfilFilter perfilFilter) {
		return dao.filtrar(dao, perfilFilter);
	}

}
