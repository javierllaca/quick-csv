package email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailClient {

  private Properties props;
  private Session session;
  private String username, format;

  public EmailClient(String username, String password, String format) {
    this.username = username;
    this.format = format;

    this.props = new Properties();
    this.props.put("mail.smtp.starttls.enable", "true");
    this.props.put("mail.smtp.auth", "true");
    this.props.put("mail.smtp.host", "smtp.gmail.com");
    this.props.put("mail.smtp.port", "587");

    this.session = Session.getInstance(this.props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });
  }

  public void sendEmail(String to, String subject, String body) throws MessagingException {
    Message msg = new MimeMessage(this.session);
    msg.setFrom(new InternetAddress(this.username));
    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    msg.setSubject(subject);
    msg.setContent(body, this.format);
    Transport.send(msg);
  }
}
