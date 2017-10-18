package br.com.quiz.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.quiz.model.Cor;
import br.com.quiz.model.Status;

@Named
@ViewScoped
public class UtilMB implements Serializable {

	private static final long serialVersionUID = 1L;

	public Status[] listarStatus() {
		return Status.values();
	}

	public Cor[] listarCores() {
		return Cor.values();
	}
}
