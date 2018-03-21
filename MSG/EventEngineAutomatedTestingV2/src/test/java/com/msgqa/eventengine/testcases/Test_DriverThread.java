package com.msgqa.eventengine.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.ParseException;
//import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.msg.suites.pom.util.DataUtil;
import com.msg.suites.pom.util.FBConstants;
import com.msg.suites.pom.util.RockettesSauceJobAccessController;
import com.msg.suites.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import MSGOnlineDigital.JIRA.MSGJIRAupdate;


//import com.qtpselenium.xls.read.Xls_Reader;

public class Test_DriverThread extends Test_DriverBase implements Runnable 
{

	//public static Logger APP_LOGS;
	//suite.xlsx
	public Xls_Reader suiteXLS;
	public int currentSuiteID;
	
		public String currentTestSuite;  // Name Of the Excel File which Is Running 
	
	// current test suite
	public Xls_Reader currentTestSuiteXLS;
	
	public  String TestEnvUrl;

	//public int currentTestCaseID;
	public  String currentTestCaseName;
	public  String currentTestCaseDescription;
	public  String CurrentTestBrowser;
	public  String CurrentTestPlatform;
	
	public int currentTestStepID;
	public String currentKeyword;
	public String ExecuteNext;

	public int currentTestDataSetID;
	public Method method[];
	//public Keywords keywords;
	public String data;
	public String object;
	public String keyword_execution_result;
	public ArrayList<String> resultSet;
	public Properties CONFIG=null;
	public Properties OR=null;

	public  String testSteps;
	
	
	//***** This WIll Hold/Store  all Common Email for a Test  
	public static Map<String, String> AllEmail = new HashMap<String, String>();

	//**** JIRA Update Class Reference 
		MSGJIRAupdate myMSGJiraUpdater;
		public String myTestCaseIssueId;
		public static String myTestCycleId;
	
		
	private Thread t;  // This Is Global Private Variable
		
