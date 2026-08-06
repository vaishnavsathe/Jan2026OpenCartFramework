package com.qa.OpenCart.pages;

import static com.qa.OpenCart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By headers = By.cssSelector("div#content > h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div #search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(ACCOUNT_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Accounts page title: "+title);
		return title;
	}
	
	public String getAcctPageURL() {
		String url = eleUtil.waitForURLContains(ACCTS_PAGE_FRACTION_URL, MED_DEFAULT_TIMEOUT);
		System.out.println("Accounts page url: "+url);
		return url;
	}
	
	
	public List<String> getAcctPageHeaders() {
		List<WebElement> headersList = eleUtil.getElements(headers);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e:headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		System.out.println("Acct Page Headers: "+headersValList);
		return headersValList;
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		System.out.println("Searching for product: "+searchKey);
		return new SearchResultsPage(driver);
	}
	
}