package com.ntt.mercury;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dell.acoe.framework.config.Environment;
import com.dell.acoe.framework.run.TestRunner;
import com.dell.acoe.framework.selenium.By;
import com.dell.acoe.framework.selenium.Forms;
import com.dell.acoe.framework.selenium.FrameworkDriver;
import com.dell.acoe.framework.selenium.testdata.DataTable;
import com.dell.acoe.framework.selenium.verify.Assert;
import com.ntt.seo2.util.math.RandomUtil;
import com.ntt.seo2.util.xml.XMLUtil;

public class FMSGroupCreation {
	static WebDriver driver = FrameworkDriver.driver;
	
	public static void createNewGroup() {
		Assert.done("Start --> UIUX_GRP_TC_09");

		try {
			
			// Login and got Group Search
			driver.get(Environment.get("FMSAdmin.URL"));
			driver.findElement(By.logicalName("LoginPG.UsernameTX")).sendKeys(Environment.get("FMSAdmin.Username"));
			driver.findElement(By.logicalName("LoginPG.PasswordTX")).sendKeys(Environment.get("FMSAdmin.Password"));
			driver.findElement(By.logicalName("LoginPG.LoginBN")).click();
			driver.findElement(By.logicalName("HomePG.MaintenanceLK")).click();
			driver.findElement(By.logicalName("HomePG.GroupMaintenanceLK")).click();

			// Search
			String sGroupId = "G"+RandomUtil.getRandomaNumber().substring(9);
			DataTable d = new DataTable("C:/SeleniumProjects/cca-portal/testdata/CCA-Portal-TestData.xlsx", "Group", "UIUX_GRP_TC_02");
			d.setValue("GroupIDTX", 1, sGroupId);
			d.setValue("ShortNameTX", 1, "Group "+sGroupId);
			
			String sScreenName = "GroupSearchPG.";
			String[] aFields = { "GroupIDTX", "ShortNameTX", "Name1TX", "Name2TX", "SearchBN", "NoGroupMSG"};
			Forms.fill(sScreenName, aFields, d);
			
			driver.findElement(By.logicalName("GroupSearchPG.CreateGroupBN")).click();
			
			// Create
			DataTable oTestData = new DataTable("C:/SeleniumProjects/cca-portal/testdata/CCA-Portal-TestData.xlsx", "Group", "UIUX_GRP_TC_02");
			sScreenName = "GroupMasterPG.";
			String[] aGroupFields = { "GroupLevelWL", "FederalTaxIdTX", "NatlEmployerIdTX", "ParentTX", "HoldReasonTX", "AccountTypeTX", "AsoIndicatorWL", "HoldDateTX", "HoldDateWE", "GroupTypeWL", "ShortNameTX", "Name1TX", "Name2TX", "Address1TX", "Address2TX", "StateTX", "CityTX", "CountryTX", "ZipCodeTX", "UD1TX", "UD2TX", "UD3TX", "UD4TX", "UD5TX" };
			Forms.fill(sScreenName, aGroupFields, oTestData);
			
			
			// Scroll to Save button and click
			WebElement element = driver.findElement(By.logicalName("GroupMasterPG.SaveBN"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(250, 0)");
			Thread.sleep(20000);
			System.out.println("Scroll done");
			element.click();
			
			// Verify
			String actualMessage = driver.findElement(By.logicalName("GroupMasterPG.GroupSaveMSG")).getText().trim();
			String expectedMessage = "Group " + oTestData.getValue("GroupIDTX") + " created successfully";
			Assert.assertEquals(actualMessage, expectedMessage.trim(), "Verifying Group Save message");		
			
			// Logout
			driver.findElement(By.logicalName("HomePG.LogoutBN")).click();
		} catch (Exception e) {
			Assert.error(e, "Exception occured while UIUX_GRP_TC_09:" + e.getMessage().toString());
		}

		System.out.println("End --> UIUX_GRP_TC_09");
	}
	

}
