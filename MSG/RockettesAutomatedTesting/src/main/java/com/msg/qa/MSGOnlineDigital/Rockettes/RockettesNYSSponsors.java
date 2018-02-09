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

public class RockettesNYSSponsors
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesNYSSponsors(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes NYSS Sponsors Page?", groups = { "nyss", "rockettesnysssponsorspage", "smoke", "fullintegration" })
	public boolean onRockettesNYSSponsorsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes NewYork Spectacular Sponsors Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes NYSS Sponsors Page.
		 * a. Read NYSS Sponsors Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: Test Case-1: Are we on New York Spectacular Sponsors Page?");		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnNYSSSponsorsTitle");
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
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("SPONSORS"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: We are on Rockettes -> New York -> Sponsors Page.");
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: Error: We are not on Rockettes -> New York -> Sponsors Page!");		
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Is Sponsors Present?", groups = { "nyss", "rockettesnysssponsorspage", "smoke", "fullintegration" })
	public boolean isSponsorsPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: Test Case-2: Is Sponsors Present?");				
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);	

		List<WebElement> mySponsors= null;
		String[] mySelector1 = this.selectors.get("btnDanceEduSocialLink");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		mySponsors = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		mySponsors = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		if (mySponsors.size()>0)
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: NYSS Sponsors are present in the Page!");
			isMyTestPassed = true;
			return isMyTestPassed;
		}
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes NYSS Sponsors Page: NYSS Sponsors are missing in the Page!");
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
		System.out.println(this.myDriverParameters+" - Rockettes NYS Sponsors Page: Test Case-3. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}
