package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class RockettesNYSTickets 
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesNYSTickets(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	/* 1. Get the URL for Christmas Calendar */
	@Test(description="Are we on Rockettes NYSS Calendar Page?", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onRockettesNYSTicketsPage() throws InterruptedException
	{
	try
	{
		// Verify the Title of the page.
	System.out.println("Our Test URL is: "+this.URL);
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
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(this.myDriverParameters+e.getMessage());;
	}
	return isMyTestPassed;
	}

	/* 2. Display Calendar - 1-19 Page
	 * a. Display default 1-19 page of calendar
	 * b. Get Calendar Load Time
	*/
	@Test(description="Display 1 to 19 Calendar Page", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean getCalenderDisplay1to19Page() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class^=msg-calendar]")));
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());;
		}
		return isMyTestPassed;
	}

	 /*3. Get Base Checks done on Calendar Page.
	 * a. Is Calendar Displayed?
	 * b. Are Filters Present?
	 * c. Is Helpful Tips displayed?
	 */
	@Test(description="Initial Check on Calendar", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onLoadGetBaseCheckDoneOnCalender() 
	{
		try
		{
		// 1. Is Purchasing filter bar Loaded?
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class=calendar-filter]")));
		List<WebElement> myPurchasingFilter = driver.findElements(By.cssSelector("div[class=calendar-filter]"));
		for (int count = 0; count < myPurchasingFilter.size(); count++) {
			if (myPurchasingFilter.get(count).getLocation().getX() != 0)
				System.out.println("Purchasing Filter is present.");
		}

		// 2. Is Calendar displayed for at least one Month?
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".msg-calendar")));
		List<WebElement> myCalendarMonths = driver.findElements(By.cssSelector(".msg-calendar"));
		if (myCalendarMonths.size()>0)
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-center.calendar-month-heading>h2")));
			List<WebElement> myCalendarMonthsTitle = driver.findElements(By.cssSelector(".fc-center.calendar-month-heading>h2"));
			for (int count =0;count<myCalendarMonthsTitle.size();count++)
				{
				System.out.println(myCalendarMonthsTitle.get(count).getText()+" Month is present in Calendar.");
				}
		}
		
		// 3. Is Helpful Tips displayed?
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sidebar-container")));
		WebElement myHelpfulTips = driver.findElement(By.cssSelector(".sidebar-container"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=helpful-tips]>[class=heading]")));
		WebElement myHelpfulTipsHeader = driver.findElement(By.cssSelector("[class=helpful-tips]>[class=heading]"));
		if (myHelpfulTips.isDisplayed() & myHelpfulTipsHeader.isDisplayed())
			System.out.println(this.myDriverParameters+" - Helpful Tips are present.");
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());;
		}
		return isMyTestPassed;
	 }

	 /* 4. Verify Calendar Cell Pricing Range. 
	 * a. Get the Price Range of Purchasing Filter 
	 * i. Get Minimum value 
	 * ii. Get Maximum value
	 */		
	@Test(description="Price Range Filter => Get Min/Max Price", groups = { "smoke", "fullintegration" })
	public boolean onLoadGetPurchasingFilterPriceRange() 
	{
		List<String> myPriceRange = new ArrayList<String>();
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#msg-calendar-filter-min")));
		WebElement myPurchasingFilterMin = driver.findElement(By.cssSelector("#msg-calendar-filter-min"));
		myPriceRange.add(myPurchasingFilterMin.getText());

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#msg-calendar-filter-max")));
		WebElement myPurchasingFilterMax = driver.findElement(By.cssSelector("#msg-calendar-filter-max"));
		myPriceRange.add(myPurchasingFilterMax.getText());

		System.out.println("Minimum Pricse in Slider is => " + myPriceRange.get(0).toString());
		System.out.println("Maximum Price in Slider is => " + myPriceRange.get(1).toString());
		Thread.sleep(1000);
		isMyTestPassed = true;
		} catch (WebDriverException | InterruptedException e) {
			// e.printStackTrace();s
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
		}	

	 /*5. Purchasing Filter: 1-19 / 20+ Options 
	 * a. Click on Purchasing Filter has to display "2" options. 
	 * b. Click on 1-19 option shall display 1-19 Page
	 * c. Click on 20+ option shall display 20+ Page.
	 */		
	@Test(description="Verify Navigation sequence: 1-19 -> 20 -> 1-19 back Calendar Page", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onClickOfPurchasingFilter() {
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
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	 /*6. Select of 1st available day show time and navigate to TM and verify the date/time 
	 * a. Select 1st available date in the calendar.
	 * b. Select the show from that date
	 * c. Click on Buy
	 */		
	@Test(description="Verify date/Time on TM for a selected Show", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onClickOfShowDateAndTimeVerifyTMFlow() {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end")));
			List<WebElement> myCalendarDate = driver.findElements(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end"));
			
			System.out.println("Number of valid days in Calendar (Without Promo): " + myCalendarDate.size());
			
			for (int i = 0; i < myCalendarDate.size(); i++) 
				{
				if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
						{
						if (myCalendarDate.get(i).getAttribute("data-event-datekey").length() > 0)
							{
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
					
								// Now you shall have the element of desired date
								List<WebElement> myShowTimeWindow= driver.findElements(By.cssSelector("[class='show-times']"));
					
								if (myShowTimeWindow.get(0).isDisplayed()) 
										{	
										List<WebElement> myShowTimes = driver.findElements(By.cssSelector(".available-time>div>label"));
										List<WebElement> myShowTimesDisabled = driver.findElements(By.cssSelector(".available-time.disabled>div>label"));
										List<WebElement> myShowTimesURL = driver.findElements(By.cssSelector(".available-time>div>input"));
							
										myShowTimesURL.removeAll(myShowTimesDisabled);
										myShowTimes.removeAll(myShowTimesDisabled);
							
										if(myShowTimes.size() > 0) 
											{
											System.out.println("Date "+myCalendarDate.get(i).getAttribute("data-event-datekey")+" is selectable and Show times are present.");
										for(int count=0;count<myShowTimes.size();count++)
											{
											System.out.println(myShowTimes.get(count).getAttribute("for").toString()+" at Time "+myShowTimes.get(count).getText());
											System.out.println("TM URL is: "+myShowTimesURL.get(count).getAttribute("URL").toString());
											String myDate = myCalendarDate.get(i).getAttribute("data-event-datekey");
											String myShowID = myShowTimes.get(count).getAttribute("for").toString();
											
											driver.navigate().to(URL+"?date="+myDate+"&show_id="+myShowID.substring(5,myShowID.length()));
											}
											}
										}
								}	
						}
				}
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	
	 /*6. Promo code Workflow  and navigation to TM 
	 * a. Apply Promo code mcdc
	 * b. Select a Show
	 * c. Navigate to TM
	 * d. Come back to Rockettes Calendar
	 */
	
	@Test(description="Single Promo Code Workflow", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onApplyPromoCodeSingle() throws InterruptedException 
	{
		try
		{

		// Promo Code Workflow:
			WebDriverWait wait = new WebDriverWait(driver, 120);

			// 1. One time only CLick on "Apply Promo Code" Text
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary.btn-md.apply-promo")));
				WebElement myPurchasingFilter = driver.findElement(By.cssSelector(".btn.btn-primary.btn-md.apply-promo"));
				myPurchasingFilter.click();

			// 2. Type the Coupon Code in Textbox
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".promocode-text")));
			WebElement myPromoCode = driver.findElement(By.cssSelector(".promocode-text"));
			myPromoCode.sendKeys("chase");

			// 3. Click on "Apply Coupon" Button
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
			WebElement myPromoCodeApplyButton = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
			myPromoCodeApplyButton.click();

			// 4. Check if Promo code has been applied or resulted in an error.
			try {
				WebElement myPromoCodeAppliedStateOK = driver.findElement(By.cssSelector(".promo-code-container"));
				if (myPromoCodeAppliedStateOK.isDisplayed()) 
					{
					System.out.println("chase Coupon Code is applied");
					// See after Applying promo code correct show IDs are getting in calendar.
					wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end")));
					List<WebElement> myCalendarDate = driver.findElements(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end"));
					if (myCalendarDate.get(0).getAttribute("data-event-datekey").length() > 0)
									{
										myCalendarDate.get(0).click();
								    // First try to find out the element in existing screen state
										try
											{
												wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='show-times']")));	
											}
										catch (TimeoutException e)
											{
											isMyTestPassed = false;
											System.out.println(this.myDriverParameters+e.getMessage());
											}
							
										// If element is not found then scroll up as you might have already scrolled down.
										//((JavascriptExecutor) driver).executeScript("scroll(1000,0);");
							
										// Now you shall have the element of desired date
										List<WebElement> myShowTimeWindow= driver.findElements(By.cssSelector("[class='show-times']"));
							
										if (myShowTimeWindow.get(0).isDisplayed()) 
												{	
												List<WebElement> myShowTimes = driver.findElements(By.cssSelector(".available-time>div>label"));
												List<WebElement> myShowTimesDisabled = driver.findElements(By.cssSelector(".available-time.disabled>div>label"));
												List<WebElement> myShowTimesURL = driver.findElements(By.cssSelector(".available-time>div>input"));
									
												myShowTimesURL.removeAll(myShowTimesDisabled);
												myShowTimes.removeAll(myShowTimesDisabled);
									
												if(myShowTimes.size() > 0) 
													{
													String myCalendarDateAndTime = myCalendarDate.get(0).getAttribute("data-event-datekey").toString();
													String myShowTime = myShowTimes.get(0).getText();
													System.out.println("Date "+myCalendarDate.get(0).getAttribute("data-event-datekey")+" is selectable and Show times are present.");
													System.out.println(myShowTimes.get(0).getAttribute("for").toString()+" at Time "+myShowTimes.get(0).getText());
													System.out.println("TM URL we are navigating is: "+myShowTimesURL.get(0).getAttribute("URL").toString());
													myShowTimes.get(0).click();
													List<WebElement> myTMButtons = driver.findElements(By.cssSelector(".buy-tickets.redirect-modalpopup"));
													myTMButtons.get(0).click();
													Thread.sleep(10000);
													String myParentWindow = driver.getWindowHandle();    
												      // Switching from parent window to child window   
													for (String myChildWindow : driver.getWindowHandles())  
												     {  								        	
														// ----------------------------------------- This piece of code is only for Testing Purposes.
														// System.out.println("Windows Handler: "+driver.getWindowHandle().toString()+" Current URL: "+driver.getCurrentUrl());
														// -----------------------------------------
														
													
														Thread.sleep(4000);
														driver.switchTo().window(myChildWindow);
														
														// ----------------------------------------- Below line of code is only for Testing Purposes.
														//driver.get(driver.getCurrentUrl().replace("http", "https"));
														
														if (!driver.getWindowHandle().toString().equals(myParentWindow))
												        	{
															
															
																// Handle TM verification here
												        		System.out.println("TM URL we navigated to: "+driver.getCurrentUrl());
																
												        		//1. Get Date/Time from TM for the navigated Show.
												        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".event-header__event-date")));
																WebElement myTMDateAndTime = driver.findElement(By.cssSelector(".event-header__event-date"));
																System.out.println("My TM Show date and Time are: "+myTMDateAndTime.getText());																
																
																//2. Get Min/Max Price from TM for the naviagted show.
																/*
																Thread.sleep(2000);
																wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".unbutton.rc-slider__label-button")));
																List<WebElement> myTMMinMaxPrice = driver.findElements(By.cssSelector(".unbutton.rc-slider__label-button"));
																System.out.println("My TM Show Min and Max Prices are: "+myTMMinMaxPrice.get(0).getText()+" - "+myTMMinMaxPrice.get(1).getText());																
																// Match price Range with TM
																*/
																
																// Match date and time with TM
																myCalendarDateAndTime = myCalendarDateAndTime.substring(5, myCalendarDateAndTime.length()).replace("-", "/");
																if (myCalendarDateAndTime.charAt(0) == '0')
																		myCalendarDateAndTime = myCalendarDateAndTime.substring(1,myCalendarDateAndTime.length());
																myCalendarDateAndTime = myCalendarDateAndTime.concat(" @ "+myShowTime.substring(0,7));
																if (myCalendarDateAndTime.contains(":00"))
																{
																	myCalendarDateAndTime = myCalendarDateAndTime.substring(0,8).concat(myCalendarDateAndTime.substring(12,myCalendarDateAndTime.length()).toLowerCase());
																}													
																else
																{
																	myCalendarDateAndTime = myCalendarDateAndTime.substring(0,10).concat(myCalendarDateAndTime.substring(12,myCalendarDateAndTime.length()).toLowerCase());	
																}
													
																String myTMShowDateAndTime = myTMDateAndTime.getText().substring(4, myTMDateAndTime.getText().length());
																
																if (!myCalendarDateAndTime.equals(myTMShowDateAndTime))
																{
																	System.out.println("Error: TM Show date/time are not matched with the Rockettes Calendar Show Date/Time!");
																	isMyTestPassed = false;
																}
																else
																{
																	System.out.println("TM Show date/time ("+myTMShowDateAndTime+") are matched with the Rockettes Calendar Show Date/Time ("+myCalendarDateAndTime+")!");

																}
																
												        	}
												     }
												     
													//Switching back to Parent Window  
													Thread.sleep(2000);
													driver.close();
													//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t"); 
													driver.switchTo().window(myParentWindow); 
												     
												}
												}
									}	
					}
				isMyTestPassed = true;
			} 
			catch (NoSuchElementException e) 
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-default.apply-button")));
				WebElement myPurchasingFilterRemovePromoCode = driver.findElement(By.cssSelector(".btn.btn-default.apply-button"));
				myPurchasingFilterRemovePromoCode.click();
				}
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	 /* 7. Get total valid days and shows when calendar is loaded. 
		 * a. Get the total valid days 
		 * b. Get the total valid shows
		 */		
		@Test(description="Get all Valid days and Shows from Calendar", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
		public static boolean onLoadGetAllValidDaysAndShowsfromCalendar(WebDriver driver, String URL, Boolean isMyTestPassed) throws InterruptedException 
		{
			try
			{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end")));
			List<WebElement> myCalendarDate = driver.findElements(By.cssSelector(".fc-day-grid-event.fc-h-event.fc-event.fc-start.fc-end"));
			int myValidDays=0, myValidShows=0;
			
			System.out.println("Number of valid days in Calendar (Without Promo): " + myCalendarDate.size());
			
			for (int i = 0; i < myCalendarDate.size(); i++) 
				{
				if (!myCalendarDate.get(i).getAttribute("style").contains("opacity"))
						{
						if (myCalendarDate.get(i).getAttribute("data-event-datekey").length() > 0)
							{
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
					
								// If element is not found then scroll up as you might have already scrolled down.
								((JavascriptExecutor) driver).executeScript("scroll(1000,0);");
					
								// Now you shall have the element of desired date
								List<WebElement> myShowTimeWindow= driver.findElements(By.cssSelector("[class='show-times']"));
					
								if (myShowTimeWindow.get(0).isDisplayed()) 
										{	
										List<WebElement> myShowTimes = driver.findElements(By.cssSelector(".available-time>div>label"));
										List<WebElement> myShowTimesDisabled = driver.findElements(By.cssSelector(".available-time.disabled>div>label"));
										List<WebElement> myShowTimesURL = driver.findElements(By.cssSelector(".available-time>div>input"));
							
										myShowTimesURL.removeAll(myShowTimesDisabled);
										myShowTimes.removeAll(myShowTimesDisabled);
							
										if(myShowTimes.size() > 0) 
											{
											System.out.println("Date "+myCalendarDate.get(i).getAttribute("data-event-datekey")+" is selectable and Show times are present.");
										for(int count=0;count<myShowTimes.size();count++)
											{
											System.out.println(myShowTimes.get(count).getAttribute("for").toString()+" at Time "+myShowTimes.get(count).getText());
											System.out.println("TM URL is: "+myShowTimesURL.get(count).getAttribute("URL").toString());
											String myDate = myCalendarDate.get(i).getAttribute("data-event-datekey");
											String myShowID = myShowTimes.get(count).getAttribute("for").toString();
											navigateToTM(driver, myShowTimesURL.get(count).getAttribute("URL").toString());
											driver.navigate().to(URL+"?date="+myDate+"&show_id="+myShowID.substring(5,myShowID.length()));
											myValidShows++;
											}
											}
										}
							}	
						myValidDays++;
						}
				}
			if (myValidShows > 0)
			{
			System.out.println("Number of valid days in Calendar (With Promo): " + myValidDays);
			System.out.println("Number of valid shows in Calendar (With Promo): " + myValidShows);
			}
			else
			{
			System.out.println("There are no shows available for the entered coupon code.");
			}
			isMyTestPassed = true;
			} catch (WebDriverException e) {
				// e.printStackTrace();
				isMyTestPassed = false;
				System.out.println(e.getMessage());
			}
			return isMyTestPassed;
		}

	 /*8. Apply Promo Code 
	 * a. Click on "Apply Promo Code" 
	 * b. Enter Promo code value 
	 * c. Click on "Apply Coupon" 
	 * d. If Valid Coupon - Read Text of Coupon 
	 * e. If Invalid Coupon - Read ToolTip Text
	 */
	
	@Test(description="Promo Code Workflow => Apply/Remove for hard coded promo codes", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean onClickOfApplyPromoCodeSelected() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		List<String> myPromoCodes = new ArrayList<String>();
		List<String> myValidPromoCodes = new ArrayList<String>();
		List<String> myInvalidPromoCodes = new ArrayList<String>();

		try
		{
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
			WebDriverWait wait = new WebDriverWait(driver, 60);

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
					System.out.println(myPromoCodes.get(count) + " Coupon Code is applied");
					myValidPromoCodes.add(myPromoCodes.get(count));
					// See after Applying promo code correct show IDs are getting in calendar.
					onLoadGetAllValidDaysAndShowsfromCalendar(driver, URL, isMyTestPassed);
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
					System.out.println(myPromoCodes.get(count) + " Coupon Code is invalid");
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

		System.out.println("Below are the "+myInvalidPromoCodes.size()+ " Invalid coupons: ");
		// Print Invalid Coupons
		for (int count = 0; count < myInvalidPromoCodes.size(); count++)
			System.out.println(myInvalidPromoCodes.get(count));
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}	

	 /*7. Apply Promo Code 
	 * a. Click on "Apply Promo Code" 
	 * b. Enter Promo code value 
	 * c. Click on "Apply Coupon" 
	 * d. If Valid Coupon - Read Text of Coupon 
	 * e. If Invalid Coupon - Read ToolTip Text
	 */
	
	@Test(description="Promo Code Workflow => Get List from event Engine API or Promo Code Dump File", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static List<String> getPromoCodeList(List<String> myPromoCodes, Boolean isMyTestPassed) throws IOException, ParseException 
	{
		try
		{
		// Automated way of retrieving the Promo Code List.
		if (HelperScriptRockettesTMAPIParser.getPromoCodelist(myPromoCodes).size()!=0)
			{
			return myPromoCodes;
			}
			else
			{
// modified this for new excel parsing paradigm
				try (BufferedReader myBufferedReader = new BufferedReader(new FileReader(":"))) 
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
				isMyTestPassed = true;
				return myPromoCodes;
			}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}		
		return myPromoCodes;
	}
	
	 /*9. DeepLinking Functionality
	 * a. Single Tag e.g. flow=individual or promo_code=mcdc
	 * b. Double Tag e.g. date=2016-12-30 and promo_code=mcdc
	 * c. Triple Tag e.g. date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E
	 * d. Quadruple Tag e.g. flow=individual and date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E 
	 */
	
	@Test(description="DeepLinking URLs Verification", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public boolean verifyDeeplinkingURLs(String myCalendarProductionURL, WebDriver driver, boolean isMyTestPassed) throws IOException, ParseException
	{
		List<String> myValidURLs=new ArrayList<String>();
		List<String> myInvalidURLs=new ArrayList<String>();		
		List<String> myShows = new ArrayList<String>();
		String  myURLDeeplinkingTagsWithValue = new String();	
		
		try
		{
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
		
		System.out.println("Number of days with valid price range in Calendar Cells (Without Promo): " + myCalendarDate.size());
		
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

		myPromoCodes = getPromoCodeList(myPromoCodes, isMyTestPassed);

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
		
		myShows = getListOfValidShows(driver, isMyTestPassed);
		
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
		
		System.out.println("Total valid Deeplinking URLs are: "+myValidURLs.size());
		System.out.println("Total Invalid Deeplinking URLs are: "+myInvalidURLs.size());
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}

	 /*8. DeepLinking Functionality
	 * a. Single Tag e.g. flow=individual or promo_code=mcdc
	 * b. Double Tag e.g. date=2016-12-30 and promo_code=mcdc
	 * c. Triple Tag e.g. date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E
	 * d. Quadruple Tag e.g. flow=individual and date=2016-12-30 and promo_code=mcdc and show_id = 3C005072CA40156E 
	 */

	@Test(description="DeepLinking URLs Verification - Fetch Show IDs", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static List<String> getListOfValidShows(WebDriver driver, Boolean isMyTestPassed)
	{
		List<String> myShowTimesList = new ArrayList<String>();
		try
		{
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
		isMyTestPassed = true;
		return myShowTimesList;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
		return myShowTimesList;
	}
	
	
	@Test(description="Promo Code Workflow => Apply/Remove for all promo codes", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static void onClickOfApplyPromoCodeAll(WebDriver driver, String URL, Boolean isMyTestPassed) throws FileNotFoundException, IOException, ParseException, InterruptedException {
		List<String> myPromoCodes = new ArrayList<String>();
		List<String> myValidPromoCodes = new ArrayList<String>();
		List<String> myInvalidPromoCodes = new ArrayList<String>();

		try
		{
		myPromoCodes = getPromoCodeList(myPromoCodes, isMyTestPassed);

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
					System.out.println(myPromoCodes.get(count) + " Coupon Code is applied");
					myValidPromoCodes.add(myPromoCodes.get(count));
					// See after Applying promo code correct show IDs are getting in calendar.
					onLoadGetAllValidDaysAndShowsfromCalendar(driver, URL, isMyTestPassed);
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
					System.out.println(myPromoCodes.get(count) + " Coupon Code is invalid");
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

		System.out.println("Below are the "+myInvalidPromoCodes.size()+ " Invalid coupons: ");
		// Print Invalid Coupons
		for (int count = 0; count < myInvalidPromoCodes.size(); count++)
			System.out.println(myInvalidPromoCodes.get(count));
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
	}	

	@Test(description="DeepLinking URLs Verification - Get Response Code for URL", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static int getResponseCode(String urlTobeVerified) throws MalformedURLException, IOException
	{
		  URL myURL = new URL(urlTobeVerified); 
		  HttpURLConnection myHuc =  (HttpURLConnection)  myURL.openConnection(); 
		  myHuc.setRequestMethod("GET"); 
		  myHuc.connect(); 
		  return myHuc.getResponseCode();
	}

	@Test(description="TM Navigation", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static void navigateToTM(WebDriver driver, String URL) throws InterruptedException
	{	
		driver.navigate().to(URL);
		Thread.sleep(1000);
	}
	
	@Test(description="TM Show Date/Time/Price Match", groups = { "nyss", "rockettesnysticketspage", "fullintegration" })
	public static boolean isPriceRangeAndShowTimeMatchedWithTM()
	{
		boolean myFlag = false;
		
		// TM Navigation for URL with SHow event ID
		
		// Verify Date & Time
		
		// Verify Price Range (Min,Max)
		
		return myFlag;
	}
	
	@Test(description="Is Partner Section displayed?", groups = { "nyss", "rockettesnysticketspage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
	{
		return RockettesReusableFunctionalities.isPartnersPresent(driver);
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Tickets Page: Test Case-12. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}