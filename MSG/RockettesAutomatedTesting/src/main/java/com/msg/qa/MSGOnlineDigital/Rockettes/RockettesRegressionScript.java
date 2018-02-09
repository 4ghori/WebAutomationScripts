package com.msg.qa.MSGOnlineDigital.Rockettes;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/*
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
*/
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.msg.qa.MSGOnlineDigital.Rockettes.*;

public class RockettesRegressionScript extends Thread
{
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
	 			  					 myChrome = "chrome", mySafari = "safari", myEdge = "edge", myIE = "IE", myFirefox = "firefox",
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
	 	  
public boolean isMyTestPassed = false;	
String myDriverParameters = null;
WebDriver myDriver= null;
DesiredCapabilities myDesiredCapabililties;
public String testCase;
public int platformExecutionIncrementor;
public Hashtable testResults = new Hashtable();
public static HashMap<String, String> isMyTestPassedHashMap = new HashMap<String, String>();
static Map<String, String[]> myDesiredSelectors;
Boolean isMobile = false;

public static String executeVisualTest = System.getProperty("executeVisualTest");
public static String endOfSeasonTesting = System.getProperty("endOfSeasonTesting");

public RockettesRegressionScript(WebDriver driver, String driverParameters, DesiredCapabilities desiredCapabilities, String testCaseName, Map<String, String[]> desiredSelectors, Boolean isMobile)
{
	this.myDriver = driver;
	this.myDriverParameters = driverParameters;
//	desiredCapabilities = executeRemoteWebDriverInSauceLabsViaJenkins();
	this.myDesiredCapabililties = desiredCapabilities;
	testCase = testCaseName;
	platformExecutionIncrementor = 0;
	myDesiredSelectors = desiredSelectors;
	this.isMobile = isMobile;
}

@Test(description="My Test central") 
public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, InterruptedException
{		
	final String myBuildNumber = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	
	/* 1. Get WebDriver Instance */	
		
	//a. Get Local web driver Instance - Manual settings
	/*
	HashMap<String, String> myURLAddresses = new HashMap<String, String>();
	myURLAddresses = RockettesURLs.getRockettesStagingURLs();
	WebDriver myDriver = getLocalWebdriverInstance(myURLAddresses.get("rockettesnyssticketspage").toString());
	myDriver.manage().window().maximize();
	SessionId mySessionID = ((RemoteWebDriver)myDriver).getSessionId();
	Thread myRockettes = new RockettesRegressionScript(myDriver);	
	myRockettes.start();
	*/
		
	//b.Get remote web driver Instance - Manual settings - Uncomment it only when we are running Regression Script directly not via Jenkins
	//executeDirectRemoteWebDriverInSauceLabs(myBuildNumber);
	
	//c. Get Web driver Instance - Jenkins	
	//executeRemoteWebDriverInSauceLabsViaJenkins(myBuildNumber);
}



synchronized public void run()
{	
try {
	runChild("thread");
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}	
	
public int runChild(String threadOrNot) throws IOException, InterruptedException
{	
	// Creation of Driver
	//1. Read the Capability
	//2. Read the URL
	//3. Create the WebDriver Object
	String passOrFail = "";
	if (threadOrNot.equalsIgnoreCase("not")) {
		
	} else {
	try {
		String URL = URLwithEnvVariables;
		if (URL.contains("null")) URL = URLwithHardCodedVariables;	
		this.myDriver = new RemoteWebDriver(new URL(URL), myDesiredCapabililties);
	} catch (MalformedURLException e) {
		System.out.println(e + e.getMessage());
		e.printStackTrace();
	}
	
//	this.myDriver.manage().window().maximize();
	
	ReentrantLock myLock = new ReentrantLock();
	
	synchronized (this) {		
		myLock.lock();
			try
			{
	// Get the Driver Instance for running the tests.			
				
	System.out.println("********************************* Start of Test *********************************");
	
	HashMap<String, String> myURLAddresses = new HashMap<String, String>();
	List<String> myFailedURLs = new ArrayList<String>();
	myFailedURLs.clear();
	
	// Change here Staging or Dev for running tests accordingly.
	//myURLAddresses = RockettesURLs.getRockettesStagingURLs();
	
	String testEnvironment = System.getProperty("testEnvironment");
	RockettesURLs rURLs = new RockettesURLs();
	
	if (testEnvironment == null) {
		testEnvironment = "qa";
	}
	
	switch (testEnvironment) {
	
	case "qa":
		myURLAddresses = rURLs.getRockettesQaURLs();
		break;
		
	case "production":
		myURLAddresses = rURLs.getRockettesProductionsURLs();
		break;
		
	case "dev":
		myURLAddresses = rURLs.getRockettesDevURLs();
		break;
		
	case "poc":
		myURLAddresses = rURLs.getRockettesPocURLs();
		break;
		
	case "uat":
		myURLAddresses = rURLs.getRockettesUatURLs();
		break;		
			
	default:
		myURLAddresses = rURLs.getRockettesProductionsURLs();
		break;
	}
	
	SessionId mySessionID = ((RemoteWebDriver)this.myDriver).getSessionId();

	switch (testCase) {

	case "testRockettesHomePage":
	/* ----   Step-1: Test Rockettes Home Page   ---- */
		try {
			isMyTestPassed = RockettesHomePage(this.myDriver, myURLAddresses.get("rocketteshomepage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if (!isMyTestPassed) {
		myFailedURLs.add("rocketteshomepage");
		passOrFail = "FAIL";
	}
	break;

	case "testRockettesCSLandingPage":
	/* ----   Step-2: Test Rockettes -> Christmas Spectacular -> Landing Page   ---- */
	isMyTestPassed =  RockettesCSLandingPage(this.myDriver, myURLAddresses.get("rockettescslandingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescslandingpage");
		passOrFail = "FAIL";

	}
	break;
	
	case "testRockettesCSTickets":
	/* ----   Step-3: Test Rockettes -> Christmas Spectacular -> Calendar   ---- */ 	
	try {
		isMyTestPassed = RockettesCSTickets(this.myDriver, myURLAddresses.get("rockettescsticketspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		if (!isMyTestPassed) {
			myFailedURLs.add("rockettescsticketspage"); 
			passOrFail = "FAIL";
		}
	} catch (IOException | ParseException | InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		passOrFail = "FAIL";
	}
	break;
		
	case "testRockettesCSGroups":
	/* ----   Step-4:  Test Rockettes -> Christmas Spectacular -> Groups   ---- */ 
	isMyTestPassed = RockettesCSGroups(this.myDriver, myURLAddresses.get("rockettescsgroupspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescsgroupspage"); 
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesCSSeatingChart":
	/* ----   Step-5:  Test Rockettes -> Christmas Spectacular -> Seating Chart   ---- */ 
	isMyTestPassed = RockettesCSSeatingChart(this.myDriver, myURLAddresses.get("rockettescsseatingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescsseatingpage"); 
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesCSPromotions":
	/* ----   Step-6:  Test Rockettes -> Christmas Spectacular -> Promotions   ---- */  
		try {
			isMyTestPassed = RockettesCSCurrentOffers(this.myDriver, myURLAddresses.get("rockettescsofferspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescsofferspage");
				passOrFail = "FAIL";
			}
		} catch (Exception e5) {
			passOrFail = "FAIL";
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
	break;
	
	case "testRockettesCSPlanYourDay":
	/* ----   Step-7:  Test Rockettes -> Christmas Spectacular -> Plan-your-day   ---- */
		try {
			isMyTestPassed = RockettesCSPlanYourDay(this.myDriver, myURLAddresses.get("rockettescspldpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) myFailedURLs.add("rockettescspldpage");
			passOrFail = "FAIL";
		} catch (InterruptedException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescspldpage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesCSExperiences":
	/* ----   Step-8:  Test Rockettes -> Christmas Spectacular -> Experiences   ---- */  
	isMyTestPassed = RockettesCSExperiences(this.myDriver, myURLAddresses.get("rockettescsexppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescsexppage");	
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesCSNews":
	/* ----   Step-9:  Test Rockettes -> Christmas Spectacular -> News   ---- */ 
		try {
			isMyTestPassed = RockettesCSNews(this.myDriver, myURLAddresses.get("rockettescsnewspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescsnewspage");	
				passOrFail = "FAIL";
			}
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
			passOrFail = "FAIL";
		}
	break;
	
	case "testRockettesCSSignUp":
	/* ----   Step-10:  Test Rockettes -> Christmas Spectacular -> Sign up   ---- */  
		try {
			isMyTestPassed = RockettesCSEmailSignUp(this.myDriver, myURLAddresses.get("rockettescssignuppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) myFailedURLs.add("rockettescssignuppage");	
			passOrFail = "FAIL";
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}	
	break;
	
	case "testRockettesCSPartners":
	/* ----   Step-11:  Test Rockettes -> Christmas Spectacular -> Partners   ---- */   
		try {
			isMyTestPassed = RockettesCSPartners(this.myDriver, myURLAddresses.get("rockettescspartnerspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				passOrFail = "FAIL";
				myFailedURLs.add("rockettescspartnerspage");
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			e3.printStackTrace();
		}	
	break;
	
	case "testRockettesNYSLandingPage":
	/* ----   Step-12: Test Rockettes -> NYS Spectacular -> Landing Page   ---- */
		try {
			isMyTestPassed = RockettesNYSLandingPage(this.myDriver, myURLAddresses.get("rockettesnysslandingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				passOrFail = "FAIL";
				myFailedURLs.add("rockettesnysslandingpage");
			}
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			e2.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			e.printStackTrace();
		}
	break;
	
	case "testRockettesNYSTickets":
	/* ----   Step-13: Test Rockettes -> NYS Spectacular -> Tickets   ---- */ 
		/* ---- COMMENTED AS IT'S OUT OF SCOPE AS OF NOW ---- */
		/*
		try {
		    isMyTestPassed = RockettesNYSTickets(this.myDriver, myURLAddresses.get("rockettesnyssticketspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) myFailedURLs.add("rockettesnyssticketspage");
			passOrFail = "FAIL";
		} catch (IOException | ParseException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			passOrFail = "FAIL";
		}
		*/
	break;

	case "testRockettesNYSGroups":
	/* ----   Step-14: Test Rockettes -> NYS Spectacular -> Groups   ---- */ 
		try {
			isMyTestPassed = RockettesNYSGroups(this.myDriver, myURLAddresses.get("rockettesnyssgroupspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesnysgroupspage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesNYSSponsors":
	/* ----   Step-15: Test Rockettes -> NYS Spectacular -> Sponsors   ---- */ 
	isMyTestPassed = RockettesNYSSponsors(this.myDriver, myURLAddresses.get("rockettesnysssponsorspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesnysssponsorspage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesBlog":
	/* ----   Step-16: Test Rockettes -> Blog   ----    */
	isMyTestPassed = RockettesBlog(this.myDriver, myURLAddresses.get("rockettesblogpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesblogpage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesHistory":
	/* ----   Step-17: Test Rockettes -> History   ---- */  
		try {
			isMyTestPassed = RockettesHistory(this.myDriver, myURLAddresses.get("rocketteshistorypage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
	if (!isMyTestPassed) {
		myFailedURLs.add("rocketteshistorypage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesPhotos":
	/* ----   Step-18: Test Rockettes -> Photos   ---- */   
	isMyTestPassed = RockettesPhotos(this.myDriver, myURLAddresses.get("rockettesphotospage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesphotospage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesDanceEducation":
	/* ----   Step-19: Test Rockettes -> Dance Education   ---- */  
		try {
			isMyTestPassed = RockettesDanceEducation(this.myDriver, myURLAddresses.get("rockettesdanceedupage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) myFailedURLs.add("rockettesdanceedupage");
			passOrFail = "FAIL";
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			e2.printStackTrace();
		}
	break;
	
	case "testRockettesFAQ":
	/* ----   Step-20: Test Rockettes -> FAQ  ---- */ 
	isMyTestPassed = RockettesFAQ(this.myDriver, myURLAddresses.get("rockettesfaqpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);		
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesfaqpage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesStore":
	/* ----   Step-21: Test Rockettes -> Store  ---- */  
	isMyTestPassed = RockettesStore(this.myDriver, myURLAddresses.get("rockettesstorepage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesstorepage");
		passOrFail = "FAIL";
	}
	break;
	
	case "testRockettesChristmasCalendarLoadTime":
	/* ----   Step-22: Test Rockettes -> Christmas Calendar -> Load Time  ---- */  
	//isMyTestPassed = RockettesCSTicketsLoadTime(this.myDriver, myURLAddresses.get("").toString(), isMyTestPassed, isMobile, this.myDriverParameters);

	case "testRockettesContactUs":
	/* ----   Step-23: Test Rockettes -> Store  ---- */  
	isMyTestPassed = RockettesContactUs(this.myDriver, myURLAddresses.get("rockettescontactuspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescontactuspage");
		passOrFail = "FAIL";
	}
	break;

	case "testRockettesMobileTestCases":
		
		System.out.println("Debug Point: Entered inside the Mobile Test Cases..");
		
		String resultsDriverSuffix = "";
		
		System.out.println(this.myDriverParameters);

		if (this.myDriverParameters.toLowerCase().contains("android")) {
			resultsDriverSuffix = "Android";
		}
		
		if (this.myDriverParameters.toLowerCase().contains("iphone")) {
			resultsDriverSuffix = "Iphone";
		}
		
		if (this.myDriverParameters.toLowerCase().contains("ipad")) {
			resultsDriverSuffix = "Ipad";
		}
		
	// ----   Step-1: Test Rockettes Home Page   ---- 
		try {
			isMyTestPassed = RockettesHomePage(this.myDriver, myURLAddresses.get("rocketteshomepage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if (!isMyTestPassed) {
		myFailedURLs.add("rocketteshomepage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesHomePage" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesHomePage" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-2: Test Rockettes -> Christmas Spectacular -> Landing Page   ---- 
	isMyTestPassed =  RockettesCSLandingPage(this.myDriver, myURLAddresses.get("rockettescslandingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescslandingpage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesCSLandingPage" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesCSLandingPage" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-3: Test Rockettes -> Christmas Spectacular -> Calendar   ----  	
	try {
		isMyTestPassed = RockettesCSTickets(this.myDriver, myURLAddresses.get("rockettescsticketspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		if (!isMyTestPassed) {
			myFailedURLs.add("rockettescsticketspage"); 
			isMyTestPassedHashMap.put("RockettesCSTickets" + resultsDriverSuffix, "FAIL");
		} else {
			isMyTestPassedHashMap.put("RockettesCSTickets" + resultsDriverSuffix, "PASS");
		}
	} catch (IOException | ParseException | InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesCSTickets" + resultsDriverSuffix, "FAIL");
	}

	// ----   Step-4:  Test Rockettes -> Christmas Spectacular -> Groups   ----  
	isMyTestPassed = RockettesCSGroups(this.myDriver, myURLAddresses.get("rockettescsgroupspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescsgroupspage"); 
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesCSGroups" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesCSGroups" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-5:  Test Rockettes -> Christmas Spectacular -> Seating Chart   ----  
	// ---- COMMENTED AS IT'S OUT OF SCOPE AS OF NOW ---- 
	//isMyTestPassed = RockettesCSSeatingChart(this.myDriver, myURLAddresses.get("rockettescsseatingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	//if (!isMyTestPassed) myFailedURLs.add("rockettescsseatingpage");
	// ----   Step-6:  Test Rockettes -> Christmas Spectacular -> Promotions   ----   
		try {
			isMyTestPassed = RockettesCSCurrentOffers(this.myDriver, myURLAddresses.get("rockettescsofferspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescsofferspage");
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesCSCurrentOffers" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSCurrentOffers" + resultsDriverSuffix, "PASS");
			}
		} catch (Exception e5) {
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesCSCurrentOffers" + resultsDriverSuffix, "FAIL");
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		
		// ----   Step-6:  Test Rockettes -> Christmas Spectacular -> Promotions   ----   
		try {
			isMyTestPassed = RockettesCSCurrentOffers(this.myDriver, myURLAddresses.get("rockettescsoffersamppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescsoffersamppage");
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesCSCurrentOffersamp" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSCurrentOffersamp" + resultsDriverSuffix, "PASS");
			}
		} catch (Exception e5) {
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesCSCurrentOffersamp" + resultsDriverSuffix, "FAIL");
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}		
		
	// ----   Step-7:  Test Rockettes -> Christmas Spectacular -> Plan-your-day   ---- 
		try {
			isMyTestPassed = RockettesCSPlanYourDay(this.myDriver, myURLAddresses.get("rockettescspldpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescspldpage");
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesCSPlanYourDay" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSPlanYourDay" + resultsDriverSuffix, "PASS");
			}
			passOrFail = "FAIL";
			
		} catch (InterruptedException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescspldpage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesCSPlanYourDay" + resultsDriverSuffix, "FAIL");
	}
	// ----   Step-8:  Test Rockettes -> Christmas Spectacular -> Experiences   ----   
	isMyTestPassed = RockettesCSExperiences(this.myDriver, myURLAddresses.get("rockettescsexppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescsexppage");	
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesCSExperiences" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesCSExperiences" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-9:  Test Rockettes -> Christmas Spectacular -> News   ----  
		try {
			isMyTestPassed = RockettesCSNews(this.myDriver, myURLAddresses.get("rockettescsnewspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescsnewspage");	
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesCSNews" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSNews" + resultsDriverSuffix, "PASS");
			}
		} catch (Exception e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesCSNews" + resultsDriverSuffix, "FAIL");
		}
	 //----   Step-10:  Test Rockettes -> Christmas Spectacular -> Sign up   ----   
		try {
			isMyTestPassed = RockettesCSEmailSignUp(this.myDriver, myURLAddresses.get("rockettescssignuppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettescssignuppage");	
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesCSEmailSignUp" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSEmailSignUp" + resultsDriverSuffix, "PASS");
			}
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
			isMyTestPassedHashMap.put("RockettesCSEmailSignUp" + resultsDriverSuffix, "FAIL");
		}	
	// ----   Step-11:  Test Rockettes -> Christmas Spectacular -> Partners   ----    
		try {
			isMyTestPassed = RockettesCSPartners(this.myDriver, myURLAddresses.get("rockettescspartnerspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				passOrFail = "FAIL";
				myFailedURLs.add("rockettescspartnerspage");
				isMyTestPassedHashMap.put("RockettesCSPartners" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesCSPartners" + resultsDriverSuffix, "PASS");
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesCSPartners" + resultsDriverSuffix, "FAIL");
			e3.printStackTrace();
		}	
		
		// Commented due to removal of NYSS Show from Rockettes.com on 10/17/17
	/*// ----   Step-12: Test Rockettes -> NYS Spectacular -> Landing Page   ---- 
		try {
			isMyTestPassed = RockettesNYSLandingPage(this.myDriver, myURLAddresses.get("rockettesnysslandingpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				passOrFail = "FAIL";
				myFailedURLs.add("rockettesnysslandingpage");
				isMyTestPassedHashMap.put("RockettesNYSLandingPage" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesNYSLandingPage" + resultsDriverSuffix, "PASS");
			}
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesNYSLandingPage" + resultsDriverSuffix, "FAIL");
			e2.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isMyTestPassedHashMap.put("RockettesNYSLandingPage" + resultsDriverSuffix, "FAIL");
			passOrFail = "FAIL";
			e.printStackTrace();
		}
	// ----   Step-13: Test Rockettes -> NYS Spectacular -> Tickets   ----  
	//	 ---- COMMENTED AS IT'S OUT OF SCOPE AS OF NOW ---- 
		
		try {
		    isMyTestPassed = RockettesNYSTickets(this.myDriver, myURLAddresses.get("rockettesnyssticketspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) myFailedURLs.add("rockettesnyssticketspage");
			passOrFail = "FAIL";
		} catch (IOException | ParseException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			passOrFail = "FAIL";
		}
		
	// ----   Step-14: Test Rockettes -> NYS Spectacular -> Groups   ----  
		try {
			isMyTestPassed = RockettesNYSGroups(this.myDriver, myURLAddresses.get("rockettesnyssgroupspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesnysgroupspage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesNYSGroups" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesNYSGroups" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-15: Test Rockettes -> NYS Spectacular -> Sponsors   ----  
	isMyTestPassed = RockettesNYSSponsors(this.myDriver, myURLAddresses.get("rockettesnysssponsorspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesnysssponsorspage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesNYSSponsors" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesNYSSponsors" + resultsDriverSuffix, "PASS");
	}*/
	// ----   Step-16: Test Rockettes -> Blog   ----   
	isMyTestPassed = RockettesBlog(this.myDriver, myURLAddresses.get("rockettesblogpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesblogpage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesBlog" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesBlog" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-17: Test Rockettes -> History   ----   
		try {
			isMyTestPassed = RockettesHistory(this.myDriver, myURLAddresses.get("rocketteshistorypage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	

		// ----   Step-17: Test Rockettes -> History -> AMP Page  ----   
				try {
					isMyTestPassed = RockettesHistory(this.myDriver, myURLAddresses.get("rocketteshistoryamppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	

		if (!isMyTestPassed) {
		myFailedURLs.add("rocketteshistoryamppage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesHistoryamp" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesHistoryamp" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-18: Test Rockettes -> Photos   ----    
	isMyTestPassed = RockettesPhotos(this.myDriver, myURLAddresses.get("rockettesphotospage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesphotospage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesPhotos" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesPhotos" + resultsDriverSuffix, "PASS");
	}
	// ----   Step-19: Test Rockettes -> Dance Education   ----   
		try {
			isMyTestPassed = RockettesDanceEducation(this.myDriver, myURLAddresses.get("rockettesdanceedupage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);
			if (!isMyTestPassed) {
				myFailedURLs.add("rockettesdanceedupage");
				passOrFail = "FAIL";
				isMyTestPassedHashMap.put("RockettesDanceEducation" + resultsDriverSuffix, "FAIL");
			} else {
				isMyTestPassedHashMap.put("RockettesDanceEducation" + resultsDriverSuffix, "PASS");
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesDanceEducation" + resultsDriverSuffix, "FAIL");
			e2.printStackTrace();
		}
	// ----   Step-20: Test Rockettes -> FAQ  ----  
	isMyTestPassed = RockettesFAQ(this.myDriver, myURLAddresses.get("rockettesfaqpage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);		
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesfaqpage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesFAQ" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesFAQ" + resultsDriverSuffix, "PASS");
	}
	
	// ----   Step-20_1: Test Rockettes -> FAQ -> AMP Page ----  
		isMyTestPassed = RockettesFAQ(this.myDriver, myURLAddresses.get("rockettesfaqamppage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);		
		if (!isMyTestPassed) {
			myFailedURLs.add("rockettesfaqamppage");
			passOrFail = "FAIL";
			isMyTestPassedHashMap.put("RockettesFAQAMP" + resultsDriverSuffix, "FAIL");
		} else {
			isMyTestPassedHashMap.put("RockettesFAQAMP" + resultsDriverSuffix, "PASS");
		}
	
	// ----   Step-21: Test Rockettes -> Store  ----   
	isMyTestPassed = RockettesStore(this.myDriver, myURLAddresses.get("rockettesstorepage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettesstorepage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesStore" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesStore" + resultsDriverSuffix, "PASS");
	}
	
	//----   Step-22: Test Rockettes -> Christmas Calendar -> Load Time  ----   
	//isMyTestPassed = RockettesCSTicketsLoadTime(this.myDriver, myURLAddresses.get("").toString(), isMyTestPassed, isMobile, this.myDriverParameters);

	// ----   Step-23: Test Rockettes -> Contact Us  ----   
	try {
	isMyTestPassed = RockettesContactUs(this.myDriver, myURLAddresses.get("rockettescontactuspage").toString(), isMyTestPassed, isMobile, this.myDriverParameters);	
	if (!isMyTestPassed) {
		myFailedURLs.add("rockettescontactuspage");
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesContactUs" + resultsDriverSuffix, "FAIL");
	} else {
		isMyTestPassedHashMap.put("RockettesContactUs" + resultsDriverSuffix, "PASS");
	}
	} catch (Exception ee) {
		passOrFail = "FAIL";
		isMyTestPassedHashMap.put("RockettesContactUs" + resultsDriverSuffix, "FAIL");
		ee.printStackTrace();
	}
	
	if (System.getProperty("mobileTestCasesResultsHashMap").contains("=")) {
		RockettesGlobalParameters rGP = new RockettesGlobalParameters(this.myDriver);
		rGP.convertHashMapStringToHashMap(System.getProperty("mobileTestCasesResultsHashMap"));
		isMyTestPassedHashMap.putAll(rGP.convertHashMapStringToHashMap(System.getProperty("mobileTestCasesResultsHashMap")));
	}
	System.setProperty("mobileTestCasesResultsHashMap", isMyTestPassedHashMap.toString());
	
	break;

	
	default: 
		passOrFail = "FAIL";
		break;
		
	}

	System.out.println("-----> Performing Post Test activities: ");
	
	/* ----   Step-24: Kill the WebDriver Instance.  ---- */  
	killWebDriverInstance(myDriver);
	platformExecutionIncrementor++;

	//killWebDriverInstance(this.myDriver);

	/* ----   Step-25: Set status in Saucelabs for the Job as Passed or Failed  ---- */  
	if(USERNAME == null && ACCESS_KEY == null)
	{
			RockettesSauceJobAccessController mySauceJobStatusUpdate = new RockettesSauceJobAccessController(myUSERNAME,myACCESS_KEY);	
			Map<String, Object> myUpdates = new HashMap<String, Object>();			
			if (myFailedURLs.size() > 0 )
				myUpdates.put("passed", false);					
			else
				myUpdates.put("passed", true);						
			try {
				mySauceJobStatusUpdate.updateJobInfo(mySessionID.toString(), myUpdates);
				System.out.println("SAUCE REST: Saucelabs Job Status is updated via Saucelabs REST API.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("SAUCE REST: Saucelabs Job Status can't be updated via Saucelabs REST API.");
			}
	}
	else
	{
		RockettesSauceJobAccessController mySauceJobStatusUpdate = new RockettesSauceJobAccessController(USERNAME,ACCESS_KEY);	
		Map<String, Object> myUpdates = new HashMap<String, Object>();			
		if (myFailedURLs.size() > 0 )
			myUpdates.put("passed", false);					
		else
			myUpdates.put("passed", true);						
		try {
			mySauceJobStatusUpdate.updateJobInfo(mySessionID.toString(), myUpdates);
			System.out.println("SAUCE REST: Saucelabs Job Status is updated via Saucelabs REST API.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAUCE REST: Saucelabs Job Status can't be updated via Saucelabs REST API.");
		}
	}
		
	/* ----   Step-26: Send Mail if Test is failed.  ---- */ 
	ReadWriteLock myInnerLock = new ReentrantReadWriteLock();
	myInnerLock.writeLock().lock();
	try
	{
		if (myFailedURLs.size() > 0 )
		{
			Thread.sleep(3000);
			String myFailedURL = null;
			int URLCount = 1;
			for (int count=0;count<myFailedURLs.size();count++)
			{
				if ( myFailedURL == null )
					myFailedURL = "\r\n URL"+URLCount+": "+myURLAddresses.get(myFailedURLs.get(count).toString()).toString();
				else
				{
				if (count == 0 )
					{
					myFailedURL = null;
					myFailedURL = "\r\n URL"+URLCount+": "+myURLAddresses.get(myFailedURLs.get(count).toString()).toString();
					}
				else
					{
					myFailedURL = myFailedURL.concat("\r\n URL"+URLCount+": "+myURLAddresses.get(myFailedURLs.get(count).toString()).toString());	
					}
				}
					URLCount++;
			}
			//new RockettesRegressionScript(this.myDriver).sendMailNotification("", myEmailFailedSubject, myFailedURL);
			try {
				if (!myEmailFailedSubject.contains("Platform")) 
					{
					myEmailFailedSubject = myEmailFailedSubject.concat(this.myDriverParameters);
					}
				else
					{
					myEmailFailedSubject = "Error: Rockettes Regression - ";
					myEmailFailedSubject = myEmailFailedSubject.concat(this.myDriverParameters);
					}
				
				/**
				 * Address the Issue of Duplicate URLs in Mail.
				 */
				
				String[] myFailedURLList = RemoveDuplicateURLs(myFailedURL);
				String myCleansedURLs = null;
				for (int i =0;i<myFailedURLList.length;i++)
				{
					if(myCleansedURLs == null)
						myCleansedURLs = "\r\n "+myFailedURLList[i];
					else
						myCleansedURLs = myCleansedURLs.concat("\r\n "+myFailedURLList[i]);	
				}
				
				System.out.println("Please find below the list of failed URLs: "+myCleansedURLs);
				
//				sendMailNotificationSendGrid("", myEmailFailedSubject, myCleansedURLs);
				} 
			catch (Exception e) 
				{
				}
		// Clear the list of failed URLs.
		myFailedURLs.clear();
		myFailedURL = null;
		}
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	finally {
		myInnerLock.writeLock().unlock();
			}
	System.out.println("********************************* End of Test *********************************");
			}
	finally 
		{
       	myLock.unlock();
    	}
	}
	}
	return platformExecutionIncrementor;
}	
	

@Test(description="Fetch All Rockettes URL")
public static HashMap<String, String> getRockettesURLs()
{
	HashMap<String, String> myURLMap = new HashMap<String, String>();	
	HelperScriptFetchRockettesURLs myURLHelper = new HelperScriptFetchRockettesURLs();	
	myURLMap = myURLHelper.ParseAPIResponse("rockettes-dev");	
	return myURLMap;
}

@Test(description="Get Remote Webdriver Instance")
public DesiredCapabilities executeRemoteWebDriverInSauceLabsViaJenkins()
{
	System.out.println(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
	JsonElement myJSONElement = new JsonParser().parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
    JsonArray   myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
    int myDriverCount = 1;
	DesiredCapabilities myBrowserCapability = new DesiredCapabilities();	

    for (int j=0;j<myJSONPlatforms.size();j++)
	{
    	String  myPlatform = null, myOS = null, myBrowser = null, myBrowserVersion = null, mySLURL = null;
    	
    	for(Entry<String, JsonElement> myelem : myJSONPlatforms.get(j).getAsJsonObject().entrySet())
    	{	
    		// 1. Parse the JSON of All SauceLabs Environment variables
    		if (myelem.getKey().toString().equals("platform")) 
    		{
    			myPlatform = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("os")) 
    		{
    			myOS = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("browser")) 
    		{
    			myBrowser = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("browser-version")) 
    		{
    			myBrowserVersion = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("url")) 
    		{
    			mySLURL = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}

    		if(myPlatform != null && myOS != null && myBrowser != null && myBrowserVersion != null && mySLURL != null)
    		{	
    		// 2. Once we get all variables then we can figure out the respective driver to be triggered.
    		if(myBrowser.equals("firefox"))
    		{
    			myBrowserCapability = DesiredCapabilities.firefox();	
    			myBrowserCapability.setBrowserName(myBrowser);
    			myBrowserCapability.setCapability("name", "Rockettes Regression Script"); 
    			myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER") + "__Firefox" + System.currentTimeMillis());
    			myBrowserCapability.setCapability("public","team");    			
    			myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
    			myBrowserCapability.setCapability("os", myOS);
    			myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200");    			
    			myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
    			WebDriver driver = null;
    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Firefox Version: "+myBrowserVersion,myBrowserCapability, testCase, myDesiredSelectors, isMobile);	
    			myRockettes.start();
    		}
    		
    		if(myBrowser.equals("chrome"))
    		{
    			myBrowserCapability = DesiredCapabilities.chrome();	
    			myBrowserCapability.setBrowserName(myBrowser);
    			myBrowserCapability.setCapability("name", "Rockettes Regression Script");  
    			myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER") + "__Chrome" + System.currentTimeMillis());
    			myBrowserCapability.setCapability("public","team");    			
    			myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200");    			
    			myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
    			myBrowserCapability.setCapability("os", myOS);	    			
    			myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
       			WebDriver driver = null;
    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Chrome Version: "+myBrowserVersion,myBrowserCapability, testCase, myDesiredSelectors, isMobile);	
    			myRockettes.start();
    		}
    		if(myBrowser.equals("safari"))
    		{
    			myBrowserCapability = DesiredCapabilities.safari();	
    			myBrowserCapability.setBrowserName(myBrowser);
    			myBrowserCapability.setCapability("name", "Rockettes Regression Script"); 
    			myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER") + "__Safari" + System.currentTimeMillis());
    			myBrowserCapability.setCapability("public","team");    			
    			myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
    			myBrowserCapability.setCapability("os", myOS);	
    			myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200");    			
    			myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
       			WebDriver driver = null;
    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Safari Version: "+myBrowserVersion,myBrowserCapability, testCase, myDesiredSelectors, isMobile);	
    			myRockettes.start();
    		}
    		if(myBrowser.equals("edge"))
    		{
    			myBrowserCapability = DesiredCapabilities.edge();	
    			myBrowserCapability.setBrowserName(myBrowser);
    			myBrowserCapability.setCapability("name", "Rockettes Regression Script");  
    			myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER") + "__Edge" + System.currentTimeMillis());
    			myBrowserCapability.setCapability("public","team");    			
    			myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
    			myBrowserCapability.setCapability("os", myOS);	    			
    			myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200");    			
    			myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
       			WebDriver driver = null;
    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Edge Version: "+myBrowserVersion,myBrowserCapability, testCase, myDesiredSelectors, isMobile);	
    			myRockettes.start();
    		}
    		if(myBrowser.equals("ie"))
    		{
    			myBrowserCapability = DesiredCapabilities.internetExplorer();	
    			myBrowserCapability.setBrowserName(myBrowser);
    			myBrowserCapability.setCapability("name", "Rockettes Regression Script"); 
    			myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER") + "__InternetExplorer" + System.currentTimeMillis());
    			myBrowserCapability.setCapability("public","team");    			
    			myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
    			myBrowserCapability.setCapability("os", myOS);	
    			myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200");    			
    			myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
       			WebDriver driver = null;
    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": IE Version: "+myBrowserVersion,myBrowserCapability, testCase, myDesiredSelectors, isMobile);	
    			myRockettes.start();
    		}    		
    		}	    		
    	}
    	System.out.println("Test("+myDriverCount+") Parameters are: Platform = "+myPlatform+", OS = "+myOS+", Browser = "+myBrowser+", BrowserVersion = "+myBrowserVersion);
    	myDriverCount++;
	}
    return myBrowserCapability;
}

@Test(description="Get Remote Webdriver Instance")
public static DesiredCapabilities executeDirectRemoteWebDriverInSauceLabs(String myBuildNumber) {
	
	DesiredCapabilities myBrowserCapability = DesiredCapabilities.chrome();
	myBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	myBrowserCapability.setCapability("build", "MSGRockBuild" + System.currentTimeMillis());
	myBrowserCapability.setCapability("build", "Build No. " + System.currentTimeMillis());
	myBrowserCapability.setCapability("public","team");    			
	myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
	myBrowserCapability.setCapability(myPlatformCapability, "OS X 10.11");
	myBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	myBrowserCapability.setCapability(myVersionCapability, "54");
	WebDriver driver = null;
	Thread myRockettes = new RockettesRegressionScript(driver, "Platform: OS X 10.11 Browser: Chrome Version: 55",myBrowserCapability, "testRockettesBlog", myDesiredSelectors, false);	
	myRockettes.start();	
	
	/*
	DesiredCapabilities mychromeBrowserCapability = DesiredCapabilities.chrome();
	mychromeBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");
	mychromeBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
	mychromeBrowserCapability.setCapability("build", myBuildNumber);
	mychromeBrowserCapability.setCapability("public","team");
	mychromeBrowserCapability.setCapability(myPlatformCapability, "OS X 10.11");
	mychromeBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	mychromeBrowserCapability.setCapability(myVersionCapability, "44");
	WebDriver driver2 = null;
	Thread myRockettes2 = new RockettesRegressionScript(driver2, "Platform: OS X 10.11 Browser: Chrome Version: 44",mychromeBrowserCapability);	
	myRockettes2.start();	
	
	DesiredCapabilities myFirefoxBrowserCapability = DesiredCapabilities.firefox();
	myFirefoxBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	myFirefoxBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
	myFirefoxBrowserCapability.setCapability("build", myBuildNumber);
	myFirefoxBrowserCapability.setCapability("public","team");
	myFirefoxBrowserCapability.setCapability(myPlatformCapability, "OS X 10.11");
	myFirefoxBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	myFirefoxBrowserCapability.setCapability(myVersionCapability, "50.0");
	WebDriver driver3 = null;
	Thread myRockettes3 = new RockettesRegressionScript(driver3, "Platform: OS X 10.11 Browser: Firefox Version: 50",myFirefoxBrowserCapability);	
	myRockettes3.start();	

	DesiredCapabilities myFirefoxBrowserCapability2 = DesiredCapabilities.firefox();
	myFirefoxBrowserCapability2.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	myFirefoxBrowserCapability2.setCapability("browser.safebrowsing.phishing.enabled;","true");
	myFirefoxBrowserCapability2.setCapability("build", myBuildNumber);
	myFirefoxBrowserCapability2.setCapability("public","team");
	myFirefoxBrowserCapability2.setCapability(myPlatformCapability, "OS X 10.11");
	myFirefoxBrowserCapability2.setCapability(myScreenResolutionCapability, "1600x1200");
	myFirefoxBrowserCapability2.setCapability(myVersionCapability, "40");
	WebDriver driver4 = null;
	Thread myRockettes4 = new RockettesRegressionScript(driver4, "Platform: OS X 10.11 Browser: Firefox Version: 40",myFirefoxBrowserCapability2);	
	myRockettes4.start();	
	
	DesiredCapabilities mySafariBrowserCapability = DesiredCapabilities.safari();
	mySafariBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	mySafariBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
	mySafariBrowserCapability.setCapability("build", myBuildNumber);
	mySafariBrowserCapability.setCapability("public","team");
	mySafariBrowserCapability.setCapability(myPlatformCapability, "OS X 10.11");
	mySafariBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	mySafariBrowserCapability.setCapability(myVersionCapability, "10.0");
	WebDriver driver5 = null;
	Thread myRockettes5 = new RockettesRegressionScript(driver5, "Platform: OS X 10.11 Browser: Safari Version: 10",mySafariBrowserCapability);	
	myRockettes5.start();	

	// Commented as Safari9 is Failing and slow.
	DesiredCapabilities mySafariBrowserCapability2 = DesiredCapabilities.safari();
	mySafariBrowserCapability2.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");
	mySafariBrowserCapability2.setCapability("browser.safebrowsing.phishing.enabled;","true");
	mySafariBrowserCapability2.setCapability("build", myBuildNumber);
	mySafariBrowserCapability2.setCapability("public","team");
	mySafariBrowserCapability2.setCapability(myPlatformCapability, "OS X 10.11");
	mySafariBrowserCapability2.setCapability(myScreenResolutionCapability, "1600x1200");
	mySafariBrowserCapability2.setCapability(myVersionCapability, "9.0");
	WebDriver driver6 = null;
	Thread myRockettes6 = new RockettesRegressionScript(driver6, "Platform: OS X 10.11 Browser: Safari Version: 9",mySafariBrowserCapability2);	
	myRockettes6.start();
	
	DesiredCapabilities myEdgeBrowserCapability = DesiredCapabilities.edge();
	myEdgeBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	myEdgeBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
	myEdgeBrowserCapability.setCapability("build", myBuildNumber);
	myEdgeBrowserCapability.setCapability("public","team");
	myEdgeBrowserCapability.setCapability(myPlatformCapability, "Windows 10");
	myEdgeBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	myEdgeBrowserCapability.setCapability(myVersionCapability, "14.14393");
	WebDriver driver7 = null;
	Thread myRockettes7 = new RockettesRegressionScript(driver7, "Platform: Windows 10 Browser: Edge Version: 14",myEdgeBrowserCapability);	
	myRockettes7.start();	

	// Commented as IE11 is failing and slow.
	DesiredCapabilities myIEBrowserCapability = DesiredCapabilities.internetExplorer();
	myIEBrowserCapability.setCapability("name", "Rockettes Regression Script - Staging Env. (Without Jenkins)");	
	myIEBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");	
	myIEBrowserCapability.setCapability("build", myBuildNumber);
	myIEBrowserCapability.setCapability("public","team");
	myIEBrowserCapability.setCapability(myPlatformCapability, "Windows 10");
	myIEBrowserCapability.setCapability(myScreenResolutionCapability, "1600x1200");
	myIEBrowserCapability.setCapability(myVersionCapability, "11.103");
	WebDriver driver8 = null;
	Thread myRockettes8 = new RockettesRegressionScript(driver8, "Platform: Windows 10 Browser: Internet Explorer Version: 11",myIEBrowserCapability);	
	myRockettes8.start();

	DesiredCapabilities myiPhoneBrowserCapability = DesiredCapabilities.iphone();	
	myiPhoneBrowserCapability.setCapability("appiumVersion", "1.6.3");
	myiPhoneBrowserCapability.setCapability("deviceName","iPhone 7 Plus Simulator");
	myiPhoneBrowserCapability.setCapability("deviceOrientation", "portrait");
	myiPhoneBrowserCapability.setCapability("platformName", "iOS");
	myiPhoneBrowserCapability.setCapability("platformVersion", "10.0");	
	myiPhoneBrowserCapability.setBrowserName("safari");
	myiPhoneBrowserCapability.setCapability("browserversion","10");
	myiPhoneBrowserCapability.setCapability("name", "Rockettes Regression Script - iPhone 7 Plus (Without Jenkins)");
	WebDriver driver9 = null;
	Thread myRockettes9 = new RockettesRegressionScript(driver9, "Platform: iPhone 7 Plus Browser: Safari 10",myiPhoneBrowserCapability);	
	myRockettes9.start();
	
	DesiredCapabilities myS7AndroidBrowserCapability = DesiredCapabilities.android();
	myS7AndroidBrowserCapability.setCapability("appiumVersion", "1.5.3");
	myS7AndroidBrowserCapability.setCapability("deviceName","Samsung Galaxy S7 Device");
	myS7AndroidBrowserCapability.setCapability("deviceOrientation", "portrait");
	myS7AndroidBrowserCapability.setCapability("browserName", "chrome");
	myS7AndroidBrowserCapability.setCapability("platformVersion", "6.0");
	myS7AndroidBrowserCapability.setCapability("platformName","Android");
	myS7AndroidBrowserCapability.setCapability("name", "Rockettes Regression Script - Samsung S7 (Without Jenkins)");
	WebDriver driver10 = null;
	Thread myRockettes10 = new RockettesRegressionScript(driver10, "Platform: Samsung S7 Browser: Chrome Latest",myS7AndroidBrowserCapability);	
	myRockettes10.start();
	
	DesiredCapabilities myiPhoneBrowserCapability2 = DesiredCapabilities.iphone();
	myiPhoneBrowserCapability2.setCapability("appiumVersion", "1.6.3");
	myiPhoneBrowserCapability2.setCapability("deviceName","iPhone 5s Simulator");
	myiPhoneBrowserCapability2.setCapability("deviceOrientation", "portrait");
	myiPhoneBrowserCapability2.setCapability("platformVersion","10.0");
	myiPhoneBrowserCapability2.setCapability("platformName", "iOS");
	myiPhoneBrowserCapability2.setCapability("browserName", "Safari");
	myiPhoneBrowserCapability.setCapability("name", "Rockettes Regression Script - iPhone 5s Plus (Without Jenkins)");
	WebDriver driver11 = null;
	Thread myRockettes11 = new RockettesRegressionScript(driver11, "Platform: iPhone 5s Browser: Safari 10",myiPhoneBrowserCapability2);	
	myRockettes11.start();
	*/
	return myBrowserCapability;
}

public WebDriver getWebDriverForDesiredCapabilities(DesiredCapabilities DessiredCapabilities)
{
	WebDriver driver = null;
	try {
		String URL = URLwithEnvVariables;
		if (URL.contains("null")) URL = URLwithHardCodedVariables;	
		driver = new RemoteWebDriver(new URL(URL), DessiredCapabilities);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	
	return driver;
}

@Test(description="Get Local Webdriver Instance")
public static WebDriver getLocalWebdriverInstance(String myShowURL) {
	System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.get(myShowURL);
	return driver;
}

@Test(description="Test Rockettes Home Page") 
public static boolean RockettesHomePage(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws Exception
{
	RockettesHomePage myRockettesHomePage = new RockettesHomePage(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesHomePageResults = new HashMap<String, String>();
	
	/**
	 * 1. Check if we are on Rockettes Home Page.
	 */
	isMyTestPassed = myRockettesHomePage.onRockettesHomePage();
	myRockettesHomePageResults.put("onRockettesHomePage", isMyTestPassed);
	
	/**
	 * 2. TBD - Check if Hamburger Menu links are working.
	 */
	try {
		isMyTestPassed = myRockettesHomePage.isHamburgerMenuPresentandHasWorkingNavLinks();
		myRockettesHomePageResults.put("isHamburgerMenuPresentandHasWorkingNavLinks", isMyTestPassed);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 * 3. TBD - Is Photos present and Scrolleble?
	 */
	isMyTestPassed = myRockettesHomePage.isPhotosPresentAndScrolleable();
	myRockettesHomePageResults.put("isPhotosPresentAndScrolleable", isMyTestPassed);
	
	/**
	 * 4. TBD - Is latest from Rockettes present and has working links?
	 */
	isMyTestPassed = myRockettesHomePage.isLatestFromRockettesPresentAndHasWorkingLinks();
	myRockettesHomePageResults.put("isLatestFromRockettesPresentAndHasWorkingLinks", isMyTestPassed);
	
	/**
	 * 5. TBD - Do we've working links for NYSS and CS?
	 */
	isMyTestPassed = myRockettesHomePage.isNYSSAndCSLinkPresent();
	myRockettesHomePageResults.put("isNYSSAndCSLinkPresent", isMyTestPassed);
		
	/**
	 * 5. TBD - Do we've working links for NYSS and CS?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesHomePage.executeVisualTest();
	myRockettesHomePageResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesHomePageResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Landing Page")
public static boolean RockettesCSLandingPage(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws InterruptedException
{
	RockettesCSLandingPage myRockettesCSLandingPage = new RockettesCSLandingPage(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSLandingPageResults = new HashMap<String, String>();
	
	/**
	 * 1. Are we on CS Landing Page?
	 */
	isMyTestPassed = myRockettesCSLandingPage.onRockettesCSLandingPage();
	myRockettesCSLandingPageResults.put("onRockettesCSLandingPage", isMyTestPassed);
	
	/**
	 * TBD - 2. Is Hero Video Present and Running?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isHeroVideoPresentAndRunning();
	myRockettesCSLandingPageResults.put("isHeroVideoPresentAndRunning", isMyTestPassed);
	
	/**
	 * TBD - 3. Is Quick Finder Present?
	 */
/**
 * Enable End of season testing.
 */
	if(endOfSeasonTesting != null && endOfSeasonTesting.matches("false"))
	{
	if (!isMobile)
	{
	isMyTestPassed = myRockettesCSLandingPage.isQuickFinderPresent();
	myRockettesCSLandingPageResults.put("isQuickFinderPresent", isMyTestPassed);
	
	/**
	 * TBD - 4. Is Quick Finder Displaying Date and Time ahead of current time?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isQuickFinderDisplayingTimeAheadOfCurrentTime();
	myRockettesCSLandingPageResults.put("isQuickFinderDisplayingTimeAheadOfCurrentTime", isMyTestPassed);
	
	/**
	 * TBD - 5. Is Quick Finder Purchase Tickets Leads to TM for selected date and time?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isQuickFinderPurchaseTicketNavigtesToTM();
	myRockettesCSLandingPageResults.put("isQuickFinderPurchaseTicketNavigtesToTM", isMyTestPassed);
	}
	}
	
	/**
	 * TBD - 6. Is Make time For Joy Present?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isMakeTimeToJoyPresent();
	myRockettesCSLandingPageResults.put("isMakeTimeToJoyPresent", isMyTestPassed);
	
	/**
	 * Enable End of season testing.
	 */
		if(endOfSeasonTesting != null && endOfSeasonTesting.matches("false"))
		{

	/**
	 * TBD - 7. Is sneak peak carousel displaying images?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isSneakpeekCaraouselDisplayingImages();
	myRockettesCSLandingPageResults.put("isSneakpeekCaraouselDisplayingImages", isMyTestPassed);
	
	/**
	 * TBD - 8. Is Unique Experiences Present?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isUniqueExperiencePresent();
	myRockettesCSLandingPageResults.put("isUniqueExperiencePresent", isMyTestPassed);
	
	/**
	 * TBD - 9. Is Upcoming Events Present?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isUpcomingEventPresent();
	myRockettesCSLandingPageResults.put("isUpcomingEventPresent", isMyTestPassed);
	
	/**
	 * TBD - 10. Is Offer Bar Present?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isOfferBarPresent();
	myRockettesCSLandingPageResults.put("isOfferBarPresent", isMyTestPassed);
	
	/**
	 * TBD - 11. Is Offer Bar Having a working deeplinking URL?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isOfferBarDeepLinkingULRWorking();
	myRockettesCSLandingPageResults.put("isOfferBarDeepLinkingULRWorking", isMyTestPassed);
		}
		
	/**
	 * 12. Is Partners present?
	 */
	isMyTestPassed = myRockettesCSLandingPage.isPartnersPresent();
	myRockettesCSLandingPageResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  13. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSLandingPage.executeVisualTest();
	myRockettesCSLandingPageResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSLandingPageResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rocketets -> Christmas Spectacular -> Calendar", parameters="driver")
public static boolean RockettesCSTickets(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws FileNotFoundException, IOException, ParseException, InterruptedException
{
	HashMap myRockettesCSTicketsResults = new HashMap<String, String>();
	RockettesCSTickets myRockettesCSTickets = new RockettesCSTickets(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);

	/**
	 * 1. We are navigable to CS Tickets Page.
	 */	
	isMyTestPassed = myRockettesCSTickets.onRockettesCSTicketsPage();
	myRockettesCSTicketsResults.put("onRockettesCSTicketsPage", isMyTestPassed);
	 
	/**
	 * End of Season flag in Tests.
	 */
	if(endOfSeasonTesting != null && endOfSeasonTesting.matches("false"))
	{
	/**2. Display Calendar - 1-19 Page
	 * a. Display default 1-19 page of calendar
	 * b. Get Calendar Load Time
	 */	
	isMyTestPassed = myRockettesCSTickets.getCalenderDisplay1to19Page();
	myRockettesCSTicketsResults.put("getCalenderDisplay1to19Page", isMyTestPassed);

	/**3. Get Base Checks done on Calendar Page.
	 * a. Is Calendar Displayed?
	 * b. Are Filters Present?
	 * c. Is Helpful Tips displayed?
	 */	
	isMyTestPassed = myRockettesCSTickets.onLoadGetBaseCheckDoneOnCalender();
	myRockettesCSTicketsResults.put("onLoadGetBaseCheckDoneOnCalender", isMyTestPassed);

	/**4. Verify Calendar Cell Pricing Range. 
	 * a. Get the Price Range of Purchasing Filter 
	 * i. Get Minimum value 
	 * ii. Get Maximum value
	 */
	isMyTestPassed = myRockettesCSTickets.onLoadGetPurchasingFilterPriceRange();
	myRockettesCSTicketsResults.put("onLoadGetPurchasingFilterPriceRange", isMyTestPassed);
	
	/** 5. Get total valid days and shows when calendar is loaded. 
	 * a. Get the total valid days 
	 * b. Get the total valid shows
	 */ 
	isMyTestPassed = myRockettesCSTickets.onLoadGetAllValidDaysAndShowsfromCalendar();	
	myRockettesCSTicketsResults.put("onClickOfPurchasingFilter", isMyTestPassed);
	
	/**6. Purchasing Filter: 1-19 / 20+ Options 
	 * a. Click on Purchasing Filter has to display "2" options. 
	 * b. Click on 1-19 option shall display 1-19 Page
	 * c. Click on 20+ option shall display 20+ Page.
	 */
	 //isMyTestPassed =  myRockettesCSTickets.onClickOfPurchasingFilter();

	/**7. Apply Promo Code 
	 * a. Click on "Apply Promo Code" 
	 * b. Enter Promo code value 
	 * c. Click on "Apply Coupon" 
	 * d. If Valid Coupon - Read Text of Coupon 
	 * e. If Invalid Coupon - Read ToolTip Text
	 */
	 //myRockettesCSTickets.onClickOfApplyPromoCodeSelected(driver);
	
	/**8. DeepLinking Functionality
	 * a. Single Tag e.g. flow=individual or promo_code=mcdc
	 * b. Double Tag e.g. date=2016-12-30 and promo_code=mcdc
	 * c. Triple Tag e.g. date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E
	 * d. Quadruple Tag e.g. flow=individual and date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E 
	 */
	 //myRockettesCSTickets.verifyDeeplinkingURLs(myURL, driver);
	}
	
	/**
	 * 9. Do we have partners present?
	 */
	isMyTestPassed = myRockettesCSTickets.isPartnersPresent();
	myRockettesCSTicketsResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  10. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSTickets.executeVisualTest();
	myRockettesCSTicketsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSTicketsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}

	return isMyTestPassed;	
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Groups")
public static boolean RockettesCSGroups(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSGroups myRockettesCSGroups = new RockettesCSGroups(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSGroupsResults = new HashMap<String, String>();
	
	try {
		Thread.sleep(10000);
	} catch (Exception e100) {
	}

	/**
	 * 1. Are we on Rockettes CS Groups Page.
	 */
	isMyTestPassed = myRockettesCSGroups.onRockettesCSGroupsPage();
	myRockettesCSGroupsResults.put("onRockettesCSGroupsPage", isMyTestPassed);
	
	/**
	 * 2. Is Related Contents Section Present?
	 */
	isMyTestPassed = myRockettesCSGroups.isRelatedContentSectionPresent();
	myRockettesCSGroupsResults.put("isRelatedContentSectionPresent", isMyTestPassed);
	
	/**
	 * 3. Do we have partners present?
	 */
	isMyTestPassed = myRockettesCSGroups.isPartnersPresent();
	myRockettesCSGroupsResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  4. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSGroups.executeVisualTest();
	myRockettesCSGroupsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSGroupsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Seating Chart")
public static boolean RockettesCSSeatingChart(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSSeatingChart myRockettesCSSeatingChart = new RockettesCSSeatingChart(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSSeatingChartResults = new HashMap<String, String>();
	
	/**
	 * 1. Are we on CS Seating Page?
	 */
	isMyTestPassed = myRockettesCSSeatingChart.onRockettesCSSeatingChartPage();
	myRockettesCSSeatingChartResults.put("onRockettesCSSeatingChartPage", isMyTestPassed);
	
	/**
	 * 2. Is Partner Section displayed?
	 */
	isMyTestPassed = myRockettesCSSeatingChart.isPartnersPresent();
	myRockettesCSSeatingChartResults.put("onRockettesCSSeatingChartPage", isMyTestPassed);
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSSeatingChart.executeVisualTest();
	myRockettesCSSeatingChartResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSSeatingChartResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Promotions")
public static boolean RockettesCSCurrentOffers(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSCurrentOffers myRockettesCSCurrentOffers = new RockettesCSCurrentOffers(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSCurrentOffersResults = new HashMap<String, String>();
	
	/**
	 * 1. Are we on CS Current Offers Page?
	 */
	isMyTestPassed = myRockettesCSCurrentOffers.onRockettesCSCurrentOffersPage();
	myRockettesCSCurrentOffersResults.put("onRockettesCSCurrentOffersPage", isMyTestPassed);
	
	/**
	 * 2. Is ShortCode 3 Block Present?
	 */
	try {
		isMyTestPassed = myRockettesCSCurrentOffers.isshotCodeThreeBlockPresent();
		myRockettesCSCurrentOffersResults.put("isshotCodeThreeBlockPresent", isMyTestPassed);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 * 3. Do we have related Contents section present? 
	 */
	isMyTestPassed = myRockettesCSCurrentOffers.isRelatedContentSectionPresent();
	myRockettesCSCurrentOffersResults.put("isRelatedContentSectionPresent", isMyTestPassed);
    
	/**
	 * 4. Do we have Partners Present?
	 */
	isMyTestPassed = myRockettesCSCurrentOffers.isPartnersPresent();			
	myRockettesCSCurrentOffersResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  5. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSCurrentOffers.executeVisualTest();
	myRockettesCSCurrentOffersResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSCurrentOffersResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Plan-your-day")
public static boolean RockettesCSPlanYourDay(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws InterruptedException
{
	RockettesCSPlanYourDay myRockettesCSPlanYourDay = new RockettesCSPlanYourDay(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSPlanYourDayResults = new HashMap<String, String>();
	
	/**
	 * 1. Are we on CS Plan your day page.
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.onRockettesCSPlanYourDayPage();
	myRockettesCSPlanYourDayResults.put("onRockettesCSPlanYourDayPage", isMyTestPassed);
	
	/**
	 * TBD - 2. Do we have navigation Map?
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.isNavigationMapPersent();
	myRockettesCSPlanYourDayResults.put("isNavigationMapPersent", isMyTestPassed);
	
	/**
	 * TBD - 3. Do we have Travel accordion with working accordions? 
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.isTravelAccordionWorking();
	myRockettesCSPlanYourDayResults.put("isTravelAccordionWorking", isMyTestPassed);
	
	/**
	 * TBD - 4. Do we Panels with Flexsliders?
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.isPanelwithFlexSliderPresent();
	myRockettesCSPlanYourDayResults.put("isPanelwithFlexSliderPresent", isMyTestPassed);
	
	/**
	 * TBD - 5. Is Flex Slider in the Panel Scorlleable?
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.isFlexSliderScorellable();
	myRockettesCSPlanYourDayResults.put("isFlexSliderScorellable", isMyTestPassed);
	
	/**
	 * 6. Is Partner Section displayed? 
	 */
	isMyTestPassed = myRockettesCSPlanYourDay.isPartnersPresent();	
	myRockettesCSPlanYourDayResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  7. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSPlanYourDay.executeVisualTest();
	myRockettesCSPlanYourDayResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSPlanYourDayResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Experiences")
public static boolean RockettesCSExperiences(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSExperiences myRockettesCSExperiences = new RockettesCSExperiences(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSExperiencesResults = new HashMap<String, String>();

	/**
	 * 1. Are we on CS Experience page?
	 */
	isMyTestPassed = myRockettesCSExperiences.onRockettesCSExperiencesPage();
	myRockettesCSExperiencesResults.put("onRockettesCSExperiencesPage", isMyTestPassed);

	/**
	 * 2. Do we see Experiences Body?
	 */
	isMyTestPassed = myRockettesCSExperiences.isExperiencesDisplayed();
	myRockettesCSExperiencesResults.put("isExperiencesDisplayed", isMyTestPassed);
	
	/**
	 * 3. Is Partners Present?
	 */
	isMyTestPassed = myRockettesCSExperiences.isPartnersPresent();
	myRockettesCSExperiencesResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  4. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSExperiences.executeVisualTest();
	myRockettesCSExperiencesResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSExperiencesResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> News")
public static boolean RockettesCSNews(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSNews myRockettesCSNews = new RockettesCSNews(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSNewsResults = new HashMap<String, String>();
	
	/**
	 * 1. Are we on Rockettes CS News Page?
	 */
	isMyTestPassed = myRockettesCSNews.onRockettesCSNewsPage();
	myRockettesCSNewsResults.put("onRockettesCSNewsPage", isMyTestPassed);
	
	/**
	 * TBD - 2. Count number of news and verify if the links are working?
	 */
	try {
		isMyTestPassed = myRockettesCSNews.isNewsPresentAndLinkWorking();
		myRockettesCSNewsResults.put("isNewsPresentAndLinkWorking", isMyTestPassed);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 * 3. Is Partners Working?
	 */
	isMyTestPassed = myRockettesCSNews.isPartnersPresent();
	myRockettesCSNewsResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  4. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSNews.executeVisualTest();
	myRockettesCSNewsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSNewsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Sign up")
public static boolean RockettesCSEmailSignUp(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws InterruptedException
{
	RockettesCSEmailSignUp myRockettesCSEmailSignUp = new RockettesCSEmailSignUp(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSEmailSignUpResults = new HashMap<String, String>();

	/**
	 * 1. Are we on CS Email Sign Up Page?
	 */
	isMyTestPassed = myRockettesCSEmailSignUp.onRockettesCSEmailSignUpPage();
	myRockettesCSEmailSignUpResults.put("onRockettesCSEmailSignUpPage", isMyTestPassed);

	/**
	 * TBD - 2. Can we submit Email Sign Up Form?
	 */
	//isMyTestPassed = myRockettesCSEmailSignUp.isEmailSigUpSubmittingDetails();	
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSEmailSignUp.executeVisualTest();
	myRockettesCSEmailSignUpResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSEmailSignUpResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> Christmas Spectacular -> Partners")
public static boolean RockettesCSPartners(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesCSPartners myRockettesCSPartners = new RockettesCSPartners(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesCSPartnersResults = new HashMap<String, String>();

	/**
	 * 1. Are we on CS Partners Page?
	 */
	isMyTestPassed = myRockettesCSPartners.onRockettesCSPartnersPage();
	myRockettesCSPartnersResults.put("onRockettesCSPartnersPage", isMyTestPassed);

	/**
	 * 2. Is Partners Present?
	 */
	try {
		isMyTestPassed = myRockettesCSPartners.isAllPartnersPresent();
		myRockettesCSPartnersResults.put("isAllPartnersPresent", isMyTestPassed);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesCSPartners.executeVisualTest();
	myRockettesCSPartnersResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesCSPartnersResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> NYS Spectacular -> Landing Page")
public static boolean RockettesNYSLandingPage(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws InterruptedException
{
	RockettesNYSLandingPage myRockettesNYSLandingPage = new RockettesNYSLandingPage(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesNYSLandingPageResults = new HashMap<String, String>();

	/**
	 * 1. Check if we are on NYSS Landing Page.
	 */
	isMyTestPassed = myRockettesNYSLandingPage.onRockettesNYSLandingPage();
	myRockettesNYSLandingPageResults.put("onRockettesNYSLandingPage", isMyTestPassed);

	// ***** NYS SUMMER 2017 HAS BEEN CANCELLED, COMMENTING OUT SECTIONS BELOW *****
	
	/**
	 * 2. Check if Hero Video is Present.
	 */
//	isMyTestPassed = myRockettesNYSLandingPage.isHeroVideoPresentAndRunning();
//	myRockettesNYSLandingPageResults.put("isHeroVideoPresentAndRunning", isMyTestPassed);

	/**
	 * 3. Check Navigation to TM for Buy Tickets.
	 * -----> Commented as this feature is removed. <---------
	 */
//	isMyTestPassed = myRockettesNYSLandingPage.isBuyTicketNavigtesToTM();
//	myRockettesNYSLandingPageResults.put("isBuyTicketNavigtesToTM", isMyTestPassed);

	/**
	 * 4. Check if Sneak Peek Carousel image are navigable. 	
	 */
//	isMyTestPassed = myRockettesNYSLandingPage.isSneakPeakCaraouselImagesNavigationEnabled();
//	myRockettesNYSLandingPageResults.put("isSneakPeakCaraouselImagesNavigationEnabled", isMyTestPassed);

	/**
	 * 5. Check if News & Reviews and Blog Links are present.
	 */
//	try {
//		isMyTestPassed = myRockettesNYSLandingPage.isNewsAndReviewsLinkEnabled();
//		myRockettesNYSLandingPageResults.put("isNewsAndReviewsLinkEnabled", isMyTestPassed);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	/**
	 * 6. Check if Sponsors are present.
	 */
//	isMyTestPassed = myRockettesNYSLandingPage.isSponsorsPresent();
//	myRockettesNYSLandingPageResults.put("isSponsorsPresent", isMyTestPassed);
	
	/**
	 *  7. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesNYSLandingPage.executeVisualTest();
	myRockettesNYSLandingPageResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesNYSLandingPageResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}

@Test(description="Test Rockettes -> NYS Spectacular -> Tickets")
public static boolean RockettesNYSTickets(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws IOException, ParseException, InterruptedException
{
	RockettesNYSTickets myRockettesNYSTickets = new RockettesNYSTickets(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesNYSTicketsResults = new HashMap<String, String>();

	/**
	 * 1. Check if you are on NYSS Tickets Page.
	 */
	
	isMyTestPassed = myRockettesNYSTickets.onRockettesNYSTicketsPage();
	myRockettesNYSTicketsResults.put("onRockettesNYSTicketsPage", isMyTestPassed);
	
	/** 2. Display Calendar - 1-19 Page
	 * a. Display default 1-19 page of calendar
	 * b. Get Calendar Load Time
	 */
	
	isMyTestPassed = myRockettesNYSTickets.getCalenderDisplay1to19Page();
	myRockettesNYSTicketsResults.put("getCalenderDisplay1to19Page", isMyTestPassed);

	 /**3. Get Base Checks done on Calendar Page.
	 * a. Is Calendar Displayed?
	 * b. Are Filters Present?
	 * c. Is Helpful Tips displayed?
	 */
	
	isMyTestPassed = myRockettesNYSTickets.onLoadGetBaseCheckDoneOnCalender();
	myRockettesNYSTicketsResults.put("onLoadGetBaseCheckDoneOnCalender", isMyTestPassed);

	 /** 4. Verify Calendar Cell Pricing Range. 
	 * a. Get the Price Range of Purchasing Filter 
	 * i. Get Minimum value 
	 * ii. Get Maximum value
	 */
	
	isMyTestPassed = myRockettesNYSTickets.onLoadGetPurchasingFilterPriceRange();
	myRockettesNYSTicketsResults.put("onLoadGetPurchasingFilterPriceRange", isMyTestPassed);
	
	 /**5. Purchasing Filter: 1-19 / 20+ Options 
	 * a. Click on Purchasing Filter has to display "2" options. 
	 * b. Click on 1-19 option shall display 1-19 Page
	 * c. Click on 20+ option shall display 20+ Page.
	 */
	 
	isMyTestPassed = myRockettesNYSTickets.onClickOfPurchasingFilter();
	myRockettesNYSTicketsResults.put("onClickOfPurchasingFilter", isMyTestPassed);

	 /**6. Promo code Workflow  and navigation to TM 
	 * a. Apply Promo code mcdc
	 * b. Select a Show
	 * c. Navigate to TM
	 * d. Come back to Rockettes Calendar
	 */
	 
	 //------------- Known FACT - Saucelabs jenkins Integration is causing an issue in triggering another browser window --------//
	isMyTestPassed = myRockettesNYSTickets.onApplyPromoCodeSingle();
	myRockettesNYSTicketsResults.put("onApplyPromoCodeSingle", isMyTestPassed);
	
	 /** 7. Get total valid days and shows when calendar is loaded. 
	 * a. Get the total valid days 
	 * b. Get the total valid shows
	 */
	 
	// isMyTestPassed = myRockettesNYSTickets.onLoadGetAllValidDaysAndShowsfromCalendar(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);	
	
	 /**8. Apply Promo Code 
	 * a. Click on "Apply Promo Code" 
	 * b. Enter Promo code value 
	 * c. Click on "Apply Coupon" 
	 * d. If Valid Coupon - Read Text of Coupon 
	 * e. If Invalid Coupon - Read ToolTip Text
	 */
	 
	//isMyTestPassed = myRockettesNYSTickets.onClickOfApplyPromoCodeSelected(driver, isMyTestPassed);
	
	 /**9. DeepLinking Functionality
	 * a. Single Tag e.g. flow=individual or promo_code=mcdc
	 * b. Double Tag e.g. date=2016-12-30 and promo_code=mcdc
	 * c. Triple Tag e.g. date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E
	 * d. Quadruple Tag e.g. flow=individual and date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E 
	 */
	 
	// isMyTestPassed = myRockettesNYSTickets.verifyDeeplinkingURLs(myURL, driver, isMyTestPassed);		

	 /**10. Verify Partners are Present 
	  * 
	  */
	 isMyTestPassed = myRockettesNYSTickets.isPartnersPresent();
	 myRockettesNYSTicketsResults.put("isPartnersPresent", isMyTestPassed);
		
		/**
		 *  11. Visual test needs to be executed?
		 */
		if(executeVisualTest != null && executeVisualTest.matches("true"))
		{
		isMyTestPassed = myRockettesNYSTickets.executeVisualTest();
		myRockettesNYSTicketsResults.put("isVisualTestSuccess", isMyTestPassed);
		}
		
		if (myRockettesNYSTicketsResults.toString().contains("false")) {
			isMyTestPassed = false;
		} else {
			isMyTestPassed = true;
		}
		
	return isMyTestPassed;
}

@Test(description="Test Rockettes -> NYS Spectacular -> Groups")
public static boolean RockettesNYSGroups(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws Exception
{
	RockettesNYSGroups myRockettesNYSGroups = new RockettesNYSGroups(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesNYSGroupsResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes -> NYSS -> Groups page.
	 */
	isMyTestPassed = myRockettesNYSGroups.onRockettesNYSSGroupsPage();
	myRockettesNYSGroupsResults.put("onRockettesNYSSGroupsPage", isMyTestPassed);
	/**
	 * 2. Is Date and Time image Displayed?
	 */
//	isMyTestPassed = myRockettesNYSGroups.isDateAndTimeImageDisplayed();
//	myRockettesNYSGroupsResults.put("isDateAndTimeImageDisplayed", isMyTestPassed);

	/**
	 * 3. Is pricing Image displayed?
	 */
//	isMyTestPassed = myRockettesNYSGroups.isPricingImageDisplayed();
//	myRockettesNYSGroupsResults.put("isPricingImageDisplayed", isMyTestPassed);

	/**
	 * 4. Is Social Media Links Working?
	 */
	isMyTestPassed = myRockettesNYSGroups.isSocialMediaLinkWorking();
	myRockettesNYSGroupsResults.put("isSocialMediaLinkWorking", isMyTestPassed);
	
	/**
	 *  5. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesNYSGroups.executeVisualTest();
	myRockettesNYSGroupsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesNYSGroupsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}	

@Test(description="Test Rockettes -> NYS Spectacular -> Sponsors")
public static boolean RockettesNYSSponsors(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesNYSSponsors myRockettesNYSSponsors = new RockettesNYSSponsors(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesNYSSponsorsResults = new HashMap<String, String>();

	/**
	 * 1. Are we on NY Sponsors Page? 
	 */
	isMyTestPassed = myRockettesNYSSponsors.onRockettesNYSSponsorsPage();
	myRockettesNYSSponsorsResults.put("onRockettesNYSSponsorsPage", isMyTestPassed);

	/**
	 * 2. Is Sponsors Present?
	 */
	isMyTestPassed = myRockettesNYSSponsors.isSponsorsPresent();
	myRockettesNYSSponsorsResults.put("isSponsorsPresent", isMyTestPassed);
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesNYSSponsors.executeVisualTest();
	myRockettesNYSSponsorsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesNYSSponsorsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}

@Test(description="Test Rockettes -> Blog")
public static boolean RockettesBlog(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesBlog myRockettesBlog = new RockettesBlog(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesBlogResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes Blog Page? 
	 */
	isMyTestPassed = myRockettesBlog.onRockettesBlogPage();
	myRockettesBlogResults.put("onRockettesBlogPage", isMyTestPassed);
	
	/**
	 * 2. Count number of Blogs? 
	 */
	try {
		isMyTestPassed = myRockettesBlog.isPhotosPresentAndScrolleable();
		myRockettesBlogResults.put("isPhotosPresentAndScrolleable", isMyTestPassed);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesBlog.executeVisualTest();
	myRockettesBlogResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesBlogResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}	

@Test(description="Test Rockettes -> History")
public static boolean RockettesHistory(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws InterruptedException
{
	RockettesHistory myRockettesHistory = new RockettesHistory(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesHistoryResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes History Page?
	 */
	isMyTestPassed = myRockettesHistory.onRockettesHistoryPage();
	myRockettesHistoryResults.put("onRockettesHistoryPage", isMyTestPassed);

	/**
	 * TBD - 2. Is slick Slider Working?
	 */
	isMyTestPassed = myRockettesHistory.isSlickSliderWorking();
	myRockettesHistoryResults.put("isSlickSliderWorking", isMyTestPassed);
	
	/**
	 * 3. Is history displayed for selected Years?
	 */
	
	isMyTestPassed = myRockettesHistory.isHistoryDisplayedForSelectedYears();
	myRockettesHistoryResults.put("isHistoryDisplayedForSelectedYears", isMyTestPassed);
	
	/**
	 * 4. Is Related Contents Section displayed?
	 */
	isMyTestPassed = myRockettesHistory.isRelatedContentSectionPresent();
	myRockettesHistoryResults.put("isRelatedContentSectionPresent", isMyTestPassed);
	
	/**
	 * 5. Is Partners Present?
	 */
	isMyTestPassed = myRockettesHistory.isPartnersPresent();
	myRockettesHistoryResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 *  6. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesHistory.executeVisualTest();
	myRockettesHistoryResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesHistoryResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;	
}

@Test(description="Test Rockettes -> Photos")
public static boolean RockettesPhotos(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesPhotos myRockettesPhotos = new RockettesPhotos(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesPhotosResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes Photos Page?
	 */
	isMyTestPassed = myRockettesPhotos.onRockettesPhotosPage();
	myRockettesPhotosResults.put("onRockettesPhotosPage", isMyTestPassed);

	/**
	 * TBD - 2. Is our photo slider working?
	 */
	isMyTestPassed = myRockettesPhotos.isPhotoSliderWorking();
	myRockettesPhotosResults.put("isPhotoSliderWorking", isMyTestPassed);

	/**
	 * TBD - 3. Is our navigation resolve to Page with Thumbnails?
	 */
	isMyTestPassed = myRockettesPhotos.isPhotoSliderLinkNavigatesToFurtherDetails();
	myRockettesPhotosResults.put("isPhotoSliderLinkNavigatesToFurtherDetails", isMyTestPassed);
	
	/**
	 * TBD - 4. Are Thumbnails working? 
	 */
	isMyTestPassed = myRockettesPhotos.isFurtherDetailsThumbnailLinksWorking();
	myRockettesPhotosResults.put("isFurtherDetailsThumbnailLinksWorking", isMyTestPassed);
	
	/**
	 * TBD - 5. Can we use Left and Right arrows over the Thumbnail Display? 
	 */
	isMyTestPassed = myRockettesPhotos.isLeftAndRightNavigationWorkingOnThumbnailDisplay();
	myRockettesPhotosResults.put("isLeftAndRightNavigationWorkingOnThumbnailDisplay", isMyTestPassed);
	
	/**
	 *  6. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesPhotos.executeVisualTest();
	myRockettesPhotosResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesPhotosResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}					

@Test(description="Test Rockettes -> Dance Education")
public static boolean RockettesDanceEducation(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters) throws Exception
{
	RockettesDanceEducation myRockettesDanceEducation = new RockettesDanceEducation(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesDanceEducationResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes Dance Education Page? 
	 */
	isMyTestPassed = myRockettesDanceEducation.onRockettesDanceEduPage();
	myRockettesDanceEducationResults.put("onRockettesDanceEduPage", isMyTestPassed);

	/**
	 * TBD - 2. Do we have working social media links?
	 */
	isMyTestPassed = myRockettesDanceEducation.isSocialMediaLinkWorking();
	myRockettesDanceEducationResults.put("isSocialMediaLinkWorking", isMyTestPassed);
	
	/**
	 * TBD - 3. Do we have Dance Stories displayed?
	 */
	isMyTestPassed = myRockettesDanceEducation.isDanceStoriesDisplayed();
	myRockettesDanceEducationResults.put("isDanceStoriesDisplayed", isMyTestPassed);
	
	/**
	 * TBD - 4. Do we have Read More displayed? 
	 */
	isMyTestPassed = myRockettesDanceEducation.isReadMoreDisplayed();
	myRockettesDanceEducationResults.put("isReadMoreDisplayed", isMyTestPassed);
	
	/**
	 *  5. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesDanceEducation.executeVisualTest();
	myRockettesDanceEducationResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesDanceEducationResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}

@Test(description="Test Rockettes -> FAQ")
public static boolean RockettesFAQ(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesFAQ myRockettesFAQ = new RockettesFAQ(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesFAQResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes FAQ Page?
	 */
	isMyTestPassed = myRockettesFAQ.onRockettesFAQPage();
	myRockettesFAQResults.put("onRockettesFAQPage", isMyTestPassed);

	/**
	 * 2. Are all of our Accordions working?
	 */
	isMyTestPassed = myRockettesFAQ.isAllAccordionWorking();
	myRockettesFAQResults.put("isAllAccordionWorking", isMyTestPassed);
	
	/**
	 * 3. Do we have partners?
	 */
	isMyTestPassed = myRockettesFAQ.isPartnersPresent();
	myRockettesFAQResults.put("isPartnersPresent", isMyTestPassed);
	
	/**
	 * 4. Is related contents section present?
	 */
	isMyTestPassed = myRockettesFAQ.isRelatedContentSectionPresent();
	myRockettesFAQResults.put("isRelatedContentSectionPresent", isMyTestPassed);
	
	/**
	 *  5. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesFAQ.executeVisualTest();
	myRockettesFAQResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesFAQResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
	
}	

@Test(description="Test Rockettes -> Contact Us")
public static boolean RockettesContactUs(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesContactUs myRockettesContactUs = new RockettesContactUs(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesContactUsResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes store Page? 
	 */
	isMyTestPassed = myRockettesContactUs.onRockettesContactUsPage();
	myRockettesContactUsResults.put("onRockettesContactUsPage", isMyTestPassed);

	/**
	 * 2. Is Email ID working?
	 */
	isMyTestPassed = myRockettesContactUs.isMailIDWorkingAndPhoneNumberPresent();
	myRockettesContactUsResults.put("isMailIDWorkingAndPhoneNumberPresent", isMyTestPassed);
	
	/**
	 *  3. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesContactUs.executeVisualTest();
	myRockettesContactUsResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesContactUsResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}	

@Test(description="Test Rockettes -> Store")
public static boolean RockettesStore(WebDriver driver, String myURL, boolean isMyTestPassed, boolean isMobile, String DriverParameters)
{
	RockettesStore myRockettesStore = new RockettesStore(driver, myURL, isMyTestPassed, myDesiredSelectors, isMobile, DriverParameters);
	HashMap myRockettesStoreResults = new HashMap<String, String>();

	/**
	 * 1. Are we on Rockettes store Page? 
	 */
	isMyTestPassed = myRockettesStore.onRockettesStorePage();
	myRockettesStoreResults.put("onRockettesStorePage", isMyTestPassed);
	
	/**
	 *  2. Visual test needs to be executed?
	 */
	if(executeVisualTest != null && executeVisualTest.matches("true"))
	{
	isMyTestPassed = myRockettesStore.executeVisualTest();
	myRockettesStoreResults.put("isVisualTestSuccess", isMyTestPassed);
	}
	
	if (myRockettesStoreResults.toString().contains("false")) {
		isMyTestPassed = false;
	} else {
		isMyTestPassed = true;
	}
	
	return isMyTestPassed;
}		

@Test(description="Kill the web-driver instance")
public static void killWebDriverInstance(WebDriver driver)
{
/* 1. Kill WebDriver Instance.
* a. Kill driver's Istance and close the session
*/
driver.quit();	
}

private class SMTPAuthenticator extends Authenticator
{
    private PasswordAuthentication authentication;

    public SMTPAuthenticator(String login, String password)
    {
         authentication = new PasswordAuthentication(login, password);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
         return authentication;
    }
}

public void sendMailNotification(String EmailBody, String EmailSubject, String EmailFailedBody)
{
	try
	    {
	        Properties myEmailProperties = new Properties();
	        myEmailProperties.setProperty("mail.host", "smtp.gmail.com");
	        myEmailProperties.setProperty("mail.smtp.port", "587");
	        myEmailProperties.setProperty("mail.smtp.auth", "true");
	        myEmailProperties.setProperty("mail.smtp.starttls.enable", "true");

	        Authenticator myEmailAuthenticator = new SMTPAuthenticator(myEmailUser, myEmailAccessKey);

	        Session mySession = Session.getInstance(myEmailProperties, myEmailAuthenticator);

	        MimeMessage myMimeMessage = new MimeMessage(mySession);

	       try
	       {
	    	    myMimeMessage.setSubject(EmailSubject);
	    	    if(EmailBody.isEmpty())
	    	    {
	    	    	myEmailBody = myEmailBody.concat(EmailFailedBody);
	    	    	String myDateAndTime = null;
	    	    	Date mySystemDate = new Date();
	    		    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat();
	    		    mySimpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	    		    if (!mySimpleDateFormat.format(mySystemDate).toString().isEmpty())
	    		   		{
	    			   		myDateAndTime = mySimpleDateFormat.format(mySystemDate);
	    		   		}
	    		    else
	    		   		{
	    			   		myDateAndTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	    		   		}	 
	    		    if (myDateAndTime !=null)
	    		    {
	    		    	myEmailBody = myEmailBody.replace("myDateAndTime", myDateAndTime);	
	    		    }
	    		    else
	    		    {
	    		    	myEmailBody =  myEmailBodyWithdefaultDateAndTime;
	    		    }
	    		    myMimeMessage.setText(myEmailBody);
	    	    }
	    	    else
	    	    {
	    	    	myMimeMessage.setText(EmailBody);	
	    	    }
	    	    myMimeMessage.setFrom(new InternetAddress(myEmailFrom));
	    	    String[] myEmailToAddresses = myEmailToList.split(";");   
	    	    for (int count=0;count<myEmailToAddresses.length;count++)
	    	    {
	    	    	myMimeMessage.addRecipient(Message.RecipientType.TO,
	    	    	new InternetAddress(myEmailToAddresses[count]));
	    	    }
	    	    Transport.send(myMimeMessage);
	    	    System.out.println("Mail notification sent.");
	       }
	       catch (MessagingException ex)
	       {
	    	   System.out.println("Mail not sent!!");
	    	   Logger.getLogger(RockettesRegressionScript.class.getName()).
	           log(Level.SEVERE, null, ex);
	       }
	    } 
	  finally
	  {
		  // 
	  }
}
/*
private void sendMailNotificationSendGrid(String EmailBody, String EmailSubject, String EmailFailedBody) throws IOException
{
	 Email from = new Email(myEmailFrom);
	 Email to = new Email(myEmailToList);
	
	 if(EmailBody.isEmpty())
	    	    {
	    	    	myEmailBody = myEmailBody.concat(EmailFailedBody);
	    	    	String myDateAndTime = null;
	    	    	Date mySystemDate = new Date();
	    		    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat();
	    		    mySimpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	    		    if (!mySimpleDateFormat.format(mySystemDate).toString().isEmpty())
	    		   		{
	    			   		myDateAndTime = mySimpleDateFormat.format(mySystemDate);
	    		   		}
	    		    else
	    		   		{
	    			   		myDateAndTime = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
	    		   		}	 
	    		    if (myDateAndTime !=null)
	    		    {
	    		    	myEmailBody = myEmailBody.replace("myDateAndTime", myDateAndTime);	
	    		    }
	    		    else
	    		    {
	    		    	myEmailBody =  myEmailBodyWithdefaultDateAndTime;
	    		    }
	    		EmailBody = myEmailBody;
	    	    }
	 
	    		 Content content = new Content("text/plain", EmailBody);
	    		 Mail mail = new Mail(from, myEmailFailedSubject, to, content);
	    		 SendGrid sg = new SendGrid("SENDGRID_API_KEY");
	    		 Request request = new Request();
	    		 try {
	    		   request.method = Method.POST;
	    		   request.endpoint = "mail/send";
	    		   request.body = mail.build();
	    		   Response response = sg.api(request);
		    	   if (response.statusCode == 202) 
		    		   System.out.println("Test Failure Mail notification sent.");
		    	   else
		    		   System.out.println("Test Failure Mail notification not sent!");
	    		 } catch (IOException ex) {
	    		   throw ex;
	    		 }
}
*/
private static List<String> getDriverParameters()
{
	List<String> myPlatforms = new ArrayList<String>();
	
	JsonElement myJSONElement = new JsonParser().parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
    JsonArray   myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();

    for (int j=0;j<myJSONPlatforms.size();j++)
	{
    	String  myPlatform = null, myOS = null, myBrowser = null, myBrowserVersion = null, mySLURL = null;
    	
    	for(Entry<String, JsonElement> myelem : myJSONPlatforms.get(j).getAsJsonObject().entrySet())
    	{	
    		// 1. Parse the JSON of All SauceLabs Environment variables
    		if (myelem.getKey().toString().equals("platform")) 
    		{
    			myPlatform = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("os")) 
    		{
    			myOS = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("browser")) 
    		{
    			myBrowser = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("browser-version")) 
    		{
    			myBrowserVersion = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}
    		if (myelem.getKey().toString().equals("url")) 
    		{
    			mySLURL = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
    		}

    		if(myPlatform != null && myOS != null && myBrowser != null && myBrowserVersion != null && mySLURL != null)
    		{
    			myPlatforms.add("Platform = "+myPlatform+", OS = "+myOS+", Browser = "+myBrowser+", BrowserVersion = "+myBrowserVersion);
    		}    		
    		}	    		
    	}
	return myPlatforms;
}

private static String[] RemoveDuplicateURLs(String FailedURLs) {
String[] myFailedURLList = FailedURLs.split("\r\n");
Set<String> myUniqueURLs = new LinkedHashSet<>(Arrays.asList(myFailedURLList));	
return (String[]) myUniqueURLs.toArray(new String[myUniqueURLs.size()]);
}

}
