package br.com.quiz.security;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.quiz.model.Chrome;
import br.com.quiz.util.WebDriverUtil;

public class TestarTelaLogin {

	private static WebDriver driver;

	@BeforeClass
	public static void inicializa() {
		driver = WebDriverUtil.driver(new Chrome());
	}

	@Before
	public void setUp() {
		driver.get("localhost:8080/quiz/login.xhtml");
	}

	@AfterClass
	public static void finaliza() {
		driver.close();
	}

	@Test
	public void deveEfeturarLogin() {

		WebElement email = driver.findElement(By.id("username"));
		email.sendKeys("user@admin");

		WebElement senha = driver.findElement(By.id("password"));
		senha.sendKeys("123");

		WebElement btnLogar = driver.findElement(By.id("btn-logar"));
		btnLogar.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(driver.getCurrentUrl().contains("/home.xhtml"));
	}

}
