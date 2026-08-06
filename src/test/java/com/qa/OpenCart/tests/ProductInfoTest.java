package com.qa.OpenCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.OpenCart.Base.BaseTest;
import com.qa.OpenCart.constants.AppConstants;
import com.qa.OpenCart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		acctPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"imac",	"iMac"},
			{"samsung",	"Samsung SyncMaster 941BW"},
			{"samsung",	"Samsung Galaxy Tab 10.1"}
		};
	}
	
	@DataProvider  // Excel TestData
	public Object[][] getProductTestData(){
		Object productData[][]= ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return productData;
	}
	
	//AAA standard - Arrange Act Assert
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey,String  expHeader) {
		searchResultsPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(expHeader);
		String actHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, expHeader);
	}
	
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object[][] {
			{"macbook","MacBook Air",4},
			{"macbook","MacBook Pro",4},
			{"imac",	"iMac",3},
			{"samsung",	"Samsung SyncMaster 941BW",1},
			{"samsung",	"Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@DataProvider  // Excel TestData
	public Object[][] getProductImagesTestData(){
		Object imagesData[][]= ExcelUtil.getTestData(AppConstants.IMAGES_SHEET_NAME);
		return imagesData;
	}
	
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImageTest(String searchKey, String productName,String expImgCount) {
		searchResultsPage = acctPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		
		// TestNG will compare 2 strings or 2 integers, but both values must be of same type
		// actImagesCount-integer & expImgCount-String (convert it to integer)
		int expectedImagesCount = Integer.parseInt(expImgCount);
		Assert.assertEquals(actImagesCount, expectedImagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = acctPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();
		
		SoftAssert softAss = new SoftAssert();
		
		softAss.assertEquals(actualProductDetailsMap.get("Brand"), "Apple");//P
		softAss.assertEquals(actualProductDetailsMap.get("Product Code"), "Product 18");//P
		softAss.assertEquals(actualProductDetailsMap.get("Reward Points"), "800");//P
		softAss.assertEquals(actualProductDetailsMap.get("Availability"), "Out Of Stock");//p
		softAss.assertEquals(actualProductDetailsMap.get("ProductPrice"), "$2,000.00");//P
		softAss.assertEquals(actualProductDetailsMap.get("ExTax"), "$2,000.00");//P
		
		softAss.assertAll();
		
	}

}