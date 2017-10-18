package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.quiz.bo.ProvaBO;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.Prova;
import br.com.quiz.model.Questao;
import br.com.quiz.model.filter.ProvaFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class ProvaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Prova prova;
	private Questao questao;
	private Alternativa alternativa;

	private List<Prova> provas;
	private MyLazyDataModel<Prova> provasLazy;
	private ProvaFilter provaFilter;

	@Inject
	private ProvaBO bo;

	@PostConstruct
	public void init() {
		Prova prova = BuscaObjeto.comParametroGET(Prova.class, "id", bo);
		this.prova = prova;
	}

	public String salvar() {
		bo.salvar(prova);
		MessageUtil.message("msg", MessageUtil.cadastrar(), prova.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		prova = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(prova);
		MessageUtil.message("msg", MessageUtil.editar(), prova.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		prova = null;
		return "/list/prova?faces-redirect=true";
	}

	public String filtrar() {
		provasLazy = bo.filtrar(provaFilter);
		return null;
	}

	public List<Prova> autoCompletar(String seq) {
		return bo.autoCompletar(seq);
	}

	public String buscarProvas() {
		provas = autoCompletar(provaFilter.getNome());
		return null;
	}

	public String deletar() {
		bo.deletar(prova);
		prova = null;
		return null;
	}

	public List<Prova> provas() {
		return bo.listar();
	}

	// GETTERS AND SETTERS

	public Prova getProva() {
		if (prova == null) {
			prova = new Prova();
		}
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Prova> getProvas() {
		if (provas == null) {
			provas = new ArrayList<>();
		}
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public MyLazyDataModel<Prova> getProvasLazy() {
		if (provasLazy == null) {
			provasLazy = bo.filtrar(provaFilter);
		}
		return provasLazy;
	}

	public void setProvasLazy(MyLazyDataModel<Prova> provasLazy) {
		this.provasLazy = provasLazy;
	}

	public ProvaFilter getProvaFilter() {
		if (provaFilter == null) {
			provaFilter = new ProvaFilter();
		}
		return provaFilter;
	}

	public void setProvaFilter(ProvaFilter provaFilter) {
		this.provaFilter = provaFilter;
	}

	// MÉTODOS AUXILIARES

	public Questao getQuestao() {
		if(questao == null){
			questao = new Questao();
		}
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Alternativa getAlternativa() {
		if(alternativa == null){
			alternativa = new Alternativa();
		}
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public void novaQuestao() {
		questao = new Questao();
	}

	public void novoAlternativa() {
		alternativa = new Alternativa();
	}

	public String adicionarAlternativa() {
		this.alternativa.setQuestao(questao);
		this.questao.adicionar(alternativa);
		MessageUtil.message("msg-alternativa", "Alternativa adicionada com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-alternativa");
		this.alternativa = null;
		return null;
	}

	public String atualizarAlternativa() {
		this.alternativa.setQuestao(questao);
		this.questao.atualizar(alternativa);
		MessageUtil.message("msg-alternativa", "Alternativa atualizada com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-alternativa");
		this.alternativa = null;
		return null;
	}

	public String removerAlternativa() {
		this.alternativa.setQuestao(null);
		this.questao.remover(alternativa);
		MessageUtil.message("msg-alternativa", "Alternativa removida com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-alternativa");
		this.alternativa = null;
		return null;
	}

	//

	public String adicionarQuestao() {
		this.questao.setProva(prova);
		this.prova.adicionar(questao);
		MessageUtil.message("msg-questao", "Questão adicionada com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-questao");
		this.questao = null;
		return null;
	}

	public String atualizarQuestao() {
		this.questao.setProva(prova);
		this.prova.atualizar(questao);
		MessageUtil.message("msg-questao", "Questão atualizada com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-questao");
		this.questao = null;
		return null;
	}

	public String removerQuestao() {
		this.questao.setProva(null);
		this.prova.remover(questao);
		MessageUtil.message("msg-questao", "Questão removida com sucesso!", "");
		RequestContext.getCurrentInstance().update("msg-questao");
		this.questao = null;
		return null;
	}

}
