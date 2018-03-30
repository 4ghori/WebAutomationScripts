package com.msg.qa.common;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class HTMLToSMTPFunctions {
        private static final Logger logger = Logger.getLogger(HTMLToSMTPFunctions.class);

        private HashMap<String, String> sauceRestCallResults;

		private WebDriver driver;
        
        public HTMLToSMTPFunctions() {
}

	//************ BEGIN FUNCTIONS ************//
        
        public String urlToHTML (String htmlURL) throws IOException {
        	
        	URL url = new URL(htmlURL);
        	InputStream is = url.openStream();
        	int ptr = 0;
        	StringBuffer buffer = new StringBuffer();
        	while ((ptr = is.read()) != -1) {
        	    buffer.append((char)ptr);
        	}
        	System.out.println(buffer.toString().contains("href=\"//"));
        	String htmlWithoutAppleWebData = buffer.toString().replace("href=\"//", "href=\"http://");
        	System.out.println(htmlWithoutAppleWebData.contains("href=\"//"));
        	return htmlWithoutAppleWebData;
        }
        
        public void htmlToSMTP(String emailBody, String reportRecipientEmailList, String reportRecipientSubject) {
            String to = reportRecipientEmailList;
            String from = "msgtesting@msg.com";
            Properties properties = System.getProperties();
            
      	  properties.setProperty("mail.smtp.host", "localhost");//change accordingly
      	  properties.put("mail.smtp.port", "25");
      	  properties.put("mail.smtp.ssl.enable", "false");
      	  properties.put("mail.smtp.auth", "false");
      	  properties.put("mail.smtp.port", "25");
      	  properties.put("mail.debug", "false"); 
      	  properties.put("mail.smtp.ssl.checkserveridentity", "false");
      	  properties.put("mail.smtp.ssl.trust", "*");

          Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication("msgtesting@msg.com", "NULL");
          }});

            try{
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
               message.setSubject(reportRecipientSubject);
               message.setContent(emailBody,"text/html" );
               Transport.send(message);
               System.out.println("Sent message successfully....");
            }catch (MessagingException mex) {
               mex.printStackTrace();
            }

        }
        
        public String fileToString(String filename) throws IOException {
        	InputStream is = new FileInputStream(filename);
        	BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        	String line = buf.readLine();
        	StringBuilder sb = new StringBuilder();
        	while(line != null){
        		sb.append(line).append("\n");
        		line = buf.readLine();
        		}
        	String fileAsString = sb.toString();
        	return fileAsString;
        }
        
        public String reportRowBuilder (String ROWNUMBER, String IDURL, String IDNAME, String STATUSBACKGROUNDCOLOR, String EXECUTIONSTATUS, String SUMMARYPREFIX, String SUMMARYDESCRIPTION, String EXECUTEDBY, String EXECUTEDON) throws IOException {
        	
        	String reportRow;
        	
        	reportRow = fileToString("ReportTemplate/HTMLEmailReportTemplateRow.html");
        	reportRow = reportRow.replace("ROWNUMBER", ROWNUMBER);
        	reportRow = reportRow.replace("IDURL", IDURL);
        	reportRow = reportRow.replace("IDNAME", IDNAME);
        	reportRow = reportRow.replace("STATUSBACKGROUNDCOLOR", STATUSBACKGROUNDCOLOR);
        	reportRow = reportRow.replace("EXECUTIONSTATUS", EXECUTIONSTATUS);
        	reportRow = reportRow.replace("SUMMARYPREFIX", SUMMARYPREFIX);
        	reportRow = reportRow.replace("SUMMARYDESCRIPTION", SUMMARYDESCRIPTION);
        	reportRow = reportRow.replace("EXECUTEDBY", EXECUTEDBY);
        	reportRow = reportRow.replace("EXECUTEDON", EXECUTEDON);
        	
        	return reportRow;
        }

        public String reportRowCombiner(List<String> reportRows) {
        	int numberOfReportRows = reportRows.size();
        	String reportRowSet = "";
        	for (int a = 0; a < numberOfReportRows; a++) {
        		String reportRowToBeAdded = reportRows.get(a);
        		reportRowSet = reportRowSet + reportRowToBeAdded;
        	}
        	return reportRowSet;
        }
        
        public String completeHTMLReport (String reportTitle, String reportRowSet) throws IOException {
        	String reportPrefix = fileToString("ReportTemplate/HTMLEmailReportTemplatePrefix.html").replace("HTMLREPORTTITLE", reportTitle);
        	String reportSuffix = fileToString("ReportTemplate/HTMLEmailReportTemplateSuffix.html");
        	String completeHTMLReport = reportPrefix + reportRowSet + reportSuffix;
        	return completeHTMLReport;
        }

}

