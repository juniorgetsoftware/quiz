package br.com.quiz.util;

public class ContextoDaAplicacao {

	private static final String CONTEXTO_DA_APLICACAO = "http://localhost:8080/quiz";

	public static String cadastrar(String pagina) {
		return CONTEXTO_DA_APLICACAO + "/cad/" + pagina + ".xhtml";
	}

	public static String editar(String pagina) {
		return CONTEXTO_DA_APLICACAO + "/edit/" + pagina + ".xhtml";
	}

	public static String listar(String pagina) {
		return CONTEXTO_DA_APLICACAO + "/list/" + pagina + ".xhtml";
	}

	public static String login() {
		return CONTEXTO_DA_APLICACAO + "/login" + ".xhtml";
	}

	public static String home() {
		return CONTEXTO_DA_APLICACAO + "/home" + ".xhtml";
	}

}
