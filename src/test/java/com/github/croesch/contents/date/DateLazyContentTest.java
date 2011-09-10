/*
 * Copyright (C) 2011  Christian Roesch
 * 
 * This file is part of crhcomponents.
 * 
 * crhcomponents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * crhcomponents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with crhcomponents.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.contents.date;

import static com.github.croesch.TestUtil.assertDateHasValues;
import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.BadLocationException;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link DateLazyContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateLazyContentTest extends FestSwingJUnitTestCase {

  private DateLazyContent content;

  private int todaysDay;

  private int todaysMonth;

  private int todaysYear;

  private final Map<String, DateSpecialChar> specCharMapEmpty = new DateSpecialCharInterpreter(new BufferedReader(new InputStreamReader(DateContent.class
    .getClassLoader().getResourceAsStream("datechars_empty.cfg")))).getSpecialCharsMap();

  /**
   * Sets up the {@link DateLazyContent}.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  @Override
  public void onSetUp() {
    this.content = new DateLazyContent(null, Locale.GERMAN, this.specCharMapEmpty);

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
  }

  @Test
  public void testInsertString_DMY_Slash() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    this.content = new DateLazyContent(null, Locale.CANADA, this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_DMY_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    this.content = new DateLazyContent(null, new Locale("mk"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YMD_Slash() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    this.content = new DateLazyContent(null, new Locale("ga"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_DMY_Slash2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    this.content = new DateLazyContent(null, new Locale("it"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_MDY() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    this.content = new DateLazyContent(null, new Locale("en"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YMD_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    this.content = new DateLazyContent(null, new Locale("sv"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YMD_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
    this.content = new DateLazyContent(null, new Locale("hu"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_DMY_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    this.content = new DateLazyContent(null, new Locale("nl"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_DMY_Point2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
    this.content = new DateLazyContent(null, new Locale("sr"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YMD_Point2() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
    this.content = new DateLazyContent(null, new Locale("lt"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YDM_Point() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy.dd.MM");
    this.content = new DateLazyContent(null, new Locale("lv"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_MDY_Minus() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
    this.content = new DateLazyContent(null, new Locale("es", "NI"), this.specCharMapEmpty);
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
  }

  @Test
  public void testInsertString_YMD_Point3() throws BadLocationException {
    final DateFormat df = new SimpleDateFormat("yyyy. MM. dd");
    this.content = new DateLazyContent(null, new Locale("ko"), this.specCharMapEmpty);
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
  }
}
