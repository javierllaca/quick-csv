package gui;

import csv.CSV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;

public class EmailDialogListener implements ActionListener {

  private JTable table;

  public EmailDialogListener(JTable table) {
    this.table = table;
  }

  public void actionPerformed(ActionEvent e) {
    JFrame dialog = new EmailDialog(
        ((CSVTableModel) this.table.getModel()).getCSV());
  }
}

