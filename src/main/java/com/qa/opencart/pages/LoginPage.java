package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. Constructor
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;	
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2. private BY locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value=\"Login\"]");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By footerlinks = By.xpath("//footer//a");
	private By errorLoginMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	private By registerLink = By.linkText("Register");
	
	//3. public page actions/methods
	
	@Step("getting login page title....")
	public String getLoginPageTitle() {
		return eleUtil.waitForFullTitleAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	
	}
	
	@Step("checking forgot pwd link exist on the login page")
	public Boolean isForgotPwdLinkDisplayed() {
		return eleUtil.checkElementIsDisplayed(forgotPwdLink);
	}
	
	@Step("getting footer links")
	public List<String> getFooterLinksList() {
		List<WebElement> footerLinkList = eleUtil.waitForElementsVisible(footerlinks,AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> links = new ArrayList<String>();
		for (WebElement e: footerLinkList) {
			String link = e.getText();
			links.add(link);
		}
		return links;
	}
	
	@Step("login with username {0} and password {1} ")
	public AccountsPage doLogin(String userName, String pwd) {
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
		
		
	}
	
	@Step("login with wrong username {0} and password {1} ")
	public boolean doLoginWithWronCredentials(String userName, String pwd) {
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId, userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		String errorMessage = eleUtil.doGetElementText(errorLoginMessage);
		System.out.println(errorMessage);
		if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
			return true;
		}
		else {
			return false;
			
		}
		
	}
	
	public RegisterPage navigateToResisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
	}
	
	

}
