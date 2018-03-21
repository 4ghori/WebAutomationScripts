package com.msgqa.eventengine.features;

import org.openqa.selenium.WebDriver;

import com.msgqa.eventengine.testcases.Test_DriverBase;
import com.msgqa.eventengine.testcases.Test_Keywords_Runner;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class ee_editpage_steps extends Test_DriverBase
{
	
 	//public WebDriver driver;
	//public  ExtentTest test;
	//public  ExtentTest ScenarioReport;
	

	
	//public Test_Keywords_Runner testrunner = null;	
	public Test_Keywords_Runner TestKeyRunner = null;

	public static void main(String[] args) throws Throwable
	{
		ee_editpage_steps editpage = new  ee_editpage_steps();
		editpage.i_Login_to("");
	}
	
	
	
	
	public ee_editpage_steps()
	{
	}
	

	@Given("^I Login to \"([^\"]*)\"$")
	public void i_Login_to(String arg1) throws Throwable 
	{
		test = extent.startTest("Test Name 1" + " - "+"Test Description...");
		init("Chrome", "Scenario : Mark Headliner Artist & Verify API Return is_headliner = true");
		
		TestKeyRunner = new Test_Keywords_Runner(driver,test,ScenarioReport);
		TestKeyRunner.EE_AdminLogin("","","","");
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@And("^I Navigate to \"([^\"]*)\"$")
	public void i_Navigate_to(String arg1) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@And("^I Search & Click First Event with Event Name \"([^\"]*)\"$")
	public void i_Search_Click_First_Event_with_Event_Name(String arg1) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@And("^I Search an Available Artist  \"([^\"]*)\" to Add to Selected Artist Section$")
	public void i_Search_an_Available_Artist_to_Add_to_Selected_Artist_Section(String arg1) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}

	@And("^I Mark  \"([^\"]*)\" As a Headliner Artist$")
	public void i_Mark_As_a_Headliner_Artist(String arg1) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
	}
	

}
