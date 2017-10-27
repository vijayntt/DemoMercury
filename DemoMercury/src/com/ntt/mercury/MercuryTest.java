package com.ntt.mercury;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MercuryTest {
	public static WebDriver driver;

	public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.get("http://newtours.demoaut.com/");
		login();
		findFlight();
	}


	public static void login(){
		driver.findElement(By.name("userName")).sendKeys("mercurry");
		driver.findElement(By.name("password")).sendKeys("mercurry");
		driver.findElement(By.name("login")).click();
		String signoutText = driver.findElement(By.linkText("SIGN-OFF")).getText();
		if(signoutText.equalsIgnoreCase("SIGN-OFF")){
			System.out.println("Pass");
		}else{
			System.out.println("Fail");
		}
	}
	
	public static void findFlight(){

		Select fromPortDropdown = new Select(driver.findElement(By.name("fromPort")) );
		fromPortDropdown.selectByVisibleText("New York");
		Select toPortDropdown = new Select(driver.findElement(By.name("toPort")));
		toPortDropdown.selectByVisibleText("Paris");	
		driver.findElement(By.name("findFlights")).click();
	}
}
