package com.msg.qa.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class DriverFactory {

	Properties properties;

	public DriverFactory() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		this.properties = new Properties();

		try {
			this.properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("there was an issue reading config.properties");
		}
	}

	public WebDriver getWebDriver(String driverType) throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = desiredCapabilitiesFromProperties(driverType);
		WebDriver driver = null;
		if(driverType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			String viewportSize = properties.getProperty("driver.chrome.viewportSize");
			chromeOptions.addArguments(String.format("--window-size=%s", viewportSize));
			driver = new ChromeDriver(chromeOptions);
		} else if(driverType.equals("ios")) {
			driver = new IOSDriver<>(new URL(properties.getProperty("serverUrl")), desiredCapabilities);
		} else if(driverType.equals("android")) {
			driver = new AndroidDriver<>(new URL(properties.getProperty("serverUrl")), desiredCapabilities);
		} else if(driverType.equals("firefox")) {
			System.setProperty("webdriver.firefox.marionette","src/test/resources/drivers/geckodriver");
			driver = new FirefoxDriver();
		}

		return driver;
	}

	private DesiredCapabilities desiredCapabilitiesFromProperties(String driverType) {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		Set<String> propertyNames = this.properties.stringPropertyNames();
		for(String property : propertyNames) {
			if(property.startsWith("driver." + driverType)) {
				desiredCapabilities.setCapability(property.split("\\.")[2], this.properties.getProperty(property));
			}
		}

		return desiredCapabilities;
	}

}
