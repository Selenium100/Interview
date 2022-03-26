package com.pages.com;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.com.Utility;

public class ProductSearchPage {

	WebDriver ldriver;

	public ProductSearchPage(WebDriver rdriver) {

		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//h2[text()='Products meeting the search criteria']//following-sibling::div[2]/div//child::h4/a")
	WebElement productList;

	@FindBy(xpath = "//a[text()='Reviews (0)']")
	WebElement reviewButton;

	public void getProductListandClick() throws InterruptedException {

		List<WebElement> textList = ldriver.findElements(By.xpath(
				"//h2[text()='Products meeting the search criteria']//following-sibling::div[2]/div//child::h4/a"));

		System.out.println(textList.size());

		//textList.stream().forEach(i ->System.out.println( i.getText())); 
		
		
		
		

			
		}
	

	public void clickReviewButton() throws InterruptedException {

		try {
			WebElement ele = ldriver.findElement(By.xpath("//a[text()='Reviews (0)']"));
			ele.click();
		} catch (StaleElementReferenceException e) {

			System.out.println("Exception Arrises");
			Thread.sleep(3000);
			clickReviewButton();
		}

	}

	public void scrollDown() {

		Utility.scrollDown(ldriver);
	}

}
