package csv.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import csv.CSV;

public class CSVTest {

  String csvString =
    "Name,Amount,Notes\n" +
    "One,1.0,Note #1\n" +
    "Two,2.0,Note #2\n" +
    "Three,3.0,Note #3";

  @Test
  public void constructor() {
    CSV csv = new CSV(csvString);
    assertEquals(csvString, csv.toString());
  }

  @Test
  public void colValues() {
    CSV csv = new CSV(csvString);
    assertEquals(
        "[One, Two, Three]",
        csv.colValues("Name").toString());
  }

  @Test
  public void addRecord() {
    CSV csv = new CSV(csvString);
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
  public void deleteRecord() {
    CSV csv = new CSV(csvString);
    csv.deleteRecord(1);
    assertEquals(
        "Name,Amount,Notes\n" +
        "One,1.0,Note #1\n" +
        "Three,3.0,Note #3",
        csv.toString());
  }
}
