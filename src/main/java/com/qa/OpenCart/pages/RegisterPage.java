package com.qa.OpenCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.constants.AppConstants;
import com.qa.OpenCart.utils.ElementUtil;
import com.qa.OpenCart.utils.StringUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value = 'Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegistration(String firstName, String lastName, String telephone, 
			String password,String subscribe) {
		eleUtil.waitForElementVisibile(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, StringUtils.getRandomEmailId());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		if(eleUtil.doElementGetText(successMsg).contains(AppConstants.REGISTER_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
		
	}
}