package com.msg.qa.MSGOnlineDigital.SalesCenter;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MSG Sales Center Global Elements
 *
 * @author MSG QA Automation Team
 * @version 1.0
 * @category MSG Sales Center Global Element
 * @since 08/31/2017, 04:02PM EST
 */

public class MSGSalesCenterGlobalElments {

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();

	/**
	 * Default Constructor
	 */
	public MSGSalesCenterGlobalElments() {

	}

	/**
	 * Overloaded Constructor
	 *
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGSalesCenterGlobalElments(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors) {
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
	}

	/**
	 * Step-1. We are navigable to MSG Sales Center Venue Rentals Page.
	 */
	@Test(description = "Are we on MSG Sales Center Venue Rentals Page?", groups = {
			"MSGSalesCenterVenueRentalsPage", "smoke", "fullintegration"})
	public boolean MSGSalesCenterTC001VenueRentalsPageTS001() {
		try {
			// Verify the Title of the page.
			System.out.println(
					"******************* MSG Sales Center Venue Rentals Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-1: We are on MSG Sales Center Venue Rentals Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-1: We are not on MSG Sales Center Venue Rentals Page!!");
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
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-2: Verify Header Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myMSGLogo = null;

			String[] mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageSvgMSGLogo");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGLogo = driver.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGLogo = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myMSGLogo.getAttribute("data-name").toString()
					.equals("msglogo")) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-2: MSG Logo is Present.");
				// return isMyTestPassed = true;
			} else {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-2: MSG Logo is missing!!");
				// return isMyTestPassed = false;
			}

