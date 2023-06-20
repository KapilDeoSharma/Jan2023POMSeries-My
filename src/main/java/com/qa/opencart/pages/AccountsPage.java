package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2. By Locators	
	private By logout = By.linkText("Logout");
	private By myAccount = By.linkText("My Account");
	private By accountHeaders = By.xpath("//div//h2");
	private By search = By.name("search");
	private By searchButton = By.xpath("//div[@id='search']//button[@type='button']");
	
	
	// 3. Actions
	
	public String acctpageTitle() {
		return eleUtil.waitForFullTitleAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.checkElementIsDisplayed(logout);
	}
	
	public boolean isMyAccountLinkExist() {
		return eleUtil.checkElementIsDisplayed(accountHeaders);
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accountHeaders, AppConstants.SHORT_DEFAULT_WAIT);
		List<String> headers = new ArrayList<String>(); 
		for(WebElement e: headersList) {
			String header = e.getText();
			headers.add(header);
		}
		return headers;
	}
	
	public ResultsPage searchProduct(String product) {
		//eleUtil.waitForElementVisible(search,5);
		eleUtil.doSendKeys(search, product);
		eleUtil.doClick(searchButton);
		return new ResultsPage(driver);
	}
	
	

}
