package MSGOnlineDigital.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExcelParser {

	public static Logger APP_LOGS;
	//suite.xlsx
	public Xls_Reader suiteXLS;
	public int currentSuiteID;
	public String currentTestSuite;
	
	// current test suite
	public Xls_Reader currentTestSuiteXLS;

	public int currentTestCaseID;
	public static String currentTestCaseName;
	public static String currentTestCaseDescription;
	public static String CurrentTestBrowser;
	public static String CurrentTestPlatform;
	
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

	public static String testSteps;

	public String projectName;
	
	public TestExcelParser() throws IOException
	{
		//keywords = new Keywords(driver,test);
		//PageFactory.initElements(driver, keywords);

		//method = keywords.getClass().getMethods();
		
		// intitilaize properties

		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		      																	
		CONFIG = new Properties();
		CONFIG.load(ip);
		
		ip = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//or.properties");
																
		OR = new Properties();
		OR.load(ip);
		
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException 
	{
		TestExcelParser test = new TestExcelParser();
		//test.start();
		Map<String, String[]> myMap = test.getlistOfAllSelectors();
		for (String key : myMap.keySet()) 
		{
			String[] myValues = new String[2];
			myValues = myMap.get(key);
			System.out.println(key+" "+myValues[0]+" "+myValues[1]);
		}
	}

/**
 * Get List as following: Test Case, 
 * @throws IllegalAccessException
 * @throws IllegalArgumentException
 * @throws InvocationTargetException
 */
public Map<String, String[]> getlistOfAllSelectors()  throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
{
	Map<String, String[]> myDesiredSelectors = new HashMap<String, String[]>();
	
	// initialize the app logs
			APP_LOGS = Logger.getLogger("devpinoyLogger");
			APP_LOGS.debug("Hello");
			
			// 1) check the runmode of test Suite
			// 2) Runmode of the test case in test suite
		    // 3) Execute keywords of the test case serially
			// 4) Execute Keywords as many times as
			// number of data sets - set to Y
			APP_LOGS.debug("Intialize Suite xlsx");
		
			//******* Reading Test Manager Excel File ********** 
			suiteXLS = new Xls_Reader(System.getProperty("user.dir")+"//data//"+Constants.TEST_SUITE_FileName); 
										
			for(currentSuiteID=2;currentSuiteID<=suiteXLS.getRowCount(Constants.TEST_SUITE_SHEET);currentSuiteID++)
			{
				System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID)+" -- "+  suiteXLS.getCellData("Test Suite", "Runmode", currentSuiteID));
				APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID)+" -- "+  suiteXLS.getCellData("Test Suite", "Runmode", currentSuiteID));
				// test suite name = test suite xls file having tes cases
				currentTestSuite=suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID);
				if(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentSuiteID).equals(Constants.RUNMODE_YES))
				{
					// execute the test cases in the suite
					APP_LOGS.debug("******Executing the Suite******"+suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.Test_Suite_ID, currentSuiteID));

					//******* Reading Test Case Excel File ********** 
					currentTestSuiteXLS=new Xls_Reader(System.getProperty("user.dir")+"//data//"+currentTestSuite+".xlsx");
		
					/**
					 * Get All Selectors data 								
					 */
						
					Map<String, String[]> myAllSelectors = new HashMap<String, String[]>();
					
						//=======>> Loop Through the Entire test selectors sheet for all rows
						for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount(Constants.TEST_OBJECT_SELECTOR_SHEET);currentTestStepID++)
						{
							String myCss = currentTestSuiteXLS.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "CSS", currentTestStepID);	
							String myXpath = currentTestSuiteXLS.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Xpath", currentTestStepID);	
							
							if (myCss.length() > 0)
							{
								String[] myValues = new String[2];
								myValues[0] = myCss; myValues[1] = "CSS";
								myAllSelectors.put(currentTestSuiteXLS.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", currentTestStepID), myValues);
							}
							
							if (myXpath.length() > 0)
							{
								String[] myValues = new String[2];
								myValues[0] = myXpath; myValues[1] = "Xpath";
								myAllSelectors.put(currentTestSuiteXLS.getCellData(Constants.TEST_OBJECT_SELECTOR_SHEET, "Object", currentTestStepID), myValues);
							}
							
						}
					
					// iterate through all the test cases in the suite
					for(currentTestCaseID=2;currentTestCaseID<=currentTestSuiteXLS.getRowCount("Test Cases");currentTestCaseID++)
					{				
						APP_LOGS.debug(currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID)+" -- "+currentTestSuiteXLS.getCellData("Test Cases", "Runmode", currentTestCaseID));

						currentTestCaseName=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCID, currentTestCaseID).trim();
						currentTestCaseDescription=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCDESC, currentTestCaseID).trim();

						if(currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.RUNMODE, currentTestCaseID).equals(Constants.RUNMODE_YES))
						{
							//===Reporting Start From Here ====  
								//test = extent.startTest(currentTestCaseName + " - "+currentTestCaseDescription);
				    			//super.cap.setCapability("name", currentTestCaseName); 
								APP_LOGS.debug("Executing the test case -> "+currentTestCaseName);
								System.out.println("Executing the test case -> "+currentTestCaseName);

							//=== Test Case Parameter From Excel :  Browser(Chrome/FireFox) & Platform (Widows/Mac) Required for this Test 
								CurrentTestBrowser=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCBROWSER, currentTestCaseID);
								CurrentTestPlatform=currentTestSuiteXLS.getCellData(Constants.TEST_CASES_SHEET, Constants.TCPlatforms, currentTestCaseID);

							 /**
							  * Place for Implementing Logic for Test Steps sheet
							  */
								//=======>> Loop Through the Entire Test case Sheet All Rows 
								for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);currentTestStepID++)
								{
									//===> String Storing Each TC ID/Name One After Another          && TestStepSheet            &&TCIDField     Current_Row 
										String CurrentRowTestCase = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID, currentTestStepID);

									//===> Compare TC Need to Execute(currentTestCaseName) with (CurrentRowTestCase) if Match Found we will Start Executing The Steps of that TC  	
									if(currentTestCaseName.equals(CurrentRowTestCase.trim()))
									{
										String Step = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "TSID", currentTestStepID);
										String ObjectName = currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, "Object", currentTestStepID);	
										
										// Check for Duplicate - scenarios like related content etc.
										
										if ( ObjectName!=null && !myDesiredSelectors.containsKey(ObjectName)) 
										{
											myDesiredSelectors.put(ObjectName, myAllSelectors.get(ObjectName));
										}
									}	
								}
						}
					}
				}

			}	
				if (myDesiredSelectors.containsKey("")) myDesiredSelectors.remove("");
				return myDesiredSelectors;
}

}
