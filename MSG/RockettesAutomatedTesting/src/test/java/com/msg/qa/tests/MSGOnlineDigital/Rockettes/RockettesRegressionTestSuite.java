package com.msg.qa.tests.MSGOnlineDigital.Rockettes;
import MSGOnlineDigital.JIRA.MSGJIRAupdate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.msg.qa.MSGOnlineDigital.Rockettes.RockettesRegressionScript;
import com.msg.qa.MSGOnlineDigital.Rockettes.RockettesSauceJobAccessController;
import com.msg.qa.MSGOnlineDigital.Rockettes.RockettesURLs;
import com.msg.qa.MSGOnlineDigital.Rockettes.RockettesGlobalParameters;
import com.msg.qa.common.SauceLabsGlobalFunctions;
import com.msg.qa.common.HTMLToSMTPFunctions;
import org.json.JSONException;
import org.json.JSONObject;

import MSGOnlineDigital.Utilities.TestExcelParser;


public class RockettesRegressionTestSuite extends UITest  {
	   // SauceLabs User Name and Access Key - to be read from Environment Variables
		  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	      public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");   
		  public static final String URLwithEnvVariables = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
		  
		  // SauceLabs User Name and Access Key - Fallback in case Environment variables are not setup
		  public static final String myUSERNAME = "msgtesting";
		  public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8"; 
		  public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

		  // Calendar Production URL
		  public static String myRockettesURL = "http://www.rockettes.com/";
		  
		  // Production API URL	  
		  public static String myAPIURL = "http://api.msg.com/v2.3/dates/";

		  // TM API URL	  
		  public static String myTMAPIURL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=74ZeqUdnyh3l5hMNcAn209ITL0KAtJxa&keyword=rockettes&size=200&stateCode=NY";
	 
		  // Platform and Browser combination file
		  public static String myPlatformFilePath = "./config/sltestconfig.json";
	     
		  // Hard Coded Promo Codes received from API
		  public static String myPromoCodeFilePath = "./config/promocodes.txt";
		  
		  // TM API Response JSON for Shows
	      public static final String myTMShowTimesFilePath = "./api/TMShows.json";
	     
	     // Flags for controlling data flow
	      public static final boolean mySourceDataReadFromAPI = false;
	 	 
		  // Configuration for test on Windows 10 / Mac Platform
	      public static String myPlatform = "Windows 10";
	 	  public static String myScreenResolution = "2560x1600";
	 	  public static String myMobileScreenResolution = "800x600";  	  
	 	  public static String myMacPlatform = "OS X 10.11";
	 	  
	 	  // Tags to be used in Desired capabilities
	 	  public static final String myBrowserTag = "browsers", 
	 			  					 myChrome = "chrome", mySafari = "safari", myEdge = "edge", myIE = "IE", myFirefox = "fiefox",
	 			                     myPlatformCapability = "platform", myVersionCapability = "version", myScreenResolutionCapability = "screenresolution";	  
	 
