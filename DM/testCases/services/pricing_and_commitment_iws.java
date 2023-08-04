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

public class pricing_and_commitment_iws extends BaseTest {


	CommonFunctions CF = new CommonFunctions();
	ApplicationFunctions AF = new ApplicationFunctions();
	WebServiceFunctions WF = new WebServiceFunctions();
	DataBaseFunctions DB = new DataBaseFunctions();
	DealManagementPageEvents DM = new DealManagementPageEvents(getDriver());

	public static String sCreateDealProspectResource = "/rest/ouaf/api/iws/C1-ProspectPersonREST/prospPerson";
	public static String sCreateDealResource = "/rest/ouaf/api/iws/C1-DealREST/dealService";
	public static String sAddPrizeListAssignmentResource = "/rest/ouaf/api/iws/C1-DealPricelistAssignmentREST/dealPricelistAssignment";
	public static String sDealPricingAndCommitmentResource = "/rest/ouaf/api/iws/C1-DealPriceAsgnCommitmentsREST/DealPriceAsgnCommitments";
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
	public static String sDealIdentifier;
	public static String sErrorMessageIWS;

	//Excel Data to be used:
	public static String sWorkbook = "./databank/services/pricing_and_commitment_iws/Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx";
	public static String sSheetName = "Data_Creation_Env";

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

//      DB.FnUpdateBillableChargeDates(23, sSheetName, sWorkbook);
//      DB.FnUpdateBillableChargeDates(24, sSheetName, sWorkbook);

    }



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43525
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 66)
	public void TC_READ_PRICING_Test_43525() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43525 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43525";
				BaseTest.sOTMTestcaseID = "";

				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "If User Provide only Deal ID While Read Pricing";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43525";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43525desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43525descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				System.out.println("TC_READ_PRICING_Test_43525 Request ->" + sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println(sDealIdentifier + " Created Deal ID Is-> " + DealDetails);

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(35, sSheetName, sWorkbook, sDealId, smodelId);

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

				//############### This function to Read Pricing Details from IWS  #############//
				DM.FnPricingAndCommitmentIWS(40, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43525========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43544
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 67)
	public void TC_READ_PRICING_Test_43544() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43544 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43544";
				BaseTest.sOTMTestcaseID = "";

				BaseTest.sTestDescription = "Verify Read Operation by providing only Model ID";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43544";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43544desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43544descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43544 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(49, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				DM.FnPricingAndCommitmentIWS(54, sSheetName, sWorkbook, "NoValue", smodelId, "NoValue");




			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43544========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43545
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 68)
	public void TC_READ_PRICING_Test_43545() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43545 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43545";
				BaseTest.sOTMTestcaseID = "Test_43545";

				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43545";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43545desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43545descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43545 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(63, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				DM.FnPricingAndCommitmentIWS(68, sSheetName, sWorkbook, "NoValue", "NoValue", sDealIdentifier);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43545========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43546
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 69)
	public void TC_READ_PRICING_Test_43546() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43546 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43546";
				BaseTest.sOTMTestcaseID = "Test 43546";

				BaseTest.sTestDescription = "Verify Read Operation by providing Entity Type and Entity ID";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43546";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43546desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43546descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43546 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(77, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(82, sSheetName, sWorkbook, "NoValue", "NoValue", sDealIdentifier);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43546========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43547
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 70)
	public void TC_READ_PRICING_Test_43547() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43547 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43547";
				BaseTest.sOTMTestcaseID = "Test 43547";

				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'PRIC'";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43547";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43547desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43547descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43547 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(91, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(96, sSheetName, sWorkbook, "NoValue", "NoValue", sDealIdentifier);




			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43547========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43548
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 71)
	public void TC_READ_PRICING_Test_43548() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43548 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43548";
				BaseTest.sOTMTestcaseID = "Test 43548";

				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'COMT'";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43548";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43548desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43548descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43548 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(105, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(110, sSheetName, sWorkbook, "NoValue", smodelId, "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43548========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43549
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 72)
	public void TC_READ_PRICING_Test_43549() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43549 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43549";
				BaseTest.sOTMTestcaseID = "Test 43549";

				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'BOTH'";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43549";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43549desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43549descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43549 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(119, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(124, sSheetName, sWorkbook, "NoValue", "NoValue", sDealIdentifier);




			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43549========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43550
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 73)
	public void TC_READ_PRICING_Test_43550() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43550 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43550";
				BaseTest.sOTMTestcaseID = "Test 43550";

				BaseTest.sTestDescription = "Verify Read Operation by not providing 'inquiryModeFlag'";


				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43550";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43550desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43550descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43550 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(133, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(138, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");




			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43550========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43551
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 74)
	public void TC_READ_PRICING_Test_43551() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43551 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43551";
				BaseTest.sOTMTestcaseID = "Test 43551";

				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'PRIC' With 'Entity' Filter";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43551";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43551desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43551descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43551 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(147, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(152, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43551========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43552
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 75)
	public void TC_READ_PRICING_Test_43552() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43552 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43552";
				BaseTest.sOTMTestcaseID = "Test 43552";

				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'COMT' With 'Entity' Filter";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43552";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43552desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43552descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43552 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(161, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(166, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43552========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43553
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 76)
	public void TC_READ_PRICING_Test_43553() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43553 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43553";
				BaseTest.sOTMTestcaseID = "Test 43553";

				BaseTest.sTestDescription = "Verify Read Operation by providing 'inquiryModeFlag' as 'BOTH' With 'Entity' Filter";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43553";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43553desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43553descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43553 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(175, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(180, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43553========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43554
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 77)
	public void TC_READ_PRICING_Test_43554() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43554 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43554";
				BaseTest.sOTMTestcaseID = "Test 43554";

				BaseTest.sTestDescription = "Verify Read Operation Validation by providing 'Entity Type' Without 'Entity ID'";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43554";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43554desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43554descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43554 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(189, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(194, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43554========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_READ_PRICING_Test_43555
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 78)
	public void TC_READ_PRICING_Test_43555() throws Exception {
		log.info("<<<======= Starting TC_READ_PRICING_Test_43555 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_READ_PRICING_Test_43555";
				BaseTest.sOTMTestcaseID = "Test 43555";

				BaseTest.sTestDescription = "Deal Management Deal Simulation Verification";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_READ_PRICING_Test_43555";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"6772262353\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_READ_PRICING_Test_43555desc\",\"dealVersionDescription\":\"TC_READ_PRICING_Test_43555descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_READ_PRICING_Test_43555 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(203, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				DM.FnPricingAndCommitmentIWS(208, sSheetName, sWorkbook, "NoValue", smodelId, "NoValue");



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_READ_PRICING_Test_43555========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43567
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 44)
	public void TC_PRICING_OVRD_Test_43567() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43567 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43567";
				BaseTest.sOTMTestcaseID = "Test 43567";

				BaseTest.sTestDescription = "Price Assignment ID on which User wants to override should be given";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43567";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43567desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43567descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43567 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(213, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(217, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(213, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(213, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(213, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43567
				DM.FnPricingAndCommitmentIWS(222, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(227, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(227, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(227, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(233, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(233, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43567========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43584
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 45)
	public void TC_PRICING_OVRD_Test_43584() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43584 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43584";
				BaseTest.sOTMTestcaseID = "Test 43584";

				BaseTest.sTestDescription = "Price Assignment ID on which User wants to override should belong to the entity";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43584";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43584desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43584descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43584 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(238, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(242, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(238, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(238, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(238, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43584
				DM.FnPricingAndCommitmentIWS(247, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(252, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(252, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(252, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(258, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(258, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43584========");
	}


	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43585
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 46)
	public void TC_PRICING_OVRD_Test_43585() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43585 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43585";
				BaseTest.sOTMTestcaseID = "Test 43585";

				BaseTest.sTestDescription = "Valid Price Item and Parameter combination should be given while overriding.";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43585";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43585desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43585descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43585 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(263, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(267, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(263, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(263, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(263, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43585
				DM.FnPricingAndCommitmentIWS(272, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(277, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(277, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(277, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(283, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(283, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43585========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43586
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 47)
	public void TC_PRICING_OVRD_Test_43586() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43586 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43586";
				BaseTest.sOTMTestcaseID = "Test 43586";

				BaseTest.sTestDescription = "Only RM level user can override Price Assignment";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43586";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43586desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43586descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43586 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(288, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(292, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(288, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(288, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(288, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43586
				//USER Change to check Override validation
				WF.FnUserChange("PMBK1");
				DM.FnPricingAndCommitmentIWS(297, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(302, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(302, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(302, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(308, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(308, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43586========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43605
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 48)
	public void TC_PRICING_OVRD_Test_43605() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43605 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43605";
				BaseTest.sOTMTestcaseID = "Test 43605";

				BaseTest.sTestDescription = "Price Status Flag should be PRPD While Overriding";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43605";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43605desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43605descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43605 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(338, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(342, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(338, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(338, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(338, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43605
				DM.FnPricingAndCommitmentIWS(347, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(352, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(352, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(352, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(358, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(358, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43605========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43606
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 49)
	public void TC_PRICING_OVRD_Test_43606() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43606 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43606";
				BaseTest.sOTMTestcaseID = "Test 43606";

				BaseTest.sTestDescription = "Price Assignment Type can't be blank and should have valid values";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43606";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43606desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43606descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43606 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(363, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(367, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(363, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(363, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(363, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43606
				DM.FnPricingAndCommitmentIWS(372, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(377, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(377, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(377, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(383, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(383, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43606========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43607
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 50)
	public void TC_PRICING_OVRD_Test_43607() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43607 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43607";
				BaseTest.sOTMTestcaseID = "Test 43607";

				BaseTest.sTestDescription = "Valid Rate Currency values";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43607";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43607desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43607descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43607 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(388, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(392, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(388, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(388, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(388, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43607
				DM.FnPricingAndCommitmentIWS(397, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(402, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(402, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(402, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(408, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(408, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43607========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43608
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 51)
	public void TC_PRICING_OVRD_Test_43608() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43608 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43608";
				BaseTest.sOTMTestcaseID = "Test 43608";

				BaseTest.sTestDescription = "Verify Validation for Rate Schedule";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43608";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43608 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(413, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(417, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(413, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(413, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(413, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43608
				DM.FnPricingAndCommitmentIWS(422, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(427, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(427, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(427, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(433, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(433, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43608========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43609
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 52)
	public void TC_PRICING_OVRD_Test_43609() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43609 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43609";
				BaseTest.sOTMTestcaseID = "Test 43609";

				BaseTest.sTestDescription = "Verify Override Pricing for Non – Negotiable Price Items";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43609";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43609 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(438, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(442, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(438, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(438, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(438, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43609
				DM.FnPricingAndCommitmentIWS(447, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(452, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(452, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(452, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(458, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(458, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43609========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43610
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 53)
	public void TC_PRICING_OVRD_Test_43610() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43610 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43610";
				BaseTest.sOTMTestcaseID = "Test 43610";

				BaseTest.sTestDescription = "Negotiability conditions should validate";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43610";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43610 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(463, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(467, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(463, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(463, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(463, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43610
				DM.FnPricingAndCommitmentIWS(472, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(477, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(477, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(477, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(483, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(483, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43610========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43611
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 54)
	public void TC_PRICING_OVRD_Test_43611() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43611 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43611";
				BaseTest.sOTMTestcaseID = "Test 43611";

				BaseTest.sTestDescription = "Print If Zero – valid values should be Y/N, can't be blank";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43611";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43611 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(488, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(492, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(488, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(488, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(488, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43611
				DM.FnPricingAndCommitmentIWS(497, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(502, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(502, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(502, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(508, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(508, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43611========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43624
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 55)
	public void TC_PRICING_OVRD_Test_43624() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43624 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43624";
				BaseTest.sOTMTestcaseID = "Test 43624";

				BaseTest.sTestDescription = "Do Not Aggregate – Valid values Y/N.";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43624";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43624 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(513, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(517, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(513, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(513, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(513, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43624
				DM.FnPricingAndCommitmentIWS(522, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(527, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(527, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(527, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(533, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(533, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43624========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43625
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 56)
	public void TC_PRICING_OVRD_Test_43625() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43625 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43625";
				BaseTest.sOTMTestcaseID = "Test 43625";

				BaseTest.sTestDescription = "Ignore Transaction – Valid values Y/N.";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43625";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43625 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(538, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(542, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(538, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(538, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(538, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43625
				DM.FnPricingAndCommitmentIWS(547, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(552, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(552, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(552, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(558, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(558, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43625========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43626
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 57)
	public void TC_PRICING_OVRD_Test_43626() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43626 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43626";
				BaseTest.sOTMTestcaseID = "Test 43626";

				BaseTest.sTestDescription = "Aggregation Schedule Lookup should have valid lookup values";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43626";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43626 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(563, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(567, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(563, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(563, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(563, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43626
				DM.FnPricingAndCommitmentIWS(572, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(577, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(577, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(577, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(583, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(583, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43626========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43627
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 58)
	public void TC_PRICING_OVRD_Test_43627() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43627 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43627";
				BaseTest.sOTMTestcaseID = "Test 43627";

				BaseTest.sTestDescription = "Eligibility Switch value can be true or false";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43627";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43627 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(588, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(592, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(588, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(588, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(588, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43627
				DM.FnPricingAndCommitmentIWS(597, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(602, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(602, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(602, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(608, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(608, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43627========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43628
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 59)
	public void TC_PRICING_OVRD_Test_43628() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43628 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43628";
				BaseTest.sOTMTestcaseID = "Test 43628";

				BaseTest.sTestDescription = "Tiered Flag of Price Component should have valid value";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43628";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43628 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(613, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(617, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(613, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(613, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(613, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43628
				DM.FnPricingAndCommitmentIWS(622, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(627, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(627, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(627, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(633, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(633, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43628========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43629
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 60)
	public void TC_PRICING_OVRD_Test_43629() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43629 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43629";
				BaseTest.sOTMTestcaseID = "Test 43629";

				BaseTest.sTestDescription = "Verify seasonal pricing by adding start date as =TODAY()+30 i.e. month later and original pricing end date will be =TODAY()+30+1";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43629";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43629 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(638, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(642, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(638, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(638, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(638, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43629
				DM.FnPricingAndCommitmentIWS(647, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				////DB.FnVerifyPricingAndCommitmentDetails(226, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(652, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(652, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(652, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(658, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(658, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43629========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43630
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 61)
	public void TC_PRICING_OVRD_Test_43630() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43630 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43630";
				BaseTest.sOTMTestcaseID = "Test 43630";

				BaseTest.sTestDescription = "Verify validation for Duplicate Seasonal Pricing";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43630";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43630 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(663, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(667, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(663, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(663, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(663, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43630
				DM.FnPricingAndCommitmentIWS(672, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(672, sSheetName, sWorkbook, smodelId); ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(677, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(677, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(677, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(683, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(683, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43630========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43644
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 62)
	public void TC_PRICING_OVRD_Test_43644() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43644 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43644";
				BaseTest.sOTMTestcaseID = "Test 43644";

				BaseTest.sTestDescription = "Verify seasonal pricing start date should be before end date else system should throw validation error";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43644";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43644 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(688, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(692, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(688, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(688, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(688, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43644
				DM.FnPricingAndCommitmentIWS(697, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				//// DB.FnVerifyPricingAndCommitmentDetails(697, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(702, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(702, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(702, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(708, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(708, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43644========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43645
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 63)
	public void TC_PRICING_OVRD_Test_43645() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43645 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43645";
				BaseTest.sOTMTestcaseID = "Test 43645";

				BaseTest.sTestDescription = "Verify validation for Seasonal Pricing if we exceed Deal Validity Period mentioned in the Feature Config";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43645";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43645 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(713, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(717, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(713, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(713, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(713, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43645
				DM.FnPricingAndCommitmentIWS(722, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				//// DB.FnVerifyPricingAndCommitmentDetails(722, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(727, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(727, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(727, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(733, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(733, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43645========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43646
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 64)
	public void TC_PRICING_OVRD_Test_43646() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43646 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43646";
				BaseTest.sOTMTestcaseID = "Test 43646";

				BaseTest.sTestDescription = "verify validation for seasonal pricing endDate as =TODAY()+1850 i.e. we exceed Deal Validity Period mentioned in the Feature Config";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43646";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43646 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(738, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(742, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(738, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(738, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(738, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43646
				DM.FnPricingAndCommitmentIWS(747, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				//// DB.FnVerifyPricingAndCommitmentDetails(747, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(752, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(752, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(752, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(758, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(758, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43646========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_OVRD_Test_43647
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 65)
	public void TC_PRICING_OVRD_Test_43647() throws Exception {
		log.info("<<<======= Starting TC_PRICING_OVRD_Test_43647 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sScriptName = "TC_PRICING_OVRD_Test_43647";
				BaseTest.sOTMTestcaseID = "Test 43647";

				BaseTest.sTestDescription = "Verify Price Assignment Type while creating seasonal";

				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_OVRD_Test_43647";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_Test_43608desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_Test_43608descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_OVRD_Test_43647 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(763, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(767, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(763, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(763, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(763, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//TC_PRICING_OVRD_Test_43647
				DM.FnPricingAndCommitmentIWS(772, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				//// DB.FnVerifyPricingAndCommitmentDetails(772, sSheetName, sWorkbook, smodelId);  ////in case of ovrd not for validation checks

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(777, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(777, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(777, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(783, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(783, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_OVRD_Test_43647========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43668
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 0)
	public void TC_PRICING_UPD_Test_43668() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43668 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43668";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43668";
				BaseTest.sTestDescription = "Entity ID should belong to the Model";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43668";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43668 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1386, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1391, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1386, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1386, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1386, sSheetName, sWorkbook, smodelId);

				//TC_PRICING_UPD_Test_43668	
				DM.FnPricingAndCommitmentIWS(1395, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1395, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1400, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1400, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1400, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1406, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1406, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43668========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43669
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 2)
	public void TC_PRICING_UPD_Test_43669() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43669 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43669";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43669";
				BaseTest.sTestDescription = "Price Assignment ID which User wants to update should not be selected empty";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43669";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43669 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1411, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1416, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1411, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1411, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1411, sSheetName, sWorkbook, smodelId);

				//TC_PRICING_UPD_Test_43669	
				DM.FnPricingAndCommitmentIWS(1420, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1420, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1425, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1425, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1425, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1431, sSheetName, sWorkbook, smodelId);
				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1431, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43669========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43670
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 3)
	public void TC_PRICING_UPD_Test_43670() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43670 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43670";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43670";
				BaseTest.sTestDescription = "Verify if error should be shown if different price assignment ID given for different entity";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43670";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43670 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1436, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1441, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1436, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1436, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1436, sSheetName, sWorkbook, smodelId);

				//TC_PRICING_UPD_Test_43670	
				DM.FnPricingAndCommitmentIWS(1445, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1445, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1450, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1450, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1450, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1456, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1456, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43670========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43671
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 4)
	public void TC_PRICING_UPD_Test_43671() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43671 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43671";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43671";
				BaseTest.sTestDescription = "Verify error should be shown if invalid Price Item and Parameter combination given while updating";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43671";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43671 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1461, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1466, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1461, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1461, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1461, sSheetName, sWorkbook, smodelId);

				//TC_PRICING_UPD_Test_43671	
				DM.FnPricingAndCommitmentIWS(1470, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1470, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1475, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1475, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1475, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1481, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1481, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43671========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43672
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 5)
	public void TC_PRICING_UPD_Test_43672() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43672 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43672";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43672";
				BaseTest.sTestDescription = "Only RM level user can update Price Assignment";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43672";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43672 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1486, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1491, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1486, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1486, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1486, sSheetName, sWorkbook, smodelId);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				WF.FnUserChange("PMBK1"); //USER Change to Verify Case

				//TC_PRICING_UPD_Test_43672	
				DM.FnPricingAndCommitmentIWS(1495, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(370, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1500, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1500, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1500, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1506, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1506, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43672========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43673
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 6)
	public void TC_PRICING_UPD_Test_43673() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43673 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43673";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43673";
				BaseTest.sTestDescription = "User can not update not owned Price Assignment";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43673";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43673 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1511, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1516, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1511, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1511, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1511, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43673	
				DM.FnPricingAndCommitmentIWS(1520, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1520, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1525, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1525, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1525, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1531, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1531, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43673========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43674
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 7)
	public void TC_PRICING_UPD_Test_43674() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43674 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43674";
				BaseTest.sTestDescription = "Verify error should thrown when we pass invalid Entity Type values except PERS/ACCT";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43674";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43674 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1536, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1541, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1536, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1536, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1536, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43674	
				DM.FnPricingAndCommitmentIWS(1545, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1545, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1550, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1550, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1550, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1556, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1556, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43674========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43684
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 8)
	public void TC_PRICING_UPD_Test_43684() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43684 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43684";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43684";
				BaseTest.sTestDescription = "Verify Validation should be thrown if we did't pass entityid";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43684";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43684 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1561, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1566, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1561, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1561, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1561, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43684	
				DM.FnPricingAndCommitmentIWS(1570, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1570, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1575, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1575, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1575, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1581, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1581, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43684========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43685
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 9)
	public void TC_PRICING_UPD_Test_43685() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43685 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43685";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43685";
				BaseTest.sTestDescription = "Price Status Flag should be PRPD. Error should be shown if invalid value is given";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43685";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43685 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1586, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1591, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1586, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1586, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1586, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43685	
				DM.FnPricingAndCommitmentIWS(1595, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1595, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1600, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1600, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1600, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1606, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1606, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43685========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43686
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 10)
	public void TC_PRICING_UPD_Test_43686() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43686 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43686";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43686";
				BaseTest.sTestDescription = "Price Assignment Type can't be blank and should have valid values – PPIN, POST,RGLR, PPPR. Verify if error is shown when this flag kept blank";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43686";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43686 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1611, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1616, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1611, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1611, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1611, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43686	
				DM.FnPricingAndCommitmentIWS(1620, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1620, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1625, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1625, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1625, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1631, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1631, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43686========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43687
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 11)
	public void TC_PRICING_UPD_Test_43687() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43687 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43687";
				BaseTest.sTestDescription = "Error should be thrown if invalid Rate Currency values provided.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43687";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43687 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1636, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1641, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1636, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1636, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1636, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43687	
				DM.FnPricingAndCommitmentIWS(1645, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1645, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1650, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1650, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1650, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1656, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1656, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43687========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43688
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 12)
	public void TC_PRICING_UPD_Test_43688() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43688 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43688";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43688";
				BaseTest.sTestDescription = "Error should be shown if invalid Rate Schedule values are shown";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43688";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43688 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1661, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1666, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1661, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1661, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1661, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43688	
				DM.FnPricingAndCommitmentIWS(1670, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1670, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1675, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1675, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1675, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1681, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1681, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43688========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43689
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 13)
	public void TC_PRICING_UPD_Test_43689() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43689 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43689";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43689";
				BaseTest.sTestDescription = "Non – Negotiable Price Items can't be updated. so verify what error is shown when we try to update";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43689";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43689 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1686, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1691, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1686, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1686, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1686, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43689	
				DM.FnPricingAndCommitmentIWS(1695, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1695, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1700, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1700, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1700, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1706, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1706, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43689========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43690
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 14)
	public void TC_PRICING_UPD_Test_43690() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43690 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43690";
				BaseTest.sTestDescription = "Print If Zero – valid values should be Y/N, can't be blank. Verify what error is shown when we keep it blank";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43690";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43690 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1711, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1716, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1711, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1711, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1711, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43690	
				DM.FnPricingAndCommitmentIWS(1720, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1720, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1725, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1725, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1725, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1731, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1731, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43690========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43691
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 15)
	public void TC_PRICING_UPD_Test_43691() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43691 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43691";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43691";
				BaseTest.sTestDescription = "Do Not Aggregate, Ignore Transaction – Valid values Y/N.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43691";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43691 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1736, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1741, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1736, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1736, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1736, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43691	
				DM.FnPricingAndCommitmentIWS(1745, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1745, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1750, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1750, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1750, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1756, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1756, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43691========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43692
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 16)
	public void TC_PRICING_UPD_Test_43692() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43692 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43692";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43692";
				BaseTest.sTestDescription = "Invalid Transaction Rating Criteria";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43692";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43692 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1761, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1766, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1761, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1761, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1761, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43692	
				DM.FnPricingAndCommitmentIWS(1770, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1770, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1775, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1775, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1775, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1781, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1781, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43692========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43693
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 17)
	public void TC_PRICING_UPD_Test_43693() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43693 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43693";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43693";
				BaseTest.sTestDescription = "ignoreSw should have valid values";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43693";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43693 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1786, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1791, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1786, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1786, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1786, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43693	
				DM.FnPricingAndCommitmentIWS(1795, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1795, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1800, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1800, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1800, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1806, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1806, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43693========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43694
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 18)
	public void TC_PRICING_UPD_Test_43694() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43694 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43694";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43694";
				BaseTest.sTestDescription = "Eligibility Switch value can be true or false";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43694";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43694 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1811, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1816, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1811, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1811, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1811, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43694	
				DM.FnPricingAndCommitmentIWS(1820, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1820, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1825, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1825, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1825, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1831, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1831, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43694========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43695
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 19)
	public void TC_PRICING_UPD_Test_43695() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43695 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43695";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43695";
				BaseTest.sTestDescription = "priceCompId should be valid as per Rate Schedule.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43695";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43695 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1836, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1841, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1836, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1836, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1836, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43695	
				DM.FnPricingAndCommitmentIWS(1845, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1845, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1850, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1850, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1850, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1856, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1856, sSheetName, sWorkbook, smodelId);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43695========");
	}



	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43696
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 20)
	public void TC_PRICING_UPD_Test_43696() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43696 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43696";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43696";
				BaseTest.sTestDescription = "Verify rate update for price item which are part of pricelist but not selected for 'pricing&commitment'";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43696";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43696 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1861, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1866, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1861, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1861, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1861, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43696	
				DM.FnPricingAndCommitmentIWS(1870, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1870, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1875, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1875, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1875, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1881, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1881, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43696========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43697
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 21)
	public void TC_PRICING_UPD_Test_43697() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43697 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43697";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43697";
				BaseTest.sTestDescription = "rcMapId should be valid as per Rate Schedule.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43697";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43697 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1886, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1891, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1886, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1886, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1886, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43697	
				DM.FnPricingAndCommitmentIWS(1895, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				/////DB.FnVerifyPriceAsgnDetails(1895, sSheetName, sWorkbook, smodelId);  if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1900, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1900, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1900, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1906, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1906, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43697========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43698
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 22)
	public void TC_PRICING_UPD_Test_43698() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43698 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43698";
				BaseTest.sTestDescription = "Update Price Item with Parameter Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43698";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43698 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1911, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1916, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1911, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1911, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1911, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43698	
				DM.FnPricingAndCommitmentIWS(1920, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1920, sSheetName, sWorkbook, smodelId); //if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1925, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1925, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1925, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1931, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1931, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43698========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43699
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 23)
	public void TC_PRICING_UPD_Test_43699() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43699 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43699";
				BaseTest.sTestDescription = "Update Price Item with Threshold Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43699";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43699 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1936, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1941, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1936, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1936, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1936, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43699	
				DM.FnPricingAndCommitmentIWS(1945, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1945, sSheetName, sWorkbook, smodelId); //if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1950, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1950, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1950, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1956, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1956, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43699========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43700
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 24)
	public void TC_PRICING_UPD_Test_43700() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43700 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43700";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43700";
				BaseTest.sTestDescription = "Update Price Item with Step Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43700";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43700 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1961, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1966, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1961, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1961, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1961, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43700	
				DM.FnPricingAndCommitmentIWS(1970, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1970, sSheetName, sWorkbook, smodelId); //if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1975, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1975, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1975, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1981, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1981, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43700========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43701
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 25)
	public void TC_PRICING_UPD_Test_43701() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43701 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43701";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43701";
				BaseTest.sTestDescription = "Update Price Item with Step Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43701";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43701 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1986, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1991, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1986, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1986, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1986, sSheetName, sWorkbook, smodelId);


				//TC_PRICING_UPD_Test_43701	
				DM.FnPricingAndCommitmentIWS(1995, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1995, sSheetName, sWorkbook, smodelId); //if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(2000, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(2000, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(2000, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(2006, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(2006, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43701========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_UPD_Test_43702
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 26)
	public void TC_PRICING_UPD_Test_43702() throws Exception {
		log.info("<<<======= Starting TC_PRICING_UPD_Test_43702 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43702";
				BaseTest.sScriptName = "TC_PRICING_UPD_Test_43702";
				BaseTest.sTestDescription = "Send deal for approval at PM Level & try to update Pricing through RM user error should be thrown";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_UPD_Test_43702";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personIdentifierType\":\"COREG\",\"personIdentifier\":\"Reg_AGREED_PRIC_N_COMT_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"NODEFCOMT\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"ABC_TEST_001desc\",\"dealVersionDescription\":\"ABC_TEST_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_UPD_Test_43702 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(2011, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(2016, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(2011, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(2011, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(2011, sSheetName, sWorkbook, smodelId);


				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_UPD_Test_43702	
				DM.FnPricingAndCommitmentIWS(2020, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(2025, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(2025, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(2025, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(2031, sSheetName, sWorkbook, smodelId);
				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(2031, sSheetName, sWorkbook, smodelId);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_UPD_Test_43702========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43649
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 36)
	public void TC_PRICING_DEL_Test_43649() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43649 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43649";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43649";
				BaseTest.sTestDescription = "Delete Regular FLAT pricing and check pricing details";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43649";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43649 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(788, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(792, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(788, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(788, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(788, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(797, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(797, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(797, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(802, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(802, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(802, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(808, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(808, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43649 	
				DM.FnPricingAndCommitmentIWS(812, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(816, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(816, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(821, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(821, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(821, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(827, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(827, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43649========");
	}









	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43650
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 37)
	public void TC_PRICING_DEL_Test_43650() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43650 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43650";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43650";
				BaseTest.sTestDescription = "Delete STEP pricing and check pricing details";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43650";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43650 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(832, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(836, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(832, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(832, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(832, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(841, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(841, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(841, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(846, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(846, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(846, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(852, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(852, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43650 	
				DM.FnPricingAndCommitmentIWS(856, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(860, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(860, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(865, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(865, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(865, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(871, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(871, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43650========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43651
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 38)
	public void TC_PRICING_DEL_Test_43651() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43651 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43651";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43651";
				BaseTest.sTestDescription = "Delete Threshold pricing and check pricing details";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43651";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43651 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(876, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(880, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(876, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(876, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(876, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(885, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(885, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(885, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(890, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(890, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(890, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(896, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(896, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43651 	
				DM.FnPricingAndCommitmentIWS(900, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(904, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(904, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(909, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(909, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(909, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(915, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(915, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43651========");
	}








	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43652
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 39)
	public void TC_PRICING_DEL_Test_43652() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43652 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43652";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43652";
				BaseTest.sTestDescription = "for deleting pricing Entity ID should belong to the Model & deal";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43652";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43652 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(920, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(924, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(920, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(920, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(920, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(929, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(929, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(929, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(934, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(934, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(934, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(940, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(940, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43652 	
				DM.FnPricingAndCommitmentIWS(944, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(948, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(948, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(953, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(953, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(953, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(959, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(959, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43652========");
	}


	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43653
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 40)
	public void TC_PRICING_DEL_Test_43653() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43653 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43653";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43653";
				BaseTest.sTestDescription = "Price Assignment ID which User wants to delete should be selected and belong to the entity";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43653";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43653 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(964, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(968, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(964, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(964, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(964, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(973, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(973, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(973, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(978, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(978, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(978, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(984, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(984, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43653 	
				DM.FnPricingAndCommitmentIWS(988, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(992, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(992, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(997, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(997, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(997, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1003, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1003, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43653========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43654
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 41)
	public void TC_PRICING_DEL_Test_43654() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43654 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43654";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43654";
				BaseTest.sTestDescription = "Model Status should be valid for deleting its Price Assignment";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43654";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43654 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1008, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1012, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1008, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1008, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1008, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(1017, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1017, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1017, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1022, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1022, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1022, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1028, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1028, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				//TC_PRICING_DEL_Test_43654 	
				DM.FnPricingAndCommitmentIWS(1032, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1036, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1036, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1041, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1041, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1041, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1047, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1047, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43654========");
	}





	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43655
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 42)
	public void TC_PRICING_DEL_Test_43655() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43655 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43655";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43655";
				BaseTest.sTestDescription = "Only RM level user can delete proposed Price Assignment and PM User Recommended Price Assignment.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43655";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43655 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1052, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1056, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1052, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1052, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1052, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");


				//TC_PRICING_OVRD_All_001	
				DM.FnPricingAndCommitmentIWS(1061, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1061, sSheetName, sWorkbook, smodelId);

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1061, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1066, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1066, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1066, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1072, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1072, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_DEL_Test_43655 	
				DM.FnPricingAndCommitmentIWS(1076, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1080, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1080, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1085, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1085, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1085, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1091, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1091, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43655========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_DEL_Test_43656
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 43)
	public void TC_PRICING_DEL_Test_43656() throws Exception {
		log.info("<<<======= Starting TC_PRICING_DEL_Test_43656 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43656";
				BaseTest.sScriptName = "TC_PRICING_DEL_Test_43656";
				BaseTest.sTestDescription = "User cannot delete Price List Price Assignment.";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_DEL_Test_43656";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_DEL_Test_43656 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1096, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1100, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1096, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1096, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1096, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				System.out.println("=====================================================================================");



				//TC_PRICING_DEL_Test_43656 	
				DM.FnPricingAndCommitmentIWS(1105, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1109, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1109, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1114, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1114, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1114, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1120, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1120, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_DEL_Test_43656========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43658
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 27)
	public void TC_PRICING_RECM_Test_43658() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43658 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43658";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43658";
				BaseTest.sTestDescription = "Verify price item Negotiability ONLY WITHIN RANGE SWITCH";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43658";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43658 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1125, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1129, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1125, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1125, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1125, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43658 	
				DM.FnPricingAndCommitmentIWS(1134, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1138, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1138, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1143, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1143, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1143, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1149, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1149, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43658========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43659
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 28)
	public void TC_PRICING_RECM_Test_43659() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43659 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43659";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43659";
				BaseTest.sTestDescription = "Verify User Can not Recommend Non-Negotiable Price Item";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43659";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43659 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1154, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1158, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1154, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1154, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1154, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43659 	
				DM.FnPricingAndCommitmentIWS(1163, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1167, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1167, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1172, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1172, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1172, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1178, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1178, sSheetName, sWorkbook, smodelId);



			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43659========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43660
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 29)
	public void TC_PRICING_RECM_Test_43660() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43660 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43660";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43660";
				BaseTest.sTestDescription = "Cannot Change Start Date and End Date while recommending";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43660";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1183, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1187, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1183, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1183, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1183, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43660 	
				DM.FnPricingAndCommitmentIWS(1192, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1196, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1196, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1201, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1201, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1201, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1207, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1207, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43660========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43661
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 30)
	public void TC_PRICING_RECM_Test_43661() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43661 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43661";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43661";
				BaseTest.sTestDescription = "Price Status Flag should be RECM";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43661";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43661 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1212, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1216, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1212, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1212, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1212, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43661 	
				DM.FnPricingAndCommitmentIWS(1221, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1225, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1225, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1230, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1230, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1230, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1236, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1236, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43661========");
	}







	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43662
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 31)
	public void TC_PRICING_RECM_Test_43662() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43662 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43662";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43662";
				BaseTest.sTestDescription = "Entity ID should belong to the Model while recommend";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43662";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43662 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1241, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1245, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1241, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1241, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1241, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43662 	
				DM.FnPricingAndCommitmentIWS(1250, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1254, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1254, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1259, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1259, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1259, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1265, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1265, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43662========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43663
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 32)
	public void TC_PRICING_RECM_Test_43663() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43663 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43663";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43663";
				BaseTest.sTestDescription = "Price Assignment ID on which User wants to Recommend should be selected and belong to the entity";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43663";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43663 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1270, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1274, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1270, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1270, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1270, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43663 	
				DM.FnPricingAndCommitmentIWS(1279, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1283, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1283, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1288, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1288, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1288, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1294, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1294, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43663========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43664
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 33)
	public void TC_PRICING_RECM_Test_43664() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43664 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43664";
				BaseTest.sTestDescription = "recommend Price Item with Flat Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43664";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43664 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1299, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1303, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1299, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1299, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1299, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43664 	
				DM.FnPricingAndCommitmentIWS(1308, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1312, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1312, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1317, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1317, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1317, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1323, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1323, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43664========");
	}




	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43665
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 34)
	public void TC_PRICING_RECM_Test_43665() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43665 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43665";
				BaseTest.sTestDescription = "Recommend Price Item with Step Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43665";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43665 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1328, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1332, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1328, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1328, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1328, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43665 	
				DM.FnPricingAndCommitmentIWS(1337, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1341, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1341, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1346, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1346, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1346, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1352, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1352, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43665========");
	}






	/***********************************************************************************************************************************************************
	 * Written by					:   	Rohit Thik
	 * Script Name					:  		TC_PRICING_RECM_Test_43666
	 * Script Description			: 		
	 * Manual Test Scenario covered	: 		Yes
	 * Date Created					:  		11-Nov-2022
	 * Date Modified				: 		
	 * Modified by					: 
	 * Environment scripted in		: 		
	 * ObjectRepository 			: 		 
	 * Datasheet name				: 		Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx
	 ************************************************************************************************************************************************************
	 ************************************************************************************************************************************************************
	 * Variable Definition
	 ***********************************************************************************************************************************************************/
	@Test(priority = 35)
	public void TC_PRICING_RECM_Test_43666() throws Exception {
		log.info("<<<======= Starting TC_PRICING_RECM_Test_43666 ========>>>");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Pricing And Commitment IWS";
				BaseTest.sScenario = "";
				BaseTest.sOTMTestcaseID = "Test 43666";
				BaseTest.sScriptName = "TC_PRICING_RECM_Test_43666";
				BaseTest.sTestDescription = "Recommend Price Item with Threshold Pricing";


				// Json Data to be used:
				
				//System.out.println(sJsonFileURLForPriceListAssignment);

				sStartDate = getStartDate();
				//System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "TC_PRICING_RECM_Test_43666";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;

				sDealId = smodelId = sErrorMessageIWS = "";

				DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
				dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");


				//################ Deal Creation IWS ####################//
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"7829433453\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"TC_PRICING_OVRD_All_001desc\",\"dealVersionDescription\":\"TC_PRICING_OVRD_All_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

				//System.out.println("TC_PRICING_RECM_Test_43666 Request ->"+sDealCreation);
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

				sDealId = DealDetails.get("sDealId");
				smodelId = DealDetails.get("sModelId");
				sDealIdentifier = DealDetails.get("sDealIdentifier");

				//This function to verify Deal Information Details from DB Table
				DB.FnVerifyDealCreationInfoIWS(1357, sSheetName, sWorkbook, sDealId);


				//################ Deal PriceList Assignment IWS ####################//
				//To Perform Operation On Deal i.e. Add , Update , Delete pricelist
				dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(1361, sSheetName, sWorkbook, sDealId, smodelId);

				//################ Deal PSEL READ IWS ####################//
				String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sDealPSELREAD);
				DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


				//################ Deal PSEL SELECT IWS ####################//
				String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
				//System.out.println("Request ->"+sDealPSELSELECT);
				DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);



				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1357, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1357, sSheetName, sWorkbook, smodelId);

				DB.FnVerifyPriceAsgnCount(1357, sSheetName, sWorkbook, smodelId);

				//################ Deal Simulation IWS ####################//
				String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
				//System.out.println("Request ->"+sSimulateDeal);
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


				//################ Send Deal For Approval IWS ####################//
				String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
				DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


				System.out.println("=====================================================================================");

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				//TC_PRICING_RECM_Test_43666 	
				DM.FnPricingAndCommitmentIWS(1366, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

				//################ Deal Simulation IWS ####################//
				DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				//System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

				//function to verify Deal Proposed SQI & Pricing Details from DB Table 
				DB.FnVerifyPricingAndCommitmentDetails(1370, sSheetName, sWorkbook, smodelId);

				System.out.println("=====================================================================================");

				//Function To verify CI_PRICEASGN Table Details for Respective Deal Price Item
				DB.FnVerifyPriceAsgnDetails(1370, sSheetName, sWorkbook, smodelId); //// if price assignment done , not in case of validation error verification

				//Function To verify CI_PRICEASGN Table Count for Respective Deal
				DB.FnVerifyPriceAsgnCount(1375, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_MODEL_SQI Table Count for Respective Deal
				DB.FnVerifySqiCount(1375, sSheetName, sWorkbook, smodelId);

				//Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
				DB.FnVerifySimulationCount(1375, sSheetName, sWorkbook, smodelId);

				//This function to verify Deal Simulation Summary Details from DB Table
				DB.Deal_Simulation_Summary(1381, sSheetName, sWorkbook, smodelId);


				//This function to verify Deal Financial Summary Details from DB Table
				DB.Deal_Financial_Summary(1381, sSheetName, sWorkbook, smodelId);



			}

		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_PRICING_RECM_Test_43666========");
	}







}