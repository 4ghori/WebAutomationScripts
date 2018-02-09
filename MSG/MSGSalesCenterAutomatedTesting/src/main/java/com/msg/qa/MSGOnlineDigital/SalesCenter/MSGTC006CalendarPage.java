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

	@Test(description = "MSG.com - Calendar Page. Calenendar Booker Date Selector", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS003() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calenendar Booker Date Selector Section.");

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
			driver.navigate().to(this.URL);
			WebElement bookerTypeFilter = getWebElement(driver, selectors,
					"MSGTC006CalendarPageBookerTypeFilter");
			bookerTypeFilter.click();
			sleep(1);
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
					+ ": Calenendar Booker Date Selector Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Calendar Page. Calenendar Events Shown Tests", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS004() {

		String pageName = "MSG.com - Calendar Page. Calenendar Events Shown";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calenendar Events.");

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
					"CalendarPageResultsEventLocationOption", "Inglewood");
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

	@Test(description = "MSG.com - Calendar Page. Calenendar Featured Events Shown Tests", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS005() {

		String pageName = "MSG.com - Calendar Page. Calenendar Events Shown";
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

	@Test(description = "MSG.com - Calendar Page. Calenendar Booker Date Selector", groups = {
			"MSGTC006CalendarPage", "fullintegration"})
	public boolean MSGTC006CalendarPageTS006() {

		String pageName = "MSG.com - Calendar Page. ";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Calendar Results, Lazy Loading");

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

			// Filters by City
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
			LOGGER.info(this.myDriverParameters+" - Total of events loaded: " + eventCount);

			// Shows the initial amount of event cards shown
			List<WebElement> CalendarPageResultsEventCards = getWebElements(
					driver, selectors, "CalendarPageResultsEventCards");
			int initialCardsNumber = CalendarPageResultsEventCards.size();
			LOGGER.info(this.myDriverParameters+" - Initial size of feature event list: "
					+ initialCardsNumber);

			// Scrolls to the end of the list to trigger the lazy loading
			scrollToElement(driver,
					CalendarPageResultsEventCards.get(initialCardsNumber - 1));
			waitOnLoader(driver);
			sleep(2);

			// Gets the new number of loaded events
			CalendarPageResultsEventCards = getWebElements(driver, selectors,
					"CalendarPageResultsEventCards");
			int loadedCardsNumber = CalendarPageResultsEventCards.size();
			LOGGER.info(this.myDriverParameters+" - New size of feature event list, after lazy loading: "
					+ loadedCardsNumber);

			// Validates the lazy loading did load new events into the list
			if (loadedCardsNumber >= initialCardsNumber) {
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Calendar Results, Lazy Loading fetched newer events");
			} else {
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Calendar Results. No new events were shown after Lazy Loading tried to update the Event List");
				return isMyTestPassed = false;
			}

			// Scrolls again to the end of the list to trigger once more the
			// lazy loading
			scrollToElement(driver,
					CalendarPageResultsEventCards.get(loadedCardsNumber - 1));
			waitOnLoader(driver);
			sleep(2);

			// Gets the new number of loaded events, for a second time
			CalendarPageResultsEventCards = getWebElements(driver, selectors,
					"CalendarPageResultsEventCards");
			int newCardsNumber = CalendarPageResultsEventCards.size();
			LOGGER.info(this.myDriverParameters+
					" - Size of feature event list, after lazy loading was triggered a second time: "
							+ newCardsNumber);

			// Validates the lazy loading did load new events into the list
			if (newCardsNumber >= loadedCardsNumber) {
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Calendar Results, Lazy Loading fetched newer events");
			} else {
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Calendar Results. No new events were shown after Lazy Loading tried to update the Event List");
				return isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Calendar Results Section, Lazy Loading not working as expected");
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
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
