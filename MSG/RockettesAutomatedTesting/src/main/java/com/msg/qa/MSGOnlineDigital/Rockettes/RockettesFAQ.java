package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RockettesFAQ
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesFAQ(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes FAQ Page?", groups = { "rockettesfaqpage", "smoke", "fullintegration" })
	public boolean onRockettesFAQPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes FAQ Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes Dance Education Page.
		 * a. Read Plan your Day Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Test Case-1: Are we on Rockettes FAQ Page?");			
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnFAQTitle");
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
		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("ROCKETTES FAQ"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: We are on Rockettes -> FAQ Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Error: We are not on Rockettes -> FAQ Page!");	
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
	
	@Test(description="Do we have all accordions working?", groups = { "rockettesfaqpage", "fullintegration" })
	public boolean isAllAccordionWorking()
	{
	System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Test Case-2: Do we have all accordions working?");			
	try
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> myFAQAccordions= null;
		String[] mySelector1 = this.selectors.get("btnFAQAccordionsParent");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myFAQAccordions = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myFAQAccordions = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		List<WebElement> myFAQAccordionsBodyText= null;
		String[] mySelector2 = this.selectors.get("btnFAQAccordionsChild");
		if (mySelector2[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
		myFAQAccordionsBodyText = driver.findElements(By.cssSelector(mySelector2[0]));
		}
		if (mySelector2[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
		myFAQAccordionsBodyText = driver.findElements(By.xpath(mySelector2[0]));
		}
		
		if ( myFAQAccordions.size() > 0 )
		{
			System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Total "+myFAQAccordions.size()+" Accordions are present on Rockettes -> FAQ Page.");
			System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Performing Open/close for all "+myFAQAccordions.size()+" Accordions now.");
			int count = 0;
			
			for (WebElement myFAQAccordion: myFAQAccordions)
			{		    
				myFAQAccordion.click();
				Thread.sleep(1000);
				if (myFAQAccordionsBodyText.get(count).getText().toString() !=null)
				{
					if (myFAQAccordionsBodyText.get(count).getText().toString()!=null & myFAQAccordionsBodyText.get(count).getText().toString().substring(0, 10)!=null)
					{
						System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Accordion "+count+" Partial Text is: "+myFAQAccordionsBodyText.get(count).getText().toString().substring(0, 10));
					}
					else
					{
						System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Accordion "+count+" Text is: "+myFAQAccordionsBodyText.get(count).getText().toString());
					}
					
/*					myFAQAccordion.click();
					Thread.sleep(1000);*/
					count++;
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].scrollIntoView();", myFAQAccordion);
				}	
			}
			/**
			 * Now perform Close on all Accordions - Closing is causing Bug in Saucelabs.
			 */				
/*				for (WebElement myFAQAccordion: myFAQAccordions)
			{		    
				myFAQAccordion.click();
				Thread.sleep(1000);
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView();", myFAQAccordion);
			}*/
			isMyTestPassed = true;
		}
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Accordions are missing on Rockettes -> FAQ Page!");
			isMyTestPassed = false;
		}
		
	} catch (WebDriverException e) {
		// e.printStackTrace();
		isMyTestPassed = false;
		System.out.println(this.myDriverParameters+e.getMessage());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.out.println(this.myDriverParameters+e.getMessage());
	}
	return isMyTestPassed;
	}
	
	@Test(description="Is Related Contents Section displayed?", groups = { "rockettesfaqpage", "smoke", "fullintegration" })
	public boolean isRelatedContentSectionPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Test Case-3: Is Related Contents Section displayed?");
		return isMyTestPassed = RockettesReusableFunctionalities.isRelatedContentSectionPresent(driver);
		}

	@Test(description="Is Partner Section displayed?", groups = { "rockettesfaqpage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Test Case-4: Is Partner Section displayed");
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes FAQ Page: Test Case-5. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}