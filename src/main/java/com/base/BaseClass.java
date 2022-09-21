package com.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.pages.LoginPage;

import freemarker.log.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static FileInputStream fis = null;
	public static  Logger log = Logger.getLogger("BaseClass");
	
	public static String readProperty(String key) {

		Properties prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);

	}
    public static void launchApplication() {
		 log.info("launching an application");
		driver.get(readProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
    public static void initialization() {
		 log.info("initialising a browser with name" + readProperty("browser"));
		try {
			if (readProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				launchApplication();
				log.info("chorme browser initialised");
				// return driver;
			} 
			else {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("firefox browser initialised");
				launchApplication();
				// return driver;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}



}
