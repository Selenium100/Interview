package com.pages.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegisterAccount {

	WebDriver ldriver;

	public RegisterAccount(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "firstname")
	WebElement firstName;
	
	@FindBy(name = "lastname")
	WebElement lastName;
	
	@FindBy(name = "email")
	WebElement email;
	
	@FindBy(name = "telephone")
	WebElement telephone;

	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "confirm")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueq;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement errorMessage;
	
	@FindBy(name = "agree")
	WebElement agreeCheckbox;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement successMessage;
	
	@FindBy(xpath="//a[text()='Continue']")
	WebElement continueAftersuccess;
	
	
	
	
	public void enterFirstName(String name) {

		firstName.sendKeys(name);
	}

	public void enterLastName(String name) {

		lastName.sendKeys(name);
	}
	
	public void enterEmail(String emailText) {

		email.sendKeys(emailText);
	}
	
	
	
	public void enterTelephone(String telephoneno) {

		telephone.sendKeys(telephoneno);
	}
	
	public void enterPassword(String passwordTxt) {

		password.sendKeys(passwordTxt);
	}
	
	public void enterConfirmPassword(String confirmpasswordTxt) {

		confirmPassword.sendKeys(confirmpasswordTxt);
	}
	
	public void clickContinue() {

		continueq.click();
	}
	
	public void checkErrormessage() {
		
		boolean check=errorMessage.isDisplayed();
		
		Assert.assertTrue(check);
	}
	
	public void clickAgree() {

		agreeCheckbox.click();
	}
	
	public void SuccessMessageValidate() {

		String actual=successMessage.getText();
		Assert.assertEquals(actual, "Your Account Has Been Created!","Not successfully added");
	}
	
	public void clickContinueafterSuccess() {

		continueAftersuccess.click();
	}

}
