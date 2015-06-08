package gui;

import csv.CSV;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

public class UpdateTableListener implements TableModelListener {

  private CSVTableModel model;
  private JTable table;

  public UpdateTableListener(JTable table, CSVTableModel model) {
    this.table = table;
    this.model = model;
  }

  public void tableChanged(TableModelEvent event) {
    if (event.getType() == TableModelEvent.UPDATE) {
      int row = event.getFirstRow();
      int col = event.getColumn();
      String val = (String) this.table.getValueAt(row, col);
      this.model.updateRow(row, col, val);
      this.table.updateUI();
    }
  }
}

