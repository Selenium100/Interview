package com.test.com;

import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utility.com.ConfigReader;
import com.utility.com.ExcelUtils;
import com.utility.com.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	public static WebDriver driver;
	
	ConfigReader reader=new ConfigReader();
	ExcelUtils utils=new ExcelUtils("./datalist.xlsx", "Sheet1");
	
	
	@BeforeMethod
	public void setup() {
		
		
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get(reader.getAppUrl());
			driver.manage().window().maximize();
			
		
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		
		
		if(ITestResult.FAILURE==result.getStatus()) {
			
			Utility.takescreenshot(driver);
		}
		
		driver.quit();
	}
	

}
