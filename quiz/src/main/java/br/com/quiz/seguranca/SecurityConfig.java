package br.com.quiz.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AppUserDetailsService userDetailService() {
		return new AppUserDetailsService();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService()).passwordEncoder(new Md5PasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		JsfLoginUrlAuthenticationEntryPoint jsfLogin = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLogin.setLoginFormUrl("/login.xhtml");
		jsfLogin.setRedirectStrategy(new JsfRedirectStrategy());

		// CONFIGURAÇÃO PARA "ACESSO NEGADO"
		JsfAccessDeniedHandler jsfAccessDenied = new JsfAccessDeniedHandler();
		jsfAccessDenied.setLoginPath("/acesso-negado.xhtml");
		jsfAccessDenied.setContextRelative(true);

		CustomAuthenticationEntryPoint custom = new CustomAuthenticationEntryPoint();
		custom.setLoginPageUrl("/login.xhtml");
		custom.setReturnParameterEnabled(true);
		custom.setReturnParameterName("page");

		http.csrf().disable().headers().frameOptions().sameOrigin()

				.and()

				.authorizeRequests().antMatchers("/form/**", "/WEB-INF/template/**").denyAll()
				.antMatchers("/login.xhtml", "/erro.xhtml", "/javax.faces.resource/**", "/nao-encontrado.xhtml")
				.permitAll().antMatchers("/home.xhtml", "/acesso-negado.xhtml").authenticated()

				/* ALTERNATIVA */
				.antMatchers("/cad/alternativa.xhtml").hasAnyRole("CADASTRAR_ALTERNATIVA")
				.antMatchers("/edit/alternativa.xhtml").hasAnyRole("EDITAR_ALTERNATIVA")
				.antMatchers("/list/alternativa.xhtml").hasAnyRole("LISTAR_ALTERNATIVA")

				/* CATEGORIA */
				.antMatchers("/cad/categoria.xhtml").hasAnyRole("CADASTRAR_CATEGORIA")
				.antMatchers("/edit/categoria.xhtml").hasAnyRole("EDITAR_CATEGORIA")
				.antMatchers("/list/categoria.xhtml").hasAnyRole("LISTAR_CATEGORIA")

				/* PÁGINA */
				.antMatchers("/cad/pagina.xhtml").hasAnyRole("CADASTRAR_PAGINA").antMatchers("/edit/pagina.xhtml")
				.hasAnyRole("EDITAR_PAGINA").antMatchers("/list/pagina.xhtml").hasAnyRole("LISTAR_PAGINA")

				/* PERFIL */
				.antMatchers("/cad/perfil.xhtml").hasAnyRole("CADASTRAR_PERFIL").antMatchers("/edit/perfil.xhtml")
				.hasAnyRole("EDITAR_PERFIL").antMatchers("/list/perfil.xhtml").hasAnyRole("LISTAR_PERFIL")

				/* PROVA */
				.antMatchers("/cad/prova.xhtml").hasAnyRole("CADASTRAR_PROVA").antMatchers("/edit/prova.xhtml")
				.hasAnyRole("EDITAR_PROVA").antMatchers("/list/prova.xhtml").hasAnyRole("LISTAR_PROVA")

				/* QUESTAO */
				.antMatchers("/cad/questao.xhtml").hasAnyRole("CADASTRAR_QUESTAO").antMatchers("/edit/questao.xhtml")
				.hasAnyRole("EDITAR_QUESTAO").antMatchers("/list/questao.xhtml").hasAnyRole("LISTAR_QUESTAO")

				/* USUÁRIO */
				.antMatchers("/cad/usuario.xhtml").hasAnyRole("CADASTRAR_USUARIO").antMatchers("/edit/usuario.xhtml")
				.hasAnyRole("EDITAR_USUARIO").antMatchers("/list/usuario.xhtml").hasAnyRole("LISTAR_USUARIO")

				.and()

				.formLogin().loginPage("/login.xhtml").defaultSuccessUrl("/home.xhtml")
				.failureUrl("/login.xhtml?invalid=true").and()

				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()

				.exceptionHandling().accessDeniedPage("/acesso-negado.xhtml").authenticationEntryPoint(jsfLogin)
				.authenticationEntryPoint(custom).accessDeniedHandler(jsfAccessDenied);

	}

}
