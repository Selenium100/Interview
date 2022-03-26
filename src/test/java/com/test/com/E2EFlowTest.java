package com.test.com;



import org.testng.annotations.Listeners;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import org.testng.annotations.Test;

import com.pages.com.AccountPage;
import com.pages.com.ChangePasswordPage;
import com.pages.com.HomePage;
import com.pages.com.RegisterAccountPage;
import com.utility.com.ExcelUtils;
import com.utility.com.MongoDbTestListner;

//@Listeners(MongoDbTestListner.class)
public class E2EFlowTest extends BaseClass {


	
	
	
	@Test(dataProvider="data")
	public void titleTest(String firstname,String lastname,String email,String phone,String password,String confirmpassword) throws InterruptedException {


		HomePage homePage = new HomePage(driver);


		homePage.verifyTitle(driver);
		Thread.sleep(4000);
		//homePage.mouseHoverDesktopLink(action);
		Thread.sleep(4000);
		
		homePage.dragAndDrop(action);
		Thread.sleep(4000);
		homePage.clickComponent();
		homePage.clickMonitor();
		Thread.sleep(4000);
		
		homePage.clickMyaccount();
		Thread.sleep(4000);
		homePage.clickRegistor();
		Thread.sleep(4000);

		
		RegisterAccountPage account=new RegisterAccountPage(driver);
		account.enterFirstName(firstname);
		account.enterLastName(lastname);
		account.enterEmail(email);
		account.enterTelephone(phone);
		account.enterPassword(password);
		account.enterConfirmPassword(confirmpassword);


		account.clickContinue();
		account.checkErrormessage();
		account.clickAgree();
		account.clickContinue();
		account.SuccessMessageValidate();
		account.clickContinueafterSuccess();

		AccountPage accountPage = new AccountPage(driver);
		accountPage.AccountPageValidation();
		accountPage.clickChangePassword();

		ChangePasswordPage changePassword = new ChangePasswordPage(driver);
		changePassword.enterChangePassword(reader.getChangepassword());
		changePassword.enterConfirmChangePassword(reader.getConfirmChangepassword());
		changePassword.clickContinue();
        
		
		accountPage.clickAccountBtn();
		accountPage.clickLogOutBtn();


	}

	
	@DataProvider(name="data")
	public Object[][] getdata() throws Exception{
		
		ExcelUtils utils = new ExcelUtils("./datalist.xlsx", "Sheet1");
		Object[][] data= utils.getTableArray();
		return data;
	}
	
	

}
