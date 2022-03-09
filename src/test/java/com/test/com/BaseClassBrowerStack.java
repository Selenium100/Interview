package com.test.com;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bson.Document;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.utility.com.ConfigReader;
import com.utility.com.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassBrowerStack {
	
	public static WebDriver driver;
	public static Actions action;
	String flag="remote";
	
	public static final String USERNAME = "nitya_AoyvxK";
	public static final String AUTOMATE_KEY = "Z7uQsqFRpXxsawXbNqsQ";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


	ConfigReader reader = new ConfigReader();
	// ExcelUtils utils = new ExcelUtils("./datalist.xlsx", "Sheet1");

	MongoCollection<Document> webCollection;
	// public static Document d1;

	@Parameters({ "os", "os_version","browser","browser_version" })
	@BeforeMethod
	public void setup(String os, String os_version,String browser, String browser_version,  Method name) {

		System.out.println("Browsername is " + browser);
        String methodName=name.getName();
		
		
		
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        cap.setCapability("browser_version", browser_version);
        cap.setCapability("name", methodName);

		if (flag.equalsIgnoreCase("remote")) {

			if (browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();
				cap.setCapability("browser", "Chrome");

			} else if (browser.equalsIgnoreCase("Firefox")) {

				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browser", "Firefox");
			}

			try {

				driver = new RemoteWebDriver(new URL(
						URL),
						cap);
				driver.manage().window().maximize();
				driver.get(reader.getAppUrl());
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (flag.equalsIgnoreCase("local")) {
			if (browser.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();

			} else if (browser.equalsIgnoreCase("firefox")) {

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
	}

	@BeforeSuite
	public void connectMongoDB() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("autoDB");

		// database.getCollection("web").drop();
		webCollection = database.getCollection("web");

	}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			Utility.takescreenshot(driver);
		}

		 driver.quit();
	}


}
