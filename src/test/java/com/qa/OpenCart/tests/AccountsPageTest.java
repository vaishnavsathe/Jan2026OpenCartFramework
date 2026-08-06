package com.qa.OpenCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qa.OpenCart.constants.AppConstants.*;

import java.util.List;

import com.qa.OpenCart.Base.BaseTest;
import com.qa.OpenCart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	//Sequence followed is: BasetTest (BeforeTest) -> BeforeClass --> Test cases -->BaseTest(AfterTest)
	

	//pre requisite is i need logged in so that i can execute the test cases for account page class
	@BeforeClass
	public void accPageSetup() {
		acctPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
		
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(acctPage.getAccPageTitle(), ACCOUNT_PAGE_TITLE);
		
	}
	
	
	@Test
	public void acctPageURLTest() {
		Assert.assertTrue(acctPage.getAcctPageURL().contains(ACCTS_PAGE_FRACTION_URL));
	}
	
	
	@Test
	public void acctPageHeadersTest() {
		List<String> actHeadersList = acctPage.getAcctPageHeaders();
		Assert.assertEquals(actHeadersList, AppConstants.expHeadersList);
	}
	
	
}