package com.msg.qa.tests.MSGOnlineDigital.SalesCenter;

import Utility.JIRA.MSGJIRAupdate;
import Utility.Parsers.TestExcelParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.msg.qa.MSGOnlineDigital.SalesCenter.MSGSalesCenterRegressionScript;
import com.msg.qa.common.HTMLToSMTPFunctions;
import com.msg.qa.common.MSGOnlineAfterSuiteMethods;
import com.msg.qa.common.MSGOnlineDigitalGlobalParameters;
import com.msg.qa.common.SauceLabsGlobalFunctions;
import com.qaprosoft.carina.core.foundation.UITest;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;

//import MSGOnlineDigital.JIRA.MSGJIRAupdate;

@SuppressWarnings("deprecation")
public class MSGSalesCenterRegressionTestSuite extends UITest {
	// SauceLabs User Name and Access Key - to be read from Environment
	// Variables
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URLwithEnvVariables = "https://" + USERNAME + ":"
			+ ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	// SauceLabs User Name and Access Key - Fallback in case Environment
	// variables are not setup
	public static final String myUSERNAME = "MSGTesting";
	public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8";
	public static final String URLwithHardCodedVariables = "https://"
			+ myUSERNAME + ":" + myACCESS_KEY
			+ "@ondemand.saucelabs.com:443/wd/hub";
	// Tags to be used in Desired capabilities
	public static final String myBrowserTag = "browsers", myChrome = "chrome",
			mySafari = "safari", myEdge = "edge", myIE = "IE",
			myFirefox = "fiefox", myPlatformCapability = "platform",
			myVersionCapability = "version",
			myScreenResolutionCapability = "screenresolution";
	// Configuration for test on Windows 10 / Mac Platform
	public static String myPlatform = "Windows 10";
	public static String myScreenResolution = "2560x1600";
	public static String myMobileScreenResolution = "800x600";
	public static String myMacPlatform = "OS X 10.11";
	// E-Mail Parameters
	public static String myEmailFrom = "MSGTesting@msg.com",
	// myEmailToList =
	// "rachitkumar.rastogi@msg.com; richard.barrow@msg.com;
	// krishna.chaparala@msg.com; sri.anne@msg.com",
	myEmailToList = "rachitkumar.rastogi@msg.com",
			myEmailFailedSubject = "Jenkins Error - Script is failed!",
			myEmailSuccessSubject = "Jenkins Success - Script is successful.",
			myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that the Regression Script has been failed at "
					+ java.text.DateFormat.getDateTimeInstance()
					.format(Calendar.getInstance().getTime())
					+ "." + "\r\nThanks, Your MSG QA Automation Team.",
			myEmailBody = "Hi, \r\nThis is to bring to your notice that the Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.",
			myEmailUser = "msgqaautomation@gmail.com",
			myEmailAccessKey = "Msgqa123";

	public static boolean isMyTestPassed = false;
	public static Map<String, String[]> myDesiredSelectors;
	public static HashMap<String, List<String>> myTestCasesWithSteps = new HashMap<String, List<String>>();
	public static HashMap<String, String> testCaseResultsToJobReport = new HashMap<String, String>();
	public static List<String> reportRowArray = Arrays.asList("", "", "", "",
			"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
			"", "", "", "");
	public static int reportRowIncrementor;
	public static int numberOfDesktopCapabilities;
	public static int numberOfMobileCapabilities;
	static int numberOfDesiredCapabilities;
	static int numberOfExecutedTests = 0;
	String myDriverParameters;
	DesiredCapabilities myDesiredCapabililties;
	List<DesiredCapabilities> myListOfDesiredCapabililties;
	DesiredCapabilities extraCapabilities;
	List<String> myCurrentJobStatus = null;
	List<String> myCompleteJobStatus = null;
	String testStepPlatform;
	String testStepStatus;
	String testStepBrowser;
	String testStepBrowserVersion;
	String testStepOS;
	// String projectID = System.getProperty("testProjectID");
	// String versionID = System.getProperty("testVersionID");;
	String cycleID = System.getProperty("testCycle");
	String projectID = "SalesCenter";
	int versionID = -1;
	// String cycleID = "Ad hoc";
	String reportRecipientList = System.getProperty("reportRecipientList");
	String testProjectName = System.getProperty("testProjectNme");
	String testProjectLongName = System.getProperty("testProjectLongName");
	// String jiraStoryPrefix = System.getProperty("jiraStoryPrefix");
	String jiraStoryPrefix = "SC";
	List<HashMap<String, String>> completeJobStatus = Arrays
			.asList(new HashMap<String, String>());
	HashMap<String, String> testCaseIndividualResults = new HashMap<String, String>();
	List<HashMap<String, String>> testCaseCompleteResults = Arrays.asList(
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults);

