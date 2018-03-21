package com.msgqa.eventengine.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;

public class EE_EventsPage extends WebUI
{
	
	@FindBy(xpath="//a[@href='/logout']")
		public WebElement weLogoutLinkIcon;
	
	@FindBy(xpath="//div[@class='card-header card-header-icon']/i[contains(text(), 'event')]")
		public WebElement weEventCardHeaderIcon;
	
	@FindBy(xpath="//h2[contains(text(), 'Events')]")
		public WebElement weEventHeadingLabel;
	
	@FindBy(xpath="//input[@placeholder='Search..']")
		public WebElement weSearchField;	
	
	@FindBy(xpath="//button[@type='submit']")
		public WebElement weSearchButton;
	
	@FindBy(xpath=".//*[@id='event-list-table']")
		public WebElement weEventsTable;
	
	///===== We will implement Passing List to Table Function later 
	@FindBy(xpath="//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']")
		public List<WebElement> listEventsTableAllRows;
		//public String sTableRowsXpath ="//div[@class='react-table-events']//div[@class='fixedDataTableLayout_rowsContainer']//div[@class='fixedDataTableRowLayout_rowWrapper']";
		//public String sTableColumnsXpath ="//div[@class='fixedDataTableCellLayout_main public_fixedDataTableCell_main']";
	
		public String sTableRowsXpath ="//*[@id='event-list-table']//tr";
		public String sTableColumnsXpath ="//td";

	
	
	
	
	//*** Common Side Bar Object Variable For all Page 
	public EE_CommonSideBar sideNavBar;
	
	public EE_EventsPage(WebDriver driver, ExtentTest test, ExtentTest ScenarioReport) 
	{
		super(driver, test, ScenarioReport);

		//=== All Logged In Page need this Side bar to Navigate 
		sideNavBar = new EE_CommonSideBar(driver, test,ScenarioReport);
	}

	
	public void goTo(String Url)
	{
		NavigateToPage(Url);
	}
	
	
	public boolean ValidateWeInEventPage()  // THis Should go as Common Ensure Page ready Function 
	{
		if(ValidateElementExist(weLogoutLinkIcon)==true)
		{
			return true;
		}
		return false;
	}
	
	public boolean Validate_Element_Present(String sEleName)
	{

		//==>> Lets Check Which element Need to Validate for Existence 
		WebElement weElementToValidate = null;
		if(sEleName.equals("EventCardHeaderIcon"))
		{
			weElementToValidate = weEventCardHeaderIcon;
		}
		else if(sEleName.equals("EventHeadingLabel"))
		{
			weElementToValidate = weEventHeadingLabel;
		}
		else if(sEleName.equals("EventSearchField"))
		{
			weElementToValidate = weSearchField;
		}
		else if(sEleName.equals("SearchButton"))
		{
			weElementToValidate = weSearchButton;
		}
		else if(sEleName.equals("EventListTable"))
		{
			weElementToValidate = weEventsTable;
		}
		

		if(ValidateElementExist(weElementToValidate)==true)
		{
			return true;
		}
		return false;
	}
	
	public boolean SearchEventByEventName(String sTextToValidate)
	{
		
		if(!EnterText(weSearchField,"Billy Joel - In Concert")) return false;
		if(!ClickButton(weSearchButton)) return false;
		if(!ValidateTableCellText(sTableRowsXpath,sTableColumnsXpath,sTextToValidate))return false;
		
		return true;
	}

	public boolean SearchAndClickFirstEvent(String sTextToValidate)
	{
		
		if(!EnterText(weSearchField,"Billy Joel - In Concert")) return false;
		if(!ClickButton(weSearchButton)) return false;
		if(!ClickTableCellText(sTableRowsXpath,sTableColumnsXpath,sTextToValidate))return false;
		
		return true;
	}
	
	
	
	
	
}
