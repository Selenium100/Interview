package com.utility.com;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
    
	
	WebDriver driver;
	@org.testng.annotations.Test
	public void clickMaxPrice() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.name("login-button")).click();
		
		List<WebElement> ele=  driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		
		Double maxPrice= ele.stream().map(WebElement::getText).map(i -> Double.valueOf(i.replace("$", ""))).max(Double::compare).get();
		
		//div[@class='inventory_item_price' and text()='"+maxPrice+" ']/following-sibling::button
		
		driver.findElement(By.xpath("//div[@class='inventory_item_price' and text()='"+maxPrice+"']/following-sibling::button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='Remove']")).getText(), "REMOVE");
		
		
		
	}

}
