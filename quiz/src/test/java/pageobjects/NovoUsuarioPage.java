package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {

	private WebDriver driver;

	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}

	public NovoUsuarioPage preencherNome(String nome) {
		WebElement input = driver.findElement(By.name("nome"));
		input.sendKeys(nome);
		return this;
	}

	public NovoUsuarioPage preencherEmail(String email) {
		WebElement input = driver.findElement(By.name("email"));
		input.sendKeys(email);
		return this;
	}

	public NovoUsuarioPage preencherSenha(String senha) {
		WebElement input = driver.findElement(By.name("senha"));
		input.sendKeys(senha);
		return this;
	}

	public NovoUsuarioPage preencherConfirmarSenha(String confirmaSenha) {
		WebElement input = driver.findElement(By.name("confirmar-senha"));
		input.sendKeys(confirmaSenha);
		return this;
	}

	public NovoUsuarioPage selecionaStatusAtivo() {
		driver.findElement(By.id("status:0")).click();
		return this;
	}

	public NovoUsuarioPage selecionaPerfil() {
		driver.findElement(By.id("perfil_label")).click();
		aguardar(5);
		driver.findElement(By.id("perfil_1")).click();
		aguardar(5);
		return this;
	}

	public NovoUsuarioPage selecionaPaginas() {
		driver.findElement(By.id("pagina:0")).click();
		driver.findElement(By.id("pagina:1")).click();
		driver.findElement(By.id("pagina:2")).click();
		return this;
	}

	public void submete() {
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		aguardar(5);
	}
	
	public void aguardar(int segundos){
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
