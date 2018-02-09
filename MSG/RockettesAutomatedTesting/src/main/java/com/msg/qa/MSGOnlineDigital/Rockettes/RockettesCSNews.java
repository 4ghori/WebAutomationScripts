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

public class RockettesCSNews
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSNews(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Christmas Spectacular News Page?", groups = { "cs", "rockettescsnewspage", "smoke", "fullintegration" })
	public boolean onRockettesCSNewsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Christmas Spectacular News Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Christmas offers Page.
		 * a. Read current offer Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes CS News Page: Test Case-1: Are we on Christmas Spectacular News Page?");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnCSNewsTitle");
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
		
		if (myHelpfulTips.getText().toString().trim().equalsIgnoreCase("THE LATEST ON OUR SHOW"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS News Page: We are on Rockettes -> Christmas -> News Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS News Page: Error: We are not on Rockettes -> Christmas -> News Page!");
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Count number of news and verify if the links are working?", groups = { "cs", "rockettescsnewspage", "smoke", "fullintegration" })
	public boolean isNewsPresentAndLinkWorking() throws Exception
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS News Page: Test Case-2: Count number of news and verify if the links are working?");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			List<WebElement> myCSNewsTexts = null;
			String[] mySelector1 = this.selectors.get("btnCSNewsTextParent");
			if (mySelector1[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
			myCSNewsTexts = driver.findElements(By.cssSelector(mySelector1[0]));
			}
			if (mySelector1[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
			myCSNewsTexts = driver.findElements(By.xpath(mySelector1[0]));
			}
			
			List<WebElement> myCSNewsReadMeLinks = null;
			String[] mySelector2 = this.selectors.get("btnCSNewsTextChild");
			if (mySelector2[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
			myCSNewsReadMeLinks = driver.findElements(By.cssSelector(mySelector2[0]));
			}
			if (mySelector2[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
			myCSNewsReadMeLinks = driver.findElements(By.xpath(mySelector2[0]));
			}
		
			if(myCSNewsTexts.size() > 0)
			{
			System.out.println(this.myDriverParameters+" - Rockettes CS News Page: We have total "+myCSNewsTexts.size()+" news.");
			int newsCount = 0, readMoreCount = 0;
			for(WebElement myEle:myCSNewsTexts)
				{
				if (newsCount > 0 && newsCount < myCSNewsTexts.size())
					{
					if(!myEle.getText().toString().isEmpty())
						{
						if(myEle.getText().toString().contains("Read more"))
							{
							if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myCSNewsReadMeLinks.get(readMoreCount).getAttribute("href").toString())!=404)
							{
								System.out.println("Rockettes CS News("+newsCount+") Text is: "+myEle.getText().toString()+" (valid link)");
							}
							else
							{
								System.out.println("Rockettes CS News("+newsCount+") Text is: "+myEle.getText().toString()+" "+myCSNewsReadMeLinks.get(readMoreCount).getAttribute("href").toString()+" (Invalid link)");
							}
							}
						}
					readMoreCount++;	
				}
				newsCount++;
				}
			}
			else
			{
				isMyTestPassed = false;
				return isMyTestPassed;
			}
			
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
	}

	
	@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescsnewspage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS News Page: Test Case-3: Is Partner Section displayed?");
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS News Page: Test Case-4. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}