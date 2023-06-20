package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ResultsPage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	private By resultsProduct = By.xpath("//div[@class='product-thumb']");
	
	
	public String getResultsPageTitle(String searchKey) {
		return eleUtil.waitForTitleIsAndCapture(searchKey, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public int getProductResultsCount() {
		int resultsCount = eleUtil.waitForElementsVisible(resultsProduct, AppConstants.SHORT_DEFAULT_WAIT).size();
		System.out.println("Total products displayed are " + resultsCount);
		return resultsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By product = By.linkText(productName);
		eleUtil.doClick(product);
		return new ProductInfoPage(this.driver);
	}
	

}
