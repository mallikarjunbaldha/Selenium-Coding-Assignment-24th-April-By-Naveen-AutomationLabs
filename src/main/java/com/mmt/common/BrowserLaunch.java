package com.mmt.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunch {
	
	public static WebDriver driver;
	static ChromeOptions cop;
	
	public static WebDriver openBrowser(String browserName) {
		
	String operatingSystem = System.getProperty("os.name");
	if(operatingSystem.startsWith("Windows")) {
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions cp = setChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(cp);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	  }
	else if(operatingSystem.startsWith("Mac")) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_mac64/chromedriver.exe");
			driver = new ChromeDriver();
		}	
	}
	else if(operatingSystem.startsWith("Linux")) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_linux64/chromedriver.exe");
			driver = new ChromeDriver();
		}	
	}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		return driver;
		
	}
	
	
	/**
	 * This method is used to set the chrome options
	 * @return
	 */
	public static ChromeOptions setChromeOptions() {
		cop = new ChromeOptions();
		cop.addArguments("start-maximized");
		cop.addArguments("disable-infobars");
		cop.addArguments("--disable-extensions");
		return cop;
	}

}
