package com.msg.qa.common;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("deprecation")
public class MSGOnlineAfterSuiteMethods {
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	private static final Logger logger = Logger
			.getLogger(MSGOnlineAfterSuiteMethods.class);
	HashMap<String, String> testCaseIndividualResults = new HashMap<String, String>();
	List<HashMap<String, String>> testCaseCompleteResults = Arrays.asList(
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults,
			testCaseIndividualResults, testCaseIndividualResults);
	private HashMap<String, String> sauceRestCallResults;
	private WebDriver driver;
	public MSGOnlineAfterSuiteMethods(WebDriver driver) {
		this.driver = driver;
	}
	public MSGOnlineAfterSuiteMethods() {
	}

	// ************ BEGIN FUNCTIONS ************//

	public String handleParentSleep(int numberOfPlatformDesiredCapabilities)
			throws InterruptedException, ClientProtocolException,
			ParseException, IOException, org.json.simple.parser.ParseException {
		this.driver.navigate().to("about:blank");
		Thread.sleep(30000);
		SauceLabsGlobalFunctions sLGF = new SauceLabsGlobalFunctions();
		String currentJobStatus = sLGF
				.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities,
						USERNAME, ACCESS_KEY)
				.toString();
		if(System.getProperty("runLocallyAs") != null) {
			while(!System.getProperty("testCasesStatus").equals("completed")) {
				Thread.sleep(3000);
			}
			currentJobStatus = "complete";
		} else {
			while (currentJobStatus.contains("progress")) {
				Thread.sleep(3000);
				this.driver.navigate().to("about:blank");
				currentJobStatus = sLGF
						.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities,
								USERNAME, ACCESS_KEY)
						.toString();
			}
			while ((currentJobStatus.contains("progress"))
					&& currentJobStatus.contains("complete")) {
				Thread.sleep(3000);
				this.driver.navigate().to("about:blank");
				currentJobStatus = sLGF
						.getSauceLabsJobStatus(numberOfPlatformDesiredCapabilities,
								USERNAME, ACCESS_KEY)
						.toString();
			}
		}
		return currentJobStatus;
	}

	public List<String> jobStatusChildren(String jobStatusPassed,
			int numberOfDesiredCaps)
			throws ClientProtocolException, InterruptedException,
			ParseException, IOException, org.json.simple.parser.ParseException {
		int jobStatusLength = jobStatusPassed.length();
		int cjsLB = 0;
		int cjsRB = 0;
		List<String> childrenJobStatus = Arrays.asList("", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "");
		for (int nDCI = 0; nDCI < numberOfDesiredCaps; nDCI++) {
			cjsLB = jobStatusPassed.indexOf("{");
			cjsRB = jobStatusPassed.indexOf("}");
			childrenJobStatus.set(nDCI,
					jobStatusPassed.substring(cjsLB, cjsRB + 1));
			jobStatusPassed = jobStatusPassed.substring(cjsRB + 1);
		}
		System.out.println(
				"Debug-Point: Children Job Status: " + childrenJobStatus);
		return childrenJobStatus;
	}

	public List<HashMap<String, String>> individualTestCaseStatus(
			String jobStatusToParse, String testCaseName,
			int numberOfDesiredCapabilities) {
		int jSLB = 0;
		int jSRB = 0;
		int iJSTSPLB = 0;
		int iJSTSPRB = 0;
		int iJSTSSLB = 0;
		int iJSTSSRB = 0;
		int iJSTSBLB = 0;
		int iJSTSBRB = 0;
		int iJSTSVLB = 0;
		int iJSTSVRB = 0;
		for (int jobStatusIncrementor = 0; jobStatusIncrementor < numberOfDesiredCapabilities; jobStatusIncrementor++) {
			HashMap<String, String> testCaseIndividualResults = new HashMap<String, String>();
			String testStepPlatform;
			jSLB = jobStatusToParse.indexOf("{");
			jSRB = jobStatusToParse.indexOf("}");
			String individualJobStatus = jobStatusToParse.substring(jSLB,
					jSRB + 1);
			if (individualJobStatus.contains("Platform:")) {
				iJSTSPLB = individualJobStatus.indexOf("Platform:");
				iJSTSPRB = individualJobStatus.indexOf("modification_time");
				testStepPlatform = individualJobStatus.substring(iJSTSPLB,
						iJSTSPRB - 3);
			} else {
				iJSTSPLB = individualJobStatus.indexOf("browser=");
				iJSTSPRB = individualJobStatus.indexOf("browser_version=");
				testStepPlatform = individualJobStatus.substring(iJSTSPLB + 9,
						iJSTSPRB - 3);
			}
			iJSTSSLB = individualJobStatus.indexOf("passed=");
			iJSTSSRB = individualJobStatus.indexOf("owner=");
			iJSTSBLB = individualJobStatus.indexOf("browser=");
			iJSTSBRB = individualJobStatus.indexOf("browser_version=");
			iJSTSVLB = individualJobStatus.indexOf("browser_short_version=");
			iJSTSVRB = individualJobStatus.indexOf("os=");
			String testStepBrowser = individualJobStatus.substring(iJSTSBLB + 9,
					iJSTSBRB - 3);
			String testStepBrowserVersion = individualJobStatus
					.substring(iJSTSVLB + 23, iJSTSVRB - 3);
			String testStepStatus = individualJobStatus.substring(iJSTSSLB,
					iJSTSSRB - 2);
			if (testStepPlatform.contains("MAC")) {
				testStepPlatform = "MAC";
			}
			if (testStepPlatform.contains("WIN")) {
				testStepPlatform = "WINDOWS";
			}
			if (testStepBrowser.toLowerCase().contains("hrome")) {
				testCaseIndividualResults.put("testStepBrowser", "Chrome");
				testStepBrowser = "Chrome";
			}
			if (testStepBrowser.toLowerCase().contains("irefox")) {
				testCaseIndividualResults.put("testStepBrowser", "Firefox");
				testStepBrowser = "Firefox";
			}
			if (testStepBrowser.toLowerCase().contains("afari")) {
				testCaseIndividualResults.put("testStepBrowser", "Safari");
				testStepBrowser = "Safari";
			}
			if (testStepBrowser.toLowerCase().contains("xplorer")) {
				testCaseIndividualResults.put("testStepBrowser", "IE");
				testStepBrowser = "IE";
			}
			if (testStepBrowser.toLowerCase().contains("dge")) {
				testCaseIndividualResults.put("testStepBrowser", "Edge");
				testStepBrowser = "Edge";
			}
			if (testStepBrowser.toLowerCase().contains("phone")) {
				testStepPlatform = "iOS";
				testStepBrowser = "iPhone";
				int iPhoneParameterLB = individualJobStatus.indexOf("- iPhone");
				int iPhoneParameterRB = individualJobStatus
						.indexOf("Simulator");
				testStepBrowserVersion = individualJobStatus.substring(
						iPhoneParameterLB + 9, iPhoneParameterRB - 1);
			}

			if (testStepBrowser.toLowerCase().contains("pad")) {
				testStepPlatform = "iOS";
				testStepBrowser = "iPad";
				int iPhoneParameterLB = individualJobStatus.indexOf("- iPad");
				int iPhoneParameterRB = individualJobStatus
						.indexOf("Simulator");
				testStepBrowserVersion = individualJobStatus.substring(
						iPhoneParameterLB + 7, iPhoneParameterRB - 1);
			}

			if (testStepBrowser.toLowerCase().contains("afari")
					&& testStepBrowserVersion.contains("8")) {
				testCaseIndividualResults.put("testStepBrowserVersion", "10");
				testStepBrowserVersion = "10";
			} else {
				testCaseIndividualResults.put("testStepBrowserVersion",
						testStepBrowserVersion);
			}

			testCaseIndividualResults.put("testCaseName", testCaseName);
			testCaseIndividualResults.put("testStepPlatform", testStepPlatform);
			/**
			 * commented on 09/29/17 by Rachit Kumar RASTOGI String to update
			 * JIRA Test Steps
			 */
			String testStepParameters = testStepPlatform; // + " " +
															// testStepBrowser +
															// " " +
															// testStepBrowserVersion;
			testCaseIndividualResults.put("testStepParameters",
					testStepParameters);

			if (testStepStatus.contains("true")) {
				testCaseIndividualResults.put("testStepStatus", "PASS");
			} else {
				testCaseIndividualResults.put("testStepStatus", "FAIL");
			}
			testCaseCompleteResults.set(jobStatusIncrementor,
					testCaseIndividualResults);
			jobStatusToParse = jobStatusToParse.substring(jSRB + 1);
		}

		System.out.println("Debug-Point: testCaseCompleteResults are - "
				+ testCaseCompleteResults);
		return testCaseCompleteResults;
	}

}
