import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.msg.suites.pom.util.FBConstants;
import com.relevantcodes.extentreports.LogStatus;


//public class MISCTest extends BasePage
public class MISCTest {
	
	public static WebDriver driver;
	static String myScreenResolutionCapability = "screenresolution";
	
	 public static final String USERNAME = "MDRasul";
	 public static final String ACCESS_KEY = "1df585b2-88bc-4578-8a98-c920e9eaf383";
	 public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	 
	 
	 public static String returnAbnormaltoNormalDate(String abnormalDateString) throws ParseException
	{
		String trDate=abnormalDateString;    
		Date tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(trDate);
		String krwtrDate = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(tradeDate);
		return 	krwtrDate;
	}
 
	
	 
	 
	 
	public static void main(String[] args) throws ParseException 
	{
	


		
		String a = "Welcome to Netflix! We are a subscription service that provides our members with access to motion pictures, television and other audio-visual entertainment (\"Netflix content\") streamed over the Internet to certain Internet-connected TV's, computers and other devices (\"Netflix ready devices\")."; 
				
		String b = "MY LOCKER ";
		
		
		
		if(a.contains("Welcome to "))
		{
			System.out.println("true");
		}
		
		
		System.out.println(StringUtils.getLevenshteinDistance(a, b));		
		
		
		
		String tmp = "SHAdmin [User 1] and then [Email 6] and nore";
		ArrayList<String> aRepl  = new ArrayList<String>();

		if (tmp.toString().indexOf("[") + 1 != 0)
		{
			String[] aRef = tmp.split("\\[");
			
			for (int i = 0; i <= aRef.length-1; i++)
			{
				if (aRef[i].indexOf("]") + 1 != 0)
				{
					aRepl.add(aRef[i].split("]", -1)[0]);
					//System.out.println(aRepl.get(0));
				}

			}
		}
		
		for (int i = 0; i <= aRepl.size()-1; i++)
		{
			System.out.println(aRepl.get(i));
			
			String sInterpreted = "xxxxxxxx";
			
			tmp = tmp.toString().replace("[" + aRepl.get(i) + "]", sInterpreted);

		}

		
		
		
		
		
	//Regular Expression 	
		String test = "User 1";
		if(test.matches(".*User \\d"))
		{
			System.out.println("Matches");
		}

		
	 //generates random number
	    int min = 0;
	    int max = 9;
	    StringBuilder randoms = new StringBuilder();
	    Random random = new Random();
	    int num1 = random.nextInt(max-min+1)+min;
	    int num2 = random.nextInt(max-min+1)+min;
	    int num3 = random.nextInt(max-min+1)+min;
	    randoms.append(num1);
	    randoms.append(num2);
	    randoms.append(num3);
	    System.out.println("Fname"+ "Lname" + randoms + "@mailinator.com");
		
		//Random Email Address Very Big 
	    String email =  "random-" + UUID.randomUUID().toString() + "@example.com";
		
		
	    //Normalize Space Of a String 
		System.out.println(StringUtils.normalizeSpace("Visa    CARD 3"));
		
		
//==== >> Getting Todays date and comapre and keep flag that there is already a event for today.
		String CorredtEventDate = returnAbnormaltoNormalDate("20170111");
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Date d=new Date();
		System.out.println("Today : " + sdf.format(d));
		String today = sdf.format(d);
		
		String evtDay = sdf.format(sdf.parse(CorredtEventDate));
		
		Calendar c = Calendar.getInstance();

		if(today.equals(evtDay))
		{
			System.out.println("same day event found ..");
			c.setTime(sdf.parse(today));
		}

		
	//Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(today));
		c.add(Calendar.DATE, 1);  // number of days to add
		String nextDay = sdf.format(c.getTime());  // dt is now the new date
		System.out.println("Next Day : " + nextDay);
		
		
/*		
		
		CreateRandomUser();
		
		
		
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
		    	//driver.get("https://qa.mymsgsuites.com/#admin"); //Loading the URL in Browser
				driver.get("https://www.mailinator.com/"); //Loading the URL in Browser


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		WebDriver driver = new HtmlUnitDriver();
    	driver.get("https://www.mailinator.com/"); //Loading the URL in Browser
    	System.out.println(driver.getTitle());
    	
    	System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
		//driver= new ChromeDriver();
		//driver.get("https://www.mailinator.com/"); //Loading the URL in Browser
   
       	WebElement CheckAnyInboxTextFiled = driver.findElement(By.xpath(".//*[@id='inboxfield']"));
    	CheckAnyInboxTextFiled.sendKeys("crad17@mailinator.com");

    	WebElement PublicEmailButton = driver.findElement(By.xpath("html/body/section[1]/div/div[3]/div[2]/div[2]/div[1]/span/button"));
    	PublicEmailButton.click();
    	//WebElement MsgLink = driver.findElement(By.xpath("//*[contains(@id,'"+"crad13"+"')]/div[2]/div[5]/div"));
    	//WebElement MsgLink = driver.findElement(By.xpath("//*[contains(@id,'"+"crad13"+"')]/div[2]/div[5]/div"));
    	
    	WebElement MsgLink = driver.findElement(By.xpath("//*[contains(@id,'crad17')]/div[2]/div[5]/div"));

    	
    	

    	MsgLink.click();
    	//WebElement VeryAccountLink = driver.findElement(By.xpath("//*[@id='mail-container']/div/p[5]/a"));
    	//WebElement VeryAccountLink = driver.findElement(By.linkText("Verify Account & Set Password"));
    	//WebElement VeryAccountLink = driver.findElement(By.xpath("//a[contains(text(),'Verify Account & Set Password')]"));
    	driver.switchTo().frame("publicshowmaildivcontent");
    	
    	//Wait 5 Sec 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		//driver.findElement(By.xpath("//a[contains(text(),'Verify Account & Set Password')]")).click();
	String Link = 	driver.findElement(By.xpath("//a[contains(text(),'Verify Account & Set Password')]")).getAttribute("href");

    	driver.switchTo().defaultContent();


    	//VeryAccountLink.click();
    	String winHandleBefore = driver.getWindowHandle();
    	Set AllWindow = driver.getWindowHandles();
    	for(String winHandle : driver.getWindowHandles())
    	{
    	driver.switchTo().window(winHandle);
    	}
	
    	
		
		
		

		//***** jdbc:postgresql Database COnnection 
			Connection conn3 = null;
	
	        // Connect method #3
	        String dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
	        Properties parameters = new Properties();
	        parameters.put("user", "pgsqldev");
	        parameters.put("password", "pgsqldev#10");
	
	        try {
				conn3 = DriverManager.getConnection(dbURL3, parameters);
			    if (conn3 != null) 
			    {
			        System.out.println("Connected to database #" + dbURL3 + parameters);
			    }
				
				//Query The Database User Table And get the REset Token 
				Statement stmt = conn3.createStatement();
				ResultSet rs=stmt.executeQuery("select reset_password_token from users where email ='crad1@mailinator.com'");
				while (rs.next())
				{
	       		String Token = rs.getString(1);	
	                System. out.println("Token Is : " + Token);		
				}		
			    
			    String Input = "crad13@mailinator.com";
				Statement stmt = conn3.createStatement();
				String mapToken = "b1a7c67da3c4d1407baacd573a7f9727f573262ab2c5f2becbb3e9ff04507c4b";
				String Query = "update users SET reset_password_token='"+mapToken+"' where email ='"+Input.trim()+"'";
				ResultSet rs=stmt.executeQuery(Query);

	
				// closing DB Connection		
				conn3.close();			
				
			} 
	        catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        finally 
	        {
	            try 
	            {
	                if (conn3 != null && !conn3.isClosed()) 
	                {
	                    conn3.close();
	                }
	            } 
	            catch (SQLException ex) 
	            {
	                ex.printStackTrace();
	            }
	        } 
	    //*//*********************************************
	  	
		
		
        //String RowPrimaryKey = null; // The Distinct Row Where the Validation will Happen 

        //String[] test = Input.split("\\|");
        
        //if(Input.contains("|"))
        //{
        	//RowPrimaryKey = Input.split("|")[1].trim();
        //}
        //else
        //{
        	//System.out.println("This Validation Need 2 input Seperated by ( | ) so it can identify which Row to Point for validation ");
        //}

		
		
		
		//Fire Fox Driver Gecko_DRIVER_EXE
		//System.setProperty("webdriver.gecko.driver", FBConstants.Gecko_DRIVER_EXE);
	    //DesiredCapabilities capabilities=DesiredCapabilities.firefox();
	    //capabilities.setCapability("marionette", true);
		//driver= new FirefoxDriver();
		//driver.get("https://newuat.mymsgsuites.com/#admin");
		
		System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
		driver= new ChromeDriver();

		
		//*****>>  Redirecting And Refreshing Page 
			String Url = "https://qa.mymsgsuites.com/#admin";   // 
			driver.get(Url); //Loading the First URL  

			//driver.get("https://qa.mymsgsuites.com/#reset?token=moiJvZ7trsi9zzxALWt-#admin"); //Loading the URL in Browser
			String redirected_url = "https://qa.mymsgsuites.com/#reset?token=moiJvZ7trsi9zzxALWt-#admin";
			driver.navigate().to(redirected_url); // Re RE-Directing to 2nd URL while Driver is Open 
			driver.navigate().refresh(); // Referesh The Page to COmplete the REdirection 
		//*//*********************************************


		//***** Loading Driver then Closing and Loading again.... 
			String Url = "https://qa.mymsgsuites.com/#admin";   // 
			driver.get(Url); //Loading the First URL  
			driver.close();
		
			//==> 2nd Time Loading the driver 
			System.setProperty("webdriver.chrome.driver", FBConstants.CHROME_DRIVER_EXE);
			driver= new ChromeDriver();
			String redirected_url = "https://qa.mymsgsuites.com/#reset?token=moiJvZ7trsi9zzxALWt-#admin";
			driver.get(redirected_url); //Loading the First URL  

		//*//*********************************************
		
		
		*/
		
		
		admin_login();
		ClickTab("//*[@id='adminEventsTab']");

		//SuiteAdminCreateAnOrder();

		//SelectDropDownList();
		//SelectListFromListBox();
		//VeryfyTextBold();

		//user_login();
		//clickASuiteEvents();
		//ImageChecking();
		
		
		
			//Right Input
				//String Input = "2017-04-04 | KNICKS VS TORONTO PLAYOFFS RD 2";
		
			//Wrong Input Seperator Not Given
				//String Input = "2017-04-04";

			//Wrong Input Seperator Given But No 2nd Value 
				//String Input = "2017-04-04 | ";

			//Wrong Input Seperator Given But No 2nd Value 
				String Input = "2017-04-04 | Nme Of Event | 3rd Input";
		
			
			
			ValidateTableCellText_Bold(Input);

			
			
		System.out.println("End Of Main Function..");	
		System.out.println("End Of Main Function..");	

			
	}

	
	
	
	
	
	
