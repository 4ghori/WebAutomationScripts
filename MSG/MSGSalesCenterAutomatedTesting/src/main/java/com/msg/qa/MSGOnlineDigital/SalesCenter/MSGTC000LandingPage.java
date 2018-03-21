package com.msg.qa.MSGOnlineDigital.SalesCenter;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElements;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.isIOSPlatform;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.isIpad;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.pressESC;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollToElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollUp;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.sleep;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testLinkNewTab;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testText;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testVisual;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.waitForElement;

/**
 * Page Class for msg.com landing page
 *
 * @author Carlos Campino
 * @category MSG.com Landing Page
 */

public class MSGTC000LandingPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC000LandingPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	private static SoftAssert softAssert = new SoftAssert();
	private boolean isMobile;
	private boolean isIpad;
	private String platform;
	String myDriverParameters = null;
	
	/**
	 * Marked as false hard-coded on 03/19/2018 by Rachit Kumar Rastogi
	 * as Cron Job is not consdering the Check parameters - Sync with Gupta for more details.
	 */
	//public static String displayDetailedLogs = System.getProperty("displayDetailedLogs");
	public static String displayDetailedLogs = "false";
	
	/**
	 * Default Constructor
	 */
	public MSGTC000LandingPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGTC000LandingPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		this.driver = driver;
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		this.platform = capabilities.getCapability("platformName") != null ?
				capabilities.getCapability("platformName").toString() :
				capabilities.getPlatform().toString();
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = MSGOnlineDigitalReusableFunctionalities
				.isMobilePlatform(this.platform)
				&& !driverParameters.contains("landscape")
				&& !MSGOnlineDigitalReusableFunctionalities.isIEBrowser(capabilities.getBrowserName());
		this.isIpad = MSGOnlineDigitalReusableFunctionalities
				.isIpad(capabilities.getPlatform().toString());
		
		if(displayDetailedLogs.matches("true")){ LOGGER.info(" >>>> CAPABILITIES::"+capabilities.getPlatform().toString()); }
		
		/**
		 * Adding Driver Parameters for Logging Purpose in Logger.
		 */
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG.com Landing Page.
	 */
	@Test(description = "Are we on MSG .com Landing Page?", groups = {
			"MSGLandingPage", "smoke", "fullintegration"})
	public boolean MSGTC000LandingPageTS001() {

		if(displayDetailedLogs.matches("true")){ LOGGER.info(this.myDriverParameters+
				" - MSG.com Landing Page: Test Step-1: Verify Page Opens.");}
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG.com Landing  Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			if (isIOSPlatform(((RemoteWebDriver) driver).getCapabilities()
					.getPlatform().toString())) {
				/*
				 * WebElement locationAlertElement = getWebElement(driver,
				 * selectors, "MSGTC000LandingPageLocationAlert");
				 * locationAlertElement.click();
				 */

				LOGGER.info(this.myDriverParameters+
						" - MSG.com Landing Page:  Test Step-1: We are auto accepting the Location Alert.");
			}
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com Landing Page:  Test Step-1: We are on MSG.com Landing Page.");
		} catch (WebDriverException e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG.com Landing Page:  Test Step-1: We are not on MSG.com Landing Page!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			isMyTestPassed = false;
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: TOP ADVERTISEMENT
	 */
	@Test(description = "MSG.com - Landing Page. Top Advertisement", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS002() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "2";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Top Advertisement.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			// The Ad takes a long time to load
			sleep(15);
			WebElement heroTopAd = getWebElement(driver, selectors,
					"MSGTC000LandingPageHeroTopAd");
			isMyTestPassed = true;

			waitForElement(driver, heroTopAd, 15);
			// Validates the Hero Top Ad
			return isMyTestPassed = testVisual(heroTopAd, pageName,
					"Hero Top Ad", testNumber, isMyTestPassed, this.myDriverParameters);

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Hero Top Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}
	
	/**
	 * Step-3: Global NAV Verifications.
	 */
	@Test(description = "MSG.com - Landing Page. Global Nav Tests", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS003() 
	{
		String pageName = "MSG.com - Landing Page";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Global Nav Tests.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement msgLogo = getWebElement(driver,
					selectors, "MSGTC000LandingPageLogo");
/*			List<WebElement> graphicTickets = getWebElements(driver,
					selectors, "MSGTC000LandingPageGlobalNAVEventsTicketsFindTicketsGraphicOnes");
			List<WebElement> exploreTickets = getWebElements(driver,
					selectors, "MSGTC000LandingPageGlobalNAVEventsTicketsFindTicketsExplore");
			WebElement msgEventTickets = getWebElement(driver,
					selectors, "MSGTC000LandingPageGlobalNAVEventsTickets");*/

			if (msgLogo.isDisplayed())
			{
			LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG logo is displayed!!");
			}
			
/*			if (msgEventTickets.isDisplayed())
			{
				msgEventTickets.click();
				Thread.sleep(3000);
			}
			
			for (WebElement myEle:graphicTickets)
			{
				myEle.click();
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": URL we navigated to: "+driver.getCurrentUrl());
				driver.navigate().back();
			}
			
			for (WebElement myEle:exploreTickets)
			{
				myEle.click();
			}
*/			
			/**
			 * Search Feature Tests are in below piece of code
			 * By Rachit Kumar Rastogi On 01/19/2018
			 */
			WebElement msgSearch = getWebElement(driver,
					selectors, "MSGTC000LandingPageGlobalNAVSearch");
			WebElement msgSearchInput = getWebElement(driver,
					selectors, "MSGTC000LandingPageGlobalNAVSearchInput");
			WebElement msgSearchClose = getWebElement(driver,
					selectors, "MSGTC000LandingPageGlobalNAVSearchClose");
			WebElement msgSearchClick = getWebElement(driver,
					selectors, "MSGTC000LandingPageGlobalNAVSearchClick");
			
			/**
			 * Perform Open/Close in Search in Nav
			 */			
			if(msgSearch.isDisplayed())
			{
				msgSearch.click();				
				if(msgSearchInput.isDisplayed() && msgSearchClose.isDisplayed())
				{
					msgSearchClose.click();
				}
			}
			
			Thread.sleep(2000);
			
			/**
			 * Valid input details for the search feature
			 */			
			if(msgSearch.isDisplayed())
			{
				msgSearch.click();			
				if(msgSearchInput.isDisplayed() && msgSearchClick.isDisplayed())
				{
					/**
					 * Partial search string Madison.
					 */
					msgSearchInput.sendKeys("Madison");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Madison is entered in search.");
					msgSearchClick.click();
					Thread.sleep(2000);
					
					WebElement msgSearchTotalEventCount = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickTotalEventCount");
					List<WebElement> msgSearchIndivEventCount = getWebElements(driver,
									selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickIndivEventCount");
					
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Total "+msgSearchTotalEventCount.getText()+" search string.");
					
					int count = 0;
					for(WebElement myEle: msgSearchIndivEventCount)
					{
						if (count > 0)
						{
							LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: "+myEle.getText()+" are present in search results.");
						}
					count++;
					}
					
					WebElement msgSearchAfterInput = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterInput");
					WebElement msgSearchAfterClick = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClick");
					
					/**
					 * Partial search string Madison Square.
					 */
					msgSearchAfterInput.clear();
					msgSearchAfterInput.sendKeys("Madison Square");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Madison Square is entered in search.");
					msgSearchAfterClick.click();
					Thread.sleep(2000);
					
					msgSearchTotalEventCount = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickTotalEventCount");
					msgSearchIndivEventCount = getWebElements(driver,
									selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickIndivEventCount");
					
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Total "+msgSearchTotalEventCount.getText()+" search string.");
					
					count = 0;
					for(WebElement myEle: msgSearchIndivEventCount)
					{
						if (count > 0)
						{
							LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: "+myEle.getText()+" are present in search results.");
						}
					count++;
					}
					
					/**
					 * Partial search string Madison Square Garden.
					 */
					msgSearchAfterInput.clear();
					msgSearchAfterInput.sendKeys("Madison Square Garden");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Madison Square Garden is entered in search.");
					Thread.sleep(3000);
					msgSearchAfterClick.click();
					Thread.sleep(2000);		
					
					msgSearchTotalEventCount = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickTotalEventCount");
					msgSearchIndivEventCount = getWebElements(driver,
									selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickIndivEventCount");
					
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Total "+msgSearchTotalEventCount.getText()+" search string.");
					count = 0;
					for(WebElement myEle: msgSearchIndivEventCount)
					{
						if (count > 0)
						{
							LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: "+myEle.getText()+" are present in search results.");
						}
					count++;
					}
					
					/**
					 * search with Invalid string.
					 */
					msgSearchAfterInput.clear();
					msgSearchAfterInput.sendKeys("Not_a_Valid_String");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Not_a_Valid_String is entered in search.");
					msgSearchAfterClick.click();
					Thread.sleep(2000);
					
					WebElement msgSearchNoEventMessage = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickNoEventMessage");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Message - "+msgSearchNoEventMessage.getText()+" for Not_a_Valid_String search string.");					
					
					/**
					 * search with Blank string.
					 */
					/**
					 * Commented after it caused Dumps in Safari Browser on 02/14/2018.
					 */
/*					msgSearchAfterInput.clear();
					msgSearchAfterInput.sendKeys("");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: BLANK is entered in search.");
					msgSearchAfterClick.click();
					Thread.sleep(2000);
					
					WebElement msgSearchBlankMessage = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickNoEventMessage");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Message - "+msgSearchBlankMessage.getText()+" for BLANK search string.");	*/				
				
					/**
					 * search with a Lengthy String.
					 */
					msgSearchAfterInput.clear();
					msgSearchAfterInput.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. is entered in search.");
					msgSearchAfterClick.click();
					Thread.sleep(2000);
					msgSearchTotalEventCount = getWebElement(driver,
							selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickTotalEventCount");
					msgSearchIndivEventCount = getWebElements(driver,
									selectors, "MSGTC000LandingPageGlobalNAVSearchAfterClickIndivEventCount");
					LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: Total "+msgSearchTotalEventCount.getText()+" search string.");					
					count = 0;
					for(WebElement myEle: msgSearchIndivEventCount)
					{
						if (count > 0)
						{
							LOGGER.info(this.myDriverParameters+" - MSG.com Landing Page:  Test Step-3: "+myEle.getText()+" are present in search results.");
						}
					count++;
					}
				}
			}
					
			isMyTestPassed = true;
			} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Global Nav error!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}			
		return isMyTestPassed;		
	}
	
	
	/**
	 * Step-4. Verify the Hero Section: IMAGE SLIDER UNDER GLOBAL NAV
	 */
	@Test(description = "MSG.com - Landing Page. Hero Slider Tests", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS004() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Hero Slider Tests.");
		/**
		 * Going back to parent URL after search in earlier test case.
		 */
		driver.navigate().to(this.URL);
		
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			List<WebElement> heroSliderSlides = getWebElements(driver,
					selectors, "MSGTC000LandingPageHeroSliderSlides");
			isMyTestPassed = true;

			// Validates the Hero Slider Slides, Detail Other Venue Slides are
			// displayed, each with image and title
			if (heroSliderSlides.size() > 0) {
				for (int countCards = 1; countCards <= heroSliderSlides
						.size(); countCards++) {
					WebElement heroSliderEventImage = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageHeroSliderEventImage",
							Integer.toString(countCards));
					WebElement heroSliderEventName = getWebElement(driver,
							selectors, "MSGTC000LandingPageHeroSliderEventName",
							Integer.toString(countCards));
					WebElement heroSliderEventDate = getWebElement(driver,
							selectors, "MSGTC000LandingPageHeroSliderEventDate",
							Integer.toString(countCards));
					WebElement heroSliderEventLocation = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageHeroSliderEventLocation",
							Integer.toString(countCards));
					WebElement heroSliderEventBuyTickets = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageHeroSliderEventBuyTickets",
							Integer.toString(countCards));
					WebElement heroSliderEventSelector = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageHeroSliderEventSelector",
							Integer.toString(countCards));

					// Access the required slide
					scrollToElement(driver, heroSliderEventSelector);
					scrollUp(driver);
					heroSliderEventSelector.click();
					sleep(1);

					isMyTestPassed = testVisual(heroSliderEventImage, pageName,
							"Hero Slider Event Image card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(heroSliderEventName, pageName,
							"Hero Slider Event Name card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(heroSliderEventDate, pageName,
							"Hero Slider Event Date card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(heroSliderEventLocation, pageName,
							"Hero Slider Event Location card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(heroSliderEventBuyTickets,
							pageName,
							"Hero Slider Event Buy Tickets card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testVisual(heroSliderEventSelector,
							pageName,
							"Hero Slider Event Selector card: " + countCards,
							testNumber, isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Hero Slider, Slides are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Hero Slider, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-5. Verify the Hero Section: EVENT SEARCH OPTIONS
	 */
	@Test(description = "MSG.com - Landing Page. Hero Filters Tests", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS005() {

		LOGGER.info(this.myDriverParameters+
				" - MSG.com - Landing Page: Test Step-5: Verify Hero Section.");
		
		/**
		 * Going back to parent URL after search in earlier test case.
		 */
		driver.navigate().to(this.URL);
		
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			List<WebElement> heroFiltersTypeList = getWebElements(driver, selectors,
					"MSGTC000LandingPageHeroFiltersType");
			
			int count = 0;
			
			for(WebElement myEle: heroFiltersTypeList)
			{
				if (count == 0)
				{
					WebElement heroFiltersType = myEle;	
					// Validates the Hero Filter Type
					if (heroFiltersType.isDisplayed()
							& heroFiltersType.getText() != null) {
						LOGGER.info(this.myDriverParameters+
								" - MSG.com - Landing Page: Test Step-5: Second Line for Hero Filter Type is present");
					} else {
						isMyTestPassed = false;
					}
				}
				
				if (count == 1)
				{
					WebElement heroFiltersHappening = myEle;	
					// Validates the Hero Filter Happening
					if (heroFiltersHappening.isDisplayed()
							& heroFiltersHappening.getText() != null) {
						LOGGER.info(this.myDriverParameters+
								" -  MSG.com - Landing Page: Test Step-5: Hero Filter Happening is present");
					} else {
						isMyTestPassed = false;
					}
				}
				
				if (count == 2)
				{
					WebElement heroFiltersLocation = myEle;	
					// Validates the Hero Filter Location
					if (heroFiltersLocation.isDisplayed()
							& heroFiltersLocation.getText() != null) {
						LOGGER.info(this.myDriverParameters+
								" - MSG.com - Landing Page: Test Step-5: Hero Filter Location is present");
					} else {
						isMyTestPassed = false;
					}
				}
				count++;
			}
			
/*			WebElement heroFiltersHappening = getWebElement(driver, selectors,
					"MSGTC000LandingPageHeroFiltersHappening");
			WebElement heroFiltersLocation = getWebElement(driver, selectors,
					"MSGTC000LandingPageHeroFiltersLocation");*/
			WebElement heroFiltersButton = getWebElement(driver, selectors,
					"MSGTC000LandingPageHeroFiltersButton");
			isMyTestPassed = true;

			// Validates the Hero Filter Button
			if (heroFiltersButton.isDisplayed()
					& heroFiltersButton.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG.com - Landing Page: Test Step-5: Hero Filter BUtton is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+
					" - MSG.com - Landing Page: Test Step-5: MSG Hero Filter Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-6: Verify the Hero Section: SECTION BETWEEN EVENT SEARCH AND HAPPENING (NEW STEP NEED TO BE CODED)
	 */
	@Test(description = "MSG.com - Landing Page. Featured Events Tests", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS006() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Featured Events Tests.");
		try {
		
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Featured Events, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
		return isMyTestPassed;

	}
	
	/**
	 * Step-7. Verify the Hero Section: FEATURE EVENT (HAPPENING IN ALL CITIES)
	 */
	@Test(description = "MSG.com - Landing Page. Featured Events Tests", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS007() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Featured Events Tests.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement featuredEventsHeader = getWebElement(driver, selectors,
					"FeaturedEventsHeader");
			WebElement featuredEventsFilter = getWebElement(driver, selectors,
					"FeaturedEventsFilter");
			WebElement featuredEventsCTA = getWebElement(driver, selectors,
					"FeaturedEventsCTA");
			WebElement featuredEventsTopResultCount = getWebElement(driver,
					selectors, "FeaturedEventsTopResultCount");
			List<WebElement> featuredEventsSlides = getWebElements(driver,
					selectors, "FeaturedEventsSlides");
			WebElement featuredEventsFilterCity = null;

			isMyTestPassed = true;

			isMyTestPassed = testText(featuredEventsHeader, pageName,
					"Featured Events Header", testNumber, isMyTestPassed, this.myDriverParameters);
			isMyTestPassed = testText(featuredEventsFilter, pageName,
					"Featured Events Filter", testNumber, isMyTestPassed, this.myDriverParameters);
			isMyTestPassed = testText(featuredEventsCTA, pageName,
					"Featured Events CTA", testNumber, isMyTestPassed, this.myDriverParameters);
			isMyTestPassed = testText(featuredEventsTopResultCount, pageName,
					"Featured Events Top Result Count", testNumber, isMyTestPassed, this.myDriverParameters);
			String cardsTopCounter = StringUtils.substringBetween(
					featuredEventsTopResultCount.getText(), "(", ")");
			System.out.println(this.myDriverParameters+" - "+cardsTopCounter);
			WebElement featuredEventsSliderCount = getWebElement(driver,
					selectors, "FeaturedEventsSliderCount");

			int cardsDisplayed;
			String cardsNumber;
			LOGGER.info(" >>> STRING::"+featuredEventsSliderCount.getText());
			LOGGER.info(" >>> platform::"+platform);
			LOGGER.info(" >>> isIpad::"+isIpad);
			LOGGER.info(" >>> isMobile::"+isMobile);

			if (featuredEventsSliderCount
					.getText().contains("of")) {
				LOGGER.info(" >>> IPAD or NOT MOBILE");
				cardsDisplayed = Integer.parseInt(featuredEventsSliderCount
						.getText().replaceAll(" ", "").substring(2, 3));
				cardsNumber = featuredEventsSliderCount.getText()
						.split(" of ")[1];
			} else {
				LOGGER.info(" >>> MOBILE");
				cardsDisplayed = Integer.parseInt(
						featuredEventsSliderCount.getText().split("/")[0]
								.trim());
				cardsNumber = featuredEventsSliderCount.getText().split("/")[1];
			}
			System.out.println(this.myDriverParameters+" - "+cardsNumber);

			scrollToElement(driver, featuredEventsFilter);
			scrollUp(driver);
			featuredEventsFilter.click();
			sleep(2);
			featuredEventsFilterCity = getWebElement(driver, selectors,
					"FeaturedEventsFilterCities", "All cities");
			isMyTestPassed = testVisual(featuredEventsFilterCity, pageName,
					"Featured Events Filter Cities Option: All cities",
					testNumber, isMyTestPassed, this.myDriverParameters);
			
			/**
			 * Reach over the entries in the Location.
			 */
			featuredEventsFilterCity.click();
			
			List<WebElement> featuredEventsFilterCityList = getWebElements(driver, selectors,
					"FeaturedEventsFilterCitiesList");
			
			/**
			 * New York is clicked in the list
			 */
			featuredEventsFilterCityList.get(1).click();
			Thread.sleep(2000);
			
			featuredEventsFilterCity = getWebElement(driver, selectors,
					"FeaturedEventsFilterCities", "New York");
			isMyTestPassed = testVisual(featuredEventsFilterCity, pageName,
					"Featured Events Filter Cities Option: New York",
					testNumber, isMyTestPassed, this.myDriverParameters);
						
			featuredEventsFilterCity = getWebElement(driver, selectors,
					"FeaturedEventsFilterCities", "Chicago");
			isMyTestPassed = testVisual(featuredEventsFilterCity, pageName,
					"Featured Events Filter Cities Option: Chicago", testNumber,
					isMyTestPassed, this.myDriverParameters);
					
			featuredEventsFilterCity = getWebElement(driver, selectors,
					"FeaturedEventsFilterCities", "Inglewood");
			isMyTestPassed = testVisual(featuredEventsFilterCity, pageName,
					"Featured Events Filter Cities Option: Inglewood",
					testNumber, isMyTestPassed, this.myDriverParameters);

			if (isIOSPlatform(this.platform)) {
				WebElement locationsMenuCloseButton = getWebElement(driver,
						selectors, "LocationsMenuCloseButton");
				waitForElement(driver, locationsMenuCloseButton, 5);
				locationsMenuCloseButton.click();

			} else {
				//Actions doesn't work on firefox
				//https://github.com/mozilla/geckodriver/issues/233
				// Close city pop up
				if(!platform.equals("darwin")) {
					pressESC(driver);
					sleep(1);
				}
			}

			// Validates the Featured Events Slider Card, Cards are displayed,
			// each with image, title, date, location and a Buy ticket
			if (featuredEventsSlides.size() > 0) {
				for (int countCards = 1; countCards <= cardsDisplayed; countCards++) {
					WebElement featuredEventsSlideType = getWebElement(driver,
							selectors, "FeaturedEventsSlideType",
							Integer.toString(countCards));
					if (featuredEventsSlideType.getAttribute("class")
							.contains("card")) {
						WebElement featuredEventsSlideImage = getWebElement(
								driver, selectors, "FeaturedEventsSlideImage",
								Integer.toString(countCards));
						WebElement featuredEventsSlideTitle = getWebElement(
								driver, selectors, "FeaturedEventsSlideTitle",
								Integer.toString(countCards));
						WebElement featuredEventsSlideDate = getWebElement(
								driver, selectors, "FeaturedEventsSlideDate",
								Integer.toString(countCards));
						WebElement featuredEventsSlideLocation = getWebElement(
								driver, selectors,
								"FeaturedEventsSlideLocation",
								Integer.toString(countCards));
						WebElement featuredEventsSlideBuyButton = getWebElement(
								driver, selectors,
								"FeaturedEventsSlideBuyButton",
								Integer.toString(countCards));
						
						// Test the visible cards content
						isMyTestPassed = testVisual(featuredEventsSlideImage,
								pageName, "Featured Events Slide Image",
								testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(featuredEventsSlideTitle,
								pageName, "Featured Events Slide Title",
								testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(featuredEventsSlideDate,
								pageName, "Featured Events Slide Date",
								testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(featuredEventsSlideLocation,
								pageName, "Featured Events Slide Location",
								testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(featuredEventsSlideBuyButton,
								pageName, "Featured Events Slide Buy Button",
								testNumber, isMyTestPassed, this.myDriverParameters);
					}
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Hero Slider, Slides are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Featured Events, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-8. Verify the Hero Section: FEATURE ADVERTISEMENT
	 */
	@Test(description = "MSG.com - Landing Page. Feature Event Advertisement", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS008() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Feature Event Advertisement.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			sleep(15);
			WebElement featureEventsAd = getWebElement(driver, selectors,
					"FeatureEventsAd");
			isMyTestPassed = true;

			waitForElement(driver, featureEventsAd, 15);
			// Validates the Feature Event Ad
			return isMyTestPassed = testVisual(featureEventsAd, pageName,
					"Feature Event Ad", testNumber, isMyTestPassed, this.myDriverParameters);

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}
	
	/**
	 * Step-9. Verify the Hero Section: ON SALE SOON  (NEW STEP NEED TO BE CODED)
	 */
	@Test(description = "MSG.com - Landing Page. On Sale Soon", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS009() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": On Sale Soon.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			sleep(15);
			WebElement featureEventsAd = getWebElement(driver, selectors,
					"FeatureEventsAd");
			isMyTestPassed = true;

			waitForElement(driver, featureEventsAd, 15);
			// Validates the Feature Event Ad
			return isMyTestPassed = testVisual(featureEventsAd, pageName,
					"Feature Event Ad", testNumber, isMyTestPassed, this.myDriverParameters);

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-10. Verify the Hero Section: SLICK SLIDER WITH LARGE IMAGES
	 */
	@Test(description = "MSG.com - Landing Page. Slick Slider", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS010() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "10";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Slick Slider.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			List<WebElement> msgSliderSlides = getWebElements(driver, selectors,
					"MSGSliderSlides");
			WebElement msgSliderLeftArrow = getWebElement(driver, selectors,
					"MSGSliderLeftArrow");
			WebElement msgSliderRightArrow = getWebElement(driver, selectors,
					"MSGSliderRightArrow");
			isMyTestPassed = true;

			// Verify the Navigation Arrows are present
			isMyTestPassed = testVisual(msgSliderLeftArrow, pageName,
					"Slider, Left Arrow", testNumber, isMyTestPassed, this.myDriverParameters);
			isMyTestPassed = testVisual(msgSliderRightArrow, pageName,
					"Slider, Right Arrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Verify Cards, Title, Description and CTA on all slides
			if (msgSliderSlides.size() > 0) {
				for (int countCards = 1; countCards <= msgSliderSlides
						.size(); countCards++) {

					// Verify the selected card is shown as active
					WebElement msgSliderDot = getWebElement(driver, selectors,
							"MSGSliderDot", Integer.toString(countCards));
					if (msgSliderDot.getAttribute("class")
							.contains("slick-active")) {
						LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
								+ ": Selected card #: " + countCards
								+ " is shown as active");

						scrollToElement(driver, msgSliderRightArrow);
						scrollUp(driver);

						WebElement msgSliderSlideCard = getWebElement(driver,
								selectors, "MSGSliderSlideCard",
								Integer.toString(countCards));
						WebElement msgSliderSlideTitle = getWebElement(driver,
								selectors, "MSGSliderSlideTitle",
								Integer.toString(countCards));
						WebElement msgSliderSlideDescription = getWebElement(
								driver, selectors, "MSGSliderSlideDescription",
								Integer.toString(countCards));
						WebElement msgSliderSlideCTA = getWebElement(driver,
								selectors, "MSGSliderSlideCTA",
								Integer.toString(countCards));

						// Test the visible cards content
						isMyTestPassed = testVisual(msgSliderSlideCard,
								pageName, "Slider, Slide Card", testNumber,
								isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(msgSliderSlideTitle, pageName,
								"Slider, Slide Title", testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(msgSliderSlideDescription,
								pageName, "Slider, Slide Description",
								testNumber, isMyTestPassed, this.myDriverParameters);
						isMyTestPassed = testText(msgSliderSlideCTA, pageName,
								"Slider, Slide CTA", testNumber, isMyTestPassed, this.myDriverParameters);

						// Moves to next card but on last one
						if (countCards < msgSliderSlides.size()) {
							msgSliderRightArrow.click();
						}

					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
								+ ": Selected card #: " + countCards
								+ " is not shown as active");
					}
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber
						+ ": MSG Slider, Slides are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Elements on MSG Slider are not working properly or missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-11. Verify the Hero Section: VENUES LIST
	 */
	@Test(description = "MSG.com - Landing Page. Venues List", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS011() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "11";

		LOGGER.info(this.myDriverParameters+" - "+
				pageName + " Page: Test Step-" + testNumber + ": Venues List.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement venuesListEyebrow = getWebElement(driver, selectors,
					"MSGTC000LandingPageVenuesListEyebrow");
			WebElement venuesListTitle = getWebElement(driver, selectors,
					"MSGTC000LandingPageVenuesListTitle");
			List<WebElement> venuesListCards = getWebElements(driver, selectors,
					"MSGTC000LandingPageVenuesListVenueCards");
			isMyTestPassed = true;

			// Validates the Venues List Eyebrow, is displayed
			isMyTestPassed = testText(venuesListEyebrow, pageName,
					"Venues List Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Venues List Title, is displayed
			isMyTestPassed = testText(venuesListTitle, pageName,
					"Venues List Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Venues Cards, Image, Name and Location are
			// displayed
			if (venuesListCards.size() > 0) {
				for (int venuesCount = 1; venuesCount <= venuesListCards
						.size(); venuesCount++) {
					WebElement venuesListVenueImage = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageVenuesListVenueImage",
							Integer.toString(venuesCount));
					WebElement venuesListVenueName = getWebElement(driver,
							selectors, "MSGTC000LandingPageVenuesListVenueName",
							Integer.toString(venuesCount));
					WebElement venuesListVenueLocation = getWebElement(driver,
							selectors,
							"MSGTC000LandingPageVenuesListVenueLocation",
							Integer.toString(venuesCount));
					isMyTestPassed = testText(venuesListVenueImage, pageName,
							"Venues List, Venue Image", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(venuesListVenueName, pageName,
							"Venues List, Venue Name", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testVisual(venuesListVenueLocation,
							pageName, "Venues List, Venue Location", testNumber,
							isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Venues List, Venue Cards are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Venues List, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-12. Verify the Hero Section: BOTTOM ADVERTISEMENT
	 */
	@Test(description = "MSG.com - Landing Page. Footer", groups = {
			"MSGLandingPage", "fullintegration"})
	public boolean MSGTC000LandingPageTS012() {

		String pageName = "MSG.com - Landing Page";
		String testNumber = "12";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Footer.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement footerPhonenumber = getWebElement(driver, selectors,
					"FooterPhonenumber");
			WebElement footerTrademarkLabel = getWebElement(driver, selectors,
					"FooterTrademarkLabel");
			WebElement footerTicketmasterIcon = getWebElement(driver, selectors,
					"FooterTicketmasterIcon");

			isMyTestPassed = true;

			WebElement footerLink = null;
			String newURL;

			// Validates the Footer Phonenumber is present
			isMyTestPassed = testText(footerPhonenumber, pageName,
					"Footer Phonenumber", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Footer Trademark Label is present
			isMyTestPassed = testText(footerTrademarkLabel, pageName,
					"Footer Trademark Label", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Footer Ticketmaster Icon is present
			isMyTestPassed = testText(footerTicketmasterIcon, pageName,
					"Footer Ticketmaster Icon", testNumber, isMyTestPassed, this.myDriverParameters);

			/**
			 * Changing the Wait Time to 5 seconds as New Tab URL resolve is taking time.
			 * CAUTION: THIS SECTION IS HARD-CODED SO IF URL CHANGES FROM HTP TO HTTPS OR ANY OTHER VALUE THEN WE NEED TO CHANGE IT HERE EXPLICITLY OR DISABLE THIS TEST STEP IN EXCEL.
			 * Change done by: Rachit Kumar Rastogi on 03/14/2018
			 */
			
			footerLink = getWebElement(driver, selectors, "FooterLink",
					"About");
			scrollToElement(driver, footerLink);

			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"https://www.themadisonsquaregardencompany.com/our-company/",
					pageName, "Footer Link About", testNumber, isMyTestPassed,
					driver);
			
			footerLink = getWebElement(driver, selectors, "FooterLink", "News");
			scrollToElement(driver, footerLink);

			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"https://www.themadisonsquaregardencompany.com/category/news/",
					pageName, "Footer Link News", testNumber, isMyTestPassed,
					driver);

			footerLink = getWebElement(driver, selectors, "FooterLink",
					"Careers");
			scrollToElement(driver, footerLink);
			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"https://www.themadisonsquaregardencompany.com/careers/",
					pageName, "Footer Link Careers", testNumber, isMyTestPassed,
					driver);

			footerLink = getWebElement(driver, selectors, "FooterLink",
					"Investors");
			scrollToElement(driver, footerLink);
			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"http://investor.msg.com/", pageName, "Footer Link Investors",
					testNumber, isMyTestPassed, driver);

			footerLink = getWebElement(driver, selectors, "FooterLink",
					"Ad Choices");
			scrollToElement(driver, footerLink);
			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"https://www.themadisonsquaregardencompany.com/privacy/#adchoices",
					pageName, "Footer Link Ad Choices", testNumber, isMyTestPassed,
					driver);

			footerLink = getWebElement(driver, selectors, "FooterLink",
					"Email Sign Up");
			scrollToElement(driver, footerLink);
			
			/**
			 * As EMail sign-up is not going on new window so we just need to verify if URL is valid.
			 */
			if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(footerLink.getAttribute("href").toString())!=404)			
				isMyTestPassed = true;
			
/*			isMyTestPassed = testLinkNewTab(footerLink, 5,
					"https://www.msg.com/email-sign-up", pageName,
					"Footer Link Email Sign-up", testNumber, isMyTestPassed, driver);*/

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Footer Links are either missing or not working!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-13: EMAIL CAPTURE - NEW STEP NEED TO BE CODED.
	 * @return
	 */
	public boolean MSGTC000LandingPageTS013() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Step-14: Footer Validations - NEW STEP NEED TO BE CODED.
	 * @return
	 */
	public boolean MSGTC000LandingPageTS014() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * Visual Test Enablement for Landing Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Landing Page";
		String testNumber = "15";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
