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

public class priceList_assignment_iws extends BaseTest {

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

	public String getStartDate() throws Exception {
		sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
		System.out.println("Start Date = " + sStartDate);
		return sStartDate;
	}

	public String getCurrentDate() throws Exception {
		sDateName = CommonFunctions.FnGetUniqueId();
		return sDateName;
	}


	@Test(priority = 0)
	public void TC_Deal_ADD_PROS_27() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_27========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_27";
				BaseTest.sOTMTestcaseID = "42792";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);


			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_27========");
	}

	@Test(priority = 1)
	public void TC_Deal_ADD_PROS_28() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_28========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_28";
				BaseTest.sOTMTestcaseID = "42793";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":3,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_28========");
	}

	@Test(priority = 2)
	public void TC_Deal_ADD_PROS_29() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_29========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_29";
				BaseTest.sOTMTestcaseID = "42794";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":0,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":-1,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_29========");
	}

	@Test(priority = 3)
	public void TC_Deal_ADD_PROS_30() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_30========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_30";
				BaseTest.sOTMTestcaseID = "42795";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_30========");
	}

	@Test(priority = 4)
	public void TC_Deal_ADD_PROS_32() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_32========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_32";
				BaseTest.sOTMTestcaseID = "42796";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				// To Change user for sending new request
				WF.FnUserChange("PMBK1");

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListFragmentSwitch\":\"Y\",\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":2,\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-28\",\"priceListFragmentSwitch\":\"Y\",\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_32========");
	}

	@Test(priority = 5)
	public void TC_Deal_ADD_PROS_33() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_33========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_33";
				BaseTest.sOTMTestcaseID = "42797";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":2,\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_33========");
	}

	@Test(priority = 6)
	public void TC_Deal_ADD_PROS_34() throws Exception {
		log.info("=======Starting TC_Deal_ADD_PROS_34========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_ADD_PROS_34";
				BaseTest.sOTMTestcaseID = "42798";

				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"endDate\":\"2025-03-28\",\"priceListFragmentSwitch\":\"\",\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":2,\"endDate\":\"2025-03-28\",\"priceListFragmentSwitch\":\"\",\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_ADD_PROS_34========");
	}

	@Test(priority = 7)
	public void TC_Deal_READ_PROS_01() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_01========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_01";
				BaseTest.sOTMTestcaseID = "42647";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_01\\TC_Deal_READ_PROS_PA_01_PLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"priceListAssignmentId\":'" + PrizelistAssignmentID + "'}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_01========");
	}

	@Test(priority = 8)
	public void TC_Deal_READ_PROS_02() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_02========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_02";
				BaseTest.sOTMTestcaseID = "42648";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_02\\TC_Deal_READ_PROS_PA_02_EntityDetails.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_02========");
	}

	@Test(priority = 9)
	public void TC_Deal_READ_PROS_03() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_03========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_03";
				BaseTest.sOTMTestcaseID = "42649";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_03\\TC_Deal_READ_PROS_PA_03_Deal_ID.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_03========");
	}

	@Test(priority = 10)
	public void TC_Deal_READ_PROS_04() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_04========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_04";
				BaseTest.sOTMTestcaseID = "42664";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_04\\TC_Deal_READ_PROS_PA_04_Model_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				String sModelId = DM.FnGetModelIdFromDeal();
				System.out.println("Model ID from Created Deal Is-> " + sModelId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"modelId\":'" + sModelId + "',\"actionFlag\":\"READ\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_04========");
	}

	@Test(priority = 11)
	public void TC_Deal_READ_PROS_05() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_05========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_05";
				BaseTest.sOTMTestcaseID = "42684";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_05\\TC_Deal_READ_PROS_PA_05_Ignored_Deal_ID_And_Identifier.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);
				String sModelId = DM.FnGetModelIdFromDeal();
				System.out.println("Model ID from Created Deal Is-> " + sModelId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"modelId\":'" + sModelId + "',\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"READ\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_05========");
	}

	@Test(priority = 12)
	public void TC_Deal_READ_PROS_07() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_07========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_07";
				BaseTest.sOTMTestcaseID = "42685";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_07\\TC_Deal_READ_PROS_PA_07_Ignored_Deal_Identifier.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				String sModelId = DM.FnGetModelIdFromDeal();
				System.out.println("Model ID from Created Deal Is-> " + sModelId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"READ\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_07========");
	}

	@Test(priority = 13)
	public void TC_Deal_READ_PROS_12() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_12========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_12";
				BaseTest.sOTMTestcaseID = "42686";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_12\\TC_Deal_READ_PROS_PA_12_Blank_Action_Flag_To_READ.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"\",\"pricelistassignmentlist\":[{\"priceListAssignmentId\":'" + PrizelistAssignmentID + "',\"priceListId\":\"5417730766\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_12========");
	}

	@Test(priority = 14)
	public void TC_Deal_READ_PROS_13() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_13========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_13";
				BaseTest.sOTMTestcaseID = "42687";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_13\\TC_Deal_READ_PROS_PA_13_EntityID.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "'}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_13========");
	}

	@Test(priority = 15)
	public void TC_Deal_READ_PROS_14() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_14========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_14";
				BaseTest.sOTMTestcaseID = "42688";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_14\\TC_Deal_READ_PROS_PA_14_Ex_CUST_Entity_type.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'" +
					sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'" +
					sPersonIdNumber_2 +
					"',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityType\":\"PERS\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_14========");
	}

	@Test(priority = 16)
	public void TC_Deal_READ_PROS_15() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_15========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_15";
				BaseTest.sOTMTestcaseID = "42783";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_15\\TC_Deal_READ_PROS_PA_15_Ex_ACCT_Entity_type.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"accountId\":'" + sAccountId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"EACCT\",\"contractedDealFlag\":false,\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"ACCT\",\"dealDescription\":\"DealDescription\",\"dealVersionDescription\":\"Dealversiondescription\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":true,\"priceSelectionDate\":\"2022-03-15\",\"usagePeriod\":\"12\",\"reviewFrequency\":\"12\",\"includeHierarchyFlag\":\"false\",\"temaplateFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"Adhocterms&Condition-C1\"},{\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"Adhocterms&Condition-C2\"}]}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sAccountId + "',\"entityType\":\"ACCT\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityType\":\"ACCT\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_15========");
	}

	@Test(priority = 17)
	public void TC_Deal_READ_PROS_16() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_16========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_16";
				BaseTest.sOTMTestcaseID = "42784";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_16\\TC_Deal_READ_PROS_16.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + PrizelistAssignmentID + "'}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_16========");
	}

	@Test(priority = 18)
	public void TC_Deal_READ_PROS_17() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_17========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_17";
				BaseTest.sOTMTestcaseID = "42785";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_17\\TC_Deal_READ_PROS_17.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"priceListId\":\"5417730766\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_17========");
	}

	@Test(priority = 19)
	public void TC_Deal_READ_PROS_18() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_18========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_18";
				BaseTest.sOTMTestcaseID = "42786";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_18\\TC_Deal_READ_PROS_18.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"startDate\":'" + sStartDate + "',\"priority\":2,\"priceListId\":\"5417730766\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_18========");
	}

	@Test(priority = 20)
	public void TC_Deal_READ_PROS_19() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_19========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_19";
				BaseTest.sOTMTestcaseID = "42787";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_19\\TC_Deal_READ_PROS_19.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"priority\":2,\"priceListId\":\"5417730766\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_19========");
	}

	@Test(priority = 21)
	public void TC_Deal_READ_PROS_20() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_20========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_20";
				BaseTest.sOTMTestcaseID = "42788";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_20\\TC_Deal_READ_PROS_20.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				String sEndDate = CF.FnGetPastDateInSpcificFormat("yyyy-MM-dd", -1);
				System.out.println("Deal End date used Is-> " + sEndDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":'" + sEndDate + "',\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_20========");
	}

	@Test(priority = 22)
	public void TC_Deal_READ_PROS_21() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_21========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_21";
				BaseTest.sOTMTestcaseID = "42789";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_21\\TC_Deal_READ_PROS_21.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				String sEndDate = CF.FnGetDateOfMonthInSpcificFormat("yyyy-MM-dd", 1);
				System.out.println("Deal End date used Is-> " + sEndDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"\",\"priority\":1,\"startDate\":'" + sStartDate + "',\"endDate\":'" + sEndDate + "',\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_21========");
	}


	@Test(priority = 23)
	public void TC_Deal_READ_PROS_23() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_23========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_23";
				BaseTest.sOTMTestcaseID = "42791";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_23\\TC_Deal_READ_PROS_PA_23_Identify_priority.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"pricelistassignmentlist\":[{\"entityType\":\"PERS\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_23========");
	}


	@Test(priority = 24)
	public void TC_Deal_READ_PROS_24() throws Exception {
		log.info("=======Starting TC_Deal_READ_PROS_17========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_READ_PROS_24";
				BaseTest.sOTMTestcaseID = "42790";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_READ_PROS_PA_24\\TC_Deal_READ_PROS_24.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String PrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + PrizelistAssignmentID);

				/*'################ Read Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sReadPrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"actionFlag\":\"READ\",\"priceListAssignStatusFlag\":\"PRPD\"}}";
				// Read prize list assignment to deal
				String sPrizelistID = DM.FnReadPrizeListAssignmentToDeal(sReadPrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistID);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_READ_PROS_24========");
	}

	@Test(priority = 25)
	public void TC_Deal_UPD_PROS_36() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_36========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_36";
				BaseTest.sOTMTestcaseID = "42799";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_36\\TC_Deal_READ_PROS_24.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":3,\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "',\"priceListFragmentSwitch\":\"Y\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_36========");
	}

	@Test(priority = 26)
	public void TC_Deal_UPD_PROS_37() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_37========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_37";
				BaseTest.sOTMTestcaseID = "42800";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_37\\TC_Deal_UPD_PROS_PA_37_PLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":1,\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "',\"priceListFragmentSwitch\":\"N\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_37========");
	}

	@Test(priority = 27)
	public void TC_Deal_UPD_PROS_39() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_39========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_39";
				BaseTest.sOTMTestcaseID = "42801";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_39\\TC_Deal_UPD_PROS_PA_39_MandatoryPLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":5,\"priceListId\":\"5417730766\",\"priceListFragmentSwitch\":\"Y\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_39========");
	}

	@Test(priority = 28)
	public void TC_Deal_UPD_PROS_40() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_40========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_40";
				BaseTest.sOTMTestcaseID = "42802";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_40\\TC_Deal_UPD_PROS_PA_40_InvalidPLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":6,\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "',\"priceListFragmentSwitch\":\"Y\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_40========");
	}

	@Test(priority = 29)
	public void TC_Deal_UPD_PROS_41() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_41========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_41";
				BaseTest.sOTMTestcaseID = "42803";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_41\\TC_Deal_UPD_PROS_PA_41_Invalid_PL_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":9,\"priceListId\":\"541773076\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "',\"priceListFragmentSwitch\":\"Y\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_41========");
	}

	@Test(priority = 30)
	public void TC_Deal_UPD_PROS_43() throws Exception {
		log.info("=======Starting TC_Deal_UPD_PROS_43========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_UPD_PROS_43";
				BaseTest.sOTMTestcaseID = "42804";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_UPD_PROS_PA_43\\TC_Deal_UPD_PROS_PA_43_Entity_Id_invalid_or_not_the_part.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Update Price List Assignment ############################# */
				// Input data for reading prize list assignment
				String sUpdatePrizeList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":\"2612707799\",\"entityType\":\"PERS\",\"actionFlag\":\"UPD\",\"startDate\":'" + sStartDate + "',\"endDate\":\"2025-03-29\",\"priority\":2,\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "',\"priceListFragmentSwitch\":\"N\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterUpdate = DM.FnUpdatePrizeListAssignmentToDeal(sUpdatePrizeList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterUpdate);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_UPD_PROS_43========");
	}
	/*
		@Test(priority = 31)
		public void TC_Deal_DEL_PROS_45() throws Exception {
			log.info("=======Starting TC_Deal_DEL_PROS_45========");

			try {
				if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
					// Provide below Information to generate excel file
					BaseTest.sFunctionalModule = "Deal  IWS";
					BaseTest.sScenario = "Price List Assignment end to end scenario";
					BaseTest.sScriptName = "TC_Deal_DEL_PROS_45";
					BaseTest.sOTMTestcaseID = "40897";

					// Json Data to be used:
					String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_DEL_PROS_PA_45\\TC_Deal_DEL_PROS_PA_45_Ex_Customer_PL.json";
					BaseTest.sTestDescription = "Deal creation end to end scenario";
					
					sStartDate = getStartDate();
					System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
					CF.FnTestCaseStatusReport("Pass","Deal Start date used for Deal creation Is-> " + sStartDate);

					sDateName = getCurrentDate();
					String sDealIdentifier = "DEAL_REST_EX_CUST_01";
					sDealIdentifier = sDealIdentifier + "_"+ sDateName;
					CF.FnTestCaseStatusReport("Pass","Deal identifier used for Deal creation Is-> " + sDealIdentifier);
					System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

					// To Change user for sending new request
					WF.FnUserChange("RMBK1");
					
					String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
					String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

					sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
					sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

					CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
					System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);				
					CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
					System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

					'################ Deal Prospect Creation ############################# 
					// Input data for prospect creation
					String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'"+ sPersonIdNumber_2 + "'}}}}";	
					String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData,sCreateDealProspectResource,sContentTypeHeader,sAcceptTypeHeader);
					System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);				

					'################ Deal Creation ##################################### 
					// Input data for creating deal
				    String sDealCreation= "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'"+ sProspectPersonId + "'},\"dealIdentifier\":'"+ sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'"+ sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEALDESCRIPTION\",\"dealVersionDescription\":\"DEALVERSIONDESCRIPTION\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"2022-03-15\",\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\",\"templateFlag\":\"false\"},\"dealCharacteristics\":[{\"characteristicType\":\"PROA\",\"effectiveDate\":\"2022-03-11\",\"characteristicValue\":\"TRUE\"},{\"characteristicType\":\"PROS1\",\"effectiveDate\":\"2022-03-11\",\"characteristicValue\":\"N\"}],\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"prospectdescription\",\"termsAndCondition\":\"DEAL1\",\"termsAndConditionText\":\"prospectdescription123\"},{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS-Desc\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"},{\"productCode\":\"PRODUCT_CON_03\"}]}}";
					// Deal creation
					DealDetails = DM.FnCreateDeal(sDealCreation,sCreateDealResource,sContentTypeHeader,sAcceptTypeHeader);
					sDealId = DealDetails.get("sDealId");

	                System.out.println("Created Deal ID Is-> " + sDealId);

					'################ Add Price List Assignment ############################# 
					// Input data for adding pricelist to deal
					String sAddPriceList= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
					// Add prize list assignment to deal
					String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
					System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

					'################ Delete Price List Assignment ############################# 
					// Input data for Deleting prize list assignment
					String sDeletePrizeListAssignment= "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'"+ sDealId + "',\"dealIdentifier\":'"+ sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'"+ sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"DEL\",\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'"+ sPrizelistAssignmentID + "'}]}}";
					// Read prize list assignment to deal
					String sPrizelistAssignmentIDAfterDelete = DM.FnDeletePrizeListAssignmentToDeal(sDeletePrizeListAssignment,sAddPrizeListAssignmentResource,sContentTypeHeader,sAcceptTypeHeader);
					System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterDelete);

				}
			}

			catch (Exception e) {
				System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
				e.printStackTrace();
				BaseTest.eFlgFound = "false";
				CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
			}

			log.info("=======Finished TC_Deal_DEL_PROS_45========");
		}		
	*/
	@Test(priority = 32)
	public void TC_Deal_DEL_PROS_46() throws Exception {
		log.info("=======Starting TC_Deal_DEL_PROS_46========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_DEL_PROS_46";
				BaseTest.sOTMTestcaseID = "42806";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_DEL_PROS_PA_46\\TC_Deal_DEL_PROS_PA_46_PLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Delete Price List Assignment ############################# */
				// Input data for Deleting prize list assignment
				String sDeletePrizeListAssignment = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"actionFlag\":\"DEL\",\"priceListAssignmentId\":'" + sPrizelistAssignmentID + "'}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterDelete = DM.FnDeletePrizeListAssignmentToDeal(sDeletePrizeListAssignment, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterDelete);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_DEL_PROS_46========");
	}


	@Test(priority = 33)
	public void TC_Deal_DEL_PROS_47() throws Exception {
		log.info("=======Starting TC_Deal_DEL_PROS_47========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_DEL_PROS_47";
				BaseTest.sOTMTestcaseID = "42807";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_DEL_PROS_PA_47\\TC_Deal_DEL_PROS_PA_46_PLA_Id.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"}]}}";
				// Add prize list assignment to deal
				String sPrizelistAssignmentID = DM.FnAddPrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				/*'################ Delete Price List Assignment ############################# */
				// Input data for Deleting prize list assignment
				String sDeletePrizeListAssignment = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"DEL\",\"priceListId\":\"5417730766\"}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterDelete = DM.FnDeletePrizeListAssignmentToDeal(sDeletePrizeListAssignment, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterDelete);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_DEL_PROS_47========");
	}

	@Test(priority = 34)
	public void TC_Deal_DEL_PROS_49() throws Exception {
		log.info("=======Starting TC_Deal_DEL_PROS_49========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal  IWS";
				BaseTest.sScenario = "Price List Assignment end to end scenario";
				BaseTest.sScriptName = "TC_Deal_DEL_PROS_49";
				BaseTest.sOTMTestcaseID = "42808";

				// Json Data to be used:
				String sJsonFileURLForReadPriceListAssignment = "databank\\services\\deal_priceListAssignment_iws\\TC_Deal_DEL_PROS_PA_49\\TC_Deal_DEL_PROS_PA_49_MultipleDelete.json";
				System.out.println(sJsonFileURLForReadPriceListAssignment);
				BaseTest.sTestDescription = "Deal creation end to end scenario";

				sStartDate = getStartDate();
				System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
				CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

				sDateName = getCurrentDate();
				String sDealIdentifier = "DEAL_REST_EX_CUST_01";
				sDealIdentifier = sDealIdentifier + "_" + sDateName;
				CF.FnTestCaseStatusReport("Pass", "Deal identifier used for Deal creation Is-> " + sDealIdentifier);
				System.out.println("Deal identifier used for Deal creation Is-> " + sDealIdentifier);

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				String sPersonIdNumber_1 = "Rt_Reg_Prospect_Per_Cust_001";
				String sPersonIdNumber_2 = "EAI_Prospect_Per_Cust_001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass", "First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass", "Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				/*'################ Deal Prospect Creation ############################# */
				// Input data for prospect creation
				String sDealProspectCreationData = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Prospect_Per_Cust_3.1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"Prospect_Per_Cust_3.1_001@oracle.com\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1\",\"city\":\"CA\",\"state\":\"GA\",\"country\":\"USA\",\"prosPerIdentifierList\":{\"personIdNumber\":'" + sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosAccountList\":{\"customerClass\":\"DM-CORP\",\"Accountdivision\":\"IND\",\"setUpDate\":\"2020-04-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":{\"characteristicValue\":\"123213\",\"effectiveDate\":\"2022-04-06\",\"characteristicType\":\"C1_F_ANO\"},\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":'" + sPersonIdNumber_2 + "'}}}}";
				String sProspectPersonId = DM.FnCreateDealProspect(sDealProspectCreationData, sCreateDealProspectResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Created Prospect Perosn ID Is-> " + sProspectPersonId);

				/*'################ Deal Creation ##################################### */
				// Input data for creating deal
				String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prospectPersonId\":'" + sProspectPersonId + "'},\"dealIdentifier\":'" + sDealIdentifier + "',\"dealEntityType\":\"PRSP\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"division\":\"IND\",\"accessGrp\":\"***\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":'" + sStartDate + "',\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"DEAL_BK_PRSP_PSEL_API_001desc\",\"dealVersionDescription\":\"DEAL_BK_PRSP_PSEL_API_001descver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":'" + sStartDate + "',\"usagePeriod\":\"12\",\"includeHierarchyFlag\":\"true\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\"}]},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":\"false\",\"referPriceSw\":\"false\",\"includeChildHierarchy\":\"false\"}}}";
				// Deal creation
				DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
				sDealId = DealDetails.get("sDealId");

				System.out.println("Created Deal ID Is-> " + sDealId);

				/*'################ Add Price List Assignment ############################# */
				// Input data for adding pricelist to deal
				String sAddPriceList = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":1,\"priceListId\":\"5417730766\"},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"ADD\",\"priority\":2,\"priceListId\":\"4368251923\"}]}}";
				// Add prize list assignment to deal
				List < String > sPrizelistAssignmentID = DM.FnAddMultiplePrizeListAssignmentToDeal(sAddPriceList, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List Assignment ID Is-> " + sPrizelistAssignmentID);

				String sPrizeListAssignmentID1 = sPrizelistAssignmentID.get(0);
				String sPrizeListAssignmentID2 = sPrizelistAssignmentID.get(1);

				System.out.println("Price list assignments ID 1 is " + sPrizeListAssignmentID1);
				System.out.println("Price list assignments ID 2 is " + sPrizeListAssignmentID2);

				/* '################ Delete Price List Assignment ############################# */
				// Input data for Deleting prize list assignment
				String sDeletePrizeListAssignment = "{\"C1-DealPricelistAssignmentREST\":{\"dealId\":'" + sDealId + "',\"dealIdentifier\":'" + sDealIdentifier + "',\"pricelistassignmentlist\":[{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"DEL\",\"priceListId\":\"5417730766\",\"priceListAssignmentId\":'" + sPrizeListAssignmentID1 + "'},{\"entityId\":'" + sProspectPersonId + "',\"entityType\":\"PERS\",\"actionFlag\":\"DEL\",\"priceListId\":\"4368251923\",\"priceListAssignmentId\":'" + sPrizeListAssignmentID2 + "'}]}}";
				// Read prize list assignment to deal
				String sPrizelistAssignmentIDAfterDelete = DM.FnDeletePrizeListAssignmentToDeal(sDeletePrizeListAssignment, sAddPrizeListAssignmentResource, sContentTypeHeader, sAcceptTypeHeader);
				System.out.println("Price List ID Is-> " + sPrizelistAssignmentIDAfterDelete);

			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_Deal_DEL_PROS_49========");
	}
}