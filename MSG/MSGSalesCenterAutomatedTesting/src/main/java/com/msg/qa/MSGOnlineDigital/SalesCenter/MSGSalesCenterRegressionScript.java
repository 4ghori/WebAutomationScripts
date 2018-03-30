package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.isIOSPlatform;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.waitForElement;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.msg.qa.common.DriverFactory;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import com.msg.qa.common.MSGOnlineDigitalGlobalParameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.Parsers.TestExcelParser;
import Utility.SauceLabs.MSGSalesCenterSauceJobAccessController;

/**
 * Utility class for JIRA REST API
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Sales Center Regression script
 * @since 05/10/2017, 5:00PM EST
 */

public class MSGSalesCenterRegressionScript extends Thread {

	// SauceLabs User Name and Access Key - to be read from Environment
	// Variables
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URLwithEnvVariables = "https://" + USERNAME + ":"
			+ ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	// SauceLabs User Name and Access Key - Fallback in case Environment
	// variables are not setup
	public static final String myUSERNAME = "rachit";
	public static final String myACCESS_KEY = "05d919ba-84e4-49fb-b84c-69f8af1918bf";
	public static final String URLwithHardCodedVariables = "https://"
			+ myUSERNAME + ":" + myACCESS_KEY
			+ "@ondemand.saucelabs.com:443/wd/hub";

	// DesiredCapabiltiies Parameters
	public static final String myBrowserTag = "browsers", myChrome = "chrome",
			mySafari = "safari", myEdge = "edge", myIE = "IE",
			myFirefox = "fiefox", myPlatformCapability = "platform",
			myVersionCapability = "version",
			myScreenResolutionCapability = "screenresolution";

	// E-Mail Parameters
	public static String myEmailFrom = "MSGTesting@msg.com",
			myEmailToList = "sri.anne@msg.com; richard.barrow@msg.com; krishna.chaparala@msg.com; snehal.chudgar@msg.com; rachitkumar.rastogi@msg.com",
			myEmailFailedSubject = "Jenkins Error - MSG Sales Center Regression Script is failed!",
			myEmailSuccessSubject = "Jenkins Success - MSG Sales Center Regression Script is successful.",
			myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that MSG Sales Center Regression Script has been failed at "
					+ java.text.DateFormat.getDateTimeInstance()
							.format(Calendar.getInstance().getTime())
					+ "." + "\r\nThanks, Your MSG QA Automation Team.",
			myEmailBody = "Hi, \r\nThis is to bring to your notice that MSG Sales Center Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.";

	// Webdriver Variables
	public static boolean isMyTestPassed = false;
	public static ExtentReports myExtentReports = Utility.Reports.ExtentManager
			.getInstance();
	public static ExtentTest myExtentTest;
	public static HashMap<String, String> isMyTestPassedHashMap = new HashMap<String, String>();
	static Map<String, String[]> myDesiredSelectors;
	public Map<String, String[]> mySelectors = new HashMap<String, String[]>();
	public HashMap<String, List<String>> myTestCasesWithSteps = new HashMap<String, List<String>>();
	public String testCase;
	public int platformExecutionIncrementor;
	public Hashtable testResults = new Hashtable();
	String myDriverParameters = null;
	WebDriver myDriver = null;
	DesiredCapabilities myDesiredCapabililties;
	
	
	/**
	 * Marked as false hard-coded on 03/12/2018 by Rachit Kumar Rastogi
	 * Once we get the License Back make sure you enabled back the commented string to
	 * read from jenkins env Variable.
	 */
	//public static String executeVisualTest = System.getProperty("executeVisualTest");
	public static String executeVisualTest = "false";

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterRegressionScript() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param driverParameters
	 * @param desiredCapabilities
	 */
	public MSGSalesCenterRegressionScript(WebDriver driver,
			String driverParameters, DesiredCapabilities desiredCapabilities,
			String testCaseName, Map<String, String[]> desiredSelectors,
			HashMap<String, List<String>> testCasesWithSteps) {
		this.myDriver = driver;
		this.myDriverParameters = driverParameters;
		this.myDesiredCapabililties = desiredCapabilities;
		try {
			TestExcelParser myExcelParser = new TestExcelParser();
			this.mySelectors = myExcelParser.getlistOfAllSelectors();
			this.myTestCasesWithSteps = myExcelParser
					.getAllTestStepsAssignedToAllTestCase(
							System.getProperty("testCycle"));
			// this.myTestCasesWithSteps = testCasesWithSteps;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		testCase = testCaseName;
		platformExecutionIncrementor = 0;
		myDesiredSelectors = desiredSelectors;

	}

	/**
	 * Execution start point, default main method.
	 *
	 * @param args
	 */
	public static void main(String args[])
			throws FileNotFoundException, IOException, ParseException,
			InterruptedException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		/**
		 * Step-1: Build Number for Saucelabs identification.
		 */
		final String myBuildNumber = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());

