package gui;

import csv.CSV;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class EmailPanel extends JPanel {

  private JTextField from, to, subject;
  private JTextArea body;
  private JPasswordField password;
  private JButton send;
  private CSV csv;

  public EmailPanel(CSV csv) {
    this.csv = csv;

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JLabel fromLabel = new JLabel("Your Email");
    fromLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel passwordLabel = new JLabel("Your Password");
    passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel toLabel = new JLabel("Recipient Email");
    toLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel subjectLabel = new JLabel("Email Subject");
    subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel bodyLabel = new JLabel("Email Body");
    bodyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.from = new JTextField(20);
    this.from.setHorizontalAlignment(JTextField.CENTER);
    this.from.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.password = new JPasswordField(20);
    this.password.setHorizontalAlignment(JPasswordField.CENTER);
    this.password.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.to = new JTextField(20);
    this.to.setHorizontalAlignment(JTextField.CENTER);
    this.to.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.subject = new JTextField(20);
    this.subject.setHorizontalAlignment(JTextField.CENTER);
    this.subject.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.body = new JTextArea(20, 5);
    this.body.setLineWrap(true);
    this.body.setWrapStyleWord(true);
    this.body.setAlignmentX(Component.CENTER_ALIGNMENT);

    this.send = new JButton("Send");
    this.send.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.send.addActionListener(
        new SendEmailListener(
          this.csv, from, password, to, subject, body, this));

    this.add(fromLabel);
    this.add(this.from);

    this.add(passwordLabel);
    this.add(this.password);

    this.add(toLabel);
    this.add(this.to);

    this.add(subjectLabel);
    this.add(this.subject);

    this.add(bodyLabel);
    this.add(this.body);

    this.add(this.send);
    this.add(new JLabel());
  }
}

