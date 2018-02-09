package com.msg.qa.MSGOnlineDigital.Rockettes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.commons.lang3.time.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RockettesCSCurrentOffers
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSCurrentOffers(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
@Test(description="Are we on Christmas Spectacular Current Offers Page and title is H1?", groups = { "cs", "rockettescsofferspage", "smoke", "fullintegration" })
public boolean onRockettesCSCurrentOffersPage()	
{
	try
	{
	// Verify the Title of the page.
	System.out.println("******************* Rockettes Christmas Spectacular Current Offers Page URL to be tested is: "+this.URL+" ******************* ");
	if (this.URL.contains("amp"))
		{
		//String myStagingURL = this.URL.replace("staging", "msgtransition:Msg123@staging");
		//driver.navigate().to(myStagingURL);
		driver.navigate().to(this.URL);
		isMyTestPassed = true;
		}
	else
		{
		driver.navigate().to(this.URL);
		//driver.manage().window().maximize();
	
	/** 1. Verify that you are on Christmas offers Page.
	 * a. Read current offer Title and style
	 */
	System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Test Case-1: Are we on Christmas Spectacular Current Offers Page?");
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	WebElement myHelpfulTips = null;
	
	String[] mySelector = this.selectors.get("btnCSCurrentOffersTitle");
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
	
	if (myHelpfulTips.getText().toString().equalsIgnoreCase("CURRENT OFFERS"))
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: We are on Christmas -> Current Offers Page!");	
		isMyTestPassed = true;
	}	
	else
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Error: We are not on Christmas -> Current Offers Page!");	
		isMyTestPassed = false;
	}
	}
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(this.myDriverParameters+e.getMessage());
	}
	return isMyTestPassed;
}

@Test(description="Is ShortCode 3 Block Present?", groups = { "cs", "rockettescsofferspage", "smoke", "fullintegration" })
public boolean isshotCodeThreeBlockPresent() throws Exception
{
	System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Test Case-2: Is ShortCode 3 Block Present?");

	try
	{
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	List<WebElement> myPromotionsLinks = null;
	
	String[] mySelector = this.selectors.get("btnCSCurrentOffersShortcode3Blocks");
	if (mySelector[1].equals("CSS"))
	{
	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
	myPromotionsLinks = driver.findElements(By.cssSelector(mySelector[0]));
	}
	if (mySelector[1].equals("Xpath"))
	{
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
	myPromotionsLinks = driver.findElements(By.xpath(mySelector[0]));
	}	
		
	if (myPromotionsLinks.size()>0)
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: We have total "+ myPromotionsLinks.size() + " Links on the Christmas - Current Offers Page!");
		int count = 1;
		for(WebElement myEle:myPromotionsLinks)
		{
			/*
			if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myEle.getAttribute("href").toString())!=404)
			{
			System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Link"+count+": "+myEle.getAttribute("href").toString()+" (valid link)");
			}
			{
			System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Link"+count+": "+myEle.getAttribute("href").toString()+" (invalid link)");
			}
			*/
			
			System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Link"+count+": "+myEle.getAttribute("href").toString());
			count++;
		}
	}
	isMyTestPassed = true;
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(e.getMessage());
	}
	return isMyTestPassed;
}

@Test(description="Is Related Contents Section displayed?", groups = { "cs", "rockettescsofferspage", "smoke", "fullintegration" })
public boolean isRelatedContentSectionPresent()
{
	System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Test Case-3: Is Related Contents Section displayed?");
	return isMyTestPassed = RockettesReusableFunctionalities.isRelatedContentSectionPresent(driver);
}

@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescsofferspage", "smoke", "fullintegration" })
public boolean isPartnersPresent()
{
	System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Test Case-4: Is Partner Section displayed?");	
	return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
}

public boolean executeVisualTest()
{
	System.out.println(this.myDriverParameters+" - Rockettes CS Current Offers Page: Test Case-5. Executing Visual Test.");
	return RockettesReusableFunctionalities.executeVisualTest(driver);
}

}