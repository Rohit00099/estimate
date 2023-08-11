package test.java.ormbframework.testCases.banking;



import java.util.Hashtable;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.ormbframework.BaseTest;
import test.java.ormbframework.pageEvents.AlgorithmPageEvents;
import test.java.ormbframework.pageEvents.DealManagementPageEvents;
import test.java.ormbframework.pageEvents.LoginPageEvents;
import test.java.ormbframework.utils.ApplicationFunctions;
import test.java.ormbframework.utils.CommonFunctions;
import test.java.ormbframework.utils.DataBaseFunctions;
import test.java.ormbframework.utils.WebServiceFunctions;

public class deal_mangement extends BaseTest {
    CommonFunctions CF = new CommonFunctions();
    ApplicationFunctions AF = new ApplicationFunctions();
    DataBaseFunctions DB = new DataBaseFunctions();
    DealManagementPageEvents DM = new DealManagementPageEvents(getDriver());
    WebServiceFunctions WF = new WebServiceFunctions();
    public static String sCreateDealProspectResource = "/rest/ouaf/api/iws/C1-ProspectPersonREST/prospPerson";
    public static String sCreateDealProspectAccountResource = "/rest/ouaf/api/iws/C1-ProspectAccountREST/prospectAccount";
    public static String sCreateDealResource = "/rest/ouaf/api/iws/C1-DealREST/dealService";
    public static String sAddPrizeListAssignmentResource = "/rest/ouaf/api/iws/C1-DealPricelistAssignmentREST/dealPricelistAssignment";
    public static String sDealPricingAndCommitmentResource = "/rest/ouaf/api/iws/C1-DealPriceAsgnCommitmentsREST/DealPriceAsgnCommitments";
    public static String sContentTypeHeader = "application/json";
    public static String sAcceptTypeHeader = "application/json";
    public static String sStatusCode, sValue;
    public static int iSuccessStatusCode = 200;
    public static int iErrorStatusCode = 400;

    public static Boolean OnlineSimulation = true;
    public static Boolean IwsSimulation = false;

    

