package br.com.quiz.util.faces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class MessageUtil {

	public static boolean isPostback() {
		return FacesContext.getCurrentInstance().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static String cadastrar() {
		return "Cadastrado com sucesso!";
	}

	public static String editar() {
		return "Editado com sucesso!";
	}

	public static String excluir() {
		return "Excluído com sucesso!";
	}

	public static String editarStatus() {
		return "Status editado com sucesso!";
	}

	public static void message(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));
	}

	public static void warn(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, message, detail));
	}

	public static void error(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail));
	}

	public static void fatal(String id, String message, String detail) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail));
	}

	public static void atualizaComponenteDeMensagem(String id) {
		RequestContext.getCurrentInstance().update(id);
	}

}
