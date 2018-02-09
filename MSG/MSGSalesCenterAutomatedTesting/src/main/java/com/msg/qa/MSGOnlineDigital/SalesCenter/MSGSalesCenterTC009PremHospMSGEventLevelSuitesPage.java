package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage {
	private static final Logger LOGGER = Logger.getLogger(
			MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	boolean isMobile;
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC009PremHospMSGEventLevelSuitesPage(WebDriver driver,
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
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Premium Hospitality MSG
	 * Event Level Suites Page.
	 */
	@Test(description = "Sales Center - MSG Event Level Suites Page. Navigation Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "smoke",
			"fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS001() {
		try {
			// Verify the Title of the page.
			System.out.println(this.myDriverParameters+
					" - ******************* MSG Sales Center Premium Hospitality Event Level Suites Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			System.out.println(this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-1: We are on MSG Sales Center Premium Hospitality Lexus Suites Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-1: We are not on MSG Sales Center Premium Hospitality Lexus Suites Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */

	@Test(description = "Sales Center - MSG Event Level Suites Page. Hero Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS002() {

		LOGGER.info(this.myDriverParameters+this.myDriverParameters+
				" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Verify Hero Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement heroTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageHeroTitle");
			WebElement heroLocation = getWebElement(driver, selectors,
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageHeroLocation");
			WebElement heroInquire = getWebElement(driver, selectors,
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageHeroInquire");
			WebElement heroExplore = getWebElement(driver, selectors,
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageHeroExplore");
			WebElement heroImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageHeroImage");
			isMyTestPassed = true;

			// Validates the Hero Title, is displayed
			if (heroTitle.isDisplayed() & heroTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: Hero Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Location, is displayed
			if (heroLocation.isDisplayed() & heroLocation.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: Hero Location is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Inquire button is displayed
			if (!isMobile) {
				if (heroInquire.isDisplayed() & heroInquire.getText() != null) {
					LOGGER.info(this.myDriverParameters+this.myDriverParameters+
							" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: Hero Inquire Button is present");
				} else {
					isMyTestPassed = false;
				}
			}

			// Validates the Hero Explore 360 Button is displayed
			if (heroExplore.isDisplayed() & heroExplore.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: Hero Explore 360 is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Image is displayed
			if (heroImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: Hero Image is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Overview Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS003() {

		LOGGER.info(this.myDriverParameters+this.myDriverParameters+
				" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Verify Overview Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement overviewTitle = getWebElement(driver, selectors,
					"OverviewTitle");
			List<WebElement> OverviewItems = getWebElements(driver, selectors,
					"OverviewItems");
			WebElement overviewItemTitle = null;
			WebElement overviewItemIcon = null;
			WebElement overviewItemDescription = null;
			WebElement overviewBottomLeftTitleL1 = getWebElement(driver,
					selectors, "OverviewBottomLeftTitleL1");
			WebElement overviewBottomLeftTitleL2 = getWebElement(driver,
					selectors, "OverviewBottomLeftTitleL2");
			WebElement overviewBottomRightTitle = getWebElement(driver,
					selectors, "OverviewBottomRightTitle");
			WebElement overviewBottomRightDescription = getWebElement(driver,
					selectors, "OverviewBottomRightDescription");
			isMyTestPassed = true;

			// Validate Overview Title is displayed
			if (overviewTitle.isDisplayed() & overviewTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validate at Least an Overview Item is shown and All Overview
			// Items contain a Title an Icon and a Description
			if (OverviewItems.size() > 0) {

				for (int count = 1; count <= OverviewItems.size(); count++) {
					overviewItemTitle = getWebElement(driver, selectors,
							"OverviewItemTitle", Integer.toString(count));
					if (overviewItemTitle.isDisplayed()
							& overviewItemTitle.getText() != null) {
						LOGGER.info(this.myDriverParameters+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Title is present");
					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Title is missing");
					}

					overviewItemIcon = getWebElement(driver, selectors,
							"OverviewItemIcon", Integer.toString(count));
					if (overviewItemIcon.isDisplayed()) {
						LOGGER.info(this.myDriverParameters+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Icon is present");
					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Icon is missing");
					}

					overviewItemDescription = getWebElement(driver, selectors,
							"OverviewItemDescription", Integer.toString(count));
					if (overviewItemDescription.isDisplayed()
							& overviewItemDescription.getText() != null) {
						LOGGER.info(this.myDriverParameters+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Description is present");
					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Item Description is missing");
					}
				}
			} else {
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview has no items");
			}

			// Validates Overview Bottom Left Title, line 1, is displayed
			if (overviewBottomLeftTitleL1.isDisplayed()
					& overviewBottomLeftTitleL1.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Left Title line 1 is displayed");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Left Title line 1 is not displayed");
			}

			// Validates Overview Bottom Left Title, line 2, is displayed
			if (overviewBottomLeftTitleL2.isDisplayed()
					& overviewBottomLeftTitleL2.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Left Title line 2 is displayed");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Left Title line 2 is not displayed");
			}

			// Validates Overview Bottom Right Title is displayed
			if (overviewBottomRightTitle.isDisplayed()
					& overviewBottomRightTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Right Title is displayed");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Left Title is not displayed");
			}

			// Validates Overview Bottom Right Description is displayed
			if (overviewBottomRightDescription.isDisplayed()
					& overviewBottomRightDescription.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Right Description is displayed");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: Overview Bottom Right Description is not displayed");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-3: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Aditional Details Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS004() {

		LOGGER.info(this.myDriverParameters+this.myDriverParameters+
				" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Verify Aditional Details Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement detailsTitle = getWebElement(driver, selectors,
					"AditionalDetailsTitle");
			List<WebElement> detailsSubsections = getWebElements(driver,
					selectors, "AditionalDetailsSubsections" + "");
			isMyTestPassed = true;

			// Validate Overview Title is displayed
			if (detailsTitle.isDisplayed() & detailsTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Details Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Details Title is not present");
			}

			// Validate at Least a Detail Item is shown and All Aditional Detail
			// Items contain a Title and an Icon on all Detail sections
			if (detailsSubsections.size() > 0) {
				for (int subSectionCount = 1; subSectionCount <= detailsSubsections
						.size(); subSectionCount++) {

					WebElement sectionTitle = getWebElement(driver, selectors,
							"AditionalDetailsSectionTitle",
							Integer.toString(subSectionCount));
					if (sectionTitle.isDisplayed()
							& sectionTitle.getText() != null) {
						LOGGER.info(this.myDriverParameters+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Overview Item Title is present for section \"+subSectionCount);");
					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Overview Item Title is missing for section \"+subSectionCount);");
					}

					List<WebElement> detailsItems = getWebElements(driver,
							selectors, "AditionalDetailsItems",
							Integer.toString(subSectionCount));
					if (detailsItems.size() > 0) {
						List<WebElement> overviewItemIcons = getWebElements(
								driver, selectors, "AditionalDetailsItemIcon",
								Integer.toString(subSectionCount));
						List<WebElement> overviewItemTitles = getWebElements(
								driver, selectors, "AditionalDetailsItemTitle",
								Integer.toString(subSectionCount));
						for (int itemCount = 0; itemCount < detailsItems
								.size(); itemCount++) {

							if (overviewItemIcons.get(itemCount)
									.isDisplayed()) {
								LOGGER.info(this.myDriverParameters+this.myDriverParameters+
										" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Details Item Icon is present for section "
												+ subSectionCount);
							} else {
								isMyTestPassed = false;
								LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
										" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Details Item Icon is missing for section \"+subSectionCount);");
							}

							if (overviewItemTitles.get(itemCount).isDisplayed()
									& overviewItemTitles.get(itemCount)
											.getText() != null) {
								LOGGER.info(this.myDriverParameters+this.myDriverParameters+
										" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Overview Item Title is present for section \"+subSectionCount);");
							} else {
								isMyTestPassed = false;
								LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
										" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Overview Item Title is missing for section \"+subSectionCount);");
							}
						}
					} else {
						LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
								" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Detail has no items");
					}
				}
			} else {
				LOGGER.error(this.myDriverParameters+" - "+this.myDriverParameters+
						" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: Aditional Detail has no section");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-4: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Gallery Module Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS005() {

		String pageName = "MSG Sales Center Premium Hospitality Event Level Suites";
		String testNumber = "5";
		LOGGER.info(this.myDriverParameters+this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Galery MOdule.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement galleryModuleEyebrow = getWebElement(driver, selectors,
					"GalleryModuleEyebrow");
			WebElement galleryModuleTitle = getWebElement(driver, selectors,
					"GalleryModuleTitle");
			WebElement galleryModuleDescription = getWebElement(driver,
					selectors, "GalleryModuleDescription");
			WebElement galleryModuleCTA = getWebElement(driver, selectors,
					"GalleryModuleCTA");
			WebElement galleryModuleImage1 = getWebElement(driver, selectors,
					"GalleryModuleFirstImage");
			WebElement galleryModuleImage2 = getWebElement(driver, selectors,
					"GalleryModuleSecondImage");

			isMyTestPassed = true;

			// Validates the Gallery Module Eyebrow, is displayed
			isMyTestPassed = testText(galleryModuleEyebrow, pageName,
					"Gallery Module Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Gallery Module Title, is displayed
			isMyTestPassed = testText(galleryModuleTitle, pageName,
					"Gallery Module Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Gallery Module Description is displayed
			isMyTestPassed = testText(galleryModuleDescription, pageName,
					"Gallery Module Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Gallery Module CTA is displayed
			isMyTestPassed = testText(galleryModuleCTA, pageName,
					"Gallery Module CTA", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Gallery Module Images are displayed
			isMyTestPassed = testText(galleryModuleImage1, pageName,
					"Gallery Module Image 1", testNumber, isMyTestPassed, this.myDriverParameters);

			isMyTestPassed = testText(galleryModuleImage2, pageName,
					"Gallery Module Image 2", testNumber, isMyTestPassed, this.myDriverParameters);

			if (!isMobile) {
				WebElement galleryModuleImage3 = getWebElement(driver,
						selectors, "GalleryModuleThirdImage");

				isMyTestPassed = testText(galleryModuleImage3, pageName,
						"Gallery Module Image 3", testNumber, isMyTestPassed, this.myDriverParameters);

				// Validates the Gallery Module Disclaimer is displayed
				WebElement galleryModuleDisclaimer1 = getWebElement(driver,
						selectors, "GalleryModuleFirstDisclaimer");
				WebElement galleryModuleDisclaimer2 = getWebElement(driver,
						selectors, "GalleryModuleSecondDisclaimer");
				WebElement galleryModuleDisclaimer3 = getWebElement(driver,
						selectors, "GalleryModuleThirdDisclaimer");

				isMyTestPassed = testText(galleryModuleDisclaimer1, pageName,
						"Gallery Module Disclaimer 1", testNumber, isMyTestPassed, this.myDriverParameters);

				isMyTestPassed = testText(galleryModuleDisclaimer2, pageName,
						"Gallery Module Disclaimer 2", testNumber, isMyTestPassed, this.myDriverParameters);

				isMyTestPassed = testText(galleryModuleDisclaimer3, pageName,
						"Gallery Module Disclaimer 3", testNumber, isMyTestPassed, this.myDriverParameters);
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+this.myDriverParameters+
					" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-5: MSG Gallery Module Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Hero Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS006() {

		LOGGER.info(this.myDriverParameters+this.myDriverParameters+
				" - MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Module.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement ResourcesTitle = getWebElement(driver, selectors,
					"ResourcesTitle");
			WebElement ResourcesSubtitle = getWebElement(driver, selectors,
					"ResourcesSubtitle");
			WebElement ResourcesDescription = getWebElement(driver, selectors,
					"ResourcesDescription");
			WebElement ResourcesDownloadLink = getWebElement(driver, selectors,
					"ResourcesDownloadLink");

			isMyTestPassed = true;

			// Validates the Resources Title, is displayed
			if (ResourcesTitle.isDisplayed()
					& ResourcesTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Title is not present");
			}

			// Validates the Resources Subtitle, is displayed
			if (ResourcesSubtitle.isDisplayed()
					& ResourcesSubtitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Subtitle is present");
			} else {
				isMyTestPassed = false;
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Subtitle is not present");
			}

			// Validates the Resources Description is displayed
			if (ResourcesDescription.isDisplayed()
					& ResourcesDescription.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Description is present");
			} else {
				isMyTestPassed = false;
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Description is not present");
			}

			// Validates the Resources Download Link is displayed
			if (ResourcesDownloadLink.isDisplayed()
					& ResourcesDownloadLink.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Download Link is present");
			} else {
				isMyTestPassed = false;
				LOGGER.info(this.myDriverParameters+
						"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-6: Resources Download Link is not present");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters+
					"MSG Sales Center Premium Hospitality Event Level Suites Page: Test Step-2: MSG Resources Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Availability Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS007() {

		String pageName = "MSG Sales Center Premium Hospitality Event Level Suites";
		String testNumber = "7";
		LOGGER.info(this.myDriverParameters+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Availability Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement availabilityEyebrow = getWebElement(driver, selectors,
					"AvailabilityEyebrow");
			WebElement availabilityTitle = getWebElement(driver, selectors,
					"AvailabilityTitle");
			WebElement availabilityDescription = getWebElement(driver,
					selectors, "AvailabilityDescription");
			List<WebElement> availabilityImages = getWebElements(driver,
					selectors, "AvailabilityImages");
			List<WebElement> availabilityCaptions = getWebElements(driver,
					selectors, "AvailabilityCaptions");
			isMyTestPassed = true;

			// Validates the Availability Eyebrow, is displayed
			isMyTestPassed = testText(availabilityEyebrow, pageName,
					"Availability Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Availability Title, is displayed
			isMyTestPassed = testText(availabilityTitle, pageName,
					"Availability Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Availability Description is displayed
			isMyTestPassed = testText(availabilityDescription, pageName,
					"Availability Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Availability Images is displayed
			if (availabilityImages.size() > 0) {
				for (int countImages = 0; countImages < availabilityImages
						.size(); countImages++) {
					isMyTestPassed = testVisual(
							availabilityImages.get(countImages), pageName,
							"Availability Images", testNumber, isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Availability Images are not present.");
			}

			// Validates the Availability Captions is displayed
			if (availabilityCaptions.size() > 0) {
				for (int countCaptions = 0; countCaptions < availabilityCaptions
						.size(); countCaptions++) {
					isMyTestPassed = testVisual(
							availabilityCaptions.get(countCaptions), pageName,
							"Availability Captions", testNumber,
							isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Availability Captions are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Availability Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Other Spaces Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS008() {

		String pageName = "MSG Sales Center Premium Hospitality Event Level Suites";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Other Spaces Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement otherSpacesTitle = getWebElement(driver, selectors,
					"OtherSpacesTitle");
			WebElement otherSpacesSubtitle = getWebElement(driver, selectors,
					"OtherSpacesSubtitle");
			List<WebElement> otherSpacesCardSlides = getWebElements(driver,
					selectors, "OtherSpacesCardSlides");
			List<WebElement> otherSpacesImages = getWebElements(driver,
					selectors, "OtherSpacesImages");
			List<WebElement> otherSpacesCardTitles = getWebElements(driver,
					selectors, "OtherSpacesCardTitles");
			List<WebElement> otherSpacesCardIcons = getWebElements(driver,
					selectors, "OtherSpacesCardIcons");
			List<WebElement> otherSpacesCardProperties = getWebElements(driver,
					selectors, "OtherSpacesCardProperties");
			isMyTestPassed = true;

			if (!isMobile) {
				// Validates the Other Spaces Title, is displayed
				isMyTestPassed = testText(otherSpacesTitle, pageName,
						"Other Spaces Title", testNumber, isMyTestPassed, this.myDriverParameters);
			}

			// Validates the Other Spaces Subtitle, is displayed
			isMyTestPassed = testText(otherSpacesSubtitle, pageName,
					"Other Spaces Subtitle", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Other Spaces, Card Slides are displayed, each with
			// image and title
			if (otherSpacesCardSlides.size() > 0) {
				for (int countCards = 0; countCards < otherSpacesCardSlides
						.size(); countCards++) {
					if (isMobile) {
						WebElement otherSpacesDotsContainer = getWebElement(
								driver, selectors, "OtherSpacesDotsContainer");
						List<WebElement> otherSpacesDots = getWebElements(
								driver, selectors, "OtherSpacesDots");
						moveToSlide(driver, countCards,
								otherSpacesDotsContainer, otherSpacesDots,
								otherSpacesCardSlides);
						isMyTestPassed = testVisual(
								otherSpacesImages.get(countCards), pageName,
								"Other Spaces Images", testNumber,
								isMyTestPassed, this.myDriverParameters);
					} else {
						isMyTestPassed = testText(
								otherSpacesCardTitles.get(countCards), pageName,
								"Other Spaces Titles", testNumber, isMyTestPassed, this.myDriverParameters);
					}
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Other Spaces, Card Slides are not present.");
			}

			// Validates the Other Spaces, Card Slides are displayed, each
			// propertie with its icon
			int slide = 0;
			if (isMobile) {
				WebElement otherSpacesDotsContainer = getWebElement(driver,
						selectors, "OtherSpacesDotsContainer");
				List<WebElement> otherSpacesDots = getWebElements(driver,
						selectors, "OtherSpacesDots");
				moveToSlide(driver, slide, otherSpacesDotsContainer,
						otherSpacesDots, otherSpacesCardSlides);
			}
			if (otherSpacesCardIcons.size() > 0) {
				for (int countProperties = 0; countProperties < otherSpacesCardIcons
						.size(); countProperties++) {
					if (isMobile && !otherSpacesCardIcons.get(countProperties)
							.isDisplayed()) {
						WebElement otherSpacesDotsContainer = getWebElement(
								driver, selectors, "OtherSpacesDotsContainer");
						List<WebElement> otherSpacesDots = getWebElements(
								driver, selectors, "OtherSpacesDots");
						slide++;
						moveToSlide(driver, slide, otherSpacesDotsContainer,
								otherSpacesDots, otherSpacesCardSlides);
					}
					isMyTestPassed = testVisual(
							otherSpacesCardIcons.get(countProperties), pageName,
							"Other Spaces Icons", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(
							otherSpacesCardProperties.get(countProperties),
							pageName, "Other Spaces Properties", testNumber, isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Other Spaces, Card Slides Properties are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Other Spaces Section, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	@Test(description = "Sales Center - MSG Event Level Suites Page. Other Spaces Section Tests", groups = {
			"MSGSalesCenterPremHospMSGEventLevelSuitesPage", "fullintegration"})
	public boolean MSGSalesCenterTC009PremHospMSGEventLevelSuitesPageTS009() {

		String pageName = "MSG Sales Center Premium Hospitality Event Level Suites";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Detail Other Venue Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement detailOtherVenueTitle = getWebElement(driver, selectors,
					"DetailOtherVenueTitle");
			List<WebElement> detailOtherVenueSlides = getWebElements(driver,
					selectors, "DetailOtherVenueSlides");
			isMyTestPassed = true;

			// Validates the Detail Other Venue Title, is displayed
			isMyTestPassed = testText(detailOtherVenueTitle, pageName,
					"Detail Other Venue Title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Other Spaces, Detail Other Venue Slides are
			// displayed, each with image and title
			if (detailOtherVenueSlides.size() > 0) {
				for (int countCards = 1; countCards <= detailOtherVenueSlides
						.size(); countCards++) {
					if (isMobile) {
						WebElement otherSpacesDotsContainer = getWebElement(driver,
								selectors, "DetailOtherVenueDotsContainer");
						List<WebElement> otherSpacesDots = getWebElements(driver, selectors,
								"DetailOtherVenueDots");
						moveToSlide(driver, countCards - 1,
								otherSpacesDotsContainer, otherSpacesDots,
								detailOtherVenueSlides);
					}
					WebElement detailOtherVenueSlideEyebrow = getWebElement(
							driver, selectors, "DetailOtherVenueSlideEyebrow",
							Integer.toString(countCards));
					WebElement detailOtherVenueSlideTitle = getWebElement(
							driver, selectors, "DetailOtherVenueSlideTitle",
							Integer.toString(countCards));
					WebElement detailOtherVenueSlideCTA = getWebElement(driver,
							selectors, "DetailOtherVenueSlideCTA",
							Integer.toString(countCards));
					isMyTestPassed = testText(detailOtherVenueSlideEyebrow,
							pageName, "Other Spaces Titles", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testText(detailOtherVenueSlideTitle,
							pageName, "Other Spaces Titles", testNumber, isMyTestPassed, this.myDriverParameters);
					isMyTestPassed = testVisual(detailOtherVenueSlideCTA,
							pageName, "Other Spaces Images", testNumber,
							isMyTestPassed, this.myDriverParameters);
				}
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
						+ ": Detail Other Venue, Slides are not present.");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Detail Other Venue, Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Visual Test Enablement for venue rentals Radio City Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Premium Hospitality Event Level Suites Page";
		String testNumber = "10";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
