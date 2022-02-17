package com.test.com;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pages.com.AccountPage;
import com.pages.com.ChangePasswordPage;
import com.pages.com.HomePage;
import com.pages.com.RegisterAccountPage;
import com.utility.com.MongoDbTestListner;



public class TestingApp extends BaseClass {

	
	
	
	@Test
	public void titleTest() {
		
		HomePage homePage=new HomePage(driver);
		homePage.verifyTitle(driver);
		
		homePage.clickComponent();
		homePage.clickMonitor();
		homePage.clickMyaccount();
		homePage.clickRegistor();
		
		RegisterAccountPage account=new RegisterAccountPage(driver);
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
		
		
		AccountPage accountPage=new AccountPage(driver);
		accountPage.AccountPageValidation();
		accountPage.clickChangePassword();
		
		
		ChangePasswordPage changePassword=new ChangePasswordPage(driver);
		changePassword.enterChangePassword(reader.getChangepassword());
		changePassword.enterConfirmChangePassword(reader.getConfirmChangepassword());
		changePassword.clickContinue();
		
		accountPage.clickAccountBtn();
		accountPage.clickLogOutBtn();
		
		
	}
	
	
}
