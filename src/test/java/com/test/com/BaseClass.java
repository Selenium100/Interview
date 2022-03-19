package com.test.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		
		if(browser.equals("Chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browser.equals("FF")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
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
	public void teardown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			Utility.takescreenshot(driver);
		}

		 driver.quit();
	}


}
