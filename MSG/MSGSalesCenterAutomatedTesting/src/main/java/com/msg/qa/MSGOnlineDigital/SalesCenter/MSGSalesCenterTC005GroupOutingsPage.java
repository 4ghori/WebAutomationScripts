package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hamcrest.text.IsEqualIgnoringWhiteSpace;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGSalesCenterTC005GroupOutingsPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC005GroupOutingsPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile;
	Boolean isIOS;
	String platform;
	String myDriverParameters = null;
	Boolean isEdge;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC005GroupOutingsPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC005GroupOutingsPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		Capabilities capabilities = ((RemoteWebDriver) driver)
				.getCapabilities();
		this.platform = capabilities.getCapability("platformName") != null
				? capabilities.getCapability("platformName").toString()
				: capabilities.getPlatform().toString();
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobilePlatform(platform)
				&& !isIEBrowser(capabilities.getBrowserName());
		this.isIOS = isIOSPlatform(platform);
		this.isEdge = isEdgeBrowser(capabilities.getBrowserName().toString());

		/**
		 * Adding Driver Parameters for Logging Purpose in Logger.
		 */
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Group Outings Page.
	 */
	@Test(description = "Sales Center - Group Outings. Hero Section Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS001() {
		try {
			// Verify the Title of the page.
			System.out.println(this.myDriverParameters
					+ " - ******************* MSG Sales Center - Group Outings Page URL to be tested is: "
					+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-1: We are on MSG Sales Center Group Outings Page.");
		} catch (Exception e) {
			isMyTestPassed = false;
			LOGGER.error(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-1: We are not on MSG Sales Center Group Outings Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */
	@Test(description = "Sales Center - Group Outings. Hero Section Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS002() {

		LOGGER.info(this.myDriverParameters
				+ " - MSG Sales Center Group Outings Page: Test Step-2: Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myHeroTitle1stLine = getWebElement(driver, selectors,
					"L2PageHeroTitle1stLine");
			WebElement myHeroTitle2ndLine = getWebElement(driver, selectors,
					"L2PageHeroTitle2ndLine");
			WebElement myHeroSubtitle = getWebElement(driver, selectors,
					"L2PageHeroSubtitle");
			WebElement myHeroDetail = getWebElement(driver, selectors,
					"L2PageHeroDetail");
			WebElement myHeroImage = getWebElement(driver, selectors,
					"L2PageHeroImage");
			isMyTestPassed = true;

			// Validates the Hero Title, line1, is displayed
			if (myHeroTitle1stLine.isDisplayed()
					& myHeroTitle1stLine.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: First Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: First Line for Hero Title is not present");
			}

			// Validates the Hero Title, line2, is displayed
			if (myHeroTitle2ndLine.isDisplayed()
					& myHeroTitle2ndLine.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Second Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Second Line for Hero Title is not present");
			}

			// Validates the Hero Subtitle is displayed
			if (myHeroSubtitle.isDisplayed()
					& myHeroSubtitle.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Subtitle is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Subtitle is not present");
			}

			// Validates the Hero Description is displayed
			if (myHeroDetail.isDisplayed() & myHeroDetail.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Detail Paragraph is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Detail Paragraph is not present");
			}

			// Validates the Hero Image is displayed
			if (myHeroImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Image is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-2: Hero Image is not present");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	/**
	 * Step-3. Verify the Secondary Navigation Bar: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Second NavBar Title -
	 * Present and not empty b. Second NavBar Accesses - Present and not empty
	 */
	@Test(description = "Sales Center - Group Outings. Secondary Navigation Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS003() {

		LOGGER.info(this.myDriverParameters
				+ " - MSG Sales Center Group Outings Page: Test Step-3: Verify Second Navigation Bar Section.");
		sleep(15);
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySecondNavTitle = getWebElement(driver, selectors,
					"L2PageSecondaryNavTitle");
			WebElement mySecondNavSportsLink = getWebElement(driver, selectors,
					"L2PageSecondaryNavLink", "New York");
			WebElement mySecondNavChicagoLink = getWebElement(driver, selectors,
					"L2PageSecondaryNavLink", "Chicago");
			isMyTestPassed = true;

			// Prevent IE11 to execute this test case as the URL related issue
			// is a known one, SC-649
			if (!isIEBrowser(((RemoteWebDriver) driver).getCapabilities()
					.getBrowserName().toString())) {
				if (isMobile) {
					openSecondayNav(driver, selectors);
				}

				// Validates the Secondary Nav Title is displayed
				if (mySecondNavTitle.isDisplayed()
						& mySecondNavTitle.getText() != null) {
					LOGGER.info(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-3: Secondary Nav Title is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-3: Secondary Nav Title is not present");
				}

				// Validates the Secondary Nav Link is working
				scrollToTop(driver);
				scrollToElement(driver, mySecondNavSportsLink);
				scrollUp(driver);
				jsClick(driver, mySecondNavSportsLink);
				sleep(3);
				if (driver.getCurrentUrl().toString()
						.contains("/group-outings#new-york-ny")) {
					LOGGER.info(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-3: Icon 1 on Navigation Bar Scrolled to the expected section. Current URL: "
							+ driver.getCurrentUrl());
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters
							+ " - Expected /group-outings#new-york-ny but found >> CURRENT URL: "
							+ driver.getCurrentUrl());
				}

				if (isMobile) {
					openSecondayNav(driver, selectors);
				}

				// Validates the Secondary Nav Link is working
				scrollToTop(driver);
				scrollToElement(driver, mySecondNavChicagoLink);
				scrollUp(driver);
				jsClick(driver, mySecondNavChicagoLink);
				sleep(3);
				if (driver.getCurrentUrl().toString()
						.contains("/group-outings#chicago-il") || driver.getCurrentUrl().toString()
						.contains("/group-outings#new-york-ny")) {
					LOGGER.info(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-3: Icon 2 on Navigation Bar Scrolled to the expected section. Current URL: "
							+ driver.getCurrentUrl());
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters
							+ " - Expected /group-outings#chicago-il but found >> CURRENT URL: "
							+ driver.getCurrentUrl());
				}

			}
			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-3: Secondary NavBar not working as expected!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	private void searchCaruselCard(WebElement card) {
		if (!isMobilePlatform(((RemoteWebDriver) driver).getCapabilities()
				.getPlatform().toString())) {
			WebElement cardCountElement = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsCardCounter");
			String cardCount = cardCountElement.getText().replaceAll(" ", "");
			String[] counter = cardCount.split("/");
			Actions actions = new Actions(driver);
			boolean found = false;
			while (counter[0] != counter[1] && !found) {
				WebElement currentCard = getWebElement(driver, selectors,
						"MSGSalesCenterTC005GroupOutingsPageEventsCurrentCard");
				if (currentCard.equals(card)) {
					found = true;
				} else {
					scrollToElement(driver, currentCard);
					actions.dragAndDropBy(currentCard, -100, 0);
					actions.build().perform();
					sleep(1);
					cardCount = cardCountElement.getText().replaceAll(" ", "");
					counter = cardCount.split("/");
				}
			}
		}
	}

	/**
	 * Step-4. Verify the Event Section on Sports.
	 */
	@Test(description = "Sales Center - Group Outings. Events Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS004() {

		LOGGER.info(this.myDriverParameters
				+ " - MSG Sales Center Group Outings Page: Test Step-4: Verify Event Card");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myEventCard = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventCard",
					"New York Liberty Tickets and Fan Experiences");
			WebElement myEventImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventImage",
					"New York Liberty Tickets and Fan Experiences");
			WebElement myEventTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventTitle",
					"New York Liberty Tickets and Fan Experiences");
			WebElement myEventDescription = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventDescription",
					"New York Liberty Tickets and Fan Experiences");
			List<WebElement> myEventFeatures = getWebElements(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventFeatures",
					"New York Liberty Tickets and Fan Experiences");
			WebElement myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsEventCTA",
					"New York Liberty Tickets and Fan Experiences");

			isMyTestPassed = true;

			scrollToElement(driver, myEventCard);
			scrollUp(driver, 80);
			sleep(5);
			// Tests the Event Card exists
			if (myEventCard.isDisplayed()) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Card exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Card does not exist");
			}

			// in mobile devices we need to move the carousel until; the card
			// is// visible
			if (isMobile) {
				scrollToElement(driver, myEventCard);
				scrollUp(driver, 80);
				if (!isIOS) searchCaruselCard(myEventCard);
			}

			// Tests the Event Image exists
			if (myEventImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Image exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Image does not exist");
			}

			// Tests the Title is shown and not empty
			if (myEventTitle.isDisplayed() & myEventTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Title is not present");
			}

			// Tests the Description is shown and not empty
			if (!isMobile) {
				if (myEventDescription.isDisplayed()
						& myEventDescription.getText() != null) {
					LOGGER.info(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Description is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters
							+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue Description is not present");
				}
			}

			// Venue shows at least one feature
			if (myEventFeatures.size() > 1) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue shows at least one feature");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue shows no feature");
			}

			// Tests the CTA is shown and is enabled
			if (myEventCTA.isDisplayed() && myEventCTA.isEnabled()) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-4: Venue CTA is not present");
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-4: Event Card not working as expected!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	private String getNewUrl(WebElement myEventCTA) {
		if (isMobile || this.platform.equals("darwin") || isEdge) {
			// due to an issue with android emulator, the test can't click on
			// the link, we are only verifying
			// the existence of the link
			return myEventCTA.getAttribute("href");
		} else {
			jsClick(driver, myEventCTA);
			sleep(5);
			return grabNewUrlAndCloseTab(driver);
		}
	}

	/**
	 * Step-5. Verify the Event Section on Sports.
	 */
	@Test(description = "Sales Center - Group Outings. Events redirection tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS005() {

		LOGGER.info(this.myDriverParameters
				+ " - MSG Sales Center Group Outings Page: Test Step-5: Verify Event Navigation");
		try {

			/*
			 * Set required variables used in test scenario
			 */
			WebElement myEventCTA = null;
			isMyTestPassed = true;
			String newURL;
			sleep(5);

			if (isMobile) {
				WebElement myEventCard = getWebElement(driver, selectors,
						"MSGSalesCenterTC005GroupOutingsPageEventsEventCard",
						"New York Liberty Tickets and Fan Experiences");
				if (!isIOS) searchCaruselCard(myEventCard);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Knicks");
			if (!isMobile) {
				scrollToElement(driver, myEventCTA);
				scrollUp(driver);
			}
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains("http://www.nba.com/knicks")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: New York Knicks, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> New York Knicks failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Rangers");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"https://www.nhl.com/rangers/tickets/group-sales")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: New York Rangers, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> New York Rangers failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Liberty Tickets and Fan Experiences");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL
					.contains("http://liberty.wnba.com/group-tickets-2017/")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: New York Liberty Tickets and Fan Experiences, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> New York Liberty Tickets and Fan Experiences failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Westchester Knicks");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"http://westchester.gleague.nba.com/group-tickets/")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: Westchester Knicks, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> Westchester Knicks failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Hartford Wolf Pack");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"http://www.hartfordwolfpack.com/tickets/group-tickets")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: Hartford Wolf Pack, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> Hartford Wolf Pack failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Christmas Spectacular Starring the Radio City Rockettes");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL
					.contains("https://www.rockettes.com/christmas/groups/")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: Christmas Spectacular Starring the Radio City Rockettes, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> Christmas Spectacular Starring the Radio City Rockettes failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"MSG All Access Tour");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"https://www.msg.com/venue-tours/madison-square-garden")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: MSG All Access Tour, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> MSG All Access Tour failed. ACCESSED URL: "
						+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Radio City Stage Door Tour");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"https://www.msg.com/venue-tours/radio-city-music-hall")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: Radio City Stage Door Tour, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(
						" >> Radio City Stage Door Tour failed. ACCESSED URL: "
								+ newURL);
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsChicagoEventCTA",
					"Chicago Theatre Marquee Tour");
			scrollToElement(driver, myEventCTA);
			newURL = getNewUrl(myEventCTA);
			if (newURL.contains(
					"https://www.msg.com/venue-tours/the-chicago-theatre")) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG Sales Center Group Outings Page: Test Step-5: Chicago Theatre Marquee Tour, Event CTA accessed the expected url: "
						+ newURL);
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters
						+ " - >> Chicago Theatre Marquee Tour failed. ACCESSED URL: "
						+ newURL);
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters
					+ " - MSG Sales Center Group Outings Page: Test Step-5: Event Card not working as expected!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	/**
	 * Visual Test Enablement for Group Outings Page.
	 *
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest() {
		String pageName = "MSG.com - Group Outings Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Visual Test.");

		return MSGOnlineDigitalReusableFunctionalities
				.executeVisualTest(driver);
	}

}
