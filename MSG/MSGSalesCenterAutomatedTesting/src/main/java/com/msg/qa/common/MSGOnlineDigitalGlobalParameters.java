package com.msg.qa.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.msg.qa.MSGOnlineDigital.SalesCenter.MSGSalesCenterRegressionScript;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("deprecation")
public class MSGOnlineDigitalGlobalParameters extends AbstractPage {
	// SauceLabs User Name and Access Key - to be read from Environment
	// Variables
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URLwithEnvVariables = "https://" + USERNAME + ":"
			+ ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	// SauceLabs User Name and Access Key - Fallback in case Environment
	// variables are not setup
	// public static final String myUSERNAME = "rachit";
	// public static final String myUSERNAME = "SnehalChudgar";
	// public static final String myACCESS_KEY =
	// "05d919ba-84e4-49fb-b84c-69f8af1918bf"; //rachit access key
	// public static final String myACCESS_KEY =
	// "48ec4a37-dd0a-49d0-b397-ef08c81dcebe"; //snehal access key
	public static final String myUSERNAME = "MSGTesting";
	public static final String myACCESS_KEY = "9efcf806-4026-4cf1-9d99-75b62178b6a8";
	public static final String URLwithHardCodedVariables = "https://"
			+ myUSERNAME + ":" + myACCESS_KEY
			+ "@ondemand.saucelabs.com:443/wd/hub";
	// Tags to be used in Desired capabilities
	public static final String myBrowserTag = "browsers", myChrome = "chrome",
			mySafari = "safari", myEdge = "edge", myIE = "IE",
			myFirefox = "fiefox", myPlatformCapability = "platform",
			myVersionCapability = "version",
			myScreenResolutionCapability = "screenresolution";
	// Configuration for test on Windows 10 / Mac Platform
	public static String myPlatform = "Windows 10";
	public static String myScreenResolution = "2560x1600";
	public static String myMobileScreenResolution = "800x600";
	public static String myMacPlatform = "OS X 10.11";
	public int totalNumberOfPlatforms;
	public int numberOfPlatformsExecuted;
	DesiredCapabilities myBrowserCapability = new DesiredCapabilities();
	Map<String, String[]> selectors;
	HashMap<String, List<String>> testCasesWithSteps;
	public MSGOnlineDigitalGlobalParameters(WebDriver driver,
			Map<String, String[]> desiredSelectors,
			HashMap<String, List<String>> myTestCasesWithSteps) {
		super(driver);
		selectors = desiredSelectors;
		testCasesWithSteps = myTestCasesWithSteps;
		// TODO Auto-generated constructor stub
	}
	public MSGOnlineDigitalGlobalParameters(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public MSGOnlineDigitalGlobalParameters() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public List<String> getDriverParameters() {
		List<String> myPlatforms = new ArrayList<String>();

		JsonElement myJSONElement = new JsonParser()
				.parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
		JsonArray myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();

		for (int j = 0; j < myJSONPlatforms.size(); j++) {
			String myPlatform = null, myOS = null, myBrowser = null,
					myBrowserVersion = null, mySLURL = null;

			for (Entry<String, JsonElement> myelem : myJSONPlatforms.get(j)
					.getAsJsonObject().entrySet()) {
				// 1. Parse the JSON of All SauceLabs Environment variables
				if (myelem.getKey().toString().equals("platform")) {
					myPlatform = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("os")) {
					myOS = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("browser")) {
					myBrowser = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("browser-version")) {
					myBrowserVersion = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("url")) {
					mySLURL = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}

				if (myPlatform != null && myOS != null && myBrowser != null
						&& myBrowserVersion != null && mySLURL != null) {
					myPlatforms.add("Platform = " + myPlatform + ", OS = "
							+ myOS + ", Browser = " + myBrowser
							+ ", BrowserVersion = " + myBrowserVersion);
				}
			}
		}
		return myPlatforms;
	}

	public DesiredCapabilities executeRemoteWebDriverInSauceLabsViaJenkins(
			String desktopOrMobile, String testCaseName, String testCase,
			Map<String, String[]> selectors,
			HashMap<String, List<String>> testCasesWithSteps)
			throws InterruptedException {
		// System.out.println(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
		JsonElement myJSONElement = new JsonParser()
				.parse(System.getenv("SAUCE_ONDEMAND_BROWSERS").toString());
		JsonArray myJSONPlatforms = (JsonArray) myJSONElement.getAsJsonArray();
		int myDriverCount = 1;
		numberOfPlatformsExecuted = 0;
		totalNumberOfPlatforms = myJSONPlatforms.size();
		for (int j = 0; j < myJSONPlatforms.size(); j++) {
			String myPlatform = null, myOS = null, myBrowser = null,
					myBrowserVersion = null, mySLURL = null, myDevice = null,
					myDeviceOrientation = null;

			for (Entry<String, JsonElement> myelem : myJSONPlatforms.get(j)
					.getAsJsonObject().entrySet()) {
				// 1. Parse the JSON of All SauceLabs Environment variables
				if (myelem.getKey().toString().equals("platform")) {
					myPlatform = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("os")) {
					myOS = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("browser")) {
					myBrowser = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("browser-version")) {
					myBrowserVersion = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("url")) {
					mySLURL = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				// Mobile specific parameters.
				if (myelem.getKey().toString().equals("device")) {
					myDevice = myelem.getValue().toString().substring(1,
							myelem.getValue().toString().length() - 1);
				}
				if (myelem.getKey().toString().equals("device-orientation")) {
					myDeviceOrientation = myelem.getValue().toString()
							.substring(1,
									myelem.getValue().toString().length() - 1);
				}

				if (myPlatform != null && myOS != null && myBrowser != null
						&& myBrowserVersion != null && mySLURL != null) {
					// 2. Once we get all variables then we can figure out the
					// respective driver to be triggered.
					if (myBrowser.equals("firefox")) {
		    				myBrowserCapability = DesiredCapabilities.firefox();
		    				myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Firefox Version: "+myBrowserVersion); 
		    				myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
		    				myBrowserCapability.setCapability("screenResolution","2360x1770");   
		    				myBrowserCapability.setCapability("version", myBrowserVersion);
		    				myBrowserCapability.setCapability("platform", "macOS 10.12");
		    				myBrowserCapability.setCapability("maxDuration","10799");             
		    				myBrowserCapability.setCapability("commandTimeout","599");

						WebDriver driver = null;
						if (desktopOrMobile.equalsIgnoreCase("desktop")) {
							Thread myTestExecution = new MSGSalesCenterRegressionScript(
									driver,
									"Platform: " + myPlatform
											+ ": Firefox Version: "
											+ myBrowserVersion,
									myBrowserCapability, testCase, selectors,
									testCasesWithSteps);
							myTestExecution.start();
						}

					}

					if (myBrowser.equals("chrome")) {
						myBrowserCapability = DesiredCapabilities.chrome();
						myBrowserCapability.setBrowserName(myBrowser);
						myBrowserCapability.setCapability("name",
								testCaseName + " - Platform: " + myPlatform
										+ ": Chrome Version: "
										+ myBrowserVersion);
	 	    	        		myBrowserCapability.setCapability("screenResolution","2360x1770");   
	 	    	        		myBrowserCapability.setCapability("version", myBrowserVersion);
	 	    	        		myBrowserCapability.setCapability("platform", "macOS 10.12");
	 	    	        		myBrowserCapability.setCapability("maxDuration","10799");             
	 	    	        		myBrowserCapability.setCapability("commandTimeout","599");
						WebDriver driver = null;
						if (desktopOrMobile.equalsIgnoreCase("desktop")) {
							Thread myTestExecution = new MSGSalesCenterRegressionScript(
									driver,
									"Platform: " + myPlatform
											+ ": Chrome Version: "
											+ myBrowserVersion,
									myBrowserCapability, testCase, selectors,
									testCasesWithSteps);
							myTestExecution.start();
						}
					}
					if (myBrowser.equals("safari")) {
		    				myBrowserCapability = DesiredCapabilities.safari();
		    				myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Safari Version: "+myBrowserVersion); 
		    				myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));		  
		    				myBrowserCapability.setCapability("version", myBrowserVersion);
		 	    	        /**
		 	    	         * Need to have another OS for lower version of safari9 / OS X EI Capitan
		 	    	         */
		 	    	        if (myBrowserVersion.toString().contains("9"))
		 	    	        {
		 	    	        	myBrowserCapability.setCapability("platform", "OS X 10.11");
		 	 	    	    myBrowserCapability.setCapability("screenResolution","2048x1536"); 
		 	    	        }
		 	    	        else
		 	    	        {
		 	    	        myBrowserCapability.setCapability("platform", "macOS 10.12");
		 	    	        myBrowserCapability.setCapability("screenResolution","2360x1770"); 
		 	    	        }
		 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
		 	    	        myBrowserCapability.setCapability("commandTimeout","599");
	 	    	        WebDriver driver = null;
						if (desktopOrMobile.equalsIgnoreCase("desktop")) {
							Thread myTestExecution = new MSGSalesCenterRegressionScript(
									driver,
									"Platform: " + myPlatform
											+ ": Safari Version: "
											+ myBrowserVersion,
									myBrowserCapability, testCase, selectors,
									testCasesWithSteps);
							myTestExecution.start();
						}
					}
					if (myBrowser.equals("microsoftedge")) {
			    			myBrowserCapability = DesiredCapabilities.edge();
		 	    			//myBrowserCapability.setBrowserName(myBrowser);
		 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": Edge Version: "+myBrowserVersion); 
		 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));		
		 	    	        myBrowserCapability.setCapability("screenResolution","2560x1600");   
		 	    	        //myBrowserCapability.setCapability("version", myBrowserVersion);
		 	    	        switch (myBrowserVersion) {
						case "13":
		 	    	        myBrowserCapability.setCapability("version", "13.10586");
						case "14":
		 	    	        myBrowserCapability.setCapability("version", "14.14393");
						case "15":
		 	    	        myBrowserCapability.setCapability("version", "15.15063");
						case "latest":
		 	    	        myBrowserCapability.setCapability("version", "15.15063");
						default:
		 	    	        myBrowserCapability.setCapability("version", "15.15063");
		 	    	        }
		 	    	        myBrowserCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 	    	       	myBrowserCapability.setCapability("trustallSSLcertificates", true);
		 	    	        myBrowserCapability.setCapability("acceptSslCerts", true);
		 	    	        myBrowserCapability.setCapability("avoidProxy", true);
		 	    	        myBrowserCapability.setCapability("platform", "Windows 10");
		 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
		 	    	        myBrowserCapability.setCapability("commandTimeout","599");
						WebDriver driver = null;
						if (desktopOrMobile.equalsIgnoreCase("desktop")) {
							Thread myTestExecution = new MSGSalesCenterRegressionScript(
									driver,
									"Platform: " + myPlatform
											+ ": Edge Version: "
											+ myBrowserVersion,
									myBrowserCapability, testCase, selectors,
									testCasesWithSteps);
							myTestExecution.start();
						}
					}
					if (myBrowser.equals("internet explorer")) {
			    			myBrowserCapability = DesiredCapabilities.internetExplorer();
		 	    			//myBrowserCapability.setBrowserName(myBrowser);
		 	    			myBrowserCapability.setCapability("name", testCaseName + " - Platform: "+myPlatform+": IE Version: "+myBrowserVersion); 
		 	    			myBrowserCapability.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));
		 	    	        myBrowserCapability.setCapability("screenResolution","2560x1600");   
		 	    	        myBrowserCapability.setCapability("version", myBrowserVersion);
		 	    	        myBrowserCapability.setCapability("platform", "Windows 7");
		 	    	        myBrowserCapability.setCapability("maxDuration","10799");             
		 	    	        myBrowserCapability.setCapability("commandTimeout","599"); 
		 	    	        myBrowserCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 	    	       	myBrowserCapability.setCapability("trustallSSLcertificates", true);
		 	    	        myBrowserCapability.setCapability("acceptSslCerts", true);
		 	    	        myBrowserCapability.setCapability("avoidProxy", true);
		 	    	       
