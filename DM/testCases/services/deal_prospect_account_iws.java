package test.java.ormbframework.testCases.services;

import org.testng.annotations.Test;
import test.java.ormbframework.BaseTest;
import test.java.ormbframework.pageEvents.DealManagementPageEvents;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

public class deal_prospect_account_iws extends BaseTest
{
	CommonFunctions CF		= new CommonFunctions();
	ApplicationFunctions AF	= new ApplicationFunctions();
	DataBaseFunctions DB 	= new DataBaseFunctions();
	WebServiceFunctions WF = new WebServiceFunctions();
	DealManagementPageEvents DM = new DealManagementPageEvents(getDriver());

	
	public static String sCreateDealProspectAccountResource = "/rest/ouaf/api/iws/C1-ProspectAccountREST/prospectAccount";
	public static String sContentTypeHeader = "application/json";
	public static String sAcceptTypeHeader = "application/json";
	public static String sStatusCode, sValue;
	public static int iSuccessStatusCode = 200;
	public static int iErrorStatusCode = 400;
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_01_42869
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_01_42869 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=0,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_01_42869() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_01_42869========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_01_42869";
				BaseTest.sOTMTestcaseID = "42869";
				
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_01_42869";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_2_PRSP_01.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create Prospect account for Existing Prospect and Verify Prospect Account should not get create without basic details like 'PROSPECT PERSON ID','CUSTOMER CLASS','SET UP DATE','DIVISION','INVOICE CURRENCY','ACCESS GROUP' if any field skipped system should";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_01_42869========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_02_42870
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_02_42870 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=1,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_02_42870() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_02_42870========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_02_42870";
				BaseTest.sOTMTestcaseID = "42870";
				
				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_2_PRSP_02.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for prospect person  with valid basic details - main, Prospect account characterstics(multiple), prospect account identifier(multiple)";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				try {
					String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
					System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_02_42870========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_03_42871
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_03_42871 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=2,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_03_42871() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_03_42871========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_03_42871";
				BaseTest.sOTMTestcaseID = "42871";
				
				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_2_PRSP_03.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for prospect person who's customer segment and Customer Tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_03_42871========");
	}
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_04_42872
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_04_42872 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=3,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_04_42872() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_04_42872========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_04_42872";
				BaseTest.sOTMTestcaseID = "42872";
				
				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_04.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for prospect person who's customer segment and Customer Tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_04_42872========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_05_42873
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_05_42873 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=4,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_05_42873() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_05_42873========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_05_42873";
				BaseTest.sOTMTestcaseID = "42873";
				
				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_05.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for prospect person who's customer tier mentioned but custmer segment blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_05_42873========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_06_42874
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_06_42874 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=5,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_06_42874() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_06_42874========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_06_42874";
				BaseTest.sOTMTestcaseID = "42874";
				
				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_06.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create prospect account  with basic details - main details, prospect account identifier";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_06_42874========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_09_42875
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_09_42875 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=6,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_09_42875() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_09_42875========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_09_42875";
				BaseTest.sOTMTestcaseID = "42875";
				
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_09_42875";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_09.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while creating Prospect account if we keep prospectid as 'mainEntityId' and 'mainEntityType' as 'EPER' instead 'PRSP' then system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
	
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_09_42875========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_10_42876
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_10_42876 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=7,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_10_42876() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_10_42876========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_10_42876";
				BaseTest.sOTMTestcaseID = "42876";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_10_42876";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_10.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'CUSTOMER CLASS' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_10_42876========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_11_42884
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_11_42884 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=8,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_11_42884() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_11_42884========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_11_42884";
				BaseTest.sOTMTestcaseID = "42884";
				
				String sWorkbook,sSheetName;

				// Json Data to be used:
				sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				sSheetName = "TC_ADD_11_42884";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_11.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'DIVISION' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify CIS Division for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_11_42884========");
	}			
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_12_42885
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_12_42885 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=9,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_12_42885() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_12_42885========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_12_42885";
				BaseTest.sOTMTestcaseID = "42885";
			
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_12_42885";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_12.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'INVOICE CURRENCY' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_12_42885========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_13_42886
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_13_42886 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=10,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_13_42886() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_13_42886========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_13_42886";
				BaseTest.sOTMTestcaseID = "42886";
			
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_13_42886";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_13.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'ACCESS GROUP' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_13_42886========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_14_42887
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_14_42887 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=11,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_14_42887() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_14_42887========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_14_42887";
				BaseTest.sOTMTestcaseID = "42887";
	
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_14_42887";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_14.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'accountIdentifierType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_14_42887========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_15_42888
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_15_42888 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=12,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_15_42888() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_15_42888========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_15_42888";
				BaseTest.sOTMTestcaseID = "42888";
	
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_15_42888";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_15.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can set 'isPrimaryId' as true in identifier for only one record while create else system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_15_42888========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_16_42889
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_16_42889 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=13,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_16_42889() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_16_42889========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_16_42889";
				BaseTest.sOTMTestcaseID = "42889";
	
				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_16_42889";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_16.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_16_42889========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_17_42890
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_17_42890 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=14,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_17_42890() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_17_42890========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_17_42890";
				BaseTest.sOTMTestcaseID = "42890";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_17_42890";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_17.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Prospect account  with invalid 'characteristicValue' of 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_17_42890========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_19_42891
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_19_42891 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=15,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_19_42891() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_19_42891========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_19_42891";
				BaseTest.sOTMTestcaseID = "42891";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_19_42891";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_19.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create prospect account with other division's billCycle i.e like '930' division and IND division Prospect Person";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_19_42891========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_20_42892
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_20_42892 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=16,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_20_42892() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_20_42892========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_20_42892";
				BaseTest.sOTMTestcaseID = "42892";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_20_42892";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_20.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify user should not provide duplicate account identifier in request system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_20_42892========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_21_42893
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_21_42893 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=17,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_21_42893() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_21_42893========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_21_42893";
				BaseTest.sOTMTestcaseID = "42893";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_21_42893";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_21.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify user should not provide duplicate characteristic in request system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_21_42893========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_22_42894
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_22_42894 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=18,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_22_42894() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_22_42894========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_22_42894";
				BaseTest.sOTMTestcaseID = "42894";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_22_42894";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_22.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation for invalid mainEntityId";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_22_42894========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_23_42895
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_23_42895 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=19,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_23_42895() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_23_42895========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_23_42895";
				BaseTest.sOTMTestcaseID = "42895";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_23_42895";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_23.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can not add prospect account for inActive prospect person";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_23_42895========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_24_42896
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_24_42896 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=20,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_24_42896() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_24_42896========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_24_42896";
				BaseTest.sOTMTestcaseID = "42896";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_24_42896";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_24.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation if we haven't provide 'mainEntityType'";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_24_42896========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_25_42897
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_25_42897 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=21,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_25_42897() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_25_42897========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_25_42897";
				BaseTest.sOTMTestcaseID = "42897";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_25_42897";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_25.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation if we haven't provide 'mainEntityId'";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_25_42897========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_26_42898
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_26_42898 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=22,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_26_42898() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_26_42898========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_26_42898";
				BaseTest.sOTMTestcaseID = "42898";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_26_42898";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_26.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify if we set prospect account status as InActive in API Request then it should get reflect as Inactive on DB as well";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_26_42898========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_PRSP_27_42899
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_PRSP_27_42899 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=23,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_PRSP_27_42899() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_PRSP_27_42899========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_PRSP_27_42899";
				BaseTest.sOTMTestcaseID = "42899";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_27_42899";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_PRSP_27.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify if we havent provide status then it should get set as ACTIVE by default i.e ‘BO_STATUS_CD’ should be ACTIVE";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify BO Status for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);
			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_PRSP_27_42899========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_01_42900
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_01_42900 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=24,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_01_42900() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_01_42900========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_01_42900";
				BaseTest.sOTMTestcaseID = "42900";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_01_42900";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_01.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify 'prospectAccountId' should be mandatory while updating prospect account through api";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_01_42900========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_02_42901
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_02_42901 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=25,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_02_42901() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_02_42901========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_02_42901";
				BaseTest.sOTMTestcaseID = "42901";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_02_42901";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_02.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Invalid 'prospectAccountId' while updating prospect account";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_02_42901========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_03_42902
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_03_42902 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=26,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_03_42902() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_03_42902========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_03_42902";
				BaseTest.sOTMTestcaseID = "42902";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_03_42902";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_03.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect Account for Existing Prospect system should not update account without basic details like ,'SET UP DATE' if the field skipped";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_03_42902========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_04_42903
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_04_42903 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=27,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_04_42903() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_04_42903========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_04_42903";
				BaseTest.sOTMTestcaseID = "42903";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_04_42903";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_04.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while updating Prospect Account if we given cross binding as input then system should throw error i.e for prospect P1 we gave Prospect P2's";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_04_42903========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_05_42904
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_05_42904 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=28,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_05_42904() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_05_42904========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_05_42904";
				BaseTest.sOTMTestcaseID = "42904";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_05_42904";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_05.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account if we keep prospectid as 'mainEntityId' and 'mainEntityType' as 'EPER' instead 'PRSP' then system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_05_42904========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_06_42905
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_06_42905 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=29,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_06_42905() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_06_42905========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_06_42905";
				BaseTest.sOTMTestcaseID = "42905";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_06_42905";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_06.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'CUSTOMER CLASS' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_06_42905========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_07_42906
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_07_42906 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=30,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_07_42906() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_07_42906========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_07_42906";
				BaseTest.sOTMTestcaseID = "42906";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_07_42906";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_07.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'DIVISION' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify CIS Division for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_07_42906========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_08_42907
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_08_42907 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=31,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_08_42907() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_08_42907========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_08_42907";
				BaseTest.sOTMTestcaseID = "42907";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_08_42907";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_08.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'INVOICE CURRENCY' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_08_42907========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_09_42908
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_09_42908 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=32,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_09_42908() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_09_42908========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_09_42908";
				BaseTest.sOTMTestcaseID = "42908";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_09_42908";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_09.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'ACCESS GROUP' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_09_42908========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_10_42909
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_10_42909 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=33,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_10_42909() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_10_42909========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_10_42909";
				BaseTest.sOTMTestcaseID = "42909";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_10_42909";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_10.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'accountIdentifierType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_10_42909========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_11_42910
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_11_42910 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=34,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_11_42910() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_11_42910========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_11_42910";
				BaseTest.sOTMTestcaseID = "42910";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_11_42910";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_11.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_11_42910========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_12_42911
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_12_42911 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=35,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_12_42911() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_12_42911========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_12_42911";
				BaseTest.sOTMTestcaseID = "42911";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_12_42911";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_12.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Prospect account  with invalid 'characteristicValue' of 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_12_42911========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_13_42912
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_13_42912 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=36,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_13_42912() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_13_42912========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_13_42912";
				BaseTest.sOTMTestcaseID = "42912";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_13_42912";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_13.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can update duplicate identifier in single update";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_13_42912========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_14_42913
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_14_42913 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=37,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_14_42913() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_14_42913========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_14_42913";
				BaseTest.sOTMTestcaseID = "42913";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_14_42913";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_14.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can set 'isPrimaryId' as true in identifier for both records while updating else system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_14_42913========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_SERVICE_15_42914
	 * Script Description				: 	TC_UPD_PRSP_SERVICE_15_42914 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=38,groups = {"sanity testing"})
	public void TC_UPD_PRSP_SERVICE_15_42914() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_SERVICE_15_42914========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_SERVICE_15_42914";
				BaseTest.sOTMTestcaseID = "42914";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_15_42914";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_SERVICE_15.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Duplicate characteristic in case of update";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Updated Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_SERVICE_15_42914========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_01_42915
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_01_42915 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=39,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_01_42915() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_01_42915========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_01_42915";
				BaseTest.sOTMTestcaseID = "42915";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_01_42915";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_01.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create Existing Customer Prospect account for Existing Prospect and Verify Existing Customer Prospect Account should not get create without basic details like";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_01_42915========");
	}
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_02_42916
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_02_42916 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=40,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_02_42916() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_02_42916========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_02_42916";
				BaseTest.sOTMTestcaseID = "42916";

				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_02.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for Existing Customer  with valid basic details - main, Existing Customer Prospect account characterstics(multiple), Existing Customer prospect";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_02_42916========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_03_42917
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_03_42917 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=41,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_03_42917() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_03_42917========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_03_42917";
				BaseTest.sOTMTestcaseID = "42917";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_03_42917";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_03.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for Existing Customer who's customer segment and Customer Tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify IS_PRS_SW for Created Deal Prospect Account 
				DB.FnVerifyIsPRSSwitchOnDealProspectAccount(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_03_42917========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_04_43184
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_04_43184 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=42,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_04_43184() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_04_43184========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_04_43184";
				BaseTest.sOTMTestcaseID = "43184";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_04_43184";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_04.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for Existing Customer who's customer segment mentioned but customer tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify IS_PRS_SW for Created Deal Prospect Account 
				DB.FnVerifyIsPRSSwitchOnDealProspectAccount(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_04_43184========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_05_42918
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_05_42918 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=43,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_05_42918() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_05_42918========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_05_42918";
				BaseTest.sOTMTestcaseID = "42918";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_05_42918";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_05.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create account for Existing Customer who's customer tier mentioned but custmer segment blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify IS_PRS_SW for Created Deal Prospect Account 
				DB.FnVerifyIsPRSSwitchOnDealProspectAccount(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_05_42918========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_09_42919
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_09_42919 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=44,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_09_42919() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_09_42919========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_09_42919";
				BaseTest.sOTMTestcaseID = "42919";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_09_42919";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_09.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while creating Existing Customer Prospect account if we keep prospectid as 'mainEntityId' and 'mainEntityType' as 'PRSP' instead 'EPER' then system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify IS_PRS_SW for Created Deal Prospect Account 
				//DB.FnVerifyIsPRSSwitchOnDealProspectAccount(3, sSheetName, sWorkbook);
				
				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_09_42919========");
	}	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_10_42920
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_10_42920 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=45,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_10_42920() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_10_42920========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_10_42920";
				BaseTest.sOTMTestcaseID = "42920";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_10_42920";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_10.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'CUSTOMER CLASS' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_10_42920========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_11_42921
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_11_42921 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=46,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_11_42921() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_11_42921========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_11_42921";
				BaseTest.sOTMTestcaseID = "42921";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_11_42921";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_11.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'DIVISION' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify CIS Division for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_11_42921========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_12_42922
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_12_42922 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=47,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_12_42922() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_12_42922========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_12_42922";
				BaseTest.sOTMTestcaseID = "42922";

				// Json Data to be used:
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_12.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'INVOICE CURRENCY' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_12_42922========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_13_42923
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_13_42923 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=48,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_13_42923() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_13_42923========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_13_42923";
				BaseTest.sOTMTestcaseID = "42923";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_13_42923";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_13.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account with invalid 'ACCESS GROUP' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_13_42923========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_14_42924
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_14_42924 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=49,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_14_42924() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_14_42924========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_14_42924";
				BaseTest.sOTMTestcaseID = "42924";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_14_42924";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_14.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'accountIdentifierType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_14_42924========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_15_42925
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_15_42925 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=50,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_15_42925() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_15_42925========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_15_42925";
				BaseTest.sOTMTestcaseID = "42925";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_15_42925";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_15.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can set 'isPrimaryId' as true in identifier for only one record while create else system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_15_42925========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_16_42927
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_16_42927 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=51,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_16_42927() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_16_42927========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_16_42927";
				BaseTest.sOTMTestcaseID = "42927";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_16_42927";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_16.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_16_42927========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_17_42928
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_17_42928 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=52,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_17_42928() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_17_42928========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_17_42928";
				BaseTest.sOTMTestcaseID = "42928";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_17_42928";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_17.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while create Existing Customer Prospect account  with invalid 'characteristicValue' of 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);

			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_17_42928========");
	}

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_19_42929
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_19_42929 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=53,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_19_42929() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_19_42929========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_19_42929";
				BaseTest.sOTMTestcaseID = "42929";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_19_42929";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_19.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create Existing Customer prospect account with other division's billCycle i.e like '930' division and IND division Existing Customer";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_19_42929========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_20_42930
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_20_42930 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=54,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_20_42930() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_20_42930========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_20_42930";
				BaseTest.sOTMTestcaseID = "42930";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_20_42930";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_20.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify user should not provide duplicate account identifier type in request system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_20_42930========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_21_42931
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_21_42931 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=55,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_21_42931() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_21_42931========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_21_42931";
				BaseTest.sOTMTestcaseID = "42931";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_21_42931";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_21.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify user should not provide duplicate characteristic in request system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_21_42931========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_22_42932
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_22_42932 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=56,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_22_42932() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_22_42932========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_22_42932";
				BaseTest.sOTMTestcaseID = "42932";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_22_42932";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_22.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify with invalid bill cycle";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_22_42932========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_23_42933
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_23_42933 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=57,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_23_42933() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_23_42933========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_23_42933";
				BaseTest.sOTMTestcaseID = "42933";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_23_42933";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_23.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Create prospect account with other division's billCycle i.e like '930' division and IND division Prospect Person";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_23_42933========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_24_42934
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_24_42934 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=58,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_24_42934() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_24_42934========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_24_42934";
				BaseTest.sOTMTestcaseID = "42934";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_24_42934";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_24.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation for invalid 'mainEntityId'";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_24_42934========");
	}	
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_26_42935
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_26_42935 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=59,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_26_42935() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_26_42935========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_26_42935";
				BaseTest.sOTMTestcaseID = "42935";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_26_42935";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_26.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation if we haven't provide 'mainEntityType'";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_26_42935========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_27_42936
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_27_42936 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=60,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_27_42936() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_27_42936========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_27_42936";
				BaseTest.sOTMTestcaseID = "42936";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_27_42936";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_27.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Prospect Account creation if we haven't provide 'mainEntityId'";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_27_42936========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_28_42937
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_28_42937 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=61,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_28_42937() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_28_42937========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_28_42937";
				BaseTest.sOTMTestcaseID = "42937";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_28_42937";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_28.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify if we set prospect account status as InActive in API Request then it should get reflect as Inactive on DB as well";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_28_42937========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_ADD_PRSP_ACC_To_EPER_29_42938
	 * Script Description				: 	TC_ADD_PRSP_ACC_To_EPER_29_42938 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=62,groups = {"sanity testing"})
	public void TC_ADD_PRSP_ACC_To_EPER_29_42938() throws Exception
	{
		log.info("=======Starting TC_ADD_PRSP_ACC_To_EPER_29_42938========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_ADD_PRSP_ACC_To_EPER_29_42938";
				BaseTest.sOTMTestcaseID = "42938";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_ADD_EPER_29_42938";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_ADD_PRSP_ACC_To_EPER_29.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify if we havent provide status then it should get set as ACTIVE by default i.e ‘BO_STATUS_CD’ should be ACTIVE";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);
				
				// Verify BO Status for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_ADD_PRSP_ACC_To_EPER_29_42938========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_01_42939
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_01_42939 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=63,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_01_42939() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_01_42939========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_01_42939";
				BaseTest.sOTMTestcaseID = "42939";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_01_42939";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_01.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify 'prospectAccountId' should be mandatory while updating Existing Person prospect account through api";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);			
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_01_42939========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_02_42940
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_02_42940 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=64,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_02_42940() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_02_42940========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_02_42940";
				BaseTest.sOTMTestcaseID = "42940";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_02_42940";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_02.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Invalid 'prospectAccountId' while updating prospect account";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_02_42940========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_03_42941
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_03_42941 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=65,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_03_42941() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_03_42941========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_03_42941";
				BaseTest.sOTMTestcaseID = "42941";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_03_42941";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_03.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect Account system should not update account without basic details 'SET UP DATE' system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);	
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_03_42941========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_04_42942
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_04_42942 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=66,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_04_42942() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_04_42942========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_04_42942";
				BaseTest.sOTMTestcaseID = "42942";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_04_42942";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_04.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while updating Existing Person Prospect Account if we given cross binding as input then system should throw error i.e for prospect P1 we gave Prospect";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_04_42942========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_05_42943
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_05_42943 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=67,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_05_42943() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_05_42943========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_05_42943";
				BaseTest.sOTMTestcaseID = "42943";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_05_42943";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_05.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account if we keep 'mainEntityType' as 'PRSP' instead 'EPER' then system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_05_42943========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_06_42944
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_06_42944 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=68,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_06_42944() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_06_42944========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_06_42944";
				BaseTest.sOTMTestcaseID = "42944";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_06_42944";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_06.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'CUSTOMER CLASS' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_06_42944========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_07_42945
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_07_42945 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=69,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_07_42945() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_07_42945========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_07_42945";
				BaseTest.sOTMTestcaseID = "42945";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_07_42945";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_07.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'DIVISION' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify CIS Division for Created Deal Prospect Account 
				DB.FnVerifyDataOnDealProspectAccount(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_07_42945========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_08_42946
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_08_42946 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=70,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_08_42946() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_08_42946========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_08_42946";
				BaseTest.sOTMTestcaseID = "42946";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_08_42946";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_08.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'INVOICE CURRENCY' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_08_42946========");
	}		
	
	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_09_42947
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_09_42947 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=71,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_09_42947() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_09_42947========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_09_42947";
				BaseTest.sOTMTestcaseID = "42947";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_09_42947";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_09.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'ACCESS GROUP' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_09_42947========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_10_42948
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_10_42948 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=72,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_10_42948() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_10_42948========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_10_42948";
				BaseTest.sOTMTestcaseID = "42948";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_10_42948";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_10.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'accountIdentifierType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_10_42948========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_11_42949
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_11_42949 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=73,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_11_42949() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_11_42949========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_11_42949";
				BaseTest.sOTMTestcaseID = "42949";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_11_42949";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_11.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify while Updating Existing Person Prospect account  with invalid 'characteristicType' System should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_11_42949========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_12_42950
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_12_42950 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=74,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_12_42950() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_12_42950========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_12_42950";
				BaseTest.sOTMTestcaseID = "42950";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_12_42950";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_12.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify duplicate identifier in case of update";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_12_42950========");
	}		

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_12_42950
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_12_42950 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=75,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_13_42951() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_13_42951========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_13_42951";
				BaseTest.sOTMTestcaseID = "42951";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_13_42951";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_13.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify we can set 'isPrimaryId' as true in identifier for only one record while updating else system should throw error";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);	
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_13_42951========");
	}	

	/***************************************************************************************************************
	 * Written by						:  	Ajinkya Joshi
	 * Script Name						:   TC_UPD_PRSP_ACC_OF_EPER_14_42980
	 * Script Description				: 	TC_UPD_PRSP_ACC_OF_EPER_14_42980 
	 * Manual Test Scenarios covered	:   
	 * Date Created						:  	18-July-2022
	 * Date Modified					: 
	 * Modified by						:  
	 * Environment scripted in			: 	https://10.40.72.101:24510/ouaf/cis.jsp
	 * ObjectRepository 				: 	ORMBObjectLibBanking.properties 
	 ***************************************************************************************************************/
	@Test(priority=76,groups = {"sanity testing"})
	public void TC_UPD_PRSP_ACC_OF_EPER_14_42980() throws Exception
	{
		log.info("=======Starting TC_UPD_PRSP_ACC_OF_EPER_14_42980========");

		try
		{
			if (BaseTest.eFlgFound.equalsIgnoreCase("true"))
			{
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect Account IWS";
				BaseTest.sScenario = "Deal Prospect Account scenarios";
				BaseTest.sScriptName = "TC_UPD_PRSP_ACC_OF_EPER_14_42980";
				BaseTest.sOTMTestcaseID = "42980";

				// Json Data to be used:
				String sWorkbook = "./databank/services/deal_prospect_account_iws/Deal_Prospect_Account_IWS_Datasheet.xlsx";	
				String sSheetName = "TC_UPD_EPER_14_42980";
				String sJsonFileURLForProsppectAccountCreation = "databank\\services\\deal_prospect_account_iws\\json_files\\TC_UPD_PRSP_ACC_OF_EPER_14.json";
				BaseTest.sTestDescription = "Deal Prospect Account Creation - Verify Duplicate characteristic in case of update";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				/*'################ Deal Prospect Account Creation #############################*/
				// Input data for prospect creation
				String sProspectPersonId = DM.FnCreateDealProspectForAccount(sJsonFileURLForProsppectAccountCreation,sCreateDealProspectAccountResource,sContentTypeHeader,sAcceptTypeHeader);
				System.out.println("Created Prospect Account ID Is-> " + sProspectPersonId);
				CF.FnWriteCellValue(3, 1, sProspectPersonId, sSheetName, sWorkbook);

				// Verify Error Message
				CF.FnVerifyErrorMessage(3, sSheetName, sWorkbook);
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_UPD_PRSP_ACC_OF_EPER_14_42980========");
	}	
	
	
}


