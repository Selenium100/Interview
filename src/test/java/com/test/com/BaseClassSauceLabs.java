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
import com.utility.com.ExcelUtils;
import com.utility.com.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassSauceLabs {

	// This base class is created by Nitya Ranjan Behera
	//Now I am having the control.

	public WebDriver driver;
	public static Actions action;
	String flag="remote";
	
	//changed by Abhay

	ConfigReader reader = new ConfigReader();
	// ExcelUtils utils = new ExcelUtils("./datalist.xlsx", "Sheet1");

	MongoCollection<Document> webCollection;
	// public static Document d1;

	@Parameters({ "browser", "platform" })
	@BeforeMethod
	public void setup(String browerName, String platformName,Method name) {

		System.out.println("Browsername is " + browerName);
        String methodName=name.getName();
		
		
		//Code for run TCs on SauceLab
		MutableCapabilities sauceLabs = new MutableCapabilities();
		sauceLabs.setCapability("name", methodName);
		sauceLabs.setCapability("build", "Java-W3C-Examples");
		sauceLabs.setCapability("username", "oauth-nityaaccenture19-06567");
		sauceLabs.setCapability("accesskey", "6b12d823-02e1-45ed-8a43-540f02e3e940");
		sauceLabs.setCapability("tags", "w3c-chrome-tests");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceLabs);
		cap.setCapability("browserVersion", "latest");
		cap.setCapability("platformName", platformName);

		if (flag.equalsIgnoreCase("remote")) {
			
			//changed by Abhay

			if (browerName.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				cap.setCapability("browserName", "chrome");

			} else if (browerName.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				cap.setCapability("browserName", "firefox");
			}

			try {

				driver = new RemoteWebDriver(new URL(
						"https://oauth-nityaaccenture19-06567:6b12d823-02e1-45ed-8a43-540f02e3e940@ondemand.eu-central-1.saucelabs.com:443/wd/hub"),
						cap);
				driver.manage().window().maximize();
				driver.get(reader.getAppUrl());
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (flag.equalsIgnoreCase("local")) {
			if (browerName.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();

			} else if (browerName.equalsIgnoreCase("firefox")) {

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
