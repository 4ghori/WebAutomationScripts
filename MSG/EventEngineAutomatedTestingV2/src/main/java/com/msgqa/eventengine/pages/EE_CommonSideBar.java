package com.msgqa.eventengine.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.msgqa.eventengine.config.MSGEventEngineURLs;
import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;

public class EE_CommonSideBar extends WebUI
{
	//===Original Code 
	//@FindBy(xpath=FBConstants.LOGIN_USERNAME)
	//public WebElement email;
	
	
	@FindBy(xpath="//*[@class='sidebar-wrapper']")
	public WebElement sideNavigationBar;
	
	
	public EE_CommonSideBar(WebDriver driver, ExtentTest test, ExtentTest ScenarioReport) 
	{
		super(driver, test, ScenarioReport);
		
	}

	
	public EE_EventsPage gotoEventPage()
	{
		ClickLink(driver.findElement(By.xpath("//a[@href='/events']")));
		//ClickLink(sideNavigationBar.findElement(By.xpath("//a[@href='/events']"))); //sideNavigationBar X Path Stopped Working
		
		EE_EventsPage eventpage =new EE_EventsPage(driver, test, ScenarioReport);
		PageFactory.initElements(driver, eventpage);
		return eventpage;
		
	}
	
	
}
