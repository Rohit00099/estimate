package test.java.ormbframework.pageEvents;


// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import test.java.ormbframework.BaseTest;
import test.java.ormbframework.pageObjects.CommonPageElements;
import test.java.ormbframework.pageObjects.DealManagementPageElements;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
// import com.fasterxml.jackson.core.JsonParser;
// import com.fasterxml.jackson.core.type.TypeReference;


public class DealManagementPageEvents {
	WebDriver driver;

	public DealManagementPageEvents(WebDriver driver) {
		this.driver = driver;
	}

	static CommonFunctions CF = new CommonFunctions();
	static ApplicationFunctions AF = new ApplicationFunctions();
	static WebServiceFunctions WF = new WebServiceFunctions();

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchEntity
	,Description          : Search Entity to Create deal through UI for given entity,person ID,Division,Name	
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchEntity(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchEntity" + iStartingRow + " || sWorkbook:-" + sWorkbook + " || sheet-" + sSheetName);

		String sSearchEntity, sPersonID, sDivision, sPersonName;

		sSearchEntity = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sPersonID = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sPersonName = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

		System.out.println("Search entity,Person ID,Division,Person Name is " + sSearchEntity + sPersonID + sDivision + sPersonName);

		try {
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Dashboard_CreateDeal_Button);
			CF.FnSetFrame(driver, "tabPage");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_SearchEntity_Text);

			if (!sSearchEntity.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_SearchBy_DropDown, sSearchEntity);
				Thread.sleep(5000);
			}

			try {
				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonID_TextBox).isDisplayed()) {
					if (!sPersonID.equalsIgnoreCase("NoValue")) {
						Thread.sleep(5000);
						CF.FnSetText(driver, DealManagementPageElements.Deal_Information_PersonID_TextBox, sPersonID);
					}
				}
			} catch (Exception e) {
				System.out.println("Person ID text box not present");
			}

			try {
				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_AccountID_TextBox).isDisplayed()) {
					if (!sPersonID.equalsIgnoreCase("NoValue")) {
						Thread.sleep(5000);
						CF.FnSetText(driver, DealManagementPageElements.Deal_Information_AccountID_TextBox, sPersonID);
					}
				}
			} catch (Exception e) {
				System.out.println("Account ID text box not present");
			}


			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Search_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Broadcast_Button);
			Thread.sleep(10000);

			CF.FnTestCaseStatusReport("Pass", "Search Entity Completed Successfully for Deal Creation");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealCreation
	,Description          : Create deal through UI for given entity,person ID
	'###############################################################################################################################################################################################################################################*/
	public String FnDealCreation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnCreateDeal");
		String dateName = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String sDealIdentifier, sDealType, sDealCurrency, sSimulationType, sStartDate, sPriceSelectionDate, sReviewFrequency, sDealFrequency, sUsageFrequency, sDealDescription, sDealVersionDescription, sSkipReference, sSkipQuestionnaire, sTemplateDeal, sTnC1, sTnC2, sTnC3, sDealID, sDealIDValue1 = null;
		sDealIdentifier = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDealType = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sDealCurrency = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sSimulationType = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sStartDate = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sPriceSelectionDate = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sReviewFrequency = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sDealFrequency = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sUsageFrequency = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sDealDescription = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sDealVersionDescription = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sSkipReference = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		sSkipQuestionnaire = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
		sTemplateDeal = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
		sTnC1 = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();
		sTnC2 = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
		sTnC3 = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
		sDealID = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
		sDealIdentifier = sDealIdentifier + "_" + dateName;
		System.out.println("Search entity,Person ID,Division,Person Name is " + sDealIdentifier + sDealType + sDealCurrency + sSimulationType + sStartDate + sPriceSelectionDate + sReviewFrequency + sDealFrequency + sUsageFrequency + sDealDescription + sDealVersionDescription + sSkipReference + sSkipQuestionnaire + sTemplateDeal + sTnC1 + sTnC2 + sTnC3 + sDealID);
		try {
			CF.FnSetFrame(driver, "zoneMapFrame_2");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealIdentifier_TextBox);
			if (!sDealIdentifier.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Information_DealIdentifier_TextBox, sDealIdentifier);
			}
			if (!sDealType.equalsIgnoreCase("NoValue")) {
				String sParentWindow = driver.getWindowHandle();
				Thread.sleep(2000);
				CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_DealTypeSearch_Icon);
				Thread.sleep(8000);
				Set < String > handles = driver.getWindowHandles();
				for (String windowHandle: handles) {
					if (!windowHandle.equals(sParentWindow)) {
						driver.switchTo().window(windowHandle);
						Thread.sleep(5000);
						CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_DealTypeSearch_ExpandFilters_Button);
						Thread.sleep(3000);
						CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_DealTypeSearch_DealType_TextBox);
						Thread.sleep(2000);
						CF.FnClearTextFieldValue(driver, DealManagementPageElements.Deal_Information_DealTypeSearch_DealType_TextBox);
						Thread.sleep(2000);
						CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealTypeSearch_DealType_TextBox).sendKeys(sDealType);
						Thread.sleep(2000);
						CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_DealTypeSearch_Button);
						Thread.sleep(2000);
						//driver.close(); //closing child window
						driver.switchTo().window(sParentWindow); //cntrl to parent window
					}
				}
			}
			if (!sDealCurrency.equalsIgnoreCase("NoValue")) {
				Thread.sleep(5000);
				CF.FnSetFrame(driver, "zoneMapFrame_2");
				Thread.sleep(2000);
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_DealCurrency_DropDown, sDealCurrency);
			}
			if (!sSimulationType.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_SimulationType_DropDown, sSimulationType);
			}
			if (!sStartDate.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Information_StartDate_DateField, sStartDate);
			}
			if (!sPriceSelectionDate.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Information_PriceSelectionDate_DateField, sPriceSelectionDate);
			}
			if (!sReviewFrequency.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_ReviewFrequency_DropDown, sReviewFrequency);
			}
			if (!sDealFrequency.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_DealFrequency_DropDown, sDealFrequency);
			}
			if (!sUsageFrequency.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Information_UsagePeriod_DropDown, sUsageFrequency);
			}
			if (!sDealDescription.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Information_DealDescription_TextBox, sDealDescription);
			}
			if (!sDealVersionDescription.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Information_DealVersionDescription_TextBox, sDealVersionDescription);
			}
			if (!sSkipReference.equalsIgnoreCase("NoValue")) {
				CF.FnSelectCheckBox(driver, DealManagementPageElements.Deal_Information_SkipReference_CheckBox);
			}
			if (!sSkipQuestionnaire.equalsIgnoreCase("NoValue")) {
				CF.FnSelectCheckBox(driver, DealManagementPageElements.Deal_Information_SkipQuestionnaire_CheckBox);
			}
			/* Uncomment this code only if this is required.
		  *   if(!sTemplateDeal.equalsIgnoreCase("NoValue"))
				{
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Deal_Information_TemplateDeal_CheckBox);    										
				}
		  */
			//Uncomment this code only if this is required.
			/*
						if(!sTnC1.equalsIgnoreCase("NoValue"))
						{
							CF.FnSetText(driver, DealManagementPageElements.Deal_Information_TNC1_TextBox,sTnC1);    										
						}
					   
						if(!sTnC2.equalsIgnoreCase("NoValue"))
						{
							CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_TNC1Plus_Icon);
							CF.FnSetText(driver, DealManagementPageElements.Deal_Information_TNC2_TextBox,sTnC2);    										
						}
					   
						if(!sTnC3.equalsIgnoreCase("NoValue"))
						{
							CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_TNC2Plus_Icon);
							CF.FnSetText(driver, DealManagementPageElements.Deal_Information_TNC3_TextBox,sTnC3);    										
						}  */
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Save_Button);
			Thread.sleep(8000);
			sDealIDValue1 = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealID_TextBox).getAttribute("value");
			System.out.println("Deal ID by value is " + sDealIDValue1);
			assertTrue(!sDealIDValue1.isEmpty(), "Deal ID is generated and Deal ID is not empty");
			CF.FnTestCaseStatusReport("Pass", "Deal Is Created Successfully. Deal ID-> " + sDealIDValue1);
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
		return sDealIDValue1;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealInformationFromDealCreation
	,Description          : Navigation to Deal Information UI from deal creation page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealInformationFromDealCreation() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealInformationFromDealCreation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_2");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Next_Button);
			Thread.sleep(8000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Information UI from deal creation page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealInformationVerification
	,Description          : Deal Information Verification through UI created deal
	'###############################################################################################################################################################################################################################################*/
	public void FnDealInformationVerification(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnDealInformationVerification");

		String sDealInformation, sDealStatus, sDealVersionStatus;

		sDealInformation = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDealStatus = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sDealVersionStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails);

			String ExpandDealInformationTab = "//*[@oramdlabel=\"DEALINFO_TLBL\" and @title=\"Deal Information - Deal Information\"]";
			CF.FnElementClick(driver, ExpandDealInformationTab);

			Thread.sleep(5000);

			if (!sDealInformation.equalsIgnoreCase("NoValue")) {
				String sDealInformationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails).getText().trim();
				System.out.println("Deal information is " + sDealInformationFromApplication);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails).isDisplayed(), "Deal Information is displayed");
			}

			if (!sDealStatus.equalsIgnoreCase("NoValue")) {
				String sDealStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealStatus_TextDetails).getText().trim();
				System.out.println("Deal status is " + sDealStatusFromApplication);
				assertEquals(sDealStatus.toLowerCase(), sDealStatusFromApplication.toLowerCase());
			}

			if (!sDealVersionStatus.equalsIgnoreCase("NoValue")) {
				String sDealVersionStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealVersionStatus_TextDetails).getText().trim();
				System.out.println("Deal Version status is " + sDealVersionStatusFromApplication);
				assertEquals(sDealVersionStatus.toLowerCase(), sDealVersionStatusFromApplication.toLowerCase());
			}


			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Information Verification Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToViewAndAssignPrizelist
	,Description          : Navigation to View And Assign Prizelist UI from deal information page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToViewAndAssignPrizelist() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToViewAndAssignPrizelist");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(3000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Click_On_Customer_Action_Icon);
			Thread.sleep(5000);
			CF.FnGetWebElement(driver, "LINKTEXT", DealManagementPageElements.Deal_Information_ViewAndAssignProposedPriceList_PencilIcon).click();
			Thread.sleep(5000);

			CF.FnTestCaseStatusReport("Pass", "Navigation to View And Assign Prizelist UI from Deal information page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnPriceListAssignment
	,Description          : To assign prizelist for given prize list ID
	'###############################################################################################################################################################################################################################################*/
	public void FnPriceListAssignment(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnPriceListAssignment");

		String sPriceListID, sPriority, sEffectiveStartDate;

		sPriceListID = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sPriority = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sEffectiveStartDate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();


		System.out.println("Prize list assignment information is " + sPriceListID + sPriority + sEffectiveStartDate);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Assign_Prizelist_PriceList_TextBox);
			Thread.sleep(10000);

			if (!sPriceListID.equalsIgnoreCase("NoValue")) {
				Thread.sleep(5000);
				CF.FnSetText(driver, DealManagementPageElements.Assign_Prizelist_PriceList_TextBox, sPriceListID);
			}

			if (!sPriority.equalsIgnoreCase("NoValue")) {
				Thread.sleep(5000);
				CF.FnSetText(driver, DealManagementPageElements.Assign_Prizelist_Priority_TextBox, sPriority);
			}

			CF.FnElementClick(driver, DealManagementPageElements.Assign_Prizelist_SaveAndSelectPriceItemGroup_Button);

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Prizelist assignment For Given Prize List ID Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchFiltersForSelectPrizeItemgroup
	,Description          : To search prize filters and assign prizelist
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchFiltersForSelectPrizeItemgroup(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchFiltersForSelectPrizeItemgroup");

		String sSearchKeyword, sSerchExpectedResult, sAssignmentLevelCustomerAgreed, sAssignmentLevelCustomerPricelist, sSelectAllCustomerAgreed, sSelectAllCustomerPricelist, sSearchKeywordFromTest;

		sSearchKeyword = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sSerchExpectedResult = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sAssignmentLevelCustomerAgreed = CF.FnGetCellValue(iStartingRow + 1, 2, sSheetName, sWorkbook).toString().trim();
		sAssignmentLevelCustomerPricelist = CF.FnGetCellValue(iStartingRow + 2, 2, sSheetName, sWorkbook).toString().trim();
		sSelectAllCustomerAgreed = CF.FnGetCellValue(iStartingRow + 1, 3, sSheetName, sWorkbook).toString().trim();
		sSelectAllCustomerPricelist = CF.FnGetCellValue(iStartingRow + 2, 3, sSheetName, sWorkbook).toString().trim();
		sSearchKeywordFromTest = "CC_032";

		System.out.println("Prize list assignment information is " + sSearchKeyword + sSerchExpectedResult + sAssignmentLevelCustomerAgreed + sAssignmentLevelCustomerPricelist + sSelectAllCustomerAgreed + sSelectAllCustomerPricelist);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Title_Label);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Filters_Label);


			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(2000);
			}

			if (!sSearchKeyword.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeyword);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
			}

			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_NoDatatoDisplayMessage_Label).isDisplayed(), "No data to Display message is shown for given search criteria");
			
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_Clear_SearchFilters);

			System.out.println("11111111111111111111111111");
			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(2000);
			}
			
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeywordFromTest);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);

			System.out.println("2222222222222222222222222222");
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_NoDatatoDisplayMessage_Label).isDisplayed(), "No data to Display message is shown for given search criteria");

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_Clear_SearchFilters);
			CF.FnSetFrame(driver, "uiMap");

			if (!sAssignmentLevelCustomerAgreed.equalsIgnoreCase("NoValue")) {
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
//				CF.FnSelectValue(driver, DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown, sAssignmentLevelCustomerAgreed);
				CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown).click();
				String Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath = DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown_Value.replaceAll("ReplacePriceAssignmentStatus", sAssignmentLevelCustomerAgreed);
				CF.FnElementClick(driver, Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath);

				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SelectAll_Button);
				Thread.sleep(5000);
				for (WebElement prizeItemcheckBox: CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBoxList)) {
					Thread.sleep(2000);
					assertTrue(prizeItemcheckBox.isDisplayed(), "All the prize list checkes are selected");
				}
			}


			if (!sAssignmentLevelCustomerPricelist.equalsIgnoreCase("NoValue")) {
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
//				CF.FnSelectValue(driver, DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown, sAssignmentLevelCustomerPricelist);
				CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown).click();
				String Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath = DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown_Value.replaceAll("ReplacePriceAssignmentStatus", sAssignmentLevelCustomerPricelist);
				CF.FnElementClick(driver, Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath);

				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SelectAll_Button);
				Thread.sleep(5000);
				for (WebElement prizeItemcheckBox: CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBoxList)) {
					Thread.sleep(2000);
					assertTrue(prizeItemcheckBox.isDisplayed(), "All the prize list checkes are selected");
				}
			}

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SaveAndViewPrizingAndCommitments_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User is returned to Pricing and Commitments Page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Search Prize Filters And Assign Prizelist Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchFiltersForSelectPerticularPrizeItemForAccount
	,Description          : To search prize filters and assign prizelist
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchFiltersForSelectPerticularPrizeItemForAccount(int iStartingRow, String sSheetName, String sWorkbook, String sPrizeItem1, String sPrizeItem2, String sPrizeItem3, String sPrizeItem4, String sPrizeItem5, String sPrizeItem6, String sPrizeItem7, String sSearchKeywordFromTest) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchFiltersForSelectPerticularPrizeItemForAccount");

		String sSearchKeyword, sSerchExpectedResult, sAssignmentLevelAccountPricelist;

		sSearchKeyword = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sSerchExpectedResult = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sAssignmentLevelAccountPricelist = CF.FnGetCellValue(iStartingRow + 1, 2, sSheetName, sWorkbook).toString().trim();

		System.out.println("Prize list assignment information is " + sSearchKeyword + sSerchExpectedResult + sAssignmentLevelAccountPricelist);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Title_Label);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Filters_Label);
			Thread.sleep(10000);

			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(2000);
			}

			if (!sSearchKeyword.equalsIgnoreCase("NoValue")) {
				
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeyword);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);

				Thread.sleep(2000);
			}

			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_NoDatatoDisplayMessage_Label).isDisplayed(), "No data to Display message is shown for given search criteria");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_Clear_SearchFilters);
			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(2000);
			}
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeywordFromTest);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_NoDatatoDisplayMessage_Label).isDisplayed(), "No data to Display message is shown for given search criteria");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_Clear_SearchFilters);
			CF.FnSetFrame(driver, "uiMap");

			if (!sAssignmentLevelAccountPricelist.equalsIgnoreCase("NoValue")) {
				
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown).click();
				
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_Value_From_DropDown.replaceAll("ReplacePriceAssignmentValue", sAssignmentLevelAccountPricelist));
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
//				CF.FnSelectValue(driver, DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown, sAssignmentLevelAccountPricelist);

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem1);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				String PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem1);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

//				Thread.sleep(5000);
//				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
//				Thread.sleep(2000);
//				CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
//				Thread.sleep(2000);
//				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem1);
//				Thread.sleep(5000);
//				CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_PrizeItemCheckBoxAfterSearch_Checkbox);
//				CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Select_Prize_Item_group_PrizeItemCheckBoxAfterSearch_Checkbox);
//				Thread.sleep(3000);
//				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem2);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem2);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem3);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem3);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				
				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem4);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem4);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem5);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem5);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem6);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem6);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

				Thread.sleep(5000);
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(5000);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem7);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
				PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
				PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPrizeItem7);
				CF.FnElementClick(driver, PriceItemPath);
				Thread.sleep(3000);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is selected");

			}

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SaveAndViewPrizingAndCommitments_Button);
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User is returned to Pricing and Commitments Page");
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SelectPriceItemGroup_Button);
			Thread.sleep(5000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox).isDisplayed()) {
				Thread.sleep(2000);
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem7);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox);
			CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Select_Prize_Item_group_PrizeItemSelectedCheckBoxAfterSearch_Checkbox);
			Thread.sleep(3000);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_PrizeItemCheckBoxAfterSearch_Checkbox).isDisplayed(), "Check box is de selected");

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SaveAndViewPrizingAndCommitments_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem7);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_NoDataDisplayedAfterPrizeItemSearch_Button);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_NoDataDisplayedAfterPrizeItemSearch_Button).isDisplayed(), "User is not able to view unchecked price item PI_031 in the screen");
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBack_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Search Prize Item Filters and Assign prizelist Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerificationOfTermsAndConditionsForCorporateBanking
	,Description          : To verify terms and conditions for corporate banking
	'###############################################################################################################################################################################################################################################*/
	public void FnVerificationOfTermsAndConditionsForCorporateBanking() throws Exception {
		System.out.println(">>>>>>>>>>--FnVerificationOfTermsAndConditionsForCorporateBanking");

		try {
			String sExpectedTermsAndConditionsFromTest_1, sExpectedTermsAndConditionsFromTest_2;
			sExpectedTermsAndConditionsFromTest_1 = "DEAL_T&C7";
			sExpectedTermsAndConditionsFromTest_2 = "DEAL_T&C8";

			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_TermsAndConditionsForCorporateBanking_PencilIcon);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel).isDisplayed(), "User is Navigated to Mapping Terms and Conditions");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_1, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition1_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_2, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition2_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			CF.FnElementClick(driver, DealManagementPageElements.Mapping_Terms_And_Conditions_Cancel_Button);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText));
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User is returned to Pricing and Commitments Page");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(8000);
			CF.FnTestCaseStatusReport("Pass", "Terms and Conditions Verification For Corporate Banking Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerificationOfTermsAndConditionsForAccountServices
	,Description          : To verify terms and conditions for Account services
	'###############################################################################################################################################################################################################################################*/
	public void FnVerificationOfTermsAndConditionsForAccountServices() throws Exception {
		System.out.println(">>>>>>>>>>--FnVerificationOfTermsAndConditionsForAccountServices");

		try {
			String sExpectedTermsAndConditionsFromTest_1, sExpectedTermsAndConditionsFromTest_2;
			sExpectedTermsAndConditionsFromTest_1 = "DEAL_T&C5";
			sExpectedTermsAndConditionsFromTest_2 = "DEAL_T&C6";

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");

			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CorporateBanking_ExpandButton);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_AccountServices_ExpandButton);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_TermsAndConditionsForAccountServices_PencilIcon);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel).isDisplayed(), "User is Navigated to Mapping Terms and Conditions");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_1, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition1_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_2, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition2_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			CF.FnElementClick(driver, DealManagementPageElements.Mapping_Terms_And_Conditions_Cancel_Button);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText));
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User is returned to Pricing and Commitments Page");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Terms and Conditions Verification For Account Services Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerificationOfTermsAndConditionsForPrizeItem
	,Description          : To verify terms and conditions for Prize Item
	'###############################################################################################################################################################################################################################################*/
	public void FnVerificationOfTermsAndConditionsForPrizeItem() throws Exception {
		System.out.println(">>>>>>>>>>--FnVerificationOfTermsAndConditionsForPrizeItem");

		try {
			String sExpectedTermsAndConditionsFromTest_1, sExpectedTermsAndConditionsFromTest_2;
			sExpectedTermsAndConditionsFromTest_1 = "DEAL_T&C3";
			sExpectedTermsAndConditionsFromTest_2 = "DEAL_T&C4";

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");

			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CorporateBanking_ExpandButton);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_AccountServices_ExpandButton);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_TermsAndConditionsForFirstPrizeItem_PencilIcon);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_Title_HeadingLabel).isDisplayed(), "User is Navigated to Mapping Terms and Conditions");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_1, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition1_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			Thread.sleep(1000);
			assertEquals(sExpectedTermsAndConditionsFromTest_2, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Mapping_Terms_And_Conditions_TermsAndCondition2_TextBox).getAttribute("value").trim(), "Terms and Conditions from first text box matches");
			CF.FnElementClick(driver, DealManagementPageElements.Mapping_Terms_And_Conditions_Cancel_Button);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText));
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User is returned to Pricing and Commitments Page");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Terms and Conditions Verification For Prize Items Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealInformationFromPricingAndCommitments
	,Description          : Navigation to Deal Information UI from Pricing And Commitments page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealInformationFromPricingAndCommitments() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealInformationFromPricingAndCommitments");

		try {
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(3000);
			try {
				CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBack_Button_0);
			} catch (Exception ere) {
				CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBack_Button);
			}
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Information UI from Pricing And Commitments page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealSimulationOnDealInformationPage
	,Description          : To Stimulate Deal
	'###############################################################################################################################################################################################################################################*/
	public void FnDealSimulationOnDealInformationPage() throws Exception {
		System.out.println(">>>>>>>>>>--FnDealSimulationOnDealInformationPage");

		try {

			driver = BaseTest.getDriver();
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_Button);
			Thread.sleep(5000);


			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(8000);
			//driver.close(); //closing child window
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_OK_Button);
			Thread.sleep(2000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(10000);
			CF.FnTestCaseStatusReport("Pass", "Deal Simulation Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndEditPricing
	,Description          : To view and edit pricing
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndEditPricing(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndEditPricing");

		String sRevenue, sCost;

		sRevenue = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sCost = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();

		System.out.println("Deal pricing information is " + sRevenue + sCost);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 120, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");

			if (!sRevenue.equalsIgnoreCase("NoValue")) {
				String sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalRevenueCorporateBanking_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Revenue value from application is " + sRevenueFromApplication);
				assertEquals(sRevenue, sRevenueFromApplication);
			}

			if (!sCost.equalsIgnoreCase("NoValue")) {
				String sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalCostCorporateBanking_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Cost value from application is " + sCostFromApplication);
				assertEquals(sCost, sCostFromApplication);
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndEditPricingForAccounts
	,Description          : To view and edit pricing and verify revenue and cost for corporate banking,AccountServices and Reporting/Swift
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndEditPricingForAccounts(int iStartingRowForCorporateBanking, int iStartingRowForAccountServices, int iStartingRowForReportingAndSwift, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndEditPricingForAccounts");

		String sRevenueForCorporateBanking, sCostForCorporateBanking, sRevenueForAccountServices, sCostForAccountServices, sRevenueForReportingAndSwift, sCostForForReportingAndSwift;

		sRevenueForCorporateBanking = CF.FnGetCellValue(iStartingRowForCorporateBanking, 19, sSheetName, sWorkbook).toString().trim();
		sCostForCorporateBanking = CF.FnGetCellValue(iStartingRowForCorporateBanking, 20, sSheetName, sWorkbook).toString().trim();
		sRevenueForAccountServices = CF.FnGetCellValue(iStartingRowForAccountServices, 19, sSheetName, sWorkbook).toString().trim();
		sCostForAccountServices = CF.FnGetCellValue(iStartingRowForAccountServices, 20, sSheetName, sWorkbook).toString().trim();
		sRevenueForReportingAndSwift = CF.FnGetCellValue(iStartingRowForReportingAndSwift, 19, sSheetName, sWorkbook).toString().trim();
		sCostForForReportingAndSwift = CF.FnGetCellValue(iStartingRowForReportingAndSwift, 20, sSheetName, sWorkbook).toString().trim();

		System.out.println("Deal pricing information is " + sRevenueForCorporateBanking + sCostForCorporateBanking);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnSetFrame(driver, "uiMap");

			if (!sRevenueForCorporateBanking.equalsIgnoreCase("NoValue")) {
				String sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalRevenueCorporateBanking_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Revenue value from application is " + sRevenueFromApplication);
				assertEquals(sRevenueForCorporateBanking, sRevenueFromApplication);
			}

			if (!sCostForCorporateBanking.equalsIgnoreCase("NoValue")) {
				String sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalCostCorporateBanking_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Cost value from application is " + sCostFromApplication);
				assertEquals(sCostForCorporateBanking, sCostFromApplication);
			}

			if (!sRevenueForAccountServices.equalsIgnoreCase("NoValue")) {
				String sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalRevenueAccountServices_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Revenue value from application is " + sRevenueFromApplication);
				assertEquals(sRevenueForAccountServices, sRevenueFromApplication);
			}

			if (!sCostForAccountServices.equalsIgnoreCase("NoValue")) {
				String sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalCostAccountServices_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Cost value from application is " + sCostFromApplication);
				assertEquals(sCostForAccountServices, sCostFromApplication);
			}

			if (!sRevenueForReportingAndSwift.equalsIgnoreCase("NoValue")) {
				String sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalRevenueReportingAndSwift_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Revenue value from application is " + sRevenueFromApplication);
				assertEquals(sRevenueForReportingAndSwift, sRevenueFromApplication);
			}

			if (!sCostForForReportingAndSwift.equalsIgnoreCase("NoValue")) {
				String sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_TotalCostReportingAndSwift_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Cost value from application is " + sCostFromApplication);
				assertEquals(sCostForForReportingAndSwift, sCostFromApplication);
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Revenue and Cost Verification For Corporate Banking,Account Services and Reporting/Swift on View and Edit Pricing Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigateToViewAndEditPricing
	,Description          : To Navigate to view and edit pricing on Pricing and commitment screen
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigateToViewAndEditPricing() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigateToViewAndEditPricing");

		try {

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(3000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Click_On_Customer_Action_Icon);
			Thread.sleep(5000);
			CF.FnGetWebElement(driver, "LINKTEXT", DealManagementPageElements.Deal_Information_ViewAndEditPricing_PencilIcon).click();
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnTestCaseStatusReport("Pass", "Navigation to View and Edit Pricing UI on Pricing and Commitment Screen Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPersonalHierarchyInformationOnDealInformation
	,Description          : Personal Hierarchy Information Verification through UI On Deal Information
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPersonalHierarchyInformationOnDealInformation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyPersonalHierarchyInformationOnDealInformation");

		String sPersonName, sDivision, sStatus, sRevenueVaration, sProjectedRevenue, sProjectedCost, sOriginalRevenue, sOriginalCost;

		sPersonName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sRevenueVaration = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

		System.out.println("Personal Hierarchy Information is " + sPersonName + sDivision + sStatus + sRevenueVaration + sProjectedRevenue + sProjectedCost + sOriginalRevenue + sOriginalCost);

		try {
			
			
			
			Thread.sleep(3000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(8000);


			CF.FnGetWebElement(driver, "ID", "compareType-labelled-by").click();
			Thread.sleep(3000);
//			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareType_Path);
//			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath);
			
			String Deal_Information_PersonalHierarchyPersonName_Label_Path = DealManagementPageElements.Deal_Information_PersonalHierarchyPersonName_Label.replaceAll("ReplacePersonName", sPersonName);
			CF.FnWaitForElement(driver, 360, Deal_Information_PersonalHierarchyPersonName_Label_Path);
		
			Thread.sleep(5000);

			if (!sPersonName.equalsIgnoreCase("NoValue")) {
				String sPersonNameFromApplication = CF.FnGetWebElement(driver, "XPATH", Deal_Information_PersonalHierarchyPersonName_Label_Path).getText().trim();
				Thread.sleep(3000);
				System.out.println("PersonName value from application is " + sPersonNameFromApplication);
				assertEquals(sPersonName, sPersonNameFromApplication);
			}

			if (!sDivision.equalsIgnoreCase("NoValue")) {
				String sDivisionFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionName_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim();
				Thread.sleep(3000);
				System.out.println("Division value from application is " + sDivisionFromApplication);
				assertEquals(sDivision, sDivisionFromApplication);
			}

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedRevenueValue_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Revenue from application is " + sProjectedRevenueFromApplication);
				System.out.println("sProjectedRevenue:-" + sProjectedRevenue + " || sProjectedRevenueFromApplication:-" + sProjectedRevenueFromApplication);
				if (!sProjectedRevenueFromApplication.equals(sProjectedRevenue)) {
					assertEquals(sProjectedRevenue, sProjectedRevenueFromApplication);
				}
			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedCostValue_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Cost from application is " + sProjectedCostFromApplication);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}

			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalRevenueValue_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalRevenueFromApplication);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalCostValue_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sOriginalCostFromApplication);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Personal Hierarchy Information Verification on Deal Information Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealFinancialSummaryOnDealInformation
	,Description          : To verify Deal Financial Summary Information On Deal Information page through UI
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealFinancialSummaryOnDealInformation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyDealFinancialSummaryOnDealInformation");

		String sApprovalStatus, sProjectedRevenue, sProjectedCost, sProjectedProfit, sProjectedProfitPercentage, sOriginalRevenue, sOriginalCost, sOriginalProfit, sOriginalProfitPercentage, sProfitVaration;

		sApprovalStatus = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProjectedProfit = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedProfitPercentage = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sOriginalProfit = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sOriginalProfitPercentage = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sProfitVaration = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();

		System.out.println("Deal Financial Summary Information is " + sApprovalStatus + sProjectedRevenue + sProjectedCost + sProjectedProfit + sProjectedProfitPercentage + sOriginalRevenue + sOriginalCost + sOriginalProfit + sOriginalProfitPercentage + sProfitVaration);

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_2");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealFinancialSummary_Label);

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedRevenueValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("PersonName value from application is " + sProjectedRevenueFromApplication);
				assertTrue(sProjectedRevenueFromApplication.contains(sProjectedRevenue));
			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedCostValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Division value from application is " + sProjectedCostFromApplication);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}


			if (!sProjectedProfitPercentage.equalsIgnoreCase("NoValue")) {
				String sProjectedProfitPercentageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedProfitPercentValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Revenue from application is " + sProjectedProfitPercentageFromApplication);
				assertTrue(sProjectedProfitPercentageFromApplication.contains(sProjectedProfitPercentage));

			}

			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalRevenueValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalRevenueFromApplication);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalCostValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sOriginalCostFromApplication);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}



			if (!sOriginalProfitPercentage.equalsIgnoreCase("NoValue")) {
				String sOriginalProfitPercentageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalProfitPercentValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalProfitPercentageFromApplication);
				assertTrue(sOriginalProfitPercentageFromApplication.contains(sOriginalProfitPercentage));

			}

			if (!sProfitVaration.equalsIgnoreCase("NoValue")) {
				String sProfitVarationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalProfitVariationValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sProfitVarationFromApplication);
				assertTrue(sProfitVarationFromApplication.contains(sProfitVaration));
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Financial Summary Information Verification on Deal Information Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToAdhocRevenueAndCostCreationFromDealInformation
	,Description          : Navigation to Adhoc Revenue And Cost Creation UI from deal information page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToAdhocRevenueAndCostCreationFromDealInformation() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToAdhocRevenueAndCostCreationFromDealInformation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_AdhocRevenueAndCost_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Adhoc_Revenue_And_Cost_Title_Label).isDisplayed(), "User is Navigated to Revenue and Cost page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Adhoc Revenue and Cost Creation UI from Deal information Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreationOfAdhocRevenueAndCost
	,Description          : Create Adhoc Revenue and Cost through UI for given entity,person ID
	'###############################################################################################################################################################################################################################################*/
	public void FnCreationOfAdhocRevenueAndCost(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnCreationOfAdhocRevenueAndCost");

		String sSequence_1, sCurrency_1, sAmount_1, sDivision_1, sPriceItem_1, sRevenueOrCostType_1, sAmountType_1, sSequence_2, sCurrency_2, sAmount_2, sDivision_2, sPriceItem_2, sRevenueOrCostType_2, sAmountType_2, sSequence_3, sCurrency_3, sAmount_3, sDivision_3, sPriceItem_3, sRevenueOrCostType_3, sAmountType_3, sSequence_4, sCurrency_4, sAmount_4, sDivision_4, sPriceItem_4, sRevenueOrCostType_4, sAmountType_4;

		sSequence_1 = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sCurrency_1 = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sAmount_1 = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sDivision_1 = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sPriceItem_1 = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sRevenueOrCostType_1 = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sAmountType_1 = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sSequence_2 = CF.FnGetCellValue(iStartingRow + 1, 1, sSheetName, sWorkbook).toString().trim();
		sCurrency_2 = CF.FnGetCellValue(iStartingRow + 1, 2, sSheetName, sWorkbook).toString().trim();
		sAmount_2 = CF.FnGetCellValue(iStartingRow + 1, 3, sSheetName, sWorkbook).toString().trim();
		sDivision_2 = CF.FnGetCellValue(iStartingRow + 1, 4, sSheetName, sWorkbook).toString().trim();
		sPriceItem_2 = CF.FnGetCellValue(iStartingRow + 1, 5, sSheetName, sWorkbook).toString().trim();
		sRevenueOrCostType_2 = CF.FnGetCellValue(iStartingRow + 1, 6, sSheetName, sWorkbook).toString().trim();
		sAmountType_2 = CF.FnGetCellValue(iStartingRow + 1, 7, sSheetName, sWorkbook).toString().trim();
		sSequence_3 = CF.FnGetCellValue(iStartingRow + 2, 1, sSheetName, sWorkbook).toString().trim();
		sCurrency_3 = CF.FnGetCellValue(iStartingRow + 2, 2, sSheetName, sWorkbook).toString().trim();
		sAmount_3 = CF.FnGetCellValue(iStartingRow + 2, 3, sSheetName, sWorkbook).toString().trim();
		sDivision_3 = CF.FnGetCellValue(iStartingRow + 2, 4, sSheetName, sWorkbook).toString().trim();
		sPriceItem_3 = CF.FnGetCellValue(iStartingRow + 2, 5, sSheetName, sWorkbook).toString().trim();
		sRevenueOrCostType_3 = CF.FnGetCellValue(iStartingRow + 2, 6, sSheetName, sWorkbook).toString().trim();
		sAmountType_3 = CF.FnGetCellValue(iStartingRow + 2, 7, sSheetName, sWorkbook).toString().trim();
		sSequence_4 = CF.FnGetCellValue(iStartingRow + 3, 1, sSheetName, sWorkbook).toString().trim();
		sCurrency_4 = CF.FnGetCellValue(iStartingRow + 3, 2, sSheetName, sWorkbook).toString().trim();
		sAmount_4 = CF.FnGetCellValue(iStartingRow + 3, 3, sSheetName, sWorkbook).toString().trim();
		sDivision_4 = CF.FnGetCellValue(iStartingRow + 3, 4, sSheetName, sWorkbook).toString().trim();
		sPriceItem_4 = CF.FnGetCellValue(iStartingRow + 3, 5, sSheetName, sWorkbook).toString().trim();
		sRevenueOrCostType_4 = CF.FnGetCellValue(iStartingRow + 3, 6, sSheetName, sWorkbook).toString().trim();
		sAmountType_4 = CF.FnGetCellValue(iStartingRow + 3, 7, sSheetName, sWorkbook).toString().trim();

		System.out.println("Adhoc Revenue and Cost is " + sSequence_1 + sCurrency_1 + sAmount_1 + sDivision_1 + sPriceItem_1 + sRevenueOrCostType_1 + sAmountType_1 + sSequence_2 + sCurrency_2 + sAmount_2 + sDivision_2 + sPriceItem_2 + sRevenueOrCostType_2 + sAmountType_2 + sSequence_3 + sCurrency_3 + sAmount_3 + sDivision_3 + sPriceItem_3 + sRevenueOrCostType_3 + sAmountType_3 + sSequence_4 + sCurrency_4 + sAmount_4 + sDivision_4 + sPriceItem_4 + sRevenueOrCostType_4 + sAmountType_4);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Adhoc_Revenue_And_Cost_Title_Label);

			if (!sSequence_1.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence1_TextBox, sSequence_1);
			}

			if (!sCurrency_1.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Currency1_DropDown, sCurrency_1);
			}

			if (!sAmount_1.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Amount1_TextBox, sAmount_1);
			}

			if (!sDivision_1.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Division1_DropDown, sDivision_1);
			}

			if (!sRevenueOrCostType_1.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_RevenueCostType1_DropDown, sRevenueOrCostType_1);
			}

			if (!sAmountType_1.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_AmountType1_DropDown, sAmountType_1);
			}

			CF.FnElementClick(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_PlusToAddRow1_Button);
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence2_TextBox);

			if (!sSequence_2.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence2_TextBox, sSequence_2);
			}

			if (!sCurrency_2.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Currency2_DropDown, sCurrency_2);
			}

			if (!sAmount_2.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Amount2_TextBox, sAmount_2);
			}

			if (!sDivision_2.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Division2_DropDown, sDivision_2);
			}

			if (!sRevenueOrCostType_2.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_RevenueCostType2_DropDown, sRevenueOrCostType_2);
			}

			if (!sPriceItem_2.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_PrizeItem2_TextBox, sPriceItem_2);
			}


			if (!sAmountType_2.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_AmountType2_DropDown, sAmountType_2);
			}

			CF.FnElementClick(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_PlusToAddRow2_Button);
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence3_TextBox);

			if (!sSequence_3.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence3_TextBox, sSequence_3);
			}

			if (!sCurrency_3.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Currency3_DropDown, sCurrency_3);
			}

			if (!sAmount_3.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Amount3_TextBox, sAmount_3);
			}

			if (!sDivision_3.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Division3_DropDown, sDivision_3);
			}

			if (!sRevenueOrCostType_3.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_RevenueCostType3_DropDown, sRevenueOrCostType_3);
			}

			if (!sAmountType_3.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_AmountType3_DropDown, sAmountType_3);
			}

			CF.FnElementClick(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_PlusToAddRow3_Button);
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence4_TextBox);

			if (!sSequence_4.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Sequence4_TextBox, sSequence_4);
			}

			if (!sCurrency_4.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Currency4_DropDown, sCurrency_4);
			}

			if (!sAmount_4.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Amount4_TextBox, sAmount_4);
			}

			if (!sDivision_4.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Division4_DropDown, sDivision_4);
			}

			if (!sRevenueOrCostType_4.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_RevenueCostType4_DropDown, sRevenueOrCostType_4);
			}

			if (!sAmountType_4.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_AmountType4_DropDown, sAmountType_4);
			}

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Adhoc_Revenue_And_Cost_Save_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Adhoc Revenue and Cost Creation Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemsRates
	,Description          : To edit rates prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdatePrizeItemsRates(String sRateOfPriceItem1, String sRateValueOfPriceItem1, String sRateOfPriceItem2, String sRateValueOfPriceItem2, String sRateOfPriceItem3, String sRateValueOfPriceItem3, String sRateOfPriceItem4, String sRateValueOfPriceItem4, String sRateOfPriceItem5, String sRateValueOfPriceItem5) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdatePrizeItemsRates");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).sendKeys(sRateValueOfPriceItem1);
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).sendKeys(sRateValueOfPriceItem2);
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).sendKeys(sRateValueOfPriceItem3);
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).sendKeys(sRateValueOfPriceItem4);
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem5);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).sendKeys(sRateValueOfPriceItem5);
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem1, "Changed rate of PI_022 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem2, "Changed rate of PI_033 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem3, "Changed rate of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem4, "Changed rate of PI_028 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem5);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem5, "Changed rate of PI_031 is reflected after search");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Rates Update of Prize Items Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemsVolumes
	,Description          : To edit volumes of prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdatePrizeItemsVolumes(String sVolumeOfPrizeItem1, String sVolumeValueOfPrizeItem1, String sVolumeOfPrizeItem2, String sVolumeValueOfPrizeItem2, String sVolumeOfPrizeItem3, String sVolumeValueOfPrizeItem3) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdatePrizeItemsVolumes");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem1);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).sendKeys(sVolumeValueOfPrizeItem1);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem2);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).sendKeys(sVolumeValueOfPrizeItem2);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem3);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).sendKeys(sVolumeValueOfPrizeItem3);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem1);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem1, "Changed Volume of PI_022 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem2);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem2, "Changed Volume of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem3);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem3, "Changed Volume of PI_028 is reflected after search");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Volume Update of Prize Items Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdateRatesOfPrizeItemsThroughSeasonalLink
	,Description          : To add seasonal price to prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdateRatesOfPrizeItemsThroughSeasonalLink(String sRateOfPriceItem1, String sRateValueOfPriceItem1, String sRateOfPriceItem2, String sRateValueOfPriceItem2, String sRateOfPriceItem3, String sRateValueOfPriceItem3, String sRateOfPriceItem4, String sRateValueOfPriceItem4, String sRateOfPriceItem5, String sRateValueOfPriceItem5) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdateRatesOfPrizeItemsThroughSeasonalLink");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			try {
				if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).isDisplayed()) {
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
				}
			} catch (Exception e) {
				System.out.println("Rate text box is not presenet");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Assign_Prizelist_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			try {
				if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).isDisplayed()) {
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
				}
			} catch (Exception e) {
				System.out.println("Rate text box is not presenet");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			try {
				if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).isDisplayed()) {
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
				}
			} catch (Exception e) {
				System.out.println("Rate text box is not presenet");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem3);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			try {
				if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).isDisplayed()) {
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
				}
			} catch (Exception e) {
				System.out.println("Rate text box is not presenet");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem4);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem5);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			try {
				if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).isDisplayed()) {
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
				}
			} catch (Exception e) {
				System.out.println("Rate text box is not presenet");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem5);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem1, "Changed rate of PI_022 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem2, "Changed rate of PI_033 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem3, "Changed rate of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem4, "Changed rate of PI_028 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem5);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem5, "Changed rate of PI_031 is reflected after search");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Rates Update of Prize Items through Seasonal link Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdateRatesOfPrizeItemsThroughSeasonalLinkAfterRecommendation
	,Description          : To add seasonal price to prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdateRatesOfPrizeItemsThroughSeasonalLinkAfterRecommendation(String sRateOfPriceItem1, String sRateValueOfPriceItem1, String sRateOfPriceItem2, String sRateValueOfPriceItem2) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdateRatesOfPrizeItemsThroughSeasonalLinkAfterRecommendation");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(10000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");

			if (BaseTest.sScriptName == "DealManagement_TC001") {
				Thread.sleep(5000);
				CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox); //error	
			}

			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem2);
			Thread.sleep(2000);


			try {
				CF.FnElementClick(driver, DealManagementPageElements.OverrideOriginalPricing); // account failing for override button PI_028
			} catch (Exception ae) {
				if (BaseTest.sScriptName == "DealManagement_TC001") {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button); // for customer
				}
				CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button); // for Account
			}


			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(10000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem1, "Changed rate of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(10000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem2, "Changed rate of PI_029 is reflected after search");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Update of Seasonal Price of Prize Items After Recommendation Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddSeasonalPriceToPrizeItems
	,Description          : To add seasonal price to prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnAddSeasonalPriceToPrizeItems(int iStartingRow, String sSheetName, String sWorkbook, String sSeasonalPrizeForPrizeItem1, String sSeasonalPrizeForPrizeItem2, String sSeasonalPrizeForPrizeItem3, String sSeasonalPrizeForPrizeItem4_1, String sSeasonalPrizeForPrizeItem4_2, String sSeasonalPrizeForPrizeItem4_3) throws Exception {
		System.out.println(">>>>>>>>>>--FnAddSeasonalPriceToPrizeItems");

		String sStartDateForSeasonalPrice = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();

		System.out.println("Start date for seasonal pricing is " + sStartDateForSeasonalPrice);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sSeasonalPrizeForPrizeItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_022PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_022PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_022PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sSeasonalPrizeForPrizeItem2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_033PrizeItemAddSeasoning_Link);
			Thread.sleep(3000);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_033PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_033PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sSeasonalPrizeForPrizeItem3);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_024PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_024PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_024PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sSeasonalPrizeForPrizeItem4_1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate2_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate2_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate2_TextBox).sendKeys(sSeasonalPrizeForPrizeItem4_2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate3_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate3_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate3_TextBox).sendKeys(sSeasonalPrizeForPrizeItem4_3);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User has returned to Prize and commitments screen");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Addition of Seasonal Price of Prize Items Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchFiltersForPerticularPrizeItemAndSendForApproval
	,Description          : To search prize item and send for approval
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchFiltersForPerticularPrizeItemAndSendForApproval(String sSearchKeywordFromTest) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchFiltersForPerticularPrizeItemAndSendForApproval");

		System.out.println("Value for search filter is " + sSearchKeywordFromTest);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Title_Label);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Filters_Label);


			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox).isDisplayed()) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			}

			if (!sSearchKeywordFromTest.equalsIgnoreCase("NoValue")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				Thread.sleep(3000);
				CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				Thread.sleep(3000);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeywordFromTest);
			}

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(3000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_SelectionOfPrizeItemAfterFilter_CheckBox);
			CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Pricing_And_Commitments_SelectionOfPrizeItemAfterFilter_CheckBox);
			Thread.sleep(3000);
			String sParentWindow = driver.getWindowHandle();
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SendPriceItemsForApproval_Button);
			Thread.sleep(8000);
			Set < String > handles = driver.getWindowHandles();
			for (String windowHandle: handles) {
				if (!windowHandle.equals(sParentWindow)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(3000);
					CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_MessageAferSentPrizeForApproval_Label);
					assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_MessageAferSentPrizeForApproval_Label).isDisplayed(), "User is navigated to Deal Information page");
					Thread.sleep(3000);
					driver.close(); //closing child window
					driver.switchTo().window(sParentWindow); //cntrl to parent window
				}
			}
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Search of Prize Item and Send It for Approval Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerificationOfDealOnDealToDoZone
	,Description          : To verify deal displayed on deal approval dashboard
	'###############################################################################################################################################################################################################################################*/
	public void FnVerificationOfDealOnDealToDoZone() throws Exception {
		System.out.println(">>>>>>>>>>--FnVerificationOfDealOnDealToDoZone");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Dashboard_FirstDealInformation_Link).isDisplayed(), "User is navigated Deal Approval Dashboard and Deal is displayed on deal zone");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Is Displayed on Deal Approval Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard
	,Description          : To navigate to deal Approval Workflow page from deal approval dashboard
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_FirstViewApprovalWorkflow_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Approval_Workflow_Title_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_Title_Label).isDisplayed(), "User is navigated Deal Approval Workflow");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Approval Workflow Page from Deal Approval Dashboard Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchDealId
	,Description          : To search deal ID from deal approval dashboard
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchDealId(String sDealId) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchDealId");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			try {
				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Dashboard_ExpandAll_Button).isDisplayed()) {
					Thread.sleep(2000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_ExpandAll_Button);
				}
			} catch (Exception e) {
				System.out.println("Search window is already expanded");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox, sDealId);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_SearchDealLower_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Search for Deal ID from Deal Approval Dashboard Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchDealIdFromDealDashboard
	,Description          : To search deal ID from deal  dashboard
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchDealIdFromDealDashboard(String sDealId) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchDealIdFromDealDashboard");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			try {
				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Dashboard_ExpandAll_Button).isDisplayed()) {
					Thread.sleep(2000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Dashboard_ExpandAll_Button);
				}
			} catch (Exception e) {
				System.out.println("Search window is already expanded");
			}
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox, sDealId);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Dashboard_Search_Deal_Lower_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Search for Deal ID from Deal Dashboard Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowForPrizeItemManager
	,Description          : To View and verify Approval Workflow of deal
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowForPrizeItemManager(String sStatus, String sRole, String sDealActionTakenStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowForPrizeItemManager");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_DealStatus_Label).getText(), sStatus, "Deal status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_SecondToDoRoleValue_Label).getText(), sRole, "To do role is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_SecondActionTakenValue_Label).getText(), sDealActionTakenStatus, "Action taken status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			String sColorValueOfRMImage = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_RM1Image_Label).getCssValue("color");
			Thread.sleep(3000);
			System.out.println("Color of RM image is " + sColorValueOfRMImage);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Workflow Verification of Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow
	,Description          : To navigate to deal approval dashboard from deal Approval Workflow page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Workflow_Close_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Approval Dashboard from Deal Approval Workflow Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealInformationFromDealApprovalDashboard
	,Description          : To navigate to deal information from deal approval dashboard page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealInformationFromDealApprovalDashboard() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealInformationFromDealApprovalDashboard");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_FirstDealForNavigationToDealInformation_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Information from Deal Approval Dashboard Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchFiltersForPerticularPrizeItemAndCheckLimits
	,Description          : To search prize item and check limits
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchFiltersForPerticularPrizeItemAndCheckLimits(String sSearchKeywordFromTest) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchFiltersForPerticularPrizeItemAndCheckLimits");

		System.out.println("Value for search filter is " + sSearchKeywordFromTest);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Filters_Label);

			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox).isDisplayed()) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			}

			if (!sSearchKeywordFromTest.equalsIgnoreCase("NoValue")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				Thread.sleep(3000);
				CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
				Thread.sleep(3000);
				CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeywordFromTest);
			}

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(3000);

			try {
				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SelectionOfPrizeItemAfterFilter_CheckBox).isDisplayed()) {
					CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Pricing_And_Commitments_SelectionOfPrizeItemAfterFilter_CheckBox);
					Thread.sleep(3000);
					String sParentWindow = driver.getWindowHandle();
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CheckLimits_Button);
					Thread.sleep(8000);
					Set < String > handles = driver.getWindowHandles();
					for (String windowHandle: handles) {
						if (!windowHandle.equals(sParentWindow)) {
							driver.switchTo().window(windowHandle);
							Thread.sleep(3000);
							CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_MessageAferCheckLimits_Label);
							assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_MessageAferCheckLimits_Label).isDisplayed(), "Check limits succesfully");
							Thread.sleep(5000);
							driver.close(); //closing child window
							driver.switchTo().window(sParentWindow); //cntrl to parent window
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Check box was not present");
			}

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_ApproverList_Label));
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ApproverList_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_ApproverList_Label).isDisplayed(), "Approval status verified succesfully");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Search Prize Item and Check Limit Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnReturnForSubmitterAndUpdateData
	,Description          : Function for return for submitter and update data from deal information
	'###############################################################################################################################################################################################################################################*/
	public void FnReturnForSubmitterAndUpdateData(String sDealReturnReason, String sCommentsForDeal) throws Exception {
		System.out.println(">>>>>>>>>>--FnReturnForSubmitterAndUpdateData");

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ReturnToSubmitter_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Approval_Return_To_Submitter_Title_Label);
			CF.FnSelectValue(driver, DealManagementPageElements.Deal_Approval_Return_To_Submitter_DealReturnReason_DropDown, sDealReturnReason);
			Thread.sleep(2000);
			CF.FnSelectValue(driver, DealManagementPageElements.Deal_Approval_Return_To_Submitter_DealReturnSendDealBackWithApproval_DropDown, "Yes");
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Deal_Approval_Return_To_Submitter_DealComments_Textbox, sCommentsForDeal);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Return_To_Submitter_Save_Button);
			Thread.sleep(8000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);

			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_OK_Button);

			Thread.sleep(2000);
			CF.FnTestCaseStatusReport("Pass", "Return for Submitter and Update of Data from Deal information Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealInformationFromDealApprovalDashboardToSendDealForApproval
	,Description          : To navigate to deal information from deal approval dashboard page
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealInformationFromDealApprovalDashboardToSendDealForApproval() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealInformationFromDealApprovalDashboardToSendDealForApproval");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_FirstDealInformationPriceItemsAreReturnedFromPriceItemManager1_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Information from Deal Approval Dashboard Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSendDealForApprovalFromDealInformation
	,Description          : To Send deal for approval
	'###############################################################################################################################################################################################################################################*/
	public void FnSendDealForApprovalFromDealInformation() throws Exception {
		System.out.println(">>>>>>>>>>--FnSendDealForApprovalFromDealInformation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			String sParentWindow = driver.getWindowHandle();
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SendDealForApproval_Button);
			Thread.sleep(8000);
			Set < String > handles = driver.getWindowHandles();
			for (String windowHandle: handles) {
				if (!windowHandle.equals(sParentWindow)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(3000);
					driver.manage().window().maximize();
					Thread.sleep(3000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_OKAferSendDealForApproval_Button);
					Thread.sleep(8000);

					driver.switchTo().window(sParentWindow); //cntrl to parent window
				}
			}
			Thread.sleep(3000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_OK_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Send Deal for Approval Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowForPM
	,Description          : To View and verify Approval Workflow of deal
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowForPM(String sStatus, String sRole, String sDealActionTakenStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowForPM");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_DealStatus_Label).getText(), sStatus, "Deal status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_ThirdToDoRoleValue_Label).getText(), sRole, "To do role is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_ThirdActionTakenValue_Label).getText(), sDealActionTakenStatus, "Action taken status is verified on deal Approval Workflow ");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Workflow Verification of Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled
	,Description          : To Verify Send deal for Approval and Return to Submitter buttons are enabled
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled() throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled");

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_ReturnToSubmitter_Button);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_ReturnToSubmitter_Button).isEnabled(), "Return to Submitter button is enebaled");
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_SendDealForApproval_Button);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_SendDealForApproval_Button).isEnabled(), "Send deal for Approval button is enebaled");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Send Deal for Approval and Return to Submitter Buttons are Enabled Verification Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyApprovalStatusOnViewAndEditPricing
	,Description          : Verify Approval status for PM on View And Edit Pricing
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyApprovalStatusOnViewAndEditPricing(String sApproverToBeVerified) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyApprovalStatusOnViewAndEditPricing");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ApproverList_Label);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Status Verification on View And Edit Pricing Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowForSPM
	,Description          : To View and verify Approval Workflow of deal
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowForSPM(String sStatus, String sRole, String sDealActionTakenStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowForSPM");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_DealStatus_Label).getText(), sStatus, "Deal status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_FourthToDoRoleValue_Label).getText(), sRole, "To do role is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_FourthActionTakenValue_Label).getText(), sDealActionTakenStatus, "Action taken status is verified on deal Approval Workflow ");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Workflow Verification of Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndRecommendPriceForPrizeItems
	,Description          : To Search prize items and recommend prize
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndRecommendPriceForPrizeItems(String sPrizeItem1, String sValueOfsPrizeItem1, String sPrizeItem2, String sValueOfsPrizeItem2) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchAndRecommendPriceForPrizeItems");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem1);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_RecommendAfterPrizeItemSearched_link);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Recommended_Pricing_PageTitle_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sValueOfsPrizeItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Recommended_Pricing_Recommend_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem2);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_RecommendAfterPrizeItemSearched_link);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Recommended_Pricing_PageTitle_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sValueOfsPrizeItem2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Recommended_Pricing_Recommend_Button);
			Thread.sleep(10000);
			CF.FnTestCaseStatusReport("Pass", "Search Prize Items and Recommend Prize Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchPrizeItemAndVerifyRecommendedPrize
	,Description          : To Search prize items and verify recommended prize
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchPrizeItemAndVerifyRecommendedPrize(String sPrizeItem1, String sValueOfsPrizeItem1, String sPrizeItem2, String sValueOfsPrizeItem2) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchPrizeItemAndVerifyRecommendedPrize");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem1);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_RecommendPrizeOfPrizeItemSearched_TextBox);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_RecommendPrizeOfPrizeItemSearched_TextBox).getAttribute("value").trim(), sValueOfsPrizeItem1, "Recommended value of prize item is verified");
			Thread.sleep(5000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPrizeItem2);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_RecommendPrizeOfPrizeItemSearched_TextBox);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_RecommendPrizeOfPrizeItemSearched_TextBox).getAttribute("value").trim(), sValueOfsPrizeItem2, "Recommended value of prize item is verified");
			Thread.sleep(10000);
			CF.FnTestCaseStatusReport("Pass", "Search Prize Items And Recommended Prize Verification Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnApproveDealFromDealInformation
	,Description          : To approve deal
	'###############################################################################################################################################################################################################################################*/
	public void FnApproveDealFromDealInformation() throws Exception {
		System.out.println(">>>>>>>>>>--FnApproveDealFromDealInformation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ApproveDeal_Button);
			Thread.sleep(8000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_OK_Button);
			Thread.sleep(2000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page");
			Thread.sleep(5000);

			CF.FnTestCaseStatusReport("Pass", "Approve Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnFinalizeDealFromDealInformation
	,Description          : To Finalize Deal
	'###############################################################################################################################################################################################################################################*/
	public void FnFinalizeDealFromDealInformation() throws Exception {
		System.out.println(">>>>>>>>>>--FnFinalizeDealFromDealInformation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			String sParentWindow = driver.getWindowHandle();
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_FinalizeDeal_Button);
			Thread.sleep(8000);
			Set < String > handles = driver.getWindowHandles();
			for (String windowHandle: handles) {
				if (!windowHandle.equals(sParentWindow)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(3000);
					driver.manage().window().maximize();
					Thread.sleep(3000);

					Thread.sleep(2000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_OKAferDealFinalized_Button);
					Thread.sleep(8000);
					Set < String > handlesAfterConfirmation = driver.getWindowHandles();
					for (String windowHandleAfterConfirmation: handlesAfterConfirmation) {
						if (!windowHandleAfterConfirmation.equals(sParentWindow)) {
							driver.switchTo().window(windowHandleAfterConfirmation);
							Thread.sleep(3000);
							driver.manage().window().maximize();
							Thread.sleep(3000);
							CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_OKAferDealFinalized_Button);
							Thread.sleep(3000);
						}
					}
					driver.switchTo().window(sParentWindow); //cntrl to parent window
				}
			}
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Finalize Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAcceptDealFromDealInformation
	,Description          : To Accept Deal
	'###############################################################################################################################################################################################################################################*/
	public void FnAcceptDealFromDealInformation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnAcceptDealFromDealInformation");

		String sEffectiveStartDate, sCommentsForAccept;

		sEffectiveStartDate = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sCommentsForAccept = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_AcceptDeal_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Acceptance_PageTitle_Label);

			if (!sEffectiveStartDate.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Acceptance_EffectiveStartDate_TextBox, sEffectiveStartDate);
			}

			if (!sCommentsForAccept.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Acceptance_DealComments_TextBox, sCommentsForAccept);
			}

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Acceptance_Save_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Accept Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnApplyBackDealFromDealInformation
	,Description          : To Apply back Deal on deal information page
	'###############################################################################################################################################################################################################################################*/
	public void FnApplyBackDealFromDealInformation() throws Exception {
		System.out.println(">>>>>>>>>>--FnApplyBackDealFromDealInformation");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_2");
			String sParentWindow = driver.getWindowHandle();
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ApplyBack_Button);
			Thread.sleep(8000);
			Set < String > handles = driver.getWindowHandles();
			for (String windowHandle: handles) {
				if (!windowHandle.equals(sParentWindow)) {
					Thread.sleep(5000);
					driver.switchTo().window(windowHandle);
					Thread.sleep(3000);
					driver.manage().window().maximize();
					Thread.sleep(3000);
					Thread.sleep(2000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_OKAferDealFinalized_Button);
					Thread.sleep(8000);
					Set < String > handlesAfterConfirmation = driver.getWindowHandles();
					for (String windowHandleAfterConfirmation: handlesAfterConfirmation) {
						if (!windowHandleAfterConfirmation.equals(sParentWindow)) {
							driver.switchTo().window(windowHandleAfterConfirmation);
							Thread.sleep(3000);
							driver.manage().window().maximize();
							Thread.sleep(3000);
							CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_OKAferDealFinalized_Button);
							Thread.sleep(3000);
						}
					}
					driver.switchTo().window(sParentWindow); //cntrl to parent window
				}
			}

			Thread.sleep(3000);
			CF.FnSetFrame(driver, "uiMap");
			/*			Thread.sleep(2000);					
						CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_SimulateDeal_OK_Button);	*/
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Apply Back Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealFinancialSummaryAfterRecommendationOnDealInformation
	,Description          : To verify Deal Financial Summary After Recommendation Information On Deal Information page through UI
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealFinancialSummaryAfterRecommendationOnDealInformation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyDealFinancialSummaryAfterRecommendationOnDealInformation");

		String sApprovalStatus, sProjectedRevenue, sProjectedCost, sProjectedProfit, sProjectedProfitPercentage, sRecommendedRevenue, sRecommendedProfit, sRecommendedProfitPercentage, sOriginalRevenue, sOriginalCost, sOriginalProfit, sOriginalProfitPercentage, sProfitVaration;

		sApprovalStatus = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProjectedProfit = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedProfitPercentage = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sRecommendedRevenue = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sRecommendedProfit = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sRecommendedProfitPercentage = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sOriginalProfit = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sOriginalProfitPercentage = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		sProfitVaration = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();

		System.out.println("Deal Financial Summary Information is " + sApprovalStatus + sProjectedRevenue + sProjectedCost + sProjectedProfit + sProjectedProfitPercentage + sRecommendedRevenue + sRecommendedProfit + sRecommendedProfitPercentage + sOriginalRevenue + sOriginalCost + sOriginalProfit + sOriginalProfitPercentage + sProfitVaration);

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_2");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealFinancialSummary_Label);

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedRevenueValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("PersonName value from application is " + sProjectedRevenueFromApplication);
				assertEquals(sProjectedRevenue, sProjectedRevenueFromApplication);
			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedCostValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Division value from application is " + sProjectedCostFromApplication);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}


			if (!sProjectedProfitPercentage.equalsIgnoreCase("NoValue")) {
				String sProjectedProfitPercentageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryProjectedProfitPercentValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Revenue from application is " + sProjectedProfitPercentageFromApplication);
				assertEquals(sProjectedProfitPercentage, sProjectedProfitPercentageFromApplication);
			}

			if (!sRecommendedRevenue.equalsIgnoreCase("NoValue")) {
				String sRecommendedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryRecommendedRevenueValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Recommended revenue value from application is " + sRecommendedRevenueFromApplication);
				assertEquals(sRecommendedRevenue, sRecommendedRevenueFromApplication);
			}

			if (!sRecommendedProfit.equalsIgnoreCase("NoValue")) {
				String sRecommendedProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryRecommendedProfitValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Recommended profit from application is " + sRecommendedProfitFromApplication);
				assertEquals(sRecommendedProfit, sRecommendedProfitFromApplication);
			}

			if (!sRecommendedProfitPercentage.equalsIgnoreCase("NoValue")) {
				String sRecommendedProfitPercentageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryRecommendedProfitPercentValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Recommended profit percentage from application is " + sRecommendedProfitPercentageFromApplication);
				assertEquals(sRecommendedProfitPercentage, sRecommendedProfitPercentageFromApplication);
			}

			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalRevenueValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalRevenueFromApplication);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalCostValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sOriginalCostFromApplication);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}


			if (!sOriginalProfitPercentage.equalsIgnoreCase("NoValue")) {
				String sOriginalProfitPercentageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalProfitPercentValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalProfitPercentageFromApplication);
				assertEquals(sOriginalProfitPercentage, sOriginalProfitPercentageFromApplication);
			}

			if (!sProfitVaration.equalsIgnoreCase("NoValue")) {
				String sProfitVarationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealFinancialSummaryOriginalProfitVariationValue_Label).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sProfitVarationFromApplication);
				assertEquals(sProfitVaration, sProfitVarationFromApplication);
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Financial Summary Verification After Price Recommendation On Deal Information page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowForRM
	,Description          : To View and verify Approval Workflow of deal
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowForRM(String sStatus, String sRole, String sDealActionTakenStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowForRM");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_DealStatus_Label).getText(), sStatus, "Deal status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_FifthToDoRoleValue_Label).getText(), sRole, "To do role is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_FifthActionTakenValue_Label).getText(), sDealActionTakenStatus, "Action taken status is verified on deal Approval Workflow ");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Workflow Verification of Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyApporvalStatusOfPrizeItems
	,Description          : To verify approval status of prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyApporvalStatusOfPrizeItems(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyApporvalStatusOfPrizeItems");

		String sPrizeItemValue, sApprovalStatusValue;
		String[] arrPrizeItems = null;
		String[] arrApprovalStatus = null;

		sPrizeItemValue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sApprovalStatusValue = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

		if (sPrizeItemValue.contains(","))
			arrPrizeItems = sPrizeItemValue.split(",");
		else if (sPrizeItemValue.contains("#"))
			arrPrizeItems = sPrizeItemValue.split("#");

		if (sApprovalStatusValue.contains(","))
			arrApprovalStatus = sApprovalStatusValue.split(",");
		else if (sApprovalStatusValue.contains("#"))
			arrApprovalStatus = sApprovalStatusValue.split("#");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);

			// To Enter Prize item values and verify status
			if (!sPrizeItemValue.equalsIgnoreCase("NoValue") && !sApprovalStatusValue.equalsIgnoreCase("NoValue")) {
				for (int icont = 0; icont < arrPrizeItems.length; icont++) {
					if (!arrPrizeItems[icont].trim().equalsIgnoreCase("NoValue")) {
						CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
						Thread.sleep(2000);
						CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
						Thread.sleep(2000);
						CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, arrPrizeItems[icont]);
						Thread.sleep(5000);
						

						List < WebElement > ApprovalStatusesFromApplication = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_ApprovalStatus_Image);
						Thread.sleep(3000);
						for (WebElement approvalStatus: ApprovalStatusesFromApplication) {
							String sApprovalStatusFromApplication = approvalStatus.getAttribute("title");
							Thread.sleep(2000);
							assertEquals(arrApprovalStatus[icont], sApprovalStatusFromApplication, "Approval Status is correct for prize item");
						}

					}
				}
			}


			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Approval Status Verification of Prize Items Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyServiceQuantityOfPrizeItemGroups
	,Description          : Verify Service quantity of prize item group 
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyServiceQuantityOfPrizeItemGroups(String sSeriveQtyOfGroup1, String sSeriveQtyOfGroup2, String sSeriveQtyOfGroup3) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyServiceQuantityOfPrizeItemGroups");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnSetFrame(driver, "uiMap");

			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CommitmentsAndVolumeForCorporateBanking_Image);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox);
			Thread.sleep(5000);
			System.out.println("Service qty value for Corporate banking is " + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""));
			assertEquals(sSeriveQtyOfGroup1, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""), "Service quantity amount is matched");
			Thread.sleep(3000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBackServiceQtyScreen_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnSetFrame(driver, "uiMap");

			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CommitmentsAndVolumeForAccountServices_Image);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox);
			Thread.sleep(5000);
			assertEquals(sSeriveQtyOfGroup2, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""), "Service quantity amount is matched");
			Thread.sleep(3000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBackServiceQtyScreen_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_AccountServicesCollapse_Button);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ReportingAndSwiftCollapse_Button);
			CF.FnSetFrame(driver, "uiMap");

			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CommitmentsAndVolumeForReportingAndSwift_Image);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox);
			Thread.sleep(5000);
			assertEquals(sSeriveQtyOfGroup3, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_DailyServiceQuantity_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""), "Service quantity amount is matched");
			Thread.sleep(3000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_LowerBackServiceQtyScreen_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);

			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Service Quantity of Prize Item Group Verification Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts
	,Description          : Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyPersonalHierarchyInformationOnDealInformation");

		String sAccountName, sDivision, sStatus, sRevenueVaration, sProjectedRevenue, sProjectedCost, sOriginalRevenue, sOriginalCost;

		sAccountName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sRevenueVaration = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

		System.out.println("Account Hierarchy Information is " + sAccountName + sDivision + sStatus + sRevenueVaration + sProjectedRevenue + sProjectedCost + sOriginalRevenue + sOriginalCost);

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(3000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(3000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_PersonalHierarchyAccountNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName));

			if (!sAccountName.equalsIgnoreCase("NoValue")) {
				String sAccountNameFromApplicationByText = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyAccountNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim();
				System.out.println("ssAccountNameFromApplication by text for account is " + sAccountNameFromApplicationByText);
				Thread.sleep(3000);
				assertTrue(sAccountNameFromApplicationByText.contains(sAccountName));
			}

			if (!sDivision.equalsIgnoreCase("NoValue")) {
				String sDivisionFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim();
				Thread.sleep(3000);
				System.out.println("Division value from application is " + sDivisionFromApplication);
				assertEquals(sDivision, sDivisionFromApplication);
			}

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedRevenueValueForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Revenue from application is " + sProjectedRevenueFromApplication);
				assertEquals(sProjectedRevenue, sProjectedRevenueFromApplication);
			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedCostValueForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Projected Cost from application is " + sProjectedCostFromApplication);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}

			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalRevenueValueForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Revenue from application is " + sOriginalRevenueFromApplication);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalCostValueForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim().replaceAll(",", "");
				Thread.sleep(3000);
				System.out.println("Original Cost from application is " + sOriginalCostFromApplication);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Personal Hierarchy Information Verification On Deal Information for Accounts Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdateRatesOfPrizeItemsThroughSeasonalLinkForAccounts
	,Description          : To add seasonal price to prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdateRatesOfPrizeItemsThroughSeasonalLinkForAccounts(String sRateOfPriceItem1, String sRateValueOfPriceItem1, String sRateOfPriceItem2, String sRateValueOfPriceItem2, String sRateOfPriceItem3, String sRateValueOfPriceItem3, String sRateOfPriceItem4, String sRateValueOfPriceItem4) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdateRatesOfPrizeItemsThroughSeasonalLinkForAccounts");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem3);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem4);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			System.out.println("Rate value is " + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""));
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", "").trim(), sRateValueOfPriceItem1, "Changed rate of PI_022 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			System.out.println("Rate value is " + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""));
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", "").trim(), sRateValueOfPriceItem2, "Changed rate of PI_033 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem3);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			System.out.println("Rate value is " + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""));
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem3, "Changed rate of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem4);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox);
			System.out.println("Rate value is " + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").replaceAll("(?<=\\d+)\\.\\d+$", ""));
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemRateValueAfterSearch_TextBox).getAttribute("value").trim(), sRateValueOfPriceItem4, "Changed rate of PI_028 is reflected after search");
			Thread.sleep(2000);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Update of Rates Of Prize Items Through Seasonal Link for Accounts Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemsVolumesForAccounts
	,Description          : To edit volumes of prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdatePrizeItemsVolumesForAccounts(String sVolumeOfPrizeItem1, String sVolumeValueOfPrizeItem1, String sVolumeOfPrizeItem2, String sVolumeValueOfPrizeItem2, String sVolumeOfPrizeItem3, String sVolumeValueOfPrizeItem3) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdatePrizeItemsVolumesForAccounts");

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);

			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem1);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).sendKeys(sVolumeValueOfPrizeItem1);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem2);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).sendKeys(sVolumeValueOfPrizeItem2);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem3);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearchForSecondPrizeItem_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearchForSecondPrizeItem_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearchForSecondPrizeItem_TextBox).sendKeys(sVolumeValueOfPrizeItem3);

			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem1);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem1, "Changed Volume of PI_022 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem2);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearch_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem2, "Changed Volume of PI_027 is reflected after search");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sVolumeOfPrizeItem3);
			Thread.sleep(5000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PrizeItemVolumeValueAfterSearchForSecondPrizeItem_TextBox).getAttribute("value").trim(), sVolumeValueOfPrizeItem3, "Changed Volume of PI_028 is reflected after search");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(5000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_CollapseAll_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Update of Volumes Of Prize Items for Accounts Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddSeasonalPriceToPrizeItemsForAccounts
	,Description          : To add seasonal price to prize items
	'###############################################################################################################################################################################################################################################*/
	public void FnAddSeasonalPriceToPrizeItemsForAccounts(int iStartingRow, String sSheetName, String sWorkbook, String sRateOfPriceItem1, String sRateValueOfPriceItem1, String sRateOfPriceItem2, String sRateValueOfPriceItem2) throws Exception {
		System.out.println(">>>>>>>>>>--FnAddSeasonalPriceToPrizeItemsForAccounts");

		String sStartDateForSeasonalPrice = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

		System.out.println("Start date for seasonal pricing is " + sStartDateForSeasonalPrice);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");


			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem1);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(8000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(8000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sRateOfPriceItem2);
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link));
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_PI_021PrizeItemAddSeasoning_Link);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, sStartDateForSeasonalPrice);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sRateValueOfPriceItem2);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText).isDisplayed(), "User has returned to Prize and commitments screen");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Addition of Seasonal Price to Prize Items for Accounts Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealInformationVerificationAfterFullyOrchestrated
	,Description          : Deal Information Verification through UI after deal Fully Orchestrated
	'###############################################################################################################################################################################################################################################*/
	public void FnDealInformationVerificationAfterFullyOrchestrated(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnDealInformationVerificationAfterFullyOrchestrated");

		String sDealInformation, sDealStatus, sDealVersionStatus, sAcceptanceDate, sFinalizedDate, sCreationDate, sStartDate, sEffectiveStartDate, sReviewDate;

		sDealInformation = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDealStatus = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sDealVersionStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sEffectiveStartDate = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sAcceptanceDate = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sFinalizedDate = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sCreationDate = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sStartDate = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sReviewDate = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();

		System.out.println("Deal information is " + sDealInformation + sDealStatus + sDealVersionStatus + sAcceptanceDate + sFinalizedDate + sCreationDate + sStartDate + sEffectiveStartDate + sReviewDate);

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails);

			if (!sDealInformation.equalsIgnoreCase("NoValue")) {
				String sDealInformationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails).getText().trim();
				System.out.println("Deal information is " + sDealInformationFromApplication);
				assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails).isDisplayed(), "Deal Information is displayed");
			}

			if (!sDealStatus.equalsIgnoreCase("NoValue")) {
				String sDealStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealStatus_TextDetails).getText().trim();
				System.out.println("Deal status is " + sDealStatusFromApplication);
				assertEquals(sDealStatus, sDealStatusFromApplication);
			}

			if (!sDealVersionStatus.equalsIgnoreCase("NoValue")) {
				String sDealVersionStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealVersionStatus_TextDetails).getText().trim();
				System.out.println("Deal Version status is " + sDealVersionStatusFromApplication);
				assertEquals(sDealVersionStatus, sDealVersionStatusFromApplication);
			}

			if (!sEffectiveStartDate.equalsIgnoreCase("NoValue")) {
				String sEffectiveStartDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_EffectiveStartDate_TextDetails).getText().trim();
				System.out.println("Effective Start Date is " + sEffectiveStartDateFromApplication);
				assertEquals(sEffectiveStartDate, sEffectiveStartDateFromApplication);
			}

			if (!sAcceptanceDate.equalsIgnoreCase("NoValue")) {
				String sAcceptanceDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_AcceptanceDate_TextDetails).getText().trim();
				System.out.println("Acceptance Date is " + sAcceptanceDateFromApplication);
				assertEquals(sAcceptanceDate, sAcceptanceDateFromApplication);
			}

			if (!sFinalizedDate.equalsIgnoreCase("NoValue")) {
				String sFinalizedDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_FinalizedDate_TextDetails).getText().trim();
				System.out.println("Finalized Date is " + sFinalizedDateFromApplication);
				assertEquals(sFinalizedDate, sFinalizedDateFromApplication);
			}

			if (!sStartDate.equalsIgnoreCase("NoValue")) {
				String sDealStartDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_StartDate_TextDetails).getText().trim();
				System.out.println("Deal SimulationType is " + sDealStartDateFromApplication);
				assertEquals(sStartDate, sDealStartDateFromApplication);
			}

			if (!sCreationDate.equalsIgnoreCase("NoValue")) {
				String sDealCreationDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_CreationDate_TextDetails).getText().trim();
				System.out.println("Deal CreationDate is " + sDealCreationDateFromApplication);
				assertEquals(sCreationDate, sDealCreationDateFromApplication);
			}

			if (!sReviewDate.equalsIgnoreCase("NoValue")) {
				String sReviewDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_ReviewDate_TextDetails).getText().trim();
				System.out.println("Deal AccessGroup is " + sReviewDateFromApplication);
				assertEquals(sReviewDate, sReviewDateFromApplication);
			}

			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Information Verification After Deal Is Fully Orchestrated Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSaveOnPriceListAssignment
	,Description          : To save the assigned prizelist for given prize list ID
	'###############################################################################################################################################################################################################################################*/
	public void FnSaveOnPriceListAssignment() throws Exception {
		System.out.println(">>>>>>>>>>--FnPriceListAssignment");

		try {
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Assign_Prizelist_Save_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Assign_Prizelist_Cancel_Button);
			Thread.sleep(3000);
			CF.FnTestCaseStatusReport("Pass", "Save for assigned prizelist for given prize list ID Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse
	,Description          : To verify Presence Of Prize Item In Price Group Selection Response
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse(int iStartingRow, String sSheetName, String sWorkbook, List < String > sListOfPriceItems) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse");

		String sPrizeItemValue;
		String[] arrPrizeItems = null;

		sPrizeItemValue = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();

		if (sPrizeItemValue.contains(","))
			arrPrizeItems = sPrizeItemValue.split(",");
		else if (sPrizeItemValue.contains("#"))
			arrPrizeItems = sPrizeItemValue.split("#");

		try {
			// To Enter Prize item values and verify status
			if (!sPrizeItemValue.equalsIgnoreCase("NoValue")) {
				int iSizeOfArray = arrPrizeItems.length;
				System.out.println("Size of Price Item Array Is " + iSizeOfArray);
				for (int icont = 0; icont < iSizeOfArray; icont++) {
					if (!arrPrizeItems[icont].trim().equalsIgnoreCase("NoValue")) {
						if (sListOfPriceItems.contains(arrPrizeItems[icont])) {
							CF.FnTestCaseStatusReport("Pass", "Price Item " + arrPrizeItems[icont] + " Is Present in Read Price Item Group Selection Response");
						} else {
							CF.FnTestCaseStatusReport("Fail", "Price Item " + arrPrizeItems[icont] + " Is NOT Present in Read Price Item Group Selection Response");
						}

					}
				}
			}

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################### WEBSERVICES COMMON FUNCTIONS #############################################################################################################################################################################################	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreateDeal
	,Description          : To create deal using API
	'###############################################################################################################################################################################################################################################*/

	public Hashtable < String, String > FnCreateDeal(String sDealCreationData, String sCreateDealResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnCreateDeal--*" + sDealCreationData);

		////sReturnField will be "sDealId" , "sModelId" , "sErrorMessage"
		Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

		String sValue = null, sDealId = null, sModelId = null, sDealIdentifier = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sCreateDealResource, sDealCreationData, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			System.out.println("Response :-" + WF.respBody);
			int iStatusCode = WF.FnGetStatusCodeFromResponse();

			if (iStatusCode == iSuccessStatusCode) {

				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealResponse.message");
				//System.out.println("Deal  Created ! Deal response message is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal  Created ! Deal response message is :" + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.dealInformation");
				//System.out.println("Deal  Created ! Deal Information is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal  Created !  Deal Information is : " + sValue);

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.dealId"); //modelId // dealId
				//System.out.println("Deal  Created ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Deal ID is : " + sDealId);

				sDealIdentifier = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.dealIdentifier"); //modelId // dealId
				//System.out.println("Deal  Created ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! DealIdentifier is : " + sDealIdentifier);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.modelId");
				//System.out.println("Deal  Created ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Model ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.startDate");
				//System.out.println("Deal  Created ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.dealEntityId");
				System.out.println("Deal  Created ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.priceSelectionDate");
				System.out.println("Deal  Created ! Price Selection Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Price Selection Date is : " + sValue);

				sModelId = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.modelId"); //modelId // dealId
				System.out.println("Deal  Created ! Model ID is : " + sModelId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Model ID is : " + sModelId);


			} else if (iStatusCode == iErrorStatusCode) {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at last so we can return text
				System.out.println("Deal Not Created ! Reason Is : " + sValue);

				CF.FnTestCaseStatusReport("Pass", "Deal Not Created ! Reason Is : " + sValue);


			} else {

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Deal Not Created ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Not Created  ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Not Created ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Not Created ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Not Created  ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Not Created  ! Message Number Is : " + sValue);


				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at end so we can return title text
				System.out.println("Deal Not Created ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Not Created ! Reason Is : " + sValue);


			}

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}




		if (sDealId != null) {
			DealDetails.put("sDealId", sDealId);
		} else {
			DealDetails.put("sDealId", "");
		}

		if (sModelId != null) {
			DealDetails.put("sModelId", sModelId);
		} else {
			DealDetails.put("sModelId", "");
		}

		if (sDealIdentifier != null) {
			DealDetails.put("sDealIdentifier", sDealIdentifier);
		} else {
			DealDetails.put("sDealIdentifier", "");
		}

		if (sValue != null) {
			DealDetails.put("sErrorMessageIWS", sValue);
		} else {
			DealDetails.put("sErrorMessageIWS", "");
		}


		System.out.println("DealDetails :-" + DealDetails);
		return DealDetails;

	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddPrizeListAssignmentToDeal
	,Description          : To Add Prize List Assignment To Deal
	'###############################################################################################################################################################################################################################################*/

	public String FnAddPrizeListAssignmentToDeal(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnAddPrizeListAssignmentToDeal");

		String sValue = null;
		String sPriceListAssignmentId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Price List NOT assigned to Deal ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Price List NOT assigned to Deal ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Price List NOT assigned to Deal ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Price List NOT assigned to Deal ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Message Number Is : " + sValue);

				CF.FnTestCaseStatusReport("PASS", "Price List NOT assigned to Deal due to Error " + iStatusCode);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.dealId");
				System.out.println("Prize List Assignment is Added ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added !  Deal ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.modelId");
				System.out.println("Prize List Assignment is Added ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Model ID is : " + sValue);

				sPriceListAssignmentId = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListAssignmentId");
				System.out.println("Prize List Assignment is Added ! Price List Assignment ID is : " + sPriceListAssignmentId);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Price List Assignment ID is : " + sPriceListAssignmentId);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListId");
				System.out.println("Prize List Assignment is Added ! Price List ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Price List ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.entityId");
				System.out.println("Prize List Assignment is Added ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.startDate");
				System.out.println("Prize List Assignment is Added ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.endDate");
				System.out.println("Prize List Assignment is Added ! End Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! End Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priority");
				System.out.println("Prize List Assignment is Added ! Priority is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Priority is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sPriceListAssignmentId;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnReadPrizeListAssignmentToDeal
	,Description          : To Read Prize List Assignment To Deal
	'###############################################################################################################################################################################################################################################*/

	public String FnReadPrizeListAssignmentToDeal(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnReadPrizeListAssignmentToDeal");

		String sValue = null, sPriceListId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Prize List read is NOT completed ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is NOT completed ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Prize List read is NOT completed ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is NOT completed ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Prize List read is NOT completed ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is NOT completed ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Prize List read is NOT completed ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is NOT completed ! Message Number Is : " + sValue);

				CF.FnTestCaseStatusReport("PASS", "Prize List read is NOT completed due to Error " + iStatusCode);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.dealId");
				System.out.println("Prize List read is completed ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is completed !  Deal ID is : " + sValue);


				sPriceListId = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListId");
				System.out.println("Prize List read is completed ! Price List ID is : " + sPriceListId);
				CF.FnTestCaseStatusReport("Pass", "Prize List read is completed ! Price List ID is : " + sPriceListId);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sPriceListId;
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnReadPrizeItemGroupSelection
	,Description          : To Read Prize prize item group selection
	'###############################################################################################################################################################################################################################################*/
	
	public List<String> FnReadPrizeItemGroupSelection(String sAddPrizeList,String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception 
    {      
    	System.out.println("*--FnReadPrizeItemGroupSelection");
    	
    	String sValue = null,sPriceItemInfo=null,sErrorMessage = null;
    	List<String> sPriceItemCode= null;
    	int iErrorStatusCode = 400;
    	int iSuccessStatusCode = 200;
    	int iStatusCode = 0 ;
    	
    	try 
		{
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader,sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account not created due to 400 Error");
				
				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Read Prize Item Group Selection Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Read Prize Item Group Selection Not Completed ! Error Message is :" + sErrorMessage);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Read Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Read Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Read Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Read Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Read Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Read Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Read Prize Item Group Selection Not Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Read Prize Item Group Selection Not Completed ! Message Text is : " + sValue);
				
			}

			else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Prize Item Group Selection Read Is completed ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Read Is completed !  Deal ID is : " + sValue);

				sPriceItemCode = WF.FnGetListOfDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemCode");
				System.out.println("Prize Item Group Selection Read Is completed ! Price Item Code is : " + sPriceItemCode);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Read Is completed ! Price Item Code ID is : " + sPriceItemCode);
				
				sPriceItemInfo = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemInfo");
				System.out.println("Prize Item Group Selection Read Is completed ! Price Informatio is : " + sPriceItemInfo);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Read Is completed ! Price Informatio is : " + sPriceItemInfo);
				
/*				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.hierarchyDetails");
				System.out.println("Prize Item Group Selection Read Is completed ! Hierarchy Details ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Read Is completed ! Hierarchy Details ID is : " + sValue);*/

		}
	}		
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
            e.printStackTrace();
        }
   
    	  return sPriceItemCode;
    	 	
    }	


	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemGroupSelection
	,Description          : To update Prize prize item group selection
	'###############################################################################################################################################################################################################################################*/
	
	public List<String> FnUpdatePrizeItemGroupSelection(String sAddPrizeList,String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception 
    {      
    	System.out.println("*--FnUpdatePrizeItemGroupSelection");
    	
    	String sValue = null,sPriceItemInfo=null;
    	List<String> sPriceItemCode= null,sErrorMessage=null;
    	int iErrorStatusCode = 400;
    	int iSuccessStatusCode = 200;
    	int iStatusCode = 0 ;
    	
    	try 
		{
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader,sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account not created due to 400 Error");
				
				sErrorMessage = WF.FnGetListOfDataFromResponse("problemDetailDocument.title");
				System.out.println("Update Prize Item Group Selection Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Error Message is :" + sErrorMessage);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);

			}

			else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Prize Item Group Selection Update Is completed ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed !  Deal ID is : " + sValue);

				sPriceItemCode = WF.FnGetListOfDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemCode");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Item Code is : " + sPriceItemCode);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Item Code ID is : " + sPriceItemCode);
				
				sPriceItemInfo = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemInfo");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Informatio is : " + sPriceItemInfo);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Informatio is : " + sPriceItemInfo);
				
/*				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.assignmentLevel");
				System.out.println("Prize Item Group Selection Update Is completed ! Assignment Level is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Assignment Level is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceAssignmentId");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Assignment ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Assignment ID is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.hierarchyDetails");
				System.out.println("Prize Item Group Selection Update Is completed ! Hierarchy Details ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Hierarchy Details ID is : " + sValue);*/

		}
	}		
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
            e.printStackTrace();
        }
    	if (iStatusCode == iErrorStatusCode)
    	{
    	  return sErrorMessage;   	
    	}
    	else
    	{
    	 return sPriceItemCode;   	
    	}
    }	
	
	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemGroupSelectionForSinglePriceItem
	,Description          : To update Prize prize item group selection
	'###############################################################################################################################################################################################################################################*/
	
	public String FnUpdatePrizeItemGroupSelectionForSinglePriceItem(String sAddPrizeList,String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception 
    {      
    	System.out.println("*--FnUpdatePrizeItemGroupSelectionForSinglePriceItem");
    	
    	String sValue = null,sPriceItemInfo=null,sPriceItemCode= null,sErrorMessage=null;
    	int iErrorStatusCode = 400;
    	int iSuccessStatusCode = 200;
    	int iStatusCode = 0 ;
    	
    	try 
		{
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader,sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account not created due to 400 Error");
				
				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Update Prize Item Group Selection Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Error Message is :" + sErrorMessage);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);

			}

			else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Prize Item Group Selection Update Is completed ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed !  Deal ID is : " + sValue);

				sPriceItemCode = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemCode");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Item Code is : " + sPriceItemCode);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Item Code ID is : " + sPriceItemCode);
				
				sPriceItemInfo = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceItemInfo");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Informatio is : " + sPriceItemInfo);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Informatio is : " + sPriceItemInfo);
				
/*				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.assignmentLevel");
				System.out.println("Prize Item Group Selection Update Is completed ! Assignment Level is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Assignment Level is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.priceAssignmentId");
				System.out.println("Prize Item Group Selection Update Is completed ! Price Assignment ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Price Assignment ID is : " + sValue);
				
				sValue = WF.FnGetDataFromResponse("C1-DealREST.priceItemGroupSelection.priceItemSelectionList.hierarchyDetails");
				System.out.println("Prize Item Group Selection Update Is completed ! Hierarchy Details ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize Item Group Selection Update Is completed ! Hierarchy Details ID is : " + sValue);*/

		}
	}		
		catch (Exception e) 
		{
			System.out.println("Common Function Exception occured ->"+e.getLocalizedMessage()); 
            e.printStackTrace();
        }
    	if (iStatusCode == iErrorStatusCode)
    	{
    	  return sErrorMessage;   	
    	}
    	else
    	{
    	 return sPriceItemCode;   	
    	}
    }	

	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSimulateDealByRequest
	,Description          : To Simulate Deal By Request
	'###############################################################################################################################################################################################################################################*/

	public String FnSimulateDealByRequest(String sSimulateDeal, String sSimulateDealResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnSimulateDealByRequest");

		String sValue = null, sDealId = null, sErrorMessage = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {

				// To send POST request to server for creating Deal 
				WF.FnPostRequestByString(sSimulateDealResource, sSimulateDeal, sContentTypeHeader, sAcceptTypeHeader);

				Thread.sleep(500);

				iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account not created due to 400 Error");

					sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Simulation By Request Not Completed ! Error Message is : " + sErrorMessage);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Not Completed ! Error Message is :" + sErrorMessage);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
					System.out.println("Deal Simulation By Request Not Completed ! Problem Type is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Not Completed ! Problem Type is : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Simulation By Request Not Completed ! Message Category is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Not Completed ! Message Category is : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Simulation By Request Not Completed ! Message Nbr is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Not Completed ! Message Nbr is : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Simulation By Request Completed ! Message Text is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Not Completed ! Message Text is : " + sValue);

				} else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
					System.out.println("Deal Simulation By Request Is completed ! Deal ID is : " + sDealId);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Is completed !  Deal ID is : " + sDealId);

					sValue = WF.FnGetDataFromResponse("C1-DealREST.dealResponse.message");
					System.out.println("Deal Simulation By Request Is completed ! Message is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Simulation By Request Is completed ! Message is : " + sValue);

				}

			

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode == iErrorStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSendDealForApprovalByRequest
	,Description          : To Send Deal Simulate Deal By Request
	'###############################################################################################################################################################################################################################################*/

	public String FnSendDealForApprovalByRequest(String sSendDealForApproval, String sSendDealForApprovalResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnSendDealForApprovalByRequest");

		String sValue = null, sDealId = null, sErrorMessage = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed due to 400 Error");

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Send Deal For Approval NOT Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Send Deal For Approval NOT Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Send Deal For Approval NOT Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Send Deal For Approval NOT Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Send Deal For Approval NOT Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval NOT Completed ! Message Text is : " + sValue);

			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Send Deal For Approval IS Completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval IS Completed !  Deal ID is : " + sDealId);

				sValue = WF.FnGetDataFromResponse("C1-DealREST.dealResponse.message");
				System.out.println("Send Deal For Approval IS Completed ! Message is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval IS Completed ! Message is : " + sValue);
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode == iErrorStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeItemGroupSelectionForErrorVerification
	,Description          : To return error message
	'###############################################################################################################################################################################################################################################*/

	public String FnUpdatePrizeItemGroupSelectionForErrorVerification(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnUpdatePrizeItemGroupSelectionForErrorVerification");

		String sValue = null, sErrorMessage = null;
		int iErrorStatusCode = 400;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed due to 400 Error");

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Update Prize Item Group Selection Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Not Completed ! Message Text is : " + sValue);

			} else {

				System.out.println("Update Prize Item Group Selection Completed ! Error Not Thrown");
				CF.FnTestCaseStatusReport("Pass", "Update Prize Item Group Selection Completed ! Error Not Thrown");
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return sErrorMessage;
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreateDeal
	,Description          : To create deal using API
	'###############################################################################################################################################################################################################################################*/

	public String FnCreateDealProspect(String sDealProspectCreationData, String sCreateDealProspectResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnCreateDeal");

		String sValue = null, sProspectPersonId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal Prospect
			WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreationData, sContentTypeHeader,
				sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sProspectPersonId = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
				System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sProspectPersonId);

				sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
				System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
				System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sProspectPersonId;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnGetModelIdFromDeal
	,Description          : To create deal using API
	'###############################################################################################################################################################################################################################################*/

	public String FnGetModelIdFromDeal() throws Exception {
		System.out.println("*--FnCreateDeal");

		String sModelId = null;

		try {
			sModelId = WF.FnGetDataFromResponse("C1-DealREST.dealDetails.modelId");
			System.out.println("Deal  Created ! Model ID is : " + sModelId);
			CF.FnTestCaseStatusReport("Pass", "Deal Created ! Model ID is : " + sModelId);

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sModelId;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdatePrizeListAssignmentToDeal
	,Description          : To Update Prize List Assignment To Deal
	'###############################################################################################################################################################################################################################################*/

	public String FnUpdatePrizeListAssignmentToDeal(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnUpdatePrizeListAssignmentToDeal");

		String sValue = null, sPriceListAssignmentId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Price List NOT Updated to Deal ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Updated to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Price List NOT Updated to Deal ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Updated to Deal ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Price List NOT Updated to Deal ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Updated to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Price List NOT Updated to Deal ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Updated to Deal ! Message Number Is : " + sValue);

				CF.FnTestCaseStatusReport("PASS", "Price List NOT Updated to Deal due to Error " + iStatusCode);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.dealId");
				System.out.println("Prize List Assignment is Updated ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated !  Deal ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.modelId");
				System.out.println("Prize List Assignment is Updated ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Model ID is : " + sValue);

				sPriceListAssignmentId = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListAssignmentId");
				System.out.println("Prize List Assignment is Updated ! Price List Assignment ID is : " + sPriceListAssignmentId);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Price List Assignment ID is : " + sPriceListAssignmentId);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListId");
				System.out.println("Prize List Assignment is Updated ! Price List ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Price List ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.entityId");
				System.out.println("Prize List Assignment is Updated ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.startDate");
				System.out.println("Prize List Assignment is Updated ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.endDate");
				System.out.println("Prize List Assignment is Updated ! End Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! End Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priority");
				System.out.println("Prize List Assignment is Updated ! Priority is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Updated ! Priority is : " + sValue);


			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sPriceListAssignmentId;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDeletePrizeListAssignmentToDeal
	,Description          : To Delete Prize List Assignment To Deal
	'###############################################################################################################################################################################################################################################*/

	public String FnDeletePrizeListAssignmentToDeal(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnDeletePrizeListAssignmentToDeal");

		String sValue = null, sPriceListAssignmentId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Price List NOT Deleted to Deal ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Deleted to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Price List NOT Deleted to Deal ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Deleted to Deal ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Price List NOT Deleted to Deal ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Deleted to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Price List NOT Deleted to Deal ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT Deleted to Deal ! Message Number Is : " + sValue);

				CF.FnTestCaseStatusReport("PASS", "Price List NOT Deleted to Deal due to Error " + iStatusCode);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.dealId");
				System.out.println("Prize List Assignment is Deleted ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted !  Deal ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.modelId");
				System.out.println("Prize List Assignment is Deleted ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Model ID is : " + sValue);

				sPriceListAssignmentId = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListAssignmentId");
				System.out.println("Prize List Assignment is Deleted ! Price List Assignment ID is : " + sPriceListAssignmentId);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Price List Assignment ID is : " + sPriceListAssignmentId);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListId");
				System.out.println("Prize List Assignment is Deleted ! Price List ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Price List ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.entityId");
				System.out.println("Prize List Assignment is Deleted ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.startDate");
				System.out.println("Prize List Assignment is Deleted ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.endDate");
				System.out.println("Prize List Assignment is Deleted ! End Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! End Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priority");
				System.out.println("Prize List Assignment is Deleted ! Priority is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Deleted ! Priority is : " + sValue);


			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sPriceListAssignmentId;
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddPrizeListAssignmentToDeal
	,Description          : To Add Prize List Assignment To Deal
	'###############################################################################################################################################################################################################################################*/

	public List < String > FnAddMultiplePrizeListAssignmentToDeal(String sAddPrizeList, String sAddPrizeListAssignmentResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnAddPrizeListAssignmentToDeal");

		String sValue = null;
		List < String > sPriceListAssignmentId = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sAddPrizeListAssignmentResource, sAddPrizeList, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Price List NOT assigned to Deal ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Price List NOT assigned to Deal ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Price List NOT assigned to Deal ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Price List NOT assigned to Deal ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Price List NOT assigned to Deal ! Message Number Is : " + sValue);

				CF.FnTestCaseStatusReport("PASS", "Price List NOT assigned to Deal due to Error " + iStatusCode);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.dealId");
				System.out.println("Prize List Assignment is Added ! Deal ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added !  Deal ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.modelId");
				System.out.println("Prize List Assignment is Added ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Model ID is : " + sValue);

				sPriceListAssignmentId = WF.FnGetListOfDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListAssignmentId");
				System.out.println("Prize List Assignment is Added ! Price List Assignment ID is : " + sPriceListAssignmentId);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Price List Assignment ID is : " + sPriceListAssignmentId);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priceListId");
				System.out.println("Prize List Assignment is Added ! Price List ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Price List ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.entityId");
				System.out.println("Prize List Assignment is Added ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.startDate");
				System.out.println("Prize List Assignment is Added ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.endDate");
				System.out.println("Prize List Assignment is Added ! End Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! End Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealPricelistAssignmentREST.pricelistassignmentlist.priority");
				System.out.println("Prize List Assignment is Added ! Priority is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Prize List Assignment is Added ! Priority is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}

		return sPriceListAssignmentId;
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreateDealProspectForAccount
	,Description          : To Deal Prospect Account using API
	'###############################################################################################################################################################################################################################################*/

	public String FnCreateDealProspectForAccount(String sDealProspectCreationData, String sCreateDealProspectResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnCreateDeal");

		String sValue = null, sProspectAccountId = null, sErrorMessage = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal Prospect
			WF.FnPostRequest(sCreateDealProspectResource, sDealProspectCreationData, sContentTypeHeader,
				sAcceptTypeHeader);
			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account not created due to 400 Error");

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Deal Prospect Account Not Created ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Not Created ! Error Message is :" + sErrorMessage);
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Deal Prospect Account Not Created ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Not Created ! Problem Type is : " + sValue);
				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Deal Prospect Account Not Created ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Not Created ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Prospect Account Not Created ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Not Created ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Prospect Account Not Created ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Not Created ! Message Text is : " + sValue);
			} else {
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");
				sProspectAccountId = WF.FnGetDataFromResponse("C1-ProspectAccountREST.prospectAccountId");
				System.out.println("Deal Prospect Account Created ! Prospect Account Id is : " + sProspectAccountId);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Created ! Prospect Account Id is :" + sProspectAccountId);
				sValue = WF.FnGetDataFromResponse("C1-ProspectAccountREST.mainEntityId");
				System.out.println("Deal Prospect Account Created ! Main Entity Id is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Created ! Main Entity Id is : " + sValue);
				sValue = WF.FnGetDataFromResponse("C1-ProspectAccountREST.prosAcctIdentifierList.accountIdentifierType");
				System.out.println("Deal Prospect Account Created ! Account Identifier Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Created ! Account Identifier Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-ProspectAccountREST.prosAcctIdentifierList.accountNumber");
				System.out.println("Deal Prospect Account Created ! Account Number Type is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Prospect Account Created ! Account Number is : " + sValue);
			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode == iErrorStatusCode) {
			return sErrorMessage;
		} else {
			return sProspectAccountId;
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigateToAccountViewAndAssignProposeProduct
	,Description          : To Navigate to view and Assign Propose Product
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigateToAccountViewAndAssignProposeProduct() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigateToAccountViewAndAssignProposeProduct--<<<<<<<<<");

		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Click_On_Account1_Action_Icon);
			Thread.sleep(5000);
			CF.FnGetWebElement(driver, "LINKTEXT", DealManagementPageElements.Deal_Information_ViewAndAssignProposeProduct_PencilIcon).click();
			Thread.sleep(5000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Product_Enrollment_Title_HeadingText);
			Thread.sleep(3000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to view and Assign Propose Product on Screen Is Completed Successfully");
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndEnrollProduct
	,Description          : Search and Enroll Propose Product through Product Enrollment UI
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndEnrollProduct(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchAndEnrollProduct--<<<<<<<<<<" + iStartingRow);

		String dateName = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String sProductCode, sProductPriority, sProductEffectiveStartDate, sProductEffectiveEndDate, sProductPriceListInheritance = null;

		sProductCode = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProductPriority = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProductEffectiveStartDate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProductEffectiveEndDate = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProductPriceListInheritance = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();


		System.out.println("sProductCode:-" + sProductCode + " || sProductPriority:-" + sProductPriority + " || sProductEffectiveStartDate:-" + sProductEffectiveStartDate);

		try {
			CF.FnSetFrame(driver, "uiMap");

			Thread.sleep(2000);
			//        CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealIdentifier_TextBox); 
			//        


			if (!sProductCode.equalsIgnoreCase("NoValue")) {
				String sParentWindow = driver.getWindowHandle();
				Thread.sleep(2000);
				CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_SearchProposeProduct_PencilIcon);
				Thread.sleep(8000);
				Set < String > handles = driver.getWindowHandles();
				for (String windowHandle: handles) {
					System.out.println("windowHandle:-" + 1);
					if (!windowHandle.equals(sParentWindow)) {
						driver.switchTo().window(windowHandle);
						Thread.sleep(5000);
						CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_SearchProposeProduct_ExpandFilters_Button);
						Thread.sleep(3000);
						CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_SearchProposeProduct_TextBox);
						Thread.sleep(2000);
						CF.FnClearTextFieldValue(driver, DealManagementPageElements.Product_Enrollment_SearchProposeProduct_TextBox);
						Thread.sleep(2000);
						CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Product_Enrollment_SearchProposeProduct_TextBox).sendKeys(sProductCode);
						Thread.sleep(2000);
						CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_SearchProposeProduct_SearchButton);
						Thread.sleep(2000);
						//driver.close(); //closing child window
						driver.switchTo().window(sParentWindow); //cntrl to parent window
						break;
					}
				}

			}
			System.out.println("done select product");

			CF.FnSetFrame(driver, "uiMap");

			Thread.sleep(3000);

			if (!sProductPriority.equalsIgnoreCase("NoValue")) {
				driver.findElement(By.xpath(DealManagementPageElements.Product_Enrollment_Set_ProductPriority)).sendKeys(sProductPriority);
				//			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Product_Enrollment_Set_ProductPriority).sendKeys(sProductPriority);    										
			}

			Thread.sleep(2000);

			CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_Save_Button);

			Thread.sleep(10000);


			List < WebElement > AllEnrolledProduct = driver.findElements(By.xpath(DealManagementPageElements.Product_Enrollment_List_Proposed_Product));

			int RecordLength = AllEnrolledProduct.size();
			System.out.println("RecordLength:-" + RecordLength);
			for (int SingleRecord = 1; SingleRecord <= RecordLength; SingleRecord++) {

				String EnrolledRecord = "";
				EnrolledRecord = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Product_Enrollment_List_Proposed_Product + "[" + SingleRecord + "]").getAttribute("innerText");
				System.out.println("EnrolledRecord:-" + EnrolledRecord + " || sProductCode:-" + sProductCode + " || SingleRecord:-" + SingleRecord);
				if (EnrolledRecord.trim().equals(sProductCode.trim())) {
					CF.FnTestCaseStatusReport("Pass", "Product Enrolled Successfully. Product Code-> " + sProductCode);

					Thread.sleep(3000);

					CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_Cancel_Button);

					break;
				} else if ((!EnrolledRecord.equals(sProductCode)) && (SingleRecord >= RecordLength)) {
					//		    CF.FnTestCaseStatusReport("Fail", "Product not Enrolled. Product Code-> "+sProductCode);
					System.out.println("<<<<<<<----Fail--->>>>>>>");
				}
			}

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}


	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndValidateProposedProduct
	,Description          : Search and Validate Propose Product through Manually Using Product Enrollment UI
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndValidateProposedProduct(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnSearchAndValidateEnrolledProduct--<<<<<<<<<<" + iStartingRow);

		String dateName = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String sProductCode, sProductDescription, sProductVersionDescription, sProductType, sProductCategory, sProductPriority, sProductStartDate, sProductEndDate = null;

		sProductCode = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProductDescription = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProductVersionDescription = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProductType = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProductCategory = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProductPriority = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sProductStartDate = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sProductEndDate = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();


		System.out.println(iStartingRow + " || sProductCode:-" + sProductCode + " || sProductDescription:-" + sProductDescription + " || sProductVersionDescription:-" + sProductVersionDescription + " || sProductType:-" + sProductType + " || sProductCategory:-" + sProductCategory + " || sProductPriority:-" + sProductPriority + " || sProductStartDate:-" + sProductStartDate + " || sProductEndDate:-" + sProductEndDate);

		try {


			JavascriptExecutor js = (JavascriptExecutor) driver;

			String sProposedProductCode, sProposedProductDescription, sProposedProductVersionDescription, sProposedProductType, sProposedProductCategory, sProposedProductPriority, sProposedProductStartDate, sProposedProductEndDate = null;


			List < WebElement > ProposedProductList = driver.findElements(By.xpath(DealManagementPageElements.Product_Enrollment_All_Proposed_Products));

			int ProposedProductListLength = ProposedProductList.size();
			System.out.println("ProposedProductListLength:-" + ProposedProductListLength);

			for (int SingleRecord = 1; SingleRecord <= ProposedProductListLength; SingleRecord++) {

				WebElement SingleProposedProduct = driver.findElement(By.xpath(DealManagementPageElements.Product_Enrollment_All_Proposed_Products + "[" + SingleRecord + "]"));

				sProposedProductCode = (String) js.executeScript("return arguments[0].children[0].cells[4].innerText;", SingleProposedProduct);
				sProposedProductDescription = (String) js.executeScript("return arguments[0].children[0].cells[5].innerText;", SingleProposedProduct);
				sProposedProductVersionDescription = (String) js.executeScript("return arguments[0].children[0].cells[6].innerText;", SingleProposedProduct);
				sProposedProductType = (String) js.executeScript("return arguments[0].children[0].cells[7].innerText;", SingleProposedProduct);
				sProposedProductCategory = (String) js.executeScript("return arguments[0].children[0].cells[8].innerText;", SingleProposedProduct);
				sProposedProductPriority = (String) js.executeScript("return arguments[0].children[0].cells[9].innerText;", SingleProposedProduct);
				sProposedProductStartDate = (String) js.executeScript("return arguments[0].children[0].cells[10].innerText;", SingleProposedProduct);
				sProposedProductEndDate = (String) js.executeScript("return arguments[0].children[0].cells[11].innerText;", SingleProposedProduct);

				System.out.println(iStartingRow + " || 1st-Test sProductCode:- " + sProductCode + " || sProposedProductCode:-" + sProposedProductCode + " || SingleRecord:-" + SingleRecord + " || ProposedProductListLength:-" + ProposedProductListLength);


				if (sProductCode.trim().equals(sProposedProductCode.trim())) {


					System.out.println(iStartingRow + " || sProductCode:- " + sProductCode + " || sProposedProductCode:-" + sProposedProductCode);
					if (!sProductCode.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductCode, sProposedProductCode);
					}
					System.out.println(iStartingRow + " || sProductDescription:- " + sProductDescription + " || sProposedProductDescription:-" + sProposedProductDescription);
					if (!sProductDescription.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductDescription, sProposedProductDescription);
					}
					System.out.println(iStartingRow + " || sProductVersionDescription:- " + sProductVersionDescription + " || sProposedProductVersionDescription:-" + sProposedProductVersionDescription);
					if (!sProductVersionDescription.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductVersionDescription, sProposedProductVersionDescription);
					}
					System.out.println(iStartingRow + " || sProductType:- " + sProductType + " || sProposedProductType:-" + sProposedProductType);
					if (!sProductType.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductType, sProposedProductType);
					}
					System.out.println(iStartingRow + " || sProductCategory:- " + sProductCategory + " || sProposedProductCategory:-" + sProposedProductCategory);
					if (!sProductCategory.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductCategory, sProposedProductCategory);
					}
					System.out.println(iStartingRow + " || sProductPriority:- " + sProductPriority + " || sProposedProductPriority:-" + sProposedProductPriority);
					if (!sProductPriority.equalsIgnoreCase("NoValue")) {
						assertEquals(sProductPriority, sProposedProductPriority);
					}
					System.out.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sProposedProductStartDate:-" + sProposedProductStartDate);
					if (!sProductStartDate.equalsIgnoreCase("NoValue")) {
						//		        assertEquals(sProductStartDate,sProposedProductStartDate);	
						sProposedProductStartDate = sProposedProductStartDate.replaceAll("\\?", "");
						System.out.println(iStartingRow + " TRIM || sProductStartDate:- " + sProductStartDate + " || sProposedProductStartDate:-" + sProposedProductStartDate);
						if (!sProposedProductStartDate.trim().equals(sProductStartDate.trim())) {
							System.err.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sProposedProductStartDate:-" + sProposedProductStartDate);
						}
					}
					System.out.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sProposedProductEndDate:-" + sProposedProductEndDate);
					if (!sProductEndDate.equalsIgnoreCase("NoValue")) {
						//		        assertEquals(sProductEndDate,sProposedProductEndDate);	 	
						if (!sProposedProductEndDate.equals(sProductEndDate)) {
							System.err.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sProposedProductEndDate:-" + sProposedProductEndDate);
						}
					}





					CF.FnTestCaseStatusReport("Pass", "Proposed Product Successfully Added. Product Code-> " + sProductCode);

					Thread.sleep(3000);

					//					CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_Cancel_Button);

					break;
				} else if ((!sProductCode.trim().equals(sProposedProductCode.trim())) && (SingleRecord >= ProposedProductListLength)) {
					CF.FnTestCaseStatusReport("Fail", "Product not Enrolled. Product Code-> " + sProductCode);
					System.out.println("<<<<<<<----Fail--->>>>>>>" + sProductCode);
				}


			}




			Thread.sleep(3000);
			CF.FnTestCaseStatusReport("Pass", "Search And Validating Proposed Product Is Completed Successfully");



		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}


	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnBroadcastProductDetailsOfProposedProduct
	,Description          : Broadcast Propose Product through Manually Using Product Enrollment UI
	'###############################################################################################################################################################################################################################################*/
	public void FnBroadcastProductDetailsOfProposedProduct(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnBroadcastProductDetailsOfProposedProduct--<<<<<<<<<<" + iStartingRow);

		String sProduct, sProductStartDate, sProductEndDate, sProductPriority, sProductService, sProductServiceStartDate, sProductServiceEndDate, sInclusion = null;


		sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProductStartDate = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProductEndDate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProductPriority = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProductService = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProductServiceStartDate = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sProductServiceEndDate = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sInclusion = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sProduct:-" + sProduct + " || sProductStartDate:-" + sProductStartDate + " || sProductEndDate:-" + sProductEndDate + " || sProductPriority:-" + sProductPriority + " || sProductService:-" + sProductService + " || sProductServiceStartDate:-" + sProductServiceStartDate + " || sProductServiceEndDate:-" + sProductServiceEndDate + " || sInclusion:-" + sInclusion);

		Thread.sleep(3000);

		CF.FnSetFrame(driver, "uiMap");

		Thread.sleep(3000);


		String BroadcastProduct = DealManagementPageElements.Product_Enrollment_All_Proposed_Product_Broadcast1;
		BroadcastProduct = BroadcastProduct.replaceAll("ReplaceProductCodeName", sProduct);
		int AllProductRecords = CF.getListWebElements(driver, "XPATH", BroadcastProduct).size();
		BroadcastProduct = BroadcastProduct + "[" + AllProductRecords + "]";
		CF.FnElementClick(driver, BroadcastProduct);
		CF.FnElementClick(driver, BroadcastProduct);
		System.out.println("BroadcastProduct:-" + BroadcastProduct);

		Thread.sleep(5000);

		String sProposedProduct, sProposedProductStartDate, sProposedProductEndDate, sProposedProductPriority, sProposedProductService, sProposedProductServiceStartDate, sProposedProductServiceEndDate, sProposedInclusion = null;

		CF.FnSetFrame(driver, "uiMap");
		Thread.sleep(3000);

		sProposedProduct = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Product_Enrollment_sProposedProduct).getAttribute("value");
		System.out.println(iStartingRow + " || sProduct:-" + sProduct + " || sProposedProduct:-" + sProposedProduct);


		sProposedProductStartDate = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductStartDate);
		sProposedProductEndDate = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductEndDate);
		sProposedProductPriority = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductPriority);
		sProposedProductService = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductService);
		sProposedProductServiceStartDate = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductServiceStartDate);
		sProposedProductServiceEndDate = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductServiceEndDate);
		sProposedInclusion = CF.FnGetText(driver, DealManagementPageElements.Product_Enrollment_sProposedProductServiceInclusion);

		Thread.sleep(2000);

		//		CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Deal_Logs_Cancel_Button);

		Thread.sleep(3000);

		System.out.println(iStartingRow + " || sProduct:-" + sProduct + " || sProposedProduct:-" + sProposedProduct);
		if (!sProduct.equalsIgnoreCase("NoValue")) {
			assertEquals(sProduct, sProposedProduct);
		}
		System.out.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sProposedProductStartDate:-" + sProposedProductStartDate);
		if (!sProductStartDate.equalsIgnoreCase("NoValue")) {
			//        assertEquals(sProductStartDate,sProposedProductStartDate);
			if (!sProposedProductStartDate.equals(sProductStartDate)) {
				System.err.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sProposedProductStartDate:-" + sProposedProductStartDate);
			}
		}
		System.out.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sProposedProductEndDate:-" + sProposedProductEndDate);
		if (!sProductEndDate.equals("NoValue")) {
			//        assertEquals(sProductEndDate,sProposedProductEndDate);
			if (!sProposedProductStartDate.equals(sProductEndDate)) {
				System.err.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sProposedProductEndDate:-" + sProposedProductEndDate);
			}
		}
		System.out.println(iStartingRow + " || sProductPriority:- " + sProductPriority + " || sProposedProductPriority:-" + sProposedProductPriority);
		if (!sProductPriority.equalsIgnoreCase("NoValue")) {
			assertEquals(sProductPriority, sProposedProductPriority);
		}
		System.out.println(iStartingRow + " || sProductService:- " + sProductService + " || sProposedProductService:-" + sProposedProductService);
		if (!sProductService.equalsIgnoreCase("NoValue")) {
			assertEquals(sProductService, sProposedProductService);
		}
		System.out.println(iStartingRow + " || sProductServiceStartDate:- " + sProductServiceStartDate + " || sProposedProductServiceStartDate:-" + sProposedProductServiceStartDate);
		if (!sProductServiceStartDate.equalsIgnoreCase("NoValue")) {
			//        assertEquals(sProductServiceStartDate,sProposedProductServiceStartDate);
			if (!sProductServiceStartDate.equals(sProposedProductServiceStartDate)) {
				System.err.println(iStartingRow + " || sProductServiceStartDate:- " + sProductServiceStartDate + " || sProposedProductServiceStartDate:-" + sProposedProductServiceStartDate);
			}
		}
		System.out.println(iStartingRow + " || sProductServiceEndDate:- " + sProductServiceEndDate + " || sProposedProductServiceEndDate:-" + sProposedProductServiceEndDate);
		if (!sProductServiceEndDate.equals("NoValue")) {
			//        assertEquals(sProductServiceEndDate,sProposedProductServiceEndDate);
			if (!sProposedProductServiceEndDate.equals(sProductServiceEndDate)) {
				System.err.println(iStartingRow + " || sProductServiceEndDate:- " + sProductServiceEndDate + " || sProposedProductServiceEndDate:-" + sProposedProductServiceEndDate);
			}
		}
		System.out.println(iStartingRow + " || sInclusion:- " + sInclusion + " || sProposedInclusion:-" + sProposedInclusion);
		if (!sInclusion.equalsIgnoreCase("NoValue")) {
			if (sProposedInclusion.equals("on")) {
				sProposedInclusion = "YES";
			}
			assertEquals(sInclusion, sProposedInclusion);
		}

	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndSelectPrizeItemgroup
	,Description          : select Price Items 
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndSelectPrizeItemgroup(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnSearchFiltersForSelectPrizeItemgroup--<<<<<<<<<<" + iStartingRow);

		String sSearchKeyword, sSelectExpectedPriceItems, sAssignmentLevel, sAssignmentLevelCustomerPricelist, sSelectAllCustomerAgreed, sSelectAllCustomerPricelist, sSearchKeywordFromTest, sSelectAll;

		sSearchKeyword = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sAssignmentLevel = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sSelectAll = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sSelectExpectedPriceItems = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

		Boolean IsSelected = false;
		System.out.println(iStartingRow + " || sSearchKeyword:-" + sSearchKeyword + " || sAssignmentLevel:-" + sAssignmentLevel + " || sSelectAll:-" + sSelectAll + " || sSelectExpectedPriceItems:-" + sSelectExpectedPriceItems);
		//		System.out.println("sSelectAllCustomerAgreed:-"+sSelectAllCustomerAgreed+" || sSelectAllCustomerPricelist:-"+sSelectAllCustomerPricelist);
		//		System.out.println("Prize list assignment information is "+sSearchKeyword +sSerchExpectedResult +sAssignmentLevelCustomerAgreed +sAssignmentLevelCustomerPricelist +sSelectAllCustomerAgreed +sSelectAllCustomerPricelist);

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Click_On_Customer_Action_Icon);
			Thread.sleep(3000);
			CF.FnGetWebElement(driver, "LINKTEXT", DealManagementPageElements.Deal_Select_Prize_Item_group_OnCustomer_Pencil_Icon).click();
			Thread.sleep(3000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);

			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Title_Label);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_Filters_Label);

			String Select_Prize_Item_group_Title_Label_TEXT = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_Title_Label).getAttribute("innerText");

			assertEquals(Select_Prize_Item_group_Title_Label_TEXT, "Price Item Selection");

			CF.FnElementClick(driver, DealManagementPageElements.Deal_Price_Item_Group_Selection_ExpandAll_Button);
			
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);

			// Filter Expand if Needed
			if ((!sSearchKeyword.equalsIgnoreCase("NoValue") || !sAssignmentLevel.equalsIgnoreCase("NoValue"))) {
			System.out.println("filter :- "+DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
					Thread.sleep(2000);
				}
			}


			if (!sSearchKeyword.equalsIgnoreCase("NoValue")) {
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sSearchKeyword);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);
			String PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
			PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sSearchKeyword);
			Thread.sleep(3000);
//			CF.FnElementClick(driver, PriceItemPath);
			}

			if (!sAssignmentLevel.equalsIgnoreCase("NoValue") && !sSelectAll.equals("NoValue")) {
				if (sAssignmentLevel.equals("Customer Pricelist") || sAssignmentLevel.equals("Customer Price List")) {
					sAssignmentLevel = "Customer Price List";
				} else if (sAssignmentLevel.equalsIgnoreCase("Account Agreed")) {
					sAssignmentLevel = "Account Agreed";
				} else if (sAssignmentLevel.equalsIgnoreCase("Account Inherited Price List")) {
					sAssignmentLevel = "Account Inherited Price List";
				} else if (sAssignmentLevel.equals("Account Price List") || sAssignmentLevel.equals("Account PriceList")) {
					sAssignmentLevel = "Account Price List";
				} else if (sAssignmentLevel.equalsIgnoreCase("Bundle Pricing")) {
					sAssignmentLevel = "Bundle Pricing";
				} else if (sAssignmentLevel.equalsIgnoreCase("Customer Agreed")) {
					sAssignmentLevel = "Customer Agreed";
				} else if (sAssignmentLevel.equals("Customer Inherited Price List") || sAssignmentLevel.equals("Customer Inherited PriceList")) {
					sAssignmentLevel = "Customer Inherited Price List";
				} else if (sAssignmentLevel.equals("Customer Price List") || sAssignmentLevel.equals("Customer PriceList")) {
					sAssignmentLevel = "Customer Price List";
				} else if (sAssignmentLevel.equals("Default Price List") || sAssignmentLevel.equals("Default Pricelist")) {
					sAssignmentLevel = "Default Price List";
				} else if (sAssignmentLevel.equals("Global Price List") || sAssignmentLevel.equals("Global PriceList")) {
					sAssignmentLevel = "Global Price List";
				} else if (sAssignmentLevel.equalsIgnoreCase("Market Product Pricing")) {
					sAssignmentLevel = "Market Product Pricing";
				} else if (sAssignmentLevel.equalsIgnoreCase("Offer Pricing")) {
					sAssignmentLevel = "Offer Pricing";
				} else if (sAssignmentLevel.equalsIgnoreCase("Parent Customer Agreed")) {
					sAssignmentLevel = "Parent Customer Agreed";
				} else if (sAssignmentLevel.equals("Parent Customer Inherited Price List") || sAssignmentLevel.equalsIgnoreCase("Parent Customer Inherited PriceList")) {
					sAssignmentLevel = "Parent Customer Inherited Price List";
				} else if (sAssignmentLevel.equals("Parent Customer Price List") || sAssignmentLevel.equals("Parent Customer PriceList")) {
					sAssignmentLevel = "Parent Customer Price List";
				} else if (sAssignmentLevel.equals("Price List") || sAssignmentLevel.equals("PriceList")) {
					sAssignmentLevel = "Price List";
				} else if (sAssignmentLevel.equalsIgnoreCase("Product Pricing")) {
					sAssignmentLevel = "Product Pricing";
				}

				Thread.sleep(2000);

				//				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown);
				CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown).click();
				String Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath = DealManagementPageElements.Select_Prize_Item_group_AssignmentLevel_DropDown_Value.replaceAll("ReplacePriceAssignmentStatus", sAssignmentLevel);
				CF.FnElementClick(driver, Select_Prize_Item_group_AssignmentLevel_DropDown_ValuePath);

				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_Apply_Button);

				if (sSelectAll.contains("yes")) {
					CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SelectAll_Button);
					Thread.sleep(1000);
					IsSelected = true;
				}

			} else if (sSelectAll.contains("yes") && sSelectExpectedPriceItems.equals("NoValue")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SelectAll_Button);
				IsSelected = true;
				Thread.sleep(1000);
			} else if (!sSelectExpectedPriceItems.equals("NoValue") && (IsSelected.equals(false))) {

				String[] AllPriceItems = sSelectExpectedPriceItems.split(",");
				System.out.println(iStartingRow + " || AllPriceItems one by one:-" + AllPriceItems);
				int a = AllPriceItems.length;
				System.out.println("a:-"+AllPriceItems);
				if(a > 1) {
					for (String SelectPriceItem: AllPriceItems) {
						System.out.println(iStartingRow + " || SelectPriceItem:-" + SelectPriceItem);
						String PriceItemPath = DealManagementPageElements.Select_Prize_Item_group_PrizeItem_CheckBox_Selection;
						PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", SelectPriceItem);

						Thread.sleep(3000);

						CF.FnElementClick(driver, PriceItemPath);

						System.out.println(iStartingRow + " || selected price item:-" + SelectPriceItem);
					}	
				} else {
					String NoDataFound = "(//*[@class=\'oj-table-body-message\'])[1]";
					String PriceItemMessagePath = CF.FnGetWebElement(driver, "XPATH", NoDataFound).getAttribute("innerText");
					assertEquals(PriceItemMessagePath, "No data to display.");
					System.out.println("assert Pass");
				}


			}


			Thread.sleep(1000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Price_Item_Group_Selection_Save_Button);
			Thread.sleep(2000);
			CF.FnTestCaseStatusReport("Pass", "Price Item Group Selection Completed Successfully for " + sAssignmentLevel + " [" + sSelectExpectedPriceItems + "]");


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddSeasonalPricingForSinglePriceItemFromExcel
	,Description          : To Add Seasonal price item from Excel
	'###############################################################################################################################################################################################################################################*/
	public void FnAddSeasonalPricingForSinglePriceItemFromExcel(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnAddSeasonalPricingForSinglePriceItemFromExcel--<<<<<<<<<<" + iStartingRow);

		String sPriceItem, sPriceItemSeasonalRate, sPriceItemSeasonalStartDate;

		sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sPriceItemSeasonalRate = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sPriceItemSeasonalStartDate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

		System.out.println("sPriceItem:-" + sPriceItem + " || sPriceItemSeasonalRate:-" + sPriceItemSeasonalRate + " || sPriceItemSeasonalStartDate:-" + sPriceItemSeasonalStartDate);
		FnAddSeasonalPricingForSinglePriceItem(sPriceItem, sPriceItemSeasonalRate, sPriceItemSeasonalStartDate);


	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddSeasonalPricingForSinglePriceItem
	,Description          : To Add Seasonal price item
	'###############################################################################################################################################################################################################################################*/
	public void FnAddSeasonalPricingForSinglePriceItem(String PriceItem, String PriceItemRate, String PriceItemStartDate) throws Exception {
		System.out.println(">>>>>>>>>>--FnAddSeasonalPricingForSinglePriceItem--<<<<<<<<<<" + PriceItem + " : " + PriceItemRate + " : " + PriceItemStartDate);

		try {


			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, PriceItem);
			Thread.sleep(5000);

			String PriceItemWithParameterNavigationToOriginalPricingScreenPath = DealManagementPageElements.Deal_PriceItem_with_Parameter_Rate_HyperLink_Navidation_To_Orininal_Pricing_And_Commitments_Screen4.replaceAll("ReplacePriceItem", PriceItem);
			PriceItemWithParameterNavigationToOriginalPricingScreenPath = PriceItemWithParameterNavigationToOriginalPricingScreenPath.replaceAll("ReplaceParameter", ""); // Replace parameter value if required

			CF.FnWaitForElement(driver, 360, PriceItemWithParameterNavigationToOriginalPricingScreenPath);
			CF.FnScrollToElement(driver, CF.FnGetWebElement(driver, "XPATH", PriceItemWithParameterNavigationToOriginalPricingScreenPath));
			Thread.sleep(5000);
			CF.FnElementClick(driver, PriceItemWithParameterNavigationToOriginalPricingScreenPath);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox);
			Thread.sleep(2000);
			CF.FnSetText(driver, DealManagementPageElements.Pricing_And_Commitments_StartDate_TextBox, PriceItemStartDate);

			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(PriceItemRate);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
			Thread.sleep(10000);


			CF.FnTestCaseStatusReport("Pass", "Adding Seasonal Pricing Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen
	,Description          : To verify Product Financial Summary Information Pricing and commitment page through UI
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(int iStartingRow, String sSheetName, String sWorkbook, String Currency, String UserRole, Boolean RecommendedColumn) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen--<<<<<<<<" + Currency + iStartingRow + Currency + UserRole);

		String sProduct, sCurrencyProposedRevenue, sCurrencyProposedCost, sCurrencyOriginalRevenue, sCurrencyOriginalCost, sRevenueVariation, sCurrencyRecommnededRevenue, sCurrencyRecommendedCost;
		sProduct = sCurrencyProposedRevenue = sCurrencyProposedCost = sCurrencyOriginalRevenue = sCurrencyOriginalCost = sCurrencyRecommnededRevenue = sCurrencyRecommendedCost = sRevenueVariation = "";


		if (Currency.equals("Deal")) {

			sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sRevenueVariation = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

			sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
			sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

			sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
			sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();

			if (RecommendedColumn.equals(true)) {
				sCurrencyRecommnededRevenue = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
				sCurrencyRecommendedCost = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
			}

		} else if (Currency.equals("Division")) {

			sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sRevenueVariation = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

			sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
			sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

			sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
			sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();

			if (RecommendedColumn.equals(true)) {
				sCurrencyRecommnededRevenue = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
				sCurrencyRecommendedCost = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
			}
		}

		System.out.println(iStartingRow + " || sProduct:-" + sProduct + " || sRevenueVariation:-" + sRevenueVariation + " || sCurrencyProposedRevenue:-" + sCurrencyProposedRevenue + " || sCurrencyProposedCost:-" + sCurrencyProposedCost + " || sCurrencyOriginalRevenue:-" + sCurrencyOriginalRevenue + " || sCurrencyOriginalCost:-" + sCurrencyOriginalCost + " || sCurrencyRecommnededRevenue:-" + sCurrencyRecommnededRevenue + " || sCurrencyRecommendedCost:-" + sCurrencyRecommendedCost);


		try {

			CF.FnSetFrame(driver, "uiMap");


			JavascriptExecutor js = (JavascriptExecutor) driver;

			String sProductFromApplication, sRevenueVariationFromApplication, sProposedRevenueFromApplication, sProposedCostFromApplication, sOriginalRevenueFromApplication, sOriginalCostFromApplication, sRecommendedRevenueFromApplication, sRecommendedCostFromApplication;
			sProductFromApplication = sRevenueVariationFromApplication = sProposedRevenueFromApplication = sProposedCostFromApplication = sOriginalRevenueFromApplication = sOriginalCostFromApplication = sRecommendedRevenueFromApplication = sRecommendedCostFromApplication = null;

			Thread.sleep(3000);

			String Select_Product_From_Product_Financial_SummaryPath = DealManagementPageElements.Deal_Information_Product_Financial_Summary_On_Pricing_And_Commitment_screen;
			System.out.println("Select_Product_From_Product_Financial_SummaryPath:-" + Select_Product_From_Product_Financial_SummaryPath);

			Select_Product_From_Product_Financial_SummaryPath = Select_Product_From_Product_Financial_SummaryPath.replaceAll("ReplaceProductName", sProduct);
			System.out.println("Select_Product_From_Product_Financial_SummaryPath 2:-" + Select_Product_From_Product_Financial_SummaryPath);

			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Information_Pricing_And_Commitment_Product_ExpandAll, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Pricing_And_Commitment_Product_ExpandAll);
				Thread.sleep(3000);
			}
			Thread.sleep(2000);


			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(3000);

			List < WebElement > FoundProductList = driver.findElements(By.xpath(Select_Product_From_Product_Financial_SummaryPath));
			System.out.println("FoundProductList:-" + FoundProductList);
			int FoundProductListLength = FoundProductList.size();
			System.out.println("FoundProductListLength:-" + FoundProductListLength);

			for (int SingleRecord = 1; SingleRecord <= FoundProductListLength; SingleRecord++) {

				WebElement SingleProposedProduct = driver.findElement(By.xpath(Select_Product_From_Product_Financial_SummaryPath + "[" + SingleRecord + "]"));

				if (Currency.equals("Deal")) {

					sProductFromApplication = (String) js.executeScript("return arguments[0].cells[0].innerText;", SingleProposedProduct);
					sRevenueVariationFromApplication = (String) js.executeScript("return arguments[0].cells[1].innerText;", SingleProposedProduct);
					sProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[2].innerText;", SingleProposedProduct);
					sProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[4].innerText;", SingleProposedProduct);
					sOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[9].innerText;", SingleProposedProduct);
					sOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[11].innerText;", SingleProposedProduct);
					sRecommendedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[6].innerText;", SingleProposedProduct);
					sRecommendedCostFromApplication = (String) js.executeScript("return arguments[0].cells[8].innerText;", SingleProposedProduct);

				} else if (Currency.equals("Division")) {

					sProductFromApplication = (String) js.executeScript("return arguments[0].cells[0].innerText;", SingleProposedProduct);
					sRevenueVariationFromApplication = (String) js.executeScript("return arguments[0].cells[1].innerText;", SingleProposedProduct);
					sProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[3].innerText;", SingleProposedProduct);
					sProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[5].innerText;", SingleProposedProduct);
					sOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[10].innerText;", SingleProposedProduct);
					sOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[12].innerText;", SingleProposedProduct);
					sRecommendedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[7].innerText;", SingleProposedProduct);
					sRecommendedCostFromApplication = (String) js.executeScript("return arguments[0].cells[8].innerText;", SingleProposedProduct);

				}

				sRevenueVariationFromApplication = sRevenueVariationFromApplication.replaceAll(",", "");
				sProposedRevenueFromApplication = sProposedRevenueFromApplication.replaceAll(",", "");
				sProposedCostFromApplication = sProposedCostFromApplication.replaceAll(",", "");
				sOriginalRevenueFromApplication = sOriginalRevenueFromApplication.replaceAll(",", "");
				sOriginalCostFromApplication = sOriginalCostFromApplication.replaceAll(",", "");
				sRecommendedRevenueFromApplication = sRecommendedRevenueFromApplication.replaceAll(",", "");
				sRecommendedCostFromApplication = sRecommendedCostFromApplication.replaceAll(",", "");


				System.out.println(iStartingRow + " || 1 sProduct:- " + sProduct + " || sProductFromApplication:-" + sProductFromApplication);

				if (sProduct.trim().equals(sProductFromApplication.trim())) {


					System.out.println(iStartingRow + " || sProduct:- " + sProduct + " || sProductFromApplication:-" + sProductFromApplication);
					if (!sProduct.equalsIgnoreCase("NoValue")) {
						assertEquals(sProduct.trim(), sProductFromApplication.trim());
					}
					System.out.println(iStartingRow + " || sRevenueVariation:- " + sRevenueVariation + " || sRevenueVariationFromApplication:-" + sRevenueVariationFromApplication);
					if (!sRevenueVariationFromApplication.equalsIgnoreCase("NoValue")) {
						assertTrue(sRevenueVariationFromApplication.contains(sRevenueVariation));
					}
					System.out.println(iStartingRow + " || sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sProposedRevenueFromApplication:-" + sProposedRevenueFromApplication);
					if (!sCurrencyProposedRevenue.equalsIgnoreCase("NoValue")) {
						assertTrue((sProposedRevenueFromApplication).contains((sCurrencyProposedRevenue)));
						if (!sProposedRevenueFromApplication.equals(sCurrencyProposedRevenue)) {
							System.err.println(iStartingRow + " || sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sProposedRevenueFromApplication:-" + sProposedRevenueFromApplication);
						}
					}
					System.out.println(iStartingRow + " || sCurrencyProposedCost:- " + sCurrencyProposedCost + " || sProposedCostFromApplication:-" + sProposedCostFromApplication);
					if (!sCurrencyProposedCost.equalsIgnoreCase("NoValue")) {
						assertTrue(((sProposedCostFromApplication)).contains((sCurrencyProposedCost)));
					}
					System.out.println(iStartingRow + " || sCurrencyOriginalRevenue:- " + sCurrencyOriginalRevenue + " || sOriginalRevenueFromApplication:-" + sOriginalRevenueFromApplication);
					if (!sCurrencyOriginalRevenue.equalsIgnoreCase("NoValue")) {
						assertTrue((sOriginalRevenueFromApplication).contains((sCurrencyOriginalRevenue)));
					}
					System.out.println(iStartingRow + " || sCurrencyOriginalCost:- " + sCurrencyOriginalCost + " || sOriginalCostFromApplication:-" + sOriginalCostFromApplication);
					if (!sCurrencyOriginalCost.equalsIgnoreCase("NoValue")) {
						assertTrue((sOriginalCostFromApplication).contains((sCurrencyOriginalCost)));
					}
					if (RecommendedColumn.equals(true)) {

						System.out.println(iStartingRow + " || sCurrencyRecommnededRevenue:- " + sCurrencyRecommnededRevenue + " || sRecommendedRevenueFromApplication:-" + sRecommendedRevenueFromApplication);
						if (!sCurrencyRecommnededRevenue.equalsIgnoreCase("NoValue")) {
							assertTrue((sRecommendedRevenueFromApplication).contains((sCurrencyRecommnededRevenue)));
						}
						System.out.println(iStartingRow + " || sCurrencyRecommendedCost:- " + sCurrencyRecommendedCost + " || sRecommendedCostFromApplication:-" + sRecommendedCostFromApplication);
						if (!sCurrencyRecommendedCost.equalsIgnoreCase("NoValue")) {
							assertTrue((sRecommendedCostFromApplication).contains((sCurrencyRecommendedCost)));
						}
					}



					CF.FnTestCaseStatusReport("Pass", "Product Financial summary Verified on Deal Information Pricing & commitment screen Page Is Completed Successfully. Product Code-> " + sProduct);

					Thread.sleep(3000);


					break;

				} else if ((!sProduct.trim().equals(sProductFromApplication.trim())) && (SingleRecord >= FoundProductListLength)) {
					CF.FnTestCaseStatusReport("Fail", "Product Financial summary NOT Verified. Product Code-> " + sProduct);
					System.out.println("<<<<<<<----Fail--->>>>>>>" + sProduct);
				}
			}
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : PricingAndCommitmentProductFinancialSummarySwitchToggle
	,Description          : To Switch PricingAndCommitmentProductFinancialSummarySwitchToggle
	'###############################################################################################################################################################################################################################################*/
	public void PricingAndCommitmentProductFinancialSummarySwitchToggle() throws Exception {
		System.out.println("<<<<<<<-- inside PricingAndCommitmentProductFinancialSummarySwitchToggle -->>>>>>>");
		Thread.sleep(2000);
		CF.FnSetFrame(driver, "uiMap");
		Thread.sleep(2000);
		try {
			//      WebElement toggle = driver.findElement(By.xpath(DealManagementPageElements.Deal_Information_Pricing_And_Commitment_Division_Currency_Switch_Toggle));
			//      toggle.click();
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Pricing_And_Commitment_Division_Currency_Switch_Toggle).click();
			Thread.sleep(3000);
		} catch (Exception rte) {
			System.out.println("error" + rte);
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : DealInformationProductFinancialSummarySwitchToggle
	,Description          : To Switch DealInformationProductFinancialSummarySwitchToggle
	'###############################################################################################################################################################################################################################################*/
	public void DealInformationProductFinancialSummarySwitchToggle() throws Exception {
		System.out.println("<<<<<<<-- inside DealInformationPersonHierarchySwitchToggle -->>>>>>>");
		Thread.sleep(2000);
		CF.FnSetFrame(driver, "zoneMapFrame_1");
		Thread.sleep(2000);
		try {
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Product_FinancialSummary_Switch_Toggle).click();
		} catch (Exception rte) {
			System.out.println("rtr" + rte);
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : DealInformationPersonHierarchySwitchToggle
	,Description          : To Switch DealInformationPersonHierarchySwitchToggle
	'###############################################################################################################################################################################################################################################*/
	public void DealInformationPersonHierarchySwitchToggle() throws Exception {
		System.out.println("<<<<<<<-- inside DealInformationPersonHierarchySwitchToggle -->>>>>>>");
		Thread.sleep(2000);
		CF.FnSetFrame(driver, "zoneMapFrame_1");
		Thread.sleep(3000);
		try {
			//	CF.FnElementClick(driver, DealManagementPageElements.Deal_Person_Hierarchy_Switch_Toggle);
			//  CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Person_Hierarchy_Switch_Toggle));
			//  WebElement toggle = driver.findElement(By.xpath(DealManagementPageElements.Deal_Person_Hierarchy_Switch_Toggle));
			//  toggle.click();
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Person_Hierarchy_Switch_Toggle).click();

		} catch (Exception rte) {
			System.out.println("rtr" + rte);
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency--" + iStartingRow);

		String sPersonName, sDivision, sStatus, sRevenueVaration, sProjectedRevenue, sProjectedCost, sOriginalRevenue, sOriginalCost;

		sPersonName = sDivision = sStatus = sRevenueVaration = sProjectedRevenue = sProjectedCost = sOriginalRevenue = sOriginalCost = null;

		sPersonName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sRevenueVaration = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || Personal Hierarchy Information for Division Currency is " + sPersonName + sDivision + sStatus + sRevenueVaration + sProjectedRevenue + sProjectedCost + sOriginalRevenue + sOriginalCost);
		System.out.println(iStartingRow + " || sPersonName:-" + sPersonName + " || sDivision:-" + sDivision + " || sStatus:-" + sStatus + " || sDivision:-" + sDivision + " || sRevenueVaration:-" + sRevenueVaration + " || sProjectedRevenue:-" + sProjectedRevenue + " || sProjectedCost:-" + sProjectedCost + " || sOriginalRevenue:-" + sOriginalRevenue + " || sOriginalCost:-" + sOriginalCost);
		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(3000);

			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_PersonalHierarchyPersonName_Label.replaceAll("ReplacePersonName", sPersonName));

			if (!sPersonName.equalsIgnoreCase("NoValue")) {
				String sPersonNameFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyPersonName_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim();
				System.out.println("PersonName value from application is " + sPersonNameFromApplication);
				assertEquals(sPersonName, sPersonNameFromApplication);
			}

			if (!sDivision.equalsIgnoreCase("NoValue")) {
				String sDivisionFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionName_Label.replaceAll("ReplacePersonName", sPersonName)).getText().trim();
				System.out.println("Division value from application is " + sDivisionFromApplication);
				assertEquals(sDivision, sDivisionFromApplication);
			}

			//			if (!sStatus.equalsIgnoreCase("NoValue")) {
			//				if (sStatus.equalsIgnoreCase("Approved")) {
			//					String sStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.PersonHierarchyApproveStatus).getAttribute("src");
			//					String sStatusIconFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.SimulationDetailsApproveStatusIcon).getAttribute("src");
			//					System.out.println("Status value from application is " + sStatus + " Icon link - " + sStatusFromApplication);
			//					assertEquals(sStatus, sStatusFromApplication);
			//				}
			//
			//			}

			//			if (!sRevenueVaration.equalsIgnoreCase("NoValue")) {
			//				String sRevenueVarationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyRevenueVariationValue_Label).getText().trim();
			//				System.out.println(iStartingRow + " || sRevenueVariation:-" + sRevenueVaration + " || sRevenueVarationFromApplication:-" + sRevenueVarationFromApplication);
			//				assertEquals(sRevenueVaration, sRevenueVarationFromApplication);
			//			}

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionProjectedRevenueValue_Label.replaceAll("ReplacePersonName", sPersonName)).getAttribute("innerText");
				sProjectedRevenueFromApplication = sProjectedRevenueFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sProjectedRevenue:-" + sProjectedRevenue + " || sProjectedRevenueFromApplication:-" + sProjectedRevenueFromApplication);
				assertTrue(sProjectedRevenueFromApplication.contains(sProjectedRevenue));

			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionProjectedCostValue_Label.replaceAll("ReplacePersonName", sPersonName)).getAttribute("innerText");
				sProjectedCostFromApplication = sProjectedCostFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sProjectedCost:-" + sProjectedCost + " || sProjectedCostFromApplication:-" + sProjectedCostFromApplication);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}
/*
			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionOriginalRevenueValue_Label.replaceAll("ReplacePersonName", sPersonName)).getAttribute("innerText");
				sOriginalRevenueFromApplication = sOriginalRevenueFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sOriginalRevenue:-" + sOriginalRevenue + " || sOriginalRevenueFromApplication:-" + sOriginalRevenueFromApplication);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionOriginalCostValue_Label.replaceAll("ReplacePersonName", sPersonName)).getAttribute("innerText");
				sOriginalCostFromApplication = sOriginalCostFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sOriginalCost:-" + sOriginalCost + " || sOriginalCostFromApplication:-" + sOriginalCostFromApplication);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}
*/
			Thread.sleep(1000);
			CF.FnTestCaseStatusReport("Pass", "Personal Hierarchy Information Verification on Deal Information Page Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPersonalHierarchyInformationOnDealInformationForAccountsForDivisionCurrency
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPersonalHierarchyInformationOnDealInformationForAccountsForDivisionCurrency(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyPersonalHierarchyInformationOnDealInformationForAccountsForDivisionCurrency--<<<<<<<<" + iStartingRow);

		String sAccountName, sDivision, sStatus, sRevenueVaration, sProjectedRevenue, sProjectedCost, sOriginalRevenue, sOriginalCost;

		sAccountName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sRevenueVaration = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sAccountName:-" + sAccountName + " || sDivision:-" + sDivision + " || sStatus:-" + sStatus + " || sDivision:-" + sDivision + " || sRevenueVaration:-" + sRevenueVaration + " || sProjectedRevenue:-" + sProjectedRevenue + " || sProjectedCost:-" + sProjectedCost + " || sOriginalRevenue:-" + sOriginalRevenue + " || sOriginalCost:-" + sOriginalCost);

		try {
			CF.FnSetFrame(driver, "main");
			Thread.sleep(1000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(1000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(3000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_PersonalHierarchyAccountNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName));


			if (!sAccountName.equalsIgnoreCase("NoValue")) {
				String sAccountNameFromApplicationByText = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyAccountNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim();
				System.out.println(iStartingRow + " || sAccountNameFromApplication by text for account is " + sAccountNameFromApplicationByText);
				Thread.sleep(3000);
				assertTrue(sAccountNameFromApplicationByText.contains(sAccountName));
			}

			if (!sDivision.equalsIgnoreCase("NoValue")) {
				String sDivisionFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyDivisionNameForAccounts_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim();
				Thread.sleep(3000);
				System.out.println(iStartingRow + " || Division value from application is " + sDivisionFromApplication);
				assertEquals(sDivision, sDivisionFromApplication);
			}


			//			if (!sRevenueVaration.equalsIgnoreCase("NoValue")) {
			//				String sRevenueVarationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyRevenueVariationValue_Label.replaceAll("ReplaceAccountName", sAccountName)).getText().trim();
			//				System.out.println(iStartingRow + " || sRevenueVariation:-" + sRevenueVaration + " || sRevenueVarationFromApplication:-" + sRevenueVarationFromApplication);
			//				assertEquals(sRevenueVaration, sRevenueVarationFromApplication);
			//			}

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				String sProjectedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedRevenueValueForAccounts_Label_Division_view.replaceAll("ReplaceAccountName", sAccountName)).getAttribute("innerText");
				sProjectedRevenueFromApplication = sProjectedRevenueFromApplication.replaceAll(",", "");
				//sProjectedRevenueFromApplication = getNumberFromString(sProjectedRevenueFromApplication);
				System.out.println(iStartingRow + " || sProjectedRevenueFromApplication:-" + sProjectedRevenueFromApplication + " || sProjectedRevenue:-" + sProjectedRevenue);
				assertTrue(sProjectedRevenueFromApplication.contains(sProjectedRevenue));
			}

			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				String sProjectedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyProjectedCostValueForAccounts_Label_Division_view.replaceAll("ReplaceAccountName", sAccountName)).getAttribute("innerText");
				sProjectedCostFromApplication = sProjectedCostFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sProjectedCostFromApplication" + sProjectedCostFromApplication + " || sProjectedCost:-" + sProjectedCost);
				assertTrue(sProjectedCostFromApplication.contains(sProjectedCost));
			}

			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalRevenueValueForAccounts_Label_Division_view.replaceAll("ReplaceAccountName", sAccountName)).getAttribute("innerText");
				sOriginalRevenueFromApplication = sOriginalRevenueFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sOriginalRevenueFromApplication:-" + sOriginalRevenueFromApplication + " || sOriginalRevenue:-" + sOriginalRevenue);
				assertTrue(sOriginalRevenueFromApplication.contains(sOriginalRevenue));
			}

			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				String sOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_PersonalHierarchyOriginalCostValueForAccounts_Label_Division_view.replaceAll("ReplaceAccountName", sAccountName)).getAttribute("innerText");
				sOriginalCostFromApplication = sOriginalCostFromApplication.replaceAll(",", "");
				System.out.println(iStartingRow + " || sOriginalCostFromApplication:-" + sOriginalCostFromApplication + " || sOriginalCost:-" + sOriginalCost);
				assertTrue(sOriginalCostFromApplication.contains(sOriginalCost));
			}

			Thread.sleep(500);
			CF.FnTestCaseStatusReport("Pass", "Personal Hierarchy Information Verification On Deal Information for Accounts Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyProductFinancialSummaryOnDealLevels
	,Description          : To verify Product Financial Summary on Deal Level
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyProductFinancialSummaryOnDealLevels(int iStartingRow, String sSheetName, String sWorkbook, String Currency, String UserRole) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyProductFinancialSummaryOnDealLevels--<<<<<<<<" + Currency + iStartingRow + Currency + UserRole);

		//		String sProduct,sProductName = null, sDivision, sApprovalStatus, sCurrencyProposedRevenue, sCurrencyProposedCost, sCurrencyProposedProfit, sCurrencyProposedProfitability, sCurrencyOriginalRevenue, sCurrencyOriginalCost, sCurrencyOriginalProfit, sCurrencyOriginalProfitability, sProfitVariation;
		//		sProduct = sDivision = sApprovalStatus = sCurrencyProposedRevenue = sCurrencyProposedCost = sCurrencyProposedProfit = sCurrencyProposedProfitability = sCurrencyOriginalRevenue = sCurrencyOriginalCost = sCurrencyOriginalProfit = sCurrencyOriginalProfitability = sProfitVariation = "";

		String sProductName = null, sProductDivisionName = null, sProductProposedRevenue = null, sProductProposedCost = null, sProductProposedProfit = null, sProductProposedProfitability = null, sProductOriginalRevenue = null, sProductOriginalCost = null, sProductOriginalProfit = null, sProductOriginalProfitability = null, sProductStandardRevenue = null, sProductStandardCost = null, sProductStandardProfit = null, sProductStandardProfitability = null;

		if (Currency.equals("Deal")) {


			sProductName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sProductDivisionName = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
			sProductProposedRevenue = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
			sProductProposedCost = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
			sProductProposedProfit = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
			sProductProposedProfitability = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();

			sProductOriginalRevenue = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
			sProductOriginalCost = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
			sProductOriginalProfit = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
			sProductOriginalProfitability = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();

			//			sProductStandardRevenue = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//			sProductStandardCost = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//			sProductStandardProfit = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//			sProductStandardProfitability = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();


		} else if (Currency.equals("Division")) {


			sProductName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sProductDivisionName = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
			sProductProposedRevenue = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
			sProductProposedCost = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
			sProductProposedProfit = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
			sProductProposedProfitability = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();

			sProductOriginalRevenue = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
			sProductOriginalCost = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
			sProductOriginalProfit = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
			sProductOriginalProfitability = CF.FnGetCellValue(iStartingRow, 19, sSheetName, sWorkbook).toString().trim();

			//					sProductStandardRevenue = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//					sProductStandardCost = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//					sProductStandardProfit = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			//					sProductStandardProfitability = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();


		}




		if (!sProductName.equalsIgnoreCase("NoValue")) {

			CF.FnSetFrame(driver, "zoneMapFrame_1");

			Thread.sleep(3000);

			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductSectionExpandButton).getAttribute("title").contains("Collapse")) {
				System.out.println("pro :-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductSectionExpandButton).getAttribute("title"));
				CF.FnElementClick(driver, DealManagementPageElements.ProductSectionExpandButton);
			}

			int TotalProductFound = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.ProductNamePath.replaceAll("ReplaceProductName", sProductName)).size();

			if (TotalProductFound > 0) {


				if (!sProductName.equalsIgnoreCase("NoValue")) {
					String sProductNameFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductNamePath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductName:-" + sProductName + " || sProductNameFromApplication:-" + sProductNameFromApplication);
					assertEquals(sProductName, sProductNameFromApplication);
				}


				if (!sProductDivisionName.equalsIgnoreCase("NoValue")) {
					String ProductDivisionNameFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductDivisionNamePath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductDivisionName:-" + sProductDivisionName + " || ProductDivisionNameFromApplication:-" + ProductDivisionNameFromApplication);
					if (!sProductDivisionName.contains(ProductDivisionNameFromApplication)) {
						assertEquals(sProductDivisionName, ProductDivisionNameFromApplication);
					}
				}

				if (!sProductProposedRevenue.equalsIgnoreCase("NoValue")) {
					String ProductProposedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductProposedRevenuePath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductProposedRevenue:-" + sProductProposedRevenue + " || ProductProposedRevenueFromApplication:-" + ProductProposedRevenueFromApplication);
					assertEquals(sProductProposedRevenue, ProductProposedRevenueFromApplication);
				}

				if (!sProductProposedCost.equalsIgnoreCase("NoValue")) {
					String ProductProposedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductProposedCostPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductProposedCost:-" + sProductProposedCost + " || ProductProposedCostFromApplication:-" + ProductProposedCostFromApplication);
					assertEquals(sProductProposedCost, ProductProposedCostFromApplication);
				}

				if (!sProductProposedProfit.equalsIgnoreCase("NoValue")) {
					String ProductProposedProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductProposedProfitPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductProposedProfit:-" + sProductProposedProfit + " || ProductProposedProfitFromApplication:-" + ProductProposedProfitFromApplication);
					assertEquals(sProductProposedProfit, ProductProposedProfitFromApplication);
				}

				if (!sProductProposedProfitability.equalsIgnoreCase("NoValue")) {
					String ProductProposedProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductProposedProfitabilityPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductProposedProfitability:-" + sProductProposedProfitability + " || ProductProposedProfitabilityFromApplication:-" + ProductProposedProfitabilityFromApplication);
					assertEquals(sProductProposedProfitability, ProductProposedProfitabilityFromApplication);
				}

				if (!sProductOriginalRevenue.equalsIgnoreCase("NoValue")) {
					String ProductOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductOriginalRevenuePath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductOriginalRevenue:-" + sProductOriginalRevenue + " || ProductOriginalRevenueFromApplication:-" + ProductOriginalRevenueFromApplication);
					assertEquals(sProductOriginalRevenue, ProductOriginalRevenueFromApplication);
				}

				if (!sProductOriginalCost.equalsIgnoreCase("NoValue")) {
					String ProductOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductOriginalCostPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductOriginalCost:-" + sProductOriginalCost + " || ProductOriginalCostFromApplication:-" + ProductOriginalCostFromApplication);
					assertEquals(sProductOriginalCost, ProductOriginalCostFromApplication);
				}

				if (!sProductOriginalProfit.equalsIgnoreCase("NoValue")) {
					String ProductOriginalProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductOriginalProfitPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductOriginalProfit:-" + sProductOriginalProfit + " || ProductOriginalProfitFromApplication:-" + ProductOriginalProfitFromApplication);
					assertEquals(sProductOriginalProfit, ProductOriginalProfitFromApplication);
				}

				if (!sProductOriginalProfitability.equalsIgnoreCase("NoValue")) {
					String ProductOriginalProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.ProductOriginalProfitabilityPath.replaceAll("ReplaceProductName", sProductName)).getText().replaceAll(",", "").trim();
					System.out.println("sProductOriginalProfitability:-" + sProductOriginalProfitability + " || ProductOriginalProfitabilityFromApplication:-" + ProductOriginalProfitabilityFromApplication);
					assertEquals(sProductOriginalProfitability, ProductOriginalProfitabilityFromApplication);
				}

			} else {
				CF.FnTestCaseStatusReport("Fail", "Product Financial summary NOT Verified. Product Code-> " + sProductName);
			}


		}



		/*
		
		if (UserRole.equals("INDSPM")) {
			if (Currency.equals("Deal")) {

				sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();

				sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

				sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();

				sProfitVariation = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

			} else if (Currency.equals("Division")) {

				sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();

				sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();

				sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();

				sProfitVariation = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
			}
		} else {

			if (Currency.equals("Deal")) {

				sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
				sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
				sApprovalStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

				sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedProfit = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedProfitability = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();

				sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalProfit = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalProfitability = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();

				sProfitVariation = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();

			} else if (Currency.equals("Division")) {

				sProduct = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
				sDivision = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
				sApprovalStatus = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

				sCurrencyProposedRevenue = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedCost = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedProfit = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
				sCurrencyProposedProfitability = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();

				sCurrencyOriginalRevenue = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalCost = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalProfit = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
				sCurrencyOriginalProfitability = CF.FnGetCellValue(iStartingRow, 19, sSheetName, sWorkbook).toString().trim();

				sProfitVariation = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();
			}

		}



		sProfitVariation = sProfitVariation.replaceAll(",", "");
		sCurrencyProposedRevenue = sCurrencyProposedRevenue.replaceAll(",", "");
		sCurrencyProposedCost = sCurrencyProposedCost.replaceAll(",", "");
		sCurrencyProposedProfit = sCurrencyProposedProfit.replaceAll(",", "");
		sCurrencyProposedProfitability = sCurrencyProposedProfitability.replaceAll(",", "");
		sCurrencyOriginalRevenue = sCurrencyOriginalRevenue.replaceAll(",", "");
		sCurrencyOriginalCost = sCurrencyOriginalCost.replaceAll(",", "");
		sCurrencyOriginalProfit = sCurrencyOriginalProfit.replaceAll(",", "");
		sCurrencyOriginalProfitability = sCurrencyOriginalProfitability.replaceAll(",", "");


		System.out.println(iStartingRow + " || Product Financial Summary Information :-" + sProduct);

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_2");


			JavascriptExecutor js = (JavascriptExecutor) driver;

			String sProductFromApplication = null, sCurrencyProposedRevenueFromApplication = null, sCurrencyProposedCostFromApplication = null, sCurrencyProposedProfitFromApplication = null, sCurrencyProposedProfitabilityFromApplication = null, sCurrencyOriginalRevenueFromApplication = null, sCurrencyOriginalCostFromApplication = null, sCurrencyOriginalProfitFromApplication = null, sCurrencyOriginalProfitabilityFromApplication = null, sProfitVariationFromApplication = null;

			Thread.sleep(3000);

			String Select_Product_From_Product_Financial_SummaryPath = DealManagementPageElements.Select_Product_From_Product_Financial_Summary;
			// System.out.println("Select_Product_From_Product_Financial_SummaryPath:-"+Select_Product_From_Product_Financial_SummaryPath);

			Select_Product_From_Product_Financial_SummaryPath = Select_Product_From_Product_Financial_SummaryPath.replaceAll("ReplaceProductName", sProduct);
			System.out.println(iStartingRow + " || Select_Product_From_Product_Financial_SummaryPath 2:-" + Select_Product_From_Product_Financial_SummaryPath);

			CF.FnSetFrame(driver, "zoneMapFrame_2");
			Thread.sleep(3000);

			List < WebElement > FoundProductList = driver.findElements(By.xpath(Select_Product_From_Product_Financial_SummaryPath));
			System.out.println("FoundProductList:-" + FoundProductList);
			int FoundProductListLength = FoundProductList.size();
			System.out.println("FoundProductListLength:-" + FoundProductListLength);

			for (int SingleRecord = 1; SingleRecord <= FoundProductListLength; SingleRecord++) {

				WebElement SingleProposedProduct = driver.findElement(By.xpath(Select_Product_From_Product_Financial_SummaryPath + "[" + SingleRecord + "]"));

				if (Currency.equals("Deal")) {
					sProductFromApplication = (String) js.executeScript("return arguments[0].cells[0].innerText;", SingleProposedProduct);
					sCurrencyProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[3].innerText;", SingleProposedProduct);
					sCurrencyProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[5].innerText;", SingleProposedProduct);
					sCurrencyProposedProfitFromApplication = (String) js.executeScript("return arguments[0].cells[7].innerText;", SingleProposedProduct);
					sCurrencyProposedProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[9].innerText;", SingleProposedProduct);

					sCurrencyOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[15].innerText;", SingleProposedProduct);
					sCurrencyOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[17].innerText;", SingleProposedProduct);
					sCurrencyOriginalProfitFromApplication = (String) js.executeScript("return arguments[0].cells[19].innerText;", SingleProposedProduct);
					sCurrencyOriginalProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[21].innerText;", SingleProposedProduct);

					sProfitVariationFromApplication = (String) js.executeScript("return arguments[0].cells[22].innerText;", SingleProposedProduct);

				} else if (Currency.equals("Division")) {
					sProductFromApplication = (String) js.executeScript("return arguments[0].cells[0].innerText;", SingleProposedProduct);
					sCurrencyProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[4].innerText;", SingleProposedProduct);
					sCurrencyProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[6].innerText;", SingleProposedProduct);
					sCurrencyProposedProfitFromApplication = (String) js.executeScript("return arguments[0].cells[8].innerText;", SingleProposedProduct);
					sCurrencyProposedProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[9].innerText;", SingleProposedProduct);

					sCurrencyOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[16].innerText;", SingleProposedProduct);
					sCurrencyOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[18].innerText;", SingleProposedProduct);
					sCurrencyOriginalProfitFromApplication = (String) js.executeScript("return arguments[0].cells[20].innerText;", SingleProposedProduct);
					sCurrencyOriginalProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[21].innerText;", SingleProposedProduct);

					sProfitVariationFromApplication = (String) js.executeScript("return arguments[0].cells[22].innerText;", SingleProposedProduct);

				}


				sProfitVariationFromApplication = sProfitVariationFromApplication.replaceAll(",", "");
				sCurrencyProposedRevenueFromApplication = sCurrencyProposedRevenueFromApplication.replaceAll(",", "");
				sCurrencyProposedCostFromApplication = sCurrencyProposedCostFromApplication.replaceAll(",", "");
				sCurrencyProposedProfitFromApplication = sCurrencyProposedProfitFromApplication.replaceAll(",", "");
				sCurrencyProposedProfitabilityFromApplication = sCurrencyProposedProfitabilityFromApplication.replaceAll(",", "");
				sCurrencyOriginalRevenueFromApplication = sCurrencyOriginalRevenueFromApplication.replaceAll(",", "");
				sCurrencyOriginalCostFromApplication = sCurrencyOriginalCostFromApplication.replaceAll(",", "");
				sCurrencyOriginalProfitFromApplication = sCurrencyOriginalProfitFromApplication.replaceAll(",", "");
				sCurrencyOriginalProfitabilityFromApplication = sCurrencyOriginalProfitabilityFromApplication.replaceAll(",", "");

				System.out.println(iStartingRow + " || sProduct:-" + sProduct + " || sProductFromApplication:-" + sProductFromApplication + " || SingleRecord:-" + SingleRecord + " || FoundProductListLength:-" + FoundProductListLength);

				if (sProduct.trim().equals(sProductFromApplication.trim())) {


					System.out.println(iStartingRow + " || sProduct:- " + sProduct + " || sProductFromApplication:-" + sProductFromApplication);
					if (!sProduct.equals("NoValue") || !UserRole.equals("INDSPM")) {
						assertEquals(sProduct.trim(), sProductFromApplication.trim());
					}
					System.out.println(iStartingRow + " || sProfitVariation:- " + sProfitVariation + " || sProfitVariationFromApplication:-" + sProfitVariationFromApplication);
					if (!sProfitVariation.equals("NoValue") || !UserRole.equals("INDSPM")) {
						assertEquals(Float.valueOf(sProfitVariation), Float.valueOf(sProfitVariationFromApplication));
					}
					System.out.println("sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sCurrencyProposedRevenueFromApplication:-" + sCurrencyProposedRevenueFromApplication);
					if (!sCurrencyProposedRevenue.equals("NoValue") || !UserRole.equals("INDSPM")) {
						assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedRevenueFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedRevenue)));
						//						if (!sCurrencyProposedRevenueFromApplication.equals(sCurrencyProposedRevenue)) {
						//							System.err.println("sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sCurrencyProposedRevenueFromApplication:-" + sCurrencyProposedRevenueFromApplication);
						//						}
					}
					System.out.println(iStartingRow + " || sCurrencyProposedCost:- " + sCurrencyProposedCost + " || sCurrencyProposedCostFromApplication:-" + sCurrencyProposedCostFromApplication);
					if (!sCurrencyProposedCost.equals("NoValue") || UserRole.equals("INDSPM")) {
						sCurrencyProposedCost = sCurrencyProposedCost.toString();
						assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedCostFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedCost)));
					}
					System.out.println(iStartingRow + " || sCurrencyProposedProfit:- " + sCurrencyProposedProfit + " || sCurrencyProposedProfitFromApplication:-" + sCurrencyProposedProfitFromApplication);
					if (!sCurrencyProposedProfit.equals("NoValue") || !UserRole.equals("INDSPM")) {
						sCurrencyProposedProfit = sCurrencyProposedProfit.toString();
						assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedProfitFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedProfit)));
						//						if (!sCurrencyProposedProfitFromApplication.equals(sCurrencyProposedProfit)) {
						//							System.err.println(iStartingRow + " || sCurrencyProposedProfit:- " + sCurrencyProposedProfit + " || sCurrencyProposedProfitFromApplication:-" + sCurrencyProposedProfitFromApplication);
						//						}
					}
					System.out.println(iStartingRow + " || sCurrencyProposedProfitability:- " + sCurrencyProposedProfitability + " || sCurrencyProposedProfitabilityFromApplication:-" + sCurrencyProposedProfitabilityFromApplication);
					if (!sCurrencyProposedProfitability.equals("NoValue") || !UserRole.equals("INDSPM")) {
						sCurrencyProposedProfitability = sCurrencyProposedProfitability.toString();
						assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedProfitabilityFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedProfitability)));
						//						if (!sCurrencyProposedProfitabilityFromApplication.equals(sCurrencyProposedProfitability)) {
						//							System.err.println(iStartingRow + " || sCurrencyProposedProfitability:- " + sCurrencyProposedProfitability + " || sCurrencyProposedProfitabilityFromApplication:-" + sCurrencyProposedProfitabilityFromApplication);
						//						}
					}


					System.out.println(iStartingRow + " || sCurrencyOriginalRevenue:- " + sCurrencyOriginalRevenue + " || sCurrencyOriginalRevenueFromApplication:-" + sCurrencyOriginalRevenueFromApplication);
					if (!sCurrencyOriginalRevenue.equals("NoValue") || !UserRole.equals("INDSPM")) {
						assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalRevenueFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalRevenue)));
					}
					System.out.println(iStartingRow + " || sCurrencyOriginalCost:- " + sCurrencyOriginalCost + " || sCurrencyOriginalCostFromApplication:-" + sCurrencyOriginalCostFromApplication);
					if (!sCurrencyOriginalCost.equals("NoValue") || !UserRole.equals("INDSPM")) {
						assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalCostFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalCost)));
					}
					System.out.println(iStartingRow + " || sCurrencyOriginalProfit:- " + sCurrencyOriginalProfit + " || sCurrencyOriginalProfitFromApplication:-" + sCurrencyOriginalProfitFromApplication);
					if (!sCurrencyOriginalProfit.equals("NoValue") || !UserRole.equals("INDSPM")) {
						sCurrencyOriginalProfit = sCurrencyOriginalProfit.toString();
						assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalProfitFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalProfit)));
					}
					System.out.println(iStartingRow + " || sCurrencyOriginalProfitability:- " + sCurrencyOriginalProfitability + " || sCurrencyOriginalProfitabilityFromApplication:-" + sCurrencyOriginalProfitabilityFromApplication);
					if (!sCurrencyOriginalProfitability.equals("NoValue") || !UserRole.equals("INDSPM")) {
						sCurrencyOriginalProfitability = sCurrencyOriginalProfitability.toString();
						assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalProfitabilityFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalProfitability)));
					}




					CF.FnTestCaseStatusReport("Pass", Currency + " Product Financial summary Verified on Deal Information Page Is Completed Successfully. Product Code-> " + sProduct);

					Thread.sleep(3000);


					break;
				} else if ((!sProduct.trim().equals(sProductFromApplication.trim())) && (SingleRecord >= FoundProductListLength)) {
					CF.FnTestCaseStatusReport("Fail", "Product Financial summary NOT Verified. Product Code-> " + sProduct);
					System.out.println("<<<<<<<----Fail--->>>>>>>" + sProduct);
				}

			}


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
		
		*/

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDivisionFinancialSummary
	,Description          : To verify Division Financial Summary Information On Deal Information page through UI
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDivisionFinancialSummary(int iStartingRow, String sSheetName, String sWorkbook, String Currency) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyDivisionFinancialSummary--<<<<<<<<< " + Currency + iStartingRow);

		String sDivision = null, sDivisionApprovalStatus, sDivisionProposedRevenue = null, sDivisionProposedCost = null, sDivisionProposedProfit = null, sDivisionProposedProfitability = null, sDivisionOriginalRevenue = null, sDivisionOriginalCost = null, sDivisionOriginalProfit = null, sDivisionOriginalProfitability = null, sRevenueVariation = null, sProfitVariation = null, sDivisionStandardRevenue = null, sDivisionStandardCost = null, sDivisionStandardProfit = null, sDivisionStandardProfitability = null;

		if (Currency.equals("Deal")) {

			sDivision = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sDivisionApprovalStatus = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

			sDivisionProposedRevenue = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedCost = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedProfit = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedProfitability = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();

			sDivisionOriginalRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalProfit = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalProfitability = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();

//			sProfitVariation = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
			sRevenueVariation = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();

			sDivisionStandardRevenue = CF.FnGetCellValue(iStartingRow, 41, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardCost = CF.FnGetCellValue(iStartingRow, 42, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardProfit = CF.FnGetCellValue(iStartingRow, 43, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardProfitability = CF.FnGetCellValue(iStartingRow, 44, sSheetName, sWorkbook).toString().trim();


		} else if (Currency.equals("Division")) {

			sDivision = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sDivisionApprovalStatus = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

			sDivisionProposedRevenue = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedCost = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedProfit = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
			sDivisionProposedProfitability = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();

			sDivisionOriginalRevenue = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalCost = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalProfit = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
			sDivisionOriginalProfitability = CF.FnGetCellValue(iStartingRow, 19, sSheetName, sWorkbook).toString().trim();

//			sProfitVariation = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();
			sRevenueVariation = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();

			sDivisionStandardRevenue = CF.FnGetCellValue(iStartingRow, 45, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardCost = CF.FnGetCellValue(iStartingRow, 46, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardProfit = CF.FnGetCellValue(iStartingRow, 47, sSheetName, sWorkbook).toString().trim();
			sDivisionStandardProfitability = CF.FnGetCellValue(iStartingRow, 48, sSheetName, sWorkbook).toString().trim();

		}


		Thread.sleep(3000);

		//
		//		sProfitVariation = sProfitVariation.replaceAll(",", "");
		//		sCurrencyProposedRevenue = sCurrencyProposedRevenue.replaceAll(",", "");
		//		sCurrencyProposedCost = sCurrencyProposedCost.replaceAll(",", "");
		//		sCurrencyOriginalRevenue = sCurrencyOriginalRevenue.replaceAll(",", "");
		//		sCurrencyOriginalCost = sCurrencyOriginalCost.replaceAll(",", "");



		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");

			Thread.sleep(3000);

			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionSectionExpandButton).getAttribute("title").contains("Collapse")) {
				System.out.println("pro :-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionSectionExpandButton).getAttribute("title"));
				CF.FnElementClick(driver, DealManagementPageElements.DivisionSectionExpandButton);
			}


			if (!sDivisionProposedRevenue.equalsIgnoreCase("NoValue")) {
				String sDivisionProposedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedRevenuePath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionProposedRevenue:-" + sDivisionProposedRevenue + " || sDivisionProposedRevenueFromApplication:-" + sDivisionProposedRevenueFromApplication);
				assertEquals(sDivisionProposedRevenue, sDivisionProposedRevenueFromApplication);
			}

			if (!sDivisionProposedCost.equalsIgnoreCase("NoValue")) {
				String sDivisionProposedCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedCostPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionProposedCost:-" + sDivisionProposedCost + " || sDivisionProposedCostFromApplication:-" + sDivisionProposedCostFromApplication);
				assertEquals(sDivisionProposedCost, sDivisionProposedCostFromApplication);
			}

			if (!sDivisionProposedProfit.equalsIgnoreCase("NoValue")) {
				String sDivisionProposedProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedProfitPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionProposedProfit:-" + sDivisionProposedProfit + " || sDivisionProposedProfitFromApplication:-" + sDivisionProposedProfitFromApplication);
				//			    assertEquals(sDivisionProposedProfit, sDivisionProposedProfitFromApplication);
			}

			if (!sDivisionProposedProfitability.equalsIgnoreCase("NoValue")) {
				String sDivisionProposedProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedProfitabilityPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionProposedProfitability:-" + sDivisionProposedProfitability + " || sDivisionProposedProfitabilityFromApplication:-" + sDivisionProposedProfitabilityFromApplication);
				assertEquals(sDivisionProposedProfitability, sDivisionProposedProfitabilityFromApplication);
			}


			if (!sRevenueVariation.equalsIgnoreCase("NoValue")) {
				String sRevenueVariationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedOGRevenueVariationPath).getText().replaceAll(",", "").trim();
				System.out.println("sRevenueVariation:-" + sRevenueVariation + " || sRevenueVariationFromApplication:-" + sRevenueVariationFromApplication);
				assertEquals(sRevenueVariation, sRevenueVariationFromApplication);
			}
			
//			if (!sProfitVariation.equalsIgnoreCase("NoValue")) {
//				String sProfitVariationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionProposedOGProfitVariationPath).getText().replaceAll(",", "").trim();
//				System.out.println("sProfitVariation:-" + sProfitVariation + " || sProfitVariationFromApplication:-" + sProfitVariationFromApplication);
//				assertEquals(sProfitVariation, sProfitVariationFromApplication);
//			}


			if (!sDivisionOriginalRevenue.equalsIgnoreCase("NoValue")) {
				String sDivisionOriginalRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionOriginalRevenuePath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionOriginalRevenue:-" + sDivisionOriginalRevenue + " || sDivisionOriginalRevenueFromApplication:-" + sDivisionOriginalRevenueFromApplication);
				assertEquals(sDivisionOriginalRevenue, sDivisionOriginalRevenueFromApplication);
			}

			if (!sDivisionOriginalCost.equalsIgnoreCase("NoValue")) {
				String sDivisionOriginalCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionOriginalCostPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionOriginalCost:-" + sDivisionOriginalCost + " || sDivisionOriginalCostFromApplication:-" + sDivisionOriginalCostFromApplication);
				assertEquals(sDivisionOriginalCost, sDivisionOriginalCostFromApplication);
			}

			if (!sDivisionOriginalProfit.equalsIgnoreCase("NoValue")) {
				String sDivisionOriginalProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionOriginalProfitPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionOriginalProfit:-" + sDivisionOriginalProfit + " || sDivisionOriginalProfitFromApplication:-" + sDivisionOriginalProfitFromApplication);
				assertEquals(sDivisionOriginalProfit, sDivisionOriginalProfitFromApplication);
			}

			if (!sDivisionOriginalProfitability.equalsIgnoreCase("NoValue")) {
				String sDivisionOriginalProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionOriginalProfitabilityPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionOriginalProfitability:-" + sDivisionOriginalProfitability + " || sDivisionOriginalProfitabilityFromApplication:-" + sDivisionOriginalProfitabilityFromApplication);
				assertEquals(sDivisionOriginalProfitability, sDivisionOriginalProfitabilityFromApplication);
			}


			if (!sDivisionStandardRevenue.equalsIgnoreCase("NoValue")) {
				String sDivisionStandardRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionStandardRevenuePath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionStandardRevenue:-" + sDivisionStandardRevenue + " || sDivisionStandardRevenueFromApplication:-" + sDivisionStandardRevenueFromApplication);
				assertEquals(sDivisionStandardRevenue, sDivisionStandardRevenueFromApplication);
			}

			if (!sDivisionStandardCost.equalsIgnoreCase("NoValue")) {
				String sDivisionStandardCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionStandardCostPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionStandardCost:-" + sDivisionStandardCost + " || sDivisionStandardCostFromApplication:-" + sDivisionStandardCostFromApplication);
				assertEquals(sDivisionStandardCost, sDivisionStandardCostFromApplication);
			}

			if (!sDivisionStandardProfit.equalsIgnoreCase("NoValue")) {
				String sDivisionStandardProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionStandardProfitPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionStandardProfit:-" + sDivisionStandardProfit + " || sDivisionStandardProfitFromApplication:-" + sDivisionStandardProfitFromApplication);
				assertEquals(sDivisionStandardProfit, sDivisionStandardProfitFromApplication);
			}

			if (!sDivisionStandardProfitability.equalsIgnoreCase("NoValue")) {
				String sDivisionStandardProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DivisionStandardProfitabilityPath).getText().replaceAll(",", "").trim();
				System.out.println("sDivisionStandardProfitability:-" + sDivisionStandardProfitability + " || sDivisionStandardProfitabilityFromApplication:-" + sDivisionStandardProfitabilityFromApplication);
				assertEquals(sDivisionStandardProfitability, sDivisionStandardProfitabilityFromApplication);
			}
			/*			
						JavascriptExecutor js = (JavascriptExecutor) driver;


						String sDivisionFromApplication = null, sCurrencyProposedRevenueFromApplication = null, sCurrencyProposedCostFromApplication = null, sCurrencyProposedProfitFromApplication = null, sCurrencyProposedProfitabilityFromApplication = null, sCurrencyOriginalRevenueFromApplication = null, sCurrencyOriginalCostFromApplication = null, sCurrencyOriginalProfitFromApplication = null, sCurrencyOriginalProfitabilityFromApplication = null, sProfitVariationFromApplication = null;

						Thread.sleep(3000);

						String Select_Product_From_Product_Financial_SummaryPath = DealManagementPageElements.Deal_Division_Financial_Summary_Record;

						CF.FnSetFrame(driver, "zoneMapFrame_1");

						Thread.sleep(3000);

						List < WebElement > FoundProductList = driver.findElements(By.xpath(Select_Product_From_Product_Financial_SummaryPath));

						int FoundProductListLength = FoundProductList.size();

						System.out.println("FoundProductListLength:-" + FoundProductListLength);
						
						
						*/




			/*
						for (int SingleRecord = 1; SingleRecord <= FoundProductListLength; SingleRecord++) {

							WebElement SingleProposedProduct = driver.findElement(By.xpath(Select_Product_From_Product_Financial_SummaryPath + "[" + SingleRecord + "]"));

							if (Currency.equals("Deal")) {

								sDivisionFromApplication = (String) js.executeScript("return arguments[0].cells[1].innerText;", SingleProposedProduct);
								sCurrencyProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[3].innerText;", SingleProposedProduct);
								sCurrencyProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[5].innerText;", SingleProposedProduct);
								sCurrencyProposedProfitFromApplication = (String) js.executeScript("return arguments[0].cells[7].innerText;", SingleProposedProduct);
								sCurrencyProposedProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[9].innerText;", SingleProposedProduct);

								sCurrencyOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[15].innerText;", SingleProposedProduct);
								sCurrencyOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[17].innerText;", SingleProposedProduct);
								sCurrencyOriginalProfitFromApplication = (String) js.executeScript("return arguments[0].cells[19].innerText;", SingleProposedProduct);
								sCurrencyOriginalProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[21].innerText;", SingleProposedProduct);

								sProfitVariationFromApplication = (String) js.executeScript("return arguments[0].cells[22].innerText;", SingleProposedProduct);

							} else if (Currency.equals("Division")) {

								sDivisionFromApplication = (String) js.executeScript("return arguments[0].cells[1].innerText;", SingleProposedProduct);
								sCurrencyProposedRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[4].innerText;", SingleProposedProduct);
								sCurrencyProposedCostFromApplication = (String) js.executeScript("return arguments[0].cells[6].innerText;", SingleProposedProduct);
								sCurrencyProposedProfitFromApplication = (String) js.executeScript("return arguments[0].cells[8].innerText;", SingleProposedProduct);
								sCurrencyProposedProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[9].innerText;", SingleProposedProduct);

								sCurrencyOriginalRevenueFromApplication = (String) js.executeScript("return arguments[0].cells[16].innerText;", SingleProposedProduct);
								sCurrencyOriginalCostFromApplication = (String) js.executeScript("return arguments[0].cells[18].innerText;", SingleProposedProduct);
								sCurrencyOriginalProfitFromApplication = (String) js.executeScript("return arguments[0].cells[20].innerText;", SingleProposedProduct);
								sCurrencyOriginalProfitabilityFromApplication = (String) js.executeScript("return arguments[0].cells[21].innerText;", SingleProposedProduct);

								sProfitVariationFromApplication = (String) js.executeScript("return arguments[0].cells[22].innerText;", SingleProposedProduct);

							}

							sProfitVariationFromApplication = sProfitVariationFromApplication.replaceAll(",", "");
							sCurrencyProposedRevenueFromApplication = sCurrencyProposedRevenueFromApplication.replaceAll(",", "");
							sCurrencyProposedCostFromApplication = sCurrencyProposedCostFromApplication.replaceAll(",", "");
							sCurrencyProposedProfitFromApplication = sCurrencyProposedProfitFromApplication.replaceAll(",", "");
							sCurrencyProposedProfitabilityFromApplication = sCurrencyProposedProfitabilityFromApplication.replaceAll(",", "");
							sCurrencyOriginalRevenueFromApplication = sCurrencyOriginalRevenueFromApplication.replaceAll(",", "");
							sCurrencyOriginalCostFromApplication = sCurrencyOriginalCostFromApplication.replaceAll(",", "");
							sCurrencyOriginalProfitFromApplication = sCurrencyOriginalProfitFromApplication.replaceAll(",", "");
							sCurrencyOriginalProfitabilityFromApplication = sCurrencyOriginalProfitabilityFromApplication.replaceAll(",", "");

							System.out.println(iStartingRow + " || 1 sDivision:- " + sDivision + " || sDivisionFromApplication:-" + sDivisionFromApplication);

							if (sDivision.trim().equals(sDivision.trim())) {


								System.out.println(iStartingRow + " || sDivision:- " + sDivision + " || sDivisionFromApplication:-" + sDivisionFromApplication);
								if (!sDivision.equalsIgnoreCase("NoValue")) {
									assertEquals(sDivision.trim(), sDivisionFromApplication.trim());
								}
								System.out.println(iStartingRow + " || sProfitVariation:- " + sProfitVariation + " || sProfitVariationFromApplication:-" + sProfitVariationFromApplication);
								if (!sProfitVariation.equalsIgnoreCase("NoValue")) {
									assertTrue(getIntegerNumberFromFloatString(sProfitVariationFromApplication).contains(getIntegerNumberFromFloatString(sProfitVariation)));
									if (!sProfitVariationFromApplication.equals(sProfitVariation)) {
										System.err.println(iStartingRow + " || sProfitVariation:- " + sProfitVariation + " || sProfitVariationFromApplication:-" + sProfitVariationFromApplication);

									}
								}
								System.out.println(iStartingRow + " || sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sCurrencyProposedRevenueFromApplication:-" + sCurrencyProposedRevenueFromApplication);
								if (!sCurrencyProposedRevenue.equalsIgnoreCase("NoValue")) {
									//		        	assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedRevenueFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedRevenue)));
									if (!sCurrencyProposedRevenueFromApplication.equals(sCurrencyProposedRevenue)) {
										System.out.println("sCurrencyProposedRevenue:- " + sCurrencyProposedRevenue + " || sCurrencyProposedRevenueFromApplication:-" + sCurrencyProposedRevenueFromApplication);
									}
								}
								System.out.println(iStartingRow + " || sCurrencyProposedCost:- " + sCurrencyProposedCost + " || sCurrencyProposedCostFromApplication:-" + sCurrencyProposedCostFromApplication);
								if (!sCurrencyProposedCost.equalsIgnoreCase("NoValue")) {
									sCurrencyProposedCost = sCurrencyProposedCost.toString();
									assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedCostFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedCost)));
								}
								System.out.println(iStartingRow + " || sCurrencyProposedProfit:- " + sCurrencyProposedProfit + " || sCurrencyProposedProfitFromApplication:-" + sCurrencyProposedProfitFromApplication);
								if (!sCurrencyProposedProfit.equalsIgnoreCase("NoValue")) {
									sCurrencyProposedProfit = sCurrencyProposedProfit.toString();
									//		        	assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedProfitFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedProfit)));
									if (!sCurrencyProposedProfitFromApplication.equals(sCurrencyProposedProfit)) {
										System.err.println(iStartingRow + " || sCurrencyProposedProfit:- " + sCurrencyProposedProfit + " || sCurrencyProposedProfitFromApplication:-" + sCurrencyProposedProfitFromApplication);
									}
								}
								System.out.println(iStartingRow + " || sCurrencyProposedProfitability:- " + sCurrencyProposedProfitability + " || sCurrencyProposedProfitabilityFromApplication:-" + sCurrencyProposedProfitabilityFromApplication);
								if (!sCurrencyProposedProfitability.equalsIgnoreCase("NoValue")) {
									sCurrencyProposedProfitability = sCurrencyProposedProfitability.toString();
									//		        	assertTrue(getIntegerNumberFromFloatString(sCurrencyProposedProfitabilityFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyProposedProfitability)));
									if (!sCurrencyProposedProfitabilityFromApplication.equals(sCurrencyProposedProfitability)) {
										System.err.println(iStartingRow + " || sCurrencyProposedProfitability:- " + sCurrencyProposedProfitability + " || sCurrencyProposedProfitabilityFromApplication:-" + sCurrencyProposedProfitabilityFromApplication);
									}
								}


								System.out.println(iStartingRow + " || sCurrencyOriginalRevenue:- " + sCurrencyOriginalRevenue + " || sCurrencyOriginalRevenueFromApplication:-" + sCurrencyOriginalRevenueFromApplication);
								if (!sCurrencyOriginalRevenue.equalsIgnoreCase("NoValue")) {
									assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalRevenueFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalRevenue)));
								}
								System.out.println(iStartingRow + " || sCurrencyOriginalCost:- " + sCurrencyOriginalCost + " || sCurrencyOriginalCostFromApplication:-" + sCurrencyOriginalCostFromApplication);
								if (!sCurrencyOriginalCost.equalsIgnoreCase("NoValue")) {
									assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalCostFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalCost)));
								}
								System.out.println(iStartingRow + " || sCurrencyOriginalProfit:- " + sCurrencyOriginalProfit + " || sCurrencyOriginalProfitFromApplication:-" + sCurrencyOriginalProfitFromApplication);
								if (!sCurrencyOriginalProfit.equalsIgnoreCase("NoValue")) {
									sCurrencyOriginalProfit = sCurrencyOriginalProfit.toString();
									assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalProfitFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalProfit)));
								}
								System.out.println(iStartingRow + " || sCurrencyOriginalProfitability:- " + sCurrencyOriginalProfitability + " || sCurrencyOriginalProfitabilityFromApplication:-" + sCurrencyOriginalProfitabilityFromApplication);
								if (!sCurrencyOriginalProfitability.equalsIgnoreCase("NoValue")) {
									sCurrencyOriginalProfitability = sCurrencyOriginalProfitability.toString();
									assertTrue(getIntegerNumberFromFloatString(sCurrencyOriginalProfitabilityFromApplication).contains(getIntegerNumberFromFloatString(sCurrencyOriginalProfitability)));
								}




								CF.FnTestCaseStatusReport("Pass", "Division Financial summary Verified on Deal Information Page Is Completed Successfully.");

								Thread.sleep(3000);


								break;
							} else if ((!sDivision.trim().equals(sDivisionFromApplication.trim())) && (SingleRecord >= FoundProductListLength)) {
								CF.FnTestCaseStatusReport("Fail", "Division Financial summary NOT Verified. Product Code-> " + sDivisionFromApplication);
								System.out.println("<<<<<<<----Fail--->>>>>>>" + sDivision);
							}

						}
			*/

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : DealInformationDivisionFinancialSummarySwitchToggle
	,Description          : To Switch currency from Deal to Division Financial Summary Information On Deal Information page through UI
	'###############################################################################################################################################################################################################################################*/
	public void DealInformationDivisionFinancialSummarySwitchToggle() throws Exception {
		System.out.println("<<<<<<<-- inside DealInformationDivisionFinancialSummarySwitchToggle -->>>>>>>");
		Thread.sleep(2000);
		CF.FnSetFrame(driver, "zoneMapFrame_1");
		Thread.sleep(2000);
		try {
			WebElement toggle = driver.findElement(By.xpath(DealManagementPageElements.Deal_Division_Financial_Summary_Switch_Toggle));
			toggle.click();
			Thread.sleep(2000);
		} catch (Exception divCurr) {
			System.out.println("divCurr:-" + divCurr);
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealFinancialSummaryStatus
	,Description          : To Verify Deal Financial Summary status
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealFinancialSummaryStatus(String DealStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyDealFinancialSummaryStatus--<<<<<<<<");

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(1000);

			if (DealStatus.equals("PENDING FOR APPROVAL")) {
				DealStatus = "c1/images/priceitem_pending.png";
				System.out.println("src:-" + DealStatus);
			} else if (DealStatus.equals("APPROVED")) {
				DealStatus = "c1/images/priceitem_appr.png";
				System.out.println("src:-" + DealStatus);
			} else if (DealStatus.equals("ERROR")) {
				DealStatus = "c1/images/priceitem_error.png";
			}


			Thread.sleep(1000);


			// here we are comparing approval image from deal information with pricing screen price item shown image
			String GetDealStatusTextFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Financial_Summary_Status, "src");
			System.out.println("System Status:-" + GetDealStatusTextFromApplication + " || Deal Status:-" + DealStatus);
			if (GetDealStatusTextFromApplication.contains(DealStatus)) {
				CF.FnTestCaseStatusReport("Pass", "Verification of Deal Financial Summary status Is Completed Successfully");
			} else {
				CF.FnTestCaseStatusReport("Fail", "Verification of Deal Financial Summary status Not Completed");
			}


			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealFinancialSummary
	,Description          : To verify Division Financial Summary Information On Deal Information page through UI
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealFinancialSummary(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyDealFinancialSummary--<<<<<<<< " + iStartingRow);

		String sDivision, sApprovalStatus, sProjectedRevenue, sProjectedCost, sProjectedProfit, sProjectedProfitability, sOriginalRevenue, sOriginalCost, sOriginalProfit, sOriginalProfitability, sOriginalRevenueVariation, sStandardRevenueVariation, sOriginalProfitVariation, sStandardProfitVariation, sCurrency;



		sDivision = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sApprovalStatus = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

		sProjectedRevenue = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProjectedCost = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProjectedProfit = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProjectedProfitability = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();

		sOriginalRevenue = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sOriginalCost = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sOriginalProfit = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sOriginalProfitability = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();

		sOriginalRevenueVariation = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		sStandardRevenueVariation = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		sOriginalProfitVariation = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
		sStandardProfitVariation = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();

		sCurrency = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();

		String sRecommendedRevenue = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
		String sRecommendedCost = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
		String sRecommendedProfit = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
		String sRecommendedProfitability = CF.FnGetCellValue(iStartingRow, 19, sSheetName, sWorkbook).toString().trim();

		try {


			String sProjectedRevenueFromApplication, sProjectedCostFromApplication, sProjectedProfitFromApplication, sProjectedProfitabilityFromApplication, sOriginalRevenueFromApplication, sOriginalCostFromApplication, sOriginalProfitFromApplication, sOriginalProfitabilityFromApplication, sOriginalRevenueVariationFromApplication, sApprovalStatusFromApplication, sRecommendedRevenueFromApplication, sRecommendedProfitabilityFromApplication, sRecommendedProfitFromApplication;

			Thread.sleep(500);

			CF.FnSetFrame(driver, "zoneMapFrame_1");

			Thread.sleep(3000);

			if (!CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.DealSectionExpandButton).getAttribute("title").contains("Collapse")) {
				Thread.sleep(500);
				CF.FnElementClick(driver, DealManagementPageElements.DealSectionExpandButton);
				Thread.sleep(500);
			}


			//                if (!sApprovalStatus.equalsIgnoreCase("NoValue")) {
			//                    sApprovalStatusFromApplication = CF.FnGetTextFromElement(driver, "(//*[@orafield=\"finSummaryStatusflg\"])[2]", "src");
			//                    System.out.println(iStartingRow + " || sApprovalStatus:- " + sApprovalStatus + " || sApprovalStatusFromApplication:-" + sApprovalStatusFromApplication);
			//                    //assertEquals(sOriginalRevenueVariation, sOriginalRevenueVariationFromApplication);
			//                }

			if (!sProjectedRevenue.equalsIgnoreCase("NoValue")) {
				sProjectedRevenueFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Projected_Revenue, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sProjectedRevenue:- " + sProjectedRevenue + " || sProjectedRevenueFromApplication:-" + sProjectedRevenueFromApplication);
				assertEquals(sProjectedRevenue, sProjectedRevenueFromApplication);
			}
			if (!sProjectedCost.equalsIgnoreCase("NoValue")) {
				sProjectedCostFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Projected_cost, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sProjectedCost:- " + sProjectedCost + " || sProjectedCostFromApplication:-" + sProjectedCostFromApplication);
				assertEquals(sProjectedCost, sProjectedCostFromApplication);
			}
			if (!sProjectedProfit.equalsIgnoreCase("NoValue")) {
				sProjectedProfitFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Projected_Profit, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sProjectedProfit:- " + sProjectedProfit + " || sProjectedProfitFromApplication:-" + sProjectedProfitFromApplication);
				assertEquals(sProjectedProfit, sProjectedProfitFromApplication);
			}
			if (!sProjectedProfitability.equalsIgnoreCase("NoValue")) {
				sProjectedProfitabilityFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Projected_Profitability, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sProjectedProfitability:- " + sProjectedProfitability + " || sProjectedProfitabilityFromApplication:-" + sProjectedProfitabilityFromApplication);
				assertEquals(sProjectedProfitability, sProjectedProfitabilityFromApplication);
			}


			if (!sOriginalRevenueVariation.equalsIgnoreCase("NoValue")) {
				sOriginalRevenueVariationFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Profit_Variation_Percent, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sOriginalRevenueVariation:- " + sOriginalRevenueVariation + " || sOriginalRevenueVariationFromApplication:-" + sOriginalRevenueVariationFromApplication);
				assertEquals(sOriginalRevenueVariation, sOriginalRevenueVariationFromApplication);
			}
			
			if (!sOriginalRevenue.equalsIgnoreCase("NoValue")) {
				sOriginalRevenueFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Original_Revenue, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sOriginalRevenue:- " + sOriginalRevenue + " || sOriginalRevenueFromApplication:-" + sOriginalRevenueFromApplication);
				assertEquals(sOriginalRevenue, sOriginalRevenueFromApplication);
			}
			if (!sOriginalCost.equalsIgnoreCase("NoValue")) {
				sOriginalCostFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Original_Cost, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sOriginalCost:- " + sOriginalCost + " || sOriginalCostFromApplication:-" + sOriginalCostFromApplication);
				assertEquals(sOriginalCost, sOriginalCostFromApplication);
			}
			if (!sOriginalProfit.equalsIgnoreCase("NoValue")) {
				sOriginalProfitFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Original_Profit, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sOriginalProfit:- " + sOriginalProfit + " || sOriginalProfitFromApplication:-" + sOriginalProfitFromApplication);
				assertEquals(sOriginalProfit, sOriginalProfitFromApplication);
			}
			if (!sOriginalProfitability.equalsIgnoreCase("NoValue")) {
				sOriginalProfitabilityFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Original_Profitability, "innerText").replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sOriginalProfitability:- " + sOriginalProfitability + " || sOriginalProfitabilityFromApplication:-" + sOriginalProfitabilityFromApplication);
				assertEquals(sOriginalProfitability, sOriginalProfitabilityFromApplication);
			}



			if (!sRecommendedRevenue.equalsIgnoreCase("NoValue")) {
				sRecommendedRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.RecommendedRevenue).getText().replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sRecommendedRevenue:- " + sRecommendedRevenue + " || sRecommendedRevenueFromApplication:-" + sRecommendedRevenueFromApplication);
				assertEquals(sRecommendedRevenue, sRecommendedRevenueFromApplication);
			}

			if (!sRecommendedProfit.equalsIgnoreCase("NoValue")) {
				sRecommendedProfitFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.RecommendedProfitAmount).getText().replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sRecommendedProfit:- " + sRecommendedProfit + " || sRecommendedProfitFromApplication:-" + sRecommendedProfitFromApplication);
				assertEquals(sRecommendedProfit, sRecommendedProfitFromApplication);
			}

			if (!sRecommendedProfitability.equalsIgnoreCase("NoValue")) {
				sRecommendedProfitabilityFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.RecommendedProfitability).getText().replaceAll(",", "").trim();
				System.out.println(iStartingRow + " || sRecommendedProfitability:- " + sRecommendedProfitability + " || sRecommendedProfitabilityFromApplication:-" + sRecommendedProfitabilityFromApplication);
				assertEquals(sRecommendedProfitability, sRecommendedProfitabilityFromApplication);
			}


			CF.FnTestCaseStatusReport("Pass", "Deal Financial summary Verified on Deal Information Page Is Completed Successfully. ");

			Thread.sleep(1000);




		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealLogs
	,Description          : To verify Deal logs of Exchange Rate from INR to USD
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealLogs(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyDealLogs--<<<<<<<<<" + iStartingRow);


		String sCreationDateTime, sDetails, sUser, sLogType = null;

		sCreationDateTime = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDetails = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sUser = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sLogType = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();

		sCreationDateTime = sCreationDateTime.split("\\ ")[0];



		System.out.println(iStartingRow + " || FnVerifyDealLogs Information || CreationDateTime:-" + sCreationDateTime + " || Details:-" + sDetails + " || User:-" + sUser + " || LogType:-" + sLogType);

		try {

			CF.FnSetFrame(driver, "tabPage");
			Thread.sleep(2000);

			//Navigate to Deal_Logs Tab
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Deal_Logs_Navigation);
			Thread.sleep(3000);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);


			String sCreationDateTimeFromApplication, sDetailsFromApplication, sUserFromApplication, sLogTypeFromApplication = null;


			int CheckAllLogSize = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Information_Deal_Logs_CreationDateTime).size();

			for (int BX = 1; BX <= CheckAllLogSize; BX++) {


				sCreationDateTimeFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Deal_Logs_CreationDateTime + "[" + BX + "]").getAttribute("innerText");
				sDetailsFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Deal_Logs_Details + "[" + BX + "]").getAttribute("innerText");
				sUserFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Deal_Logs_User + "[" + BX + "]").getAttribute("innerText");
				sLogTypeFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Deal_Logs_LogType + "[" + BX + "]").getAttribute("innerText");

				sCreationDateTimeFromApplication = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");

				int CheckPoints = 0;

				System.out.println(iStartingRow + " || CreationDateTimeFromApplication:-" + sCreationDateTimeFromApplication + " || sCreationDateTime:-" + sCreationDateTime);
				if (!sCreationDateTime.equalsIgnoreCase("NoValue")) {
					if (sCreationDateTime.equalsIgnoreCase(sCreationDateTimeFromApplication)) {
						assertTrue(sCreationDateTimeFromApplication.contains(sCreationDateTime));
						CheckPoints = CheckPoints + 1;
					} else if (BX == CheckAllLogSize) {
						assertTrue(sCreationDateTimeFromApplication.contains(sCreationDateTime));
					}
				}

				System.out.println(iStartingRow + " || sDetails:- " + sDetails + " || sDetailsFromApplication:-" + sDetailsFromApplication);
				if (!sDetails.equalsIgnoreCase("NoValue")) {
					if (sDetails.equalsIgnoreCase(sDetailsFromApplication)) {
						assertEquals(sDetails, sDetailsFromApplication);
						CheckPoints = CheckPoints + 1;
					} else if (BX == CheckAllLogSize) {
						assertEquals(sDetails, sDetailsFromApplication);
					}
				}


				System.out.println(iStartingRow + " || sUser:- " + sUser + " || sUserFromApplication:-" + sUserFromApplication);
				if (!sUser.equalsIgnoreCase("NoValue")) {
					if (sUser.equalsIgnoreCase(sUserFromApplication)) {
						assertEquals(sUser, sUserFromApplication);
						CheckPoints = CheckPoints + 1;
					} else if (BX == CheckAllLogSize) {
						assertEquals(sUser, sUserFromApplication);
					}
				}

				System.out.println(iStartingRow + " || sLogType:- " + sLogType + " || sLogTypeFromApplication:-" + sLogTypeFromApplication);
				if (!sLogType.equalsIgnoreCase("NoValue")) {
					if (sLogType.equalsIgnoreCase(sLogTypeFromApplication)) {
						assertEquals(sLogType, sLogTypeFromApplication);
						CheckPoints = CheckPoints + 1;
					} else if (BX == CheckAllLogSize) {
						assertEquals(sLogType, sLogTypeFromApplication);
					}
				}


				System.out.println("CheckPoints :-" + CheckPoints + " || CheckAllLogSize:-" + CheckAllLogSize);
				if (CheckPoints == 4) {
					System.out.println("IF CheckPoints :-" + CheckPoints);
					break;
				} else if ((BX < CheckAllLogSize)) {
					System.out.println("Else if CheckPoints :-" + CheckPoints);
					continue;
				} else {
					System.out.println("Else CheckPoints :-" + CheckPoints);
					CF.FnTestCaseStatusReport("Fail", "'" + sDetails + "' Deal Log Not Verified");
				}

			}

			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Deal_Logs_Cancel_Button);
			Thread.sleep(3000);

			CF.FnTestCaseStatusReport("Pass", "'" + sDetails + "' Deal Log Verified Successfully");


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}


	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealToDoType
	,Description          : To Verify deal ToDo Type from deal dashboard
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealToDoType(String DealId, String TabName) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyDealToDoTYPE--<<<<<<<<<<" + DealId + " || TabName:-" + TabName);

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");

			String DealToDo = null;
			if (TabName.equals("Deal_Dashboard")) {
				DealToDo = DealManagementPageElements.Deal_Information_Deal_Dashboard_ToDo_on_Table;
			} else if (TabName.equals("Deal_Approver_Dashboard")) {
				DealToDo = DealManagementPageElements.Deal_Information_Deal_Approver_Dashboard_ToDo_on_Table;
			}

			DealToDo = DealToDo.replaceAll("ReplaceDealId", DealId);
			System.out.println(" || DealToDo:-" + DealToDo);

			try {

				int sdb = CF.getListWebElements(driver, "XPATH", "(//div[.//span[text()=\"Deal To Do\"]]//td[.//@value=\"Search\"]/input)").size();
				if (sdb > 0) {
					CF.FnElementClick(driver, "(//div[.//span[text()=\"Deal To Do\"]]//td[.//@value=\"Search\"]/input)");
					Thread.sleep(5000);
				}
				if (CF.FnGetWebElement(driver, "XPATH", DealToDo).isDisplayed()) {
					Thread.sleep(5000);
					String DealTodoGetText = CF.FnGetTextFromElement(driver, DealToDo, "innerText");

					if (DealTodoGetText.contains(DealId) && DealTodoGetText.contains("next Level Successfully") && TabName.equals("Deal_Dashboard")) {
						CF.FnTestCaseStatusReport("Pass", "Deal TODO for Deal ID from Deal Dashboard Is Completed Successfully");
					} else if (DealTodoGetText.contains(DealId) && DealTodoGetText.contains("Deal Assigned to You for Approval") && TabName.equals("Deal_Approver_Dashboard")) {
						CF.FnTestCaseStatusReport("Pass", "Deal TODO for Deal ID from Deal Approval Dashboard Is Completed Successfully");
					} else if (DealTodoGetText.contains(DealId) && DealTodoGetText.contains("Deal is Approved") && TabName.equals("Deal_Dashboard")) {
						CF.FnTestCaseStatusReport("Pass", "Deal TODO for Deal Approved from Deal Dashboard Is Completed Successfully");
					} else {
						CF.FnTestCaseStatusReport("Fail", "Deal TODO for Deal ID from Deal Dashboard Is not Completed.");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyCustomerStatusOnPersonalhierarchy
	,Description          : Verify Approval status for PM on View And Edit Pricing
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyCustomerStatusOnPersonalhierarchy(String status) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyCustomerStatusOnPersonalhierarchy--<<<<<<<<<<<");

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(3000);
			String DealAllStatus = null;
			if (status.equals("PENDING FOR APPROVAL")) {
				DealAllStatus = "c1/images/priceitem_pending.png";
			} else if (status.equals("APPROVED")) {
				DealAllStatus = "c1/images/priceitem_appr.png";
			}


			String Deal_Information_PersonalHierarchyPersonName_Label_Path = DealManagementPageElements.Deal_Information_PersonalHierarchyPersonName_Label.replaceAll("ReplacePersonName", "CurrConv_Cust1Auto,IND");
			CF.FnWaitForElement(driver, 360, Deal_Information_PersonalHierarchyPersonName_Label_Path);

			Thread.sleep(2000);
			String GetStatusText =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_Customer_Status_On_Personalhierarchy1).getAttribute("src");
			System.out.println("inn:-" + CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Information_Customer_Status_On_Personalhierarchy1, "innerText"));
			System.out.println("GetStatusText:-" + GetStatusText + " || status:-" + status);
			if (GetStatusText.contains(DealAllStatus)) {
				CF.FnTestCaseStatusReport("Pass", "Verification of Personal hierarchy Customer Status Is Completed Successfully");
			} else {
				CF.FnTestCaseStatusReport("Fail", "Verification of Personal hierarchy Customer Status Is Not Completed");
			}


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}






	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyStatusOfPriceItemOnCommitment
	,Description          : To Verify Price item status on pricing and commitment screen
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyStatusOfPriceItemOnCommitment(String PriceItem, String PriceItemStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyStatusOfPriceItemOnCommitment--<<<<<<<<" + PriceItem);



		Boolean CheckDirect = false;
		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(1000);

			String DealAllStatus = null;
			if (PriceItemStatus.equals("PENDING FOR APPROVAL")) {
				DealAllStatus = "c1/images/priceitem_pending.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (PriceItemStatus.equals("APPROVED")) {
				DealAllStatus = "c1/images/priceitem_appr.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (PriceItemStatus.equals("ERROR")) {
				DealAllStatus = "c1/images/priceitem_error.png";
			} else if (PriceItemStatus.equals("APPROVER-RECOMMENDED")) {
				DealAllStatus = "c1/images/func_clipboard_16_act@2x.png";
			} else {
				CheckDirect = true;
			}


			Thread.sleep(1000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);


			if (CheckDirect == false) {
				// here we are comparing approval image from deal information with pricing screen price item shown image
				String GetStatusTextFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Pricing_And_Commitments_Filter_PriceItem_Get_Status_Image.replaceAll("ReplacePriceItem", PriceItem), "src");
				System.out.println("System Status:-" + GetStatusTextFromApplication + " || DealAllStatus:-" + DealAllStatus);
				if (GetStatusTextFromApplication.contains(DealAllStatus)) {
					CF.FnTestCaseStatusReport("Pass", "Verification of Price Item Status Is Completed Successfully");
				} else {
					CF.FnTestCaseStatusReport("Fail", "Verification of Price Item Status Not Completed");
				}
			}

			//			else if (CheckDirect == false) {
			//				String GetStatusTextFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Pricing_And_Commitments_Filter_PriceItem_Get_Status_Image, "title");
			//				if (GetStatusTextFromApplication.contains(PriceItemStatus)) {
			//					CF.FnTestCaseStatusReport("Pass", "Verification of Price Item Status Is Completed Successfully");
			//				} else {
			//					CF.FnTestCaseStatusReport("Fail", "Verification of Price Item Status Not Completed");
			//				}
			//			}

			//			FnNavigationToDealInformationFromPricingAndCommitments();

			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}



	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyStatusOfSeasonalPriceItemOnPricingAndCommitment
	,Description          : To Verify Price item status on pricing and commitment screen
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyStatusOfSeasonalPriceItemOnPricingAndCommitment(String PriceItem, String PriceItemStatus, int SeasonalPriceItemPositionNumber) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyStatusOfSeasonalPriceItemOnPricingAndCommitment--<<<<<<<<" + PriceItem);



		Boolean CheckDirect = false;
		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(1000);

			String DealAllStatus = null;
			if (PriceItemStatus.equals("PENDING FOR APPROVAL")) {
				DealAllStatus = "c1/images/priceitem_pending.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (PriceItemStatus.equals("APPROVED")) {
				DealAllStatus = "c1/images/priceitem_appr.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (PriceItemStatus.equals("ERROR")) {
				DealAllStatus = "c1/images/priceitem_error.png";
			} else if (PriceItemStatus.equals("APPROVER-RECOMMENDED")) {
				DealAllStatus = "c1/images/func_clipboard_16_act@2x.png";
			} else {
				CheckDirect = true;
			}


			Thread.sleep(1000);

			FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(PriceItem);

			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Pricing_and_Commitments_Level_Expand_Seasonal_Price_Item.replaceAll("ReplacePriceItem", PriceItem));

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);

			String PriceItemStatusFromApplicationPath = DealManagementPageElements.Deal_Information_Pricing_and_Commitments_Level_Seasonal_Price_Item_status;
			PriceItemStatusFromApplicationPath = PriceItemStatusFromApplicationPath.replaceAll("ReplacePriceItem", PriceItem);

			if (CheckDirect == false) {
				// here we are comparing approval image from deal information with pricing screen price item shown image
				String GetStatusTextFromApplication = CF.FnGetTextFromElement(driver, PriceItemStatusFromApplicationPath, "src");
				System.out.println("System Status:-" + GetStatusTextFromApplication + " || DealAllStatus:-" + DealAllStatus);
				if (GetStatusTextFromApplication.contains(DealAllStatus)) {
					CF.FnTestCaseStatusReport("Pass", "Verification of Price Item Status Is Completed Successfully");
				} else {
					CF.FnTestCaseStatusReport("Fail", "Verification of Price Item Status Not Completed");
				}
			} else if (CheckDirect == false) {
				String GetStatusTextFromApplication = CF.FnGetTextFromElement(driver, PriceItemStatusFromApplicationPath, "title");
				if (GetStatusTextFromApplication.contains(PriceItemStatus)) {
					CF.FnTestCaseStatusReport("Pass", "Verification of Price Item Status Is Completed Successfully");
				} else {
					CF.FnTestCaseStatusReport("Fail", "Verification of Price Item Status Not Completed");
				}
			}

			//			FnNavigationToDealInformationFromPricingAndCommitments();

			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}



	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE
	,Description          : To View and verify DealApprovalWorkflowForRecentTODOROLE
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE(String sStatus, String sRole, String sDealActionTakenStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE--<<<<<<<<<<" + sStatus);

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_DealStatus_Label).getText(), sStatus, "Deal status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			List < WebElement > AllRoles = driver.findElements(By.xpath(DealManagementPageElements.Deal_Approval_Workflow_ToDoRoleValue_Label));
			int LatestRecord = AllRoles.size();
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_ToDoRoleValue_Label + "[" + LatestRecord + "]").getText(), sRole, "To do role is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			assertEquals(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_ToDoRoleActionTakenValue_Label + "[" + LatestRecord + "]").getText(), sDealActionTakenStatus, "Action taken status is verified on deal Approval Workflow ");
			Thread.sleep(3000);
			CF.FnTestCaseStatusReport("Pass", "Approval Workflow Verification of Deal Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifydivisionFinancialSummaryStatus
	,Description          : To Verify division Financial Summary status
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifydivisionFinancialSummaryStatus(String DivisionStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifydivisionFinancialSummaryStatus--<<<<<<<<");

		try {
			CF.FnSetFrame(driver, "zoneMapFrame_1");

			Thread.sleep(3000);

			String DealAllStatus = null;

			if (DivisionStatus.equals("PENDING FOR APPROVAL")) {
				DealAllStatus = "c1/images/priceitem_pending.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (DivisionStatus.equals("APPROVED")) {
				DealAllStatus = "c1/images/priceitem_appr.png";
				System.out.println("src:-" + DealAllStatus);
			} else if (DivisionStatus.equals("ERROR")) {
				DealAllStatus = "c1/images/priceitem_error.png";
			}



			// here we are comparing approval image from deal information with pricing screen price item shown image
			String DivisionStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_division_Financial_Summary_Status).getAttribute("src");
			System.out.println("DivisionStatusFromApplication :-" + DivisionStatusFromApplication);
			if (DivisionStatusFromApplication.contains(DealAllStatus)) {
				CF.FnTestCaseStatusReport("Pass", "Verification of division Financial Summary status Is Completed Successfully");
			} else {
				CF.FnTestCaseStatusReport("Fail", "Verification of division Financial Summary status Not Completed");
			}


			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchDealIdAssignedToMyRole
	,Description          : To Search Deal Assigned User
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchDealIdAssignedToMyRole(String sDealId, Boolean UncheckDealAssignedToMyRolecheckbox, Boolean VerifyDealAssignedToMyRole, Boolean NavigateToDeal, String LoginUser) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchDealIdAssignedToMyRole--<<<<<<<<<<" + sDealId + " || AssignedToMyRole:-" + VerifyDealAssignedToMyRole);



		try {

			
//			Thread.sleep(5000);
//			CF.FnSetFrame(driver, "main");
//			Thread.sleep(10000);
//
//			WebElement scroll = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Search_Deal_Section);
//			CF.FnScrollToElement(driver, scroll);
//			Thread.sleep(5000);
//			CF.FnElementClick(driver, DealManagementPageElements.Search_Deal_Section);
//			Thread.sleep(5000);
			
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "tabPage");
			Thread.sleep(10000);
			System.out.println("get");
			
			int size = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Search_Deal_Expand_Filter_Button).size();
			if (size > 0 ) {
				if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Search_Deal_Expand_Filter_Button+"["+size+"]", "value").contains("Expand")) {
					CF.FnElementClick(driver, DealManagementPageElements.Search_Deal_Expand_Filter_Button+"["+size+"]");
					Thread.sleep(2000);
				}
			} else {
				System.out.println("Deal Search Window are already Expanded");
			}


			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox);
			Thread.sleep(1000);
			CF.FnSetText(driver, DealManagementPageElements.Deal_Approval_Dashboard_DealId_TextBox, sDealId);
			Thread.sleep(2000);
			if (!LoginUser.equals("RMBK1")) {
				if (UncheckDealAssignedToMyRolecheckbox.equals(true)) {
					if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Dashboard_Search_Assign_To_My_Role).isSelected()) {
						CF.FnElementClick(driver, DealManagementPageElements.Deal_Dashboard_Search_Assign_To_My_Role);
					}
				}
			}
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Dashboard_SearchDealLower_Button1);
			Thread.sleep(3000);
			if (VerifyDealAssignedToMyRole.equals(true)) {
				String searchResult = DealManagementPageElements.Deal_Dashboard_Search_Deal_Result_Record.replaceAll("ReplaceDealId", sDealId);

				System.out.println("searchResult:" + searchResult);

				if (CF.FnGetWebElement(driver, "XPATH", searchResult).isDisplayed()) {
					CF.FnTestCaseStatusReport("Pass", "Verified Deal Assigned to My Role from Deal Dashboard Is Completed Successfully");
				}


			}

			if (NavigateToDeal.equals(true)) {
				CF.FnElementClick(driver, DealManagementPageElements.Deal_Approval_Dashboard_FirstDealForNavigationToDealInformation_Link);
				CF.FnTestCaseStatusReport("Pass", "Deal Search for Deal ID from Deal Dashboard Is Completed Successfully");
			}
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}






	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnViewAndverifyDealApprovalWorkflowApprovalHistory
	,Description          : To Verify Deal Workflow ApprovalHistory 
	'###############################################################################################################################################################################################################################################*/
	public void FnViewAndverifyDealApprovalWorkflowApprovalHistory(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnViewAndverifyDealApprovalWorkflowApprovalHistory--<<<<<<<<" + iStartingRow);


		String sSequence, sProcess, sApprovalType, sDivision, sProduct, sApprovalLevel, sToDoRole, sUserName, sActionTaken, sCreateDateTime, sUpdateDateTime = null;

		sSequence = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProcess = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sApprovalType = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sDivision = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProduct = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sApprovalLevel = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sToDoRole = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sUserName = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		sActionTaken = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		sCreateDateTime = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		sUpdateDateTime = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || ApprovalHistory || sSequence:-" + sSequence + " || sProcess:-" + sProcess + " || sApprovalType:-" + sApprovalType + " || sDivision:-" + sDivision + " || sApprovalLevel:-" + sApprovalLevel + " || sToDoRole:-" + sToDoRole + " || sUserName:-" + sUserName + " || sActionTaken:-" + sActionTaken + " || sCreateDateTime:-" + sCreateDateTime + " || sUpdateDateTime:-" + sUpdateDateTime);

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;

			List < WebElement > allRecordOnTable = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Approval_Workflow_Approval_History_Table);

			int ApprovalsrecordLen = allRecordOnTable.size();
			System.out.println("ApprovalsrecordLen:-" + ApprovalsrecordLen);

			String sSequenceFromApplication, sProcessFromApplication, sApprovalTypeFromApplication, sDivisionFromApplication, sApprovalLevelFromApplication, sProductFromApplication, sToDoRoleFromApplication, sUserNameFromApplication, sActionTakenFromApplication, sCreateDateTimeFromApplication, sUpdateDateTimeFromApplication = null;

			for (int SingleRecord = 1; SingleRecord <= ApprovalsrecordLen; SingleRecord++) {

				WebElement SingleWebElement = driver.findElement(By.xpath(DealManagementPageElements.Deal_Approval_Workflow_Approval_History_Table + "[" + SingleRecord + "]"));

				
				sSequenceFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sSequenceApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sProcessFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sProcessApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sApprovalTypeFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sApprovalTypeApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sDivisionFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sDivisionApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sProductFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sProductApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sApprovalLevelFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sApprovalLevelApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sToDoRoleFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sToDoRoleApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sUserNameFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sUserNameApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sActionTakenFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sActionTakenApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sCreateDateTimeFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sCreateDateTimeApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");
				sUpdateDateTimeFromApplication =  CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.sUpdateDateTimeApprovalHistoryPath + "[" + SingleRecord + "]").getAttribute("innerText");


				sCreateDateTimeFromApplication = sCreateDateTimeFromApplication.split("\\ ")[0];
				sUpdateDateTimeFromApplication = sUpdateDateTimeFromApplication.split("\\ ")[0];

				if (sSequence.equals(sSequenceFromApplication)) {
					System.out.println(iStartingRow + " || sSequence:- " + sSequence + " || sSequenceFromApplication:-" + sSequenceFromApplication);
					if (!sSequence.equalsIgnoreCase("NoValue")) {
						assertEquals(sSequence, sSequenceFromApplication);
					}
					System.out.println(iStartingRow + " || sProcess:- " + sProcess + " || sProcessFromApplication:-" + sProcessFromApplication);
					if (!sProcess.equalsIgnoreCase("NoValue")) {
						assertEquals(sProcess, sProcessFromApplication);
					}
					System.out.println(iStartingRow + " || sApprovalType:- " + sApprovalType + " || sApprovalTypeFromApplication:-" + sApprovalTypeFromApplication);
					if (!sApprovalType.equalsIgnoreCase("NoValue")) {
						assertEquals(sApprovalType, sApprovalTypeFromApplication);
					}
					System.out.println("sDivision:- " + sDivision + " || sDivisionFromApplication:-" + sDivisionFromApplication);
					if (!sDivision.equalsIgnoreCase("NoValue")) {
						assertEquals(sDivision, sDivisionFromApplication);
					}
					System.out.println(iStartingRow + " || sApprovalLevel:- " + sApprovalLevel + " || sApprovalLevelFromApplication:-" + sApprovalLevelFromApplication);
					if (!sApprovalLevel.equalsIgnoreCase("NoValue")) {
						assertEquals(sApprovalLevel, sApprovalLevelFromApplication);
					}
					System.out.println(iStartingRow + " || sProduct:- " + sProduct + " || sProductFromApplication:-" + sProductFromApplication);
					if (!sProduct.equalsIgnoreCase("NoValue")) {
						//		        assertEquals(sProduct,sProductFromApplication);	 //getting empty
						if (!sProductFromApplication.equals(sProduct)) {
							System.err.println(iStartingRow + " || sProduct:- " + sProduct + " || sProductFromApplication:-" + sProductFromApplication);
						}
					}
					System.out.println(iStartingRow + " || sToDoRole:- " + sToDoRole + " || sToDoRoleFromApplication:-" + sToDoRoleFromApplication);
					if (!sToDoRole.equalsIgnoreCase("NoValue")) {
						assertEquals(sToDoRole, sToDoRoleFromApplication);
					}
					System.out.println(iStartingRow + " || sUserName:- " + sUserName + " || sUserNameFromApplication:-" + sUserNameFromApplication);
					if (sUserName.trim().equals("")) {
						sUserName = "NoValue";
					}
					if (!sUserName.trim().equalsIgnoreCase("NoValue")) {
						assertEquals(sUserName, sUserNameFromApplication);
					}
					System.out.println(iStartingRow + " || sActionTaken:- " + sActionTaken + " || sActionTakenFromApplication:-" + sActionTakenFromApplication);
					if (!sActionTaken.equalsIgnoreCase("NoValue")) {
						assertEquals(sActionTaken, sActionTakenFromApplication);
					}
					System.out.println(iStartingRow + " || sCreateDateTime:- " + sCreateDateTime + " || sCreateDateTimeFromApplication:-" + sCreateDateTimeFromApplication);
					if (!sCreateDateTime.equalsIgnoreCase("NoValue")) {
						//		        assertEquals(sCreateDateTime,sCreateDateTimeFromApplication);	
						if (!sCreateDateTimeFromApplication.equals(sCreateDateTime)) {
							System.err.println(iStartingRow + " || sCreateDateTime:- " + sCreateDateTime + " || sCreateDateTimeFromApplication:-" + sCreateDateTimeFromApplication);
						}
					}
					System.out.println(iStartingRow + " || sUpdateDateTime:- " + sUpdateDateTime + " || sUpdateDateTimeFromApplication:-" + sUpdateDateTimeFromApplication);
					if (!sUpdateDateTime.equalsIgnoreCase("NoValue")) {
						//		        assertEquals(sUpdateDateTime,sUpdateDateTimeFromApplication);	
						if (!sUpdateDateTimeFromApplication.equals(sUpdateDateTime)) {
							System.err.println(iStartingRow + " || sUpdateDateTime:- " + sUpdateDateTime + " || sUpdateDateTimeFromApplication:-" + sUpdateDateTimeFromApplication);
						}
					}

					Thread.sleep(1000);

					CF.FnTestCaseStatusReport("Pass", "Workflow Approval History Verified Successfully");

					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}


	}







	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndRecommendPriceForSinglePriceItem
	,Description          : To Search prize items and recommend prize
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndRecommendPriceForSinglePriceItem(String sPrizeItem1, String sValueOfsPrizeItem1) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchAndRecommendPriceForSinglePriceItem--<<<<<<<<<<" + sPrizeItem1 + " : " + sValueOfsPrizeItem1);

		try {
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Recommended_Rate_Input_TextBox_On_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", sPrizeItem1));
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(10000);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox);
			Thread.sleep(2000);
			CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox).sendKeys(sValueOfsPrizeItem1);
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Recommended_Pricing_Recommend_Button);
			Thread.sleep(10000);

			CF.FnTestCaseStatusReport("Pass", "Search Prize Items and Recommend Prize Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}






	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyDealInformationDealStatusAndDealVersionStatus
	,Description          : To Verify Deal Information DealStatus And Deal Version Status
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyDealInformationDealStatusAndDealVersionStatus(String DealStatus, String DealVersionStatus) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyDealInformationDealStatusAndDealVersionStatus--<<<<<<<<");
		System.out.println("DealStatus:-" + DealStatus + " || DealVersionStatus:-" + DealVersionStatus);
		try {
			Thread.sleep(1000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(2000);

			if (!DealStatus.equals("") && !DealStatus.equals(null)) {
				//				String DealStatusFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Information_Deal_Status, "innerText");
				String DealStatusFromApplication = CF.FnGetText(driver, DealManagementPageElements.Deal_Information_Deal_Status);
				System.out.println("System DealStatus:-" + DealStatus + " || DealStatusFromApplication:-" + DealStatusFromApplication);
				if (DealStatus.equals(DealStatusFromApplication)) {
					assertEquals(DealStatus, DealStatusFromApplication);
				}
			}

			if (!DealVersionStatus.equals("") && !DealVersionStatus.equals(null)) {
				//				String DealVersionStatusFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.Deal_Information_Deal_Version_Status, "innerText");
				String DealVersionStatusFromApplication = CF.FnGetText(driver, DealManagementPageElements.Deal_Information_Deal_Version_Status);
				System.out.println("System DealVersionStatus:-" + DealVersionStatus + " || DealVersionStatusFromApplication:-" + DealVersionStatusFromApplication);
				if (DealVersionStatus.equals(DealVersionStatusFromApplication)) {
					assertEquals(DealVersionStatus, DealVersionStatusFromApplication);
				}
			}

			CF.FnTestCaseStatusReport("Pass", "Deal Status & Deal Version Status Verified Successfully");

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPriceItemApproverOnPricingAndCommitment
	,Description          : To Verify Price item status on pricing and commitment screen
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPriceItemApproverOnPricingAndCommitment(String PriceItem, String Approver) throws Exception {
		System.out.println(">>>>>>>>>>--FnVerifyPriceItemApproverOnPricingAndCommitment--<<<<<<<<" + PriceItem + Approver);


		try {

			Thread.sleep(1000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);


			String approvalPath = DealManagementPageElements.Deal_Information_Pricing_and_Commitments_Level_Price_Item_Approval.replaceAll("ReplacePriceItem", PriceItem);
			String GetTextFromApplication = CF.FnGetTextFromElement(driver, approvalPath, "innerText");
			System.out.println("System Status:-" + GetTextFromApplication + " || Approver:-" + Approver);
			if (GetTextFromApplication.contains(Approver)) {
				CF.FnTestCaseStatusReport("Pass", "Verification of Price Item Approver Is Completed Successfully");
			} else {
				CF.FnTestCaseStatusReport("Fail", "Verification of Price Item Approver Not Completed");
			}


			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}



	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndEnrollProduct
	,Description          : Search and Enroll Propose Product through Product Enrollment UI
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndValidateEnrolledProduct(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnSearchAndValidateEnrolledProduct--<<<<<<<<<<" + iStartingRow);

		String dateName = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
		String sProductCode, sProductDescription, sProductVersionDescription, sProductType, sProductCategory, sProductPriority, sProductStartDate, sProductEndDate = null;

		sProductCode = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sProductDescription = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sProductVersionDescription = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sProductType = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sProductCategory = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		sProductPriority = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sProductStartDate = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		sProductEndDate = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();


		System.out.println(iStartingRow + " || sProductCode:-" + sProductCode + " || sProductDescription:-" + sProductDescription + " || sProductVersionDescription:-" + sProductVersionDescription + " || sProductType:-" + sProductType + " || sProductCategory:-" + sProductCategory + " || sProductPriority:-" + sProductPriority + " || sProductStartDate:-" + sProductStartDate + " || sProductEndDate:-" + sProductEndDate);

		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;

			String sEnrolledProductCode, sEnrolledProductDescription, sEnrolledProductVersionDescription, sEnrolledProductType, sEnrolledProductCategory, sEnrolledProductPriority, sEnrolledProductStartDate, sEnrolledProductEndDate = null;


			WebElement EnrolledProductList = driver.findElement(By.xpath(DealManagementPageElements.Product_Enrollment_List_Enrolled_Product));


			sEnrolledProductCode = (String) js.executeScript("return arguments[0].children[0].cells[4].innerText;", EnrolledProductList);
			sEnrolledProductDescription = (String) js.executeScript("return arguments[0].children[0].cells[5].innerText;", EnrolledProductList);
			sEnrolledProductVersionDescription = (String) js.executeScript("return arguments[0].children[0].cells[6].innerText;", EnrolledProductList);
			sEnrolledProductType = (String) js.executeScript("return arguments[0].children[0].cells[7].innerText;", EnrolledProductList);
			sEnrolledProductCategory = (String) js.executeScript("return arguments[0].children[0].cells[8].innerText;", EnrolledProductList);
			sEnrolledProductPriority = (String) js.executeScript("return arguments[0].children[0].cells[9].innerText;", EnrolledProductList);
			sEnrolledProductStartDate = (String) js.executeScript("return arguments[0].children[0].cells[10].innerText;", EnrolledProductList);
			sEnrolledProductEndDate = (String) js.executeScript("return arguments[0].children[0].cells[11].innerText;", EnrolledProductList);

			System.out.println(iStartingRow + " || sProductCode:- " + sProductCode + " || sEnrolledProductCode:-" + sEnrolledProductCode);
			if (!sProductCode.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductCode, sEnrolledProductCode);
			}
			System.out.println(iStartingRow + " || sProductDescription:- " + sProductDescription + " || sEnrolledProductDescription:-" + sEnrolledProductDescription);
			if (!sProductDescription.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductDescription, sEnrolledProductDescription);
			}
			System.out.println(iStartingRow + " || sProductVersionDescription:- " + sProductVersionDescription + " || sEnrolledProductVersionDescription:-" + sEnrolledProductVersionDescription);
			if (!sProductVersionDescription.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductVersionDescription, sEnrolledProductVersionDescription);
			}
			System.out.println(iStartingRow + " || sProductType:- " + sProductType + " || sEnrolledProductType:-" + sEnrolledProductType);
			if (!sProductType.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductType, sEnrolledProductType);
			}
			System.out.println(iStartingRow + " || sProductCategory:- " + sProductCategory + " || sEnrolledProductCategory:-" + sEnrolledProductCategory);
			if (!sProductCategory.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductCategory, sEnrolledProductCategory);
			}
			System.out.println(iStartingRow + " || sProductPriority:- " + sProductPriority + " || sEnrolledProductPriority:-" + sEnrolledProductPriority);
			if (!sProductPriority.equalsIgnoreCase("NoValue")) {
				assertEquals(sProductPriority, sEnrolledProductPriority);
			}
			System.out.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sEnrolledProductStartDate:-" + sEnrolledProductStartDate);
			if (!sProductStartDate.equalsIgnoreCase("NoValue")) {
				sEnrolledProductStartDate = sEnrolledProductStartDate.replaceAll("\\?", "-"); //01?01?2020
				if (!sEnrolledProductStartDate.equals(sProductStartDate)) {
					System.err.println(iStartingRow + " || sProductStartDate:- " + sProductStartDate + " || sEnrolledProductStartDate:-" + sEnrolledProductStartDate);
				}
				//				assertEquals(sProductStartDate,sEnrolledProductStartDate);	

			}
			System.out.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sEnrolledProductEndDate:-" + sEnrolledProductEndDate);
			if (!sProductEndDate.equalsIgnoreCase("NoValue")) {
				if (!sEnrolledProductEndDate.equals(sProductEndDate)) {
					System.err.println(iStartingRow + " || sProductEndDate:- " + sProductEndDate + " || sEnrolledProductEndDate:-" + sEnrolledProductEndDate);
				}
				assertEquals(sProductEndDate, sEnrolledProductEndDate);
			}



			Thread.sleep(3000);
			CF.FnTestCaseStatusReport("Pass", "Search And Validating Enrolled Product Is Completed Successfully");




		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}


	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : getNumberFromString
	,Description          : To Get Number From String
	'###############################################################################################################################################################################################################################################*/

	public String getNumberFromString(String NumberValue) throws Exception {
		String NewValue = "";
		System.out.println("NumberValue.equals(\"\")" + !NumberValue.equals(""));
		System.out.println("null:-" + !NumberValue.equals(null));
		if (!NumberValue.equals("") || !NumberValue.equals(null) || !NumberValue.equals("NoValue")) {
			NewValue = NumberValue.replaceAll("[^\\d.]", "");
		}
		return NewValue;
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : getIntegerNumberFromFloatString
	,Description          : To Get Integer Value i.e remove all string value after dot i,e (123.11 => 123)
	'###############################################################################################################################################################################################################################################*/
	public String getIntegerNumberFromFloatString(String NumberValue) throws Exception {
		String NewValue = "NoValue";

		System.out.println("NumberValue null:-" + !NumberValue.equals(null));
		if (!NumberValue.equals("") || !NumberValue.equals(null) || !NumberValue.equals("NoValue")) {
			String[] removedDigitAfterDot = NumberValue.split("\\.");
			NewValue = removedDigitAfterDot[0];
			return NewValue;
		} else {
			return NumberValue;
		}
	}



	///==================================== 13/10/2022 ====================================//




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen--<<<<<<<<" + iStartingRow);

		String sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, VerifyValidationMessage, sPreviousPriceItem;

		sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sVerifyPriceItemRate = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sUpdatePriceItemRate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		VerifyValidationMessage = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sPreviousPriceItem = CF.FnGetCellValue((iStartingRow - 1), 1, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sPriceItem:-" + sPriceItem + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate + " || sUpdatePriceItemRate:-" + sUpdatePriceItemRate + " || VerifyValidationMessage:-" + VerifyValidationMessage);

		try {

			if (!sPriceItem.equalsIgnoreCase("NoValue")) {

				if (!sPreviousPriceItem.equals(sPriceItem)) {
					//To Filter & Search Price Item on Pricing & Commitment Screen
					FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(sPriceItem);
				}

				// Function To Update and verify price Item rate and check validation for incorrect input On "Pricing & Commitment" screen UI
				FnUpdateAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, VerifyValidationMessage);


			}



		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen
	,Description          : To Filter & Search Price Item on Pricing & Commitment Screen
	'###############################################################################################################################################################################################################################################*/
	public void FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(String PriceItemName) throws Exception {
		System.out.println(">>>>>>>>>>--FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen--<<<<<<<<<<<<<" + PriceItemName);

		try {


			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);

			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(1000);

			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(500);
			}

			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(500);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(500);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, PriceItemName);
			Thread.sleep(500);

			String sPriceItemFromApplication = DealManagementPageElements.Deal_PriceItem_Select_On_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", PriceItemName);
			CF.FnWaitForElement(driver, 360, sPriceItemFromApplication);

			String FilteredPriceItem = CF.FnGetWebElement(driver, "XPATH", sPriceItemFromApplication + "[1]").getAttribute("innerText");

			if (FilteredPriceItem.contains(PriceItemName)) {
				CF.FnTestCaseStatusReport("Pass", "Price Item :- " + PriceItemName + " Filter Searched Successfully.");
			} else {
				CF.FnTestCaseStatusReport("Fail", "Price Item :- " + PriceItemName + " Filter not Found in Search.");
			}

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdateAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen
	,Description          : To Update and verify price Item rate and check validation for incorrect input
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdateAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(String sPriceItem, String sVerifyPriceItemRate, String sUpdatePriceItemRate, String VerifyValidationMessage) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdateAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen--<<<<<<<<<<<<<" + sPriceItem + " = Verify Rate " + sVerifyPriceItemRate +" = " + sUpdatePriceItemRate);

		try {
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);

			if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {
				System.out.println("var error");
				CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
				Thread.sleep(1000);

			} else {
				System.out.println("var error not");
			}

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);
						
			if(!sVerifyPriceItemRate.equalsIgnoreCase("NoValue")) {
				
				String SystemPriceItemRate = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Rate_Input_TextBox_On_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", sPriceItem)).getAttribute("value");
				if(SystemPriceItemRate.equals(sVerifyPriceItemRate)) {
					CF.FnTestCaseStatusReport("Pass", "Pricing Verified for Price Item  :- " + sPriceItem + " Successfully");
				}
			}

			
			if(!sUpdatePriceItemRate.equalsIgnoreCase("NoValue")) {
				
				

			String PriceItemRateTextBox = DealManagementPageElements.Deal_PriceItem_Rate_Input_TextBox_On_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", sPriceItem);

			System.out.println("sizeeeee - " + sPriceItem + " : " + PriceItemRateTextBox + CF.getListWebElements(driver, "XPATH", PriceItemRateTextBox).size());

			if (CF.getListWebElements(driver, "XPATH", PriceItemRateTextBox).size() != 0) {

				System.out.println("sizeeeee 111 - " + sPriceItem + " : " + PriceItemRateTextBox);

				CF.FnWaitForElement(driver, 360, PriceItemRateTextBox);
//					CF.FnElementClick(driver, PriceItemRateTextBox);
				Thread.sleep(500);
				CF.FnClearTextFieldValue(driver, PriceItemRateTextBox);
				Thread.sleep(100);

				WebDriverWait w = new WebDriverWait(driver, 60);

				try {
					//alertIsPresent() condition applied
					if (w.until(ExpectedConditions.alertIsPresent()) != null) {
						System.out.println("Alert exists");
						driver.switchTo().alert().dismiss();
					}
					CF.FnSetFrame(driver, "uiMap");
					Thread.sleep(1000);
					CF.FnWaitForElement(driver, 60, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
				} catch (Exception e) {
					System.out.println("######");
				}

				try {
					CF.FnGetWebElement(driver, "XPATH", PriceItemRateTextBox).sendKeys(sUpdatePriceItemRate);
					Thread.sleep(200);
					CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);

					for (int alertsCount = 1; alertsCount <= 3; alertsCount++) { //throwing 2-3 alert messages

						if (w.until(ExpectedConditions.alertIsPresent()) != null) {
							String AlertWarningMessageFromApplication = driver.switchTo().alert().getText();
							System.out.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
							if (AlertWarningMessageFromApplication.contains(VerifyValidationMessage)) {
								if (alertsCount == 1) {
									assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));
									System.out.println("passed");
									driver.switchTo().alert().dismiss();
									CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e " + VerifyValidationMessage + " for input - " + sUpdatePriceItemRate);
								}

							} else {
								System.err.println(alertsCount + " || " + sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
								// assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));

								// two validation on alert i.e numeric and range
							}
							System.out.println("Alert Exists - dismissed");
							Thread.sleep(500);
							if (w.until(ExpectedConditions.alertIsPresent()) != null) {

								if (AlertWarningMessageFromApplication.contains("Please enter the rate value between Floor")) {

									System.out.println("passed 11");
									driver.switchTo().alert().dismiss();
									CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e " + AlertWarningMessageFromApplication + " for Rate:-" + sUpdatePriceItemRate + " on Pricing Screen");

								}

							} else if (w.until(ExpectedConditions.alertIsPresent()) == null) {
								break;
							}
							Thread.sleep(500);

						}
					}
				} catch (Exception er) {}

				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(1000);

				System.out.println("444444444");

				if (driver.findElements(By.xpath(DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen)).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {

					System.out.println("display:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed());
					System.out.println("en:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isEnabled());
					System.out.println("select:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isSelected());

					System.out.println("innnn");
					String ErrorValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

					Thread.sleep(1000);
					CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);

					System.out.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);

					if (VerifyValidationMessage.contains("Only numeric values are allowed")) {
						System.err.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
					} else if (!ErrorValidationMessageFromApplication.equals(VerifyValidationMessage)) {
						System.err.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
						assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));
					} else {
						//  CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Validation Verification Is Completed Successfully on Pricing & commitment screen.");
						assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));
					}


				} else {
					System.out.println("elseeeeeee");
					CF.FnTestCaseStatusReport("Pass", "Price Item :- " + sPriceItem + " Rate Updated Successfully as " + sUpdatePriceItemRate);
				}



			} else {
				System.out.println("Price Item Rate TextBox not Visible as Intended for :-" + sPriceItem);
				if (sUpdatePriceItemRate.equals("NoValue")) {
					if (!sUpdatePriceItemRate.equals("")) {
						CF.FnTestCaseStatusReport("Pass", "Rate Textbox Does not Exists for Price Item :-" + sPriceItem + " as intended on Pricing & commitment screen.");
					}
				}
			}


				
				
		}




			Thread.sleep(2000);






		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPriceItemValidationOnPricingScreen
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPriceItemValidationOnPricingScreen(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnVerifyPriceItemValidationOnPricingScreen--<<<<<<<<" + iStartingRow);


		String sPriceItem, PriceItemParameters, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, sPreviousPriceItem;

		sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		PriceItemParameters = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sVerifyPriceItemRate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sUpdatePriceItemRate = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		RateTextBoxRowNumber = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		VerifyValidationMessage = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sPreviousPriceItem = CF.FnGetCellValue((iStartingRow - 1), 1, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sPriceItem:-" + sPriceItem + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate + " || sUpdatePriceItemRate:-" + sUpdatePriceItemRate + " || VerifyValidationMessage:-" + VerifyValidationMessage);

		try {

			if (!sPriceItem.equals("NoValue")) {

				if (!sPreviousPriceItem.equals(sPriceItem)) {
					//To Filter & Search Price Item on Pricing & Commitment Screen
					FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(sPriceItem);

					// To Navigate to Pricing screen From "Pricing & Commitment" screen ui required "PriceItem and Parameter" if have Parameter
					FnNavigationToPricingScreenFromPricingAndCommitmentScreen(sPriceItem, PriceItemParameters);
				}



				if (VerifyValidationMessage.contains("User can not propose Pricing as the Price Item is non- negotiable")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, false);

					// To Navigate to Pricing and commitment screen from pricing screen either it will be "back" or "save" operation
					FnNavigationToPricingAndCommitmentScreenFromPricingScreen("back");

				} else if (VerifyValidationMessage.equals("NoValue") || VerifyValidationMessage.equals("")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, false);

					// To Navigate to Pricing and commitment screen from pricing screen either it will be "back" or "save" operation
					FnNavigationToPricingAndCommitmentScreenFromPricingScreen("save");

					CF.FnTestCaseStatusReport("Pass", "Price Item :- " + sPriceItem + " Rate Successfully Updated.");


				} else if (VerifyValidationMessage.contains("Please enter the rate value between Floor")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, true);

				}



			}



		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}






	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen
	,Description          : To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
	'###############################################################################################################################################################################################################################################*/
	public void FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(String sPriceItem, String sVerifyPriceItemRate, String sUpdatePriceItemRate, String RowNumber, String VerifyValidationMessage, Boolean IsRequiredInternalSave) throws Exception {
		System.out.println(">>>>>>>>>>--FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen--<<<<<<<<<<<<<" + sPriceItem + " = " + sUpdatePriceItemRate + " || Row:-" + RowNumber);

		try {

			//================================================================//

			Thread.sleep(500);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			Thread.sleep(2000);


			Boolean IsRateTextBoxVisible = false;
			Boolean IsOverrideCheckBoxVisible = false;
			Boolean DoHaveFixedValidationMessage = false; //Price Item Might not be visible
			Boolean DoHaveVariableErrorValidationMessage = false; //Price Item Will have error Validate Like Range for Rate for LoggedIn User


			String RateTextBoxPath = null;
			if (RowNumber.equals("NoValue") || RowNumber.equals("")) {
				RateTextBoxPath = DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox;
			} else {
				if (RowNumber.equals("1")) {
					RateTextBoxPath = DealManagementPageElements.Pricing_And_Commitments_Rate1_TextBox;
				} else if (RowNumber.equals("2")) {
					RateTextBoxPath = DealManagementPageElements.Pricing_And_Commitments_Rate2_TextBox;
				} else if (RowNumber.equals("3")) {
					RateTextBoxPath = DealManagementPageElements.Pricing_And_Commitments_Rate3_TextBox;
				}

			}

			Thread.sleep(500);


			System.out.println("Path :- " + RateTextBoxPath);
			System.out.println("CF.getListWebElements(driver, \"XPATH\", RateTextBoxPath).size() :->" + CF.getListWebElements(driver, "XPATH", RateTextBoxPath).size());
			//System.out.println("en:-" + CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isEnabled());
			//System.out.println("select:-" + CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isSelected());
			//System.out.println("display:-" + CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isDisplayed());

			if (CF.getListWebElements(driver, "XPATH", RateTextBoxPath).size() > 0 && CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isDisplayed()) {

				if (CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isDisplayed()) {

					IsRateTextBoxVisible = true;

					System.out.println("dis:-" + CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).isDisplayed());

					CF.FnElementClick(driver, RateTextBoxPath);
					Thread.sleep(200);
					String PriceItemRateFromApplication = CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).getAttribute("value");
					CF.FnClearTextFieldValue(driver, RateTextBoxPath);
					Thread.sleep(500);
					CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).sendKeys(sUpdatePriceItemRate);
					Thread.sleep(500);
					//				    	CF.FnMoveToElementAndClick(driver, RateTextBoxPath);
					//					    Thread.sleep(500);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Pricing_screen_Display_Sequense);

					if (!sVerifyPriceItemRate.equals("NoValue")) {
						System.out.println("PriceItemRateFromApplication:-" + PriceItemRateFromApplication + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate);
						if (PriceItemRateFromApplication.contains(sVerifyPriceItemRate) && (!sVerifyPriceItemRate.equals(""))) {
							assertTrue(PriceItemRateFromApplication.contains(sVerifyPriceItemRate));
							CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Rate Verified Successfully.");
						} else if (!sVerifyPriceItemRate.equals("")) {
							System.err.println("PriceItemRateFromApplication:-" + PriceItemRateFromApplication + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate);
							assertTrue(PriceItemRateFromApplication.contains(sVerifyPriceItemRate));
							CF.FnTestCaseStatusReport("Fail", "Price Item :-" + sPriceItem + " Rate Does Not Match.");
						}
					} else {
						System.out.println("&&&&&&&&&&&&&&&&&&&&");
					}

					////boolean hasAlert = ExpectedConditions.alertIsPresent().apply(driver) != null;

					//				    	Thread.sleep(500);
					for (int alertss = 1; alertss <= 3; alertss++) {
						try {
							WebDriverWait w = new WebDriverWait(driver, 60);

							if (w.until(ExpectedConditions.alertIsPresent()) != null) {
								System.out.println("alert-->>");
								String AlertWarningMessageFromApplication = driver.switchTo().alert().getText();

								System.out.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);

								driver.switchTo().alert().dismiss();

								if (AlertWarningMessageFromApplication.contains(VerifyValidationMessage)) {

									assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));

									System.out.println("passed");

									CF.FnClearTextFieldValue(driver, RateTextBoxPath);
									Thread.sleep(500);
									CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).sendKeys(sVerifyPriceItemRate);


									CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e 'Only numeric values are allowed.' for input - " + sUpdatePriceItemRate);
								} else {
									System.err.println(sPriceItem + "  || Alert WarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
									CF.FnTestCaseStatusReport("Fail", "Price Item :-" + sPriceItem + " Throwing Not Expected Alert i.e 'Only numeric values are allowed.' for input - " + sUpdatePriceItemRate);

								}

								Thread.sleep(2000);
								try {
									if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {
										System.out.println(sPriceItem + " var error on Pricing screen");
										CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
										Thread.sleep(2000);
									}
								} catch (Exception er) {
									System.out.println("Alert Exists111 " + er);

								}
								System.out.println("Alert Exists - dismissed");


							} else {
								System.out.println("not exists alert-->>");
								break;
							}

							boolean PriceItemRateTemp = sUpdatePriceItemRate.matches("[a-zA-Z]+");
							if (PriceItemRateTemp == true) {
								CF.FnElementClick(driver, RateTextBoxPath);
								Thread.sleep(500);
								CF.FnClearTextFieldValue(driver, RateTextBoxPath);
								Thread.sleep(500);
							}

							break;

						} catch (Exception ez) {

							boolean PriceItemRateTemp = sUpdatePriceItemRate.matches("[a-zA-Z]+");
							if (PriceItemRateTemp == false) {
								System.out.println("Price Item Rate:-" + sPriceItem + " = " + sUpdatePriceItemRate);
								break;
							} else {
								System.err.println("Price Item Rate:-" + sPriceItem + " = " + sUpdatePriceItemRate);
							}

							System.out.println("catch exception  :- " + ez);
						}
					}

				}

			} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).size() != 0 && IsRateTextBoxVisible == false && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).isDisplayed()) {

				IsOverrideCheckBoxVisible = true;

				System.out.println("override display:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).isDisplayed());
				System.out.println("Override Enabled:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).isEnabled());
				System.out.println("Override selected:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).isSelected());




				if (CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox).isSelected() == false) {
					CF.FnSetFrame(driver, "uiMap");
					Thread.sleep(500);
					CF.FnSelectCheckBox(driver, DealManagementPageElements.Pricing_And_Commitments_Override_Checkbox);
					Thread.sleep(1000);
				} else {
					System.out.println("checked----@@");
				}

				CF.FnElementClick(driver, RateTextBoxPath);
				Thread.sleep(500);
				String OldRate = CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).getAttribute("value");
				CF.FnClearTextFieldValue(driver, RateTextBoxPath);
				Thread.sleep(500);
				CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).sendKeys(sUpdatePriceItemRate);
				Thread.sleep(1000);
				CF.FnMoveToElementAndClick(driver, RateTextBoxPath);
				Thread.sleep(500);

				System.out.println("OldRate:-" + OldRate + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate);

				if (!sVerifyPriceItemRate.equals("NoValue")) {
					if (OldRate.equals(sVerifyPriceItemRate) && !sVerifyPriceItemRate.equals("")) {
						assertTrue(OldRate.contains(sVerifyPriceItemRate));
						CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Rate Verified Successfully.");
					} else if (!sVerifyPriceItemRate.equals("")) {
						System.err.println("OldRate:-" + OldRate + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate);
						//								CF.FnTestCaseStatusReport("Fail", "Price Item :-" + sPriceItem + " Rate Does Not Verified.");
						assertTrue(OldRate.contains(sVerifyPriceItemRate));
					}
				}
				try {
					WebDriverWait w = new WebDriverWait(driver, 60);

					if (w.until(ExpectedConditions.alertIsPresent()) != null) {
						String AlertWarningMessageFromApplication = driver.switchTo().alert().getText();
						System.out.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
						driver.switchTo().alert().dismiss();

						if (AlertWarningMessageFromApplication.contains(VerifyValidationMessage)) {
							System.out.println("passed***");
							CF.FnClearTextFieldValue(driver, RateTextBoxPath);
							Thread.sleep(500);

							if (NumberUtils.isParsable(OldRate)) {
								System.out.println("String is numeric!");
								CF.FnGetWebElement(driver, "XPATH", RateTextBoxPath).sendKeys(OldRate);
								Thread.sleep(1000);
							}

							assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));

							CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e 'Only numeric values are allowed.' for input - " + sUpdatePriceItemRate);
						} else {
							System.err.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
							assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));
						}

						Thread.sleep(500);

						if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {
							System.out.println("@@@@ var error on Pricing screen @@@");

							String ErrorValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

							//									    	assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));
							CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);

							//											CF.FnTestCaseStatusReport("Pass", "Rate Range Validation Verified Successfully for Price Item :-"+sPriceItem);

							System.out.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("value") + " || VerifyValidationMessage:-" + VerifyValidationMessage);

							Thread.sleep(1000);
						}

						System.out.println("Alert Exists - dismissed");
					}
				} catch (Exception e) {}




			} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Not_Editable_FixedErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0) {

				DoHaveFixedValidationMessage = true;

				String FixedValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Not_Editable_FixedErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

				System.err.println("FixedValidationMessageFromApplication:-" + FixedValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);

				assertTrue(FixedValidationMessageFromApplication.contains(VerifyValidationMessage));

				CF.FnTestCaseStatusReport("Pass", sPriceItem + " Verified Successfully Throwing Expected Error Message i.e " + FixedValidationMessageFromApplication);


			} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0) {

				DoHaveVariableErrorValidationMessage = true;

				String ErrorValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

				System.err.println("ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);

				assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));


			} else {
				CF.FnTestCaseStatusReport("Fail", "Price Item :-" + sPriceItem + " Rates Updation & Verification Is Not Completed.");
			}



			if (IsRequiredInternalSave.equals(true)) {

				System.out.println("-->-->--> IsRequiredInternalSave <--<--<--");


				FnNavigationToPricingAndCommitmentScreenFromPricingScreen("ValidationCheckSave");

				Thread.sleep(500);

				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(1000);

				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {

					CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen);

					String ErrorValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

					System.out.println("ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
					System.out.println("..................................");
					assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));

					CF.FnTestCaseStatusReport("Pass", sPriceItem + " Rate Range Validation Verified Successfully for Price Item Rate " + sUpdatePriceItemRate + " i.e " + ErrorValidationMessageFromApplication);

				}

			}

			//================================================================//


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnRecommendAndVerifyPriceItemValidationOnPricingScreen
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnRecommendAndVerifyPriceItemValidationOnPricingScreen(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnRecommendAndVerifyPriceItemValidationOnPricingScreen--<<<<<<<<" + iStartingRow);


		String sPriceItem, PriceItemParameters, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, sPreviousPriceItem;

		sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		PriceItemParameters = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sVerifyPriceItemRate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sUpdatePriceItemRate = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		RateTextBoxRowNumber = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		VerifyValidationMessage = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		sPreviousPriceItem = CF.FnGetCellValue((iStartingRow - 1), 1, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sPriceItem:-" + sPriceItem + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate + " || sUpdatePriceItemRate:-" + sUpdatePriceItemRate + " || VerifyValidationMessage:-" + VerifyValidationMessage);

		try {



			if (!sPriceItem.equals("NoValue")) {

				if (!sPreviousPriceItem.equals(sPriceItem)) {
					//To Filter & Search Price Item on Pricing & Commitment Screen
					FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(sPriceItem);

					// To Navigate to Pricing screen From "Pricing & Commitment" screen ui required "PriceItem and Parameter" if have Parameter
					FnRecommendAndUpdatePriceItemAndCheckValidationOnPricingScreen(sPriceItem);
				}



				if (VerifyValidationMessage.contains("User can not propose Pricing as the Price Item is non- negotiable")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, false);

					// To Navigate to Pricing and commitment screen from pricing screen either it will be "back" or "save" operation
					FnNavigationToPricingAndCommitmentScreenFromPricingScreen("back");

				} else if (VerifyValidationMessage.equals("NoValue") || VerifyValidationMessage.equals("")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, false);

					// To Navigate to Pricing and commitment screen from pricing screen either it will be "back" or "save" operation
					FnNavigationToPricingAndCommitmentScreenFromPricingScreen("save");

				} else if (VerifyValidationMessage.contains("Please enter the rate value between Floor")) {

					// To Update and verify price Item rate and check alerts and validation message on "Pricing Screen".
					FnUpdateAndVerifyPriceItemsWithParametersRatesOnPricingScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, RateTextBoxRowNumber, VerifyValidationMessage, true);

				}



			}



		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen
	,Description          : Division Currency Personal Hierarchy Information Verification through UI On Deal Information for accounts
	'###############################################################################################################################################################################################################################################*/
	public void FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen--<<<<<<<<" + iStartingRow);

		String sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, VerifyValidationMessage, sPreviousPriceItem;

		sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sVerifyPriceItemRate = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		sUpdatePriceItemRate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		VerifyValidationMessage = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		sPreviousPriceItem = CF.FnGetCellValue((iStartingRow - 1), 1, sSheetName, sWorkbook).toString().trim();

		System.out.println(iStartingRow + " || sPriceItem:-" + sPriceItem + " || sVerifyPriceItemRate:-" + sVerifyPriceItemRate + " || sUpdatePriceItemRate:-" + sUpdatePriceItemRate + " || VerifyValidationMessage:-" + VerifyValidationMessage);

		try {

			if (!sPriceItem.equalsIgnoreCase("NoValue")) {

				if (!sPreviousPriceItem.equals(sPriceItem)) {
					//To Filter & Search Price Item on Pricing & Commitment Screen
					FnSearchAndFilterPriceItemOnPricingAndCommitmentScreen(sPriceItem);
				}

				// Function To Update and verify price Item rate and check validation for incorrect input On "Pricing & Commitment" screen UI
				FnRecommendPriceItemsRatesOnPricingAndCommitmentScreen(sPriceItem, sVerifyPriceItemRate, sUpdatePriceItemRate, VerifyValidationMessage);


			}



		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToPricingAndCommitmentScreenFromPricingScreen
	,Description          : To Navigate to Pricing and commitment screen from pricing screen either it will be "back" or "save" operation And if we keep empty is will perform any one which is visible
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToPricingAndCommitmentScreenFromPricingScreen(String OperationName) throws Exception {
		System.out.println(">>>>>>>>OperationName>>--FnNavigationToPricingAndCommitmentScreenFromPricingScreen--<<OperationName<<<<<<<<<<<" + OperationName);

		try {

			ArrayList < String > SavedPriceItemRates = new ArrayList < String > ();


			if (OperationName.contains("save") || OperationName.contains("recommend") || OperationName.contains("ValidationCheckSave")) {

				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Pricing_Screen_Price_Component_Rate_TextBoxs_Path + "[1]").size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_Screen_Price_Component_Rate_TextBoxs_Path + "[1]").isDisplayed()) {
					for (WebElement singleRate: CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Pricing_Screen_Price_Component_Rate_TextBoxs_Path)) {
						String singlePriceItemRate = singleRate.getAttribute("value");
						SavedPriceItemRates.add(singlePriceItemRate);
					}
				}

				System.out.println(OperationName + "======== for saving =========");
				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button);
					System.out.println(OperationName + "1======== for saving =========1");

				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
					//					    	CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button));
					System.out.println(OperationName + "2======== for saving =========2");
				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
					//					    	CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button));
					System.out.println(OperationName + "3======== for saving =========3");
				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Recommended_Pricing_Recommend_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Recommended_Pricing_Recommend_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Recommended_Pricing_Recommend_Button);
					System.out.println(OperationName + "4======== for saving =========4");
				} else {
					System.err.println(OperationName + "=======not save=====");
				}


			} else if (OperationName.contains("back")) {

				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button).isDisplayed()) {
					System.out.println(OperationName + "1======== BACK =========1");
					CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button));
				} else {
					System.err.println(OperationName + "=======not back=====");
				}

			} else {

				Boolean IsSaved = false;

				System.out.println("======== for saving =========");
				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveAfterChangingRatesOfRecommendation_Button);
					IsSaved = true;
					System.out.println("1======== for saving =========1");
				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button);
					//					    	CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_SaveForPI033_Button));
					IsSaved = true;
					System.out.println("2======== for saving =========2");
				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_Save_Button);
					//					    	CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Pricing_And_Commitments_Save_Button));
					IsSaved = true;
					System.out.println("3======== for saving =========3");
				} else if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Recommended_Pricing_Recommend_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Recommended_Pricing_Recommend_Button).isDisplayed()) {
					CF.FnElementClick(driver, DealManagementPageElements.Recommended_Pricing_Recommend_Button);
					IsSaved = true;
					System.out.println("4======== for saving =========4");
				} else {
					System.err.println("=======not save=====");
				}


				if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button).isDisplayed() && IsSaved.equals(false)) {
					System.out.println("1======== BACK =========1");
					CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Orininal_Pricing_And_Commitments_Screen_Back_Button));
				} else {
					System.err.println("=======not back=====");
				}


			}



			///////////------------------////
			try {
				String checkCurrenctScreenUi = null;

				if (!OperationName.contains("ValidationCheckSave")) {
					Thread.sleep(2000);

					CF.FnSetFrame(driver, "uiMap");
					//				CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Pricing_And_Commitment_Table_Heading_Text);

					Thread.sleep(1000);

					//				CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Pricing_And_Commitment_Table_Heading_Text);

					checkCurrenctScreenUi = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Table_Heading_Text).getAttribute("innerText");

					System.out.println("checkCurrenctScreenUi:-" + checkCurrenctScreenUi);
					if (checkCurrenctScreenUi.contains("PRICE ITEM HIERARCHY") && OperationName.contains("save")) {
						CF.FnTestCaseStatusReport("Pass", "Price Item Rate Updated as " + SavedPriceItemRates + " & Saved Successfully & Navigated To Pricing And Commitment Screen");
					} else if (checkCurrenctScreenUi.contains("PRICE ITEM HIERARCHY") && OperationName.contains("back")) {
						CF.FnTestCaseStatusReport("Pass", "Navigated To Pricing And Commitment Screen Successfully.");
					}

				} else if (OperationName.contains("ValidationCheckSave")) {
					System.out.println("<------ValidationCheckSave----->");
				}

				Thread.sleep(1000);
			} catch (Exception ee) {

			}

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToPricingScreenFromPricingAndCommitmentScreen
	,Description          : To Navigate to Pricing screen From "Pricing & Commitment" screen ui required PriceItem and Parameter if have
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToPricingScreenFromPricingAndCommitmentScreen(String sPriceItem, String Parameter) throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToPricingScreenFromPricingAndCommitmentScreen--<<<<<<<<<<<<<" + sPriceItem + " = " + Parameter);

		try {

			/*				   
			  				    Thread.sleep(1000);
			  					CF.FnSetFrame(driver, "uiMap");       
						        CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
						        CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button); 
					    
								if(CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
								    CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label); 
								    Thread.sleep(1000);
								}
							    Thread.sleep(1000);

							    
						        CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox); 
							    Thread.sleep(500);
						        CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox); 
							    Thread.sleep(500);
								CF.FnSetText (driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox,sPriceItem);    										
							    Thread.sleep(500);
			*/

			JavascriptExecutor js = (JavascriptExecutor) driver;

			String NavigationToOriginalPricingScreenPath = null;

			String PriceItemWithParameterNavigationToOriginalPricingScreenPath = DealManagementPageElements.Deal_PriceItem_with_Parameter_Rate_HyperLink_Navidation_To_Orininal_Pricing_And_Commitments_Screen4.replaceAll("ReplacePriceItem", sPriceItem);
			PriceItemWithParameterNavigationToOriginalPricingScreenPath = PriceItemWithParameterNavigationToOriginalPricingScreenPath.replaceAll("ReplaceParameter", Parameter);

			System.out.println("PriceItemWithParameterNavigationToOriginalPricingScreenPath:-" + PriceItemWithParameterNavigationToOriginalPricingScreenPath);


			if (driver.findElements(By.xpath(DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem))).size() != 0) {
				NavigationToOriginalPricingScreenPath = DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem);
				System.out.println("=====1=====");
			} else if (CF.getListWebElements(driver, "XPATH", PriceItemWithParameterNavigationToOriginalPricingScreenPath).size() != 0 && CF.FnGetWebElement(driver, "XPATH", PriceItemWithParameterNavigationToOriginalPricingScreenPath).isDisplayed()) {
				NavigationToOriginalPricingScreenPath = PriceItemWithParameterNavigationToOriginalPricingScreenPath;
				System.out.println("=====2=====");
			} else if (driver.findElements(By.xpath(PriceItemWithParameterNavigationToOriginalPricingScreenPath)).size() != 0) {
				NavigationToOriginalPricingScreenPath = PriceItemWithParameterNavigationToOriginalPricingScreenPath;
				System.out.println("=====2.1=====");
			} else if (driver.findElements(By.xpath(DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem))).size() != 0) {
				NavigationToOriginalPricingScreenPath = DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem);
				System.out.println("=====3=====");
				List < WebElement > PricingPath = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem));
				int AllRecordSize = PricingPath.size();
				for (int SingleRec = 1; SingleRec <= AllRecordSize; SingleRec++) {
					WebElement NavigationToOriginalPricingScreenPath1 = CF.FnGetWebElement(driver, "XPATH", NavigationToOriginalPricingScreenPath + "[" + SingleRec + "]");
					String FoundParameter = (String) js.executeScript("return arguments[0].offsetParent.nextElementSibling.parentElement.nextElementSibling.nextSibling.parentElement.firstElementChild.nextElementSibling.innerText;", NavigationToOriginalPricingScreenPath1);
					System.out.println("FoundParameter:-" + FoundParameter + " || Parameter:-" + Parameter);
					if (FoundParameter.equals(Parameter)) {
						NavigationToOriginalPricingScreenPath = NavigationToOriginalPricingScreenPath + "[" + SingleRec + "]";
						break;
					}
				}

			} else if (driver.findElements(By.xpath(DealManagementPageElements.Deal_PriceItem_HyperLink_To_Navigate_Orininal_Pricing_And_Commitments_Screen1.replaceAll("ReplacePriceItem", sPriceItem))).size() != 0) {
				//				    	NavigationToOriginalPricingScreenPath = DealManagementPageElements.Deal_PriceItem_Rate_HyperLink_Navidation_To_Orininal_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", sPriceItem);
				System.out.println("=====4=====");
			}

			System.out.println("NavigationToOriginalPricingScreenPath:-" + NavigationToOriginalPricingScreenPath);

			if (driver.findElements(By.xpath(NavigationToOriginalPricingScreenPath)).size() != 0) {


				//				    	CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "Xpath", NavigationToOriginalPricingScreenPath));
				CF.FnElementClick(driver, NavigationToOriginalPricingScreenPath);

				Thread.sleep(1000);

				CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);



				CF.FnTestCaseStatusReport("Pass", "Navigate to Pricing screen for Price Item = " + sPriceItem + " Is Completed Successfully");
				System.out.println("==================================================================================");



			} else {
				System.err.println("NavigationToOriginalPricingScreenPath:-" + NavigationToOriginalPricingScreenPath);
				CF.FnTestCaseStatusReport("Fail", "Unable to Navigate to Pricing Screen for Price Item :-" + sPriceItem);
			}

			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnRecommendAndUpdatePriceItemAndCheckValidationOnPricingScreen
	,Description          : To Recommend Price item and check its Validation on Pricing screen
	'###############################################################################################################################################################################################################################################*/
	public void FnRecommendAndUpdatePriceItemAndCheckValidationOnPricingScreen(String sPriceItem) throws Exception {
		System.out.println(">>>>>>>>>>--FnRecommendAndUpdatePriceItemAndCheckValidationOnPricingScreen--<<<<<<<<<<<<<" + sPriceItem);

		try {
			Thread.sleep(1000);

			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);

			if (CF.FnGetTextFromElement(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label, "title").contains("Expand")) {
				CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_FilterExpand_Label);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);


			CF.FnElementClick(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(500);
			CF.FnClearTextFieldValue(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox);
			Thread.sleep(500);
			CF.FnSetText(driver, DealManagementPageElements.Select_Prize_Item_group_SearchFilters_TextBox, sPriceItem);
			Thread.sleep(500);







			if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Pricing_Recommend_Price_item_link).size() != 0) {

				CF.FnElementClick(driver, DealManagementPageElements.Deal_Pricing_Recommend_Price_item_link);

				Thread.sleep(500);

				CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);



				CF.FnTestCaseStatusReport("Pass", "Navigation to Recommedation on Pricing screen for Price Item = " + sPriceItem + " Is Completed Successfully");
				System.out.println("==================================================================================");



			} else {
				System.err.println("Unable to Navigate to Recommend Price Item:-" + sPriceItem);
				CF.FnTestCaseStatusReport("Fail", "Unable to Navigate to Recommend Price Item :-" + sPriceItem);
			}

			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnRecommendPriceItemsRatesOnPricingAndCommitmentScreen
	,Description          : To Recommend price Item rate and check validation for incorrect input
	'###############################################################################################################################################################################################################################################*/
	public void FnRecommendPriceItemsRatesOnPricingAndCommitmentScreen(String sPriceItem, String sVerifyPriceItemRate, String sUpdatePriceItemRate, String VerifyValidationMessage) throws Exception {
		System.out.println(">>>>>>>>>>--FnRecommendPriceItemsRatesOnPricingAndCommitmentScreen--<<<<<<<<<<<<<" + sPriceItem + " = " + sUpdatePriceItemRate);

		try {
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);

			if (CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {
				System.out.println("var error");
				CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
				Thread.sleep(1000);

			} else {
				System.out.println("var error not");
			}



			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(500);

			String PriceItemRateTextBox = DealManagementPageElements.Deal_PriceItem_Recommended_Rate_Input_TextBox_On_Pricing_And_Commitments_Screen.replaceAll("ReplacePriceItem", sPriceItem);


			if (CF.getListWebElements(driver, "XPATH", PriceItemRateTextBox).size() != 0) {

				System.out.println("sizeeeee - " + sPriceItem + " : " + PriceItemRateTextBox);

				CF.FnWaitForElement(driver, 360, PriceItemRateTextBox);
				CF.FnMoveToElementAndClick(driver, PriceItemRateTextBox);
				Thread.sleep(100);
				CF.FnGetWebElement(driver, "XPATH", PriceItemRateTextBox).click();
				Thread.sleep(200);
				CF.FnGetWebElement(driver, "XPATH", PriceItemRateTextBox).clear();
				Thread.sleep(500);

				WebDriverWait w = new WebDriverWait(driver, 120);

				try {
					//alertIsPresent() condition applied
					if (w.until(ExpectedConditions.alertIsPresent()) != null) {
						System.out.println("Alert exists");
						driver.switchTo().alert().dismiss();
					}
					CF.FnSetFrame(driver, "uiMap");
					Thread.sleep(1000);
					CF.FnWaitForElement(driver, 60, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
				} catch (Exception e) {
					System.out.println("######");
				}

				try {
					CF.FnGetWebElement(driver, "XPATH", PriceItemRateTextBox).sendKeys(sUpdatePriceItemRate);
					Thread.sleep(200);
					CF.FnMoveToElementAndClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);

					for (int alertsCount = 1; alertsCount <= 3; alertsCount++) { //throwing 2-3 alert messages

						if (w.until(ExpectedConditions.alertIsPresent()) != null) {
							String AlertWarningMessageFromApplication = driver.switchTo().alert().getText();
							System.out.println(sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
							if (AlertWarningMessageFromApplication.contains(VerifyValidationMessage)) {
								if (alertsCount == 1) {
									assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));
									System.out.println("passed");
									driver.switchTo().alert().dismiss();
									CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e " + VerifyValidationMessage + " for input - " + sUpdatePriceItemRate);
								}

							} else {
								System.err.println(alertsCount + " || " + sPriceItem + "  || AlertWarningMessageFromApplication:-" + AlertWarningMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
								//			    		  assertTrue(AlertWarningMessageFromApplication.contains(VerifyValidationMessage));

								// two validation on alert i.e numeric and range
							}
							System.out.println("Alert Exists - dismissed");
							Thread.sleep(500);
							if (w.until(ExpectedConditions.alertIsPresent()) != null) {

								if (AlertWarningMessageFromApplication.contains("Please enter the rate value between Floor")) {

									System.out.println("passed 11");
									driver.switchTo().alert().dismiss();
									CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Throwing Expected Alert i.e " + AlertWarningMessageFromApplication + " for Rate:-" + sUpdatePriceItemRate + " on Pricing Screen");

								}

							} else if (w.until(ExpectedConditions.alertIsPresent()) == null) {
								break;
							}
							Thread.sleep(500);

						}
					}
				} catch (Exception er) {}

				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(1000);

				System.out.println("444444444");

				if (driver.findElements(By.xpath(DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen)).size() != 0 && CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed()) {

					System.out.println("display:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isDisplayed());
					System.out.println("en:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isEnabled());
					System.out.println("select:-" + CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).isSelected());

					System.out.println("innnn");
					String ErrorValidationMessageFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_PriceItem_Variable_ErrorMSG_On_Orininal_And_Proposed_Pricing_And_Commitments_Screen).getAttribute("innerText");

					Thread.sleep(1000);
					CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_PriceItem_Close_ErrorMSG_On_Pricing_And_Commitments_Screen);

					System.out.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);

					if (VerifyValidationMessage.contains("Only numeric values are allowed")) {
						System.err.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
					} else if (!ErrorValidationMessageFromApplication.equals(VerifyValidationMessage)) {
						System.err.println(sPriceItem + "  || ErrorValidationMessageFromApplication:-" + ErrorValidationMessageFromApplication + " || VerifyValidationMessage:-" + VerifyValidationMessage);
						assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));
					} else {
						CF.FnTestCaseStatusReport("Pass", "Price Item :-" + sPriceItem + " Validation Verification Is Completed Successfully on Pricing & commitment screen.");
						assertTrue(ErrorValidationMessageFromApplication.contains(VerifyValidationMessage));
					}


				} else {
					System.out.println("elseeeeeee");
					CF.FnTestCaseStatusReport("Pass", "Price Item :- " + sPriceItem + " Rate Recommended Successfully as " + sUpdatePriceItemRate);
				}



			} else {
				System.out.println("Price Item Rate TextBox not Visible as Intended for :-" + sPriceItem);
				if (sUpdatePriceItemRate.equals("") || sUpdatePriceItemRate.equals(null)) {
					CF.FnTestCaseStatusReport("Pass", "Rate Textbox Does not Exists for Price Item :-" + sPriceItem + " as intended on Pricing & commitment screen.");
				}
			}





			Thread.sleep(2000);






		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/////////////////////////NEW IWS//////////////////////////


	public void AssignPriceListToDeal(String dealId, String entityId, String entityType, String actionFlag, String priority, String startDate, String endDate, String priceListId) throws Exception {

		DealManagementPageEvents DM = new DealManagementPageEvents(driver);

		String sAddPrizeListAssignmentResource = "/rest/ouaf/api/iws/C1-DealPricelistAssignmentREST/dealPricelistAssignment";
		String sContentTypeHeader = "application/json";
		String sAcceptTypeHeader = "application/json";
		String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");

		String sAddPrizeListJson = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":\"RequireddealId\",\"pricelistassignmentlist\":[{\"entityId\":\"RequestentityId\",\"entityType\":\"RequestentityType\",\"actionFlag\":\"RequestactionFlag\",\"priority\":\"Requestpriority\",\"startDate\":\"RequeststartDate\",\"endDate\":\"RequestendDate\",\"priceListId\":\"RequestpriceListId\"}]}}";
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequireddealId", dealId);
		//		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestdealIdentifier", dealIdentifier);
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestentityId", entityId);
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestentityType", entityType);
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestactionFlag", actionFlag);
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("Requestpriority", priority);
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequeststartDate", sStartDate);
		if (!endDate.equalsIgnoreCase("")) {
			sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestendDate", endDate);
		} else {
			sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"endDate\":\"RequestendDate\",", "");
		}
		sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequestpriceListId", priceListId);
		System.out.println("New Generated PriceList Json :-> " + sAddPrizeListJson);

		String sAddPrizeListRequest = DM.FnAddPrizeListAssignmentToDeal(sAddPrizeListJson, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
		System.out.println("Pricelist Added PRICELIST_ASGN_ID -> " + sAddPrizeListRequest);


	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealPricelistAddUpdateDeleteIWS
	,Description          : To Perform Operation On Deal i.e. Add , Update , Delete pricelist 
	'###############################################################################################################################################################################################################################################*/
	public void FnDealPricelistAddUpdateDeleteIWS(int iStartingRow, String sSheetName, String sWorkbook, String dealId, String modelId) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnDealPricelistAddUpdateDeleteIWS--<<<<<<<<" + iStartingRow);

		String dealidentifier, entityId, entityType, actionFlag, priority, startDate, endDate, priceListId, priceListInheritanceSw, priceListAssignmentId;

		priceListId = CF.FnGetCellValue((iStartingRow), 1, sSheetName, sWorkbook).toString().trim();
		startDate = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		priority = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		endDate = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		priceListInheritanceSw = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		entityId = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		entityType = CF.FnGetCellValue((iStartingRow), 9, sSheetName, sWorkbook).toString().trim();
		actionFlag = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		priceListAssignmentId = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();

		dealidentifier = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();


		System.out.println(iStartingRow + " || dealId:-" + dealId + " || modelId:-" + modelId + " || dealIdentifier:-" + dealidentifier);


		try {

			DealManagementPageEvents DM = new DealManagementPageEvents(driver);

			String sAddPrizeListAssignmentResource = "/rest/ouaf/api/iws/C1-DealPricelistAssignmentREST/dealPricelistAssignment";
			String sContentTypeHeader = "application/json";
			String sAcceptTypeHeader = "application/json";
			String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");

			String sAddPrizeListJson = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":\"RequireddealId\",\"modelId\":\"RequiredmodelId\",\"dealIdentifier\":\"RequireddealIdentifier\",\"pricelistassignmentlist\":[{\"entityId\":\"RequiredentityId\",\"entityType\":\"RequiredentityType\",\"actionFlag\":\"RequiredactionFlag\",\"priority\":\"Requiredpriority\",\"startDate\":\"RequiredstartDate\",\"endDate\":\"RequiredendDate\",\"priceListId\":\"RequiredpriceListId\",\"priceListInheritanceSw\":\"RequiredpriceListInheritanceSw\",\"priceListAssignmentId\":\"RequiredpriceListAssignmentId\"}]}}";

			if (!dealId.equalsIgnoreCase("NoValue") || !dealId.equalsIgnoreCase(null)) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequireddealId", dealId);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"dealId\":\"RequireddealId\",", "");
			}

			if (!modelId.equalsIgnoreCase("NoValue") || !modelId.equalsIgnoreCase(null)) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredmodelId", modelId);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"modelId\":\"RequiredmodelId\",", "");
			}

			if (!dealidentifier.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequireddealIdentifier", dealidentifier);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"dealIdentifier\":\"RequireddealIdentifier\",", "");
			}

			if (!entityId.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredentityId", entityId);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"entityId\":\"RequiredentityId\",", "");
			}

			if (!entityType.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredentityType", entityType);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"entityType\":\"RequiredentityType\",", "");
			}

			if (!actionFlag.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredactionFlag", actionFlag);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"actionFlag\":\"RequiredactionFlag\",", "");
			}

			if (!priority.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("Requiredpriority", priority);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"priority\":\"Requiredpriority\",", "");
			}

			if (!startDate.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredstartDate", startDate);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"startDate\":\"RequiredstartDate\",", "");
			}

			if (!endDate.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredendDate", endDate);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"endDate\":\"RequiredendDate\",", "");
			}

			if (!priceListId.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredpriceListId", priceListId);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"priceListId\":\"RequiredpriceListId\",", "");
			}

			if (!priceListInheritanceSw.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredpriceListInheritanceSw", priceListInheritanceSw);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"priceListInheritanceSw\":\"RequiredpriceListInheritanceSw\",", "");
			}

			if (!priceListAssignmentId.equalsIgnoreCase("NoValue")) {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("RequiredpriceListAssignmentId", priceListAssignmentId);
			} else {
				sAddPrizeListJson = sAddPrizeListJson.replaceAll("\"priceListAssignmentId\":\"RequiredpriceListAssignmentId\"", "");
			}

			System.out.println("New Generated PriceList Json :-> " + sAddPrizeListJson);

			String sAddPrizeListRequest = DM.FnAddPrizeListAssignmentToDeal(sAddPrizeListJson, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
			System.out.println("Pricelist Added PRICELIST_ASGN_ID -> " + sAddPrizeListRequest);

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnReadPriceItemGroupSelectionIWS
	,Description          : To Simulate Deal By Request
	'###############################################################################################################################################################################################################################################*/

	public String FnReadPriceItemGroupSelectionIWS(String sReadPSELResource, String sReadPSELBody, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnReadPriceItemGroupSelectionIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sReadPSELResource, sReadPSELBody, sContentTypeHeader, sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("READ iStatusCode :-" + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Deal READ Price Item Group Selection By Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal READ Price Item Group Selection By Request Is completed !  Deal ID is : " + sDealId);

			} else {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Gruup Selection not completed due to 400 Error");

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Deal READ Price Item Group Selection By Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Group Selection By Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Deal READ Price Item Group Selection By Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Group Selection By Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Deal READ Price Item Group Selection By Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Group Selection By Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal READ Price Item Group Selection By Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Group Selection By Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal READ Price Item Group Selection By Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal READ Price Item Group Selection By Request Not Completed ! Message Text is : " + sValue);


			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode == iErrorStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSelectPriceItemGroupSelectionIWS
	,Description          : To Select Price Item From Price Item Group Selection By IWS Request
	'###############################################################################################################################################################################################################################################*/

	public String FnSelectPriceItemGroupSelectionIWS(String sSelectPSELResource, String sSelectPSELBody, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnSelectPriceItemGroupSelectionIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSelectPSELResource, sSelectPSELBody, sContentTypeHeader, sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);

				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Deal Select Price Item Group Selection By Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Select Price Item Group Selection By Request Is completed !  Deal ID is : " + sDealId);

			} else {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Gruup Selection not completed due to 400 Error");

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Deal Select Price Item Group Selection By Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Group Selection By Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Deal Select Price Item Group Selection By Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Group Selection By Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Deal Select Price Item Group Selection By Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Group Selection By Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Select Price Item Group Selection By Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Group Selection By Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Select Price Item Group Selection By Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Select Price Item Group Selection By Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode == iErrorStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}






	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnSendDealForApprovalIWS
	,Description          : To Select Price Item From Price Item Group Selection By IWS Request
	'###############################################################################################################################################################################################################################################*/

	public String FnSendDealForApprovalIWS(String sSendDealForApprovalResource, String SendDealForApprovalJson, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnSendDealForApprovalIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, SendDealForApprovalJson, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			System.out.println("<<<<<---- FnSendDealForApprovalIWS iStatusCode ---->>>>>" + iStatusCode);

			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Send Deal For Approval By Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Send Deal For Approval By Request Is completed !  Deal ID is : " + sDealId);

			} else if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Is not completed due to 400 Error");
			} else {

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Send Deal For Approval By Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Send Deal For Approval By Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Send Deal For Approval By Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Send Deal For Approval By Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Send Deal For Approval By Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Send Deal For Approval By Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode != iSuccessStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}




	/*'############################################### WEBSERVICES COMMON FUNCTIONS #############################################################################################################################################################################################	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealPricingAndCommitment
	,Description          : To FnDealPricingAndCommitment
	'###############################################################################################################################################################################################################################################*/

	public String FnReadPricingAndCommitmentIWS(String sReadPricing, String sDealPricingAndCommitmentResource, String sContentTypeHeader, String sAcceptTypeHeader, String sActionFlag, String sInquiryModeFlag, String sDealId, String sModelId, String sDealidentifier, String sEntityType, String sEntityId, String spricingDetails, String scommitmentDetails, String sErrorMessageFlag) throws Exception {
		System.out.println("*--*--*--FnReadPricingAndCommitmentIWS--*--*--*" + sReadPricing + " actionFlag :-" + sActionFlag);

		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		String FnDealPricingAndCommitmentResp = null;
		String sValue = null;


		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sDealPricingAndCommitmentResource, sReadPricing, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();

			FnDealPricingAndCommitmentResp = BaseTest.respBody.asString();

			//			System.out.println("C1-DealPriceAsgnCommitmentsREST ApiResp :-" + FnDealPricingAndCommitmentResp);

			if (iStatusCode == iSuccessStatusCode) {

				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed Successfully ! Deal READ Request is :" + sReadPricing);

				sValue = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST");
				//System.out.println("Pricing And Commitment Performed Successfully !  response message is : " + sValue);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Deal response message is :" + sValue);

				sDealId = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST.dealId"); // dealId
				//System.out.println("Pricing And Commitment Performed Successfully ! Deal ID is : " + sDealId);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Deal ID is : " + sDealId);

				sModelId = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST.modelId"); //modelId
				//System.out.println("Pricing And Commitment Performed Successfully ! Model ID is : " + sModelId);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Model ID is : " + sModelId);


				JSONObject DealPriceAsgnCommitmentsIWS = new JSONObject(FnDealPricingAndCommitmentResp);

				JSONObject C1DealPriceAsgnCommitmentsREST = DealPriceAsgnCommitmentsIWS.getJSONObject("C1-DealPriceAsgnCommitmentsREST");

				//Boolean IsExistsC1DealPriceAsgnCommitmentsREST = C1DealPriceAsgnCommitmentsREST.toString().contains("pricingAndCommitmentsDetails");

				JSONObject pricingAndCommitmentsDetails = null;
				JSONArray pricingAndCommitmentsDetailsArray = null;

				if (C1DealPriceAsgnCommitmentsREST.get("pricingAndCommitmentsDetails") instanceof JSONObject) {

					pricingAndCommitmentsDetails = C1DealPriceAsgnCommitmentsREST.getJSONObject("pricingAndCommitmentsDetails");


				} else if (C1DealPriceAsgnCommitmentsREST.get("pricingAndCommitmentsDetails") instanceof JSONArray) {

					pricingAndCommitmentsDetailsArray = new JSONArray(C1DealPriceAsgnCommitmentsREST.getJSONArray("pricingAndCommitmentsDetails"));

				}

				//JSONObject pricingAndCommitmentsDetails = C1DealPriceAsgnCommitmentsREST.getJSONObject("pricingAndCommitmentsDetails");


				//				System.out.println("<<< sInquiryModeFlag >>>" + sInquiryModeFlag);


				if (!sDealId.equalsIgnoreCase("NoValue")) {

					String dealId = (String) C1DealPriceAsgnCommitmentsREST.get("dealId").toString().trim();

					assertEquals(sDealId, dealId);

				}

				if (!sModelId.equalsIgnoreCase("NoValue")) {

					String modelId = (String) C1DealPriceAsgnCommitmentsREST.get("modelId").toString().trim();

					assertEquals(sModelId, modelId);

				}

				//				System.out.println("sDealIdentifier:-" + sDealidentifier);
				if (!sDealidentifier.equalsIgnoreCase("NoValue")) {

					String dealIdentifier = (String) C1DealPriceAsgnCommitmentsREST.get("dealIdentifier").toString().trim();

					assertEquals(sDealidentifier, dealIdentifier);

				}

				if (!sActionFlag.equalsIgnoreCase("NoValue")) {

					String actionFlag = (String) C1DealPriceAsgnCommitmentsREST.get("actionFlag").toString().trim();
					//					System.out.println("sActionFlag:-" + sActionFlag + " || actionFlag:-" + actionFlag);
					//assertEquals(sActionFlag, actionFlag);

				}


				if (!sEntityType.equalsIgnoreCase("NoValue")) {

					String entityType = (String) C1DealPriceAsgnCommitmentsREST.get("entityType").toString().trim();

					assertEquals(sEntityType, entityType);

				}

				if (!sEntityId.equalsIgnoreCase("NoValue")) {

					String entityId = (String) C1DealPriceAsgnCommitmentsREST.get("entityId").toString().trim();

					assertEquals(sEntityId, entityId);

				}

				if (!sInquiryModeFlag.equalsIgnoreCase("NoValue")) {

					String inquiryModeFlag = (String) C1DealPriceAsgnCommitmentsREST.get("inquiryModeFlag").toString().trim();

					assertEquals(sInquiryModeFlag, inquiryModeFlag);


					if (sInquiryModeFlag.equals("PRIC") || sInquiryModeFlag.equals("COMT") || sInquiryModeFlag.equals("BOTH")) {

						String[] PricingTotalRecCount = C1DealPriceAsgnCommitmentsREST.toString().split("pricingDetails");
						String[] CommitmentTotalRecCount = C1DealPriceAsgnCommitmentsREST.toString().split("commitmentDetails");
						int PricingTotalCount = PricingTotalRecCount.length - 1;
						int CommitmentTotalCount = CommitmentTotalRecCount.length - 1;

						//						System.out.println("! PricingTotalCount:-" + PricingTotalCount + " || spricingDetails:-" + spricingDetails);

						assertEquals(String.valueOf(PricingTotalCount).trim(), spricingDetails);

						//						System.out.println("! CommitmentTotalCount:-" + CommitmentTotalCount + " || scommitmentDetails:-" + scommitmentDetails);

						assertEquals(String.valueOf(CommitmentTotalCount).trim(), scommitmentDetails);

						//						System.out.println("!! CommitmentTotalCount:-" + CommitmentTotalCount + " || scommitmentDetails:-" + scommitmentDetails);



					} else {

						CF.FnTestCaseStatusReport("Fail", "" + sActionFlag + " Pricing And Commitment Not Performed With sInquiryModeFlag !! sDealId :- " + sDealId);

					}

				} else {


					String[] PricingTotalRecCount = C1DealPriceAsgnCommitmentsREST.toString().split("pricingDetails");
					String[] CommitmentTotalRecCount = C1DealPriceAsgnCommitmentsREST.toString().split("commitmentDetails");
					int PricingTotalCount = PricingTotalRecCount.length - 1;
					int CommitmentTotalCount = CommitmentTotalRecCount.length - 1;


					//					System.out.println("@ PricingTotalCount:-" + PricingTotalCount + " || spricingDetails:-" + spricingDetails);

					assertEquals(String.valueOf(PricingTotalCount).trim(), spricingDetails);

					//					System.out.println("@ CommitmentTotalCount:-" + CommitmentTotalCount + " || scommitmentDetails:-" + scommitmentDetails);

					assertEquals(String.valueOf(CommitmentTotalCount).trim(), scommitmentDetails);

					//					System.out.println("@ CommitmentTotalCount:-" + CommitmentTotalCount + " || scommitmentDetails:-" + scommitmentDetails);


				}


				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed Successfully !! sDealId :- " + sDealId);


			} else if (iStatusCode == iErrorStatusCode) {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Read Pricing And Commitment Not Performed  ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at last so we can return text
				System.out.println("Read Pricing And Commitment Not Performed ! Reason Is : " + sValue);

				System.out.println(sActionFlag + "$ sValue:-" + sValue + " || sErrorMessageFlag:-" + sErrorMessageFlag);

				if (!sErrorMessageFlag.equalsIgnoreCase("NoValue") && sActionFlag.equalsIgnoreCase("READ")) {

					assertEquals(sValue, sErrorMessageFlag);

					CF.FnTestCaseStatusReport("Pass", "Error Message Thrown Successfully ! Reason Is : " + sValue);


				} else {
					CF.FnTestCaseStatusReport("Fail", "Error Message Not Thrown Successfully ! Reason Is : " + sValue);
				}


			} else {

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				//System.out.println("Read Pricing And Commitment Not Performed ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! messageText Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! Message Number Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemDetailDocument.problemType");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! problemType Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at end so we can return title text
				//System.out.println("Read Pricing And Commitment Not Performed ! Reason Is : " + sValue);

				System.out.println(sActionFlag + "$ sValue:-" + sValue + " || sErrorMessageFlag:-" + sErrorMessageFlag);

				if (!sErrorMessageFlag.equalsIgnoreCase("NoValue") && sActionFlag.equalsIgnoreCase("READ")) {
					assertEquals(sValue, sErrorMessageFlag);

					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed ! Reason Is : " + sValue);
				} else {
					CF.FnTestCaseStatusReport("Fail", "" + sActionFlag + " Pricing And Commitment Not Performed ! Reason Is : " + sValue);
				}

			}

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return FnDealPricingAndCommitmentResp;




	}





	/*'############################################### WEBSERVICES COMMON FUNCTIONS #############################################################################################################################################################################################	
	/*###########################################################################################################################################################################################################################################################################/
    'Function Name        : FnPricingAndCommitmentIWS
    'Function Description : This function to Read Pricing Details from IWS 
    'Input Parameters     : iStartingRow     -> Starting Row of the excel sheet which has the Price Item SQI Details at person level.
    '#########################################################################################################################################################################################################################################################################################################################*/
	public void FnPricingAndCommitmentIWS(int iStartingRow, String sSheetName, String sWorkbook, String sDealId, String sModelId, String sDealidentifier) throws Exception {
		System.out.println(iStartingRow + "<<<===FnPricingAndCommitmentIWS===>>>" + iStartingRow);
		String sDealPricingAndCommitmentResource = "/rest/ouaf/api/iws/C1-DealPriceAsgnCommitmentsREST/DealPriceAsgnCommitments";
		String sContentTypeHeader = "application/json";
		String sAcceptTypeHeader = "application/json";


		try {
			String sPRICEITEM_CD, sPRICEITEM_PARM, sSQI_TYPE_FLG, sStartDate, sEndDate, sRate, sSQI_CD, sSVC_QTY, sEntityType, sEntityId, sInquiryModeFlag, spricingDetails, scommitmentDetails, sDataReplaceDetails, sErrorMessageFlag;

			//sqi c1_model_sqi
			sPRICEITEM_CD = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sPRICEITEM_PARM = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
			sSQI_TYPE_FLG = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
			sStartDate = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
			sEndDate = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
			sRate = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
			sSQI_CD = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
			sSVC_QTY = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
			sEntityType = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();
			sEntityId = CF.FnGetCellValue(iStartingRow, 21, sSheetName, sWorkbook).toString().trim();
			sInquiryModeFlag = CF.FnGetCellValue(iStartingRow, 22, sSheetName, sWorkbook).toString().trim();
			spricingDetails = CF.FnGetCellValue(iStartingRow, 23, sSheetName, sWorkbook).toString().trim();
			scommitmentDetails = CF.FnGetCellValue(iStartingRow, 24, sSheetName, sWorkbook).toString().trim();
			sDataReplaceDetails = CF.FnGetCellValue(iStartingRow, 25, sSheetName, sWorkbook).toString().trim();
			sErrorMessageFlag = CF.FnGetCellValue(iStartingRow, 26, sSheetName, sWorkbook).toString().trim();

			if (sErrorMessageFlag.contains("ReplaceDealID")) {
				sErrorMessageFlag = sErrorMessageFlag.replaceAll("ReplaceDealID", sDealId); //in case where in validation deal id exists
			}
			if (sErrorMessageFlag.contains("ReplacesStartDate")) {
				sErrorMessageFlag = sErrorMessageFlag.replaceAll("ReplacesStartDate", sStartDate); //in case where in validation deal id exists
			}
			if (sErrorMessageFlag.contains("ReplacesEndDate")) {
				sErrorMessageFlag = sErrorMessageFlag.replaceAll("ReplacesEndDate", sEndDate); //in case where in validation deal id exists
			}

			String sActionFlag = sSQI_TYPE_FLG;

			System.out.println(iStartingRow + "(--->> PRICEITEM_CD :- " + sPRICEITEM_CD + " <<---)");
			System.out.println(iStartingRow + "(--->> sPRICEITEM_PARM :- " + sPRICEITEM_PARM + " <<---)");
			System.out.println(iStartingRow + "(--->> sSQI_TYPE_FLG :- " + sSQI_TYPE_FLG + " <<---)");
			System.out.println(iStartingRow + "(--->> sStartDate :- " + sStartDate + " <<---)");
			System.out.println(iStartingRow + "(--->> sRate :- " + sRate + " <<---)");
			System.out.println(iStartingRow + "(--->> sDealId :- " + sDealId + " <<---)");
			System.out.println(iStartingRow + "(--->> sModelId :- " + sModelId + " <<---)");
			System.out.println(iStartingRow + "(--->> sDealIdentifier :- " + sDealidentifier + " <<---)");
			System.out.println(iStartingRow + "(--->> sEntityType :- " + sEntityType + " <<---)");
			System.out.println(iStartingRow + "(--->> sEntityId :- " + sEntityId + " <<---)");
			System.out.println(iStartingRow + "(--->> sActionFlag :- " + sActionFlag + " <<---)");
			System.out.println(iStartingRow + "(--->> sStartDate :- " + sStartDate + " <<---)");
			System.out.println(iStartingRow + "(--->> sEndDate :- " + sEndDate + " <<---)");
			System.out.println(iStartingRow + "(--->> sErrorMessageFlag :- " + sErrorMessageFlag + " <<---)");


			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {

				String actionFlag = sSQI_TYPE_FLG;


				String sReadPricing = "{\"C1-DealPriceAsgnCommitmentsREST\":{\"actionFlag\":\"RequiredactionFlag\",\"inquiryModeFlag\":\"RequiredinquiryModeFlag\",\"dealId\":\"RequireddealId\",\"modelId\":\"RequiredmodelId\",\"dealIdentifier\":\"RequiredDealIdentifier\",\"entityId\":\"RequiredentityId\",\"entityType\":\"RequiredentityType\"}}";

				if (!actionFlag.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredactionFlag", "READ");
				} else {
					sReadPricing = sReadPricing.replaceAll("\"actionFlag\":\"RequiredactionFlag\",", "");
				}
				if (!sInquiryModeFlag.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredinquiryModeFlag", sInquiryModeFlag);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"inquiryModeFlag\":\"RequiredinquiryModeFlag\",", "");
				}
				if (!sDealId.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequireddealId", sDealId);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"dealId\":\"RequireddealId\",", "");
				}
				if (!sModelId.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredmodelId", sModelId);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"modelId\":\"RequiredmodelId\",", "");
				}
				if (!sDealidentifier.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredDealIdentifier", sDealidentifier);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"dealIdentifier\":\"RequiredDealIdentifier\",", "");
				}
				if (!sEntityId.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredentityId", sEntityId);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"entityId\":\"RequiredentityId\",", "");
				}
				if (!sEntityType.equalsIgnoreCase("NoValue")) {
					sReadPricing = sReadPricing.replaceAll("RequiredentityType", sEntityType);
				} else {
					sReadPricing = sReadPricing.replaceAll("\"entityType\":\"RequiredentityType\"", "");
				}

				if (sReadPricing.contains("\",}")) {
					sReadPricing = sReadPricing.replaceAll("\",}", "\"}");
				}

				if (sActionFlag.equalsIgnoreCase("NoValue")) {
					sActionFlag = "READ";
				}
								System.out.println("sReadPricing 1 :-> " + sReadPricing);


				String GetResponse = FnReadPricingAndCommitmentIWS(sReadPricing, sDealPricingAndCommitmentResource, sContentTypeHeader, sAcceptTypeHeader, sActionFlag, sInquiryModeFlag, sDealId, sModelId, sDealidentifier, sEntityType, sEntityId, spricingDetails, scommitmentDetails, sErrorMessageFlag);

								System.out.println("GetResponse 1 :-> " + GetResponse);
								
								try {
							        FileWriter myWriter = new FileWriter("./databank/banking/deal_management/"+BaseTest.sScriptName+"_ReadResponse.txt");
							        myWriter.write(GetResponse);
							        myWriter.close();
							        System.out.println("Successfully wrote Read Response to the file.");
							      } catch (IOException e) {
							        System.out.println("An error occurred.");
							        e.printStackTrace();
							      }				

				if (actionFlag.contains("Seasonal")) {
					sSQI_TYPE_FLG = actionFlag = "OVRD";
				}

				if (actionFlag.equals("OVRD") || actionFlag.equals("UPD") || actionFlag.equals("DEL") || actionFlag.equals("RECM")) {

					JSONObject DealPriceAsgnCommitmentsIWS = new JSONObject(GetResponse);

					JSONObject C1DealPriceAsgnCommitmentsREST = DealPriceAsgnCommitmentsIWS.getJSONObject("C1-DealPriceAsgnCommitmentsREST");
					//String modelId = C1DealPriceAsgnCommitmentsREST.getString("modelId");

					C1DealPriceAsgnCommitmentsREST.remove("actionFlag");
					C1DealPriceAsgnCommitmentsREST.remove("inquiryModeFlag");

					//					System.out.println("sDataReplaceDetails:-" + sDataReplaceDetails);
					if (!sDataReplaceDetails.equalsIgnoreCase("NoValue")) {

						JSONObject sDataReplaceDetailsObj = new JSONObject(sDataReplaceDetails);
						JSONObject UserPriceAsgnCommitments = sDataReplaceDetailsObj.getJSONObject("C1-DealPriceAsgnCommitmentsREST");

						if (UserPriceAsgnCommitments.get("delete") instanceof JSONArray) {
							JSONArray deleteObjArray = new JSONArray(UserPriceAsgnCommitments.getJSONArray("delete"));
							//System.out.println("deleteObjArray() -> "+deleteObjArray);

							for (int singleRemove = 0; singleRemove < deleteObjArray.length(); singleRemove++) {

								String singleRemoveRecord = deleteObjArray.getString(singleRemove).trim();

								C1DealPriceAsgnCommitmentsREST.remove(singleRemoveRecord);

								//								System.out.println("singleRemoveRecord:-" + singleRemoveRecord);

							}
						}



						//System.out.println("PriceAsgnCommitments.length():-" + UserPriceAsgnCommitments.length());
						if (UserPriceAsgnCommitments.length() > 2) {
							UserPriceAsgnCommitments.keySet().forEach(keyStr -> {
								Object keyvalue = UserPriceAsgnCommitments.get(keyStr);
								//System.out.println("Main key: " + keyStr + " Main value: " + keyvalue);
								if ((!keyStr.toString().trim().equals("delete")) && (!keyStr.toString().trim().equals("pricingAndCommitmentsDetails"))) {
									//									System.out.println("inside keyStr :-" + keyStr);
									C1DealPriceAsgnCommitmentsREST.put(keyStr, keyvalue);
								}
							});
						}




					}

					//					System.out.println("after updation C1DealPriceAsgnCommitmentsREST :-" + C1DealPriceAsgnCommitmentsREST);



					JSONObject pricingAndCommitmentsDetails = C1DealPriceAsgnCommitmentsREST.getJSONObject("pricingAndCommitmentsDetails");

					JSONArray PricingDetailsJsonArray = new JSONArray(pricingAndCommitmentsDetails.getJSONArray("pricingDetails"));
					System.out.println("<<<PricingDetailsJsonArray:-" + PricingDetailsJsonArray.length());

					boolean EnterValidObj = false;

					for (int i = PricingDetailsJsonArray.length() - 1; i >= 0; i--) {
						JSONObject pricingDetailsJSONObject = PricingDetailsJsonArray.getJSONObject(i);
						String pricingStatus = (String) pricingDetailsJSONObject.get("pricingStatus").toString().trim();
						String priceItemCode = (String) pricingDetailsJSONObject.get("priceItemCode").toString().trim();
						String priceItemstartDate = (String) pricingDetailsJSONObject.get("startDate").toString().trim();

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

						int result = 0;
						if (!sStartDate.equals("NoValue")) {
							Date date1 = sdf.parse(priceItemstartDate);
							Date date2 = sdf.parse(sStartDate);
							result = date1.compareTo(date2);
						}

						System.out.println("result:-" + result);

						if (pricingStatus.equals("RECM") && (sSQI_TYPE_FLG.equals("OVRD") || sSQI_TYPE_FLG.equals("UPD") || sSQI_TYPE_FLG.equals("RECM"))) {  //-- && (sSQI_TYPE_FLG.equals("OVRD") || sSQI_TYPE_FLG.equals("UPD"))
							PricingDetailsJsonArray.remove(i);
						} else if (pricingStatus.equals("ACTV")) {
							PricingDetailsJsonArray.remove(i);
						} else if ((priceItemCode.equals(sPRICEITEM_CD) && (result <= 0))) {
							//Keep only Expected Price Item in Array

							System.out.println("oooooooooooooooooooo");

							if (pricingDetailsJSONObject.toString().contains("parameterDetails") && !sPRICEITEM_PARM.equals("NoValue")) {
								String parameterDetails = (String) pricingDetailsJSONObject.get("parameterDetails").toString().trim();
								System.out.println("yyy parameterDetails :-" + parameterDetails);
								System.out.println("parameterDetails:-" + parameterDetails + " || sPRICEITEM_PARM:-" + sPRICEITEM_PARM);

								String tempStr = "";

								if (pricingDetailsJSONObject.get("parameterDetails") instanceof JSONObject) {

									JSONObject parameterDetailsObj = pricingDetailsJSONObject.getJSONObject("parameterDetails");
									String parameterCode = parameterDetailsObj.getString("parameterCode");
									String parameterValue = parameterDetailsObj.getString("parameterValue");

									tempStr = parameterCode + "=" + parameterValue;
									System.out.println("wwwwwwww:-" + tempStr);

								} else if (pricingDetailsJSONObject.get("parameterDetails") instanceof JSONArray) {
									JSONArray pricingDetailsJSONObjectarr = new JSONArray(pricingDetailsJSONObject.getJSONArray("parameterDetails"));
									System.out.println("pricingDetailsJSONObjectarr.length() ->" + pricingDetailsJSONObjectarr.length());
									for (int qqq = 0; qqq < pricingDetailsJSONObjectarr.length(); qqq++) {

										JSONObject tempparameterDetailsObj = pricingDetailsJSONObjectarr.getJSONObject(qqq);

										String parameterCode = tempparameterDetailsObj.getString("parameterCode");
										String parameterValue = tempparameterDetailsObj.getString("parameterValue");

										if (qqq == 0) {
											tempStr = tempStr + parameterCode + "=" + parameterValue;
										} else {
											tempStr = tempStr + "~" + parameterCode + "=" + parameterValue;
										}

									}

									System.out.println("vvvvvvv:-" + tempStr);


								}
								System.out.println("tempStr:-" + tempStr + " || sPRICEITEM_PARM:-" + sPRICEITEM_PARM);

								if (tempStr.equals(sPRICEITEM_PARM)) {
									System.out.println("zzzz parameterDetails :-" + parameterDetails);
									EnterValidObj = true;
								} else {
									System.out.println("pppp parameterDetails :-" + parameterDetails);
									PricingDetailsJsonArray.remove(i);
								}

							} else if (pricingDetailsJSONObject.toString().contains("parameterDetails") && sPRICEITEM_PARM.equals("NoValue")) {
								PricingDetailsJsonArray.remove(i);
							} else if ((EnterValidObj == false) && (sPRICEITEM_PARM.equals("NoValue")) && (!pricingDetailsJSONObject.toString().contains("parameterDetails"))) {
								System.out.println("elseeeeeeee");
								EnterValidObj = true;
							} else {
								PricingDetailsJsonArray.remove(i);
							}
							System.out.println("pricingDetailsJSONObject:-" + pricingDetailsJSONObject);
						} else {
							PricingDetailsJsonArray.remove(i);
						}
					}

					System.out.println("Main RT Len :-" + PricingDetailsJsonArray.length());
					System.out.println("PricingDetailsJsonArray:-" + PricingDetailsJsonArray);


					for (int i = PricingDetailsJsonArray.length() - 1; i >= 0; i--) {

						System.out.println("PricingDetailsJsonArrayLen:-" + PricingDetailsJsonArray.length());

						System.out.println("PricingDetailsJsonArray:-" + PricingDetailsJsonArray);
						JSONObject pricingDetailsJSONObject = PricingDetailsJsonArray.getJSONObject(i);
						//						System.out.println(i+") pricingDetailsJSONObject 11 :-"+pricingDetailsJSONObject);
						String pricingStatus = (String) pricingDetailsJSONObject.get("pricingStatus").toString().trim();
						String priceItemCode = (String) pricingDetailsJSONObject.get("priceItemCode").toString().trim();

						String priceItemstartDate = "NoValue";
						if (pricingDetailsJSONObject.has("startDate")) {
							priceItemstartDate = (String) pricingDetailsJSONObject.get("startDate").toString().trim();
						}

						String priceItemEndDate = "NoValue";
						if (pricingDetailsJSONObject.has("endDate")) {
							priceItemEndDate = (String) pricingDetailsJSONObject.get("endDate").toString().trim();
						}



						System.out.println("pricingStatus:->" + pricingStatus);
						System.out.println("priceItemCode:->" + priceItemCode + " || sPRICEITEM_CD:->" + sPRICEITEM_CD);
						System.out.println("priceItemstartDate:->" + priceItemstartDate + " || sStartDate:->" + sStartDate);
						System.out.println("priceItemEndDate:->" + priceItemEndDate + " || sEndDate:->" + sEndDate);


						if (pricingStatus.equals("ACTV") || (EnterValidObj == false)) {
							System.out.println("ACTV -->");
							PricingDetailsJsonArray.remove(i);
						} else if (pricingStatus.equals("PRPD") && priceItemCode.equals(sPRICEITEM_CD) && ((priceItemstartDate.equals(sStartDate)) || (PricingDetailsJsonArray.length() == 1)) && (EnterValidObj == true)) {

							System.out.println("PRPD -->");


							if (!sSQI_TYPE_FLG.equalsIgnoreCase("NoValue")) {
								pricingDetailsJSONObject.put("actionFlag", sSQI_TYPE_FLG);
							}

							if (sSQI_TYPE_FLG.equalsIgnoreCase("RECM")) {
								pricingDetailsJSONObject.put("pricingStatus", "RECM");
							}

							if (!sStartDate.equalsIgnoreCase("NoValue")) {
								pricingDetailsJSONObject.put("startDate", sStartDate);
							}

							if (!sEndDate.equalsIgnoreCase("NoValue")) {
								pricingDetailsJSONObject.put("endDate", sEndDate);
							}



							//System.out.println("sDataReplaceDetails:-"+sDataReplaceDetails);
							if (!sDataReplaceDetails.equalsIgnoreCase("NoValue")) {
								System.out.println("inside sDataReplaceDetails ->>");
								JSONObject sDataReplaceDetailsObj = new JSONObject(sDataReplaceDetails);
								JSONObject PriceAsgnCommitmentsUserObj = sDataReplaceDetailsObj.getJSONObject("C1-DealPriceAsgnCommitmentsREST");
								if (sDataReplaceDetailsObj.get("C1-DealPriceAsgnCommitmentsREST").toString().trim().contains("pricingAndCommitmentsDetails")) {
									JSONObject pricingAndCommitmentsUserDetails = PriceAsgnCommitmentsUserObj.getJSONObject("pricingAndCommitmentsDetails");

									if (pricingAndCommitmentsUserDetails.has("pricingDetails")) {

										JSONArray PricingDetailsUserJsonArray = new JSONArray(pricingAndCommitmentsUserDetails.getJSONArray("pricingDetails"));

										for (int priceasgnusr = PricingDetailsUserJsonArray.length() - 1; priceasgnusr >= 0; priceasgnusr--) {

											JSONObject pricingDetailsUserJSONObject = PricingDetailsUserJsonArray.getJSONObject(priceasgnusr);

											if (pricingDetailsUserJSONObject.get("delete") instanceof JSONArray) {
												JSONArray deleteObjArray = new JSONArray(pricingDetailsUserJSONObject.getJSONArray("delete"));
												System.out.println("deleteObjArray() -> " + deleteObjArray);

												for (int singleRemove = 0; singleRemove < deleteObjArray.length(); singleRemove++) {

													String singleRemoveRecord = deleteObjArray.getString(singleRemove).trim();

													pricingDetailsJSONObject.remove(singleRemoveRecord);

													System.out.println(priceasgnusr + "pricingDetailsJSONObject remove:-" + singleRemoveRecord);

												}
											}

											System.out.println("11 pricingDetailsUserJSONObject.length():-" + pricingDetailsUserJSONObject.length());
											if (pricingDetailsUserJSONObject.length() > 1) {
												pricingDetailsUserJSONObject.keySet().forEach(keyStr -> {
													Object keyvalue = pricingDetailsUserJSONObject.get(keyStr);
													System.out.println("Pricing key: " + keyStr + "Pricing value: " + keyvalue);
													if (!keyStr.toString().trim().equals("delete")) {
														pricingDetailsJSONObject.put(keyStr, keyvalue);
													}
												});
											}
										}

									}



									if (pricingAndCommitmentsUserDetails.toString().trim().contains("commitmentDetails")) {

										if (pricingDetailsJSONObject.get("priceCompDetails") instanceof JSONObject) {

											JSONObject commitmentDetailsUserJSONObject = pricingAndCommitmentsUserDetails.getJSONObject("commitmentDetails");

											pricingDetailsJSONObject.put("commitmentDetails", commitmentDetailsUserJSONObject);

										} else if (pricingDetailsJSONObject.get("priceCompDetails") instanceof JSONArray) {

											JSONArray commitmentDetailsUserJSONObject = new JSONArray(pricingAndCommitmentsUserDetails.getJSONArray("commitmentDetails"));

											pricingDetailsJSONObject.put("commitmentDetails", commitmentDetailsUserJSONObject);
										}


									}


								}

							}


							//System.out.println("<<<<<<pricingDetailsJSONObject>>>"+pricingDetailsJSONObject);


							if (pricingDetailsJSONObject.get("priceCompDetails") instanceof JSONArray) {
								//System.out.println("<<<<< | JSONObject | >>>>>");

								JSONArray priceCompDetailsArray = new JSONArray(pricingDetailsJSONObject.getJSONArray("priceCompDetails"));
								//System.out.println("priceCompDetailsArray.length():-"+priceCompDetailsArray.length());

								for (int ixt = 0; ixt < priceCompDetailsArray.length(); ixt++) {


									JSONObject priceCompDetails = priceCompDetailsArray.getJSONObject(ixt);

									//System.out.println("[[ priceCompDetails:-"+priceCompDetails);

									JSONObject priceCompTier = priceCompDetails.getJSONObject("priceCompTier");
									String upperLimit = (String) priceCompTier.get("upperLimit").toString().trim();
									String lowerLimit = (String) priceCompTier.get("lowerLimit").toString().trim();

									String valueAmt = null;

									JSONArray UsersRateArray = new JSONArray(sRate);

									for (int SingleUsersRate = 0; SingleUsersRate < UsersRateArray.length(); SingleUsersRate++) {

										JSONObject SinglepriceCompDetails = UsersRateArray.getJSONObject(ixt);
										String SingleUserupperLimit = (String) SinglepriceCompDetails.get("upperLimit").toString().trim();
										String SingleUserlowerLimit = (String) SinglepriceCompDetails.get("lowerLimit").toString().trim();
										String SingleUservalueAmt = (String) SinglepriceCompDetails.get("valueAmt").toString().trim();
										//System.out.println("SingleUserupperLimit:-"+SingleUserupperLimit+" || upperLimit:-"+upperLimit);
										//System.out.println("SingleUserlowerLimit:-"+SingleUserlowerLimit+" || lowerLimit:-"+lowerLimit);
										//System.out.println("--SingleUservalueAmt---"+SingleUservalueAmt);
										if (upperLimit.equals(SingleUserupperLimit) && lowerLimit.equals(SingleUserlowerLimit)) {
											valueAmt = SingleUservalueAmt;
											//System.out.println("SingleUservalueAmt:-"+SingleUservalueAmt+" || valueAmt:-"+valueAmt);
											break;
										}


									}

									if (!valueAmt.equalsIgnoreCase("NoValue")) {
										priceCompDetails.put("valueAmt", valueAmt);
									}
									//System.out.println("<<< UsersRate >>>" + UsersRateArray);

									//priceCompDetails.put("valueAmt", "0.51");

								}

							} else if (pricingDetailsJSONObject.get("priceCompDetails") instanceof JSONObject) {
								//System.out.println("<<<<< | JSONArray | >>>>>");

								JSONObject priceCompDetails = pricingDetailsJSONObject.getJSONObject("priceCompDetails");

								if (!sRate.equalsIgnoreCase("NoValue")) {
									priceCompDetails.put("valueAmt", sRate);
								}

								//								if(PricingDetailsJsonArray.length() == 1 && (pricingStatus.equalsIgnoreCase("ACTV") || pricingStatus.equalsIgnoreCase("PRPD"))) {
								//									pricingDetailsJSONObject.put("pricingStatus", "PRPD");
								//								}
							}

						} else {
							PricingDetailsJsonArray.remove(i);
						}
					}


					pricingAndCommitmentsDetails.put("pricingDetails", PricingDetailsJsonArray);



					String OverridePricing = DealPriceAsgnCommitmentsIWS.toString();

					System.out.println("Final Override Pricing Json :-" + OverridePricing);


					String OverridePricingResponse = FnOvrdandUpdPricingAndCommitmentIWS(OverridePricing, sDealPricingAndCommitmentResource, sContentTypeHeader, sAcceptTypeHeader, sActionFlag, sErrorMessageFlag);

					System.out.println("Final Override Pricing Response 1 :-" + OverridePricingResponse);

					try {
				        FileWriter myWriter = new FileWriter("./databank/banking/deal_management/"+BaseTest.sScriptName+"_OVRDOrUPDPricingResponse.txt");
				        myWriter.write(OverridePricingResponse);
				        myWriter.close();
				        System.out.println("Successfully wrote to theOverridePricingResponse file.");
				      } catch (IOException e) {
				        System.out.println("An error occurred.");
				        e.printStackTrace();
				      }

					CF.FnWriteCellValue(iStartingRow, 47, OverridePricing, sSheetName, sWorkbook);
					CF.FnWriteCellValue(iStartingRow, 48, OverridePricingResponse, sSheetName, sWorkbook);


				} else {
//					CF.FnWriteCellValue(iStartingRow, 47, sReadPricing, sSheetName, sWorkbook);
//					CF.FnWriteCellValue(iStartingRow, 48, GetResponse, sSheetName, sWorkbook);
				}










			} else {
				System.out.println("Deal Pricing & Commitment Data not available in db");
			}

		} catch (Exception e) {
			System.out.println("Exception in Application Function ==> FileRecordTypeUploadFileRequestTableVerification");
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}



	/*'############################################### WEBSERVICES COMMON FUNCTIONS #############################################################################################################################################################################################	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnOvrdandUpdPricingAndCommitmentIWS
	,Description          : To Overide and Update pricing and commitment
	'###############################################################################################################################################################################################################################################*/

	public String FnOvrdandUpdPricingAndCommitmentIWS(String InputJson, String sDealPricingAndCommitmentResource, String sContentTypeHeader, String sAcceptTypeHeader, String sActionFlag, String sErrorMessageFlag) throws Exception {
		System.out.println("*--*--*--FnOvrdandUpdPricingAndCommitmentIWS--*--*--*" + InputJson);

		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;
		String FnDealPricingAndCommitmentResp = null;
		String sValue = null, sDealId = null, sModelId = null;


		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sDealPricingAndCommitmentResource, InputJson, sContentTypeHeader, sAcceptTypeHeader);

			int iStatusCode = WF.FnGetStatusCodeFromResponse();

			FnDealPricingAndCommitmentResp = BaseTest.respBody.asString();

			System.out.println("FnDealPricingAndCommitmentResp:-" + FnDealPricingAndCommitmentResp);

			System.out.println("C1-DealPriceAsgnCommitmentsREST ApiResp :-" + FnDealPricingAndCommitmentResp);
			System.out.println("iStatusCode:-" + iStatusCode);
			if (iStatusCode == iSuccessStatusCode) {

				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed Successfully ! Deal READ Request is :" + InputJson);

				sValue = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST");
				//System.out.println("Pricing And Commitment Performed Successfully !  response message is : " + sValue);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Deal response message is :" + sValue);

				sDealId = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST.dealId"); // dealId
				//System.out.println("Pricing And Commitment Performed Successfully ! Deal ID is : " + sDealId);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Deal ID is : " + sDealId);

				sModelId = WF.FnGetDataFromResponse("C1-DealPriceAsgnCommitmentsREST.modelId"); //modelId
				//System.out.println("Pricing And Commitment Performed Successfully ! Model ID is : " + sModelId);
				//CF.FnTestCaseStatusReport("Pass", ""+sActionFlag+" Pricing And Commitment Performed Successfully ! Model ID is : " + sModelId);

				System.out.println("sErrorMessageFlag:-" + sErrorMessageFlag);
				if (sErrorMessageFlag.equalsIgnoreCase("NoValue")) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed Successfully !! sDealId :- " + sDealId);
				} else if (sErrorMessageFlag.equalsIgnoreCase("No Price Assignment for Model")) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed Successfully !! sDealId :- " + sDealId);
				} else {
					CF.FnTestCaseStatusReport("Fail", "" + sActionFlag + " Pricing And Commitment TestCase Failed Expected Result : " + sErrorMessageFlag + " !! sDealId :- " + sDealId);
				}


			} else if (iStatusCode == iErrorStatusCode) {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("* " + sActionFlag + " Pricing And Commitment Not Performed : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at last so we can return text
				System.out.println("* " + sActionFlag + " Pricing And Commitment Not Performed ! Reason Is : " + sValue);

				System.out.println("*sErrorMessageFlag:-" + sErrorMessageFlag + " || sValue:-" + sValue);

				if (!sErrorMessageFlag.equalsIgnoreCase("NoValue") && sErrorMessageFlag.equalsIgnoreCase(sValue)) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed ! Reason Is : " + sValue);
				} else if (sErrorMessageFlag.equalsIgnoreCase("No Price Assignment for Model")) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed ! Reason Is : " + sValue);
				} else {
					CF.FnTestCaseStatusReport("Fail", "" + sActionFlag + " Pricing And Commitment Not Performed ! Reason Is : " + sValue);
				}

			} else {

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				//System.out.println("Read Pricing And Commitment Not Performed ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! messageText Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! Message Number Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemDetailDocument.problemType");
				//System.out.println("Read Pricing And Commitment Not Performed ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Not Performed ! problemType Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at end so we can return title text
				//System.out.println("Read Pricing And Commitment Not Performed ! Reason Is : " + sValue);

				System.out.println("* sValueFromApplication:-" + sValue + " || sErrorMessageFlag:-" + sErrorMessageFlag);



				if (!sErrorMessageFlag.equalsIgnoreCase("NoValue") && sErrorMessageFlag.equalsIgnoreCase(sValue)) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed ! Reason Is : " + sValue);
				} else if (sErrorMessageFlag.equalsIgnoreCase("No Price Assignment for Model")) {
					CF.FnTestCaseStatusReport("Pass", "" + sActionFlag + " Pricing And Commitment Performed ! Reason Is : " + sValue);
				} else {
					CF.FnTestCaseStatusReport("Fail", "" + sActionFlag + " Pricing And Commitment Not Performed ! Reason Is : " + sValue);
				}

			}

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return FnDealPricingAndCommitmentResp;




	}








	/////////////////////////NEW IWS END///////////////////////


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAddBillableCharge
	,Description          : To Add/ Update Billable charge on Account
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public void FnAddBillableCharge(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {

		System.out.println(">>>>>>>>>>--FnAddBillableCharge");

		String sPersonName = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		String sPersonAccount = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		String sContractId = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		String sPriceItem = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		String sPriceItemParameter1 = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		String sPriceItemParameterValue1 = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		String sPriceItemParameter2 = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		String sPriceItemParameterValue2 = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();

		String sBillableChargeStartDate = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		String sBillableChargeEndDate = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		String sServiceQtyIdentifier = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		String sServiceQty = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
		String sBillableChargeId = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();

		String sActionFlag = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();

		System.out.println("sPersonName:-" + sPersonName);
		System.out.println("sPersonAccount:-" + sPersonAccount);
		System.out.println("sContractId:-" + sContractId);
		System.out.println("sPriceItem:-" + sPriceItem);
		System.out.println("sBillableChargeStartDate:-" + sBillableChargeStartDate);
		System.out.println("sBillableChargeEndDate:-" + sBillableChargeEndDate);
		System.out.println("sServiceQtyIdentifier:-" + sServiceQtyIdentifier);
		System.out.println("sServiceQty:-" + sServiceQty);
		System.out.println("sBillableChargeId:-" + sBillableChargeId);


		try {

			Date sBillableChargeStartDateOld = new SimpleDateFormat("mm-dd-yyyy").parse(sBillableChargeStartDate);
		    String sBillableChargeStartDate1 = new SimpleDateFormat("dd-MMM-yy").format(sBillableChargeStartDateOld);
		    System.out.println("sBillableChargeStartDate1 = "+sBillableChargeStartDate1);

			Date sBillableChargeEndDateOld = new SimpleDateFormat("mm-dd-yyyy").parse(sBillableChargeEndDate);
		    String sBillableChargeEndDate1 = new SimpleDateFormat("dd-MMM-yy").format(sBillableChargeEndDateOld);

			String IsBillableChargeAdded = (String) DataBaseFunctions.FnGetDBColumnValue("SELECT COUNT(*) FROM CI_BILL_CHG FULL JOIN CI_BCHG_SQ ON CI_BILL_CHG.BILLABLE_CHG_ID = CI_BCHG_SQ.BILLABLE_CHG_ID WHERE SA_ID = '"+sContractId+"' and PRICEITEM_CD = '"+sPriceItem+"' and SVC_QTY = '"+sServiceQty+"'", "COUNT(*)", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort"));

			if (IsBillableChargeAdded.equalsIgnoreCase("0")) {
				
			Thread.sleep(2000);

			AF.FnNavigation(driver, "BillableCharge");

			Thread.sleep(5000);

			CF.FnSetFrame(driver, "tabPage");

			Thread.sleep(5000);


			if (sActionFlag.equalsIgnoreCase("ADD")) {

				//enter contract id 
				CF.FnSetTextByKeyPressEnter(driver, DealManagementPageElements.SearchContractIdOnBillableCharge, sContractId);

				Thread.sleep(5000);

				//StartDate
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeStartDate, sBillableChargeStartDate);

				//EndDate
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeEndDate, sBillableChargeEndDate);

				CF.FnSetFrame(driver, "tabMenu");

				//SQ Details Tab Navigation
				CF.FnElementClick(driver, DealManagementPageElements.NavigateToSQDetailsTabInBillableCharge);

				Thread.sleep(5000);

				CF.FnSetFrame(driver, "BCHG_LN_CHAR");

				Thread.sleep(3000);

				//Service Qty identifier
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeServiceQtyIdentifier, sServiceQtyIdentifier);

				//Service QTY
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeServiceQty, sServiceQty);


				CF.FnSetFrame(driver, "tabMenu");

				//Navigate to Pricing Information Tab
				CF.FnElementClick(driver, DealManagementPageElements.NavigateToPricingInfoTabInBillableCharge);

				Thread.sleep(2000);

				CF.FnSetFrame(driver, "tabPage");

				Thread.sleep(3000);

				CF.FnSetText(driver, DealManagementPageElements.BillableChargePriceItem, sPriceItem);


				if (!sPriceItemParameter1.equalsIgnoreCase("NoValue")) {
					CF.FnSetFrame(driver, "BCHG_LN_CHAR");

					Thread.sleep(1000);

					CF.FnSelectValue(driver, DealManagementPageElements.BillableChargePriceItemParameter1, sPriceItemParameter1);
					CF.FnSetText(driver, DealManagementPageElements.BillableChargePriceItemParameterValue1, sPriceItemParameterValue1);
				}

				if (!sPriceItemParameter2.equalsIgnoreCase("NoValue")) {

					CF.FnElementClick(driver, DealManagementPageElements.BillableChargeAddPriceItemParameter);
					Thread.sleep(1000);
					CF.FnSelectValue(driver, DealManagementPageElements.BillableChargePriceItemParameter2, sPriceItemParameter2);
					CF.FnSetText(driver, DealManagementPageElements.BillableChargePriceItemParameterValue2, sPriceItemParameterValue2);

				}


				CF.FnSetFrame(driver, "main");

				Thread.sleep(2000);

				//Save Billable Charge
				CF.FnElementClick(driver, DealManagementPageElements.BillableChargeSaveButton);

				CF.FnSetFrame(driver, "tabPage");

				Thread.sleep(2000);

				String BillableChargeIdFromApplication = CF.FnGetTextFromElement(driver, DealManagementPageElements.BillableChargeId, "value");

				if (!BillableChargeIdFromApplication.equals("")) {
					CF.FnWriteCellValue(iStartingRow, 14, BillableChargeIdFromApplication, sSheetName, sWorkbook);
					CF.FnTestCaseStatusReport("Pass", "Billable Charge Added Successfully on Person :-" + sPersonName + " for Price Item :-" + sPriceItem);
				} else {
					CF.FnTestCaseStatusReport("Fail", "Billable Charge Not Added on Person :-" + sPersonName + " for Price Item :-" + sPriceItem);
				}


			} else if (sActionFlag.equalsIgnoreCase("UPD")) {

				//enter contract id 
				CF.FnSetTextByKeyPressEnter(driver, DealManagementPageElements.SearchContractIdOnBillableCharge, sContractId);

				Thread.sleep(5000);

				//enter Billable Charge id 
				CF.FnSetTextByKeyPressEnter(driver, DealManagementPageElements.EnterBillableChargeIdOnBillableChargeScreen, sBillableChargeId);

				Thread.sleep(5000);

				//StartDate
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeStartDate, sBillableChargeStartDate);

				//EndDate
				CF.FnSetText(driver, DealManagementPageElements.BillableChargeEndDate, sBillableChargeEndDate);

				CF.FnSetFrame(driver, "main");

				Thread.sleep(3000);

				//Save Billable Charge
				CF.FnElementClick(driver, DealManagementPageElements.BillableChargeSaveButton);

				CF.FnTestCaseStatusReport("Pass", "Billable Charge Updated Successfully on Person :-" + sPersonName + " for Price Item :-" + sPriceItem);


			}
		 }
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnRefreshDeal
	,Description          : Function to Refresh Deal Information
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public void FnRefreshDeal(WebDriver driver) throws Exception {
		System.out.println(" || >>>>>>>>>>-- FnRefreshDeal --<<<<<<<<<<< ||");
		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails);


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}

	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnApproveDealUsingIWS
	,Description          : To Approved Deal Using IWS Request
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/

	public String FnApproveDealUsingIWS(String sSendDealForApprovalResource, String SendDealForApprovalJson, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnApproveDealUsingIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, SendDealForApprovalJson, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			System.out.println("<<<<<---- FnApproveDealUsingIWS iStatusCode ---->>>>>" + iStatusCode);

			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Deal Approved Using IWS Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Approved Using IWS Request Is completed !  Deal ID is : " + sDealId);

			} else if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Is not completed due to 400 Error");
			} else {

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Deal Approved Using IWS Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Deal Approved Using IWS Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Deal Approved Using IWS Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Approved Using IWS Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Approved Using IWS Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Approved Using IWS Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode != iSuccessStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}



	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnFinalizeDealUsingIWS
	,Description          : To Finalize Deal Using IWS Request
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/

	public String FnFinalizeDealUsingIWS(String sSendDealForApprovalResource, String SendDealForApprovalJson, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnFinalizeDealUsingIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, SendDealForApprovalJson, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			System.out.println("<<<<<---- FnFinalizeDealUsingIWS iStatusCode ---->>>>>" + iStatusCode);

			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Deal Finalize Using IWS Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Finalize Using IWS Request Is completed !  Deal ID is : " + sDealId);

			} else if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Is not completed due to 400 Error");
			} else {

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Deal Finalize Using IWS Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Deal Finalize Using IWS Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Deal Finalize Using IWS Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Finalize Using IWS Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Finalize Using IWS Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Finalize Using IWS Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode != iSuccessStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}




	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnAcceptDealUsingIWS
	,Description          : To Customer Accept Deal Using IWS Request
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/

	public String FnAcceptDealUsingIWS(String sSendDealForApprovalResource, String SendDealForApprovalJson, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnAcceptDealUsingIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, SendDealForApprovalJson, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			System.out.println("<<<<<---- FnAcceptDealUsingIWS iStatusCode ---->>>>>" + iStatusCode);

			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Customer Accept Deal Using IWS Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Customer Accept Deal Using IWS Request Is completed !  Deal ID is : " + sDealId);

			} else if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Is not completed due to 400 Error");
			} else {

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Customer Accept Deal Using IWS Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Customer Accept Deal Using IWS Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Customer Accept Deal Using IWS Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Customer Accept Deal Using IWS Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Customer Accept Deal Using IWS Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Customer Accept Deal Using IWS Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode != iSuccessStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnReturnDealToSubmitterUsingIWS
	,Description          : To Return Deal To Submitter Using IWS Request
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/

	public String FnReturnDealToSubmitterUsingIWS(String sSendDealForApprovalResource, String SendDealForApprovalJson, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("<<<<<---- FnReturnDealToSubmitterUsingIWS ---->>>>>");

		String sValue = null, sDealId = null, sErrorMessage = null;

		int iErrorStatusCode = 400; //iBadRequestCode
		int iSuccessStatusCode = 200;
		int iStatusCode = 0;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sSendDealForApprovalResource, SendDealForApprovalJson, sContentTypeHeader, sAcceptTypeHeader);

			iStatusCode = WF.FnGetStatusCodeFromResponse();
			System.out.println("<<<<<---- FnReturnDealToSubmitterUsingIWS iStatusCode ---->>>>>" + iStatusCode);

			if (iStatusCode == iSuccessStatusCode) {

				System.out.println("Pass Request Status : " + iStatusCode);
				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sDealId = WF.FnGetDataFromResponse("C1-DealREST.dealOperation.dealId");
				System.out.println("Return Deal To Submitter Using IWS Request Is completed ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Return Deal To Submitter Using IWS Request Is completed !  Deal ID is : " + sDealId);

			} else if (iStatusCode == iErrorStatusCode) {
				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Is not completed due to 400 Error");
			} else {

				sErrorMessage = WF.FnGetDataFromResponse("problemDetailDocument.title");
				System.out.println("Return Deal To Submitter Using IWS Request Not Completed ! Error Message is : " + sErrorMessage);
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Not Completed ! Error Message is :" + sErrorMessage);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.problemType");
				System.out.println("Return Deal To Submitter Using IWS Request Not Completed ! Problem Type is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Not Completed ! Problem Type is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
				System.out.println("Return Deal To Submitter Using IWS Request Not Completed ! Message Category is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Not Completed ! Message Category is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Return Deal To Submitter Using IWS Request Not Completed ! Message Nbr is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Not Completed ! Message Nbr is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Return Deal To Submitter Using IWS Request Completed ! Message Text is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Return Deal To Submitter Using IWS Request Not Completed ! Message Text is : " + sValue);

			}
		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		if (iStatusCode != iSuccessStatusCode) {
			return sErrorMessage;
		} else {
			return sDealId;
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigateToAccountViewAndEditPricing
	,Description          : To Navigate Specific Person/Account Pricing and Commitment screen
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigateToAccountViewAndEditPricing(String PersonAccountName) throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigateToAccountViewAndEditPricing--<<R");

		try {

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_ExpandAll_Button);
			Thread.sleep(3000);

			String PersonAccountNamePath = DealManagementPageElements.Deal_Information_ViewAndEditPricing_PencilIcon_Of_Specific_Account.replaceAll("ReplacePersonAccountName", PersonAccountName);
			System.out.println("PersonAccountNamePath  :- " + PersonAccountNamePath);
			CF.FnElementClick(driver, PersonAccountNamePath);
//			CF.FnClickOnElementByJavaScriptExecutor(driver, CF.FnGetWebElement(driver, "XPATH", PersonAccountNamePath));
			Thread.sleep(5000);
			CF.FnGetWebElement(driver, "LINKTEXT", DealManagementPageElements.Deal_Information_ViewAndEditPricing_PencilIcon).click();
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(5000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			CF.FnTestCaseStatusReport("Pass", "Navigation to '" + PersonAccountName + "' Pricing and Commitment Screen Is Completed Successfully");
		
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}





	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem
	,Description          : Function To Verify Project & Original Pricing and Commitment Details for Specific/Given Price item
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public void FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println("<<<<<<<<<<----FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem---->>>>>>>>>>" + iStartingRow);

		String sPriceItemFromApplication, sStartDateFromApplication, sEndDateFromApplication, sParameterFromApplication, sPricingInformationFromApplication, sAveragePriceFromApplication, sVolumeFromApplication, sRevenueFromApplication, sVariationFromApplication, sCostFromApplication, sPriceListFromApplication, sLevelFromApplication, sStatusFromApplication, sApproverFromApplication = "";

		String sPriceItem = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		String sPriceItemSection = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		String sStartDate = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		String sEndDate = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		String sParameter = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		String sPricingInformation = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		String sAveragePrice = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		String sVolume = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		String sRevenue = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		String sVariation = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		String sCost = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
		String sPriceList = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
		String sLevel = CF.FnGetCellValue(iStartingRow, 15, sSheetName, sWorkbook).toString().trim();
		String sStatus = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();
		String sApprover = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();


		System.out.println(iStartingRow + "-> sPriceItem :- " + sPriceItem);
		System.out.println(iStartingRow + "-> sStartDate :- " + sStartDate);
		System.out.println(iStartingRow + "-> sEndDate :- " + sEndDate);
		System.out.println(iStartingRow + "-> sParameter :- " + sParameter);
		System.out.println(iStartingRow + "-> sPricingInformation :- " + sPricingInformation);
		System.out.println(iStartingRow + "-> sAveragePrice :- " + sAveragePrice);
		System.out.println(iStartingRow + "-> sVolume :- " + sVolume);
		System.out.println(iStartingRow + "-> sRevenue :- " + sRevenue);
		System.out.println(iStartingRow + "-> sVariation :- " + sVariation);
		System.out.println(iStartingRow + "-> sCost :- " + sCost);
		System.out.println(iStartingRow + "-> sPriceList :- " + sPriceList);
		System.out.println(iStartingRow + "-> sLevel :- " + sLevel);
		System.out.println(iStartingRow + "-> sStatus :- " + sStatus);
		System.out.println(iStartingRow + "-> sApprover :- " + sApprover);


		try {


			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Pricing_And_Commitments_Title_HeadingText);
			Thread.sleep(1000);

			int isCompareTypeSelected = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath).size();
			if(isCompareTypeSelected == 0) {
			
			int recordFound = 	CF.getListWebElements(driver, "XPATH", "//oj-select-single[@id='compareType']").size();
			System.out.println("recordFound-"+recordFound);
			for (WebElement singleRate: CF.getListWebElements(driver, "XPATH", "//oj-select-single[@id='compareType']")) {
				if(singleRate.isDisplayed()) {
					singleRate.click();
					Thread.sleep(2000);
//					CF.FnGetWebElement(driver, "ID", "compareType").click();
					Thread.sleep(3000);
					CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath);
					Thread.sleep(3000);
					CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
					Thread.sleep(5000);

					}
					

				}
			}



//			int isCompareTypeSelected = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath).size();
//			if(isCompareTypeSelected == 0) {
//			WebElement scroll1 = CF.FnGetWebElement(driver, "ID", "compareType");
//			CF.FnScrollToElement(driver, scroll1);
//			Thread.sleep(200);
//			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareType_Path);
//			Thread.sleep(3000);
//			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath);
//			Thread.sleep(3000);
//			}
			
//			int isCompareTypeSelected = CF.getListWebElements(driver, "XPATH", DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath).size();
//			if(isCompareTypeSelected == 0) {
//			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareType_Path);
////			Thread.sleep(2000);
////			CF.FnGetWebElement(driver, "ID", "compareType").click();
//			Thread.sleep(3000);
//			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Screen_CompareTypeProposedAndOriginalValuePath);
//			Thread.sleep(3000);
//			}
//			CF.FnElementClick(driver, DealManagementPageElements.Pricing_And_Commitments_ExpandAll_Button);
//			Thread.sleep(5000);



			String PriceItemPath = DealManagementPageElements.Deal_Pricing_And_Commitment_Information_For_Specific_Price_Item;
			System.out.println("PriceItemPath 1 :- " + PriceItemPath);

			String TempParameter = sParameter;
			if (TempParameter.equals("NoValue")) {
				TempParameter = "";
			}
			PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemName", sPriceItem);
			PriceItemPath = PriceItemPath.replaceAll("ReplacePriceItemParameter", TempParameter);

			System.out.println("PriceItemPath 2 :- " + PriceItemPath);

			if (sPriceItemSection.equalsIgnoreCase("Projected")) {

				System.out.println("<-------->  Projected  <--------->");



				if (!sStartDate.equals("NoValue")) {
					sStartDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_StartDate.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sStartDate :- " + sStartDate + " || sStartDateFromApplication :- " + sStartDateFromApplication);
					assertEquals(sStartDateFromApplication, sStartDate);
				}

				if (!sEndDate.equals("NoValue")) {
					sEndDateFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_EndDate.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sEndDate :- " + sEndDate + " || sEndDateFromApplication :- " + sEndDateFromApplication);
					assertEquals(sEndDateFromApplication, sEndDate);
				}

				if (!sParameter.equals("NoValue")) {
					sParameterFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Parameter.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sParameter :- " + sParameter + " || sParameterFromApplication :- " + sParameterFromApplication);
					assertEquals(sParameterFromApplication, sParameter);
				}

//				if (!sPricingInformation.equals("NoValue")) {
//					sPricingInformationFromApplication = CF.FnGetWebElement(driver, "XPATH", PriceItemPath + "/following-sibling::td[7]").getText().trim();
//					System.out.println("sPricingInformation :- " + sPricingInformation + " || sPricingInformationFromApplication :- " + sPricingInformationFromApplication);
//					assertEquals(sPricingInformationFromApplication, sPricingInformation);
//				}

				if (!sAveragePrice.equals("NoValue")) {
					sAveragePriceFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_AveragePrice.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sAveragePrice :- " + sAveragePrice + " || sAveragePriceFromApplication :- " + sAveragePriceFromApplication);
					assertEquals(sAveragePriceFromApplication, sAveragePrice);
				}

				if (!sVolume.equals("NoValue")) {
					sVolumeFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Volume.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getAttribute("value").trim().replaceAll(",", "");
					System.out.println("sVolume :- " + sVolume + " || sVolumeFromApplication :- " + sVolumeFromApplication);
					assertEquals(sVolumeFromApplication, sVolume);
				}


				if (!sRevenue.equals("NoValue")) {
					sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Revenue.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sRevenue :- " + sRevenue + " || sRevenueFromApplication :- " + sRevenueFromApplication);
					assertEquals(sRevenueFromApplication, sRevenue);
				}


				if (!sVariation.equals("NoValue")) {
					sVariationFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_RevenueVariation.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sVariation :- " + sVariation + " || sVolumeFromApplication :- " + sVariationFromApplication);
					assertEquals(sVariationFromApplication, sVariation);
				}

				if (!sCost.equals("NoValue")) {
					sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Cost.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sCost :- " + sCost + " || sVolumeFromApplication :- " + sCostFromApplication);
					assertEquals(sCostFromApplication, sCost);
				}

				if (!sPriceList.equals("NoValue")) {
					sPriceListFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_PriceList.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sPriceList :- " + sPriceList + " || sVolumeFromApplication :- " + sPriceListFromApplication);
					assertEquals(sPriceListFromApplication.toLowerCase(), sPriceList.toLowerCase());
				}

				if (!sLevel.equals("NoValue")) {
					sLevelFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_AssignmentLevel.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sLevel :- " + sLevel + " || sVolumeFromApplication :- " + sLevelFromApplication);
					assertEquals(sLevelFromApplication.toLowerCase(), sLevel.toLowerCase());
				}

				if (!sStatus.equals("NoValue")) {
					sStatusFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Status.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getAttribute("title").trim();
					System.out.println("sStatus :- " + sStatus + " || sStatusFromApplication :- " + sStatusFromApplication);
//					assertEquals(sStatusFromApplication.toLowerCase(), sStatus.toLowerCase());
				}

				if (!sApprover.equals("NoValue")) {
					sVolumeFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Approver.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim();
					System.out.println("sApprover :- " + sApprover + " || sVolumeFromApplication :- " + sApproverFromApplication);
					assertEquals(sApproverFromApplication.toLowerCase(), sApprover.toLowerCase());
				}

			} else if (sPriceItemSection.equalsIgnoreCase("Original")) {

				//Open Original Section for Specific Price Item Button
//				CF.FnElementClick(driver, PriceItemPath + "/following-sibling::td[6]//button");

				System.out.println("<-------->  Original  <--------->");
				Thread.sleep(500);

				if (!sAveragePrice.equals("NoValue")) {
					sAveragePriceFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Original_AveragePrice.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sAveragePrice :- " + sAveragePrice + " || sAveragePriceFromApplication :- " + sAveragePriceFromApplication);
					assertEquals(sAveragePriceFromApplication, sAveragePrice);
				}

				if (!sRevenue.equals("NoValue")) {
					sRevenueFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Original_Revenue.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sRevenue :- " + sRevenue + " || sRevenueFromApplication :- " + sRevenueFromApplication);
					assertEquals(sRevenueFromApplication, sRevenue);
				}

				if (!sCost.equals("NoValue")) {
					sCostFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Original_Cost.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sCost :- " + sCost + " || sVolumeFromApplication :- " + sCostFromApplication);
					assertEquals(sCostFromApplication, sCost);
				}

				if (!sPriceList.equals("NoValue")) {
					sPriceListFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Original_PriceList.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sPriceList :- " + sPriceList + " || sVolumeFromApplication :- " + sPriceListFromApplication);
					assertEquals(sPriceListFromApplication.toLowerCase(), sPriceList.toLowerCase());
				}

				if (!sLevel.equals("NoValue")) {
					sLevelFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Pricing_And_Commitment_Original_AssignmentLevel.replaceAll("ReplacePriceItemName", sPriceItem).replaceAll("ReplacePriceItemParameterName", TempParameter)).getText().trim().replaceAll(",", "");
					System.out.println("sLevel :- " + sLevel + " || sVolumeFromApplication :- " + sLevelFromApplication);
					assertEquals(sLevelFromApplication.toLowerCase(), sLevel.toLowerCase());
				}

			}


			CF.FnTestCaseStatusReport("Pass", "" + sPriceItemSection + " Pricing And Commitment Verified Successfully for Price Item :- " + sPriceItem);


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}



	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreateDealVersionFromDealInformation
	,Description          : To Deal Version Creation
	,Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public void FnCreateDealVersionFromDealInformation(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(">>>>>>>>>>--FnCreateDealVersionFromDealInformation");

		String sDealVersionDesc, sDealVersionCreationMethod;

		sDealVersionDesc = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		sDealVersionCreationMethod = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();

		try {
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_CreateDealVersion_Button);
			Thread.sleep(5000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Version_PageTitle_Label);

			if (!sDealVersionDesc.equalsIgnoreCase("NoValue")) {
				CF.FnSetText(driver, DealManagementPageElements.Deal_Version_Description, sDealVersionDesc);
			}

			if (!sDealVersionCreationMethod.equalsIgnoreCase("NoValue")) {
				CF.FnSelectValue(driver, DealManagementPageElements.Deal_Version_Creation_Method, sDealVersionCreationMethod);
			}

			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(2000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Version_Save_Button);
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Deal Version Creation for '" + sDealVersionCreationMethod + "' Is Completed Successfully");

		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}


	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnGetCurrentDealVersionFromDealInformation
	,Description          : To Get Current Deal Version Model Id
    'Created by           : Rohit Thik	
	'###############################################################################################################################################################################################################################################*/
	public String FnGetCurrentDealVersionFromDealInformation(WebDriver driver) throws Exception {
		System.out.println(">>>>>>>>>>--FnGetCurrentDealVersionFromDealInformation");

		String sCurrentModelIdFromApplication = "";
		try {

			Thread.sleep(5000);
			CF.FnSetFrame(driver, "main");
			Thread.sleep(5000);
			CF.FnElementClick(driver, DealManagementPageElements.Deal_Information_Refresh_Button);
			Thread.sleep(10000);

			CF.FnSetFrame(driver, "zoneMapFrame_1");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoSeaction_TextDetails);

			sCurrentModelIdFromApplication = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Version_Information).getAttribute("innerText");
			System.out.println("1st :-" + sCurrentModelIdFromApplication);
			sCurrentModelIdFromApplication = sCurrentModelIdFromApplication.split(",")[0];
			System.out.println("sCurrentModelIdFromApplication :->" + sCurrentModelIdFromApplication);
			CF.FnTestCaseStatusReport("Pass", "Latest Deal Version '" + sCurrentModelIdFromApplication + "' Fetch Successfully");


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
		return sCurrentModelIdFromApplication;
	}


	/*'###############################################################
	'Function Name        : FnUpdateAlgorithmValue
	'Function Description : Update ParameterValue for given algo
	'Input Parameters  
	'                     
	'Output Parameters    : Not Applicable
	'Created by           : Rohit Thik
	'################################################################*/
	public void FnUpdateAlgorithmValue(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println("||>>>>>>>>>>--FnUpdateAlgorithmValue--<<<<<<<<<<<<||" + iStartingRow);

		try {
			String sParameterValues, sParameter, sAlgoCode;

			sAlgoCode = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
			sParameter = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
			sParameterValues = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				//Navigate to Algorithm Page
				CF.FnSetFrame(driver, "main");
				Thread.sleep(5000);
				CF.FnElementClick(driver, CommonPageElements.ORMB_TopMenu_Home_Button);
				Thread.sleep(3000);
				CF.FnElementClick(driver, CommonPageElements.ORMB_TopMenu_Admin_Button);
				Thread.sleep(3000);
				CF.FnElementClick(driver, DealManagementPageElements.AdminMenuAoption);
				Thread.sleep(3000);
				CF.FnElementClick(driver, DealManagementPageElements.AdminMenuAlgorithmOption);
				Thread.sleep(3000);
				CF.FnElementClick(driver, DealManagementPageElements.AdminMenuAlgorithmSearchOption);
				Thread.sleep(5000);

				CF.FnSetFrame(driver, "tabPage");
				Thread.sleep(3000);

				CF.FnSetText(driver, DealManagementPageElements.search_Algorithm_TextBox, sAlgoCode);
				Thread.sleep(5000);
				CF.FnElementClick(driver, DealManagementPageElements.Search_Algorithm_Code);
				Thread.sleep(5000);
				CF.FnElementClick(driver, DealManagementPageElements.Algorithm_Description_Navigation_link);
				Thread.sleep(5000);

				//Switch to tabPage
				CF.FnSetFrame(driver, "zoneMapFrame_1");
				Thread.sleep(4000);

				CF.FnElementClick(driver, DealManagementPageElements.Algorithm_parameter_frame_Edit_button_click);

				Thread.sleep(8000);

				//Switch to uiMap
				CF.FnSetFrame(driver, "uiMap");
				Thread.sleep(4000);
				
				WebElement scroll = CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Algorithm_Save_Button);
				CF.FnScrollToElement(driver, scroll);
				Thread.sleep(5000);
				
				String ParameterPath = DealManagementPageElements.Algorithm_Parameter_Value_Field.replaceAll("ReplaceParameter", sParameter);
				System.out.println("ParameterPath:-" + ParameterPath);
				CF.FnSetText(driver, ParameterPath, sParameterValues);

				Thread.sleep(2000);

				CF.FnElementClick(driver, DealManagementPageElements.Algorithm_Save_Button);
				Thread.sleep(5000);

				String URL = System.getProperty("appUrl");
				driver.navigate().to(URL + "/flushAll.jsp");
				Thread.sleep(5000);
				driver.navigate().to(URL + "/cis.jsp");
				Thread.sleep(10000);

				CF.FnTestCaseStatusReport("Pass", "" + sAlgoCode + " Algorithm's " + sParameter + " Parameter value Updated as - " + sParameterValues + " Successfully.");


			}
		} catch (Exception e) {
			System.out.println("Exception occured in FnUpdateAlgo->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "cf =FnUpdateAlgo - Fail - Exception occoured \n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnNavigationToDealInformationFromProductScreen
	,Description          : Navigation to Deal Information UI from Enroll Product Screen
	'###############################################################################################################################################################################################################################################*/
	public void FnNavigationToDealInformationFromProductScreen() throws Exception {
		System.out.println(">>>>>>>>>>--FnNavigationToDealInformationFromProductScreen");

		try {
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "uiMap");
			Thread.sleep(3000);
			try {
				CF.FnElementClick(driver, DealManagementPageElements.Product_Enrollment_Cancel_Button);
			} catch (Exception ere) {}
			Thread.sleep(10000);
			CF.FnSetFrame(driver, "main");
			CF.FnWaitForElement(driver, 360, DealManagementPageElements.Deal_Information_DealInfoTitle_Label);
			assertTrue(CF.FnGetWebElement(driver, "XPATH", DealManagementPageElements.Deal_Information_DealInfoTitle_Label).isDisplayed(), "User is navigated to Deal Information page Successfully");
			Thread.sleep(5000);
			CF.FnTestCaseStatusReport("Pass", "Navigation to Deal Information UI from Enroll Product Screen Is Completed Successfully");
		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
	}

	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnCreateDealIWS
	,Description          : To Create Deal Using Web Service 
	'Input Parameters     : iStartingRow   	-> Starting Row
	'Created By			  :	Rohit Thik
	'###############################################################################################################################################################################################################################################*/
	public Hashtable < String, String >  FnCreateDealIWS(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
		System.out.println(iStartingRow + " || >>>>>>>>>>--FnCreateDealIWS--<<<<<<<<" + iStartingRow);

		Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

		String sDateName = CommonFunctions.FnGetUniqueId();
		String sDEAL_IDENTIFIER_1 = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		sDEAL_IDENTIFIER_1 = sDEAL_IDENTIFIER_1 + "_" + sDateName;

		String dealEntityType = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
		String dealEntityID = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
		String dealEntityIdentifierType = CF.FnGetCellValue(iStartingRow, 43, sSheetName, sWorkbook).toString().trim();
		String dealEntityIdentifier = CF.FnGetCellValue(iStartingRow, 44, sSheetName, sWorkbook).toString().trim();
		String dealEntitydivision = CF.FnGetCellValue(iStartingRow, 45, sSheetName, sWorkbook).toString().trim();

		String dealIdentifier = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();
		dealIdentifier = dealIdentifier + "_" + sDateName;
		String contractedDealFlag = CF.FnGetCellValue(iStartingRow, 17, sSheetName, sWorkbook).toString().trim();
		String currency = CF.FnGetCellValue(iStartingRow, 5, sSheetName, sWorkbook).toString().trim();
		String dealTypeCode = CF.FnGetCellValue(iStartingRow, 4, sSheetName, sWorkbook).toString().trim();
		String startDate = CF.FnGetCellValue(iStartingRow, 7, sSheetName, sWorkbook).toString().trim();
		String priceSelectionDate = CF.FnGetCellValue(iStartingRow, 8, sSheetName, sWorkbook).toString().trim();
		String simulationTypeFlag = CF.FnGetCellValue(iStartingRow, 6, sSheetName, sWorkbook).toString().trim();
		String reviewFrequency = CF.FnGetCellValue(iStartingRow, 9, sSheetName, sWorkbook).toString().trim();
		String dealFrequency = CF.FnGetCellValue(iStartingRow, 10, sSheetName, sWorkbook).toString().trim();
		String usagePeriod = CF.FnGetCellValue(iStartingRow, 11, sSheetName, sWorkbook).toString().trim();
		String dealDescription = CF.FnGetCellValue(iStartingRow, 12, sSheetName, sWorkbook).toString().trim();
		String dealVersionDescription = CF.FnGetCellValue(iStartingRow, 13, sSheetName, sWorkbook).toString().trim();
		String skipReferenceFlag = CF.FnGetCellValue(iStartingRow, 14, sSheetName, sWorkbook).toString().trim();
		String hierarchyFilterFlag = CF.FnGetCellValue(iStartingRow, 18, sSheetName, sWorkbook).toString().trim();
		String templateFlag = CF.FnGetCellValue(iStartingRow, 16, sSheetName, sWorkbook).toString().trim();

		String templateDealId = CF.FnGetCellValue(iStartingRow, 33, sSheetName, sWorkbook).toString().trim();
		String templateModelId = CF.FnGetCellValue(iStartingRow, 35, sSheetName, sWorkbook).toString().trim();
		String templateDealIdentifier = CF.FnGetCellValue(iStartingRow, 34, sSheetName, sWorkbook).toString().trim();
		String copyBasicDetailsFlag = CF.FnGetCellValue(iStartingRow, 36, sSheetName, sWorkbook).toString().trim();
		String copyPricingFlag = CF.FnGetCellValue(iStartingRow, 37, sSheetName, sWorkbook).toString().trim();
		String copyUsageFlag = CF.FnGetCellValue(iStartingRow, 38, sSheetName, sWorkbook).toString().trim();

		String TNC1 = CF.FnGetCellValue(iStartingRow, 20, sSheetName, sWorkbook).toString().trim();
		String TNC2 = CF.FnGetCellValue(iStartingRow, 21, sSheetName, sWorkbook).toString().trim();
		String TNC3 = CF.FnGetCellValue(iStartingRow, 22, sSheetName, sWorkbook).toString().trim();

		String productCode1 = CF.FnGetCellValue(iStartingRow, 23, sSheetName, sWorkbook).toString().trim();
		String productCode2 = CF.FnGetCellValue(iStartingRow, 24, sSheetName, sWorkbook).toString().trim();
		String productCode3 = CF.FnGetCellValue(iStartingRow, 25, sSheetName, sWorkbook).toString().trim();

		String characteristicType1 = CF.FnGetCellValue(iStartingRow, 46, sSheetName, sWorkbook).toString().trim();
		String dealCharacteristiceffectiveDate1 = CF.FnGetCellValue(iStartingRow, 47, sSheetName, sWorkbook).toString().trim();
		String characteristicValue1 = CF.FnGetCellValue(iStartingRow, 48, sSheetName, sWorkbook).toString().trim();
		String characteristicType2 = CF.FnGetCellValue(iStartingRow, 49, sSheetName, sWorkbook).toString().trim();
		String dealCharacteristiceffectiveDate2 = CF.FnGetCellValue(iStartingRow, 50, sSheetName, sWorkbook).toString().trim();
		String characteristicValue2 = CF.FnGetCellValue(iStartingRow, 51, sSheetName, sWorkbook).toString().trim();
		String characteristicType3 = CF.FnGetCellValue(iStartingRow, 52, sSheetName, sWorkbook).toString().trim();
		String dealCharacteristiceffectiveDate3 = CF.FnGetCellValue(iStartingRow, 53, sSheetName, sWorkbook).toString().trim();
		String characteristicValue3 = CF.FnGetCellValue(iStartingRow, 54, sSheetName, sWorkbook).toString().trim();

		String referenceTypeFlg = CF.FnGetCellValue(iStartingRow, 26, sSheetName, sWorkbook).toString().trim();
		String referenceDealId = CF.FnGetCellValue(iStartingRow, 27, sSheetName, sWorkbook).toString().trim();
		String referPersonId = CF.FnGetCellValue(iStartingRow, 29, sSheetName, sWorkbook).toString().trim();
		String referAccountId = CF.FnGetCellValue(iStartingRow, 29, sSheetName, sWorkbook).toString().trim();
		String referenceModelId = CF.FnGetCellValue(iStartingRow, 28, sSheetName, sWorkbook).toString().trim();
		String referUsageSw = CF.FnGetCellValue(iStartingRow, 30, sSheetName, sWorkbook).toString().trim();
		String referPriceSw = CF.FnGetCellValue(iStartingRow, 31, sSheetName, sWorkbook).toString().trim();
		String includeChildHierarchy = CF.FnGetCellValue(iStartingRow, 32, sSheetName, sWorkbook).toString().trim();

		String sDealId, smodelId, sModelId1, sDealIdentifier = "";


		try {

			DealManagementPageEvents DM = new DealManagementPageEvents(driver);

			String sCreateDealResource = "/rest/ouaf/api/iws/C1-DealREST/dealService";
			String sContentTypeHeader = "application/json";
			String sAcceptTypeHeader = "application/json";
			String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");

            if(dealEntityType.equalsIgnoreCase("EPER") && simulationTypeFlag.equalsIgnoreCase("CUST") && hierarchyFilterFlag.equalsIgnoreCase("N"))
            {
            	hierarchyFilterFlag = "WHEP";
            } else if(dealEntityType.equalsIgnoreCase("EPER") && simulationTypeFlag.equalsIgnoreCase("CUST") && hierarchyFilterFlag.equalsIgnoreCase("Y"))
            {
            	hierarchyFilterFlag = "HIPR";
            } else if(dealEntityType.equalsIgnoreCase("PRSP") && simulationTypeFlag.equalsIgnoreCase("CUST") && hierarchyFilterFlag.equalsIgnoreCase("Y"))
            {
            	hierarchyFilterFlag = "HIPR";
            } else if(dealEntityType.equalsIgnoreCase("PRSP") && simulationTypeFlag.equalsIgnoreCase("CUST") && hierarchyFilterFlag.equalsIgnoreCase("N"))
            {
            	hierarchyFilterFlag = "HIPR";
            } else {  
                if((simulationTypeFlag.equalsIgnoreCase("DEAL") || simulationTypeFlag.equalsIgnoreCase("ACCT")) && simulationTypeFlag.equalsIgnoreCase("N"))
                {
                	hierarchyFilterFlag = "NoValue";
                }
            }
			//################ Deal Creation IWS ####################//
			String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"RequiredpersonId\",\"prospectPersonId\":\"RequiredprospectPersonId\",\"accountId\":\"RequiredaccountId\",\"personIdentifierType\":\"RequiredpersonIdentifierType\",\"personIdentifier\":\"RequiredpersonIdentifier\",\"accountIdentifierType\":\"RequiredaccountIdentifierType\",\"accountIdentifier\":\"RequiredaccountIdentifier\",\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",\"division\":\"Requireddivision\"},\"dealIdentifier\":\"RequireddealIdentifier\",\"dealEntityType\":\"RequireddealEntityType\",\"contractedDealFlag\":\"RequiredcontractedDealFlag\",\"currency\":\"Requiredcurrency\",\"dealTypeCode\":\"RequireddealTypeCode\",\"startDate\":\"RequiredstartDate\",\"simulationTypeFlag\":\"RequiredsimulationTypeFlag\",\"reviewFrequency\":\"RequiredreviewFrequency\",\"dealDescription\":\"RequireddealDescription\",\"dealVersionDescription\":\"RequireddealVersionDescription\",\"dealFrequency\":\"RequireddealFrequency\",\"skipReferenceFlag\":\"RequiredskipReferenceFlag\",\"priceSelectionDate\":\"RequiredpriceSelectionDate\",\"usagePeriod\":\"RequiredusagePeriod\",\"hierarchyFilterFlag\":\"RequiredhierarchyFilterFlag\",\"templateFlag\":\"RequiredtemplateFlag\"},\"templateReferenceDetails\":{\"templateDealId\":\"RequiredtemplateDealId\",\"templateModelId\":\"RequiredtemplateModelId\",\"templateDealIdentifier\":\"RequiredtemplateDealIdentifier\",\"copyBasicDetailsFlag\":\"RequiredcopyBasicDetailsFlag\",\"copyPricingFlag\":\"RequiredcopyPricingFlag\",\"copyUsageFlag\":\"RequiredcopyUsageFlag\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"RequireddescriptionTNC1\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC1\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC1\"},{\"description\":\"RequireddescriptionTNC2\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC2\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC2\"},{\"description\":\"RequireddescriptionTNC3\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC3\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC3\"}]},\"dealCharacteristics\":[{\"characteristicType\":\"RequiredcharacteristicType1\",\"effectiveDate\":\"RequiredeffectiveDate1\",\"characteristicValue\":\"RequiredcharacteristicValue1\"},{\"characteristicType\":\"RequiredcharacteristicType2\",\"effectiveDate\":\"RequiredeffectiveDate2\",\"characteristicValue\":\"RequiredcharacteristicValue2\"},{\"characteristicType\":\"RequiredcharacteristicType3\",\"effectiveDate\":\"RequiredeffectiveDate3\",\"characteristicValue\":\"RequiredcharacteristicValue3\"}],\"productDetailsList\":[{\"productCode\":\"RequiredproductCode1\"},{\"productCode\":\"RequiredproductCode2\"},{\"productCode\":\"RequiredproductCode3\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RequiredreferenceTypeFlg\",\"referPersonId\":\"RequiredreferPersonId\",\"referAccountId\":\"RequiredreferAccountId\",\"referenceDealId\":\"RequiredreferenceDealId\",\"referenceModelId\":\"RequiredreferenceModelId\",\"referUsageSw\":\"RequiredreferUsageSw\",\"referPriceSw\":\"RequiredreferPriceSw\",\"includeChildHierarchy\":\"RequiredincludeChildHierarchy\"}}}";

			if (!dealEntitydivision.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("Requireddivision", dealEntitydivision);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"division\":\"Requireddivision\"", "");
			}

			//dealEntityDetails
			if (dealEntityType.equalsIgnoreCase("EPER")) {
				if (!dealEntityID.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("\"division\":\"Requireddivision\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifier\":\"RequiredpersonIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifierType\":\"RequiredpersonIdentifierType\",", "");

					sDealCreation = sDealCreation.replaceAll("RequiredpersonId", dealEntityID);
					sDealCreation = sDealCreation.replaceAll("\"prospectPersonId\":\"RequiredprospectPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountId\":\"RequiredaccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifierType\":\"RequiredaccountIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifier\":\"RequiredaccountIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",", "");
				} else {
					sDealCreation = sDealCreation.replaceAll("RequiredpersonIdentifierType", dealEntityIdentifierType);
					sDealCreation = sDealCreation.replaceAll("RequiredpersonIdentifier", dealEntityIdentifier);
					sDealCreation = sDealCreation.replaceAll("Requireddivision", dealEntitydivision);
					sDealCreation = sDealCreation.replaceAll("\"personId\":\"RequiredpersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prospectPersonId\":\"RequiredprospectPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountId\":\"RequiredaccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifierType\":\"RequiredaccountIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifier\":\"RequiredaccountIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",", "");
				}
			} else if (dealEntityType.equalsIgnoreCase("EACC")) {
				if (!dealEntityID.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredaccountId", dealEntityID);
					sDealCreation = sDealCreation.replaceAll("\"prospectPersonId\":\"RequiredprospectPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personId\":\"RequiredpersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifierType\":\"RequiredpersonIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifier\":\"RequiredpersonIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifierType\":\"RequiredaccountIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifier\":\"RequiredaccountIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"division\":\"Requireddivision\",", "");
				} else {
					sDealCreation = sDealCreation.replaceAll("RequiredaccountIdentifierType", dealEntityIdentifierType);
					sDealCreation = sDealCreation.replaceAll("RequiredaccountIdentifier", dealEntityIdentifier);
					sDealCreation = sDealCreation.replaceAll("Requireddivision", dealEntitydivision);
					sDealCreation = sDealCreation.replaceAll("\"personId\":\"RequiredpersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prospectPersonId\":\"RequiredprospectPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountId\":\"RequiredaccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifierType\":\"RequiredpersonIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifier\":\"RequiredpersonIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",", "");
				}
			} else if (dealEntityType.equalsIgnoreCase("PRSP")) {
				if (!dealEntityID.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredprospectPersonId", dealEntityID);
					sDealCreation = sDealCreation.replaceAll("\"accountId\":\"RequiredaccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personId\":\"RequiredpersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifierType\":\"RequiredpersonIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifier\":\"RequiredpersonIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifierType\":\"RequiredaccountIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifier\":\"RequiredaccountIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifierType\":\"RequiredprsPerIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prsPerIdentifier\":\"RequiredprsPerIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"division\":\"Requireddivision\",", "");
				} else {
					sDealCreation = sDealCreation.replaceAll("RequiredprsPerIdentifierType", dealEntityIdentifierType);
					sDealCreation = sDealCreation.replaceAll("RequiredprsPerIdentifier", dealEntityIdentifier);
					sDealCreation = sDealCreation.replaceAll("Requireddivision", dealEntitydivision);
					sDealCreation = sDealCreation.replaceAll("\"personId\":\"RequiredpersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"prospectPersonId\":\"RequiredprospectPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountId\":\"RequiredaccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifierType\":\"RequiredpersonIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"personIdentifier\":\"RequiredpersonIdentifier\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifierType\":\"RequiredaccountIdentifierType\",", "");
					sDealCreation = sDealCreation.replaceAll("\"accountIdentifier\":\"RequiredaccountIdentifier\",", "");
				}
			}




			//dealDetails
			if (!dealIdentifier.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealIdentifier", dealIdentifier);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealIdentifier\":\"RequireddealIdentifier\",", "");
			}
			if (!dealEntityType.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealEntityType", dealEntityType);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealEntityType\":\"RequireddealEntityType\",", "");
			}
			if (!contractedDealFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredcontractedDealFlag", contractedDealFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"contractedDealFlag\":\"RequiredcontractedDealFlag\",", "");
			}
			if (!currency.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("Requiredcurrency", currency);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"currency\":\"Requiredcurrency\",", "");
			}
			if (!dealTypeCode.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealTypeCode", dealTypeCode);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealTypeCode\":\"RequireddealTypeCode\",", "");
			}
			if (!startDate.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredstartDate", startDate);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"startDate\":\"RequiredstartDate\",", "");
			}
			if (!simulationTypeFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredsimulationTypeFlag", simulationTypeFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"simulationTypeFlag\":\"RequiredsimulationTypeFlag\",", "");
			}
			if (!reviewFrequency.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreviewFrequency", reviewFrequency);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"reviewFrequency\":\"RequiredreviewFrequency\",", "");
			}
			if (!dealDescription.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealDescription", dealDescription);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealDescription\":\"RequireddealDescription\",", "");
			}
			if (!dealVersionDescription.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealVersionDescription", dealVersionDescription);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealVersionDescription\":\"RequireddealVersionDescription\",", "");
			}
			if (!dealFrequency.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddealFrequency", dealFrequency);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"dealFrequency\":\"RequireddealFrequency\",", "");
			}
			if (!skipReferenceFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredskipReferenceFlag", skipReferenceFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"skipReferenceFlag\":\"RequiredskipReferenceFlag\",", "");
			}
			if (!priceSelectionDate.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredpriceSelectionDate", priceSelectionDate);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"priceSelectionDate\":\"RequiredpriceSelectionDate\",", "");
			}
			if (!usagePeriod.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredusagePeriod", usagePeriod);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"usagePeriod\":\"RequiredusagePeriod\",", "");
			}
			if (!hierarchyFilterFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredhierarchyFilterFlag", hierarchyFilterFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"hierarchyFilterFlag\":\"RequiredhierarchyFilterFlag\",", "");
			}
			if (!templateFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredtemplateFlag", templateFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"templateFlag\":\"RequiredtemplateFlag\",", "");
			}

			//templateReferenceDetails
			if (!templateDealId.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredtemplateDealId", templateDealId);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"templateDealId\":\"RequiredtemplateDealId\",", "");
			}
			if (!templateModelId.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredtemplateModelId", templateModelId);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"templateModelId\":\"RequiredtemplateModelId\",", "");
			}
			if (!templateDealIdentifier.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredtemplateDealIdentifier", templateDealIdentifier);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"templateDealIdentifier\":\"RequiredtemplateDealIdentifier\",", "");
			}
			if (!copyBasicDetailsFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredcopyBasicDetailsFlag", copyBasicDetailsFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"copyBasicDetailsFlag\":\"RequiredcopyBasicDetailsFlag\",", "");
			}
			if (!copyPricingFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredcopyPricingFlag", copyPricingFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"copyPricingFlag\":\"RequiredcopyPricingFlag\",", "");
			}
			if (!copyUsageFlag.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredcopyUsageFlag", copyUsageFlag);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"copyUsageFlag\":\"RequiredcopyUsageFlag\",", "");
			}

			//dealTermsAndConditionsDetails
			if (!TNC1.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddescriptionTNC1", TNC1);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTNC1", TNC1);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTextTNC1", TNC1);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"description\":\"RequireddescriptionTNC1\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC1\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC1\"\\},", "");
			}
			if (!TNC2.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddescriptionTNC2", TNC2);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTNC2", TNC2);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTextTNC2", TNC2);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"description\":\"RequireddescriptionTNC2\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC2\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC2\"\\},", "");
			}
			if (!TNC3.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequireddescriptionTNC3", TNC3);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTNC3", TNC3);
				sDealCreation = sDealCreation.replaceAll("RequiredtermsAndConditionTextTNC3", TNC3);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"description\":\"RequireddescriptionTNC3\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC3\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC3\"\\}", "");
			}
			if (TNC1.equalsIgnoreCase("NoValue") && TNC2.equalsIgnoreCase("NoValue") && TNC3.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("\"dealTermsAndConditionsDetails\":\\{\"termsAndConditionsList\":[{\"description\":\"RequireddescriptionTNC1\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC1\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC1\"},{\"description\":\"RequireddescriptionTNC2\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC2\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC2\"},{\"description\":\"RequireddescriptionTNC3\",\"termsAndCondition\":\"RequiredtermsAndConditionTNC3\",\"termsAndConditionText\":\"RequiredtermsAndConditionTextTNC3\"}]},", "");
			}

			//productDetailsList
			if (!productCode1.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredproductCode1", productCode1);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"productCode\":\"RequiredproductCode1\"\\},", "");
			}
			if (!productCode2.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredproductCode2", productCode2);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"productCode\":\"RequiredproductCode2\"\\},", "");
			}
			if (!productCode3.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredproductCode3", productCode3);
			} else {
				sDealCreation = sDealCreation.replaceAll("\\{\"productCode\":\"RequiredproductCode3\"\\}", "");
			}
			if (productCode1.equalsIgnoreCase("NoValue") && productCode2.equalsIgnoreCase("NoValue") && productCode3.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("\"productDetailsList\":[{\"productCode\":\"RequiredproductCode1\"},{\"productCode\":\"RequiredproductCode2\"},{\"productCode\":\"RequiredproductCode3\"}],", "");
			}

			//dealCharacteristics

				System.out.println("elseeeeeeeeeeeee");
				if (!characteristicType1.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicType1", characteristicType1);
					sDealCreation = sDealCreation.replaceAll("RequiredeffectiveDate1", dealCharacteristiceffectiveDate1);
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicValue1", characteristicValue1);
				} else {
					sDealCreation = sDealCreation.replaceAll("\\{\"characteristicType\":\"RequiredcharacteristicType1\",\"effectiveDate\":\"RequiredeffectiveDate1\",\"characteristicValue\":\"RequiredcharacteristicValue1\"\\},", "");
				}
				if (!dealCharacteristiceffectiveDate2.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicType2", characteristicType2);
					sDealCreation = sDealCreation.replaceAll("RequiredeffectiveDate2", dealCharacteristiceffectiveDate2);
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicValue2", characteristicValue2);
				} else {
					sDealCreation = sDealCreation.replaceAll("\\{\"characteristicType\":\"RequiredcharacteristicType2\",\"effectiveDate\":\"RequiredeffectiveDate2\",\"characteristicValue\":\"RequiredcharacteristicValue2\"\\},", "");
				}
				if (!dealCharacteristiceffectiveDate3.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicType3", characteristicType3);
					sDealCreation = sDealCreation.replaceAll("RequiredeffectiveDate3", dealCharacteristiceffectiveDate3);
					sDealCreation = sDealCreation.replaceAll("RequiredcharacteristicValue3", characteristicValue3);
				} else {
					sDealCreation = sDealCreation.replaceAll("\\{\"characteristicType\":\"RequiredcharacteristicType3\",\"effectiveDate\":\"RequiredeffectiveDate3\",\"characteristicValue\":\"RequiredcharacteristicValue3\"\\}", "");
				}
			
				
				if (characteristicType1.equalsIgnoreCase("NoValue") && characteristicType2.equalsIgnoreCase("NoValue") && characteristicType3.equalsIgnoreCase("NoValue")) {
//					sDealCreation = sDealCreation.replaceAll("\"dealCharacteristics\": [],", "");
				} 

			//referenceDetails
			if (!referenceTypeFlg.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreferenceTypeFlg", referenceTypeFlg);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"referenceTypeFlg\":\"RequiredreferenceTypeFlg\",", "");
			}
			if (!referenceDealId.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreferenceDealId", referenceDealId);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"referenceDealId\":\"RequiredreferenceDealId\",", "");
			}
			System.out.println("referenceTypeFlg - "+referenceTypeFlg);
			if (referenceTypeFlg.equalsIgnoreCase("RPER")) {
				if (!referPersonId.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredreferPersonId", referPersonId);
					sDealCreation = sDealCreation.replaceAll("\"referAccountId\":\"RequiredreferAccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"referenceDealId\":\"RequiredreferenceDealId\",", "");
				} else {
					sDealCreation = sDealCreation.replaceAll("\"referPersonId\":\"RequiredreferPersonId\",", "");
				}
			} else if (referenceTypeFlg.equalsIgnoreCase("RDEL")) {
				if (!referenceDealId.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredreferenceDealId", referenceDealId);
					sDealCreation = sDealCreation.replaceAll("\"referAccountId\":\"RequiredreferAccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"referPersonId\":\"RequiredreferPersonId\",", "");
				} else {
					sDealCreation = sDealCreation.replaceAll("\"referenceDealId\":\"RequiredreferenceDealId\",", "");
				}
			} else {
				if (!referAccountId.equalsIgnoreCase("NoValue")) {
					sDealCreation = sDealCreation.replaceAll("RequiredreferAccountId", referAccountId);
					sDealCreation = sDealCreation.replaceAll("\"referenceDealId\":\"RequiredreferenceDealId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"referPersonId\":\"RequiredreferPersonId\",", "");
					} else {
					sDealCreation = sDealCreation.replaceAll("\"referAccountId\":\"RequiredreferAccountId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"referPersonId\":\"RequiredreferPersonId\",", "");
					sDealCreation = sDealCreation.replaceAll("\"referenceDealId\":\"RequiredreferenceDealId\",", "");
				}
			}

			if (!referenceModelId.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreferenceModelId", referenceModelId);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"referenceModelId\":\"RequiredreferenceModelId\",", "");
			}
			if (!referUsageSw.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreferUsageSw", referUsageSw);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"referUsageSw\":\"RequiredreferUsageSw\",", "");
			}
			if (!referPriceSw.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredreferPriceSw", referPriceSw);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"referPriceSw\":\"RequiredreferPriceSw\",", "");
			}
			if (!includeChildHierarchy.equalsIgnoreCase("NoValue")) {
				sDealCreation = sDealCreation.replaceAll("RequiredincludeChildHierarchy", includeChildHierarchy);
			} else {
				sDealCreation = sDealCreation.replaceAll("\"includeChildHierarchy\":\"RequiredincludeChildHierarchy\",", "");
			}

			if (sDealCreation.contains("},]")) {
				sDealCreation = sDealCreation.replaceAll("},]", "}]");
			}
			if (sDealCreation.contains(",}")) {
				sDealCreation = sDealCreation.replaceAll(",}", "}");
			}
			if (sDealCreation.contains(",,")) {
				sDealCreation = sDealCreation.replaceAll(",,", ",");
			}
			if (sDealCreation.contains("Y")) {
				sDealCreation = sDealCreation.replaceAll(":\"Y\"", ": true");
			}
			if (sDealCreation.contains("N")) {
				sDealCreation = sDealCreation.replaceAll(":\"N\"", ": false");
			}
			if (sDealCreation.contains("\"characteristicValue\": true")) {
				sDealCreation = sDealCreation.replaceAll("\"characteristicValue\": true", "\"characteristicValue\": Y");
			}
			if (sDealCreation.contains("\"characteristicValue\": false")) {
				sDealCreation = sDealCreation.replaceAll("\"characteristicValue\": false", "\"characteristicValue\": N");
			}


			DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

			sDealId = DealDetails.get("sDealId");
			smodelId = DealDetails.get("sModelId");
			sDealIdentifier = DealDetails.get("sDealIdentifier");
			
			System.out.println("DealDetails:-"+DealDetails);
			System.out.println("sDealId :- " + sDealId);
			System.out.println("smodelId :- " + smodelId);
			System.out.println("sDealIdentifier :- " + sDealIdentifier);


		} catch (Exception e) {
			System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
		}
		
		System.out.println("DealDetails1:-"+DealDetails);

		return DealDetails;

	}
	
	/*'############################################### WEBSERVICES COMMON FUNCTIONS #############################################################################################################################################################################################	
	/*'############################################################################################################################################################################################################################################
	'Function Name        : FnDealExtract
	,Description          : To Extract deal using API
	'###############################################################################################################################################################################################################################################*/

	public static Object FnDealExtract(String sDealCreationData, String sCreateDealResource, String sContentTypeHeader, String sAcceptTypeHeader) throws Exception {
		System.out.println("*--FnDealExtract--*" + sDealCreationData);

		////sReturnField will be "sDealId" , "sModelId" , "sErrorMessage"
		Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

		String sValue = null, sDealId = null, sModelId = null, sDealIdentifier = null;
		int iErrorStatusCode = 400;
		int iSuccessStatusCode = 200;

		try {
			// To send POST request to server for creating Deal 
			WF.FnPostRequestByString(sCreateDealResource, sDealCreationData, sContentTypeHeader, sAcceptTypeHeader);

			Thread.sleep(500);

			System.out.println("Response :-" + WF.respBody);
			int iStatusCode = WF.FnGetStatusCodeFromResponse();

			if (iStatusCode == iSuccessStatusCode) {

				assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealResponse.message");
				//System.out.println("Deal Extracted ! Deal response message is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Extracted ! Deal response message is :" + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.dealInformation");
				//System.out.println("Deal Extracted ! Deal Information is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Extracted !  Deal Information is : " + sValue);

				sDealId = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.dealId"); //modelId // dealId
				//System.out.println("Deal Extracted ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Deal ID is : " + sDealId);

				sDealIdentifier = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.dealIdentifier"); //modelId // dealId
				//System.out.println("Deal Extracted ! Deal ID is : " + sDealId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! DealIdentifier is : " + sDealIdentifier);

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.modelId");
				//System.out.println("Deal Extracted ! Model ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Model ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.startDate");
				//System.out.println("Deal Extracted ! Start Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Start Date is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.dealEntityId");
				System.out.println("Deal Extracted ! Entity ID is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Entity ID is : " + sValue);

				sValue = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.priceSelectionDate");
				System.out.println("Deal Extracted ! Price Selection Date is : " + sValue);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Price Selection Date is : " + sValue);

				sModelId = WF.FnGetDataFromResponse("C1-DealExtractREST.dealDetails.modelId"); //modelId // dealId
				System.out.println("Deal Extracted ! Model ID is : " + sModelId);
				CF.FnTestCaseStatusReport("Pass", "Deal Created ! Model ID is : " + sModelId);


			} else if (iStatusCode == iErrorStatusCode) {

				assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at last so we can return text
				System.out.println("Deal Not Created ! Reason Is : " + sValue);

				CF.FnTestCaseStatusReport("Fail", "Deal Not Created ! Reason Is : " + sValue);


			} else {

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
				System.out.println("Deal Not Created ! httpStatus Is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Not Created  ! httpStatus Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
				System.out.println("Deal Not Created ! Message text Is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Not Created ! Reason Is : " + sValue);

				sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
				System.out.println("Deal Not Created  ! Message Number Is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Not Created  ! Message Number Is : " + sValue);


				sValue = WF.FnGetDataFromResponse("problemDetailDocument.title"); //keep title at end so we can return title text
				System.out.println("Deal Not Created ! Reason Is : " + sValue);
				CF.FnTestCaseStatusReport("Fail", "Deal Not Created ! Reason Is : " + sValue);


			}

		} catch (Exception e) {
			System.out.println("Common Function Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
		}


		System.out.println("DealDetails :-" + WF.respBody);
		return WF.respBody;

	}
/*
	'############################################################################################################################################################################################################################################
	'Function Name        : FnDealExtractIWS
	,Description          : To Extract Deal
	'###############################################################################################################################################################################################################################################
	public static void FnDealExtractIWS(int iStartingRow, String sSheetName, String sWorkbook) throws Exception {
	    System.out.println(">>>>>>>>>>--FnDealExtractIWS");

	    String sDealExtractInputJsonRequest, sExpectedDealExtractResponse, sActualDealExtractResponse;

	    sDealExtractInputJsonRequest = CF.FnGetCellValue(iStartingRow, 1, sSheetName, sWorkbook).toString().trim();
	    sExpectedDealExtractResponse = CF.FnGetCellValue(iStartingRow, 2, sSheetName, sWorkbook).toString().trim();
	    sActualDealExtractResponse = CF.FnGetCellValue(iStartingRow, 3, sSheetName, sWorkbook).toString().trim();

	    String sDealExtractResource = "/rest/ouaf/api/iws/C1-DealExtractREST/DealExtract";
		String sContentTypeHeader = "application/json";
		String sAcceptTypeHeader = "application/json";

	    System.out.println("sDealExtractInputJsonRequest is " + sDealExtractInputJsonRequest);
	    System.out.println("Expected Deal Extract Response is " + sExpectedDealExtractResponse);
	    System.out.println("Actual Deal Extract Response is " + sActualDealExtractResponse);

	    try {

	        if (!sDealExtractInputJsonRequest.equalsIgnoreCase("NoValue") && !sExpectedDealExtractResponse.equalsIgnoreCase("NoValue") && !sActualDealExtractResponse.equalsIgnoreCase("NoValue")) {


	            //Expected JSON Data to be used:
	            FileInputStream expected = new FileInputStream(sExpectedDealExtractResponse);

	            // DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
	            // dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

	            // To Change user for sending new request
	            WF.FnUserChange("RMBK1");

	        	Object DealDetails = new Hashtable < Object, Object > ();

	            //################ Deal Extract IWS ####################//
	            String DealExtract = sDealExtractInputJsonRequest;
//	        	String DealExtract = "{\"C1-DealExtractREST\":{\"dealDetails\":{\"dealId\":\"5107043172\",\"modelId\":\"3903876691\"}}}";
//	            System.out.println("deal_extraction_001 Request ->" + DealExtract);
	            DealDetails = FnDealExtract(DealExtract, sDealExtractResource, sContentTypeHeader, sAcceptTypeHeader);
//	            System.out.println("Extracted Deal Is-> " + DealDetails);

	            ObjectMapper mapper = new ObjectMapper();
	            TypeReference < HashMap < String, Object >> type = new TypeReference < HashMap < String, Object >> () {};

	            try {
	                FileWriter myWriter = new FileWriter(sActualDealExtractResponse);
	                myWriter.write(BaseTest.respBody.asString());
	                myWriter.close();
	            } catch (IOException e) {
	    	        CF.FnTestCaseStatusReport("Fail", "Deal Extract For Provided Deal Details Not Verified");
	                e.printStackTrace();
	            }

	            FileInputStream actual = new FileInputStream(sActualDealExtractResponse);
	            
	            Map < String, Object > actualResponse = mapper.readValue(actual, type);
	            Map < String, Object > expectedResponse = mapper.readValue(expected, type);

	            System.out.println("actualResponse:-"+actualResponse);
	            MapDifference < String, Object > difference = Maps.difference(actualResponse, expectedResponse);
	            System.out.println("difference :-" + difference);


	            if(difference.entriesDiffering().isEmpty()) {
	    	        CF.FnTestCaseStatusReport("Pass", "Deal Extract For Provided Deal Details Verified Successfully");	
	            } else {
	    	        CF.FnTestCaseStatusReport("Fail", "Deal Extract For Provided Deal Details Not Verified");
	            }


	        }


	    } catch (Exception e) {
	        System.out.println("Application Function Exception occured ->" + e.getLocalizedMessage());
	        e.printStackTrace();
	        BaseTest.eFlgFound = "false";
	        CF.FnTestCaseStatusReport("Fail", "Application Function Exception occured ->\n" + CF.erromsg(e));
	    }

	}

*/
	///////////////////////////////////////////
}