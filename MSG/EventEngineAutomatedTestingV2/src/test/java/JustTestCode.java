import java.util.Calendar;

public class JustTestCode {

	
	  // E-Mail Parameters  All after Line 8 is a Individual String Variable 
	  public static String 
	  					myEmailFrom = "MSGTesting@msg.com",
//  			  		   	myEmailToList = "rachitkumar.rastogi@msg.com; richard.barrow@msg.com; krishna.chaparala@msg.com; sri.anne@msg.com",
	  	    			   	myEmailToList = "rachitkumar.rastogi@msg.com",
	  	    			   	myEmailFailedSubject = "Jenkins Error - Script is failed!",
	  	    			   	myEmailSuccessSubject = "Jenkins Success - Script is successful.",
	  	    			   	myEmailBodyWithdefaultDateAndTime = "Hi, \r\nThis is to bring to your notice that the Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"\r\nThanks, Your MSG QA Automation Team.",
	  	    			   	myEmailBody = "Hi, \r\nThis is to bring to your notice that the Regression Script has been failed at myDateAndTime. \r\nThanks, Your MSG QA Automation Team.",      
	  	    			   	myEmailUser = "msgqaautomation@gmail.com",
			 	        myEmailAccessKey = "Msgqa123"; 	

	
	
	public static void main(String[] args) 
	{
		
		System.out.println(myEmailFrom);
		System.out.println(myEmailToList);
		System.out.println(myEmailBodyWithdefaultDateAndTime);

		

	}

}
