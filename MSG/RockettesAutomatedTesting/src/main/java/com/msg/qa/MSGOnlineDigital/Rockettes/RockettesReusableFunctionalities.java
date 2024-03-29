package com.msg.qa.MSGOnlineDigital.Rockettes;

import java.util.List;

import org.apache.commons.validator.EmailValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import com.sendgrid.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FixedCutProvider;
import com.applitools.eyes.MatchLevel;
//import com.applitools.eyes.NewTestException;
import com.applitools.eyes.RectangleSize;
//import com.applitools.eyes.TestFailedException;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.exceptions.NewTestException;
import com.applitools.eyes.exceptions.TestFailedException;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.support.FindBy;


public class RockettesReusableFunctionalities extends RockettesGlobalParameters
{

	public RockettesReusableFunctionalities(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[contains(@class, 'xmas-sponsors')]")
    public ExtendedWebElement eWEmyPartnerText;

	public static boolean isPartnersPresent(WebDriver driver)
	{
		try
		{

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement myPartnerText = driver.findElement(By.xpath("//div[contains(@class, 'sponsors')]"));
		jse.executeScript("arguments[0].scrollIntoView();", myPartnerText);
//	    myPartnerText.click();


		WebDriverWait wait = new WebDriverWait(driver, 30);
//	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@style, 'text-align')][contains(@class, 'sponsor-logos')]/p/a")));
//	    List<WebElement> myPartners = driver.findElements(By.xpath("//div[contains(@style, 'text-align')][contains(@class, 'sponsor-logos')]/p/a"));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'sponsors')]/a")));
	    List<WebElement> myPartners = driver.findElements(By.xpath("//div[contains(@class, 'sponsors')]/a"));
	  	  
		if (myPartners.size()>0)
		{
			System.out.println("We have total "+ myPartners.size() + " Partners in the Footer of the Page!");
			System.out.println("Partners are: ");
				
			for (WebElement myElem:myPartners)
			{
				System.out.print(myElem.getText().toString()+"   ");
			}
			System.out.println("");
			return true;
		}
		else
		{
			System.out.println("Partners are missing in the Footer of the Page!");
			return false;
		}
		} catch (WebDriverException e) {
			System.out.println("Partners are missing in the Footer of the Page!");
			return false;
		}
	}
	
	public static boolean isSponsorsPresent(WebDriver driver)
	{
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='hidden-xs']>p>[class*='sponsor-logos']")));
		List<WebElement> mySponsors = driver.findElements(By.cssSelector("[class='hidden-xs']>p>[class*='sponsor-logos']"));
		
		if (mySponsors.size()>0)
		{
			System.out.println("We have total "+ mySponsors.size() + " Sponsors in the Footer of the Page!");
			System.out.println("Sponsors are: ");
			
			for (WebElement myElem:mySponsors)
			{
				System.out.print(myElem.getAttribute("title").toString()+"   ");
			}
			System.out.println("");
			return true;
		}
		else
		{
			System.out.println("Sponsors are missing in the Footer of the Page!");
			return false;
		}
		} catch (WebDriverException e) {
			System.out.println("Sponsors are missing in the Footer of the Page!");
			return false;
		}
	}

	public static boolean isRelatedContentSectionPresent(WebDriver driver)
	{
		try
		{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println("Made it BEFORE myRelatedContentsText");
		WebElement myRelatedContentsText = driver.findElement(By.cssSelector("[class='container related-content-container']>h3"));
		System.out.println("Made it AFTER myRelatedContentsText");
	    jse.executeScript("arguments[0].scrollIntoView();", myRelatedContentsText);
	    
		WebDriverWait wait = new WebDriverWait(driver, 30);
		System.out.println("Made it BEFORE class equals row related content");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='row related-content']")));
		List<WebElement> myRealtedContents = driver.findElements(By.cssSelector("[class='row related-content']"));
		System.out.println("Made it AFTER class equals row related content");
		
		if (myRealtedContents.size()>0)
		{
			System.out.println("We have total "+ myRealtedContents.size() + " Related Contents on the Page!");
			return true;
		}	
		else
		{
			System.out.println("Related Contents is missing on the Page!");
			return false;
		}
		} catch (WebDriverException e) {
			System.out.println("Related Contents is missing on the Page!");
			return false;
		}
	}

	/**
	 * Caution when you enable the POM dependencies for Sendgrid so in turn it utilizes the Jackson databind which may cconflict with JIRA REST core dep. or with appliTools.
	 * @param urlToVerify
	 * @return
	 * @throws Exception
	 */
	/*public static void SendMailViaSendGrid(String EmailBody) throws IOException {
	 Email from = new Email("MSGTesting@msg.com");
	 String subject = "Jenkins Error - Rockettes Regression Script is failed!";
	 Email to = new Email("rachitkumar.rastogi@msg.com");
	 Content content = new Content("text/plain", "Hi, \r\nThis is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"\r\nThanks, Your MSG QA Automation Team.");
	 Mail mail = new Mail(from, subject, to, content);

	 SendGrid sg = new SendGrid("SENDGRID_API_KEY");
	 Request request = new Request();
	 try {
	   request.method = Method.POST;
	   request.endpoint = "mail/send";
	   request.body = mail.build();
	   Response response = sg.api(request);
	   System.out.println(response.statusCode);
	 } catch (IOException ex) {
	   throw ex;
	 }
	}*/
		
	public static Integer verifyIfLinkIsWorking(String urlToVerify) throws Exception
	{
			URL url = new URL(urlToVerify);
			HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
			try	 
			{	 
				myConnection.connect(); 
			    Integer myResponse = myConnection.getResponseCode();      	 
			    myConnection.disconnect();	 
			    return myResponse;	 
			}	 
			catch(Exception exp)	 
			{	 
				System.out.println("URL: "+url+" Can't be rsolved");
				return 404;
			}					 
		}	
	
	@SuppressWarnings("deprecation")
	public static boolean verifyEmailAddress(String EmailAddress)
	{
		return EmailValidator.getInstance().isValid(EmailAddress);	
	}
	
	/**
	 * Method for Running the Visual tests, if you need to run with your user just replace the API Key below.
	 * @author Rachit Kumar Rastogi
	 * @param driver
	 * @return
	 */
	//private static BatchInfo batch; 
	
	public static boolean executeVisualTest(WebDriver driver)
	{
		String myDriverParameters = "Platform: "+((RemoteWebDriver) driver).getCapabilities().getPlatform().toString() +" "+ ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString() + " Version: " +((RemoteWebDriver) driver).getCapabilities().getVersion();
		
		BatchInfo batchInfo = new BatchInfo(System.getenv("APPLITOOLS_BATCH_NAME"));
		String batchId = System.getenv("APPLITOOLS_BATCH_ID");
		if (batchId != null) {
		    batchInfo.setId(batchId);
		}
		Eyes eyes = new Eyes();
		/**
		 * Replace the API key if you would like to run tests with your user.
		 */
	    eyes.setApiKey("DJZskprruP4VgGih6dp4ilrLk0MDoVDItxuZpBXmIUA110");
	    eyes.setForceFullPageScreenshot(true);
	    eyes.setHideScrollbars(true);
	    eyes.setStitchMode(StitchMode.CSS);
		eyes.setBatch(batchInfo);
		eyes.setMatchLevel(MatchLevel.LAYOUT2);
		
		try{  
            driver.get(driver.getCurrentUrl());
            System.out.println(myDriverParameters+": Applitools Visual Test - Rockettes page is: "+driver.getTitle()+" and URL is: "+driver.getCurrentUrl());
            /**
             * Create the Regions based upon the viewport size and desktop/mobile.
             * Change done by Rachit Kumar Rastogi on 10/11/2017.
             */
           
            /**
             * Handle iPhone viewport
             */
            if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*iphone.*"))
            {
            	eyes.setImageCut(new FixedCutProvider(95,135,0,0));
            	eyes.open(driver, "Rockettes", "Mobile (iPhone) - "+driver.getTitle());
            }
            
            /**
             * Handle android viewport
             */
            if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*android.*"))
            {
            	eyes.open(driver, "Rockettes", "Mobile (Android) - "+driver.getTitle());           
            }
            /**
             * Handle iPad Viewport
             */
            else if(((RemoteWebDriver) driver).getCapabilities().getPlatform().toString().matches("(?i).*ipad.*"))
            {
            	/**
            	 * Put FixedCutProvider for cutting the iPad boundaries extra space in AppliTools.
            	 */
            	eyes.open(driver, "Rockettes", "Mobile (iPad) - "+driver.getTitle());
            }
            
            /**
             * Handle desktop Viewport
             */
            else
            {
            	eyes.open(driver, "Rockettes", "Desktop - "+driver.getTitle(), new RectangleSize(1416,1200));	
            }
            eyes.checkWindow();
            eyes.close();
  	      }catch(NewTestException e)
  	      {
  	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Let's store Baseline version for Rockettes!!");
  	    	 	return false;
  	      }
  	      catch(UnsupportedCommandException e1)
  	      {
	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Unsupported command Exception we can't continue!!");
	    	 	return false;
  	      }
  	      catch(TestFailedException e2)
  	      {
	    	 	System.out.println(myDriverParameters+": Applitools Visual Test - Test failed Exception we can't continue!!");
	    	 	return false;
  	      }
		return true;
	}
}