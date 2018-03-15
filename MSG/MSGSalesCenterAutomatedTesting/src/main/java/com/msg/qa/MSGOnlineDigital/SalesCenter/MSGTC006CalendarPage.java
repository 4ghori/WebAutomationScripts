package com.msg.qa.MSGOnlineDigital.SalesCenter;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

/**
 * @author Carlos Campino
 * @category MSG.com Venue Rentals - msg Page
 * @url https://www.msg.com/venue-rentals/madison-square-garden
 */

public class MSGTC006CalendarPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC006CalendarPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	boolean isMobile;
	String myDriverParameters = null;
	boolean isEdge;

	/**
	 * Default Constructor
	 */
	public MSGTC006CalendarPage() {

	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGTC006CalendarPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String platform = capabilities.getCapability("platformName") != null ?
				capabilities.getCapability("platformName").toString() :
				capabilities.getPlatform().toString();
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = MSGOnlineDigitalReusableFunctionalities
				.isMobilePlatform(platform)
				&& !MSGOnlineDigitalReusableFunctionalities.isIEBrowser(capabilities.getBrowserName());
		this.myDriverParameters = driverParameters;
		this.isEdge=isEdgeBrowser(capabilities.getBrowserName().toString());
	}

	/**
	 * Step-1. We are navigable to MSG.com Calendar Page.
	 */
	@Test(description = "MSG.com - Calendar Page. MSG Tests", groups = {
			"MSGTC006CalendarPage", "smoke", "fullintegration"})
	public boolean MSGTC006CalendarPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG.com - Calendar Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com Calendar Page: Test Step-1: We are on MSG Calendar Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com Calendar Page: Test Step-1: We are not on MSG Calendar Page.!!");
		}
		return isMyTestPassed;
	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Hero Section Tests", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS002() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "2";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Booker Filters.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement bookerTitle = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerTitle");
			WebElement bookerTypeFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerTypeFilter");
			WebElement bookerDateFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerDateFilter");
			WebElement bookerLocationFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerLocationFilter");
			WebElement bookerResultsGrid = getWebElement(driver, selectors,
					"MSGTC006CalendarPageResultsGrid");
			isMyTestPassed = true;

			// Validates the Booker Title, is displayed
			isMyTestPassed = testText(bookerTitle, pageName, "Booker Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Booker Type Filter, is displayed
			isMyTestPassed = testText(bookerTypeFilter, pageName,
					"Booker Type Filter", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Booker Date Filter is displayed
			isMyTestPassed = testText(bookerDateFilter, pageName,
					"Booker Date Filter", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Booker Location Filter is displayed
			isMyTestPassed = testText(bookerLocationFilter, pageName,
					"Booker Location Filter", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Booker Result Grid is displayed
			isMyTestPassed = testVisual(bookerResultsGrid, pageName,
					"Booker Result Grid", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG.com Calendar Page: Test Step-2: MSG Booker Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Calendar Page. Calendar Booker Date Selector", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS003() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Booker Date Selector Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement bookerLocationFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerLocationFilter");
			WebElement CalendarPageResultsEventCount = getWebElement(driver,
					selectors, "CalendarPageResultsEventCount");
			isMyTestPassed = true;

			// Verify the Calendar Event Count is shown
			isMyTestPassed = testText(CalendarPageResultsEventCount, pageName,
					"Calendar Event Count", testNumber, isMyTestPassed, this.myDriverParameters);

			// Verify the right number of events are shown when filtered by City
			bookerLocationFilter.click();
			sleep(1);
			WebElement eventLocationList = getWebElement(driver, selectors,
					"CalendarPageResultsEventLocationList");
			isMyTestPassed = testVisual(eventLocationList, pageName,
					"Location Filter List", testNumber, isMyTestPassed, this.myDriverParameters);
			WebElement eventLocationOption = getWebElement(driver, selectors,
					"CalendarPageResultsEventLocationOption", "New York");
			eventLocationOption.click();
			waitOnLoader(driver);
			CalendarPageResultsEventCount = getWebElement(driver, selectors,
					"CalendarPageResultsEventCount");
			String eventCount = CalendarPageResultsEventCount.getText()
					.split(" ")[0];
			isMyTestPassed = testTextMatches(eventCount,
					Integer.toString(getTotalEventCountForNewYork()), pageName,
					"Event Number filtered by City", testNumber,
					isMyTestPassed, this.myDriverParameters);

			// Verify the right number of events are shown when filtered by
			// Category
			/**
			 * Commented on 02/27/2018 By Rachit Kumar Rastogi
			 * https://ci.msghubvision.com/job/Msg.com_Test_Automation_SmokeTest_Cycle/89/console
			 */
/*			driver.navigate().to(this.URL);
			WebElement bookerTypeFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerTypeFilter");
			bookerTypeFilter.click();
			sleep(1);*/
			
			WebElement eventTypeList = getWebElement(driver, selectors,
					"CalendarPageResultsEventTypeList");
			isMyTestPassed = testVisual(eventTypeList, pageName,
					"Type Filter List", testNumber, isMyTestPassed, this.myDriverParameters);
			/**
			 * Change done by Rachit Kumar Rastogi, 01/02/18
			 * Handling of Event Types
			 */
			eventTypeList.click();
					
			List<WebElement> eventTypeOption = getWebElements(driver, selectors,
					"CalendarPageResultsEventTypeOption");
			LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Event Type Selected: "+eventTypeOption.get(1).getText());

			eventTypeOption.get(1).click();
			waitOnLoader(driver);
			
			CalendarPageResultsEventCount = getWebElement(driver, selectors,
					"CalendarPageResultsEventCount");
			eventCount = CalendarPageResultsEventCount.getText().split(" ")[0];
			isMyTestPassed = testTextMatches(eventCount,
					Integer.toString(getTotalEventCount("Comedy")), pageName,
					"Event Number filtered by Type", testNumber,
					isMyTestPassed, this.myDriverParameters);

			// Verify the right number of events are shown when filtered by
			// Category
			driver.navigate().to(this.URL);
			WebElement bookerDateFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerDateFilter");
			bookerDateFilter.click();
			sleep(1);
			WebElement eventDateList = getWebElement(driver, selectors,
					"CalendarPageResultsEventDateList");
			isMyTestPassed = testVisual(eventDateList, pageName,
					"Date Filter List", testNumber, isMyTestPassed, this.myDriverParameters);
			WebElement eventDateOption = getWebElement(driver, selectors,
					"CalendarPageResultsEventDateOption",
					getCurrentMonthName());
			isMyTestPassed = testVisual(eventDateOption, pageName,
					"Date Filter List", testNumber, isMyTestPassed, this.myDriverParameters);
			eventDateOption.click();
			waitOnLoader(driver);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Booker Date Selector Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Calendar Page. Calendar Events Shown Tests", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS004() {

		String pageName = "MSG.com - Calendar Page. Calendar Events Shown";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Events.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement bookerLocationFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerLocationFilter");
			bookerLocationFilter.click();
			sleep(1);
			WebElement eventLocationList = getWebElement(driver, selectors,
					"CalendarPageResultsEventLocationList");
			WebElement eventLocationOption = getWebElement(driver, selectors,
					"CalendarPageResultsEventLocationOption", "New York");
			eventLocationOption.click();
			waitOnLoader(driver);

			WebElement bookerDateFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerDateFilter");
			bookerDateFilter.click();
			sleep(1);
			WebElement eventDateList = getWebElement(driver, selectors,
					"CalendarPageResultsEventDateList");
			WebElement eventDateOption = getWebElement(driver, selectors,
					"CalendarPageResultsEventDateOption",
					getCurrentMonthName());
			eventDateOption.click();
			waitOnLoader(driver);

			WebElement CalendarPageResultsEventCountIcon = getWebElement(driver,
					selectors, "CalendarPageResultsEventCountIcon");
			List<WebElement> CalendarPageResultsEventCards = getWebElements(
					driver, selectors, "CalendarPageResultsEventCards");
			List<WebElement> CalendarPageResultsEventImages = getWebElements(
					driver, selectors, "CalendarPageResultsEventImages");
			List<WebElement> CalendarPageResultsEventNames = getWebElements(
					driver, selectors, "CalendarPageResultsEventNames");
			List<WebElement> CalendarPageResultsEventDates = getWebElements(
					driver, selectors, "CalendarPageResultsEventDates");
			List<WebElement> CalendarPageResultsEventTime = getWebElements(
					driver, selectors, "CalendarPageResultsEventTime");
			List<WebElement> CalendarPageResultsEventVenue = getWebElements(
					driver, selectors, "CalendarPageResultsEventVenue");
			List<WebElement> CalendarPageResultsEventLocation = getWebElements(
					driver, selectors, "CalendarPageResultsEventLocation");
			List<WebElement> CalendarPageResultsEventBuyButton = getWebElements(
					driver, selectors, "CalendarPageResultsEventBuyButton");
			List<WebElement> CalendarPageResultsEventDetailsButton = getWebElements(
					driver, selectors, "CalendarPageResultsEventDetailsButton");
			isMyTestPassed = true;

			// Prevent Edge to run this test as advised by Rachit SC-2103
			if(!isEdge) {
				// Verify the Event Count Icon shows a calendar
				isMyTestPassed = testVisual(CalendarPageResultsEventCountIcon,
						pageName, "Event Count Icon", testNumber, isMyTestPassed, this.myDriverParameters);
				isMyTestPassed = testTextIncluded(
						getWebElementAttribute(CalendarPageResultsEventCountIcon,
								"class"),
						"calendar", pageName, "Event Count Icon", testNumber,
						isMyTestPassed, this.myDriverParameters);
	
				// Verify the Events are shown
				isMyTestPassed = testListSizeIsAbove(
						CalendarPageResultsEventCards.size(), 1, pageName,
						"Regular Events List", testNumber, isMyTestPassed, this.myDriverParameters);
	
				int cards = CalendarPageResultsEventCards.size();
				LOGGER.info(this.myDriverParameters+" - Size of event list: " + cards);
	
				// Verify the Event Info is shown right for regular Events, Date
				for (int cardNumber = 0; cardNumber < cards; cardNumber++) {
					scrollToElement(driver,
							CalendarPageResultsEventNames.get(cardNumber));
					isMyTestPassed = testVisual(
							CalendarPageResultsEventImages.get(cardNumber),
							pageName, "Event Image, Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventNames.get(cardNumber), pageName,
							"Event Name Card nr:" + (cardNumber + 1), testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventDates.get(cardNumber), pageName,
							"Event Date Card nr:" + (cardNumber + 1), testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventTime.get(cardNumber), pageName,
							"Event Time Card nr:" + (cardNumber + 1), testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventVenue.get(cardNumber), pageName,
							"Event Venue Card nr:" + (cardNumber + 1), testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventLocation.get(cardNumber),
							pageName, "Event Location Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsEventBuyButton.get(cardNumber),
							pageName,
							"Event Buy Button Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					if(!isMobile) {
						isMyTestPassed = testText(
								CalendarPageResultsEventDetailsButton.get(cardNumber),
								pageName,
							"Event Details Button Card nr:" + (cardNumber + 1),
								testNumber, isMyTestPassed, this.myDriverParameters);
					}
				}
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Events Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Calendar Page. Calendar Featured Events Shown Tests", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS005() {

		String pageName = "MSG.com - Calendar Page. Calendar Events Shown";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Featured Events.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			driver.navigate().to(this.URL);
			List<WebElement> CalendarPageResultsFeaturedEventCards = getWebElements(
					driver, selectors, "CalendarPageResultsFeaturedEventCards");
			List<WebElement> CalendarPageResultsFeaturedEventImages = getWebElements(
					driver, selectors,
					"CalendarPageResultsFeaturedEventImages");
			List<WebElement> CalendarPageResultsFeaturedEventNames = getWebElements(
					driver, selectors, "CalendarPageResultsFeaturedEventNames");
			List<WebElement> CalendarPageResultsFeaturedEventDates = getWebElements(
					driver, selectors, "CalendarPageResultsFeaturedEventDates");
			List<WebElement> CalendarPageResultsFeaturedEventTime = getWebElements(
					driver, selectors, "CalendarPageResultsFeaturedEventTime");
			List<WebElement> CalendarPageResultsFeaturedEventVenue = getWebElements(
					driver, selectors, "CalendarPageResultsFeaturedEventVenue");
			List<WebElement> CalendarPageResultsFeaturedEventLocation = getWebElements(
					driver, selectors,
					"CalendarPageResultsFeaturedEventLocation");
			List<WebElement> CalendarPageResultsFeaturedEventBuyButton = getWebElements(
					driver, selectors,
					"CalendarPageResultsFeaturedEventBuyButton");
			List<WebElement> CalendarPageResultsFeaturedEventDetailsButton = getWebElements(
					driver, selectors,
					"CalendarPageResultsFeaturedEventDetailsButton");
			isMyTestPassed = true;

			// Prevent Edge to run this test as advised by Rachit SC-2103
			if(!isEdge) {
				// Verify the Events are shown
				isMyTestPassed = testListSizeIsAbove(
						CalendarPageResultsFeaturedEventCards.size(), 1, pageName,
						"Featured Events List", testNumber, isMyTestPassed, this.myDriverParameters);
	
				int cards = CalendarPageResultsFeaturedEventCards.size();
				LOGGER.info(this.myDriverParameters+" - Size of feature event list: " + cards);
	
				// Verify the Event Info is shown right for regular Events, Date
				for (int cardNumber = 0; cardNumber < cards; cardNumber++) {
					scrollToElement(driver,
							CalendarPageResultsFeaturedEventNames.get(cardNumber));
					sleep(2);
					scrollUp(driver);
					sleep(2);
					isMyTestPassed = testVisual(
							CalendarPageResultsFeaturedEventImages.get(cardNumber),
							pageName,
							"Featured Event Image, Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsFeaturedEventNames.get(cardNumber),
							pageName,
							"Featured Event Name Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsFeaturedEventDates.get(cardNumber),
							pageName,
							"Featured Event Date Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					if (CalendarPageResultsFeaturedEventDates.get(cardNumber)
							.getText().contains("-")) {
						isMyTestPassed = testNotVisible(
								CalendarPageResultsFeaturedEventTime
										.get(cardNumber),
								pageName,
								"Featured Event Time Card nr:" + (cardNumber + 1),
								testNumber, isMyTestPassed, this.myDriverParameters);
					} else {
						isMyTestPassed = testText(
								CalendarPageResultsFeaturedEventTime
										.get(cardNumber),
								pageName,
								"Featured Event Time Card nr:" + (cardNumber + 1),
								testNumber, isMyTestPassed, this.myDriverParameters);
					}
					isMyTestPassed = testText(
							CalendarPageResultsFeaturedEventVenue.get(cardNumber),
							pageName,
							"Featured Event Venue Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsFeaturedEventLocation
									.get(cardNumber),
							pageName,
							"Featured Event Location Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							CalendarPageResultsFeaturedEventBuyButton
									.get(cardNumber),
							pageName,
							"Featured Event Buy Button Card nr:" + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					if(!isMobile) {
						isMyTestPassed = testText(
								CalendarPageResultsFeaturedEventDetailsButton
										.get(cardNumber),
								pageName, "Featured Event Details Button Card nr:"
										+ (cardNumber + 1),
								testNumber, isMyTestPassed, this.myDriverParameters);
					}
				}
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Featured Events, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Calendar Page. Dynamic Event Type verification with Hard coded types / REST retrned event Types with Location", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS006() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Dynamic Event Type verification");

		try {

			
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Dynamic event types are not working as expected.");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}
	
	@Test(description = "MSG.com - Calendar Page Secondary Filters", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS007() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Secondary Filters");

		try {
			WebElement secFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageSecFilter");
			WebElement secFilterSeeAll = getWebElement(driver, selectors,
					"MSGTC006CalendarPageSecFilterSeeAll");
			WebElement secFilterSeeAllDoneBtn = getWebElement(driver, selectors,
					"MSGTC006CalendarPageSecFilterSeeAllDoneBtn");
			List<WebElement>  secFilterTags = getWebElements(driver, selectors,
					"MSGTC006CalendarPageSecFilterTags");
			List<WebElement> secFilterDayOfWeek = getWebElements(driver, selectors,
					"MSGTC006CalendarPageSecFilterDayOfWeek");
			List<WebElement>  secFilterTimeOfDay = getWebElements(driver, selectors,
					"MSGTC006CalendarPageSecFilterTimeOfDay");
			List<WebElement>  secFilterVenues = getWebElements(driver, selectors,
					"MSGTC006CalendarPageSecFilterVenues");
			WebElement secFilterEventCount = getWebElement(driver, selectors,
					"MSGTC006CalendarFilteredEventCount");	
			
			/**
			 * Check if Secondary filter is displayed or not?
			 */
			if (secFilter.isDisplayed())
			{
			LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Secondary Filters are present.");
			
			/**
			 * Check for CLick/Collapse action of filter.
			 */
			if(secFilterSeeAll.isDisplayed())
			{
				secFilterSeeAll.click();
				Thread.sleep(1000);
				secFilterSeeAllDoneBtn.click();
				isMyTestPassed= true;
				
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Calendar Secondary Filters See All Filters can be open/closed.");
			}
			
			/**
			 * Check if all Tags are available.
			 */
			if (secFilterTags.size() > 0)
			{
				for (int i=0;i<secFilterTags.size();i++)
				{
					secFilterTags.get(i).click();
					Thread.sleep(5000);
					secFilterEventCount = getWebElement(driver, selectors,"MSGTC006CalendarFilteredEventCount");	
					Thread.sleep(2000);
					if(secFilterEventCount.isDisplayed())
					{
						LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
								+ ": Calendar Secondary Filters Tag - "+secFilterTags.get(i).getText().toString()+" has "+secFilterEventCount.getText().toString()+".");
					}
					else
					{
						isMyTestPassed = false;	
					}
					Thread.sleep(2000);
					System.out.println("Debug-Point-0");
					/**
					 * Re-fetch due to stale locator.
					 */
					secFilterTags = getWebElements(driver, selectors,"MSGTC006CalendarPageSecFilterTags");
					Thread.sleep(2000);
					secFilterTags.get(i).click();
					Thread.sleep(3000);
				}
				isMyTestPassed = true;	
			}
			
			/**
			 * Check if day of the week option is filtering the events.
			 */
			System.out.println("Debug-Point-1");
			secFilterSeeAll = getWebElement(driver, selectors,"MSGTC006CalendarPageSecFilterSeeAll");
			Thread.sleep(2000);
			System.out.println("Debug-Point-2");
			secFilterSeeAll.click();
			System.out.println("Debug-Point-3");
			if (secFilterDayOfWeek.size() > 0)
			{
				for (int i=0;i<secFilterDayOfWeek.size();i++)
				{
					/**
					 * Open the secondary filter for selecting venue.
					 */
					if(i!=0)
					{
						System.out.println("Debug-Point-4");
						secFilterSeeAll.click();
						Thread.sleep(1000);	
					}
					
					/**
					 * select a venue.
					 */
					System.out.println("Debug-Point-5");
					secFilterDayOfWeek.get(i).click();
					/**
					 * Close the selection by clicking done Button.
					 */
					System.out.println("Debug-Point-6");
					secFilterSeeAllDoneBtn.click();
					Thread.sleep(5000);
					/**
					 * If Event count is updated then display it.
					 */
					secFilterEventCount = getWebElement(driver, selectors,
							"MSGTC006CalendarFilteredEventCount");	
					Thread.sleep(2000);
					if(secFilterEventCount.isDisplayed())
					{
						LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
								+ ": Calendar Secondary Filters Venues - "+secFilterDayOfWeek.get(i).getText().toString()+" has "+secFilterEventCount.getText().toString()+".");
					/**
					 * Reuse the feature of calendar lazyload to verify if events are of the selected day of the week only.
					 */
					}
					else
					{
						isMyTestPassed = false;	
					}
					Thread.sleep(3000);
					/**
					 * Click the Secondary filter for unchecking the selected venue.
					 */
					secFilterSeeAll.click();
					/**
					 * Un-check the selected venue.
					 */
					secFilterDayOfWeek.get(i).click();	
					/**
					 * Close the selection by clicking done Button.
					 */
					secFilterSeeAllDoneBtn.click();
				}		
				isMyTestPassed = true;
			}
			
			/**
			 * Check if event time option is filtering the events.
			 */
			secFilterSeeAll = getWebElement(driver, selectors,"MSGTC006CalendarPageSecFilterSeeAll");
			secFilterSeeAll.click();
			if (secFilterTimeOfDay.size() > 0)
			{
				for (int i=0;i<secFilterTimeOfDay.size();i++)
				{
					/**
					 * Open the secondary filter for selecting venue.
					 */
					if(i!=0)
					{
						secFilterSeeAll.click();
						Thread.sleep(1000);	
					}
					/**
					 * select an event time.
					 */
					secFilterTimeOfDay.get(i).click();
					/**
					 * Close the selection by clicking done Button.
					 */
					secFilterSeeAllDoneBtn.click();
					Thread.sleep(5000);
					/**
					 * If Event count is updated then display it.
					 */
					secFilterEventCount = getWebElement(driver, selectors,
							"MSGTC006CalendarFilteredEventCount");	
					Thread.sleep(2000);
					if(secFilterEventCount.isDisplayed())
					{
						LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
								+ ": Calendar Secondary Filters Event Time - "+secFilterTimeOfDay.get(i).getText().toString()+" has "+secFilterEventCount.getText().toString()+".");
					/**
					 * Reuse the feature of calendar lazyload to verify if events are of the selected day of the week only.
					 */
					}
					else
					{
						isMyTestPassed = false;	
					}
					Thread.sleep(3000);
					/**
					 * Click the Secondary filter for unchecking the selected Time.
					 */
					secFilterSeeAll.click();
					/**
					 * Un-check the selected venue.
					 */
					secFilterTimeOfDay.get(i).click();	
					/**
					 * Close the selection by clicking done Button.
					 */
					secFilterSeeAllDoneBtn.click();
				}		
				isMyTestPassed = true;
			}
			
				/**
				 * Check if all venues are available and events are there in the calendar.
				 */
				secFilterSeeAll = getWebElement(driver, selectors,"MSGTC006CalendarPageSecFilterSeeAll");
				secFilterSeeAll.click();
				if (secFilterVenues.size() > 0)
				{
					for (int i=0;i<secFilterVenues.size();i++)
					{
						/**
						 * Open the secondary filter for selecting venue.
						 */
						if(i!=0)
						{
							secFilterSeeAll.click();
							Thread.sleep(1000);	
						}
						/**
						 * select a venue.
						 */
						secFilterVenues.get(i).click();
						/**
						 * Close the selection by clicking done Button.
						 */
						secFilterSeeAllDoneBtn.click();
						Thread.sleep(5000);
						/**
						 * If Event count is updated then display it.
						 */
						secFilterEventCount = getWebElement(driver, selectors,
								"MSGTC006CalendarFilteredEventCount");	
						Thread.sleep(2000);
						if(secFilterEventCount.isDisplayed())
						{
							LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
									+ ": Calendar Secondary Filters Venues - "+secFilterVenues.get(i).getText().toString()+" has "+secFilterEventCount.getText().toString()+".");
						/**
						 * Reuse the feature of calendar lazyload to verify if events are of the selected venue only.
						 */
						}
						else
						{
							isMyTestPassed = false;	
						}
						Thread.sleep(3000);
						/**
						 * Click the Secondary filter for unchecking the selected venue.
						 */
						secFilterSeeAll.click();
						/**
						 * Un-check the selected venue.
						 */
						secFilterVenues.get(i).click();	
						/**
						 * Close the selection by clicking done Button.
						 */
						secFilterSeeAllDoneBtn.click();
					}		
					isMyTestPassed = true;
				}	
			}
			return isMyTestPassed;

		} 
		catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Secondary Filters are not working as expected!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	
	/**
	 * Visual Test Enablement for Calendar Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Calendar Page";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
}