		/**
		 * Step-2: Excel Parser for TEST meta data.
		 */
		// TestExcelParser myExcelParser = new TestExcelParser();
		// mySelectors = myExcelParser.getlistOfAllSelectors();
		// myTestCasesWithSteps =
		// myExcelParser.getAllTestStepsAssignedToAllTestCase();

		/**
		 * Step-3: Get Local web driver Instance
		 */
		// executeLocalWebDriver(myBuildNumber);

		/**
		 * Step-3: Get remote web driver Instance - Manual settings
		 */
		// executeDirectRemoteWebDriverInSauceLabs(myBuildNumber);

		/**
		 * Step-3: Get Web driver Instance - Jenkins
		 */
		// executeRemoteWebDriverInSauceLabsViaJenkins(myBuildNumber);
	}

	/**
	 * ---- Step-2: Test MSG.com Landing Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSG.com Landing Page")
	public static boolean MSGTC000LandingPage(WebDriver driver, String myURL,
			boolean isMyTestPassed, Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGTC000LandingPage myMSGLandingPage = new MSGTC000LandingPage(driver,
				myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC000LandingPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		/**
		 * 1. Check if we are on MSG.com landing Page - LANDING PAGE NAVIGATION 
		 */

		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		/**
		 * 2. TOP ADVERTISEMENT IS DISPLAYED
		 */	
		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 3. GLOBAL NAV
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 4. IMAGE SLIDER UNDER MEGA NAV
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 5. EVENT SEARCH OPTIONS
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 6. SECTION BETWEEN EVENT SEARCH AND HAPPENING
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 7. HAPPENING IN ALL CITIES
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 8. ADVERTISEMENT BETWEEN IMAGE CAROUSEL AND SLICK SLIDER
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 9. ON SALE SOON
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 10. SLICK SLIDER WITH LARGE IMAGES
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS010"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS010();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 11. VENUES VERIFICATION
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS011"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS011();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 12. ADVERTISEMENT BETWEEN VENUES AND FOOTER
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS012"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS012();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 13. EMAIL CAPTURE
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS013"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS013();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * 14. FOOTER VALIDATIONS
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS014"))
			isMyTestPassed = myMSGLandingPage.MSGTC000LandingPageTS014();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGLandingPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println("Steps Status for MSGTC000LandingPage: "
				+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Step-2: Test MSGSalesCenter Venue Rentals Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSGSalesCenter Venue Rentals Page")
	public static boolean MSGSalesCenterTC001VenueRentalsPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGSalesCenterTC001VenueRentalsPage myMSGSalesCenterVenueRentalsPage = new MSGSalesCenterTC001VenueRentalsPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC001VenueRentalsPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC001VenueRentalsPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * 2. Verify the Header Section: Object/Image/Video/ Link/
		 * Text/Label/Button - Validation Parameters a. Company Logo - Present
		 * b. All clickable links redirect to a new window or appropriate
		 * section in the venue page - Verified c. Email - Leads to outlook
		 * email compose section d. Call link - Leads to contact us /(acc to bus
		 * req)
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS010"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS010();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS011"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS011();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS012"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS012();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS013"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS013();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS014"))
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage
					.MSGSalesCenterTC001VenueRentalsPageTS014();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterVenueRentalsPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}

	/**
	 * ---- Step-2: Test MSG.com Venue Landing Page ----
	 **/
	@Test(description = "Test MSG.com Venue Landing Page")
	public static boolean MSGTC002VenueLandingPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters)
			throws Exception {
		MSGTC002VenueLandingPage myMSGVenueLandingPage = new MSGTC002VenueLandingPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC002VenueLandingPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println("Test to be executed for MSGTC002VenueLandingPage: "
				+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG.com landing Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGVenueLandingPage
					.MSGTC002VenueLandingPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGVenueLandingPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println("Steps Status for MSGTC002VenueLandingPage: "
				+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Group Outings Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSGSalesCenter Group Outings Page")
	public static boolean MSGSalesCenterTC003CorporateEntertainmentPage(
			WebDriver driver, String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGSalesCenterTC003CorporateEntertainmentPage myMSGSalesCenterTC003CorporateEntertainmentPage = new MSGSalesCenterTC003CorporateEntertainmentPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC003CorporateEntertainmentPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage
					.MSGSalesCenterTC003CorporateEntertainmentPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC003CorporateEntertainmentPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Steps Status for MSGSalesCenterTC003CorporateEntertainmentPage: "
						+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Premium Hospitality Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSGSalesCenter Premium Hospitality Page")
	public static boolean MSGSalesCenterTC004PremiumHospitalityPage(
			WebDriver driver, String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGSalesCenterTC004PremiumHospitalityPage myMSGSalesCenterPremiumHospitalityPage = new MSGSalesCenterTC004PremiumHospitalityPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC004PremiumHospitalityPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC004PremiumHospitalityPage: "
						+ myPageClassMethodsToBeExecuted);

		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage
					.MSGSalesCenterTC004PremiumHospitalityPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterPremiumHospitalityPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Steps Status for MSGSalesCenterTC004PremiumHospitalityPage: "
						+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Group Outings Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSGSalesCenter Group Outings Page")
	public static boolean MSGSalesCenterTC005GroupOutingsPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, 
			String myDriverParameters)
			throws Exception {
		MSGSalesCenterTC005GroupOutingsPage myMSGSalesCenterTC005GroupOutingsPage = new MSGSalesCenterTC005GroupOutingsPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC005GroupOutingsPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC005GroupOutingsPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage
					.MSGSalesCenterTC005GroupOutingsPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage
					.MSGSalesCenterTC005GroupOutingsPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage
					.MSGSalesCenterTC005GroupOutingsPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage
					.MSGSalesCenterTC005GroupOutingsPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage
					.MSGSalesCenterTC005GroupOutingsPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC005GroupOutingsPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Steps Status for MSGSalesCenterTC005GroupOutingsPage: "
						+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Calendar Page ----
	 **/
	@Test(description = "Test MSGSalesCenter Calendar Page")
	public static boolean MSGTC006CalendarPage(WebDriver driver, String myURL,
			boolean isMyTestPassed, Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGTC006CalendarPage myMSGTC006CalendarPage = new MSGTC006CalendarPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC006CalendarPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println("Test to be executed for MSGTC006CalendarPage: "
				+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGTC006CalendarPage.MSGTC006CalendarPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		
		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC006CalendarPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println("Steps Status for MSGTC006CalendarPage: "
				+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Event Details Page ----
	 **/
	@Test(description = "Test MSG Event Details Page")
	public static boolean MSGTC007EventDetailsPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGTC007EventDetailsPage myMSGTC007EventDetailsPage = new MSGTC007EventDetailsPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC007EventDetailsPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println("Test to be executed for MSGTC007EventDetailsPage: "
				+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Event Details.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));		
		
		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));		
		
		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		
		if (myPageClassMethodsToBeExecuted.contains("TS010"))
			isMyTestPassed = myMSGTC007EventDetailsPage
					.MSGTC007EventDetailsPageTS010();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC007EventDetailsPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println("Steps Status for MSGTC007EventDetailsPage: "
				+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Venue rentals MSG Radio City Page
	 * ----
	 **/
	@Test(description = "Test MSGSalesCenter Premium Hospitality Lexus Suites Page")
	public static boolean MSGSalesCenterTC008VenueRentalsMSGRadioCityPage(
			WebDriver driver, String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGSalesCenterTC008VenueRentalsMSGRadioCityPage myMSGSalesCenterTC008VenueRentalsMSGRadioCityPage = new MSGSalesCenterTC008VenueRentalsMSGRadioCityPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC008VenueRentalsMSGRadioCityPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC008VenueRentalsMSGRadioCityPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC008VenueRentalsMSGRadioCityPage
					.MSGSalesCenterTC008VenueRentalsMSGRadioCityPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC008VenueRentalsMSGRadioCityPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}

	/**
	 * ---- Added Test: MSGSalesCenter Premium Hospitality Event Level Suites Page
	 * ----
	 **/
	@Test(description = "Test MSGSalesCenter Premium Hospitality Lexus Suites Page")
	public static boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage(
			WebDriver driver, String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps,
			String myDriverParameters) throws Exception {
		MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage = new MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage
					.MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC009PremHospMSGEventLevelSuitesPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Steps Status for MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage: "
						+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSGSalesCenter Venue Tours Page ----
	 **/
	@Test(description = "Test MSG.com Venue Tours Page")
	public static boolean MSGSalesCenterTC010VenueToursPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGSalesCenterTC010VenueToursPage myMSGSalesCenterTC010VenueToursPage = new MSGSalesCenterTC010VenueToursPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC010VenueToursPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC010VenueToursPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC010VenueToursPage
					.MSGSalesCenterTC010VenueToursPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGSalesCenterTC010VenueToursPage
					.MSGSalesCenterTC010VenueToursPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC010VenueToursPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out
				.println("Steps Status for MSGSalesCenterTC010VenueToursPage: "
						+ PageClassStepsExecutionStatus);
		return !PageClassStepsExecutionStatus.contains("false");
	}

	/**
	 * ---- Added Test: MSG.com Premium Hospitality Knicks locker Page ----
	 **/
	@Test(description = "Test MSG.com Knicks Tickets Page")
	public static boolean MSGSalesCenterTC011PremHospMSGKnicksPage(
			WebDriver driver, String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGSalesCenterTC011PremHospMSGKnicksPage myMSGSalesCenterTC011PremHospMSGKnicksPage = new MSGSalesCenterTC011PremHospMSGKnicksPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGSalesCenterTC011PremHospMSGKnicksPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println(
				"Test to be executed for MSGSalesCenterTC011PremHospMSGKnicksPage: "
						+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGSalesCenterTC011PremHospMSGKnicksPage
					.MSGSalesCenterTC011PremHospMSGKnicksPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGSalesCenterTC011PremHospMSGKnicksPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}

	/**
	 * ---- Added Test: MSG.com Knicks Tickets Page ----
	 **/
	@Test(description = "Test MSG.com Knicks Tickets Page")
	public static boolean MSGTC012KnicksTicketsPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGTC012KnicksTicketsPage myMSGTC012KnicksTicketsPage = new MSGTC012KnicksTicketsPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC012KnicksTicketsPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		System.out.println("Test to be executed for MSGTC012KnicksTicketsPage: "
				+ myPageClassMethodsToBeExecuted);

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS002"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS002();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS003"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS003();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS004"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS004();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS005"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS005();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS006"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS006();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS007"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS007();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS008"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS008();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS009"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS009();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS011"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS011();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS012"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS012();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		if (myPageClassMethodsToBeExecuted.contains("TS013"))
			isMyTestPassed = myMSGTC012KnicksTicketsPage
					.MSGTC012KnicksTicketsPageTS013();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC012KnicksTicketsPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}

	/**
	 * ---- Added Test: MSG.com Rangers Tickets Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSG.com Rangers Tickets Page")
	public static boolean MSGTC013RangersTicketsPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGTC013RangersTicketsPage myMSGTC013RangersTicketsPage = new MSGTC013RangersTicketsPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC013RangersTicketsPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC013RangersTicketsPage
					.MSGTC013RangersTicketsPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC013RangersTicketsPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}
	
	/**
	 * ---- Added Test: MSG.com Rangers Tickets Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSG.com MSG Venue Guides Page")
	public static boolean MSGTC014MSGVenueGuidePage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGTC014MSGVenueGuidePage myMSGTC014MSGVenueGuidePage = new MSGTC014MSGVenueGuidePage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC014MSGVenueGuidePage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC014MSGVenueGuidePage.MSGTC014MSGVenueGuidePageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC014MSGVenueGuidePage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}
	
	/**
	 * ---- Added Test: MSG.com Search Page ----
	 *
	 * @param myTestCasesWithSteps
	 **/
	@Test(description = "Test MSG.com Rangers Tickets Page")
	public static boolean MSGTC015SearchPage(WebDriver driver,
			String myURL, boolean isMyTestPassed,
			Map<String, String[]> mySelectors,
			HashMap<String, List<String>> myTestCasesWithSteps, String myDriverParameters)
			throws Exception {
		MSGTC015SearchPage myMSGTC015SearchPage = new MSGTC015SearchPage(
				driver, myURL, isMyTestPassed, mySelectors, myDriverParameters);

		List<String> myPageClassMethodsToBeExecuted = myTestCasesWithSteps
				.get("MSGTC015SearchPage");
		List<String> PageClassStepsExecutionStatus = new ArrayList<String>();

		/**
		 * 1. Check if we are on MSG Sales Center Venue Rentals Page.
		 */
		if (myPageClassMethodsToBeExecuted.contains("TS001"))
			isMyTestPassed = myMSGTC015SearchPage.MSGTC015SearchPageTS001();
		PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));

		/**
		 * Code piece to address Visual Test integration in CI/CD Pipeline for msg.com
		 */
		if(executeVisualTest.matches("true"))
		{
			isMyTestPassed = myMSGTC015SearchPage.executeVisualTest();
			PageClassStepsExecutionStatus.add(String.valueOf(isMyTestPassed));
		}
		
		System.out.println(
				"Different Steps status are: " + PageClassStepsExecutionStatus);

		if (PageClassStepsExecutionStatus.contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}

		return isMyTestPassed;
	}

	@Test(description = "Kill the web-driver instance")
	public static void killWebDriverInstance(WebDriver driver) {
		/*
		 * 1. Kill WebDriver Instance. a. Kill driver's Instance and close the
		 * session
		 */
		String message = String.format(
				"SauceOnDemandSessionID=%1$s job-name=%2$s",
				(((RemoteWebDriver) driver).getSessionId()).toString(),
				"MSG Sales Center Regression Script");
		System.out
				.println("Jenkins Build status Update Parameters: " + message);

		driver.quit();
	}

	private static String[] RemoveDuplicateURLs(String FailedURLs) {
		String[] myFailedURLList = FailedURLs.split("\r\n");
		Set<String> myUniqueURLs = new LinkedHashSet<>(
				Arrays.asList(myFailedURLList));
		return (String[]) myUniqueURLs.toArray(new String[myUniqueURLs.size()]);
	}

	@Test(description = "Get Local Webdriver Instance")
	public static WebDriver getLocalWebdriverInstance(String myURL) {
		System.setProperty("webdriver.chrome.driver",
				"resources/driver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(myURL);
		return driver;
	}

	/**
	 * --- Heart of the script, a thread method to keep all of the page classes ---
	 * method calls, sauce/Jira update etc.
	 */

	synchronized public void run() {

		/**
		 * Step-2: Remote Web-driver invocation is handled here: a. Creation of
		 * Driver b. Read the Capability c. Read the URL d. Create the WebDriver
		 * Object
		 */
		try {
			System.setProperty("testCasesStatus", "running");
			String URL = URLwithEnvVariables;
			if (URL.contains("null"))
				URL = URLwithHardCodedVariables;
			if (System.getProperty("runLocallyAs") != null) {
				DriverFactory driverFactory = new DriverFactory();
				this.myDriver = driverFactory
						.getWebDriver(System.getProperty("runLocallyAs"));
			} else {
				// driver.manage().timeouts().implicitlyWait(30,
				// TimeUnit.SECONDS);
				this.myDriver = new RemoteWebDriver(new URL(URL),
						myDesiredCapabililties);
			}

		} catch (MalformedURLException e) {
			killWebDriverInstance(this.myDriver);
			e.printStackTrace();
		}

		ReentrantLock myLock = new ReentrantLock();

		synchronized (this) {
			myLock.lock();
			try {
				// Get the Driver Instance for running the tests.

				System.out.println(
						"********************************* Start of Testing Individual Pages in Saucelabs *********************************");

				HashMap<String, String> myURLAddresses = new HashMap<String, String>();
				List<String> myFailedURLs = new ArrayList<String>();
				myFailedURLs.clear();

				String testEnvironment = System.getProperty("testEnvironment");

				if (testEnvironment == null) {
					testEnvironment = "production";
				}

				System.out.println("testEnvironment: " + testEnvironment);

				myURLAddresses = MSGSalesCenterURLs.getMSGURLs(testEnvironment);

				SessionId mySessionID = ((RemoteWebDriver) this.myDriver)
						.getSessionId();

				// ===Reporting Start From Here ====

				switch (testCase) {

					case "testMsgLandingPage" :

						myExtentTest = myExtentReports
								.startTest("MSGTC000LandingPage" + " - "
										+ "Sales Center Venue Rentals");

						/**
						 * Step-3: Page class method are handled in the below
						 * script: a. Test MSG.com Landing Page
						 */
						try {
							isMyTestPassed = MSGTC000LandingPage(this.myDriver,
									myURLAddresses.get("MSGTC000LandingPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC000LandingPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Landing Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testSalesCenterVenueRentalsPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC001VenueRentalsPage - Sales Center Venue Rentals");
						try {
							isMyTestPassed = MSGSalesCenterTC001VenueRentalsPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC001VenueRentalsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC001VenueRentalsPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals");

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMsgVenueLandingPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGTC002VenueLandingPage - MSG.com Venue Landing Page");
						try {
							isMyTestPassed = MSGTC002VenueLandingPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC002VenueLandingPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC002VenueLandingPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Venue Landing Page");

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testSalesCenterCorporateEntertainmentPage" :

						myExtentTest = myExtentReports.startTest(

								"MSGSalesCenterTC003CorporateEntertainmentPage - Sales Center Corporate Entertainment Page");
						try {
							isMyTestPassed = MSGSalesCenterTC003CorporateEntertainmentPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC003CorporateEntertainmentPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC003CorporateEntertainmentPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Corporate Entertainment");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testSalesCenterPremiumHospitalityPage" :

						myExtentTest = myExtentReports.startTest(

								"MSGSalesCenterTC004PremiumHospitalityPage Sales Center Premium Hospitality Page");
						try {
							isMyTestPassed = MSGSalesCenterTC004PremiumHospitalityPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC004PremiumHospitalityPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC004PremiumHospitalityPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testSalesCenterGroupOutingsPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC005GroupOutingsPage Sales Center Group Outings Page");
						try {
							isMyTestPassed = MSGSalesCenterTC005GroupOutingsPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC005GroupOutingsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC005GroupOutingsPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Group Outings");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGCalendarPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGTC006CalendarPage - MSG.com Calendar Page");
						try {
							isMyTestPassed = MSGTC006CalendarPage(this.myDriver,
									myURLAddresses.get("MSGTC006CalendarPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC006CalendarPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals MSG Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGEventDetailsPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGTC007EventDetailsPage - MSG Event Details Page");
						try {
							isMyTestPassed = MSGTC007EventDetailsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC007EventDetailsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC007EventDetailsPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG Event Details Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGSalesCenterVenueRentalsMSGRadioCityPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage - Sales Center Venue Rentals MSG Page");
						try {
							isMyTestPassed = MSGSalesCenterTC008VenueRentalsMSGRadioCityPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals Radio City Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGSalesCenterPremHospMSGEventLevelSuitesPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage - Sales Center Premium Hospitality Event Level Suites Page");
						try {
							isMyTestPassed = MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality Event Level Suites Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGSalesCenterVenueToursPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC010VenueToursPage - MSG.com Venue Tours Page");
						try {
							isMyTestPassed = MSGSalesCenterTC010VenueToursPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC010VenueToursPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC010VenueToursPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Venue Tours Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGSalesCenterPremHospMSGKnicksPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGSalesCenterTC011PremHospMSGKnicksPage - Sales Center Premium Hospitality Knicks Locker Room Page");
						try {
							isMyTestPassed = MSGSalesCenterTC011PremHospMSGKnicksPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC011PremHospMSGKnicksPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add(
										"MSGSalesCenterTC011PremHospMSGKnicksPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality Knicks Locker Room Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGKnicksTicketsPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGTC012KnicksTicketsPage - MSG.com Knicks Tickets Page");
						try {
							isMyTestPassed = MSGTC012KnicksTicketsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC012KnicksTicketsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC012KnicksTicketsPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Knicks Tickets Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testMSGRangersTicketsPage" :

						myExtentTest = myExtentReports.startTest(
								"MSGTC013RangersTicketsPage - MSG.com Rangers Tickets Page");
						try {
							isMyTestPassed = MSGTC013RangersTicketsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC013RangersTicketsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed)
								myFailedURLs.add("MSGTC013RangersTicketsPage");
							else
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Knicks Tickets Page");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						break;

					case "testSalesCenterMobileTestCases" :
						String resultsDriverSuffix = "";

						System.out.println(this.myDriverParameters);

						if (this.myDriverParameters.toLowerCase()
								.contains("android")) {
							resultsDriverSuffix = "Android";
						}

						if (this.myDriverParameters.toLowerCase()
								.contains("iphone")) {
							resultsDriverSuffix = "Iphone";
						}

						if (this.myDriverParameters.toLowerCase()
								.contains("ipad")) {
							resultsDriverSuffix = "Ipad";
						}

						/**
						 * Test-case0: Landing Page
						 */

						myExtentTest = myExtentReports
								.startTest("Mobile Test: MSG.com Landing Page");

						try {
							isMyTestPassed = MSGTC000LandingPage(this.myDriver,
									myURLAddresses.get("MSGTC000LandingPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGTC000LandingPage");
								isMyTestPassedHashMap.put(
										"MSGLandingPage" + resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Landing Page");
								isMyTestPassedHashMap.put(
										"MSGLandingPage" + resultsDriverSuffix,
										"PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGLandingPage" + resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case1: Venue Rental Page:
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Venue Rental Page");

						try {
							isMyTestPassed = MSGSalesCenterTC001VenueRentalsPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC001VenueRentalsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterTC001VenueRentalsPage");
								isMyTestPassedHashMap
										.put("MSGSalesCenterVenueRentalsPage"
												+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals");
								isMyTestPassedHashMap
										.put("MSGSalesCenterVenueRentalsPage"
												+ resultsDriverSuffix, "PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterVenueRentalsPage"
											+ resultsDriverSuffix, "FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterVenueRentalsPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterVenueRentalsPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case2: Venue Landing Page:
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: MSG.com Venue Landing Page");

						try {
							isMyTestPassed = MSGTC002VenueLandingPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC002VenueLandingPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGTC002VenueLandingPage");
								isMyTestPassedHashMap.put("MSGVenueLandingPage"
										+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Venue Landing Page");
								isMyTestPassedHashMap.put("MSGVenueLandingPage"
										+ resultsDriverSuffix, "PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGVenueLandingPage" + resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case3: Premium Hospitality Page:
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Corporate Entertainment Page");

						try {
							isMyTestPassed = MSGSalesCenterTC003CorporateEntertainmentPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC003CorporateEntertainmentPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterTC003CorporateEntertainmentPage");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterCorporateEntertainmentPage"
												+ resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Corporate Entertainment");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterCorporateEntertainmentPage"
												+ resultsDriverSuffix,
										"PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterCorporateEntertainmentPage"
											+ resultsDriverSuffix,
									"FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterCorporateEntertainmentPage"
											+ resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterCorporateEntertainmentPage"
											+ resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case4: Premium Hospitality Page:
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Premium Hospitality Page");

						try {
							isMyTestPassed = MSGSalesCenterTC004PremiumHospitalityPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC004PremiumHospitalityPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterTC004PremiumHospitalityPage");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremiumHospitalityPage"
												+ resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremiumHospitalityPage"
												+ resultsDriverSuffix,
										"PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremiumHospitalityPage"
											+ resultsDriverSuffix, "FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremiumHospitalityPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremiumHospitalityPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case6: MSG.com Calendar Page
						 */

						myExtentTest = myExtentReports
								.startTest("Mobile Test: Calendar Page");

						try {

							isMyTestPassed = MSGSalesCenterTC005GroupOutingsPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC005GroupOutingsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterTC005GroupOutingsPage");
								isMyTestPassedHashMap
										.put("MSGSalesCenterGroupOutingsPage"
												+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Venue Landing Page");
								isMyTestPassedHashMap
										.put("MSGSalesCenterGroupOutingsPage"
												+ resultsDriverSuffix, "PASS");
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterGroupOutingsPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case3: Premium Hospitality Page:
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Corporate Entertainment Page");
						try {
							isMyTestPassed = MSGTC006CalendarPage(this.myDriver,
									myURLAddresses.get("MSGTC006CalendarPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGCalendarPage");
								isMyTestPassedHashMap.put(
										"MSGCalendarPage" + resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals MSG");
								isMyTestPassedHashMap.put(
										"MSGCalendarPage" + resultsDriverSuffix,
										"PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGCalendarPage" + resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case7: Venue Rental Delta Sky Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: MSG Event Details Page");

						try {
							isMyTestPassed = MSGTC007EventDetailsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC007EventDetailsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGEventDetailsPage");
								isMyTestPassedHashMap.put("MSGEventDetailsPage"
										+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"MSG Event Details");
								isMyTestPassedHashMap.put("MSGEventDetailsPage"
										+ resultsDriverSuffix, "PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGEventDetailsPage" + resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case8: Venue Rental Radio City Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Venue Rental Radio City Page");

						try {
							isMyTestPassed = MSGSalesCenterTC008VenueRentalsMSGRadioCityPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC008VenueRentalsMSGRadioCityPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterVenueRentalsMSGRadioCityPage");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterVenueRentalsMSGRadioCityPage"
												+ resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Venue Rentals Radio City");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterVenueRentalsMSGRadioCityPage"
												+ resultsDriverSuffix,
										"PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterVenueRentalsMSGRadioCityPage"
											+ resultsDriverSuffix,
									"FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterVenueRentalsMSGRadioCityPage"
											+ resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterVenueRentalsMSGRadioCityPage"
											+ resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case9: Venue Rental Premium Hospitality Event
						 * Level Suites Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Premium Hospitality Event Level Suites Page");

						try {
							isMyTestPassed = MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps,
									this.myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterPremHospMSGEventLevelSuitesPage");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremHospMSGEventLevelSuitesPage"
												+ resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality Event Level Suites");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremHospMSGEventLevelSuitesPage"
												+ resultsDriverSuffix,
										"PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put(
									"MSGSalesCenterPremHospMSGEventLevelSuitesPage"
											+ resultsDriverSuffix,
									"FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case10: Venue Tours Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: MSG.com Venue Tours Page");

						try {
							isMyTestPassed = MSGSalesCenterTC010VenueToursPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC010VenueToursPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs
										.add("MSGSalesCenterVenueToursPage");
								isMyTestPassedHashMap
										.put("MSGSalesCenterVenueToursPage"
												+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"MSG.com Venue Tours Page");
								isMyTestPassedHashMap
										.put("MSGSalesCenterVenueToursPage"
												+ resultsDriverSuffix, "PASS");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterVenueToursPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case11: Venue Rental Premium Hospitality Knicks
						 * Lockers Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Sales Center Premium Hospitality Knicks Lockers Page");

						try {
							isMyTestPassed = MSGSalesCenterTC011PremHospMSGKnicksPage(
									this.myDriver,
									myURLAddresses.get(
											"MSGSalesCenterTC011PremHospMSGKnicksPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add(
										"MSGSalesCenterPremHospMSGKnicksPage");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremHospMSGKnicksPage"
												+ resultsDriverSuffix,
										"FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Sales Center Premium Hospitality Knicks Locker");
								isMyTestPassedHashMap.put(
										"MSGSalesCenterPremHospMSGKnicksPage"
												+ resultsDriverSuffix,
										"PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremHospMSGKnicksPage"
											+ resultsDriverSuffix, "FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremHospMSGKnicksPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap
									.put("MSGSalesCenterPremHospMSGKnicksPage"
											+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case12: Msg.com Knicks Tickets Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Msg.com Knicks Tickets Page");

						try {
							isMyTestPassed = MSGTC012KnicksTicketsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC012KnicksTicketsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGKnicksTicketsPage");
								isMyTestPassedHashMap.put("MSGKnicksTicketsPage"
										+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Msg.com Knicks Tickets");
								isMyTestPassedHashMap.put("MSGKnicksTicketsPage"
										+ resultsDriverSuffix, "PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGKnicksTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGKnicksTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGKnicksTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * Test-case13: Msg.com Rangers Tickets Page
						 */

						myExtentTest = myExtentReports.startTest(
								"Mobile Test: Msg.com Rangers Tickets Page");

						try {
							isMyTestPassed = MSGTC013RangersTicketsPage(
									this.myDriver,
									myURLAddresses
											.get("MSGTC013RangersTicketsPage")
											.toString(),
									isMyTestPassed, mySelectors,
									myTestCasesWithSteps, myDriverParameters);
							if (!isMyTestPassed) {
								myFailedURLs.add("MSGRangersTicketsPage");
								isMyTestPassedHashMap
										.put("MSGRangersTicketsPage"
												+ resultsDriverSuffix, "FAIL");
							} else {
								myExtentTest.log(LogStatus.PASS,
										"Msg.com Knicks Tickets");
								isMyTestPassedHashMap
										.put("MSGRangersTicketsPage"
												+ resultsDriverSuffix, "PASS");
							}
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGRangersTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e2.printStackTrace();
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGRangersTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							isMyTestPassedHashMap.put("MSGRangersTicketsPage"
									+ resultsDriverSuffix, "FAIL");
							e.printStackTrace();
						}

						myExtentReports.endTest(myExtentTest);
						myExtentReports.flush();

						/**
						 * mobileTestCasesResultsHashMap
						 */

						if (System.getProperty("mobileTestCasesResultsHashMap")
								.contains("=")) {
							MSGOnlineDigitalGlobalParameters rGP = new MSGOnlineDigitalGlobalParameters(
									this.myDriver);
							rGP.convertHashMapStringToHashMap(
									System.getProperty(
											"mobileTestCasesResultsHashMap"));
							isMyTestPassedHashMap
									.putAll(rGP.convertHashMapStringToHashMap(
											System.getProperty(
													"mobileTestCasesResultsHashMap")));
						}
						System.setProperty("mobileTestCasesResultsHashMap",
								isMyTestPassedHashMap.toString());
						System.setProperty("testCasesStatus", "completed");

						break;

					default :

						break;
				}
				/**
				 * Step-4: Performing Post Test activities.
				 */
				System.out.println("-----> Performing Post Test activities: ");

				killWebDriverInstance(this.myDriver);

				/**
				 * Step-5: Set status in Saucelabs for the Job as Passed or
				 * Failed
				 */
				if (USERNAME == null && ACCESS_KEY == null) {
					MSGSalesCenterSauceJobAccessController mySauceJobStatusUpdate = new MSGSalesCenterSauceJobAccessController(
							myUSERNAME, myACCESS_KEY);
					Map<String, Object> myUpdates = new HashMap<String, Object>();
					if (myFailedURLs.size() > 0)
						myUpdates.put("passed", false);
					else
						myUpdates.put("passed", true);
					try {
						mySauceJobStatusUpdate.updateJobInfo(
								mySessionID.toString(), myUpdates);
						System.out.println(
								"SAUCE REST: Saucelabs Job Status is updated via Saucelabs REST API.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(
								"SAUCE REST: Saucelabs Job Status can't be updated via Saucelabs REST API.");
					}
				} else if (System.getProperty("runLocallyAs") == null) {
					MSGSalesCenterSauceJobAccessController mySauceJobStatusUpdate = new MSGSalesCenterSauceJobAccessController(
							USERNAME, ACCESS_KEY);
					Map<String, Object> myUpdates = new HashMap<String, Object>();
					if (myFailedURLs.size() > 0)
						myUpdates.put("passed", false);
					else
						myUpdates.put("passed", true);
					try {
						mySauceJobStatusUpdate.updateJobInfo(
								mySessionID.toString(), myUpdates);
						System.out.println(
								"SAUCE REST: Saucelabs Job Status is updated via Saucelabs REST API.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(
								"SAUCE REST: Saucelabs Job Status can't be updated via Saucelabs REST API.");
					}
				}

				/**
				 * Step-6: Set status in Saucelabs for the Job as Passed or
				 * Failed.
				 */
				ReadWriteLock myInnerLock = new ReentrantReadWriteLock();
				myInnerLock.writeLock().lock();
				try {
					if (myFailedURLs.size() > 0) {
						Thread.sleep(3000);
						String myFailedURL = null;
						int URLCount = 1;
						for (int count = 0; count < myFailedURLs
								.size(); count++) {
							if (myFailedURL == null)
								myFailedURL = "\r\n URL" + URLCount + ": "
										+ myURLAddresses.get(myFailedURLs
												.get(count).toString())
												.toString();
							else {
								if (count == 0) {
									myFailedURL = null;
									myFailedURL = "\r\n URL" + URLCount + ": "
											+ myURLAddresses
													.get(myFailedURLs.get(count)
															.toString())
													.toString();
								} else {
									myFailedURL = myFailedURL.concat("\r\n URL"
											+ URLCount + ": "
											+ myURLAddresses
													.get(myFailedURLs.get(count)
															.toString())
													.toString());
								}
							}
							URLCount++;
						}
						// new
						// MSGSalesCenterRegressionScript(this.myDriver).sendMailNotification("",
						// myEmailFailedSubject, myFailedURL);
						if (!myEmailFailedSubject.contains("Platform")) {
							myEmailFailedSubject = myEmailFailedSubject
									.concat(this.myDriverParameters);
						} else {
							myEmailFailedSubject = "Error: MSGSalesCenter Regression - ";
							myEmailFailedSubject = myEmailFailedSubject
									.concat(this.myDriverParameters);
						}

						/**
						 * Address the Issue of Duplicate URLs in Mail.
						 */

						String[] myFailedURLList = RemoveDuplicateURLs(
								myFailedURL);
						String myCleansedURLs = null;
						for (int i = 0; i < myFailedURLList.length; i++) {
							if (myCleansedURLs == null)
								myCleansedURLs = "\r\n " + myFailedURLList[i];
							else
								myCleansedURLs = myCleansedURLs
										.concat("\r\n " + myFailedURLList[i]);
						}

						System.out.println(
								"Please find below the list of failed URLs: "
										+ myCleansedURLs);

						// Clear the list of failed URLs.
						myFailedURLs.clear();
						myFailedURL = null;

					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.setProperty("testCasesStatus", "completed");
				} finally {
					myInnerLock.writeLock().unlock();
					System.setProperty("testCasesStatus", "completed");
				}
				System.out.println(
						"********************************* End of Testing Individual Pages in Saucelabs *********************************");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				myLock.unlock();
				System.setProperty("testCasesStatus", "completed");
			}
		}
	}

}
