package br.com.quiz.controllers.security;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.quiz.util.faces.MessageUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;

	private String email;

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			MessageUtil.error("msg", "Usuário não encontrado!", "Tente novamente.");
		}
	}

	public void login() throws ServletException, IOException {
		MessageUtil.message("mensagem", "Bem-vindo!", "");
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
		dispatcher.forward(request, response);
		facesContext.responseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
