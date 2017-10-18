package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.CategoriaBO;
import br.com.quiz.model.Categoria;
import br.com.quiz.model.filter.CategoriaFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private List<Categoria> categorias;
	private MyLazyDataModel<Categoria> categoriasLazy;
	private CategoriaFilter categoriaFilter;

	@Inject
	private CategoriaBO bo;

	@PostConstruct
	public void init() {
		Categoria categoria = BuscaObjeto.comParametroGET(Categoria.class, "id", bo);
		this.categoria = categoria;
	}

	public String salvar() {
		bo.salvar(categoria);
		MessageUtil.message("msg", MessageUtil.cadastrar(), categoria.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		categoria = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(categoria);
		MessageUtil.message("msg", MessageUtil.editar(), categoria.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		categoria = null;
		return "/list/categoria?faces-redirect=true";
	}

	public String filtrar() {
		categoriasLazy = bo.filtrar(categoriaFilter);
		return null;
	}

	public String deletar() {
		bo.deletar(categoria);
		categoria = null;
		return null;
	}

	public List<Categoria> categorias() {
		return bo.listar();
	}

	// GETTERS AND SETTERS

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> autoCompletar(String seq) {
		return bo.autoCompletar(seq);
	}

	public List<Categoria> getCategorias() {
		if (categorias == null) {
			categorias = new ArrayList<>();
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public MyLazyDataModel<Categoria> getCategoriasLazy() {
		if (categoriasLazy == null) {
			categoriasLazy = bo.filtrar(categoriaFilter);
		}
		return categoriasLazy;
	}

	public void setCategoriasLazy(MyLazyDataModel<Categoria> categoriasLazy) {
		this.categoriasLazy = categoriasLazy;
	}

	public CategoriaFilter getCategoriaFilter() {
		if (categoriaFilter == null) {
			categoriaFilter = new CategoriaFilter();
		}
		return categoriaFilter;
	}

	public void setCategoriaFilter(CategoriaFilter categoriaFilter) {
		this.categoriaFilter = categoriaFilter;
	}

}
