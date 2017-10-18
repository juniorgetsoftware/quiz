package br.com.quiz.seguranca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.quiz.bo.UsuarioBO;
import br.com.quiz.model.Pagina;
import br.com.quiz.model.Usuario;
import br.com.quiz.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UsuarioBO usuarioBO = CDIServiceLocator.getBean(UsuarioBO.class);
		Usuario usuario = usuarioBO.porEmail(email);
		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getPaginas(usuario));
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getPaginas(Usuario usuario) {
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		for (Pagina grupo : usuario.getPaginas()) {
			String group = "ROLE_" + grupo.getNomeIdentificador();
			autorities.add(new SimpleGrantedAuthority(group));
		}
		return autorities;
	}

}
