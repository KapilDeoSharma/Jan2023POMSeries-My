package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}


	@Test(dataProvider = "productSearchKey", dataProviderClass = ProductDataProvider.class)
	public void searchProductResultCountTest(String searchKey) {
		resultsPage = accPage.searchProduct(searchKey);
		Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
	}

	@Test(dataProvider = "productSearchKey", dataProviderClass = ProductDataProvider.class)
	public void searchPageTitleTest(String searchKey) {
		resultsPage = accPage.searchProduct(searchKey);
		String actSearchTitle = resultsPage.getResultsPageTitle(searchKey);
		System.out.println("Search page Title : " + actSearchTitle);
		Assert.assertEquals(actSearchTitle, "Search - " + searchKey);
	}
	

	@Test(dataProvider = "productDataWithName", dataProviderClass = ProductDataProvider.class)
	public void selectProductTest(String searchKey, String ProductName) {
		resultsPage = accPage.searchProduct(searchKey);
		productInfoPage = resultsPage.selectProduct(ProductName);
		Assert.assertEquals(productInfoPage.getProductHeaderName(), ProductName);
	}

	@Test(dataProvider = "productDataWithImages", dataProviderClass = ProductDataProvider.class)
	public void productImagesTest(String searchKey, String ProductName, int imageCount) {
		resultsPage = accPage.searchProduct(searchKey);
		productInfoPage = resultsPage.selectProduct(ProductName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}

}
