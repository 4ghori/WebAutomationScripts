package com.msg.suites.pom.util;

import java.util.Hashtable;

public class FBConstants {
	public static final boolean GRID_RUN = true;
	
	//paths
	//public static final String CHROME_DRIVER_EXE="F:\\chromedriver.exe";
	public static final String CHROME_DRIVER_EXE=System.getProperty("user.dir")+"//chromedriver";
	public static final String Gecko_DRIVER_EXE=System.getProperty("user.dir")+"//geckodriver";
                                              
	
	// locators
	public static final String Suit_QA_LOGINField = "//div[@id='login-username']/input";
	public static final String Suit_QA_PasswordField = "//div[@id='login-password']/input";
	public static final String Suit_QA_LoginButton = "//*[@id='login-button']/span";
	public static final String Suit_QA_LogoutLink = "//*[@id='client-tabs']/div/div[1]/div/span";
	public static final String Suit_QA_MyAccountTab = "//*[@id='myAccountTab']/span";
	
	public static final String LOGIN_USERNAME = "//*[@id='email']";
	public static final String LOGIN_PASSWORD = "//*[@id='pass']";
	public static final String PROFILEPAGE_LINK = "//*[@id='pagelet_welcome_box']/ul/li[1]/div/a";
	public static final String NAVIGATION_LABEL = "html/body/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div[3]/div[2]/div/div/a";
	public static final String SETTINGS_LINK = "//span[text()='Settings']";
	public static final String PASSWORD_CHANGE = "//*[@id='SettingsPage_Content']/ul/li[4]/a";
	public static final String OLD_PASSWORD = "//*[@id='password_old']";
	public static final String NEW_PASSWORD = "//*[@id='password_new']";
	public static final String CONFIRM_CHANGE = "//*[@id='password_confirm']";
	public static final String SAVE_CHANGES = "//label[@class='submit uiButton uiButtonConfirm']";
	public static final String KILL_SESSION = "//input[@value='kill_sessions']";
	public static final String CONTINUE_PASSWORD_CHANGE_BUTTON = "//button[text()='Continue']";
	
	// URLs-prod
	public static final String PROD_HOMEPAGE_URL = "http://facebook.com";
	public static final String PROD_USERNAME = "its.thakur@gmail.com";
	public static final String PROD_PASSWORD = "Ashish@789";
	
	// URLs-uat
	public static final String UAT_HOMEPAGE_URL = "http://uat.facebook.com";
	public static final String UAT_USERNAME = "uat_its.thakur@gmail.com";
	public static final String UAT_PASSWORD = "uat_Ashish@123";
	
	
	//&&&&&&& URLs-Suit QA
	public static final String Suit_QA_ADMIN_URL = "https://qa.mymsgsuites.com/#admin";
		public static final String Suit_DefaultAdminUSERNAME = "starwars@mailinator.com";
		public static final String Suit_DefaultAdminPASSWORD = "Suites123";
	
	public static final String Suit_QA_Client_URL = "https://qa.mymsgsuites.com/";
		public static final String Suit_DefaultClientUSERNAME = "ironman@mailinator.com";
		public static final String Suit_DefaultClientPASSWORD = "Suites123";

	//&&&&&&& URLs-Suit UAT
		public static final String Suit_UAT_ADMIN_URL = "https://newuat.mymsgsuites.com/#admin";
			public static final String Suit_UATDefaultAdminUSERNAME = "starwars@mailinator.com";
			public static final String Suit_UATDefaultAdminPASSWORD = "Suites12";                          ////// Password Should Suite 123
		
		public static final String Suit_UAT_Client_URL = "https://newuat.mymsgsuites.com/";
			public static final String Suit_UATDefaultClientUSERNAME = "ironman@mailinator.com";
			public static final String Suit_UATDefaultClientPASSWORD = "Suites12";
		
	//public static final String ENV="SUIT_QA"; //SUIT_QA  or SUIT_UAT
			public static  String ENV="SUIT_QA"; //SUIT_QA  or SUIT_UAT


			
	//*************  M A R Q U E  E  - Marquee URLS		
			public static final String Marquee_DEV_URL = "https://dev.msgmarqueeathome.com/";
			public static final String Marquee_QA_URL = "https://qa.msgmarqueeathome.com/";
			public static final String Marquee_PROD_URL = "https://msgmarqueeathome.com/";
		
			
	//*************  E V E N T  - Event Engine URLS 		
			public static final String EventEngine_DEV_URL = "http://dev-api.msg.com/login/";
			public static final String EventEngine_QA_URL = "http://qa-api.msg.com/login/";
			public static final String EventEngine_Staging_URL = "http://staging-api.msg.com/login/";
			public static final String EventEngine_Prod_URL = "http://api.msg.com/login/";

	//paths for Windows 
		//public static final String REPORTS_PATH = "D:\\reports\\";
		//public static final String REPORTS_ZipPATH = System.getProperty("user.dir")+"/reportszip/";
		//public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\data\\Data.xlsx";
	
	//path for MAC / Unix 
		public static final String REPORTS_PATH = System.getProperty("user.dir")+"//reports//";
		public static final String REPORTS_ZipPATH = System.getProperty("user.dir")+"/reportszip/";

		public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"//data//Data.xlsx";
	
	
		public static final String TESTDATA_SHEET = "TestData";
		public static final Object RUNMODE_COL = "Runmode";
		public static final String TESTCASES_SHEET = "TestCases";
	
	public static Hashtable<String,String> table;
	
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null)
		{
			table = new Hashtable<String,String>();
			
			//****** EE URLS 
			if(ENV.equals("EventEngine_DEV"))
			{
				table.put("EventEngine_DEV_URL", Marquee_DEV_URL);
			}
			else if(ENV.equals("EventEngine_QA"))
			{
				table.put("EventEngine_QA_URL", EventEngine_QA_URL);
			}
			else if(ENV.equals("EventEngine_Staging"))
			{
				table.put("EventEngine_Staging_URL", EventEngine_Staging_URL);
			}
			else if(ENV.equals("EventEngine_Prod"))
			{
				table.put("EventEngine_Prod_URL", EventEngine_Prod_URL);
			}
		}
		return table;
	}

}
