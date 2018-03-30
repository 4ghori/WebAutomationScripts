package com.msg.qa.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;


public class MSGOnlineDigitalSeleniumActions 
{
	
		public static final String REPORTS_PATH = System.getProperty("user.dir")+"//reports//";

		public String currentTestSuite;  // Name Of the Excel File which Is Running 
	
	//==>> Driver That Perform All Actions on UI - This will Be Loaded In Constructor with driver coming From Test 
		public WebDriver driver;
		public ExtentTest ExtendTestReport;
		public ExtentTest ScenarioReport;
		//public ReportTest report = null;
		public ExtentTest test;

	//==>> Reporting Variable
		public static String PassFailFailDescription;
		
	public MSGOnlineDigitalSeleniumActions(WebDriver driver, ExtentTest test,ExtentTest ScenarioReport)
	{
		//super(driver,test);
		this.driver=driver;
		this.ExtendTestReport=test;
		this.ScenarioReport=ScenarioReport;
	}
		
	public void NavigateToPage(String Url)
	{

		driver.get(Url);
		
	}
	
	public boolean ValidateElementExist(WebElement el)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
		WebElement TargetObject=el;
		boolean s=false;
		
		try
		{
			if(!waitUntilClickable(TargetObject)) return false; // wait a Little bit for Object to Load
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			s = TargetObject.isDisplayed();
		}
		catch(Exception e)
		{
			s=false;
		}

