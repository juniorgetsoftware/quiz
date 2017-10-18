package br.com.quiz.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements DriverStrategy{

	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "//home/junior/webdriver/chromedriver");
		return new ChromeDriver();
	}

}
