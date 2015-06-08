package gui.controller;

import csv.CSV;
import email.EmailClient;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class SendEmailListener implements ActionListener {

  private CSV csv;
  private JTextComponent from, password, to, subject, body;
  private Component parent;

  public SendEmailListener(CSV csv, JTextComponent from, 
      JTextComponent password, JTextComponent to, 
      JTextComponent subject, JTextComponent body,
      Component parent) {
    this.csv = csv;
    this.from = from;
    this.password = password;
    this.to = to;
    this.subject = subject;
    this.body = body;
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent event) {
    try {
      EmailClient client = new EmailClient(
          this.from.getText(), 
          this.password.getText(), 
          "text/html; charset=utf-8");
      client.sendEmail(
          this.to.getText(), 
          this.subject.getText(), 
          emailBody(
            this.body.getText(), 
            this.csv.toHtmlString()));
      JOptionPane.showMessageDialog(
          this.parent,
          "Email sent!");
    } catch (MessagingException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(
          this.parent,
          "Email could not be sent. Please try again.");
    }
  }

  public static String emailBody(String body, String table) {
    StringBuilder s = new StringBuilder();
    s.append("<p>\n\t");
    s.append(body);
    s.append("</p>\n\n");
    s.append(table);
    return s.toString();
  }
}

