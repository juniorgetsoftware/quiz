package br.com.quiz.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Firefox implements DriverStrategy {

	@Override
	public WebDriver getDriver() {
		System.setProperty("webdriver.gecko.driver", "//home/junior/webdriver/chromedriver");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);
		return new FirefoxDriver(dc);
	}

}
