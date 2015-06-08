package csv.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import java.util.LinkedList;
import csv.CSV;

public class CSVTest {

  String csvString =
    "Name,Amount,Notes\n" +
    "One,1.0,Note #1\n" +
    "Two,2.0,Note #2\n" +
    "Three,3.0,Note #3";

  CSV csv;

  @Test
  public void constructor() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(csvString, csv.toString());
  }

  @Test
  public void colValues() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(
        "[One, Two, Three]",
        csv.colValues("Name").toString());
  }

  @Test
  public void addRecordArray() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    csv.addRecord(0, "Zero", "0.0", "Note #0");
    assertEquals(
        "Name,Amount,Notes\n" +
        "Zero,0.0,Note #0\n" +
        "One,1.0,Note #1\n" +
        "Two,2.0,Note #2\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }

  @Test
  public void addRecordList() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<String> values = new LinkedList<>();
    values.add("Zero");
    values.add("0.0");
    values.add("Note #0");
    csv.addRecord(0, values);
    assertEquals(
        "Name,Amount,Notes\n" +
        "Zero,0.0,Note #0\n" +
        "One,1.0,Note #1\n" +
        "Two,2.0,Note #2\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }

  @Test
  public void addRecordEmpty() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    csv.addRecord(1);
    assertEquals(
        "Name,Amount,Notes\n" +
        "One,1.0,Note #1\n" +
        ",,\n" +
        "Two,2.0,Note #2\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }

  @Test
  public void deleteRecord() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    csv.deleteRecord(1);
    assertEquals(
        "Name,Amount,Notes\n" +
        "One,1.0,Note #1\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }

  @Test
  public void updateRecord() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    csv.updateRecord(0, 1, "1.1");
    assertEquals(
        "Name,Amount,Notes\n" +
        "One,1.1,Note #1\n" +
        "Two,2.0,Note #2\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }

  @Test
  public void toHtmlString() {
    try {
      csv = new CSV(csvString);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertEquals(
        "<table cellpadding=\"5\">\n" +
        "\t<tr bgcolor=\"#F1F1F1\">\n" +
        "\t\t<td>Name</td>\n" +
        "\t\t<td>Amount</td>\n" +
        "\t\t<td>Notes</td>\n" +
        "\t</tr>\n" +
        "\t<tr>\n" +
        "\t\t<td>One</td>\n" +
        "\t\t<td>1.0</td>\n" +
        "\t\t<td>Note #1</td>\n" +
        "\t</tr>\n" +
        "\t<tr>\n" +
        "\t\t<td>Two</td>\n" +
        "\t\t<td>2.0</td>\n" +
        "\t\t<td>Note #2</td>\n" +
        "\t</tr>\n" +
        "\t<tr>\n" +
        "\t\t<td>Three</td>\n" +
        "\t\t<td>3.0</td>\n" +
        "\t\t<td>Note #3</td>\n" +
        "\t</tr>\n" +
        "</table>\n",
      csv.toHtmlString());
  }
}
