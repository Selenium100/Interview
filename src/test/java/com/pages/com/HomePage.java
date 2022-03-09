package com.pages.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.utility.com.Utility;

public class HomePage {

	WebDriver ldriver;

	public HomePage(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//a[text()='Your Store']")
	WebElement title;

	@FindBy(xpath = "//a[text()='Components']")
	WebElement components;

	@FindBy(xpath = "//a[text()='Monitors (2)']")
	WebElement monitor;

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myaccount;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement register;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement searchbar;
	
	@FindBy(xpath="//a[text()='Desktops']")
	WebElement desktopLink;
	

	public void verifyTitle(WebDriver driver) {

		String text = title.getText();
		Assert.assertEquals(text, "Your Store", "Title not matched");

	}

	public void clickComponent() {
		components.click();
	}

	public String getTitle() {

		return ldriver.getTitle();
	}

	public String getCurrentUrl() {

		return ldriver.getCurrentUrl();
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
	
	public void enterOnSearchBar(String text) {
		searchbar.sendKeys(text);
		searchbar.sendKeys(Keys.ENTER);
	}
	
	public void mouseHoverDesktopLink(Actions action) {
		
		Utility.mouseHover(ldriver, desktopLink, action);
	}
	
	public void dragAndDrop(Actions action) {
		
		Utility.dragAndDrop(ldriver, desktopLink, searchbar, action);
	}

}
