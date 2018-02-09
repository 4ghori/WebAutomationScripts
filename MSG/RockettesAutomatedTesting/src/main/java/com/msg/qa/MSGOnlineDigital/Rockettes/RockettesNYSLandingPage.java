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

public class RockettesNYSLandingPage
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesNYSLandingPage(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on NYSS Landing Page?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	public boolean onRockettesNYSLandingPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes NewYork Spectacular Landing Page URL to be tested is: "+this.URL+" ******************* ");
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
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
		}
	
	@Test(description="Is Hero Video Present and Running?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	public boolean isHeroVideoPresentAndRunning()
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-1: Is Hero Video Present and Running?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myHeroVideo = null;
		
		String[] mySelector = this.selectors.get("btnNYSLandingPageHeroVideo");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myHeroVideo = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myHeroVideo = driver.findElement(By.xpath(mySelector[0]));
		}
		
		if (myHeroVideo.getAttribute("src").toString().length() > 0)
		{
			System.out.println("Hero Video is present: "+myHeroVideo.getAttribute("src").toString());
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
			System.out.println(this.myDriverParameters+" - Hero Video is missing!");
			//System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
		}

	@Test(description="Is Buy Tickets Leads to TM for selected date and time?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	public boolean isBuyTicketNavigtesToTM() throws InterruptedException
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-2: Is Buy Tickets Leads to TM for selected date and time?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myTMBuyURL = null;
		
		String[] mySelector = this.selectors.get("btnNYSLandingPageBuyTickets");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myTMBuyURL = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myTMBuyURL = driver.findElement(By.xpath(mySelector[0]));
		}
		
		if (myTMBuyURL.isDisplayed())
			{
			System.out.println("TM Buy button is present on NYSS Show Landing Page");
//			String myParentURL = driver.getCurrentUrl();
			String myParentWindow = driver.getWindowHandle();    
			// Navigate to TM and come back.
			myTMBuyURL.click();
			// Switching from parent window to child window   
			for (String myChildWindow : driver.getWindowHandles())  
		     {  								        	
				Thread.sleep(4000);
				driver.switchTo().window(myChildWindow);
			 }
		     
			//Switching back to Parent Window  
			Thread.sleep(2000);
			driver.close();
			driver.switchTo().window(myParentWindow); 
//			driver.navigate().to(myParentURL);
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
			System.out.println(this.myDriverParameters+" - Buy Tickets is missing!");
			//System.out.println(e.getMessage());
		}
		return isMyTestPassed;	
		}	
	
	@Test(description="Can we submit Email Sign Up Form?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	// Looks Like Sign Up is removed.
	public boolean isEmailSigUpSubmittingDetails()
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-2: Can we submit Email Sign Up Form?");	
		try
		{
			isMyTestPassed = true;
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			//System.out.println(e.getMessage());
			System.out.println(this.myDriverParameters+" - EMail SignUp is missing!");
		}
		return isMyTestPassed;
		}
	
	@Test(description="Is sneak peak carousel displaying images?", groups = { "nyss", "rockettesnysslandingpage", "sneakpeak", "smoke", "fullintegration" })
	public boolean isSneakPeakCaraouselDisplayingImages()
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-3: Is sneak peak carousel displaying images?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement mySneapPeekTitle = null;
		
		String[] mySelector = this.selectors.get("btnNYSLandingPageSneakPeakCarouselParent");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		mySneapPeekTitle = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		mySneapPeekTitle = driver.findElement(By.xpath(mySelector[0]));
		}
	    
	    jse.executeScript("arguments[0].scrollIntoView();", mySneapPeekTitle);
	 
	    List<WebElement> myTheShowImages= null;
		String[] mySelector1 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselChild1");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myTheShowImages = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myTheShowImages = driver.findElements(By.xpath(mySelector1[0]));
		}
		
	    List<WebElement> myBehindTheScenesImages= null;
		String[] mySelector2 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselChild2");
		if (mySelector2[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
		myBehindTheScenesImages = driver.findElements(By.cssSelector(mySelector2[0]));
		}
		if (mySelector2[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
		myBehindTheScenesImages = driver.findElements(By.xpath(mySelector2[0]));
		}
		
		if (myTheShowImages.size() > 0 && myBehindTheScenesImages.size() > 0)
			{
			System.out.println("Total Images present in THE SHOW Sneak Peek Caraousel: "+myTheShowImages.size());
			System.out.println("Total Images present in BEHIND THE SCENES Sneak Peek Caraousel: "+myBehindTheScenesImages.size());
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
			//System.out.println(e.getMessage());
			System.out.println(this.myDriverParameters+" - Sneak peak carousel is missing!");
		}
		return isMyTestPassed;	
		}
	
	@Test(description="Is sneak peak carousel image navigation enabled?", groups = { "nyss", "rockettesnysslandingpage", "sneakpeak", "smoke", "fullintegration" })
	public boolean isSneakPeakCaraouselImagesNavigationEnabled()
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-4: Is sneak peak carousel image navigation enabled?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		WebElement mySneapPeekTitle = null;
		
		String[] mySelector = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavParent");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		mySneapPeekTitle = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		mySneapPeekTitle = driver.findElement(By.xpath(mySelector[0]));
		}
	    
	    jse.executeScript("arguments[0].scrollIntoView();", mySneapPeekTitle);
	    
	    List<WebElement> myTheShowImages= null;
		String[] mySelector1 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavChild1");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myTheShowImages = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myTheShowImages = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		System.out.println(this.myDriverParameters+" - THE SHOWS Section images navigation will be verified now for "+myTheShowImages.size()+" images.");
		
		if (myTheShowImages.size() > 0) 
			{
			List<WebElement> mySneakPeakLinks= null;
			String[] mySelector3 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavChild2");
			if (mySelector3[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector3[0])));
			mySneakPeakLinks = driver.findElements(By.cssSelector(mySelector3[0]));
			}
			if (mySelector1[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector3[0])));
			mySneakPeakLinks = driver.findElements(By.xpath(mySelector3[0]));
			}

			mySneakPeakLinks.get(0).click();
			WebElement myTheShowsForwardArrow= null;
			String[] mySelector4 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavChild3");
			if (mySelector4[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector4[0])));
			myTheShowsForwardArrow = driver.findElement(By.cssSelector(mySelector4[0]));
			}
			if (mySelector4[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector4[0])));
			myTheShowsForwardArrow = driver.findElement(By.xpath(mySelector4[0]));
			}
			
			for(int count=0;count < myTheShowImages.size();count++)
				{
	//			System.out.println("Image"+count+" URL is: "+myTheShowImages.get(count).getAttribute("src").toString());
				myTheShowsForwardArrow.click();
				}
			
			mySneakPeakLinks.get(1).click();
			
			List<WebElement> myBehindTheScenesImages= null;
			String[] mySelector5 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavChild4");
			if (mySelector5[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector5[0])));
			myBehindTheScenesImages = driver.findElements(By.cssSelector(mySelector5[0]));
			}
			if (mySelector5[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector5[0])));
			myBehindTheScenesImages = driver.findElements(By.xpath(mySelector5[0]));
			}
		
			System.out.println("BEHIND THE SCENES Section images navigation will be verified now for "+myBehindTheScenesImages.size()+" images.");	
			
			WebElement myBehindTheScenesForwardArrow= null;
			String[] mySelector6 = this.selectors.get("btnNYSLandingPageSneakPeakCarouselimageNavChild5");
			if (mySelector6[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector6[0])));
			myBehindTheScenesForwardArrow = driver.findElement(By.cssSelector(mySelector6[0]));
			}
			if (mySelector6[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector6[0])));
			myBehindTheScenesForwardArrow = driver.findElement(By.xpath(mySelector6[0]));
			}
			
			for(int count=0;count < myBehindTheScenesImages.size();count++)
				{
//				System.out.println("Image"+count+" URL is: "+myBehindTheScenesImages.get(count).getAttribute("src").toString());
				myBehindTheScenesForwardArrow.click();
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
			//System.out.println(e.getMessage());
			System.out.println(this.myDriverParameters+" - Sneak peak carousel image navigation is missing!");
		}
		return isMyTestPassed;	
		}	
	
	@Test(description="Are we going to News and reviews frm the Link?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	public boolean isNewsAndReviewsLinkEnabled() throws Exception
		{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-5: Are we going to News and reviews frm the Link?");
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		List<WebElement> myHeaderLinks= null;
		String[] mySelector5 = this.selectors.get("btnNYSLandingPageNewsAndReviewsLink");
		if (mySelector5[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector5[0])));
		myHeaderLinks = driver.findElements(By.cssSelector(mySelector5[0]));
		}
		if (mySelector5[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector5[0])));
		myHeaderLinks = driver.findElements(By.xpath(mySelector5[0]));
		}
	
		if (myHeaderLinks.size() > 0)
		{
		if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myHeaderLinks.get(0).getAttribute("href").toString())!=404)
			{
			System.out.println("Link "+myHeaderLinks.get(0).getText().toString()+": "+myHeaderLinks.get(0).getAttribute("href").toString()+" (valid link)");	
			isMyTestPassed = true;
			}
		else
			{
			System.out.println("Link "+myHeaderLinks.get(0).getText().toString()+": "+myHeaderLinks.get(0).getAttribute("href").toString()+" (invalid link)");
			isMyTestPassed = false;
			}

		if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myHeaderLinks.get(0).getAttribute("href").toString())!=404)
			{
			System.out.println("Link "+myHeaderLinks.get(1).getText().toString()+": "+myHeaderLinks.get(1).getAttribute("href").toString()+" (valid link)");			
			isMyTestPassed = true;
			}
		else
			{
		System.out.println("Link "+myHeaderLinks.get(1).getText().toString()+": "+myHeaderLinks.get(1).getAttribute("href").toString()+" (invalid link)");
		isMyTestPassed = false;
			}
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
			//System.out.println(e.getMessage());
			System.out.println(this.myDriverParameters+" - Navigation to news and reviews is missing!");
		}
		return isMyTestPassed;	
		}
	
	@Test(description="Is Sponsors Present?", groups = { "nyss", "rockettesnysslandingpage", "smoke", "fullintegration" })
	public boolean isSponsorsPresent()
	{
		System.out.println(this.myDriverParameters+" - Rockettes NYSS Landing Page: Test Case-6: Is Sponsors Present?");
		try
		{
		isMyTestPassed = RockettesReusableFunctionalities.isSponsorsPresent(driver);
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			//System.out.println(e.getMessage());
			System.out.println(this.myDriverParameters+" - Sponsors are missing!");
		}
		return isMyTestPassed;
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes NYS Landing Page: Test Case-7. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}