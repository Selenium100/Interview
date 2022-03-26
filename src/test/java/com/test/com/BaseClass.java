package com.test.com;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.utility.com.ConfigReader;
import com.utility.com.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Actions action;
	ConfigReader reader = new ConfigReader();
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		
		if(browser.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("browserName", "chrome");
			
			try {
				
				driver=new RemoteWebDriver(new URL("http://65.0.80.26:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.getMessage();
			}
			
		}else if(browser.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			
			try {
				
				driver=new RemoteWebDriver(new URL("http://65.0.80.26:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				
				e.getMessage();
			}
		}
		try {

			driver.get(reader.getAppUrl());
			driver.manage().window().maximize();
			driver.get(reader.getAppUrl());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void teardown() {


		// driver.quit();
	}


}
