package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElements;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollToElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollUp;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.sleep;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testText;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testVisual;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.waitOnLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGTC012KnicksTicketsPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC012KnicksTicketsPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGTC012KnicksTicketsPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGTC012KnicksTicketsPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG.com Knicks Tickets Page.
	 */
	@Test(description = "MSG.com kNicks Tickets Tests", groups = {
			"MSGKnicksTicketsPage", "smoke", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - ******************* MSG Knicks Tickets Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG Knicks Tickets: Test Step-1: We are on MSG Knicks Tickets Page.");
			sleep(15);
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.error(
					" - MSG Knicks Tickets: Test Step-1: We are not on MSG Knicks Tickets Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Hero Section Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS002() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Ticket Central - Knicks Page: Test Step-2: Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement tCHeroTeamLogo = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageTeamLogo");
			scrollToElement(driver,tCHeroTeamLogo);
			scrollUp(driver);
			WebElement tCHeroTeamLink = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageTeamLink");
			WebElement tCHeroTitleLine1 = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageTitleLine1");
			WebElement tCHeroTitleLine2 = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageTitleLine2");
			WebElement tCHeroDescription = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageDescription");
			WebElement tCHeroForm = getWebElement(driver, selectors,
					"MSGTC012KnicksTicketsPageForm");
			isMyTestPassed = true;

			// Validates the TC Hero Team logo
			if (tCHeroTeamLogo.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Team logo");
			} else {
				isMyTestPassed = false;
			}

			// Validates the TC Hero Link
			if (tCHeroTeamLink.isDisplayed()
					& tCHeroTeamLink.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Team link is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the TC Hero Title, line1, is displayed
			if (tCHeroTitleLine1.isDisplayed()
					& tCHeroTitleLine1.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Title link, line 1, is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the TC Hero Title, line2, is displayed
			if (tCHeroTitleLine2.isDisplayed()
					& tCHeroTitleLine2.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Title link, line 2, is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the TC Hero Description is displayed
			if (tCHeroDescription.isDisplayed()
					& tCHeroDescription.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Description is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the TC Hero Form
			if (tCHeroForm.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Form is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+
					" - MSG Ticket Central - Knicks Page: Test Step-2: TC Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-3. Verify Ticket Central: SINGLE GAME TICKETS, Upcoming Games
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Upcoming Events Section Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS003() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Upcoming Events. Game list");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Single Game Tickets");
			isMyTestPassed = true;

			// Access Single Game tickets
			tCMenuBarOption.click();

			WebElement tCUpcomingGamesTitle = getWebElement(driver, selectors,
					"TCUpcomingGamesTitle");
			WebElement tCUpcomingGamesFilter = getWebElement(driver, selectors,
					"TCUpcomingGamesFilter");
			WebElement tCUpcomingGamesFilterOpponent = getWebElement(driver,
					selectors, "TCUpcomingGamesFilterOpponent");

			// Validate Upcoming Games Title
			isMyTestPassed = testText(tCUpcomingGamesTitle, pageName,
					"Upcoming Games Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Upcoming Games Filter
			isMyTestPassed = testText(tCUpcomingGamesFilter, pageName,
					"Upcoming Games Filter", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Upcoming Games Filter Options
			tCUpcomingGamesFilter.click();
			isMyTestPassed = testText(tCUpcomingGamesFilterOpponent, pageName,
					"Upcoming Games Option", testNumber, isMyTestPassed, this.myDriverParameters);
			WebElement tCUpcomingGamesOpponentOption = getWebElement(driver,
					selectors, "TCUpcomingGamesOpponentOption",
					"Cleveland Cavaliers");

			// Validate Upcoming Games Event cards
			tCUpcomingGamesOpponentOption.click();
			sleep(1);
			List<WebElement> TIcketCentralPageResultsEventCards = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventCards");

			List<WebElement> TIcketCentralPageResultsEventImages = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventImages");
			List<WebElement> TIcketCentralPageResultsEventNames = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventNames");
			List<WebElement> TIcketCentralPageResultsEventDates = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventDates");
			List<WebElement> TIcketCentralPageResultsEventTime = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventTime");
			List<WebElement> TIcketCentralPageResultsEventVenue = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventVenue");
			List<WebElement> TIcketCentralPageResultsEventLocation = getWebElements(
					driver, selectors,
					"TCPageUpcomingGamesResultsEventLocation");
			List<WebElement> TIcketCentralPageResultsEventBuyButton = getWebElements(
					driver, selectors,
					"TCPageUpcomingGamesResultsEventBuyButton");
			List<WebElement> TIcketCentralPageResultsEventDetailsButton = getWebElements(
					driver, selectors,
					"TCPageUpcomingGamesResultsEventDetailsButton");

			if (TIcketCentralPageResultsEventCards.size() >= 1) {

				for (int cardNumber = 0; cardNumber < TIcketCentralPageResultsEventCards
						.size(); cardNumber++) {

					isMyTestPassed = testVisual(
							TIcketCentralPageResultsEventImages.get(cardNumber),
							pageName,
							"Upcoming Games Event, Image " + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventNames.get(cardNumber),
							pageName,
							"Upcoming Games Event, Name " + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventDates.get(cardNumber),
							pageName,
							"Upcoming Games Event, Date " + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventTime.get(cardNumber),
							pageName,
							"Upcoming Games Event, Time " + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventVenue.get(cardNumber),
							pageName,
							"Upcoming Games Event, Venue " + (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventLocation
									.get(cardNumber),
							pageName,
							"Upcoming Games Event, Location "
									+ (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventBuyButton
									.get(cardNumber),
							pageName,
							"Upcoming Games Event, Buy Button "
									+ (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							TIcketCentralPageResultsEventDetailsButton
									.get(cardNumber),
							pageName,
							"Upcoming Games Event, Details Button "
									+ (cardNumber + 1),
							testNumber, isMyTestPassed, this.myDriverParameters);

				}
			} else {
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": No upcoming Games Event cards found, check Event Engine.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-4. Verify Ticket Central: SINGLE GAME TICKETS, Show More Games
	 * Loading
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Group Ticket Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS004() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Upcoming Games, Show More Loading.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			driver.navigate().to(this.URL);

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Single Game Tickets");
			isMyTestPassed = true;

			// Access Single Game tickets
			tCMenuBarOption.click();

			List<WebElement> TIcketCentralPageResultsEventCards = getWebElements(
					driver, selectors, "TCPageUpcomingGamesResultsEventCards");

			int cardNumberInit = TIcketCentralPageResultsEventCards.size();

			WebElement upcomingGamesResultsShowMoreButton = getWebElement(
					driver, selectors,
					"TCPageUpcomingGamesResultsShowMoreButton");
			WebElement upcomingGamesResultsSeeFullSchedule = getWebElement(
					driver, selectors,
					"TCPageUpcomingGamesResultsSeeFullSchedule");

			// Validate Show More Button
			isMyTestPassed = testText(upcomingGamesResultsShowMoreButton,
					pageName, "Upcoming Games Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate See Full Shedule Button
			isMyTestPassed = testText(upcomingGamesResultsSeeFullSchedule,
					pageName, "Upcoming Games Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate The Show More button loads more games
			upcomingGamesResultsShowMoreButton.click();
			waitOnLoader(driver);
			sleep(2);
			int cardNumberMore = TIcketCentralPageResultsEventCards.size();

			if (cardNumberMore >= cardNumberInit) {
				LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Show More button showed more games");
			} else {
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Show More button is not working properly");
				return isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-5. Verify Ticket Central: SINGLE GAME TICKETS, Last minute
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Group Ticket Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS005() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Upcoming Games, Last Minute.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Single Game Tickets");
			isMyTestPassed = true;

			// Access Single Game tickets
			tCMenuBarOption.click();

			WebElement lastMinuteTitle = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteTitle");
			WebElement lastMinuteImage = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteImage");
			WebElement lastMinuteEyebrow = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteEyebrow");
			WebElement lastMinuteSubtitle = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteSubtitle");
			WebElement lastMinuteDetail = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteDetail");
			WebElement lastMinuteLearnMore = getWebElement(driver, selectors,
					"TCPageUpcomingGamesLastMinuteLearnMore");

			// Validate Last Minute Title
			isMyTestPassed = testText(lastMinuteTitle, pageName,
					"Last Minute Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Last Minute Image
			isMyTestPassed = testText(lastMinuteImage, pageName,
					"Last Minute Image", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Last Minute Eyebrow
			isMyTestPassed = testText(lastMinuteEyebrow, pageName,
					"Last Minute Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Last Minute Subtitle
			isMyTestPassed = testText(lastMinuteSubtitle, pageName,
					"Last Minute Subtitle", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Last Minute Detail
			isMyTestPassed = testText(lastMinuteDetail, pageName,
					"Last Minute Detail", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Last Minute Learn More Button
			isMyTestPassed = testText(lastMinuteLearnMore, pageName,
					"Last Minute Learn More Button", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-6. Verify Ticket Central: SINGLE GAME TICKETS, Plan Ahead
	 */

	@Test(description = "MSG Ticket Central - Knicks Page. Plan Ahead Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS006() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Plan Ahead Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement planAheadTitle = getWebElement(driver, selectors,
					"PlanAheadTitle");
			scrollToElement(driver, planAheadTitle);
			List<WebElement> planAheadPlanningCards = getWebElements(driver,
					selectors, "PlanAheadPlanningCards");
			isMyTestPassed = true;

			// Validates the Plan Ahead Title
			isMyTestPassed = testText(planAheadTitle, pageName,
					"Plan Ahead Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Plan Ahead Cards Info
			for (int cardNumber = 1; cardNumber <= planAheadPlanningCards
					.size(); cardNumber++) {
				WebElement planAheadPlanningCardImage = getWebElement(driver,
						selectors, "PlanAheadPlanningCardImages",
						Integer.toString(cardNumber));
				WebElement planAheadPlanningCardEyeBrow = getWebElement(driver,
						selectors, "PlanAheadPlanningCardEyeBrows",
						Integer.toString(cardNumber));
				WebElement planAheadPlanningCardEyeTitle = getWebElement(driver,
						selectors, "PlanAheadPlanningCardEyeTitles",
						Integer.toString(cardNumber));
				WebElement planAheadPlanningCardEyeDescription = getWebElement(
						driver, selectors,
						"PlanAheadPlanningCardEyeDescriptions",
						Integer.toString(cardNumber));
				WebElement planAheadPlanningCardEyeButton = getWebElement(
						driver, selectors, "PlanAheadPlanningCardEyeButtons",
						Integer.toString(cardNumber));

				// Image
				isMyTestPassed = testVisual(planAheadPlanningCardImage,
						pageName, "Plan Ahead Image", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// Eyebrow
				isMyTestPassed = testText(planAheadPlanningCardEyeBrow,
						pageName, "Plan Ahead Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

				// EventTitle
				isMyTestPassed = testText(planAheadPlanningCardEyeTitle,
						pageName, "Plan Ahead EventTitle", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Description
				isMyTestPassed = testText(planAheadPlanningCardEyeDescription,
						pageName, "Plan Ahead Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Button
				isMyTestPassed = testText(planAheadPlanningCardEyeButton,
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

	/**
	 * Step-7. Verify Ticket Central: GROUP TICKETS - Media Contact
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Group Ticket Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS007() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": group Tickets.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Group Tickets");
			isMyTestPassed = true;

			// Access Group Tickets
			tCMenuBarOption.click();

			WebElement mediaContactHeadline = getWebElement(driver, selectors,
					"TCGroupTicketsMediaContactHeadline");
			WebElement mediaContactDescription = getWebElement(driver,
					selectors, "TCGroupTicketsMediaContactDescription");
			WebElement mediaContactCTA = getWebElement(driver, selectors,
					"TCGroupTicketsMediaContactCTA");
			WebElement mediaContactNumber = getWebElement(driver, selectors,
					"TCGroupTicketsMediaContactNumber");
			WebElement mediaContactImage = getWebElement(driver, selectors,
					"TCGroupTicketsMediaContactImage");
			WebElement mediaContactButton = getWebElement(driver, selectors,
					"TCGroupTicketsMediaContactButton");

			// Validate Media Contact Title
			isMyTestPassed = testText(mediaContactHeadline, pageName,
					"Media Contact Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Media Contact Description
			isMyTestPassed = testText(mediaContactDescription, pageName,
					"Media Contact Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Media Contact CTA
			isMyTestPassed = testText(mediaContactCTA, pageName,
					"Media Contact CTA", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Media Contact Phone Number
			isMyTestPassed = testText(mediaContactNumber, pageName,
					"Media Contact Phone", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Media Contact Image
			isMyTestPassed = testVisual(mediaContactImage, pageName,
					"Media Contact Image", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Media Contact Gallery Button
			isMyTestPassed = testText(mediaContactButton, pageName,
					"Media Contact Gallery Button", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-8. Verify Ticket Central: GROUP TICKETS - Benefits
	 */

	@Test(description = "MSG Ticket Central - Knicks Page. Group Ticket Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS008() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": group Tickets.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Group Tickets");
			isMyTestPassed = true;

			// Access Group Tickets
			tCMenuBarOption.click();

			// Validates Benefits Eyebrow, Headline and Description

			WebElement benefitsEyebrow = getWebElement(driver, selectors,
					"TCGroupTicketsBenefitsEyebrow");

			WebElement benefitsHeadline = getWebElement(driver, selectors,
					"TCGroupTicketsBenefitsHeadline");
			scrollToElement(driver, benefitsHeadline);
			List<WebElement> tCGroupTicketsAllBenefits = getWebElements(driver,
					selectors, "TCGroupTicketsAllBenefits");
			isMyTestPassed = true;

			WebElement benefitsDescription = getWebElement(driver, selectors,
					"TCGroupTicketsBenefitsDescription");
			// Validate Benefits Eyebrow
			isMyTestPassed = testText(benefitsEyebrow, pageName,
					"Benefits Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Benefits Headline
			isMyTestPassed = testText(benefitsHeadline, pageName,
					"Benefits Headline", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Benefits Description
			isMyTestPassed = testText(benefitsDescription, pageName,
					"Benefits Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Plan Ahead Cards Info
			for (int benefitNumber = 1; benefitNumber <= tCGroupTicketsAllBenefits
					.size(); benefitNumber++) {

				WebElement benefitsTitle = getWebElement(driver, selectors,
						"TCGroupTicketsBenefitsTitle");
				WebElement benefitsTitleDescription = getWebElement(driver,
						selectors, "TCGroupTicketsBenefitsTitleDescription");
				WebElement benefitsIcon = getWebElement(driver, selectors,
						"TCGroupTicketsBenefitsIcon");

				// Validate Benefits Title
				isMyTestPassed = testText(benefitsTitle, pageName,
						"Benefits Title", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Benefits Title Description
				isMyTestPassed = testText(benefitsTitleDescription, pageName,
						"Benefits Title Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Benefits Icon
				isMyTestPassed = testVisual(benefitsIcon, pageName,
						"Benefits Icon", testNumber, isMyTestPassed, this.myDriverParameters);
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-9. Verify Ticket Central: TICKET PLANS - PLAN CARDS
	 */

	@Test(description = "MSG Ticket Central - Knicks Page. Ticket Plan Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS009() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Ticket Plans.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Ticket Plans");
			isMyTestPassed = true;

			// Access Ticket Plans
			tCMenuBarOption.click();

			// Validate Ticket Plan Title

			WebElement plansTitle = getWebElement(driver, selectors,
					"TCTicketPlansTitle");
			scrollToElement(driver, plansTitle);
			List<WebElement> tCTicketPlansCards = getWebElements(driver,
					selectors, "TCTicketPlansCards");
			isMyTestPassed = true;

			// Validate Ticket Plan Title
			isMyTestPassed = testText(plansTitle, pageName, "Plans Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Ticket Plans cards info
			for (int cardNumber = 1; cardNumber <= tCTicketPlansCards
					.size(); cardNumber++) {

				WebElement plansCardImages = getWebElement(driver, selectors,
						"TCTicketPlansCardImages");
				WebElement plansCardImageTitles = getWebElement(driver,
						selectors, "TCTicketPlansCardImageTitles");
				WebElement plansCardHeadline = getWebElement(driver, selectors,
						"TCTicketPlansCardHeadline");
				WebElement plansCardDescription = getWebElement(driver,
						selectors, "TCTicketPlansCardDescription");
				WebElement plansCardInquireButton = getWebElement(driver,
						selectors, "TCTicketPlansCardInquireButton");
				WebElement plansCardLearnMore = getWebElement(driver, selectors,
						"TCTicketPlansCardLearnMore");

				// Validate Ticket Plan Card Image
				isMyTestPassed = testVisual(plansCardImages, pageName,
						"Ticket Plan Card Image", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Ticket Plan Card Image Title
				isMyTestPassed = testText(plansCardImageTitles, pageName,
						"Ticket Plan Card Image Title", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Ticket Plan Card Headline
				isMyTestPassed = testText(plansCardHeadline, pageName,
						"Ticket Plan Card Headline", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Ticket Plan Card Description
				isMyTestPassed = testText(plansCardDescription, pageName,
						"Ticket Plan Card Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Ticket Plan Card Inquire Button
				isMyTestPassed = testText(plansCardInquireButton, pageName,
						"Ticket Plan Card Inquire Button", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Ticket Plan Card Learn More
				isMyTestPassed = testText(plansCardLearnMore, pageName,
						"Ticket Plan Card Learn More", testNumber, isMyTestPassed, this.myDriverParameters);
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-11. Verify Ticket Central: TICKET PLANS - FAQs
	 */

	@Test(description = "MSG Ticket Central - Knicks Page. Ticket Plan FAQs Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS011() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "11";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Ticket Plans.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Ticket Plans");
			isMyTestPassed = true;

			// Access Ticket Plans
			tCMenuBarOption.click();

			// Validate Ticket Plan FAQs Title

			WebElement faqsTitle = getWebElement(driver, selectors,
					"TCTicketPlansFaqsTitle");
			scrollToElement(driver, faqsTitle);
			List<WebElement> tCTicketPlansAllFaqs = getWebElements(driver,
					selectors, "TCTicketPlansCards");
			isMyTestPassed = true;

			WebElement faqsButton = getWebElement(driver, selectors,
					"TCTicketPlansFaqsButton");

			// Validate Ticket Plan FAQs Title
			isMyTestPassed = testText(faqsTitle, pageName, "FAQs Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Ticket Plan FAQs Button
			isMyTestPassed = testText(faqsButton, pageName, "FAQs Button",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the FAQs info
			for (int faqNumber = 1; faqNumber <= tCTicketPlansAllFaqs
					.size(); faqNumber++) {

				WebElement faqsHeadLine = getWebElement(driver, selectors,
						"TCTicketPlansFaqsHeadLine");
				faqsHeadLine.click();
				sleep(1);
				WebElement faqsDescription = getWebElement(driver, selectors,
						"TCTicketPlansFaqsDescription");

				// Validate FAQs Headline
				isMyTestPassed = testText(faqsHeadLine, pageName,
						"FAQs Headline", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate FAQs Description
				isMyTestPassed = testText(faqsDescription, pageName,
						"FAQs Description", testNumber, isMyTestPassed, this.myDriverParameters);

			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-12. Verify Ticket Central: Premium & Corporate - Spotlight
	 */
	@Test(description = "MSG Ticket Central - Knicks Page. Premium & Corporate Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS012() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "12";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Premium & Corporate.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Premium & Corporate");
			isMyTestPassed = true;

			// Access Premium & Corporate
			tCMenuBarOption.click();

			WebElement spotlightTitle = getWebElement(driver, selectors,
					"TCPremiumSpotlightTitle");
			WebElement spotlightDescription = getWebElement(driver, selectors,
					"TCPremiumSpotlightDescription");
			WebElement spotlightLearnMoreButton = getWebElement(driver,
					selectors, "TCPremiumSpotlightLearnMoreButton");
			WebElement spotlightImage = getWebElement(driver, selectors,
					"TCPremiumSpotlightImage");

			// Validate Spotlight Title
			isMyTestPassed = testText(spotlightTitle, pageName,
					"Spotlight Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Spotlight Description
			isMyTestPassed = testText(spotlightDescription, pageName,
					"Spotlight Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Spotlight Learn More Button
			isMyTestPassed = testText(spotlightLearnMoreButton, pageName,
					"Spotlight Learn More Button", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validate Spotlight Image
			isMyTestPassed = testVisual(spotlightImage, pageName,
					"Spotlight Image", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-13. Verify Ticket Central: Premium & Corporate - Premium Hospitality
	 */

	@Test(description = "MSG Ticket Central - Knicks Page. Premium Hospitality Tests", groups = {
			"MSGTC012KnicksTicketsPage", "fullintegration"})
	public boolean MSGTC012KnicksTicketsPageTS013() {

		String pageName = "MSG Ticket Central - Knicks Page";
		String testNumber = "13";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Premium & Corporate.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement tCMenuBarOption = getWebElement(driver, selectors,
					"TCMenuBarOption", "Premium & Corporate.");
			isMyTestPassed = true;

			// Access Premium & Corporate
			tCMenuBarOption.click();

			// Validate Premium Hospitality Title and CTA

			WebElement seeAllButton = getWebElement(driver, selectors,
					"TCPremiumHospitalitySeeAllButton");
			WebElement hospitalityTitle = getWebElement(driver, selectors,
					"TCPremiumHospitalityTitle");
			scrollToElement(driver, hospitalityTitle);
			List<WebElement> tCPremiumCardAllCards = getWebElements(driver,
					selectors, "TCPremiumCardAllCards");
			isMyTestPassed = true;

			// Validate Premium See All button
			isMyTestPassed = testText(seeAllButton, pageName,
					"Premium See All Button", testNumber, isMyTestPassed, this.myDriverParameters);
			// Validate Premium Hospitality Title
			isMyTestPassed = testText(hospitalityTitle, pageName,
					"Premium Hospitality Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Premium Hospitality cards info
			for (int cardNumber = 1; cardNumber <= tCPremiumCardAllCards
					.size(); cardNumber++) {

				WebElement premiumCardImage = getWebElement(driver, selectors,
						"TCPremiumCardImage");
				WebElement premiumCardTitle = getWebElement(driver, selectors,
						"TCPremiumCardTitle");
				WebElement premiumCardDescription = getWebElement(driver,
						selectors, "TCPremiumCardDescription");
				WebElement premiumCardInquireButton = getWebElement(driver,
						selectors, "TCPremiumCardInquireButton");
				WebElement premiumCardLearnMoreButton = getWebElement(driver,
						selectors, "TCPremiumCardLearnMoreButton");

				// Validate Premium Hospitality Card Image
				isMyTestPassed = testVisual(premiumCardImage, pageName,
						"Premium Hospitality Card Image", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// Validate Premium Hospitality Card Title
				isMyTestPassed = testText(premiumCardTitle, pageName,
						"Premium Hospitality Card Title", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Premium Hospitality Card Description
				isMyTestPassed = testText(premiumCardDescription, pageName,
						"Premium Hospitality Card Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Premium Hospitality Card Inquire Button
				isMyTestPassed = testText(premiumCardInquireButton, pageName,
						"Premium Hospitality Card Inquire Button", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validate Premium Hospitality Card Learn More Button
				isMyTestPassed = testText(premiumCardLearnMoreButton, pageName,
						"Premium Hospitality Card Learn More Button",
						testNumber, isMyTestPassed, this.myDriverParameters);
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Feature Event Ad is missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Visual Test Enablement for Knicks Tickets Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Knicks Tickets Page";
		String testNumber = "14";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}	
	
}
