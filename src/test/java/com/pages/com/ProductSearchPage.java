package com.pages.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage {
	
	WebDriver ldriver;

	public ProductSearchPage(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//h2[text()='Products meeting the search criteria']//following-sibling::div[2]/div//child::h4/a")
	WebElement productList;
	
	public void getProductList() {
		
		List<WebElement> textList= productList.findElements(By.tagName("a"));
		for(WebElement ele:textList) {
			
			System.out.println(ele.getText());
		}
	}

}
