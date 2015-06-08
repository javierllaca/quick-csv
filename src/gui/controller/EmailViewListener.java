package gui.controller;

import csv.CSV;
import gui.model.CSVTableModel;
import gui.view.EmailView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTable;

public class EmailViewListener implements ActionListener {

  private JTable table;

  public EmailViewListener(JTable table) {
    this.table = table;
  }

  public void actionPerformed(ActionEvent e) {
    JFrame dialog = new EmailView(
        ((CSVTableModel) this.table.getModel()).getCSV());
  }
}

