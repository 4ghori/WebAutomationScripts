package com.msgqa.eventengine.testcases;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.msg.suites.pom.util.ExtentManager;
import com.msg.suites.pom.util.FBConstants;
import com.msg.suites.pom.util.RockettesSauceJobAccessController;
import com.msg.suites.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test_DriverBase 
{
	public static ExtentReports extent = ExtentManager.getInstance();
	public  ExtentTest test;
	public  ExtentTest ScenarioReport;
	
	public  String PassFailFailDescription;
	public static ArrayList<String> PassFailScreenFileName = new ArrayList<String>();

	//===== Sauce Connect Credentials 
 	public static final String USERNAME = "MDRasul";
 	public static final String ACCESS_KEY = "1df585b2-88bc-4578-8a98-c920e9eaf383";
 	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
 	public  SessionId mySessionID;
 
 	public WebDriver driver;
 	DesiredCapabilities myBrowserCapability = new DesiredCapabilities();
 	static String myScreenResolutionCapability = "screenresolution";

	//==> Constructors 
 	public Test_DriverBase(){}  
 	public Test_DriverBase(WebDriver driver, ExtentTest test,ExtentTest ScenarioReport)
		{
			//super(driver,test);
			this.driver=driver;
			this.test=test;
			this.ScenarioReport=ScenarioReport;
		}
	
	public void init(String bType, String TestName)
	{
		test.log(LogStatus.INFO, "Opening browser - "+ bType);
		if(!FBConstants.GRID_RUN)
		{
			// local machine
			if(bType.equals("Mozilla"))
			{
				System.setProperty("webdriver.gecko.driver", FBConstants.Gecko_DRIVER_EXE);
				driver= new FirefoxDriver();
				
				//ProfilesIni allprof = new ProfilesIni();
				//FirefoxProfile prof = allprof.getProfile("default");
				//driver = new FirefoxDriver(prof);
				//driver.get("https://www.msgmarqueeathome.com/");
			}
			else if(bType.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
    				myBrowserCapability = DesiredCapabilities.chrome();	
    			//myBrowserCapability.setCapability(myScreenResolutionCapability,"1600x1200"); 
				driver= new ChromeDriver(myBrowserCapability);
				
				//**** Debug Code  using My Local machine Deafult Chrome Profile to Work with Aspire Marque Cash 
					/*ChromeOptions options = new ChromeOptions();
					//options.addArguments("user-data-dir=C:/Users/user_name/AppData/Local/Google/Chrome/User Data");
					options.addArguments("user-data-dir=/Users/rasulm/Library/Application Support/Google/Chrome");
					options.addArguments("--start-maximized");
					driver = new ChromeDriver(options);*/
			}
		}
		else
		{
			// grid - Sauce Lab
			DesiredCapabilities cap=null;
			if(bType.equals("Mozilla"))
			{
				cap = DesiredCapabilities.firefox();
				cap.setCapability("name", TestName);
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			else if(bType.equals("Chrome"))
			{
				 cap = DesiredCapabilities.chrome();
				 cap.setCapability("name", TestName);
				 cap.setBrowserName("chrome");
				 cap.setCapability(myScreenResolutionCapability,"1600x1200");    			
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try 
			{
				//==Grid Original Code 
					//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				//==> Sending Test to Sauce Lab 
			    	driver = new RemoteWebDriver(new URL(URL), cap);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}

		//===>> Post Driver / Browser Works
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened browser successfully - "+ bType);
		mySessionID = ((RemoteWebDriver)driver).getSessionId();	
	}
	
	public void reportTestSteps(String sTestPassOrFail,String sTestStep, String sDescription, boolean bTakeScreenShot )
	{
		if(sTestPassOrFail=="PASS")
		{
			ScenarioReport.log(LogStatus.PASS,  sTestStep + " -> Passed");
			if(bTakeScreenShot==true)
			{
				takePassFailScreenShots(sTestStep);
			}
		}
		else if(sTestPassOrFail=="FAILED")
		{
			ScenarioReport.log(LogStatus.FAIL,  sTestStep + "-> FAILED :-( <br>" + sDescription);
			if(bTakeScreenShot==true)
			{
				takePassFailScreenShots(sTestStep);
			}
		}
		
	}
	public void takePassFailScreenShots(String currentScenario)
	{
		currentScenario.replaceAll("\\s","");
		Date d=new Date();
		String screenshotFile=currentScenario+"-"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(scrFile, new File(filePath));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//PassFailScreenFileName.add(filePath);
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(PassFailScreenFileName.get(i)));

		ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(filePath));
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture("screenshots/"+screenshotFile));
	}
	
	public static void sendPAssFailToSauce(SessionId mySessionID,boolean PassOrFail,String myUSERNAME, String myACCESS_KEY)
	{
		
		if(USERNAME == null && ACCESS_KEY == null)//
		{
				RockettesSauceJobAccessController mySauceJobStatusUpdate = new RockettesSauceJobAccessController(myUSERNAME,myACCESS_KEY);	
				Map<String, Object> myUpdates = new HashMap<String, Object>();	//		
				if (PassOrFail == false )//
					myUpdates.put("passed", false);					
				else
					myUpdates.put("passed", true);						
				try 
				{
					mySauceJobStatusUpdate.updateJobInfo(mySessionID.toString(), myUpdates);
					System.out.println("Saucelabs Job Status is updated via Saucelabs REST API.");
				} 
				catch (IOException e) 
				{
					System.out.println("Saucelabs Job Status can't be updated via Saucelabs REST API.");
				}
		}
		else
		{
			RockettesSauceJobAccessController mySauceJobStatusUpdate = new RockettesSauceJobAccessController(USERNAME,ACCESS_KEY);	
			Map<String, Object> myUpdates = new HashMap<String, Object>();			
			if (PassOrFail == false )
				myUpdates.put("passed", false);					
			else
				myUpdates.put("passed", true);						
			try 
			{
				mySauceJobStatusUpdate.updateJobInfo(mySessionID.toString(), myUpdates);
				System.out.println("Saucelabs Job Status is updated via Saucelabs REST API.");
			} catch (IOException e) 
			{
				System.out.println("Saucelabs Job Status can't be updated via Saucelabs REST API.");
			}
		}
	}

	
	
	
}
