package com.msgqa.eventengine.testcases;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.postgresql.util.PSQLException;
import org.testng.Assert;

import com.mailinator.InboxMessage;
import com.mailinator.Mailinator;
import com.msg.suites.pom.util.FBConstants;
import com.msg.suites.pom.util.Xls_Reader;
import com.msgqa.eventengine.config.MSGEventEngineURLs;
import com.msgqa.eventengine.pages.EE_CommonSideBar;
import com.msgqa.eventengine.pages.EE_EventsEditPage;
import com.msgqa.eventengine.pages.EE_EventsPage;
import com.msgqa.eventengine.pages.EE_HomePage;
import com.msgqa.eventengine.pages.EE_LoginPage;
import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;


public class Test_Keywords_Runner extends Test_DriverBase
{
	
	
		public String currentTestSuite;  // Name Of the Excel File which Is Running 
	
	//==>> Driver That Perform All Actions on UI - This will Be Loaded In Constructor with driver coming From Test 
		//public WebDriver driver;
		//public ExtentTest test;
		//public ExtentTest ScenarioReport;
		public ReportTest report = null;

	//==>> Reporting Variable
		public static String PassFailFailDescription;
		
	
	public Test_Keywords_Runner(WebDriver driver, ExtentTest test,ExtentTest ScenarioReport )
	{
		super(driver,test,ScenarioReport);
		//this.driver=driver;
		//this.test=test;
		//this.ScenarioReport=ScenarioReport;
	}
	
 //################### Event Engine Page Specific Steps  ##################################################################################################

	public boolean EE_AdminLogin(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
		//===========> Variables That will Hold MEthod Name / Execution Status Pass / Fail and If Failed its Reason  
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		boolean bReturnPass=false;
		String sFailReason = null;
	
			//==>Initialize EE Login Page & Land On EE Login Page 
			EE_LoginPage eeLogin = new EE_LoginPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eeLogin);
			
			String sLoginPageURL = MSGEventEngineURLs.getEnvDetails().get("URL"); 
			boolean bLandOnLoginPage = eeLogin.goTo(sLoginPageURL);
			
