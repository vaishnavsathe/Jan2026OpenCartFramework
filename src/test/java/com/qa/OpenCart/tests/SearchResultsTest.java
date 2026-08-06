package com.qa.OpenCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.OpenCart.Base.BaseTest;

public class SearchResultsTest extends BaseTest{

	@BeforeClass
	public void searchSetup() {
		acctPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = acctPage.doSearch("imac");
		int actResultsCount = searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actResultsCount, 1);
	}
	
	@Test
	public void searchUnavailableTest() {
		searchResultsPage = acctPage.doSearch("tdit");
		int actResultsCount = searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actResultsCount, 0);
	}
		
}