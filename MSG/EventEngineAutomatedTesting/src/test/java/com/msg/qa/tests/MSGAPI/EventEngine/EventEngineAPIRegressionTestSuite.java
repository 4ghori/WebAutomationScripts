package com.msg.qa.tests.MSGAPI.EventEngine;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
import java.net.MalformedURLException;
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
import com.msg.qa.MSGAPI.EventEngine.EventEngineAPIRegressionScript;


public class EventEngineAPIRegressionTestSuite extends UITest  {
	   // SauceLabs User Name and Access Key - to be read from Environment Variables
		  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	      public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");   
		  public static final String URLwithEnvVariables = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
		  
		  // SauceLabs User Name and Access Key - Fallback in case Environment variables are not setup
		  //public static final String myUSERNAME = "rachit";
		  public static final String myUSERNAME = "SnehalChudgar";
	      //public static final String myACCESS_KEY = "05d919ba-84e4-49fb-b84c-69f8af1918bf"; //rachit access key   
	      public static final String myACCESS_KEY = "48ec4a37-dd0a-49d0-b397-ef08c81dcebe"; //snehal access key
		  public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

		  // Calendar Production URL
		  public static String myEventEngineURL = "http://www.qa-api.msg.com/";
		  
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
int numberOfDesiredCapabilities;
DesiredCapabilities extraCapabilities;
public String soapUISuitePath = "src/test/resources/soapui_xml_suites/";
HashMap<String, HashMap<String, String>> venuesPositiveResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> venuesEdgeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> venuesNegativeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> artistsPositiveResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> artistsEdgeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> artistsNegativeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> datesPositiveResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> datesEdgeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> datesNegativeResults = new HashMap<String, HashMap<String, String>>();

//==>> RS Code - Added V2.5 API Calls  
HashMap<String, HashMap<String, String>> eventsPositiveResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> eventsEdgeResults = new HashMap<String, HashMap<String, String>>();
HashMap<String, HashMap<String, String>> eventsNegativeResults = new HashMap<String, HashMap<String, String>>();




@BeforeMethod(alwaysRun=true)
public void suiteSetup1() {
}

@Test(description="Event Engine Test - All")
public void MSGEEAPI000All() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
	EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
	
	venuesPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - POSITIVE");
	venuesEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - EDGE");
	venuesNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - NEGATIVE");
	artistsPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - POSITIVE");
	artistsEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - EDGE");
	artistsNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - NEGATIVE");
	datesPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - POSITIVE");
	datesEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - EDGE");
	datesNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - NEGATIVE");
	
	//== RS - Added V2.5 
	eventsPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "Event Engine API 2.5-readyapi-project.xml", "Event Engine API Calls V2.5", "Events - POSITIVE");

	
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesPositiveResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesEdgeResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesNegativeResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsPositiveResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsEdgeResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsNegativeResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesPositiveResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesEdgeResults);
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesNegativeResults);
	
	//== RS - Report Added V2.5 
	System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + eventsPositiveResults);

}	

	@Test(description="Event Engine Test - Venues - POSITIVE")
	public void MSGEEAPI001VenuesPositive() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		venuesPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - POSITIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesPositiveResults);
	}	

	@Test(description="Event Engine Test - Venues - EDGE")
	public void MSGEEAPI002VenuesEdge() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		venuesEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - EDGE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesEdgeResults);
	}	

	@Test(description="Event Engine Test - Venues - NEGATIVE")
	public void MSGEEAPI003VenuesNegative() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		venuesNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Venues - NEGATIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + venuesNegativeResults);
	}	

	@Test(description="Event Engine Test - Artists - POSITIVE")
	public void MSGEEAPI004ArtistsPositive() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		artistsPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - POSITIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsPositiveResults);
	}	

	@Test(description="Event Engine Test - Artists - EDGE")
	public void MSGEEAPI005ArtistsEdge() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		artistsEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - EDGE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsEdgeResults);
	}	

	@Test(description="Event Engine Test - Venues - NEGATIVE")
	public void MSGEEAPI006ArtistsNegative() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		artistsNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Artists - NEGATIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + artistsNegativeResults);
	}	

	@Test(description="Event Engine Test - Dates - POSITIVE")
	public void MSGEEAPI007DatesPositive() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		datesPositiveResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - POSITIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesPositiveResults);
	}	

	@Test(description="Event Engine Test - Dates - EDGE")
	public void MSGEEAPI008DatesEdge() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		datesEdgeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - EDGE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesEdgeResults);
	}	

	@Test(description="Event Engine Test - Dates - NEGATIVE")
	public void MSGEEAPI009DatesNegative() throws ClientProtocolException, IOException, ParseException, InterruptedException, MalformedURLException, NoSuchAlgorithmException, KeyManagementException, Exception {
		EventEngineAPIRegressionScript eEAPI = new EventEngineAPIRegressionScript(driver);
		datesNegativeResults = eEAPI.testSoapUIXML(soapUISuitePath + "EventEngine_QA.xml", "Event Engine API Calls", "Dates - NEGATIVE");
		System.out.println("THE OUTPUT FOR THIS XML SUITE IS ***" + datesNegativeResults);
	}	

}
