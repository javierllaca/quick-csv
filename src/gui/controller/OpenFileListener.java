package gui.controller;

import csv.CSV;
import gui.model.CSVTableModel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFileListener implements ActionListener {

  private JTable table;
  private Component parent;

  public OpenFileListener(JTable table, Component parent) {
    this.table = table;
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent event) {
    JFileChooser chooser = new JFileChooser();
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.setFileFilter(
        new FileNameExtensionFilter(
          "CSV Files",
          "csv"));
    int returnVal = chooser.showOpenDialog(this.parent);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        this.table.setModel(
            new CSVTableModel(
              new CSV(chooser.getSelectedFile())));
      } catch (IOException e) {
        e.printStackTrace();
      } 
    }
  }
}

