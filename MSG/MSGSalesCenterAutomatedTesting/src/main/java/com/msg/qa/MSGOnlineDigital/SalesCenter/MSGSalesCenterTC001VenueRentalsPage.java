package com.msg.qa.MSGOnlineDigital.SalesCenter;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

/**
 * Utility class for JIRA REST API
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Sales Center Venues Rental Page
 * @since 06/06/2017, 10:30AM EST
 */

public class MSGSalesCenterTC001VenueRentalsPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGSalesCenterTC001VenueRentalsPage.class);

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	boolean isMobile;
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterTC001VenueRentalsPage() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterTC001VenueRentalsPage(WebDriver driver, String URL,
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
		/**
		 * Adding Driver Parameters for Logging Purpose in Logger.
		 * By Rachit Kumar Rastogi on 12/08/2017
		 */
		this.myDriverParameters = driverParameters;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Venue Rentals Page.
	 */
	@Test(description = "Are we on MSG Sales Center Venue Rentals Page?", groups = {
			"MSGSalesCenterVenueRentalsPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC001VenueRentalsPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG Sales Center Venue Rentals Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-1: We are on MSG Sales Center Venue Rentals Page.");
			if (isMobile) {
				removeIntercom(driver);
			}
		} catch (Exception e) {
			isMyTestPassed = false;
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-1: We are not on MSG Sales Center Venue Rentals Page!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify the Header Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Company Logo - Present b.
	 * All clickable links redirect to a new window or appropriate section in
	 * the venue page - Verified c. Email - Leads to outlook email compose
	 * section d. Call link - Leads to contact us /(acc to bus req)
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS002() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-2: Verify Header Section.");
		try {
			WebElement myMSGLogo = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageSvgMSGLogo");
			WebElement myMSGNavLinks = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageNavLinks");
			isMyTestPassed = true;

			if (myMSGLogo.getAttribute("data-name").toString()
					.equals("msglogo")) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-2: MSG Logo is Present.");
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-2: MSG Logo is missing!!");
				isMyTestPassed = false;
			}

			if (myMSGNavLinks.isDisplayed()) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are present.");
				// Perform Further validation in the nav Options.
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are missing!!");
				isMyTestPassed = false;
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-3. Verify the Venue Rentals Section.
	 *
	 * @throws Exception
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS003() throws Exception {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-3: Verify Venue Rentals Section.");
		try {
			WebElement myVenueTitlePart1 = getWebElement(driver, selectors,
					"L2PageHeroTitle1stLine");
			WebElement myVenueTitleText1 = getWebElement(driver, selectors,
					"L2PageHeroSubtitle");
			WebElement myVenueTitleText2 = getWebElement(driver, selectors,
					"L2PageHeroDetail");
			WebElement myVenueImage = getWebElement(driver, selectors,
					"L2PageHeroImage");
			isMyTestPassed = true;

			if (myVenueTitlePart1.isDisplayed()
					& myVenueTitlePart1.getText() != null) {
				WebElement myVenueTitlePart2 = getWebElement(driver, selectors,
						"L2PageHeroTitle2ndLine");
				if (myVenueTitlePart2.isDisplayed()
						& myVenueTitlePart2.getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered is: "
									+ myVenueTitlePart1.getText() + " "
									+ myVenueTitlePart2.getText());
				} else {
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-3: MSG Nav Titles are missing!!");
					isMyTestPassed = false;
				}
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: MSG Nav Titles are missing!!");
				isMyTestPassed = false;
			}

			if (myVenueTitleText1.isDisplayed()
					& myVenueTitleText1.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (small) is: "
								+ myVenueTitleText1.getText());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (small) is missing!!");
				isMyTestPassed = false;
			}

			if (myVenueTitleText2.isDisplayed()
					& myVenueTitleText2.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (long) is: "
								+ myVenueTitleText2.getText());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (long) is missing!!");
				isMyTestPassed = false;
			}


			String testEnvironment = driver.getCurrentUrl();

			testEnvironment = testEnvironment.substring(0, testEnvironment.lastIndexOf('/'));

			String imageSrc = myVenueImage.getAttribute("src").toString();

			String imageURL = imageSrc.startsWith("http") ? imageSrc : testEnvironment.concat(imageSrc);

			if (myVenueImage.isDisplayed()
					&& MSGOnlineDigitalReusableFunctionalities
							.verifyIfLinkIsWorking(imageURL) != 404) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered Image URL is: "
								+ myVenueImage.getAttribute("src").toString());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered Image is missing!!");
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-3 is having issues.");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-4. Verify the Browse By Venue Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS004() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-4: Verify Browse By Venue Section.");
		try {

			if (isMobile) {
				removeIntercom(driver);
				sleep(2);
				WebElement pageSubNavButton = getWebElement(driver, selectors,
						"L2PageSecondaryNavTitle");
				scrollToElement(driver, pageSubNavButton);
				sleep(3);
				scrollUp(driver, 80);
				sleep(2);
				jsClick(driver, pageSubNavButton);
				sleep(5);
			}

			List<WebElement> myVenues = getWebElements(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTotalVenueCount");
			List<WebElement> myVenuesTitles = getWebElements(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageVenueTitles");
			isMyTestPassed = true;

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-4: Total Venues are "
							+ myVenues.size());

			int count = 1;
			for (int i = 0; i < myVenuesTitles.size(); i++) {
				if (myVenuesTitles.get(i).isDisplayed()
						& myVenuesTitles.get(i).getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-4: Venue"
									+ count + " Title & location is: "
									+ myVenuesTitles.get(i).getText());
				} else {
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-4: Title & location ("
									+ i + ") is missing!!");
					isMyTestPassed = false;
				}
				count++;
			}
			if (isMobile) {
				LOGGER.info(" >>> IS MOBILE//");
				WebElement myMSGVenueDropdownClose = getWebElement(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPagePageSubNavButtonClose");
				myMSGVenueDropdownClose.click();
				sleep(5);
			}
			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-4: Browse by venue section is missing on validation!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-5. Verify the Madison Square Garden Venue Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS005() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-5: Verify Madison Square Garden Venue Section.");
		try {

			WebElement myMSGVenueTitle = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGVenueTitle");
			WebElement myMSGVenueLocation = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGVenueLoc");
			WebElement myMSGVenueSpaceCountOnDisplay = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesDisplayCount");
			List<WebElement> myMSGVenueTotalSpaces = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesCount");
			List<WebElement> myMSGVenueSpaceTitles = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesTitles");
			List<WebElement> myMSGVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesLocParam");
			List<WebElement> myMSGVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesImages");
			isMyTestPassed = true;

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myMSGVenueTitle);

			if (myMSGVenueTitle.isDisplayed()
					& myMSGVenueTitle.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Title: "
								+ myMSGVenueTitle.getText());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Title is missing!!");
				isMyTestPassed = false;
			}

			scrollToElement(driver,myMSGVenueLocation);
			scrollUp(driver);
			if (myMSGVenueLocation.isDisplayed()
					& myMSGVenueLocation.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Location: "
								+ myMSGVenueLocation.getText());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Location is missing!!");
				isMyTestPassed = false;
			}

			if (myMSGVenueSpaceCountOnDisplay.isDisplayed()
					& myMSGVenueSpaceCountOnDisplay.getText() != null) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Spaces: "
								+ myMSGVenueSpaceCountOnDisplay.getText()
										.split(" ")[0]);
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Spaces number is missing!!");
				isMyTestPassed = false;
			}

			if (Integer.valueOf(myMSGVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myMSGVenueTotalSpaces.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Spaces displayed below are: "
								+ myMSGVenueTotalSpaces.size());
			} else {
				LOGGER.error(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Venue Spaces count is missing!!");
				isMyTestPassed = false;
			}

			List<WebElement> myMSGVenueSlickDots = null;
			WebElement myMSGVenueSlickDotsContainer = null;
			if (isMobile) {
				myMSGVenueSlickDots = getWebElements(driver, selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDots", "0");
				myMSGVenueSlickDotsContainer = getWebElement(driver, selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDotsContainer",
						"0");
			}

			List<WebElement> myMSGVenueLocationCapacity = new ArrayList<>();
			int slide = 0;
			for (int j = 0; j < myMSGVenueLocationParameter.size(); j++) {
				if (isMobile
						&& !myMSGVenueLocationParameter.get(j).isDisplayed()) {
					moveToSlide(driver, slide, myMSGVenueSlickDotsContainer,
							myMSGVenueSlickDots, myMSGVenueSpaceTitles);
				}
				if (myMSGVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myMSGVenueLocationCapacity
							.add(myMSGVenueLocationParameter.get(j));
					slide++;
				}
			}

			int count = 1;
			if (isMobile) {
				myMSGVenueSlickDots = getWebElements(driver, selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDots", "0");
			}

			for (int i = 0; i < myMSGVenueSpaceTitles.size(); i++) {

				if (isMobile) {
					moveToSlide(driver, i, myMSGVenueSlickDotsContainer,
							myMSGVenueSlickDots, myMSGVenueSpaceTitles);
				}

				if (myMSGVenueSpaceTitles.get(i).isDisplayed()
						& myMSGVenueSpaceTitles.get(i).getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Space("
									+ i + ") Title is: "
									+ myMSGVenueSpaceTitles.get(i).getText());
				} else {
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Space("
									+ i + ") Title is missing!!");
					isMyTestPassed = false;
				}

				if (myMSGVenueLocationCapacity.get(i).isDisplayed()
						& myMSGVenueLocationCapacity.get(i).getText() != null) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Space("
									+ i + ") Location Parameter are: "
									+ myMSGVenueLocationCapacity.get(i)
											.getText());
				} else {
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Space("
									+ i + ") Location Parameter is missing!!");
					isMyTestPassed = false;
				}

				count++;
			}

			if (Integer.valueOf(myMSGVenueSpaceCountOnDisplay
					.getText()) == (myMSGVenueTotalSpacesImages.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myMSGVenueTotalSpacesImages.size(); j++) {
					if (myMSGVenueTotalSpacesImages.get(j).isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myMSGVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);

				if (myMSGVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myMSGVenueTitle.getText() + " are: "
									+ (myMSGVenueTotalSpacesImages.size()
											- myDisplayedImagesCount));
				}
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-5: Missing conditions in Madison Square Garden Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-6. Verify the Theater at MSG Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS006() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-6: Verify Theater at MSG Section.");
		try {

			String[] mySelector = this.selectors.get("");

			WebElement myTheaterAtMSGVenueTitle = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGVenueTitle");
			WebElement myTheaterAtMSGVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGVenueLoc");
			WebElement myTheaterAtMSGVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesDisplayCount");
			List<WebElement> myTheaterAtMSGVenueTotalSpaces = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesCount");
			List<WebElement> myTheaterAtMSGVenueSpaceTitles = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesTitles");
			List<WebElement> myTheaterAtMSGVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesLocParam");
			List<WebElement> myTheaterAtMSGVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myTheaterAtMSGVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-6: Venue Title: "
							+ myTheaterAtMSGVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-6: Venue Location: "
							+ myTheaterAtMSGVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-6: Venue Spaces: "
							+ myTheaterAtMSGVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);
			if (Integer.valueOf(myTheaterAtMSGVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTheaterAtMSGVenueTotalSpaces
							.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-6: Total Spaces displayed below are: "
								+ myTheaterAtMSGVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myTheaterAtMSGVenueLocationCapacity = new ArrayList<WebElement>();

			List<WebElement> myTheaterAtMSGVenueSlickDots = null;
			WebElement myTheaterAtMSGVenueSlickDotsContainer = null;
			if (isMobile) {
				myTheaterAtMSGVenueSlickDots = getWebElements(driver, selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDots", "1");
				myTheaterAtMSGVenueSlickDotsContainer = getWebElement(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDotsContainer",
						"1");
			}

			int slide = 0;
			for (int j = 0; j < myTheaterAtMSGVenueLocationParameter
					.size(); j++) {
				if (!myTheaterAtMSGVenueLocationParameter.get(j)
						.isDisplayed()) {
					moveToSlide(driver, slide,
							myTheaterAtMSGVenueSlickDotsContainer,
							myTheaterAtMSGVenueSlickDots,
							myTheaterAtMSGVenueSpaceTitles);
				}
				if (myTheaterAtMSGVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myTheaterAtMSGVenueLocationCapacity
							.add(myTheaterAtMSGVenueLocationParameter.get(j));
					slide++;
				}
			}

			int count = 1;
			for (int i = 0; i < myTheaterAtMSGVenueSpaceTitles.size(); i++) {
				if (isMobile) {
					moveToSlide(driver, i,
							myTheaterAtMSGVenueSlickDotsContainer,
							myTheaterAtMSGVenueSlickDots,
							myTheaterAtMSGVenueSpaceTitles);
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-6: Space("
								+ count + ") Title is: "
								+ myTheaterAtMSGVenueSpaceTitles.get(i)
										.getText());
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-6: Space("
								+ count + ") Location Parameter are: "
								+ myTheaterAtMSGVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			if (Integer.valueOf(myTheaterAtMSGVenueSpaceCountOnDisplay
					.getText()) == (myTheaterAtMSGVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myTheaterAtMSGVenueTotalSpacesImages
						.size(); j++) {
					if (myTheaterAtMSGVenueTotalSpacesImages.get(j)
							.isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myTheaterAtMSGVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);
				if (myTheaterAtMSGVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myTheaterAtMSGVenueTitle.getText()
									+ " are: "
									+ (myTheaterAtMSGVenueTotalSpacesImages
											.size() - myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-6: Missing conditions in Theatre at MSG Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-7. Verify the Radio City Music Hall Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS007() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-7: Verify Radio City Music Hall Section.");
		try {
			WebElement myRadioCityVenueTitle = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCityVenueTitle");
			WebElement myRadioCityVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCityVenueLoc");
			WebElement myRadioCityVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesDisplayCount");
			List<WebElement> myRadioCityVenueTotalSpaces = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesCount");
			List<WebElement> myRadioCityVenueSpaceTitles = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesTitles");
			List<WebElement> myRadioCityVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesLocParam");
			List<WebElement> myRadioCityVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myRadioCityVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-7: Venue Title: "
							+ myRadioCityVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-7: Venue Location: "
							+ myRadioCityVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-7: Venue Spaces: "
							+ myRadioCityVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			if (Integer.valueOf(myRadioCityVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myRadioCityVenueTotalSpaces.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-7: Total Spaces displayed below are: "
								+ myRadioCityVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myRadioCityVenueLocationCapacity = new ArrayList<WebElement>();

			List<WebElement> myRadioCityVenueSlickDots = null;
			WebElement myRadioCityVenueSlickDotsContainer = null;
			if (isMobile) {
				myRadioCityVenueSlickDots = getWebElements(driver, selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDots", "2");
				myRadioCityVenueSlickDotsContainer = getWebElement(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDotsContainer",
						"2");
			}

			int slide = 0;
			for (int j = 0; j < myRadioCityVenueLocationParameter.size(); j++) {
				if (!myRadioCityVenueLocationParameter.get(j).isDisplayed()) {
					moveToSlide(driver, slide,
							myRadioCityVenueSlickDotsContainer,
							myRadioCityVenueSlickDots,
							myRadioCityVenueSpaceTitles);
				}
				if (myRadioCityVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myRadioCityVenueLocationCapacity
							.add(myRadioCityVenueLocationParameter.get(j));
					slide++;
				}
			}

			int count = 1;
			for (int i = 0; i < myRadioCityVenueSpaceTitles.size(); i++) {
				if (isMobile) {
					moveToSlide(driver, i, myRadioCityVenueSlickDotsContainer,
							myRadioCityVenueSlickDots,
							myRadioCityVenueSpaceTitles);
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-7: Space("
								+ count + ") Title is: "
								+ myRadioCityVenueSpaceTitles.get(i).getText());
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-7: Space("
								+ count + ") Location Parameter are: "
								+ myRadioCityVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			if (Integer.valueOf(myRadioCityVenueSpaceCountOnDisplay
					.getText()) == (myRadioCityVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myRadioCityVenueTotalSpacesImages
						.size(); j++) {
					if (myRadioCityVenueTotalSpacesImages.get(j)
							.isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}

				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myRadioCityVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);

				if (myRadioCityVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myRadioCityVenueTitle.getText() + " are: "
									+ (myRadioCityVenueTotalSpacesImages.size()
											- myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-7:  Missing conditions in Radio City music Hall Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-8. Verify the Beacon Theatre Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS008() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-8: Verify Beacon Theatre Section.");
		try {
			WebElement myBeaconTheatreVenueTitle = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreVenueTitle");
			WebElement myBeaconTheatreVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreVenueLoc");
			WebElement myBeaconTheatreVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesDisplayCount");
			List<WebElement> myBeaconTheatreVenueTotalSpaces = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesCount");
			List<WebElement> myBeaconTheatreVenueSpaceTitles = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesTitles");
			List<WebElement> myBeaconTheatreVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesLocParam");
			List<WebElement> myBeaconTheatreVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myBeaconTheatreVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-8: Venue Title: "
							+ myBeaconTheatreVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-8: Venue Location: "
							+ myBeaconTheatreVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-8: Venue Spaces: "
							+ myBeaconTheatreVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			if (Integer
					.valueOf(myBeaconTheatreVenueSpaceCountOnDisplay.getText()
							.split(" ")[0]) == (myBeaconTheatreVenueTotalSpaces
									.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-8: Total Spaces displayed below are: "
								+ myBeaconTheatreVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myBeaconTheatreVenueLocationCapacity = new ArrayList<WebElement>();

			for (int j = 0; j < myBeaconTheatreVenueLocationParameter
					.size(); j++) {
				if (myBeaconTheatreVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myBeaconTheatreVenueLocationCapacity
							.add(myBeaconTheatreVenueLocationParameter.get(j));
				}
			}

			int count = 1;
			for (int i = 0; i < myBeaconTheatreVenueSpaceTitles.size(); i++) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-8: Space("
								+ count + ") Title is: "
								+ myBeaconTheatreVenueSpaceTitles.get(i)
										.getText());
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-8: Space("
								+ count + ") Location Parameter are: "
								+ myBeaconTheatreVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			if (Integer.valueOf(myBeaconTheatreVenueSpaceCountOnDisplay
					.getText()) == (myBeaconTheatreVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myBeaconTheatreVenueTotalSpacesImages
						.size(); j++) {
					if (myBeaconTheatreVenueTotalSpacesImages.get(j)
							.isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}

				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myBeaconTheatreVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);
				if (myBeaconTheatreVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myBeaconTheatreVenueTitle.getText()
									+ " are: "
									+ (myBeaconTheatreVenueTotalSpacesImages
											.size() - myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-8:  Missing conditions in Beacon Theatre Section!! ");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-9. Verify the Chicago Theatre Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS009() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-9: Verify Chicago Theatre Section.");
		try {
			WebElement myChicagoTheatreVenueTitle = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreVenueTitle");
			WebElement myChicagoTheatreVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreVenueLoc");
			WebElement myChicagoTheatreVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesDisplayCount");
			List<WebElement> myChicagoTheatreVenueTotalSpaces = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesCount");
			List<WebElement> myChicagoTheatreVenueSpaceTitles = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesTitles");
			List<WebElement> myChicagoTheatreVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesLocParam");
			List<WebElement> myChicagoTheatreVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myChicagoTheatreVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-9: Venue Title: "
							+ myChicagoTheatreVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-9: Venue Location: "
							+ myChicagoTheatreVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-9: Venue Spaces: "
							+ myChicagoTheatreVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			if (Integer
					.valueOf(myChicagoTheatreVenueSpaceCountOnDisplay.getText()
							.split(" ")[0]) == (myChicagoTheatreVenueTotalSpaces
									.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-9: Total Spaces displayed below are: "
								+ myChicagoTheatreVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myChicagoTheatreVenueLocationCapacity = new ArrayList<WebElement>();

			List<WebElement> myChicagoTheatreVenueSlickDots = null;
			WebElement myChicagoTheatreVenueSlickDotsContainer = null;
			if (isMobile) {
				myChicagoTheatreVenueSlickDots = getWebElements(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDots", "4");
				myChicagoTheatreVenueSlickDotsContainer = getWebElement(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageSlickDotsContainer",
						"4");
			}

			int slide = 0;
			for (int j = 0; j < myChicagoTheatreVenueLocationParameter
					.size(); j++) {
				if (!myChicagoTheatreVenueLocationParameter.get(j)
						.isDisplayed()) {
					moveToSlide(driver, slide,
							myChicagoTheatreVenueSlickDotsContainer,
							myChicagoTheatreVenueSlickDots,
							myChicagoTheatreVenueSpaceTitles);
				}
				if (myChicagoTheatreVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myChicagoTheatreVenueLocationCapacity
							.add(myChicagoTheatreVenueLocationParameter.get(j));
					slide++;
				}
			}

			int count = 1;

			for (int i = 0; i < myChicagoTheatreVenueSpaceTitles.size(); i++) {
				if (isMobile) {
					moveToSlide(driver, i,
							myChicagoTheatreVenueSlickDotsContainer,
							myChicagoTheatreVenueSlickDots,
							myChicagoTheatreVenueSpaceTitles);
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-9: Space("
								+ count + ") Title is: "
								+ myChicagoTheatreVenueSpaceTitles.get(i)
										.getText());
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-9: Space("
								+ count + ") Location Parameter are: "
								+ myChicagoTheatreVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			if (Integer.valueOf(myChicagoTheatreVenueSpaceCountOnDisplay
					.getText()) == (myChicagoTheatreVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myChicagoTheatreVenueTotalSpacesImages
						.size(); j++) {
					if (myChicagoTheatreVenueTotalSpacesImages.get(j)
							.isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-9: Total Images displayed for "
								+ myChicagoTheatreVenueTitle.getText()
								+ " are: " + myDisplayedImagesCount);
				if (myChicagoTheatreVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-9: Total Images missed for "
									+ myChicagoTheatreVenueTitle.getText()
									+ " are: "
									+ (myChicagoTheatreVenueTotalSpacesImages
											.size() - myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-9: Missing conditions in Chicago Theatre Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-10. Verify the The Forum Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS010() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-10: Verify The Forum Section.");
		try {
			WebElement myTheForumVenueTitle = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumVenueTitle");
			WebElement myTheForumVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumVenueLoc");
			WebElement myTheForumVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesDisplayCount");
			List<WebElement> myTheForumVenueTotalSpaces = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesCount");
			List<WebElement> myTheForumVenueSpaceTitles = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesTitles");
			List<WebElement> myTheForumVenueLocationParameter = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesLocParam");
			List<WebElement> myTheForumVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myTheForumVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-10: Venue Title: "
							+ myTheForumVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-10: Venue Location: "
							+ myTheForumVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-10: Venue Spaces: "
							+ myTheForumVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			if (Integer.valueOf(myTheForumVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTheForumVenueTotalSpaces.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-10: Total Spaces displayed below are: "
								+ myTheForumVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myTheForumVenueLocationCapacity = new ArrayList<WebElement>();
			for (int j = 0; j < myTheForumVenueLocationParameter.size(); j++) {
				if (myTheForumVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myTheForumVenueLocationCapacity
							.add(myTheForumVenueLocationParameter.get(j));
				}
			}

			int count = 1;

			for (int i = 0; i < myTheForumVenueSpaceTitles.size(); i++) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-10: Space("
								+ count + ") Title is: "
								+ myTheForumVenueSpaceTitles.get(i).getText());
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-10: Space("
								+ count + ") Location Parameter are: "
								+ myTheForumVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			if (Integer.valueOf(myTheForumVenueSpaceCountOnDisplay
					.getText()) == (myTheForumVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myTheForumVenueTotalSpacesImages
						.size(); j++) {
					if (myTheForumVenueTotalSpacesImages.get(j).isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myTheForumVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);
				if (myTheForumVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myTheForumVenueTitle.getText() + " are: "
									+ (myTheForumVenueTotalSpacesImages.size()
											- myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-10: Missing conditions in The Forum Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-11. Verify the Tao Group Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS011() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-11: Verify Tao Group Section.");
		try {
			WebElement myTaoGroupVenueTitle = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupVenueTitle");
			WebElement myTaoGroupVenueLocation = getWebElement(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupVenueLoc");
			WebElement myTaoGroupVenueSpaceCountOnDisplay = getWebElement(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesDisplayCount");
			List<WebElement> myTaoGroupVenueTotalSpaces = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesCount");
			List<WebElement> myTaoGroupVenueSpaceTitles = getWebElements(driver,
					selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesTitles");
			List<WebElement> myTaoGroupVenueTotalSpacesImages = getWebElements(
					driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesImages");

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView();",
					myTaoGroupVenueTitle);

			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-11: Venue Title: "
							+ myTaoGroupVenueTitle.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-11: Venue Location: "
							+ myTaoGroupVenueLocation.getText());
			LOGGER.info(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-11: Venue Spaces: "
							+ myTaoGroupVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			if (Integer.valueOf(myTaoGroupVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTaoGroupVenueTotalSpaces.size())) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-11: Total Spaces displayed below are: "
								+ myTaoGroupVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			int count = 1;
			for (int i = 0; i < myTaoGroupVenueSpaceTitles.size(); i++) {
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-11: Space("
								+ count + ") Title is: "
								+ myTaoGroupVenueSpaceTitles.get(i).getText());
				count++;
			}

			if (Integer.valueOf(myTaoGroupVenueSpaceCountOnDisplay
					.getText()) == (myTaoGroupVenueTotalSpaces.size())) {
				int myDisplayedImagesCount = 0;
				for (int j = 0; j < myTaoGroupVenueTotalSpacesImages
						.size(); j++) {
					if (myTaoGroupVenueTotalSpacesImages.get(j).isDisplayed()) {
						myDisplayedImagesCount++;
					}
				}
				LOGGER.info(this.myDriverParameters+
						" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for "
								+ myTaoGroupVenueTitle.getText() + " are: "
								+ myDisplayedImagesCount);
				if (myTaoGroupVenueTotalSpacesImages
						.size() != myDisplayedImagesCount) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-5: Total Images missed for "
									+ myTaoGroupVenueTitle.getText() + " are: "
									+ (myTaoGroupVenueTotalSpacesImages.size()
											- myDisplayedImagesCount));
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-11: Missing conditions in Tao Group Section!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-12. Verify the Connect With Us Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS012() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-12: Verify Connect with Us Section.");
		try {

			WebElement myContactUsForm = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsRendered");
			WebElement myContactUsName = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsName");
			WebElement myContactUsCompany = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsCompany");
			WebElement myContactUsEmail = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsEmail");
			WebElement myContactUsPhone = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsPhone");
			WebElement myContactUsSelect = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsSelectById");
			WebElement myContactUsGuests = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsGuests");
			WebElement myContactUsSubmit = getWebElement(driver, selectors,
					"btnMSGSalesCenterTC001VenueRentalsPageContactUsSubmit");
			isMyTestPassed = true;

			if (!isSafariBrowser(((RemoteWebDriver) driver).getCapabilities()
					.getBrowserName().toString())) {

				if (!myContactUsForm.isDisplayed()) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-12: Contact us form is not rendered.");
				}
				if (isMobile) {
					removeIntercom(driver);
				}
				scrollToElement(driver, myContactUsName);
				scrollUp(driver);

				myContactUsName.click();
				writeStringCharacterByCharacter(myContactUsName,
						"MSG QA Automation Team");
				sleep(1);

				scrollToElement(driver, myContactUsCompany);
				scrollUp(driver);
				jsClick(driver, myContactUsCompany);
				writeStringCharacterByCharacter(myContactUsCompany,
						"The Madison Square Garden Limited");
				sleep(1);

				scrollToElement(driver, myContactUsEmail);
				scrollUp(driver);
				jsClick(driver, myContactUsEmail);
				writeStringCharacterByCharacter(myContactUsEmail,
						"MSGTesting@msg.com");
				sleep(1);

				scrollToElement(driver, myContactUsPhone);
				scrollUp(driver);
				jsClick(driver, myContactUsPhone);
				writeStringCharacterByCharacter(myContactUsPhone, "2124656741");
				sleep(1);

				scrollToElement(driver, myContactUsSelect);
				scrollUp(driver);
				scrollToElement(driver, myContactUsSelect);
				Select myDropdown = new Select(myContactUsSelect);
				/**
				 * Change made by Rachit Kumar Rastogi, 11/30/2017 - Ticket SC-1206
				 */
				myDropdown.getOptions().get(2).click();
				sleep(1);

				scrollToElement(driver, myContactUsGuests);
				scrollUp(driver);
				jsClick(driver, myContactUsGuests);
				writeStringCharacterByCharacter(myContactUsGuests, "21");
				sleep(1);

				scrollToElement(driver, myContactUsSubmit);
				scrollUp(driver);
				jsClick(driver, myContactUsSubmit);
				sleep(3);
				WebElement myContactUsSubmitConfirmation = getWebElement(driver,
						selectors,
						"btnMSGSalesCenterTC001VenueRentalsPageContactUsSubmitConfirmation");
				if (myContactUsSubmitConfirmation.getText()
						.equals("Your information has been sent")) {
					LOGGER.info(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-12: Contact Form is submitted successfully.");
				} else {
					LOGGER.error(this.myDriverParameters+
							" - MSG Sales Center Venue Rentals Page: Test Step-12: There is an error in contact form submission!!");
					return isMyTestPassed = false;
				}

			} else {
				LOGGER.info(this.myDriverParameters+
						" - This test is no suitable for Safari due technical issues tracked on Jira: SC-650");

			}

			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+
					" - MSG Sales Center Venue Rentals Page: Test Step-12: There is an error in contact form submission!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-13. Verify the Get In Touch Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS013() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-13: Verify Connect With Us Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - MSG Sales Center Venue Rentals Page: Test Step-13: ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-14. Verify the Footer Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS014() {
		LOGGER.info(this.myDriverParameters+
				" - MSG Sales Center Venue Rentals Page: Test Step-14: Verify Footer Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return isMyTestPassed = true;
		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - MSG Sales Center Venue Rentals Page: Test Step-14: ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Visual Test Enablement for Venue rental landing Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Venue Rentals Page";
		String testNumber = "15";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
}