		if(s==false)
		{
			MSGOnlineDigitalSeleniumActions.PassFailFailDescription = thisMethodName + " -> Element Not Found " + "<br>";
			Rtype= false;
		}
		else
		{
			MSGOnlineDigitalSeleniumActions.PassFailFailDescription = thisMethodName + " -> Element Found " + "<br>";
			highlightElement(TargetObject,"pass");
			Rtype= true;
		}
	    return Rtype;		
	}

	public boolean EnterText(WebElement el, String Input)
	{
		
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
	    		boolean Rtype = false;
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			TargetObject.clear();
	    			hardWaitFor(1000);
	    			TargetObject.sendKeys(Input);;
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Passed" + "<br>";
	    		}
	    		catch(Exception e){
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= false;
	
	    			PassFailFailDescription = thisMethodName + "->" + el + "->"+ Input + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
	   }
	
	public boolean ClickButton(WebElement el)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		
		
		boolean Rtype = false;
    	
    	//****** Report: This Step That is Executing ************************************   
    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
    	//********************************************************************************
    	
    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
    		try{
    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
    			WebElement TargetObject = el;
    			TargetObject.click();
    			hardWait();
    			Rtype= true;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
    		}
    		catch(Exception e){
    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    		Rtype= false;
    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}
		//}
		//else
		//{
			//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
		//}
		return Rtype;
    }
	
	public boolean ClickLink(WebElement el)
    {
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
	    	//****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName );
	    	//********************************************************************************
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			if(!waitUntilClickable(el)) return false; // wait a Little bit for Object to Load
	    			WebElement TargetObject = el;
	    			TargetObject.click();
	    			hardWait();
	    			Rtype= true;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Passed" + "<br>";
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "->" + ObjectName + "-> Passed");
	    		}
	    		catch(Exception e){
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
		    		Rtype= false;
	    			PassFailFailDescription = thisMethodName + "->" + el + "-> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; //If Failed (PAge is not Ready or Object is Not Ready) Pass/Report is inside the fEnsurePageIsReady, fEnsureObjectIsReady Function.
			//}
			return Rtype;
    }
	
	public boolean ValidateTableCellText(String sAllRowsXpath, String sAllColumnXPath,String sExpectedText)
	{
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
		
		boolean Rtype = false;
    		try
    		{	    			
    			//String allRowXpath = "//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']";
    			//String allColumnXpath = "//div[@class='fixedDataTableCellLayout_main public_fixedDataTableCell_main']";

    			String allRowXpath = sAllRowsXpath;
    			String allColumnXpath = sAllColumnXPath;

    	        List<WebElement> listAllRows = driver.findElements(By.xpath(allRowXpath));
    	        int iTotalRows = listAllRows.size();
    	        int iStartingRow = 1;

    	        TableRowLoop: for(int i=iStartingRow;i<iTotalRows;i++)
    	        {
    		  		//System.out.println(listAllRows.get(i).getText());
    		  		//highlightElement(listAllRows.get(i),"pass");
    		  		
	    	        List<WebElement> listCurrentRowColumns = driver.findElements(By.xpath(allRowXpath+"["+i+"]"+allColumnXpath));
	    	        int iTotalColumn = listCurrentRowColumns.size();

    		  		String EachCellData = "";
	    	        for(int x=0;x<=iTotalColumn;x++)
	    	        {
	    		  		//System.out.println(listCurrentRowColumns.get(x).getText());
	    		  		//highlightElement(listCurrentRowColumns.get(x),"pass");
	    		  		
	    		  		EachCellData += listCurrentRowColumns.get(x).getText();
    	                if(StringUtils.containsIgnoreCase(EachCellData,sExpectedText))
    	                {
    	                		Rtype= true;
    	                		highlightElementBackground(listCurrentRowColumns.get(x),"pass");
    	                		highlightElement(listCurrentRowColumns.get(x),"pass");
    	                		break TableRowLoop;
    	                }
	    	        }
	    	        System.out.println("");
    	        }
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    		}
    	
	    	return Rtype;
	 }
	
	public boolean ClickTableCellText(String sAllRowsXpath, String sAllColumnXPath,String sExpectedText)
	{
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		
		boolean Rtype = false;
    		try
    		{	    			
    			String allRowXpath = sAllRowsXpath;
    			String allColumnXpath = sAllColumnXPath;

    	        List<WebElement> listAllRows = driver.findElements(By.xpath(allRowXpath));
    	        int iTotalRows = listAllRows.size();
    	        int iStartingRow = 1;

    	        TableRowLoop: for(int i=iStartingRow;i<iTotalRows;i++)
    	        {
    		  		//System.out.println(listAllRows.get(i).getText());
    		  		//highlightElement(listAllRows.get(i),"pass");
    		  		
	    	        List<WebElement> listCurrentRowColumns = driver.findElements(By.xpath(allRowXpath+"["+i+"]"+allColumnXpath));
	    	        int iTotalColumn = listCurrentRowColumns.size();

    		  		String EachCellData = "";
	    	        for(int x=0;x<=iTotalColumn;x++)
	    	        {
	    		  		//System.out.println(listCurrentRowColumns.get(x).getText());
	    		  		highlightElement(listCurrentRowColumns.get(x),"pass");
	    		  		
	    		  		EachCellData += listCurrentRowColumns.get(x).getText();
    	                if(StringUtils.containsIgnoreCase(EachCellData,sExpectedText))
    	                {
    	                		Rtype= true;
    	                		highlightElementBackground(listCurrentRowColumns.get(x),"pass");
    	                		highlightElement(listCurrentRowColumns.get(x),"pass");
    	                		listCurrentRowColumns.get(x).click();
    	                		break TableRowLoop;
    	                }
	    	        }
	    	        System.out.println("");
    	        }
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    		}
    	
	    	return Rtype;
	 }
	
	public boolean SelectAnItemFrom_ListBox(WebElement listElement, String listNameOrNumbertoSelect)
	{
		//************ All SELENIUM Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************
	  
		    	boolean Rtype = false;
		    boolean matChFoundinTable=false;
		    String errorFlag = "";
				
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	    		try
	    		{
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        //printconsole.printdebuglog("Total No of Item Found in ListBox = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					//printconsole.printdebuglog("ListBox Item ("+i+") :"+ list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						list_collection.get(i).click();
						hardWait();
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			System.out.println("Failed : " + PassFailFailDescription);
	    			Rtype= false;
	    		}
		    	
		    	if(!matChFoundinTable)
		    	{
		        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
					PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
		    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			return Rtype;
	}

	public boolean Click_A_WebElement_FromListBox(WebElement listElement, String ElementType, String listNameOrNumbertoSelect)
	{
	
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		
	    	boolean Rtype = false;
	    boolean matChFoundinTable=false;
	    String errorFlag = "";
	
	    //****** Report: This Step That is Executing ************************************   
	    	//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "-> From Table  " + ObjectName +"->" + Input );
	    	//********************************************************************************
	    	
			//Input = fInterpretExcelData(Input);
	
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
	    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	    	
	    	//if (fEnsurePageIsReady(Page) && fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
		//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			//WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
	    			WebElement liste_element = listElement;
	    	        List<WebElement> list_collection = liste_element.findElements(By.xpath("li"));
	    	        //System.out.println("NUMBER OF Item Found in List  = "+list_collection.size());
	
	       listLoop:for(int i=0;i<list_collection.size();i++)
	    	        {
					//System.out.println(list_collection.get(i).getText());
					if(list_collection.get(i).getText().trim().equals(listNameOrNumbertoSelect.trim()) || list_collection.get(i).getText().trim().contains(listNameOrNumbertoSelect.trim()))
					{
						//==>> There Could Be Different type of webelement inside a ListBox Like ( EditBox/ Button/ Link /span ETC)
						//==>> We Have to check which Item need to click and then Click it 
						if(list_collection.get(i).findElements(By.xpath(ElementType)).size() > 0) //Month FiletList in Suite Client Section
						{
							list_collection.get(i).findElement(By.xpath(ElementType)).click();
						}
						hardWait();
						//Test_DriverThread.ScenarioReport.log(LogStatus.PASS, thisMethodName + "-> From Listbox  " + ObjectName +"->" + Input + "->Clicked Passed" );
						PassFailFailDescription = thisMethodName + "->" + "" + "-> Passed" + "<br>";
						Rtype= true;
						matChFoundinTable=true;
						break listLoop;
					}
					else
	                {
						matChFoundinTable= false;
						errorFlag = listNameOrNumbertoSelect + "  Not Found In List ";
	                }
	    	        } 		
	    		}
	    		catch(Exception e)
	    		{
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "->Clicked Failed" );
	            	//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage() );
	    			PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    			Rtype= false;
	    		}
			//}
			//else
			//{
				//==If Page OR Object Is Not Ready - We Don't Need to Report Here Reporting Already Performed [fEnsurePageRady & fEnsureObject Ready Function]
				//Rtype= false;
			//}
	    	
	    	if(!matChFoundinTable)
	    	{
	        	//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "-> From List  " + ObjectName +"->" + Input + "-> Clicked Failed" );
				PassFailFailDescription = thisMethodName + "->" + "" +" Item->" + listNameOrNumbertoSelect + "->Clicked Failed" + "<br>";
	    	}
	    	
	    	//==== Re-set Driver Time -  because it has a Driver.FindElemens Function which takes time 
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	
			return Rtype;
	    	
	    
	}

	public boolean SelectDropDown(String ObjectXpath, String Input)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	boolean Rtype = false;
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			select.selectByVisibleText(Input);
    			Rtype= true;
    		}
    		catch(Exception e)
    		{
    			Rtype= false;
    			PassFailFailDescription = e.getMessage() + "<br>";
    		}   		
    		return Rtype;
    }
	
	public String GetDropDownFirstSelectedOption(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

		
	    	
	    	String FirstSelectedOption = null;
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			FirstSelectedOption = select.getFirstSelectedOption().getText();
    			return FirstSelectedOption;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return "";
    		}   		
    }

	public List<String> GetAllDropDownOptionAsList(String ObjectXpath)
    {
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
	//************ ************************** *****************************************************

	    	
		List<String> OptionList = new ArrayList<String>();
	    	
    		try
    		{
    			Select select = new Select(driver.findElement(By.xpath(ObjectXpath)));
    			List <WebElement> allOptions= select.getOptions();
    			//System.out.println("\nTotal NUmber Of Option Found Inside the Dropdown Box : " + allOptions.size() );
    			for(int i=0;i<allOptions.size();i++)
    			{
        			//System.out.println("Category Box Oprion " + i + " is : " + allOptions.get(i).getText() );
    				OptionList.add(allOptions.get(i).getText());
    			}
    			return OptionList;
    		}
    		catch(Exception e)
    		{
    			PassFailFailDescription = e.getMessage() + "<br>";
    			System.out.println("Error in Selenium Method " + thisMethodName +" : "+ PassFailFailDescription);
    			return null;
    		}   		
    }
	
	public boolean scrollToElement(WebElement el)
	{
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

		boolean rtype = false;

		WebElement TargetObject=el;
		try
		{
			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
			//TargetObject = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", TargetObject);
			hardWaitFor(1000);
			rtype=true;
		}
		catch(Exception e) 
		{
			PassFailFailDescription = thisMethodName + " Scrolling To Element " + TargetObject.toString() + "  -Failed" + "<br>";
			PassFailFailDescription = e.getMessage() + "<br>";
			System.out.println(PassFailFailDescription);
			rtype=false;
			//e.printStackTrace();
		}
		return rtype;
	}
	
	public String GetAttributeText(WebElement el, String AttributeName)
	{
		
		
		//************ All SELENIUM Function Header ***************************************************
		String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
	           thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		//printconsole.printutilitylog("Selenium Action : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *****************************************************

	    		String Rtype = "";
	    	//****** Report: This Step That is Executing ************************************   
	    		//Test_DriverThread.ScenarioReport.log(LogStatus.INFO, ">>>"+ Step + " : " + thisMethodName + "->" + ObjectName + "->" + Input);
	    	//********************************************************************************
	    	
	    	//==>> We need to check if the Input is ( Email 1 / User 1 / RandomName / Random Number) ETC
				//Input = fInterpretInput(Input);
				//Input = fInterpretExcelData(Input);
				
	    	//if (fEnsureObjectIsReady(ObjectName)) //Check If the Page & Object is ready Else Return False - 
			//{
	    		try
	    		{
	    			//String ObjectLocatorXpathCssETC = GetObjectLocatorXPathCssEtcFromRepo(ObjectName);
	    			WebElement TargetObject = el;
	    			//TargetObject.clear();
	    			hardWaitFor(1000);
	    			//TargetObject.sendKeys(Input);
	    			Rtype= TargetObject.getAttribute(AttributeName);
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Passed" + "<br>";
	    		}
	    		catch(Exception e)
	    		{
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.FAIL, thisMethodName + "->" + ObjectName + "->"+ Input + "-> Failed");
	    			//Test_DriverThread.ScenarioReport.log(LogStatus.ERROR, e.getMessage());
	    			Rtype= "";
	    			PassFailFailDescription = thisMethodName + " From -> " + el + " -> Failed" + "<br>";
	    			PassFailFailDescription = e.getMessage() + "<br>";
	    		}
			//}
			//else
			//{
				//Rtype= false; // Failed Report in Pageready Object Ready Function
			//}
			return Rtype;
			
	   }
	
	
	
    
//###################  Helper Functions  ##################################################################################################
     
	
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

	//=== Driver Wait & Special Screen-Shot Function
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
			WebDriverWait wait = new WebDriverWait(driver,1);
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
		String filePath=REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ScenarioReport.log(LogStatus.INFO,ExtendTestReport.addScreenCapture(filePath));
	}
	public  void takeScreenShotForScenario()
	{
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=REPORTS_PATH+"screenshots//"+screenshotFile;
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

	//Global List Variable That will store all the ScreenShot file name for Current Scenario is Running 
	public static ArrayList<String> PassFailScreenFileName = new ArrayList<String>();
	public  void takePassFailScreenShots(String currentScenario)
	{
		Date d=new Date();
		String screenshotFile=currentScenario+"-"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=REPORTS_PATH+"screenshots//"+screenshotFile;
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
	}
	
} // XXXXXXXXX END Of CLass XXXXXXXx 
