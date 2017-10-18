package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.PerfilBO;
import br.com.quiz.model.Perfil;
import br.com.quiz.model.Status;
import br.com.quiz.model.filter.PerfilFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class PerfilMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Perfil perfil;
	private List<Perfil> perfils;
	private MyLazyDataModel<Perfil> perfilsLazy;
	private PerfilFilter perfilFilter;

	@Inject
	private PerfilBO bo;

	@PostConstruct
	public void init() {
		Perfil perfil = BuscaObjeto.comParametroGET(Perfil.class, "id", bo);
		this.perfil = perfil;
	}

	public String salvar() {
		bo.salvar(perfil);
		MessageUtil.message("msg", MessageUtil.cadastrar(), perfil.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		perfil = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(perfil);
		MessageUtil.message("msg", MessageUtil.editar(), perfil.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		perfil = null;
		return "/list/perfil?faces-redirect=true";
	}

	public String filtrar() {
		perfilsLazy = bo.filtrar(perfilFilter);
		return null;
	}

	public String deletar() {
		bo.deletar(perfil);
		perfil = null;
		return null;
	}

	public List<Perfil> perfis() {
		return bo.listar();
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	// GETTERS AND SETTERS

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfils() {
		if (perfils == null) {
			perfils = new ArrayList<>();
		}
		return perfils;
	}

	public void setPerfils(List<Perfil> perfils) {
		this.perfils = perfils;
	}

	public MyLazyDataModel<Perfil> getPerfilsLazy() {
		if (perfilsLazy == null) {
			perfilsLazy = bo.filtrar(perfilFilter);
		}
		return perfilsLazy;
	}

	public void setPerfilsLazy(MyLazyDataModel<Perfil> perfilsLazy) {
		this.perfilsLazy = perfilsLazy;
	}

	public PerfilFilter getPerfilFilter() {
		if (perfilFilter == null) {
			perfilFilter = new PerfilFilter();
		}
		return perfilFilter;
	}

	public void setPerfilFilter(PerfilFilter perfilFilter) {
		this.perfilFilter = perfilFilter;
	}

}
