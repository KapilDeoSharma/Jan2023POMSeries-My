package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.Product;
import com.qa.opencart.pojo.ProductWithMetaData;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(dataProvider = "productDataWithName", dataProviderClass = ProductDataProvider.class)
	public void productHeaderTest(String searchKey, String productName) {
		resultsPage = accPage.searchProduct(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		String expectedProductName = productInfoPage.getProductHeaderName();
		Assert.assertEquals(expectedProductName, productName);
	}
	
	@Test(dataProvider = "productDataWithMetaData", dataProviderClass = ProductDataProvider.class)
	public void productInfoTest(ProductWithMetaData product) {
		resultsPage = accPage.searchProduct(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		Map<String,String> productMap = productInfoPage.getProductInfo();
		System.out.println(productMap);
		//{Brand=Apple, Availability=In Stock, Product Code=Product 18, Reward Points=800, productPrice=Ex Tax: $2,000.00, exTaxPrice=$2,000.00, productName=MacBook Pro}
		softAssert.assertEquals(productMap.get("Brand"), product.getBrand());
		softAssert.assertEquals(productMap.get("Availability"), product.getAvailability());
		softAssert.assertEquals(productMap.get("Product Code"), product.getProductCode());
		softAssert.assertEquals(productMap.get("Reward Points"), product.getRewardsPoints());
		softAssert.assertAll();
	}
}
