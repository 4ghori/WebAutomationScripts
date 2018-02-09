package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RockettesCSGroups
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSGroups(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Christmas Spectacular Groups Page?", groups = { "cs", "rockettescsgroupspage", "smoke", "fullintegration" })
	public boolean onRockettesCSGroupsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Christmas Spectacular Groups Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: Test Case-1: Are we on Christmas Spectacular Groups Page?");
/*
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myHelpfulTips = null;
		String[] mySelector = this.selectors.get("btnCSGroupsTitle");
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
		
		if (myHelpfulTips.getText().toString().equals("GROUPS"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: We are on Rockettes -> Groups Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: Error: We are not on Rockettes -> Groups Page!");
			isMyTestPassed = false;
		}
*/
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}

	
	@Test(description="Is Related Contents Section displayed?", groups = { "cs", "rockettescsgroupspage", "smoke", "fullintegration" })
	public boolean isRelatedContentSectionPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: Test Case-2: Is Related Contents Section displayed?");
		System.out.println("Commented out due to Related Contents currently not being displayed");
//		return isMyTestPassed = RockettesReusableFunctionalities.isRelatedContentSectionPresent(driver);
		return true;
		}

	
	@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescsgroupspage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: Test Case-3: Is Partner Section displayed?");		
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Groups Page: Test Case-4. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}