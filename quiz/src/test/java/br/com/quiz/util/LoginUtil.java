package br.com.quiz.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginUtil {

	public static void efetuarLogin(WebDriver driver) {
		driver.get(ContextoDaAplicacao.login());

		WebElement email = driver.findElement(By.id("username"));
		email.sendKeys("user@admin");

		WebElement senha = driver.findElement(By.id("password"));
		senha.sendKeys("123");

		WebElement btnLogar = driver.findElement(By.id("btn-logar"));
		btnLogar.click();
	}

	public static void logout(WebDriver logout) {
		WebElement btnLogar = logout.findElement(By.id("logout"));
		btnLogar.click();
	}
}
