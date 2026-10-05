package reusableMethods;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods 
{
	static WebDriverWait wait;
	static WebDriver driver;
	static int sscount =0;
	
	
	
	public ReusableMethods(WebDriver driver) 
	{
		this.driver= driver;
	}
	public static void waitforElement(By by,int seconds) 
	{
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void takescreenshot(WebDriver driver1) throws InterruptedException
	{
		try {
			TakesScreenshot ss = ((TakesScreenshot)driver);
			File srcFile = ss.getScreenshotAs(OutputType.FILE);
			File destFile = new File(System.getProperty("user.dir")+"/Screenshots/"+"screenshot"+sscount+".png");
			sscount++;
			FileUtils.copyFile(srcFile, destFile);
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
	}
	public static void  waitforSeconds(int sec) 
	{
		try {
			System.out.println("Waiting for "+ sec+ " Seconds");
			Thread.sleep(sec*1000);
			
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