			//==>If we Land On Login Page  
			if(bLandOnLoginPage == true)
			{
				//==>Enter Default ID PAssword and Check we are In HOme Page   
				Object oLoginReturnPage=eeLogin.doLogin("default");
				if(oLoginReturnPage instanceof EE_HomePage)
				{
					bReturnPass =  true;
				}
				else //==>Couldn't Login using ID / Password Something Wrong 
				{
					bReturnPass =  false;
					sFailReason = oLoginReturnPage.toString();
				}
			}
			else//==>Failed TO Land On Login Page   
			{
				bReturnPass =  false;
				sFailReason = "Failed to Land On Event Engine Login Page";
			}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}
	}

	public boolean NavigateTo_EventPage(String Step, String Page, String ObjectName, String Input) throws IllegalArgumentException, IllegalAccessException
	{
		//===> Variables That will Hold MEthod Name / Execution Status Pass / Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		//===> - Navigation Logic - 
		//===> All Logged In  Page Contains the Common Side Bar ( Left Navigation Bar)  
		//===> so we will create a Object Of Side Bar Class can call the Method to Navigate to Respective LINKED PAGE
		try {
			EE_CommonSideBar eeSideBar = new EE_CommonSideBar(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eeSideBar);
			EE_EventsPage eventPage = eeSideBar.gotoEventPage();
			bReturnPass =  true;
		}catch(Exception e) {
			
			bReturnPass =  false;
			//sFailReason = "Couldn't navigate to " + thisMethodName.split("_")[1]  ;
			sFailReason = " [ " + e.toString() + " ] ";
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}

	}
    

	public boolean EventPage_Validate_Element_Exist(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Hold MEthod Name / Execution Status Pass / Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try {
			EE_EventsPage eventPage = new EE_EventsPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventPage);
			eventPage.Validate_Element_Present(ObjectName);
			bReturnPass =  true;
		}catch(Exception e) {
			
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			return false;

		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
		
	}
	
	public boolean EventPage_SearchEventByEventName(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsPage eventPage = new EE_EventsPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventPage);
			
			if(eventPage.SearchEventByEventName(ObjectName)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't Find Event for" + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
		
	}
	
	public boolean EventPage_SearchAndClickFirstEvent(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsPage eventPage = new EE_EventsPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventPage);
			
			if(eventPage.SearchAndClickFirstEvent(ObjectName)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't CLick Event for" + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
		
	}
	

	public boolean EventEditPage_SearchAvailableArtistAndAdd(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventEditPage);
			
			if(eventEditPage.SearchAvailableArtistAndAdd(ObjectName)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't Add An Artist - > " + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
		
	}
	
	
	
	public boolean EventEditPage_PublishEvent(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean bReturnPass=false;
			String sFailReason = null;

		//===> Function Body 
			try 
			{
				EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
				PageFactory.initElements(driver, eventEditPage);
				
				if(eventEditPage.PublishEvent(ObjectName)==true) // Generic Add Page Function here
				{
					bReturnPass =  true;
				}
				else
				{
					bReturnPass =  false;
					sFailReason = "Couldn't Publish The Event - > " + ObjectName ;
				}
			}
			catch(Exception e) 
			{
				sFailReason = e.getMessage();
				bReturnPass =  false;
			}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
			if(bReturnPass==true)
			{
				return true;
			}
			else
			{
				reportTestSteps("FAILED",thisMethodName,sFailReason , true);
				System.out.println("Failed Test_Keywords : " + thisMethodName + " >> " + sFailReason);
				return false;
			}		
	}	
		
	public boolean EventEditPage_MarkAsHeadlinerArtist(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventEditPage);
			
			if(eventEditPage.MarkAsHeadlinerArtist(ObjectName)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't Add An Artist - > " + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
		
	}
	
	public boolean EventEditPage_AddArrivingAtDescription(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventEditPage);
			
			if(eventEditPage.ArrivingAtDescription(ObjectName)==true)
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = "Couldn't Add An Arriving At Description Text - > " + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed : " + thisMethodName + ">>" + sFailReason);
			return false;
		}		
	}	
	
	public boolean EventEditPage_AddRemoveCategory(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean bReturnPass=false;
		String sFailReason = null;

		try 
		{
			EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
			PageFactory.initElements(driver, eventEditPage);
			
			if(eventEditPage.AddRemoveCategory(ObjectName)==true) // Generic Add Page Function here
			{
				bReturnPass =  true;
			}
			else
			{
				bReturnPass =  false;
				sFailReason = " Couldn't Add Remove Category - > " + ObjectName ;
			}
		}
		catch(Exception e) 
		{
			sFailReason = e.getMessage();
			bReturnPass =  false;
		}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
		if(bReturnPass==true)
		{
			return true;
		}
		else
		{
			reportTestSteps("FAILED",thisMethodName,sFailReason , true);
			System.out.println("Failed Test_Keywords : " + thisMethodName + " >> " + sFailReason);
			return false;
		}		
	}	
	
	public boolean EventEditPage_AddRemoveSponsor(String Step, String Page, String ObjectName, String Input)
	{
		//===> Variables That will Store MEthod Name, Execution Status Pass/Fail and If Fail Reason For Failing
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			boolean bReturnPass=false;
			String sFailReason = null;

		//===> Function Body 
			try 
			{
				EE_EventsEditPage eventEditPage = new EE_EventsEditPage(driver,test,ScenarioReport);
				PageFactory.initElements(driver, eventEditPage);
				
				if(eventEditPage.AddRemoveSponsor(ObjectName)==true) // Generic Add Page Function here
				{
					bReturnPass =  true;
				}
				else
				{
					bReturnPass =  false;
					sFailReason = "Couldn't Add / Remove Sponsor - > " + ObjectName ;
				}
			}
			catch(Exception e) 
			{
				sFailReason = e.getMessage();
				bReturnPass =  false;
			}
		
		//=====>> Return Pass Or Fail of This Step If Fail Report it inside Scenario Report 
			if(bReturnPass==true)
			{
				return true;
			}
			else
			{
				reportTestSteps("FAILED",thisMethodName,sFailReason , true);
				System.out.println("Failed Test_Keywords : " + thisMethodName + " >> " + sFailReason);
				return false;
			}		
	}	
	
	
	
	
