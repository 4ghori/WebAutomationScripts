package com.msg.qa.MSGOnlineDigital.SalesCenter;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGSalesCenterTC011PremHospMSGKnicksPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC011PremHospMSGKnicksPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC011PremHospMSGKnicksPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC011PremHospMSGKnicksPage(WebDriver driver,
			String URL, boolean isMyTestPassed,
			Map<String, String[]> selectors,
			String driverParameters) {
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Group Outings Page.
	 */
	@Test(description = "Sales Center - Group Outings. Hero Section Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC011PremHospMSGKnicksPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - ******************* MSG Sales Center - Group Outings Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Group Outings: Test Step-1: We are on MSG Sales Center Group Outings Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.error(myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Group Outings: Test Step-1: We are not on MSG Sales Center Group Outings Page!!");
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

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center. Group Outings: Test Step-2: Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myHeroTitle1stLine = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageHeroTitleLine1");
			WebElement myHeroTitle2ndLine = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageHeroTitleLine2");
			WebElement myHeroSubtitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageHeroSubtitle");
			WebElement myHeroDetail = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageHeroDetail");
			WebElement myHeroImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageHeroImage");
			isMyTestPassed = true;

			// Validates the Hero Title, line1, is displayed
			if (myHeroTitle1stLine.isDisplayed()
					& myHeroTitle1stLine.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-2: First Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Title, line2, is displayed
			if (myHeroTitle2ndLine.isDisplayed()
					& myHeroTitle2ndLine.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-2: Second Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Subtitle is displayed
			if (myHeroSubtitle.isDisplayed()
					& myHeroSubtitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-2: Hero Subtitle is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Description is displayed
			if (myHeroDetail.isDisplayed() & myHeroDetail.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-2: Hero Detail Paragraph is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Image is displayed
			if (myHeroImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-2: Hero Image is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center. Group Outings: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
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

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center. Group Outings: Test Step-3: Verify Second Navigation Bar Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySecondNavTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageSecondNavTitle");
			WebElement mySecondNavSportsLink = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageSecondNavVenuesLink",
					"sports");
			WebElement mySecondNavChicagoLink = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageSecondNavVenuesLink",
					"CHICAGO");
			isMyTestPassed = true;

			// Validates the Secondary Nav Title is displayed
			if (mySecondNavTitle.isDisplayed()
					& mySecondNavTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-3: Secondary Nav Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Secondary Nav Link is working
			mySecondNavSportsLink.click();
			if (driver.getCurrentUrl().toString()
					.contains("/group-outings#sports")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-3: Icon 1 on Navigation Bar Scrolled to the expected section. Current URL: "
								+ this.driver.getCurrentUrl());
			} else {
				LOGGER.info(this.myDriverParameters+" >> CURRENT URL: " + this.driver.getCurrentUrl());
				isMyTestPassed = false;
			}

			// Validates the Secondary Nav Link is working
			scrollToTop(driver);
			mySecondNavChicagoLink.click();
			sleep(2);
			if (driver.getCurrentUrl().toString()
					.contains("/group-outings#chicago")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-3: Icon 2 on Navigation Bar Scrolled to the expected section. Current URL: "
								+ this.driver.getCurrentUrl());
			} else {
				LOGGER.info(this.myDriverParameters+" >> CURRENT URL: " + this.driver.getCurrentUrl());
				isMyTestPassed = false;
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center - Group Outings Page: Test Step-3: Secondary NavBar not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-4. Verify the Event Section on Sports.
	 */
	@Test(description = "Sales Center - Group Outings. Events Tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS004() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center. Group Outings: Test Step-4: Verify Event Card");
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

			// Tests the Event Card exists
			if (myEventCard.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue Card exists");
			} else {
				isMyTestPassed = false;
			}

			// Tests the Event Image exists
			if (myEventImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue Image exists");
			} else {
				isMyTestPassed = false;
			}

			// Tests the Title is shown and not empty
			if (myEventTitle.isDisplayed() & myEventTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Tests the Description is shown and not empty
			if (myEventDescription.isDisplayed()
					& myEventDescription.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue Description is present");
			} else {
				isMyTestPassed = false;
			}

			// Venue shows at least one feature
			if (myEventFeatures.size() > 1) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue shows at least one fature");
			} else {
				isMyTestPassed = false;
			}

			// Tests the CTA is shown and is enabled
			if (myEventCTA.isDisplayed() && myEventCTA.isEnabled()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center. Group Outings: Test Step-4: Venue CTA is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;
		} catch (WebDriverException e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center. Group Outings: Test Step-4: Event Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-5. Verify the Event Section on Sports.
	 */
	@Test(description = "Sales Center - Group Outings. Events redirection tests", groups = {
			"MSGSalesCenterGroupOutingsPage", "fullintegration"})
	public boolean MSGSalesCenterTC005GroupOutingsPageTS005() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center. Group Outings: Test Step-5: Verify Event Navigation");
		try {

			/*
			 * Set required variables used in test scenario
			 */
			WebElement myEventCTA = null;
			isMyTestPassed = true;
			String newURL;
			sleep(5);

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Liberty Tickets and Fan Experiences");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL
					.contains("http://liberty.wnba.com/group-tickets-2017/")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: New York Liberty Tickets and Fan Experiences, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> New York Liberty Tickets and Fan Experiences failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Knicks");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains("http://www.nba.com/knicks/groups")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: New York Knicks, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> New York Knicks failed. ACCESSED URL: " + newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"New York Rangers");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(
					"https://www.nhl.com/rangers/tickets/group-sales")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: New York Rangers, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> New York Rangers failed. ACCESSED URL: " + newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Westchester Knicks");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(3);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(
					"http://westchester.gleague.nba.com/group-tickets/")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: Westchester Knicks, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+" >> Westchester Knicks failed. ACCESSED URL: "
						+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Hartford Wolf Pack");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(
					"http://www.hartfordwolfpack.com/tickets/group-tickets")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: Hartford Wolf Pack, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+" >> Hartford Wolf Pack failed. ACCESSED URL: "
						+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Christmas Spectacular Starring the Radio City Rockettes");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL
					.contains("https://www.rockettes.com/christmas/groups/")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: Christmas Spectacular Starring the Radio City Rockettes, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> Christmas Spectacular Starring the Radio City Rockettes failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"2K Classic Benefiting Wounded Warrior Project");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(
					"http://www.thegarden.com/events/2017/november/2k-classic.html")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: 2K Classic Benefiting Wounded Warrior Project, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> 2K Classic Benefiting Wounded Warrior Project failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"WWE Live Summerslam Heatwave Tour");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(
					"http://www.thegarden.com/events/2016/july/wwe-live-summerslam.html")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: WWE Live Summerslam Heatwave Tour, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> WWE Live Summerslam Heatwave Tour failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"MSG All Access Tour");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains("http://www.thegarden.com/allaccesstour")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: MSG All Access Tour, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+" >> MSG All Access Tour failed. ACCESSED URL: "
						+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsSportEventCTA",
					"Radio City Stage Door Tour");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains("http://www.radiocity.com/tour")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: Radio City Stage Door Tour, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" >> Radio City Stage Door Tour failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			// Tests the Event is accessed via CTA
			myEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC005GroupOutingsPageEventsChicagoEventCTA",
					"Chicago Theatre Marquee Tour");
			scrollToElement(driver, myEventCTA);
			jsClick(driver, myEventCTA);
			sleep(1);
			newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains("http://www.thechicagotheatre.com/tour")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Group Outings: Test Step-5: Chicago Theatre Marquee Tour, Event CTA accessed the expected url: "
								+ newURL);
			} else {
				LOGGER.info(this.myDriverParameters+
						" - >> Chicago Theatre Marquee Tour failed. ACCESSED URL: "
								+ newURL);
				isMyTestPassed = false;
			}

			return isMyTestPassed;
		} catch (WebDriverException e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center. Group Outings: Test Step-4: Event Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Visual Test Enablement for Premium Hospitaity - Knicks Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Premium Hospitaity - Knicks Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
