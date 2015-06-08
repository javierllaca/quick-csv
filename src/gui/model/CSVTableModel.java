package gui.model;

import csv.CSV;
import javax.swing.table.AbstractTableModel;

public class CSVTableModel extends AbstractTableModel {

  private CSV csv;

  public CSVTableModel(CSV csv) {
    this.csv = csv;
  }

  public int getRowCount() {
    return this.csv.rowCount();
  }

  public int getColumnCount() {
    return this.csv.colCount();
  }

  public void addRow(int row) {
    this.csv.addRecord(row);
  }

  public void deleteRow(int row) {
    this.csv.deleteRecord(row);
  }

  public void updateRow(int row, int col, String value) {
    this.csv.updateRecord(row, col, value);
  }

  public boolean isCellEditable(int row, int col) {
    return true;
  }

  public void setValueAt(Object val, int row, int col) {
    this.csv.updateRecord(row, col, (String) val);
    this.fireTableCellUpdated(row, col);
  }

  public String getColumnName(int col) {
    return this.csv.getHeader().get(col);
  }

  public CSV getCSV() {
    return this.csv;
  }

  public void setCSV(CSV csv) {
    this.csv = csv;
  }

  public Object getValueAt(int row, int col) {
    return this.csv.get(row, col);
  }
}

