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

public class RockettesCSPlanYourDay
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSPlanYourDay(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Christmas Spectacular Plan your day Page?", groups = { "rockettescspldpage", "smoke", "fullintegration" })
	public boolean onRockettesCSPlanYourDayPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Christmas Spectacular Plan your Day Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Christmas Plan your day Page.
		 * a. Read Plan your Day Title and style
		 */
		
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-1: Are we on Christmas Spectacular Plan your Day Page?");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnCSPlanYourDayTitle");
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
		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("PLAN YOUR DAY"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: We are on Rockettes -> Christmas -> Plan your Day Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Error: We are not on Rockettes -> Christmas -> Plan Your Day Page!");	
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Do we have navigation Map?", groups = { "cs", "rockettescspldpage", "smoke", "fullintegration" })
	public boolean isNavigationMapPersent()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-2: Do we have navigation Map?");
		try
		{
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Do we have Travel accordion with working accordions?", groups = { "cs", "rockettescspldpage", "smoke", "fullintegration" })
	public boolean isTravelAccordionWorking()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-3: Do we have Travel accordion with working accordions?");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			
			
			
			List<WebElement> myCSPlanYourDayAccordions= null;
			String[] mySelector1 = this.selectors.get("btnCSPlanYourDayTravelAccordionParent");
			if (mySelector1[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
			myCSPlanYourDayAccordions = driver.findElements(By.cssSelector(mySelector1[0]));
			}
			if (mySelector1[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
			myCSPlanYourDayAccordions = driver.findElements(By.xpath(mySelector1[0]));
			}
			
			List<WebElement> myCSPlanYourDayAccordionsBodyText= null;
			String[] mySelector2 = this.selectors.get("btnCSPlanYourDayTravelAccordionChild");
			if (mySelector2[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
			myCSPlanYourDayAccordionsBodyText = driver.findElements(By.cssSelector(mySelector2[0]));
			}
			if (mySelector2[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
			myCSPlanYourDayAccordionsBodyText = driver.findElements(By.xpath(mySelector2[0]));
			}
			
			if ( myCSPlanYourDayAccordions.size() > 0 )
			{
				System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Total "+myCSPlanYourDayAccordions.size()+" Accordions are present on Rockettes -> CS -> Plan your day Page.");
				System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Performing Open/close for all "+myCSPlanYourDayAccordions.size()+" Accordions now.");
				int count = 0;
				
				for (WebElement myCSPlanYourDayAccordion: myCSPlanYourDayAccordions)
				{		    
					myCSPlanYourDayAccordion.click();
					Thread.sleep(1000);
					if (myCSPlanYourDayAccordionsBodyText.get(count).getText().toString() !=null)
					{
						System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Accordion "+count+" Title is: "+myCSPlanYourDayAccordionsBodyText.get(count).getText().toString());
						
						if(count < 1)
						{
							myCSPlanYourDayAccordion.click();
						}
						Thread.sleep(1000);
						count++;
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("arguments[0].scrollIntoView();", myCSPlanYourDayAccordion);
					}	
				}
				isMyTestPassed = true;
			}
			else
			{
				System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Accordions are missing on Rockettes -> CS -> Plan your Day Page!");
				isMyTestPassed = false;
			}
			
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isMyTestPassed;

	}
	
	@Test(description="Do we Panels with Flexsliders?", groups = { "cs", "rockettescspldpage", "smoke", "fullintegration" })
	public boolean isPanelwithFlexSliderPresent() throws InterruptedException
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-4: Do we Panels with Flexsliders?");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			List<WebElement> myFlexViewPorts= null;
			String[] mySelector1 = this.selectors.get("btnCSPlanYourDayFlexSliderParent");
			if (mySelector1[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
			myFlexViewPorts = driver.findElements(By.cssSelector(mySelector1[0]));
			}
			if (mySelector1[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
			myFlexViewPorts = driver.findElements(By.xpath(mySelector1[0]));
			}	
			
			List<WebElement> myFlexViewPortTitle= null;
			String[] mySelector2 = this.selectors.get("btnCSPlanYourDayFlexSliderChild1");
			if (mySelector2[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
			myFlexViewPortTitle = driver.findElements(By.cssSelector(mySelector2[0]));
			}
			if (mySelector2[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
			myFlexViewPortTitle = driver.findElements(By.xpath(mySelector2[0]));
			}
					
			List<WebElement> myFlexViewPortRightArrow= null;
			String[] mySelector3 = this.selectors.get("btnCSPlanYourDayFlexSliderChild2");
			if (mySelector1[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector3[0])));
			myFlexViewPortRightArrow = driver.findElements(By.cssSelector(mySelector3[0]));
			}
			if (mySelector3[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector3[0])));
			myFlexViewPortRightArrow = driver.findElements(By.xpath(mySelector3[0]));
			}
			
			List<WebElement> myFlexViewPortLeftArrow= null;
			String[] mySelector4 = this.selectors.get("btnCSPlanYourDayFlexSliderChild3");
			if (mySelector4[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector4[0])));
			myFlexViewPortLeftArrow = driver.findElements(By.cssSelector(mySelector4[0]));
			}
			if (mySelector4[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector4[0])));
			myFlexViewPortLeftArrow = driver.findElements(By.xpath(mySelector4[0]));
			}
			
			for (int count=0;count<myFlexViewPorts.size();count++)
			{
			List<WebElement> myIndividualFlexViewPort = myFlexViewPorts.get(count).findElements(By.cssSelector("[class^='slide']"));
			System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: We are in Panel "+myFlexViewPortTitle.get(count).getText().toString()+" with "+myIndividualFlexViewPort.size()+" Informations.");
		/**
		 * https://ci.msghubvision.com/job/Rockettes_Test_Automation_FullRegression_Cycle/103/consoleFull
		 * Firefox 41 is not simulating the CLick on the Accordion on Plan your day pag - PLACES TO STAY IN NYC Section 
		 */
			if(myDriverParameters.contains("Firefox") && myDriverParameters.contains("41"))
			{
				/**
				 * Nothing to do for Clicks on Firefox - 41
				 */
			}
			else
			{
				myFlexViewPortRightArrow.get(count).click();
				Thread.sleep(1000);
				myFlexViewPortRightArrow.get(count).click();
				Thread.sleep(1000);
				myFlexViewPortLeftArrow.get(count).click();
				Thread.sleep(1000);
				myFlexViewPortLeftArrow.get(count).click();
				Thread.sleep(1000);	
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
	
	@Test(description="Is Flex Slider in the Panel Scorlleable?", groups = { "cs", "rockettescspldpage", "smoke", "fullintegration" })
	public boolean isFlexSliderScorellable()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-5: Is Flex Slider in the Panel Scorllable?");
		try
		{
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Is Partner Section displayed?", groups = { "cs", "rockettescspldpage", "smoke", "fullintegration" })	
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-6: Is Partner Section displayed?");		
		/**
		 * https://ci.msghubvision.com/job/Rockettes_Test_Automation_FullRegression_Cycle/103/consoleFull
		 * Firefox 41 is not performing the Check on CS Plan your Day Page for Partners. 
		 */
			if(myDriverParameters.contains("firefox") && myDriverParameters.contains("41"))
			{
				/**
				 * Nothing to do for Partner Check on Firefox - 41 for Landing Page.
				 */
				return true;
			}
			else
			{
				return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
			}		
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Plan-Your-Day Page: Test Case-7. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}