	@BeforeSuite(alwaysRun = true)
	public void suiteSetup1() throws org.apache.http.ParseException,
			JSONException, URISyntaxException, ParseException, IOException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		TestNG myTestNG = new TestNG();
		myTestNG.setAnnotationTransformer(new MSGTestNGAnnotationTransfer());

		if (testProjectName == null) {
			testProjectName = projectID;
		}
		TestExcelParser tEP = new TestExcelParser();
		myDesiredSelectors = tEP.getlistOfAllSelectors();
		if (cycleID == null) {
			cycleID = "Ad hoc";
			System.out.println(
					"THE CYCLE ID WAS NULL AND HAS BEEN SET BY DEFAULT TO "
							+ cycleID);

		}
		JsonElement myJSONElement = new JsonParser()
				.parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
		JsonArray myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
		numberOfDesiredCapabilities = myJSONPlatforms.size();
		numberOfDesktopCapabilities = 0;
		numberOfMobileCapabilities = 0;
		for (int j = 0; j < myJSONPlatforms.size(); j++) {
			myJSONPlatforms.get(j).getAsJsonObject().entrySet();
			if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().toString()
					.matches("(?i).*iphone.*|.*ipad.*|.*android.*")) {
				numberOfMobileCapabilities++;
				continue;
			}
			if (myJSONPlatforms.get(j).getAsJsonObject().entrySet().toString()
					.matches(
							"(?i).*firefox.*|.*chrome.*|.*safari.*|.*edge.*|.*internet.*")) {
				numberOfDesktopCapabilities++;
			}
		}

		System.out.println("Total Sauce On-demand Browsers: "
				+ String.valueOf(numberOfDesiredCapabilities));
		System.out.println("Total Desktop Browsers: "
				+ String.valueOf(numberOfDesktopCapabilities));
		System.out.println("Total Mobile Browsers: "
				+ String.valueOf(numberOfMobileCapabilities));

		/**
		 * Changed on: 09/28/17 by Rachit Kumar RASTOGI Removed the check of not
		 * clearing JIRA if test cycle is not adHoc
		 */
		// MSGJIRAupdate mJU = new MSGJIRAupdate(projectID, versionID, cycleID);
		// if (!(cycleID.contains("hoc"))) {
		// mJU.clearStatusForAllTestCasesInJira();
		// }
		reportRowIncrementor = 1;
	}

	/**
	 * Variable to address requirement of overriding TestNG XML Suite based upon
	 * the selections from excel. By Rachit Kumar Rastogi on 10/23/17
	 */
	@BeforeClass
	public void before() {
		TestNG myTestNG = new TestNG();
		myTestNG.setAnnotationTransformer(new MSGTestNGAnnotationTransfer());
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTeardown()
			throws ClientProtocolException, ParseException, IOException,
			org.json.simple.parser.ParseException, InterruptedException {
		SauceLabsGlobalFunctions sLGF = new SauceLabsGlobalFunctions();
		String myCompleteJobStatusString = sLGF.getSauceLabsJobStatus(
				numberOfExecutedTests * numberOfDesiredCapabilities, USERNAME,
				ACCESS_KEY).toString();
		MSGOnlineAfterSuiteMethods aSM = new MSGOnlineAfterSuiteMethods();
		myCompleteJobStatus = aSM.jobStatusChildren(myCompleteJobStatusString,
				numberOfDesiredCapabilities);
		HTMLToSMTPFunctions hTSF = new HTMLToSMTPFunctions();
		String resultsTemplateRows = hTSF.reportRowCombiner(reportRowArray);
		String myHTMLReport = hTSF.completeHTMLReport(
				testProjectName + " " + cycleID + " Cycle - "
						+ System.getProperty("testEnvironment"),
				resultsTemplateRows);
		hTSF.htmlToSMTP(myHTMLReport, reportRecipientList,
				testProjectName + " " + cycleID + " Cycle - "
						+ System.getProperty("testEnvironment"));
	}

	@Test
	public void MSGTC000LandingPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC000LandingPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);
		executeTestCase("desktop", "MSG.com Regression Test - 000 Landing Page",
				"testMsgLandingPage", "MSGTC000LandingPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC001VenueRentalsPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC001VenueRentalsPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);
		executeTestCase("desktop",
				"MSG.com Regression Test - 001 Sales Center -> Venue Rentals Page",
				"testSalesCenterVenueRentalsPage",
				"MSGSalesCenterTC001VenueRentalsPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC002VenueLandingPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC002VenueLandingPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);
		executeTestCase("desktop",
				"MSG.com Regression Test - 002 Venue Landing Page",
				"testMsgVenueLandingPage", "MSGTC002VenueLandingPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC003CorporateEntertainmentPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC003CorporateEntertainmentPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 003 Sales Center -> Corporate Entertainment Page",
				"testSalesCenterCorporateEntertainmentPage",
				"MSGSalesCenterTC003CorporateEntertainmentPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC004PremiumHospitalityPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC004PremiumHospitalityPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 004 Sales Center -> Premium Hospitality Page",
				"testSalesCenterPremiumHospitalityPage",
				"MSGSalesCenterTC004PremiumHospitalityPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC005GroupOutingsPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC005GroupOutingsPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 005 Sales Center -> Group Outings Page",
				"testSalesCenterGroupOutingsPage",
				"MSGSalesCenterTC005GroupOutingsPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC006CalendarPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC006CalendarPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 006 MSG.com -> Calendar -> Madison Square Garden Page",
				"testMSGCalendarPage", "MSGTC006CalendarPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC007EventDetailsPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC007EventDetailsPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 007 Event Details Page",
				"testMSGEventDetailsPage", "MSGTC007EventDetailsPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC008VenueRentalsMSGRadioCityPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains(
					"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 008 Sales Center -> Venue Rentals -> Radio City Page",
				"testMSGSalesCenterVenueRentalsMSGRadioCityPage",
				"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains(
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 009 Sales Center -> Premium Hospitality -> Event Level Suites Page",
				"testMSGSalesCenterPremHospMSGEventLevelSuitesPage",
				"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC010VenueToursPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC010VenueToursPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 010 MSG SalesCenter VenueToursPage",
				"testMSGSalesCenterVenueToursPage",
				"MSGSalesCenterTC010VenueToursPage", myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGSalesCenterTC011PremHospMSGKnicksPage()
			throws ClientProtocolException, IOException, ParseException,
			InterruptedException, MalformedURLException,
			NoSuchAlgorithmException, KeyManagementException, JSONException,
			Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTC011PremHospMSGKnicksPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 011 Sales Center -> Premium Hospitality -> Knicks Locker Room Page",
				"testMSGSalesCenterPremHospMSGKnicksPage",
				"MSGSalesCenterTC011PremHospMSGKnicksPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC012KnicksTicketsPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC012KnicksTicketsPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 012 Knicks Tickets Page",
				"testMSGKnicksTicketsPage", "MSGTC012KnicksTicketsPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC013RangersTicketsPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC013RangersTicketsPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 013 Rangers Tickets Page",
				"testMSGRangersTicketsPage", "MSGTC013RangersTicketsPage",
				myIssueKey.split("-")[1]);
	}

	@Test
	public void MSGTC014MSGVenueGuidePage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC014MSGVenueGuidePage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 014 MSG Venue Guides",
				"testMSGVenueGuidePage", "MSGTC014MSGVenueGuidePage",
				myIssueKey.split("-")[1]);
	}
	
	@Test
	public void MSGTC015SearchPage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGTC015SearchPage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		executeTestCase("desktop",
				"MSG.com Regression Test - 015 Search Page",
				"testMSGSearchPage", "MSGTC015SearchPage",
				myIssueKey.split("-")[1]);
	}
	
	@Test
	public void MSGSalesCenterTCAllMobilePage() throws ClientProtocolException,
			IOException, ParseException, InterruptedException,
			MalformedURLException, NoSuchAlgorithmException,
			KeyManagementException, JSONException, Exception {
		// 1. Get the Test Case ID specific to test case and pass it as per the
		// cycle.
		MSGJIRAupdate myMSGJiraUpdater = new MSGJIRAupdate(projectID, versionID,
				cycleID);
		Map<String, String> myCycleIssuessummaryAndKey = new HashMap<String, String>();
		myCycleIssuessummaryAndKey = myMSGJiraUpdater
				.getAllIssueSummaryAndKeysAssignedToACycle();

		String myIssueKey = null;

		for (String key : myCycleIssuessummaryAndKey.keySet()) {
			if (key.contains("MSGSalesCenterTCAllMobilePage")) {
				myIssueKey = myCycleIssuessummaryAndKey.get(key);
				break;
			}
		}
		// 2. Execute the corresponding test case.
		System.out.println(
				"JIRA Ticket number part is: " + myIssueKey.split("-")[1]);

		System.setProperty("mobileTestCasesResultsHashMap", "");
		executeTestCase("mobile",
				"MSG Sales Center Regression Test - Mobile Test Cases",
				"testSalesCenterMobileTestCases",
				"MSGSalesCenterTCAllMobilePage", myIssueKey.split("-")[1]);
	}

	public void executeTestCase(String desktopOrMobile, String fullTestCaseName,
								String caseStatementTestCaseName,
								String individualTestCaseStatusName, String testCaseJiraStoryNumber)
			throws InterruptedException, ClientProtocolException,
			ParseException, IOException, org.json.simple.parser.ParseException,
			org.apache.http.ParseException, JSONException, URISyntaxException {
		MSGJIRAupdate mJU = new MSGJIRAupdate(projectID, versionID, cycleID);
		MSGOnlineDigitalGlobalParameters rGP = new MSGOnlineDigitalGlobalParameters(driver);
		MSGOnlineAfterSuiteMethods aSM = new MSGOnlineAfterSuiteMethods();
		MSGOnlineAfterSuiteMethods aSMD = new MSGOnlineAfterSuiteMethods(driver);
		myDriverParameters = rGP.getDriverParameters().toString();
		myDesiredCapabililties = rGP.executeRemoteWebDriverInSauceLabsViaJenkins(desktopOrMobile,
				fullTestCaseName, caseStatementTestCaseName,
				myDesiredSelectors, myTestCasesWithSteps);
		numberOfExecutedTests++;
		int numberOfDesiredCaps = 0;
		String jobStatus = new String();
		if (desktopOrMobile.equalsIgnoreCase("desktop")) {
			numberOfDesiredCaps = numberOfDesktopCapabilities;
			jobStatus = aSMD.handleParentSleep(numberOfDesktopCapabilities);
			if(System.getProperty("runLocallyAs") == null) {
				myCurrentJobStatus = aSM.jobStatusChildren(jobStatus,
						numberOfMobileCapabilities);
			}
		}
		if (desktopOrMobile.equalsIgnoreCase("mobile")) {
			numberOfDesiredCaps = numberOfMobileCapabilities;
			jobStatus = aSMD.handleParentSleep(numberOfMobileCapabilities);
			if(System.getProperty("runLocallyAs") == null) {
				myCurrentJobStatus = aSM.jobStatusChildren(jobStatus,
						numberOfMobileCapabilities);
			}
			HashMap<String, String> mobileTestCaseResults = rGP
					.convertHashMapStringToHashMap(System
							.getProperty("mobileTestCasesResultsHashMap"));
			System.out.println(mobileTestCaseResults.toString());
		}

		HashMap<String, String> abc = MSGSalesCenterRegressionScript.isMyTestPassedHashMap;
		if(System.getProperty("runLocallyAs") != null ) {
			System.out.println(abc);
		} else {
			List<HashMap<String, String>> completeTestCaseResults = aSM
					.individualTestCaseStatus(jobStatus,
							individualTestCaseStatusName, numberOfDesiredCaps);
			System.out.println(jobStatus);
			System.out.println(individualTestCaseStatusName);
			String failureList = new String();
			String entireFailureList = new String();
			String testCaseNameFromResults = new String();
			for (int jobStatusIncrementor = 0; jobStatusIncrementor < numberOfDesiredCapabilities; jobStatusIncrementor++) {
				if ((completeTestCaseResults.get(jobStatusIncrementor)
						.get("testStepStatus").contains("FAIL"))) {
					failureList = completeTestCaseResults.get(jobStatusIncrementor)
							.get("testStepPlatform")
							+ " "
							+ completeTestCaseResults.get(jobStatusIncrementor)
							.get("testStepBrowser")
							+ " "
							+ completeTestCaseResults.get(jobStatusIncrementor).get(
							"testStepBrowserVersion")
							+ " HAS FAILED\r\n";
					entireFailureList = entireFailureList + failureList;
					testCaseResultsToJobReport.put(jiraStoryPrefix
							+ testCaseJiraStoryNumber + "EXECUTIONSTATUS", "FAIL");
					testCaseResultsToJobReport.put(jiraStoryPrefix
									+ testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR",
							"#CC3300");
					testCaseNameFromResults = completeTestCaseResults
							.get(jobStatusIncrementor).get("testCaseName");
				} else {
					testCaseResultsToJobReport.put(jiraStoryPrefix
							+ testCaseJiraStoryNumber + "EXECUTIONSTATUS", "PASS");
					testCaseResultsToJobReport.put(jiraStoryPrefix
									+ testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR",
							"#75B000");
					testCaseNameFromResults = completeTestCaseResults
							.get(jobStatusIncrementor).get("testCaseName");
				}
				testCaseResultsToJobReport.put(
						jiraStoryPrefix + testCaseJiraStoryNumber + "EXECUTEDON",
						rGP.getCurrentTimestamp());
				// mJU.updateStatusForSingleTestCasesInJira(completeTestCaseResults.get(jobStatusIncrementor).get("testCaseName"),
				// completeTestCaseResults.get(jobStatusIncrementor).get("testStepStatus"));
				System.out.println(completeTestCaseResults.get(jobStatusIncrementor)
						.get("testCaseName"));
				System.out.println(completeTestCaseResults.get(jobStatusIncrementor)
						.get("testStepParameters"));
				System.out.println(completeTestCaseResults.get(jobStatusIncrementor)
						.get("testStepStatus"));
				System.out.println("JIRA Steps Parameters are: "
						+ completeTestCaseResults
						.get(jobStatusIncrementor).get("testCaseName")
						+ " another one: "
						+ completeTestCaseResults.get(jobStatusIncrementor)
						.get("testStepParameters")
						+ " last one: " + completeTestCaseResults
						.get(jobStatusIncrementor).get("testStepStatus"));
				mJU.updateStatusForSingleTestStepsInJira(
						completeTestCaseResults.get(jobStatusIncrementor)
								.get("testCaseName"),
						completeTestCaseResults.get(jobStatusIncrementor)
								.get("testStepParameters"),
						completeTestCaseResults.get(jobStatusIncrementor)
								.get("testStepStatus"));
			}
			System.out.println(
					"TEST CASE NAME FROM RESULTS: " + testCaseNameFromResults);
			System.out.println("ENTIRE FAILURE LIST: " + entireFailureList);
			if (!(entireFailureList.equalsIgnoreCase(""))) {
				mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults,
						"FAIL");
			} else {
				mJU.updateStatusForSingleTestCasesInJira(testCaseNameFromResults,
						"PASS");
			}
			String filePath = "reports/";
			String attachmentPath = filePath
					+ rGP.getFolderContents(filePath).get("files").get(0);
			String issueKey = jiraStoryPrefix + "-" + testCaseJiraStoryNumber;
			System.out.println(issueKey);
			System.out.println(attachmentPath);
			mJU.createAnAttachmentInJira(issueKey, attachmentPath);

			HTMLToSMTPFunctions hTSF = new HTMLToSMTPFunctions();
			// reportRowArray.set(reportRowIncrementor,
			// hTSF.reportRowBuilder(String.valueOf(reportRowIncrementor),
			// "https://thegarden.atlassian.net/browse/MSGEW-" +
			// testCaseJiraStoryNumber, "MSGEW-" + testCaseJiraStoryNumber,
			// testCaseResultsToJobReport.get(jiraStoryPrefix +
			// testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"),
			// testCaseResultsToJobReport.get(jiraStoryPrefix +
			// testCaseJiraStoryNumber + "EXECUTIONSTATUS"),"Rockettes",
			// "Home Page", "MSGTesting",
			// testCaseResultsToJobReport.get(jiraStoryPrefix +
			// testCaseJiraStoryNumber + "EXECUTEDON")));
			// Commented for addressing MDT
			// reportRowArray.set(1, hTSF.reportRowBuilder(String.valueOf(1),
			// "https://thegarden.atlassian.net/browse/MSGEW-" +
			// testCaseJiraStoryNumber, "MSGEW-" + testCaseJiraStoryNumber,
			// testCaseResultsToJobReport.get(jiraStoryPrefix +
			// testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"), "","Rockettes",
			// "Home Page", "MSGTesting",
			// testCaseResultsToJobReport.get(jiraStoryPrefix +
			// testCaseJiraStoryNumber + "EXECUTEDON")));
			reportRowArray.set(1, hTSF.reportRowBuilder(String.valueOf(1),
					"https://thegarden.atlassian.net/browse/" + jiraStoryPrefix
							+ "-" + testCaseJiraStoryNumber,
					jiraStoryPrefix + "-" + testCaseJiraStoryNumber,
					testCaseResultsToJobReport.get(jiraStoryPrefix
							+ testCaseJiraStoryNumber + "STATUSBACKGROUNDCOLOR"),
					"", "Sales Center", "Venue Rentals", "MSGTesting",
					testCaseResultsToJobReport.get(jiraStoryPrefix
							+ testCaseJiraStoryNumber + "EXECUTEDON")));
			reportRowIncrementor++;
			if (!(entireFailureList.equalsIgnoreCase(""))) {
				Assert.fail(entireFailureList);
			}
		}

	}
}