    @BeforeClass
    public void beforeClass() throws Exception {

        System.out.println("Before Class method");
        //Excel Data to be used:
        String sPRICWorkbook = "./databank/services/pricing_and_commitment_iws/Deal_Pricing_&_Commitment_API_Test_Data_New.xlsx";
        String sPRICSheetName = "Data_Creation_Env";

        DB.FnUpdateBillableChargeDates(23, sPRICSheetName, sPRICWorkbook);
        DB.FnUpdateBillableChargeDates(24, sPRICSheetName, sPRICWorkbook);

        //Excel Data to be used:
        String sDealWorkbook = "./databank/services/deal_creation_iws/Deal_Creation_API_Test_Data.xlsx";
        String sDealSheetName = "Data_Creation_Env";

        DB.FnUpdateBillableChargeDates(2243, sDealSheetName, sDealWorkbook);
        DB.FnUpdateBillableChargeDates(2244, sDealSheetName, sDealWorkbook);
        
        
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='INDPM'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='INDSPM'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='PMBK1'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='PMBK2'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='PRDMBK1'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='RMBK1'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries
        DB.FnUpdateValueInDb("update sc_user set F1_SECURITY_HASH =' ' where user_id ='SPMBK1'","COMMIT", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort")); //after 6.0 latest QADATA Does not need this queries


    }

    



    /***********************************************************************************************************************************************************
     * Written by					:   	Ajinkya Joshi
     * Script Name					:  		DealManagement_TC001
     * Script Description			: 		Deal Management end to end flow for customer entity
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		08-Oct-2021
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		DealManagementPageElements.java 
     * Datasheet name				: 		DM_Automation_Deal_Managment_Ver_11.xls
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(enabled = true, priority = 0, groups = {
        "sanity testing"
    })
    public void DealManagement_TC001() throws Exception {
        log.info("=======Starting DealManagement_TC001========");

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "Deal Management end to end flow for customer entity";
                BaseTest.sScriptName = "DealManagement_TC001";
                BaseTest.sOTMTestcaseID = "NA";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DM_Automation_Deal_Managment_Ver_11.xlsx";
                String sSheetName = "TC-Existing customer";
                BaseTest.sTestDescription = "Deal Management verifications";

                //Function to Search entity 
                dealManagementPageEvents.FnSearchEntity(98, sSheetName, sWorkbook);

                //Function to create Deal 
                String sDealIDCreatedAfterDealCreation = dealManagementPageEvents.FnDealCreation(102, sSheetName, sWorkbook);

                //Function to navigate to deal information from deal creation page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealCreation();

                //Function to Verify of deal information 
                dealManagementPageEvents.FnDealInformationVerification(106, sSheetName, sWorkbook);

                //Function to navigate to View And AssignPrizelist 
                dealManagementPageEvents.FnNavigationToViewAndAssignPrizelist();

                //Function to Verify Price List Assignment 
                dealManagementPageEvents.FnPriceListAssignment(110, sSheetName, sWorkbook);

                //Search filter options on Select Prize Item group
                dealManagementPageEvents.FnSearchFiltersForSelectPrizeItemgroup(114, sSheetName, sWorkbook);

                //Function to Verify terms and conditions for corporate banking
                //dealManagementPageEvents.FnVerificationOfTermsAndConditionsForCorporateBanking();	

                //Function to Verify terms and conditions for Account services
                //dealManagementPageEvents.FnVerificationOfTermsAndConditionsForAccountServices();

                //Function to Verify terms and conditions for Prize Item
                //dealManagementPageEvents.FnVerificationOfTermsAndConditionsForPrizeItem();

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To verify of view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnViewAndEditPricing(121, sSheetName, sWorkbook);

                //Function to Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(192, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(197, sSheetName, sWorkbook);

                //Function to Verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(201, sSheetName, sWorkbook);

                //Function To Navigate to Adhoc Revenue and cost UI from Deal information page
                dealManagementPageEvents.FnNavigationToAdhocRevenueAndCostCreationFromDealInformation();

                //Function To create Adhoc Revenue and cost 
                dealManagementPageEvents.FnCreationOfAdhocRevenueAndCost(204, sSheetName, sWorkbook);

                //Function to Verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(210, sSheetName, sWorkbook);

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(215, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(218, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(222, sSheetName, sWorkbook);

                //Function to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To add seasonal pricing of prize items 
                dealManagementPageEvents.FnUpdateRatesOfPrizeItemsThroughSeasonalLink("PI_022", "9.89", "PI_033", "111.08", "PI_027", "0.14", "PI_028", "1.99", "PI_031", "16.67");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function for Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To edit volumes of prize items 
                dealManagementPageEvents.FnUpdatePrizeItemsVolumes("PI_022", "3", "PI_027", "2", "PI_028", "2");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function for Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To add seasonal pricing of prize items 
                dealManagementPageEvents.FnAddSeasonalPriceToPrizeItems(211, sSheetName, sWorkbook, "12.99", "8.99", "109.9", "13.05", "11.15", "11.95");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(299, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(302, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(306, sSheetName, sWorkbook);

                //Function for Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search filter options on Select Prize Item group
                dealManagementPageEvents.FnSearchFiltersForPerticularPrizeItemAndSendForApproval("PI_030");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                AF.FnLoginChange(getDriver(), "PRDMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function for verify deal displayed on deal approval dashboard
                //dealManagementPageEvents.FnVerificationOfDealOnDealToDoZone();

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForPrizeItemManager("Pending For Approval", "Price Item Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search filter options on Select Prize Item group
                dealManagementPageEvents.FnSearchFiltersForPerticularPrizeItemAndCheckLimits("PI_030");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function for return for submitter and update data
                dealManagementPageEvents.FnReturnForSubmitterAndUpdateData("Deal Return", "Comments For Returning deal");

                //Function to verify of deal information 
                dealManagementPageEvents.FnDealInformationVerification(311, sSheetName, sWorkbook);

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function to Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "PMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForPM("Pending For Approval", "Pricing Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Verify Send deal for Approval and Return to Submitter buttons are enabled
                dealManagementPageEvents.FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Verify Approval status for PM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyApprovalStatusOnViewAndEditPricing("Pricing Manager1");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(348, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(341, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(344, sSheetName, sWorkbook);

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "SPMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForSPM("Pending For Approval", "Senior Pricing Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and recommend prize
                dealManagementPageEvents.FnSearchAndRecommendPriceForPrizeItems("PI_027", "0.11", "PI_029", "0.32");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and verify recommended prize
                dealManagementPageEvents.FnSearchPrizeItemAndVerifyRecommendedPrize("PI_027", "0.11", "PI_029", "0.32");

                //Function To verify Approval status for SPM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyApprovalStatusOnViewAndEditPricing("Senior Pricing Manager1");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify deal information //failed Recommended
                dealManagementPageEvents.FnDealInformationVerification(434, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryAfterRecommendationOnDealInformation(427, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(430, sSheetName, sWorkbook);

                //Function for return for submitter and update data
                dealManagementPageEvents.FnReturnForSubmitterAndUpdateData("Deal Return", "verify price item PI_030  and return to the RM1 - user");

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRM("Pending For Approval", "Relationship Manager 1", "Returned Deal to Submitter as Rejected");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To add seasonal pricing of prize items 
                dealManagementPageEvents.FnUpdateRatesOfPrizeItemsThroughSeasonalLinkAfterRecommendation("PI_027", "0.11", "PI_029", "0.32");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and verify recommended prize
                dealManagementPageEvents.FnSearchPrizeItemAndVerifyRecommendedPrize("PI_027", "0.11", "PI_029", "0.32");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify of deal information 
                //dealManagementPageEvents.FnDealInformationVerification(523, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(519, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(514, sSheetName, sWorkbook);

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "PMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function for verify deal displayed on deal approval dashboard
                //dealManagementPageEvents.FnVerificationOfDealOnDealToDoZone();

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "SPMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To approve deal 
                dealManagementPageEvents.FnApproveDealFromDealInformation();

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Finalize Deal 
                dealManagementPageEvents.FnFinalizeDealFromDealInformation();

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(527, sSheetName, sWorkbook);

                //Function To Accept Deal 
                dealManagementPageEvents.FnAcceptDealFromDealInformation(531, sSheetName, sWorkbook);

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(534, sSheetName, sWorkbook);

                //Function To Apply back Deal 
                dealManagementPageEvents.FnApplyBackDealFromDealInformation();

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerificationAfterFullyOrchestrated(541, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(548, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(549, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(550, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(551, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(552, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(553, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(554, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(555, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(556, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(557, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(558, sSheetName, sWorkbook);

                //Function to get prize item details from DB and verify
                DB.FnGetPriceItemDetails(561, sSheetName, sWorkbook);
            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }

        log.info("=======Finished DealManagement_TC001========");

    }
    /***********************************************************************************************************************************************************
     * Written by					:   	Ajinkya Joshi
     * Script Name					:  		Accrual_TC_34465
     * Script Description			: 		ActualBillLevel_TrialBill
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		13-Dec-2021
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		BankingTestEnvironment.csv, Accrual.csv, Accrual_PrerequisiteData.xls
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 1, groups = {
        "sanity testing"
    })
    public void DealManagement_TC002() throws Exception {
        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_TC002";
                BaseTest.sScriptName = "DealManagement_TC002";
                BaseTest.sOTMTestcaseID = "34465";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DM_Automation_Deal_Managment_Account_v1.xlsx";
                String sSheetName = "TC_Existing Account";
                BaseTest.sTestDescription = "Deal Management verifications for account";


                //Search entity 
                dealManagementPageEvents.FnSearchEntity(72, sSheetName, sWorkbook);

                //Deal Creation 
                String sDealIDCreatedAfterDealCreation = dealManagementPageEvents.FnDealCreation(76, sSheetName, sWorkbook);

                dealManagementPageEvents.FnNavigationToDealInformationFromDealCreation();

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(80, sSheetName, sWorkbook);

                dealManagementPageEvents.FnNavigationToViewAndAssignPrizelist();

                //verification of Price List Assignment 
                dealManagementPageEvents.FnPriceListAssignment(84, sSheetName, sWorkbook);

                //Search filter options on Select Prize Item group
                dealManagementPageEvents.FnSearchFiltersForSelectPerticularPrizeItemForAccount(88, sSheetName, sWorkbook, "PI_021", "PI_022", "PI_023", "PI_027", "PI_028", "PI_028", "PI_031", "CC_032");

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();
                /*
                                //Verification of terms and conditions for corporate banking
                                dealManagementPageEvents.FnVerificationOfTermsAndConditionsForCorporateBanking();	
                                							
                                //Verification of terms and conditions for Account services
                                dealManagementPageEvents.FnVerificationOfTermsAndConditionsForAccountServices();
                                				
                                //Verification of terms and conditions for Prize Item
                                dealManagementPageEvents.FnVerificationOfTermsAndConditionsForPrizeItem();
                */
                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To verify of view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnViewAndEditPricingForAccounts(95, 97, 113, sSheetName, sWorkbook);

                //To add seasonal pricing of prize items 
                dealManagementPageEvents.FnVerifyApporvalStatusOfPrizeItems(83, sSheetName, sWorkbook);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Verify Service quantity of prize item group 
                dealManagementPageEvents.FnVerifyServiceQuantityOfPrizeItemGroups("5", "2", "3");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(130, sSheetName, sWorkbook);

                //To verify Personal Hierarchy Information On Deal Information for accounts
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(131, sSheetName, sWorkbook);

                //To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(135, sSheetName, sWorkbook);

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(139, sSheetName, sWorkbook);

                //To Navigate to Adhoc Revenue and cost UI from Deal information page
                dealManagementPageEvents.FnNavigationToAdhocRevenueAndCostCreationFromDealInformation();

                //To create Adhoc Revenue and cost 
                dealManagementPageEvents.FnCreationOfAdhocRevenueAndCost(142, sSheetName, sWorkbook);

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(148, sSheetName, sWorkbook);

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(153, sSheetName, sWorkbook);

                //To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(156, sSheetName, sWorkbook);

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(160, sSheetName, sWorkbook);

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To add seasonal pricing of prize items 
                dealManagementPageEvents.FnUpdateRatesOfPrizeItemsThroughSeasonalLinkForAccounts("PI_021", "12", "PI_022", "12", "PI_027", "0.11", "PI_028", "0.69");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To edit volumes of prize items 
                dealManagementPageEvents.FnUpdatePrizeItemsVolumesForAccounts("PI_022", "5", "PI_028", "5", "PI_028", "2");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To add seasonal pricing of prize items 
                dealManagementPageEvents.FnAddSeasonalPriceToPrizeItemsForAccounts(160, sSheetName, sWorkbook, "PI_021", "11", "PI_028", "0.63");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To verify of view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnViewAndEditPricingForAccounts(166, 168, 184, sSheetName, sWorkbook);

                //To add seasonal pricing of prize items 
                dealManagementPageEvents.FnVerifyApporvalStatusOfPrizeItems(160, sSheetName, sWorkbook);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Verify Service quantity of prize item group 
                dealManagementPageEvents.FnVerifyServiceQuantityOfPrizeItemGroups("14", "6", "8");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To verify Personal Hierarchy Information On Deal Information for accounts
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(205, sSheetName, sWorkbook);

                //To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(202, sSheetName, sWorkbook);

                //verification of deal information // stable
                dealManagementPageEvents.FnDealInformationVerification(209, sSheetName, sWorkbook);

                //To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(213, sSheetName, sWorkbook);

                AF.FnLoginChange(getDriver(), "PMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Verify Send deal for Approval and Return to Submitter buttons are enabled
                dealManagementPageEvents.FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "SPMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForPM("Pending For Approval", "Senior Pricing Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and recommend prize
                dealManagementPageEvents.FnSearchAndRecommendPriceForPrizeItems("PI_027", "0.89", "PI_028", "0.59");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and verify recommended prize
                dealManagementPageEvents.FnSearchPrizeItemAndVerifyRecommendedPrize("PI_027", "0.89", "PI_028", "0.59");

                //Function To verify Approval status for SPM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyApprovalStatusOnViewAndEditPricing("Senior Pricing Manager1");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify deal information //stable
                dealManagementPageEvents.FnDealInformationVerification(276, sSheetName, sWorkbook);

                //FAILING due to defect 33159560
                //Function To verify Deal Financial Summary Information On Deal Information
                //dealManagementPageEvents.FnVerifyDealFinancialSummaryAfterRecommendationOnDealInformation(269, sSheetName, sWorkbook);

                //To verify Personal Hierarchy Information On Deal Information for accounts
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(272, sSheetName, sWorkbook);

                //Function for return for submitter and update data
                dealManagementPageEvents.FnReturnForSubmitterAndUpdateData("Deal Return", "verify price item PI_028 and return to the RM1 - user");

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForSPM("Pending For Approval", "Relationship Manager 1", "Returned Deal to Submitter as Rejected");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //To add seasonal pricing of prize items 
                dealManagementPageEvents.FnVerifyApporvalStatusOfPrizeItems(276, sSheetName, sWorkbook);

                //Function To add seasonal pricing of prize items 
                dealManagementPageEvents.FnUpdateRatesOfPrizeItemsThroughSeasonalLinkAfterRecommendation("PI_027", "0.89", "PI_028", "0.59");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and verify recommended prize
                dealManagementPageEvents.FnSearchPrizeItemAndVerifyRecommendedPrize("PI_027", "0.89", "PI_028", "0.59");

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify of deal information 
                dealManagementPageEvents.FnDealInformationVerification(335, sSheetName, sWorkbook);

                //FAILING due to defect 33159560
                //Function To verify Deal Financial Summary Information On Deal Information
                //dealManagementPageEvents.FnVerifyDealFinancialSummaryOnDealInformation(328, sSheetName, sWorkbook);

                //FAILING due to defect 33159560
                //To verify Personal Hierarchy Information On Deal Information for accounts
                //dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(331, sSheetName, sWorkbook);

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "PMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function for verify deal displayed on deal approval dashboard
                dealManagementPageEvents.FnVerificationOfDealOnDealToDoZone();

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                AF.FnLoginChange(getDriver(), "SPMBK1");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealId(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To approve deal 
                dealManagementPageEvents.FnApproveDealFromDealInformation();

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation);

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To Finalize Deal 
                dealManagementPageEvents.FnFinalizeDealFromDealInformation();

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(339, sSheetName, sWorkbook);

                //Function To Accept Deal 
                dealManagementPageEvents.FnAcceptDealFromDealInformation(343, sSheetName, sWorkbook);

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(346, sSheetName, sWorkbook);

                //Function To Apply back Deal 
                dealManagementPageEvents.FnApplyBackDealFromDealInformation();

                //Function for verify deal information 
                dealManagementPageEvents.FnDealInformationVerificationAfterFullyOrchestrated(353, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(373, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(374, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(375, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(376, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(377, sSheetName, sWorkbook);

                DB.FnGetPriceItemDetailsForAccount(378, sSheetName, sWorkbook);

            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		Currency Conversion
     * Script Description			: 		Currency Conversion - Test 39126	
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		13-Dec-2021
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Deal_Managment_CurrencyAndDivProd.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(enabled = true, priority = 1, groups = {
        "sanity testing"
    })
    public void DM_03() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DM_03";
                BaseTest.sScriptName = "CurrencyConversion";
                BaseTest.sOTMTestcaseID = "34465";
                BaseTest.sTestDescription = "Deal Management Currency Conversion Test Case";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                
                
                //Function to update "Stacking Required" Option as N
                //Excel Data to be used:
                String sWorkbookSTACK = "./databank/banking/deal_management/DealManagement_Test_Stacking_39768.xlsx";
                String sSheetNameSTACK = "TEST_DATA";
                dealManagementPageEvents.FnUpdateAlgorithmValue(233, sSheetNameSTACK, sWorkbookSTACK);
                dealManagementPageEvents.FnUpdateAlgorithmValue(234, sSheetNameSTACK, sWorkbookSTACK);
                

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DM_Automation_Deal_Managment_CurrencyAndDivProdOLD.xlsx";
                String sSheetName = "Currency Conversion - Deal ";
                BaseTest.sTestDescription = "Deal Management verifications for Currency Conversion";

                //Update Billable Charge Start Date and End Date
                DB.FnUpdateBillableChargeDates(76, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(77, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(78, sSheetName, sWorkbook);
                

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");


                String DealId = "3734785295";

                boolean skipDealCreation = false;

                if (skipDealCreation == true) {

                    dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId); //for Test Resume
                    dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //for Test Resume

                }


                //Search entity 
                dealManagementPageEvents.FnSearchEntity(83, sSheetName, sWorkbook);

                //Deal Creation 
                String sDealIDCreatedAfterDealCreation = dealManagementPageEvents.FnDealCreation(87, sSheetName, sWorkbook);

                System.out.println("===> sDealIDCreatedAfterDealCreation Deal id:-" + sDealIDCreatedAfterDealCreation);

                DealId = sDealIDCreatedAfterDealCreation;

                //CF.FnWriteCellValue(87, 19, sDealIDCreatedAfterDealCreation, sSheetName, sWorkbook);

                dealManagementPageEvents.FnNavigationToDealInformationFromDealCreation();

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(91, sSheetName, sWorkbook);

                dealManagementPageEvents.FnNavigationToViewAndAssignPrizelist();

                //verification of Price List Assignment 
                dealManagementPageEvents.FnPriceListAssignment(95, sSheetName, sWorkbook);

                //To save the assigned prizelist for given prize list ID
                dealManagementPageEvents.FnSaveOnPriceListAssignment();
                ////have calculation issue if we assign pricelist from deal assign pricelist on customer i.e customer Pricelist


                dealManagementPageEvents.FnNavigateToAccountViewAndAssignProposeProduct();
                dealManagementPageEvents.FnSearchAndEnrollProduct(99, sSheetName, sWorkbook);

                dealManagementPageEvents.FnNavigateToAccountViewAndAssignProposeProduct();
                dealManagementPageEvents.FnSearchAndEnrollProduct(100, sSheetName, sWorkbook);

                //Function to Navigate back to Deal Information Screen from Product Enrollment screen
                dealManagementPageEvents.FnNavigationToDealInformationFromProductScreen();

                //Function to Navigate To Account View And Assign Product Enrollment screen
                dealManagementPageEvents.FnNavigateToAccountViewAndAssignProposeProduct();

                //Function to Search And Validate Enrolled Product Details
                dealManagementPageEvents.FnSearchAndValidateEnrolledProduct(104, sSheetName, sWorkbook);
                dealManagementPageEvents.FnSearchAndValidateProposedProduct(108, sSheetName, sWorkbook);
                dealManagementPageEvents.FnSearchAndValidateProposedProduct(109, sSheetName, sWorkbook);
                dealManagementPageEvents.FnSearchAndValidateProposedProduct(110, sSheetName, sWorkbook);

                //Function to Broadcast and Verify Product Details
                dealManagementPageEvents.FnBroadcastProductDetailsOfProposedProduct(114, sSheetName, sWorkbook);
                dealManagementPageEvents.FnBroadcastProductDetailsOfProposedProduct(115, sSheetName, sWorkbook);
                dealManagementPageEvents.FnBroadcastProductDetailsOfProposedProduct(116, sSheetName, sWorkbook);

                //Function to Navigate back to Deal Information Screen from Product Enrollment screen
                dealManagementPageEvents.FnNavigationToDealInformationFromProductScreen();

                // price item selection line no 21
                //Search filter options on Select Prize Item group
                dealManagementPageEvents.FnSearchAndSelectPrizeItemgroup(120, sSheetName, sWorkbook);
                dealManagementPageEvents.FnSearchAndSelectPrizeItemgroup(121, sSheetName, sWorkbook);

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                dealManagementPageEvents.FnAddSeasonalPricingForSinglePriceItemFromExcel(125, sSheetName, sWorkbook); //26

                //Function to Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments(); //28

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //29

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To verify of view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnViewAndEditPricing(130, sSheetName, sWorkbook); //30


                //Function To Verify PRODUCT FINANCIAL SUMMARY For Deal Currency
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(187, sSheetName, sWorkbook, "Deal", "INDSPM", false);
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(188, sSheetName, sWorkbook, "Deal", "INDSPM", false);
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(189, sSheetName, sWorkbook, "Deal", "INDSPM", false);

                //Function To Switch Currency Toggle For Product financial summary 
                dealManagementPageEvents.PricingAndCommitmentProductFinancialSummarySwitchToggle();
                //Raised Bug 35237989 - UNABLE TO SHOW DIVISION CURRENCY PRODUCT FINANCIAL SUMMARY ON PRICING SCREEN
                //Function To Verify PRODUCT FINANCIAL SUMMARY For Division Currency
                //dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(187, sSheetName, sWorkbook, "Division", "INDSPM", false);
                //dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(188, sSheetName, sWorkbook, "Division", "INDSPM", false);
                //dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(189, sSheetName, sWorkbook, "Division", "INDSPM", false);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(195, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(196, sSheetName, sWorkbook);


                //Function To verify Personal Hierarchy Information On Deal Information for "Customer Division / Account Currency"
                dealManagementPageEvents.DealInformationPersonHierarchySwitchToggle();

                //Function To verify Division Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(195, sSheetName, sWorkbook);

                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccountsForDivisionCurrency(196, sSheetName, sWorkbook);

                // Deal Currency on Product financial summary zone
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(202, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(203, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(204, sSheetName, sWorkbook, "Deal", "");

                // Division Currency on Product financial summary zone
                dealManagementPageEvents.DealInformationProductFinancialSummarySwitchToggle();

                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(202, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(203, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(204, sSheetName, sWorkbook, "Division", "");


                //Deal Currency on Division financial summary zone
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(210, sSheetName, sWorkbook, "Deal");

                dealManagementPageEvents.DealInformationDivisionFinancialSummarySwitchToggle();

                //Division Currency on Division financial summary zone
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(210, sSheetName, sWorkbook, "Division");

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(216, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(221, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(225, sSheetName, sWorkbook); //issue in assert sCreationDateTime

                //Function To Navigate to Adhoc Revenue and cost UI from Deal information page
                dealManagementPageEvents.FnNavigationToAdhocRevenueAndCostCreationFromDealInformation();

                //Function To create Adhoc Revenue and cost 
                dealManagementPageEvents.FnCreationOfAdhocRevenueAndCost(230, sSheetName, sWorkbook);

                //Function to Verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(237, sSheetName, sWorkbook);

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(307, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(308, sSheetName, sWorkbook);

                // Deal Currency on Product financial summary zone
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(314, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(315, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(316, sSheetName, sWorkbook, "Deal", "");


                //Function To Verify Product financial summary zone
                dealManagementPageEvents.DealInformationProductFinancialSummarySwitchToggle();

                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(314, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(315, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(316, sSheetName, sWorkbook, "Division", "");


                //Function To Verify Division financial summary zone
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(322, sSheetName, sWorkbook, "Deal");

                dealManagementPageEvents.DealInformationDivisionFinancialSummarySwitchToggle();

                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(322, sSheetName, sWorkbook, "Division");


                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(328, sSheetName, sWorkbook);

                //Function to Verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(332, sSheetName, sWorkbook);

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();

                // Navigate to Deal Dashboard UI
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForPrizeItemManager("Pending For Approval", "India Pricing Manager", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();


                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "INDPM"); //60
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //61


                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard"); //62

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard(); //63

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForPrizeItemManager("Pending For Approval", "India Pricing Manager", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //64

                //Function To verify Approval status for SPM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("PENDING FOR APPROVAL"); //65

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();
                //65 Pending = Click on View and Edit pricing button = The status of the price items should be displayed of the previous approver.	The price item should in 'error' status

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_021", "APPROVED"); //67 //showing status approved for price items
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_022", "ERROR"); //67
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_024", "ERROR"); //67
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_025", "APPROVED"); //67
                //Error -> not showing status for other reporting/swift price items i.e cpivfn_046 to CPICFN_052

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //67

                //Function to navigate Pricing and commitment edit 
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_022", "PENDING FOR APPROVAL"); //68

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function to navigate Pricing and commitment edit 
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //dealManagementPageEvents.FnVerifyStatusOfSeasonalPriceItemOnPricingAndCommitment("CC_022", "PENDING FOR APPROVAL", 1); //68
                //error 

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments(); //68		

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation(); //69

                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "INDSPM"); //70
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");


                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "India Senior Pricing Manager", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To verify Approval status for INDSPM on personal Hierarchy
                //74 = The consolidated status displayed on hierarchy section should be of the previous approver	The consolidated status should be displayed successfully on status column.
                //bug -> not showing status for any approval

                dealManagementPageEvents.FnNavigateToViewAndEditPricing(); //75		

                //75 = Click on View and Edit pricing button	The status of the price items should be displayed of the previous approver.
                //bug -> not showing price items on pricing screen i.e showing no data to display error

                //Function to Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments(); //75

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //76
                //have to test message I,e Pricing Limits and Financial Summary Validated Successfully

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_024", "APPROVED"); // 77
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnApproveDealFromDealInformation(); //78

                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("APPROVED"); //78

                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //80
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(DealId, true, false, false, "INDSPM");


                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard(); //81

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Pricing Manager 1", "Unapproved"); //83

                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowApprovalHistory(336, sSheetName, sWorkbook); //83
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowApprovalHistory(337, sSheetName, sWorkbook); //83
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowApprovalHistory(338, sSheetName, sWorkbook); //83
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowApprovalHistory(339, sSheetName, sWorkbook); //83

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow(); //82

                //Navigate to Deal Approver Dashboard Through PMBK1 user
                AF.FnLoginChange(getDriver(), "PMBK1"); //84
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //85


                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Pricing Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("APPROVED");

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //91

                //Function to Verify Send deal for Approval and Return to Submitter buttons are enabled
                dealManagementPageEvents.FnVerifySendDealForApprovalAndReturnToSubmitterButtonsEnabled(); //92

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Search prize items and recommend prize
                dealManagementPageEvents.FnSearchAndRecommendPriceForSinglePriceItem("CPIVFN_046", "23.22"); //95

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function to Verify Deal information Deal Status And Deal Version Status
                dealManagementPageEvents.FnVerifyDealInformationDealStatusAndDealVersionStatus("Pending For Approval", "Finalized"); //96

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //97

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Deal Currency
                //                				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(401, sSheetName, sWorkbook, "Deal", "INDSPM", true);
                //                				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(402, sSheetName, sWorkbook, "Deal", "INDSPM", true);
                //                				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(403, sSheetName, sWorkbook, "Deal", "INDSPM", true);

                //Function To Switch Currency Toggle For Product financial summary 
                //                				dealManagementPageEvents.PricingAndCommitmentProductFinancialSummarySwitchToggle();

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Division Currency
                //				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(401, sSheetName, sWorkbook, "Division", "INDSPM", true);
                //				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(402, sSheetName, sWorkbook, "Division", "INDSPM", true);
                //				dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(403, sSheetName, sWorkbook, "Division", "INDSPM", true);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                
                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(409, sSheetName, sWorkbook);

                //Function To verify Account Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(410, sSheetName, sWorkbook);

                // Deal Currency on Product financial summary on deal level
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(416, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(417, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(418, sSheetName, sWorkbook, "Deal", "");

                //Function To Verify Product financial summary on deal level
                dealManagementPageEvents.DealInformationProductFinancialSummarySwitchToggle(); //103

                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(416, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(417, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(418, sSheetName, sWorkbook, "Division", ""); //103


                //Function To Verify Division financial summary for deal currency
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(424, sSheetName, sWorkbook, "Deal");

                //function to switch currency toggle
                dealManagementPageEvents.DealInformationDivisionFinancialSummarySwitchToggle();

                //Function To Verify Division financial summary for division currency
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(424, sSheetName, sWorkbook, "Division");

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(430, sSheetName, sWorkbook);

                //Function to Verify Deal information Deal Status And Deal Version Status
                dealManagementPageEvents.FnVerifyDealInformationDealStatusAndDealVersionStatus("Pending For Approval", "Finalized"); //96

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(434, sSheetName, sWorkbook);

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To verify of view and edit pricing on Pricing and commitment screen i,e Personal Hierarchy Information
                //				//dealManagementPageEvents.FnViewAndEditPricing(344, sSheetName, sWorkbook); //109  SKIP Because have hierarchy "DE_AUT02,Account Services" & "DE_AUTO3,Payment Services"
                dealManagementPageEvents.FnViewAndEditPricing(366, sSheetName, sWorkbook); //109 // instead 344 checking 366 because Reporting/Swift" are on 366 
                //error checking "DE_AUT01,Corporate Banking" instead "DE_AUTO4,Reporting/Swift" 

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function for return for submitter and update data
                dealManagementPageEvents.FnReturnForSubmitterAndUpdateData("Deal Return", "Deal Returned");

                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("PENDING FOR APPROVAL"); //111

                dealManagementPageEvents.FnVerifyDealFinancialSummaryStatus("PENDING FOR APPROVAL"); //111

                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("PENDING FOR APPROVAL"); ///111

                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");

                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(DealId, true, true, false, "PMBK1");

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Relationship Manager 1", "Returned Deal to Submitter as Rejected");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                AF.FnLoginChange(getDriver(), "RMBK1"); //116
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //117

                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(DealId, true, true, false, "RMBK1"); //118

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard(); //119

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Relationship Manager 1", "Returned Deal to Submitter as Rejected");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("PENDING FOR APPROVAL"); //121

                dealManagementPageEvents.FnVerifyDealFinancialSummaryStatus("PENDING FOR APPROVAL"); //121

                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("PENDING FOR APPROVAL"); ///121

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CPIVFN_046", "APPROVER-RECOMMENDED"); //121

                dealManagementPageEvents.FnVerifyPriceItemApproverOnPricingAndCommitment("CPIVFN_046", "Pricing Manager1"); //122

                // Function To Update and verify price Item rate and check validation for incorrect input On "Pricing & Commitment" screen UI
                dealManagementPageEvents.FnUpdateAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen("CPIVFN_046", "25.00", "23.22", ""); //123 update rate

                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //124

                //Function To Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Deal Currency
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(496, sSheetName, sWorkbook, "Deal", "INDSPM", true); //125
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(497, sSheetName, sWorkbook, "Deal", "INDSPM", true);
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(498, sSheetName, sWorkbook, "Deal", "INDSPM", true);

                //Function To Switch Currency Toggle For Product financial summary 
                dealManagementPageEvents.PricingAndCommitmentProductFinancialSummarySwitchToggle(); //126

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Division Currency
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(496, sSheetName, sWorkbook, "Division", "INDSPM", true); //127
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(497, sSheetName, sWorkbook, "Division", "INDSPM", true);
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnCustomerOrAccountPricingScreen(498, sSheetName, sWorkbook, "Division", "INDSPM", true);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();


                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(504, sSheetName, sWorkbook); //127


                //Function To verify Account Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForAccounts(505, sSheetName, sWorkbook); //127

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Deal Currency
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(511, sSheetName, sWorkbook, "Deal", ""); //129
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(512, sSheetName, sWorkbook, "Deal", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(513, sSheetName, sWorkbook, "Deal", "");

                //Function To Switch Currency Toggle For Product financial summary 
                dealManagementPageEvents.DealInformationProductFinancialSummarySwitchToggle(); //130

                //Function To Verify PRODUCT FINANCIAL SUMMARY For Division Currency
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(511, sSheetName, sWorkbook, "Division", ""); //130
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(512, sSheetName, sWorkbook, "Division", "");
                dealManagementPageEvents.FnVerifyProductFinancialSummaryOnDealLevels(513, sSheetName, sWorkbook, "Division", "");
                //error //product

                //Function To Verify Division financial summary for deal currency
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(519, sSheetName, sWorkbook, "Deal");

                //function to switch currency toggle
                dealManagementPageEvents.DealInformationDivisionFinancialSummarySwitchToggle();

                //Function To Verify Division financial summary for division currency
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(519, sSheetName, sWorkbook, "Division");


                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(525, sSheetName, sWorkbook); //133

                //verification of deal information 
                dealManagementPageEvents.FnDealInformationVerification(529, sSheetName, sWorkbook); //135

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();


                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "INDPM");
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard");


                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard");

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "India Pricing Manager", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function To verify Approval status for SPM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("APPROVED");

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();
                //65 Pending = Click on View and Edit pricing button = The status of the price items should be displayed of the previous approver.	The price item should in 'error' status

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_021", "APPROVED"); //142
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_022", "APPROVED"); //142
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_024", "APPROVED"); //142
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_025", "APPROVED"); //142
                //Error -> not showing status for other reporting/swift price items i.e cpivfn_046 to CPICFN_052

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function to navigate Pricing and commitment edit 
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_022", "APPROVED"); //144

                dealManagementPageEvents.FnVerifyStatusOfSeasonalPriceItemOnPricingAndCommitment("CC_022", "APPROVED", 1); //144
                //error 
                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation(); //145


                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "INDSPM"); //146
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //147

                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard"); //148

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "India Senior Pricing Manager", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //150

                //Function To verify Approval status for SPM on View And Edit Pricing
                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("APPROVED"); //151

                dealManagementPageEvents.FnNavigateToViewAndEditPricing();
                //65 Pending = Click on View and Edit pricing button = The status of the price items should be displayed of the previous approver.	The price item should in 'error' status

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_022", "APPROVED"); //152
                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_024", "APPROVED"); //152

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function to navigate Pricing and commitment edit 
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                dealManagementPageEvents.FnVerifyStatusOfPriceItemOnCommitment("CC_024", "APPROVED"); //154

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("APPROVED"); //155

                //Function To Send deal for approval 
                dealManagementPageEvents.FnApproveDealFromDealInformation(); //156

                // Navigate to "Deal Approver Dashboard"
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //157

                //Function To Search for deal ID created which is not longer assign to logged in user
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(DealId, true, false, false, "INDSPM"); //157

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard(); //158

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Pricing Manager 1", "Unapproved");

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow(); //158

                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "PMBK1"); //159
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //160

                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard"); //161

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Pricing Manager 1", "Unapproved"); //162

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //150

                //Function To verify Approval status for Person Hierarchy Financial Summary 
                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("APPROVED"); //164

                //Function To verify Approval status for Division Financial Summary 
                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("APPROVED"); //164

                // ->  (price item status not exists for reporting/swift priceitems)   164 = Click on View and Edit pricing button	The status of the price items should be displayed of the previous approver.
                //Error

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Send deal for approval 
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation(); //166


                //Navigate to Deal Approver Dashboard Through INDPM user
                AF.FnLoginChange(getDriver(), "SPMBK1"); //167
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //168

                //Function To Verify Deal ToDo Type
                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Approver_Dashboard"); //169

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId);

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Pending For Approval", "Senior Pricing Manager 1", "Unapproved"); //170

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //171

                //Function To verify Approval status for Person Hierarchy Financial Summary 
                dealManagementPageEvents.FnVerifyCustomerStatusOnPersonalhierarchy("APPROVED"); //172

                //Function To verify Approval status for Division Financial Summary 
                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("APPROVED"); //172

                //Function To verify Approval status for Deal Financial Summary 
                dealManagementPageEvents.FnVerifyDealFinancialSummaryStatus("APPROVED"); //172
                //not showing approval status


                // -> (Price item status not exists i,e empty status for Reporting/swift CPIVFN_045 like price items) Testcase Excel line no :-172,	Click on View and Edit pricing button,	The status of the price items should be displayed of the previous approver.
                //Error ->173

                //To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage(); //174



                //Function To verify Approval status for Division Financial Summary 
                dealManagementPageEvents.FnVerifydivisionFinancialSummaryStatus("APPROVED"); //176

                //Function To verify Approval status for Deal Financial Summary 
                //dealManagementPageEvents.FnVerifyDealFinancialSummaryStatus("APPROVED"); //176

                //Function To Send deal for approval 
                dealManagementPageEvents.FnApproveDealFromDealInformation(); //177


                AF.FnLoginChange(getDriver(), "RMBK1"); //178
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //179

                //Function To Verify Deal ToDo Type
//                dealManagementPageEvents.FnVerifyDealToDoType(DealId, "Deal_Dashboard"); //179

                //Function To Search for deal ID created 
                dealManagementPageEvents.FnSearchDealIdFromDealDashboard(DealId); //180

                //Function To navigate to deal Approval Workflow page from deal approval dashboard
                dealManagementPageEvents.FnNavigationToDealApprovalWorkflowFromDealApprovalDashboard();

                //Function for View and verify Approval Workflow of deal 
                dealManagementPageEvents.FnViewAndverifyDealApprovalWorkflowForRecentTODOROLE("Approved", "Relationship Manager 1", "Returned Deal to Submitter as Approved"); //181

                //Function To navigate to deal approval dashboard from deal Approval Workflow page 
                dealManagementPageEvents.FnNavigationToDealApprovalDashboardFromDealApprovalWorkflow();

                //Function To navigate to deal information from deal approval dashboard page 
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //182

                //Function To Finalize Deal 
                dealManagementPageEvents.FnFinalizeDealFromDealInformation();

                //Function To Accept Deal 
                dealManagementPageEvents.FnAcceptDealFromDealInformation(533, sSheetName, sWorkbook);


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }


    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		Nogotiability
     * Script Description			: 		Nogotiability
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Negotiability_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(enabled = true, priority = 23, groups = {
        "sanity testing"
    })
    public void DM_04() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DM_04";
                BaseTest.sScriptName = "DM_04";
                BaseTest.sOTMTestcaseID = "39544";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DM_Automation_Negotiability_Module.xlsx";
                String sSheetName = "TestDataSheet";
                BaseTest.sTestDescription = "Deal Management verifications for Negotiability";

                Boolean StartDealFromSearchDealId = false;

                String sDealIDCreatedAfterDealCreation = "8811270919"; //for Test Resume [5218241303]



                if (StartDealFromSearchDealId == false) {
                    //Search entity 
                    dealManagementPageEvents.FnSearchEntity(103, sSheetName, sWorkbook);

                    //Deal Creation 
                    sDealIDCreatedAfterDealCreation = dealManagementPageEvents.FnDealCreation(107, sSheetName, sWorkbook);

                    System.out.println("deal id:-" + sDealIDCreatedAfterDealCreation);

                    //Navigation to Deal Information UI from deal creation page
                    dealManagementPageEvents.FnNavigationToDealInformationFromDealCreation();


                    //verification of deal information 
                    dealManagementPageEvents.FnDealInformationVerification(111, sSheetName, sWorkbook);

                    // price item selection line no 21
                    //Search filter options on Select Prize Item group
                    dealManagementPageEvents.FnSearchAndSelectPrizeItemgroup(115, sSheetName, sWorkbook);
                    dealManagementPageEvents.FnSearchAndSelectPrizeItemgroup(116, sSheetName, sWorkbook);

                } else if (StartDealFromSearchDealId == true) {

                    dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation); //for Test Resume
                    dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard(); //for Test Resume


                }

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                
                //NPI_021
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(120, sSheetName, sWorkbook);

                //NPI_021
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(131, sSheetName, sWorkbook);

                //NPI_032
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(121, sSheetName, sWorkbook); //rate 15
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(122, sSheetName, sWorkbook); //rate 10.99
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(123, sSheetName, sWorkbook); //rate 11
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(124, sSheetName, sWorkbook); //rate 15
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(125, sSheetName, sWorkbook); //rate 20.12
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(126, sSheetName, sWorkbook); //rate 16

                //NPI_029
                dealManagementPageEvents.FnVerifyPriceItemsRatesOnPricingAndCommitmentScreen(127, sSheetName, sWorkbook);

                //NPI_035
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(132, sSheetName, sWorkbook);

                //NPI_036
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(133, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(134, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(135, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(136, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(137, sSheetName, sWorkbook);

                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(139, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(140, sSheetName, sWorkbook);

                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(141, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(142, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPriceItemValidationOnPricingScreen(143, sSheetName, sWorkbook);


                //Function to Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments(); //28

                //Function To simulate deal and verify deal simulated successfully
                dealManagementPageEvents.FnDealSimulationOnDealInformationPage();

                //Function To Send deal for approval
                dealManagementPageEvents.FnSendDealForApprovalFromDealInformation();


                //Navigate to Deal Approver Dashboard Through PMBK1 user
                AF.FnLoginChange(getDriver(), "PMBK1"); //84
                AF.FnNavigation(getDriver(), "Deal Approver Dashboard"); //85

                //Function To Search for deal ID created
                //				dealManagementPageEvents.FnSearchDealIdFromDealDashboard(sDealIDCreatedAfterDealCreation); //for Test Resume
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealIDCreatedAfterDealCreation, false, false, false, "PMBK1");

                //Function To navigate to deal information from deal approval dashboard page
                dealManagementPageEvents.FnNavigationToDealInformationFromDealApprovalDashboard();

                //Function to navigate Pricing & Commitment Screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Recommend NPI_021
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemValidationOnPricingScreen(147, sSheetName, sWorkbook);

                //Recommend NPI_030
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemValidationOnPricingScreen(148, sSheetName, sWorkbook);
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemValidationOnPricingScreen(149, sSheetName, sWorkbook);
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemValidationOnPricingScreen(150, sSheetName, sWorkbook);

                //Recommend from Pricing screen NPI_030
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(154, sSheetName, sWorkbook);
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(155, sSheetName, sWorkbook);
                dealManagementPageEvents.FnRecommendAndVerifyPriceItemsRatesOnPricingAndCommitmentScreen(156, sSheetName, sWorkbook);


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }




    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43706
     * Script Description			: 		BANK_82_6_Test_43706 ( Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as false thus verify Prospect Person should not get shown for deal )
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		TestCase_BANK_82_6.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 5, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43706() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43706";
                BaseTest.sScriptName = "BANK_82_6_Test_43706";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as false thus verify Prospect Person should not get shown for deal";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");



                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43706.xlsx";
                String sSheetName = "TEST_DATA";
              
                String ExistingPersonId = CF.FnGetCellValue(85, 2, sSheetName, sWorkbook).toString().trim();

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");


                //To Update Prospect Person As Child of Existing Customer
                String UpdateProspectAsChild = "{\"C1-ADDPROSPERSONREST\":{\"prospectPersonId\":\"2049157035\",\"actionFlag\":\"UPD\",\"prospectPersonName\":\"BANK_82_PRSPCH1_001\",\"division\":\"IND\",\"customerSegment\":\"COMM\",\"customerTier\":\"T\",\"accessGroup\":\"***\",\"emailAddress\":\"BANK_82_PRSPCH1_001@oracle.com\",\"status\":\"ACTIVE\",\"birthDate\":\"1994-03-21\",\"address1\":\"addr1BANK_82_PRSPCH1_001\",\"prosPerIdentifierList\":{\"personIdNumber\":\"Reg_BANK_82_PRSPCH1_001\",\"persIsPrimaryId\":\"true\",\"idType\":\"COREG\"},\"prosChildRelationList\":{\"childPersonId\":\"3002038115\",\"personRelationshipType\":\"CHILD\",\"childPerType\":\"PRSP\",\"startDate\":\"2020-01-01\"},\"prosParentPerRelationList\":{\"parentPersonId\":\"8797366188\",\"personRelationshipType\":\"CHILD\",\"parentPerType\":\"EPER\",\"startDate\":\"2020-01-01\"},\"prosAccountList\":{\"prospectAccountId\":\"8043703981\",\"customerClass\":\"DM-CORP\",\"accountDivision\":\"IND\",\"status\":\"ACTIVE\",\"setUpDate\":\"2020-01-01\",\"currency\":\"USD\",\"billCycle\":\"BKEM\",\"accountAccessGroup\":\"***\",\"prosAcctIdentifierList\":{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":\"EAI_BANK_82_PRSPCH1_001\"}}}}";
                WF.FnPostRequestByString(sCreateDealProspectResource, UpdateProspectAsChild, sContentTypeHeader, sAcceptTypeHeader);

                String sTotalProspectAccount = (String) DB.FnGetDBColumnValue("SELECT COUNT(*) FROM C1_PRS_ACCT_PER WHERE per_id = '" + ExistingPersonId + "'", "COUNT(*)", System.getProperty("dbName"), System.getProperty("dbUserName"), System.getProperty("dbPassword"), System.getProperty("dbMachineIP"), System.getProperty("dbPort"));

                System.out.println("sTotalProspectAccount:-" + sTotalProspectAccount);

                if (sTotalProspectAccount.equalsIgnoreCase("0")) {
                    //// To Add Propsect Account to Existing Person
                    String AddProspectAccountToEPERJsonRequest = "{\"C1-ProspectAccountREST\":{\"actionFlag\":\"ADD\",\"mainEntityId\":\"" + ExistingPersonId + "\",\"mainEntityType\":\"EPER\",\"status\":\"ACTIVE\",\"customerClass\":\"DM-CORP\",\"accountDivision\":\"IND\",\"setUpDate\":\"2020-01-01\",\"currency\":\"USD\",\"accountAccessGroup\":\"***\",\"prosAcctCharacteristicList\":[{\"characteristicValue\":\"MH\",\"effectiveDate\":\"2021-04-04\",\"characteristicType\":\"VG_STATE\"}],\"prosAcctIdentifierList\":[{\"accountIdentifierType\":\"C1_F_ANO\",\"isPrimaryId\":\"true\",\"accountNumber\":\"EAI_BANK_82_EPPRSP_001\"},{\"accountIdentifierType\":\"ACCT_NM\",\"isPrimaryId\":\"false\",\"accountNumber\":\"AN_BANK_82_EPPRSP_001\"}]}}";
                    WF.FnPostRequestByString(sCreateDealProspectAccountResource, AddProspectAccountToEPERJsonRequest, sContentTypeHeader, sAcceptTypeHeader);
                }
                
                //Added on QADATA
//                DB.FnUpdateBillableChargeDates(70, sSheetName, sWorkbook);
//                DB.FnUpdateBillableChargeDates(71, sSheetName, sWorkbook);
//                DB.FnUpdateBillableChargeDates(72, sSheetName, sWorkbook);
//                DB.FnUpdateBillableChargeDates(73, sSheetName, sWorkbook);
//                DB.FnUpdateBillableChargeDates(74, sSheetName, sWorkbook);

                dealManagementPageEvents.FnAddBillableCharge(128, sSheetName, sWorkbook);
                dealManagementPageEvents.FnAddBillableCharge(129, sSheetName, sWorkbook);
                dealManagementPageEvents.FnAddBillableCharge(130, sSheetName, sWorkbook);
                dealManagementPageEvents.FnAddBillableCharge(131, sSheetName, sWorkbook);
                dealManagementPageEvents.FnAddBillableCharge(132, sSheetName, sWorkbook);


                String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43706";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                String sDealId, smodelId = "";

                //DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"Deal_BANK_82_7_001 desc\",\"dealVersionDescription\":\"Deal_BANK_82_7_001 desc ver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                //System.out.println("BANK_82_6_Test_43706 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(89, sSheetName, sWorkbook, sDealId, smodelId);
                //dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(90, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                System.out.println("Request ->" + sDealPSELSELECT);
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test

                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sSimulateDeal);
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(96, sSheetName, sWorkbook, smodelId);


                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(96, sSheetName, sWorkbook, smodelId);


                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(102, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information for "Customer Division / Account Currency"
                dealManagementPageEvents.DealInformationPersonHierarchySwitchToggle();


                //Function To verify Division Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(102, sSheetName, sWorkbook);


                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(110, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(114, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(118, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(119, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(120, sSheetName, sWorkbook); //issue in assert sCreationDateTime


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43707
     * Script Description			: 		BANK_82_6_Test_43707 ( Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as true thus verify Prospect Person should get shown for deal )
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 6, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43707() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43707";
                BaseTest.sScriptName = "BANK_82_6_Test_43707";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as true thus verify Prospect Person should get shown for deal";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43707_V1.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

				String sStartDate = CF.FnGetCellValue(85, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(85, 8, sSheetName, sWorkbook).toString().trim();
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43707";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"8797366188\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_6_Test_43707 desc\",\"dealVersionDescription\":\"BANK_82_6_Test_43707 desc ver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(89, sSheetName, sWorkbook, sDealId, smodelId);
                //dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(90, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_027\",\"priceItemInfo\":\"PI_027\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                System.out.println("Request ->" + sDealPSELSELECT);
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sSimulateDeal);
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

                AF.FnNavigation(getDriver(), "Deal Dashboard"); 
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(96, sSheetName, sWorkbook, smodelId);


                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(96, sSheetName, sWorkbook, smodelId);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(102, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information for "Customer Division / Account Currency"
                dealManagementPageEvents.DealInformationPersonHierarchySwitchToggle();

                //Function To verify Division Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(102, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(110, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(114, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(118, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(119, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(120, sSheetName, sWorkbook); //issue in assert sCreationDateTime    


                //TC_PRICING_OVRD
                DM.FnPricingAndCommitmentIWS(124, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(125, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(130, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(131, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(136, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(137, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(142, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(143, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


                DM.FnPricingAndCommitmentIWS(148, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(149, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(151, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(152, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(156, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(157, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(161, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(162, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(166, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(167, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To Navigate to Adhoc Revenue and cost UI from Deal information page
                dealManagementPageEvents.FnNavigationToAdhocRevenueAndCostCreationFromDealInformation();

                //Function To create Adhoc Revenue and cost 
                dealManagementPageEvents.FnCreationOfAdhocRevenueAndCost(172, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(181, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(187, sSheetName, sWorkbook);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(194, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(194, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(194, sSheetName, sWorkbook, smodelId);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(198, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function to Recommend Pricing                 
                DM.FnPricingAndCommitmentIWS(202, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");


                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal5 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal5, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitter = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitter, sContentTypeHeader, sAcceptTypeHeader);
                
                // Login Change to RMBK1 User
                WF.FnUserChange("RMBK1");

                // Function to Update Recommended Pricing on RM Level 
                DM.FnPricingAndCommitmentIWS(203, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal51 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal51, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(209, sSheetName, sWorkbook);

                String sSendDealForApproval11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval11, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to PMBK1 User
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal7 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal7, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromPM11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromPM11, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to SPMBK1 User
                WF.FnUserChange("SPMBK1");

                //Function To Simulate Deal
                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Approve Deal
                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Change Login
                WF.FnUserChange("RMBK1");

                //Function To Finalize Deal
                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Customer Accept Deal
                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(221, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(227, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(233, sSheetName, sWorkbook); //127

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(237, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(238, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(239, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(240, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(241, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(242, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(243, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(244, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(245, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(246, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(247, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(248, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(249, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(250, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(251, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(252, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(253, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(254, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(255, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(256, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(257, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(258, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(259, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(260, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(261, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(262, sSheetName, sWorkbook);


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43784
     * Script Description			: 		BANK_82_6_Test_43784 ( Verify Bank_82_6 for Reference deal with Refer Pricing and Usage) ,dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 7, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43784() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43784";
                BaseTest.sScriptName = "BANK_82_6_Test_43784";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Verify Bank_82_6 for Reference deal with Refer Pricing and Usage";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43784.xlsx";
                String sSheetName = "TEST_DATA";


                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String ExistingPersonId = CF.FnGetCellValue(85, 2, sSheetName, sWorkbook).toString().trim();
				String sStartDate = CF.FnGetCellValue(85, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(85, 8, sSheetName, sWorkbook).toString().trim();
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43784";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                String sDealId, smodelId = "";


                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_6_Test_43784 desc\",\"dealVersionDescription\":\"BANK_82_6_Test_43784\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                System.out.println("Request ->" + sDealPSELSELECT);
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //Navigate to Deal
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);


                //Function to verify Deal Proposed SQI & Pricing Details from DB Table
                DB.FnVerifyPricingAndCommitmentDetails(94, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(98, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(102, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(106, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(114, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(118, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(126, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(132, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(136, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(140, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(154, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(158, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(162, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(166, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(174, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(185, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(185, sSheetName, sWorkbook, smodelId);


                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(191, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information for "Customer Division / Account Currency"
                dealManagementPageEvents.DealInformationPersonHierarchySwitchToggle();

                //Function To verify Division Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(191, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(199, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(203, sSheetName, sWorkbook);


                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(207, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(208, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(209, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(210, sSheetName, sWorkbook);


                DM.FnPricingAndCommitmentIWS(214, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(215, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(217, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(218, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(220, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(221, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Simulation-2 by RM
                String sSimulateDealByRM2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(227, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);


                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByPM1, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("SPMBK1");

                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(231, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(237, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(243, sSheetName, sWorkbook); //127



            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43785
     * Script Description			: 		BANK_82_6_Test_43785 ( Verify Bank_82_6 for Reference Person with Refer Pricing and Usage),dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 8, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43785() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43785";
                BaseTest.sScriptName = "BANK_82_6_Test_43785";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Verify Bank_82_6 for Reference Person with Refer Pricing and Usage";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43785.xlsx";
                String sSheetName = "TEST_DATA";

                DB.FnUpdateBillableChargeDates(277, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(278, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String ExistingPersonId = CF.FnGetCellValue(85, 2, sSheetName, sWorkbook).toString().trim();
				String sStartDate = CF.FnGetCellValue(85, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(85, 8, sSheetName, sWorkbook).toString().trim();
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43785";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_6_Test_43785 desc\",\"dealVersionDescription\":\"BANK_82_6_Test_43785 Ver Desc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(89, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                System.out.println("Request ->" + sDealPSELSELECT);
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //Navigate to Deal
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);


                //Function to verify Deal Proposed SQI & Pricing Details from DB Table
                DB.FnVerifyPricingAndCommitmentDetails(94, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(98, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(102, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(106, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(114, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(118, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(126, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(132, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(136, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(140, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(154, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(158, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(162, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(166, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(174, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(185, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(185, sSheetName, sWorkbook, smodelId);


                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(191, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information for "Customer Division / Account Currency"
                dealManagementPageEvents.DealInformationPersonHierarchySwitchToggle();

                //Function To verify Division Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformationForDivisionCurrency(191, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(199, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(203, sSheetName, sWorkbook);


                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(207, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(208, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(209, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(210, sSheetName, sWorkbook);


                DM.FnPricingAndCommitmentIWS(214, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(215, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(217, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(218, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(220, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(221, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(224, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(225, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(230, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(231, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(236, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(237, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(242, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(243, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(247, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(248, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Simulation-2 by RM
                String sSimulateDealByRM2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(255, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByPM1, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("SPMBK1");

                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(259, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(265, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(271, sSheetName, sWorkbook); //127



            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }






    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43805
     * Script Description			: 		BANK_82_6_Test_43805 ( Verify Seasonal Pricing for Bank_82_7 ) ,dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 9, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43805() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43805";
                BaseTest.sScriptName = "BANK_82_6_Test_43805";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Verify Seasonal Pricing for Bank_82_7";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43805_V1.xlsx";
                String sSheetName = "TEST_DATA";


                // To Change user for sending new request
                WF.FnUserChange("RMBK1");


                String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
                //System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43805";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;
 

                String sDealId, smodelId;

                //DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifierType\":\"COREG\",\"prsPerIdentifier\":\"Reg_BANK_82_PRSPCH1_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_6_Test_43805 desc\",\"dealVersionDescription\":\"BANK_82_6_Test_43805 desc ver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(89, sSheetName, sWorkbook, sDealId, smodelId);
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(90, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                System.out.println("Request ->" + sDealPSELSELECT);
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //Navigate to Deal
                AF.FnNavigation(getDriver(), "Deal Dashboard"); 
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(96, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(96, sSheetName, sWorkbook, smodelId);

                //Function to verify Deal Proposed SQI & Pricing Details from DB Table
                DB.FnVerifyPricingAndCommitmentDetails(100, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(104, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(108, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(112, sSheetName, sWorkbook, smodelId);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(121, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(130, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(134, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(138, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(139, sSheetName, sWorkbook); //issue in assert sCreationDateTime
                dealManagementPageEvents.FnVerifyDealLogs(140, sSheetName, sWorkbook); //issue in assert sCreationDateTime    


                //TC_PRICING_OVRD
                DM.FnPricingAndCommitmentIWS(144, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(145, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(150, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(151, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(156, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(157, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(162, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(163, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(168, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(169, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(171, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(172, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(176, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(177, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(181, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(182, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function To Navigate to Adhoc Revenue and cost UI from Deal information page
                dealManagementPageEvents.FnNavigationToAdhocRevenueAndCostCreationFromDealInformation();

                //Function To create Adhoc Revenue and cost 
                dealManagementPageEvents.FnCreationOfAdhocRevenueAndCost(187, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(196, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(202, sSheetName, sWorkbook);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(210, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(210, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(210, sSheetName, sWorkbook, smodelId);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(214, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                DM.FnPricingAndCommitmentIWS(218, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                String sSimulateDeal5 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal5, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitter = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitter, sContentTypeHeader, sAcceptTypeHeader);
                
                //Login Change
                WF.FnUserChange("RMBK1");

                DM.FnPricingAndCommitmentIWS(219, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                String sSimulateDeal6 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal6, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(225, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(231, sSheetName, sWorkbook); ////Issue - deal_mangement.BANK_82_6_Test_43805:2581 expected [$57266.36] but found [$57226.36]

                String sSendDealForApproval11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval11, sContentTypeHeader, sAcceptTypeHeader);


                WF.FnUserChange("PMBK1");

                String sSimulateDeal7 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal7, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromPM11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromPM11, sContentTypeHeader, sAcceptTypeHeader);


                WF.FnUserChange("SPMBK1");

                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(238, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(244, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(250, sSheetName, sWorkbook); //127

            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_6_Test_43808
     * Script Description			: 		BANK_82_6_Test_43808 (Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as false) ,dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 10, groups = {
        "sanity testing"
    })
    public void BANK_82_6_Test_43808() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_6_Test_43808";
                BaseTest.sScriptName = "BANK_82_6_Test_43808";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Add Propsect Person as child of Existing Customer and keep includeHierarchyFlag as false";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_6_Test_43808.xlsx";
                String sSheetName = "TEST_DATA";

                String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
                //System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_6_Test_43808";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifierType\":\"COREG\",\"prsPerIdentifier\":\"Reg_BANK_82_PRSPCH1_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_6_Test_43808 desc\",\"dealVersionDescription\":\"BANK_82_6_Test_43808 desc ver\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                String sErrorMessageIWS = DealDetails.get("sErrorMessageIWS");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sErrorMessageIWS);

            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }



    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_Test_43810
     * Script Description			: 		BANK_82_Test_43810 ( Verify Bank_82_7 for Reference deal with Refer Pricing and Usage) ,dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 11, groups = {
        "sanity testing"
    })
    public void BANK_82_Test_43810() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_Test_43810";
                BaseTest.sScriptName = "BANK_82_Test_43810";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Verify Bank_82 for Reference deal with Refer Pricing and Usage";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_Test_43810_V1.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");


                String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
                //System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_Test_43810";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                String sDealId, smodelId = "";


                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifierType\":\"COREG\",\"prsPerIdentifier\":\"Reg_BANK_82_PRSPCH1_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_Test_43810 desc\",\"dealVersionDescription\":\"BANK_82_Test_43810\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //Navigate to Deal
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);


                //Function to verify Deal Proposed SQI & Pricing Details from DB Table
                DB.FnVerifyPricingAndCommitmentDetails(94, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(98, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(102, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(106, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(114, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(118, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(126, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(132, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(136, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(140, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(154, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(158, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(162, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(166, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(174, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(185, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(185, sSheetName, sWorkbook, smodelId);


                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());
                
                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(191, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(200, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(204, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(208, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(209, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(210, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(211, sSheetName, sWorkbook);

                //Function To Override Pricing
                DM.FnPricingAndCommitmentIWS(215, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(216, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(218, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(219, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(221, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(222, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Simulation-2 by RM
                String sSimulateDealByRM2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(228, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByPM1, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("SPMBK1");

                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Refresh Deal Information Screen
                dealManagementPageEvents.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(232, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(238, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(244, sSheetName, sWorkbook); //127



            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }






    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		BANK_82_Test_43811
     * Script Description			: 		BANK_82_Test_43811 ( Verify Bank_82_7 for Reference Person with Refer Pricing and Usage) ,dependsOnMethods = { "BANK_82_6_Test_43706" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 12, groups = {
        "sanity testing"
    })
    public void BANK_82_Test_43811() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "BANK_82_Test_43811";
                BaseTest.sScriptName = "BANK_82_Test_43811";
                BaseTest.sOTMTestcaseID = "";
                BaseTest.sTestDescription = "Verify Bank_82_7 for Reference Person with Refer Pricing and Usage";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));
                AF.FnLoginChange(getDriver(), "RMBK1");


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/BANK_82_Test_43811_V1.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");


                String sStartDate = CF.FnGetCurrentDateInSpcificFormat("yyyy-MM-dd");
                //System.out.println("Deal Start date used for Deal creation Is-> " + sStartDate);
                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier = "BANK_82_Test_43811";
                sDealIdentifier = sDealIdentifier + "_" + sDateName;

                String sDealId, smodelId = "";


                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"prsPerIdentifierType\":\"COREG\",\"prsPerIdentifier\":\"Reg_BANK_82_PRSPCH1_001\",\"division\":\"IND\"},\"dealIdentifier\":\"" + sDealIdentifier + "\",\"dealEntityType\":\"PRSP\",\"contractedDealFlag\":\"false\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"reviewFrequency\":\"12\",\"dealDescription\":\"BANK_82_Test_43811 desc\",\"dealVersionDescription\":\"BANK_82_Test_43811 Ver Desc\",\"dealFrequency\":\"12\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sStartDate + "\",\"usagePeriod\":\"12\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                //System.out.println("TC_PRICING_RECM_Test_43660 Request ->"+sDealCreation);
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();

                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println(sDealIdentifier+" Created Deal ID Is-> " + DealDetails);			

                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(85, sSheetName, sWorkbook, sDealId);


                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(89, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(85, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(85, sSheetName, sWorkbook, smodelId);

                //Function to verify Deal Proposed SQI & Pricing Details from DB Table
                DB.FnVerifyPricingAndCommitmentDetails(94, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(98, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(102, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(106, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(114, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(118, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(126, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(132, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(136, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(140, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(154, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(158, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(162, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(166, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(174, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Simulation Summary Details from DB Table
                DB.Deal_Simulation_Summary(185, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(185, sSheetName, sWorkbook, smodelId);

                //Function to Navigate Deal Dashboard
                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test

                //Function to search Deal and nivate to deal information screen
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1"); 
                
                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(191, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(200, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(204, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(208, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(209, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(210, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(211, sSheetName, sWorkbook);

                DM.FnPricingAndCommitmentIWS(215, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(216, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(218, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(219, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(221, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(222, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(225, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(226, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(231, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(232, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(237, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(238, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(243, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(244, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                DM.FnPricingAndCommitmentIWS(248, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");
                DM.FnPricingAndCommitmentIWS(249, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Simulation-2 by RM
                String sSimulateDealByRM2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(256, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByPM1, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("SPMBK1");

                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + sStartDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(260, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(266, sSheetName, sWorkbook);

                //Function To verify Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(272, sSheetName, sWorkbook); //127



            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }





    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39944
     * Script Description			: 		DealManagement_Test_39944 ( Verify deal simulation for Existing Customer , simulation type Deal and Skip Reference, Verify recommended pricing with parallel level approval, Verify the deal approval for parallel level approvers,Verify the flag of deal type code: Highest Level Approval Required-Yes )
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 14, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39944() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39944";
                BaseTest.sScriptName = "DealManagement_Test_39944";
                BaseTest.sOTMTestcaseID = "39944 39546 38724 38688";
                BaseTest.sTestDescription = "Verify deal simulation for Existing Customer , simulation type Deal and Skip Reference, Verify recommended pricing with parallel level approval, Verify the deal approval for parallel level approvers,Verify the flag of deal type code: Highest Level Approval Required-Yes";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39944.xlsx";
                String sSheetName = "TEST_DATA";


              dealManagementPageEvents.FnAddBillableCharge(288, sSheetName, sWorkbook);
              dealManagementPageEvents.FnAddBillableCharge(289, sSheetName, sWorkbook);
              dealManagementPageEvents.FnAddBillableCharge(290, sSheetName, sWorkbook);
              dealManagementPageEvents.FnAddBillableCharge(291, sSheetName, sWorkbook);
              dealManagementPageEvents.FnAddBillableCharge(292, sSheetName, sWorkbook);

                
                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(86, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(86, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(86, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(86, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(86, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(86, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(86, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(86, 11, sSheetName, sWorkbook).toString().trim();


                String sDealId, smodelId, sDealIdentifier = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"Deal_Management_Test_39944 Desc\",\"dealVersionDescription\":\"Deal_Management_Test_39944 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

//                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"Deal_Management_Test_39944 Desc\",\"dealVersionDescription\":\"Deal_Management_Test_39944 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";
                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(87, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_031\",\"priceItemInfo\":\"NPI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_037\",\"priceItemInfo\":\"NPI_037\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(86, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(86, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(86, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(97, sSheetName, sWorkbook, smodelId);


                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(103, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(109, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(113, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(90, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD1 = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD1, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";

                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT1, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(118, sSheetName, sWorkbook, sDealId);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(118, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(118, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(118, sSheetName, sWorkbook, smodelId);



                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(124, sSheetName, sWorkbook, smodelId);

                //Function To Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(130, sSheetName, sWorkbook);


                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(136, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(140, sSheetName, sWorkbook);

                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(144, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(145, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(146, sSheetName, sWorkbook);

                // Function To Verify Pricing And Commitment Details From DB Table
                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(156, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(163, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(177, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(184, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(191, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(198, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(202, sSheetName, sWorkbook, smodelId);

                //Function To Override,Update,Delete,Add Seasonal Pricing Using IWS
                //PI_021
                DM.FnPricingAndCommitmentIWS(203, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(204, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(151, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(152, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(208, sSheetName, sWorkbook);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(212, sSheetName, sWorkbook);


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(218, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(224, sSheetName, sWorkbook);


                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(228, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function to Recommend Pricing                 
                DM.FnPricingAndCommitmentIWS(157, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Recommend Pricing                 
                DM.FnPricingAndCommitmentIWS(164, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                String sSimulateDeal5 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal5, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitter = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitter, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to RMBK1 User
                WF.FnUserChange("RMBK1");

                // Function to Update Recommended Pricing on RM Level 
                DM.FnPricingAndCommitmentIWS(158, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                // Function to Update Recommended Pricing on RM Level 
                DM.FnPricingAndCommitmentIWS(165, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                // Function to Simulate Deal
                DM.FnSimulateDealByRequest(sSimulateDeal5, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());


                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(234, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApproval33 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApproval33, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to PMBK1 User
                WF.FnUserChange("PMBK1");

                String sSimulateDeal7 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal7, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromPM11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromPM11, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to PMBK1 User
                WF.FnUserChange("PMBK2");

                String sSimulateDealByPMBK2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPMBK2, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromByPMBK2 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromByPMBK2, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to SPMBK1 User
                WF.FnUserChange("SPMBK1");

                //Function To Simulate Deal
                String sSimulateDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPM, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function to Recommend Pricing By SPMBK1 User              
                DM.FnPricingAndCommitmentIWS(171, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Recommend Pricing By SPMBK1 User              
                DM.FnPricingAndCommitmentIWS(178, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Recommend Pricing By SPMBK1 User              
                DM.FnPricingAndCommitmentIWS(185, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Recommend Pricing By SPMBK1 User              
                DM.FnPricingAndCommitmentIWS(192, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                String sSimulateDeal5BySPMBK1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal5BySPMBK1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitterBySPMBK1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitterBySPMBK1, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to SPMBK1 User
                WF.FnUserChange("RMBK1");

                //Function to Override Recommend Pricing By RMBK1 User // PI_028          
                DM.FnPricingAndCommitmentIWS(172, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Override Recommend Pricing By RMBK1 User              
                DM.FnPricingAndCommitmentIWS(179, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Override Recommend Pricing By RMBK1 User              
                DM.FnPricingAndCommitmentIWS(186, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //Function to Override Recommend Pricing By RMBK1 User              
                DM.FnPricingAndCommitmentIWS(193, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //################ Deal Simulation IWS ####################//
                String sSimulateDealByRMBK1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRMBK1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(240, sSheetName, sWorkbook);

                //Function to Navigate to view and edit pricing on Pricing and commitment screen
                dealManagementPageEvents.FnNavigateToViewAndEditPricing();

                //Function To Verify Project & Original Pricing and Commitment for Specific/Given Price item
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(246, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(247, sSheetName, sWorkbook);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();


                //Function to Override Update Pricing By RMBK1 User // PI_028          
                DM.FnPricingAndCommitmentIWS(248, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue");

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELUNSELECT1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_037\",\"priceItemInfo\":\"NPI_037\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"false\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELUNSELECT1, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to RMBK1 User
                WF.FnUserChange("RMBK1");

                // Function to Simulate Deal
                String sSimulateDealByRMBK11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRMBK11, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByRMBK11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRMBK11, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to PMBK1 User
                WF.FnUserChange("PMBK1");

                String sSimulateDealByPMBK11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPMBK11, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromPM1133 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromPM1133, sContentTypeHeader, sAcceptTypeHeader);

                // Login Change to PMBK1 User
                WF.FnUserChange("PMBK2");

                String sSimulateDealByPMBK211 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPMBK211, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                String sSendDealForApprovalFromByPMBK211 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalFromByPMBK211, sContentTypeHeader, sAcceptTypeHeader);


                // Login Change to SPMBK1 User
                WF.FnUserChange("SPMBK1");

                //Function To Simulate Deal
                String sSimulateDealBySPMBK11 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + smodelId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealBySPMBK11, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To Approve Deal
                String sApproveDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(266, sSheetName, sWorkbook);

                //Function To Change Login
                WF.FnUserChange("RMBK1");

                //Function To Finalize Deal
                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(270, sSheetName, sWorkbook);

                String CustomerAcceptanceEffectiveStartDate = CF.FnGetCellValue(252, 1, sSheetName, sWorkbook).toString().trim();
                String CustomerAcceptanceEffectiveEndDate = CF.FnGetCellValue(252, 2, sSheetName, sWorkbook).toString().trim();

                //Function To Customer Accept Deal
                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + CustomerAcceptanceEffectiveStartDate + "\",\"dealAcceptEffEndDate\":\"" + CustomerAcceptanceEffectiveEndDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(256, sSheetName, sWorkbook);


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(262, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(276, sSheetName, sWorkbook);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(281, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(281, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(281, sSheetName, sWorkbook, smodelId);



            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }





    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39945
     * Script Description			: 		DealManagement_Test_39945 ( Verify deal simulation for Existing Customer , simulation type Deal and Skip Reference, Verify recommended pricing with parallel level approval, Verify the deal approval for parallel level approvers,Verify the flag of deal type code: Highest Level Approval Required-NO ) ,dependsOnMethods = { "DealManagement_Test_39944" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 15, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39945() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39945";
                BaseTest.sScriptName = "DealManagement_Test_39945";
                BaseTest.sOTMTestcaseID = "38727 39945";
                BaseTest.sTestDescription = "Verify deal simulation for Existing Customer , simulation type deal and reference by another customer with refer usage and refer price both";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);


                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39945.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Login to the system
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(86, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(86, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(86, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(86, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(86, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(86, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(86, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(86, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"Deal_Management_Test_39945 Desc\",\"dealVersionDescription\":\"Deal_Management_Test_39945 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(86, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_031\",\"priceItemInfo\":\"NPI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_037\",\"priceItemInfo\":\"NPI_037\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_021\",\"priceItemInfo\":\"NPI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_022\",\"priceItemInfo\":\"NPI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_023\",\"priceItemInfo\":\"NPI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_024\",\"priceItemInfo\":\"NPI_024\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_025\",\"priceItemInfo\":\"NPI_025\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(86, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(86, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(86, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(97, sSheetName, sWorkbook, smodelId);


                AF.FnNavigation(getDriver(), "Deal Dashboard"); //for Test
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(103, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(109, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(113, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //################ Deal PriceList Assignment IWS ####################//
                //To Perform Operation On Deal i.e. Add , Update , Delete pricelist
                dealManagementPageEvents.FnDealPricelistAddUpdateDeleteIWS(90, sSheetName, sWorkbook, sDealId, smodelId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD1 = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD1, sContentTypeHeader, sAcceptTypeHeader);


                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT1, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(118, sSheetName, sWorkbook, sDealId);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(118, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(118, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(118, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(124, sSheetName, sWorkbook, smodelId);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(130, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(136, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(140, sSheetName, sWorkbook);

                DB.FnVerifyPricingAndCommitmentDetails(150, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(156, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(163, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(170, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(177, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(184, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(191, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(198, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(202, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(203, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(204, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(151, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(152, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function Refresh Deal on UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(208, sSheetName, sWorkbook);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(212, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(218, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(224, sSheetName, sWorkbook);

                String sDealPsel12 = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPsel12, sContentTypeHeader, sAcceptTypeHeader);

                String READResponse = BaseTest.respBody.asString();
                READResponse = READResponse.replaceAll("READ", "UPD");
                READResponse = READResponse.replaceAll("true", "false");
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, READResponse, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT12 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"false\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT12, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT3 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"NPI_037\",\"priceItemInfo\":\"NPI_037\",\"assignmentLevel\":\"CustomerPriceList\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=KA\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"NPI_036\",\"priceItemInfo\":\"NPI_036\",\"parameterInfo\":\"DM_COUNTRY=IND,DM_STATE=AP\",\"assignmentLevel\":\"CustomerAgreed\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT3, sContentTypeHeader, sAcceptTypeHeader);


                //simulate deal after selecting RM1 Assigned Price Items
                String sSimulateDeal22 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal22, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Approve Deal
                String sApproveDealByRM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealByRM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(228, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(238, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(244, sSheetName, sWorkbook);

                //Function To Discard Deal
                String sDiscardDealByRM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"DSCR\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sDiscardDealByRM, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(232, sSheetName, sWorkbook);


                //Function To verify Deal Logs 
                dealManagementPageEvents.FnVerifyDealLogs(248, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(249, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(250, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(251, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(252, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(253, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(254, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(255, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(256, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(257, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(258, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(259, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(260, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(261, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(262, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyDealLogs(263, sSheetName, sWorkbook);


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }








    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39767
     * Script Description			: 		DealManagement_Test_39767 ( Verify Stacking functionality for the case deal for existing Parent customer with skip reference )
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 16, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39767() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39767";
                BaseTest.sScriptName = "DealManagement_Test_39767";
                BaseTest.sOTMTestcaseID = "39767";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Parent customer with skip reference";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39767_V1.xlsx";
                String sSheetName = "TEST_DATA";

                //Function to update "Stacking Required" Option as Y
                dealManagementPageEvents.FnUpdateAlgorithmValue(163, sSheetName, sWorkbook);
                dealManagementPageEvents.FnUpdateAlgorithmValue(164, sSheetName, sWorkbook);


                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(90, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(90, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(90, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(90, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(90, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(90, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                Thread.sleep(3000);
                
                
                DB.FnUpdateBillableChargeDates(68, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(69, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(70, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(71, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(72, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(73, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(74, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(75, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(76, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(77, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(78, sSheetName, sWorkbook);
                DB.FnUpdateBillableChargeDates(79, sSheetName, sWorkbook);
                
                

                String sDealId, smodelId, sDealIdentifier = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"Deal_Management_Test_39944 Desc\",\"dealVersionDescription\":\"Deal_Management_Test_39944 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);

                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(101, sSheetName, sWorkbook, smodelId);

                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(107, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(113, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(117, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                DB.FnVerifyPricingAndCommitmentDetails(121, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(127, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(133, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(122, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(123, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(128, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(129, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(137, sSheetName, sWorkbook);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(141, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(147, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(153, sSheetName, sWorkbook);

                //Function To verify Division Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(159, sSheetName, sWorkbook, "Deal");

            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }




    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39771
     * Script Description			: 		DealManagement_Test_39771 ( Verify Stacking functionality for the case deal for existing Parent customer with reference another customer with combination of refer usage and refer price ) ,dependsOnMethods = { "DealManagement_Test_39767" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 17, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39771() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39771";
                BaseTest.sScriptName = "DealManagement_Test_39771";
                BaseTest.sOTMTestcaseID = "39771";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Parent customer with reference another customer with combination of refer usage and refer price";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39771.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(90, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(90, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(90, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(90, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(90, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(90, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"DealManagement_Test_39771 Desc\",\"dealVersionDescription\":\"DealManagement_Test_39771 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RPER\",\"referPersonId\":\"3091575921\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(101, sSheetName, sWorkbook, smodelId);

                //Function To Navigate to Deal Information Page
                AF.FnNavigation(getDriver(), "Deal Dashboard");
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(107, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(113, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(117, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                DB.FnVerifyPricingAndCommitmentDetails(121, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(127, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(133, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(122, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(123, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(128, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(129, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(137, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(141, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(147, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(153, sSheetName, sWorkbook);

                //Function To verify Division Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(160, sSheetName, sWorkbook, "Deal");


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }





    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39772
     * Script Description			: 		DealManagement_Test_39772 ( Verify Stacking functionality for the case deal for existing Parent customer with reference another deal with combination of refer usage and refer price ) ,dependsOnMethods = { "DealManagement_Test_39767" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 18, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39772() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39772";
                BaseTest.sScriptName = "DealManagement_Test_39772";
                BaseTest.sOTMTestcaseID = "39772";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Parent customer with reference another deal with combination of refer usage and refer price";

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39772.xlsx";
                String sSheetName = "TEST_DATA";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(90, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(90, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(90, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(90, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(90, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(90, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);

                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"DealManagement_Test_39772 Desc\",\"dealVersionDescription\":\"DealManagement_Test_39772 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"false\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"hierarchyFilterFlag\":\"WHEP\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referenceTypeFlg\":\"RDEL\",\"referenceDealId\":\"6739465360\",\"referUsageSw\":true,\"referPriceSw\":true,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(101, sSheetName, sWorkbook, smodelId);


//                String sDealId = "3792091403";
//                String smodelId = "4706737307";
                
                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");
                //Hit Deal Extract AP

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(107, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(113, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(117, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Verify Projected and Original Pricing
                DB.FnVerifyPricingAndCommitmentDetails(121, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(128, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(129, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(236, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(123, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(124, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(130, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(131, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(140, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(144, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(150, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(156, sSheetName, sWorkbook);

                //Function To verify Division Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(162, sSheetName, sWorkbook, "Deal");


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }




    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39773
     * Script Description			: 		DealManagement_Test_39773 ( Verify Stacking functionality for the case deal for existing Parent customer with skip reference )
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 19, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39773() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39773";
                BaseTest.sScriptName = "DealManagement_Test_39773";
                BaseTest.sOTMTestcaseID = "39767";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Parent customer with skip reference";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39773.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDealIdentifier1 = CF.FnGetCellValue(90, 3, sSheetName, sWorkbook).toString().trim();
                sDealIdentifier1 = sDealIdentifier1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(90, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(90, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(90, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(90, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(90, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);


                String sDealId, smodelId = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDealIdentifier1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"DealManagement_Test_39773 Desc\",\"dealVersionDescription\":\"DealManagement_Test_39773 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"includeHierarchyFlag\":\"false\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(101, sSheetName, sWorkbook, smodelId);


                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(107, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(113, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(117, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                DB.FnVerifyPricingAndCommitmentDetails(121, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(127, sSheetName, sWorkbook, smodelId);
                DB.FnVerifyPricingAndCommitmentDetails(133, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(122, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(123, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(128, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(129, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(137, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(141, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(147, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(153, sSheetName, sWorkbook);

                //Function To verify Division Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDivisionFinancialSummary(160, sSheetName, sWorkbook, "Deal");


            }

        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }







    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_39774
     * Script Description			: 		DealManagement_Test_39774 ( Verify Stacking functionality for the case deal for existing Parent customer with simulation type "customer" and different usage/deal frequency period ) ,dependsOnMethods = { "DealManagement_Test_39767" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 20, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_39774() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_39774";
                BaseTest.sScriptName = "DealManagement_Test_39774";
                BaseTest.sOTMTestcaseID = "39774";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Parent customer with simulation type customer and different usage/deal frequency period";

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_39774.xlsx";
                String sSheetName = "TEST_DATA";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDEAL_IDENTIFIER_1 = CF.FnGetCellValue(90, 3, sSheetName, sWorkbook).toString().trim();
                sDEAL_IDENTIFIER_1 = sDEAL_IDENTIFIER_1 + "_" + sDateName;

                String ExistingPersonId = CF.FnGetCellValue(90, 2, sSheetName, sWorkbook).toString().trim();
                String sDealType1 = CF.FnGetCellValue(90, 4, sSheetName, sWorkbook).toString().trim();
                String sSimulationType = CF.FnGetCellValue(90, 6, sSheetName, sWorkbook).toString().trim();
                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();
                String sDealFrequency = CF.FnGetCellValue(90, 10, sSheetName, sWorkbook).toString().trim();
                String sUsageFrequency = CF.FnGetCellValue(90, 11, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);


                String sDealId, smodelId, sModelId1, sDealIdentifier = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"" + ExistingPersonId + "\"},\"dealIdentifier\":\"" + sDEAL_IDENTIFIER_1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"" + sDealType1 + "\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"" + sSimulationType + "\",\"dealDescription\":\"DealManagement_Test_39774 Desc\",\"dealVersionDescription\":\"DealManagement_Test_39774 Ver Desc\",\"dealFrequency\":\"" + sDealFrequency + "\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"" + sUsageFrequency + "\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(107, sSheetName, sWorkbook, smodelId);

                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(101, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(107, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(111, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                // Verify Pricing & Commitments
                DB.FnVerifyPricingAndCommitmentDetails(115, sSheetName, sWorkbook, smodelId); //PI_024
                DB.FnVerifyPricingAndCommitmentDetails(116, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId); //PI_025
                DB.FnVerifyPricingAndCommitmentDetails(123, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(129, sSheetName, sWorkbook, smodelId); //PI_026
                DB.FnVerifyPricingAndCommitmentDetails(130, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(117, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(118, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(124, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(125, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(134, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(138, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(144, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(150, sSheetName, sWorkbook);
                
                //Function To Navigate to Pricing And Commitment Screen
                dealManagementPageEvents.FnNavigateToAccountViewAndEditPricing("External Account Identifier - EAI_STACKING_COMT_PARENT_ACC1");

                //Function to Verify RollUp Revenue & Cost
                dealManagementPageEvents.FnViewAndEditPricing(154, sSheetName, sWorkbook);

                //Function To Verify Project & Original Pricing and Commitment for Specific/Given Price item
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(157, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(158, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(160, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(161, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(163, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(174, sSheetName, sWorkbook);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To Create Deal Version
                dealManagementPageEvents.FnCreateDealVersionFromDealInformation(168, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(172, sSheetName, sWorkbook);

                //Function To Get Latest Model Id
                sModelId1 = DM.FnGetCurrentDealVersionFromDealInformation(getDriver());

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDealByRM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(176, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByRM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRM1, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(180, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //simulate deal after selecting INDPM Assigned Price Items
                String sSimulateDealByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPM1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function to Recommend Pricing By PMBK1 User              
                DM.FnPricingAndCommitmentIWS(184, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //simulate deal after selecting INDPM Assigned Price Items
                String sSimulateDealByPM1V1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitterBySPMBK1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitterBySPMBK1, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Function to Recommend Pricing By RMBK1 User              
                DM.FnPricingAndCommitmentIWS(185, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");
                DM.FnPricingAndCommitmentIWS(187, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");
                DM.FnPricingAndCommitmentIWS(189, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(193, sSheetName, sWorkbook);

                //simulate deal after selecting RMBK1 Assigned Price Items
                String sSimulateDealByRM1V1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(197, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(203, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(209, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByRMR1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRMR1, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(213, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //Function to Simulate Deal Using IWS By PMBK1 User
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRMR1, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("SPMBK1");

                //Function to Simulate Deal Using IWS By PMBK1 User
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Approve Deal
                String sApproveDealByRM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealByRM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(217, sSheetName, sWorkbook);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(221, sSheetName, sWorkbook);

                String CustomerAcceptanceEffectiveStartDate = CF.FnGetCellValue(225, 1, sSheetName, sWorkbook).toString().trim();
                String CustomerAcceptanceEffectiveEndDate = CF.FnGetCellValue(225, 2, sSheetName, sWorkbook).toString().trim();

                //Function To Customer Accept Deal
                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + CustomerAcceptanceEffectiveStartDate + "\",\"dealAcceptEffEndDate\":\"" + CustomerAcceptanceEffectiveEndDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(229, sSheetName, sWorkbook);



            }



        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }





    /***********************************************************************************************************************************************************
     * Written by					:   	Rohit Thik
     * Script Name					:  		DealManagement_Test_Stacking_39768
     * Script Description			: 		DealManagement_Test_Stacking_39768 ( Verify Stacking functionality for the case deal for existing Child customer with skip reference ) ,dependsOnMethods = { "DealManagement_Test_39767" }
     * Manual Test Scenario covered	: 		Yes
     * Date Created					:  		23-Feb-2022
     * Date Modified				: 		
     * Modified by					: 
     * Environment scripted in		: 		
     * ObjectRepository 			: 		ORMBObjectLibBanking.properties 
     * Datasheet name				: 		DM_Automation_Bank_82_Module_Version3.2_Testcases _Data_Creation.xlsx
     ************************************************************************************************************************************************************
     ************************************************************************************************************************************************************
     * Variable Definition
     ***********************************************************************************************************************************************************/
    @Test(priority = 21, groups = {
        "sanity testing"
    })
    public void DealManagement_Test_Stacking_39768() throws Exception {

        try {
            if (BaseTest.eFlgFound.equalsIgnoreCase("true")) {
                /*Provide below Information to generate excel file*/
                BaseTest.sFunctionalModule = "Deal Management";
                BaseTest.sScenario = "DealManagement_Test_Stacking_39768";
                BaseTest.sScriptName = "DealManagement_Test_Stacking_39768";
                BaseTest.sOTMTestcaseID = "39768";
                BaseTest.sTestDescription = "Verify Stacking functionality for the case deal for existing Child customer with skip reference";

                LoginPageEvents loginEvents = new LoginPageEvents(getDriver());
                loginEvents = PageFactory.initElements(getDriver(), LoginPageEvents.class);

                DealManagementPageEvents dealManagementPageEvents = new DealManagementPageEvents(getDriver());
                dealManagementPageEvents = PageFactory.initElements(getDriver(), DealManagementPageEvents.class);

                /*Login to the system*/
                loginEvents.loginToApplication(System.getProperty("userid"), System.getProperty("userPassword"));

                AF.FnLoginChange(getDriver(), "RMBK1");

                //Excel Data to be used:
                String sWorkbook = "./databank/banking/deal_management/DealManagement_Test_Stacking_39768.xlsx";
                String sSheetName = "TEST_DATA";

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                String sDateName = CommonFunctions.FnGetUniqueId();
                String sDEAL_IDENTIFIER_1 = "DealManagement_Test_Stacking_39768" + "_" + sDateName;

                String sStartDate = CF.FnGetCellValue(90, 7, sSheetName, sWorkbook).toString().trim();
                String sPriceSelectionDate = CF.FnGetCellValue(90, 8, sSheetName, sWorkbook).toString().trim();

                CF.FnTestCaseStatusReport("Pass", "Deal Start date used for Deal creation Is-> " + sStartDate);


                String sDealId, smodelId, sModelId1, sDealIdentifier = "";

                //################ Deal Creation IWS ####################//
                String sDealCreation = "{\"C1-DealREST\":{\"actionFlag\":\"ADD\",\"dealDetails\":{\"dealEntityDetails\":{\"personId\":\"0070573193\"},\"dealIdentifier\":\"" + sDEAL_IDENTIFIER_1 + "\",\"dealEntityType\":\"EPER\",\"contractedDealFlag\":\"true\",\"currency\":\"USD\",\"dealTypeCode\":\"DLAPR\",\"startDate\":\"" + sStartDate + "\",\"simulationTypeFlag\":\"CUST\",\"dealDescription\":\"DealManagement_Test_Stacking_39768 Desc\",\"dealVersionDescription\":\"DealManagement_Test_Stacking_39768 Ver Desc\",\"dealFrequency\":\"3\",\"skipReferenceFlag\":\"true\",\"priceSelectionDate\":\"" + sPriceSelectionDate + "\",\"usagePeriod\":\"1\",\"hierarchyFilterFlag\":\"HIPR\",\"templateFlag\":\"false\"},\"templateReferenceDetails\":{\"copyBasicDetailsFlag\":\"false\",\"copyPricingFlag\":\"false\",\"copyUsageFlag\":\"false\"},\"dealTermsAndConditionsDetails\":{\"termsAndConditionsList\":[{\"description\":\"DEAL_T&C1\",\"termsAndCondition\":\"DEAL_T&C1\",\"termsAndConditionText\":\"DEAL_T&C1\"},{\"description\":\"DEAL_T&C2\",\"termsAndCondition\":\"DEAL_T&C2\",\"termsAndConditionText\":\"DEAL_T&C2\"}],\"adhocTermsAndCondition\":\"ADHOCTERMSANDCONDITIONS\"},\"productDetailsList\":[{\"productCode\":\"PRODUCT_CON_01\"},{\"productCode\":\"PRODUCT_CON_02\"}],\"referenceDetails\":{\"referUsageSw\":false,\"referPriceSw\":false,\"includeChildHierarchy\":false}}}";

                Hashtable < String, String > DealDetails = new Hashtable < String, String > ();
                DealDetails = DM.FnCreateDeal(sDealCreation, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                sDealId = DealDetails.get("sDealId");
                smodelId = DealDetails.get("sModelId");
                sDealIdentifier = DealDetails.get("sDealIdentifier");

                //This function to verify Deal Information Details from DB Table
                DB.FnVerifyDealCreationInfoIWS(90, sSheetName, sWorkbook, sDealId);


                //################ Deal PSEL READ IWS ####################//
                String sDealPSELREAD = "{\"C1-DealREST\":{\"actionFlag\":\"READ\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"}}}";
                //System.out.println("Request ->"+sDealPSELREAD);
                DM.FnReadPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELREAD, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal PSEL SELECT IWS ####################//
                String sDealPSELSELECT = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"PSEL\",\"dealId\":\"" + sDealId + "\"},\"priceItemGroupSelection\":{\"priceItemSelectionList\":[{\"priceItemCode\":\"PI_021\",\"priceItemInfo\":\"PI_021\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_022\",\"priceItemInfo\":\"PI_022\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_023\",\"priceItemInfo\":\"PI_023\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_033\",\"priceItemInfo\":\"PI_033\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,ACCOUNTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_024\",\"priceItemInfo\":\"PI_024\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_025\",\"priceItemInfo\":\"PI_025\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_026\",\"priceItemInfo\":\"PI_026\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,PAYMENTSERVICES\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=INR,DM_TYPE=BT\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_028\",\"priceItemInfo\":\"PI_028\",\"parameterInfo\":\"DM_CURRENCY=USD\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_029\",\"priceItemInfo\":\"PI_029\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_030\",\"priceItemInfo\":\"PI_030\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"},{\"priceItemCode\":\"PI_031\",\"priceItemInfo\":\"PI_031\",\"assignmentLevel\":\"CustomerPriceList\",\"hierarchyDetails\":\"CORPORATEBANKING,REPORTING/SWIFT\",\"includeFlag\":\"true\"}]}}}";
                DM.FnSelectPriceItemGroupSelectionIWS(sCreateDealResource, sDealPSELSELECT, sContentTypeHeader, sAcceptTypeHeader);

                //################ Deal Simulation IWS ####################//
                String sSimulateDeal = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);


                //Function To verify CI_PRICEASGN Table Count for Respective Deal
                DB.FnVerifyPriceAsgnCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_MODEL_SQI Table Count for Respective Deal
                DB.FnVerifySqiCount(90, sSheetName, sWorkbook, smodelId);

                //Function To verify C1_DEAL_SIMULATION_DTL Table Count for Respective Deal
                DB.FnVerifySimulationCount(90, sSheetName, sWorkbook, smodelId);

                //This function to verify Deal Financial Summary Details from DB Table
                DB.Deal_Financial_Summary(107, sSheetName, sWorkbook, smodelId);


                AF.FnNavigation(getDriver(), "Deal Dashboard");

                //Function To Navigate to Deal Information Page
                dealManagementPageEvents.FnSearchDealIdAssignedToMyRole(sDealId, false, false, true, "RMBK1");


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(101, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(107, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(111, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                // Verify Pricing & Commitments
                DB.FnVerifyPricingAndCommitmentDetails(115, sSheetName, sWorkbook, smodelId); //PI_024
                DB.FnVerifyPricingAndCommitmentDetails(116, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(122, sSheetName, sWorkbook, smodelId); //PI_025
                DB.FnVerifyPricingAndCommitmentDetails(123, sSheetName, sWorkbook, smodelId);

                DB.FnVerifyPricingAndCommitmentDetails(129, sSheetName, sWorkbook, smodelId); //PI_026
                DB.FnVerifyPricingAndCommitmentDetails(130, sSheetName, sWorkbook, smodelId);

                //PI_021
                DM.FnPricingAndCommitmentIWS(117, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(118, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //PI_022
                DM.FnPricingAndCommitmentIWS(124, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //OVRD
                DM.FnPricingAndCommitmentIWS(125, sSheetName, sWorkbook, sDealId, "NoValue", "NoValue"); //Seasonal

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(134, sSheetName, sWorkbook);


                //################ Deal Simulation IWS ####################//
                String sSimulateDeal21 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDeal21, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(138, sSheetName, sWorkbook);

                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(144, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(150, sSheetName, sWorkbook);
                
                //Function To Navigate to Pricing And Commitment Screen
                dealManagementPageEvents.FnNavigateToAccountViewAndEditPricing("External Account Identifier - EAI_STACKING_COMT_PARENT_CH1");

                //Function to Verify RollUp Revenue & Cost
                dealManagementPageEvents.FnViewAndEditPricing(154, sSheetName, sWorkbook);

                //Function To Verify Project & Original Pricing and Commitment for Specific/Given Price item
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(157, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(158, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(160, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(161, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(163, sSheetName, sWorkbook);
                dealManagementPageEvents.FnVerifyPricingAndCommitmentDetailsForSpecificPriceItem(164, sSheetName, sWorkbook);

                //Function for Navigation To Deal Information From Pricing And Commitments
                dealManagementPageEvents.FnNavigationToDealInformationFromPricingAndCommitments();

                //Function To Create Deal Version
                dealManagementPageEvents.FnCreateDealVersionFromDealInformation(168, sSheetName, sWorkbook);

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(172, sSheetName, sWorkbook);

                //Function To Get Latest Model Id
                sModelId1 = DM.FnGetCurrentDealVersionFromDealInformation(getDriver());

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //################ Deal Simulation IWS ####################//
                String sSimulateDealByRM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);
                //System.out.println("Simulation Deal ID Is-> " + sSimulateDealDealId);

                //Function To Refresh Deal From UI
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(176, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByRM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRM1, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(180, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //simulate deal after selecting INDPM Assigned Price Items
                String sSimulateDealByPM1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPM1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function to Recommend Pricing By PMBK1 User              
                DM.FnPricingAndCommitmentIWS(184, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //simulate deal after selecting INDPM Assigned Price Items
                String sSimulateDealByPM1V1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByPM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Return Deal To Submitter IWS ####################//
                String ReturnDealtoSubmitterBySPMBK1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"RETN\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\",\"division\":\"IND\",\"comments\":\"Recommended Price Item\",\"rejectReasonCode\":\"RETURN\"}}}";
                DM.FnReturnDealToSubmitterUsingIWS(sCreateDealResource, ReturnDealtoSubmitterBySPMBK1, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("RMBK1");

                //Function to Recommend Pricing By RMBK1 User              
                DM.FnPricingAndCommitmentIWS(185, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");
                DM.FnPricingAndCommitmentIWS(187, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");
                DM.FnPricingAndCommitmentIWS(189, sSheetName, sWorkbook, sDealId, sModelId1, "NoValue");


                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(193, sSheetName, sWorkbook);


                //simulate deal after selecting RMBK1 Assigned Price Items
                String sSimulateDealByRM1V1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SMLD\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(197, sSheetName, sWorkbook);


                //Function To verify Deal Currency Personal Hierarchy Information On Deal Information
                dealManagementPageEvents.FnVerifyPersonalHierarchyInformationOnDealInformation(203, sSheetName, sWorkbook);

                //Function To verify Deal Financial Summary Information On Deal Information
                dealManagementPageEvents.FnVerifyDealFinancialSummary(209, sSheetName, sWorkbook);

                //################ Send Deal For Approval IWS ####################//
                String sSendDealForApprovalByRMR1 = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"SNDP\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRMR1, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(213, sSheetName, sWorkbook);

                // To Change user for sending new request
                WF.FnUserChange("PMBK1");

                //Function to Simulate Deal Using IWS By PMBK1 User
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //################ Send Deal For Approval IWS ####################//
                DM.FnSendDealForApprovalIWS(sCreateDealResource, sSendDealForApprovalByRMR1, sContentTypeHeader, sAcceptTypeHeader);

                // To Change user for sending new request
                WF.FnUserChange("SPMBK1");

                //Function to Simulate Deal Using IWS By PMBK1 User
                DM.FnSimulateDealByRequest(sSimulateDealByRM1V1, sCreateDealResource, sContentTypeHeader, sAcceptTypeHeader);

                //Function To Approve Deal
                String sApproveDealByRM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"APPR\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnApproveDealUsingIWS(sCreateDealResource, sApproveDealByRM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(217, sSheetName, sWorkbook);

                WF.FnUserChange("RMBK1");

                String sFinalizeDeaBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"FNAL\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\"}}}";
                DM.FnFinalizeDealUsingIWS(sCreateDealResource, sFinalizeDeaBySPM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(221, sSheetName, sWorkbook);

                String CustomerAcceptanceEffectiveStartDate = CF.FnGetCellValue(225, 1, sSheetName, sWorkbook).toString().trim();
                String CustomerAcceptanceEffectiveEndDate = CF.FnGetCellValue(225, 2, sSheetName, sWorkbook).toString().trim();

                //Function To Customer Accept Deal
                String sAcceptDealBySPM = "{\"C1-DealREST\":{\"actionFlag\":\"UPD\",\"dealOperation\":{\"operationFlag\":\"ACPT\",\"modelId\":\"" + sModelId1 + "\",\"dealId\":\"" + sDealId + "\",\"dealAcceptEffStartDate\":\"" + CustomerAcceptanceEffectiveStartDate + "\",\"dealAcceptEffEndDate\":\"" + CustomerAcceptanceEffectiveEndDate + "\",\"comments\":\"" + sDealIdentifier + " Deal Accepted\"}}}";
                DM.FnAcceptDealUsingIWS(sCreateDealResource, sAcceptDealBySPM, sContentTypeHeader, sAcceptTypeHeader);

                // Function to Refresh Deal
                DM.FnRefreshDeal(getDriver());

                //Function To verify deal information 
                dealManagementPageEvents.FnDealInformationVerification(229, sSheetName, sWorkbook);

                //Function to update "Stacking Required" Option as N
                dealManagementPageEvents.FnUpdateAlgorithmValue(233, sSheetName, sWorkbook);
                dealManagementPageEvents.FnUpdateAlgorithmValue(234, sSheetName, sWorkbook);



            }



        } catch (Exception e) {
            System.out.println("Script Exception occured ->" + e.getLocalizedMessage());
            e.printStackTrace();
            BaseTest.eFlgFound = "false";
            CF.FnTestCaseStatusReport("Fail", "Script Exception occured ->" + e.getLocalizedMessage().replace(",", ""));
        }
    }

















}