//###################  Helper Functions  ##################################################################################################
     
	public String getFieldValues(final Object obj, String FieldName)
		    throws IllegalArgumentException,IllegalAccessException
		  {
		
	      Object value = "";

		    Class<? extends Object> c1 = obj.getClass();
		    Field[] fields = c1.getDeclaredFields();
		    
		    for (int i = 0; i < fields.length; i++) {
		      String name = fields[i].getName();
		      if(name.equals(FieldName))
		      {
		    	   value = fields[i].get(obj);
		    	   break;
		      }
		      
		    }
		    return (String) value;
		  }	
	public boolean fEnsurePageIsReady(String oPage) throws IllegalArgumentException, IllegalAccessException
	{
		int giTimeOut = 2;
		boolean bFound = false;
		int iMax = 0;
		String ErrorMsg="";
		String ObjectLocator;

	
		//==> Get The "Page" Locator From Excel File If Available 
		 ObjectLocator = GetObjectLocatorXPathCssEtcFromRepo(oPage);;

		//==> If Locator Not Available in Excel File Get it From REPO Java File 
  		 if(ObjectLocator.equals(""))
  		 {
	   		//Suite_Test_Repository dummy = new Suite_Test_Repository();
	   		//ObjectLocator = getFieldValues(dummy,oPage.trim());
  		 }
	   	
  		 if(ObjectLocator.equals(null)) ObjectLocator="";

	   //==> Check Page's Unique Object is Not Null Or Empty - If Empty Return False  or Log Page is Ready...		
			if(!ObjectLocator.equals(""))
			{
				while(iMax < giTimeOut  && !bFound)
				{
					try
					{
						iMax++; 
						int s = driver.findElements(By.xpath(ObjectLocator)).size();

						if(s==0)
						{
							bFound=false;
						}
						else
						{
							//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Page : ["+oPage + "] : Is Ready ");
							bFound =  true;
						}
					}
					catch(Exception e)
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, );
						bFound=false;
						System.out.println(e.getMessage());
						ErrorMsg = e.getMessage();
					}

				}
			 }

		//==> If Page's Unique Object Failed - Report Error And Take a Screen Shot 		
			if(bFound==false)
			{
				PassFailFailDescription = "Landing Page [" + oPage +"] is Not ready...<br>" + ErrorMsg;
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Landing Page [" + oPage +"] is Not ready..." + ErrorMsg);
				//takeScreenShot();
			}
		return bFound;
	}
	public boolean fEnsureObjectIsReady(String oObjectName) throws IllegalArgumentException, IllegalAccessException
	{
		int giTimeOut = 2;
		boolean bFound = false;
		int iMax = 0;
		String ErrorMsg="";
		String ObjectLocator;
	
		//==> Get The Object Locator From Excel File If Available 
		 ObjectLocator = GetObjectLocatorXPathCssEtcFromRepo(oObjectName);;

 		//==> If Locator Not Available in Excel File Get it From REPO Java File 
   		 if(ObjectLocator.equals(""))
   		 {
 	   		//Suite_Test_Repository dummy = new Suite_Test_Repository();
 	   		//ObjectLocator = getFieldValues(dummy,oObjectName.trim());
   		 }
	   	
   		 if(ObjectLocator.equals(null)) ObjectLocator="";

	    //==> See If this Object is Ready in Page Before Perform any Action ( Click Validate ) on it 		
			if(!ObjectLocator.equals(""))
			{
				while(iMax < giTimeOut  && !bFound)
				{
					try
					{
						iMax++; 
						driver.findElement(By.xpath(ObjectLocator)).isDisplayed();
						int s = driver.findElements(By.xpath(ObjectLocator)).size();
						if(s==0)
						{
							bFound=false;
						}
						else
						{
							//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, "Object : ["+oObjectName + "] : Is Ready ");
							bFound =  true;
						}
					}
					catch(Exception e)
					{
						//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, );
						bFound=false;
						System.out.println(e.getMessage());
						ErrorMsg = e.getMessage();
					}

				}
			 }

		//==> If Page's Unique Object Failed - Report Error And Take a Screen Shot 		
			if(bFound==false)
			{
				PassFailFailDescription = "Target WebElement [" + oObjectName +"] is Not Found...<br>" + ErrorMsg + "<br>";
				//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, "Target Object [" + oObjectName +"] is Not ready..." + ErrorMsg);
				//takeScreenShot();
			}
		return bFound;
	}
	public String GetObjectLocatorXPathCssEtcFromRepo(String ObjectName) throws IllegalArgumentException, IllegalAccessException
	{
		String Locator="";

		// Get the Locator From Excel If Available 
		Map<String, String[]> AllLocatorFromTestCaseExcelFileObjectCommonSheet = getlistOfAllSelectors();
		String[] findLocator = new String[2];
		findLocator = AllLocatorFromTestCaseExcelFileObjectCommonSheet.get(ObjectName);
 		//if(findLocator.equals(null)) findLocator="";
		//System.out.println(findLocator.length);
		
		if(findLocator!=null)
		{
			Locator = findLocator[0];
			return Locator;
		}

		/*
		int count =1;
		for (String key : AllLocatorFromTestCaseExcelFileObjectCommonSheet.keySet()) 
		{
			String[] myValues = new String[2];
			myValues = AllLocatorFromTestCaseExcelFileObjectCommonSheet.get(key);
			System.out.println("Entry"+count+" "+key+" "+myValues[0]+" "+myValues[1]);
			// Out Put :        Entry1 SuiteHolderUser_Firstname_Field .//*[@id='editshuser']/div[2]/div[1]/div[1]/div[2]/input Xpath
			// Out Put :        Entry3 Admin_EventdetailsPage   CSS
			if(key.trim().equals(ObjectName.trim()))
			{
				Locator = myValues[0];
				return Locator;
			}
			count++;
		}
		*/
		

		//===>> If Locator (Xpath,CSS)  Not Found in Excel File look it in Java repo Class File  
			//Suite_Test_Repository dummy = new Suite_Test_Repository();
			//String ObjectLocator = getFieldValues(dummy,ObjectName);
			/*if(ObjectLocator.equals(null)) ObjectLocator="";

			if(!ObjectLocator.equals(""))
			{
				Locator = ObjectLocator.trim();
			}
			else 
			{
				//ZTest_DriverThreadThread.ScenarioReport.log(LogStatus.ERROR, "This Object " + ObjectName +" is Not Found In Suite_Test_Repository Java File..");
				System.out.println("\n ***** This Object " + ObjectName +" is Not Found In Suite_Test_Repository Java File..\n");
			}*/
		
		
		return Locator;
	}
	public Map<String, String[]> getlistOfAllSelectors()  throws IllegalAccessException, IllegalArgumentException
	{
		//Map<String, String[]> myDesiredSelectors = new HashMap<String, String[]>();
		
		Map<String, String[]> myAllSelectors = new HashMap<String, String[]>();
	
		
		 Xls_Reader currentTestCaseXLSFile;
		 //currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+ZTest_DriverThreadThread.currentTestSuite+".xlsx");
		 currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+currentTestSuite+".xlsx");



		 //=======>> Loop Through the Entire test selectors sheet for all rows
		for(int row=2;row<=currentTestCaseXLSFile.getRowCount(Constants.TEST_OBJECT_SELECTOR_SHEET);row++)
		{
			String myCss = currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "CSS", row);	//
			String myXpath = currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Xpath", row);	
			
			if (myCss.length() > 0)
			{
				String[] myValues = new String[2];
				myValues[0] = myCss; myValues[1] = "CSS";
				myAllSelectors.put(currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", row), myValues);
			}
			
			if (myXpath.length() > 0)
			{
				String[] myValues = new String[2];
				myValues[0] = myXpath; myValues[1] = "Xpath";
				myAllSelectors.put(currentTestCaseXLSFile.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", row), myValues);
			}
		}
		
			//if (myDesiredSelectors.containsKey("")) myDesiredSelectors.remove("");
			return myAllSelectors;
	}
	
	public Map<String, String> parseExcelInputCellData(String excelInputString)
	{
		
		Map<String, String> map = new HashMap<String, String>();
		//String test = "pet:cat::car:honda::location:Japan::food:sushi";
		String test = excelInputString;

		String[] test1 = test.split("\n");

		for (String s : test1) {
		    String[] t = s.split(":");
		    map.put(t[0], t[1]);
		}

		for (String s : map.keySet()) {
		    System.out.println(s + " is " + map.get(s));
		}
		

		return map;
	}
	
	//==== Random Input / [Random Input] Functions
	public String fInterpretExcelData(String Input)
	{
		String sOut = fInterpretData(Input);
		return sOut;
	}
	public String fInterpretData(String Input)
	{
		String sOut=Input;
		
		//String tmp = "SHAdmin [User 1] and then [Email 6] and nore";
		String tmp = Input;
		ArrayList<String> aRepl  = new ArrayList<String>();

		if (tmp.toString().indexOf("[") + 1 != 0)
		{
			String[] aRef = tmp.split("\\[");
			
			for (int i = 0; i <= aRef.length-1; i++)
			{
				if (aRef[i].indexOf("]") + 1 != 0)
				{
					aRepl.add(aRef[i].split("]", -1)[0]);
					//System.out.println(aRepl.get(0));
				}

			}

			for (int i = 0; i <= aRepl.size()-1; i++)
			{
				System.out.println(aRepl.get(i));
				String sInterpreted = fGetRandomTestData(aRepl.get(i));
				tmp = tmp.toString().replace("[" + aRepl.get(i) + "]", sInterpreted);
				sOut = tmp;
			}
		}
		else
		{
			sOut = fGetRandomTestData(Input);
		}
		
		return sOut;
	}
	public String fGetRandomTestData(String Input)
	{
		String returnString=Input;
		
		//====== Return A Random Email ================
			if(Input.matches(".*Email \\d"))
			{
				
				returnString=generateEmail(Input); //  Not Using this Any more Pain on ass
				
				// == We can Not Use Random Number for a email to create it can Make a Duplicate in DB so we need a DB Query when create email
				/*if(ZTest_DriverThreadThread.AllEmail.get(Input).equals(""))
				{
					//ZTest_DriverThreadThread.AllEmail.put(Input, Constants.emailAcctAlias + RandomNumber() + "@mailinator.com");
					Input=
					returnString = ZTest_DriverThreadThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				else
				{
					returnString = ZTest_DriverThreadThread.AllEmail.get(Input);          // Because data is already in Hasmap Return ii
				}*/
			}
		
		//====== Return A Random User ID ================
			if(Input.matches(".*User \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals(""))
				{
					Test_DriverThread.AllEmail.put(Input, "User" + RandomNumber()); // First Store The random Data in HasMap 
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				else
				{
					returnString =Test_DriverThread.AllEmail.get(Input);  // Because data is already in Hasmap Return it 
				}
			}

			
			
			//====== Get The Order Total From CheckOut Page and Store it in Has  ================		
			if(Input.matches(".*Order Total \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorXpathCssETC = null;
					try
					{
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("CheckOutOrderTotalLabel");
						WebElement ChkOutOrderTotalLAbel = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
						String OrderTotalAmount = ChkOutOrderTotalLAbel.getText().trim();

						Test_DriverThread.AllEmail.put(Input, OrderTotalAmount);
						returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
					}
					catch(Exception e)
					{
						
					}
				}
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				
			}
			
			
			
			//====== Select A Event From Suites "Event Scroll Box" That Appears in Different Part of the Suites APp  ================		

			if(Input.trim().matches(".*Event List \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorXpathCssETC = null;
					try 
					{
					  //First we have to Load All Available Event List Elements 
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("Scrollbox_List"); //Common Object Scroll Box List
						List<WebElement> Event_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC));
						System.out.println("NUMBER OF Event Found = "+Event_collection.size());
						//for(int i=0;i<Event_collection.size();i++)
						//{
							String listNumberToPick = stripNonDigits(Input);
							String EventName = driver.findElement(By.xpath(ObjectLocatorXpathCssETC+"["+listNumberToPick+"]/div[2]/span[1]")).getText();
							System.out.println("Events Details : " + EventName);
							//String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
							Test_DriverThread.AllEmail.put(Input, EventName);
							returnString = Test_DriverThread.AllEmail.get(Input);   // Return the Data From HasMap
						//}// End Looping For Events 
					} 
					catch (Exception e) 
					{
						//=== Error Problem Finding Event From Event List 
						e.printStackTrace();
					} 
				}
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
				}
				
			}

			
			//====== Select A Event From Suites "Client Event Tile" if not Found in Current Month Move to Next Month  ================		
			if(Input.trim().matches(".*Event \\d.*"))
			{
				if(Test_DriverThread.AllEmail.get(Input).equals("")) // If the Data HasMap Dictionary is Empty then go and get the Event Name 
				{
					String ObjectLocatorMonthFilterList = null;
					String ObjectLocatorXpathCssETC = null;
					try 
					{
						
					//First we have to Load All Available Month From Month Filter List 
						ObjectLocatorMonthFilterList = GetObjectLocatorXPathCssEtcFromRepo("EventsMonthsFilter_List");  //Get the Xpath Of Month Filter List here 
						ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo("ClientEventsList"); //Get the Xpath Of Event Filter List Here 

						List<WebElement> Month_collection = driver.findElements(By.xpath(ObjectLocatorMonthFilterList+"/li"));
						System.out.println("NUMBER OF Month Found = "+Month_collection.size());
		Monthloop:	for(int j=0;j<=Month_collection.size()-1;j++)
						{
							
							System.out.println("Looking In  Month : " + Month_collection.get(j).getText());
							this.highlightElementBorder(driver.findElement(By.xpath(ObjectLocatorMonthFilterList+"/li["+(j+1)+"]/span")), "pass");
							driver.findElement(By.xpath(ObjectLocatorMonthFilterList+"/li["+(j+1)+"]/span")).click(); //Clicking On Current Month
							hardWait();
							
						//======= Event To event  Looping
							List<WebElement> Event_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/client-event/div"));
							System.out.println("NUMBER OF Event Found = "+Event_collection.size());
							//--> Select The First AvailAble Event and Store It in a HasMap for This Entire Test case
							for(int i=0;i<=Event_collection.size()-1;i++)
							{
								System.out.println("Events Details : " + Event_collection.get(i).getText());
								//System.out.println("Events Details : " + Event_collection.get(i).findElement(By.xpath("//section[1]/h1")).getText());
								//System.out.println("Events Details : " + driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+i+"]/div/section[1]/h1")).getText());
								String allTextinEventTile = driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]")).getText();
							
								//&&&&&&&&&&&&&  Get a PRE-GAme Day Play Off Event &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*PreGamePlayOff.*"))
								{
									if(!allTextinEventTile.contains("GAME DAY PRICING") && allTextinEventTile.contains("KOSHER NOT AVAILABLE"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}
								
								
								//&&&&&&&&&&&&&  Get a PRE-GAme Day Regular &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*PreGame.*"))
								{
									if(!allTextinEventTile.contains("GAME DAY PRICING"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}


								//&&&&&&&&&&&&&  Get a PRE-GAme Day Regular &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
								if(Input.trim().matches(".*GameDay.*"))
								{
									if(allTextinEventTile.contains("GAME DAY PRICING"))
									{
										String StoreEventName =  driver.findElement(By.xpath(ObjectLocatorXpathCssETC+ "/client-event["+(i+1)+"]/div/section[1]/h1")).getText();
										Test_DriverThread.AllEmail.put(Input, StoreEventName);
										returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
										break Monthloop;
									}
								}
								
								
								
								
							}// End Looping For Events 
						}//End Looping In Month List 
					} 
					catch (Exception e) 
					{
						//=== Error Problem Finding Event From Event List 
						e.printStackTrace();
					} 
				}
				
				else
				{
					returnString = Test_DriverThread.AllEmail.get(Input);          // Return the Data From HasMap
					//break;
				}

			}
			
			
			
		return returnString;
	}
	/*
	 	* DataBase Depended Functions 
	 	* Get the maximum Test email ID from Suites DB and make a new one 	 
	 */
 	public String generateEmail(String eMailNo)
	{
		
		String returnString="";
		
		//***** Connect To DataBase Query All User 
		Connection conn3 = null;
		String dbURL3 = "";
        Properties parameters = new Properties();

        if(FBConstants.ENV.equals("SUIT_UAT")) //Looking For Default QA/UAT/PRD URL
		{
	         dbURL3 = "jdbc:postgresql://suitessitestage.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_uat";
		     parameters.put("user", "pgsqlprd");
		     parameters.put("password", "d8LaHYTk86SD");
		}
		else if(FBConstants.ENV.equals("SUIT_QA"))
		{
	        dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
	        parameters.put("user", "pgsqldev");
	        parameters.put("password", "pgsqldev#10");
		}
        
        try 
        {
			//==Connecting to Database 
	        	conn3 = DriverManager.getConnection(dbURL3, parameters);
			    if (conn3 != null) 
			    {
			        System.out.println("\n Connected to database #" + dbURL3 + parameters);
			    }
			    
			//==Count Query IF It return Zero we will Return from This Function 
			    Statement Countstmt = conn3.createStatement();
				ResultSet Countrs=Countstmt.executeQuery("select Count(email) from users where email LIKE '" + Constants.emailAcctAlias +"%@mailinator.com'");
				int TotalRecord = 0 ;	
				while (Countrs.next())
				{
					 TotalRecord =  Integer.parseInt(Countrs.getString(1));
				}
				
				if(TotalRecord==0)
				{
					Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias+"1@mailinator.com");
					returnString = Constants.emailAcctAlias+"1@mailinator.com";
					return returnString; // ****** This Entire Function will return here ************ 
				}
				
			//==Select Query The Database User Table 
			    String Input = Constants.emailAcctAlias +"%@mailinator.com";
			    Statement stmt = conn3.createStatement();
				ResultSet rs=stmt.executeQuery("select email from users where email LIKE'"+Input.trim()+"'");
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				while (rs.next())
				{
					String email = rs.getString(1);	
	                System. out.println("Email Found in dataBase : " + email);
	                System. out.println("Only NUmber Part From Email : " + stripNonDigits(email));
	                arrayList.add(Integer.parseInt(stripNonDigits(email)) );
				}
				
				System.out.println("Max Number From All Email " + Collections.max(arrayList));
				int MaxNumber = Collections.max(arrayList)+1;
				if(Test_DriverThread.AllEmail.get(eMailNo).equals(""))
				{
					Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias + MaxNumber + "@mailinator.com");
					System.out.println("New Email Generated For USe : " + Test_DriverThread.AllEmail.get(eMailNo));
					returnString =Constants.emailAcctAlias + MaxNumber + "@mailinator.com";
				}
				else//==> If the HasMap(Key) Already Has a Email Return That one  
				{
					returnString =Test_DriverThread.AllEmail.get(eMailNo);
				}
			//== closing DB Connection		
				conn3.close();		
		} 
        catch (SQLException ex) 
        {
        	//***** Back-Up Code IF DB Connection Fail A Random NUmber Email Will Generated - No Gurantee Work
        	if(Test_DriverThread.AllEmail.get(eMailNo).equals(""))
			{
				Test_DriverThread.AllEmail.put(eMailNo, Constants.emailAcctAlias + RandomNumber() + "@mailinator.com");
				returnString = Test_DriverThread.AllEmail.get(eMailNo);          // Return the Data From HasMap
			}
			else
			{
				returnString = Test_DriverThread.AllEmail.get(eMailNo);          // Because data is already in Hasmap Return ii
			}
        }
        /*finally 
        {
            try 
            {
                if (conn3 != null && !conn3.isClosed()) 
                {
                    conn3.close();
                }
            } 
            catch (SQLException ex) 
            {
                //ex.printStackTrace();
            }
        } */
        
        return returnString;		
	}
	public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */)
	{
		final StringBuilder sb = new StringBuilder(
            input.length() /* also inspired by seh's comment */);
    for(int i = 0; i < input.length(); i++)
    {
        final char c = input.charAt(i);
        if(c > 47 && c < 58){
            sb.append(c);
        }
    }
    return sb.toString();
}
	public StringBuilder RandomNumber()
	{
		 //generates random number
	    int min = 0;
	    int max = 9;
	    StringBuilder randoms = new StringBuilder();
	    Random random = new Random();
	    int num1 = random.nextInt(max-min+1)+min;
	    int num2 = random.nextInt(max-min+1)+min;
	    int num3 = random.nextInt(max-min+1)+min;
	    randoms.append(num1);
	    randoms.append(num2);
	    randoms.append(num3);
	    
	    System.out.println("Fname"+ "Lname" + randoms + "@mailinator.com");
	    
	    return randoms;
	}
	
	//==== Highlight element 
	public void highlightElement(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(3000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }
	public void highlightElementBackground(WebElement element, String flag)
	{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        if(flag.equalsIgnoreCase("pass"))
        {
            //js.executeScript("arguments[0].style.border='1px groove green'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"yellow"+"'",  element);
        }
        else 
        {
            //js.executeScript("arguments[0].style.border='4px solid red'", element);
	        js.executeScript("arguments[0].style.backgroundColor = '"+"red"+"'",  element);
        }

        try 
        {
			Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
        }
                
        //===> Take a Screen Shot and Reset the Element 
        	//takeScreenShot();
        //js.executeScript("arguments[0].style.border=''", element);
        //js.executeScript("arguments[0].style.backgroundColor = '"+""+"'",  element);
	}
	public void highlightElementBorder(WebElement element, String flag) 
	{
           JavascriptExecutor js = (JavascriptExecutor) driver;
           
           if(flag.equalsIgnoreCase("pass"))
           {
               js.executeScript("arguments[0].style.border='2px groove green'", element);
           }
           else 
           {
               js.executeScript("arguments[0].style.border='2px solid red'", element);

           }

           try 
           {
			Thread.sleep(1000);
           } 
           catch (InterruptedException e) 
           {
			e.printStackTrace();
           }
                   
           // Take a Screen Shot and Reset the Element 
           //takeScreenShot();
           //js.executeScript("arguments[0].style.border=''", element);
    }

	//=== Driver Wait & Special Screen Shot Function
	public void waitForPageToLoad() throws InterruptedException 
	{
		//wait(2);
		this.hardWaitFor(500);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String state = (String)js.executeScript("return document.readyState");
		while(!state.equals("complete"))
		{
			//wait(2);
			this.hardWaitFor(500);
			state = (String)js.executeScript("return document.readyState");
		}
	}	
	public void hardWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void hardWaitFor(int Second)
	{
		try {
			Thread.sleep(Second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean waitUntilClickable(String ObjectName)
	{
		//************
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			
    		PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    		PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	
	public boolean waitUntilClickable(WebElement ObjectName)
	{
		//************
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//*********
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ObjectName)));
			 wait.until(ExpectedConditions.elementToBeClickable(ObjectName));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectName)));
			return true;
		}
		catch(Exception e)
		{
			
    		PassFailFailDescription = thisMethodName + "-> For  :  " + ObjectName + " ->Failed" + "<br>";
    		PassFailFailDescription = e.getMessage() + "<br>";
		}

		return false;
	}
	public void SwitchFrameWait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void takeScreenShot()
	{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ScenarioReport.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}
	public  void takeScreenShotForScenario()
	{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(filePath));
		ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture("screenshots/"+screenshotFile));
	}
