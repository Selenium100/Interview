package com.pages.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
	
	WebDriver ldriver;

	public ChangePasswordPage(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name = "password")
	WebElement passwordTxt;
	
	@FindBy(name = "confirm")
	WebElement confirmPasswordTxt;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continuebtn;
	
	public void enterChangePassword(String changePasswrd) {

		passwordTxt.sendKeys(changePasswrd);
	}
	
	public void enterConfirmChangePassword(String confirmChangePasswrd) {

		confirmPasswordTxt.sendKeys(confirmChangePasswrd);
	}
	
	public void clickContinue() {

		continuebtn.click();
	}

}
