package com.msg.qa.MSGOnlineDigital.SalesCenter;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.*;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities;
import com.sun.jna.platform.win32.WinUser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

/**
 * @author Carlos Campino
 * @category MSG Sales Center Group Outings Page
 */

public class MSGTC007EventDetailsPage {
	private static final Logger LOGGER = Logger
			.getLogger(MSGTC007EventDetailsPage.class);
	private static final String eeProductionAPIEndPoint = "https://api.msg.com/v2/events/";

	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	boolean isMobile;
	String platform;
	String myDriverParameters = null;

	/**
	 * Default Constructor
	 */
	public MSGTC007EventDetailsPage() {

	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 */
	public MSGTC007EventDetailsPage(WebDriver driver, String URL,
			boolean isMyTestPassed, Map<String, String[]> selectors,
			String driverParameters) {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		this.platform = capabilities.getCapability("platformName") != null ?
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
	 * Step-1. We are navigable to MSG Sales Center Group Outings Page.
	 */
	@Test(description = "MSG.com - Event Details Page Tests", groups = {
			"MSGEventDetailsPage", "smoke", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS001() {
		try {
			// Verify the Title of the page.
			LOGGER.info(this.myDriverParameters+
					" - ******************* MSG.com - Event Details Page URL to be tested is: "
							+ this.URL + " ******************* ");
			driver.navigate().to(this.URL);
			isMyTestPassed = true;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com - Event Details Page: Test Step-1: We are on MSG Event Details Page.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			LOGGER.info(this.myDriverParameters+
					" - MSG.com - Event Details Page: Test Step-1: We are not on MSG Event Details Page!!");
		}
		return isMyTestPassed;
	}

	/**
	 * Step-2. Verify Global Nav
	 */
	@Test(description = " - MSG.com Event Details - Madison Square Garden Page. Global Nav", groups = {
			"MSGEDPPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS002() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Event Details Page: Test Step-2: Verify Global Nav.");
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
						+ "MSG.com - Event Details Page: Test Step-2: Global NAV is displayed.");
				if(globalNAVEventAndTickets.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Events and Tickets Option is present.");	
					isMyTestPassed = true;
				}
				
				/**
				 * Check for Venues and options inside the venue.
				 */
				if(globalNAVVenueControl.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Venues Option is present.");	
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
										+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Venues Option has "+globalNAVVenueTitle.get(i).getText().toString()+" venue at location: "+globalNAVVenueLoc.get(i).getText().toString()+" with a valid Thumbnail.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Venues Option has "+globalNAVVenueTitle.get(i).getText().toString()+" venue at location: "+globalNAVVenueLoc.get(i).getText().toString()+" doesn't have a valid Thumbnail!!");
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
							+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Brands Option is present.");	
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
										+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Brands Option has "+globalNAVBrandsTitles.get(i).getText().toString()+" Brand with a valid Thumbnail.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Our Brands Option has "+globalNAVBrandsTitles.get(i).getText().toString()+" Brand doesn't have a valid Thumbnail!!");
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
							+ "MSG.com - Event Details Page: Test Step-2: Global Nav -> Official Blog Option is present.");	
					isMyTestPassed = true;
				}
				
			}			
		return isMyTestPassed;

	} catch (Exception e) {
		LOGGER.info(this.myDriverParameters
				+ " - MSG.com Event Details- Madison Square Garden Page: Test Step-2: MSG Global Nav is missing!!");
		LOGGER.error(this.myDriverParameters + " - " + e);
		return false;
	}

}

