package MSGOnlineDigital.SendGrid;
 
//import com.sendgrid.*;
 
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class MailClient {
               
/**
* @author Rachit Kumar Rastogi
* @param from
* @param subject
* @param to
* @param content
* @throws IOException
* @details Send Mail functionality for sending mail via Sendgrid with mail options supplied by user.
*//*
public static void SendMailViaSendGrid(Email from, String subject, Email to, Content content) throws IOException
                {
                Mail mail = new Mail(from, subject, to, content);
                SendGrid sg = new SendGrid("SENDGRID_API_KEY");
                Request request = new Request();
                try {
                   request.method = Method.POST;
                   request.endpoint = "mail/send";
                   request.body = mail.build();
                   Response response = sg.api(request);
                   System.out.println(response.statusCode);
                } catch (IOException ex) {
                   throw ex;
                }
                }
 
*//**
* @author Rachit Kumar Rastogi
* @throws IOException
* @details Send Mail functionality for sending mail via Sendgrid (Caution: Overloaded).
*//*
 
public static void SendMailViaSendGrid() throws IOException
{
Email from = new Email("MSGTesting@msg.com");
String subject = "Jenkins Error - Rockettes Regression Script is failed!";
Email to = new Email("rachitkumar.rastogi@msg.com;");
Content content = new Content("text/html", "<html><body>Hi,<br>This is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"<br>Thanks, <br>Your MSG QA Automation Team.</body></html>");
Mail mail = new Mail(from, subject, to, content);
SendGrid sg = new SendGrid("SENDGRID_API_KEY");
Request request = new Request();
try {
   request.method = Method.POST;
   request.endpoint = "mail/send";
   request.body = mail.build();
   Response response = sg.api(request);
   System.out.println(response.statusCode);
} catch (IOException ex) {
   throw ex;
}
}
 
*//**
* @author Rachit Kumar Rastogi
* @throws IOException
* @details Send Mail functionality for sending mail via Sendgrid (Caution: Overloaded with Email BODY of URLs).
*//*
 
public static void SendMailViaSendGrid(String EmailBodyPart) throws IOException
{
Email from = new Email("MSGTesting@msg.com");
String subject = "Jenkins Error - Rockettes Regression Script is failed!";
Email to = new Email("rachitkumar.rastogi@msg.com;");
Content content = new Content("text/html", "<html><body>Hi,<br>This is to bring to your notice that Rockettes Regression Script has been failed at "+java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())+"."+"<br>"+EmailBodyPart+"<br>Thanks, <br>Your MSG QA Automation Team.</body></html>");
Mail mail = new Mail(from, subject, to, content);
SendGrid sg = new SendGrid("SENDGRID_API_KEY");
Request request = new Request();
try {
   request.method = Method.POST;
   request.endpoint = "mail/send";
   request.body = mail.build();
   Response response = sg.api(request);
   System.out.println(response.statusCode);
} catch (IOException ex) {
   throw ex;
}
}
*/
/**
* @author Rachit Kumar Rastogi
* @details SMTP Mailer class - need to pass the credential of smtp server.
*/
@SuppressWarnings("unused")
private class SMTPEmailer extends Authenticator
{
    private PasswordAuthentication authentication;
 
    public SMTPEmailer(String login, String password)
    {
         authentication = new PasswordAuthentication(login, password);
    }
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication()
    {
         return authentication;
    }
 
    /**
     * @author Rachit Kumar Rastogi
     * @param myEmailUser
     * @param myEmailAccessKey
     * @param EmailFrom
     * @param EmailTo
     * @param EmailBody
     * @param EmailSubject
     * @param EmailFailedBody
     */
    public void sendMailviaSMTP(String myEmailUser, String myEmailAccessKey, String EmailFrom, String EmailTo, String EmailBody, String EmailSubject, String EmailFailedBody)
    {
                try
                    {
                        Properties myEmailProperties = new Properties();
                        myEmailProperties.setProperty("mail.host", "smtp.gmail.com");
                        myEmailProperties.setProperty("mail.smtp.port", "587");
                        myEmailProperties.setProperty("mail.smtp.auth", "true");
                        myEmailProperties.setProperty("mail.smtp.starttls.enable", "true");
 
                        Authenticator myEmailAuthenticator = new SMTPEmailer(myEmailUser, myEmailAccessKey);
                        Session mySession = Session.getInstance(myEmailProperties, myEmailAuthenticator);
                        MimeMessage myMimeMessage = new MimeMessage(mySession);
 
                       try
                       {
                                    myMimeMessage.setSubject(EmailSubject);
                                    myMimeMessage.setText(EmailBody);   
                                    myMimeMessage.setFrom(new InternetAddress(EmailFrom));
                                   String[] myEmailToAddresses = EmailTo.split(";");  
                                    for (int count=0;count<myEmailToAddresses.length;count++)
                                    {
                                                myMimeMessage.addRecipient(Message.RecipientType.TO,
                                                new InternetAddress(myEmailToAddresses[count]));
                                    }
                                    Transport.send(myMimeMessage);
                                    System.out.println("Mail notification sent.");
                       }
                       catch (MessagingException ex)
                       {
                                   System.out.println("Mail not sent!!");
                                   Logger.getLogger(MailClient.class.getName()).
                           log(Level.SEVERE, null, ex);
                       }
                    }
                  finally
                  {
                                  //
                  }
    }
}
 
                }