package com.test.com;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pages.com.HomePage;
import com.pages.com.RegisterAccount;

public class TestingApp extends BaseClass {

	
	
	
	@Test
	public void titleTest() {
		
		HomePage homePage=new HomePage(driver);
		homePage.verifyTitle(driver);
		
		homePage.clickComponent();
		homePage.clickMonitor();
		homePage.clickMyaccount();
		homePage.clickRegistor();
		
		RegisterAccount account=new RegisterAccount(driver);
		account.enterFirstName(reader.getFirstName());
		account.enterLastName(reader.getLastName());
		account.enterEmail(reader.getemail());
		account.enterTelephone(reader.getphone());
		account.enterPassword(reader.getpassword());
		account.enterConfirmPassword(reader.getConfirmpassword());
		account.clickContinue();
		account.checkErrormessage();
		account.clickAgree();
		account.clickContinue();
		account.SuccessMessageValidate();
		account.clickContinueafterSuccess();
		
		
		
	}
	
	
}
