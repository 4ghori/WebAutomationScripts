package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RockettesNYSGroups
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesNYSGroups(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes NYSS Groups Page?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean onRockettesNYSSGroupsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes NewYork Spectacular Groups Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes NYSS Groups Page.
		 * a. Read NYSS Groups Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-1: Are we on Rockettes NYSS Groups Page?");		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnNYSSGroupsTitle");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myHelpfulTips = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myHelpfulTips = driver.findElement(By.xpath(mySelector[0]));
		}		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("GROUPS") || myHelpfulTips.getText().toString().equalsIgnoreCase("GROUPS INQUIRY"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: We are on Rockettes -> Groups Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Error: We are not on Rockettes -> Groups Page!");	
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Do we have image for Date and Time?", groups = { "nyss", "rockettesnysgroupspage", "smoke", "fullintegration" })
	public boolean isDateAndTimeImageDisplayed() throws Exception
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-2: Do we have image for Date and Time?");		
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> myDateAndTime= null;
		String[] mySelector1 = this.selectors.get("btnNYSSGroupsDateandImage");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myDateAndTime = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myDateAndTime = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		if (myDateAndTime.size()>0)
			{
			if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myDateAndTime.get(0).getAttribute("src").toString())!=404)
				{
					System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Date/Time for NYSS Shows can be found at: "+myDateAndTime.get(0).getAttribute("src").toString()+" (valid link).");
					isMyTestPassed = true;
					return isMyTestPassed;
				}
			else
				{
				System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Date/Time for NYSS Shows is missing at: "+myDateAndTime.get(0).getAttribute("src").toString()+" (invalid link).");
				isMyTestPassed = true;
				return isMyTestPassed;
				}
			}
		else
			{
			isMyTestPassed = false;
			return isMyTestPassed;
			}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}	
	
	@Test(description="Do we have pricing image displayed?", groups = { "nyss", "rockettesnysgroupspage", "smoke", "fullintegration" })
	public boolean isPricingImageDisplayed() throws Exception
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-3: Do we have pricing image displayed?");		
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> myDateAndTime= null;
		String[] mySelector1 = this.selectors.get("btnNYSSGroupsPricingImage");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myDateAndTime = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myDateAndTime = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		if (myDateAndTime.size()>0)
			{
			if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myDateAndTime.get(1).getAttribute("src").toString()) !=404)
			{
				System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Price matrix for NYSS Shows can be found at: "+myDateAndTime.get(1).getAttribute("src").toString()+" (valid link).");
				isMyTestPassed = true;
				return isMyTestPassed;	
			}
			else
			{
				System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Price matrix for NYSS Shows is not present at: "+myDateAndTime.get(1).getAttribute("src").toString()+" (invalid link).");
				isMyTestPassed = false;
				return isMyTestPassed;
			}
			
			}
		else
			{
			isMyTestPassed = false;
			return isMyTestPassed;
			}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}	
	
	@Test(description="Do we have working social media links?", groups = { "nyss", "rockettesnysgroupspage", "smoke", "fullintegration" })
	public boolean isSocialMediaLinkWorking() throws Exception
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-4: Do we have working social media links?");				
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> mySocialLinks= null;
		String[] mySelector1 = this.selectors.get("btnNYSSGroupsSocialMediaLinks");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		mySocialLinks = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		mySocialLinks = driver.findElements(By.xpath(mySelector1[0]));
		}

		if (mySocialLinks.size()>0)
			{
			int count = 1;
			for(WebElement myElem:mySocialLinks)
			{
				if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myElem.getAttribute("href").toString()) != 404)
				{
					System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Social Media Link"+count+": "+myElem.getAttribute("href").toString()+" (Valid Link).");
					isMyTestPassed = true;
				}
				
				else
				{
					System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Social Media Link"+count+": "+myElem.getAttribute("href").toString()+" (Invalid Link).");
					isMyTestPassed = false;
					return isMyTestPassed;
				}
				count++;
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
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}
	
	public boolean isMailIDWorkingAndPhoneNumberPresent()
	{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-5: Is Mail ID working and Phone numbers present?");						
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='purchase-little']>a")));
		List<WebElement> myMailID = driver.findElements(By.cssSelector("[class='purchase-little']>a"));

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='purchase-little']>b")));
		WebElement myPhoneNumber = driver.findElement(By.cssSelector("[class='purchase-little']>b"));
		
		if(myMailID.size() > 0 & myPhoneNumber.isDisplayed())
		{		
		// Print Cell Number
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Contact Number for Group Booking is: "+myPhoneNumber.getText().toString());
		
		// Print & Verify Mail ID
		
		if(RockettesReusableFunctionalities.verifyEmailAddress(myMailID.get(0).getText().toString()))
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Mail ID for Group Booking is: "+myMailID.get(0).getText().toString()+" (Valid EMail ID)");
		}
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Mail ID for Group Booking is: "+myMailID.get(0).getText().toString()+" (Invalid EMail ID)");
			isMyTestPassed = false;
			return isMyTestPassed;

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
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Groups Page: Test Case-6. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}