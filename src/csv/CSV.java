package csv;

import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;

import java.nio.charset.Charset;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.lang3.StringUtils;

/**
 * Wrapper class for Apache Commons CSV objects 
 *
 * @author Javier Llaca
 */
public class CSV {

  private Set<String> header;

  /**
   * Instantiated as an ArrayList for fast indexed retrieval
   */
  private List<CSVRecord> records;

  public CSV(String csv) {
    try {
      CSVParser parser = CSVParser.parse(
          csv,
          CSVFormat.EXCEL.withHeader());
      this.header = parser.getHeaderMap().keySet();
      this.records = parser.getRecords();
      parser.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public CSV(File file) {
    try {
      CSVParser parser = CSVParser.parse(
          file,
          Charset.defaultCharset(),
          CSVFormat.EXCEL.withHeader());
      this.header = parser.getHeaderMap().keySet();
      this.records = parser.getRecords();
      parser.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addRecord(int position, String... values) {
    try {
      String header = StringUtils.join(this.header, ',');
      String record = StringUtils.join(values, ',');
      CSVParser parser = CSVParser.parse(
          header + "\n" + record,
          CSVFormat.EXCEL.withHeader());
      CSVRecord newRecord = parser.getRecords().get(0);
      if (newRecord.isConsistent()) {
        this.records.add(position, newRecord);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteRecord(int position) {
    this.records.remove(position);
  }

  /**
   * Returns a list with all the values of a column in the csv
   *
   * @param colName Column header name
   */
  public List<String> colValues(String colName) {
    List<String> values = new LinkedList<String>();
    for (CSVRecord record : this.records) {
      values.add(record.get(colName));
    }
    return values;
  }

  public void writeToFile(String path) {
    try {
      CSVPrinter printer = new CSVPrinter(
          new FileWriter(new File(path)),
          CSVFormat.EXCEL);
      printer.printRecord(this.header);
      printer.printRecords(this.records);
      printer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String headerString() {
    StringBuilder header = new StringBuilder();
    for (String field : this.header) {
      header.append(field);
      header.append(',');
    }
    return header.toString();
  }

  public String toString() {
    StringBuilder s = new StringBuilder(StringUtils.join(this.header, ','));
    for (CSVRecord record : this.records) {
      s.append("\n");
      for (int i = 0; i < record.size() - 1; i++) {
        s.append(record.get(i));
        s.append(',');
      }
      if (record.size() > 0) {
        s.append(record.get(record.size() - 1));
      }
    }
    return s.toString();
  }
}
