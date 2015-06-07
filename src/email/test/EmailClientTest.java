package email.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import email.EmailClient;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class EmailClientTest {

  @Test(expected = AuthenticationFailedException.class)
  public void constructor() throws MessagingException {
    EmailClient client = new EmailClient(
        "alice@gmail.com",
        "password",
        "text/html; charset=utf-8");
    client.sendEmail(
        "bob@gmail.com",
        "subject",
        "body");
  }
}
