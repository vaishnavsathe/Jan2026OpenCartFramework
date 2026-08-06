package com.qa.OpenCart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.constants.AppConstants;
import com.qa.OpenCart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By productHeader = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap;//null
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String header = eleUtil.waitForElementVisibile(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("Product Header is: "+header);
		return header;
	}
	
	public int getProductImagesCount() {
		int imageCount =
		eleUtil.waitForAllElementsVisibile(productImages, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Total no of images: "+imageCount);
		return imageCount;
	}
	
	public Map<String, String> getProductDetailsMap() {
//		productMap = new HashMap<String,String>();//Orderless and derieve the index based on key randomly
//		productMap = new LinkedHashMap<String,String>();//Store in insertion order
		productMap = new TreeMap<String,String>();
		
		productMap.put("productHeader", getProductHeader());
		productMap.put("productImages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getPriceData();
		System.out.println("Full Product Details: "+productMap);
		return productMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	
	
	private void getProductMetaData() {
		List<WebElement> MetaList = eleUtil.waitForAllElementsVisibile(productMetaData, AppConstants.DEFAULT_TIMEOUT);
		for(WebElement e:MetaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String metaKey = meta[0];
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getPriceData() {
		List<WebElement> priceList  = eleUtil.waitForAllElementsVisibile(productPriceData, AppConstants.DEFAULT_TIMEOUT);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("ProductPrice", productPrice);
		productMap.put("ExTax", exTaxPrice);
		
	}
	
	
	
}