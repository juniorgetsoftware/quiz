package br.com.quiz.util;

import org.openqa.selenium.WebDriver;

import br.com.quiz.model.DriverStrategy;

public class WebDriverUtil {

	public static WebDriver driver(DriverStrategy driver) {
		return driver.getDriver();
	}
}
