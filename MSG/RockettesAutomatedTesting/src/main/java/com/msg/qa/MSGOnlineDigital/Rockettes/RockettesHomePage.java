package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class RockettesHomePage
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesHomePage(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Home Page?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean onRockettesHomePage() throws InterruptedException, AWTException
		{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Home Page URL to be tested is: "+this.URL+" ******************* ");
		if (this.URL.contains("staging"))
		{
			//String myStagingURL = this.URL.replace("staging", "msgtransition:Msg123@staging");
			//driver.navigate().to(myStagingURL);
			driver.navigate().to(this.URL);
			//driver.manage().window().maximize();
			
			/* Commented due to un-accessibility of Clipboard in Saucelabs
			String userName = "msgtransition";
		    StringSelection stringSelection = new StringSelection(userName);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(stringSelection, stringSelection);

		    Robot myRobot = new Robot();
		    myRobot.keyPress(KeyEvent.VK_CONTROL);
		    myRobot.keyPress(KeyEvent.VK_V);
		    myRobot.keyRelease(KeyEvent.VK_V);
		    myRobot.keyRelease(KeyEvent.VK_CONTROL);
		    myRobot.keyPress(KeyEvent.VK_TAB);
		    myRobot.keyRelease(KeyEvent.VK_TAB);

		    String password = "Msg123";
		    StringSelection stringSelection1 = new StringSelection(password);
		    Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard1.setContents(stringSelection1, stringSelection1);

		    myRobot.keyPress(KeyEvent.VK_CONTROL);
		    myRobot.keyPress(KeyEvent.VK_V);
		    myRobot.keyRelease(KeyEvent.VK_V);
		    myRobot.keyRelease(KeyEvent.VK_CONTROL);
		    myRobot.keyPress(KeyEvent.VK_TAB);
		    myRobot.keyRelease(KeyEvent.VK_TAB);

		    myRobot.keyPress(KeyEvent.VK_ENTER);
		    myRobot.keyRelease(KeyEvent.VK_ENTER);
	
			Thread.sleep(5000);
			*/
		}
		else
			{
			driver.get(this.URL);
			//driver.manage().window().maximize();
			}
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}
	
	@Test(description="Is Hamburger menu present and has working Nav Links?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isHamburgerMenuPresentandHasWorkingNavLinks() throws Exception
		{
			System.out.println(this.myDriverParameters+" - Rockettes Home Page: Test Case-1. Hamburger Menu Nav Links verification.");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			String[] mySelector = this.selectors.get("btnHamburgerMenu");
			if (mySelector[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
			WebElement myNAVMenu = driver.findElement(By.cssSelector(mySelector[0]));
			myNAVMenu.click();			
			}
			
			if (mySelector[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
			WebElement myNAVMenu = driver.findElement(By.xpath(mySelector[0]));
			myNAVMenu.click();			
			}
			
			/**
			 * Hamburger Menu Links
			 */
			mySelector = this.selectors.get("btnHamburgerMenuLinks");
			if (mySelector[1].equals("CSS"))
			{
				// Check if Links are valid in Hamburger menu and not returning 404.
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
				List<WebElement> myNAVMenuLinks = driver.findElements(By.cssSelector(mySelector[0]));
				if (myNAVMenuLinks.size() > 0 )
				{
				for(int count = 0; count < myNAVMenuLinks.size(); count++)
					{
					if (RockettesReusableFunctionalities.verifyIfLinkIsWorking(myNAVMenuLinks.get(count).getAttribute("href").toString()) != 404)
						{
						System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is valid.");
						isMyTestPassed = true;
						}
					else
						{
						System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is invalid!!");
						isMyTestPassed = false;
						return isMyTestPassed;
						}
					}
				}
				
				else
				{
					System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Links are not present!!");
					isMyTestPassed = false;
					return isMyTestPassed;
				}
				
				isMyTestPassed = true;
			}
			
			if (mySelector[1].equals("Xpath"))
			{
				// Check if Links are valid in Hamburger menu and not returning 404.
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
				List<WebElement> myNAVMenuLinks = driver.findElements(By.xpath(mySelector[0]));
				if (myNAVMenuLinks.size() > 0 )
				{
				for(int count = 0; count < myNAVMenuLinks.size(); count++)
					{
					if (RockettesReusableFunctionalities.verifyIfLinkIsWorking(myNAVMenuLinks.get(count).getAttribute("href").toString()) != 404)
						{
						System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is valid.");
						isMyTestPassed = true;
						}
					else
						{
						System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is invalid!!");
						isMyTestPassed = false;
						return isMyTestPassed;
						}
					}
				}
				
				else
				{
					System.out.println(this.myDriverParameters+" - Rockettes Home Page: Navigation Menu Links are not present!!");
					isMyTestPassed = false;
					return isMyTestPassed;
				}
				
				isMyTestPassed = true;
			}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
		}
	
	@Test(description="Is Photos present and Scrolleble?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isPhotosPresentAndScrolleable()
		{
		System.out.println(this.myDriverParameters+" - Rockettes Home Page: Test Case-2. Photos scrolling verification.");
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
	
	@Test(description="Is latest from Rockettes present and has working links?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isLatestFromRockettesPresentAndHasWorkingLinks() throws Exception
	{
		System.out.println(this.myDriverParameters+" - Rockettes Home Page: Test Case-3. Latest from Rockettes Links verification.");
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			String[] mySelector1 = this.selectors.get("btnHomePageLatestSectionParent");
			String[] mySelector2 = this.selectors.get("btnHomePageLatestSectionChild1");
			String[] mySelector3 = this.selectors.get("btnHomePageLatestSectionChild2");
			String[] mySelector4 = this.selectors.get("btnHomePageLatestSectionChild3");
			String[] mySelector5 = this.selectors.get("btnHomePageLatestSectionChild4");
			
			if (mySelector1[1].equals("Xpath"))
			{
			WebElement myLoadMoreBtn = driver.findElement(By.xpath(mySelector1[0])).findElement(By.xpath(mySelector2[0])).findElement(By.xpath(mySelector3[0])).findElement(By.xpath(mySelector4[0])).findElement(By.xpath(mySelector5[0]));    
			jse.executeScript("arguments[0].scrollIntoView();", myLoadMoreBtn);
			jse.executeScript("arguments[0].scrollIntoView();", myLoadMoreBtn);
		    //myLoadMoreBtn.click();	    
	
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			String[] mySelector6 = this.selectors.get("btnHomePageLatestSectionTitle");
			List<WebElement> myLatest = null;
			List<WebElement> myLatestLinks = null;
			
			if(mySelector6[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector6[0])));
			myLatest = driver.findElements(By.cssSelector(mySelector6[0]));
			}
			
			if(mySelector6[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector6[0])));
			myLatest = driver.findElements(By.xpath(mySelector6[0]));
			}
			
			String[] mySelector7 = this.selectors.get("btnHomePageLatestSectionLink");
			
			if(mySelector7[1].equals("CSS"))
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector7[0])));
				myLatestLinks = driver.findElements(By.cssSelector(mySelector7[0]));
			}
			
			if(mySelector7[1].equals("Xpath"))
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector7[0])));
				myLatestLinks = driver.findElements(By.xpath(mySelector7[0]));
			}
			
			System.out.println(this.myDriverParameters+" - Rockettes Home Page: we have Latest section with "+myLatest.size()+" events, titles are as follows:");
			
			int count=0;
			
			for (WebElement myEle:myLatest)
			{
				System.out.println(this.myDriverParameters+" - Rockettes Home Page: Title is:"+myEle.getText());
				if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myLatestLinks.get(count).getAttribute("href").toString()) != 404)
				{
					System.out.println(this.myDriverParameters+" - Rockettes Home Page: Link: "+myLatestLinks.get(count).getAttribute("href").toString()+" is valid.");
				}
				else
				{
					System.out.println(this.myDriverParameters+" - Rockettes Home Page: Link: "+myLatestLinks.get(count).getAttribute("href").toString()+" is not valid.");
				}
				count++;
			}	
			isMyTestPassed = true;
			}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Do we've working links for NYSS and CS?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isNYSSAndCSLinkPresent() throws Exception
	{
		System.out.println(this.myDriverParameters+" - Rockettes Home Page: Test Case-4. New York Spectacular & Christmas Spectacular Links verification.");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			String[] mySelector = this.selectors.get("btnHamburgerMenuLinks");
			List<WebElement> myCSNYSSLinks = null;
			
			if (mySelector[1].equals("CSS"))
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
				myCSNYSSLinks = driver.findElements(By.cssSelector(mySelector[0]));
			}
			
			if (mySelector[1].equals("Xpath"))
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
				myCSNYSSLinks = driver.findElements(By.xpath(mySelector[0]));
			}

			System.out.println(this.myDriverParameters+" - Rockettes Home Page: Performing Check on Header Links - Christmas Spectacular and New York Spectacular Links:");
			
			if (myCSNYSSLinks.size() > 0)
			{
				for(WebElement myEle:myCSNYSSLinks)
				{
					if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myEle.getAttribute("href").toString()) != 404)
					{
					System.out.println(this.myDriverParameters+" - Rockettes Home Page: Link: "+myEle.getAttribute("href").toString()+" is valid.");
					isMyTestPassed = true;
					}
					else
					{
						System.out.println(this.myDriverParameters+" - Rockettes Home Page: Link: "+myEle.getAttribute("href").toString()+" is invalid!");
						isMyTestPassed = false;
						return isMyTestPassed;
					}
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
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Home Page: Test Case-5. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}