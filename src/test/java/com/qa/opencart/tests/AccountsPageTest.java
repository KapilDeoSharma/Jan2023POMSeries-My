package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	
	
	@BeforeClass
	public void acctsetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.acctpageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isMyAccountExist() {
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}
	
	@Test
	public void accountHeaderCount() {
		Assert.assertEquals(accPage.getAccountPageHeadersList().size(),4);
	}
	
	@Test
	public void accPageHeaderList() {
		List<String> actheaderList = accPage.getAccountPageHeadersList();
		//List<String> expectheaderList = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
		Assert.assertEquals(actheaderList, AppConstants.ACC_EXP_HEADER_LIST);
	}
	
}
