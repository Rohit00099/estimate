package test.java.ormbframework.testCases.services;


import java.sql.SQLException;
import java.util.Hashtable;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.ormbframework.BaseTest;
import test.java.ormbframework.pageEvents.DealManagementPageEvents;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

public class deal_creation_iws extends BaseTest {


	CommonFunctions CF = new CommonFunctions();
	ApplicationFunctions AF = new ApplicationFunctions();
	WebServiceFunctions WF = new WebServiceFunctions();
	DataBaseFunctions DB = new DataBaseFunctions();
	DealManagementPageEvents DM = new DealManagementPageEvents(getDriver());

	public static String sCreateDealProspectResource = "/rest/ouaf/api/iws/C1-ProspectPersonREST/prospPerson";
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

	public static Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
	public static String sDealId;
	public static String smodelId;
	public static String sErrorMessageIWS;


	public String getStartDate() throws Exception {
		sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
		System.out.println("Start Date = " + sStartDate);
		return sStartDate;
	}

	public String getCurrentDate() throws Exception {
		sDateName = CommonFunctions.FnGetUniqueId();
		return sDateName;
	}


    @BeforeClass
    public void beforeClass() throws Exception{
      System.out.println("Before Class method");

		try {
			
			//Excel Data to be used:
			String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
			String sSheetName = "Data_Creation_Env";
			
		    DB.FnUpdateBillableChargeDates(2243, sSheetName, sWorkbook);
		    DB.FnUpdateBillableChargeDates(2244, sSheetName, sWorkbook);
		    
		    DB.FnUpdateBillableChargeDates(2248, sSheetName, sWorkbook);
		    DB.FnUpdateBillableChargeDates(2249, sSheetName, sWorkbook);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      
    }





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43125
	 * Script Description			: 		Test_43125	[ Create Deal for prospect with siumation type "Customer" with skip reference ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 0)
	public void TC_Deal_Creation_IWS_Test_43125() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_431251 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43125";
				BaseTest.sOTMTestcaseID = "Test 43125";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_001.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_Deal_Prospect_01_1desc\",\"dealVersionDescription\":\"TC_Deal_Prospect_01_1descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_431251 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(47, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(51, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(52, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		


				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(56, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(60, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(64, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(68, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(76, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(80, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(84, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(88, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(94, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(98, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(102, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(108, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(112, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(116, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(120, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(124, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(128, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(132, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(136, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(145, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(145, sSheetName, sWorkbook, smodelId);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_431251========");
	}









	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43126
	 * Script Description			: 		Test_43126	[ Deal Creation with Reference,Create deal for prospect with simulation type "Customer" with reference another customer and select refer usage, price and UNCHECK include Hierarhcy ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 1)
	public void TC_Deal_Creation_IWS_Test_43126() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43126 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43126";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_02.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_02";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Prospect_02_v111desc\",\"dealVersionDescription\":\"ID_TC_Deal_Prospect_02_v111descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43126 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);


				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(150, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(154, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(159, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(163, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(167, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(171, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(179, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(183, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(187, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(191, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(197, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(201, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(205, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(211, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(215, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(219, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(223, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(227, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(231, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(235, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(239, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(248, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(248, sSheetName, sWorkbook, smodelId);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43126========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43127
	 * Script Description			: 		Test_43127	[Deal Creation with Deal Reference,Create Deal for prospect with Simulation type"Customer" with reference of another Deal with select all option ( Refer price, refer usage only)]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 2)
	public void TC_Deal_Creation_IWS_Test_43127() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43127 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43127";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_03.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_03";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Prospect_03desc\",\"dealVersionDescription\":\"ID_TC_Deal_Prospect_03descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43127 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");


				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(253, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		


				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(257, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(261, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(265, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(269, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(277, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(281, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(285, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(289, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(295, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(299, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(303, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(309, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(313, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(317, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(321, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(325, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(329, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(333, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(337, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(346, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(346, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43127========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43128
	 * Script Description			: 		Test_43128	[Create Deal for prospect with simulation type "Deal" with reference of another deal with select all option usage , price and include hierarchy]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 3)
	public void TC_Deal_Creation_IWS_Test_43128() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43128 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43128";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_03.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_03";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43128desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43128descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println(sDealIdentifier + " Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Got Expected Error i.e. -> " + DealDetails);

				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(351, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43128========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43129
	 * Script Description			: 		Test_43129	[Create deal for propsect with simulation type "Deal" with reference of another deal and select only pricing]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 4)
	public void TC_Deal_Creation_IWS_Test_43129() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43129 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43129";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_05.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_05";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43129_v1desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43129_v1descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":false,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43129 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(356, sSheetName, sWorkbook, sDealId);



				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		


				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(360, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(364, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(368, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(372, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(380, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(384, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(388, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(392, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(398, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(402, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(406, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(412, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(416, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(420, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(424, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(428, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(432, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(436, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(440, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(449, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(449, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43129========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43130
	 * Script Description			: 		Test_43130	[Create deal for propsect with simulation type "Deal" with reference of another deal and select only pricing and usage]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 5)
	public void TC_Deal_Creation_IWS_Test_43130() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43130 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43130";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_06.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_06";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43130_v1desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43130_v1descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43130 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(454, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(458, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(462, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(466, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(470, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(478, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(482, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(486, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(490, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(496, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(500, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(504, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(510, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(514, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(518, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(522, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(526, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(530, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(534, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(538, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(547, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(547, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43130========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43131
	 * Script Description			: 		Test_43131	[Create deal for propsect with simulation type "Deal" with reference of another deal and select only usage]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 6)
	public void TC_Deal_Creation_IWS_Test_43131() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43131 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43131";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_07.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_07";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43131desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43131descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43131 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(552, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(556, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(557, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(561, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(565, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(569, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(573, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(581, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(585, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(589, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(593, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(599, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(603, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(607, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(613, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(617, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(621, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(625, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(629, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(633, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(637, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(641, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(650, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(650, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43131========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43132
	 * Script Description			: 		Test_43132	[Create deal for prospect with simulation type "Deal" with skip reference]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 7)
	public void TC_Deal_Creation_IWS_Test_43132() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43132 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43132";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_08.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_08";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43132desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43132descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43132 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(655, sSheetName, sWorkbook, sDealId);

				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(659, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(660, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(664, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(668, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(672, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(676, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(684, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(688, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(692, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(696, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(702, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(706, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(710, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(716, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(720, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(724, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(728, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(732, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(736, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(740, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(744, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(753, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(753, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43132========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43133
	 * Script Description			: 		Test_43133	[Create deal for prospect ( multiple Hierarhcy) with simulation type "Customer" and skip reference false with include Hierarchy option "YES" and refer Pricing]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 8)
	public void TC_Deal_Creation_IWS_Test_43133() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43133 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43133";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_09.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_09";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43133desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43133descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":false,\"referPriceSw\":true,\"includeChildHierarchy\":true}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43133 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");


				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(758, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(762, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(766, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(770, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(774, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(782, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(786, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(790, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(794, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(800, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(804, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(808, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(814, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(818, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(822, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(826, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(830, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(834, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(838, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(842, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(851, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(851, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43133========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43134
	 * Script Description			: 		Test_43134	[Create deal for prospect ( multiple Hierarchy) with simulation type "Customer" and skip reference with include Hierarhcy option "No" and refer Usage as "Yes"]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 9)
	public void TC_Deal_Creation_IWS_Test_43134() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43134 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43134";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_10.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_10";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43134desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43134descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43134 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(856, sSheetName, sWorkbook, sDealId);



				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(860, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(861, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(865, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(869, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(873, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(877, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(885, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(889, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(893, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(897, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(903, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(907, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(911, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(917, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(921, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(925, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(929, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(933, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(937, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(941, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(945, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(954, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(954, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43134========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43135
	 * Script Description			: 		Test_43135	[Create deal for prospect with contracted deal option "Yes" with skip reference true]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 10)
	public void TC_Deal_Creation_IWS_Test_43135() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43135 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43135";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_11.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_11";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43135desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43135descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":true,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43135 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(959, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(963, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(964, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(968, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(972, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(976, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(980, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(988, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(992, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(996, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1000, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1006, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1010, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1014, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1020, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1024, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1028, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1032, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1036, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1040, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1044, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1048, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1057, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1057, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43135========");
	}









	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43136
	 * Script Description			: 		Test_43136	[Create deal for prospect with contracted deal option "Yes" with reference deal scenarios i.e skip reference false]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 11)
	public void TC_Deal_Creation_IWS_Test_43136() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43136 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43136";
				BaseTest.sOTMTestcaseID = "";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_12.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_12";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43136desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43136descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":true}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43136 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1062, sSheetName, sWorkbook, sDealId);



				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(1066, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1070, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1074, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1078, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1086, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1090, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1094, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1098, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1104, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1108, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1112, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1118, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1122, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1126, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1130, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1134, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1138, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1142, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1146, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1155, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1155, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43136========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43137
	 * Script Description			: 		Test_43137	[Create deal for prospect with contracted deal "YES" with reference of another customer i.e refer Person but at the same time keep referenceDealId details;system should only refer person instead deal]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 12)
	public void TC_Deal_Creation_IWS_Test_43137() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43137 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43137";
				BaseTest.sOTMTestcaseID = "Test 43137";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_13.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_13";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43137desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43137descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referenceDealId\":\"6739465360\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":true}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43137 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1160, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1164, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(1169, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1173, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1177, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1181, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1189, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1193, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1197, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1201, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1207, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1211, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1215, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1221, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1225, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1229, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1233, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1237, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1241, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1245, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1249, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1258, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1258, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43137========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43138
	 * Script Description			: 		Test_43138	[Verify to create deal for prospect with template deal option "YES" with reference deal scenario and that deal to be check in the table "c1_deal_references" and in the copy template ora search field in the SCREEN]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 13)
	public void TC_Deal_Creation_IWS_Test_43138() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43138 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43138";
				BaseTest.sOTMTestcaseID = "Test 43138";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_14.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_14";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43138desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43138descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referPersonId\":\"3091575921\",\"referenceDealId\":\"6739465360\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":true}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43138 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1263, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(1267, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1271, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1275, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1279, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1287, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1291, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1295, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1299, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1305, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1309, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1313, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1319, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1323, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1327, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1331, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1335, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1339, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1343, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1347, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1356, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1356, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43138========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43139
	 * Script Description			: 		Test_43139	[Create deal for prospect and refer template which have "dealEntityType" as PRSP and "simulationTypeFlag" as CUST & "includeHierarchyFlag" as True thus system should throw error instead of creating deal]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 14)
	public void TC_Deal_Creation_IWS_Test_43139() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43139 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43139";
				BaseTest.sOTMTestcaseID = "Test_43139";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_15.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_15";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"startDate\":\"" + sStartDate + "\",\"skipReferenceFlag\":\"true\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"templateDealId\":\"1683082995\",\"copyBasicDetailsFlag\":\"true\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43139 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1361, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43139========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43140
	 * Script Description			: 		Test_43140	[ Create deal for Prospect with copy template deal with option basic details only with skip reference ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 15)
	public void TC_Deal_Creation_IWS_Test_43140() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43140 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Create deal for Prospect with copy template deal with option basic details only with skip reference";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43140";
				BaseTest.sOTMTestcaseID = "Test_43140";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_16.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_16";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"startDate\":\"" + sStartDate + "\",\"skipReferenceFlag\":\"true\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"templateDealId\":\"1683082995\",\"copyBasicDetailsFlag\":\"true\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43140 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1366, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(1370, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1374, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1378, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1382, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1390, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1394, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1398, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1402, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1408, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1412, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1416, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1422, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1426, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1430, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1434, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1438, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1442, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1446, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1450, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1459, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1459, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43140========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43141
	 * Script Description			: 		Test_43141 [ Create deal for Prospect with copy template deal with option pricing , usage with skip reference ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 16)
	public void TC_Deal_Creation_IWS_Test_43141() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43141 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Create deal for Prospect with copy template deal with option pricing , usage with skip reference";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43141";
				BaseTest.sOTMTestcaseID = "Test 43141";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_17.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_17";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"templateDealId\":\"1683082995\",\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43141 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1464, sSheetName, sWorkbook, sDealId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
				DB.FnVerifyPricingAndCommitmentDetails(1468, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1472, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1476, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1480, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1488, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1492, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1496, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1500, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1506, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1510, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1514, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1520, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1524, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1528, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1532, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1536, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1540, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1544, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1548, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1557, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1557, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43141========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43142
	 * Script Description			: 		Test_43142	[ Create deal for Prospect with copy template deal with option basic details, pricing , usage with reference another customer with refer usage, pricing so pricing & commitment copy from reference screen ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 17)
	public void TC_Deal_Creation_IWS_Test_43142() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43142 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Create deal for Prospect with copy template deal with option basic details, pricing , usage with reference another customer with refer usage, pricing so pricing & commitment copy from reference screen";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43142";
				BaseTest.sOTMTestcaseID = "Test 43142";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_18.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_18";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43142desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43142descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"templateDealId\":\"1683082995\",\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referenceDealId\":\"6739465360\",\"referenceModelId\":\"4402189258\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43142 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1562, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1566, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		
//
//				//Function to verify Deal Proposed SQI & Pricing Details from DB Table
//				DB.FnVerifyPricingAndCommitmentDetails(1571, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1575, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1579, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1583, sSheetName, sWorkbook, smodelId);
//
//				DB.FnVerifyPricingAndCommitmentDetails(1591, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1595, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1599, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1603, sSheetName, sWorkbook, smodelId);
//
//				DB.FnVerifyPricingAndCommitmentDetails(1609, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1613, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1617, sSheetName, sWorkbook, smodelId);
//
//				DB.FnVerifyPricingAndCommitmentDetails(1623, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1627, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1631, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1635, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1639, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1643, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1647, sSheetName, sWorkbook, smodelId);
//				DB.FnVerifyPricingAndCommitmentDetails(1651, sSheetName, sWorkbook, smodelId);

				//function to verify simulation Details from DB Table
				DB.Deal_Simulation_Summary(1660, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1660, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43142========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43143
	 * Script Description			: 		Test_43143 [ Create deal for Prospect with copy template deal with Existing Customer Deal(which has no template), system should throw error "The deal template is invalid ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 18)
	public void TC_Deal_Creation_IWS_Test_43143() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43143 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Deal Template";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43143";
				BaseTest.sOTMTestcaseID = "Test 43143";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_19.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_19";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":\"3736477346\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_IWS_Test_43143desc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_IWS_Test_43143descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"templateDealId\":\"8243675951\",\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43143 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1665, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43143========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43144
	 * Script Description			: 		Test_43144	[ Error Message if create deal for prospect customer/ Existing Customer and select simulation type "Account" ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 19)
	public void TC_Deal_Creation_IWS_Test_43144() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43144 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Deal creation and Simulation type- Account";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43144";
				BaseTest.sOTMTestcaseID = "Test 43144";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_21.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_21";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"ACCT\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43144 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1670, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43144========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43145
	 * Script Description			: 		Test_43145	[ Error Message if create deal for existing account and selection type "Deal/ Customer" ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 20)
	public void TC_Deal_Creation_IWS_Test_43145() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43145 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Deal Creation for Entity Type ACCOUNT";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43145";
				BaseTest.sOTMTestcaseID = "Test 43145";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_22.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_22";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"accountIdentifier\":\"EAI_TemplateReference_Cust_001\",\"accountIdentifierType\":\"C1_F_ANO\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EACC\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43145 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1675, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43145========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43146
	 * Script Description			: 		Test_43146	[ Error Message if deal created for "ind" division and added different deal type which has "930" Division ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 21)
	public void TC_Deal_Creation_IWS_Test_43146() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43146 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Division and Deal Type Validation";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43146";
				BaseTest.sOTMTestcaseID = "Test 43146";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_23.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_23";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"930\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43146 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1680, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43146========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43147
	 * Script Description			: 		Test_43147	[ Error message should be displayed if deal identifier has kept blank ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 22)
	public void TC_Deal_Creation_IWS_Test_43147() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43147 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Blank Deal Identifier";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43147";
				BaseTest.sOTMTestcaseID = "Test 43147";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_24.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_24";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43147 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1685, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43147========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43148
	 * Script Description			: 		Test_43148	[ Error message should be displayed if deal identifier already present in the system ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 23)
	public void TC_Deal_Creation_IWS_Test_43148() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43148 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Duplicate Deal Identifier";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43148";
				BaseTest.sOTMTestcaseID = "Test 43148";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_25.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "ID_Reference_Customer_001"; //Verify duplTC_Deal_Creation_API_25 reference deal deal_identifier


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43148 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1690, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43148========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43149
	 * Script Description			: 		Test_43149	[ Error message should be displayed if deal type has kept blank ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 24)
	public void TC_Deal_Creation_IWS_Test_43149() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43149 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Blank Deal Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43149";
				BaseTest.sOTMTestcaseID = "Test 43149";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_26.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_26";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43149 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1695, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43149========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43150
	 * Script Description			: 		Test_43150	[ Error message should be displayed if invalid deal type has given ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 25)
	public void TC_Deal_Creation_IWS_Test_43150() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43150 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Deal Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43150";
				BaseTest.sOTMTestcaseID = "Test 43150";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_27.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_27";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				//Function To Update Deal Type
				DB.UpdateDealType();

				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT1\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43150 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1700, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43150========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43151
	 * Script Description			: 		Test_43151	[ Error message should be displayed if Invalid deal currency has given ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 26)
	public void TC_Deal_Creation_IWS_Test_43151() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43151 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Deal Currency";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43151";
				BaseTest.sOTMTestcaseID = "Test 43151";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_28.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_28";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"IN0\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43151 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1705, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43151========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43152
	 * Script Description			: 		Test_43152	[ Error message if include Hierarhcy flag/check box is "yes" for the case where Simulation type is "Deal" ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 27)
	public void TC_Deal_Creation_IWS_Test_43152() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43152 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Include Hierarchy Flag and Simulation Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43152";
				BaseTest.sOTMTestcaseID = "Test 43152";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_29.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_29";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43152 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);


				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1710, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43152========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43153
	 * Script Description			: 		Test_43153	[ Error message if given start date is less than the current date ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 28)
	public void TC_Deal_Creation_IWS_Test_43153() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43153 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Validation message for earlier start date";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43153";
				BaseTest.sOTMTestcaseID = "Test 43153";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_30.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_30";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"2020-07-01\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43153 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1715, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43153========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43154
	 * Script Description			: 		Test_43154	[ Error Message if end date is less than the start date ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 29)
	public void TC_Deal_Creation_IWS_Test_43154() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43154 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Validation message when earlier end date has given";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43154";
				BaseTest.sOTMTestcaseID = "Test 43154";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_31.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_31";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2020-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43154 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1720, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43154========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43155
	 * Script Description			: 		Test_43155	[ Error message if start date is blank ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 30)
	public void TC_Deal_Creation_IWS_Test_43155() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43155 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Blank Start Date";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43155";
				BaseTest.sOTMTestcaseID = "Test 43155";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_32.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_32";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43155 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1725, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43155========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43156
	 * Script Description			: 		Test_43156	[ Error message if given price selection date is blank ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 31)
	public void TC_Deal_Creation_IWS_Test_43156() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43156 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Blank Price Selection Date";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43156";
				BaseTest.sOTMTestcaseID = "Test 43156";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_33.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_33";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43156 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1730, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43156========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43157
	 * Script Description			: 		Test_43157	[ Error message if given review frequency is invalid ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 32)
	public void TC_Deal_Creation_IWS_Test_43157() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43157 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Review Frequency";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43157";
				BaseTest.sOTMTestcaseID = "Test 43157";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_34.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_34";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"13\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43157 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1735, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43157========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43158
	 * Script Description			: 		Test_43158	[ Error message if given deal frequency is invalid ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 33)
	public void TC_Deal_Creation_IWS_Test_43158() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43158 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Deal Frequency";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43158";
				BaseTest.sOTMTestcaseID = "Test 43158";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_35.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_35";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"13\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43158 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1740, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43158========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43159
	 * Script Description			: 		Test_43159	[ Error message if given usage period is invalid ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 34)
	public void TC_Deal_Creation_IWS_Test_43159() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43159 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Usage Period";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43159";
				BaseTest.sOTMTestcaseID = "Test 43159";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_36.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_36";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"13\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43159 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1745, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43159========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43160
	 * Script Description			: 		Test_43160	[ Error message if given terms & conditions are invalid ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 35)
	public void TC_Deal_Creation_IWS_Test_43160() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43160 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid T&C";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43160";
				BaseTest.sOTMTestcaseID = "Test 43160";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_37.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_37";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C21\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43160 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1750, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43160========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43161
	 * Script Description			: 		Test_43161	[ Error message if given T&Cs are duplicate  ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 36)
	public void TC_Deal_Creation_IWS_Test_43161() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43161 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Duplicate T&C";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43161";
				BaseTest.sOTMTestcaseID = "Test 43161";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_40.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_40";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"},{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43161 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1755, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43161========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43162
	 * Script Description			: 		Test_43162	[ Error Message if invalid product has given ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 37)
	public void TC_Deal_Creation_IWS_Test_43162() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43162 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Product";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43162";
				BaseTest.sOTMTestcaseID = "Test 43162";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_41.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_41";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"},{\"productCode\":\"PRODUCT_CON_22\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43162 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1760, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43162========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43163
	 * Script Description			: 		Test_43163	[ Error Message if we add duplicate product ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 38)
	public void TC_Deal_Creation_IWS_Test_43163() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43163 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Duplicate Product Validation";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43163";
				BaseTest.sOTMTestcaseID = "Test 43163";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_42.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_42";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"},{\"productCode\":\"PRODUCT_CON_02\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43163 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1765, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43163========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43164
	 * Script Description			: 		Test_43164	[ Validation if invalid characterstics type in the characterstics object has given ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 39)
	public void TC_Deal_Creation_IWS_Test_43164() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43164 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Characteristics Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43164";
				BaseTest.sOTMTestcaseID = "Test 43164";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_43.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_43";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":{\"characteristicType\":\"C1CONTD\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"N\"}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43164 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1770, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43164========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43165
	 * Script Description			: 		Test_43165	[ Add same characterstics type multiple times in the characterstics object ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 40)
	public void TC_Deal_Creation_IWS_Test_43165() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43165 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Duplicate Characteristics Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43165";
				BaseTest.sOTMTestcaseID = "Test 43165";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_44.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_44";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"N\"},{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"N\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43165 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1775, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43165========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43166
	 * Script Description			: 		Test_43166	[ Blank Effective Date in Characteristics Object ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 41)
	public void TC_Deal_Creation_IWS_Test_43166() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43166 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Duplicate Characteristics Type";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43166";
				BaseTest.sOTMTestcaseID = "Test 43166";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_45.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_45";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"\",\"characteristicValue\":\"N\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43166 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1780, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43166========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43167
	 * Script Description			: 		Test_43167	[ Verify validation when valid characterstics type and effective date have given but blank Characterstics value in the characterstics object ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/

	@Test(priority = 42)
	public void TC_Deal_Creation_IWS_Test_43167() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43167 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Blank Characteristics Value in Characteristics Object";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43167";
				BaseTest.sOTMTestcaseID = "Test 43167";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_46.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_46";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43167 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1785, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43167========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43168
	 * Script Description			: 		Test_43168	[ Verify validation when valid characterstic type and date given but invalid characteristics value in the characterstics object ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 43)
	public void TC_Deal_Creation_IWS_Test_43168() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43168 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Invalid Characteristics value in Characteristic object";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43168";
				BaseTest.sOTMTestcaseID = "Test 43168";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_47.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_47";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"NY\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43168 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1790, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43168========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43169
	 * Script Description			: 		Test_43169	[ Create deal for prospect with simulation type "Customer" with different date format ( for ex - yyyy-mm-dd) ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 44)
	public void TC_Deal_Creation_IWS_Test_43169() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43169 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Deal Creation with different Date Format";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43169";
				BaseTest.sOTMTestcaseID = "Test 43169";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_48.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_48";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";


				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"06-12-2022\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"N\"}]}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43169 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1795, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43169========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_43170
	 * Script Description			: 		Test_43170	[ Verify when skip reference flag value is false and we don't pass reference field i.e Reference Type Flag field ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 45)
	public void TC_Deal_Creation_IWS_Test_43170() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_43170 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Reference Type Flag field";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_43170";
				BaseTest.sOTMTestcaseID = "Test 43170";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_49.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_49";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifier\":\"Reg_Deal_Creation_API_USER\",\"prsPerIdentifierType\":\"COREG\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ID_TC_Deal_Creation_APIdesc\",\"dealVersionDescription\":\"ID_TC_Deal_Creation_APIverdesc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"endDate\":\"2050-07-05\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"dealCharacteristics\":[{\"characteristicType\":\"C1CONTDL\",\"effectiveDate\":\"2020-07-01\",\"characteristicValue\":\"N\"}],\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"true\",\"copyUsageFlag\":\"true\"}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_43170 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1800, sSheetName, sWorkbook, sErrorMessageIWS);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_43170========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_39944
	 * Script Description			: 		Test_39944	[ Verify deal simulation for Existing Customer , simulation type Deal and Skip Reference ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 46)
	public void TC_Deal_Creation_IWS_Test_39944() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_39944 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Verify deal simulation for Existing Customer , simulation type Deal and Skip Reference";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_39944";
				BaseTest.sOTMTestcaseID = "Test 39944";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_50.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_50";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_Deal_Creation_IWS_Test_39944desc\",\"dealVersionDescription\":\"TC_Deal_Creation_IWS_Test_39944descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_39944 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1805, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1809, sSheetName, sWorkbook, sDealId, smodelId);
				///dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1810, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		


				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1814, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1818, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1822, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1827, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1834, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1838, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1842, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1846, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1852, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1856, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1860, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1866, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1870, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1874, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1878, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1882, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1886, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1890, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1894, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1903, sSheetName, sWorkbook, smodelId); //Raised Bug 34777630 - ZERO 'PROFITABILITY_VAL' VALUE SHOWING IN 'C1_DEAL_SIMULATION_SMRY' TABLE 


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1903, sSheetName, sWorkbook, smodelId);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_39944========");
	}








	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_39945
	 * Script Description			: 		Test_39945	[ Verify deal simulation for Existing Customer , simulation type deal and reference by another customer with refer usage and refer price both ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 47)
	public void TC_Deal_Creation_IWS_Test_39945() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_39945 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Verify deal simulation for Existing Customer , simulation type deal and reference by another customer with refer usage and refer price both";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_39945";
				BaseTest.sOTMTestcaseID = "Test 39945";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_51.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_51";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);
				
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"dealDescription\":\"TC_Deal_Creation_IWS_Test_39945desc\",\"dealVersionDescription\":\"TC_Deal_Creation_IWS_Test_39945descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"3\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_39945 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1908, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1912, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1914, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CC_021\",\"priceItemInfo\":\"CC_021\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CC_022\",\"priceItemInfo\":\"CC_022\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		


				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1918, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1922, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1926, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1930, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1938, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1942, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1946, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1950, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1954, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1958, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1964, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1968, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1972, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(1978, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1982, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1986, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1990, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1994, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(1998, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2002, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2006, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Simulation Summary Details from DB Table
				//DB.Deal_Simulation_Summary(2015, sSheetName, sWorkbook, smodelId); //Raised Bug 34777630 - ZERO 'PROFITABILITY_VAL' VALUE SHOWING IN 'C1_DEAL_SIMULATION_SMRY' TABLE 


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(2015, sSheetName, sWorkbook, smodelId);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_39945========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_39946
	 * Script Description			: 		Test_39946	[ Verify deal simulation for Existing Customer , simulation type Deal and reference by another customer with refer usage only and skip refer price option ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 48)
	public void TC_Deal_Creation_IWS_Test_39946() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_39946 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Verify deal simulation for Existing Customer , simulation type Deal and reference by another customer with refer usage only and skip refer price option";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_39946";
				BaseTest.sOTMTestcaseID = "Test 39946";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_52.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_52";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";
				
				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"dealDescription\":\"TC_Deal_Creation_IWS_Test_39945desc\",\"dealVersionDescription\":\"TC_Deal_Creation_IWS_Test_39945descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_39946 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(2020, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(2024, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(2026, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CC_021\",\"priceItemInfo\":\"CC_021\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CC_022\",\"priceItemInfo\":\"CC_022\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(2020, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(2020, sSheetName, sWorkbook, smodelId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(2030, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2034, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2038, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2042, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(2050, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2054, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2058, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2062, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2066, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2070, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(2076, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2080, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2084, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPricingAndCommitmentDetails(2090, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2094, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2098, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2102, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2106, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2110, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2114, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2118, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Simulation Summary Details from DB Table
				//DB.Deal_Simulation_Summary(2127, sSheetName, sWorkbook, smodelId); //Raised Bug 34777630 - ZERO 'PROFITABILITY_VAL' VALUE SHOWING IN 'C1_DEAL_SIMULATION_SMRY' TABLE 


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(2127, sSheetName, sWorkbook, smodelId);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_39946========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_Deal_Creation_IWS_Test_39947
	 * Script Description			: 		Test_39947	[ Verify deal simulation for Existing Customer , simulation type Deal and reference by another customer with refer price and skip refer usage ]
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Creation_API_Test_Data.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 49)
	public void TC_Deal_Creation_IWS_Test_39947() throws Exception {
		log.info("<<<======= Starting TC_Deal_Creation_IWS_Test_39947 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal creation IWS";
				BaseTest.sScenario = "Verify deal simulation for Existing Customer , simulation type Deal and reference by another customer with refer price and skip refer usage";
				BaseTest.sScriptName = "TC_Deal_Creation_IWS_Test_39947";
				BaseTest.sOTMTestcaseID = "Test 39947";

				//Excel Data to be used:
				String sWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
				String sSheetName = "Data_Creation_Env";
				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				String sJsonFileURLForDealCreation = "databank\\services\\deal_creation_iws\\TC_Deal_Creation_API_53.json";
				System.out.println(sJsonFileURLForDealCreation);
				BaseTest.sTestDescription = "Deal Creation Using IWS Scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_Deal_Creation_API_53";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"DEAL\",\"dealDescription\":\"TC_Deal_Creation_IWS_Test_39947desc\",\"dealVersionDescription\":\"TC_Deal_Creation_IWS_Test_39947descver\",\"dealFrequency\":\"6\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"6\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":false,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";
				System.out.println("TC_Deal_Creation_IWS_Test_39947 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(2132, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(2136, sSheetName, sWorkbook, sDealId, smodelId);
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(2138, sSheetName, sWorkbook, sDealId, smodelId);


				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"CC_021\",\"priceItemInfo\":\"CC_021\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"CC_022\",\"priceItemInfo\":\"CC_022\",\"assignmentLevel\":\"Customer Price List\",\"hierarchyDetails\":\"CORPORATE BANKING,ACCOUNT SERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				System.out.println("Request ->" + sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				System.out.println("Request ->" + sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
		

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(2132, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(2132, sSheetName, sWorkbook, smodelId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(2178, sSheetName, sWorkbook, smodelId);
				DB.FnVerifyPricingAndCommitmentDetails(2182, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				//DB.Deal_Simulation_Summary(2239, sSheetName, sWorkbook, smodelId); //Raised Bug 34777630 - ZERO 'PROFITABILITY_VAL' VALUE SHOWING IN 'C1_DEAL_SIMULATION_SMRY' TABLE 

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(2239, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_Creation_IWS_Test_39947========");
	}







}