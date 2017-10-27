package com.ntt.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.dell.acoe.framework.config.Environment;
import com.dell.acoe.framework.selenium.By;
import com.dell.acoe.framework.selenium.FrameworkDriver;
import com.dell.acoe.framework.selenium.testdata.DataTable;
import com.dell.acoe.framework.selenium.verify.Assert;

public class MercuryTestWithSeO3 {
	public static WebDriver driver = FrameworkDriver.driver;

	public static void main() {

		driver.get( Environment.get("MercuryUrl") );  // unique across all scripts
		login();
		findFlight();
		driver.quit();
	}

	public static void login(){
		driver.findElement(By.logicalName("LoginPG.UserNameTX")).sendKeys( Environment.get("Mercury.Username") );  // unique across all scripts
		driver.findElement(By.logicalName("LoginPG.PasswordTX")).sendKeys( Environment.get("Mercury.Password") );  // unique across all scripts
		driver.findElement(By.logicalName("LoginPG.LoginBN")).click();
		String actualSignoutText = driver.findElement(By.linkText("SIGN-OFF")).getText(); // verifications
		Assert.assertEquals(actualSignoutText, "SIGN-OFF", "Verifying Signout link in Home page"); // print, execution log, report, screenshot
	
	
	
	
	}
	
	public static void findFlight(){
		DataTable dt = new DataTable("C:/SeleniumProjects/cca-portal/testdata/CCA-Portal-TestData.xlsx", "Flights", "TC1");  // test data
		Select fromPortDropdown = new Select(driver.findElement(By.logicalName("BookingPG.FromPortWL")) );
		fromPortDropdown.selectByVisibleText(  dt.getValue("FromPortWL") );
		Select toPortDropdown = new Select(driver.findElement(By.logicalName("BookingPG.ToPortWL")));
		toPortDropdown.selectByVisibleText( dt.getValue("ToPortWL"));
		driver.findElement(By.name("findFlights")).click();
	}
	
}
