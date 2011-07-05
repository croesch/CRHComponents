package com.github.croesch.contents.date;

import static com.github.croesch.TestUtil.assertDateHasValues;
import static org.fest.assertions.Assertions.assertThat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link DateLazyContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateLazyContentTest {

  private DateLazyContent content;

  private int todaysDay;

  private int todaysMonth;

  private int todaysYear;

  /**
   * Sets up the {@link DateLazyContent}.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  @Before
  public void setUp() {
    this.content = new DateLazyContent(null, Locale.GERMAN);

    final Calendar cal = new GregorianCalendar();
    cal.setTime(new Date());
    this.todaysDay = cal.get(Calendar.DAY_OF_MONTH);
    this.todaysMonth = cal.get(Calendar.MONTH) + 1;
    this.todaysYear = cal.get(Calendar.YEAR);
  }

  @Test
  public void testInsertString() throws BadLocationException {
    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12.10.2010", null);
    assertThat(this.content.getText()).isEqualTo("12.10.2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06.12.2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10.01.1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_DMY_Slash() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    this.content = new DateLazyContent(null, Locale.CANADA);
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12/10/2010", null);
    assertThat(this.content.getText()).isEqualTo("12/10/2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06/12/2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10/01/1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_DMY_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    this.content = new DateLazyContent(null, new Locale("mk"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12.10.2010", null);
    assertThat(this.content.getText()).isEqualTo("12.10.2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06.12.2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10.01.1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YMD_Slash() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    this.content = new DateLazyContent(null, new Locale("ga"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(6, "1", null);
    this.content.insertString(7, "2", null);
    this.content.insertString(9, "2", null);
    assertThat(this.content.getText()).isEqualTo("1944/11/22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944/11/22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944/11/22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010/10/12", null);
    assertThat(this.content.getText()).isEqualTo("2010/10/12");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002/12/06");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988/01/10");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_DMY_Slash2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    this.content = new DateLazyContent(null, new Locale("it"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22/11/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12/10/2010", null);
    assertThat(this.content.getText()).isEqualTo("12/10/2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06/12/2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10/01/1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_MDY() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    this.content = new DateLazyContent(null, new Locale("en"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "1", null);
    this.content.insertString(1, "1", null);
    this.content.insertString(2, "2", null);
    this.content.insertString(4, "2", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("11/22/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("11/22/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("11/22/1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "10/12/2010", null);
    assertThat(this.content.getText()).isEqualTo("10/12/2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("12/06/2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("01/10/1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YMD_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    this.content = new DateLazyContent(null, new Locale("sv"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(6, "1", null);
    this.content.insertString(7, "2", null);
    this.content.insertString(9, "2", null);
    assertThat(this.content.getText()).isEqualTo("1944-11-22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944-11-22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944-11-22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010-10-12", null);
    assertThat(this.content.getText()).isEqualTo("2010-10-12");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002-12-06");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988-01-10");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YMD_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
    this.content = new DateLazyContent(null, new Locale("hu"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(6, "1", null);
    this.content.insertString(7, "2", null);
    this.content.insertString(9, "2", null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010.10.12.", null);
    assertThat(this.content.getText()).isEqualTo("2010.10.12.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002.12.06.");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988.01.10.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_DMY_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    this.content = new DateLazyContent(null, new Locale("nl"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22-11-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22-11-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22-11-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12-10-2010", null);
    assertThat(this.content.getText()).isEqualTo("12-10-2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06-12-2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10-01-1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_DMY_Point2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
    this.content = new DateLazyContent(null, new Locale("sr"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "12.10.2010.", null);
    assertThat(this.content.getText()).isEqualTo("12.10.2010.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("06.12.2002.");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("10.01.1988.");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YMD_Point2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
    this.content = new DateLazyContent(null, new Locale("lt"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(6, "1", null);
    this.content.insertString(7, "2", null);
    this.content.insertString(9, "2", null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944.11.22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010.10.12", null);
    assertThat(this.content.getText()).isEqualTo("2010.10.12");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002.12.06");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988.01.10");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YDM_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.dd.MM");
    this.content = new DateLazyContent(null, new Locale("lv"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "2", null);
    this.content.insertString(6, "2", null);
    this.content.insertString(7, "1", null);
    this.content.insertString(9, "1", null);
    assertThat(this.content.getText()).isEqualTo("1944.22.11");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944.22.11");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944.22.11");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010.12.10", null);
    assertThat(this.content.getText()).isEqualTo("2010.12.10");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002.06.12");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988.10.01");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_MDY_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    this.content = new DateLazyContent(null, new Locale("es", "NI"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "1", null);
    this.content.insertString(1, "1", null);
    this.content.insertString(2, "2", null);
    this.content.insertString(4, "2", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("11-22-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("11-22-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("11-22-1944");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "10-12-2010", null);
    assertThat(this.content.getText()).isEqualTo("10-12-2010");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("12-06-2002");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("01-10-1988");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }

  @Test
  public void testInsertString_YMD_Point3() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy. MM. dd");
    this.content = new DateLazyContent(null, new Locale("ko"));
    assertThat(this.content.getDateContent()).isEqualTo(df.format(new Date()));
    assertDateHasValues(this.content.getDate(), this.todaysDay, this.todaysMonth, this.todaysYear);

    this.content.insertString(0, "4", null);
    this.content.insertString(1, "4", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(7, "1", null);
    this.content.insertString(8, "2", null);
    this.content.insertString(11, "2", null);
    assertThat(this.content.getText()).isEqualTo("1944. 11. 22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("1944. 11. 22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("1944. 11. 22");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 22, 11, 1944);

    this.content.insertString(0, "2010. 10. 12", null);
    assertThat(this.content.getText()).isEqualTo("2010. 10. 12");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 12, 10, 2010);

    final Calendar cal = new GregorianCalendar();
    cal.set(2002, 11, 6);
    this.content.setDate(cal.getTime());
    assertThat(this.content.getText()).isEmpty();
    assertDateHasValues(this.content.getDate(), 6, 12, 2002);
    assertThat(this.content.getDateContent()).isEqualTo("2002. 12. 06");

    cal.set(1988, 0, 10);
    this.content.setDateAndDisplay(cal.getTime());
    assertThat(this.content.getText()).isEqualTo("1988. 01. 10");
    assertThat(this.content.getDateContent()).isEqualTo(this.content.getText());
    assertDateHasValues(this.content.getDate(), 10, 1, 1988);
  }
}
