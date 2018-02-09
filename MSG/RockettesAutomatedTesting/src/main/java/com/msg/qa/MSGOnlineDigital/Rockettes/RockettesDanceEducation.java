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

public class RockettesDanceEducation
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesDanceEducation(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Dance Education Page?", groups = { "rockettesdanceedupage", "smoke", "fullintegration" })
	public boolean onRockettesDanceEduPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Dance Education Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes Dance Education Page.
		 * a. Read Plan your Day Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Test Case-1: Are we on Rockettes Dance Education Page?");			
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnDanceEduTitle");
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
		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("DANCE EDUCATION"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: We are on Rockettes -> Dance Education Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Error: We are not on Rockettes -> Dance Education Page!");		
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Do we have working social media links?", groups = { "rockettesdanceedupage", "smoke", "fullintegration" })
	public boolean isSocialMediaLinkWorking() throws Exception
	{
		System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Test Case-2: Do we have working social media links?");			
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			List<WebElement> mySocialLinks= null;
			String[] mySelector1 = this.selectors.get("btnDanceEduSocialLink");
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
			
			if(mySocialLinks.size() > 0)
			{
				for (int count =0; count<mySocialLinks.size();count++)
				{
					if( RockettesReusableFunctionalities.verifyIfLinkIsWorking(mySocialLinks.get(count).getAttribute("href").toString()) == 404)
							{
							System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Social Media Link("+count+") is: "+mySocialLinks.get(count).getAttribute("href").toString()+" invalid!");
							isMyTestPassed = false;
							return isMyTestPassed;
							}
					else
							{
							System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Social Media Link("+count+") is: "+mySocialLinks.get(count).getAttribute("href").toString()+" valid.");				
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
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
	}

	@Test(description="Do we have Dance Stories displayed?", groups = { "rockettesdanceedupage", "fullintegration" })
	public boolean isDanceStoriesDisplayed()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Test Case-3: Do we have Dance Stories displayed?");		
		try
		{
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Do we have Read More displayed?", groups = { "rockettesdanceedupage", "fullintegration" })
	public boolean isReadMoreDisplayed()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Test Case-4: Do we have Read More displayed?");		
		try
		{
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Dance Education Page: Test Case-5. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}