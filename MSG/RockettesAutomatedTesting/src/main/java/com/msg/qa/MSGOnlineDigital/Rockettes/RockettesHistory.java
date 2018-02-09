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

public class RockettesHistory
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	/**
	 * Static Variable declaration
	 */
	
	static boolean isAMPPagePresent = false;
	
	/**
	 * Constructor
	 * @param driver
	 * @param URL
	 * @param isMyTestPassed
	 * @param selectors
	 * @param isMobile
	 * @param driverParameters
	 */
	
	public RockettesHistory(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Rockettes History Page?", groups = { "rocketteshistorypage", "smoke", "fullintegration" })
	public boolean onRockettesHistoryPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes History Page URL to be tested is: "+this.URL+" ******************* ");
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
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
			/** 1. Verify that you are on Rockettes History Page.
			 * a. Read History Page Title and style
			 */
			System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-1: Are we on Rockettes History Page?");		

			
			WebElement myHelpfulTips = null;
			
			String[] mySelector = this.selectors.get("btnHistoryTitle");
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
			
			if (myHelpfulTips.getText().toString().equalsIgnoreCase("THE HISTORY OF THE RADIO CITY ROCKETTES"))
			{
				System.out.println(this.myDriverParameters+" - Rockettes History Page: We are on Rockettes -> History Page.");	
				isMyTestPassed = true;
			}	
			else
			{
				System.out.println(this.myDriverParameters+" - Rockettes History Page: Error: We are not on Rockettes -> History Page!");	
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
	
	@Test(description="Is slick Slider Working?", groups = { "rocketteshistorypage", "smoke", "fullintegration" })
	public boolean isSlickSliderWorking() throws InterruptedException
	{
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-2: Is slick Slider Working?");	
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		WebElement myHistoryFirstImage = null;
		
		String[] mySelector = this.selectors.get("btnHistorySlickSliderParent");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		myHistoryFirstImage = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		myHistoryFirstImage = driver.findElement(By.xpath(mySelector[0]));
		}
	    
	    jse.executeScript("arguments[0].scrollIntoView();", myHistoryFirstImage);
	    
	    List<WebElement> myHistoryImages= null;
		String[] mySelector1 = this.selectors.get("btnHistorySlickSliderChild1");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myHistoryImages = driver.findElements(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myHistoryImages = driver.findElements(By.xpath(mySelector1[0]));
		}
		
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Images Forward navigation will be verified now for "+myHistoryImages.size()+" images.");
		
		if (myHistoryImages.size() > 0) 
			{
			
			WebElement myLeftArrowSlickSlider = null;
			
			String[] mySelector3 = this.selectors.get("btnHistorySlickSliderChild2");
			if (mySelector3[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector3[0])));
			myLeftArrowSlickSlider = driver.findElement(By.cssSelector(mySelector3[0]));
			}
			if (mySelector3[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector3[0])));
			myLeftArrowSlickSlider = driver.findElement(By.xpath(mySelector3[0]));
			}
			
			WebElement myRightArrowSlickSlider = null;
			String[] mySelector4 = this.selectors.get("btnHistorySlickSliderChild3");
			if (mySelector4[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector4[0])));
			myRightArrowSlickSlider = driver.findElement(By.cssSelector(mySelector4[0]));
			}
			if (mySelector4[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector4[0])));
			myRightArrowSlickSlider = driver.findElement(By.xpath(mySelector4[0]));
			}		
	
			// forward Click Check
			for(int count=1;count < myHistoryImages.size();count++)
				{
				myRightArrowSlickSlider.click();
				Thread.sleep(1000);
				
				try {
					if(RockettesReusableFunctionalities.verifyIfLinkIsWorking(myHistoryImages.get(count).getAttribute("src").toString()) !=404)
					{
						System.out.println(this.myDriverParameters+" - Rockettes History Page: Image("+count+") is present at (link validated): "+myHistoryImages.get(count).getAttribute("src").toString());
					}
					else
					{
						System.out.println(this.myDriverParameters+" - Rockettes History Page: Image("+count+") is missing at (invalid link): "+myHistoryImages.get(count).getAttribute("src").toString());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Thread.sleep(1000);
				}
	
			// Reverse Click Check
			System.out.println(this.myDriverParameters+" - Rockettes History Page: Images Backward navigation will be verified now for "+myHistoryImages.size()+" images.");

			for(int count=1;count < myHistoryImages.size();count++)
				{
				myLeftArrowSlickSlider.click();
				Thread.sleep(1000);
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

	@Test(description="Is history displayed for selected Years?", groups = { "rocketteshistorypage", "smoke", "fullintegration" })
	public boolean isHistoryDisplayedForSelectedYears()
	{
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-3: Is history displayed for selected Years?");			
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			List<WebElement> myHistoryAccordions= null;
			String[] mySelector1 = this.selectors.get("btnHistoryDisplayYearsParent");
			if (mySelector1[1].equals("CSS"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
			myHistoryAccordions = driver.findElements(By.cssSelector(mySelector1[0]));
			}
			if (mySelector1[1].equals("Xpath"))
			{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
			myHistoryAccordions = driver.findElements(By.xpath(mySelector1[0]));
			}
			
			List<WebElement> myHistoryAccordionsBodyText= null;
				String[] mySelector2 = this.selectors.get("btnHistoryDisplayYearsChild");
				if (mySelector2[1].equals("CSS"))
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
				myHistoryAccordionsBodyText = driver.findElements(By.cssSelector(mySelector2[0]));
				}
				if (mySelector2[1].equals("Xpath"))
				{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
				myHistoryAccordionsBodyText = driver.findElements(By.xpath(mySelector2[0]));
				}
			
				/**
				 * First Expand all Accordions.
				 */
			if ( myHistoryAccordions.size() > 0 )
			{
				System.out.println(this.myDriverParameters+" - Rockettes History Page: Total "+myHistoryAccordions.size()+" Accordions are present on Rockettes -> History Page.");
				System.out.println(this.myDriverParameters+" - Rockettes History Page: Performing Open/close for all "+myHistoryAccordions.size()+" Accordions now.");
				int count = 0;
				
				for (WebElement myHistoryAccordion: myHistoryAccordions)
				{		    
					myHistoryAccordion.click();
					Thread.sleep(1000);
					if (myHistoryAccordionsBodyText.get(count).getText().toString() !=null)
					{
						if (myHistoryAccordionsBodyText.get(count).getText().toString().substring(0, 12)!=null)
						{
							System.out.println(this.myDriverParameters+" - Rockettes History Page: History-Accordion "+count+" Partial Text is: "+myHistoryAccordionsBodyText.get(count).getText().toString().substring(0, 12));
						}
						else
						{
							System.out.println(this.myDriverParameters+" - Rockettes History Page: History-Accordion "+count+" Text is: "+myHistoryAccordionsBodyText.get(count).getText().toString());
						}
						
/*						myHistoryAccordion.click();
						Thread.sleep(1000);
*/						count++;
						JavascriptExecutor jse = (JavascriptExecutor) driver;
						jse.executeScript("arguments[0].scrollIntoView();", myHistoryAccordion);
					}	
				}
				
				/**
				 * Now perform Close on all Accordions - Closing is causing Bug in Saucelabs.
				 */				
/*				for (WebElement myHistoryAccordion: myHistoryAccordions)
				{		    
					myHistoryAccordion.click();
					Thread.sleep(1000);
					JavascriptExecutor jse = (JavascriptExecutor) driver;
					jse.executeScript("arguments[0].scrollIntoView();", myHistoryAccordion);
				}*/
				
				isMyTestPassed = true;
			}
			else
			{
				System.out.println(this.myDriverParameters+" - Rockettes History Page: Accordions are missing on Rockettes -> History Page!");
				isMyTestPassed = false;
			}
			
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}

	@Test(description="Is Related Contents Section displayed?", groups = { "rocketteshistorypage", "smoke", "fullintegration" })
	public boolean isRelatedContentSectionPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-4: Is Related Contents Section displayed?");		
		return isMyTestPassed = RockettesReusableFunctionalities.isRelatedContentSectionPresent(driver);
		}

	@Test(description="Is Partner Section displayed?", groups = { "rocketteshistorypage", "smoke", "fullintegration" })
	public boolean isPartnersPresent()
		{
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-5: Is Partner Section displayed?");
		return isMyTestPassed = RockettesReusableFunctionalities.isPartnersPresent(driver);
		}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes History Page: Test Case-6. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}