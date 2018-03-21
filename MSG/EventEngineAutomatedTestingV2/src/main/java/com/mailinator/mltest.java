package com.mailinator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.mailinator.InboxMessage;
import com.mailinator.Mailinator;


public class mltest {

	public static void main(String[] args) throws IOException 
	{
		
		String apikey = "3adbc8639b524da9a6a0d888d546adcf";
		String emailAddress = "msgqa12345@mailinator.com";
		ArrayList<InboxMessage> inboxMessages = null;
		try 
		{
			inboxMessages = (ArrayList<InboxMessage>) Mailinator.getInboxMessages(apikey, emailAddress);

		} catch (IOException e) {
			e.printStackTrace();
		}

		//==>> Reading All Email MSG 
	        for(InboxMessage imsg : inboxMessages)
	        {
	             System.out.println(imsg);
	        }
	
	   //==>> Reading Content Of each Email      
        for(InboxMessage imsg : inboxMessages)
        {
            System.out.println(imsg.getSubject());
            String msgId = imsg.getId();
            
            //Email email = Mailinator.getEmail(apikey, msgId);
            String emailBody = Mailinator.getEmailBody(apikey, msgId, false);
            
            
          /*  ArrayList<String> result = new ArrayList<String>();
            String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(emailBody);
            while (m.find())
            {
                result.add(m.group());
            }*/
            
            
            
            String HTMLPage = emailBody;
            
            //<a href="https://u1656594.ct.sendgrid.net/wf/click?upn=mFi3EZkfiskhIWzuD0Do1pvu7S2-2BaQftjDgtylYqTn8I4mXWJ6Pv2BdaMYc-2BfTJj9lSRtr-2BiEI4ekF64vQVtFnkfaYWfBCbM1uNLnZLNUCA-3D_hfcRoyXDs5hmDkFR0SToqRjh-2F4yto3s31g-2FvYg0nY99N0eB1RPcM70dyn1GG2-2BWfBPS54N3pEIifV9Xyu3GUwqXYCY6wcRX7gHWFkTrPj75n3K4KtrQ6yLaxTKYqI0xVq-2B3NvMymangdnBODV36AvpP1AhxtBdpfGQ7fMxaknNr0VOwp8HGTXaDi9aA4ZdLXkY6hFhKiPiAEu8bVP-2Fm2GpKh15ky5XxH9e06N9j-2FPf8-3D">Verify Account &amp; Set Password</a>
            //Pattern linkPattern = Pattern.compile("(<a[^>]+>.+?<\\/a>)",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Pattern linkPattern = Pattern.compile("(<a[^>]+>.+?Set Password<\\/a>)",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            //Pattern linkPattern = Pattern.compile("<a[^>]+href=[\"']?([\"'>]+)[\"']?[^>]*>(.+?)<\\/a>",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
            Matcher pageMatcher = linkPattern.matcher(HTMLPage);
            ArrayList<String> links = new ArrayList<String>();
            while(pageMatcher.find())
            {
                links.add(pageMatcher.group());
            }
            
            
            //== Get Only Send Grid  the Token Link URL 
            Pattern p = Pattern.compile("href=\"(.*?)\"");
            Matcher m = p.matcher(links.get(0));
            String url = null;
            if (m.find()) {
                url = m.group(1); // this variable should contain the link URL
            }
            
            
           
            System.out.println(emailBody);
        }
        
	}
	
	
	
	

}
