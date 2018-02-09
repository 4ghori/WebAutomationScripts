package com.msg.qa.MSGOnlineDigital.Rockettes;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RockettesCSEmailSignUp
{
	WebDriver driver = null;
	String URL;
	Boolean isMyTestPassed;
	Map<String, String[]> selectors = new HashMap<String, String[]>();
	Boolean isMobile = false;
	String myDriverParameters = null;
	
	public RockettesCSEmailSignUp(WebDriver driver, String URL, boolean isMyTestPassed, Map<String, String[]> selectors, boolean isMobile, String driverParameters)
	{
		this.driver = driver;
		this.URL = URL;
		this.myDriverParameters = driverParameters;
		this.isMyTestPassed = isMyTestPassed;
		this.selectors = selectors;
		this.isMobile = isMobile;
	}
	
	@Test(description="Are we on Christmas Spectacular Email Sign-up Page?", groups = { "cs", "rockettescssignuppage", "smoke", "fullintegration" })
	public boolean onRockettesCSEmailSignUpPage()
	{
		try
		{
		// Verify the Title of the page.
		System.out.println("******************* Rockettes Christmas Spectacular EMail Signup Page URL to be tested is: "+this.URL+" ******************* ");
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
		
		/** 1. Verify that you are on Christmas Sign Up Page.
		 * 
		 */
		
		System.out.println(this.myDriverParameters+" - Rockettes CS Email Signup Page: Test Case-1: Are we on Christmas Spectacular Email Signup Page?");

		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		WebElement myHelpfulTips = null;
		
		String[] mySelector = this.selectors.get("btnCSEmailSignUpTitle");
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
		
		if (myHelpfulTips.getText().toString().equalsIgnoreCase("SIGN UP"))
		{
			System.out.println(this.myDriverParameters+" - We are on Rockettes -> Christmas -> Email Sign Up Page.");	
			isMyTestPassed = true;
		}	
		else
		{
			System.out.println(this.myDriverParameters+" - Error: We are not on Rockettes -> Christmas -> Email Sign Up Page!");	
			isMyTestPassed = false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+e.getMessage());
		}
		return isMyTestPassed;
	}
	
	@Test(description="Can we submit Email Sign Up Form?", groups = { "cs", "rockettescssignuppage", "smoke", "fullintegration" })
	public boolean isEmailSigUpSubmittingDetails() throws InterruptedException
	{ 
		System.out.println(this.myDriverParameters+" - Rockettes CS Email Signup Page: Test Case-2: Can we submit Email Sign Up Form?");

		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		WebElement MyFirstName = null;
		String[] mySelector = this.selectors.get("btnCSEmailSignUpFormParent");
		if (mySelector[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector[0])));
		MyFirstName = driver.findElement(By.cssSelector(mySelector[0]));
		}
		if (mySelector[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector[0])));
		MyFirstName = driver.findElement(By.xpath(mySelector[0]));
		}
	    jse.executeScript("arguments[0].scrollIntoView();", MyFirstName);
		MyFirstName.sendKeys("TestUserFirstName");

		WebElement myLastName = null;
		String[] mySelector1 = this.selectors.get("btnCSEmailSignUpFormChild1");
		if (mySelector1[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector1[0])));
		myLastName = driver.findElement(By.cssSelector(mySelector1[0]));
		}
		if (mySelector1[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector1[0])));
		myLastName = driver.findElement(By.xpath(mySelector1[0]));
		}
	    jse.executeScript("arguments[0].scrollIntoView();", myLastName);
		myLastName.sendKeys("TestUserLastName");
		
		WebElement myEMail = null;
		String[] mySelector2 = this.selectors.get("btnCSEmailSignUpFormChild2");
		if (mySelector2[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector2[0])));
		myEMail = driver.findElement(By.cssSelector(mySelector2[0]));
		}
		if (mySelector2[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector2[0])));
		myEMail = driver.findElement(By.xpath(mySelector2[0]));
		}
	    jse.executeScript("arguments[0].scrollIntoView();", myEMail);
	    myEMail.sendKeys("MSGTesting@msg.com");
		
		WebElement myZipCode = null;
		String[] mySelector3 = this.selectors.get("btnCSEmailSignUpFormChild3");
		if (mySelector3[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector3[0])));
		myZipCode = driver.findElement(By.cssSelector(mySelector3[0]));
		}
		if (mySelector3[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector3[0])));
		myZipCode = driver.findElement(By.xpath(mySelector3[0]));
		}
	    jse.executeScript("arguments[0].scrollIntoView();", myZipCode);
		myZipCode.sendKeys("10001");		
		
		WebElement mySignUpSubmitbtn = null;
		String[] mySelector4 = this.selectors.get("btnCSEmailSignUpFormChild4");
		if (mySelector4[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector4[0])));
		mySignUpSubmitbtn = driver.findElement(By.cssSelector(mySelector4[0]));
		}
		if (mySelector4[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector4[0])));
		mySignUpSubmitbtn = driver.findElement(By.xpath(mySelector4[0]));
		}
	    mySignUpSubmitbtn.click();	
		
		Thread.sleep(2000);
		
		WebElement myThankYouText = null;
		String[] mySelector5 = this.selectors.get("btnCSEmailSignUpFormChild5");
		if (mySelector3[1].equals("CSS"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(mySelector5[0])));
		myThankYouText = driver.findElement(By.cssSelector(mySelector5[0]));
		}
		if (mySelector5[1].equals("Xpath"))
		{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mySelector5[0])));
		myThankYouText = driver.findElement(By.xpath(mySelector5[0]));
		}

		if ( myThankYouText.getText().length() > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
		} catch (WebDriverException e) {
			// e.printStackTrace();
			isMyTestPassed = false;
			System.out.println(this.myDriverParameters+ e.getMessage());
		}
		return isMyTestPassed;
	}
	
	public boolean executeVisualTest()
	{
		System.out.println(this.myDriverParameters+" - Rockettes CS Email Signup Page: Test Case-3. Executing Visual Test.");
		return RockettesReusableFunctionalities.executeVisualTest(driver);
	}
}