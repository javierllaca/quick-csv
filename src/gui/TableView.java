package gui;

import csv.CSV;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableView extends JFrame {

  public TableView() {
    this.getContentPane().setLayout(
        new BoxLayout(
          this.getContentPane(),
          BoxLayout.Y_AXIS));

    CSVTableModel model = new CSVTableModel(new CSV(5, 10));
    JTable table = new JTable(model);

    model.addTableModelListener(
        new UpdateTableListener(
          table,
          model));

    this.setJMenuBar(makeMenu(table, this));

    this.add(new JScrollPane(table));

    //this.addWindowListener(new ExitSaveListener(this.csv, path));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.pack();
    this.setLocationRelativeTo(null); // center frame
    this.setVisible(true);
  }

  public static JMenuBar makeMenu(JTable table, Component parent) {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu, submenu;
    JMenuItem menuItem;

    // File Menu
    menu = new JMenu("File");

    menuItem = new JMenuItem("Open");
    menuItem.addActionListener(
        new OpenFileListener(
          table,
          parent));
    menu.add(menuItem);

    menuItem = new JMenuItem("Save");
    menu.add(menuItem);

    menuItem = new JMenuItem("Email");
    menu.add(menuItem);

    menuBar.add(menu);

    // Edit Menu
    menu = new JMenu("Edit");
    menuBar.add(menu);

    menuItem = new JMenuItem("Add Row(s) Above");
    menuItem.addActionListener(
        new AddRowListener(
          table,
          true));
    menu.add(menuItem);

    menuItem = new JMenuItem("Add Row(s) Below");
    menuItem.addActionListener(
        new AddRowListener(
          table,
          false));
    menu.add(menuItem);

    menuItem = new JMenuItem("Delete Selected Row(s)");
    menuItem.addActionListener(
        new DeleteRowListener(
          table));
    menu.add(menuItem);

    menuBar.add(menu);

    return menuBar;
  }
}

