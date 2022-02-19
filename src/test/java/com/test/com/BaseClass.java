package com.test.com;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bson.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.utility.com.ConfigReader;
import com.utility.com.ExcelUtils;
import com.utility.com.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	ConfigReader reader = new ConfigReader();
	//ExcelUtils utils = new ExcelUtils("./datalist.xlsx", "Sheet1");

	MongoCollection<Document> webCollection;
	//public static Document d1;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(reader.getAppUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@BeforeSuite
	public void connectMongoDB() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("autoDB");

		 database.getCollection("web").drop(); 
		 webCollection =database.getCollection("web");

	}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			Utility.takescreenshot(driver);
		}

		//driver.quit();
	}

}
