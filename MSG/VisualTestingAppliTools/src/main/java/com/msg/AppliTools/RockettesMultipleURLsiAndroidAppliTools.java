package com.msg.AppliTools;

import java.net.MalformedURLException;
import java.net.URL;

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

public class RockettesMultipleURLsiAndroidAppliTools {
    
      public static final String myUSERNAME = "msgtesting";
      public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8"; 
      public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

      public static String myRockettesURL = "http://qa.rockettes.com/";
    
  public static void main(String[] args) {

      DesiredCapabilities myMobileBrowserCapability = new DesiredCapabilities();     
      myMobileBrowserCapability = DesiredCapabilities.android(); 
      myMobileBrowserCapability.setCapability("name", "Msg.com Baseline AppliTools Multiple URL Test (Mobile - Android)"); 
      myMobileBrowserCapability.setCapability("appiumVersion", "1.6.4");
      myMobileBrowserCapability.setCapability("deviceName","Android Emulator");
      myMobileBrowserCapability.setCapability("deviceOrientation", "portrait");
      myMobileBrowserCapability.setCapability("browserName", "Chrome");
      myMobileBrowserCapability.setCapability("platformVersion", "6.0");
      myMobileBrowserCapability.setCapability("platformName","Android");
       
        WebDriver driver = null;
        
        try {
            String URL = URLwithHardCodedVariables;    
            driver = new RemoteWebDriver(new URL(URL), myMobileBrowserCapability);
        } catch (MalformedURLException e) {
            System.out.println(e + e.getMessage());
            e.printStackTrace();
        }
        
        Eyes eyes = new Eyes();
        eyes.setApiKey("DJZskprruP4VgGih6dp4ilrLk0MDoVDItxuZpBXmIUA110");
        eyes.setForceFullPageScreenshot(true);
        eyes.setHideScrollbars(true);
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setMatchLevel(MatchLevel.LAYOUT2);
        eyes.setImageCut(new FixedCutProvider(95,135,0,0));
        
    	  for(String URL:RockettesURLs.getRockettesQaURLs().keySet())
    	  {
    	      try{  
              driver.get(RockettesURLs.getRockettesQaURLs().get(URL));
              System.out.println("Rockettes page is: "+driver.getTitle()+" and URL is: "+driver.getCurrentUrl());
              eyes.open(driver, "Rockettes", "Mobile (QA env.) - "+driver.getTitle()); 
              eyes.checkWindow();
              eyes.close();
    	      }catch(NewTestException e)
    	      {
    	    	 	System.out.println("Let's store Baseline version for Rockettes!!");
    	    	      continue;
    	      }
    	      catch(UnsupportedCommandException e1)
    	      {
  	    	 	System.out.println("some Exception we can continue");
	    	      continue;
    	      }
    	      catch(TestFailedException e2)
    	      {
  	    	 	System.out.println("some Exception we can continue");
	    	      continue;
    	      }
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