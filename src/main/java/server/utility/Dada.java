package server.utility;

import server.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Dada {


    public static boolean sendEmail(User user, EmailPurpose emailPurpose, String[] additionalContent) {
        // Sender's email ID needs to be mentioned
        String from = "GarchingAirlines@gmail.com";

        String to = user.getEmail();

        emailPurpose.setUser(user.getUsername());

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "ukvivtewatnjrkhs");
            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(emailPurpose.getSubject());

            // Now set the actual message
            message.setContent(emailPurpose.getContent(additionalContent),
                    "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            return false;
        }

        return true;
    }


    public static void main(String args[]) {

        // Recipient's email ID needs to be mentioned.
        String to = "georgemerezas@gmail.com";

        //System.out.println(sendEmail(to, EmailPurpose.REGISTRATION, new String[]{"username", "A57BCD"}));
    }
}
