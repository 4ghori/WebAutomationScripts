package com.msg.qa.MSGOnlineDigital.SalesCenter;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

/**
 * Utility class for JIRA REST API
 *
 * @author Carlos Campino
 * @category MSG Sales Center Premium Hospitality Page
 */

public class MSGSalesCenterTC004PremiumHospitalityPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC004PremiumHospitalityPage.class);
	private static SoftAssert softAssert = new SoftAssert();
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile;
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC004PremiumHospitalityPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC004PremiumHospitalityPage(WebDriver driver,
			String URL, boolean isMyTestPassed, Map<String, String[]> selectors,
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
		/**
		 * Adding Driver Parameters for Logging Purpose in Logger.
		 */
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Venue Rentals Page.
	 */
	@Test(description = "Are we on MSG Sales Center Premium Hospitality Page?", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS001() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-1: Verify Page Opens.");
		try {
			// Verify the Title of the page.
			LOGGER.info(myDriverParameters+
					" - ******************* MSG Sales Center Venue Rentals Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-1: We are on MSG Sales Center Premium Hospitality Page.");
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-1: We are not on MSG Sales Center Premium Hospitality Page!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			isMyTestPassed = false;
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty
	 */
	@Test(description = "Sales Center - Premium Hospitality. Hero Section Tests", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS002() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-2: Verify Hero Section.");
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
			isMyTestPassed = true;

			// Validates the Hero Title, line1, is displayed
			if (myHeroTitle1stLine.isDisplayed()
					& myHeroTitle1stLine.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: First Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: First Line for Hero Title is not present");
			}

			// Validates the Hero Title, line2, is displayed
			if (myHeroTitle2ndLine.isDisplayed()
					& myHeroTitle2ndLine.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Second Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Second Line for Hero Title is not present");
			}

			// Validates the Hero Subtitle is displayed
			if (myHeroSubtitle.isDisplayed()
					& myHeroSubtitle.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Subtitle is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Subtitle is not present");
			}

			// Validates the Hero Description is displayed
			if (myHeroDetail.isDisplayed() & myHeroDetail.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Detail paragraph Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-2: Detail paragraph Hero Title is not present");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-3. Verify the Secondary Navigation Bar: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Second NavBar Title -
	 * Present and not empty b. Second NavBar Accesses - Present and not empty
	 */
	@Test(description = "Sales Center - Premium Hospitality. Secondary Navigation Tests", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS003() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-3: Verify Second Navigation Bar Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySecondNavTitle = getWebElement(driver, selectors,
					"L2PageSecondaryNavTitle");
			isMyTestPassed = true;

			// Validates the Secondary Nav Title is displayed
			if (mySecondNavTitle.isDisplayed()
					& mySecondNavTitle.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-3: Secondary Nav Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-3: Secondary Nav Title is not present");
			}

			if (isMobile) {
				removeIntercom(driver);
				scrollToElement(driver, mySecondNavTitle);
				scrollUp(driver);
				mySecondNavTitle.click();
				sleep(3);
			}

			WebElement mySecondNavNewYorkLink = getWebElement(driver, selectors,
					"L2PageSecondaryNavLink", "Madison Square Garden");

			// Validates the Secondary Nav Link is working
			scrollToTop(driver);
			scrollToElement(driver, mySecondNavNewYorkLink);
			scrollUp(driver);
			mySecondNavNewYorkLink.click();
			sleep(5);
			if (driver.getCurrentUrl().toString()
					.contains("/premium-hospitality#madison-square-garden")) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-3: Icon 1 on Navigation Bar Scrolled to the expected section. Current URL: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - Expected /premium-hospitality#madison-square-garden but found >> CURRENT URL: "
								+ driver.getCurrentUrl());
			}

			if (isMobile) {
				mySecondNavTitle = getWebElement(driver, selectors,
						"L2PageSecondaryNavTitle");
				scrollToElement(driver, mySecondNavTitle);
				sleep(1);
				scrollUp(driver);
				sleep(1);
				mySecondNavTitle.click();
				sleep(3);
			}

			WebElement mySecondNavLosAngelesLink = getWebElement(driver,
					selectors, "L2PageSecondaryNavLink", "The Forum");

			// Validates the Secondary Nav Link is working
			scrollToTop(driver);
			scrollToElement(driver, mySecondNavLosAngelesLink);
			scrollUp(driver);

			mySecondNavLosAngelesLink.click();
			sleep(3);
			if (driver.getCurrentUrl().toString()
					.contains("/premium-hospitality#the-forum") || driver.getCurrentUrl().toString()
					.contains("/premium-hospitality#madison-square-garden")) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-3: Icon 2 on Navigation Bar Scrolled to the expected section. Current URL: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - Expected /premium-hospitality#the-forum but found >> CURRENT URL: "
								+ driver.getCurrentUrl());
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-3: Secondary NavBar not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-4. Verify the Venue Filter Section.
	 */
	@Test(description = "Sales Center - Premium Hospitality. Venue Filtering Tests", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS004() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-4: Verify Venue Filters");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myFiltersVenueGroupTitle = getWebElement(driver,
					selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersVenueGroupTitle");
			WebElement myFiltersSpacesCount = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersSpacesCount");
			WebElement myFiltersSpaceText = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersSpaceText");
			WebElement filterSeeAll = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"See All");
			WebElement filterFullSeason = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"Full Season");
			WebElement filterYear = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"Year");
			WebElement filterSingleEvent = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"Single Event");
			WebElement filterMultiNight = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"Multi Night");
			List<WebElement> myVenuesGroup = getWebElements(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesVenuesGroup");
			isMyTestPassed = true;

			// Close Chat
			if (isMobile) {
				removeIntercom(driver);
			}

			// Tests the Title is shown and not empty
			if (myFiltersVenueGroupTitle.isDisplayed()
					& myFiltersVenueGroupTitle.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Venue group Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Venue group Title is not present");
			}

			// Tests the 'See All' filters is preset
			if (getWebElementAttribute(filterSeeAll, "class")
					.contains("active")) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: See All filter is preset");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: See All filter is not preset");
			}

			scrollToElement(driver,myFiltersSpaceText);
			scrollUp(driver);
			// Tests the Filters Space Text is shown and not empty
			if (myFiltersSpaceText.isDisplayed()
					& myFiltersSpaceText.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Venue group Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Venue group Title is not present");
			}

			// Quantity of venues shown matches the preset See All filter
			if (Integer.parseInt(
					myFiltersSpacesCount.getText()) == myVenuesGroup.size()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Sell All filters is OK");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Sell All filters is wrong");
			}

			// Quantity of venues shown is changed when selecting a different
			// filter
			scrollToElement(driver, filterFullSeason);
			scrollUp(driver);
			filterFullSeason.click();
			sleep(3);
			filterFullSeason = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
					"Full Season");
			if (getWebElementAttribute(filterFullSeason, "class")
					.contains("active")) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Full Season filter is activated");
				myVenuesGroup = getWebElements(driver, selectors,
						"MSGSalesCenterTC004PremiumHospitalityPageVenuesVenuesGroup");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Full Season filter is not activated");
			}
			if (Integer.parseInt(
					myFiltersSpacesCount.getText()) == myVenuesGroup.size()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Full Season filter is OK");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Full Season filter is wrong");
			}

			// Test Year filter only on Desktop
			if (!isMobile) {
				scrollToElement(driver, filterYear);
				scrollUp(driver);
				filterYear.click();
				sleep(3);
				filterYear = getWebElement(driver, selectors,
						"MSGSalesCenterTC004PremiumHospitalityPageVenuesFiltersFilter",
						"Year");
				if (getWebElementAttribute(filterYear, "class")
						.contains("active")) {
					LOGGER.info(
							"MSG Sales Center Premium Hospitality Page: Test Step-4: Year filter is activated");
					myVenuesGroup = getWebElements(driver, selectors,
							"MSGSalesCenterTC004PremiumHospitalityPageVenuesVenuesGroup");
				} else {
					isMyTestPassed = false;
					LOGGER.error(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-4: Year filter is not activated");
				}

				if (Integer.parseInt(
						myFiltersSpacesCount.getText()) == myVenuesGroup
								.size()) {
					LOGGER.info(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Year filter is OK");
				} else {
					isMyTestPassed = false;
					LOGGER.error(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-4: Quantity of venues shown for Year filter is wrong");
				}
			}
			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-4: Venue Filters not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}
	}

	/**
	 * Step-5. Verify the Venue Section.
	 */
	@Test(description = "Sales Center - Premium Hospitality. Venue Tests", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS005() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-5: Verify Venue NY");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myVenueCard = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueCard",
					"Event Level Suites");
			WebElement myVenueImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueImage",
					"Event Level Suites");
			WebElement myVenueTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueTitle",
					"Event Level Suites");
			WebElement myVenueDescription = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueDescription",
					"Event Level Suites");
			List<WebElement> myVenueFeatures = getWebElements(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueFeatures",
					"Event Level Suites");
			WebElement myVenueCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesNYVenueCTA",
					"Event Level Suites");
			isMyTestPassed = true;

			// Tests the Venue Card exists
			if (myVenueCard.isDisplayed()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Card exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Card does not exist");
			}

			// Tests the Venue Image exists
			if (myVenueImage.isDisplayed()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Image exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Image does not exist");
			}

			// Tests the Title is shown and not empty
			if (myVenueTitle.isDisplayed() & myVenueTitle.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Title is not present");
			}

			// Tests the Description is shown and not empty
			if (!isMobile) {
				if (myVenueDescription.isDisplayed()
						& myVenueDescription.getText() != null) {
					LOGGER.info(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Description is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue Description is not present");
				}
			}

			// Venue shows at least one feature
			if (myVenueFeatures.size() > 1) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue shows at least one feature");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue shows no feature");
			}

			// Tests the CTA is shown and is enabled
			if (myVenueCTA.isDisplayed() && myVenueCTA.isEnabled()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-5: Venue CTA is not present");
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-5: NY Venue Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-6. Verify the Venue Section on Los Angeles CA.
	 */
	@Test(description = "Sales Center - Premium Hospitality. Venue Tests", groups = {
			"MSGSalesCenterPremiumHospitalityPage", "fullintegration"})
	public boolean MSGSalesCenterTC004PremiumHospitalityPageTS006() {

		LOGGER.info(myDriverParameters+
				" - MSG Sales Center Premium Hospitality Page: Test Step-6: Verify Venue LA");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySpacesCount = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLASpacesCount");
			List<WebElement> myVenuesGroup = getWebElements(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenuesGroup");
			WebElement myVenueCard = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueCard",
					"Senate Seats at the Forum");
			WebElement myVenueImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueImage",
					"Senate Seats at the Forum");
			WebElement myVenueTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueTitle",
					"Senate Seats at the Forum");
			WebElement myVenueDescription = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueDescription",
					"Senate Seats at the Forum");
			List<WebElement> myVenueFeatures = getWebElements(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueFeatures",
					"Senate Seats at the Forum");
			WebElement myVenueCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC004PremiumHospitalityPageVenuesLAVenueCTA",
					"Senate Seats at the Forum");
			isMyTestPassed = true;

			// Quantity of venues shown matches the number of venue cards
			// displayed
			if (Integer.parseInt(mySpacesCount.getText()) == myVenuesGroup
					.size()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Quantity of venues shown for LA is OK");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Quantity of venues shown for LA is wrong");
			}

			// Tests the Venue Card exists
			if (myVenueCard.isDisplayed()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Card exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Card does not exist");
			}

			// Tests the Venue Image exists
			if (myVenueImage.isDisplayed()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Image exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Image does not exist");
			}

			// Tests the Title is shown and not empty
			if (myVenueTitle.isDisplayed() & myVenueTitle.getText() != null) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Title is not present");
			}

			// Tests the Description is shown and not empty
			if (!isMobile) {
				if (myVenueDescription.isDisplayed()
						& myVenueDescription.getText() != null) {
					LOGGER.info(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Description is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(myDriverParameters+
							" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue Description is not present");
				}
			}

			// Venue shows at least one feature
			if (myVenueFeatures.size() > 1) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue shows at least one frature");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue shows no frature");
			}

			// Tests the CTA is shown and is enabled
			if (myVenueCTA.isDisplayed() && myVenueCTA.isEnabled()) {
				LOGGER.info(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters+
						" - MSG Sales Center Premium Hospitality Page: Test Step-6: Venue CTA is not present");
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(myDriverParameters+
					" - MSG Sales Center Premium Hospitality Page: Test Step-6: LA Venue Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}
	
	/**
	 * Visual Test Enablement for Premium Hospitality Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Premium Hospitality Page";
		String testNumber = "7";

		LOGGER.info(myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}	

}
