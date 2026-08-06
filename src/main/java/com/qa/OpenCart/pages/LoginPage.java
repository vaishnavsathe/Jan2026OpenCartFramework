package com.qa.OpenCart.pages;

import static com.qa.OpenCart.constants.AppConstants.*;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By Locators as private
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");
	
	
	//2. Constructor of this page class - Public
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. public page actions/methods
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login page title: "+title);
		return title;
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login page URL: "+url);
		return url;
	}
	
	@Step("checking forgot password link")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("Logging in with valid username: {0} and password : {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User credentials:"+username+" : "+ pwd);
		eleUtil.waitForElementVisibile(email, DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
}