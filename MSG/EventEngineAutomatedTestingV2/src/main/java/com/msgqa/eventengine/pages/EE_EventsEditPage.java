package com.msgqa.eventengine.pages;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.msg.suites.pom.util.printconsole;
import com.msgqa.eventengine.api.EE_API;
import com.msgqa.eventengine.config.MSGEventEngineURLs;
import com.msgqa.selenium.Keyword.WebUI;
import com.relevantcodes.extentreports.ExtentTest;

public class EE_EventsEditPage extends WebUI
{
	
//###### Locators :-   *****
	@FindBy(xpath="//a[@href='/logout']")
		public WebElement weLogoutLinkIcon;
	@FindBy(xpath="//div[@class='card-header card-header-icon']/i[contains(text(), 'event')]")
		public WebElement weEventCardHeaderIcon;
	@FindBy(xpath="//h2[contains(text(), 'Events')]")
		public WebElement weEventHeadingLabel;
	@FindBy(xpath="(//label[@class='form-group'][2])")
		public WebElement wePublishUnpublishSwitch;	
	@FindBy(xpath="//button[text() = 'Update']")
		public WebElement weupdate_event_button;
	@FindBy(xpath="//input[contains(@value,'ticketmaster.com')]")
		public WebElement weevent_ticket_master_urlTextField;
	@FindBy(xpath="//*[@id='eventArrivalDescription']")
		public WebElement weArrivingAtTheEventDescription;
	
//#### Locators :- Artist Selection ( //h4[text() = 'Artists:']/.. )
	@FindBy(xpath="(//h4[text() = 'Artists:']/..//input)[1]")
		public WebElement weEventsAvailableArtistsSearchField;
	@FindBy(xpath="//h4[text() = 'Artists:']/..//div[@class='availableItemsContainer']//ul")
		public WebElement weEventsAvailableArtistsListBox;
	@FindBy(xpath="//h4[text() = 'Artists:']/..//div[@class='selectedItemsContainer']//ul")
		public WebElement weEventsSelectedArtistsListBox;
	

	//#### Locators :- Sponsor Selection ( //h4[text() = 'Artists:']/.. )
		@FindBy(xpath="(//h4[text() = 'Sponsors:']/..//input)[1]")
			public WebElement weEventsAvailableSponsorSearchField;
		@FindBy(xpath="//h4[text() = 'Sponsors:']/..//div[@class='availableItemsContainer']//ul")
			public WebElement weEventsAvailableSponsorListBox;
		@FindBy(xpath="//h4[text() = 'Sponsors:']/..//div[@class='selectedItemsContainer']//ul")
			public WebElement weEventsSelectedSponsorListBox;
	
	
	
	
	
	
//#### Locators :- Category Selection ( //h4[text() = 'Category:']/.. )
	@FindBy(xpath="//h4[text() = 'Categories:']/..")
		public WebElement weCategoryContainer;
		public String XpathCategoryDropDownBox_1 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][0]']";
		public String XpathCategoryDropDownBox_2 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][1]']";
		public String XpathCategoryDropDownBox_3 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][2]']";
	@FindBy(xpath="//h4[text() = 'Categories:']/..//select[@name='event[categories][2]']/..//a")
		public WebElement XpathRemoveCategory_3;
		public String XpathCategoryDropDownBox_4 = "//h4[text() = 'Categories:']/..//select[@name='event[categories][3]']";
	@FindBy(xpath="//h4[text() = 'Categories:']/..//button[text() = 'Add more']")
		public WebElement weAddMoreCataButton;
	

//##### Common Variables   *****
		public String lastSavedEventId;  	// This variable will store the Last Saved Event ID for API CALL
		public EE_CommonSideBar sideNavBar;	// This Object will Hold the Common Side Navigation Bar that will help to navigate	
	
	//~ ~ ~ ~  Constructor 
	public EE_EventsEditPage(WebDriver driver, ExtentTest test, ExtentTest ScenarioReport) 
	{
		super(driver, test, ScenarioReport);

		//=== All Logged In Page need this Side bar to Navigate 
		sideNavBar = new EE_CommonSideBar(driver, test,ScenarioReport);
	}
	
	public void goTo(String Url)
	{
		NavigateToPage(Url);
	}

