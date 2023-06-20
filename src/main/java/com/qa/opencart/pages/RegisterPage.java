package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage{
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	
	private By firstName = By.name("firstname");
	private By lastName = By.name("lastname");
	private By emailId = By.name("email");
	private By telephone = By.name("telephone");
	private By password = By.id("input-password");
	private By passwordConfirm = By.id("input-confirm");
	
	private By policyCheckbox = By.xpath("//input[@name='agree']");
	private By continueButton = By.xpath("//input[@value='Continue']");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[1]");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline']/input[@type='radio'])[2]");
	
	private By userRegSeccMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public String registerUser(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.SHORT_DEFAULT_WAIT);
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.emailId, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.passwordConfirm, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
				
		eleUtil.doClick(policyCheckbox);
		eleUtil.doClick(continueButton);
		
		String registerMessage = eleUtil.waitForElementVisible(userRegSeccMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		return registerMessage;
		
	}
	
	
	
	

}
