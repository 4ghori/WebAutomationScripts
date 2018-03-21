package com.msgqa.eventengine.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;

public class EE_HomePage extends WebUI
{
	
	@FindBy(xpath="//a[@href='/logout']")
	public WebElement logoutLinkIcon;

	
	public EE_CommonSideBar sideNavBar;

	public EE_HomePage(WebDriver driver, ExtentTest test, ExtentTest ScenarioReport) 
	{
		super(driver, test, ScenarioReport);

		//=== All Logged In Page need this Side bar to Navigate 
		sideNavBar = new EE_CommonSideBar(driver, test,ScenarioReport);
		PageFactory.initElements(driver, sideNavBar);

	}
	
	public void goTo(String Url)
	{
		NavigateToPage(Url);
	}
	
	public boolean ValidateWeInEventEngineDashBoard()
	{
		if(ValidateElementExist(logoutLinkIcon)==true)
		{
			return true;
		}
		return false;
	}
	
	public EE_EventsPage gotoEventsPage()
	{
		EE_EventsPage EventsPage = sideNavBar.gotoEventPage() ;
		return EventsPage;
	}
	
	
}
