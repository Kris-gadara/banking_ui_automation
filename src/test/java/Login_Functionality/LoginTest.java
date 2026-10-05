package Login_Functionality;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import Helpers.GlobalVariables;
import Helpers.ReadProperties;
import Helpers.WebDriverHelper;
import WebLocators.XYZLocators;

import WebMethods.PageMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import reusableMethods.ReusableMethods;

@Listeners(listeners.Listeners.class)
public class LoginTest 
{
	

	static XYZLocators by;
	static PageMethods browser;
	static WebDriver driver;
	static String url;
	static ReusableMethods bot;
	  static GlobalVariables glovar;
	
	@Parameters("browserName")
	@BeforeTest
	public void Setup(String browserName) 
	{
		 driver = WebDriverHelper.getDriver(browserName);
		 by = new XYZLocators();
		 browser = new PageMethods(driver);
		 url = ReadProperties.getProperty("xyzbank");
		 bot = new ReusableMethods(driver);
		 glovar = new GlobalVariables();
	}	
	
	@Test(priority=0)
	public static void Login() throws InterruptedException
	{
		
		browser.navigateTo(url);
		bot.waitforElement(by.Cx_login, 3);
		bot.takescreenshot(driver);
		browser.click(by.Cx_login);
		bot.waitforSeconds(4);
		browser.Select(by.slt_user, "Harry Potter");
		bot.waitforSeconds(2);
		browser.click(by.btn_login);
		bot.waitforSeconds(5);
		bot.takescreenshot(driver);
		
		
	
	}
	
	@Test(priority=1)
	public static void BalanceCheck() throws InterruptedException
	{
		Assert.assertEquals(browser.getText(by.txt_AccountName),"Harry Potter");
		glovar.AccountNum = browser.getText(by.txt_AccountNum);
		glovar.AccountBalance = browser.getText(by.txt_AccntBalance);
		glovar.Currency = browser.getText(by.txt_Currency);
		if(glovar.AccountNum.contains("004")) 
		{
			System.out.println("Account Number is : "+ glovar.AccountNum);
			System.out.println("Account Balance is : "+ glovar.AccountBalance);
			System.out.println("Account Number is : "+ glovar.Currency);
			
		}
		bot.takescreenshot(driver);
		bot.waitforSeconds(5);
		
	}
	
	@Test(priority=2)
	public static void Deposit() throws InterruptedException 
	{
		browser.click(by.btn_deposit);
		bot.waitforSeconds(5);
		if(glovar.AccountBalance.equals("0")) 
		{
			browser.enterText(by.txt_Amount,"10000");
			bot.takescreenshot(driver);
			bot.waitforSeconds(5);
		}else {
			System.out.println("Account balance is Not Zero");
		}
		browser.click(by.btn_depAmnt);
		bot.waitforSeconds(5);
		bot.waitforElement(by.txt_successDeposit,5);
		Assert.assertEquals(browser.getText(by.txt_successDeposit),"Deposit Successful");
		
		BalanceCheck();
	}
	@AfterTest
	public void TearDown() 
	{
		browser.click(by.btn_logout);
		bot.waitforSeconds(5);
		WebDriverHelper.CloseDriver();
		System.out.println("Browser Closed");
	}
	
	

}
