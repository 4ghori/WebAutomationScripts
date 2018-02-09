package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import com.sun.jna.platform.win32.WinUser;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGTC007EventDetailsPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC007EventDetailsPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	boolean isMobile;
	String platform;
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGTC007EventDetailsPage() {

	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGTC007EventDetailsPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		this.platform = capabilities.getCapability("platformName") != null ?
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
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Group Outings Page.
	 */
	@Test(description = "MSG.com - Event Details Page Tests", groups = {
			"MSGEventDetailsPage", "smoke", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG.com - Event Details Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com - Event Details Page: Test Step-1: We are on MSG Event Details Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com - Event Details Page: Test Step-1: We are not on MSG Event Details Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */
	@Test(description = "MSG.com - Event Details. Hero Section Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS002() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "2";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Hero Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myHeroEventType = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventType");
			WebElement myHeroEventName = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventName");
			WebElement myHeroExploreButton = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroExploreButton");
			WebElement myHeroEventImage = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventImage");
			isMyTestPassed = true;

			// Validates the Hero Event Type
			isMyTestPassed = testText(myHeroEventType, pageName,
					"Hero Event Type", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Hero Event Name
			isMyTestPassed = testText(myHeroEventName, pageName,
					"Hero Event Name", testNumber, isMyTestPassed, this.myDriverParameters);

			if (!isMobile) {
				// Validates the Hero Explore button
				isMyTestPassed = testText(myHeroExploreButton, pageName,
						"Hero Explore button", testNumber, isMyTestPassed, this.myDriverParameters);
			}

			// Validates the Hero Event Image is displayed
			isMyTestPassed = testVisual(myHeroEventImage, pageName,
					"Hero Event Image", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Event Details. Event Date Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS003() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Even Date Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement eventDateTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTitle");
			scrollToElement(driver, eventDateTitle);
			if (isMobile) {
				scrollUp(driver);
			}
			WebElement eventDateTime = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTime");
			WebElement eventLocation = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventLocation");
			WebElement buyTicketsButton = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageBuyTicketsButton");
			isMyTestPassed = true;

			// Validates the Event Title is shown
			isMyTestPassed = testText(eventDateTitle, pageName, "Event Title",
					testNumber, isMyTestPassed, this.myDriverParameters);
			String eventNumber = eventDateTitle.getText().split(" ")[0];

			// Validates the Event Date Selector is present
			isMyTestPassed = testText(eventDateTime, pageName,
					"Event Date Selector", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Date Pop Up is shown
			WebElement eventDateButtonDropdown = getWebElement(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateButtonDropdown");
			scrollToElement(driver, eventDateButtonDropdown);
			scrollUp(driver);
			eventDateButtonDropdown.click();
			sleep(3);
			isMyTestPassed = testText(eventDateButtonDropdown, pageName,
					"Hero Event Type", testNumber, isMyTestPassed, this.myDriverParameters);
			List<WebElement> eventDateDropdownEvents = getWebElements(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateDropdownEvents");

			// Validates the Event Date Pop Up matches the number of events
			isMyTestPassed = testTextMatches(
					Integer.toString(eventDateDropdownEvents.size()),
					eventNumber, pageName, "Hero Event Type", testNumber,
					isMyTestPassed, this.myDriverParameters);

			// Close Event Date pop up
			if (isIOSPlatform(this.platform)) {
				WebElement eventDateMenuCloseButton = getWebElement(driver,
						selectors, "EventDateMenuCloseButton");
				waitForElement(driver, eventDateMenuCloseButton, 5);
				eventDateMenuCloseButton.click();

			} else {
				if(!platform.equals("darwin")) {
					pressESC(driver);
					sleep(1);
				}
			}

			// Validates the Event Venue is shown
			isMyTestPassed = testText(eventLocation, pageName, "Event Venue",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Buy Ticket is shown
			isMyTestPassed = testText(buyTicketsButton, pageName,
					"Buy Ticket Button", testNumber, isMyTestPassed, this.myDriverParameters);

			// ** Disabled the test as NY connection got banned from
			// Ticketmaster
			// Validates the Buy Ticketmaster Url is shown
			// isMyTestPassed = testLinkNewTab(buyTicketsButton, 5,
			// "ticketmaster.com/event/", pageName, "Hero Event Type",
			// testNumber, isMyTestPassed, driver);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Even Date Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Event Details. About the Event Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS004() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": About the Event Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement aboutEventTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventTitle");
			scrollToElement(driver, aboutEventTitle);
			WebElement aboutSponsorIcon = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutSponsorIcon");
			WebElement aboutEventDescription = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventDescription");
			WebElement aboutEventPriceRange = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventPriceRange");
			isMyTestPassed = true;

			// Validates the Event Title
			isMyTestPassed = testText(aboutEventTitle, pageName, "Event Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Sponsor Icon is shown
			isMyTestPassed = testText(aboutSponsorIcon, pageName,
					"Sponsor Icon", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Description is shown
			isMyTestPassed = testText(aboutEventDescription, pageName,
					"Event Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Price Range is shown
			isMyTestPassed = testVisual(aboutEventPriceRange, pageName,
					"Event Price Range", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG About Event. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Event Details. About the Event Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS005() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": About the Event Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement aboutEventTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventTitle");
			scrollToElement(driver, aboutEventTitle);
			isMyTestPassed = true;

			// Validates the ADS name
			isMyTestPassed = testVisual(aboutEventTitle, pageName, "ADS",
					testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events ADS. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Event Details. Plan Ahead Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS006() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Plan Ahead Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement PlanAheadTitle = getWebElement(driver, selectors,
					"PlanAheadTitle");
			scrollToElement(driver, PlanAheadTitle);
			List<WebElement> PlanAheadPlanningCards = getWebElements(driver,
					selectors, "PlanAheadPlanningCards");
			isMyTestPassed = true;

			// Validates the Plan Ahead Title
			isMyTestPassed = testText(PlanAheadTitle, pageName,
					"Plan Ahead Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Plan Ahead Cards Info
			for (int cardNumber = 1; cardNumber <= PlanAheadPlanningCards
					.size(); cardNumber++) {
				WebElement PlanAheadPlanningCardImage = getWebElement(driver,
						selectors, "PlanAheadPlanningCardImages",
						Integer.toString(cardNumber));
				WebElement PlanAheadPlanningCardEyeBrow = getWebElement(driver,
						selectors, "PlanAheadPlanningCardEyeBrows",
						Integer.toString(cardNumber));
				WebElement PlanAheadPlanningCardEyeTitle = getWebElement(driver,
						selectors, "PlanAheadPlanningCardEyeTitles",
						Integer.toString(cardNumber));
				WebElement PlanAheadPlanningCardEyeDescription = getWebElement(
						driver, selectors,
						"PlanAheadPlanningCardEyeDescriptions",
						Integer.toString(cardNumber));
				WebElement PlanAheadPlanningCardEyeButton = getWebElement(
						driver, selectors, "PlanAheadPlanningCardEyeButtons",
						Integer.toString(cardNumber));

				// Image
				isMyTestPassed = testVisual(PlanAheadPlanningCardImage,
						pageName, "Plan Ahead Image", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// Eyebrow
				isMyTestPassed = testText(PlanAheadPlanningCardEyeBrow,
						pageName, "Plan Ahead Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

				// EventTitle
				isMyTestPassed = testText(PlanAheadPlanningCardEyeTitle,
						pageName, "Plan Ahead EventTitle", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Description
				isMyTestPassed = testText(PlanAheadPlanningCardEyeDescription,
						pageName, "Plan Ahead Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Button
				isMyTestPassed = testText(PlanAheadPlanningCardEyeButton,
						pageName, "Plan Ahead Button", testNumber, isMyTestPassed, this.myDriverParameters);

			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events ADS. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Event Details. Visual links title Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS007() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Visual links section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement linkListTitle = getWebElement(driver, selectors,
					"VisualLinkListTitle");
			isMyTestPassed = true;
			scrollToElement(driver, linkListTitle);
			WebElement linkFAQ = getWebElement(driver, selectors,
					"VisualLinkListLinkDestination", "Venue FAQs");
			isMyTestPassed = true;
			WebElement linkDisabledServices = getWebElement(driver, selectors,
					"VisualLinkListLinkDestination", "Disabled Services");

			WebElement iconFAQ = getWebElement(driver, selectors,
					"VisualLinkListLinkIcon", "Venue FAQs");
			isMyTestPassed = true;
			WebElement iconDisabledServices = getWebElement(driver, selectors,
					"VisualLinkListLinkIcon", "Disabled Services");

			isMyTestPassed = true;

			// Validates the visual link title
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon FAQ
			isMyTestPassed = testVisual(iconFAQ, pageName, "FAQ icon",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon Disability Services
			isMyTestPassed = testVisual(iconDisabledServices, pageName,
					"Disability Services icon", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon text
			isMyTestPassed = testText(linkFAQ, pageName, "FAQ text", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon text
			isMyTestPassed = testText(linkDisabledServices, pageName,
					"Disability Services text", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the visual link destination
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link destination", testNumber, isMyTestPassed, this.myDriverParameters);

			isMyTestPassed = testTextIncluded(
					getWebElementAttribute(iconFAQ, "class"), "faq", pageName,
					"FAQ Icon", testNumber, isMyTestPassed, this.myDriverParameters);

			isMyTestPassed = testTextIncluded(
					getWebElementAttribute(iconDisabledServices, "class"),
					"disability-services", pageName, "Disabled Services Icon",
					testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events ADS. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}
	
	/**
	 * Visual Test Enablement for Events Details Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Events Details Page";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
}
