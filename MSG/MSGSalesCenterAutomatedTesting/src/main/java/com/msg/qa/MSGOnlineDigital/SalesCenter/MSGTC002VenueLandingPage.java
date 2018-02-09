package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

/**
 * Page Class for msg.com landing page
 *
 * @author Carlos Campino
 * @category MSG.com Landing Page
 */

public class MSGTC002VenueLandingPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC002VenueLandingPage.class);
	private static SoftAssert softAssert = new SoftAssert();
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	String myDriverParameters = null;
	String platform;
	Boolean isIE;

	/**
	 * Default Constructor
	 */
	public MSGTC002VenueLandingPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */

	public MSGTC002VenueLandingPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		this.driver = driver;
		Capabilities capabilities = ((RemoteWebDriver) driver)
				.getCapabilities();
		this.platform = capabilities.getCapability("platformName") != null
				? capabilities.getCapability("platformName").toString()
				: capabilities.getPlatform().toString();
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.myDriverParameters = driverParameters;
		this.isIE = isIEBrowser(capabilities.getBrowserName().toString());
	}

	/**
	 * Step-1. We are navigable to MSG.com Landing Page.
	 */
	@Test(description = "Are we on MSG.com Venue Landing - Radio City Music Hall Page?", groups = {
			"MSGVenueLandingPage", "smoke", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS001() {

		LOGGER.info(this.myDriverParameters
				+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-1: Verify Page Opens.");
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters + this.myDriverParameters
					+ " - ******************* MSG.com Landing  Page URL to be tested is: "
					+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters
					+ " - MSG.com Venue Landing - Radio City Music Hall Page: Test Step-1: We are on MSG.com Venue Landing Page - Radio City Music Hall!!");
		} catch (WebDriverException e) {
			LOGGER.info(this.myDriverParameters
					+ " - MSG.com Venue Landing - Radio City Music Hall Page: Test Step-1: We are not on MSG.com Venue Landing Page - Radio City Music Hall!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			isMyTestPassed = false;
		}
		return isMyTestPassed;
	}

	/**
	 * Step-4. Verify the Hero Section: EVENT SEARCH OPTIONS
	 */
	@Test(description = " - MSG.com Venue Landing - Madison Square Garden Page. Hero Filters Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS002() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Venue Landing Page: Test Step-2: Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement heroTitle = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageHeroTitle");
			List<WebElement> heroFiltersTypeList = getWebElements(driver, selectors,
					"MSGTC002VenueLandingPageHeroTypeFilter");
			
			/*WebElement heroFiltersDate = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageHeroDateFilter");
*/			
			WebElement heroFindEventsButton = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageHeroFindEventsButton");
			isMyTestPassed = true;
			
			WebElement heroFiltersType = heroFiltersTypeList.get(0);
			WebElement heroFiltersDate = heroFiltersTypeList.get(1);
			
			// Validates the Hero Title
			if (heroTitle.isDisplayed() & heroTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: Hero Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Filter Type
			if (heroFiltersType.isDisplayed()
					& heroFiltersType.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: Hero Filter Type is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Filter Date
			if (heroFiltersDate.isDisplayed()
					& heroFiltersDate.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: Hero Filter Date is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Find Events Button
			if (heroFindEventsButton.isDisplayed()
					& heroFindEventsButton.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: Hero Find Events Button is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters
					+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	@Test(description = "MSG.com - Venue landing. Generic Content Tests", groups = {
			"MSGVenueLanding", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS003() {

		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "3";

		LOGGER.info(
				this.myDriverParameters + " - " + pageName + " Page: Test Step-"
						+ testNumber + ": Generic Content section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			driver.navigate().to(this.URL);
			sleep(3);
			WebElement msgSliderLeftArrow = getWebElement(driver, selectors,
					"GenericContentSliderLeftArrow");
			WebElement msgSliderRightArrow = getWebElement(driver, selectors,
					"GenericContentSliderRightArrow");
			scrollToElement(driver, msgSliderRightArrow);
			scrollUp(driver);
			scrollUp(driver);
			List<WebElement> msgSliderSlides = getWebElements(driver, selectors,
					"GenericContentSliderSlides");
			isMyTestPassed = true;

			// SC-2079 Saucelabs IE11 behaving different than a local IE11
			if (!isIE) {
				// Verify the Navigation Arrows are present
				isMyTestPassed = testVisual(msgSliderLeftArrow, pageName,
						"Slider, Left Arrow", testNumber, isMyTestPassed,
						this.myDriverParameters);
				isMyTestPassed = testVisual(msgSliderRightArrow, pageName,
						"Slider, Right Arrow", testNumber, isMyTestPassed,
						this.myDriverParameters);

				// Verify Cards, Title, Description and CTA on all slides
				if (msgSliderSlides.size() > 0) {
					for (int countCards = 1; countCards <= msgSliderSlides
							.size(); countCards++) {

						// Verify the selected card is shown as active
						WebElement msgSliderDot = getWebElement(driver,
								selectors, "GenericContentSliderDot",
								Integer.toString(countCards));
						if (msgSliderDot.getAttribute("class")
								.contains("slick-active")) {
							LOGGER.info(this.myDriverParameters + " - "
									+ pageName + " Page: Test Step-"
									+ testNumber + ": Selected card #: "
									+ countCards + " is shown as active");

							scrollToElement(driver, msgSliderRightArrow);
							scrollUp(driver);

							WebElement msgSliderSlideCard = getWebElement(
									driver, selectors,
									"GenericContentSliderSlideCard",
									Integer.toString(countCards));
							WebElement msgSliderSlideTitle = getWebElement(
									driver, selectors,
									"GenericContentSliderSlideTitle",
									Integer.toString(countCards));
							WebElement msgSliderSlideDescription = getWebElement(
									driver, selectors,
									"GenericContentSliderSlideDescription",
									Integer.toString(countCards));
							WebElement msgSliderSlideCTA = getWebElement(driver,
									selectors, "GenericContentSliderSlideCTA",
									Integer.toString(countCards));

							// Test the visible cards content
							isMyTestPassed = testVisual(msgSliderSlideCard,
									pageName, "Slider, Slide Card", testNumber,
									isMyTestPassed, this.myDriverParameters);
							isMyTestPassed = testText(msgSliderSlideTitle,
									pageName, "Slider, Slide Title", testNumber,
									isMyTestPassed, this.myDriverParameters);
							isMyTestPassed = testText(msgSliderSlideDescription,
									pageName, "Slider, Slide Description",
									testNumber, isMyTestPassed,
									this.myDriverParameters);
							isMyTestPassed = testText(msgSliderSlideCTA,
									pageName, "Slider, Slide CTA", testNumber,
									isMyTestPassed, this.myDriverParameters);

							// Moves to next card but on last one
							if (countCards < msgSliderSlides.size()) {
								msgSliderRightArrow.click();
								sleep(1);
							}

						} else {
							isMyTestPassed = false;
							LOGGER.error(this.myDriverParameters + " - "
									+ pageName + " Page: Test Step-"
									+ testNumber + ": Selected card #: "
									+ countCards + " is not shown as active");
						}
					}
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters + " - " + pageName
							+ " Page: Test Step-" + testNumber
							+ ": Generic Content, Slides are not present.");
				}
			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters + " - " + pageName
					+ " Page: Test Step-" + testNumber
					+ ": Elements on Generic Content are missing or not working properly!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Venue Landing. Event Grid Test", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS004() {
		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "4";
		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Event Grid Section.");
		try {
			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement eventGridHeadline = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageEventGridHeadline");
			WebElement eventGridCalendarCTA = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageFullCalendarCta");

			// Hero Headline
			isMyTestPassed = testText(eventGridHeadline, pageName,
					"Event Grid Headline", testNumber, isMyTestPassed,
					this.myDriverParameters);
			// Hero Calendar CTA
			isMyTestPassed = testText(eventGridCalendarCTA, pageName,
					"Event Grid Calendar CTA", testNumber, isMyTestPassed,
					this.myDriverParameters);

			WebElement HeroTitle = getWebElement(driver, selectors,
					"MSGTC002VenueLandingPageHeroTitle");

			List<WebElement> MSGTC002VenueLandingEventGridCards = getWebElements(
					driver, selectors, "MSGTC002VenueLandingEventGridCards");
			isMyTestPassed = true;
			// Validates the Plan Ahead Title
			isMyTestPassed = testText(HeroTitle, pageName, "Hero Title ",
					testNumber, isMyTestPassed, this.myDriverParameters);
			// Validates the Plan Ahead Cards Info
			for (int cardNumber = 1; cardNumber <= MSGTC002VenueLandingEventGridCards
					.size(); cardNumber++) {
				WebElement heroPlanningCardImage = getWebElement(driver,
						selectors, "MSGTC002VenueLandingEventGridCardImage",
						Integer.toString(cardNumber));
				WebElement heroPlanningCardHeadline = getWebElement(driver,
						selectors,
						"MSGTC002VenueLandingEventGridCardHeadlineTitle",
						Integer.toString(cardNumber));
				WebElement heroPlanningCardDate = getWebElement(driver,
						selectors, "MSGTC002VenueLandingEventGridCardDate",
						Integer.toString(cardNumber));
				WebElement heroPlanningCardEyeLocation = getWebElement(driver,
						selectors, "MSGTC002VenueLandingEventGridCardLocation",
						Integer.toString(cardNumber));
				WebElement heroPlanningCardButton = getWebElement(driver,
						selectors, "MSGTC002VenueLandingEventGridCardButton",
						Integer.toString(cardNumber));
				scrollToElement(driver, heroPlanningCardImage);
				scrollUp(driver);
				// Image
				isMyTestPassed = testVisual(heroPlanningCardImage, pageName,
						"Event Grid Image", testNumber, isMyTestPassed,
						this.myDriverParameters);
				// Eyebrow
				isMyTestPassed = testText(heroPlanningCardHeadline, pageName,
						"Event Grid Eyebrow", testNumber, isMyTestPassed,
						this.myDriverParameters);
				// EventTitle
				isMyTestPassed = testText(heroPlanningCardDate, pageName,
						"Event Grid EventTitle", testNumber, isMyTestPassed,
						this.myDriverParameters);
				// Event Description
				isMyTestPassed = testText(heroPlanningCardEyeLocation, pageName,
						"Event Grid Description", testNumber, isMyTestPassed,
						this.myDriverParameters);
				// Event Button
				isMyTestPassed = testText(heroPlanningCardButton, pageName,
						"Event Grid Button", testNumber, isMyTestPassed,
						this.myDriverParameters);

			}
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters + " - " + pageName
					+ " Page: Test Step-" + testNumber
					+ ": MSG Venue Landing. Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return isMyTestPassed = false;
		}
	}

	@Test(description = "MSG.com - Venue landing. Plan Ahead Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS005() {

		String pageName = "MSG.com - Venue Landing Page";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Plan Ahead Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement PlanAheadTitle = getWebElement(driver, selectors,
					"PlanAheadTitle");
			List<WebElement> PlanAheadPlanningCards = getWebElements(driver,
					selectors, "PlanAheadPlanningCards");
			isMyTestPassed = true;

			// Validates the Plan Ahead Title
			isMyTestPassed = testText(PlanAheadTitle, pageName,
					"Plan Ahead Title", testNumber, isMyTestPassed,
					this.myDriverParameters);

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
						pageName, "Plan Ahead Eyebrow", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// EventTitle
				isMyTestPassed = testText(PlanAheadPlanningCardEyeTitle,
						pageName, "Plan Ahead EventTitle", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// Event Description
				isMyTestPassed = testText(PlanAheadPlanningCardEyeDescription,
						pageName, "Plan Ahead Description", testNumber,
						isMyTestPassed, this.myDriverParameters);

				// Event Button
				isMyTestPassed = testText(PlanAheadPlanningCardEyeButton,
						pageName, "Plan Ahead Button", testNumber,
						isMyTestPassed, this.myDriverParameters);

			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters + " - " + pageName
					+ " Page: Test Step-" + testNumber
					+ ": MSG Events ADS. Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com - Venue landing. Visual links title Tests", groups = {
			"MSGVenueLanding", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS006() {

		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Visual links section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement linkListTitle = getWebElement(driver, selectors,
					"VisualLinkListTitle");

			isMyTestPassed = true;
			WebElement linkVenueHistory = getWebElement(driver, selectors,
					"VisualLinkListLinkDestination", "Venue History");

			isMyTestPassed = true;
			WebElement iconVenueHistory = getWebElement(driver, selectors,
					"VisualLinkListLinkIcon", "Venue History");

			isMyTestPassed = true;

			// Validates the visual link title
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link title", testNumber, isMyTestPassed,
					this.myDriverParameters);

			// Validates the icon Venue History
			isMyTestPassed = testVisual(iconVenueHistory, pageName,
					"Disability Services icon", testNumber, isMyTestPassed,
					this.myDriverParameters);

			// Validates the icon text
			isMyTestPassed = testText(linkVenueHistory, pageName,
					"Venue History", testNumber, isMyTestPassed,
					this.myDriverParameters);

			// Validates the visual link destination
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link destination", testNumber, isMyTestPassed,
					this.myDriverParameters);

			isMyTestPassed = testTextIncluded(
					getWebElementAttribute(iconVenueHistory, "class"),
					"venue-history", pageName, "Venue History Icon", testNumber,
					isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters + " - " + pageName
					+ " Page: Test Step-" + testNumber
					+ ": MSG.com - Venue landing Page. Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Visual Test Enablement for Venue Landing Page.
	 * 
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest() {
		String pageName = "MSG.com - Venue Landing Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Visual Test.");

		return MSGOnlineDigitalReusableFunctionalities
				.executeVisualTest(driver);
	}

}
