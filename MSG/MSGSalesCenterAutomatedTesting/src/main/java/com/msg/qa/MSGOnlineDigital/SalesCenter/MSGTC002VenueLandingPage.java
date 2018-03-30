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
	 * Step-2. Verify Global Nav
	 */
	@Test(description = " - MSG.com Venue Landing - Madison Square Garden Page. Hero Filters Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS002() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Venue Landing Page: Test Step-2: Verify Global Nav.");
		try {
			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement globalNAV = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAV");
			WebElement globalNAVEventAndTickets = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAVEventAndTickets");
			WebElement globalNAVVenueControl = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAVVenueControl");
			
			
			List<WebElement> globalNAVVenueTitle = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVVenueTitle");
			List<WebElement>  globalNAVVenueLoc = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVVenueLoc");
			List<WebElement>  globalNAVVenueImage = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVVenueImage");
			List<WebElement>  globalNAVLearnMore = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVVenueLearnMore");
			WebElement globalNAVBrandsControl = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAVBrandsControl");
			List<WebElement>  globalNAVBrandsImages = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVBrandsImages");
			List<WebElement>  globalNAVBrandsTitles = getWebElements(driver, selectors,
					"MSGTC000LandingPageGlobalNAVBrandsTitles");
			WebElement globalNAVBlogControl = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAVBlogControl");
			WebElement globalNAVBlogLink = getWebElement(driver, selectors,
					"MSGTC000LandingPageGlobalNAVBlogLink");		
			
			if (globalNAV.isDisplayed())
			{
				LOGGER.info(this.myDriverParameters
						+ "MSG.com - Venue Landing Page: Test Step-2: Global NAV is displayed.");
				if(globalNAVEventAndTickets.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Events and Tickets Option is present.");	
					isMyTestPassed = true;
				}
				
				/**
				 * Check for Venues and options inside the venue.
				 */
				if(globalNAVVenueControl.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Venues Option is present.");	
					if(!globalNAVVenueTitle.isEmpty() && !globalNAVVenueLoc.isEmpty() && !globalNAVVenueImage.isEmpty())
					{
					for(int i=0; i<globalNAVVenueTitle.size();i++)
						{
						/**
						 * This section will verify if ALT attribute of image will have a valid image path.
						 */
							if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(globalNAVVenueImage.get(i).getAttribute("src").toString())!=404)
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Venues Option has "+globalNAVVenueTitle.get(i).getText().toString()+" venue at location: "+globalNAVVenueLoc.get(i).getText().toString()+" with a valid Thumbnail.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Venues Option has "+globalNAVVenueTitle.get(i).getText().toString()+" venue at location: "+globalNAVVenueLoc.get(i).getText().toString()+" doesn't have a valid Thumbnail!!");
								isMyTestPassed = false;
								break;
							}
							i++;
						}
					}
				}
				
				/**
				 * Check for Our Brands and Brands inside Our Brands section.
				 */
				if(globalNAVBrandsControl.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Brands Option is present.");	
					if(!globalNAVBrandsTitles.isEmpty() && !globalNAVBrandsImages.isEmpty())
					{
					for(int i=0; i<globalNAVBrandsTitles.size();i++)
						{
						/**
						 * This section will verify if ALT attribute of image will have a valid image path.
						 */
							if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(globalNAVBrandsImages.get(i).getAttribute("src").toString())!=404)
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Brands Option has "+globalNAVBrandsTitles.get(i).getText().toString()+" Brand with a valid Thumbnail.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Our Brands Option has "+globalNAVBrandsTitles.get(i).getText().toString()+" Brand doesn't have a valid Thumbnail!!");
								isMyTestPassed = false;
								break;
							}
							i++;
						}
					}
				}
				
				/**
				 * Official Blog Link is present.
				 */
				if(globalNAVBlogLink.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Global Nav -> Official Blog Option is present.");	
					isMyTestPassed = true;
				}
				
			}			
		return isMyTestPassed;

	} catch (Exception e) {
		LOGGER.info(this.myDriverParameters
				+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-2: MSG Global Nav is missing!!");
		LOGGER.error(this.myDriverParameters + " - " + e);
		return false;
	}

}

	/**
	 * Step-3. Verify Secondary Nav
	 */
	@Test(description = " - MSG.com Venue Landing - Madison Square Garden Page. Secondary Nav Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS003() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Venue Landing Page: Test Step-3: Verify Secondary Nav.");
		try {
			
			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement secNAV = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAV");
			WebElement secNAVBuyTickets = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVBuyTickets");			
			WebElement secNAVBuyTicketsLink = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVBuyTicketsLink");	
			List<WebElement> secNAVBuyTicketsOptions = getWebElements(driver, selectors,
					"MSGTC000LandingPageSecNAVBuyTicketsOptions");	
			WebElement secNAVBuyTicketsExploreAllOptions = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVBuyTicketsExploreAllEvents");	
			WebElement secNAVPremHosp = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVPremHosp");	
			WebElement secNAVPremHospLink = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVPremHospLink");	
			WebElement secNAVPanYourVisit = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVPlanYourVisit");	
			WebElement secNAVPlanYourVisitLink = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVPlanYourVisitLink");	
			List<WebElement> secNAVPlanYourVisitOptions = getWebElements(driver, selectors,
					"MSGTC000LandingPageSecNAVPlanYourVisitOptions");	
			WebElement secNAVVenueRentals = getWebElement(driver, selectors,
					"MSGTC000LandingPageSecNAVVenueRentals");	
			
			if (secNAV.isDisplayed())
			{
				LOGGER.info(this.myDriverParameters
						+ "MSG.com - Venue Landing Page: Test Step-3: Secondary NAV is displayed.");
				
				/**
				 * Check for Venues and options inside the venue.
				 */
				if(secNAVBuyTicketsLink.isDisplayed())
				{
					secNAVBuyTicketsLink.click();
					
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-3: Secondary Nav -> Buy Tickets Option is present.");	
					if(!secNAVBuyTicketsOptions.isEmpty())
					{
					for(int i=0; i<secNAVBuyTicketsOptions.size();i++)
						{
						/**
						 * This section will verify if ALT attribute of image will have a valid image path.
						 */
							if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(secNAVBuyTicketsOptions.get(i).getAttribute("href").toString())!=404)
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Buy Tickets Option "+secNAVBuyTicketsOptions.get(i).getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Buy Tickets Option "+secNAVBuyTicketsOptions.get(i).getText().toString()+" has a invalid Link!!");		
								isMyTestPassed = false;
								break;
							}
							i++;
						}
					}
					
					if(secNAVBuyTicketsExploreAllOptions.isDisplayed())
					{
						/**
						 * This section will verify if ALT attribute of image will have a valid image path.
						 */
							if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(secNAVBuyTicketsExploreAllOptions.getAttribute("href").toString())!=404)
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Buy Tickets -> Explore All Events Option "+secNAVBuyTicketsExploreAllOptions.getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Buy Tickets -> Explore All Events Option "+secNAVBuyTicketsExploreAllOptions.getText().toString()+" has a invalid Link!!");		
								isMyTestPassed = false;
							}
					}
				}
				
				/**
				 * Check if Premium Hospitality options is displayed.
				 */
				if(secNAVPremHospLink.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Premium Hospitality Option is present.");	
					isMyTestPassed = true;
				}			
				
				/**
				 * Check for Plan Your Visit section.
				 */
				if(secNAVPlanYourVisitLink.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Plan Your Visit Option is present.");	
					if(!secNAVPlanYourVisitOptions.isEmpty())
					{
					for(int i=0; i<secNAVPlanYourVisitOptions.size();i++)
						{
						/**
						 * This section will verify if ALT attribute of image will have a valid image path.
						 */
							if(MSGOnlineDigitalReusableFunctionalities.verifyIfLinkIsWorking(secNAVPlanYourVisitOptions.get(i).getAttribute("href").toString())!=404)
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Plan your Visit Option "+secNAVPlanYourVisitOptions.get(i).getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Plan your Visit Option "+secNAVPlanYourVisitOptions.get(i).getText().toString()+" has a invalid Link!!");		
								isMyTestPassed = false;
								break;
							}
							i++;
						}
					}
				}
				
				/**
				 * Venue rentals Link is present.
				 */
				if(secNAVVenueRentals.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Venue Landing Page: Test Step-2: Secondary Nav -> Venue Rentals Option is present.");	
					isMyTestPassed = true;
				}
				
			}			
		return isMyTestPassed;

	} catch (Exception e) {
		LOGGER.info(this.myDriverParameters
				+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-3: MSG Secondary Nav is missing!!");
		LOGGER.error(this.myDriverParameters + " - " + e);
		return false;
	}

}
	
	/**
	 * Step-4. Verify the Hero Section: EVENT SEARCH OPTIONS
	 */
	@Test(description = " - MSG.com Venue Landing - Madison Square Garden Page. Hero Filters Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS004() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Venue Landing Page: Test Step-4: Verify Hero Section.");
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
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-4: Hero Title is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Filter Type
			if (heroFiltersType.isDisplayed()
					& heroFiltersType.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-4: Hero Filter Type is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Filter Date
			if (heroFiltersDate.isDisplayed()
					& heroFiltersDate.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-4: Hero Filter Date is present");
			} else {
				isMyTestPassed = false;
			}

			// Validates the Hero Find Events Button
			if (heroFindEventsButton.isDisplayed()
					& heroFindEventsButton.getText() != null) {
				LOGGER.info(this.myDriverParameters
						+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-4: Hero Find Events Button is present");
			} else {
				isMyTestPassed = false;
			}

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.info(this.myDriverParameters
					+ " - MSG.com Venue Landing - Madison Square Garden Page: Test Step-4: MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return false;
		}

	}

	/**
	 * Step-5: Venue landing - Generic Content Tests.
	 * @return
	 */
	@Test(description = "MSG.com - Venue landing. Generic Content Tests", groups = {
			"MSGVenueLanding", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS005() {

		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "5";

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

	/**
	 * Step-6: Venue Landing Page - Event grid Tests
	 * @return
	 */
	@Test(description = "MSG.com - Venue Landing. Event Grid Test", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS006() {
		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "6";
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
	
/**
 * Step-7: Plan head Tests.
 * @return
 */
	@Test(description = "MSG.com - Venue landing. Plan Ahead Tests", groups = {
			"MSGVenueLandingPage", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS007() {

		String pageName = "MSG.com - Venue Landing Page";
		String testNumber = "7";

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
	
/**
 * step-8: Visual Link Tests
 * @return
 */
	@Test(description = "MSG.com - Venue landing. Visual links title Tests", groups = {
			"MSGVenueLanding", "fullintegration"})
	public boolean MSGTC002VenueLandingPageTS008() {

		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "8";

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
	 * Step-9: Venue Landing Page Delta Features: Email Capture
	 * @date 02/28/2018
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean MSGTC002VenueLandingPageTS009() {
		String pageName = "MSG.com - Venue landing Page";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters + " - " + pageName
				+ " Page: Test Step-" + testNumber + ": Email Capture");

		try {

	
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters + " - " + pageName
					+ " Page: Test Step-" + testNumber
					+ ": MSG.com - Venue landing Page. EMail Capture Elements are missing!!");
			LOGGER.error(this.myDriverParameters + " - " + e);
			return isMyTestPassed = false;
		}
	}
	
	/**
	 * Steip-10: Visual Test Enablement for Landing Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Landing Page";
		String testNumber = "10";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
}
