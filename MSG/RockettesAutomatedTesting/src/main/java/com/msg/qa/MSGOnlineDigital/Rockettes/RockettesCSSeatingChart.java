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

public class RockettesCSSeatingChart
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSSeatingChart(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Christmas Spectacular Seatmap Page?", groups = { "cs", "rockettescssignuppage", "smoke", "fullintegration" })
	public boolean onRockettesCSSeatingChartPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println(this.myDriverParameters+" - Our Test URL is: "+this.URL);
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
		
		/** 1. Verify that you are on Christmas Seatmap Page.
		 * a. Read current Title and style
		 */
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnCSSeatingTitle");
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
		
		if (myHelpfulTips.getText().toString().trim().equalsIgnoreCase("EXPLORE SEATS"))
		{
			System.out.println(this.myDriverParameters+" - We are on Rockettes -> Christmas -> Seatmap Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Error: We are not on Rockettes -> Christmas -> Seatmap Page!");	
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}
	
	@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescssignuppage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS Seatmap: Test Case-2: Is Partner Section displayed?");		
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Seatmap Page: Test Case-3. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}