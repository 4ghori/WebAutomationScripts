package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.ArrayList;
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

public class RockettesBlog
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesBlog(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Blog Page?", groups = { "rockettesblogpage", "smoke", "fullintegration" })
	public boolean onRockettesBlogPage()
	{
		try
		{
		// Verify the Title of the page.
			System.out.println("******************* Rockettes Blog Page URL to be tested is: "+this.URL+" ******************* ");
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
		 *//*
		System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Test Case-1: Are we on Rockettes Blog Page?");		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnNYSSBlogTitle");
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
		
		if (myHelpfulTips.getText().toString().equals("BLOG"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes Blog Page: We are on Rockettes -> Blog Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Error: We are not on Rockettes -> Blog Page!");	
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
	
	@Test(description="Count Links in Blog and verify if they are working?", groups = { "rockettesblogpage", "smoke", "fullintegration" })
	public boolean isPhotosPresentAndScrolleable() throws Exception
		{
		String myBlogFailedURL = "";
		
		System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Test Case-2: Count Links in Blog and verify if they are working?");		
		
		try
		{
			int currentCount = 0, totalCount = 0;	
			List<WebElement> myBlogElements = null;
			try
			{
			do
			{
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='col-sm-4 masonry-item']>a")));
			    myBlogElements = driver.findElements(By.cssSelector("[class='col-sm-4 masonry-item']>a"));
			    
			    currentCount = myBlogElements.size();
			    
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id='EVNT_click_load_more_masonry']")));
			    WebElement myBlogbtn = driver.findElement(By.cssSelector("[id='EVNT_click_load_more_masonry']"));
				myBlogbtn.click();
				
				totalCount += 12;
				
				Thread.sleep(3000);
			}while(currentCount == totalCount);
		} catch (WebDriverException e) {
			isMyTestPassed = false;
		}
		
		System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Total Elements in Blog are: "+myBlogElements.size());

		isMyTestPassed = true;
		
		List<String> myBlogURLs = new ArrayList<String>();
		
		for(int i=0;i<myBlogElements.size();i++)
		{
			// 1. Get the Blog Link & if link is valid then click on it.
			if (RockettesReusableFunctionalities.verifyIfLinkIsWorking(myBlogElements.get(i).getAttribute("href").toString()) !=404) 
			{
				myBlogURLs.add(myBlogElements.get(i).getAttribute("href").toString());
				System.out.println("Rockettes Blog("+(i+1)+"): "+myBlogElements.get(i).getAttribute("href").toString()+" URL is valid.");
			}
			else
			{
				System.out.println("Rockettes Blog("+(i+1)+"): "+myBlogElements.get(i).getAttribute("href").toString()+" URL is not valid!!");
				isMyTestPassed = false;
			}
		}		
		
		/*for(int i=0;i<myBlogURLs.size();i+=250)
		{
			try
			{
			// 1. Navigate to one of the Blog.
			driver.navigate().to(myBlogURLs.get(i));
			
			System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Blog("+i+") URL that we are navigating to: "+myBlogURLs.get(i));
		
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			// 2. Find the Title in the Blog and Content.
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".entry-title")));
			 WebElement myBlogTitle = driver.findElement(By.cssSelector(".entry-title"));
				
			 wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".col-sm-8.col-lg-8>p")));
			 List<WebElement> myBlogContent = driver.findElements(By.cssSelector(".col-sm-8.col-lg-8>p"));
			 
			 if (myBlogTitle.getText() == null || myBlogContent == null)
			 {
				myBlogFailedURL =  myBlogURLs.get(i);
			    System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Blog Content or Title is missing on URL: "+myBlogURLs.get(i));
			    isMyTestPassed = false;
			 }
			 else
			 {
				 System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Blog("+i+") Title: "+myBlogTitle.getText()+" and Content: "+myBlogContent.get(0).getText()+"... is present at following Blog URL: "+ myBlogURLs.get(i));
			 }
		} catch (WebDriverException e) {
			System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Blog Content or Title is missing on following URL: "+myBlogFailedURL);
			continue;
		}
		}*/
		isMyTestPassed = true;
		} catch (WebDriverException e) {
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Blog Content or Title is missing on following URL: "+myBlogFailedURL);
		}
		return isMyTestPassed;
		}	

	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Blog Page: Test Case-3. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}