package gui.view;

import csv.CSV;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;

public class EmailView extends JFrame {

  private CSV csv;
  private JButton send;

  public EmailView(CSV csv) {
    this.getContentPane().setLayout(
        new BoxLayout(
          this.getContentPane(),
          BoxLayout.Y_AXIS));

    this.csv = csv;

    this.add(new EmailPanel(this.csv));

    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }
}

