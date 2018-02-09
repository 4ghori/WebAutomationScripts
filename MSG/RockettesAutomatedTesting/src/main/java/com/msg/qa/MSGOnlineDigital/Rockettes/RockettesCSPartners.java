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

public class RockettesCSPartners
{	
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSPartners(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Christmas Spectacular Partners Page?", groups = { "rockettescspartnerspage", "smoke", "fullintegration" })
	public boolean onRockettesCSPartnersPage()
	{
	try
	{
		// Verify the Title of the page.
	System.out.println("******************* Rockettes Christmas Spectacular Partners Page URL to be tested is: "+this.URL+" ******************* ");
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
	
	/** 1. Verify that you are on Christmas Partners Page.
	 * a. Read current Partners Title and style
	 */
	System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Test Case-1: Are we on Christmas Spectacular Partners Page?");
	WebDriverWait wait = new WebDriverWait(driver, 30);
	WebElement myHelpfulTips = null;
	
	String[] mySelector = this.selectors.get("btnCSPartnersTitle");
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
	
	if (myHelpfulTips.getText().toString().equalsIgnoreCase("OUR PARTNERS"))
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: We are on Rockettes -> Christmas -> Partners Page.");	
		isMyTestPassed = true;
	}	
	else
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Error: We are not on Rockettes -> Christmas -> Partners Page!");
		isMyTestPassed = false;
	}
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(this.myDriverParameters+e.getMessage());
	}
	return isMyTestPassed;
}	
	
	@Test(description="Is Partners Present?", groups = { "cs", "rockettescspartnerspage", "smoke", "fullintegration" })
	public boolean isAllPartnersPresent() throws Exception
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Test Case-1: Is Partners Present?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myPresentingPartner = null;
		
		String[] mySelector = this.selectors.get("btnCSPartnersPresentParent");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myPresentingPartner = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myPresentingPartner = driver.findElement(By.xpath(mySelector[0]));
		}	

		List<WebElement> myPartners= null;
		String[] mySelector1 = this.selectors.get("btnCSPartnersPresentChild");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myPartners = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myPartners = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		if (myPartners.size() > 0 && myPresentingPartner.isDisplayed())
			{
			if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myPresentingPartner.getAttribute("src").toString())!=404)
				{	
				System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Presenting Partner: "+myPresentingPartner.getAttribute("src").toString()+" (valid link)");
				}
			else
				{
				System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Presenting Partner: "+myPresentingPartner.getAttribute("src").toString()+" (invalid link)");
				}
				int count = 1;
				for(WebElement myEle:myPartners)
					{
					if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myEle.getAttribute("src").toString())!=404)
					{
						System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Partner"+count+": "+myEle.getAttribute("src").toString()+" (valid link)");
					}
					else
					{
						System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Partner"+count+": "+myEle.getAttribute("src").toString()+" (invalid link)");						
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
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Partners Page: Test Case-2. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}
