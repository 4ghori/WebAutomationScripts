package com.msgqa.eventengine.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.msg.suites.pom.util.FBConstants;
import com.msg.suites.pom.util.Xls_Reader;
import com.msgqa.eventengine.config.MSGEventEngineURLs;
import com.relevantcodes.extentreports.ExtentTest;
import MSGOnlineDigital.JIRA.MSGJIRAupdate;

public class Test_CasesReader
{

	public static Logger APP_LOGS;

	//==> Test Suite 
	public Xls_Reader suiteXLS;
	public int currentSuiteID;
	public String currentTestSuite;
	
	public  String TestEnvUrl;

	
	//==>Test Case
	public Xls_Reader currentTestSuiteXLS;
	public int currentTestCaseID;
	public String currentTestCaseName;
	public String currentTestCaseDescription;
	public String CurrentTestBrowser;
	public String CurrentTestPlatform;
	
	//==>Test Steps
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

	public String testSteps;
	
	//public static ExtentTest ScenarioReport = null;

	//**** JIRA Update Class Reference 
		MSGJIRAupdate myMSGJiraUpdater;
		public String myTestCaseIssueId;
		public static String myTestCycleId;
	
	public Test_CasesReader() throws IOException
	{
		//keywords = new Keywords(driver,test);
		//PageFactory.initElements(driver, keywords);

		//method = keywords.getClass().getMethods();
		
		// Initialize properties
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		      																	
		CONFIG = new Properties();
		CONFIG.load(ip);
		
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//or.properties");
																
		OR = new Properties();
		OR.load(ip);
		
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ParseException, URISyntaxException, java.text.ParseException 
	{
		Test_CasesReader test = new Test_CasesReader();
		test.start();
	}
	
	@Test(description="MSG Test Start..")
	public void MsgTestStart() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, URISyntaxException, java.text.ParseException
	{
		Test_CasesReader driverScriptTest = new Test_CasesReader();
		driverScriptTest.start();
	}

	public void start() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, URISyntaxException, java.text.ParseException, IOException{
		// initialize the logs
		//APP_LOGS = Logger.getLogger("devpinoyLogger");
		//.debug("Hello");
		
		//******* Start Reading Test Suite Excel-File and Loop Through all Test Suite Name (Example: Regression SUite,Smoke Suite)  ********** 
		suiteXLS = new Xls_Reader(System.getProperty("user.dir")+"//data//"+Constants.TEST_SUITE_FileName); 
		
		for(currentSuiteID=2;currentSuiteID<=suiteXLS.getRowCount(Constants.TEST_SUITE_SHEET);currentSuiteID++)
		{
			//################ Suite That Need to Execute ############################# 
				System.out.println("");
				System.out.println("Test Suite Name & Its Runmod \t: " 
									+ suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID) 
									+ " -- " 
									+ suiteXLS.getCellData("Test Suite", "Runmode", currentSuiteID));
				//APP_LOGS.debug("Test Suite Name & Its Runmod \t: " + suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID)+" -- "+  suiteXLS.getCellData("Test Suite", "Runmode", currentSuiteID));
			//############################################# 

			//################ Set Up Environment QA/Staging/Production or Take it From JENKIN ############################# 
				MSGEventEngineURLs.ENV = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RunEnv, currentSuiteID);
				System.out.println("Test Environemnt \t\t: " + MSGEventEngineURLs.getEnvDetails().get("URL"));
				//TestEnvUrl = MSGEventEngineURLs.getEnvDetails().get("URL");
			
				//===> If we get Env From Jenkins 
				//MSGEventEngineURLs.ENV = System.getProperty("testEnvironment");
				//TestEnvUrl = MSGEventEngineURLs.getEnvDetails().get("URL");
			//################################################################################## 
			
			if(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentSuiteID).equals(Constants.RUNMODE_YES))
			{
				//==> Test Suite That Flagged to Execute  
				currentTestSuite=suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID);
				
				//################Logging############################# 
					//System.out.println("");
					//System.out.println("\tExecuting the Suite \t\t: "+ currentTestSuite);
					//APP_LOGS.debug("\tExecuting the Suite \t\t: "+ currentTestSuite);
					//System.out.println("\t=========================================================================");

			    //############################################# 
	

				//****** > Read The Default Environment ( QA/Stage/ Dev ) and store the value
					//FBConstants.ENV = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RunEnv, currentSuiteID);
				
