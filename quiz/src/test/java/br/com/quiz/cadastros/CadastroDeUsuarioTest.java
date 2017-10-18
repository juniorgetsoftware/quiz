package br.com.quiz.cadastros;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.quiz.model.Chrome;
import br.com.quiz.util.ContextoDaAplicacao;
import br.com.quiz.util.LoginUtil;
import br.com.quiz.util.WebDriverUtil;
import pageobjects.UsuarioPage;

public class CadastroDeUsuarioTest {
	private static WebDriver driver;

	@BeforeClass
	public static void inicializa() {
		driver = WebDriverUtil.driver(new Chrome());
		LoginUtil.efetuarLogin(driver);
	}

	@Before
	public void setUp() {
		//
	}
	
	@After
	public void tearDown(){
		
	}

	@Test
	public void deveAdicionarNovoCliente() {
		UsuarioPage listagem = new UsuarioPage(driver);
		listagem.abre().novo().preencherNome("Usuário teste").preencherEmail("usuario@teste.com.br")
				.preencherSenha("123").preencherConfirmarSenha("123").selecionaPerfil().submete();
		
		assertTrue(listagem.contemMensagem("Cadastrado com sucesso!"));
	}

	@Ignore
	@Test
	public void naoDeveAdicionarNovoClienteSemEmail() {
		driver.get(ContextoDaAplicacao.cadastrar("usuario"));
		WebElement nome = driver.findElement(By.name("nome"));
		nome.sendKeys("Nome do Usuário");
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		assertTrue(driver.getPageSource().contains("Campo 'E-mail' é obrigatório!"));
	}

	@AfterClass
	public static void finaliza() {
		driver.close();
	}

}
