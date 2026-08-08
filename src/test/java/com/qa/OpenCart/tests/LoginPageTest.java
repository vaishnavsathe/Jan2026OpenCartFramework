package com.qa.OpenCart.tests;

import static com.qa.OpenCart.constants.AppConstants.*;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.OpenCart.Base.BaseTest;
import com.qa.OpenCart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design pages for trading application")
@Feature("Fea 121: Open cart login feature")
@Story("Story 141: Impement login page on open cart application")
public class LoginPageTest extends BaseTest{
	
	//Sequence followed: BaseTest (BeforeTest) -> Test cases ->BaseTest (AfterTest)
	
	@Description("Checking open cart login page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("TDIT")
	@Test(description = "Title test ")
	public void loginPageTitleTest() {
		
		String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("checking login page title"+actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
		
	}
	
	@Description("Checking open cart login page URL ...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("TDIT")
	@Test(description = "URL test")
	public void loginPageURLTest() {
		String acctURL = loginPage.getLoginPageURL();
		Assert.assertTrue(acctURL.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("Checking open cart Forgot Password link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("TDIT")
	@Test(description = "Forgot pwd link test")
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Checking open cart login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("TDIT")
	@Test(priority = Short.MAX_VALUE, description = "Login with valid credentials")
	public void doLoginTest() {
		acctPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAccPageTitle(), ACCOUNT_PAGE_TITLE);
	}
	
	@Test(enabled = false, description = "WIP - forgot pwd check")
	public void forgotPwd() {
		System.out.println("WIP");
	}
}