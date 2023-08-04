package test.java.ormbframework.testCases.services;

import static org.testng.Assert.*;



import org.testng.annotations.Test;

import test.java.ormbframework.BaseTest;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

public class deal_prospect_person_iws extends BaseTest {
	CommonFunctions CF = new CommonFunctions();
	ApplicationFunctions AF = new ApplicationFunctions();
	WebServiceFunctions WF = new WebServiceFunctions();
	DataBaseFunctions DB = new DataBaseFunctions();

	public static String sCreateDealProspectResource = "/rest/ouaf/api/iws/C1-ProspectPersonREST/prospPerson";
	public static String sContentTypeHeader = "application/json";
	public static String sAcceptTypeHeader = "application/json";
	public static int iStartingRowNum = 4;
	public static String sStatusCode, sValue;
	public static int iSuccessStatusCode = 200;
	public static int iErrorStatusCode = 400;
	public String sDateName;

	
	public String getCurrentDate() throws Exception
	{
		sDateName = CommonFunctions.FnGetUniqueId();
		return sDateName;
	}


	@Test(priority = 0)
	public void TC_01_Add_Person_43084() throws Exception {
		log.info("=======Starting TC_01_Add_Person_43084========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with valid basic details - main, prospect person address, Prospect person characterstics(multiple), prospect person through web service";
				BaseTest.sScriptName = "TC_01_Add_Person_43084";
				BaseTest.sOTMTestcaseID = "43084";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_01.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect Creation with  with valid basic details";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_101";
				String sPersonIdNumber_2 = "01082001";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2
						+ "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_01_Add_Person_43084========");
	}

	@Test(priority = 1)
	public void TC_02_Add_Person_43085() throws Exception {
		log.info("=======Starting TC_02_Add_Person_43085========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Create of new prospect person with customer segment and Customer Tier blank through web service";
				BaseTest.sScriptName = "TC_02_Add_Person_43085";
				BaseTest.sOTMTestcaseID = "43085";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_02.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with customer segment and Customer Tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_102";
				String sPersonIdNumber_2 = "01082002";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_02,IND\",\"division\":\"IND\",\"customerSegment\":\"\",\"customerTier\":\"\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2
						+ "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		} catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_02_Add_Person_43085========");
	}

	@Test(priority = 2)
	public void TC_03_Add_Person_43086() throws Exception {
		log.info("=======Starting TC_03_Add_Person_43086========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Create of new prospect person with customer segment mentioned but customer tier blank through web service";
				BaseTest.sScriptName = "TC_03_Add_Person_43086";
				BaseTest.sOTMTestcaseID = "43086";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_03.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with customer segment mentioned but customer tier blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_103";
				String sPersonIdNumber_2 = "01082003";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_03,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2
						+ "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_03_Add_Person_43086========");
	}

	@Test(priority = 3)
	public void TC_04_Add_Person_43087() throws Exception {
		log.info("=======Starting TC_04_Add_Person_43087========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Create of new prospect person with customer tier mentioned but custmer segment blank through web service";
				BaseTest.sScriptName = "TC_04_Add_Person_43087";
				BaseTest.sOTMTestcaseID = "43087";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_04.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with customer tier mentioned but custmer segment blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();				
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_104";
				String sPersonIdNumber_2 = "01082004";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_04,IND\",\"division\":\"IND\",\"customerSegment\":\"\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2
						+ "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_04_Add_Person_43087========");
	}

