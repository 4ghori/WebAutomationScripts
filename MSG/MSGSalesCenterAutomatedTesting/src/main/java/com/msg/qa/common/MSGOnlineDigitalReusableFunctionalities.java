package com.msg.qa.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.validator.EmailValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FixedCutProvider;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.exceptions.NewTestException;
import com.applitools.eyes.exceptions.TestFailedException;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.msg.qa.MSGOnlineDigital.SalesCenter.MSGSalesCenterURLs;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@SuppressWarnings("deprecation")
public class MSGOnlineDigitalReusableFunctionalities
		extends
			MSGOnlineDigitalGlobalParameters {

	static String EEAPIKeyProd = "Bearer e0554a52bf12b176ae14a9f85b60fcb2";
	static String[] EEEventCity = {"New%20York", "Chicago", "Inglewood"};
	static String[] EEEventTypes = {"Sports", "Music", "Family", "Arts&Theater",
			"Comedy", "Knicks", "Rangers", "Liberty", "Rockettes",
			"Special%20Events", "Chase%20CardHolder%20Access", "Other"};
	static String[] EEEventTimes = {"This Week", "Next Week", "Current Month",
			"Next month", "Custom"};

	@FindBy(xpath = "//div[contains(@class, 'xmas-sponsors')]")
	public ExtendedWebElement eWEmyPartnerText;

	public MSGOnlineDigitalReusableFunctionalities(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		/**
		 * a. Total Event Count for All Events + Anytime + Anywhere b. Total
		 * Event Count for All Events + Anytime + New York c. Total Event Count
		 * for All Events + Anytime + Chicago d. Total Event Count for All
		 * Events + Anytime + Inglewood e. Total Event Count for
		 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
		 * Events + Anytime + Anywhere f. Total Event Count for
		 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
		 * Events + Anytime + New York g. Total Event Count for
		 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
		 * Events + Anytime + Chicago h. Total Event Count for
		 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
		 * Events + Anytime + Inglewood i. Total Event Count for "Today","This
		 * Week" ,"Next Week","This Month","Next Month","Date Range"
		 */
		System.out.println(
				"Total Event Count for All Events + Anytime + Anywhere: "
						+ getTotalEventCount());

		System.out.println(
				"Total Event Count for All Events + Anytime + New York: "
						+ getTotalEventCountForNewYork());

		System.out.println(
				"Total Event Count for All Events + Anytime + Chicago: "
						+ getTotalEventCountForChicago());

		System.out.println(
				"Total Event Count for All Events + Anytime + Inglewood: "
						+ getTotalEventCountForInglewood());

		for (int i = 0; i < EEEventTypes.length; i++) {
			System.out.println("Total Event Count for " + EEEventTypes[i]
					+ " + Anytime + Anywhere: "
					+ getTotalEventCount(EEEventTypes[i]));

			System.out.println("Total Event Count for " + EEEventTypes[i]
					+ " + Anytime + New York: "
					+ getTotalEventCountForNewYork(EEEventTypes[i]));

			System.out.println("Total Event Count for " + EEEventTypes[i]
					+ " + Anytime + Chicago: "
					+ getTotalEventCountForChicago(EEEventTypes[i]));

			System.out.println("Total Event Count for " + EEEventTypes[i]
					+ " + Anytime + Inglewood: "
					+ getTotalEventCountForInglewood(EEEventTypes[i]));
		}

		/**
		 * Happening Section handling of msg.com calendar
		 */

		System.out.println(
				"Total Event Count for All Events + This Week + Anywhere: "
						+ getTotalEventCountHappening("This Week"));

		System.out.println(
				"Total Event Count for All Events + Next Week + Anywhere: "
						+ getTotalEventCountHappening("Next Week"));

		System.out.println(
				"Total Event Count for All Events + This Month + Anywhere: "
						+ getTotalEventCountHappening("This Month"));

		System.out.println(
				"Total Event Count for All Events + Next Month + Anywhere: "
						+ getTotalEventCountHappening("Next Month"));

		System.out.println(
				"Total Event Count for All Events + Date Range + Anywhere: "
						+ getTotalEventCountHappeningDateRange("10032017",
								"12312018"));

	}

	public static boolean isPartnersPresent(WebDriver driver) {
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement myPartnerText = driver.findElement(
					By.xpath("//div[contains(@class, 'sponsors')]"));
			jse.executeScript("arguments[0].scrollIntoView();", myPartnerText);
			// myPartnerText.click();

			WebDriverWait wait = new WebDriverWait(driver, 30);
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@style,
			// 'text-align')][contains(@class, 'sponsor-logos')]/p/a")));
			// List<WebElement> myPartners =
			// driver.findElements(By.xpath("//div[contains(@style,
			// 'text-align')][contains(@class, 'sponsor-logos')]/p/a"));
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[contains(@class, 'sponsors')]/a")));
			List<WebElement> myPartners = driver.findElements(
					By.xpath("//div[contains(@class, 'sponsors')]/a"));

			if (myPartners.size() > 0) {
				System.out.println("We have total " + myPartners.size()
						+ " Partners in the Footer of the Page!");
				System.out.println("Partners are: ");

				for (WebElement myElem : myPartners) {
					System.out.print(myElem.getText().toString() + "   ");
				}
				System.out.println("");
				return true;
			} else {
				System.out.println(
						"Partners are missing in the Footer of the Page!");
				return false;
			}
		} catch (WebDriverException e) {
			System.out
					.println("Partners are missing in the Footer of the Page!");
			return false;
		}
	}

	public static boolean isSponsorsPresent(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(
					ExpectedConditions.presenceOfElementLocated(By.cssSelector(
							"[class='hidden-xs']>p>[class*='sponsor-logos']")));
			List<WebElement> mySponsors = driver.findElements(By.cssSelector(
					"[class='hidden-xs']>p>[class*='sponsor-logos']"));

			if (mySponsors.size() > 0) {
				System.out.println("We have total " + mySponsors.size()
						+ " Sponsors in the Footer of the Page!");
				System.out.println("Sponsors are: ");

				for (WebElement myElem : mySponsors) {
					System.out.print(
							myElem.getAttribute("title").toString() + "   ");
				}
				System.out.println("");
				return true;
			} else {
				System.out.println(
						"Sponsors are missing in the Footer of the Page!");
				return false;
			}
		} catch (WebDriverException e) {
			System.out
					.println("Sponsors are missing in the Footer of the Page!");
			return false;
		}
	}

	public static boolean isRelatedContentSectionPresent(WebDriver driver) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement myRelatedContentsText = driver
					.findElement(By.cssSelector(
							"[class='container related-content-container']>h3"));
			jse.executeScript("arguments[0].scrollIntoView();",
					myRelatedContentsText);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.cssSelector("[class='row related-content']")));
			List<WebElement> myRealtedContents = driver.findElements(
					By.cssSelector("[class='row related-content']"));

			if (myRealtedContents.size() > 0) {
				System.out.println("We have total " + myRealtedContents.size()
						+ " Related Contents on the Page!");
				return true;
			} else {
				System.out.println("Related Contents is missing on the Page!");
				return false;
			}
		} catch (WebDriverException e) {
			System.out.println("Related Contents is missing on the Page!");
			return false;
		}
	}

	public static void SendMailViaSendGrid(String EmailBody)
			throws IOException {
		Email from = new Email("MSGTesting@msg.com");
		String subject = "Jenkins Error - Rockettes Regression Script is failed!";
		Email to = new Email("rachitkumar.rastogi@msg.com");
		Content content = new Content("text/plain",
				"Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at "
						+ java.text.DateFormat.getDateTimeInstance()
								.format(Calendar.getInstance().getTime())
						+ "." + "\r\nThanks, Your MSG QA Automation Team.");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(
				"SENDGRID_API_KEY");
		Request request = new Request();
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			Response response = sg.api(request);
			System.out.println(response.statusCode);
		} catch (IOException ex) {
			throw ex;
		}
	}

	public static Integer verifyIfLinkIsWorking(String urlToVerify)
			throws Exception {
		URL url = new URL(urlToVerify);
		HttpURLConnection myConnection = (HttpURLConnection) url
				.openConnection();
		try {
			myConnection.connect();
			Integer myResponse = myConnection.getResponseCode();
			myConnection.disconnect();
			return myResponse;
		} catch (Exception exp) {
			System.out.println("URL: " + url + " Can't be rsolved");
			return 404;
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean verifyEmailAddress(String EmailAddress) {
		return EmailValidator.getInstance().isValid(EmailAddress);
	}

	public static String getWebElementAttribute(WebElement webElement,
			String attributeName) {
		return webElement.getAttribute(attributeName);
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0,0);");
	}

	public static void scrollUp(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scrollBy(0,-250);");
	}

	public static void scrollUp(WebDriver driver, int pixels) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(String.format("scrollBy(0,-%d);", pixels));
	}

	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scrollBy(0,250);");
	}

	public static void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
	}

	public static String getUserAgent(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		return jse.executeScript("navigator.userAgent;").toString();
	}

	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement getWebElement(WebDriver driver,
			Map<String, String[]> selectors, String webElementName) {
		return getWebElement(driver, selectors, webElementName, "", false);
	}

	public static WebElement getWebElement(WebDriver driver,
										   Map<String, String[]> selectors, String webElementName, String value) {
		return getWebElement(driver, selectors, webElementName, value, false);
	}

	public static WebElement getWebElement(WebDriver driver,
			Map<String, String[]> selectors, String webElementName,
			String value, Boolean forceMedium) {
		String selector = "";
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String platform = capabilities.getCapability("platformName") != null ?
				capabilities.getCapability("platformName").toString() :
				capabilities.getPlatform().toString();
		try {
			String[] mySelector = selectors.get(webElementName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement webElement = null;
			/**
			 * Change done for handling the bug as RemoteWebDriver is returning platform as ANY.
			 * By Rachit Kumar Rastogi on 11/20/2017
			 */
			if (MSGOnlineDigitalReusableFunctionalities
					.isMobilePlatform(platform) && !forceMedium) {
				if (mySelector[1].isEmpty()) {
					selector = String.format(mySelector[0], value);
				} else {
					selector = String.format(mySelector[1], value);
				}
			} else {
				selector = String.format(mySelector[0], value);
			}

			if (selector.substring(0, 2).contains("/")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(selector)));
				webElement = driver.findElement(By.xpath(selector));
			} else {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector(selector)));
				webElement = driver.findElement(By.cssSelector(selector));
			}
			return webElement;
		} catch (Exception e) {
			System.out.println(" ================== ELEMENT ERROR ");
			LOGGER.error(" Failure on getWebElement for element: "
					+ webElementName + ". With locator: " + selector);
			LOGGER.error(" MESSAGE: " + e.getLocalizedMessage());
			System.out.println(" ================================ ");
			return null;
		}
	}

	public static List<WebElement> getWebElements(WebDriver driver,
			Map<String, String[]> selectors, String webElementName) {
		return getWebElements(driver, selectors, webElementName, "", false);
	}

	public static List<WebElement> getWebElements(WebDriver driver,
												  Map<String, String[]> selectors, String webElementName, String value) {
		return getWebElements(driver, selectors, webElementName, value, false);
	}

	public static List<WebElement> getWebElements(WebDriver driver,
			Map<String, String[]> selectors, String webElementName,
			String value, Boolean forceMedium) {
		String selector = "";
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String platform = capabilities.getCapability("platformName") != null ?
				capabilities.getCapability("platformName").toString() :
				capabilities.getPlatform().toString();
		try {
			String[] mySelector = selectors.get(webElementName);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			List<WebElement> webElements = null;
			/**
			 * Change done for handling the bug as RemoteWebDriver is returning platform as ANY.
			 * By Rachit Kumar Rastogi on 11/20/2017
			 */
			if (MSGOnlineDigitalReusableFunctionalities
					.isMobilePlatform(platform) && !forceMedium) {
				if (mySelector[1].isEmpty()) {
					selector = String.format(mySelector[0], value);
				} else {
					selector = String.format(mySelector[1], value);
				}
			} else {
				selector = String.format(mySelector[0], value);
			}

			if (selector.substring(0, 2).contains("/")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(selector)));
				webElements = driver.findElements(By.xpath(selector));
			} else {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector(selector)));
				webElements = driver.findElements(By.cssSelector(selector));
			}
			return webElements;
		} catch (Exception e) {
			System.out.println(" =========== ELEMENTS GROUP ERROR ");
			LOGGER.error(" Failure on getWebElements for group of elements: "
					+ webElementName + ". With locator: " + selector);
			LOGGER.error(" MESSAGE: " + e.getLocalizedMessage());
			System.out.println(" ================================ ");
			return null;
		}
	}

	public static String grabNewUrlAndCloseTab(WebDriver driver) {
		String url = null;
		try {
			String parentTab = driver.getWindowHandle();
			for (String windowHandle : driver.getWindowHandles()) {
				if (!parentTab.equals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					sleep(1);
					break;
				}
			}
			url = driver.getCurrentUrl();
			driver.close();
			driver.switchTo().window(parentTab);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return url;
	}

	public static boolean isMobilePlatform(String plaformName) {
		return plaformName.matches("(?i).*iphone.*|.*ipad.*|.*android.*|.*iOS.*");
	}

	public static boolean isIpad(String plaformName) {
		return plaformName.matches("(?i).*ipad.*");
	}

	public static boolean isIOSPlatform(String plaformName) {
		return plaformName.matches("(?i).*ANY.*|.*iphone.*|.*ipad.*|.*iOS.*");
	}

	public static boolean isSafariBrowser(String browserName) {
		return browserName.matches("(?i).*safari.*|.*Safari.*");
	}

	public static boolean isIEBrowser(String browserName) {
		return browserName.matches("(?i).*iexplore.*|.*internet explorer.*");
	}
	
	public static boolean isEdgeBrowser(String browserName) {
		return browserName.matches("(?i).*MicrosoftEdge.*|.*microsoft edge.*");
	}

	/**
	 * Event Engine API Verification methods a. Total Event Count for All Events
	 * + Anytime + Anywhere b. Total Event Count for All Events + Anytime + New
	 * York c. Total Event Count for All Events + Anytime + Chicago d. Total
	 * Event Count for All Events + Anytime + Inglewood e. Total Event Count for
	 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
	 * Events + Anytime + Anywhere f. Total Event Count for
	 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
	 * Events + Anytime + New York g. Total Event Count for
	 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
	 * Events + Anytime + Chicago h. Total Event Count for
	 * "Sports","Music","Family","Arts&Theater","Comedy","Knicks","Rangers","Liberty","Rockettes","Special%20Events","Chase%20CardHolder%20Access","Other"
	 * Events + Anytime + Inglewood
	 *
	 * @author Rachit Kumar Rastogi
	 * @date 10/03/17
	 */

	public static int getEventCountWithFilter(String uri, String Token) {
		int totalCount = 0;
		System.out.println("Fetching Events Information....");
		try {

			BufferedReader myBufferedReader;
			// 1. Read Shows Information from Event Engine Api
			HttpClient myDefaultHttpClientEventEngine = HttpClients.createDefault();
			HttpGet myHttpGetRequestEventEngine = new HttpGet(uri);
			myHttpGetRequestEventEngine.addHeader("accept", "application/json");
			myHttpGetRequestEventEngine.addHeader("Authorization", Token);
			HttpResponse myHttpResponseEventEngine = myDefaultHttpClientEventEngine
					.execute(myHttpGetRequestEventEngine);
			if (myHttpResponseEventEngine.getStatusLine()
					.getStatusCode() != 200) {
				throw new RuntimeException(
						"Http Error Code: " + myHttpResponseEventEngine
								.getStatusLine().getStatusCode());
			}
			myBufferedReader = new BufferedReader(new InputStreamReader(
					(myHttpResponseEventEngine.getEntity().getContent())));

			if (myBufferedReader != null) {
				JSONParser myJSONParser = new JSONParser();
				JSONObject myJSONObject = (JSONObject) myJSONParser
						.parse(myBufferedReader);
				String myJSONLine = myJSONObject.toJSONString();
				JsonElement myJSONElement = new JsonParser().parse(myJSONLine);
				JsonObject myJSONHeadNodeObject = myJSONElement
						.getAsJsonObject();
				JsonObject myJSONMeta = myJSONHeadNodeObject
						.getAsJsonObject("meta");
				JsonObject myJSONMetaPagination = myJSONMeta
						.getAsJsonObject("pagination");
				JsonElement myJSONMetaPaginationTotalElements = myJSONMetaPagination
						.get("total_elements");
				return myJSONMetaPaginationTotalElements.getAsInt();
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return totalCount;
	}
	
	public static int getEventCountWithFilterFromWP(String uri) {
		int totalCount = 0;
		int pagesToInvestigate = 0;
		
		HttpClient httpClient = HttpClients.createDefault();
		
		HttpGet initialRequest = new HttpGet(uri);
		initialRequest.addHeader("Accept", "application/json");
		
		HttpResponse initialResponse = null;
		
		try {
			initialResponse = httpClient.execute(initialRequest);
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
		
		if (initialResponse.getStatusLine()
				.getStatusCode() != 200) {
			throw new RuntimeException(
					"Http Error Code: " + initialResponse
							.getStatusLine().getStatusCode());
		}
		
		JsonObject jsonRoot = null;
		
		try(BufferedReader jsonReader = new BufferedReader(new InputStreamReader(initialResponse.getEntity().getContent()))) {
			jsonRoot = new JsonParser().parse(jsonReader).getAsJsonObject();
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}

		if (jsonRoot != null) {
			JsonObject jsonResults = jsonRoot.getAsJsonObject("results");
			totalCount += jsonResults.entrySet().size();
			
			// The total_elements value is unreliable as it is never recomputed on WP. In order to get the correct
			// number of events we need to count them one by one. This entails querying events for every page of the
			// paged results and counting the events returned.
			pagesToInvestigate = jsonRoot
					.getAsJsonObject("meta")
					.getAsJsonObject("pagination")
					.get("total_pages")
					.getAsInt();
		}
		
		// If only one page, nothing left to do
		if (pagesToInvestigate < 2) {
			return totalCount;
		}
		
		// Spawn futures for counting paged events in parallel. One future per page.
		totalCount += IntStream.rangeClosed(2, pagesToInvestigate).parallel()
				
			// First create the requests for each page
			.mapToObj(page -> {
				String suffix = "page=" + page;
				
				suffix = uri.indexOf('?') != -1
					? '&' + suffix
					: '?' + suffix;
				
				HttpGet request = new HttpGet(uri + suffix);
				request.addHeader("Accept", "application/json");
				return request;
			})
			
			// Create the future that will count the events on each page
			.map(request -> {
				return CompletableFuture.supplyAsync(() -> {
					HttpResponse response = null;
					
					try {
						response = httpClient.execute(request);
					} catch (IOException ioException) {
						return null;
					}
					
					if (response.getStatusLine().getStatusCode() != 200) {
						return null;
					}
					
					try(BufferedReader jsonReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
						return new JsonParser().parse(jsonReader).getAsJsonObject();
					} catch (IOException ioExcception) {
						return null;
					}
				}).thenApplyAsync(jsonResponse -> {
					int count = 0;
					
					if (jsonResponse != null) {
						JsonObject jsonResults = jsonResponse.getAsJsonObject("results");
						count += jsonResults.entrySet().size();
					}
					
					return count;
				});
			})
			
			// Get all the results and add them together
			.map(CompletableFuture::join)
			.collect(Collectors.summingInt(n -> n));
		
		return totalCount;
	}

	/**
	 * Call these methods for getting the total event count without any filters
	 *
	 * @return
	 */

	public static int getTotalEventCount() {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?publish=true");
	}

	public static int getTotalEventCountForNewYork() {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=New%20York&publish=true");
	}

	public static int getTotalEventCountForChicago() {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=Chicago&publish=true");
	}

	public static int getTotalEventCountForInglewood() {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=inglewood&publish=true");
	}

	/**
	 * Call these events for retrieving the total event count for SELECTED
	 * CATEGORY
	 *
	 * @return
	 */
	public static int getTotalEventCount(String eventCategory) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?category=" + eventCategory + "&publish=true");
	}

	public static int getTotalEventCountForNewYork(String eventCategory) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=New%20York&category=" + eventCategory + "&publish=true");
	}

	public static int getTotalEventCountForChicago(String eventCategory) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=Chicago&category=" + eventCategory + "&publish=true");
	}

	public static int getTotalEventCountForInglewood(String eventCategory) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		return getEventCountWithFilterFromWP(baseURL + "/wp-json/msg/v1/events?city=inglewood&category=" + eventCategory + "&publish=true");
	}

	public static List<String> getStartAndEndDate(String calendarDays) {
		List<String> myDates = new ArrayList<>();
		String start_date = "", end_date = "";
		LocalDate myLocal = LocalDate.now();
		start_date = DateTimeFormatter.ofPattern("MM/dd/yyy").format(myLocal);

		switch (calendarDays) {
			case "Today" : {
				start_date = start_date.replaceAll("/", "");
				end_date = start_date;
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}

			case "This Week" : {
				Calendar calendar2 = Calendar.getInstance();
				int weekday2 = calendar2.get(Calendar.DAY_OF_WEEK);
				int days2 = Calendar.SUNDAY - weekday2;
				if (days2 < 0)
					days2 += 7;
				calendar2.add(Calendar.DAY_OF_YEAR, days2);
				end_date = DateTimeFormatter.ofPattern("MM/dd/yyy")
						.format(myLocal.plusDays(days2));
				start_date = start_date.replaceAll("/", "");
				end_date = end_date.replaceAll("/", "");
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}

			case "Next Week" : {
				Calendar calendar3 = Calendar.getInstance();
				int weekday3 = calendar3.get(Calendar.DAY_OF_WEEK);
				int days3 = Calendar.SUNDAY - weekday3;
				if (days3 < 0)
					days3 += 14;
				calendar3.add(Calendar.DAY_OF_YEAR, days3);
				end_date = DateTimeFormatter.ofPattern("MM/dd/yyy")
						.format(myLocal.plusDays(days3));
				start_date = start_date.replaceAll("/", "");
				end_date = end_date.replaceAll("/", "");
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}

			case "Current Month" : {
				end_date = DateTimeFormatter.ofPattern("MM/dd/yyy").format(
						myLocal.withDayOfMonth(myLocal.getMonth().maxLength()));
				start_date = start_date.replaceAll("/", "");
				end_date = end_date.replaceAll("/", "");
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}

			case "Next Month" : {
				LocalDate myNewLocal = LocalDate.now().plusMonths(1);
				end_date = DateTimeFormatter.ofPattern("MM/dd/yyy")
						.format(myNewLocal.withDayOfMonth(
								(myNewLocal.getMonth().maxLength())));
				start_date = start_date.replaceAll("/", "");
				end_date = end_date.replaceAll("/", "");
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}

			default : {
				start_date = start_date.replaceAll("/", "");
				end_date = start_date;
				System.out.println("For the Happening " + calendarDays
						+ " - Start date is: " + start_date
						+ " and End date is: " + end_date);
				break;
			}
		}

		myDates.add(start_date);
		myDates.add(end_date);
		return myDates;
	}

	/**
	 * Call these events for retrieving the total event count for SELECTED DATE
	 * DURATION
	 *
	 * @return
	 */
	public static int getTotalEventCountHappening(String eventHappening) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		List<String> myDates = getStartAndEndDate(eventHappening);
		return getEventCountWithFilter(
				baseURL + "/wp-json/msg/v1/events?start_date="
						+ myDates.get(0).toString() + "&end_date="
						+ myDates.get(1).toString() + "&publish=true",
				EEAPIKeyProd);
	}

	public static int getTotalEventCountHappeningDateRange(String start_date,
			String end_date) {
		String baseURL = MSGSalesCenterURLs.getEnvironmentBaseURL(MSGSalesCenterURLs.getTestEnvironment());
		System.out.println("For the Happening Date Range - Start date is: "
				+ start_date + " and End date is: " + end_date);
		return getEventCountWithFilter(
				baseURL + "/wp-json/msg/v1/events?start_date=" + start_date
						+ "&end_date=" + end_date + "&publish=true",
				EEAPIKeyProd);
	}

	public static boolean testVisual(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus) {
		try {
			if (element.isDisplayed()) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is present");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not present");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Overloaded method with driver parameters ---- Only used for Logging Purposes with one extra parameter rest remains same.
	 * @param element
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testVisual(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus, String driverParameters) {
		try {
			if (element.isDisplayed()) {
				LOGGER.info(driverParameters+" - "+ pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is present");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+ pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not present");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testNotVisible(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus) {
		try {
			if (!element.isDisplayed() || element.getText().equals("")) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not shown, as expected");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName
						+ " is displayed. Not an expected behaviour");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Overloaded method for testNotVisible with same functionality only with driver parameter in addition.
	 * @param element
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testNotVisible(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus, String driverParameters) {
		try {
			if (!element.isDisplayed() || element.getText().equals("")) {
				LOGGER.info(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not shown, as expected");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName
						+ " is displayed. Not an expected behaviour");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testText(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus) {
		try {
			if (element.isDisplayed() & element.getText() != null) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is present");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not present");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Overloaded method of testText with additional parameter for driver
	 * @param element
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testText(WebElement element, String pageName,
			String moduleName, String testNumber, boolean testStatus, String driverParameters) {
		try {
			if (element.isDisplayed() & element.getText() != null) {
				LOGGER.info(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is present");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " is not present");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testTextMatches(String value, String expected,
			String pageName, String moduleName, String testNumber,
			boolean testStatus) {
		try {
			if (expected.equals(value)) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " '" + value
						+ "' matches the expected value: '" + expected + "'");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " has a different value. Expected ‘"
						+ expected + " but got ‘" + value + "‘");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Overloaded method with driver Parameters.
	 * @param value
	 * @param expected
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testTextMatches(String value, String expected,
			String pageName, String moduleName, String testNumber,
			boolean testStatus, String driverParameters) {
		try {
			if (expected.equals(value)) {
				LOGGER.info(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " '" + value
						+ "' matches the expected value: '" + expected + "'");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " has a different value. Expected ‘"
						+ expected + " but got ‘" + value + "‘");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testTextIncluded(String value, String expected,
			String pageName, String moduleName, String testNumber,
			boolean testStatus) {
		try {
			if (value.contains(expected)) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + "  contains the expected value");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName
						+ " does not contain the expected text. Expected ‘"
						+ expected + " but got ‘" + value + "‘");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Overloaded method for testTextIncluded only change is driverParameters are included.
	 * @param value
	 * @param expected
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testTextIncluded(String value, String expected,
			String pageName, String moduleName, String testNumber,
			boolean testStatus, String driverParameters) {
		try {
			if (value.contains(expected)) {
				LOGGER.info(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + "  contains the expected value");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName
						+ " does not contain the expected text. Expected ‘"
						+ expected + " but got ‘" + value + "‘");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testListSizeIsAbove(int listSize, int expectedSize,
			String pageName, String moduleName, String testNumber,
			boolean testStatus) {
		try {
			if (listSize >= expectedSize) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " grouped elements are present");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " not showing the expected quantity");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}
	
	/**
	 * Obverloaded method testlistSizeIsAbove same functionality only with driver Parameters.
	 * @param listSize
	 * @param expectedSize
	 * @param pageName
	 * @param moduleName
	 * @param testNumber
	 * @param testStatus
	 * @param driverParameters
	 * @return
	 */
	public static boolean testListSizeIsAbove(int listSize, int expectedSize,
			String pageName, String moduleName, String testNumber,
			boolean testStatus, String driverParameters) {
		try {
			if (listSize >= expectedSize) {
				LOGGER.info(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " grouped elements are present");
			} else {
				testStatus = false;
				LOGGER.error(driverParameters+" - "+pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " not showing the expected quantity");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(driverParameters+" - "+e);
			return false;
		}
	}

	public static boolean testURLContains(WebDriver driver,
			String expectedValue, String pageName, String moduleName,
			String testNumber, boolean testStatus) {
		try {
			if (driver.getCurrentUrl().contains(expectedValue)) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + "  matches the expected URL chunk");
			} else {
				testStatus = false;
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + ". URL differs. Expected ‘"
						+ expectedValue + " to contain ‘"
						+ driver.getCurrentUrl() + "’");
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}
	}

	public static void pressESC(WebDriver driver) {
		new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
	}

	public static void pressEnter(WebDriver driver) {
		new Actions(driver).sendKeys(Keys.ENTER).build().perform();
	}

	public static boolean testLinkNewTab(WebElement element, int seconds,
			String expectedURL, String pageName, String moduleName,
			String testNumber, boolean testStatus, WebDriver driver) {
		try {
			element.click();
			sleep(seconds);
			String newURL = grabNewUrlAndCloseTab(driver);
			if (newURL.contains(expectedURL)) {
				LOGGER.info(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName + " accessed the expected url: " + newURL);
			} else {
				LOGGER.error(pageName + " Page: Test Step-" + testNumber + ": "
						+ moduleName
						+ " failed to access expected URL. Accessed URL: "
						+ newURL);
				testStatus = false;
			}
			return testStatus;
		} catch (Exception e) {
			LOGGER.error(e);
			return false;
		}

	}

	public static void writeStringCharacterByCharacter(WebElement element,
			String string) {
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			element.sendKeys(String.valueOf(c));
		}

	}

	public static boolean isElementPresent(WebDriver driver,
			WebElement element) {
		try {
			WebDriverWait wwait = new WebDriverWait(driver, 1);
			wwait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException
				| TimeoutException e) {
			return false;
		}
	}

	public static void waitForElement(WebDriver driver, String selector,
			int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			if (selector.substring(0, 2).contains("/")) {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(selector)));
			} else {
				wait.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector(selector)));
			}
		} catch (Exception e) {
		}
	}

	public static void waitForElement(WebDriver driver, WebElement element,
			int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		}
	}

	public static void waitForElementToDisapear(WebDriver driver,
			WebElement element, int seconds) {
		if (isElementPresent(driver, element)) {
			new WebDriverWait(driver, seconds).until(ExpectedConditions
					.not(ExpectedConditions.visibilityOf(element)));
		}
	}

	public static void waitOnLoader(WebDriver driver) {
		try {
			WebElement loader = driver
					.findElement(By.xpath("//div[contains(@class,'loader')]"));
			waitForElement(driver, loader, 2);
			waitForElementToDisapear(driver, loader, 30);
		} catch (Exception e) {
		}
	}

	public static String getCurrentMonthName() {
		String[] monthName = {"January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December"};
		return monthName[Calendar.getInstance().get(Calendar.MONTH)];
	}

	public static void moveToSlide(WebDriver driver, int index, WebElement dotsContainer, List<WebElement> dots, List<WebElement> slides) {
		scrollToElement(driver, dotsContainer);
		scrollUp(driver, 150);
		dots.get(index).click();
		sleep(1);
		scrollToElement(driver, slides.get(index));
	}

	public static void removeIntercom(WebDriver driver) {
		if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("return document.querySelector('.intercom-notifications-frame') && " +
					"document.querySelector('.intercom-notifications-frame').remove();");
		}
	}

	public static void openSecondayNav(WebDriver driver, Map<String, String[]> selectors) {
		WebElement mySecondNavTitle = getWebElement(driver, selectors,
				"L2PageSecondaryNavTitle");
		scrollToElement(driver, mySecondNavTitle);
		sleep(1);
		scrollUp(driver);
		sleep(1);
		mySecondNavTitle.click();
		sleep(3);
	}
	
	/**
	 * Method for Running the Visual tests, if you need to run with your user just replace the API Key below.
	 * @author Rachit Kumar Rastogi
	 * @param driver
	 * @return
	 */
	//private static BatchInfo batch; 
	
	public static boolean executeVisualTest(WebDriver driver)
	{
		String myDriverParameters = "Platform: "+((RemoteWebDriver) driver).getCapabilities().getPlatform().toString() +" "+ ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString() + " Version: " +((RemoteWebDriver) driver).getCapabilities().getVersion();
		
		BatchInfo batchInfo = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
		String batchId = System.getenv("APPLITOOLS_BATCH_ID");
		if (batchId != null) {
		    batchInfo.setId(batchId);
		}
		Eyes eyes = new Eyes();
		/**
		 * Replace the API key if you would like to run tests with your user.
		 */
	    eyes.setApiKey("DJZskprruP4VgGih6dp4ilrLk0MDoVDItxuZpBXmIUA110");
	    eyes.setForceFullPageScreenshot(true);
	    eyes.setHideScrollbars(true);
	    eyes.setStitchMode(StitchMode.CSS);
		eyes.setBatch(batchInfo);
		eyes.setMatchLevel(MatchLevel.LAYOUT2);
		
		try{  
            driver.get(driver.getCurrentUrl());
            System.out.println(myDriverParameters+": Applitools Visual Test - msg.com page is: "+driver.getTitle()+" and URL is: "+driver.getCurrentUrl());
            /**
             * Create the Regions based upon the viewport size and desktop/mobile.
             * Change done by Rachit Kumar Rastogi on 11/28/2017.
             */
           
            /**
             * Handle iPhone viewport
             */
            if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*iphone.*"))
            {
            	eyes.setImageCut(new FixedCutProvider(95,135,0,0));
            	eyes.open(driver, "MSG", "Mobile (iPhone) - "+driver.getTitle());
            }
            
            /**
             * Handle android viewport
             */
            if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*android.*"))
            {
            	eyes.open(driver, "MSG", "Mobile (Android) - "+driver.getTitle());           
            }
            /**
             * Handle iPad Viewport
             */
            else if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*ipad.*"))
            {
            	/**
            	 * Put FixedCutProvider for cutting the iPad boundaries extra space in AppliTools.
            	 */
            	eyes.open(driver, "MSG", "Mobile (iPad) - "+driver.getTitle());
            }
            
            /**
             * Handle desktop Viewport
             */
            else
            {
            	eyes.open(driver, "MSG", "Desktop - "+driver.getTitle(), new RectangleSize(1416,1200));	
            }
            eyes.checkWindow();
            eyes.close();
  	      }catch(NewTestException e)
  	      {
  	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Let's store Baseline version for msg.com!!");
  	    	 	return false;
  	      }
  	      catch(UnsupportedCommandException e1)
  	      {
	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Unsupported command Exception we can't continue!!");
	    	 	return false;
  	      }
  	      catch(TestFailedException e2)
  	      {
	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Test failed Exception we can't continue!!");
	    	 	return false;
  	      }
		return true;
	}
}