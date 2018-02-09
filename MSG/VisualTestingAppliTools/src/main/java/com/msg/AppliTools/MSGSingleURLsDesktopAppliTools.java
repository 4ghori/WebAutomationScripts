package com.msg.AppliTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

public class MSGSingleURLsDesktopAppliTools {
    
      public static final String myUSERNAME = "msgtesting";
      public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8"; 
      public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

      public static String myMSGURL = "http://www.msg.com/";
    
  public static void main(String[] args) {

      DesiredCapabilities myBrowserCapability = new DesiredCapabilities();     
      myBrowserCapability.setBrowserName("chrome");
      myBrowserCapability.setCapability("name", "Rockettes Baseline AppliTools Single URL Test - Calendar"); 
      myBrowserCapability.setCapability("screenResolution","2360x1770");   
      myBrowserCapability.setCapability("version", "62.0");
      myBrowserCapability.setCapability("platform", "macOS 10.12");
      myBrowserCapability.setCapability("maxDuration","10799");             
      myBrowserCapability.setCapability("commandTimeout","599");      
       
      DesiredCapabilities myMobileBrowserCapability = new DesiredCapabilities();      
      myMobileBrowserCapability = DesiredCapabilities.iphone();  
      myMobileBrowserCapability.setCapability("name", "Msg.com Baseline AppliTools Multiple URL Test (Mobile)"); 
      myMobileBrowserCapability.setCapability("appiumVersion", "1.7.1");
      myMobileBrowserCapability.setCapability("deviceName","iPad Air 2 Simulator");
      myMobileBrowserCapability.setCapability("deviceOrientation", "portrait");
      myMobileBrowserCapability.setCapability("platformVersion","11.0");
      myMobileBrowserCapability.setCapability("platformName", "iOS");
      myMobileBrowserCapability.setCapability("browserName", "Safari"); 
      
        WebDriver driver = null;
        
        try {
            String URL = URLwithHardCodedVariables;    
            driver = new RemoteWebDriver(new URL(URL), myMobileBrowserCapability);
        } catch (MalformedURLException e) {
            System.out.println(e + e.getMessage());
            e.printStackTrace();
        }
        
        
/*        Map params = new HashMap<Object, Object>();        
        params.put("property", "model");       
        String properties = (String) ((RemoteWebDriver) driver).executeScript("mobile:handset:info", params);
        System.out.println(properties);*/
/*        System.out.println("Debug-Point1: "+((RemoteWebDriver) driver).getCapabilities());
        System.out.println("Debug-Point2: "+((RemoteWebDriver) driver).getCapabilities().toString());
        System.out.println("Debug-Point3: "+((RemoteWebDriver) driver).getCapabilities().getCapability("platform").toString());
        System.out.println("Debug-Point4: "+((RemoteWebDriver) driver).getCapabilities().asMap());
        System.out.println("Debug-Point5: "+((RemoteWebDriver) driver).getCapabilities().asMap().toString());*/
/*        Map<String, ?> myDriverParameters = new HashMap<String, Object>();
        myDriverParameters = ((RemoteWebDriver) driver).getCapabilities().asMap();
        System.out.println("Debug-Point6: "+myDriverParameters.get("device").toString());*/
        System.out.println("Debug-Point7: "+((RemoteWebDriver) driver).getCapabilities().asMap().get("device").toString());
        	
        Actions builder = new Actions(driver);   
        //builder.moveToElement(knownElement, 10, 25).click().build().perform();
        		
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
              driver.get(myMSGURL);
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