//############### Common Page Run Function All Page Should Run this ########################		
	public boolean ValidateWeInEventEditPage()  // THis Should go as Common Ensure Page ready Function 
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

		if(ValidateElementExist(weElementToValidate)==true)
		{
			return true;
		}
		return false;
	}

	public boolean ValidateEventEditForm()
	{
		//==> Event Name
		
		//==>> Tag
		
		//==>> Description
		
		//==>> Event Date 
		
		//==>> Door Open 
		
		//==>> Start Time 
		
		//==>> Chase Early Seating 
		
		//==>> Preferred Seating 
		
		//==>> Default Image 
		
		//==>> Image Aspect Ratio 
		
		//==>> Image Height 
		
		//==>> Image Width
		
		//==>> Image Preview Button
		
		//==>> Add Artist Button
		
		//==>> Venue 
		
		//==>> User Sponsor LOGO 
		
		//==>> At least One Minimum Category 
		
		//==>> Add More Category Button 
		
		
		


		
		return true;
	}

 //############### Actual Test Runner Functions ########################	

	public boolean PublishEvent(String sArrivingAtDescriptionTextx)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//************ Default Variable For this Functions  ************************************
			EE_API eeapi = new EE_API();
			String sResponseAPIArray= "";
			String ExpectedResponseValue = "";
			String ActualResponseValue=""; 
			

		//######## Step >>> 1st : Enter Arriving At Description Text ############## 
			printconsole.print("Step >>> 1st : Click On Publish - Unpublish Switch <<<<");
			if(!ClickButton(wePublishUnpublishSwitch))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				  "Failed to CLick the Publish - Unpublish Switch .. ", 
						 				  "Exp  N/A", 
						 				  "Actual   N/A");
				return false;
			}
		
			//==>>  Save the Event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After " + thisMethodName, 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}
		
			//==>> Call API And validate API Response for 
			sResponseAPIArray= "publish";
			ExpectedResponseValue = 	"true";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,sResponseAPIArray,ExpectedResponseValue);
			if(!ActualResponseValue.toLowerCase().equals(ExpectedResponseValue.toLowerCase()))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											sResponseAPIArray + ":" + ExpectedResponseValue, 
											sResponseAPIArray + ":" + ActualResponseValue);
				return false;
			}
		
		//#### If there is no false this function returns true	
		return true;	
	}
	
	public boolean ArrivingAtDescription(String sArrivingAtDescriptionTextx)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//************ Default Data For this Functions  ************************************
			EE_API eeapi = new EE_API();
			String Arrival_descriptionArray = "arrival_description";
			String ExpectedResponseValue="";
			String ActualResponseValue=""; 
			
			String sArrivingAtDescriptionText = "Arriving At Description";

		//######## Step >>> 1st : Enter Arriving At Description Text ############## 
			printconsole.print("Step >>> 1st : Enter Arriving At Description Text <<<<");
			if(!EnterText(weArrivingAtTheEventDescription,sArrivingAtDescriptionText))
			{
				System.out.println("Error In [EE_EventsEditPage_"+thisMethodName+") :>> Faild Couldn't Enter "+ sArrivingAtDescriptionText);
				return false;
			}
		
			//==>>  Save the Event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After " + thisMethodName, 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}
		
			//==>> Call API And validate API Response for 
			ExpectedResponseValue = 	sArrivingAtDescriptionText;
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,Arrival_descriptionArray,ExpectedResponseValue);
			if(!ActualResponseValue.toLowerCase().contains(ExpectedResponseValue.toLowerCase()))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											Arrival_descriptionArray + ":" + ExpectedResponseValue, 
											ActualResponseValue);
				return false;
			}
		
		//#### If there is no false this function returns true	
		return true;	
	}

	public boolean MarkAsHeadlinerArtist(String sArtistNameX)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//************ Default Data For this Functions  ************************************
			EE_API eeapi = new EE_API();
			String ArtistArray = "artists";
			String ExpectedResponseValue="";
			String ActualResponseValue=""; 
			
			String sArtistName = "Bill Maher";
			String sArtistID = "K8vZ91714jf";

		//######## Step >>> 1st : Search Bill MAHER in AvailableArtist List & Take him to selected artist list ############## 
			printconsole.print("Step >>> 1st : Search Bill Maher in AvailableArtist List & & Take him to selected artist list <<<<");			
			if(!SearchAvailableArtistAndAdd(sArtistName))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Find Mr. " + sArtistName + " in Available Artist List", 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}
			
		//######## Step >>> 2nd : Click the Headliner News Icon ############## 
			printconsole.print("Step >>> 2nd : Click the Headliner News Icon <<<<");			
			if(!Click_A_WebElement_FromListBox(weEventsSelectedArtistsListBox,"i",sArtistName))
			{
				System.out.println("Error In [EE_EventsEditPage_"+thisMethodName+") :>> Faild To Mark "+ sArtistName+" As Headliner Artist. ");
				return false;
			}		
		
		//######## Step >>> 3rd : Save the Event & Get the Event ID  ############## 
			printconsole.print("Step >>> 3rd : Save the Event & Get the Event ID");			
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After " + thisMethodName, 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}

		//######## Step >>> 4th : Call API And validate API Response for "artist":[{name=Bill Maher, id=K8vZ91714jf, is_headliner=true}]]  ############## 
			printconsole.print("Step >>> 4th : Call API And validate API Response for \"artist\":[{name=Bill Maher, id=K8vZ91714jf, is_headliner=true}]]");			
			ExpectedResponseValue = 	"{name="+sArtistNameX+", id="+sArtistID+", is_headliner=true}]";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,ArtistArray,ExpectedResponseValue);
			if(!ActualResponseValue.toLowerCase().contains(ExpectedResponseValue.toLowerCase()))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											ArtistArray + ":" + ExpectedResponseValue, 
											ActualResponseValue);
				return false;
			}
		
		//#### If there is no false this function returns true	
		return true;	
	}

	public boolean AddRemoveCategory(String sCategoryName)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		
		//************ Default Data For this Functions  ************************************
			EE_API eeapi = new EE_API();
			String CategoryArray = "category";
			String ExpectedResponseValue="";
			String ActualResponseValue=""; 
			
			sCategoryName = "Family";
			String sFirstCtegoryToChange = "Family";
			String sSecondCtegoryChaseCardholderAccess = "Chase Cardholder Access";
			String sThirdCtegoryAddNew = "Music";
			String sCategorySwapHelper = "Sports";
		//************ ************************** *******************************************


		//###########  Step >>> 1st : We Will Change Default Category to Family - This will Prove There is At least one Category Present ####################
			printconsole.print("Step >>> 1st : We Will Change Default Category to Family - This will Prove There is At least one Category Present <<<<");
			if(!scrollToElement(weCategoryContainer))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				 "Faild Scroll to Category Element weCategoryContainer", 
					 					 "Exp  N/A", 
						 				 "Actual   N/A");
				return false;
			}
			if(!SelectDropDown(XpathCategoryDropDownBox_1,sFirstCtegoryToChange))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
		 				 				"Can Not Select [" + sCategoryName + "] From CategoryDropDownBox_1", 
		 				 				"Exp  N/A", 
		 				 				"Actual N/A");
				return false;
			}
	
		//###########  Step >>> 2nd : Look For Default Chase Cardholder Access 	Category is There ####################
			printconsole.print("Step >>> 2nd : Look For Default Chase Cardholder Access 	Category is There <<<<");
			if(!GetDropDownFirstSelectedOption(XpathCategoryDropDownBox_2).equals(sSecondCtegoryChaseCardholderAccess))
			{
				
				printconsole.printfailure(thisClassName, thisMethodName, 
			 								"Faild to Get [" + sSecondCtegoryChaseCardholderAccess + "] From CategoryDropDownBox_1", 
			 								"Exp  N/A", 
			 								"Actual N/A");
				return false;
			}
	
		//###########  Step >>> 3rd : Clicking Add More Button ####################
			printconsole.print("Step >>> 3rd : Clicking Add More Button <<<<");
			if(!ClickButton(weAddMoreCataButton))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Click Add More Button", 
											"Exp  N/A", 
											"Actual N/A");
				return false;
			}
			
		//###########  Step >>> 4th : Make Sure Family Option is no longer exist in New Category DropDown. As we already added it in  1st step ####################
			printconsole.print("Step >>> 4th : Make Sure Family Option is no longer exist in New Category DropDown. As we already added it in  1st step <<<<");
			//sCategoryName = "Sports"; Debug with negative Value
			if(GetAllDropDownOptionAsList(XpathCategoryDropDownBox_3).contains(sFirstCtegoryToChange))
			{
				
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Category ["+ sCategoryName + "] Already Selected Once it Can't Be Repeat" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
	
		//###########  Step >>> 5th : Add a New category  ####################
			printconsole.print("Step >>> 5th : Add a New category <<<<");
			if(!SelectDropDown(XpathCategoryDropDownBox_3,sThirdCtegoryAddNew))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Add A  New Category"+ sCategoryName , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}

		//###########  Step >>> 6th : Swap MUSIC with SPORTS category so MUSIC become available for First Category Drop Box  ####################
			printconsole.print("Step >>> 6th : Swap MUSIC with SPORTS category so MUSIC become available for First Category Drop Box <<<<");
			//==>>Swapping from CategoryDropDownBox_3 -> MUSIC Category to SPORTS Category [ MUSIC to SPORTS ]
			if(!SelectDropDown(XpathCategoryDropDownBox_3,sCategorySwapHelper))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Music to  [" + "Sports" + "] From CategoryDropDownBox_3" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>Swapping from CategoryDropDownBox_1 -> Family Category to MUSIC Category [ Family to Music ]
			if(!SelectDropDown(XpathCategoryDropDownBox_1,sThirdCtegoryAddNew)) 
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Family to  [" + "Music" + "] From CategoryDropDownBox_1" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>Swapping from CategoryDropDownBox_3 -> Sports Category to Family Category [ Sports to family ]
			if(!SelectDropDown(XpathCategoryDropDownBox_3,sFirstCtegoryToChange))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Faild to Change Category from Sports to  [" + "Family" + "] From CategoryDropDownBox_3" , 
											"Exp  N/A", 
											"Actual N/A");				
				return false;
			}
			
			//==>>  Save the Event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				if(!Save_And_StoreEventID())
				{
					printconsole.printfailure(thisClassName, thisMethodName, 
											 "Couldn't Save the Event After Swapping Category from Box3.Music to Sports | Box1.Family to Music | Box3.Sports To Family", 
											 "Exp  N/A", 
											 "Actual   N/A");
					return false;
				}
				return false;
			}
			
			//==>> Call API And validate API Response for "category":[{"name": "Music"},{"name": "Chase Cardholder Access"}]
			ExpectedResponseValue = "[{name="+sThirdCtegoryAddNew+"}, {name="+sSecondCtegoryChaseCardholderAccess+"}, {name="+sFirstCtegoryToChange+"}]";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,CategoryArray,ExpectedResponseValue);
			if(!ActualResponseValue.equals(ExpectedResponseValue))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											CategoryArray + ":" + ExpectedResponseValue, 
											ActualResponseValue);
				return false;
			}


		//###########  Step >>> 7th : Remove the Last Category From the The List & Validate API Response  ####################
			printconsole.print("Step >>> 7th : Remove the Last Category From the The List & Validate API Response <<<<<<<<<<<<<<<<<<<<<");			
			if(!scrollToElement(weCategoryContainer))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				 "Faild Scroll to Category Element", 
					 					 "Exp  N/A", 
						 				 "Actual   N/A");
				return false;
			}
			if(!ClickLink(XpathRemoveCategory_3))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
		 				 				"Faild Scroll to Remove Category" + sFirstCtegoryToChange + " From Category List", 
	 				 					"Exp  N/A", 
		 				 				"Actual   N/A");
				return false;
			}

			//==>>  Save the event & Get the Event ID 
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After Removing Category" + sFirstCtegoryToChange, 
										 "Exp  N/A", 
										 "Actual   N/A");
				return false;
			}
			//==>> Call API And validate API REsponse now Now don't have that Removed "category":[{"name": "Music"},{"name": "Chase Cardholder Access"}]
			ExpectedResponseValue = "[{name="+sThirdCtegoryAddNew+"}, {name="+sSecondCtegoryChaseCardholderAccess+"}]";
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,CategoryArray,ExpectedResponseValue);
			if(!ActualResponseValue.equals(ExpectedResponseValue))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										"Event/Event ID API Response doesn't Match with", 
										CategoryArray + ":" + ExpectedResponseValue, 
										ActualResponseValue);
				return false;
			}
			
		//#### If there is no false this function returns true	
		return true;	
	}
	
	public boolean AddRemoveSponsor(String sSponsorNameX)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//************ Default Data For this Functions  ************************************
			EE_API eeapi = new EE_API();
			String SponsorArray = "sponsors";
			String ExpectedResponseValue="";
			String ActualResponseValue=""; 
			
			String sSponsorName = "billyjoel";
			String sSponsorDesc = "Please hold onto your original tickets, as tickets for the June 6, 2017 performance will be honored for the newly rescheduled December 20, 2017 performance. Your original tickets will not need to be exchanged.";
			String sSponsorIconURL = "http://res.cloudinary.com/madison-square-garden/image/upload/h_20,w_20/v1477067897/icon-balloon_zvlwo3.png";
			

		//######## Step >>> 1st : Search BillyJoel in AvailableSponsor List & Take him to Selected Sponsor list ############## 
			printconsole.print("Step >>> 1st : Search BillyJoel in AvailableSponsor List & Move It to Selected Sponsor list <<<<");			
			if(!SearchAvailableSponsorAndAdd(sSponsorName))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Find Mr. " + sSponsorName + " in Available Artist List", 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}
			
		//######## Step >>> 2nd : Save the Event & Get the Event ID  ############## 
			printconsole.print("Step >>> 2nd : Save the Event & Get the Event ID");			
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After " + thisMethodName, 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}

		//######## Step >>> 3rd : Call API And validate API Response for "sponsors":[{icon_url=sSponsorIconURL, description="sSponsorDesc", title=sSponsorName}]  ############## 
			printconsole.print("Step >>> 3rd : Call API And validate API Response for \"sponsors\":[{icon_url=sSponsorIconURL, description=\"sSponsorDesc\", title=sSponsorName}] ");			
			ExpectedResponseValue = "[{icon_url="+sSponsorIconURL+", description="+sSponsorDesc+", title="+sSponsorName+"}]" ;
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,SponsorArray,ExpectedResponseValue);
			if(!ActualResponseValue.toLowerCase().contains(ExpectedResponseValue.toLowerCase()))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											SponsorArray + ":" + ExpectedResponseValue, 
											SponsorArray + ":" + ActualResponseValue);
				return false;
			}
		
			
		//###########  Step >>> 4th : Remove the Last Added Sponsor  ####################
			printconsole.print("Step >>> 7th : Remove the Last Added Sponsor <<<<<<<<<<<<<<<<<<<<<");			
			if(!scrollToElement(weEventsAvailableSponsorSearchField))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
						 				 "Faild Scroll to Avialalbe Sponsor Search Text Field", 
					 					 "Exp  N/A", 
						 				 "Actual   N/A");
				return false;
			}
			if(!SelectAnItemFrom_ListBox(weEventsSelectedSponsorListBox,sSponsorName))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
			 				"Failed to Find the Sponsor bilyjoel on Selected Sponsor List", 
		 					"Exp  N/A", 
			 				"Actual   N/A");
					return false;
			}
		//######## Step >>> 5th : Save the Event & Get the Event ID  ############## 
			printconsole.print("Step >>> 5th : Save the Event & Get the Event ID");			
			if(!Save_And_StoreEventID())
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
										 "Couldn't Save the Event After " + thisMethodName, 
										 "Exp  N/A", 
										 "Actual   N/A");
					return false;
			}

		//######## Step >>> 6th : Call API And validate API Response for "sponsors":[]  ############## 
			printconsole.print("Step >>> 6th : Call API And validate API Response for \"sponsors\":[] ");			
			ExpectedResponseValue = "[]" ;
			ActualResponseValue = eeapi.Get_Response_AttributeValue(lastSavedEventId,SponsorArray,ExpectedResponseValue);
			if(!ActualResponseValue.toLowerCase().contains(ExpectedResponseValue.toLowerCase()))
			{
				printconsole.printfailure(thisClassName, thisMethodName, 
											"Event/Event ID API Response doesn't Match with", 
											SponsorArray + ":" + ExpectedResponseValue, 
											SponsorArray + ":" + ActualResponseValue);
				return false;
			}
			
		//#### If there is no false this function returns true	
		return true;	
	}

	
	
	
 //############### Page Test Runner Helper Functions ########################	
	public boolean SearchAvailableArtistAndAdd(String sArtistName)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			//printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//######## Step >>> 1st : Scroll To Artist Section ############## 
			//printconsole.print("Step >>> 1st : Scroll To Artist Section <<<<");
			scrollToElement(weEventsAvailableArtistsSearchField);
		
		//######## Step >>> 2nd : Check IF the sArtistName already Selected then Return back to Available Artist ListBox :-) ##############	
			//printconsole.print("Step >>> 2nd : Check IF the "+sArtistName+" already Selected then Return back to Available Artist ListBox :-) <<<<");
			if(SelectAnItemFrom_ListBox(weEventsSelectedArtistsListBox,sArtistName))
			{
				//return true;
				//System.out.println(thisMethodName+") :>>"+  sArtistName+" Was already Selected we are Retuning it back to available artist List box  ");
			}

		//######## Step >>> 3nd : Enter Search Text On Available Artist Search Field  <<<< ##############	
			//printconsole.print("Step >>> 3nd : Enter Search Text On Available Artist Search Field  <<<<");
			EnterText(weEventsAvailableArtistsSearchField,sArtistName);

		//######## Step >>> 4th : Selecting The Artist   <<<< ##############	
			//printconsole.print("Step >>> 4th : Selecting Artist " + sArtistName +" <<<<");			
			if(SelectAnItemFrom_ListBox(weEventsAvailableArtistsListBox,sArtistName))
			{
				return true;
			}
		
		return false;
	}
	public boolean SearchAvailableSponsorAndAdd(String sSponsorName)
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
			       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
			String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			//printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
			//printconsole.print(".............................................................");
		//************ ************************** *******************************************

		//######## Step >>> 1st : Scroll To Artist Section ############## 
			//printconsole.print("Step >>> 1st : Scroll To Artist Section <<<<");
			scrollToElement(weEventsAvailableSponsorSearchField);
		
		//######## Step >>> 2nd : Check IF the sArtistName already Selected then Return back to Available Artist ListBox :-) ##############	
			//printconsole.print("Step >>> 2nd : Check IF the "+sArtistName+" already Selected then Return back to Available Artist ListBox :-) <<<<");
			if(SelectAnItemFrom_ListBox(weEventsSelectedSponsorListBox,sSponsorName))
			{
				//return true;
				//System.out.println(thisMethodName+") :>>"+  sArtistName+" Was already Selected we are Retuning it back to available artist List box  ");
			}

		//######## Step >>> 3nd : Enter Search Text On Available Artist Search Field  <<<< ##############	
			//printconsole.print("Step >>> 3nd : Enter Search Text On Available Artist Search Field  <<<<");
			EnterText(weEventsAvailableSponsorSearchField,sSponsorName);

		//######## Step >>> 4th : Selecting The Artist   <<<< ##############	
			//printconsole.print("Step >>> 4th : Selecting Artist " + sArtistName +" <<<<");			
			if(SelectAnItemFrom_ListBox(weEventsAvailableSponsorListBox,sSponsorName))
			{
				return true;
			}
		
		return false;
	}
	
	
	
	public boolean Save_And_StoreEventID()
	{
		//************ All Function Header ***************************************************
			String thisClassName = Thread.currentThread().getStackTrace()[1].getClassName();
		       thisClassName = thisClassName.substring(thisClassName.lastIndexOf('.') + 1);
		    String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		    //printconsole.print("\nClass Method : " + thisClassName + "." +thisMethodName+"()");
		//************ ************************** *******************************************

		String sEventIDFromTMURL;
		//==Lets Save the Tiket Master URL From Current Edit Event Screen 
		String tmUrl = GetAttributeText(weevent_ticket_master_urlTextField,"value");
		if(!tmUrl.equals("")) 
		{
			// == > Parse the URL text and Store only the Event ID x`
			URI uri;
			try 
			{
				uri = new URI(tmUrl);
				String path = uri.getPath();
				sEventIDFromTMURL = path.substring(path.lastIndexOf('/') + 1);
				lastSavedEventId = sEventIDFromTMURL;
			} 
			catch (Exception e) 
			{
				System.out.println("ERROR :>> Failed To Get Event ID From Ticket Master URL ");
				return false;
				//e.printStackTrace();
			}
		}
		
		//==>> Scroll TO  AvailableArtistsSearchField
		scrollToElement(weupdate_event_button);
		
		//== >> Click The Save Update Button 
		if(ClickButton(weupdate_event_button)) 
		{
			//sFailReason = "Failed To CLick Login Button .. ";
			return true;
		}
		
		
		return false;
		
	}

	
	
	
}//END OF CLass
