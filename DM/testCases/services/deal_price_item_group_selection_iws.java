package test.java.ormbframework.testCases.services;


import java.util.Hashtable;
import java.util.List;


import org.testng.annotations.Test;

import test.java.ormbframework.BaseTest;
import test.java.ormbframework.pageEvents.DealManagementPageEvents;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

public class deal_price_item_group_selection_iws extends BaseTest {
	
	CommonFunctions CF = new CommonFunctions();
	ApplicationFunctions AF = new ApplicationFunctions();
	WebServiceFunctions WF = new WebServiceFunctions();
	DataBaseFunctions DB = new DataBaseFunctions();
	DealManagementPageEvents DM = new DealManagementPageEvents(getDriver());
	
	public static String sCreateDealResource = "/rest/ouaf/api/iws/C1-DealREST/dealService"; 
	public static String sAddPrizeListAssignmentResource = "/rest/ouaf/api/iws/C1-DealPricelistAssignmentREST/dealPricelistAssignment";
	public static String sContentTypeHeader = "application/json";
	public static String sAcceptTypeHeader = "application/json";
	public static String sStatusCode, sValue;
	public static int iSuccessStatusCode = 200;
	public static int iErrorStatusCode = 400;
	public static String sAccountId = "3353104474";
	public String sStartDate;
	public String sDateName;
	
	public String getStartDate() throws Exception
	{
		sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
		System.out.println("Start Date = " +sStartDate);
		return sStartDate;
	}
	
