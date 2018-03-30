package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RockettesHomePageForcedFailure
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	
	public RockettesHomePageForcedFailure(WebDriver driver, String URL, boolean isMyTestPassed)
	{
		this.driver = driver;
		this.URL = URL;
		this.isMyTestPassed = isMyTestPassed;
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
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;
		}
	
	@Test(description="Is Hamburger menu present and has working Nav Links?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isHamburgerMenuPresentandHasWorkingNavLinks() throws Exception
		{
			System.out.println("Rockettes Home Page: Test Case-1. Hamburger Menu Nav Links verification.");
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn-menu']")));
			WebElement myNAVMenu = driver.findElement(By.cssSelector("[class='btn-menuFAILURE']"));
			myNAVMenu.click();
			
			// Check if Links are valid in Hamburger menu and not returning 404.
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='menu-dropdown-menu']>li>a")));
			List<WebElement> myNAVMenuLinks = driver.findElements(By.cssSelector("[id='menu-dropdown-menu']>li>a"));
			
			if (myNAVMenuLinks.size() > 0 )
			{
			for(int count = 0; count < myNAVMenuLinks.size(); count++)
				{
				if (RockettesReusableFunctionalities.verifyIfLinkIsWorking(myNAVMenuLinks.get(count).getAttribute("href").toString()) != 404)
					{
					System.out.println("Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is valid.");
					isMyTestPassed = true;
					}
				else
					{
					System.out.println("Rockettes Home Page: Navigation Menu Link("+count+"): "+myNAVMenuLinks.get(count).getAttribute("href").toString()+" is invalid!!");
					isMyTestPassed = false;
					return isMyTestPassed;
					}
				}
			}
			
			else
			{
				System.out.println("Rockettes Home Page: Navigation Menu Links are not present!!");
				isMyTestPassed = false;
				return isMyTestPassed;
			}
			
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
			System.out.println("************* FORCED FAILURE FORCED FAILURE FORCED FAILURE *************");
		}
		return isMyTestPassed;	
		}
	
	@Test(description="Is Photos present and Scrolleble?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isPhotosPresentAndScrolleable()
		{
		System.out.println("Rockettes Home Page: Test Case-2. Photos scrolling verification.");
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
	
	@Test(description="Is latest from Rockettes present and has working links?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isLatestFromRockettesPresentAndHasWorkingLinks() throws Exception
	{
		System.out.println("Rockettes Home Page: Test Case-3. Latest from Rockettes Links verification.");
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			WebElement myLoadMoreBtn = driver.findElement(By.xpath("//div[@class='container']")).findElement(By.xpath("div[@class='row']")).findElement(By.xpath("div[@class='col-sm-10 col-sm-offset-1']")).findElement(By.xpath("div[@class='load-more']")).findElement(By.xpath("a[@class='btn'][contains(text(), 'Load More')]"));    jse.executeScript("arguments[0].scrollIntoView();", myLoadMoreBtn);
			jse.executeScript("arguments[0].scrollIntoView();", myLoadMoreBtn);
		    //myLoadMoreBtn.click();	    
	
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".caption>h3")));
			List<WebElement> myLatest = driver.findElements(By.cssSelector(".caption>h3"));
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".col-sm-4.masonry-item>a")));
			List<WebElement> myLatestLinks = driver.findElements(By.cssSelector(".col-sm-4.masonry-item>a"));
			
			System.out.println("Rockettes Home Page: we have Latest section with "+myLatest.size()+" events, titles are as follows:");
			
			int count=0;
			
			for (WebElement myEle:myLatest)
			{
				System.out.println("Rockettes Home Page: Title is:"+myEle.getText());
				if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myLatestLinks.get(count).getAttribute("href").toString()) != 404)
				{
					System.out.println("Rockettes Home Page: Link: "+myLatestLinks.get(count).getAttribute("href").toString()+" is valid.");
				}
				else
				{
					System.out.println("Rockettes Home Page: Link: "+myLatestLinks.get(count).getAttribute("href").toString()+" is not valid.");
				}
				count++;
			}	
			
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Do we've working links for NYSS and CS?", groups = { "rocketteshomepage", "smoke", "fullintegration" })
	public boolean isNYSSAndCSLinkPresent() throws Exception
	{
		System.out.println("Rockettes Home Page: Test Case-4. New York Spectacular & Christmas Spectacular Links verification.");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='menu-ribbon']>li>a")));
			List<WebElement> myCSNYSSLinks = driver.findElements(By.cssSelector("[id='menu-ribbon']>li>a"));

			System.out.println("Rockettes Home Page: Performing Check on Header Links - Christmas Spectacular and New York Spectacular Links:");
			
			if (myCSNYSSLinks.size() > 0)
			{
				for(WebElement myEle:myCSNYSSLinks)
				{
					if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myEle.getAttribute("href").toString()) != 404)
					{
					System.out.println("Rockettes Home Page: Link: "+myEle.getAttribute("href").toString()+" is valid.");
					isMyTestPassed = true;
					}
					else
					{
						System.out.println("Rockettes Home Page: Link: "+myEle.getAttribute("href").toString()+" is invalid!");
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
			System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
	}
}
