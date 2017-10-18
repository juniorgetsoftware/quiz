package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.PaginaBO;
import br.com.quiz.model.Pagina;
import br.com.quiz.model.Status;
import br.com.quiz.model.filter.PaginaFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class PaginaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pagina pagina;
	private List<Pagina> paginas;
	private MyLazyDataModel<Pagina> paginasLazy;
	private PaginaFilter paginaFilter;

	@Inject
	private PaginaBO bo;

	@PostConstruct
	public void init() {
		Pagina pagina = BuscaObjeto.comParametroGET(Pagina.class, "id", bo);
		this.pagina = pagina;
	}

	public String salvar() {
		bo.salvar(pagina);
		MessageUtil.message("msg", MessageUtil.cadastrar(), pagina.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		pagina = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(pagina);
		MessageUtil.message("msg", MessageUtil.editar(), pagina.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		pagina = null;
		return "/list/pagina?faces-redirect=true";
	}

	public String filtrar() {
		paginasLazy = bo.filtrar(paginaFilter);
		return null;
	}

	public String deletar() {
		bo.deletar(pagina);
		pagina = null;
		return null;
	}

	public Status[] listarStatus() {
		return Status.values();
	}

	public List<Pagina> paginas() {
		return bo.listar();
	}

	// GETTERS AND SETTERS

	public Pagina getPagina() {
		if (pagina == null) {
			pagina = new Pagina();
		}
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public List<Pagina> getPaginas() {
		if (paginas == null) {
			paginas = new ArrayList<>();
		}
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public MyLazyDataModel<Pagina> getPaginasLazy() {
		if (paginasLazy == null) {
			paginasLazy = bo.filtrar(paginaFilter);
		}
		return paginasLazy;
	}

	public void setPaginasLazy(MyLazyDataModel<Pagina> paginasLazy) {
		this.paginasLazy = paginasLazy;
	}

	public PaginaFilter getPaginaFilter() {
		if (paginaFilter == null) {
			paginaFilter = new PaginaFilter();
		}
		return paginaFilter;
	}

	public void setPaginaFilter(PaginaFilter paginaFilter) {
		this.paginaFilter = paginaFilter;
	}

}