	//*******  Default Constructor Functions ****************	
	public Test_DriverThread() throws IOException
	{
		//keywords = new Keywords(driver,test);
		//PageFactory.initElements(driver, keywords);

		//method = keywords.getClass().getMethods();
		
		/*// Intitilaize properties
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		      																	
		CONFIG = new Properties();
		CONFIG.load(ip);
		
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//or.properties");
																
		OR = new Properties();
		OR.load(ip);*/
		
	}

	
	//*******  Multiple  Threading Start Run Functions ****************	
	public void start () 
	 {
	      //System.out.println("Starting " +  "Thread...." );
	      //System.out.println("Befor Thread Started" + currentTestCaseName);
	      if (t == null) 
	      {
	         t = new Thread (this, "");
	         t.setName(currentTestCaseName);
	         t.start ();
	         //monitorThread(t);
			 //System.out.println("After  Thread Start : " + currentTestCaseName);
	      }
	  }
	synchronized public void run() 
   {
	   try 
	   {
		   
		   //System.out.println("Before  Thread Run : " + currentTestCaseName);
		   startTest();
	   } 
	   catch (Exception e) 
	   {
		   e.printStackTrace();
	   } 
   	}		
	
	

	
	//*******  Functions That Executes Steps One By One   ****************	
	public void startTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, URISyntaxException, java.text.ParseException, IOException
	{
		//LoadTestDataDictionary(); // This Need to Take on Test case Reader Class 
		test = extent.startTest(currentTestCaseName + " - "+currentTestCaseDescription);
		//System.out.println("\tExecuting Test \t\t\t  " + currentTestCaseName + "....... ");
		//APP_LOGS.debug("\tExecuting the test case ->  \t\t: " + currentTestCaseName);

		//************** JIRA Create The Test Case  *********************************************
			//myTestCaseIssueId = myMSGJiraUpdater.createTestCaseInJira(currentTestCaseName + " - "+currentTestCaseDescription);
		//***********************************************************************************	

		// == Running With Multiple set of Data - If There is a TestCase Name Sheet Available in Excel with Mobile / Android Bla bla 	
			 if(currentTestSuiteXLS.isSheetExist(currentTestCaseName)) 
			 {
				 // RUN as many times as number of test data sets with runmode Y
				 for(currentTestDataSetID=2;currentTestDataSetID<=currentTestSuiteXLS.getRowCount(currentTestCaseName);currentTestDataSetID++)	
				  {
					  resultSet= new ArrayList<String>();
					  //APP_LOGS.debug("Iteration number "+(currentTestDataSetID-1));
					  // checking the runmode for the current data set
					  if(currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.RUNMODE, currentTestDataSetID).equals(Constants.RUNMODE_YES))
					  {
						  //=> iterating through all keywords - With multiple sets of data
						 //executeKeywords(); 
						  
						  fExecuteTestSteps();
					  }
					  //createXLSReport();
				  }
			 }
			 else
			 {
				  //=> iterating through all keywords - 
				 //executeKeywords();// no data with The test
				 
				 fExecuteTestSteps();
				 
				 //resultSet= new ArrayList<String>(); //PATCH - Introduced later
				 //createXLSReport();
			 }
			 
			 
			//System.out.println("\tExecution \t\t\t  "+ currentTestCaseName +"Complete............  ");
			//System.out.println("");
	}
	
	public void fExecuteTestSteps() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, URISyntaxException, java.text.ParseException, IOException
	{
		init(CurrentTestBrowser, currentTestCaseName + " - "+currentTestCaseDescription);
		
		//==> This Below Initialized Class Contains Login to Run All Steps 
		Test_Keywords_Runner TestKeyRunner = null;
		
		boolean passOrFail = false; // Need for Find all Test case Passed/Fail Flag
		boolean stopTest = false;

		boolean ScenarioReportRunning = false;
		String RunningScenario="" ;


		
		//=======>> Loop Through the Entire Test Steps Sheet All Rows 
		StepRunnerloop:for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);currentTestStepID++)
		{
			//===> String Storing Each TC ID/Name One After Another          && TestStepSheet            &&TCIDField     Current_Row 
				String CurrentRowTestCase = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID, currentTestStepID);
				
			//===> Compare TC Need to Execute(currentTestCaseName) with (CurrentRowTestCase) if Match Found we will Start Executing The Steps of that TC  	
			if(currentTestCaseName.equals(CurrentRowTestCase.trim()))
			{

				String Step = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Description", currentTestStepID);
				String Description = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Description", currentTestStepID);	
				String Page = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Page", currentTestStepID);	

				currentKeyword=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Action", currentTestStepID);

				String Action = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Action", currentTestStepID);	
				String ObjectName = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Object", currentTestStepID);	
				String Input = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Input", currentTestStepID);	
				
				ExecuteNext=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "ExecuteNext", currentTestStepID);

				
				//=================>>  Scenario by Scenario  Runner ====================
					String CurrentScnario = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Scenario", currentTestStepID);
					String NextScnario = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Scenario", currentTestStepID+1);
					//boolean ScenarioReportRunning = false;
					if(!CurrentScnario.equals(""))
					{
						RunningScenario = CurrentScnario;
						ScenarioReportRunning = true;
						//ScenarioReport = extent.startTest(CurrentScnario);
						ScenarioReport = extent.startTest(CurrentScnario);

						TestKeyRunner = new Test_Keywords_Runner(driver,test,ScenarioReport);
						TestKeyRunner.currentTestSuite=this.currentTestSuite; //This need because the KeyWord Runner File Need the Current Excel File 

						//*********** JIRA Create The Test Steps  *********************************************
							//myMSGJiraUpdater.createTestStepInJira(myTestCaseIssueId, currentTestCaseName + " - "+currentTestCaseDescription,CurrentScnario, TestKeyRunner.fInterpretInput(Input) , "");
						//***********************************************************************************
					}

				//===>> Executing  All Test Steps 
				switch (currentKeyword)  
				{
					case "EE_AdminLogin":
						
						if ((passOrFail=TestKeyRunner.EE_AdminLogin(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;	
						
					case "NavigateTo_EventPage":
						
						if ((passOrFail=TestKeyRunner.NavigateTo_EventPage(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;
						
						
					case "EventPage_Validate_Element_Exist":
						
						if ((passOrFail=TestKeyRunner.EventPage_Validate_Element_Exist(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;	
						
					case "EventPage_SearchEventByEventName":
						
						if ((passOrFail=TestKeyRunner.EventPage_SearchEventByEventName(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;
						
					case "EventPage_SearchAndClickFirstEvent":
						
						if ((passOrFail=TestKeyRunner.EventPage_SearchAndClickFirstEvent(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;

					case "EventEditPage_PublishEvent":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_PublishEvent(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;	
						
						
					case "EventEditPage_SearchAvailableArtistAndAdd":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_SearchAvailableArtistAndAdd(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;	
						
					case "EventEditPage_MarkAsHeadlinerArtist":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_MarkAsHeadlinerArtist(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;	
						
					case "EventEditPage_AddArrivingAtDescription":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_AddArrivingAtDescription(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;		
	
					case "EventEditPage_AddRemoveCategory":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_AddRemoveCategory(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;
						
					case "EventEditPage_AddRemoveSponsor":
						
						if ((passOrFail=TestKeyRunner.EventEditPage_AddRemoveSponsor(Step,Page,ObjectName,Input)) == false && ExecuteNext.equals("N"))
						{
							stopTest=true;
							break;
						}
						break;		


					default:
						
						passOrFail = false;
						Test_Keywords_Runner.PassFailFailDescription="This Action/Keywords "+ currentKeyword+" is Not Found in Script Check" + "<br>";
						stopTest=true;
						break;
				}

				
				if(!RunningScenario.equals(NextScnario))
				{
					if(passOrFail==true)  // If the Entire Scenario Passes 
					{
						//*********** JIRA Update Step Status Pass   *********************************************
							//myMSGJiraUpdater.updateStatusForSingleTestStepsInJira(currentTestCaseName + " - "+currentTestCaseDescription,CurrentScnario, "PASS");
						//***********************************************************************************
						
						if(!ObjectName.equals("")) ObjectName = "-><b>" +ObjectName +"</b>";
						reportTestSteps("PASS",currentKeyword + ObjectName," ", false);
						
						//ScenarioReport.log(LogStatus.PASS,  Description + "-> Passed");
						//ScenarioReport.log(LogStatus.INFO,  ZSuite_Test_Keywords_Runner.PassFailFailDescription);
						//takeScreenShotForScenario();
					}
					else // If the entire Scenario Failed
					{
						//*********** JIRA Update Step Status FAIL   *********************************************
							//myMSGJiraUpdater.updateStatusForSingleTestStepsInJira(currentTestCaseName + " - "+currentTestCaseDescription,CurrentScnario, "FAIL");
						//***********************************************************************************
							
						//ScenarioReport.log(LogStatus.FAIL,  Description + "-> FAILED :-(");
						//ScenarioReport.log(LogStatus.INFO,  Test_Keywords_Runner.PassFailFailDescription);
						//attachPassFailScreenShotForScenario();
					}
				}
				
				if(ScenarioReportRunning==true)
				{
					test.appendChild(ScenarioReport);
					ScenarioReportRunning = false;
				}

				if(stopTest==true)
				{
					break StepRunnerloop;
				}
				
			}//--End Of IF all Steps Run For "Y" Flagged Test Case 
			

		}//---End OF Step Runner Loop 

		//==>> Closing Report After Each Keyword/Action
		//test.appendChild(ScenarioReport);
		extent.endTest(test);
		extent.flush();
		
		if(FBConstants.GRID_RUN)
		{
			
			System.out.println("\nTest End - teardown - Logging Post Test Activity");	
			System.out.println("Session ID Sending to SauceLab  : "+ super.mySessionID);
			System.out.println("Check Test Pass Or Fail\t\t: "+ passOrFail);
			super.sendPAssFailToSauce(super.mySessionID,passOrFail,super.USERNAME,super.ACCESS_KEY);
		}
		else
		{
			System.out.println("\nTest End - teardown - Logging Post Test Activity");	
		}



		//===>> Report Test Pass Fail in JIRAAA 
		if(passOrFail==true)
		{
			//*********** JIRA Update Test case  Status PAss   *********************************************
				//myMSGJiraUpdater.updateStatusForSingleTestCasesInJira(currentTestCaseName + " - "+currentTestCaseDescription, "PASS");	
			//***********************************************************************************
				
				//*** ZIP REport and Attached with Email or Jira 
				/*String reportFolder=FBConstants.REPORTS_ZipPATH;
				String xsltReportPath=reportFolder+"Reports.zip";
				try 
				{
					Zip.zipDir(System.getProperty("user.dir")+"//reportszip", xsltReportPath);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}

				myMSGJiraUpdater.createAnAttachmentInJira(myTestCaseIssueId, xsltReportPath);*/
		}
		else
		{
			//*********** JIRA Update Test case  Status FAIL   *********************************************
				//myMSGJiraUpdater.updateStatusForSingleTestCasesInJira(currentTestCaseName + " - "+currentTestCaseDescription, "FAIL");	
			//***********************************************************************************
		}
	}

	
	public void attachPassFailScreenShotForScenario()
	{
		for(int i=0; i<Test_Keywords_Runner.PassFailScreenFileName.size();i++)
		{
			ScenarioReport.log(LogStatus.FAIL,ScenarioReport.addScreenCapture(Test_Keywords_Runner.PassFailScreenFileName.get(i)));
		}
		Test_Keywords_Runner.PassFailScreenFileName.clear();
	}
	
	public  void takeScreenShotForScenario(){
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
	
	public static void ReportAndExit(String Step, String Page, String ObjectName, String Input, String ErrorText)
	{
		//test.log(LogStatus.FAIL, ErrorText);
		//extent.endTest(test);
		//extent.flush();
		//Assert.fail("Failed.. ");
		System.exit(0); // Stop Entire Run Session
	}

	public void LoadTestDataDictionary()
	{
		Xls_Reader currentTestCaseXLSFile;
		//currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+ZSuite_Test_DriverThread.currentTestSuite+".xlsx");
		currentTestCaseXLSFile = new Xls_Reader(System.getProperty("user.dir")+"//data//"+currentTestSuite+".xlsx");

		for(int row=2;row<=currentTestCaseXLSFile.getRowCount(Constants.TEST_DATA_SHEET);row++)
		{
			AllEmail.put(currentTestCaseXLSFile.getCellData(Constants.TEST_DATA_SHEET, "Account Email", row), "");
		}
		
	}

// ====>> OLD EXEC KEY Method using Reflection API 
	
	/*
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}

	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
	*/
	public void executeKeywords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
		
		// iterating through all keywords	
		for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);currentTestStepID++)
		{
			
			// checking TCID
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID, currentTestStepID)))
			{
				//currentKeyword=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.KEYWORD, currentTestStepID);
				currentKeyword=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Page", currentTestStepID);

				
				//APP_LOGS.debug(currentKeyword);
				// code to execute the keywords as well
			    // reflection API
				
				for(int i=0;i<method.length;i++){
					//System.out.println(method[i].getName());
					
					if(method[i].getName().equals(currentKeyword)){
					// finallize the data and object -pass it to function
					// Data Col - XLS
						
					String dataCol = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.Data, currentTestStepID);	
					// config, OR, XLS
					if(dataCol.startsWith(Constants.CONFIG)){
						// data has to be read from config.prop
						String temp[] = dataCol.split(Constants.dataSeparator);
						String key=temp[1];
						data=CONFIG.getProperty(key);
					}else if(dataCol.startsWith(Constants.COL)){
						String temp[] = dataCol.split(Constants.dataSeparator);
						String colName=temp[1];
						
						data= currentTestSuiteXLS.getCellData(currentTestCaseName, colName, currentTestDataSetID);
					}else{
						data=OR.getProperty(dataCol);
					}
					
					// OBJECT
					object=currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.OBJECT, currentTestStepID);
					
				    //keyword_execution_result=(String)method[i].invoke(keywords); // pass on the object and data to all functions in Keywords.java
				    
					//APP_LOGS.debug(keyword_execution_result);
					resultSet.add(keyword_execution_result);
					// report the result
					}
				}
			}	
		}
	}
	public void createXLSReport(){
		
		String colName=Constants.RESULT +(currentTestDataSetID-1);
		boolean isColExist=false;
		
		for(int c=0;c<currentTestSuiteXLS.getColumnCount(Constants.TEST_STEPS_SHEET);c++){
			System.out.println(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET,c , 2));
			if(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET,c , 1).equals(colName)){
				isColExist=true;
				break;
			}
		}
		
		if(!isColExist)
			currentTestSuiteXLS.addColumn(Constants.TEST_STEPS_SHEET, colName);
		int index=0;
		for(int i=2;i<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);i++){
			
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID, i))){
				if(resultSet.size()==0)
					currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.KEYWORD_SKIP);
				else	
					currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, resultSet.get(index));
				index++;
			}
			
			
		}
		
		if(resultSet.size()==0){
			// skip
			currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_SKIP);
			return;
		}else{
			for(int i=0;i<resultSet.size();i++){
				if(!resultSet.get(i).equals(Constants.KEYWORD_PASS)){
					currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_FAIL);
					return;
				}
			}
		}
		currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_PASS);
	//	if(!currentTestSuiteXLS.getCellData(currentTestCaseName, "Runmode",currentTestDataSetID).equals("Y")){}
		
	}

	
	
}
