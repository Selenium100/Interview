package com.test.com;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.testng.annotations.Test;

import com.pages.com.AccountPage;
import com.pages.com.ChangePasswordPage;
import com.pages.com.HomePage;
import com.pages.com.RegisterAccountPage;

public class TestingApp extends BaseClass {

	@Test
	public void titleTest() {

		HomePage homePage = new HomePage(driver);
		String title = homePage.getTitle();
		String currentUrl = homePage.getCurrentUrl();
		d1 = new Document();
		d1.append("title", title);
		d1.append("currentUrl", currentUrl);

		
		
		homePage.verifyTitle(driver);

		homePage.clickComponent();
		homePage.clickMonitor();
		homePage.clickMyaccount();
		homePage.clickRegistor();

		RegisterAccountPage account = new RegisterAccountPage(driver);
		account.enterFirstName(reader.getFirstName());
		d1.append("FirstName", reader.getFirstName());
		account.enterLastName(reader.getLastName());
		d1.append("LastName", reader.getLastName());
		account.enterEmail(reader.getemail());
		d1.append("Email", reader.getemail());
		account.enterTelephone(reader.getphone());
		account.enterPassword(reader.getpassword());
		account.enterConfirmPassword(reader.getConfirmpassword());
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
		
		List<Document> docList = new ArrayList<>();
		docList.add(d1);
		webCollection.insertMany(docList);

	}

}
