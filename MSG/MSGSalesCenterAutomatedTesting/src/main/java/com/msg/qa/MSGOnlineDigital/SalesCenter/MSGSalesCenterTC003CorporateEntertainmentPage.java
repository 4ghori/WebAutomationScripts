package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElements;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.isIEBrowser;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.jsClick;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.removeIntercom;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollToElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollToTop;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.scrollUp;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.sleep;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.waitForElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.waitOnLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Corporate Entertainment Page
 */

public class MSGSalesCenterTC003CorporateEntertainmentPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC003CorporateEntertainmentPage.class);
	private static SoftAssert softAssert = new SoftAssert();
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile;
	String myDriverParameters = null;
	Boolean isIE;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC003CorporateEntertainmentPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC003CorporateEntertainmentPage(WebDriver driver,
			String URL, boolean isMyTestPassed,
			Map<String, String[]> selectors, String driverParameters) {
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
		this.isIE=isIEBrowser(capabilities.getBrowserName().toString());
		/**
		 * Adding Driver Parameters for Logging Purpose in Logger.
		 */
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Corporate Entertainment
	 * Page.
	 */
	@Test(description = "Are we on MSG Sales Center Corporate Entertainment Page?", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "smoke",
			"fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS001() {

		LOGGER.info(
				"MSG Sales Center Corporate Entertainment Page: Test Step-1: Verify Page Opens.");
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG Sales Center Corporate Entertainment Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-1: We are on MSG Sales Center Corporate Entertainment Page.");
			if (isMobile) {
				removeIntercom(driver);
			}
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-1: We are not on MSG Sales Center Corporate Entertainment Page!!");
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
	@Test(description = "Sales Center - Corporate Entertainment. Hero Tests", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS002() {

		LOGGER.info(
				"MSG Sales Center Corporate Entertainment Page: Test Step-2: Verify Hero Section.");
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
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: First Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(myDriverParameters=
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: First Line for Hero Title is not present");
			}

			// Validates the Hero Title, line2, is displayed
			if (myHeroTitle2ndLine.isDisplayed()
					& myHeroTitle2ndLine.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Second Line for Hero Title is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Second Line for Hero Title is not present");
			}

			// Validates the Hero Subtitle is displayed
			if (myHeroSubtitle.isDisplayed()
					& myHeroSubtitle.getText() != null) {
				LOGGER.info(
						"MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Subtitle is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Subtitle is not present");
			}

			// Validates the Hero Description is displayed
			if (myHeroDetail.isDisplayed() & myHeroDetail.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Detail paragraph is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Detail paragraph is not present");
			}

			// Validates the Hero Image is displayed
			if (myHeroImage.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Image is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-2: Hero Image is not present");
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-2: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	private void scrollToSecondaryNav(WebElement navTitle, WebElement link) {
		if (isMobile) {
			scrollToElement(driver, navTitle);
			scrollUp(driver);
			jsClick(driver, navTitle);
		} else {
			scrollToElement(driver, link);
			scrollUp(driver);
		}
	}

	/**
	 * Step-3. Verify the Secondary Navigation Bar: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Second NavBar Title -
	 * Present and not empty b. Second NavBar Accesses - Present and not empty
	 */
	@Test(description = "Sales Center - Corporate Entertainment. Secondary Navigation Tests", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS003() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Verify Second Navigation Bar Section.");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySecondNavTitle = getWebElement(driver, selectors,
					"L2PageSecondaryNavTitle");
			WebElement mySecondNavPremiumLink = getWebElement(driver, selectors,
					"L2PageSecondaryNavLink", "Premium Hospitality");
			WebElement mySecondNavVenueLink = getWebElement(driver, selectors,
					"L2PageSecondaryNavLink", "Venue Rentals");
			isMyTestPassed = true;

			//SC-2080 Saucelabs IE11 has known issues, check SC-649 for details
			if(!isIE) {
				// Validates the Secondary Nav Title is displayed
				if (mySecondNavTitle.isDisplayed()
						& mySecondNavTitle.getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Secondary Nav Title is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Secondary Nav Title is not present");
				}

				// Validates the Secondary Nav Link is working
				scrollToTop(driver);
				scrollToSecondaryNav(mySecondNavTitle, mySecondNavPremiumLink);
				sleep(3);
				jsClick(driver, mySecondNavPremiumLink);
				if (driver.getCurrentUrl().toString()
						.contains("/corporate-entertainment#premium-hospitality")) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Icon 1 on Navigation Bar Scrolled to the expected section. Current URL: "
									+ driver.getCurrentUrl());
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - Expected /corporate-entertainment#premium-hospitality but found >> CURRENT URL: "
									+ driver.getCurrentUrl());
				}

				// Validates the Secondary Nav Link is working
				scrollToTop(driver);
				scrollToSecondaryNav(mySecondNavTitle, mySecondNavVenueLink);
				jsClick(driver, mySecondNavVenueLink);
				sleep(3);
				if (driver.getCurrentUrl().toString()
						.contains("/corporate-entertainment#venue-rentals") || driver.getCurrentUrl().toString()
						.contains("/corporate-entertainment#premium-hospitality")) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Icon 2 on Navigation Bar Scrolled to the expected section. Current URL: "
									+ driver.getCurrentUrl());
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - Expected /corporate-entertainment#venue-rentals but found >> CURRENT URL: "
									+ driver.getCurrentUrl());
				}
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-3: Secondary NavBar not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-4. Verify the Event Section on Sports.
	 */
	@Test(description = "Sales Center - Corporate Entertainment. Events Tests", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS004() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Verify Solo Event");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement mySoloEventTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventTitle");
			WebElement mySoloEventDescription = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventDescription");
			WebElement mySoloEventCTA = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventCTA");
			WebElement mySoloEventLink = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventLink");
			WebElement mySoloEventImage = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventImage");
			WebElement mySoloEventDisclaimer = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageSoloEventDisclaimer");
			isMyTestPassed = true;

			//SC-2080 Saucelabs IE11 has known issues, check SC-649 for details
			if(!isIE) {
				scrollToElement(driver,mySoloEventTitle);
				scrollUp(driver);
				// Tests the Title is shown and not empty
				if (mySoloEventTitle.isDisplayed()
						& mySoloEventTitle.getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Title exists");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Title does not exists");
				}
	
				// Tests the Description is shown and not empty
				if (mySoloEventDescription.isDisplayed()
						& mySoloEventDescription.getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Description exists");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Description does not exist");
				}
	
				// Tests the CTA is shown and is enabled
				if (mySoloEventCTA.isDisplayed() && mySoloEventCTA.isEnabled()) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event CTA is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event CTA is not present");
				}
	
				// Tests the Link is shown and not empty
				if (mySoloEventLink.isDisplayed()
						& mySoloEventLink.getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Link is present");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Link is not present");
				}
	
				// Tests the Event Image exists
				if (mySoloEventImage.isDisplayed()) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Image exists");
				} else {
					isMyTestPassed = false;
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Image does not exist");
				}
	
				// Tests the Disclaimer is shown and not empty
				if(!isMobile) {
					if (mySoloEventDisclaimer.isDisplayed()
							& mySoloEventDisclaimer.getText() != null) {
						LOGGER.info(this.myDriverParameters+
								" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Disclaimer is present");
					} else {
						isMyTestPassed = false;
						LOGGER.error(this.myDriverParameters+
								" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Solo Event Disclaimer is not present");
					}
				}
			}
			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-4: Event Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-5. Verify the Feature Venue Section
	 */
	@Test(description = "Sales Center - Corporate Entertainment. Feature Venue Tests", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS005() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Verify Feature Venue Card");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myFeatureVenueTitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageFeatureVenueTitle");
			WebElement myFeatureVenueDescription = getWebElement(driver,
					selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageFeatureVenueDescription");
			WebElement myFeatureVenueSubtitle = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageFeatureVenueSubtitle");
			WebElement myFeatureVenueLink = getWebElement(driver, selectors,
					"MSGSalesCenterTC003CorporateEntertainmentPageFeatureVenueLink");
			isMyTestPassed = true;

			// Tests the Title is shown and not empty
			if (myFeatureVenueTitle.isDisplayed()
					& myFeatureVenueTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Title exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Title does not exists");
			}

			// Tests the Description is shown and not empty
			if (myFeatureVenueDescription.isDisplayed()
					& myFeatureVenueDescription.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Description exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Description does not exist");
			}

			// Tests the CTA is shown and is enabled
			if (myFeatureVenueSubtitle.isDisplayed()
					&& myFeatureVenueSubtitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue CTA is not present");
			}

			// Tests the Link is shown and not empty
			if (myFeatureVenueLink.isDisplayed()
					& myFeatureVenueLink.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Link is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Link is not present");
			}

			// Tests the Venue is accessed via Link
			scrollToElement(driver, myFeatureVenueLink);
			scrollUp(driver);
			myFeatureVenueLink.click();
			sleep(3);
			if (driver.getCurrentUrl().contains("/venue-rentals")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-5: MSG Corporate Entertainment, Feature Venue Link accessed the expected url: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - >> MSG Corporate Entertainment, Feature Venue Link failed. ACCESSED URL: "
								+ driver.getCurrentUrl());
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-5: Feature Venue Card not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Step-6. Verify the Feature Venue Section
	 */

	private void changeSlide(int index, WebElement webElement) {
		if (isMobile) {
			List<WebElement> mySlideDots = getWebElements(driver, selectors,
					"VenueSlidesDots");
			scrollToElement(driver, mySlideDots.get(index));
			scrollUp(driver);
			mySlideDots.get(1).click();
			waitForElement(driver, webElement, 15);
		}
	}
	@Test(description = "Sales Center - Corporate Entertainment. MSG Slides Tests", groups = {
			"MSGSalesCenterCorporateEntertainmentPage", "fullintegration"})
	public boolean MSGSalesCenterTC003CorporateEntertainmentPageTS006() {

		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Verify Slide Card");
		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			driver.navigate().to(this.URL);
			if (isMobile) {
				removeIntercom(driver);
			}
			WebElement mySlideEyebrowLeft = getWebElement(driver, selectors,
					"VenueSlidesEyebrowLeft");
			isMyTestPassed = true;

			scrollToElement(driver, mySlideEyebrowLeft);
			scrollUp(driver);
			sleep(3);

			// Tests the Left Eyebrow is shown and not empty
			if (mySlideEyebrowLeft.isDisplayed()
					& mySlideEyebrowLeft.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Title exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Title does not exist");
			}

			// Tests the Event is accessed via CTA
			mySlideEyebrowLeft.click();
			waitOnLoader(driver);
			WebElement mySlideVenueName = getWebElement(driver, selectors,
					"VenuePageHeroVenueName", "Madison Square Garden Arena");
			LOGGER.info(" Accessed Venue: " + mySlideVenueName.getText());
			waitForElement(driver, mySlideVenueName, 15);
			if (driver.getCurrentUrl()
					.contains("/venue-rentals/madison-square-garden")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: MSG Corporate Entertainment, Slide Left Eyebrow accessed the expected url: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - >> MSG Corporate Entertainment, Slide Left Eyebrow failed. ACCESSED URL: "
								+ driver.getCurrentUrl());
			}

			// Return to previous screen
			driver.navigate().back();
			sleep(5);
			if (isMobile) {
				removeIntercom(driver);
			}

			// Tests the Left Image is shown
			WebElement mySlideImageLeft = getWebElement(driver, selectors,
					"VenueSlidesImageLeft");
			if (mySlideImageLeft.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Left Image exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Left Image does not exist");
			}

			// Tests the CTA is shown and is enabled
			WebElement mySlideCTALeft = getWebElement(driver, selectors,
					"VenueSlidesCTALeft");
			if (mySlideCTALeft.isDisplayed() && mySlideCTALeft.isEnabled()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Left CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Left CTA is not present");
			}

			// Tests the Event is accessed via CTA
			scrollToElement(driver, mySlideCTALeft);
			scrollUp(driver);
			jsClick(driver, mySlideCTALeft);
			waitOnLoader(driver);
			mySlideVenueName = getWebElement(driver, selectors,
					"VenuePageHeroVenueName", "Madison Square Garden Arena");
			LOGGER.info(" Accessed Venue: " + mySlideVenueName.getText());
			if (driver.getCurrentUrl()
					.contains("/venue-rentals/madison-square-garden")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: MSG Corporate Entertainment, Slide Left CTA accessed the expected url: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - >> MSG Corporate Entertainment, Solo Event Link failed. ACCESSED URL: "
								+ driver.getCurrentUrl());
			}

			// Return to previous screen
			driver.navigate().back();
			sleep(5);
			if (isMobile) {
				removeIntercom(driver);
			}

			// Tests the Right Eyebrow is shown and not empty
			WebElement mySlideEyebrowRight = getWebElement(driver, selectors,
					"VenueSlidesEyebrowRight");
			changeSlide(1, mySlideEyebrowRight);
			if (mySlideEyebrowRight.isDisplayed()
					& mySlideEyebrowRight.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Solo Event Title exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Solo Event Title does not exist");
			}

			// Tests the Event is accessed via CTA
			scrollToElement(driver, mySlideEyebrowRight);
			scrollUp(driver);
			jsClick(driver, mySlideEyebrowRight);
			waitOnLoader(driver);
			mySlideVenueName = getWebElement(driver, selectors,
					"VenuePageHeroVenueName", "Radio City Music Hall");
			LOGGER.info(" Accessed Venue: " + mySlideVenueName.getText());
			if (driver.getCurrentUrl()
					.contains("/venue-rentals/radio-city-music-hall")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: MSG Corporate Entertainment, Slide Right Eyebrow accessed the expected url: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - >> MSG Corporate Entertainment, Slide Right Eyebrow failed. ACCESSED URL: "
								+ driver.getCurrentUrl());
			}

			// Return to previous screen
			driver.navigate().back();
			sleep(5);
			if (isMobile) {
				removeIntercom(driver);
			}

			// Tests the Right Image is shown
			WebElement mySlideImageRight = getWebElement(driver, selectors,
					"VenueSlidesImageRight");
			changeSlide(1, mySlideImageRight);
			if (mySlideImageRight.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Right Image exists");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Right Image does not exist");
			}

			// Tests the CTA is shown and is enabled
			WebElement mySlideCTARight = getWebElement(driver, selectors,
					"VenueSlidesCTARight");
			changeSlide(1, mySlideCTARight);
			if (mySlideCTARight.isDisplayed() && mySlideCTARight.isEnabled()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Right CTA is present");
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Slide Right CTA is not present");
			}

			// Tests the Event is accessed via CTA
			jsClick(driver, mySlideCTARight);
			waitOnLoader(driver);
			mySlideVenueName = getWebElement(driver, selectors,
					"VenuePageHeroVenueName", "Radio City Music Hall");
			LOGGER.info(" Accessed Venue: " + mySlideVenueName.getText());

			if (driver.getCurrentUrl()
					.contains("/venue-rentals/radio-city-music-hall")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Corporate Entertainment Page: Test Step-6: MSG Corporate Entertainment, Slide Right CTA accessed the expected url: "
								+ driver.getCurrentUrl());
			} else {
				isMyTestPassed = false;
				LOGGER.error(this.myDriverParameters+
						" - >> MSG Corporate Entertainment, Solo Event Link failed. ACCESSED URL: "
								+ driver.getCurrentUrl());
			}

			// Return to previous screen
			driver.navigate().back();
			sleep(5);
			if (isMobile) {
				removeIntercom(driver);
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Corporate Entertainment Page: Test Step-6: Solo Event not working as expected!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return false;
		}

	}

	/**
	 * Visual Test Enablement for Venue rental landing Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Corporate Entertainment Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
	
}
