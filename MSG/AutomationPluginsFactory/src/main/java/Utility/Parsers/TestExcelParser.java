package Utility.Parsers;

/**
 * Utility class for Excel Parser for KDD
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Automation Framework Excel Parser
 * @since 02/08/2017, 10:00AM EST
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TestExcelParser {

	public static Logger APP_LOGS;
	public static String currentTestCaseName;
	public static String currentTestCaseDescription;
	public static String currentTestStepName;
	public static String currentTestStepDescription;
	public static String CurrentTestBrowser;
	public static String CurrentTestPlatform;
	public static String testSteps;
	// suite.xlsx
	public Xls_Reader suiteXLS;
	public int currentSuiteID;
	public String currentTestSuite;
	// current test suite
	public Xls_Reader currentTestSuiteXLS;
	public int currentTestCaseID;
	public int currentTestStepID;
	public String currentKeyword;
	public String ExecuteNext;
	public int currentTestDataSetID;
	public Method method[];
	// public Keywords keywords;
	public String data;
	public String object;
	public String keyword_execution_result;
	public ArrayList<String> resultSet;
	public Properties CONFIG = null;
	public Properties OR = null;
	public String projectName;

	public TestExcelParser() throws IOException {
		// initialize properties
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//resources//config.properties");

		CONFIG = new Properties();
		CONFIG.load(ip);

		ip = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//resources//or.properties");

		OR = new Properties();
		OR.load(ip);

	}

	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		TestExcelParser test = new TestExcelParser();
		Map<String, String[]> myMap = test.getlistOfAllSelectors();
		int count = 1;
		for (String key : myMap.keySet()) {
			String[] myValues = new String[2];
			myValues = myMap.get(key);
			System.out.println("Entry" + count + " " + key + " " + myValues[0]
					+ " " + myValues[1]);
			count++;
		}
		test.getAllTestCasesOfTestCycle(test.getTestCycleName());
		Map<String, List<String>> testSteps = new HashMap<String, List<String>>();
		System.out.println(
				"Test Cases: " + test.getAllTestCasesOfTestCycle("Ad hoc"));
		System.out.println("Test Steps: "
				+ test.getAllTestStepsAssignedToAllTestCase("Ad hoc"));
	}

	/**
	 * Get all Selectors with their values in HashMap
	 *
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Map<String, String[]> getlistOfAllSelectors()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Map<String, String[]> myDesiredSelectors = new HashMap<String, String[]>();

		// initialize the app logs
		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("Hello");

		// 1) check the runmode of test Suite
		// 2) Runmode of the test case in test suite
		// 3) Execute keywords of the test case serially
		// 4) Execute Keywords as many times as
		// number of data sets - set to Y
		APP_LOGS.debug("Intialize Suite xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.TEST_SUITE_FileName);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS
				.getRowCount(Constants.TEST_SUITE_SHEET); currentSuiteID++) {
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			// test suite name = test suite xls file having tes cases
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID);
			if (suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				// execute the test cases in the suite
				APP_LOGS.debug("******Executing the Suite******"
						+ suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
								Constants.Test_Suite_ID, currentSuiteID));

				// ******* Reading Test Case Excel File **********
				currentTestSuiteXLS = new Xls_Reader(
						System.getProperty("user.dir") + "//data//"
								+ currentTestSuite + ".xlsx");

				/**
				 * Get All Selectors data
				 */

				Map<String, String[]> myAllSelectors = new HashMap<String, String[]>();

				// =======>> Loop Through the Entire test selectors sheet for
				// all rows
				for (currentTestStepID = 2; currentTestStepID <= currentTestSuiteXLS
						.getRowCount(
								Constants.TEST_OBJECT_SELECTOR_SHEET); currentTestStepID++) {
					String[] myValues = new String[2];
					String desktopSelector = currentTestSuiteXLS.getCellData(
							Constants.TEST_OBJECT_SELECTOR_SHEET, "Desktop",
							currentTestStepID);
					String mobileSelector = currentTestSuiteXLS.getCellData(
							Constants.TEST_OBJECT_SELECTOR_SHEET, "Mobile",
							currentTestStepID);
					if (System.getenv("SAUCE_ONDEMAND_BROWSERS").toString().matches("(?i).*iphone.*|.*ipad.*|.*android.*|.*iOS.*")) {
						myValues[0] = desktopSelector;
						myValues[1] = mobileSelector;
					} else {
						myValues[0] = desktopSelector;
						myValues[1] = "";
					}
					myAllSelectors.put(currentTestSuiteXLS.getCellData(
							Constants.TEST_OBJECT_SELECTOR_SHEET, "Object",
							currentTestStepID), myValues);
				}

				// iterate through all the test cases in the suite
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS
						.getRowCount("Test Cases"); currentTestCaseID++) {
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(
							Constants.TEST_CASES_SHEET, Constants.TCID,
							currentTestCaseID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Cases",
									"Runmode", currentTestCaseID));

					currentTestCaseName = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCID, currentTestCaseID)
							.trim();
					currentTestCaseDescription = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCDESC, currentTestCaseID)
							.trim();

					if (currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.RUNMODE, currentTestCaseID)
							.equals(Constants.RUNMODE_YES)) {
						// ===Reporting Start From Here ====
						// test = extent.startTest(currentTestCaseName + " -
						// "+currentTestCaseDescription);
						// super.cap.setCapability("name", currentTestCaseName);
						APP_LOGS.debug("Executing the test case -> "
								+ currentTestCaseName);
						System.out.println("Executing the test case -> "
								+ currentTestCaseName);

						// === Test Case Parameter From Excel :
						// Browser(Chrome/FireFox) & Platform (Widows/Mac)
						// Required for this Test
						CurrentTestBrowser = currentTestSuiteXLS.getCellData(
								Constants.TEST_CASES_SHEET, Constants.TCBROWSER,
								currentTestCaseID);
						CurrentTestPlatform = currentTestSuiteXLS.getCellData(
								Constants.TEST_CASES_SHEET,
								Constants.TCPlatforms, currentTestCaseID);

						/**
						 * Place for Implementing Logic for Test Steps sheet
						 */
						// =======>> Loop Through the Entire Test case Sheet All
						// Rows
						for (currentTestStepID = 2; currentTestStepID <= currentTestSuiteXLS
								.getRowCount(
										Constants.TEST_STEPS_SHEET); currentTestStepID++) {
							// ===> String Storing Each TC ID/Name One After
							// Another && TestStepSheet &&TCIDField Current_Row
							String CurrentRowTestCase = currentTestSuiteXLS
									.getCellData(Constants.TEST_STEPS_SHEET,
											Constants.TCID, currentTestStepID);

							// ===> Compare TC Need to
							// Execute(currentTestCaseName) with
							// (CurrentRowTestCase) if Match Found we will Start
							// Executing The Steps of that TC
							if (currentTestCaseName
									.equals(CurrentRowTestCase.trim())) {
								String Step = currentTestSuiteXLS.getCellData(
										Constants.TEST_STEPS_SHEET, "TSID",
										currentTestStepID);
								String ObjectName = currentTestSuiteXLS
										.getCellData(Constants.TEST_STEPS_SHEET,
												"Object", currentTestStepID);

								// Check for Duplicate - scenarios like related
								// content etc.

								if (ObjectName != null && !myDesiredSelectors
										.containsKey(ObjectName)) {
									myDesiredSelectors.put(ObjectName,
											myAllSelectors.get(ObjectName));
								}
							}
						}
					}
				}
			}

		}
		if (myDesiredSelectors.containsKey(""))
			myDesiredSelectors.remove("");
		return myDesiredSelectors;
	}

	private String getTestCycleName() {
		String cycleName = null;

		// initialize the app logs
		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("Hello");
		APP_LOGS.debug("Intialize Suite xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.TEST_SUITE_FileName);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS
				.getRowCount(Constants.TEST_SUITE_SHEET); currentSuiteID++) {
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID);
			if (suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				return suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
						Constants.Test_Suite_ID, currentSuiteID);
			}
		}
		System.out.println(cycleName);
		return cycleName;
	}

	public List<String> getAllTestCasesOfTestCycle(String testCycle) {
		List<String> testCases = new ArrayList<String>();

		// initialize the app logs
		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("Hello");

		// 1) check the runmode of test Suite
		// 2) Runmode of the test case in test suite
		// 3) Execute keywords of the test case serially
		// 4) Execute Keywords as many times as
		// number of data sets - set to Y
		APP_LOGS.debug("Intialize Suite xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.TEST_SUITE_FileName);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS
				.getRowCount(Constants.TEST_SUITE_SHEET); currentSuiteID++) {
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			// test suite name = test suite xls file having tes cases
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID);
			if (suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				// execute the test cases in the suite
				APP_LOGS.debug("******Executing the Suite******"
						+ suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
								Constants.Test_Suite_ID, currentSuiteID));

				// ******* Reading Test Case Excel File **********
				currentTestSuiteXLS = new Xls_Reader(
						System.getProperty("user.dir") + "//data//"
								+ currentTestSuite + ".xlsx");

				// iterate through all the test cases in the suite
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS
						.getRowCount("Test Cases"); currentTestCaseID++) {
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(
							Constants.TEST_CASES_SHEET, Constants.TCID,
							currentTestCaseID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Cases",
									"Runmode", currentTestCaseID));

					currentTestCaseName = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCID, currentTestCaseID)
							.trim();
					currentTestCaseDescription = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCDESC, currentTestCaseID)
							.trim();

					if (currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.RUNMODE, currentTestCaseID)
							.equals(Constants.RUNMODE_YES)) {
						// ===Reporting Start From Here ====
						// test = extent.startTest(currentTestCaseName + " -
						// "+currentTestCaseDescription);
						// super.cap.setCapability("name", currentTestCaseName);
						APP_LOGS.debug("Executing the test case -> "
								+ currentTestCaseName);
						// System.out.println("Executing the test case ->
						// "+currentTestCaseName);
						testCases.add(currentTestCaseName);
					}
				}
			}
		}

		System.out.println(testCases);
		return testCases;
	}

	public HashMap<String, List<String>> getAllTestStepsAssignedToAllTestCase(
			String testCycleName) {
		HashMap<String, List<String>> testSteps = new HashMap<String, List<String>>();

		List<String> testCases = new ArrayList<String>();

		// initialize the app logs
		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("Hello");

		// 1) check the runmode of test Suite
		// 2) Runmode of the test case in test suite
		// 3) Execute keywords of the test case serially
		// 4) Execute Keywords as many times as
		// number of data sets - set to Y
		APP_LOGS.debug("Intialize Suite xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.TEST_SUITE_FileName);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS
				.getRowCount(Constants.TEST_SUITE_SHEET); currentSuiteID++) {
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			// test suite name = test suite xls file having tes cases
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID);
			if (suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				// execute the test cases in the suite
				APP_LOGS.debug("******Executing the Suite******"
						+ suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
								Constants.Test_Suite_ID, currentSuiteID));

				// ******* Reading Test Case Excel File **********
				currentTestSuiteXLS = new Xls_Reader(
						System.getProperty("user.dir") + "//data//"
								+ currentTestSuite + ".xlsx");

				// iterate through all the test cases in the suite
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS
						.getRowCount("Test Cases"); currentTestCaseID++) {
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(
							Constants.TEST_CASES_SHEET, Constants.TCID,
							currentTestCaseID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Cases",
									"Runmode", currentTestCaseID));

					currentTestCaseName = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCID, currentTestCaseID)
							.trim();
					currentTestCaseDescription = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCDESC, currentTestCaseID)
							.trim();

					if (currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.RUNMODE, currentTestCaseID)
							.equals(Constants.RUNMODE_YES)) {
						// ===Reporting Start From Here ====
						// test = extent.startTest(currentTestCaseName + " -
						// "+currentTestCaseDescription);
						// super.cap.setCapability("name", currentTestCaseName);
						APP_LOGS.debug("Executing the test case -> "
								+ currentTestCaseName);
						// System.out.println("Executing the test case ->
						// "+currentTestCaseName);
						testCases.add(currentTestCaseName);
					}
				}
			}
		}

		int count = 1;
		String lastTestCaseName = null;
		List<String> stepsList = new ArrayList<String>();

		// iterate through all the test Steps in the suite
		for (currentTestStepID = 2; currentTestStepID <= currentTestSuiteXLS
				.getRowCount("Test Steps"); currentTestStepID++) {
			APP_LOGS.debug(
					currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET,
							Constants.TCID, currentTestStepID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Steps",
									"Runmode", currentTestStepID));

			currentTestCaseName = currentTestSuiteXLS
					.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID,
							currentTestStepID)
					.trim();
			if (testCases.contains(currentTestCaseName)) {
				currentTestStepName = currentTestSuiteXLS
						.getCellData(Constants.TEST_STEPS_SHEET, Constants.TSID,
								currentTestStepID)
						.trim();
				currentTestStepDescription = currentTestSuiteXLS
						.getCellData(Constants.TEST_STEPS_SHEET,
								Constants.TCDESC, currentTestStepID)
						.trim();

				if (currentTestSuiteXLS
						.getCellData(Constants.TEST_STEPS_SHEET, testCycleName,
								currentTestStepID)
						.equals(Constants.RUNMODE_YES)) {
					// ===Reporting Start From Here ====
					// test = extent.startTest(currentTestStepName + " -
					// "+currentTestStepDescription);
					// super.cap.setCapability("name", currentTestStepName);
					APP_LOGS.debug("Executing the test Step -> "
							+ currentTestStepName);

					// Control the adding to the TestSteps Hashmap.
					if (count == 1) {
						lastTestCaseName = currentTestCaseName;
					}

					if (testCases.contains(lastTestCaseName)) {
						if (lastTestCaseName != currentTestCaseName) {
							testSteps.put(lastTestCaseName, stepsList);
							lastTestCaseName = currentTestCaseName;
							stepsList = new ArrayList<String>();
						}

						if (!currentTestStepName.isEmpty()) {
							stepsList.add(currentTestStepName);
						}
					}
					count++;
				}
			}
		}
		if (testCases.contains(lastTestCaseName)) {
			testSteps.put(lastTestCaseName, stepsList);
		}
		System.out.println(testSteps);
		return testSteps;
	}

	public HashMap<String, List<String>> getAllTestStepsAssignedToAllTestCase() {
		HashMap<String, List<String>> testSteps = new HashMap<String, List<String>>();

		List<String> testCases = new ArrayList<String>();

		// initialize the app logs
		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("Hello");

		// 1) check the runmode of test Suite
		// 2) Runmode of the test case in test suite
		// 3) Execute keywords of the test case serially
		// 4) Execute Keywords as many times as
		// number of data sets - set to Y
		APP_LOGS.debug("Intialize Suite xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.TEST_SUITE_FileName);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS
				.getRowCount(Constants.TEST_SUITE_SHEET); currentSuiteID++) {
			System.out.println(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID) + " -- "
					+ suiteXLS.getCellData("Test Suite", "Runmode",
							currentSuiteID));
			// test suite name = test suite xls file having tes cases
			currentTestSuite = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.Test_Suite_ID, currentSuiteID);
			if (suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
					Constants.RUNMODE, currentSuiteID)
					.equals(Constants.RUNMODE_YES)) {
				// execute the test cases in the suite
				APP_LOGS.debug("******Executing the Suite******"
						+ suiteXLS.getCellData(Constants.TEST_SUITE_SHEET,
								Constants.Test_Suite_ID, currentSuiteID));

				// ******* Reading Test Case Excel File **********
				currentTestSuiteXLS = new Xls_Reader(
						System.getProperty("user.dir") + "//data//"
								+ currentTestSuite + ".xlsx");

				// iterate through all the test cases in the suite
				for (currentTestCaseID = 2; currentTestCaseID <= currentTestSuiteXLS
						.getRowCount("Test Cases"); currentTestCaseID++) {
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(
							Constants.TEST_CASES_SHEET, Constants.TCID,
							currentTestCaseID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Cases",
									"Runmode", currentTestCaseID));

					currentTestCaseName = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCID, currentTestCaseID)
							.trim();
					currentTestCaseDescription = currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.TCDESC, currentTestCaseID)
							.trim();

					if (currentTestSuiteXLS
							.getCellData(Constants.TEST_CASES_SHEET,
									Constants.RUNMODE, currentTestCaseID)
							.equals(Constants.RUNMODE_YES)) {
						// ===Reporting Start From Here ====
						// test = extent.startTest(currentTestCaseName + " -
						// "+currentTestCaseDescription);
						// super.cap.setCapability("name", currentTestCaseName);
						APP_LOGS.debug("Executing the test case -> "
								+ currentTestCaseName);
						// System.out.println("Executing the test case ->
						// "+currentTestCaseName);
						testCases.add(currentTestCaseName);
					}
				}
			}
		}

		int count = 1;
		String lastTestCaseName = null;
		List<String> stepsList = new ArrayList<String>();

		// iterate through all the test Steps in the suite
		for (currentTestStepID = 2; currentTestStepID <= currentTestSuiteXLS
				.getRowCount("Test Steps"); currentTestStepID++) {
			APP_LOGS.debug(
					currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET,
							Constants.TCID, currentTestStepID) + " -- "
							+ currentTestSuiteXLS.getCellData("Test Steps",
									"Runmode", currentTestStepID));

			currentTestCaseName = currentTestSuiteXLS
					.getCellData(Constants.TEST_STEPS_SHEET, Constants.TCID,
							currentTestStepID)
					.trim();
			if (testCases.contains(currentTestCaseName)) {
				currentTestStepName = currentTestSuiteXLS
						.getCellData(Constants.TEST_STEPS_SHEET, Constants.TSID,
								currentTestStepID)
						.trim();
				currentTestStepDescription = currentTestSuiteXLS
						.getCellData(Constants.TEST_STEPS_SHEET,
								Constants.TCDESC, currentTestStepID)
						.trim();

				if (currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET,
						Constants.STEP_EXECUTION_CONTROLLER, currentTestStepID)
						.equals(Constants.RUNMODE_YES)) {
					// ===Reporting Start From Here ====
					// test = extent.startTest(currentTestStepName + " -
					// "+currentTestStepDescription);
					// super.cap.setCapability("name", currentTestStepName);
					APP_LOGS.debug("Executing the test Step -> "
							+ currentTestStepName);

					// Control the adding to the TestSteps Hashmap.
					if (count == 1) {
						lastTestCaseName = currentTestCaseName;
					}

					if (testCases.contains(lastTestCaseName)) {
						if (lastTestCaseName != currentTestCaseName) {
							testSteps.put(lastTestCaseName, stepsList);
							lastTestCaseName = currentTestCaseName;
							stepsList = new ArrayList<String>();
						}

						if (!currentTestStepName.isEmpty()) {
							stepsList.add(currentTestStepName);
						}
					}
					count++;
				}
			}
		}
		if (testCases.contains(lastTestCaseName)) {
			testSteps.put(lastTestCaseName, stepsList);
		}
		System.out.println(testSteps);
		return testSteps;
	}

	/**
	 * Specific Requirement for monitoring the URLs returned through Appdynamics
	 * Monitoring tol
	 */

	public List<String> getAllValidURLs(String testEnvrionment) {
		List<String> validURLs = new ArrayList<String>();
		List<String> invalidURLs = new ArrayList<String>();

		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("MSG QA Team Excel Parser");
		APP_LOGS.debug("Sales_center_MSGCom_business_transactions xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.SALES_CENTER_MSGCom_BTE);

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS.getRowCount(
				Constants.SALES_CENTER_MSGCom_BTE_SHEET); currentSuiteID++) {
			// ******* Reading Test Case Excel File **********
			currentTestCaseName = suiteXLS
					.getCellData(Constants.SALES_CENTER_MSGCom_BTE_SHEET,
							Constants.BTETYPE, currentSuiteID)
					.trim();
			currentTestCaseDescription = suiteXLS
					.getCellData(Constants.SALES_CENTER_MSGCom_BTE_SHEET,
							Constants.BTENAME, currentSuiteID)
					.trim();

			if (currentTestCaseDescription != null) {
				// Check if URL is valid or Invalid.
				try {
					if (verifyIfLinkIsWorking(testEnvrionment
									+ currentTestCaseDescription) != 404) {
						validURLs.add(
								testEnvrionment + currentTestCaseDescription);
					} else {
						invalidURLs.add(
								testEnvrionment + currentTestCaseDescription);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("List of Valid URLs: ");
		for (int i = 0; i < validURLs.size(); i++) {
			System.out.println(validURLs.get(i).toString());
		}

		System.out.println("List of Invalid URLs: ");
		for (int i = 0; i < invalidURLs.size(); i++) {
			System.out.println(invalidURLs.get(i).toString());
		}

		return validURLs;
	}

	public List<String> getAllValidURLsWithNav(String testEnvrionment) {
		List<String> validURLs = new ArrayList<String>();
		List<String> invalidURLs = new ArrayList<String>();

		APP_LOGS = Logger.getLogger("ExcelParser");
		APP_LOGS.debug("MSG QA Team Excel Parser");
		APP_LOGS.debug("Sales_center_MSGCom_business_transactions xlsx");

		// ******* Reading Test Manager Excel File **********
		suiteXLS = new Xls_Reader(System.getProperty("user.dir") + "//data//"
				+ Constants.SALES_CENTER_MSGCom_BTE);
		System.setProperty("webdriver.chrome.driver",
				"/Users/rastogir/git/MSGSalesCenterAutomatedTesting/resources/driver/chromedriver");
		WebDriver myDriver = new ChromeDriver();

		for (currentSuiteID = 2; currentSuiteID <= suiteXLS.getRowCount(
				Constants.SALES_CENTER_MSGCom_BTE_SHEET); currentSuiteID++) {
			// ******* Reading Test Case Excel File **********
			currentTestCaseName = suiteXLS
					.getCellData(Constants.SALES_CENTER_MSGCom_BTE_SHEET,
							Constants.BTETYPE, currentSuiteID)
					.trim();
			currentTestCaseDescription = suiteXLS
					.getCellData(Constants.SALES_CENTER_MSGCom_BTE_SHEET,
							Constants.BTENAME, currentSuiteID)
					.trim();

			if (currentTestCaseDescription != null) {
				// Check if URL is valid or Invalid.
				try {
					myDriver.get(testEnvrionment + currentTestCaseDescription);
					WebDriverWait wait = new WebDriverWait(myDriver, 2);
					wait.until(ExpectedConditions.presenceOfElementLocated(
							By.cssSelector("[id=app]>div>div>div>h1")));
					WebElement myStatus = myDriver.findElement(
							By.cssSelector("[id=app]>div>div>div>h1"));
					if (myStatus.getText().toString().equals("404")) {
						invalidURLs.add(
								testEnvrionment + currentTestCaseDescription);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					validURLs.add(testEnvrionment + currentTestCaseDescription);
				}
			}
		}

		System.out.println("List of Valid URLs: ");
		for (int i = 0; i < validURLs.size(); i++) {
			System.out.println(validURLs.get(i).toString());
		}

		System.out.println("List of Invalid URLs: ");
		for (int i = 0; i < invalidURLs.size(); i++) {
			System.out.println(invalidURLs.get(i).toString());
		}

		return validURLs;
	}
	
	public static Integer verifyIfLinkIsWorking(String urlToVerify)
			throws Exception {
		URL url = new URL(urlToVerify);
		HttpURLConnection myConnection = (HttpURLConnection) url
				.openConnection();
		try {
			myConnection.connect();
			Integer myResponse = myConnection.getResponseCode();
			myConnection.disconnect();
			return myResponse;
		} catch (Exception exp) {
			System.out.println("URL: " + url + " Can't be rsolved");
			return 404;
		}
	}
}