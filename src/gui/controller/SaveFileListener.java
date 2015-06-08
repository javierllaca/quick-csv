package gui.controller;

import csv.CSV;
import gui.model.CSVTableModel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFileChooser;

public class SaveFileListener implements ActionListener {

  private JTable table;
  private Component parent;

  public SaveFileListener(JTable table, Component parent) {
    this.table = table;
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent event) {
    JFileChooser chooser = new JFileChooser();
    int returnVal = chooser.showSaveDialog(this.parent);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      CSV csv = ((CSVTableModel) this.table.getModel()).getCSV();
      try {
        csv.writeToFile(chooser.getSelectedFile().getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            this.parent,
            "Could not save file. Please try again.");
      }
    }
  }
}

