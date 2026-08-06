package com.qa.OpenCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.constants.AppConstants;
import com.qa.OpenCart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By resultsProduct = By.cssSelector("div.product-thumb"); 
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getResultsProductCount() {
		int searchCount = 
				eleUtil.waitForAllElementsVisibile(resultsProduct, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Total number of search products: "+searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product name selected on Search Results: "+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}