	 	  // E-Mail Parameters
	 	  public static String 
	 	  					   myEmailFrom = "MSGTesting@msg.com",
//	    			  		   myEmailToList = "rachitkumar.rastogi@msg.com; richard.barrow@msg.com; krishna.chaparala@msg.com; sri.anne@msg.com",
	 	  	    			   myEmailToList = "rachitkumar.rastogi@msg.com",
	 	  					   myEmailFailedSubject = "Jenkins Error - Rockettes Regression Script is failed!",
	 			  			   myEmailSuccessSubject = "Jenkins Success - Rockettes Regression Script is successful.",
	 			  			   myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"\r\nThanks, Your MSG QA Automation Team.",
	 		 			  	   myEmailBody = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.",      
	  		    			   myEmailUser = "msgqaautomation@gmail.com",
	 			 	           myEmailAccessKey = "Msgqa123"; 	

public static boolean isMyTestPassed = false;	
String myDriverParameters;
DesiredCapabilities myDesiredCapabililties;
List<DesiredCapabilities> myListOfDesiredCapabililties;
static int numberOfDesiredCapabilities;
static int numberOfExecutedTests = 0;
DesiredCapabilities extraCapabilities;
List<String> myCurrentJobStatus = null;
List<String> myCompleteJobStatus = null;
String testStepPlatform;
String testStepStatus;
String testStepBrowser;
String testStepBrowserVersion;
String testStepOS;
String testDeviceOrientation;

String projectID = "Rockettes";
int versionID = 11500;
String cycleID = System.getProperty("testCycle");
String reportRecipientList = System.getProperty("reportRecipientList");

String jiraStoryPrefix = "MSGEW";

List<HashMap<String, String>> completeJobStatus = Arrays.asList(new HashMap<String, String>());
HashMap<String, String> testCaseIndividualResults = new HashMap<String, String>();
List<HashMap<String, String>> testCaseCompleteResults = Arrays.asList(testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults, testCaseIndividualResults);
public static Map<String, String[]> myDesiredSelectors;
public static HashMap<String, String> testCaseResultsToJobReport = new HashMap<String, String>();
public static List<String> reportRowArray = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
public static int reportRowIncrementor;
public static int numberOfDesktopCapabilities;
public static int numberOfMobileCapabilities;

@BeforeSuite(alwaysRun=true)
public void suiteSetup1() throws org.apache.http.ParseException, JSONException, URISyntaxException, ParseException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TestExcelParser tEP = new TestExcelParser();
		myDesiredSelectors = tEP.getlistOfAllSelectors();
		if (cycleID == null) {
			cycleID = "Ad hoc";
			System.out.println("THE CYCLE ID WAS NULL AND HAS BEEN SET BY DEFAULT TO " + cycleID);

		}
		List<String> myPlatforms = new ArrayList<String>();
		JsonElement myJSONElement = new JsonParser().parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
	    JsonArray   myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
	    numberOfDesiredCapabilities = myJSONPlatforms.size();
	    numberOfDesktopCapabilities = 0;
	    numberOfMobileCapabilities = 0;
 /*	    for (int j=0;j<myJSONPlatforms.size();j++) {
 	    	myJSONPlatforms.get(j).getAsJsonObject().entrySet();
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"ipad\"")) {
 	    		numberOfMobileCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"iphone\"")) {
 	    		numberOfMobileCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"android\"")) {
 	    		numberOfMobileCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"firefox\"")) {
 	    		numberOfDesktopCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"chrome\"")) {
 	    		numberOfDesktopCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"safari\"")) {
 	    		numberOfDesktopCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"edge\"")) {
 	    		numberOfDesktopCapabilities++;
 	    	}
 	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"ie\"")) {
 	    		numberOfDesktopCapabilities++;
 	    	}
 	    }*/
 	    
 	   for (int j=0;j<myJSONPlatforms.size();j++) {
	    	myJSONPlatforms.get(j).getAsJsonObject().entrySet();
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().toString().matches("(?i).*iphone.*|.*ipad.*|.*android.*")) {
	    		numberOfMobileCapabilities++;
	    		continue;
	    	}
	    	/*if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"iphone\"")) {
	    		numberOfMobileCapabilities++;
	    	}
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"android\"")) {
	    		numberOfMobileCapabilities++;
	    	}*/
	    	
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().toString().matches("(?i).*firefox.*|.*chrome.*|.*safari.*|.*edge.*|.*internet.*")) {
	    		numberOfDesktopCapabilities++;
	    	}
	    	
/*	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"firefox\"")) {
	    		numberOfDesktopCapabilities++;
	    	}
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"chrome\"")) {
	    		numberOfDesktopCapabilities++;
	    	}
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"safari\"")) {
	    		numberOfDesktopCapabilities++;
	    	}
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"edge\"")) {
	    		numberOfDesktopCapabilities++;
	    	}
	    	if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().contains("browser=\"ie\"")) {
	    		numberOfDesktopCapabilities++;
	    	}*/
	    }
 	    
 	    System.out.println("NUMBER OF SAUCE ONDEMAND BROWSERS: " + String.valueOf(numberOfDesiredCapabilities));
	    System.out.println("Debug Point - NUMBER OF SAUCE ONDEMAND BROWSERS DESKTOP: " + String.valueOf(numberOfDesktopCapabilities));
	    System.out.println("Debug Point - NUMBER OF SAUCE ONDEMAND BROWSERS MBOILE: " + String.valueOf(numberOfMobileCapabilities));
		MSGJIRAupdate mJU = new MSGJIRAupdate(projectID, versionID, cycleID);
		
		/**
		 * Changed on: 09/28/17 by Rachit Kumar RASTOGI
		 * Removed the check of not clearing JIRA if test cycle is not adHoc
		 */
		//if (!(cycleID.contains("hoc"))) {
		//mJU.clearStatusForAllTestCasesInJira();
		//}
		reportRowIncrementor = 1;
}

