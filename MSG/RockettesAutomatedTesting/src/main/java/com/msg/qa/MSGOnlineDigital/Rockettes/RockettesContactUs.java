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

public class RockettesContactUs
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesContactUs(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Contact Us Page?", groups = { "rockettescontactuspage", "smoke", "fullintegration" })
	public boolean onRockettesContactUsPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Contact Us Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes Blog Page.
		 * a. Read Blog Style and Text.
		 */
		System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Test Case-1: Are we on Rockettes contact Us Page?");			
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnContactUsTitle");
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
		
		System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Contact Us element renederd title: "+myHelpfulTips.getText().toString());
		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("CONTACT US"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: We are on Rockettes -> Contact us Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Error: We are not on Rockettes -> Contact us Page!");	
			isMyTestPassed = false;
		}
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Error: We are not on Rockettes -> Contact us Page!");
	}
		return isMyTestPassed;
	}
	
	@Test(description="Is Mail ID working?", groups = { "rockettescontactuspage", "smoke", "fullintegration" })
	public boolean isMailIDWorkingAndPhoneNumberPresent()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Test Case-2: Is Mail ID working?");			
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> myMailID = null;
		
		String[] mySelector = this.selectors.get("btnContactUsMail");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myMailID = driver.findElements(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myMailID = driver.findElements(By.xpath(mySelector[0]));
		}
		
		if(myMailID.size() > 0 )
		{				
		// Print & Verify Mail ID
		
		for (int count = 0; count<myMailID.size(); count++)
		{
		if(RockettesReusableFunctionalities.verifyEmailAddress(myMailID.get(count).getText().toString()))
		{
			System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Mail ID"+count+": "+myMailID.get(count).getText().toString()+" (Valid EMail ID)");
		}
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: Mail ID"+count+": "+myMailID.get(count).getText().toString()+" (Invalid EMail ID)");
			isMyTestPassed = false;
			return isMyTestPassed;

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
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes Contact Us Page: EMail ID is missing!");
		}
		return isMyTestPassed;
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Contact us Page: Test Case-3. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
	
}