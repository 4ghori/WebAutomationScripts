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

public class MSGSalesCenterTC010VenueToursPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC010VenueToursPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	String myDriverParameters = null;
	
	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC010VenueToursPage() {

	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC010VenueToursPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Premium Hospitality Page.
	 */
	@Test(description = "MSG.com Universal Template - Venue Tours Page Tests", groups = {
			"MSGSalesCenterVenueToursPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC010VenueToursPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - ******************* MSG.com Universal Template - Venue Tours Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					"MSG.com Universal Template - Venue Tours Page: Test Step-1: We are on MSG.como Universal Template - Venue Tours Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.info(this.myDriverParameters+
					"MSG.com Universal Template - Venue Tours Page: Test Step-1: We are not on MSG.como Universal Template - Venue Tours Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */
	@Test(description = "MSG.com Universal Template - Venue Tours Page. Hero Section Tests", groups = {
			"MSGSalesCenterVenueToursPage", "fullintegration"})
	public boolean MSGSalesCenterTC010VenueToursPageTS002() {

		String pageName = "MSG.com Universal Template - Venue Tours Page";
		String testNumber = "2";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myHeroTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC010VenueToursPageHeroTitle");
			WebElement myHeroImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC010VenueToursPageHeroImage");
			isMyTestPassed = true;

			// Validates the Hero Title
			isMyTestPassed = testText(myHeroTitle, pageName, "Hero Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Hero Image
			isMyTestPassed = testVisual(myHeroImage, pageName, "Hero Image",
					testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Hero Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com Universal Template - Venue Tours Page. Body Section Tests", groups = {
			"MSGSalesCenterVenueToursPage", "fullintegration"})
	public boolean MSGSalesCenterTC010VenueToursPageTS003() {

		String pageName = "MSG.com Universal Template - Venue Tours Page";
		String testNumber = "3";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Body Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement bodyText = getWebElement(driver, selectors,
					"VenueTourBodyText");

			isMyTestPassed = true;

			// Validates the Body Text
			isMyTestPassed = testText(bodyText, pageName, "Body Text",
					testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Body Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "MSG.com Universal Template - Venue Tours Page. Directions Section Tests", groups = {
			"MSGSalesCenterVenueToursPage", "fullintegration"})
	public boolean MSGSalesCenterTC010VenueToursPageTS004() {

		String pageName = "MSG.com Universal Template - Venue Tours Page";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Hero Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement directionsTitle = getWebElement(driver, selectors,
					"VenueTourDirectionsTitle");
			List<WebElement> directions = getWebElements(driver, selectors,
					"VenueTourDirections");
			isMyTestPassed = true;

			// Validates the Direction Title
			isMyTestPassed = testText(directionsTitle, pageName,
					"Direction Title", testNumber, isMyTestPassed, this.myDriverParameters);

			if (directions.size() > 1) {
				for (int dirNumber = 1; dirNumber <= directions
						.size(); dirNumber++) {
					WebElement dirLocation = getWebElement(driver, selectors,
							"VenueTourDirLocation",
							Integer.toString(dirNumber));
					WebElement dirContent = getWebElement(driver, selectors,
							"VenueTourDirContent", Integer.toString(dirNumber));
					WebElement dirCTA = getWebElement(driver, selectors,
							"VenueTourDirCTA", Integer.toString(dirNumber));

					isMyTestPassed = testText(dirLocation, pageName,
							"Direction, Location", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(dirContent, pageName,
							"Direction, Content", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(dirCTA, pageName,
							"Direction, CTA", testNumber, isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": No directions present in screen.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Directions Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Visual Test Enablement for Universal Template - Venue Tours Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Universal Template - Venue Tours Page";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
