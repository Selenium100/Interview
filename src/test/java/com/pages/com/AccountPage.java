package com.pages.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.utility.com.Utility;

public class AccountPage {

	WebDriver ldriver;

	public AccountPage(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//a[text()='Account']")
	WebElement accountTag;
	
	@FindBy(xpath = "//a[text()='Change your password']")
	WebElement changePasword;
	
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']/text()")
	WebElement changePasswordSuccess;
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement accountBtn;
	
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logOut;
	
	
	

	public void AccountPageValidation() {

		String actual = accountTag.getText();
		Assert.assertEquals(actual,"Account" ,"User is not on Account's page");
	}
	
	public void clickChangePassword() {

		changePasword.click();
	}
	
	public void changePasswordLinkisdisplayed() {
		
		boolean flag=changePasswordSuccess.isDisplayed();
		Assert.assertTrue(flag);
	}
	
	public void clickAccountBtn() {

		accountBtn.click();
	}
	
	public void clickLogOutBtn() {

		logOut.click();
	}
	
	public void mouseHoverAccountButton(Actions action) {
		
		Utility.mouseHover(ldriver, accountBtn, action);
	}

}
