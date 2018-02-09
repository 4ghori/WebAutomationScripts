package com.msg.qa.MSGAPI.EventEngine;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.junit.Testcase;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.tools.SoapUITestCaseRunner;
import com.eviware.soapui.tools.SoapUILoadTestRunner;
import com.eviware.soapui.impl.rest.*;
import com.eviware.soapui.support.types.StringToObjectMap;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;
import com.msg.qa.MSGAPI.EventEngine.EventEngineAPIGlobalFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import com.qaprosoft.carina.core.foundation.UITest;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;



@SuppressWarnings("deprecation")
public class EventEngineAPIRegressionScript extends AbstractPage {
	private static final Logger logger = Logger.getLogger(EventEngineAPIRegressionScript.class);

	public HashMap<String, String> testErrors = new HashMap<String, String>();
	
	public EventEngineAPIRegressionScript(WebDriver driver) {
		super(driver);
	}

	public static String testHost = System.getProperty("testHost");
	
	public HashMap<String, HashMap<String, String>> testSoapUIXML(String XMLFile, String xmlTestSuite, String xmlTestCase) throws IOException {
		EventEngineAPIGlobalFunctions eGF = new EventEngineAPIGlobalFunctions(driver);
		SoapUITestCaseRunner soapUITestCaseRunner = new SoapUITestCaseRunner();
		String testCompleteResults= "";
		HashMap<String, HashMap<String, String>> testCompleteResultsHashMap = new HashMap<String, HashMap<String, String>>();
		String hello = new String();
		TestCaseRunner tr;
		soapUITestCaseRunner.setProjectFile(XMLFile);
		 try {
			 soapUITestCaseRunner.setOutputFolder("SoapOutputFolder");
			 soapUITestCaseRunner.setPrintReport(true);
			 soapUITestCaseRunner.setJUnitReport(true);
			 soapUITestCaseRunner.setPrintAlertSiteReport(true);
			 soapUITestCaseRunner.setTestSuite(xmlTestSuite);
			 soapUITestCaseRunner.setTestCase(xmlTestCase);
			 System.out.println(testHost);
			 soapUITestCaseRunner.setHost(testHost);
			 soapUITestCaseRunner.run();
			 testCompleteResults = eGF.fileToString("SoapOutputFolder/test_case_run_log_report.xml");
			 testCompleteResultsHashMap = eGF.parseTestCaseResults(testCompleteResults);
			 System.out.println("getProjectFile: " + soapUITestCaseRunner.getProjectFile());
 			 if (!(soapUITestCaseRunner.getAssertionResults().toString().equalsIgnoreCase("{}"))) {
 				 testCompleteResults = eGF.fileToString("SoapOutputFolder/test_case_run_log_report.xml");
 				testCompleteResultsHashMap = eGF.parseTestCaseResults(testCompleteResults);
// 				 Assert.fail();
 			 }
			 
		 } catch (Exception e) {
			 testCompleteResults = eGF.fileToString("SoapOutputFolder/test_case_run_log_report.xml");
			 testCompleteResultsHashMap = eGF.parseTestCaseResults(testCompleteResults);
			 testErrors.put(soapUITestCaseRunner.getProjectFile().toString(), e.getLocalizedMessage().toString());
			 if (testErrors.get(soapUITestCaseRunner.getProjectFile().toString()).contains("Status: FAILED")) {
			 }
	    }
		 return testCompleteResultsHashMap;
	}
	

}
