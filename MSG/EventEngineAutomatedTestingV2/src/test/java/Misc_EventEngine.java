import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class Misc_EventEngine {

	public static final String EventEngine_QA_URL = "http://qa-api.msg.com/login/";

	public static void main(String[] args) 
	{
		WebDriver driver;
		 final String USERNAME = "MDRasul";
		 final String ACCESS_KEY = "1df585b2-88bc-4578-8a98-c920e9eaf383";
		 final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

		
		
		String myScreenResolutionCapability = "screenresolution";
		DesiredCapabilities cap=null;
		 //cap = DesiredCapabilities.chrome();
		 cap = DesiredCapabilities.htmlUnit();
		 cap.setCapability("name", "Debug Test...");
		 cap.setBrowserName("chrome");
		 cap.setCapability(myScreenResolutionCapability,"1600x1200");    			
		 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);

		 try {
			 	//Original Code 
				//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				
				//Sauce Code 
			    driver = new RemoteWebDriver(new URL(URL), cap);
			    
	    			driver.get(EventEngine_QA_URL); //Loading the URL in Browser

	    			//======>> Login To Event Engine Admin UI 
	    				String sUsername = "admin@msg.com";
	    				String sPassword = "admin123";
	    			
	    				WebElement email=driver.findElement(By.xpath("//div/input[@name='email']"));
	    				WebElement password=driver.findElement(By.xpath("//div/input[@name='password']"));
	    				WebElement submit=driver.findElement(By.xpath("//button[@type='submit']"));

	    				email.sendKeys(sUsername);
	    				password.sendKeys(sPassword);
	    				submit.click();
	    			
	    		
	    		  //======>> Common Side Navigation Bar 
	    				
	    				WebElement sideNavigationBar=driver.findElement(By.xpath("//a[@href='/events']"));
	    				if(sideNavigationBar.isDisplayed())System.out.println("Side Bar Found");
	    				
		
	    				
	    			
		    		//driver.get("https://qa.mymsgsuites.com/#admin"); //Loading the URL in Browser
				//driver.get("https://www.mailinator.com/"); //Loading the URL in Browser
			    

			} catch (Exception e) {
				e.printStackTrace();
			}

	}

}
