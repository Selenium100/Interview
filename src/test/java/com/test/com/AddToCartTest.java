package com.test.com;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pages.com.HomePage;
import com.pages.com.ProductSearchPage;
import com.utility.com.ExcelUtils;
import com.utility.com.MongoDbTestListner;

//@Listeners(MongoDbTestListner.class)
public class AddToCartTest extends BaseClassBrowerStack{
	
	
	@Test(dataProvider = "data")
	public void AddProductToCart(String txt) throws InterruptedException  {
		
		HomePage page=new HomePage(driver);
		page.enterOnSearchBar(txt);
		
		ProductSearchPage productSearchPage=new ProductSearchPage(driver);
		 
		
		productSearchPage.getProductListandClick();
		//productSearchPage.clickReviewButton();
		
		
		
	}
	
	
	@DataProvider(name="data")
	public Object[][] getdata() throws Exception{
		
		ExcelUtils utils=new ExcelUtils("./AddtoCartTest.xlsx", "Sheet1");
		Object[][] data=utils.getTableArray();
		return data;
		
	}

}
