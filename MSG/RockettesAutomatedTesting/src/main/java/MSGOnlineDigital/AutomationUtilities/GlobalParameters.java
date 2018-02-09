package MSGOnlineDigital.AutomationUtilities;

import java.util.Calendar;

public class GlobalParameters
{	  
	  // SauceLabs User Name and Access Key - to be read from Environment Variables
	  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");   
	  public static final String URLwithEnvVariables = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	  
	  // SauceLabs User Name and Access Key - Fallback in case Environment variables are not setup
	  public static final String myUSERNAME = "rachit";
   public static final String myACCESS_KEY = "05d919ba-84e4-49fb-b84c-69f8af1918bf";   
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
	  					   myEmailFrom = "msgqaautomation@gmail.com",
//  			  		   myEmailToList = "rachitkumar.rastogi@msg.com; richard.barrow@msg.com; krishna.chaparala@msg.com; sri.anne@msg.com",
	  	    			   myEmailToList = "rachitkumar.rastogi@msg.com",
	  					   myEmailFailedSubject = "Jenkins Error - Rockettes Regression Script is failed!",
			  			   myEmailSuccessSubject = "Jenkins Success - Rockettes Regression Script is successful.",
			  			   myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"\r\nThanks, Your MSG QA Automation Team.",
		 			  	   myEmailBody = "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.",      
		    			   myEmailUser = "msgqaautomation@gmail.com",
			 	           myEmailAccessKey = "Msgqa123"; 	  
}