package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginPageNegativeTest extends BaseTest{
	
//	@DataProvider 
//	public Object[][] incorrectLoginTestData(){
//		return new Object[][]{
//			{prop.getProperty("username"),"12345"},
//			{prop.getProperty("username"),""},
//			{"incorrectName", prop.getProperty("password")},
//			{"",""}
//		};
//		
//	}
	
	
	@DataProvider(name = "incorrectLoginExcelTestData")
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.LOGIN_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "incorrectLoginExcelTestData")
	public void loginWithWronCredentailsTest(String username,String password) {
		Assert.assertTrue(loginPage.doLoginWithWronCredentials(username, password));
	}

}
