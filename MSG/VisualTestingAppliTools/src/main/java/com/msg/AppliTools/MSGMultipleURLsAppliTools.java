package com.msg.AppliTools;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FixedCutProvider;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.NewTestException;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

public class MSGMultipleURLsAppliTools {
    
      public static final String myUSERNAME = "msgtesting";
      public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8"; 
      public static final String URLwithHardCodedVariables = "https://" + myUSERNAME + ":" + myACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

      public static String myRockettesURL = "http://www.msg.com/";
    
  public static void main(String[] args) {

	  /**
	   * DesiredCapabiltiies for Desktop
	   */
        DesiredCapabilities myBrowserCapability = new DesiredCapabilities();     
        myBrowserCapability = DesiredCapabilities.chrome();    
        myBrowserCapability.setBrowserName("chrome");
        myBrowserCapability.setCapability("name", "Msg.com Baseline AppliTools Multiple URL Test (Desktop)"); 
        myBrowserCapability.setCapability("browser.safebrowsing.phishing.enabled;","true");
        myBrowserCapability.setCapability("public","team");                
        myBrowserCapability.setCapability("screenresolution","2360x1770");                
        myBrowserCapability.setCapability(CapabilityType.PLATFORM, "Mac 10.12");
       
        /**
         * DesiredCapabilities for Mobile
         */
        DesiredCapabilities myMobileBrowserCapability = new DesiredCapabilities();     
        myMobileBrowserCapability = DesiredCapabilities.iphone();  
        myMobileBrowserCapability.setCapability("name", "Msg.com Baseline AppliTools Multiple URL Test (Mobile)"); 
        myMobileBrowserCapability.setCapability("appiumVersion", "1.7.1");
        myMobileBrowserCapability.setCapability("deviceName","iPhone X Simulator");
        myMobileBrowserCapability.setCapability("deviceOrientation", "portrait");
        myMobileBrowserCapability.setCapability("platformVersion","11.0");
        myMobileBrowserCapability.setCapability("platformName", "iOS");
        myMobileBrowserCapability.setCapability("browserName", "Safari");
       // myMobileBrowserCapability.setCapability("autoAcceptAlerts",true);
        myMobileBrowserCapability.setCapability("autoDismissAlerts",true);
            
        WebDriver driver = null;
        
        try {
            String URL = URLwithHardCodedVariables;    
            //driver = new RemoteWebDriver(new URL(URL), myBrowserCapability);
            driver = new RemoteWebDriver(new URL(URL), myMobileBrowserCapability);
            Eyes eyes = new Eyes();
            eyes.setApiKey("DJZskprruP4VgGih6dp4ilrLk0MDoVDItxuZpBXmIUA110");
            eyes.setForceFullPageScreenshot(true);
            eyes.setHideScrollbars(true);
            eyes.setStitchMode(StitchMode.CSS);
            eyes.setImageCut(new FixedCutProvider(95,135,0,0)); //remove URL and footer.

    	  for(String myURL:MSGURLs.getMSGSalesCenterProductionURLs().keySet())
    	  {
    	      try{  
              driver.get(MSGURLs.getMSGSalesCenterProductionURLs().get(myURL));
              System.out.println("msg.com page is: "+driver.getTitle()+" and URL is: "+driver.getCurrentUrl());           
 //             System.out.println(" Check for the number of handles: "+driver.getWindowHandles().size());
/*              while(driver.getWindowHandles().iterator().hasNext())
              {
            	  System.out.println("Print the handle: "+driver.getWindowHandle());
              }*/

/*              for (String handle : driver.getWindowHandles()) 
              {
            	  	System.out.println("Print the handle: "+driver.getWindowHandle());
                driver.switchTo().window(handle);
                driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();
              }*/
              
             // ((JavascriptExecutor) driver).executeScript("window.confirm = function(msg) { return true; }");
              
              //driver.switchTo().alert().accept();
              //eyes.open(driver, "msg.com - ", driver.getTitle(),
              //        new RectangleSize(2100, 1000));
              eyes.open(driver, "msg.com", "Mobile - "+driver.getTitle()); 
           // Visual checkpoint #1.
              eyes.checkWindow("The Madison Square Garden Company");
              eyes.close();
    	      }catch(NewTestException e)
    	      {
    	    	 	System.out.println("Let's store Baseline version for Msg.com!!");
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
        catch(WebDriverException e)
        {
        	System.out.println("Error in fetching the remote webdriver in Saucelabs!!");
        } catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  }

}