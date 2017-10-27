package com.ntt.mercury;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.dell.acoe.framework.config.Environment;
import com.dell.acoe.framework.run.TestRunner;
import com.dell.acoe.framework.selenium.By;
import com.dell.acoe.framework.selenium.FrameworkDriver;
import com.dell.acoe.framework.selenium.testdata.DataTable;
import com.dell.acoe.framework.selenium.verify.Assert;

public class MercuryTestWithSeO2 {
	public static WebDriver driver = FrameworkDriver.driver;

	public static void main() {
		System.out.println("Hello World");
		
		//driver = new FirefoxDriver(); // should not have here
		driver.get( Environment.get("Mercury.Url") );  // unique across all scripts
		login();
		findFlight();
		driver.quit();
	}

	public static void login(){
		// login
		driver.findElement(By.logicalName("Mlogin.UsernameTX")).sendKeys( Environment.get("Mercury.Username") );  // unique across all scripts
		driver.findElement(By.logicalName("Mlogin.PasswordTX")).sendKeys( Environment.get("Mercury.Password") );  // unique across all scripts
		
		TestRunner.stopThisTest("Stopping as it is logged in");
		
		driver.findElement(By.logicalName("Mlogin.LoginBN")).click();
		
		String actualSignoutText = driver.findElement(By.linkText("SIGN-OFF")).getText().trim(); // verifications
		Assert.assertEquals(actualSignoutText, "SIGN-OFF", "Verifying Signout link in Home page"); // print, execution log, report, screenshot
		
//		TestRunner.stopThisTest("Stopping as it is logged in");
//		TestRunner.stopExecution("Stopping on demand");
		
//		if(signoutText.equalsIgnoreCase("SIGN-OFF")){
//			System.out.println("Pass");
//		}else{
//			System.out.println("Fail");
//		}
	}
	
	public static void findFlight(){
		Assert.done("Start -> findFlight");
		try{
			DataTable dt = new DataTable("C:/SeleniumProjects/cca-portal/testdata/CCA-Portal-TestData1.xlsx", "Flights", "TC_001_MercuryLogin");
			//DataTable dt = new DataTable("C:/SeleniumProjects/cca-portal/testdata/CCA-Portal-TestData.xlsx", "Flights", "TC2");
			Select fromPortDropdown = new Select(driver.findElement(By.name("fromPort")) );
			fromPortDropdown.selectByVisibleText(  dt.getValue("FromPort") );  // test data
			Select toPortDropdown = new Select(driver.findElement(By.name("toPort")));
			toPortDropdown.selectByVisibleText( dt.getValue("ToPort"));	// test data
			driver.findElement(By.name("findFlights")).click();
		}catch(Exception e){
			Assert.error(e, "There is an exception while booking");
		}
		Assert.done("End -> findFlight");
	}
	
	
}
