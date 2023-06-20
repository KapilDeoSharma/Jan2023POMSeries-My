package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchDataTest extends BaseTest{
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	

	
	@Test(dataProvider = "productData",dataProviderClass= ProductDataProvider.class)
	public void searchProductResultCountTest(Product product) {
		resultsPage = accPage.searchProduct(product.getSearchKey());
		Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
	}

	@Test(dataProvider = "productData",dataProviderClass= ProductDataProvider.class)
	public void searchPageTitleTest(Product product) {
		resultsPage = accPage.searchProduct(product.getSearchKey());
		String actSearchTitle = resultsPage.getResultsPageTitle(product.getSearchKey());
		System.out.println("Search page Title : " + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + product.getSearchKey());
	}
	
	
	@Test(dataProvider = "productData",dataProviderClass= ProductDataProvider.class)
	public void selectProductTest(Product product) {
		resultsPage = accPage.searchProduct(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		Assert.assertEquals(productInfoPage.getProductHeaderName(), product.getProductName());
	}

	@Test(dataProvider = "productData",dataProviderClass= ProductDataProvider.class)
	public void productImagesTest(Product product) {
		resultsPage = accPage.searchProduct(product.getSearchKey());
		productInfoPage = resultsPage.selectProduct(product.getProductName());
		Assert.assertEquals(productInfoPage.getProductImagesCount(), product.getProductImages());
	}

}