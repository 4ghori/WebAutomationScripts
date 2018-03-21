package com.msgqa.eventengine.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.msgqa.eventengine.config.MSGEventEngineURLs;
import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;

public class EE_LoginPage extends WebUI
{
	//===Original Code 
	//@FindBy(xpath=FBConstants.LOGIN_USERNAME)
	//public WebElement email;
	
	
	@FindBy(xpath="//div/input[@name='email']")
	public WebElement email;
	
	@FindBy(xpath="//div/input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement btnLogin;
	
	
	
	public EE_LoginPage(WebDriver driver, ExtentTest test, ExtentTest ScenarioReport) 
	{
		super(driver, test, ScenarioReport);
	}

	
	public boolean goTo(String Url)
	{
		NavigateToPage(Url);
		//== If we See a Common Element From EE UI Login page We will return True as a Pass of this Action or fail 
		if(ValidateElementExist(email)==true)
		{
			return true;
		}
		
		return false;
	}
	
	
	public Object doLogin(String input)
	{
		//=========>> Input Parsing  
		if(input.equals("default"))
		{
			input = MSGEventEngineURLs.getEnvDetails().get("EE_USERNAME") + "|" + MSGEventEngineURLs.getEnvDetails().get("EE_PASSWORD");
		}
		String sUsername = input.split("\\|")[0].trim();
		String sPassword = input.split("\\|")[1].trim();

		//=========>> Variable that will Store the Fail to Login Reason  
		String sFailReason = "";

		//=========>> Check ID/Password Field is Editable and Button Is Clickable
		if(!EnterText( email, sUsername))
		{
			sFailReason =  "Failed To Enter User ID .. ";
			return sFailReason;
		}
		
		if(!EnterText( password, sPassword))
		{
			sFailReason =  "Failed To Enter Password .. ";
			return sFailReason;

		}
		
		if(!ClickButton(btnLogin)) 
		{
			sFailReason = "Failed To CLick Login Button .. ";
			return sFailReason;

		}

		//=========>> If All Above PAssed Check we are in Event Engine HOme Page 
		EE_HomePage homePage = new EE_HomePage(driver, test, ScenarioReport);
		PageFactory.initElements(driver, homePage);
		boolean loginSuccess=homePage.ValidateWeInEventEngineDashBoard();
		if(loginSuccess)
		{
			return homePage;
		}
		else
		{
			sFailReason = "Failed To Login with User ID  :-  " + sUsername + " And Password :- " + sPassword ;
			return sFailReason;
		}
	}
	
	
	
	
}
