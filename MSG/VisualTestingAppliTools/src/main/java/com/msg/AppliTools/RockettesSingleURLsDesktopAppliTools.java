package com.msg.AppliTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

public class RockettesSingleURLsDesktopAppliTools {
    
      public static final String myUSERNAME = "msgtesting";
      public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8"; 
      public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

      public static String myRockettesURL = "http://qa.rockettes.com/christmas/calendar";
    
  public static void main(String[] args) {

      DesiredCapabilities myBrowserCapability = new DesiredCapabilities();     
      myBrowserCapability.setBrowserName("chrome");
      myBrowserCapability.setCapability("name", "Rockettes Baseline AppliTools Single URL Test - Calendar"); 
      myBrowserCapability.setCapability("screenResolution","2360x1770");   
      myBrowserCapability.setCapability("version", "62.0");
      myBrowserCapability.setCapability("platform", "macOS 10.12");
      myBrowserCapability.setCapability("maxDuration","10799");             
      myBrowserCapability.setCapability("commandTimeout","599");      
       
        WebDriver driver = null;
        
        try {
            String URL = URLwithHardCodedVariables;    
            driver = new RemoteWebDriver(new URL(URL), myBrowserCapability);
        } catch (MalformedURLException e) {
            System.out.println(e + e.getMessage());
            e.printStackTrace();
        }
        
		BatchInfo batchInfo = new BatchInfo("Rockettes_Test_Automation_Manual_Calendar_Validation");
		String batchId = "Rockettes_Test_Automation_Manual_Calendar_Validation"+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		if (batchId != null) {
		    batchInfo.setId(batchId);
		}
		
        Eyes eyes = new Eyes();
        eyes.setApiKey("DJZskprruP4VgGih6dp4ilrLk0MDoVDItxuZpBXmIUA110");
        eyes.setForceFullPageScreenshot(true);
        eyes.setHideScrollbars(true);
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setMatchLevel(MatchLevel.LAYOUT2);
		eyes.setBatch(batchInfo);
        
    	      try{  
              driver.get(myRockettesURL);
              System.out.println("Rockettes page is: "+driver.getTitle()+" and URL is: "+driver.getCurrentUrl());
              eyes.open(driver, "Rockettes", "Desktop - "+driver.getTitle()); 
              eyes.checkWindow();
              eyes.close();
    	      }catch(NewTestException e)
    	      {
    	    	 	System.out.println("Let's store Baseline version for Rockettes!!");
    	      }
    	      catch(UnsupportedCommandException e1)
    	      {
  	    	 	System.out.println("some Exception we can continue");
    	      }
    	      catch(TestFailedException e2)
    	      {
  	    	 	System.out.println("some Exception we can continue");
    	      }

     /* finally {

          // Close the browser.
          driver.quit();

          // If the test was aborted before eyes.close was called, ends the test as aborted.
          eyes.abortIfNotClosed();
      }*/

          // Close the browser.
          driver.quit();

          // If the test was aborted before eyes.close was called, ends the test as aborted.
          eyes.abortIfNotClosed();
  }

}