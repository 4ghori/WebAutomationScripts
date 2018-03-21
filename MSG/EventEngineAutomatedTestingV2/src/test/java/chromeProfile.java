import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class chromeProfile {

	
	public static WebDriver driver;

	
	public static void main(String[] args) 
	{
		
		
		
		
		ProfilesIni allprof = new ProfilesIni();
		FirefoxProfile prof = allprof.getProfile("default");
		driver = new FirefoxDriver(prof);
		driver.get("https://www.msgmarqueeathome.com/");

		
		
	/*	
		//System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("user-data-dir=C:/Users/user_name/AppData/Local/Google/Chrome/User Data");
		//options.addArguments("user-data-dir=/Users/rasulm/Library/Application Support/Google/Chrome/Default");
		options.addArguments("user-data-dir=/Users/rasulm/Library/Application Support/Google/Chrome/Profile 1");

		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("https://www.msgmarqueeathome.com/");
		driver.quit();*/
	
	}

}
