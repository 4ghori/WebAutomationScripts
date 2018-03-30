package exceute;

import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElement;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.getWebElements;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.pressESC;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.sleep;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testText;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testTextMatches;
import static com.msg.qa.common.MSGOnlineDigitalReusableFunctionalities.testURLContains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Utility.Parsers.TestExcelParser;
public class LocalExec {
	private static final Logger LOGGER = Logger.getLogger(LocalExec.class);
	private static WebDriver driver;
	private static Map<String, String[]> selectors = new HashMap<String, String[]>();
	private static String pageToTest = "https://www.msg.com/calendar/the-chicago-theatre-january-2018-an-evening-with-2cellos";
	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver",
					"src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver();
			driver.get(pageToTest);
			driver.manage().window().maximize();
			boolean testResult = runTest(driver);
			Assert.assertTrue(testResult, "<<Error when running test>>");
		} catch (Exception e) {
			LOGGER.error(" ERROR:: " + e);
		} finally {
			sleep(3);
			driver.quit();
		}
	}
	public static boolean runTest(WebDriver driver) throws Exception {
		boolean isMyTestPassed = false;
		TestExcelParser myExcelParser = new TestExcelParser();
		selectors = myExcelParser.getlistOfAllSelectors();
		String pageName = "MSG.com - Event Details Page";
		String testNumber = "3";

		LOGGER.info(pageName + " Page: Test Step-" + testNumber
				+ ": Verify Even Date Section.");

		try {

			/*
			 * Set required WebElements to be used in test scenario
			 */

			WebElement eventDateTitle = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTitle");
			WebElement eventDateTime = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventDateTime");
			WebElement eventLocation = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageEventLocation");
			WebElement buyTicketsButton = getWebElement(driver, selectors,
					"MSGTC007EventDetailsPageBuyTicketsButton");
			isMyTestPassed = true;

			// Validates the Event Title is shown
			isMyTestPassed = testText(eventDateTitle, pageName, "Event Title",
					testNumber, isMyTestPassed);
			String eventNumber = eventDateTitle.getText().split(" ")[0];

			// Validates the Event Date Selector is present
			isMyTestPassed = testText(eventDateTime, pageName,
					"Event Date Selector", testNumber, isMyTestPassed);

			// Validates the Event Date Pop Up is shown
			eventDateTime.click();
			sleep(1);
			WebElement eventDateButtonDropdown = getWebElement(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateButtonDropdown");
			isMyTestPassed = testText(eventDateButtonDropdown, pageName,
					"Hero Event Type", testNumber, isMyTestPassed);
			List<WebElement> eventDateDropdownEvents = getWebElements(driver,
					selectors,
					"MSGTC007EventDetailsPageEventDateDropdownEvents");

			// Validates the Event Date Pop Up matches the number of events
			isMyTestPassed = testTextMatches(
					Integer.toString(eventDateDropdownEvents.size()),
					eventNumber, pageName, "Hero Event Type", testNumber,
					isMyTestPassed);
			pressESC(driver);
			sleep(1);

			// Validates the Event Venue is shown
			isMyTestPassed = testText(eventLocation, pageName, "Event Venue",
					testNumber, isMyTestPassed);

			// Validates the Buy Ticket is shown
			isMyTestPassed = testText(buyTicketsButton, pageName,
					"Buy Ticket Button", testNumber, isMyTestPassed);
			sleep(5);

			// Validates the Ticketmaster url is shown
			isMyTestPassed = testURLContains(driver, "ticketmaster.com/event/",
					pageName, "Hero Event Type", testNumber, isMyTestPassed);

			return isMyTestPassed;

		} catch (Exception e) {
			LOGGER.error(pageName + " Page: Test Step-" + testNumber
					+ ": Even Date Elements are missing!!");
			LOGGER.error(e);
			return isMyTestPassed = false;
		}
	}
}
