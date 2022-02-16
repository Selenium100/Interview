package com.pages.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
	
	WebDriver ldriver;
	
	public HomePage(WebDriver rdriver) {
		
		this.ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	


	@FindBy(xpath="//a[text()='Your Store']")
	WebElement title;
	
	@FindBy(xpath="//a[text()='Components']")
	WebElement components;
	
	@FindBy(xpath="//a[text()='Monitors (2)']")
	WebElement monitor;
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myaccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement register;
	
	
	
	
	
	public void verifyTitle(WebDriver driver) {
		
		String text=title.getText();
		Assert.assertEquals(text, "Your Store", "Title not matched");
		
	}
	
	public void clickComponent() {
		components.click();
	}
	
	public void clickMonitor() {
		monitor.click();
	}
	
	public void clickRegistor() {
		register.click();
	}
	
	public void clickMyaccount() {
		myaccount.click();
	}
	
	


}
