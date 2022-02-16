package com.test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import com.utility.com.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	public static WebDriver driver;
	
	ConfigReader reader=new ConfigReader();
	
	
	@BeforeMethod
	public void setup() {
		
		
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get(reader.getAppUrl());
			driver.manage().window().maximize();
		
		
		
		
	}
	
	public void teardown() {
		driver.quit();
	}
	

}