/*
	//Global List Variable That will store all the ScreenShot file name for Current Scenario is Running 
	public static ArrayList<String> PassFailScreenFileName = new ArrayList<String>();
	public  void takePassFailScreenShots(String currentScenario)
	{
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
		
		PassFailScreenFileName.add(filePath);
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(filePath));
		//ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture("screenshots/"+screenshotFile));
	}*/
	

	
 	//******In Progress -- > Below Function Used For Debug Purpose Works Browser Mob Proxy Help:  http://www.swtestacademy.com/webdriver-browsermob-proxy/ *** 
	public boolean CheckdbConnection()
	{
	Connection conn3 = null;
	String dbURL3 = "";
    Properties parameters = new Properties();

    if(FBConstants.ENV.equals("SUIT_UAT")) //Looking For Default QA/UAT/PRD URL
	{
         dbURL3 = "jdbc:postgresql://suitessitestage.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_uat";
	     parameters.put("user", "pgsqlprd");
	     parameters.put("password", "d8LaHYTk86SD");
	}
	else if(FBConstants.ENV.equals("SUIT_QA"))
	{
        dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
        parameters.put("user", "pgsqldev");
        parameters.put("password", "pgsqldev#10");
	}
    
    
    try 
    {
		//==Connecting to Database 
        	conn3 = DriverManager.getConnection(dbURL3, parameters);
		    if (conn3 != null) 
		    {
		        System.out.println("Connected to database #" + dbURL3 + parameters);
		    }
		
		//==Query The Database User Table And get the REset Token 
		    /*Statement stmt = conn3.createStatement();
			ResultSet rs=stmt.executeQuery("select reset_password_token from users where email ='"+Input.trim()+"'");
			while (rs.next())
			{
				resetToken = rs.getString(1);	
                System. out.println("Reset Token for this  User : " + resetToken);		
			}*/
			//String input = "some input string";
			//int hashCode = resetToken.hashCode();
			//System.out.println("input hash code = " + hashCode);
		
		//==Update Query     
			Statement stmt = conn3.createStatement();
			String mapToken = "7a0cafe277fc84b79cf4aeacb687d53fa57e72c0b86797b0d0619030b37fd20e";
			//int rs=stmt.executeUpdate("update users SET reset_password_token = null where email Like '"+Constants.emailAcctAlias+"%@mailinator.com'");
			
		    	//int rs2=stmt.executeUpdate("update users SET reset_password_token='"+mapToken+"' where email ='"+Input.trim()+"'");
		    }
		    catch(Exception e)
		    {
		    	System.out.println(e.getMessage());
		    	
		    }

    return true;
	}
	public static BrowserMobProxyServer server;
	public boolean driverWithProxy()
	{
		
		server = new BrowserMobProxyServer();
	server.start();
	int port = server.getPort();
	Proxy proxy = ClientUtil.createSeleniumProxy(server);
//	Proxy proxy = new Proxy();
	DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
	seleniumCapabilities.setCapability(CapabilityType.PROXY, proxy);
	driver = new FirefoxDriver(seleniumCapabilities);
	System.out.println("Port started:" + port);
		return true;
	}

	public void reportPassFail(ReportTest reporttest, String failedInMethod, String step, String Description)
	{
		if(reporttest.PASS.toString().equals(report.PASS))
		{
			
		}
		else
		{
	    	PassFailFailDescription = failedInMethod +  "  FAILED"  + "<br>";
			takePassFailScreenShots(step.replaceAll("\\s",""));
		}
	}
	
	
//**************END OF CLass*******************************************
}
