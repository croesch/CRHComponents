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

import static org.fest.assertions.Assertions.assertThat;

import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.BadLocationException;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides tests for the special chars defined in the special chars file.
 * 
 * @author croesch
 * @since Date: Sep 8, 2011
 */
public class DateLazyContentTest_SpecialChars extends FestSwingJUnitTestCase {

  private final Map<String, DateSpecialChar> specCharMap = DateSpecialCharInterpreter
    .createFrom(new InputStreamReader(DateContent.class.getClassLoader().getResourceAsStream("datechars.cfg")))
    .getSpecialCharsMap();

  @Test
  public void testUnknownCharacters() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "²", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "µ", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");
  }

  @Test
  public void testIncrementDay() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "+", null);
    assertThat(content.getText()).isEqualTo("13.03.1903");

    content.insertString(0, "29", null);
    assertThat(content.getText()).isEqualTo("29.03.1903");

    content.insertString(1, "+", null);
    assertThat(content.getText()).isEqualTo("30.03.1903");

    content.insertString(2, "+", null);
    assertThat(content.getText()).isEqualTo("31.03.1903");

    content.insertString(3, "+", null);
    assertThat(content.getText()).isEqualTo("01.04.1903");

    content.insertString(0, "3112", null);
    assertThat(content.getText()).isEqualTo("31.12.1903");

    content.insertString(13, "+", null);
    assertThat(content.getText()).isEqualTo("01.01.1904");

    content.insertString(0, "282", null);
    assertThat(content.getText()).isEqualTo("28.02.1904");

    content.insertString(-1, "+", null);
    assertThat(content.getText()).isEqualTo("29.02.1904");

    content.insertString(10, "+", null);
    assertThat(content.getText()).isEqualTo("01.03.1904");

    content.insertString(0, "28.02.1905", null);
    assertThat(content.getText()).isEqualTo("28.02.1905");

    content.insertString(0, "+", null);
    assertThat(content.getText()).isEqualTo("01.03.1905");
  }

  @Test
  public void testDecrementDay() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "-", null);
    assertThat(content.getText()).isEqualTo("11.03.1903");

    content.insertString(0, "01.04.", null);
    assertThat(content.getText()).isEqualTo("01.04.1903");

    content.insertString(1, "-", null);
    assertThat(content.getText()).isEqualTo("31.03.1903");

    content.insertString(2, "-", null);
    assertThat(content.getText()).isEqualTo("30.03.1903");

    content.insertString(3, "-", null);
    assertThat(content.getText()).isEqualTo("29.03.1903");

    content.insertString(0, "01.01", null);
    assertThat(content.getText()).isEqualTo("01.01.1903");

    content.insertString(13, "-", null);
    assertThat(content.getText()).isEqualTo("31.12.1902");

    content.insertString(0, "01.03.1904", null);
    assertThat(content.getText()).isEqualTo("01.03.1904");

    content.insertString(-1, "-", null);
    assertThat(content.getText()).isEqualTo("29.02.1904");

    content.insertString(10, "-", null);
    assertThat(content.getText()).isEqualTo("28.02.1904");

    content.insertString(0, "01.03.1905", null);
    assertThat(content.getText()).isEqualTo("01.03.1905");

    content.insertString(0, "-", null);
    assertThat(content.getText()).isEqualTo("28.02.1905");
  }

  @Test
  public void testToday() throws BadLocationException {
    final Calendar cal = new GregorianCalendar();
    final String year = String.format("%04d", cal.get(Calendar.YEAR));
    final String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
    final String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "d", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(0, "01.04.", null);
    assertThat(content.getText()).isEqualTo("01.04." + year);

    content.insertString(1, "d", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(2, "----d", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);
  }

  @Test
  public void testYesterday() throws BadLocationException {
    final Calendar cal = new GregorianCalendar();
    cal.setTime(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
    final String year = String.format("%04d", cal.get(Calendar.YEAR));
    final String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
    final String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "y", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(0, "01.04.", null);
    assertThat(content.getText()).isEqualTo("01.04." + year);

    content.insertString(1, "y", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(2, "----y", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);
  }

  @Test
  public void testTomorrow() throws BadLocationException {
    final Calendar cal = new GregorianCalendar();
    cal.setTime(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
    final String year = String.format("%04d", cal.get(Calendar.YEAR));
    final String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
    final String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));

    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "m", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(0, "01.04.", null);
    assertThat(content.getText()).isEqualTo("01.04." + year);

    content.insertString(1, "m", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);

    content.insertString(2, "----m", null);
    assertThat(content.getText()).isEqualTo(day + "." + month + "." + year);
  }

  @Test
  public void testIncrementMonth() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "*", null);
    assertThat(content.getText()).isEqualTo("12.04.1903");

    content.insertString(0, "29.11", null);
    assertThat(content.getText()).isEqualTo("29.11.1903");

    content.insertString(1, "*", null);
    assertThat(content.getText()).isEqualTo("29.12.1903");

    content.insertString(2, "*", null);
    assertThat(content.getText()).isEqualTo("29.01.1904");

    content.insertString(3, "*", null);
    assertThat(content.getText()).isEqualTo("29.02.1904");

    content.insertString(0, "3112", null);
    assertThat(content.getText()).isEqualTo("31.12.1904");

    content.insertString(13, "*", null);
    assertThat(content.getText()).isEqualTo("31.01.1905");

    content.insertString(0, "29.01", null);
    assertThat(content.getText()).isEqualTo("29.01.1905");

    content.insertString(-1, "*", null);
    assertThat(content.getText()).isEqualTo("01.03.1905");

    content.insertString(10, "*", null);
    assertThat(content.getText()).isEqualTo("01.04.1905");

    content.insertString(0, "31.08.1905", null);
    assertThat(content.getText()).isEqualTo("31.08.1905");

    content.insertString(0, "*", null);
    assertThat(content.getText()).isEqualTo("01.10.1905");

    content.insertString(0, "************", null);
    assertThat(content.getText()).isEqualTo("01.10.1906");
  }

  @Test
  public void testDecrementMonth() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "_", null);
    assertThat(content.getText()).isEqualTo("12.02.1903");

    content.insertString(0, "29.02", null);
    assertThat(content.getText()).isEqualTo("29.02.1903");

    content.insertString(1, "_", null);
    assertThat(content.getText()).isEqualTo("29.01.1903");

    content.insertString(2, "_", null);
    assertThat(content.getText()).isEqualTo("29.12.1902");

    content.insertString(3, "_", null);
    assertThat(content.getText()).isEqualTo("29.11.1902");

    content.insertString(0, "31.01", null);
    assertThat(content.getText()).isEqualTo("31.01.1902");

    content.insertString(13, "_", null);
    assertThat(content.getText()).isEqualTo("31.12.1901");

    content.insertString(0, "29.03", null);
    assertThat(content.getText()).isEqualTo("29.03.1901");

    content.insertString(-1, "_", null);
    assertThat(content.getText()).isEqualTo("01.03.1901");

    content.insertString(10, "_", null);
    assertThat(content.getText()).isEqualTo("01.02.1901");

    content.insertString(0, "31.10.1905", null);
    assertThat(content.getText()).isEqualTo("31.10.1905");

    content.insertString(0, "_", null);
    assertThat(content.getText()).isEqualTo("01.10.1905");

    content.insertString(0, "____________", null);
    assertThat(content.getText()).isEqualTo("01.10.1904");
  }

  @Test
  public void testIncrementYear() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, ":", null);
    assertThat(content.getText()).isEqualTo("12.03.1904");

    content.insertString(0, "29.02", null);
    assertThat(content.getText()).isEqualTo("29.02.1904");

    content.insertString(0, ":", null);
    assertThat(content.getText()).isEqualTo("01.03.1905");

    content.insertString(0, ":::::::::::::::::::::::::", null);
    assertThat(content.getText()).isEqualTo("01.03.1930");
  }

  @Test
  public void testDecrementYear() throws BadLocationException {
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, ";", null);
    assertThat(content.getText()).isEqualTo("12.03.1902");

    content.insertString(0, "29.02.1904", null);
    assertThat(content.getText()).isEqualTo("29.02.1904");

    content.insertString(0, ";", null);
    assertThat(content.getText()).isEqualTo("01.03.1903");

    content.insertString(0, ";;;;;;;;;;;;;;", null);
    assertThat(content.getText()).isEqualTo("01.03.1889");
  }

  @Test
  public void testFirstNextMonth() throws BadLocationException {
    final Calendar cal = new GregorianCalendar();
    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
    final String year = String.format("%04d", cal.get(Calendar.YEAR));
    final String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "n", null);
    assertThat(content.getText()).isEqualTo("01." + month + "." + year);

    content.insertString(0, "+++++", null);
    assertThat(content.getText()).isEqualTo("06." + month + "." + year);

    content.insertString(0, "n", null);
    assertThat(content.getText()).isEqualTo("01." + month + "." + year);

    content.insertString(0, "nnnnnnnnnnnnnnnnnnnnnnnnnn", null);
    assertThat(content.getText()).isEqualTo("01." + month + "." + year);
  }

  @Test
  public void testFirstNextYear() throws BadLocationException {
    final Calendar cal = new GregorianCalendar();
    final String year = String.format("%04d", cal.get(Calendar.YEAR) + 1);
    final DateLazyContent content = new DateLazyContent(null, Locale.GERMAN, this.specCharMap);

    content.insertString(0, "12.03.1903", null);
    assertThat(content.getText()).isEqualTo("12.03.1903");

    content.insertString(0, "N", null);
    assertThat(content.getText()).isEqualTo("01.01." + year);

    content.insertString(0, "+++++", null);
    assertThat(content.getText()).isEqualTo("06.01." + year);

    content.insertString(0, "N", null);
    assertThat(content.getText()).isEqualTo("01.01." + year);

    content.insertString(0, "NNNNNNNNNNNNNNNNNNNNNNNNNN", null);
    assertThat(content.getText()).isEqualTo("01.01." + year);
  }

  @Override
  protected void onSetUp() {
    // nothing to be setup
  }
}