	public String getCurrentDate() throws Exception
	{
		sDateName = CommonFunctions.FnGetUniqueId();
		return sDateName;
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_READ_PROS_01_42825
	 * Script Description				: 	TC_PSEL_READ_PROS_01_42825 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority = 0)
	public void TC_PSEL_READ_PROS_01_42825() throws Exception {
		log.info("=======Starting TC_PSEL_READ_PROS_01_42825========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_READ_PROS_01_42825";
				BaseTest.sOTMTestcaseID = "42825";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_READ_PROS_01_42825";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_READ_PROS_01.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				List<String> sListOfPriceItemCode = DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sListOfPriceItemCode);
				
				
				// Verify Presence of Price Item in Read Price Item Group Selection Response
				DM.FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse(6,sSheetName, sWorkbook, sListOfPriceItemCode);
				
				
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_READ_PROS_01_42825========");
	}
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_READ_PROS_02_42826
	 * Script Description				: 	TC_PSEL_READ_PROS_02_42826 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 1)
	public void TC_PSEL_READ_PROS_02_42826() throws Exception {
		log.info("=======Starting TC_PSEL_READ_PROS_02_42826========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_READ_PROS_02_42826";
				BaseTest.sOTMTestcaseID = "42826";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_READ_PROS_02_42826";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_READ_PROS_02.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'}}}";
			    // Add prize list assignment to deal
				List<String> sListOfPriceItemCode = DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sListOfPriceItemCode);				
				
				// Verify Presence of Price Item in Read Price Item Group Selection Response
				DM.FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse(6,sSheetName, sWorkbook, sListOfPriceItemCode);				
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_READ_PROS_02_42826========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_03_42827
	 * Script Description				: 	TC_PSEL_UPD_PROS_03_42827 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 2)
	public void TC_PSEL_UPD_PROS_03_42827() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_03_42827========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_03_42827";
				BaseTest.sOTMTestcaseID = "42827";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_03_42827";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_03.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CPIVFN_042\",\"priceItemInfo\":\"CPIVFN_042\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEE02\",\"priceItemInfo\":\"PRICFEE02\",\"parameterInfo\":\"P1=!A1\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"}]}}}";
			    // Add prize list assignment to deal
			    List<String> sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelection(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sListOfPriceItemCodeAfterUpdate);
				
				// Verify Presence of Price Item in Read Price Item Group Selection Response
				DM.FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse(6,sSheetName, sWorkbook, sListOfPriceItemCodeAfterUpdate);				
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_03_42827========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_04_Val_42828
	 * Script Description				: 	TC_PSEL_UPD_PROS_04_Val_42828 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 3)
	public void TC_PSEL_UPD_PROS_04_Val_42828() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_04_Val_42828========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_04_Val_42828";
				BaseTest.sOTMTestcaseID = "42828";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_04_42828";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_04_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CPIVFN_042\",\"priceItemInfo\":\"CPIVFN_042\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
	
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_04_Val_42828========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_05_Val_42829
	 * Script Description				: 	TC_PSEL_UPD_PROS_05_Val_42829 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 4)
	public void TC_PSEL_UPD_PROS_05_Val_42829() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_05_Val_42829========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_05_Val_42829";
				BaseTest.sOTMTestcaseID = "42829";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_05_42829";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_05_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"SPI_021\",\"priceItemInfo\":\"SPI_021\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
	
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_05_Val_42829========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_06_Val_42830
	 * Script Description				: 	TC_PSEL_UPD_PROS_06_Val_42830 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 5)
	public void TC_PSEL_UPD_PROS_06_Val_42830() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_06_Val_42830========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_06_Val_42830";
				BaseTest.sOTMTestcaseID = "42830";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_06_42830";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_06_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealResponse\":\"\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CPIVFN_042\",\"priceItemInfo\":\"CPIVFN_042\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATETesting,REPORTING/Test\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"}]}}}";
			    //String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealResponse\":\"\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATETesting,REPORTING/Test\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    DM.FnUpdatePrizeItemGroupSelection(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_06_Val_42830========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_07_Val_42831
	 * Script Description				: 	TC_PSEL_UPD_PROS_07_Val_42831 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 6)
	public void TC_PSEL_UPD_PROS_07_Val_42831() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_07_Val_42831========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_07_Val_42831";
				BaseTest.sOTMTestcaseID = "42831";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_07_42831";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_07_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
	
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_07_Val_42831========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_08_Val_42844
	 * Script Description				: 	TC_PSEL_UPD_PROS_08_Val_42844 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 7)
	public void TC_PSEL_UPD_PROS_08_Val_42844() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_08_Val_42844========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_08_Val_42844";
				BaseTest.sOTMTestcaseID = "42844";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_08_42844";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_08_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelectionForSinglePriceItem(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price Item Code Is-> " + sListOfPriceItemCodeAfterUpdate);
				CF.FnWriteCellValue(3, 8, sListOfPriceItemCodeAfterUpdate, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_08_Val_42844========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_11_Val_42845
	 * Script Description				: 	TC_PSEL_UPD_PROS_11_Val_42845 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 8)
	public void TC_PSEL_UPD_PROS_11_Val_42845() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_11_Val_42845========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_11_Val_42845";
				BaseTest.sOTMTestcaseID = "42845";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_11_42845";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_11_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				// To Change user for sending new request
				WF.FnUserChange("PMBK1");
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_11_Val_42845========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_12_Val_42846
	 * Script Description				: 	TC_PSEL_UPD_PROS_12_Val_42846 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 9)
	public void TC_PSEL_UPD_PROS_12_Val_42846() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_12_Val_42846========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_12_Val_42846";
				BaseTest.sOTMTestcaseID = "42846";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_12_42846";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_12_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelectionForSinglePriceItem(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price Item Code Is-> " + sListOfPriceItemCodeAfterUpdate);
				CF.FnWriteCellValue(3, 8, sListOfPriceItemCodeAfterUpdate, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_12_Val_42846========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_15_42847
	 * Script Description				: 	TC_PSEL_UPD_PROS_15_42847 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 10)
	public void TC_PSEL_UPD_PROS_15_42847() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_15_42847========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_15_42847";
				BaseTest.sOTMTestcaseID = "42847";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_15_42847";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_15.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelectionForSinglePriceItem(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price Item Code Is-> " + sListOfPriceItemCodeAfterUpdate);
				CF.FnWriteCellValue(3, 8, sListOfPriceItemCodeAfterUpdate, sSheetName, sWorkbook);			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_15_42847========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_11_Val_42845
	 * Script Description				: 	TC_PSEL_UPD_PROS_11_Val_42845 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 11)
	public void TC_PSEL_UPD_PROS_16_Val_42848() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_16_Val_42848========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_16_Val_42848";
				BaseTest.sOTMTestcaseID = "42848";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_16_42848";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_16_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=EURO\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_16_Val_42848========");
	}		
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_17_Val_42849
	 * Script Description				: 	TC_PSEL_UPD_PROS_17_Val_42849 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 12)
	public void TC_PSEL_UPD_PROS_17_Val_42849() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_17_Val_42849========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_17_Val_42849";
				BaseTest.sOTMTestcaseID = "42849";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_17_42849";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_17_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_17_Val_42849========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_18_Val_42850
	 * Script Description				: 	TC_PSEL_UPD_PROS_18_Val_42850 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 13)
	public void TC_PSEL_UPD_PROS_18_Val_42850() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_18_Val_42850========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_18_Val_42850";
				BaseTest.sOTMTestcaseID = "42850";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_18_42850";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_18_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_18_Val_42850========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_19_Val_42851
	 * Script Description				: 	TC_PSEL_UPD_PROS_19_Val_42851 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 14)
	public void TC_PSEL_UPD_PROS_19_Val_42851() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_19_Val_42851========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_19_Val_42851";
				BaseTest.sOTMTestcaseID = "42851";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_19_42851";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_19_Val.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"Update_Test_PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelectionForSinglePriceItem(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price Item Code Is-> " + sListOfPriceItemCodeAfterUpdate);
				CF.FnWriteCellValue(3, 8, sListOfPriceItemCodeAfterUpdate, sSheetName, sWorkbook);	
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection1= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				List<String> sListOfPriceItemCode1 = DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection1,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sListOfPriceItemCode1);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_19_Val_42851========");
	}
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_22_42854
	 * Script Description				: 	TC_PSEL_UPD_PROS_22_42854 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 15)
	public void TC_PSEL_UPD_PROS_22_42854() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_22_42854========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_22_42854";
				BaseTest.sOTMTestcaseID = "42854";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_22_42854";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_22.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CPIVFN_042\",\"priceItemInfo\":\"CPIVFN_042\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"false\"}]}}}";
			    // Add prize list assignment to deal
			    DM.FnUpdatePrizeItemGroupSelection(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_22_42854========");
	}	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_23_42855
	 * Script Description				: 	TC_PSEL_UPD_PROS_23_42855 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/	
	@Test(priority = 16)
	public void TC_PSEL_UPD_PROS_23_42855() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_23_42855========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_23_42855";
				BaseTest.sOTMTestcaseID = "42855";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_UPD_PROS_23_42855";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_23.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				

				
				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
								
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sListOfPriceItemCodeAfterUpdate = DM.FnUpdatePrizeItemGroupSelectionForSinglePriceItem(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price Item Code Is-> " + sListOfPriceItemCodeAfterUpdate);
				CF.FnWriteCellValue(3, 8, sListOfPriceItemCodeAfterUpdate, sSheetName, sWorkbook);	
				
				/*'################ Simulate Deal By Request ############################# */
				// Input data for adding price list to deal
			    String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
			    String sDealIdAfterDealSimulation = DM.FnSimulateDealByRequest(sSimulateDeal,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Deal ID After Simulation Is-> " + sDealIdAfterDealSimulation);
				CF.FnWriteCellValue(3, 9, sDealIdAfterDealSimulation, sSheetName, sWorkbook);
				
				/*'################ Send Deal For Approval By RM ############################# */
				// Input data for adding price list to deal
			    String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
			    String sDealIdAfterSendingDealForApproval = DM.FnSendDealForApprovalByRequest(sSendDealForApproval,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Deal ID Sent For Approval Is-> " + sDealIdAfterSendingDealForApproval);
				CF.FnWriteCellValue(3, 10, sDealIdAfterSendingDealForApproval, sSheetName, sWorkbook);
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerAgreed\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    String sErrorMessage = DM.FnUpdatePrizeItemGroupSelectionForErrorVerification(sUpdatePriceItemGroupSelection1,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				CF.FnWriteCellValue(6, 1, sErrorMessage, sSheetName, sWorkbook);
	
				// Verify Error Message
				CF.FnVerifyErrorMessage(6, sSheetName, sWorkbook);		
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_23_42855========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_PSEL_UPD_PROS_24_43205
	 * Script Description				: 	TC_PSEL_UPD_PROS_24_43205 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	26-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority = 17)
	public void TC_PSEL_UPD_PROS_24_43205() throws Exception {
		log.info("=======Starting TC_PSEL_UPD_PROS_24_43205========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Management IWS";
				BaseTest.sScenario = "Price Item Group Selection scenario";
				BaseTest.sScriptName = "TC_PSEL_UPD_PROS_24_43205";
				BaseTest.sOTMTestcaseID = "43205";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_price_item_group_selection_iws/Deal_Price_Item_Group_Selection_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_PSEL_READ_PROS_24_43205";
				String sJsonFileURLForPriceListAssignment = "databank\\services\\deal_price_item_group_selection_iws\\json_files\\TC_PSEL_UPD_PROS_24.json";
				System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal - Price Item Group Selection scenario";

				String sProspectPersonId = CF.FnGetCellValue(3,1,sSheetName, sWorkbook).toString().trim();
				String sDealIdentifier   = CF.FnGetCellValue(3,2,sSheetName, sWorkbook).toString().trim();
				String sPriceListId      = CF.FnGetCellValue(3,6,sSheetName, sWorkbook).toString().trim();
				
				sDateName = getCurrentDate();
				sDealIdentifier = sDealIdentifier + "_"+ sDateName;
				CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				CF.FnWriteCellValue(3, 3, sDealIdentifier, sSheetName, sWorkbook);
				
				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnWriteCellValue(3, 4, sStartDate, sSheetName, sWorkbook);

				CF.FnTestCaseStatusReport("Pass","Deal Propect Person ID used for Deal creation Is-> " + sProspectPersonId);
				CF.FnTestCaseStatusReport("Pass","Price List ID used for Price List Assignemnt Is-> "  + sPriceListId);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'"+ sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                String sDealId = DealDetails.get("sDealId");
				CF.FnWriteCellValue(3, 5, sDealId, sSheetName, sWorkbook);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding price list to deal
			    String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"ADD\",\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'"+ sStartDate + "',\"priority\":\"2\",\"priceListId\":'"+ sPriceListId + "'}]}}";
			    // Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);
				CF.FnWriteCellValue(3, 7, sPrizelistAssignmentID, sSheetName, sWorkbook);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				
				
				/*'################ Update Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sUpdatePriceItemGroupSelection = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealIdentifier\":'"+ sDealIdentifier + "'},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_042\",\"priceItemInfo\":\"CPIVFN_042\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_043\",\"priceItemInfo\":\"CPIVFN_043\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_044\",\"priceItemInfo\":\"CPIVFN_044\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_046\",\"priceItemInfo\":\"CPIVFN_046\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_047\",\"priceItemInfo\":\"CPIVFN_047\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_048\",\"priceItemInfo\":\"CPIVFN_048\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_051\",\"priceItemInfo\":\"CPIVFN_051\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_052\",\"priceItemInfo\":\"CPIVFN_052\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_053\",\"priceItemInfo\":\"CPIVFN_053\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFN_055\",\"priceItemInfo\":\"CPIVFN_055\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFY_041\",\"priceItemInfo\":\"CPIVFY_041\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFY_045\",\"priceItemInfo\":\"CPIVFY_045\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFY_049\",\"priceItemInfo\":\"CPIVFY_049\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFY_050\",\"priceItemInfo\":\"CPIVFY_050\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CPIVFY_054\",\"priceItemInfo\":\"CPIVFY_054\",\"assignmentLevel\":\"DefaultPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLB_RMB\",\"priceItemInfo\":\"GLB_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.UK,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_CURRENCY=USD,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_COUNTRY=TFM.USA,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_CURRENCY=GBP,TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"GLOBAL\",\"priceItemInfo\":\"GLOBAL\",\"parameterInfo\":\"TFM_TYPE=AB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"P18_RMB\",\"priceItemInfo\":\"P18_RMB\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT501\",\"priceItemInfo\":\"PDT501\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT502\",\"priceItemInfo\":\"PDT502\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT503\",\"priceItemInfo\":\"PDT503\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT504\",\"priceItemInfo\":\"PDT504\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT505\",\"priceItemInfo\":\"PDT505\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PDT506\",\"priceItemInfo\":\"PDT506\",\"parameterInfo\":\"MPP23_COUNTRY1=USA,MPP23_TYPE1=BT\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PRICFEE02\",\"priceItemInfo\":\"PRICFEE02\",\"parameterInfo\":\"P1=!A1\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PRICFEETEST\",\"priceItemInfo\":\"PRICFEETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PRICRATETEST\",\"priceItemInfo\":\"PRICRATETEST\",\"assignmentLevel\":\"GlobalPriceList\",\"includeFlag\":\"true\"}]}}}";
			    // Add prize list assignment to deal
			    DM.FnUpdatePrizeItemGroupSelection(sUpdatePriceItemGroupSelection,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				
				/*'################ Read Price Item Group Selection ############################# */
				// Input data for adding price list to deal
			    String sReadPriceItemGroupSelection1= "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":'"+ sDealId + "'}}}";
			    // Add prize list assignment to deal
				List<String> sListOfPriceItemCode1 = DM.FnReadPrizeItemGroupSelection(sReadPriceItemGroupSelection1,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sListOfPriceItemCode1);
				
				// Verify Presence of Price Item in Read Price Item Group Selection Response
				DM.FnVerifyPresenceOfPrizeItemInPriceGroupSelectionResponse(6,sSheetName, sWorkbook, sListOfPriceItemCode1);
				
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PSEL_UPD_PROS_24_43205========");
	}	

}