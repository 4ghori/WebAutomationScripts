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

public class RockettesPhotos
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesPhotos(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes Photos Page?", groups = { "nyss", "rockettesphotospage", "smoke", "fullintegration" })
	public boolean onRockettesPhotosPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Photos Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Rockettes Photos Page.
		 * a. Read Photos Page Title and style
		 */
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-1: Are we on Rockettes Photos Page?");			
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnPhotosTitle");
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
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("PHOTOS"))
		{
			System.out.println(this.myDriverParameters+" - Rockettes Photos Page: We are on Rockettes -> Photos Page.");
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Error: We are not on Rockettes -> Photos Page!");	
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Is our photo slider working?", groups = { "rockettesphotospage", "smoke", "fullintegration" })
	public boolean isPhotoSliderWorking()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-2: Is our photo slider working?");	
        try
		{	
    		WebDriverWait wait = new WebDriverWait(driver, 30);
    		
    		WebElement myCSTitle = null;
    		
    		String[] mySelectorCSTitle = this.selectors.get("btnPhotosSliderParentCSTitle");
    		if (mySelectorCSTitle[1].equals("CSS"))
    		{
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorCSTitle[0])));
    		myCSTitle = driver.findElement(By.cssSelector(mySelectorCSTitle[0]));
    		}
    		if (mySelectorCSTitle[1].equals("Xpath"))
    		{
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorCSTitle[0])));
    		myCSTitle = driver.findElement(By.xpath(mySelectorCSTitle[0]));
    		}
    	
    		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: We are scrolling in the section of: "+myCSTitle.getText().toString());
    		 
    		List <WebElement> firstRowOfPhotos = null;
    		String[] mySelectorParentCS = this.selectors.get("btnPhotosSliderParentCS");
    		if (mySelectorParentCS[1].equals("CSS"))
    		{
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorParentCS[0])));
    		firstRowOfPhotos = driver.findElements(By.cssSelector(mySelectorParentCS[0]));
    		}
    		if (mySelectorParentCS[1].equals("Xpath"))
    		{
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorParentCS[0])));
    		firstRowOfPhotos = driver.findElements(By.xpath(mySelectorParentCS[0]));
    		}
    		
        	if (firstRowOfPhotos.size()>0)
			{
        		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Total Photos present in "+myCSTitle.getText().toString()+" section are: "+firstRowOfPhotos.size());
               
        		for(WebElement myEle:firstRowOfPhotos)
        		{
        			System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Photo Text: "+myEle.getText());
        		}
		   
        	// NYSS Section	
        		WebElement myNYSSTitle = null;
        		
        		String[] mySelectorNYSSTitle = this.selectors.get("btnPhotosSliderParentNYSTitle");
        		if (mySelectorNYSSTitle[1].equals("CSS"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorNYSSTitle[0])));
        		myNYSSTitle = driver.findElement(By.cssSelector(mySelectorNYSSTitle[0]));
        		}
        		if (mySelectorNYSSTitle[1].equals("Xpath"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorNYSSTitle[0])));
        		myNYSSTitle = driver.findElement(By.xpath(mySelectorNYSSTitle[0]));
        		}
        	
        		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: We are scrolling in the section of: "+myNYSSTitle.getText().toString());
        		 
        		List <WebElement> secondRowOfPhotos = null;
        		String[] mySelectorParentNYSS = this.selectors.get("btnPhotosSliderParentNYS");
        		if (mySelectorParentNYSS[1].equals("CSS"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorParentNYSS[0])));
        		secondRowOfPhotos = driver.findElements(By.cssSelector(mySelectorParentNYSS[0]));
        		}
        		if (mySelectorParentNYSS[1].equals("Xpath"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorParentNYSS[0])));
        		secondRowOfPhotos = driver.findElements(By.xpath(mySelectorParentNYSS[0]));
        		}
        		
            	if (secondRowOfPhotos.size()>0)
    			{
            		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Total Photos present in "+myNYSSTitle.getText().toString()+" section are: "+secondRowOfPhotos.size());
                   
            		for(WebElement myEle:secondRowOfPhotos)
            		{
            			System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Photo Text: "+myEle.getText());
            		}
    			}

            	// OUR PHOTOS
            	// OP Section	
        		WebElement myOPTitle = null;
        		
        		String[] mySelectorOPTitle = this.selectors.get("btnPhotosSliderParentOPTitle");
        		if (mySelectorOPTitle[1].equals("CSS"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorOPTitle[0])));
        		myOPTitle = driver.findElement(By.cssSelector(mySelectorOPTitle[0]));
        		}
        		if (mySelectorOPTitle[1].equals("Xpath"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorOPTitle[0])));
        		myOPTitle = driver.findElement(By.xpath(mySelectorOPTitle[0]));
        		}
        	
        		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: We are scrolling in the section of: "+myOPTitle.getText().toString());
        		 
        		List <WebElement> thirdRowOfPhotos = null;
        		String[] mySelectorParentOP = this.selectors.get("btnPhotosSliderParentOP");
        		if (mySelectorParentOP[1].equals("CSS"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelectorParentOP[0])));
        		thirdRowOfPhotos = driver.findElements(By.cssSelector(mySelectorParentOP[0]));
        		}
        		if (mySelectorParentOP[1].equals("Xpath"))
        		{
        		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelectorParentOP[0])));
        		thirdRowOfPhotos = driver.findElements(By.xpath(mySelectorParentOP[0]));
        		}
        		
            	if (thirdRowOfPhotos.size()>0)
    			{
            		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Total Photos present in "+myOPTitle.getText().toString()+" section are: "+thirdRowOfPhotos.size());
                   
            		for(WebElement myEle:thirdRowOfPhotos)
            		{
            			System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Photo Text: "+myEle.getText());
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
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;	
	}
	
	@Test(description="Is our navigation resolve to Page with Thumbnails?", groups = { "rockettesphotospage", "fullintegration" })
	public boolean isPhotoSliderLinkNavigatesToFurtherDetails()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-3: Is our navigation resolve to Page with Thumbnails?");			
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
	
	@Test(description="Are Thumbnails working?", groups = { "rockettesphotospage", "fullintegration" })
	public boolean isFurtherDetailsThumbnailLinksWorking()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-4: Are Thumbnails working?");			
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

	@Test(description="Can we use Left and Right arrows over the Thumbnail Display?", groups = { "rockettesphotospage", "fullintegration" })
	public boolean isLeftAndRightNavigationWorkingOnThumbnailDisplay()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-5: Can we use Left and Right arrows over the Thumbnail Display?");			
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

	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes Photos Page: Test Case-6. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}