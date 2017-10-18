package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.UsuarioBO;
import br.com.quiz.model.Status;
import br.com.quiz.model.Usuario;
import br.com.quiz.model.filter.UsuarioFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private MyLazyDataModel<Usuario> usuariosLazy;
	private UsuarioFilter usuarioFilter;
	private Boolean mudarSenha;

	@Inject
	private UsuarioBO bo;

	@PostConstruct
	public void init() {
		Usuario usuario = BuscaObjeto.comParametroGET(Usuario.class, "id", bo);
		this.usuario = usuario;
	}

	public String salvar() {
		bo.salvar(usuario);
		MessageUtil.message("msg", MessageUtil.cadastrar(), usuario.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		usuario = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(usuario);
		MessageUtil.message("msg", MessageUtil.editar(), usuario.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		usuario = null;
		return "/list/usuario?faces-redirect=true";
	}

	public String filtrar() {
		usuariosLazy = bo.filtrar(usuarioFilter);
		return null;
	}

	public void perfilSelecionado() {
		this.usuario.setPaginas(usuario.getPerfil().getPaginas());
	}

	public String deletar() {
		bo.deletar(usuario);
		usuario = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	// GETTERS AND SETTERS

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<>();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public MyLazyDataModel<Usuario> getUsuariosLazy() {
		if (usuariosLazy == null) {
			usuariosLazy = bo.filtrar(usuarioFilter);
		}
		return usuariosLazy;
	}

	public void setUsuariosLazy(MyLazyDataModel<Usuario> usuariosLazy) {
		this.usuariosLazy = usuariosLazy;
	}

	public UsuarioFilter getUsuarioFilter() {
		if (usuarioFilter == null) {
			usuarioFilter = new UsuarioFilter();
		}
		return usuarioFilter;
	}

	public void setUsuarioFilter(UsuarioFilter usuarioFilter) {
		this.usuarioFilter = usuarioFilter;
	}

	public Boolean getMudarSenha() {
		return mudarSenha;
	}

	public void setMudarSenha(Boolean mudarSenha) {
		this.mudarSenha = mudarSenha;
	}

}