	@Test(priority = 4)
	public void TC_05_Add_Person_43088() throws Exception {
		log.info("=======Starting TC_05_Add_Person_43088========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person  with basic details - main, prospect person address, prospect person identifier through web service";
				BaseTest.sScriptName = "TC_05_Add_Person_43088";
				BaseTest.sOTMTestcaseID = "43088";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_05.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with basic details - main, prospect person address, prospect person identifier";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();	
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_105";
				String sPersonIdNumber_2 = "01082005";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_05,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_05_Add_Person_43088========");
	}

	@Test(priority = 5)
	public void TC_06_Add_Person_43089() throws Exception {
		log.info("=======Starting TC_06_Add_Person_43089========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with basic details - main, prospect person address through web service";
				BaseTest.sScriptName = "TC_06_Add_Person_43089";
				BaseTest.sOTMTestcaseID = "43089";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_06.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with basic details - main, prospect person address";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_105";
				String sPersonIdNumber_2 = "01082005";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_05,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"
						+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"
						+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_06_Add_Person_43089========");
	}

	@Test(priority = 6)
	public void TC_07_Add_Person_43090() throws Exception {
		log.info("=======Starting TC_07_Add_Person_43090========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with basic details - main, prospect person address, Prospect person characterstics, prospect person releationships(multipler child person) through web service";
				BaseTest.sScriptName = "TC_07_Add_Person_43090";
				BaseTest.sOTMTestcaseID = "43090";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_07.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Deal Prospect for person Creation with basic details - main, prospect person address, Prospect person characterstics, prospect person releationships(multipler child person)";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_07";
				String sPersonIdNumber_2 = "21082017";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":+'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);	
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_07_Add_Person_43090========");
	}	

	@Test(priority = 7)
	public void TC_08_Add_Person_43091() throws Exception {
		log.info("=======Starting TC_08_Add_Person_43091========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Verify add person name data exceeded maximum character limit of the field database through web service";
				BaseTest.sScriptName = "TC_08_Add_Person_43091";
				BaseTest.sOTMTestcaseID = "43091";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_08";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "Verify add person name data exceeded maximum character limit of the field database";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_Prospect_API_MP_08";
				String sPersonIdNumber_2 = "01082008";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_" + sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 + "_" + sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"aaaaaaafdafsafsafsafkjalkjjskfjksajfjfkjflkjflkajflkjaflkjdsafaaaaaaafdafsafsafsafkjalkjjskfjksajfjfkjflkjflkajflkjaflkjdsafaaaaaaafdafsafsafsafkjalkjjskfjksajfjfkjflkjflkajflkjaflkjdsafaaaaaaafdafsafsafsafkjalkjjskfjksajfjfkjflkjflkajflkjaflkjdsafsssssWZZZZZ\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_08_Add_Person_43091========");
	}	

	@Test(priority = 8)
	public void TC_10_Read_Person_43092() throws Exception {
		log.info("=======Starting TC_10_Read_Person_43092========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "read prospect person  with read by prospect person id present in the prospect table";
				BaseTest.sScriptName = "TC_10_Read_Person_43092";
				BaseTest.sOTMTestcaseID = "43092";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_READ_TS_10.json";
				BaseTest.sTestDescription = "read prospect person  with read by prospect person id present in the prospect table";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequest(sCreateDealProspectResource, sJsonFileURLForDealProspectCreation, sContentTypeHeader,sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_10_Read_Person_43092========");
	}	

	@Test(priority = 9)
	public void TC_11_Read_Person_43093() throws Exception {
		log.info("=======Starting TC_11_Read_Person_43093========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Read prospect person  with read by prospect  person id  which is not present in the prospect table through web service";
				BaseTest.sScriptName = "TC_11_Read_Person_43093";
				BaseTest.sOTMTestcaseID = "43093";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_READ_TS_11.json";
				BaseTest.sTestDescription = "Read prospect person  with read by prospect  person id  which is not present in the prospect table";
			
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
								
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequest(sCreateDealProspectResource, sJsonFileURLForDealProspectCreation, sContentTypeHeader,sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);	
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_11_Read_Person_43093========");
	}		

	@Test(priority = 10)
	public void TC_12_Read_Person_43094() throws Exception {
		log.info("=======Starting TC_12_Read_Person_43094========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Read prospect person name with read by person identifer type and person identifier name through web service";
				BaseTest.sScriptName = "TC_12_Read_Person_43094";
				BaseTest.sOTMTestcaseID = "43094";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_READ_TS_12.json";
				BaseTest.sTestDescription = "Read prospect person name with read by person identifer type and person identifier name";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequest(sCreateDealProspectResource, sJsonFileURLForDealProspectCreation, sContentTypeHeader,sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);	
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_12_Read_Person_43094========");
	}		

	@Test(priority = 12)
	public void TC_18_Update_Person_43095() throws Exception {
		log.info("=======Starting TC_18_Update_Person_43095========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Update Prospect new prospect person  with valid basic details - main, prospect person address, Prospect person characterstics(multiple), prospect person through web service";
				BaseTest.sScriptName = "TC_18_Update_Person_43095";
				BaseTest.sOTMTestcaseID = "43095";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_05_Add4Update_01.json";
				System.out.println(sJsonFileURLForDealProspectCreation);
				BaseTest.sTestDescription = "update Prospect new prospect person  with valid basic details - main, prospect person address, Prospect person characterstics(multiple), prospect person";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();			
				String sPersonIdNumber_1 = "Deal_PROS_UP_API_205";
				String sPersonIdNumber_2 = "01092005";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MPUP_05,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

					// Json Data to be used:
					String sJsonFileURLForDealProspectUpdate = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_UPDATE_TS_18.json";
					System.out.println(sJsonFileURLForDealProspectUpdate);		
					// Input data converted to string:
					String sDealProspectCreationForUpdate = "{\"C1-ADDPROSPERSONREST\":{\"prospectPersonId\":\"8043610594\",\"actionFlag\":\"UPD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_UPD_05,IND\",\"division\":\"IND\",\"customerSegment\":\"CORP\",\"customerTier\":\"\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx_UPD@oracle.com\",\"birthDate\":\"1983-03-04\",\"address1\":\"Addr11\",\"address2\":\"Addr21\",\"address3\":\"Addr31\",\"address4\":\"Addr41\",\"city\":\"Georgia1\",\"state\":\"SDY\",\"country\":\"AUS\",\"postal\":\"13071983\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"SSN\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
					
					// To send POST request to server for creating Deal Prospect
					WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreationForUpdate, sContentTypeHeader,
							sAcceptTypeHeader);

					iStatusCode = WF.FnGetStatusCodeFromResponse();
					if (iStatusCode == iErrorStatusCode) {
						assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					}

					else {
						assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");
						
						sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
						System.out.println("Deal Prospect Updated ! Prospect Person Id is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Prospect Person Id is :" + sValue);

						sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
						System.out.println("Deal Prospect Updated ! Prospect Person Name is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Prospect Person Name is : " + sValue);

						sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
						System.out.println("Deal Prospect Updated ! Person ID Number is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Person ID Number is : " + sValue);
					
				}
			}
		}
	}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_18_Update_Person_43095========");
	}
	
	@Test(priority = 13)
	public void TC_19_Update_Person_43096() throws Exception {
		log.info("=======Starting TC_19_Update_Person_43096========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Update prospect person with remove one records from person relationships, person identifier, person characterstics through web service";
				BaseTest.sScriptName = "TC_19_Update_Person_43096";
				BaseTest.sOTMTestcaseID = "43096";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_ADD_TS_05_Add4Update_02.json";
				BaseTest.sTestDescription = "Update prospect person with remove one records from person relationships, person identifier, person characterstics";
			
				// To Change user for sending new request
				WF.FnUserChange("RMBK1");

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequest(sCreateDealProspectResource, sJsonFileURLForDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

					// Json Data to be used:
					String sJsonFileURLForDealProspectUpdate = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_UPDATE_TS_19.json";

					// To send POST request to server for creating Deal Prospect
					WF.FnPostRequest(sCreateDealProspectResource, sJsonFileURLForDealProspectUpdate, sContentTypeHeader,
							sAcceptTypeHeader);

					iStatusCode = WF.FnGetStatusCodeFromResponse();
					if (iStatusCode == iErrorStatusCode) {
						assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					}

					else {
						assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");
						
						sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonId");
						System.out.println("Deal Prospect Updated ! Prospect Person Id is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Prospect Person Id is :" + sValue);

						sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prospectPersonName");
						System.out.println("Deal Prospect Updated ! Prospect Person Name is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Prospect Person Name is : " + sValue);

						sValue = WF.FnGetDataFromResponse("C1-C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
						System.out.println("Deal Prospect Updated ! Person ID Number is : " + sValue);
						CF.FnTestCaseStatusReport("Pass", "Deal Prospect Updated ! Person ID Number is : " + sValue);
					
				}
			}
		}
	}
		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_19_Update_Person_43096========");
	}	

	@Test(priority = 14)
	public void TC_29_Add_Person_Validation_43098() throws Exception {
		log.info("=======Starting TC_29_Add_Person_Validation_43098========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Division blank through web service";
				BaseTest.sScriptName = "TC_29_Add_Person_Validation_43098";
				BaseTest.sOTMTestcaseID = "43098";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_29.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Division blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();	
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";				
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_29_Add_Person_Validation_43098========");
	}
	
	@Test(priority = 15)
	public void TC_30_Add_Person_Validation_43099() throws Exception {
		log.info("=======Starting TC_30_Add_Person_Validation_43099========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Invalid Division   through web service";
				BaseTest.sScriptName = "TC_30_Add_Person_Validation_43099";
				BaseTest.sOTMTestcaseID = "43099";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_30.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Invalid Division";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"INDXX\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_30_Add_Person_Validation_43099========");
	}	

	@Test(priority = 16)
	public void TC_31_Add_Person_Validation_43100() throws Exception {
		log.info("=======Starting TC_31_Add_Person_Validation_43100========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Access group blank through web service";
				BaseTest.sScriptName = "TC_31_Add_Person_Validation_43100";
				BaseTest.sOTMTestcaseID = "43100";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_31.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Access group blank";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
	
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_31_Add_Person_Validation_43100========");
	}

	@Test(priority = 17)
	public void TC_32_Add_Person_Validation_43101() throws Exception {
		log.info("=======Starting TC_32_Add_Person_Validation_43101========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Invalid access group through web service";
				BaseTest.sScriptName = "TC_32_Add_Person_Validation_43101";
				BaseTest.sOTMTestcaseID = "43101";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_32.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Invalid access group";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***11\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";	

				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_32_Add_Person_Validation_43101========");
	}	

	@Test(priority = 18)
	public void TC_33_Add_Person_Validation_43102() throws Exception {
		log.info("=======Starting TC_33_Add_Person_Validation_43102========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Invalid Country through web service";
				BaseTest.sScriptName = "TC_33_Add_Person_Validation_43102";
				BaseTest.sOTMTestcaseID = "43102";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_33.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Invalid Country";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USD\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_33_Add_Person_Validation_43102========");
	}	

	@Test(priority = 19)
	public void TC_34_Add_Person_Validation_43103() throws Exception {
		log.info("=======Starting TC_34_Add_Person_Validation_43103========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Invalid State through web service";
				BaseTest.sScriptName = "TC_34_Add_Person_Validation_43103";
				BaseTest.sOTMTestcaseID = "43103";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_34.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Invalid State";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CAX\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
		
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_34_Add_Person_Validation_43103========");
	}		

	@Test(priority = 20)
	public void TC_35_Add_Person_Validation_43104() throws Exception {
		log.info("=======Starting TC_35_Add_Person_Validation_43104========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Valid Country and state for different country through web service";
				BaseTest.sScriptName = "TC_35_Add_Person_Validation_43104";
				BaseTest.sOTMTestcaseID = "43104";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_35.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Valid Country and state for different country";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_SMP_01,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"SDY\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}]}}";
		
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_35_Add_Person_Validation_43104========");
	}		

	@Test(priority = 21)
	public void TC_36_Add_Person_Validation_43105() throws Exception {
		log.info("=======Starting TC_36_Add_Person_Validation_43105========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Invalid Person Identifier Type through web service";
				BaseTest.sScriptName = "TC_36_Add_Person_Validation_43105";
				BaseTest.sOTMTestcaseID = "43105";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_36.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Invalid Person Identifier Type";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREGX\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_36_Add_Person_Validation_43105========");
	}	

	
	@Test(priority = 22)
	public void TC_37_Add_Person_Validation_43106() throws Exception {
		log.info("=======Starting TC_37_Add_Person_Validation_43106========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Person Same Person Identifier Type through web service";
				BaseTest.sScriptName = "TC_37_Add_Person_Validation_43106";
				BaseTest.sOTMTestcaseID = "43106";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_37.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation ith Prospect Person Same Person Identifier Type";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "Deal_PROS_MP_API_108";

				sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":\"01082007\",\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_37_Add_Person_Validation_43106========");
	}	

	@Test(priority = 23)
	public void TC_38_Add_Person_Validation_43107() throws Exception {
		log.info("=======Starting TC_38_Add_Person_Validation_43107========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Valid person identifier type but blank person identifier value through web service";
				BaseTest.sScriptName = "TC_38_Add_Person_Validation_43107";
				BaseTest.sOTMTestcaseID = "43107";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_38.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With Prospect Valid person identifier type but blank person identifier value";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "";
				String sPersonIdNumber_2 = "Deal_PROS_MP_API_108";

				//sPersonIdNumber_1 = sPersonIdNumber_1 + "_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_38_Add_Person_Validation_43107========");
	}		

	@Test(priority = 24)
	public void TC_39_Add_Person_Validation_43108() throws Exception {
		log.info("=======Starting TC_39_Add_Person_Validation_43108========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect invalid primary id value through web service";
				BaseTest.sScriptName = "TC_39_Add_Person_Validation_43108";
				BaseTest.sOTMTestcaseID = "43108";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_39.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With Prospect invalid primary id value";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_10XX7";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":\"fxs\",\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_39_Add_Person_Validation_43108========");
	}		

	@Test(priority = 25)
	public void TC_40_Add_Person_Validation_43109() throws Exception {
		log.info("=======Starting TC_40_Add_Person_Validation_43109========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect blank person identifier type but  person identifier value mentioned through web service";
				BaseTest.sScriptName = "TC_40_Add_Person_Validation_43109";
				BaseTest.sOTMTestcaseID = "43109";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_40.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With blank person identifier type but  person identifier value mentioned ";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_40_Add_Person_Validation_43109========");
	}		
	
	@Test(priority = 26)
	public void TC_41_Add_Person_Validation_43110() throws Exception {
		log.info("=======Starting TC_41_Add_Person_Validation_43110========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect blank person invalid characterstics type in the person characterstics object through web service";
				BaseTest.sScriptName = "TC_41_Add_Person_Validation_43110";
				BaseTest.sOTMTestcaseID = "43110";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_41.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With blank person invalid characterstics type in the person characterstics object ";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"TAXID\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_41_Add_Person_Validation_43110========");
	}		

	@Test(priority = 27)
	public void TC_42_Add_Person_Validation_43111() throws Exception {
		log.info("=======Starting TC_42_Add_Person_Validation_43111========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Add same characterstics type multipler times  in the person characterstics object";
				BaseTest.sScriptName = "TC_42_Add_Person_Validation_43111";
				BaseTest.sOTMTestcaseID = "43111";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_42.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With blank Add same characterstics type multipler times  in the person characterstics object";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_42_Add_Person_Validation_43111========");
	}	

	@Test(priority = 28)
	public void TC_43_Add_Person_Validation_43112() throws Exception {
		log.info("=======Starting TC_43_Add_Person_Validation_43112========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect Add same characterstics type multipler times with different dates   in the person characterstics object through IWS";
				BaseTest.sScriptName = "TC_43_Add_Person_Validation_43112";
				BaseTest.sOTMTestcaseID = "43112";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_43.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With Add same characterstics type multipler times with different dates   in the person characterstics object";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_1XX07";
				String sPersonIdNumber_2 = "0108200X7";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_X07,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-10\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";		
			
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_43_Add_Person_Validation_43112========");
	}	

	@Test(priority = 29)
	public void TC_44_Add_Person_Validation_43113() throws Exception {
		log.info("=======Starting TC_44_Add_Person_Validation_43113========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect valid characterstics type but blank effective date not mentioned through IWS";
				BaseTest.sScriptName = "TC_44_Add_Person_Validation_43113";
				BaseTest.sOTMTestcaseID = "43113";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_44.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With valid characterstics type but blank effective date not mentioned";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_44_Add_Person_Validation_43113========");
	}	

	@Test(priority = 30)
	public void TC_45_Add_Person_Validation_43171() throws Exception {
		log.info("=======Starting TC_45_Add_Person_Validation_43171========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect valid characterstics type but blank effective date in the person characterstics object through IWS";
				BaseTest.sScriptName = "TC_45_Add_Person_Validation_43171";
				BaseTest.sOTMTestcaseID = "43171";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_45.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With valid characterstics type but blank effective date in the person characterstics object";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_45_Add_Person_Validation_43171========");
	}		

	@Test(priority = 31)
	public void TC_46_Add_Person_Validation_43114() throws Exception {
		log.info("=======Starting TC_46_Add_Person_Validation_43114========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect valid characterstics type and   effective date but blank Characterstcs value in the person characterstics object through IWS";
				BaseTest.sScriptName = "TC_46_Add_Person_Validation_43114";
				BaseTest.sOTMTestcaseID = "43114";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_46.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With valid characterstics type and   effective date but blank Characterstcs value in the person characterstics object";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_46_Add_Person_Validation_43114========");
	}		

	@Test(priority = 32)
	public void TC_47_Add_Person_Validation_43115() throws Exception {
		log.info("=======Starting TC_47_Add_Person_Validation_43115========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Prospect valid characterstic type and date but invalid charactserstics value in the person characterstics object through IWS";
				BaseTest.sScriptName = "TC_47_Add_Person_Validation_43115";
				BaseTest.sOTMTestcaseID = "43115";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_47.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With valid characterstic type and date but invalid charactserstics value in the person characterstics object";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALEE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2222-03-05\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_47_Add_Person_Validation_43115========");
	}		

	@Test(priority = 34)
	public void TC_49_Add_Person_Validation_43116() throws Exception {
		log.info("=======Starting TC_49_Add_Person_Validation_43116========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if effective end date less than the effective start in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_49_Add_Person_Validation_43116";
				BaseTest.sOTMTestcaseID = "43116";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_49.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if effective end date less than the effective start in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"1999-03-05\",\"startDate\":\"2001-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
			
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_49_Add_Person_Validation_43116========");
	}			

	@Test(priority = 35)
	public void TC_50_Add_Person_Validation_43117() throws Exception {
		log.info("=======Starting TC_50_Add_Person_Validation_43117========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if mentioned child perosn but releationship type and start date field missing in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_50_Add_Person_Validation_43117";
				BaseTest.sOTMTestcaseID = "43117";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_50.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if mentioned child perosn but releationship type and start date field missing in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();				
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
			
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_50_Add_Person_Validation_43117========");
	}		

	@Test(priority = 36)
	public void TC_51_Add_Person_Validation_43118() throws Exception {
		log.info("=======Starting TC_51_Add_Person_Validation_43118========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with Validation if mentioned child erpson , releationship type and end date  but start date field missing in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_51_Add_Person_Validation_43118";
				BaseTest.sOTMTestcaseID = "43118";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_51.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With Validation if mentioned child erpson , releationship type and end date  but start date field missing in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
						
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_51_Add_Person_Validation_43118========");
	}	
	
	@Test(priority = 37)
	public void TC_52_Add_Person_Validation_43119() throws Exception {
		log.info("=======Starting TC_52_Add_Person_Validation_43119========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if mentioned releationship and start date but child person missing in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_52_Add_Person_Validation_43119";
				BaseTest.sOTMTestcaseID = "43119";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_52.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if mentioned releationship and start date but child person missing in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";		
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_52_Add_Person_Validation_43119========");
	}		

	@Test(priority = 38)
	public void TC_53_Add_Person_Validation_43120() throws Exception {
		log.info("=======Starting TC_53_Add_Person_Validation_43120========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if invalid child person id  in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_53_Add_Person_Validation_43120";
				BaseTest.sOTMTestcaseID = "43120";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_53.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if invalid child person id  in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101x\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_53_Add_Person_Validation_43120========");
	}		

	@Test(priority = 39)
	public void TC_54_Add_Person_Validation_43172() throws Exception {
		log.info("=======Starting TC_54_Add_Person_Validation_43172========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if invalid releatinship type in the prospet person releationship objets through IWS";
				BaseTest.sScriptName = "TC_54_Add_Person_Validation_43172";
				BaseTest.sOTMTestcaseID = "43172";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_54.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if invalid releatinship type in the prospet person releationship objets";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILDx\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_54_Add_Person_Validation_43172========");
	}		

	@Test(priority = 40)
	public void TC_55_Add_Person_Validation_43121() throws Exception {
		log.info("=======Starting TC_55_Add_Person_Validation_43121========");

		try {
			if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
				// Provide below Information to generate excel file
				BaseTest.sFunctionalModule = "Deal Prospect IWS";
				BaseTest.sScenario = "Creation of new prospect person with validation if multipler  person releationships records in which child person and releationship type  and effective start date are same through IWS";
				BaseTest.sScriptName = "TC_55_Add_Person_Validation_43121";
				BaseTest.sOTMTestcaseID = "43121";

				// Json Data to be used:
				String sJsonFileURLForDealProspectCreation = "databank\\services\\deal_prospect_person_iws\\json_files\\JSON_PRS_MP_VALID_TS_55.json";
				System.out.println(sJsonFileURLForDealProspectCreation);		
				BaseTest.sTestDescription = "Deal Prospect Creation With validation if multipler  person releationships records in which child person and releationship type  and effective start date are same ";

				// To Change user for sending new request
				WF.FnUserChange("RMBK1");
				
				sDateName = getCurrentDate();
				String sPersonIdNumber_1 = "Deal_PROS_MP_API_107";
				String sPersonIdNumber_2 = "01082007";

				sPersonIdNumber_1 = sPersonIdNumber_1 +"_"+ sDateName;
				sPersonIdNumber_2 = sPersonIdNumber_2 +"_"+ sDateName;

				CF.FnTestCaseStatusReport("Pass",
						"First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				CF.FnTestCaseStatusReport("Pass",
						"Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				System.out.println("First Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_1);
				System.out.println("Second Perosn ID Number used for Deal Prospect creation Is-> " + sPersonIdNumber_2);

				// Input data converted to string:
				String sDealProspectCreation = "{\"C1-ADDPROSPERSONREST\":{\"actionFlag\":\"ADD\",\"prospectPersonName\":\"Deal_Prospect_API_MP_107,IND\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"mil_xx@oracle.com\",\"birthDate\":\"1994-03-04\",\"address1\":\"Addr1\",\"address2\":\"Addr2\",\"address3\":\"Addr3\",\"address4\":\"Addr4\",\"city\":\"Georgia\",\"state\":\"CA\",\"country\":\"USA\",\"postal\":\"313576\",\"prosPerIdentifierList\":[{\"personIdNumber\":'"+ sPersonIdNumber_1 + "',\"PersIsPrimaryId\":true,\"idType\":\"COREG\"},{\"personIdNumber\":'"+ sPersonIdNumber_2 + "',\"PersIsPrimaryId\":false,\"idType\":\"CUSTID\"}],\"prosPerCharactersticList\":[{\"personcharacteristicValue\":\"MALE\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1-GENDR\"},{\"personcharacteristicValue\":\"63\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"PERAGE\"},{\"personcharacteristicValue\":\"1994-03-20\",\"personEffectiveDate\":\"2022-03-04\",\"personCharType\":\"C1_BRTDT\"}],\"prosPerRelationList\":[{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_101\",\"endDate\":\"2100-03-05\",\"startDate\":\"1999-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_102\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"RD\",\"idType2\":\"CUSTID\",\"parentPersonIdNumber\":\"01082003\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"CHILD\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"},{\"personRelationshipType\":\"REL1\",\"idType2\":\"COREG\",\"parentPersonIdNumber\":\"Deal_PROS_MP_API_104\",\"startDate\":\"1950-03-05\"}]}}";
				
				// To send POST request to server for creating Deal Prospect
				WF.FnPostRequestByString(sCreateDealProspectResource, sDealProspectCreation, sContentTypeHeader,
						sAcceptTypeHeader);

				int iStatusCode = WF.FnGetStatusCodeFromResponse();
				if (iStatusCode == iErrorStatusCode) {
					assertEquals(iStatusCode, iErrorStatusCode, "Status code is 400 : Error Is thrown");
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect not created due to 400 Error");
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.title");
					System.out.println("Deal Prospect NOT Created ! Reason IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);					
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.httpStatus");
					System.out.println("Deal Prospect NOT Created ! httpStatus IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! httpStatus IS : " + sValue);

					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageText");
					System.out.println("Deal Prospect NOT Created ! Message text IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Reason IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageNbr");
					System.out.println("Deal Prospect NOT Created ! Message Number IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Number IS : " + sValue);
					
					sValue = WF.FnGetDataFromResponse("problemDetailDocument.serverMessage.messageCategory");
					System.out.println("Deal Prospect NOT Created ! Message Category IS : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect NOT Created ! Message Category IS : " + sValue);					
				}

				else {
					assertEquals(iStatusCode, iSuccessStatusCode, "Status code is Matching");

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonId");
					System.out.println("Deal Prospect Created ! Prospect Person Id is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Id is :" + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prospectPersonName");
					System.out.println("Deal Prospect Created ! Prospect Person Name is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Prospect Person Name is : " + sValue);

					sValue = WF.FnGetDataFromResponse("C1-ADDPROSPERSONREST.prosPerIdentifierList.personIdNumber");
					System.out.println("Deal Prospect Created ! Person ID Number is : " + sValue);
					CF.FnTestCaseStatusReport("Pass", "Deal Prospect Created ! Person ID Number is : " + sValue);

				}
			}
		}

		catch (Exception e) {
			System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
			e.printStackTrace();
			BaseTest.eFlgFound = "false";
			CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
		}

		log.info("=======Finished TC_55_Add_Person_Validation_43121========");
	}	
	
}