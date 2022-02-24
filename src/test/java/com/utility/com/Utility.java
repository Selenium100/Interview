package com.utility.com;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public interface Utility  {
	
	
	static String currectdate() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	
	
	static void takescreenshot(WebDriver driver) {
		
		
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		System.out.println(currectdate());
		File target=new File("./screenshots/"+currectdate()+".png");
		try {
			
			FileUtils.copyFile(source, target);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	static void scrollDown(WebDriver driver) {
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
	}

}
