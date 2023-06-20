package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver= driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	private By productHeader = By.xpath("//h1");
	private By productImages = By.xpath("//a[@class='thumbnail']");
	private By productMetaData = By.xpath("(//h1/..//following-sibling::ul)[1]/li");
	private By priceMetaData = By.xpath("(//h1/..//following-sibling::ul)[2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	private Map<String, String> productInfoMap;
		
	public String getProductHeaderName() {
		return eleUtil.doGetElementText(productHeader);
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, AppConstants.SHORT_DEFAULT_WAIT).size();
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		
		productInfoMap = new HashMap<String,String>();
		//productInfoMap = new LinkedHashMap<String,String>();
		//productInfoMap = new TreeMap<String,String>();
		
		
		for(WebElement e: metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
			
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(priceMetaData);
		String price = priceList.get(0).getText();
		String exTaxPrice =priceList.get(1).getText();
		String exTaxPriceValue = exTaxPrice.split(":")[1].trim();
		
		productInfoMap.put("productPrice", exTaxPrice);
		productInfoMap.put("exTaxPrice", exTaxPriceValue);
			
		}
	
	public Map<String, String> getProductInfo() {
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productName", getProductHeaderName());
		return productInfoMap;
		
	}
	
	
	

}
