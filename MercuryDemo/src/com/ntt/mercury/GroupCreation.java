package com.ntt.mercury;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dell.acoe.framework.selenium.By;
import com.dell.acoe.framework.selenium.Forms;
import com.dell.acoe.framework.selenium.FrameworkDriver;
import com.dell.acoe.framework.selenium.testdata.DataTable;
import com.dell.acoe.framework.selenium.verify.Assert;

public class GroupCreation {
	static WebDriver driver = FrameworkDriver.driver;

	public static void GroupCreate(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) throws InterruptedException {
		Assert.pass("Start --> GroupCreation");
		System.out.println("Start --> GroupCreation");

		try {
			GroupGeneralInformation(sTestDataFile, sSheetName, sTestCaseId, iIterations, bIsContinue);
			GroupGroupInformation(sTestDataFile, sSheetName, sTestCaseId, iIterations, bIsContinue);
			GroupGroupPrimaryAddress(sTestDataFile, sSheetName, sTestCaseId, iIterations, bIsContinue);
			GroupUserDefinedFields(sTestDataFile, sSheetName, sTestCaseId, iIterations, bIsContinue);

			// Scroll to Save button and click
			String sScreenName = "GroupMasterPG.";
			WebElement element = driver.findElement(By.logicalName(sScreenName + "SaveBN"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(250, 0)");
			Thread.sleep(20000);
			System.out.println("Scroll done");
			element.click();

			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String actualMessage = driver.findElement(By.logicalName(sScreenName + "GroupSaveMSG")).getText().trim();
			String expectedMessage = "Group " + oTestData.getValue("GroupIDTX") + " created successfully";
			Assert.assertEquals(actualMessage, expectedMessage.trim(), "Verifying Group Save message");
		}

		catch (Exception e) {
			Assert.fail("Exception occured while GroupCreation:" + e.getMessage());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupCreation");
		System.out.println("End --> GroupCreation");
	}

	public static void GroupGeneralInformation(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) throws InterruptedException {
		Assert.pass("Start --> GroupGeneralInformation");
		System.out.println("Start --> GroupGeneralInformation");

		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "GroupMasterPG.GEIN.";
			String[] aFields = { "GroupLevelWL", "FederalTaxIdTX", "NatlEmployerIdTX", "ParentTX" };
			Forms.fill(sScreenName, aFields, oTestData);
		} catch (Exception e) {
			Assert.fail("Exception occured while GroupGeneralInformation:" + e.getMessage().toString());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupGeneralInformation");
		System.out.println("End --> GroupGeneralInformation");
	}

	public static void GroupGroupInformation(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) throws InterruptedException {
		Assert.pass("Start --> GroupGroupInformation");
		System.out.println("Start --> GroupGroupInformation");

		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "GroupMasterPG.GRIN.";
			String[] aFields = { "HoldReasonTX", "AccountTypeTX", "AsoIndicatorWL", "HoldDateTX", "HoldDateWE", "GroupTypeWL", "ShortNameTX", "Name1TX", "Name2TX" };
			Forms.fill(sScreenName, aFields, oTestData);
		} catch (Exception e) {
			Assert.fail("Exception occured while GroupGroupInformation:" + e.getMessage().toString());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupGroupInformation");
		System.out.println("End --> GroupGroupInformation");
	}

	public static void GroupGroupPrimaryAddress(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) throws InterruptedException {
		Assert.pass("Start --> GroupGroupPrimaryAddress");
		System.out.println("Start --> GroupGroupPrimaryAddress");

		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "GroupMasterPG.GPA.";
			String[] aFields = { "Address1TX", "Address2TX", "StateTX", "CityTX", "CountryTX", "ZipCodeTX" };
			Forms.fill(sScreenName, aFields, oTestData);
		} catch (Exception e) {
			Assert.fail("Exception occured while GroupGroupPrimaryAddress:" + e.getMessage().toString());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupGroupPrimaryAddress");
		System.out.println("End --> GroupGroupPrimaryAddress");
	}

	public static void GroupUserDefinedFields(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) throws InterruptedException {
		Assert.pass("Start --> GroupUserDefinedFields");
		System.out.println("Start --> GroupUserDefinedFields");

		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "GroupMasterPG.UDF.";
			String[] aFields = { "UD1TX", "UD2TX", "UD3TX", "UD4TX", "UD5TX" };
			Forms.fill(sScreenName, aFields, oTestData);
		} catch (Exception e) {
			Assert.fail("Exception occured while GroupUserDefinedFields:" + e.getMessage());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupUserDefinedFields");
		System.out.println("End --> GroupUserDefinedFields");
	}

	public static void GroupContractAdd(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) {
		Assert.pass("Start --> GroupContractAdd");
		System.out.println("Start --> GroupContractAdd");

		try {
			String screen = "GroupContractsPG.";
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			driver.findElement(By.logicalName(screen + "ContractsLK")).click();
			driver.findElement(By.logicalName(screen + "AddContractBN")).click();

			driver.findElement(By.logicalName(screen + "EffectiveDateTX")).click();
			driver.findElement(By.logicalName(screen + "EffectiveDateTX")).clear();
			driver.findElement(By.logicalName(screen + "EffectiveDateTX")).sendKeys(oTestData.getValue("EffectiveDateTX"));
			driver.findElement(By.logicalName(screen + "UpdateContractBN")).click();

			String message1 = driver.findElement(By.logicalName(screen + "GCAMessageWE")).getText();
			if (message1.contains(oTestData.getValue("GCAMessageExpectedWE"))) {
				Assert.pass("Contract added successfully to the group");
			} else {
				Assert.fail("Contract addition to group is failed!");
			}
		} catch (Exception e) {
			Assert.fail("Exception occured while Group Setup:" + e.getMessage());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupContractAdd");
		System.out.println("End --> GroupContractAdd");

	}

	public static void GroupDetailAdd(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) {
		Assert.pass("Start --> GroupDetailAdd");
		System.out.println("Start --> GroupDetailAdd");

		try {
			String screen = "GroupDetailsPG.";
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			driver.findElement(By.logicalName(screen + "DetailRecordLK")).click();
			driver.findElement(By.logicalName(screen + "NewdetailLK")).click();

			Select rectype = new Select(driver.findElement(By.logicalName(screen + "RecordTypeWL")));
			rectype.selectByVisibleText(oTestData.getValue("RecordTypeWL"));
			driver.findElement(By.id(screen + "EffectiveDateTX")).click();

			driver.findElement(By.id(screen + "EffectiveDateTX")).clear();
			driver.findElement(By.logicalName(screen + "EffectiveDateTX")).sendKeys(oTestData.getValue("EffectiveDateTX"));
			driver.findElement(By.logicalName(screen + "PlanTX")).sendKeys(oTestData.getValue("PlanTX"));
			driver.findElement(By.logicalName(screen + "LOBTX")).sendKeys(oTestData.getValue("LOBTX"));
			driver.findElement(By.logicalName(screen + "SaveDetailBN")).click();
			String message = driver.findElement(By.logicalName(screen + "GDMessageWE")).getText();
			if (message.contains(oTestData.getValue("GDMessageExpectedWE"))) {
				Assert.pass("Group detail added successfully");
			} else {
				Assert.fail("Group detail adding failed!");
			}
		}

		catch (Exception e) {
			Assert.fail("Exception occured while Group Detail Add:" + e.getMessage());
			e.printStackTrace();
		}

		Assert.pass("End --> GroupDetailAdd");
		System.out.println("End --> GroupDetailAdd");
	}

	public static void verifyAddressLK() {
		Assert.pass("Start --> verifyAddressLK");
		try {
			String sScreenName = "GroupMasterPG.";
			boolean bIsAddressesLKDisplayed = driver.findElement(By.logicalName(sScreenName + "AddressesLK")).isDisplayed();
			Assert.assertTrue(bIsAddressesLKDisplayed, "Verify address link is displayed or not!");
			driver.findElement(By.logicalName(sScreenName + "AddressesLK")).click();
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the address link is displayed or not!");
		}
		Assert.pass("End --> verifyAddressLK");
	}

	public static void verifyFieldsUnderAddressScreen() {
		Assert.pass("Start --> verifyFieldsUnderAddressScreen");
		try {
			String sScreenName1 = "Group.AddAddress.GI.";
			String aFieldName1[] = { "AddressTypeWL", "EffDateTX", "TermReasonTX", "TermDateTX", "PrimaryAddressWL", "BillingAddressWL", "InActiveWL", "Address1TX", "Address2TX", "CityTX", "StateWL",
					"ZipCodeTX", "CountryTX", "CountyTX" };
			Forms.verifyFields(sScreenName1, aFieldName1);
			String sScreenName2 = "Group.AddAddress.UDF.";
			String aFieldName2[] = { "UserDefiend1TX", "UserDefiend2TX", "UserDefiend3TX", "UserDefiend4TX", "UserDefiend5TX", "UserDate1TX", "UserDate2TX", "UserDate3TX", "UserDate4TX",
					"UserDate5TX" };
			Forms.verifyFields(sScreenName2, aFieldName2);
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the fields under address screen is displayed or not!");
		}
		Assert.pass("End --> verifyFieldsUnderAddressScreen");

	}

	public static void verifyNewAddessBN() {
		Assert.pass("Start --> verifyNewAddessBN");
		try {
			boolean bIsNewAddressBNDisplayed = driver.findElement(By.logicalName("Group.NewAddressBN")).isDisplayed();
			Assert.assertTrue(bIsNewAddressBNDisplayed, "Verify the new address button is displayed or not!");
			driver.findElement(By.logicalName("Group.NewAddressBN")).click();
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the  new address button is displayed or npt!");
		}
		Assert.pass("End --> verifyNewAddessBN");
	}

	public static void verifyButtonsUnderAddressScreen() {
		Assert.pass("Start --> verifyButtonsUnderAddressScreen");
		try {
			String sScreenName = "Group.Address.";
			String aFieldName[] = { "NewSearchBN", "SaveAllChangesBN", "UndoAllChangesBN", "AddressViewRowBN" };
			Forms.verifyFields(sScreenName, aFieldName);
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the buttons under address screen!");
		}
		Assert.pass("End --> verifyButtonsUnderAddressScreen");
	}

	public static void addAddress(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) {
		Assert.pass("Start --> addAddress");
		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName1 = "Group.AddAddress.GI.";
			String aFieldName1[] = { "AddressTypeWL", "EffDateTX", "TermReasonTX", "TermDateTX", "PrimaryAddressWL", "BillingAddressWL", "InActiveWL", "Address1TX", "Address2TX", "CityTX", "StateWL",
					"ZipCodeTX", "CountryTX", "CountyTX" };
			Forms.fill(sScreenName1, aFieldName1, oTestData);
			String sScreenName2 = "Group.AddAddress.UDF.";
			String aFieldName2[] = { "UserDefiend1TX", "UserDefiend2TX", "UserDefiend3TX", "UserDefiend4TX", "UserDefiend5TX", "UserDate1TX", "UserDate2TX", "UserDate3TX", "UserDate4TX",
					"UserDate5TX" };
			Forms.fill(sScreenName2, aFieldName2, oTestData);
			String sScreenName3 = "Group.AddAddress.";
			String aFieldName3[] = { "SaveBN", "SuccessMSG" };
			Forms.fill(sScreenName3, aFieldName3, oTestData);
		} catch (Exception e) {
			Assert.error(e, "Exception occured while adding address!");
		}
		Assert.pass("End --> addAddress");
	}

	public static void verifyAddAddressScreenFieldsValidation(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) {
		Assert.pass("Start --> verifyAddAddressScreenFieldsValidation");
		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "Group.Address.";
			List<WebElement> weAddressTableTr = driver.findElement(By.logicalName(sScreenName + "TableId")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
			for (int i = 0; i < weAddressTableTr.size(); i++) {
				List<WebElement> weAddressTableTd = weAddressTableTr.get(i).findElements(By.tagName("td"));
				String sActualEffDateFromApp = weAddressTableTd.get(0).getText().trim();
				String sExpectedEffDateFromDT = oTestData.getValue("EffDateTX").trim();

				String sActualAddressTypeFromApp = weAddressTableTd.get(3).getText().trim();
				String sExpectedAddressTypeFromDT = oTestData.getValue("AddressTypeWL").trim();

				if ((sActualEffDateFromApp.equalsIgnoreCase(sExpectedEffDateFromDT)) && (sActualAddressTypeFromApp.equalsIgnoreCase(sExpectedAddressTypeFromDT))) {
					boolean bIsAddressViewRowBN = weAddressTableTd.get(12).findElement(By.logicalName(sScreenName + "AddressViewRowBN")).isDisplayed();
					Assert.assertTrue(bIsAddressViewRowBN, "Verify the address row edit button is displayed or not!");
					weAddressTableTd.get(12).findElement(By.logicalName(sScreenName + "AddressViewRowBN")).click();
					
					String sScreenName1 = "Group.AddAddress.GI.";
					String aFieldName1[] = { "AddressTypeWL", "EffDateTX", "TermReasonTX", "TermDateTX", "PrimaryAddressWL", "BillingAddressWL", "InActiveWL", "Address1TX", "Address2TX", "CityTX",
							"StateWL", "ZipCodeTX", "CountryTX", "CountyTX" };
					DataTable oTestDataLengths = new DataTable(sTestDataFile, sSheetName, sTestCaseId+"_Lengths");
					for (int k = 0; k < aFieldName1.length; k++) {
						String sActualAddressGIFieldsFromApp = driver.findElement(By.logicalName(sScreenName1 + aFieldName1[k])).getAttribute("value");
						String sExpectedAddressGIFieldsFromDT = oTestData.getValue(aFieldName1[k]);
						Assert.assertEquals(sActualAddressGIFieldsFromApp.length(), Integer.parseInt(oTestDataLengths.getValue(aFieldName1[k])), "verify the address screen general information field lengths!");
						
					}
					String sScreenName2 = "Group.AddAddress.UDF.";
					String aFieldName2[] = { "UserDefiend1TX", "UserDefiend2TX", "UserDefiend3TX", "UserDefiend4TX", "UserDefiend5TX", "UserDate1TX", "UserDate2TX", "UserDate3TX", "UserDate4TX",
							"UserDate5TX" };
					for (int m = 0; m < aFieldName2.length; m++) {
						String sActualAddressGIFieldsFromApp = driver.findElement(By.logicalName(sScreenName2 + aFieldName2[m])).getAttribute("value");
						String sExpectedAddressGIFieldsFromDT = oTestData.getValue(aFieldName2[m]);
						Assert.assertEquals(sActualAddressGIFieldsFromApp.length(), Integer.parseInt(oTestDataLengths.getValue(aFieldName2[m])), "verify the address screen user defiend field lengths!");
					}
					boolean bIsAddressRowCollapseBN = driver.findElement(By.logicalName(sScreenName +"RowCollapseBN")).isDisplayed();
					Assert.assertTrue(bIsAddressRowCollapseBN, "Verify the address row collapse button is displayed or not!");
					driver.findElement(By.logicalName(sScreenName +"RowCollapseBN")).click();
				}

			}

		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the add address screen field validations !");
		}
		Assert.pass("End --> verifyAddAddressScreenFieldsValidation");
	}

	public static void verifyAsteriskFieldsOfAddress() {
		Assert.pass("Start --> verifyAsteriskFieldsOfAddress");
		try {
			String sScreenName = "Group.AddAddress.GI.";
			String aFieldName[] = { "AddressTypeLB", "EffDateLB", "CityLB", "StateLB", "ZipCodeLB", "Address1LB" };
			for (int i = 0; i < aFieldName.length; i++) {
				String sAsteriskFields = driver.findElement(By.logicalName(sScreenName + aFieldName[i])).getAttribute("class");
				boolean bIssAsteriskFields = sAsteriskFields.contains("fms_required");
				Assert.assertTrue(bIssAsteriskFields, "Verify the Asterisk fields in Address page");
			}
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verifying asterisk fields in address!");
		}
		Assert.pass("End --> verifyAsteriskFieldsOfAddress");
	}

	public static void verifyKeywordUnderGroupMaintenaceScreens(String sTestDataFile, String sSheetName, String sTestCaseId, int iIterations, boolean bIsContinue) {
		Assert.pass("Start --> verifyKeywordUnderGroupMaintenaceScreens");
		try {
			DataTable oTestData = new DataTable(sTestDataFile, sSheetName, sTestCaseId);
			String sScreenName = "GroupMasterPG.";
			boolean bIsGROUPKeyword = driver.findElement(By.logicalName(sScreenName + "MasterRecord.GROUPKeyword")).isDisplayed();
			Assert.assertTrue(bIsGROUPKeyword, "Verify Group master page GROUP keyword!");
			String sActualGROUPKeyword = driver.findElement(By.logicalName(sScreenName + "MasterRecord.GROUPKeyword")).getText().trim();
			String sExpectedGROUPKeyword = oTestData.getValue("GROUPKeyword").trim();
			Assert.assertEquals(sActualGROUPKeyword, sExpectedGROUPKeyword, "Verify group master page GROUP keyword!");

			boolean bIsContractsLKDisplayed = driver.findElement(By.logicalName(sScreenName + "ContractsLK")).isDisplayed();
			Assert.assertTrue(bIsContractsLKDisplayed, "Verify Contact link!");
			driver.findElement(By.logicalName(sScreenName + "ContractsLK")).click();
			boolean bIsGRUPCKeyWordDisplayed = driver.findElement(By.logicalName(sScreenName + "Contracts.GRUPCKeyword")).isDisplayed();
			Assert.assertTrue(bIsGRUPCKeyWordDisplayed, "Verify GRUPC keyword is displayed or not in contracts screen!");
			String sActualGRUPCKeyWord = driver.findElement(By.logicalName(sScreenName + "Contracts.GRUPCKeyword")).getText().trim();
			String sExpectedGRUPCKeyWord = oTestData.getValue("GRUPCKeyWord").trim();
			Assert.assertEquals(sActualGRUPCKeyWord, sExpectedGRUPCKeyWord, "Verify the GRUPC keyword in Contracts screen! ");

			boolean bIsDetailRecordsLKDisplayed = driver.findElement(By.logicalName(sScreenName + "DetailRecordsLK")).isDisplayed();
			Assert.assertTrue(bIsDetailRecordsLKDisplayed, "Verify the details records is displayed or not!");
			driver.findElement(By.logicalName(sScreenName + "DetailRecordsLK")).click();
			boolean bIsGRUPDKeywordDisplayed = driver.findElement(By.logicalName(sScreenName + "DetailRecords.GRUPDKeyword")).isDisplayed();
			Assert.assertTrue(bIsGRUPDKeywordDisplayed, "Verify the GRUPD keyword!");
			String sActualGRUPDKeyword = driver.findElement(By.logicalName(sScreenName + "DetailRecords.GRUPDKeyword")).getText().trim();
			String sExpectedGRUPDKeyword = oTestData.getValue("GRUPDKeyword").trim();
			Assert.assertEquals(sActualGRUPDKeyword, sExpectedGRUPDKeyword, "Verify the GRUPD keyword in details records screen!");

			boolean bIsAddressesLKDisplayed = driver.findElement(By.logicalName(sScreenName + "AddressesLK")).isDisplayed();
			Assert.assertTrue(bIsAddressesLKDisplayed, "Verify the address link is displayed or not!");
			driver.findElement(By.logicalName(sScreenName + "AddressesLK")).click();
			boolean bIsGRUPAKeywordDisplayed = driver.findElement(By.logicalName(sScreenName + "Addresses.GRUPAKeyword")).isDisplayed();
			Assert.assertTrue(bIsGRUPAKeywordDisplayed, "Verify the GRUPA keyword is displayed or not in address screen!");
			String sActualGRUPAKeyword = driver.findElement(By.logicalName(sScreenName + "Addresses.GRUPAKeyword")).getText().trim();
			String sExpectedGRUPAKeyword = oTestData.getValue("GRUPAKeyword").trim();
			Assert.assertEquals(sActualGRUPAKeyword, sExpectedGRUPAKeyword, "Verify the GRUPA keyword in address screen!");

			boolean bIsContactsLKDisplayed = driver.findElement(By.logicalName(sScreenName + "ContactsLK")).isDisplayed();
			Assert.assertTrue(bIsContactsLKDisplayed, "Verify the contact link is displayed or not!");
			driver.findElement(By.logicalName(sScreenName + "ContactsLK")).click();

			boolean bIsGRUPCONTKeywordDisplayed = driver.findElement(By.logicalName(sScreenName + "Contacts.GRUPCONTKeyword")).isDisplayed();
			Assert.assertTrue(bIsGRUPCONTKeywordDisplayed, "Verify the GROUPCONT keyword in contact screen!");
			String sActualGRUPCONTKeyword = driver.findElement(By.logicalName(sScreenName + "Contacts.GRUPCONTKeyword")).getText().trim();
			String sExpectedGRUPCONTKeyword = oTestData.getValue("GRUPCONTKeyword").trim();
			Assert.assertEquals(sActualGRUPCONTKeyword, sExpectedGRUPCONTKeyword, "Verify the GROUPCONT keyword in contact screen!");

			boolean bIsGroupRatesLKDisplayed = driver.findElement(By.logicalName(sScreenName+"CommissionRatesLK")).isDisplayed();
			Assert.assertTrue(bIsGroupRatesLKDisplayed, "Verify the group commission rates link!");
			driver.findElement(By.logicalName(sScreenName+"CommissionRatesLK")).click();
			boolean bIsCOMMGKeywordDisplayed = driver.findElement(By.logicalName(sScreenName + "GroupRates.COMMGKeyword")).isDisplayed();
			Assert.assertTrue(bIsCOMMGKeywordDisplayed, "Verify the COMMG keyword in group rates screen!");
			String sActualCOMMGKeyword = driver.findElement(By.logicalName(sScreenName + "GroupRates.COMMGKeyword")).getText().trim();
			String sExpectedCOMMGKeyword = oTestData.getValue("COMMGKeyword").trim();
			Assert.assertEquals(sActualCOMMGKeyword, sExpectedCOMMGKeyword, "Verify the COMMG keyword in Group rates screen!");
		} catch (Exception e) {
			Assert.error(e, "Exception occured while verify the Group maintennace links and keyword in Screens!");
		}
	}
}