				//******** > JIRA Test Cycle Creating *********************************************************
							//myMSGJiraUpdater = new MSGJIRAupdate("MyMSGSuites",11200, currentTestSuite);
							//myMSGJiraUpdater.cycleId = myMSGJiraUpdater.createTestCycleInJira(currentTestSuite);
				//**********************************************************************************************


				//******* Reading Test Case Excel File ********** 
					//currentTestSuiteXLS=new Xls_Reader(System.getProperty("user.dir")+"//data//"+currentTestSuite+".xlsx");
				
				// iterate through all the test cases in the suite
				for(currentTestCaseID=2;currentTestCaseID<=suiteXLS.getRowCount("Test Cases");currentTestCaseID++)
				{
					//################Logging############################# 
						//System.out.println("\tTest Case & Its Runmod \t\t: " +currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID)+" -- "+currentTestSuiteXLS.getCellData("Test Cases", "Runmode", currentTestCaseID));
						//APP_LOGS.debug("\tTest Case & Its Runmod \t\t: " +currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID)+" -- "+currentTestSuiteXLS.getCellData("Test Cases", "Runmode", currentTestCaseID));
					//############################################# 

					currentTestCaseName=suiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID).trim();
					currentTestCaseDescription=suiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCDESC, currentTestCaseID).trim();
	
					if(suiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.RUNMODE, currentTestCaseID).equals(Constants.RUNMODE_YES))
					{
						System.out.println("\n==================================================================================");
						System.out.println("\tExecuting Test\t:- " + currentTestCaseName + "-" + currentTestCaseDescription);
						System.out.println("====================================================================================\n");
						
						//test = extent.startTest(currentTestCaseName + " - "+currentTestCaseDescription);
						
						
						   Test_DriverThread TestCaseThread = new Test_DriverThread();
							TestCaseThread.currentTestCaseName = currentTestCaseName;
							TestCaseThread.currentTestCaseDescription = currentTestCaseDescription;
							TestCaseThread.CurrentTestBrowser = suiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCBROWSER, currentTestCaseID);
							TestCaseThread.CurrentTestPlatform = suiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCPlatforms, currentTestCaseID);
							TestCaseThread.currentTestSuiteXLS = suiteXLS;
							TestCaseThread.currentTestSuite = currentTestSuite;
							
							//TestCaseThread.start();  	//Run Parallel Thread
							TestCaseThread.startTest(); //Run WithOut Thread       
						
						
						//===Reporting Start From Here ====  
							//test = extent.startTest(currentTestCaseName + " - "+currentTestCaseDescription);
							//APP_LOGS.debug("Executing the test case -> "+currentTestCaseName);
							//System.out.println("Executing the test case -> "+currentTestCaseName);
							

						//************** JIRA Create The Test Case  *********************************************
									//myTestCaseIssueId = myMSGJiraUpdater.createTestCaseInJira(currentTestCaseName + " - "+currentTestCaseDescription);
						//***********************************************************************************	
				 			
							
						//=== Test Case Parameter From Excel :  Browser(Chrome/FireFox) & Platform (Widows/Mac) Required for this Test 
							//CurrentTestBrowser=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCBROWSER, currentTestCaseID);
							//CurrentTestPlatform=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCPlatforms, currentTestCaseID);

					 // == Running With Multiple set of Data - If There is a TestCase Name Sheet Available in Excel 	
					 //if(currentTestSuiteXLS.isSheetExist(currentTestCaseName)) 
					 //{
						 // RUN as many times as number of test data sets with runmode Y
						 //for(currentTestDataSetID=2;currentTestDataSetID<=currentTestSuiteXLS.getRowCount(currentTestCaseName);currentTestDataSetID++)	
						  //{
							  //resultSet= new ArrayList<String>();
							  //APP_LOGS.debug("Iteration number "+(currentTestDataSetID-1));
							  // checking the runmode for the current data set
							  //if(currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.RUNMODE, currentTestDataSetID).equals(Constants.RUNMODE_YES))
							  //{

								  //=> iterating through all keywords - With multiple sets of data
								 //executeKeywords(); 
								  //fExecuteTestSteps();
							  //}
							  //createXLSReport();
						  //}
					  
					 //}
					 //else
					 //{
						  //=> iterating through all keywords - 
						 //executeKeywords();// no data with The test
						 //fExecuteTestSteps();

						 //resultSet= new ArrayList<String>(); //PATCH - Introduced later
						 //createXLSReport();
					 //}
					}
				}
			}
		}

		System.out.println("");
		System.out.println("**ALl Test Run Completed***");
	}
}
