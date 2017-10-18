package br.com.quiz.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.quiz.bo.QuestaoBO;
import br.com.quiz.model.Alternativa;
import br.com.quiz.model.Questao;
import br.com.quiz.model.filter.QuestaoFilter;
import br.com.quiz.util.BuscaObjeto;
import br.com.quiz.util.MyLazyDataModel;
import br.com.quiz.util.faces.MessageUtil;

@Named
@ViewScoped
public class QuestaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Questao questao;

	private List<Alternativa> alternativas;
	private List<Questao> questoes;

	private MyLazyDataModel<Questao> questoesLazy;

	private QuestaoFilter questaoFilter;
	private Integer numeroDeQuestoes = 5;

	@Inject
	private QuestaoBO bo;

	@PostConstruct
	public void init() {
		Questao questao = BuscaObjeto.comParametroGET(Questao.class, "id", bo);
		this.questao = questao;
	}

	public String salvar() {
		bo.salvar(questao);
		MessageUtil.message("msg", MessageUtil.cadastrar(), questao.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		questao = null;
		return null;
	}

	public String atualizar() {
		bo.atualizar(questao);
		MessageUtil.message("msg", MessageUtil.editar(), questao.toString());
		MessageUtil.atualizaComponenteDeMensagem("msg");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		questao = null;
		return "/list/questao?faces-redirect=true";
	}

	public String filtrar() {
		questoesLazy = bo.filtrar(questaoFilter);
		return null;
	}

	public String deletar() {
		bo.deletar(questao);
		questao = null;
		return null;
	}

	public List<Questao> questoes() {
		return bo.listar();
	}

	// GETTERS AND SETTERS

	public Questao getQuestao() {
		if (questao == null) {
			questao = new Questao();
		}
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public List<Questao> getQuestoes() {
		if (questoes == null) {
			questoes = new ArrayList<>();
		}
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public MyLazyDataModel<Questao> getQuestoesLazy() {
		if (questoesLazy == null) {
			questoesLazy = bo.filtrar(questaoFilter);
		}
		return questoesLazy;
	}

	public void setQuestoesLazy(MyLazyDataModel<Questao> questoesLazy) {
		this.questoesLazy = questoesLazy;
	}

	public QuestaoFilter getQuestaoFilter() {
		if (questaoFilter == null) {
			questaoFilter = new QuestaoFilter();
		}
		return questaoFilter;
	}

	public void setQuestaoFilter(QuestaoFilter questaoFilter) {
		this.questaoFilter = questaoFilter;
	}

	public Integer getNumeroDeQuestoes() {
		return numeroDeQuestoes;
	}

	public void setNumeroDeQuestoes(Integer numeroDeQuestoes) {
		this.numeroDeQuestoes = numeroDeQuestoes;
	}

	public String gerarCamposDeAlternativas() {
		alternativas = new ArrayList<>();
		for (int i = 0; i < numeroDeQuestoes; i++) {
			alternativas.add(new Alternativa());
		}
		return null;
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
}
