package csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

public class CSV {

  private List<String> header;
  private List<CSVRecord> records;

  private static List<String> defaultHeader(int n) {
    List<String> header = new ArrayList<>();
    for (char c = 'A'; n > 0 && c < 'Z'; c++, n--) {
      header.add(Character.toString(c));
    }
    return header;
  }

  public CSV(int m, int n) {
    this.header = defaultHeader(m);
    this.records = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      this.addRecord();
    }
  }

  public CSV(CSVParser parser) throws IOException {
    this.header = new ArrayList<>(parser.getHeaderMap().keySet());
    this.records = parser.getRecords();
    parser.close();
  }

  public CSV(String csv) throws IOException {
    this(CSVParser.parse(
          csv,
          CSVFormat.EXCEL.withHeader()));
  }

  public CSV(File file) throws IOException {
    this(CSVParser.parse(
          file,
          Charset.defaultCharset(),
          CSVFormat.EXCEL.withHeader()));
  }

  public String get(int i, int j) {
    return this.records.get(i).get(j);
  }

  public List<String> getHeader() {
    return this.header;
  }

  public int rowCount() {
    return this.records.size();
  }

  public int colCount() {
    return this.header.size();
  }

  public void addRecord(int position, String record) {
    try {
      String header = StringUtils.join(this.header, ',');
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
  public void addRecord(int position, List<String> values) {
    this.addRecord(position, StringUtils.join(values, ','));
  }

  public void addRecord() {
    this.addRecord(0);
  }

  public void addRecord(int position) {
    this.addRecord(position, StringUtils.repeat("", ",", this.header.size()));
  }

  public void addRecord(int position, String... values) {
    this.addRecord(position, Arrays.asList(values));
  }

  public void deleteRecord(int position) {
    this.records.remove(position);
  }

  public void updateRecord(int position, int field, String value) {
    List<String> values = recordValues(this.records.get(position));
    values.set(field, value);
    this.deleteRecord(position);
    this.addRecord(position, values);
  }

  public List<String> colValues(String colName) {
    List<String> values = new ArrayList<>();
    for (CSVRecord record : this.records) {
      values.add(record.get(colName));
    }
    return values;
  }

  public void writeToFile(String path) throws IOException {
    CSVPrinter printer = new CSVPrinter(
        new FileWriter(new File(path)),
        CSVFormat.EXCEL);
    printer.printRecord(this.header);
    printer.printRecords(this.records);
    printer.close();
  }

  public String headerString() {
    StringBuilder header = new StringBuilder();
    for (String field : this.header) {
      header.append(field);
      header.append(',');
    }
    return header.toString();
  }

  public boolean isEmpty() {
    return this.records.isEmpty();
  }

  public String toString() {
    StringBuilder s = new StringBuilder(StringUtils.join(this.header, ','));
    for (CSVRecord record : this.records) {
      s.append("\n");
      s.append(StringUtils.join(recordValues(record), ','));
    }
    return s.toString();
  }

  public String toHtmlString() {
    StringBuilder s = new StringBuilder("<table cellpadding=\"5\">\n");

    // Header
    s.append("\t<tr bgcolor=\"#F1F1F1\">\n");
    for (String field : this.header) {
      s.append("\t\t<td>");
      s.append(field);
      s.append("</td>\n");
    }
    s.append("\t</tr>\n");

    // Records
    for (CSVRecord record : this.records) {
      s.append("\t<tr>\n");
      for (String field : record) {
        s.append("\t\t<td>");
        s.append(field);
        s.append("</td>\n");
      }
      s.append("\t</tr>\n");
    }

    s.append("</table>\n");

    return s.toString();
  }

  public static List<String> recordValues(CSVRecord record) {
    List<String> values = new ArrayList<>();
    for (String value : record) {
      values.add(value);
    }
    return values;
  }
}

