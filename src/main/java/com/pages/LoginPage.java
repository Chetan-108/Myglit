package com.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;
    public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[text()='Sign in']")
	public static WebElement signInButton;
	
	@FindBy(id = "loginInputEmail1")
	public static WebElement email;
	
	@FindBy(id = "loginInputPassword1")
	public static WebElement password;
	
	@FindBy(xpath = "//button[@type='submit' and text()!='Search']")
	public static WebElement submitButton;
	
	@FindBy(xpath = "//title[contains(text(),'Myglit Jobs |  Dashboard')]")
	public static WebElement dashboardTitle;
	
	@FindBy(xpath = "//*[@id=\"bsidebarAccordion\"]/a[2]/span")
	public static WebElement lgout;
	
	public void signin() {
		signInButton.click();
	}
	
	public void setUserName(String uname) {
//		email.clear();
		email.sendKeys(uname);
	}
	public void setPassword(String pass) {
//		password.clear();
		password.sendKeys(pass);
	}
	public void clickSubmit() {
		submitButton.click();
	}
	public void logout() {
		lgout.click();
		
	}
}
