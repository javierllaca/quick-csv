package gui;

import csv.CSV;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class AddRowListener implements ActionListener {

  private JTable table;
  private int direction;

  public AddRowListener(JTable table, boolean above) {
    this.table = table;
    this.direction = above ? 0 : 1;
  }

  public void actionPerformed(ActionEvent e) {
    CSVTableModel model = (CSVTableModel) this.table.getModel();
    int[] selectedRows = this.table.getSelectedRows();
    int index = (this.direction == 0) ? // set to first or last row
      selectedRows[0] : 
      selectedRows[selectedRows.length - 1];
    for (int i = 0; i < selectedRows.length; i++) {
      model.addRow(index + this.direction);
    }
    this.table.updateUI();
  }
}