			WebElement myMSGNavLinks = null;

			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageNavLinks");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGNavLinks = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGNavLinks = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myMSGNavLinks.isDisplayed()) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are present.");
				// Perform Further validation in the nav Options.

				return isMyTestPassed = true;
			} else {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are missing!!");
				return isMyTestPassed = false;
			}
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-2: MSG Nav Links are missing!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-3. Verify the Venue Rentals Section.
	 *
	 * @throws Exception
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS003() throws Exception {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-3: Verify Venue Rentals Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myVenueTitlePart1 = null, myVenueTitlePart2 = null,
					myVenueTitleText1 = null, myVenueTitleText2 = null,
					myVenueImage = null;

			String[] mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageVenRenTitle1");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenueTitlePart1 = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenueTitlePart1 = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myVenueTitlePart1.isDisplayed()
					& myVenueTitlePart1.getText() != null) {
				mySelector = this.selectors.get(
						"btnMSGSalesCenterTC001VenueRentalsPageVenRenTitle2");
				if (mySelector[1].equals("CSS")
						|| mySelector[1].equals("ReactID")) {
					wait.until(ExpectedConditions.presenceOfElementLocated(
							By.cssSelector(mySelector[0])));
					myVenueTitlePart2 = driver
							.findElement(By.cssSelector(mySelector[0]));
				}
				if (mySelector[1].equals("Xpath")) {
					wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath(mySelector[0])));
					myVenueTitlePart2 = driver
							.findElement(By.xpath(mySelector[0]));
				}

				if (myVenueTitlePart2.isDisplayed()
						& myVenueTitlePart2.getText() != null) {
					System.out.println(
							"MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered is: "
									+ myVenueTitlePart1.getText() + " "
									+ myVenueTitlePart2.getText());
				}
			}

			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageVenRenText");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenueTitleText1 = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenueTitleText1 = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myVenueTitleText1.isDisplayed()
					& myVenueTitleText1.getText() != null) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (small) is: "
								+ myVenueTitleText1.getText());
			}

			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageVenRenText2");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenueTitleText2 = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenueTitleText2 = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myVenueTitleText2.isDisplayed()
					& myVenueTitleText2.getText() != null) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered text (long) is: "
								+ myVenueTitleText2.getText());
			}

			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageVenRenImage");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenueImage = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenueImage = driver.findElement(By.xpath(mySelector[0]));
			}

			if (myVenueImage.isDisplayed()
					&& MSGOnlineDigitalReusableFunctionalities
							.verifyIfLinkIsWorking(myVenueImage
									.getAttribute("src").toString()) != 404) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-3: Venue Title Rendered Image URL is: "
								+ myVenueImage.getAttribute("src").toString());
			}

			return isMyTestPassed = true;

		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-3: ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-4. Verify the Browse By Venue Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS004() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-4: Verify Browse By Venue Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			List<WebElement> myVenues = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTotalVenueCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenues = driver.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenues = driver.findElements(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-4: Total Venues are "
							+ myVenues.size());

			List<WebElement> myVenuesTitles = null;
			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageVenueTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myVenuesTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myVenuesTitles = driver.findElements(By.xpath(mySelector[0]));
			}

			int count = 1;
			for (int i = 0; i < myVenuesTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-4: Venue"
								+ count + " Title & location is: "
								+ myVenuesTitles.get(i).getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-4: Browse by venue section is missing on validation!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-5. Verify the Madison Square Garden Venue Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS005() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-5: Verify Madison Square Garden Venue Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myMSGVenueTitle = null;
			String[] mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageMSGVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myMSGVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myMSGVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myMSGVenueTitle = driver.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myMSGVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-5: Venue Title: "
							+ myMSGVenueTitle.getText());

			WebElement myMSGVenueLocation = null;
			mySelector = this.selectors
					.get("btnMSGSalesCenterTC001VenueRentalsPageMSGVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-5: Venue Location: "
							+ myMSGVenueLocation.getText());

			WebElement myMSGVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-5: Venue Spaces: "
							+ myMSGVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myMSGVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myMSGVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myMSGVenueTotalSpaces.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-5: Total Spaces displayed below are: "
								+ myMSGVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myMSGVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myMSGVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myMSGVenueLocationCapacity = new ArrayList<WebElement>();

			for (int j = 0; j < myMSGVenueLocationParameter.size(); j++) {
				if (myMSGVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myMSGVenueLocationCapacity
							.add(myMSGVenueLocationParameter.get(j));
				}
			}

			int count = 1;

			for (int i = 0; i < myMSGVenueSpaceTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-5: Space("
								+ count + ") Title is: "
								+ myMSGVenueSpaceTitles.get(i).getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-5: Space("
								+ count + ") Location Parameter are: "
								+ myMSGVenueLocationCapacity.get(i).getText());
				count++;
			}

			List<WebElement> myMSGVenueTotalSpacesImages = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageMSGSpacesImages");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myMSGVenueTotalSpacesImages = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myMSGVenueTotalSpacesImages = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myMSGVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myMSGVenueTotalSpacesImages.size())) {
				for (int j = 0; j < myMSGVenueTotalSpacesImages.size(); j++) {
					if (myMSGVenueTotalSpacesImages.get(j).isDisplayed())
						System.out.println(
								"MSG Sales Center Venue Rentals Page: Test Step-5: Total Images displayed for spaces are: "
										+ myMSGVenueTotalSpacesImages.size());
				}
			} else {
				return isMyTestPassed = false;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-5: Missing conditions in Madison Square Garden Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-6. Verify the Theater at MSG Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS006() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-6: Verify Theater at MSG Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myTheaterAtMSGVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTheaterAtMSGVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTheaterAtMSGVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTheaterAtMSGVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTheaterAtMSGVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-6: Venue Title: "
							+ myTheaterAtMSGVenueTitle.getText());

			WebElement myTheaterAtMSGVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheaterAtMSGVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheaterAtMSGVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-6: Venue Location: "
							+ myTheaterAtMSGVenueLocation.getText());

			WebElement myTheaterAtMSGVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheaterAtMSGVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheaterAtMSGVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-6: Venue Spaces: "
							+ myTheaterAtMSGVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myTheaterAtMSGVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheaterAtMSGVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheaterAtMSGVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myTheaterAtMSGVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTheaterAtMSGVenueTotalSpaces
							.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-6: Total Spaces displayed below are: "
								+ myTheaterAtMSGVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myTheaterAtMSGVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheaterAtMSGVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheaterAtMSGVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myTheaterAtMSGVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheaterAtMSGSpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheaterAtMSGVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheaterAtMSGVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myTheaterAtMSGVenueLocationCapacity = new ArrayList<WebElement>();

			for (int j = 0; j < myTheaterAtMSGVenueLocationParameter
					.size(); j++) {
				if (myTheaterAtMSGVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myTheaterAtMSGVenueLocationCapacity
							.add(myTheaterAtMSGVenueLocationParameter.get(j));
				}
			}

			int count = 1;

			for (int i = 0; i < myTheaterAtMSGVenueSpaceTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-6: Space("
								+ count + ") Title is: "
								+ myTheaterAtMSGVenueSpaceTitles.get(i)
										.getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-6: Space("
								+ count + ") Location Parameter are: "
								+ myTheaterAtMSGVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			return isMyTestPassed = true;

		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-6: Missing conditions in Theatre at MSG Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-7. Verify the Radio City Music Hall Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS007() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-7: Verify Radio City Music Hall Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myRadioCityVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCityVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myRadioCityVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myRadioCityVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myRadioCityVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myRadioCityVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-7: Venue Title: "
							+ myRadioCityVenueTitle.getText());

			WebElement myRadioCityVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCityVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myRadioCityVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myRadioCityVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-7: Venue Location: "
							+ myRadioCityVenueLocation.getText());

			WebElement myRadioCityVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myRadioCityVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myRadioCityVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-7: Venue Spaces: "
							+ myRadioCityVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myRadioCityVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myRadioCityVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myRadioCityVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myRadioCityVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myRadioCityVenueTotalSpaces.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-7: Total Spaces displayed below are: "
								+ myRadioCityVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myRadioCityVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myRadioCityVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myRadioCityVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myRadioCityVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageRadioCitySpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myRadioCityVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myRadioCityVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myRadioCityVenueLocationCapacity = new ArrayList<WebElement>();

			for (int j = 0; j < myRadioCityVenueLocationParameter.size(); j++) {
				if (myRadioCityVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myRadioCityVenueLocationCapacity
							.add(myRadioCityVenueLocationParameter.get(j));
				}
			}

			int count = 1;

			for (int i = 0; i < myRadioCityVenueSpaceTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-7: Space("
								+ count + ") Title is: "
								+ myRadioCityVenueSpaceTitles.get(i).getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-7: Space("
								+ count + ") Location Parameter are: "
								+ myRadioCityVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-7:  Missing conditions in Radio City music Hall Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-8. Verify the Beacon Theatre Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS008() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-8: Verify Beacon Theatre Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myBeaconTheatreVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myBeaconTheatreVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myBeaconTheatreVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myBeaconTheatreVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myBeaconTheatreVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-8: Venue Title: "
							+ myBeaconTheatreVenueTitle.getText());

			WebElement myBeaconTheatreVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myBeaconTheatreVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myBeaconTheatreVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-8: Venue Location: "
							+ myBeaconTheatreVenueLocation.getText());

			WebElement myBeaconTheatreVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myBeaconTheatreVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myBeaconTheatreVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-8: Venue Spaces: "
							+ myBeaconTheatreVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myBeaconTheatreVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myBeaconTheatreVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myBeaconTheatreVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer
					.valueOf(myBeaconTheatreVenueSpaceCountOnDisplay.getText()
							.split(" ")[0]) == (myBeaconTheatreVenueTotalSpaces
									.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-8: Total Spaces displayed below are: "
								+ myBeaconTheatreVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myBeaconTheatreVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myBeaconTheatreVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myBeaconTheatreVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myBeaconTheatreVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageBeaconTheatreSpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myBeaconTheatreVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myBeaconTheatreVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
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
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-8: Space("
								+ count + ") Title is: "
								+ myBeaconTheatreVenueSpaceTitles.get(i)
										.getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-8: Space("
								+ count + ") Location Parameter are: "
								+ myBeaconTheatreVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-8:  Missing conditions in Beacon Theatre Section!! ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-9. Verify the Chicago Theatre Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS009() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-9: Verify Chicago Theatre Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myChicagoTheatreVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myChicagoTheatreVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myChicagoTheatreVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myChicagoTheatreVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myChicagoTheatreVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-9: Venue Title: "
							+ myChicagoTheatreVenueTitle.getText());

			WebElement myChicagoTheatreVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myChicagoTheatreVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myChicagoTheatreVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-9: Venue Location: "
							+ myChicagoTheatreVenueLocation.getText());

			WebElement myChicagoTheatreVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myChicagoTheatreVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myChicagoTheatreVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-9: Venue Spaces: "
							+ myChicagoTheatreVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myChicagoTheatreVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myChicagoTheatreVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myChicagoTheatreVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer
					.valueOf(myChicagoTheatreVenueSpaceCountOnDisplay.getText()
							.split(" ")[0]) == (myChicagoTheatreVenueTotalSpaces
									.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-9: Total Spaces displayed below are: "
								+ myChicagoTheatreVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myChicagoTheatreVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myChicagoTheatreVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myChicagoTheatreVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myChicagoTheatreVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageChicagoTheatreSpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myChicagoTheatreVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myChicagoTheatreVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myChicagoTheatreVenueLocationCapacity = new ArrayList<WebElement>();

			for (int j = 0; j < myChicagoTheatreVenueLocationParameter
					.size(); j++) {
				if (myChicagoTheatreVenueLocationParameter.get(j).getText()
						.contains("Capacity")) {
					myChicagoTheatreVenueLocationCapacity
							.add(myChicagoTheatreVenueLocationParameter.get(j));
				}
			}

			int count = 1;

			for (int i = 0; i < myChicagoTheatreVenueSpaceTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-9: Space("
								+ count + ") Title is: "
								+ myChicagoTheatreVenueSpaceTitles.get(i)
										.getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-9: Space("
								+ count + ") Location Parameter are: "
								+ myChicagoTheatreVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-9: Missing conditions in Chicago Theatre Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-10. Verify the The Forum Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS010() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-10: Verify The Forum Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myTheForumVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTheForumVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTheForumVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTheForumVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTheForumVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-10: Venue Title: "
							+ myTheForumVenueTitle.getText());

			WebElement myTheForumVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheForumVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheForumVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-10: Venue Location: "
							+ myTheForumVenueLocation.getText());

			WebElement myTheForumVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheForumVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheForumVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-10: Venue Spaces: "
							+ myTheForumVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myTheForumVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheForumVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheForumVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myTheForumVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTheForumVenueTotalSpaces.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-10: Total Spaces displayed below are: "
								+ myTheForumVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myTheForumVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheForumVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheForumVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			List<WebElement> myTheForumVenueLocationParameter = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTheForumSpacesLocParam");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTheForumVenueLocationParameter = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTheForumVenueLocationParameter = driver
						.findElements(By.xpath(mySelector[0]));
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
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-10: Space("
								+ count + ") Title is: "
								+ myTheForumVenueSpaceTitles.get(i).getText());
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-10: Space("
								+ count + ") Location Parameter are: "
								+ myTheForumVenueLocationCapacity.get(i)
										.getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-10: Missing conditions in The Forum Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-11. Verify the Tao Group Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS011() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-11: Verify Tao Group Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement myTaoGroupVenueTitle = null;
			String[] mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupVenueTitle");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTaoGroupVenueTitle = driver
						.findElement(By.cssSelector(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTaoGroupVenueTitle);
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				myTaoGroupVenueTitle = driver
						.findElement(By.xpath(mySelector[0]));
				jse.executeScript("arguments[0].scrollIntoView();",
						myTaoGroupVenueTitle);
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-11: Venue Title: "
							+ myTaoGroupVenueTitle.getText());

			WebElement myTaoGroupVenueLocation = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupVenueLoc");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTaoGroupVenueLocation = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTaoGroupVenueLocation = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-11: Venue Location: "
							+ myTaoGroupVenueLocation.getText());

			WebElement myTaoGroupVenueSpaceCountOnDisplay = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesDisplayCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTaoGroupVenueSpaceCountOnDisplay = driver
						.findElement(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTaoGroupVenueSpaceCountOnDisplay = driver
						.findElement(By.xpath(mySelector[0]));
			}

			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-11: Venue Spaces: "
							+ myTaoGroupVenueSpaceCountOnDisplay.getText()
									.split(" ")[0]);

			List<WebElement> myTaoGroupVenueTotalSpaces = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesCount");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTaoGroupVenueTotalSpaces = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTaoGroupVenueTotalSpaces = driver
						.findElements(By.xpath(mySelector[0]));
			}

			if (Integer.valueOf(myTaoGroupVenueSpaceCountOnDisplay.getText()
					.split(" ")[0]) == (myTaoGroupVenueTotalSpaces.size())) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-11: Total Spaces displayed below are: "
								+ myTaoGroupVenueTotalSpaces.size());
			} else {
				return isMyTestPassed = false;
			}

			List<WebElement> myTaoGroupVenueSpaceTitles = null;
			mySelector = this.selectors.get(
					"btnMSGSalesCenterTC001VenueRentalsPageTaoGroupSpacesTitles");
			if (mySelector[1].equals("CSS")
					|| mySelector[1].equals("ReactID")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector(mySelector[0])));
				myTaoGroupVenueSpaceTitles = driver
						.findElements(By.cssSelector(mySelector[0]));
			}
			if (mySelector[1].equals("Xpath")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(mySelector[0])));
				myTaoGroupVenueSpaceTitles = driver
						.findElements(By.xpath(mySelector[0]));
			}

			int count = 1;

			for (int i = 0; i < myTaoGroupVenueSpaceTitles.size(); i++) {
				System.out.println(
						"MSG Sales Center Venue Rentals Page: Test Step-11: Space("
								+ count + ") Title is: "
								+ myTaoGroupVenueSpaceTitles.get(i).getText());
				count++;
			}

			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-11: Missing conditions in Tao Group Section!!");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-12. Verify the Connect With Us Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS012() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-12: Verify Connect with Us Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-12: ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-13. Verify the Get In Touch Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS013() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-13: Verify Get In Touch Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-13: ");
			return isMyTestPassed = false;
		}
	}

	/**
	 * Step-14. Verify the Footer Section.
	 */

	public boolean MSGSalesCenterTC001VenueRentalsPageTS014() {
		System.out.println(
				"MSG Sales Center Venue Rentals Page: Test Step-14: Verify Footer Section.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return isMyTestPassed = true;
		} catch (WebDriverException e) {
			System.out.println(
					"MSG Sales Center Venue Rentals Page: Test Step-14: ");
			return isMyTestPassed = false;
		}
	}

}
