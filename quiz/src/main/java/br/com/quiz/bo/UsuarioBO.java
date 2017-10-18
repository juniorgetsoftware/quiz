package br.com.quiz.bo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.quiz.dao.UsuarioDAO;
import br.com.quiz.generic.dao.Buscador;
import br.com.quiz.model.Usuario;
import br.com.quiz.model.filter.UsuarioFilter;
import br.com.quiz.util.MyLazyDataModel;

public class UsuarioBO implements Serializable, Buscador<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO dao;

	public void salvar(Usuario usuario) {
		validar(usuario);
		dao.salvar(usuario);
	}

	public void atualizar(Usuario usuario) {
		validar(usuario);
		dao.atualizar(usuario);

	}

	public void deletar(Usuario usuario) {
		dao.excluir(usuario);
	}

	public List<Usuario> listar() {
		return dao.listar(true);
	}

	public void validar(Usuario usuario) {

	}

	@Override
	public Usuario buscarPorId(Serializable id) {
		return dao.buscarPorId(id);
	}

	public MyLazyDataModel<Usuario> filtrar(UsuarioFilter usuarioFilter) {
		return dao.filtrar(dao, usuarioFilter);
	}

	public Usuario porEmail(String email) {
		return dao.porEmail(email);
	}

}