	/**
	 * Step-3. Verify Secondary Nav
	 */
	@Test(description = " - MSG.com Event Details- Madison Square Garden Page. Secondary Nav Tests", groups = {
			"MSGEDPPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS003() {

		LOGGER.info(this.myDriverParameters
				+ "MSG.com - Event Details Page: Test Step-3: Verify Secondary Nav.");
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
						+ "MSG.com - Event Details Page: Test Step-3: Secondary NAV is displayed.");
				
				/**
				 * Check for Venues and options inside the venue.
				 */
				if(secNAVBuyTicketsLink.isDisplayed())
				{
					secNAVBuyTicketsLink.click();
					
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Event Details Page: Test Step-3: Secondary Nav -> Buy Tickets Option is present.");	
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
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Buy Tickets Option "+secNAVBuyTicketsOptions.get(i).getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Buy Tickets Option "+secNAVBuyTicketsOptions.get(i).getText().toString()+" has a invalid Link!!");		
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
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Buy Tickets -> Explore All Events Option "+secNAVBuyTicketsExploreAllOptions.getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Buy Tickets -> Explore All Events Option "+secNAVBuyTicketsExploreAllOptions.getText().toString()+" has a invalid Link!!");		
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
							+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Premium Hospitality Option is present.");	
					isMyTestPassed = true;
				}			
				
				/**
				 * Check for Plan Your Visit section.
				 */
				if(secNAVPlanYourVisitLink.isDisplayed())
				{
					LOGGER.info(this.myDriverParameters
							+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Plan Your Visit Option is present.");	
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
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Plan your Visit Option "+secNAVPlanYourVisitOptions.get(i).getText().toString()+" has a valid Link.");		
								isMyTestPassed = true;
							}
							else
							{
								LOGGER.info(this.myDriverParameters
										+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Plan your Visit Option "+secNAVPlanYourVisitOptions.get(i).getText().toString()+" has a invalid Link!!");		
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
							+ "MSG.com - Event Details Page: Test Step-2: Secondary Nav -> Venue Rentals Option is present.");	
					isMyTestPassed = true;
				}
				
			}			
		return isMyTestPassed;

	} catch (Exception e) {
		LOGGER.info(this.myDriverParameters
				+ " - MSG.com Event Details- Madison Square Garden Page: Test Step-3: MSG Secondary Nav is missing!!");
		LOGGER.error(this.myDriverParameters + " - " + e);
		return false;
	}

}	
	
	/**
	 * Step-4. Verify the Hero Section: Object/Image/Video/ Link/
	 * Text/Label/Button - Validation Parameters a. Hero Title - Present and not
	 * empty b. Hero Subtitle - Present and not empty c. Hero Detail - Present
	 * and not empty d. Hero Image - Present
	 */
	@Test(description = "MSG.com - Event Details. Hero Section Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS004() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "4";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Hero Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement myHeroEventType = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventType");
			WebElement myHeroEventName = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventName");
			WebElement myHeroExploreButton = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroExploreButton");
			WebElement myHeroEventImage = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageHeroEventImage");
			isMyTestPassed = true;

			// Validates the Hero Event Type
			isMyTestPassed = testText(myHeroEventType, pageName,
					"Hero Event Type", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Hero Event Name
			isMyTestPassed = testText(myHeroEventName, pageName,
					"Hero Event Name", testNumber, isMyTestPassed, this.myDriverParameters);

			if (!isMobile) {
				// Validates the Hero Explore button
				isMyTestPassed = testText(myHeroExploreButton, pageName,
						"Hero Explore button", testNumber, isMyTestPassed, this.myDriverParameters);
			}

			// Validates the Hero Event Image is displayed
			isMyTestPassed = testVisual(myHeroEventImage, pageName,
					"Hero Event Image", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Hero Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-5. Event Details. Event Date Tests
	 */
	@Test(description = "MSG.com - Event Details. Event Date Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS005() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "5";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Verify Even Date Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement eventDateTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTitle");
			scrollToElement(driver, eventDateTitle);
			if (isMobile) {
				scrollUp(driver);
			}
			WebElement eventDateTime = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTime");
			WebElement eventLocation = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventLocation");
			WebElement buyTicketsButton = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageBuyTicketsButton");
			isMyTestPassed = true;

			// Validates the Event Title is shown
			isMyTestPassed = testText(eventDateTitle, pageName, "Event Title",
					testNumber, isMyTestPassed, this.myDriverParameters);
			String eventNumber = eventDateTitle.getText().split(" ")[0];

			// Validates the Event Date Selector is present
			isMyTestPassed = testText(eventDateTime, pageName,
					"Event Date Selector", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Date Pop Up is shown
			WebElement eventDateButtonDropdown = getWebElement(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateButtonDropdown");
			scrollToElement(driver, eventDateButtonDropdown);
			scrollUp(driver);
			eventDateButtonDropdown.click();
			sleep(3);
			isMyTestPassed = testText(eventDateButtonDropdown, pageName,
					"Hero Event Type", testNumber, isMyTestPassed, this.myDriverParameters);
			List<WebElement> eventDateDropdownEvents = getWebElements(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateDropdownEvents");

			// Validates the Event Date Pop Up matches the number of events
			isMyTestPassed = testTextMatches(
					Integer.toString(eventDateDropdownEvents.size()),
					eventNumber, pageName, "Hero Event Type", testNumber,
					isMyTestPassed, this.myDriverParameters);

			// Close Event Date pop up
			if (isIOSPlatform(this.platform)) {
				WebElement eventDateMenuCloseButton = getWebElement(driver,
						selectors, "EventDateMenuCloseButton");
				waitForElement(driver, eventDateMenuCloseButton, 5);
				eventDateMenuCloseButton.click();

			} else {
				if(!platform.equals("darwin")) {
					pressESC(driver);
					sleep(1);
				}
			}

			// Validates the Event Venue is shown
			isMyTestPassed = testText(eventLocation, pageName, "Event Venue",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Buy Ticket is shown
			isMyTestPassed = testText(buyTicketsButton, pageName,
					"Buy Ticket Button", testNumber, isMyTestPassed, this.myDriverParameters);

			// ** Disabled the test as NY connection got banned from
			// Ticketmaster
			// Validates the Buy Ticketmaster Url is shown
			// isMyTestPassed = testLinkNewTab(buyTicketsButton, 5,
			// "ticketmaster.com/event/", pageName, "Hero Event Type",
			// testNumber, isMyTestPassed, driver);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": Even Date Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-6. Event Details. About Event
	 */
	@Test(description = "MSG.com - Event Details. About the Event Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS006() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "6";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": About the Event Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement aboutEventTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventTitle");
			scrollToElement(driver, aboutEventTitle);
			WebElement aboutSponsorIcon = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutSponsorIcon");
			WebElement aboutEventDescription = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventDescription");
			WebElement aboutEventPriceRange = getWebElement(driver, selectors,
					"MSGTC007EventDetailsAboutEventPriceRange");
			isMyTestPassed = true;

			// Validates the Event Title
			isMyTestPassed = testText(aboutEventTitle, pageName, "Event Title",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Sponsor Icon is shown
			isMyTestPassed = testText(aboutSponsorIcon, pageName,
					"Sponsor Icon", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Description is shown
			isMyTestPassed = testText(aboutEventDescription, pageName,
					"Event Description", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the Event Price Range is shown
			isMyTestPassed = testVisual(aboutEventPriceRange, pageName,
					"Event Price Range", testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG About Event. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-7. Event Details. Related Events
	 * https://thegarden.atlassian.net/browse/MSGCOM-618
	 * Change is done by Rachit Kumar Rastogi, on 03/20/2018
	 */
	@Test(description = "MSG.com - Event Details. Related Event Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS007() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "7";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Related Events Section.");

		try {

			/**
			 * 1. Parse DOM to get the Event ID (XPATH: .//a[@data-linkname[contains(.,'calendar:event-detail:under-hero:buy-tickets')]]) 
			 *    e.g. If you pick Bily Joel then use Event ID of Bily Joel from the DOM (e.g. 3B005352966C17E4).
			 */
			WebElement relatedEventId = getWebElement(driver, selectors,"MSGTC007EventDetailsPageGetEventID");
			LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + " relatedEventId - "+ relatedEventId.getAttribute("href").toString());
			
			/**
			 * 1.1: Split the string and fetch the Event id.
			 */
			String[] myPatterns = relatedEventId.getAttribute("href").toString().split("?");			
			String myEDPEventID = myPatterns[0].substring(myPatterns[0].length()-17, myPatterns[0].length()-1);
			List<String> myRelatedEventIDs = new ArrayList<String>();
			/**
			 * 1.2: Now that we have Fetched Event ID we should hit the EE Production API end Point and fetch the related Events from JSON Array of Related events.
			 */
			//List<String> relatedEventIDs = new ArrayList<String>();
			// TODO Auto-generated method stub
			URI uri = new URI(eeProductionAPIEndPoint+myEDPEventID);

			HttpResponse response = null;
			DefaultHttpClient restClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(uri);
			/**
			 * CAUTION --- Implement authorization if you plan to use v.25 api End Point.
			 */
			//httpGet.setHeader("Authorization", this.basicAuthKey);

			try {
				response = restClient.execute(httpGet);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode >= 200 && statusCode < 300) {
				HttpEntity entity1 = response.getEntity();
				String string1 = null;
				try {
					string1 = EntityUtils.toString(entity1);
				} catch (IOException e) {
					e.printStackTrace();
				}

				JSONObject eventsMetaData = new JSONObject(string1);
				JSONArray eventsResultsData = eventsMetaData.getJSONArray("results");
				if (eventsResultsData.length() == 0) {
					/**
					 * No related events Error handling.
					 */
				}

				for (int j = 0; j <= eventsResultsData.length() - 1; j++) {
					JSONObject singleEventResultSet = eventsResultsData.getJSONObject(j);

					for (Object Keys : singleEventResultSet.keySet()) {
						if (Keys.equals("related_events")) {
							JSONArray relatedEventsData = singleEventResultSet.getJSONArray("related_events");
							for (int k = 0; k <= relatedEventsData.length() - 1; k++) {
								/**
								 * 1.3: Use the Related Event IDs and find the event date/time/Venue.
								 */
								myRelatedEventIDs.add(relatedEventsData.getString(k).toString());	
							}					
						}
					}
				}
			}
			/**
			 * 1.4: iterate over the Event ids and find the date/time/venue of the event.
			 */
			for(String relatedEvent:myRelatedEventIDs)
			{
				uri = new URI(eeProductionAPIEndPoint+relatedEvent);

				response = null;
				restClient = new DefaultHttpClient();
				httpGet = new HttpGet(uri);
				/**
				 * CAUTION --- Implement authorization if you plan to use v.25 api End Point.
				 */
				//httpGet.setHeader("Authorization", this.basicAuthKey);

				try {
					response = restClient.execute(httpGet);
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				statusCode = response.getStatusLine().getStatusCode();

				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity1 = response.getEntity();
					String string1 = null;
					try {
						string1 = EntityUtils.toString(entity1);
					} catch (IOException e) {
						e.printStackTrace();
					}

					JSONObject eventsMetaData = new JSONObject(string1);
					JSONArray eventsResultsData = eventsMetaData.getJSONArray("results");
					if (eventsResultsData.length() == 0) {
						/**
						 * No related events Error handling.
						 */
					}

					for (int j = 0; j <= eventsResultsData.length() - 1; j++) {
						JSONObject singleEventResultSet = eventsResultsData.getJSONObject(j);

						for (Object Keys : singleEventResultSet.keySet()) {
							if (Keys.equals("related_events")) {
								JSONArray relatedEventsData = singleEventResultSet.getJSONArray("related_events");
								for (int k = 0; k <= relatedEventsData.length() - 1; k++) {
									/**
									 * 1.3: Use the Related Event IDs and find the event date/time/Venue.
									 */
									myRelatedEventIDs.add(relatedEventsData.getString(k).toString());	
								}					
							}
						}
					}
				}
			}
			
			
			/**
			 * 2. Based on Event ID fetched in Step-1, query: https://api.msg.com/v2/events/3B005352966C17E4 for fetching related_events Array. 
			 */
			
			
			/**
			 * 3. In Response JSON you will get related events in JSONArray: "related_events":["3B00543AF4153ECF","3B005432FAE5327E"]
			 *    Now parse through this Array to get the related events IDs
			 */
			
			
			/**
			 * 4. Based on Event ID fetched in Step-3, query: https://api.msg.com/v2/events/3B00543AF4153ECF and https://api.msg.com/v2/events/3B005432FAE5327E for fetching related event details. 
			 */
			
			
			/**
			 * 5. Now access the Interested in module in front-end: (XPATH: .//h2[contains(text(),'You Might Be Interested In')])
			 */
			
			
			/**
			 * 6. Related Event Cards date/time: (XPATH: .//*[@class='event-detail-list'])
			 * a. Date/Time: (XPATH: .//*[@class='event-detail-list']/li/time)
			 * b. Venue: (XPATH: .//*[@class='event-detail-list']/li[2])
			 */
			
			
			/**
			 * 7. Related Event Image - check if images are displayed: (XPATH: .//*[@class='LazyLoad is-visible']/div/picture/img) src attribute for image path
			 */
			
			
		
			/**
			 * 8. Related Event Title - Check if Title is displayed: (XPATH: .//h2[@class='h5 medium'])
			 */
			
			
			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events Related Events Section is having issues!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}

	/**
	 * Step-8. Event Details. Ads
	 */
	@Test(description = "MSG.com - Event Details. Ads Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS008() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": ad Section.");

		try {

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events Ad Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}	
	
	/**
	 * Step-9. Event Details. Plan Ahead.
	 */
	@Test(description = "MSG.com - Event Details. Plan Ahead Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS009() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "9";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Plan Ahead Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */
			WebElement PlanAheadTitle = getWebElement(driver, selectors,
					"PlanAheadTitle");
			scrollToElement(driver, PlanAheadTitle);
			List<WebElement> PlanAheadPlanningCards = getWebElements(driver,
					selectors, "PlanAheadPlanningCards");
			isMyTestPassed = true;

			// Validates the Plan Ahead Title
			isMyTestPassed = testText(PlanAheadTitle, pageName,
					"Plan Ahead Title", testNumber, isMyTestPassed, this.myDriverParameters);

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
						pageName, "Plan Ahead Eyebrow", testNumber, isMyTestPassed, this.myDriverParameters);

				// EventTitle
				isMyTestPassed = testText(PlanAheadPlanningCardEyeTitle,
						pageName, "Plan Ahead EventTitle", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Description
				isMyTestPassed = testText(PlanAheadPlanningCardEyeDescription,
						pageName, "Plan Ahead Description", testNumber, isMyTestPassed, this.myDriverParameters);

				// Event Button
				isMyTestPassed = testText(PlanAheadPlanningCardEyeButton,
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
	 * Step-10. Event Details. Visual Link Tests
	 */
	@Test(description = "MSG.com - Event Details. Visual links title Tests", groups = {
			"MSGEventDetailsPage", "fullintegration"})
	public boolean MSGTC007EventDetailsPageTS010() {

		String pageName = "MSG.com - Event Details Page";
		String testNumber = "10";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
				+ ": Visual links section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement linkListTitle = getWebElement(driver, selectors,
					"VisualLinkListTitle");
			isMyTestPassed = true;
			scrollToElement(driver, linkListTitle);
			WebElement linkFAQ = getWebElement(driver, selectors,
					"VisualLinkListLinkDestination", "Venue FAQs");
			isMyTestPassed = true;
			WebElement linkDisabledServices = getWebElement(driver, selectors,
					"VisualLinkListLinkDestination", "Disabled Services");

			WebElement iconFAQ = getWebElement(driver, selectors,
					"VisualLinkListLinkIcon", "Venue FAQs");
			isMyTestPassed = true;
			WebElement iconDisabledServices = getWebElement(driver, selectors,
					"VisualLinkListLinkIcon", "Disabled Services");

			isMyTestPassed = true;

			// Validates the visual link title
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link title", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon FAQ
			isMyTestPassed = testVisual(iconFAQ, pageName, "FAQ icon",
					testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon Disability Services
			isMyTestPassed = testVisual(iconDisabledServices, pageName,
					"Disability Services icon", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon text
			isMyTestPassed = testText(linkFAQ, pageName, "FAQ text", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the icon text
			isMyTestPassed = testText(linkDisabledServices, pageName,
					"Disability Services text", testNumber, isMyTestPassed, this.myDriverParameters);

			// Validates the visual link destination
			isMyTestPassed = testText(linkListTitle, pageName,
					"visual link destination", testNumber, isMyTestPassed, this.myDriverParameters);

			isMyTestPassed = testTextIncluded(
					getWebElementAttribute(iconFAQ, "class"), "faq", pageName,
					"FAQ Icon", testNumber, isMyTestPassed, this.myDriverParameters);

			isMyTestPassed = testTextIncluded(
					getWebElementAttribute(iconDisabledServices, "class"),
					"disability-services", pageName, "Disabled Services Icon",
					testNumber, isMyTestPassed, this.myDriverParameters);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber
					+ ": MSG Events ADS. Elements are missing!!");
			LOGGER.error(this.myDriverParameters+" - "+e);
			return isMyTestPassed = false;
		}

	}
	
	/**
	 * Visual Test Enablement for Events Details Page.
	 * @author Rachit Kumar Rastogi
	 * @return
	 */
	public boolean executeVisualTest()
	{
		String pageName = "MSG.com - Events Details Page";
		String testNumber = "8";

		LOGGER.info(this.myDriverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": Visual Test.");
		
		return MSGOnlineDigitalReusableFunctionalities.executeVisualTest(driver);
	}
}
