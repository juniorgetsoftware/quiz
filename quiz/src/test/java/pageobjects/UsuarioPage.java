package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.quiz.util.ContextoDaAplicacao;

public class UsuarioPage {

	private WebDriver driver;

	public UsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public UsuarioPage abre() {
		driver.get(ContextoDaAplicacao.listar("usuario"));
		return this;
	}

	public NovoUsuarioPage novo() {
		driver.findElement(By.id("novo")).click();
		return new NovoUsuarioPage(driver);
	}

	public boolean contemMensagem(String msg) {
		WebElement mensagens = driver.findElement(By.id("msg"));
		return mensagens.getText().contains(msg);

	}

}