@AfterSuite(alwaysRun=true)
public void suiteTeardown() throws ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException, InterruptedException {
	SauceLabsGlobalFunctions sLGF = new SauceLabsGlobalFunctions();
	String myCompleteJobStatusString = sLGF.getSauceLabsJobStatus(numberOfExecutedTests * numberOfDesiredCapabilities, USERNAME, ACCESS_KEY).toString();
	myCompleteJobStatus = jobStatusChildren(myCompleteJobStatusString, numberOfDesiredCapabilities);
	HTMLToSMTPFunctions hTSF = new HTMLToSMTPFunctions();
	String resultsTemplateRows = hTSF.reportRowCombiner(reportRowArray);
	String myHTMLReport = hTSF.completeHTMLReport("Rockettes " +  cycleID + " Cycle - " + System.getProperty("testEnvironment"), resultsTemplateRows);
	hTSF.htmlToSMTP(myHTMLReport, reportRecipientList, "Rockettes " +  cycleID + " Cycle - " + System.getProperty("testEnvironment"));
}

	@Test
	public void MSGRock001RockettesHomePage() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
				MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
				Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
				myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
				
				String myIssueKey = null;
				
				for(String key: myCycleIssuessummaryAndKey.keySet())
				{
					if(key.contains("MSGRock001RockettesHomePage"))
					{
						myIssueKey = myCycleIssuessummaryAndKey.get(key);
						break;
					}
				}
			//2. Execute the corresponding test case.
			System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);
			executeTestCase("desktop", "Rockettes Regression Test - 001 Rockettes Home Page", "testRockettesHomePage", "MSGRock001RockettesHomePage", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock002RockettesCSLandingPage() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock002RockettesCSLandingPage"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);
		executeTestCase("desktop", "Rockettes Regression Test - 002 Rockettes CS Landing Page", "testRockettesCSLandingPage", "MSGRock002RockettesCSLandingPage", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock003RockettesCSTickets() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock003RockettesCSTickets"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);
		executeTestCase("desktop", "Rockettes Regression Test - 003 Rockettes CS Tickets", "testRockettesCSTickets", "MSGRock003RockettesCSTickets", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock004RockettesCSGroups() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock004RockettesCSGroups"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);	
		executeTestCase("desktop", "Rockettes Regression Test - 004 Rockettes CS Groups", "testRockettesCSGroups", "MSGRock004RockettesCSGroups", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock005RockettesCSSeatingChart() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock005RockettesCSSeatingChart"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);	
		executeTestCase("desktop", "Rockettes Regression Test - 005 Rockettes CS Seating Chart", "testRockettesCSSeatingChart", "MSGRock005RockettesCSSeatingChart", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock006RockettesCSPromotions() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock006RockettesCSPromotions"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);	
		executeTestCase("desktop", "Rockettes Regression Test - 006 Rockettes CS Promotions", "testRockettesCSPromotions", "MSGRock006RockettesCSPromotions", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock007RockettesCSPlanYourDay() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock007RockettesCSPlanYourDay"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);	
		executeTestCase("desktop", "Rockettes Regression Test - 007 Rockettes CS Plan Your Day", "testRockettesCSPlanYourDay", "MSGRock007RockettesCSPlanYourDay", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock008RockettesCSExperiences() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock008RockettesCSExperiences"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);	
		executeTestCase("desktop", "Rockettes Regression Test - 008 Rockettes CS Experiences", "testRockettesCSExperiences", "MSGRock008RockettesCSExperiences", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock009RockettesCSNews() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock009RockettesCSNews"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 009 Rockettes CS News", "testRockettesCSNews", "MSGRock009RockettesCSNews", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock010RockettesCSEmailSignUp() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock010RockettesCSEmailSignUp"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 010 Rockettes CS Email Sign Up", "testRockettesCSSignUp", "MSGRock010RockettesCSEmailSignUp", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock011RockettesCSPartners() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock011RockettesCSPartners"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 011 Rockettes CS Partners", "testRockettesCSPartners", "MSGRock011RockettesCSPartners", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock012RockettesNYSLandingPage() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock012RockettesNYSLandingPage"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 012 Rockettes NYS Landing Page", "testRockettesNYSLandingPage", "MSGRock012RockettesNYSLandingPage", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock013RockettesNYSTickets() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock013RockettesNYSTickets"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 013 Rockettes NYS Tickets", "testRockettesNYSTickets", "MSGRock013RockettesNYSTickets", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock014RockettesNYSGroups() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock014RockettesNYSGroups"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 014 Rockettes NYS Groups", "testRockettesNYSGroups", "MSGRock014RockettesNYSGroups", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock015RockettesNYSSponsors() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock015RockettesNYSSponsors"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 015 Rockettes NYS Sponsors", "testRockettesNYSSponsors", "MSGRock015RockettesNYSSponsors", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock016RockettesBlog() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock016RockettesBlog"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 016 Rockettes Blog", "testRockettesBlog", "MSGRock016RockettesBlog", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock017RockettesHistory() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock017RockettesHistory"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 017 Rockettes History", "testRockettesHistory", "MSGRock017RockettesHistory", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock018RockettesPhotos() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock018RockettesPhotos"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 018 Rockettes Photos", "testRockettesPhotos", "MSGRock018RockettesPhotos", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock019RockettesDanceEducation() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock019RockettesDanceEducation"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 019 Rockettes Dance Education", "testRockettesDanceEducation", "MSGRock019RockettesDanceEducation", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock020RockettesFAQ() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock020RockettesFAQ"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 020 Rockettes FAQ", "testRockettesFAQ", "MSGRock020RockettesFAQ", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock021RockettesStore() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock021RockettesStore"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 021 Rockettes Store", "testRockettesStore", "MSGRock021RockettesStore", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock022RockettesCSTicketsLoadTime() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		executeTestCase("desktop", "Rockettes Regression Test - 022 Rockettes Christmas Calendar Load Time", "testRockettesChristmasCalendarLoadTime", "MSGRock022RockettesCSTicketsLoadTime", "1600??");
	}	

	@Test
	public void MSGRock023RockettesContactUs() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock023RockettesContactUs"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			
		executeTestCase("desktop", "Rockettes Regression Test - 023 Rockettes Contact Us", "testRockettesContactUs", "MSGRock023RockettesContactUs", myIssueKey.split("-")[1]);
	}	

	@Test
	public void MSGRock024RockettesMobileTestCases() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, JSONException, Exception {
		//1. Get the Test Case ID specific to test case and pass it as per the cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID, cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater.getAllIssueSummaryAndKeysAssignedToACycle();
		
		String myIssueKey = null;
		
		for(String key: myCycleIssuessummaryAndKey.keySet())
		{
			if(key.contains("MSGRock024RockettesMobileTestCases"))
			{
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		//2. Execute the corresponding test case.
		System.out.println("JIRA Ticket number part is: "+myIssueKey.split("-")[1]);			

		System.setProperty("mobileTestCasesResultsHashMap", "");
		executeTestCase("mobile", "Rockettes Regression Test - 024 Rockettes Mobile Test Cases", "testRockettesMobileTestCases", "MSGRock024RockettesMobileTestCases", myIssueKey.split("-")[1]);
/*
		MSGJIRAupdate mJU = new MSGJIRAupdate(projectID, versionID, cycleID);
		RockettesGlobalParameters rGP = new RockettesGlobalParameters(driver);
		myDriverParameters = rGP.getDriverParameters().toString();
 	    myDesiredCapabililties = rGP.executeRemoteWebDriverInSauceLabsViaJenkins("mobile", "Rockettes Regression Test - 024 Rockettes Mobile Test Cases", "testRockettesMobileTestCases", myDesiredSelectors);
 	    numberOfExecutedTests++;
 	    String jobStatus = handleParentSleep(numberOfMobileCapabilities);
 	    String testCaseJiraStoryNumber = "";
 	    String individualTestCaseStatusName = "";
 	    myCurrentJobStatus = jobStatusChildren(jobStatus, numberOfDesiredCapabilities);
 	    List<HashMap<String, String>> completeTestCaseResults = individualTestCaseStatus(jobStatus, individualTestCaseStatusName);
 	    String failureList = new String();
 	    String entireFailureList = new String();
 	    String testCaseNameFromResults = new String();
 	    for (int jobStatusIncrementor = 0; jobStatusIncrementor < numberOfDesiredCapabilities; jobStatusIncrementor++) {
 	    	if ((completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus").contains("FAIL"))) {
	 	    	failureList = completeTestCaseResults.get(jobStatusIncrementor).get("testStepPlatform") + " " + completeTestCaseResults.get(jobStatusIncrementor).get("testStepBrowser") + " " + completeTestCaseResults.get(jobStatusIncrementor).get("testStepBrowserVersion") + " HAS FAILED\r\n";
	 	    	entireFailureList = entireFailureList + failureList;
	 	    	testCaseResultsToJobReport.put("MSGEW" + testCaseJiraStoryNumber + "EXECUTIONSTATUS", "FAIL");
	 	    	testCaseResultsToJobReport.put("MSGEW" + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR", "#CC3300");
	 	    	testCaseNameFromResults = completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName");
 	    	} else {
	 	    	testCaseResultsToJobReport.put("MSGEW" + testCaseJiraStoryNumber + "EXECUTIONSTATUS", "PASS");
	 	    	testCaseResultsToJobReport.put("MSGEW" + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR", "#75B000");
	 	    	testCaseNameFromResults = completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName");
 	    	}
 	    	testCaseResultsToJobReport.put("MSGEW" + testCaseJiraStoryNumber + "EXECUTEDON", rGP.getCurrentTimestamp());
 	    	mJU.updateStatusForSingleTestCasesInJira(completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
 	    	mJU.updateStatusForSingleTestStepsInJira(completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepParameters"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
 	    }
 	    if (!(entireFailureList.equalsIgnoreCase(""))) {
 	    	mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults, "PASS");
 	    } else {
 	    	mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults, "FAIL");
 	    }
 		HTMLToSMTPFunctions hTSF = new HTMLToSMTPFunctions();
 		reportRowArray.set(reportRowIncrementor, hTSF.reportRowBuilder(String.valueOf(reportRowIncrementor), "https://thegarden.atlassian.net/browse/MSGEW-" + testCaseJiraStoryNumber, "MSGEW-" + testCaseJiraStoryNumber, testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"), testCaseResultsToJobReport.get("MSGEW1579EXECUTIONSTATUS"),"Rockettes", "Home Page", "MSGTesting", testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "EXECUTEDON")));
 		reportRowIncrementor++;
	    	if (!(entireFailureList.equalsIgnoreCase(""))) {
 	    		Assert.fail(entireFailureList);
 	    	}
*/
	}	

		public String handleParentSleep(int numberOfPlatformDesiredCapabilities) throws InterruptedException, ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException {
		 	driver.navigate().to("about:blank");
		 	Thread.sleep(30000);
	 		SauceLabsGlobalFunctions sLGF = new SauceLabsGlobalFunctions();
	 		String currentJobStatus = sLGF.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities, USERNAME, ACCESS_KEY).toString();
	 		while (currentJobStatus.contains("progress")) {
	 	 	    Thread.sleep(3000);
	 	 	    driver.navigate().to("about:blank");
	 	 	    currentJobStatus = sLGF.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities, USERNAME, ACCESS_KEY).toString();
	 		}
	 		while ((currentJobStatus.contains("progress")) && currentJobStatus.contains("complete")) {
	 	 	    Thread.sleep(3000);
	 	 	    driver.navigate().to("about:blank");
	 	 	    currentJobStatus = sLGF.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities, USERNAME, ACCESS_KEY).toString();
	 		}
	 		return currentJobStatus;
		}
		public List<String> jobStatusChildren(String jobStatusPassed, int numberOfDesiredCaps) throws ClientProtocolException, InterruptedException, ParseException, IOException, org.json.simple.parser.ParseException {
	 	    int jobStatusLength = jobStatusPassed.length();
	 	    int cjsLB = 0;
	 	    int cjsRB = 0;
	 	    List<String> childrenJobStatus = Arrays.asList("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""); 
	 	    for (int nDCI = 0; nDCI < numberOfDesiredCapabilities; nDCI++) {
	 		    cjsLB = jobStatusPassed.indexOf("{");
	 		    cjsRB = jobStatusPassed.indexOf("}");
	 	    	childrenJobStatus.set(nDCI, jobStatusPassed.substring(cjsLB, cjsRB+1));
	 	    	jobStatusPassed = jobStatusPassed.substring(cjsRB+1);
	 	    }
	 	    return childrenJobStatus;
		}

		public List<HashMap<String, String>> individualTestCaseStatus(String jobStatusToParse, String testCaseName) throws JSONException
		{
			int jSLB = 0;
		    int jSRB = 0;
		    int iJSTSPLB = 0;
		    int iJSTSPRB = 0;
		    int iJSTSSLB = 0;
		    int iJSTSSRB = 0;
		    int iJSTSBLB = 0;
		    int iJSTSBRB = 0;
		    int iJSTSVLB = 0;
		    int iJSTSVRB = 0;
	 	    for (int jobStatusIncrementor = 0; jobStatusIncrementor < numberOfDesiredCapabilities; jobStatusIncrementor++) {
	 		 	testCaseIndividualResults = new HashMap<String, String>();
	 	    	jSLB = jobStatusToParse.indexOf("{");
	 	    	jSRB = jobStatusToParse.indexOf("}");
	 	    	String individualJobStatus = jobStatusToParse.substring(jSLB, jSRB+1);
	 	    	if (individualJobStatus.contains("Platform:")) {
		 	    	iJSTSPLB = individualJobStatus.indexOf("Platform:");
		 	    	iJSTSPRB = individualJobStatus.indexOf("modification_time");
		 		 	testStepPlatform = individualJobStatus.substring(iJSTSPLB, iJSTSPRB-3);
	 	    	} else {
		 	    	iJSTSPLB = individualJobStatus.indexOf("browser=");
		 	    	iJSTSPRB = individualJobStatus.indexOf("browser_version=");
		 		 	testStepPlatform = individualJobStatus.substring(iJSTSPLB+9, iJSTSPRB-3);
	 	    	}
	 	    	iJSTSSLB = individualJobStatus.indexOf("passed=");
	 	    	iJSTSSRB = individualJobStatus.indexOf("owner=");
	 	    	iJSTSBLB = individualJobStatus.indexOf("browser=");
	 	    	iJSTSBRB = individualJobStatus.indexOf("browser_version=");
	 	    	iJSTSVLB = individualJobStatus.indexOf("browser_short_version=");
	 	    	iJSTSVRB = individualJobStatus.indexOf("os=");
	 	    	/**
	 	    	 * Include device orientation for mobile
	 	    	 * By: Rachit Kumar Rastogi on: 10/12/17
	 	    	 */
	 	    	int idx = individualJobStatus.indexOf(" id=");
	 	    	String testDriverId = individualJobStatus.substring(idx+5, idx+37);
 	    		RockettesSauceJobAccessController mySauceJobStatusFetch = new RockettesSauceJobAccessController(USERNAME,ACCESS_KEY);	
	 	    JSONObject mySauceJobDetails = new JSONObject(mySauceJobStatusFetch.getJobInfo(testDriverId));
	 	    testDeviceOrientation = mySauceJobDetails.getString("name");
	 	    if (testDeviceOrientation.contains("portrait"))
	 	    {
	 	    	testDeviceOrientation = "portrait";
	 	    }
	 	    if (testDeviceOrientation.contains("landscape"))
	 	    {
	 	    	testDeviceOrientation = "landscape";
	 	    }
	 		 	testStepBrowser = individualJobStatus.substring(iJSTSBLB+9, iJSTSBRB-3);
	 		 	testStepBrowserVersion = individualJobStatus.substring(iJSTSVLB+23, iJSTSVRB-3);
	 		 	testStepStatus = individualJobStatus.substring(iJSTSSLB, iJSTSSRB-2);
	 		 	if (testStepPlatform.contains("MAC")) {
	 		 	testStepPlatform = "MAC";
	 		 	}
	 		 	if (testStepPlatform.contains("WIN")) {
	 		 	testStepPlatform = "WINDOWS";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("hrome")) {
	 		 	testCaseIndividualResults.put("testStepBrowser", "Chrome");
	 		 	testStepBrowser = "Chrome";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("irefox")) {
	 		 	testCaseIndividualResults.put("testStepBrowser", "Firefox");
	 		 	testStepBrowser = "Firefox";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("afari")) {
	 		 	testCaseIndividualResults.put("testStepBrowser", "Safari");
	 		 	testStepBrowser = "Safari";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("xplorer")) {
	 		 	testCaseIndividualResults.put("testStepBrowser", "IE");
	 		 	testStepBrowser = "IE";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("dge")) {
	 		 	testCaseIndividualResults.put("testStepBrowser", "Edge");
	 		 	testStepBrowser = "Edge";
	 		 	}
	 		 	if (testStepBrowser.toLowerCase().contains("phone")) {
	 		 	testStepPlatform = "iOS";
	 		 	testStepBrowser = "iPhone";
	 		 	int iPhoneParameterLB = individualJobStatus.indexOf("- iPhone"); 
	 		 	int iPhoneParameterRB = individualJobStatus.indexOf("Simulator");
	 		 	testStepBrowserVersion = individualJobStatus.substring(iPhoneParameterLB+9, iPhoneParameterRB-1);
	 		 	}

	 		 	if (testStepBrowser.toLowerCase().contains("pad")) {
	 		 	testStepPlatform = "iOS";
	 		 	testStepBrowser = "iPad";
	 		 	int iPhoneParameterLB = individualJobStatus.indexOf("- iPad"); 
	 		 	int iPhoneParameterRB = individualJobStatus.indexOf("Simulator");
	 		 	testStepBrowserVersion = individualJobStatus.substring(iPhoneParameterLB+7, iPhoneParameterRB-1);
	 		 	}

	 		 	if (testStepBrowser.toLowerCase().contains("afari") && testStepBrowserVersion.contains("8")) {
	 		 	testCaseIndividualResults.put("testStepBrowserVersion", "10");
	 		 	testStepBrowserVersion = "10";
	 		 	} else {
		 		 	testCaseIndividualResults.put("testStepBrowserVersion", testStepBrowserVersion);
	 		 	}

	 		 	testCaseIndividualResults.put("testCaseName", testCaseName);
	 		 	testCaseIndividualResults.put("testStepPlatform", testStepPlatform);
	 		 	
	 		 	String testStepParameters = null;
	 		 	
	 		 	/**
	 		 	 * Hotfix for missing JIRA update for mobile devices, identified in Full regression build of Rockettes.com
	 		 	 * Fixed By: Rachit Kumar Rastogi on: 10/12/2017
	 		 	 */
	 		 	
	 		 	if (testStepBrowser.toLowerCase().contains("ipad") || testStepBrowser.toLowerCase().contains("iphone") || testStepBrowser.toLowerCase().contains("android"))
	 		 	{
	 		 		 testStepParameters = testStepPlatform + " " + testStepBrowser + " " + testStepBrowserVersion + " " + testDeviceOrientation;
	 		 	}
	 		 	else
	 		 	{
	 		 		 testStepParameters = testStepPlatform ; //+ " " + testStepBrowser + " " + testStepBrowserVersion;	
	 		 	}
	 		 	
	 		 	testCaseIndividualResults.put("testStepParameters", testStepParameters);
	 		 	
	 		 	if (testStepStatus.contains("true")) {
		 		 	testCaseIndividualResults.put("testStepStatus", "PASS");
	 		 	} else {
		 		 	testCaseIndividualResults.put("testStepStatus", "FAIL");
	 		 	}
	 		 	testCaseCompleteResults.set(jobStatusIncrementor, testCaseIndividualResults);
	 		 	jobStatusToParse = jobStatusToParse.substring(jSRB+1);
	 	    }
	 	    return testCaseCompleteResults;
		}

		public void executeTestCase(String desktopOrMobile, String fullTestCaseName, String caseStatementTestCaseName, String individualTestCaseStatusName, String testCaseJiraStoryNumber) throws InterruptedException, ClientProtocolException, ParseException, IOException, org.json.simple.parser.ParseException, org.apache.http.ParseException, JSONException, URISyntaxException {
			MSGJIRAupdate mJU = new MSGJIRAupdate(projectID, versionID, cycleID);
			RockettesGlobalParameters rGP = new RockettesGlobalParameters(driver);
			myDriverParameters = rGP.getDriverParameters().toString();
	 	    myDesiredCapabililties = rGP.executeRemoteWebDriverInSauceLabsViaJenkins(desktopOrMobile, fullTestCaseName, caseStatementTestCaseName, myDesiredSelectors);
	 	    numberOfExecutedTests++;
	 	    String jobStatus = new String();
	 	    if (desktopOrMobile.equalsIgnoreCase("desktop")) {
		 	    jobStatus = handleParentSleep(numberOfDesktopCapabilities);
		 	    myCurrentJobStatus = jobStatusChildren(jobStatus, numberOfDesktopCapabilities);
	 	    }
	 	    if (desktopOrMobile.equalsIgnoreCase("mobile")) {
		 	    jobStatus = handleParentSleep(numberOfMobileCapabilities);
		 	    myCurrentJobStatus = jobStatusChildren(jobStatus, numberOfMobileCapabilities);
		 	   HashMap<String, String> mobileTestCaseResults;
		 	   if (numberOfMobileCapabilities == 0) {
			 	    mobileTestCaseResults = new HashMap<String, String>();
		 	   } else {
			 	    mobileTestCaseResults = rGP.convertHashMapStringToHashMap(System.getProperty("mobileTestCasesResultsHashMap"));
		 	   }
	 	    }
	 	    
			HashMap<String, String> abc = RockettesRegressionScript.isMyTestPassedHashMap;
	 	    List<HashMap<String, String>> completeTestCaseResults = individualTestCaseStatus(jobStatus, individualTestCaseStatusName);
	 	    String failureList = new String();
	 	    String entireFailureList = new String();
	 	    String testCaseNameFromResults = new String();
	 	    for (int jobStatusIncrementor = 0; jobStatusIncrementor < numberOfDesiredCapabilities; jobStatusIncrementor++) {
	 	    	if ((completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus").contains("FAIL"))) {
		 	    	failureList = completeTestCaseResults.get(jobStatusIncrementor).get("testStepPlatform") + " " + completeTestCaseResults.get(jobStatusIncrementor).get("testStepBrowser") + " " + completeTestCaseResults.get(jobStatusIncrementor).get("testStepBrowserVersion") + " HAS FAILED\r\n";
		 	    	entireFailureList = entireFailureList + failureList;
		 	    	testCaseResultsToJobReport.put(jiraStoryPrefix + testCaseJiraStoryNumber + "EXECUTIONSTATUS", "FAIL");
		 	    	testCaseResultsToJobReport.put(jiraStoryPrefix + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR", "#CC3300");
		 	    	testCaseNameFromResults = completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName");
	 	    	} else {
		 	    	testCaseResultsToJobReport.put(jiraStoryPrefix + testCaseJiraStoryNumber + "EXECUTIONSTATUS", "PASS");
		 	    	testCaseResultsToJobReport.put(jiraStoryPrefix + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR", "#75B000");
		 	    	testCaseNameFromResults = completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName");
	 	    	}
	 	    	testCaseResultsToJobReport.put(jiraStoryPrefix + testCaseJiraStoryNumber + "EXECUTEDON", rGP.getCurrentTimestamp());
//	 	    	mJU.updateStatusForSingleTestCasesInJira(completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
	 	    	System.out.println("JIRA Steps Parameters are: "+completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName")+" another one: "+ completeTestCaseResults.get(jobStatusIncrementor).get("testStepParameters")+" last one: "+ completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
	 	    	mJU.updateStatusForSingleTestStepsInJira(completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepParameters"), completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
	 	    }
	 	    
	 	    System.out.println("TEST CASE NAME FROM RESULTS: " +  testCaseNameFromResults);
	 	    System.out.println("ENTIRE FAILURE LIST: " +  entireFailureList);
	 	    if (!(entireFailureList.equalsIgnoreCase(""))) {
	 	    	mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults, "FAIL");
	 	    } else {
	 	    	mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults, "PASS");
	 	    }
	 		HTMLToSMTPFunctions hTSF = new HTMLToSMTPFunctions();
//	 		reportRowArray.set(reportRowIncrementor, hTSF.reportRowBuilder(String.valueOf(reportRowIncrementor), "https://thegarden.atlassian.net/browse/MSGEW-" + testCaseJiraStoryNumber, "MSGEW-" + testCaseJiraStoryNumber, testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"), testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "EXECUTIONSTATUS"),"Rockettes", "Home Page", "MSGTesting", testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "EXECUTEDON")));
	 		reportRowArray.set(1, hTSF.reportRowBuilder(String.valueOf(1), "https://thegarden.atlassian.net/browse/MSGEW-" + testCaseJiraStoryNumber, "MSGEW-" + testCaseJiraStoryNumber, testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"), "","Rockettes", "Home Page", "MSGTesting", testCaseResultsToJobReport.get("MSGEW" + testCaseJiraStoryNumber + "EXECUTEDON")));
	 		reportRowIncrementor++;
		    	if (!(entireFailureList.equalsIgnoreCase(""))) {
	 	    		Assert.fail(entireFailureList);
	 	    	}

		}
		
}
