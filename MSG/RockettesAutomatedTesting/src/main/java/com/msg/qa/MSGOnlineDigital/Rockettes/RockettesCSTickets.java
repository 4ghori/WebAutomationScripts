package com.msg.qa.MSGOnlineDigital.Rockettes;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.commons.lang3.time.StopWatch;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class RockettesCSTickets {
	
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSTickets(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	/**
	 * 1. We are navigable to CS Tickets Page.
	 */	
	
	@Test(description="Are we on Rockettes Christmas Calendar Page?", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean onRockettesCSTicketsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Christmas Spectacular Calendar Page URL to be tested is: "+this.URL+" ******************* ");
		if (this.URL.contains("staging"))
			{
			//String myStagingURL = this.URL.replace("staging", "msgtransition:Msg123@staging");
			//driver.navigate().to(myStagingURL);
			driver.navigate().to(this.URL);
			}
		else
			{
			driver.navigate().to(this.URL);
			}	
		//driver.manage().window().maximize();
		isMyTestPassed = true;
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-1: We are on Rockettes Christmas Calendar Page?");	
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-1: We are not on Rockettes Christmas Calendar Page?");	
		}
		return isMyTestPassed;
	}
	
	/**2. Display Calendar - 1-19 Page
	 * a. Display default 1-19 page of calendar
	 * b. Get Calendar Load Time
	 */	
	
	@Test(description="Display 1 to 19 Calendar Page", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean getCalenderDisplay1to19Page() {
		
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-2: Is calendar displayed?");		
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendar-container']")));
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-2: Calendar is displayed.");
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-2: Calendar is not displayed.");
		}
		return isMyTestPassed;
	}
	
	/**3. Get Base Checks done on Calendar Page.
	 * a. Is Calendar Displayed?
	 * b. Are Filters Present?
	 * c. Is Helpful Tips displayed?
	 */	

	@Test(description="Initial Check on Calendar", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean onLoadGetBaseCheckDoneOnCalender() {
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-3: Basic Check on Calendar: Filters, Calendar, Sidebar etc.?");
		try
		{
		// 1. Is Purchasing filter bar Loaded?
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendar-filters-desktop calendar-filters']")));
		List<WebElement> myPurchasingFilters = driver.findElements(By.xpath("//div[@class='calendar-filters-desktop calendar-filters']"));
		for (int count = 0; count < myPurchasingFilters.size(); count++) {
			if (myPurchasingFilters.get(count).getLocation().getX() != 0)
				System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-3: Purchasing Filter: "+myPurchasingFilters.get(count).getText()+" is present in filters.");
		}

		// 2. Is Calendar displayed for at least one Month?
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'msg-calendars')]")));
		List<WebElement> myCalendarMonths = driver.findElements(By.xpath("//div[contains(@class, 'msg-calendars')]"));
		if (myCalendarMonths.size()>0)
		{
			for (int count =0;count<myCalendarMonths.size();count++)
				{
				System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-3: "+myCalendarMonths.get(count).getText()+" Month is present in Calendar.");
				}
		}
		
		// 3. Is Helpful Tips displayed?
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='calendar-sidebar']")));
		List<WebElement> myHelpfulTips = driver.findElements(By.xpath("//div[@id='calendar-sidebar']"));

		if (myHelpfulTips.get(0).isDisplayed())
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-3: Helpful Tips are present.");
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-3: Helpful Tips are missing!");
		}
		return isMyTestPassed;
		}

	/**4. Verify Calendar Cell Pricing Range. 
	 * a. Get the Price Range of Purchasing Filter 
	 * i. Get Minimum value 
	 * ii. Get Maximum value
	 */
	
	@Test(description="Price Range Filter => Get Min/Max Price", groups = { "smoke", "fullintegration" })
	public boolean onLoadGetPurchasingFilterPriceRange() 
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-4: Price Range Min and Max Price values are present?");	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='msg-calendar-filter-min']")));
		WebElement myPurchasingFilterMin = driver.findElement(By.xpath("//span[@id='msg-calendar-filter-min']"));

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='msg-calendar-filter-max']")));
		WebElement myPurchasingFilterMax = driver.findElement(By.xpath("//span[@id='msg-calendar-filter-max']"));

		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-4: Minimum Pricse in Slider is => " + myPurchasingFilterMin.getText());
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-4: Maximum Price in Slider is => " + myPurchasingFilterMax.getText());
		Thread.sleep(1000);
		
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-4: Price Range Min and Max Price values are present.");
		
		isMyTestPassed = true;
		} catch (WebDriverException | InterruptedException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-4: Price Range Min and Max Price values are missing!!");
		}
		return isMyTestPassed;	
	}
	
	/** 5. Get total valid days and shows when calendar is loaded. 
	 * a. Get the total valid days 
	 * b. Get the total valid shows
	 */ 
	
	@Test(description="Get all Valid days and Shows from Calendar.", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean onLoadGetAllValidDaysAndShowsfromCalendar() throws InterruptedException 
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-5: Get all Valid days and Shows from Calendar.");	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='fc-event-container']/a")));
		List<WebElement> myCalendarDate = driver.findElements(By.xpath("//td[@class='fc-event-container']/a"));
		if(myCalendarDate.size()>0)
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-5: Number of valid days in Calendar (Without Promo): " + myCalendarDate.size());
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
				myCalendarDate.get(i).click();
				// Now you shall have the element of desired date
				List<WebElement> myShowTimeWindow= driver.findElements(By.xpath("//div[@class='show-times']"));
				//System.out.println("MY SHOWTIME WINDOW: " + myShowTimeWindow);
				if (myShowTimeWindow.get(0).isDisplayed()) 
					{	
					List<WebElement> myShowTimesDisabled = driver.findElements(By.cssSelector(".available-time.disabled>div>label"));
					List<WebElement> myShowTimesTotal = driver.findElements(By.cssSelector(".available-time>div>label"));
					myShowTimesTotal.removeAll(myShowTimesDisabled);
					
					if(myShowTimesTotal.size() > 0) 
						{
						System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-5: Date "+myCalendarDate.get(i).getAttribute("data-event-datekey")+" is selectable and Show times are present.");
						
						//List<WebElement> myShowTimeSel = driver.findElements(By.cssSelector("[class='label__time']"));
						
							for(int count=0;count<myShowTimesTotal.size();count++)
								{
								System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-5: Show"+(count+1)+" is at Time "+myShowTimesTotal.get(count).getText()+".");
								}
						}
					}
			}
		isMyTestPassed = true;
		return isMyTestPassed;
		}
		else
		{
			isMyTestPassed = false;
			return isMyTestPassed; 
		}
		
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-5: Calendar is missing or no shows are present!!");
		}
		return isMyTestPassed;
	}
	
	/**6. Purchasing Filter: 1-19 / 20+ Options 
	 * a. Click on Purchasing Filter has to display "2" options. 
	 * b. Click on 1-19 option shall display 1-19 Page
	 * c. Click on 20+ option shall display 20+ Page.
	**/
	
	@Test(description="Verify Navigation sequence: 1-19 -> 20 -> 1-19 back Calendar Page", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean onClickOfPurchasingFilter() {
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-6: Verify Navigation sequence: 1-19 -> 20 -> 1-19 back Calendar Page.");
		try
		{
		// 1. Find purchasing Filter in UI
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='fa-chevron-down']")));
		List<WebElement> myPurchasingFilter = driver.findElements(By.cssSelector("[class*='fa-chevron-down']"));

		// 2. Click to expand the Menu
		JavascriptExecutor myJSExecutor = (JavascriptExecutor) driver;
		myJSExecutor.executeScript("arguments[0].click()", myPurchasingFilter.get(0));
		myJSExecutor.executeScript("arguments[0].click()", myPurchasingFilter.get(1));

		// 3. Wait for Menu Options.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='ticket-box']>div")));
		List<WebElement> myPurchasingFilterOptions = driver.findElements(By.cssSelector("[class='ticket-box']>div"));

		// 4. Click on 20+ Page
		myPurchasingFilterOptions.get(1).click();

		// 5. Click on 20+ Menu to get further options
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='fa-chevron-down']")));
		List<WebElement> myPurchasingFilterReload = driver.findElements(By.cssSelector("[class*='fa-chevron-down']"));

		// 6. Click to expand the Menu
		myJSExecutor.executeScript("arguments[0].click()", myPurchasingFilterReload.get(0));
		myJSExecutor.executeScript("arguments[0].click()", myPurchasingFilterReload.get(1));

		// 7. Wait for Menu Options.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='ticket-box']>div")));
		List<WebElement> myPurchasingFilterOptionsReload = driver.findElements(By.cssSelector("[class='ticket-box']>div"));

		// 8. Click on 1-19 Page
		myPurchasingFilterOptionsReload.get(0).click();
		isMyTestPassed = true;
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-6: Navigation sequence: 1-19 -> 20 -> 1-19 back Calendar Page is fine.");
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-6: Navigation sequence: 1-19 -> 20 -> 1-19 back Calendar Page is not fine!!");
		}
		return isMyTestPassed;
		}

	/**7. Apply Promo Code 
	 * a. Click on "Apply Promo Code" 
	 * b. Enter Promo code value 
	 * c. Click on "Apply Coupon" 
	 * d. If Valid Coupon - Read Text of Coupon 
	 * e. If Invalid Coupon - Read ToolTip Text
	 **/

	@Test(description="Promo Code Workflow => Apply/Remove for hard coded promo codes", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean onClickOfApplyPromoCodeSelected() throws FileNotFoundException, IOException, ParseException, InterruptedException  {
		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Promo Code Workflow => Apply/Remove for hard coded promo codes.");
		try
		{
		List<String> myPromoCodes = new ArrayList<String>();
		List<String> myValidPromoCodes = new ArrayList<String>();
		List<String> myInvalidPromoCodes = new ArrayList<String>();

		if (myPromoCodes.isEmpty()) {
			myPromoCodes.add("mcdc");
			myPromoCodes.add("chase");
			myPromoCodes.add("web1nov");	
			myPromoCodes.add("Chsemp");
			myPromoCodes.add("wrong");
			myPromoCodes.add("carlton");
		}

		// Promo Code Workflow:
		for (int count = 0; count < myPromoCodes.size(); count++) 
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// 1. One time only CLick on "Apply Promo Code" Text
			if (count == 0) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary.btn-md.apply-promo")));
				WebElement myPurchasingFilter = driver.findElement(By.cssSelector(".btn.btn-primary.btn-md.apply-promo"));
				myPurchasingFilter.click();
				}

			// 2. Type the Coupon Code in Textbox
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".promocode-text")));
			WebElement myPromoCode = driver.findElement(By.cssSelector(".promocode-text"));
			myPromoCode.sendKeys(myPromoCodes.get(count));

			// 3. Click on "Apply Coupon" Button
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
			WebElement myPromoCodeApplyButton = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
			myPromoCodeApplyButton.click();

			// 4. Check if Promo code has been applied or resulted in an error.
			try {
				WebElement myPromoCodeAppliedStateOK = driver.findElement(By.cssSelector(".promo-code-container"));
				if (myPromoCodeAppliedStateOK.isDisplayed()) 
					{
					System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Promo Code "+myPromoCodes.get(count) + " is applied.");
					myValidPromoCodes.add(myPromoCodes.get(count));
					// See after Applying promo code correct show IDs are getting in calendar.
					onLoadGetAllValidDaysAndShowsfromCalendar();
					}
			} 
			catch (NoSuchElementException e) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
				WebElement myPurchasingFilterRemovePromoCode = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
				myPurchasingFilterRemovePromoCode.click();
				continue;
				}
			
			try {
				WebElement myPromoCodeAppliedStateNotOK = driver.findElement(By.cssSelector(".popover-content"));
				if (myPromoCodeAppliedStateNotOK.isDisplayed()) 
					{
					System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Promo Code "+myPromoCodes.get(count) + " is invalid.");
					myInvalidPromoCodes.add(myPromoCodes.get(count));
					}
				} 
			catch (NoSuchElementException e) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
				WebElement myPurchasingFilterRemovePromoCode = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
				myPurchasingFilterRemovePromoCode.click();
				continue;
				}
		}

		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Below are the "+myValidPromoCodes.size()+ " Valid coupons: ");
		// Print Valid Coupons
		for (int count = 0; count < myValidPromoCodes.size(); count++)
			System.out.println(myValidPromoCodes.get(count));

		System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Below are the "+myInvalidPromoCodes.size()+ " Invalid coupons: ");
		// Print Invalid Coupons
		for (int count = 0; count < myInvalidPromoCodes.size(); count++)
			System.out.println(myInvalidPromoCodes.get(count));
		
		isMyTestPassed = true;
		
		return isMyTestPassed;
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes CS Calendar Page: Test Case-7: Promo Code Workflow => Apply/Remove for hard coded promo codes is failed!!");
		}
		
		return isMyTestPassed;
	}	
	
	@Test(description="Promo Code Workflow => Get List from event Engine API or Promo Code Dump File", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public static List<String> getPromoCodeList(List<String> myPromoCodes) throws IOException, ParseException 
	{
		RockettesGlobalParameters rGP = new RockettesGlobalParameters();
		// Automated way of retrieving the Promo Code List.
		HelperScriptRockettesTMAPIParser myTMAPIParser = new HelperScriptRockettesTMAPIParser();
		if (myTMAPIParser.getPromoCodelist(myPromoCodes).size()!=0)
			{
			return myPromoCodes;
			}
			else
			{
				try (BufferedReader myBufferedReader = new BufferedReader(new FileReader(rGP.myPromoCodeFilePath))) 
					{
					StringBuilder myStringBuilder = new StringBuilder();
					String myPromoCode = myBufferedReader.readLine();
					if (!myPromoCode.isEmpty())
						myPromoCodes.add(myPromoCode);
					while (myPromoCode != null) 
						{
						myStringBuilder.append(myPromoCode);
						myPromoCode = myBufferedReader.readLine();
						if (myPromoCode != null)
							myPromoCodes.add(myPromoCode);
						}
					} 
				catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
				catch (IOException e) 
					{
						e.printStackTrace();
					}
				System.out.println("Total Promo Codes are => "+myPromoCodes.size());
				return myPromoCodes;
			}
	}
	
	@Test(description="Promo Code Workflow => Apply/Remove for all promo codes", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public void onClickOfApplyPromoCodeAll() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		{
		try
		{
		List<String> myPromoCodes = new ArrayList<String>();
		List<String> myValidPromoCodes = new ArrayList<String>();
		List<String> myInvalidPromoCodes = new ArrayList<String>();

		myPromoCodes = getPromoCodeList(myPromoCodes);

		if (myPromoCodes.isEmpty()) {
			myPromoCodes.add("mcdc");
			myPromoCodes.add("chase");
			myPromoCodes.add("web1nov");	
			myPromoCodes.add("Chsemp");
			myPromoCodes.add("wrong");
			myPromoCodes.add("carlton");
		}

		// Promo Code Workflow:
		for (int count = 0; count < myPromoCodes.size(); count++) 
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);

			// 1. One time only CLick on "Apply Promo Code" Text
			if (count == 0) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary.btn-md.apply-promo")));
				WebElement myPurchasingFilter = driver.findElement(By.cssSelector(".btn.btn-primary.btn-md.apply-promo"));
				myPurchasingFilter.click();
				}

			// 2. Type the Coupon Code in Textbox
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".promocode-text")));
			WebElement myPromoCode = driver.findElement(By.cssSelector(".promocode-text"));
			myPromoCode.sendKeys(myPromoCodes.get(count));

			// 3. Click on "Apply Coupon" Button
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
			WebElement myPromoCodeApplyButton = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
			myPromoCodeApplyButton.click();

			// 4. Check if Promo code has been applied or resulted in an error.
			try {
				WebElement myPromoCodeAppliedStateOK = driver.findElement(By.cssSelector(".promo-code-container"));
				if (myPromoCodeAppliedStateOK.isDisplayed()) 
					{
					System.out.println(this.myDriverParameters+" - "+myPromoCodes.get(count) + " Coupon Code is applied");
					myValidPromoCodes.add(myPromoCodes.get(count));
					// See after Applying promo code correct show IDs are getting in calendar.
					onLoadGetAllValidDaysAndShowsfromCalendar();
					}
			} 
			catch (NoSuchElementException e) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
				WebElement myPurchasingFilterRemovePromoCode = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
				myPurchasingFilterRemovePromoCode.click();
				continue;
				}
			
			try {
				WebElement myPromoCodeAppliedStateNotOK = driver.findElement(By.cssSelector(".popover-content"));
				if (myPromoCodeAppliedStateNotOK.isDisplayed()) 
					{
					System.out.println(this.myDriverParameters+" - "+myPromoCodes.get(count) + " Coupon Code is invalid");
					myInvalidPromoCodes.add(myPromoCodes.get(count));
					}
				} 
			catch (NoSuchElementException e) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
				WebElement myPurchasingFilterRemovePromoCode = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
				myPurchasingFilterRemovePromoCode.click();
				continue;
				}
		}

		System.out.println("Below are the "+myValidPromoCodes.size()+ " Valid coupons: ");
		// Print Valid Coupons
		for (int count = 0; count < myValidPromoCodes.size(); count++)
			System.out.println(myValidPromoCodes.get(count));

		System.out.println(this.myDriverParameters+" - Below are the "+myInvalidPromoCodes.size()+ " Invalid coupons: ");
		// Print Invalid Coupons
		for (int count = 0; count < myInvalidPromoCodes.size(); count++)
			System.out.println(myInvalidPromoCodes.get(count));
		} catch (WebDriverException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	}
	
	@Test(description="DeepLinking URLs Verification", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public void verifyDeeplinkingURLs(String myCalendarProductionURL, WebDriver driver) throws IOException, ParseException
	{
		try
		{
		List<String> myValidURLs=new ArrayList<String>();
		List<String> myInvalidURLs=new ArrayList<String>();		
		List<String> myShows = new ArrayList<String>();
		String  myURLDeeplinkingTagsWithValue = new String();	
		
		// ---- Single Tag Deeplinking Verification ---
		
		// 1. Verify for Flow = individual	
		// 1.1 Generate Single Tag Deeplinking URL
		myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"flow=individual";	
		int myResponseCode = 0;
		try {
			myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			}
		// 1.2 Verify Single Tag Deeplinking URL		
		if ( myResponseCode == 404)
				myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
		else
				myValidURLs.add(myURLDeeplinkingTagsWithValue);	
		
		// 2. Verify for Date - 		
		// 2.1 Get all Available (enabled) dates from calendar
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end")));
		List<WebElement> myCalendarDate = driver.findElements(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end"));
		
		System.out.println(this.myDriverParameters+" - Number of days with valid price range in Calendar Cells (Without Promo): " + myCalendarDate.size());
		
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{
				// 2.2 Generate Single Tag Deeplinking URL		
				myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey");
				
				// 2.3 Verify Single Tag Deeplinking URL
						try {
							myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
							}
						catch (IOException e) {
							// TODO Auto-generated catch block
							}
						// 1.2 Verify Single Tag Deeplinking URL		
						if ( myResponseCode == 404)
							myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
						else
							myValidURLs.add(myURLDeeplinkingTagsWithValue);	
					}
			}		
		
		// 3. Verify for Promo Codes
		
		// 3.1 Get all Available Promo Codes List
		
		List<String> myPromoCodes = new ArrayList<String>();

		myPromoCodes = getPromoCodeList(myPromoCodes);

		if (myPromoCodes.isEmpty()) {
			myPromoCodes.add("mcdc");
			myPromoCodes.add("chase");
			myPromoCodes.add("web1nov");	
			myPromoCodes.add("Chsemp");
			myPromoCodes.add("wrong");
			myPromoCodes.add("carlton");
		}
		
		for (int count = 0; count < myPromoCodes.size(); count++) 
		{
			// 3.2 Generate Single Tag Deeplinking URL		
			myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"promo_code="+myPromoCodes.get(count).toString();
			
			// 3.3 Verify Single Tag Deeplinking URL
					try {
						myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
						}
					catch (IOException e) {
						// TODO Auto-generated catch block
						}	
					if ( myResponseCode == 404)
						myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
					else
						myValidURLs.add(myURLDeeplinkingTagsWithValue);	
		}		
		
		// 4. Fetch All Valid SHow IDs
		
		myShows = getListOfValidShows(driver);
		
		// ---- Double Tag Deeplinking Verification ---		
		
		// 1. Verify for Flow = individual& any date	
		
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{
				
				// 1.1 Generate Double Tag Deeplinking URL	
				myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"flow=individual"+"&"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey");
				
				// 1.2 Verify Double Tag Deeplinking URL
						try {
							myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
							}
						catch (IOException e) {
							// TODO Auto-generated catch block
							}
						// 1.2 Verify Single Tag Deeplinking URL		
						if ( myResponseCode == 404)
							myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
						else
							myValidURLs.add(myURLDeeplinkingTagsWithValue);	
					}
			}		
		
		// 2. Verify for Flow = individual& any Promo Code
		
		// 2.1 Get all Available Promo Codes List
		for (int count = 0; count < myPromoCodes.size(); count++) 
		{
			// 2.2 Generate Double Tag Deeplinking URL			
			myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"flow=individual"+"&"+"promo_code="+myPromoCodes.get(count).toString();
			
			// 2.3 Verify Double Tag Deeplinking URL
					try {
						myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
						}
					catch (IOException e) {
						// TODO Auto-generated catch block
						}	
					if ( myResponseCode == 404)
						myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
					else
						myValidURLs.add(myURLDeeplinkingTagsWithValue);	
		}	
		
		// 3. Verify for any date & any promo code
		
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{				
				for (int count = 0; count < myPromoCodes.size(); count++) 
						{
						// 2.2 Generate Double Tag Deeplinking URL			
						myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey")+"&"+"promo_code="+myPromoCodes.get(count).toString();					
						// 2.3 Verify Double Tag Deeplinking URL
							try {
								myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
								}
							catch (IOException e) {
								// TODO Auto-generated catch block
								}	
							if ( myResponseCode == 404)
								myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
							else
								myValidURLs.add(myURLDeeplinkingTagsWithValue);	
						}

					}
			}		

		// 4. Verify for any date & any show ID
		
		for (int i = 0; i < myCalendarDate.size(); i++) 
		{
		if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
				{				
				for (int count=0;count<myShows.size();count++)
					{
					// 2.2 Generate Double Tag Deeplinking URL			
					myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey")+"&"+"show_id="+myShows.get(count).toString();					
					// 2.3 Verify Double Tag Deeplinking URL
						try {
							myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
							}
						catch (IOException e) {
							// TODO Auto-generated catch block
							}	
						if ( myResponseCode == 404)
							myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
						else
							myValidURLs.add(myURLDeeplinkingTagsWithValue);	
					}

				}
		}		
		
		// ---- Triple Tag Deeplinking Verification ---		
		
		// 1. Verify for Flow = individual & any date & any promo code	
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{				
				for (int count = 0; count < myPromoCodes.size(); count++) 
						{
						// 2.2 Generate Triple Tag Deeplinking URL			
						myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"flow=individual"+"&"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey")+"&"+"promo_code="+myPromoCodes.get(count).toString();					
						// 2.3 Verify Triple Tag Deeplinking URL
							try {
								myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
								}
							catch (IOException e) {
								// TODO Auto-generated catch block
								}	
							if ( myResponseCode == 404)
								myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
							else
								myValidURLs.add(myURLDeeplinkingTagsWithValue);	
						}

					}
			}	
		
		// ---- Quadruple Tag Deeplinking Verification ---		
		
		// 1. Verify for Flow = individual & any date & any promo code & any show ID
		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{				
				for (int count = 0; count < myPromoCodes.size(); count++) 
						{
					for (int j=0;j<myShows.size();j++)
							{
						// 2.2 Generate Triple Tag Deeplinking URL			
						myURLDeeplinkingTagsWithValue = myCalendarProductionURL+"?"+"flow=individual"+"&"+"date="+myCalendarDate.get(i).getAttribute("data-event-datekey")+"&"+"promo_code="+myPromoCodes.get(count).toString()+"&"+"show_id="+myShows.get(count).toString();					
						// 2.3 Verify Triple Tag Deeplinking URL
							try {
								myResponseCode = getResponseCode(myURLDeeplinkingTagsWithValue);
								}
							catch (IOException e) {
								// TODO Auto-generated catch block
								}	
							if ( myResponseCode == 404)
								myInvalidURLs.add(myURLDeeplinkingTagsWithValue);
							else
								myValidURLs.add(myURLDeeplinkingTagsWithValue);	
							}
						}

					}
			}
		
		System.out.println(this.myDriverParameters+" - Total valid Deeplinking URLs are: "+myValidURLs.size());
		System.out.println(this.myDriverParameters+" - Total Invalid Deeplinking URLs are: "+myInvalidURLs.size());
		} catch (WebDriverException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Test(description="DeepLinking URLs Verification - Fetch Show IDs", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public static List<String> getListOfValidShows(WebDriver driver)
	{
		List<String> myShowTimesList = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end")));
		List<WebElement> myCalendarDate = driver.findElements(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end"));

		for (int i = 0; i < myCalendarDate.size(); i++) 
			{
			if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
					{
					if (myCalendarDate.get(i).getAttribute("data-event-datekey").length() > 0)
						{
						// System.out.println(myCalendarDate.get(i).getAttribute("data-event-datekey") + "  "+ myCalendarDatePriceRange.get(i).getText());
							myCalendarDate.get(i).click();
					    // First try to find out the element in existing screen state
							try
								{
									wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='show-times']")));	
								}
							catch (TimeoutException e)
								{
									continue;
								}
				
					// If element is not found then scroll up as you might have alrady scrolled down.
					((JavascriptExecutor) driver).executeScript("scroll(0, -600);");
				
					// Now you shall have the element of desired date
					List<WebElement> myShowTimeWindow= driver.findElements(By.cssSelector("[class='show-times']"));
				
					if (myShowTimeWindow.get(0).isDisplayed()) 
						{	
						List<WebElement> myShowTimes = driver.findElements(By.cssSelector(".available-time>div>label"));
						List<WebElement> myShowTimesDisabled = driver.findElements(By.cssSelector(".available-time.disabled>div>label"));
						List<WebElement> myShowTimesURL = driver.findElements(By.cssSelector(".available-time>div>input"));
						
						myShowTimesURL.removeAll(myShowTimesDisabled);
						myShowTimes.removeAll(myShowTimesDisabled);
						
						for(int count=0;count<myShowTimes.size();count++)
							{							
								String[] myShowText = myShowTimes.get(count).getAttribute("for").toString().split("-");
								myShowTimesList.add(myShowText[1]);
							}
						}
						}	
					}
			}
		return myShowTimesList;
		}

	@Test(description="DeepLinking URLs Verification - Get Response Code for URL", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public static int getResponseCode(String urlTobeVerified) throws MalformedURLException, IOException
	{
		  URL myURL = new URL(urlTobeVerified); 
		  HttpURLConnection myHuc =  (HttpURLConnection)  myURL.openConnection(); 
		  myHuc.setRequestMethod("GET"); 
		  myHuc.connect(); 
		  return myHuc.getResponseCode();
	}

	@Test(description="TM Navigation", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public static void navigateToTM(URL url)
	{	

	}
	
	@Test(description="TM Show Date/Time/Price Match", groups = { "cs", "rockettescsticketspage", "fullintegration" })
	public static boolean isPriceRangeAndShowTimeMatchedWithTM()
	{
		boolean myFlag = false;
		
		// TM Navigation for URL with SHow event ID
		
		// Verify Date & Time
		
		// Verify Price Range (Min,Max)
		
		return myFlag;
	}
	
	@Test(description="TM Navigation", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static void navigateToTM(WebDriver driver, String URL) throws InterruptedException
	{	
		driver.navigate().to(URL);
		Thread.sleep(1000);
	}
	
	@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescsticketspage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Tickets Page: Test Case-8: Is Partner Section displayed?");
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Tickets Page: Test Case-10. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}