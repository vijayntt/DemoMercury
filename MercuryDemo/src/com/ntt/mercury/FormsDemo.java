package com.ntt.mercury;

import org.openqa.selenium.WebDriver;

import com.dell.acoe.framework.config.Environment;
import com.dell.acoe.framework.selenium.By;
import com.dell.acoe.framework.selenium.Forms;
import com.dell.acoe.framework.selenium.FrameworkDriver;
import com.dell.acoe.framework.selenium.testdata.DataTable;
import com.dell.acoe.framework.selenium.verify.Assert;
import com.ntt.seo2.util.math.RandomUtil;

public class FormsDemo {
	static WebDriver driver = FrameworkDriver.driver;

	public static void groupSearchAndCreation(){
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

/*		driver.findElement(By.logicalName("GroupSearchPG.GroupIDTX")).sendKeys( d.getValue("GroupIDTX") );
		driver.findElement(By.logicalName("GroupSearchPG.ShortNameTX")).sendKeys( d.getValue("ShortNameTX") );
		driver.findElement(By.logicalName("GroupSearchPG.Name1TX")).sendKeys( d.getValue("Name1TX") );
		driver.findElement(By.logicalName("GroupSearchPG.Name2TX")).sendKeys( d.getValue("Name2TX") );
		driver.findElement(By.logicalName("GroupSearchPG.SearchBN")).click();
		Assert.done("Search done");
		String actualSearchResultMessage = driver.findElement(By.logicalName("GroupSearchPG.NoGroupMSG")).getText().trim();
		String expectedSearchResultMessage = "No Groups found for the search criteria. Click Create new group button to create a new group with the given ID.";
		Assert.assertEquals(actualSearchResultMessage.trim(), expectedSearchResultMessage.trim(), "Verifying Group Save message");					
*/
		String sScreenName = "GroupSearchPG.";
		String[] aFields = { "GroupIDTX", "ShortNameTX", "Name1TX", "Name2TX", "SearchBN", "NoGroupMSG"};
		Forms.fill(sScreenName, aFields, d);
		
		String sScreenName1 = "GroupViewPG.";
		Forms.verifyFields(sScreenName1, aFields, d);
		
	}
}
