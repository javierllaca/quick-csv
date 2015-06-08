package gui;

import csv.CSV;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFileChooser;

public class SaveFileListener implements ActionListener {

  private CSV csv;
  private Component parent;

  public SaveFileListener(CSV csv, Component parent) {
    this.csv = csv;
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent event) {
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = chooser.showSaveDialog(this.parent);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        this.csv.writeToFile(
            chooser.getSelectedFile().getAbsolutePath());
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            this.parent,
            "Could not save file. Please try again.");
      }
    }
  }
}

