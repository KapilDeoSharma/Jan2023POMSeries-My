package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void resisterSetup() {
		registerPage = loginPage.navigateToResisterPage();
	}
	
	public String getRandomEmailID() {
		return "test" + System.currentTimeMillis()+"@gmail.com";
	}
	
//	@DataProvider(name='regData')
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//			{"kapil", "sharma", "1234567890", "kapil@123", "yes"},
//			{"anil", "sharma", "1234567890", "kapil@123", "yes"},
//			{"daniel", "martin", "1234567890", "kapil@123", "yes"}
//		};
//	}
	
	
	@DataProvider(name = "regExcelData")
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.RESGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "regExcelData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		String successMessage = registerPage.registerUser(firstName, lastName, getRandomEmailID(), telephone, password, subscribe);
		System.out.println(successMessage);
		Assert.assertTrue(successMessage.contains(AppConstants.USER_REG_SUCCESS_MESSAGE));
	}

}
