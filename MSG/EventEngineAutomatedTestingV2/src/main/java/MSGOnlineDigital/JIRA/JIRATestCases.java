package MSGOnlineDigital.JIRA;

import java.util.HashMap;

public class JIRATestCases {
	
	public static HashMap<String, String> getIssueKeys(String projectName)
	{	
		HashMap<String, String> myJIRAIssueKeys = new HashMap<String, String>();
		
		if (projectName.equals("Rockettes"))
		{
			myJIRAIssueKeys.put("MSGEW-1579", "MSGRock001RockettesHomePage");
			myJIRAIssueKeys.put("MSGEW-1586", "MSGRock002RockettesCSLandingPage");
			//myJIRAIssueKeys.put("MSGEW-1591", "MSGRock003RockettesCSCalendar");		
			myJIRAIssueKeys.put("MSGEW-1585", "MSGRock004RockettesCSGroups");
			//myJIRAIssueKeys.put("MSGRock005RockettesCSSeatingChart", "MSGEW-1590");
			myJIRAIssueKeys.put("MSGEW-1582", "MSGRock006RockettesCSPromotions");
			myJIRAIssueKeys.put("MSGEW-1589", "MSGRock007RockettesCSPlanYourDay");
			myJIRAIssueKeys.put("MSGEW-1584", "MSGRock008RockettesCSExperiences"); 
			myJIRAIssueKeys.put("MSGEW-1587", "MSGRock009RockettesCSNews");
			myJIRAIssueKeys.put("MSGEW-1583", "MSGRock010RockettesCSEmailSignUp");
			myJIRAIssueKeys.put("MSGEW-1588", "MSGRock011RockettesCSPartners");
			myJIRAIssueKeys.put("MSGEW-1596", "MSGRock012RockettesNYSLandingPage");
			//myJIRAIssueKeys.put("MSGRock013RockettesNYSTickets", "MSGEW-1598");
			myJIRAIssueKeys.put("MSGEW-1595", "MSGRock014RockettesNYSGroups");
			myJIRAIssueKeys.put("MSGEW-1597", "MSGRock015RockettesNYSSponsors");		
			myJIRAIssueKeys.put("MSGEW-1580", "MSGRock016RockettesBlog");
			myJIRAIssueKeys.put("MSGEW-1594", "MSGRock017RockettesHistory");
			myJIRAIssueKeys.put("MSGEW-1599", "MSGRock018RockettesPhotos");
			myJIRAIssueKeys.put("MSGEW-1592", "MSGRock019RockettesDanceEducation");
			myJIRAIssueKeys.put("MSGEW-1593", "MSGRock020RockettesFAQ");
			myJIRAIssueKeys.put("MSGEW-1600", "MSGRock021RockettesStore");
			myJIRAIssueKeys.put("MSGEW-1581", "MSGRock023RockettesContactUs");		
			return myJIRAIssueKeys;	
		}
		
		if (projectName.equals("MyMSGSuites"))
		{
			myJIRAIssueKeys.put("SND-1633", "My Test Case001");
			return myJIRAIssueKeys;
		}
		
		return myJIRAIssueKeys;
	}
	
	public static HashMap<String, String> getTestCases(String projectName)
	{	
		HashMap<String, String> myJIRATestCases = new HashMap<String, String>();
		
		if (projectName.equals("Rockettes"))
		{
			myJIRATestCases.put("MSGRock001RockettesHomePage", "MSGEW-1579");
			myJIRATestCases.put("MSGRock002RockettesCSLandingPage", "MSGEW-1586");
			//myJIRATestCases.put("MSGRock003RockettesCSCalendar", "MSGEW-1591");		
			myJIRATestCases.put("MSGRock004RockettesCSGroups", "MSGEW-1585");
			//myJIRATestCases.put("MSGRock005RockettesCSSeatingChart", "MSGEW-1590");
			myJIRATestCases.put("MSGRock006RockettesCSPromotions", "MSGEW-1582");
			myJIRATestCases.put("MSGRock007RockettesCSPlanYourDay", "MSGEW-1589");
			myJIRATestCases.put("MSGRock008RockettesCSExperiences", "MSGEW-1584"); 
			myJIRATestCases.put("MSGRock009RockettesCSNews", "MSGEW-1587");
			myJIRATestCases.put("MSGRock010RockettesCSEmailSignUp", "MSGEW-1583");
			myJIRATestCases.put("MSGRock011RockettesCSPartners", "MSGEW-1588");
			myJIRATestCases.put("MSGRock012RockettesNYSLandingPage", "MSGEW-1596");
			//myJIRATestCases.put("MSGRock013RockettesNYSTickets", "MSGEW-1598");
			myJIRATestCases.put("MSGRock014RockettesNYSGroups", "MSGEW-1595");
			myJIRATestCases.put("MSGRock015RockettesNYSSponsors", "MSGEW-1597");		
			myJIRATestCases.put("MSGRock016RockettesBlog", "MSGEW-1580");
			myJIRATestCases.put("MSGRock017RockettesHistory", "MSGEW-1594");
			myJIRATestCases.put("MSGRock018RockettesPhotos", "MSGEW-1599");
			myJIRATestCases.put("MSGRock019RockettesDanceEducation", "MSGEW-1592");
			myJIRATestCases.put("MSGRock020RockettesFAQ", "MSGEW-1593");
			myJIRATestCases.put("MSGRock021RockettesStore", "MSGEW-1600");
			myJIRATestCases.put("MSGRock023RockettesContactUs", "MSGEW-1581");
		return myJIRATestCases;
		}
		
		if (projectName.equals("MyMSGSuites"))
		{
			myJIRATestCases.put("My Test Case001","SND-1633");
			return myJIRATestCases;	
		}
		return myJIRATestCases;	
	}
}
