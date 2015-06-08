package gui;

import csv.CSV;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DeleteRowListener implements ActionListener {

  private JTable table;

  public DeleteRowListener(JTable table) {
    this.table = table;
  }

  public void actionPerformed(ActionEvent e) {
    CSVTableModel model = (CSVTableModel) this.table.getModel();
    int[] selectedRows = this.table.getSelectedRows();
    for (int i = 0; i < selectedRows.length; i++) {
      model.deleteRow(selectedRows[i]);
      for (int j = i + 1; j < selectedRows.length; j++) {
        selectedRows[j]--;
      }
    }
    this.table.updateUI();
  }
}

