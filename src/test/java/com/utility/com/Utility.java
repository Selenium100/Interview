package com.utility.com;

import java.io.File;
import java.sql.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public interface Utility  {
	
	
	static String currectdate() {
		Date date = new Date(System.currentTimeMillis());
		return date.toString();
	}
	
	
	
	static void takescreenshot(WebDriver driver) {
		
		
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File("./screenshots/"+currectdate()+".png");
		try {
			
			FileUtils.copyFile(source, target);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}
