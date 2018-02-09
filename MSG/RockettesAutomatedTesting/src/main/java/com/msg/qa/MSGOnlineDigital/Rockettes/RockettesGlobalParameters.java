package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;


@SuppressWarnings("deprecation")
public class RockettesGlobalParameters extends AbstractPage
{	  
    public RockettesGlobalParameters(WebDriver driver, Map<String, String[]> desiredSelectors) {
		super(driver);
		selectors = desiredSelectors;
		// TODO Auto-generated constructor stub
	}

    public RockettesGlobalParameters(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

    public RockettesGlobalParameters() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	// SauceLabs User Name and Access Key - to be read from Environment Variables
	  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
      public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");   
	  public static final String URLwithEnvVariables = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  
	  // SauceLabs User Name and Access Key - Fallback in case Environment variables are not setup
	  public static final String myUSERNAME = "MSGTesting";
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
//    			  		   myEmailToList = "rachitkumar.rastogi@msg.com; richard.barrow@msg.com; krishna.chaparala@msg.com; sri.anne@msg.com",
 	  	    			   myEmailToList = "rachitkumar.rastogi@msg.com",
 	  	    			   myEmailFailedSubjectDefault = "Error: Rockettes Regression - ",
 	  	    			   myEmailFailedSubject = "Error: Rockettes Regression - ",
 			  			   myEmailSuccessSubject = "Jenkins Success - Rockettes Regression Script is successful.",
 			  			   myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"\r\nThanks, Your MSG QA Automation Team.",
 		 			  	   myEmailBody = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.",      
  		    			   myEmailUser = "msgqaautomation@gmail.com",
 			 	           myEmailAccessKey = "Msgqa123"; 	 

 	  public int totalNumberOfPlatforms;
 	  public int numberOfPlatformsExecuted;
	  DesiredCapabilities myBrowserCapability = new DesiredCapabilities();	
	  Map<String, String[]> selectors;

 	 public List<String> getDriverParameters()
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

 	public DesiredCapabilities executeRemoteWebDriverInSauceLabsViaJenkins(String desktopOrMobile, String testCaseName, String testCase, Map<String, String[]> selectors) throws InterruptedException
 	{
// 		System.out.println(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
 		JsonElement myJSONElement = new JsonParser().parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
 	    JsonArray   myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
 	    int myDriverCount = 1;
 	    int n = 0;
	 	numberOfPlatformsExecuted = 0;
 		totalNumberOfPlatforms = myJSONPlatforms.size();
 	    for (int j=0;j<myJSONPlatforms.size();j++)
 		{
 	    	String  myPlatform = null, myOS = null, myBrowser = null, myBrowserVersion = null, mySLURL = null, myDevice = null, myDeviceOrientation = null;
 	    	
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
 	    		// Mobile specific parameters.
 	            if (myelem.getKey().toString().equals("device"))
 	            {
 	               myDevice = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
 	            }
 	            if (myelem.getKey().toString().equals("device-orientation"))
 	            {
 	               myDeviceOrientation = myelem.getValue().toString().substring(1, myelem.getValue().toString().length()-1);
 	            }
 	           
 	    		// 2. Once we get all variables then we can figure out the respective driver to be triggered.
 	    		if(myPlatform != null && myOS != null && myBrowser != null && myBrowserVersion != null && mySLURL != null)
 	    		{	
 	    		if(myBrowser.equals("firefox"))
 	    		{
 	    			myBrowserCapability = DesiredCapabilities.firefox();
 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Firefox Version: "+myBrowserVersion); 
 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
 	    	        myBrowserCapability.setCapability("screenResolution","2360x1770");   
 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
 	    	        myBrowserCapability.setCapability("platform", "macOS 10.12");
 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
 	    	        myBrowserCapability.setCapability("commandTimeout","599");
 	    			
 	    			WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("desktop")) {
 	    				Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Firefox Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, false);
 	    				myRockettes.start();
 	    			}
 	    		}
 	    		 		
 	    		if(myBrowser.equals("chrome"))
 	    		{
 	    			myBrowserCapability = DesiredCapabilities.chrome();
 	    			//myBrowserCapability.setBrowserName(myBrowser);
 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Chrome Version: "+myBrowserVersion); 
 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));		
 	    	        myBrowserCapability.setCapability("screenResolution","2360x1770");   
 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
 	    	        myBrowserCapability.setCapability("platform", "macOS 10.12");
 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
 	    	        myBrowserCapability.setCapability("commandTimeout","599");
 	       		WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("desktop")) {
 	    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Chrome Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, false);	
 	    			myRockettes.start();
 	    			}
 	    		}
 	    		
 	    		if(myBrowser.equals("safari"))
 	    		{
 	    			myBrowserCapability = DesiredCapabilities.safari();
 	    			//myBrowserCapability.setBrowserName(myBrowser);
 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Safari Version: "+myBrowserVersion); 
 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));		  
 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
 	    	        /**
 	    	         * Need to have another OS for lower version of safari9 / OS X EI Capitan
 	    	         */
 	    	        if (myBrowserVersion.toString().contains("9"))
 	    	        {
 	    	        	myBrowserCapability.setCapability("platform", "OS X 10.11");
 	 	    	    myBrowserCapability.setCapability("screenResolution","2048x1536"); 
 	    	        }
 	    	        else
 	    	        {
 	    	        myBrowserCapability.setCapability("platform", "macOS 10.12");
 	    	        myBrowserCapability.setCapability("screenResolution","2360x1770"); 
 	    	        }
 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
 	    	        myBrowserCapability.setCapability("commandTimeout","599");
 	       		WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("desktop")) {
 	    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Safari Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, false);	
 	    			myRockettes.start();
 	    			}
 	    		}
 	    		if(myBrowser.equals("microsoftedge"))
 	    		{
 	    			myBrowserCapability = DesiredCapabilities.edge();
 	    			//myBrowserCapability.setBrowserName(myBrowser);
 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Edge Version: "+myBrowserVersion); 
 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));		
 	    	        myBrowserCapability.setCapability("screenResolution","2560x1600");   
 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
 	    	        myBrowserCapability.setCapability("platform", "Windows 10");
 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
 	    	        myBrowserCapability.setCapability("commandTimeout","599");
 	    	        WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("desktop")) {
 	    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": Edge Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, false);	
 	    			myRockettes.start();
 	    			}
 	    		}
 	    		if(myBrowser.equals("internet explorer"))
 	    		{
 	    			myBrowserCapability = DesiredCapabilities.internetExplorer();
 	    			//myBrowserCapability.setBrowserName(myBrowser);
 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": IE Version: "+myBrowserVersion); 
 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
 	    	        myBrowserCapability.setCapability("screenResolution","2560x1600");   
 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
 	    	        myBrowserCapability.setCapability("platform", "Windows 7");
 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
 	    	        myBrowserCapability.setCapability("commandTimeout","599"); 
 	    	         
 	       		WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("desktop")) {
 	    			Thread myRockettes = new RockettesRegressionScript(driver, "Platform: "+myPlatform+": IE Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, false);	
 	    			myRockettes.start();
 	    			}
 	    		}    		
 	    		/**
 	             * We will create the Mobile capabilities here.
 	             */
 	            if(myDevice != null && myDeviceOrientation != null)
 	            {
 	            // 2. Once we get all variables then we can figure out the respective driver to be triggered.
 	            if(myBrowser.equals("iphone"))
 	            {
 	                DesiredCapabilities myBrowserCapability = DesiredCapabilities.iphone();
 	                //myBrowserCapability.setBrowserName(myBrowser);
 	                myBrowserCapability.setCapability("maxDuration","10799");             
 	                myBrowserCapability.setCapability("commandTimeout","599");                        
 	                myBrowserCapability.setCapability("name", testCaseName + " - " + myDevice +" with "+myDeviceOrientation+" mode"); 
 	                myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
 	                myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
 	                myBrowserCapability.setCapability("public","team");             
 	                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
 	                myBrowserCapability.setCapability("os", myOS);
 	                myBrowserCapability.setCapability("deviceName",myDevice);
 	                myBrowserCapability.setCapability("deviceOrientation", myDeviceOrientation);              
 	                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
 	                WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("mobile")) {
 	                Thread myRockettes = new RockettesRegressionScript(driver, "Device: "+myDevice+" Orientation: "+myDeviceOrientation+" Safari Version: "+myBrowserVersion, myBrowserCapability, testCase, selectors, true);    
 	                myRockettes.start();
 	    			}
 	            }
 	            
 	            if(myBrowser.equals("ipad"))
 	            {
 	                DesiredCapabilities myBrowserCapability = DesiredCapabilities.ipad();
 	                //myBrowserCapability.setBrowserName(myBrowser);
 	                myBrowserCapability.setCapability("maxDuration","10799");             
 	                myBrowserCapability.setCapability("commandTimeout","599");             
 	                myBrowserCapability.setCapability("name", testCaseName + " - " + myDevice +" with "+myDeviceOrientation+" mode");  
 	                myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
 	                myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
 	                myBrowserCapability.setCapability("public","team");             
 	                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
 	                myBrowserCapability.setCapability("os", myOS);
 	                myBrowserCapability.setCapability("deviceName",myDevice);
 	                myBrowserCapability.setCapability("deviceOrientation", myDeviceOrientation);              
 	                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
 	                WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("mobile")) {
 	                Thread myRockettes = new RockettesRegressionScript(driver, "Device: "+myDevice+" Orientation: "+myDeviceOrientation+" Safari Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, true);    
 	                myRockettes.start();
 	    			}
 	            }
 	            
 	            if(myBrowser.equals("android"))
 	            {
 	                DesiredCapabilities myBrowserCapability = DesiredCapabilities.android();
 	                //myBrowserCapability.setBrowserName(myBrowser);
 	                myBrowserCapability.setCapability("maxDuration","10799");             
 	                myBrowserCapability.setCapability("commandTimeout","599");             
 	                myBrowserCapability.setCapability("idleTimeout","1000");             
 	                myBrowserCapability.setCapability("name", testCaseName + " - " + myDevice +" with "+myDeviceOrientation+" mode"); 
 	                myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
 	                myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
 	                myBrowserCapability.setCapability("public","team");             
 	                myBrowserCapability.setCapability("browserVersion",myBrowserVersion);
 	                myBrowserCapability.setCapability("os", myOS);
 	                myBrowserCapability.setCapability("deviceName",myDevice);
 	                myBrowserCapability.setCapability("deviceOrientation", myDeviceOrientation);              
 	                myBrowserCapability.setCapability(CapabilityType.PLATFORM, myPlatform);
 	                WebDriver driver = null;
 	    			if (desktopOrMobile.equalsIgnoreCase("mobile")) {
 	                Thread myRockettes = new RockettesRegressionScript(driver, "Device: "+myDevice+" Orientation: "+myDeviceOrientation+" Safari Version: "+myBrowserVersion,myBrowserCapability, testCase, selectors, true);    
 	                myRockettes.start();
 	    			}
 	            }
 	            
 	    		}	    		
 	    	}
 		}
 	    	System.out.println("Test("+myDriverCount+") Parameters are: Platform = "+myPlatform+", OS = "+myOS+", Browser = "+myBrowser+", BrowserVersion = "+myBrowserVersion);
 	    	myDriverCount++;
 		}
 	    return myBrowserCapability;
 	}

 	    public void clickE(ExtendedWebElement eWE) {
 	    	click(eWE);
 	    }
 	    
        public String getCurrentTimestamp() {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

            //method 1
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.println(timestamp);

            //method 2 - via Date
            Date date = new Date();
//            System.out.println(new Timestamp(date.getTime()));

            //return number of milliseconds since January 1, 1970, 00:00:00 GMT
//            System.out.println(timestamp.getTime());

            //format timestamp
//            System.out.println(sdf.format(timestamp));
            
            return timestamp.toString();

        }

        public HashMap<String, String> convertHashMapStringToHashMap(String stringOfHashMap) throws IOException {
	 	   String str = stringOfHashMap;
	 	   Properties props = new Properties();
	 	   props.load(new StringReader(str.substring(1, str.length() - 1).replace(", ", "\n")));       
	 	   HashMap<String, String> mobileTestCasesResultsHashMap = new HashMap<String, String>();
	 	   for (Map.Entry<Object, Object> e : props.entrySet()) {
	 		  mobileTestCasesResultsHashMap.put((String)e.getKey(), (String)e.getValue());
	 	   }
	 	   return mobileTestCasesResultsHashMap;
        }
 	 
}