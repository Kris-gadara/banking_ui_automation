package WebLocators;

import org.openqa.selenium.By;

public class XYZLocators 
{

	public static By Cx_login = By.xpath("//button[@ng-click='customer()']");
	public static By slt_user = By.xpath("//select[@id='userSelect']");
	public static By btn_login = By.xpath("//button[text()='Login']");
	public static By btn_logout = By.xpath("//button[text()='Logout']");
	
	//AfterLogin
	public static By txt_AccountName = By.xpath("//div/strong/span[@class='fontBig ng-binding']");
	public static By txt_AccountNum = By.xpath("//div[@ng-hide='noAccount' and text()='Account Number : ']//strong[1]");
	public static By txt_AccntBalance= By.xpath("//div[@ng-hide='noAccount' and text()='Account Number : ']//strong[2]");
	public static By txt_Currency = By.xpath("//div[@ng-hide='noAccount' and text()='Account Number : ']//strong[3]");
	
	//transactions
	public static By btn_deposit = By.xpath("//button[@ng-click='deposit()']");
	public static By txt_Amount = By.xpath("//input[@placeholder='amount']");
	public static By btn_depAmnt =By.xpath("//button[@type='submit']");
	public static By txt_successDeposit = By.xpath("//span[@ng-show='message' and text()='Deposit Successful']");
}