	//********************** Debug Functions *********** 
	
	
	public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */)
	{
		final StringBuilder sb = new StringBuilder(
            input.length() /* also inspired by seh's comment */);
    for(int i = 0; i < input.length(); i++)
    {
        final char c = input.charAt(i);
        if(c > 47 && c < 58){
            sb.append(c);
        }
    }
    return sb.toString();
}
	

	
	public static void CreateRandomUser()
	{
		
		//***** Connect To DataBase Query All User 
		Connection conn3 = null;
        String dbURL3 = "jdbc:postgresql://suitessite.cqp6htpq4zp6.us-east-1.rds.amazonaws.com:5432/suites_qa";
        Properties parameters = new Properties();
        parameters.put("user", "pgsqldev");
        parameters.put("password", "pgsqldev#10");
        try 
        {
			//==Connecting to Database 
	        	conn3 = DriverManager.getConnection(dbURL3, parameters);
			    if (conn3 != null) 
			    {
			        System.out.println("Connected to database #" + dbURL3 + parameters);
			    }
			
			//==Query The Database User Table And get the REset Token 
			    
				 Map<String, String> AllEmail = new HashMap<String, String>();
				 AllEmail.put("Email 1", "");
				 AllEmail.put("Email 2", "");
				 AllEmail.put("Email 3", "");
				 
			    
			    String Input = "crad%@mailinator.com";
			    Statement stmt = conn3.createStatement();
				ResultSet rs=stmt.executeQuery("select email from users where email LIKE'"+Input.trim()+"'");
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				while (rs.next())
				{
					String email = rs.getString(1);	
	                //System. out.println("Email Found : " + email);
	                System. out.println("Only NUmber From Email : " + stripNonDigits(email));
	                arrayList.add(Integer.parseInt(stripNonDigits(email)) );
				}
				
				
				
				
				System.out.println(Collections.max(arrayList));
				int MaxNumber = Collections.max(arrayList)+1;

				
				if(AllEmail.get("Email 1").equals(""))
				{
					AllEmail.put("Email 1", "SuiteQA"+MaxNumber+"@mailinator.com");
					System.out.println(AllEmail.get("Email 1"));					
				}

				if(AllEmail.get("Email 1").equals(""))
				{
					AllEmail.put("Email 1", "SuiteQA"+Collections.max(arrayList)+"@mailinator.com");
					System.out.println(AllEmail.get("Email 1"));					
				}
				else 
				{
					System.out.println("Email 1 Already FIll"  );
				}

				
				
				
			//== closing DB Connection		
				conn3.close();		

		} 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        finally 
        {
            try 
            {
                if (conn3 != null && !conn3.isClosed()) 
                {
                    conn3.close();
                }
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        } 
		
	}//Function End
	
	
	
	public static void ValidateTableCellText_Bold(String Input)
	{
		String ObjectLocatorXpathCssETC = "//*[@id='events-default-screen']/table";
		WebElement table_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
        List<WebElement> tr_collection = table_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/tbody/tr"));
        System.out.println("NUMBER OF ROWS IN THIS TABLE = "+tr_collection.size());
        String RowPrimaryKey = null; // The Distinct Row Where the Validation will Happen 
        if(Input.contains("|"))
        {
        	String[] temp = Input.split("\\|");
        	if(temp.length>1)
        	{
            	RowPrimaryKey = Input.split("\\|")[1].trim();
            	if(RowPrimaryKey.equals("") || RowPrimaryKey.equals(" ") || RowPrimaryKey.equals(null) )
            	{
            		System.out.println("Check Input Test Data it should be 2 value seperated with (|) Character  ");
            		System.out.println("Example [ Date | EventNAme ] ");
                	return;
            	}
            	
        	}
        	else 
        	{
        		System.out.println("Check Input Test Data it should be 2 value seperated with (|) Character  ");
        		System.out.println("Example [ Date | EventNAme ] ");
            	return;
        	}

        }
        else
        {
    		System.out.println("Check Input Test Data it should be 2 value seperated with (|) Character  ");
    		System.out.println("Example [ Date | EventNAme ] ");
        	return;
        	//System.out.println("This Validation Need 2 input Seperated by ( | ) so it can identify which Row to Point for validation ");
        }
        
TableRowLoop: for(WebElement trElement : tr_collection)
        {
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            String EachRowData = "";
            //for(WebElement tdElement : td_collection)
	        for(int i=1;i<td_collection.size();i++)
            {
                //System.out.println("row # "+row_num+", col # "+col_num+ " text="+tdElement.getText());
                System.out.print(td_collection.get(i).getText()+" ");
                EachRowData = td_collection.get(i).getText() + " ";
                if(EachRowData.contains(RowPrimaryKey))
                {
                	/**
                	 * Suite Events Table Structure 
                	 * Col1. GearIcon | Col2. Date | Col3. Event | Col4. Start Time |  
                	 * Col5. DoorsOpen| Col6. Reg. Cut off | Col7. Kosher Deadline | Col8. Orders
                	**/                              
                	WebElement DateCellElement = td_collection.get(2);  // get(2) because date is in Second Column

                	//ElementName.click();  // if you need to Click
                	//super.hardWait();
                	
                	String weight =  DateCellElement.getCssValue("font-weight");
                	if(weight.toUpperCase().equals("BOLD"))
                	{
    	            	//test.log(LogStatus.PASS, thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Bold" );
    	    			//Rtype= true;
            			//highlightElement(DateCellElement,"pass");
    	    			//matChFoundinTable=true;
						break TableRowLoop;
                	}
                	else
                	{
    	            	//test.log(LogStatus.FAIL, thisMethodName + " -> For Table ->  " + ObjectName +" -> Date Field for EventName : " + RowPrimaryKey + " -> Is Not Bold" );
            			//Rtype= false;
            			//highlightElement(DateCellElement,"fail");
    	    			//matChFoundinTable=true;
						break TableRowLoop;
                	}
                }
                else  // This is a Column Looping inside Each Table Row - Error Flag will Store here until it not match 
                {
	            	//matChFoundinTable= false;
	            	//errorFlag = thisMethodName + " - > EventName : " + RowPrimaryKey + " -> Is Not Found In  ["+ ObjectName +"]  Table";
	            	//errorFlag = errorFlag +  "Text" + "<br>"  //If you wants to multine Error Msg
                }

            }//Columns Loop End Here
	        
            System.out.println(""); // One Line Break After Each Rows 

        }//Row Loop End Here 


	}
	
	
	public void VeryfyTextBold()
	{
		//<<<< Very a Text Is BOLD Or NOt 

		ClickTab(".//*[@id='adminEventsTab']/span");
		ClickTab(".//*[@id='1053']/td[4]");

		//******* ##### Very Text Bold Debug Code  
		WebElement TargetObject = driver.findElement(By.xpath(".//*[@id='event-details-summary']/tbody/tr[1]/td[1]/span[2]"));
		System.out.println("Target Object Get Text " + TargetObject.getText());
		String weight =  TargetObject.getCssValue("font-weight");
		System.out.println("Expected Object Bold or Not : " + weight);
		
	}
	
	
	public void SelectDropDownList()
	{
		//Select dropdown = new Select(driver.findElement(By.id("eventsList-months")));
		//dropdown.selectByVisibleText("Programmer ");
	}
	
	
	public void SuiteAdminCreateAnOrder()
	{
//      //===========Creates Orders 		
//		ClickTab(".//*[@id='orders-default-actionbar']/ul/li");
//		Select select = new Select(driver.findElement(By.xpath(".//*[@id='createOrder-companySelect']")));
//		select.selectByVisibleText("WABC TV");			
//
//		Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='createOrder-suiteSelect']")));
//		select2.selectByVisibleText("ELS-2A");			
//
//		Select select3 = new Select(driver.findElement(By.xpath(".//*[@id='createOrder-userSelect']")));
//		select3.selectByVisibleText("QA1 USER1");			
//	
//		Select select4 = new Select(driver.findElement(By.xpath(".//*[@id='createOrder-menuTypeSelect']")));
//		select4.selectByVisibleText("Non Kosher");			
//
//		
//		//*[@id="createorder"]/div[9]
//        //List<WebElement> list_collection = driver.findElements(By.xpath(".//*[@id='createorder']/div[9]/li"));
//
//		//WebElement liste_element = driver.findElement(By.xpath(""));
//        //List<WebElement> list_collection = driver.findElements(By.xpath(".//*[@id='createorder']/div[9]/ul[@class='scrollbox-list']/li"));
//		//System.out.println("Lise OF Event List : " + list_collection.size());
//
//		String ObjectLocatorXpathCssETC = ".//*[@id='createorder']/div[9]/ul[@class='scrollbox-list']";
//		WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
//        List<WebElement> list_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
//		System.out.println("Lise OF Event List : " + list_collection.size());
//

	}

	public void clickASuiteEvents()
	{
		
		
		//**** ##### Select An event From All Event List and Verify its  Secondary Text Section 

		String ObjectLocatorXpathCssETC1 = ".//div[@id='eventsList']";
        List<WebElement> Event_collection = driver.findElements(By.xpath(ObjectLocatorXpathCssETC1+"/client-event/div"));
        System.out.println("Total Event Tile Found in page  : " + Event_collection.size());

        
        
		 //===Input Breaking 
				String EventNameAndTexttoVerify="";
				String EventName="";
				String TexttoVerify="";
				String testString = "For Event Name : RANGERS PLAYOFF ROUND 1 2017 Game 3\nText To Verify : May 14\nSunday\n8:00 PM"; 
				//String testString = "For Event Name : RANGERS PLAYOFF ROUND 1 2017 Game 3\nText To Verify : May 14\nSunday\n8:00 PM"; 
		
				System.out.println("Event Name & text to verify : " +testString);
				String[] temp = testString.split("\n",2);
				//System.out.println("Size Of User URL,ID PASword Inpt Splited Array:  " + temp.length);
				for(int i=0;i<temp.length;i++)
				{
					System.out.println(temp[i].split(":")[1]);
					EventNameAndTexttoVerify = EventNameAndTexttoVerify + temp[i].split(":")[1] + ":";
				}
				EventName = EventNameAndTexttoVerify.split(":")[0].trim();
				TexttoVerify = EventNameAndTexttoVerify.split(":")[1].trim();
		//=============== Input Breaking End ==============

		StringUtils sutil = new StringUtils();
		for(int i=0;i<Event_collection.size();i++)	
		{
			//System.out.println(Event_collection.get(i).getText());
			
			if(sutil.containsIgnoreCase(Event_collection.get(i).getText(),EventName))
			{
				Event_collection.get(i).click();
				fwait();

				
				//Print Primary Information 
					System.out.println("Events Primary Information");
					System.out.println("==============================");
					WebElement EventPrimaryElelemnt = driver.findElement(By.xpath(ObjectLocatorXpathCssETC1+"/client-event["+(i+1)+"]/div/section[1]"));
					System.out.println(EventPrimaryElelemnt.getText());

				//Print Secondary Information 
					System.out.println("\nEvents Secondary Information");
					System.out.println("==============================");
					WebElement EventSecendoryElelemnt = driver.findElement(By.xpath(ObjectLocatorXpathCssETC1+"/client-event["+(i+1)+"]/div/section[2]"));
					System.out.println(EventSecendoryElelemnt.getText());
					
					if(sutil.containsIgnoreCase(EventSecendoryElelemnt.getText(), TexttoVerify))
					{
						System.out.println("Pased");
					}

					
					
			       
					JavascriptExecutor js = (JavascriptExecutor) driver;
			        js.executeScript("arguments[0].style.backgroundColor = '"+"lime"+"'",  EventSecendoryElelemnt);
		            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", EventSecendoryElelemnt, "color: yellow; border: 0px solid yellow;");

					//js.executeScript("arguments[0].setAttribute('style', arguments[1]);",EventSecendoryElelemnt, "color: Red;");
		            //js.executeScript("arguments[0].style.border='1px groove green'", EventSecendoryElelemnt);


					
		            //js.executeScript("arguments[0].style.border='2px groove green'", EventSecendoryElelemnt);

			}
		}

	}
	
	
	public void SelectListFromListBox()
	{
//		//**** ##### Select A List From a List Box 
//		String ObjectLocatorXpathCssETC = "//*[@id='eventsList-months']";
//		WebElement liste_element = driver.findElement(By.xpath(ObjectLocatorXpathCssETC));
//        List<WebElement> list_collection = liste_element.findElements(By.xpath(ObjectLocatorXpathCssETC+"/li"));
//		//List <WebElement> we = driver.findElements(By.xpath(".//*[@id='eventsList-months']/li"));
//		System.out.println("Lise OF MOnth List : " + list_collection.size());
//
//		for(int i=0;i<list_collection.size();i++)	
//		{
//			System.out.println(list_collection.get(i).getText());
//			if(list_collection.get(i).getText().equals("APR"))
//			{
//				liste_element.findElement(By.xpath("//*[@id='eventsList-months']/li["+(i+1)+"]/span")).click();
//			}
//		}
	}
	
	
	public void ImageChecking()
	{
		

		//===== Check An Images 
		boolean matchfound = false;
		boolean imagemissing = false;

		String ErrorFlag = "";
		
        List<WebElement> Event_collection = driver.findElements(By.xpath(".//div[@id='eventsList']/client-event/div"));
        System.out.println("NUMBER OF Event Found = "+Event_collection.size());
		String ImageName = "NYK_70th_Anniversary_NUMERAL_PATCH_jufdp1";
		
		
		for(int i=0;i<Event_collection.size();i++)
		{
			System.out.println(Event_collection.get(i) +"\n");
			System.out.println("********************************");


			String imgxpath = ".//*[@id='eventsList']";
			//WebElement ImageFile = driver.findElement(By.xpath(imgxpath));
			WebElement EventImage = driver.findElement(By.xpath(imgxpath+"/client-event["+(i+1)+"]/div/img[contains(@ng-src,'"+ImageName+"')]"));
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", EventImage);
			if (ImagePresent)
		    {
		        System.out.println("Image displayed.");
		        matchfound = true;
		    }
		    else
		    {
				System.out.println("Image not displayed.");
				imagemissing = true;
				//ErrorFlag += "Image Missing For Event Number " + i + Event_collection.get(0).getText();
				ErrorFlag += "Image Missing For Event Number " + (i+1) + "\n";
		    }
			
		}
		
		if(imagemissing)
		{
			System.out.println(ErrorFlag);
		}
		

		
		//String ImgSourceString = ImageFile.getAttribute("src");
		//System.out.println("Image Source is : " + ImgSourceString );

	}
	
	
	// Suite App Login and Help Functions 

	public static void admin_login()
	{
		
	/*	String Url = "https://qa.mymsgsuites.com/#admin";
		String Username = "suitesqa1@gmail.com";
		String Password = "Suites123";
		driver.get(Url); //Loading the Direct URL From EXCEL FIle 
		WebElement email = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginUserID_Field));
		WebElement password = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginPassword_Field));
		WebElement loginButton = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginButton));
		email.sendKeys(Username);
		password.sendKeys(Password);
		//password.sendKeys(Keys.ENTER);  // This is can be used only 
		loginButton.click();
		fwait();*/
		//super.hardWait();
		
	}

	public static void user_login()
	{/*
		
		String Url = "https://qa.mymsgsuites.com/";
		String Username = "suitesqu1@gmail.com";
		String Password = "Suites123";
		driver.get(Url); //Loading the Direct URL From EXCEL FIle 
		WebElement email = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginUserID_Field));
		WebElement password = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginPassword_Field));
		WebElement loginButton = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginButton));
		email.sendKeys(Username);
		password.sendKeys(Password);
		//password.sendKeys(Keys.ENTER);  // This is can be used only 
		loginButton.click();
		fwait();
		//super.hardWait();
		
	*/}

	public static void UATuser_login()
	{
		
		/*String Url = "https://newuat.mymsgsuites.com/";
		String Username = "msg_uat.sha.15@mailinator.com";
		String Password = "test@1234";
		driver.get(Url); //Loading the Direct URL From EXCEL FIle 
		WebElement email = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginUserID_Field));
		WebElement password = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginPassword_Field));
		WebElement loginButton = driver.findElement(By.xpath(Suite_Test_Repository.Suite_LoginButton));
		email.sendKeys(Username);
		password.sendKeys(Password);
		//password.sendKeys(Keys.ENTER);  // This is can be used only 
		loginButton.click();
		fwait();
		//super.hardWait();
*/		
	}

	public static void ClickTab(String EleXpath)
	{
			WebElement TargetObject = driver.findElement(By.xpath(EleXpath));
			TargetObject.click();
			fwait();			
	}

	public static void fwait()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void MethodNameOne()
	{
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		           
	}
	
	public void MethodNameTwo()
	{
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());

	}

	
}