						WebDriver driver = null;
						if (desktopOrMobile.equalsIgnoreCase("desktop")) {
							Thread myTestExecution = new MSGSalesCenterRegressionScript(
									driver,
									"Platform: " + myPlatform + ": IE Version: "
											+ myBrowserVersion,
									myBrowserCapability, testCase, selectors,
									testCasesWithSteps);
							myTestExecution.start();
						}
					}
					/**
					 * We will create the Mobile capabilities here.
					 */
					if (myDevice != null && myDeviceOrientation != null) {
						// 2. Once we get all variables then we can figure out
						// the respective driver to be triggered.
						if (myBrowser.equals("iphone")) {
							DesiredCapabilities myBrowserCapability = DesiredCapabilities
									.iphone();
							myBrowserCapability.setBrowserName(myBrowser);
							myBrowserCapability.setCapability("maxDuration",
									"10799");
							myBrowserCapability.setCapability("commandTimeout",
									"599");
							// myBrowserCapability.setCapability("idleTimeout","1000");
							myBrowserCapability.setCapability("name",
									testCaseName + " - " + myDevice);
							myBrowserCapability.setCapability(
									"browser.safebrowsing.phishing.enabled;",
									"true");
							// myBrowserCapability.setCapability("build",
							// myBuildNumber);
							myBrowserCapability.setCapability("build",
									System.getenv("JOB_NAME") + "__"
											+ System.getenv("BUILD_NUMBER"));
							myBrowserCapability.setCapability("public",
									"public");
							myBrowserCapability.setCapability("browserVersion",
									myBrowserVersion);
							myBrowserCapability.setCapability("os", myOS);
							// myBrowserCapability.setCapability("appiumVersion",
							// "1.6.3");
							myBrowserCapability.setCapability("deviceName",
									myDevice);
							myBrowserCapability.setCapability(
									"deviceOrientation", myDeviceOrientation);
							myBrowserCapability.setCapability(
									CapabilityType.PLATFORM, myPlatform);
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/13/2017
							 */
							myBrowserCapability.setCapability(
									"locationServicesEnabled", false);
							myBrowserCapability.setCapability(
									"locationServicesAuthorized", false);
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/14/2017
							 */	
							myBrowserCapability.setCapability(
									"autoAcceptAlerts", true);
							WebDriver driver = null;
							if (desktopOrMobile.equalsIgnoreCase("mobile")) {
								Thread myTestExecution = new MSGSalesCenterRegressionScript(
										driver,
										"Device: " + myDevice + " Orientation: "
												+ myDeviceOrientation
												+ " Safari Version: "
												+ myBrowserVersion,
										myBrowserCapability, testCase,
										selectors, testCasesWithSteps);
								myTestExecution.start();
							}
						}

						if (myBrowser.equals("ipad")) {
							DesiredCapabilities myBrowserCapability = DesiredCapabilities
									.ipad();
							myBrowserCapability.setBrowserName(myBrowser);
							myBrowserCapability.setCapability("maxDuration",
									"10799");
							myBrowserCapability.setCapability("commandTimeout",
									"599");
							// myBrowserCapability.setCapability("idleTimeout","1000");
							myBrowserCapability.setCapability("name",
									testCaseName + " - " + myDevice);
							myBrowserCapability.setCapability(
									"browser.safebrowsing.phishing.enabled;",
									"true");
							// myBrowserCapability.setCapability("build",
							// myBuildNumber);
							myBrowserCapability.setCapability("build",
									System.getenv("JOB_NAME") + "__"
											+ System.getenv("BUILD_NUMBER"));
							myBrowserCapability.setCapability("public",
									"public");
							myBrowserCapability.setCapability("browserVersion",
									myBrowserVersion);
							myBrowserCapability.setCapability("os", myOS);
							// myBrowserCapability.setCapability("appiumVersion",
							// "1.6.3");
							myBrowserCapability.setCapability("deviceName",
									myDevice);
							myBrowserCapability.setCapability(
									"deviceOrientation", myDeviceOrientation);
							myBrowserCapability.setCapability(
									CapabilityType.PLATFORM, myPlatform);
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/13/2017
							 */
							myBrowserCapability.setCapability(
									"locationServicesEnabled", false);
							myBrowserCapability.setCapability(
									"locationServicesAuthorized", false);
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/14/2017
							 */	
							myBrowserCapability.setCapability(
									"autoAcceptAlerts", true);
							WebDriver driver = null;
							if (desktopOrMobile.equalsIgnoreCase("mobile")) {
								Thread myTestExecution = new MSGSalesCenterRegressionScript(
										driver,
										"Device: " + myDevice + " Orientation: "
												+ myDeviceOrientation
												+ " Safari Version: "
												+ myBrowserVersion,
										myBrowserCapability, testCase,
										selectors, testCasesWithSteps);
								myTestExecution.start();
							}
						}

						if (myBrowser.equals("android")) {
							DesiredCapabilities myBrowserCapability = DesiredCapabilities
									.android();
							myBrowserCapability.setBrowserName(myBrowser);
							myBrowserCapability.setCapability("maxDuration",
									"10799");
							myBrowserCapability.setCapability("commandTimeout",
									"599");
							myBrowserCapability.setCapability("idleTimeout",
									"1000");
							myBrowserCapability.setCapability("name",
									testCaseName + " - " + myDevice);
							myBrowserCapability.setCapability(
									"browser.safebrowsing.phishing.enabled;",
									"true");
							// myBrowserCapability.setCapability("build",
							// myBuildNumber);
							myBrowserCapability.setCapability("build",
									System.getenv("JOB_NAME") + "__"
											+ System.getenv("BUILD_NUMBER"));
							myBrowserCapability.setCapability("public",
									"public");
							myBrowserCapability.setCapability("browserVersion",
									myBrowserVersion);
							myBrowserCapability.setCapability("os", myOS);
							// myBrowserCapability.setCapability("appiumVersion",
							// "1.5.3");
							myBrowserCapability.setCapability("deviceName",
									myDevice);
							myBrowserCapability.setCapability(
									"deviceOrientation", myDeviceOrientation);
							myBrowserCapability.setCapability(
									CapabilityType.PLATFORM, myPlatform);
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/13/2017
							 */
							myBrowserCapability.setCapability(
									"locationServicesEnabled", true);	
							myBrowserCapability.setCapability(
									"locationServicesAuthorized", true);	
							/**
							 * For handling location pop-up
							 * change done by Rachit Kumar Rastogi on 11/14/2017
							 */	
							myBrowserCapability.setCapability(
									"autoAcceptAlerts", true);
							WebDriver driver = null;
							if (desktopOrMobile.equalsIgnoreCase("mobile")) {
								Thread myTestExecution = new MSGSalesCenterRegressionScript(
										driver,
										"Device: " + myDevice + " Orientation: "
												+ myDeviceOrientation
												+ " Safari Version: "
												+ myBrowserVersion,
										myBrowserCapability, testCase,
										selectors, testCasesWithSteps);
								myTestExecution.start();
							}
						}

					}
				}
			}
			System.out.println("Test(" + myDriverCount
					+ ") Parameters are: Platform = " + myPlatform + ", OS = "
					+ myOS + ", Browser = " + myBrowser + ", BrowserVersion = "
					+ myBrowserVersion);
			myDriverCount++;
		}
		return myBrowserCapability;
	}

	public void clickE(ExtendedWebElement eWE) {
		click(eWE);
	}

	public String getCurrentTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

		// method 1
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// System.out.println(timestamp);

		// method 2 - via Date
		Date date = new Date();
		// System.out.println(new Timestamp(date.getTime()));

		// return number of milliseconds since January 1, 1970, 00:00:00 GMT
		// System.out.println(timestamp.getTime());

		// format timestamp
		// System.out.println(sdf.format(timestamp));

		return timestamp.toString();

	}

	public HashMap<String, String> convertHashMapStringToHashMap(
			String stringOfHashMap) throws IOException {
		String str = stringOfHashMap;
		Properties props = new Properties();
		props.load(new StringReader(
				str.substring(1, str.length() - 1).replace(", ", "\n")));
		HashMap<String, String> mobileTestCasesResultsHashMap = new HashMap<String, String>();
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			mobileTestCasesResultsHashMap.put((String) e.getKey(),
					(String) e.getValue());
		}
		return mobileTestCasesResultsHashMap;
	}

	public HashMap<String, List<String>> getFolderContents(String folderPath) {
		List<String> fileList = new ArrayList<>();
		List<String> folderList = new ArrayList<>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				fileList.add(listOfFiles[i].getName().toString());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
				folderList.add(listOfFiles[i].getName().toString());
			}
		}
		HashMap<String, List<String>> folderContents = new HashMap<String, List<String>>();
		folderContents.put("files", fileList);
		folderContents.put("folders", folderList);
		return folderContents;
	}
}