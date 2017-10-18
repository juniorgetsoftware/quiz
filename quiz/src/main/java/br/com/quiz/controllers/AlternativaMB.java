package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.AlternativaBO;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.filter.AlternativaFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class AlternativaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Alternativa alternativa;
	private List<Alternativa> alternativas;
	private MyLazyDataModel<Alternativa> alternativasLazy;
	private AlternativaFilter alternativaFilter;

	@Inject
	private AlternativaBO bo;

	@PostConstruct
	public void init() {
		Alternativa alternativa = BuscaObjeto.comParametroGET(Alternativa.class, "id", bo);
		this.alternativa = alternativa;
	}

	public String salvar() {
		bo.salvar(alternativa);
		MessageUtil.message("msg", MessageUtil.cadastrar(), alternativa.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		alternativa = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(alternativa);
		MessageUtil.message("msg", MessageUtil.editar(), alternativa.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		alternativa = null;
		return "/list/alternativa?faces-redirect=true";
	}

	public String filtrar() {
		alternativasLazy = bo.filtrar(alternativaFilter);
		return null;
	}

	public String deletar() {
		bo.deletar(alternativa);
		alternativa = null;
		return null;
	}

	public List<Alternativa> alternativas() {
		return bo.listar();
	}

	// GETTERS AND SETTERS

	public Alternativa getAlternativa() {
		if (alternativa == null) {
			alternativa = new Alternativa();
		}
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public List<Alternativa> getAlternativas() {
		if (alternativas == null) {
			alternativas = new ArrayList<>();
		}
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public MyLazyDataModel<Alternativa> getAlternativasLazy() {
		if (alternativasLazy == null) {
			alternativasLazy = bo.filtrar(alternativaFilter);
		}
		return alternativasLazy;
	}

	public void setAlternativasLazy(MyLazyDataModel<Alternativa> alternativasLazy) {
		this.alternativasLazy = alternativasLazy;
	}

	public AlternativaFilter getAlternativaFilter() {
		if (alternativaFilter == null) {
			alternativaFilter = new AlternativaFilter();
		}
		return alternativaFilter;
	}

	public void setAlternativaFilter(AlternativaFilter alternativaFilter) {
		this.alternativaFilter = alternativaFilter;
	}

}
