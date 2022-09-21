package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.LoginPage;
import com.utility.DriverUtils;
public class LoginTest extends BaseClass{
LoginPage lp;
    @BeforeMethod
	public void setup() {
		initialization();
		System.out.println("Login Page Open Sucessfully");
		lp = new LoginPage(driver);
	}
    @AfterMethod
	public void teardown() {
		driver.close();
	}
	@Test(dataProvider="LoginData")
	public void verifyHeaderOfOtpVerificationPageByLoggingIntoApplication(String user,String pwd) throws Exception {
	    lp=new LoginPage(driver);
	    lp.signin();
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		Thread.sleep(3000);
		String act=driver.getTitle();
	//	String act1=act.trim();
		System.out.println(act);
		String exp="Myglit Jobs | Dashboard";
		
		if(act.equalsIgnoreCase(exp)) {
			Assert.assertTrue(true);
			lp.logout();
		}
		else {
			Assert.assertTrue(false);
		}}
	@DataProvider(name="LoginData")
	public String[][] getData() throws Exception{
		String path=System.getProperty("user.dir")+"/MyglitLogin.xlsx";

		int totalrows=DriverUtils.getRowCount(path,"LoginTest");
		int totalcols=DriverUtils.getCellCount(path,"LoginTest", 1);
		String logindata[][]=new String [totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[i-1][j]=DriverUtils.getCellData(path,"LoginTest", i, j);
			}}
		return logindata;
	}
	
}
