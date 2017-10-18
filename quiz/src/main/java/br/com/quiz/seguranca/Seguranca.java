package br.com.quiz.seguranca;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNomeCompleto();
		}

		return nome;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}

		return usuario;
	}

	public boolean isEmitirPedidoPermitido() {
		return externalContext.isUserInRole("ADMINISTRADORES");
	}

	public boolean isCadastrarPagina() {
		return externalContext.isUserInRole("CADASTRAR_PAGINA");
	}

	public boolean isEditarPagina() {
		return externalContext.isUserInRole("EDITAR_PAGINA");
	}

	public boolean isListarPagina() {
		return externalContext.isUserInRole("LISTAR_PAGINA");
	}

	//

	public boolean isCadastrarUsuario() {
		return externalContext.isUserInRole("CADASTRAR_USUARIO");
	}

	public boolean isEditarUsuario() {
		return externalContext.isUserInRole("EDITAR_USUARIO");
	}

	public boolean isListarUsuario() {
		return externalContext.isUserInRole("LISTAR_USUARIO");
	}

	//

	public boolean isCadastrarCategoria() {
		return externalContext.isUserInRole("CADASTRAR_CATEGORIA");
	}

	public boolean isEditarCategoria() {
		return externalContext.isUserInRole("EDITAR_CATEGORIA");
	}

	public boolean isListarCategoria() {
		return externalContext.isUserInRole("LISTAR_CATEGORIA");
	}

	//

	public boolean isCadastrarAlternativa() {
		return externalContext.isUserInRole("CADASTRAR_ALTERNATIVA");
	}

	public boolean isEditarAlternativa() {
		return externalContext.isUserInRole("EDITAR_ALTERNATIVA");
	}

	public boolean isListarAlternativa() {
		return externalContext.isUserInRole("LISTAR_ALTERNATIVA");
	}

	//

	public boolean isCadastrarProva() {
		return externalContext.isUserInRole("CADASTRAR_PROVA");
	}

	public boolean isEditarProva() {
		return externalContext.isUserInRole("EDITAR_PROVA");
	}

	public boolean isListarProva() {
		return externalContext.isUserInRole("LISTAR_PROVA");
	}

	//

	public boolean isCadastrarQuestao() {
		return externalContext.isUserInRole("CADASTRAR_QUESTAO");
	}

	public boolean isEditarQuestao() {
		return externalContext.isUserInRole("EDITAR_QUESTAO");
	}

	public boolean isListarQuestao() {
		return externalContext.isUserInRole("LISTAR_QUESTAO");
	}

	//

	public boolean isCadastrarPerfil() {
		return externalContext.isUserInRole("CADASTRAR_PERFIL");
	}

	public boolean isEditarPerfil() {
		return externalContext.isUserInRole("EDITAR_PERFIL");
	}

	public boolean isListarPerfil() {
		return externalContext.isUserInRole("LISTAR_PERFIL");
	}

}