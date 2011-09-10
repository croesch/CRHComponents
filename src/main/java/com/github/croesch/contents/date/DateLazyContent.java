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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.github.croesch.contents.date.DateSpecialChar.ValueType;
import com.github.croesch.logging.Log;

/**
 * {@link DateContent.MODE#LAZY} implementation of {@link DateContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
class DateLazyContent extends DateContent {

  /** generated */
  private static final long serialVersionUID = 530985421120602593L;

  /** list of editors that will edit the different parts of the date */
  private List<IDateLazyPartEditor> editors;

  /** text component to set the cursor */
  private final JTextComponent textComponent;

  /** the locale of the date content */
  private final Locale locale;

  /** the map that contains all available special characters */
  private final Map<String, DateSpecialChar> specialCharactersMap;

  /**
   * Creates a new {@link DateLazyContent} that gives special support for date values. The given text component is used
   * to set the cursor to the correct position. The locale is used to fetch the format for the date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param tc the text component for this document
   * @param loc the locale to fetch the date format from
   * @param specialCharsMap the map of {@link String}s and {@link DateSpecialChar} that contains all possible keys to
   *        enter to get a special date
   */
  public DateLazyContent(final JTextComponent tc, final Locale loc, final Map<String, DateSpecialChar> specialCharsMap) {
    this.specialCharactersMap = specialCharsMap;
    this.textComponent = tc;
    this.locale = loc;
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY);
  }

  @Override
  public final void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    if (str != null && str.length() > 0) {
      if (str.length() == 1) {
        performInsert(offs, str, a);
      } else {
        int o = offs;
        for (int i = 0; i < str.length(); ++i) {
          if (this.textComponent != null) {
            insertString(o, str.substring(i, i + 1), a);
            o = this.textComponent.getCaretPosition();
          } else {
            insertString(o + i, str.substring(i, i + 1), a);
          }
        }
      }
    }
  }

  /**
   * Performs the insertion of a string not <code>null</code> and being one char long.
   * 
   * @author croesch
   * @since Date: Jul 4, 2011
   * @param offs the offset where to insert basically
   * @param str the string to insert, not <code>null</code>, single character
   * @param a the {@link AttributeSet}.
   * @throws BadLocationException if inserted on an invalid position
   */
  private void performInsert(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    int startPos = 0;
    int tmpOfss = offs;
    boolean inserted = false;

    for (int i = 0; i < this.editors.size() && !inserted; ++i) {
      final IDateLazyPartEditor editor = this.editors.get(i);

      if (tmpOfss - editor.getSize() < 0) {
        final int z = editor.enterValue(str, tmpOfss);
        if (z == -1) {
          tmpOfss = editor.getSize(); // pass to next editor
        } else {
          remove(0, getLength());
          super.insertString(0, getDateContent(), a);

          if (this.textComponent != null) {
            this.textComponent.setCaretPosition(tmpOfss + startPos + z);
          }
          inserted = true;
        }
      }

      tmpOfss -= editor.getSize();
      startPos += editor.getSize();
    }

    // we were not able to insert this character, so maybe we have a special char
    if (!inserted) {
      performEnterOfSpecialChar(a, this.specialCharactersMap.get(str));
    }
  }

  /**
   * Performs to enter the given special character.
   * 
   * @since Date: Sep 8, 2011
   * @param a the {@link AttributeSet} to enter text in the field
   * @param sc the special character to enter
   * @throws BadLocationException if something went wrong
   */
  private synchronized void performEnterOfSpecialChar(final AttributeSet a, final DateSpecialChar sc)
                                                                                                     throws BadLocationException {
    if (sc != null) {
      final Calendar cal = new GregorianCalendar();
      int year = 1, month = 1, day = 1;
      for (final IDateLazyPartEditor e : this.editors) {
        if (e instanceof DateLazyYearEditor) {
          year = Integer.parseInt(e.getValue());
        } else if (e instanceof DateLazyMonEditor) {
          month = Integer.parseInt(e.getValue());
        } else if (e instanceof DateLazyDayEditor) {
          day = Integer.parseInt(e.getValue());
        }
      }

      // perform the update of the current date with values fetched from special char
      year = calculateNewValue(year, cal.get(Calendar.YEAR), sc.getYearValue(), sc.getYearValueType());
      month = calculateNewValue(month, cal.get(Calendar.MONTH) + 1, sc.getMonthValue(), sc.getMonthValueType()) - 1;
      day = calculateNewValue(day, cal.get(Calendar.DAY_OF_MONTH), sc.getDayValue(), sc.getDayValueType());

      // calculate valid date from special char
      cal.set(year, month, day);
      // set the calculated date
      setDate(cal.getTime());
      // insert the date into the text field
      remove(0, getLength());
      super.insertString(0, getDateContent(), a);
    }
  }

  /**
   * Calculates the new value based on the given data.
   * 
   * @since Date: Sep 8, 2011
   * @param curVal the current value of the field
   * @param todayVal the value of the field in todays date
   * @param value the value for the field fetched from special character
   * @param type the type of the value fetched from the special character
   * @return the calculated new value
   */
  private int calculateNewValue(final int curVal, final int todayVal, final int value, final ValueType type) {
    switch (type) {
      case CONSTANT:
        return value;
      case OFFSET:
        return todayVal + value;
      default: //increment
        return curVal + value;
    }
  }

  @Override
  public final String getDateContent() {
    final StringBuilder sb = new StringBuilder();
    for (final IDateLazyPartEditor e : this.editors) {
      sb.append(e.getValue());
    }
    return sb.toString();
  }

  @Override
  public final Date getDate() {
    final Calendar cal = new GregorianCalendar();
    int day = 1;
    int month = 1;
    int year = 1;
    for (final IDateLazyPartEditor e : this.editors) {
      if (e instanceof DateLazyYearEditor) {
        year = Integer.parseInt(e.getValue());
      } else if (e instanceof DateLazyMonEditor) {
        month = Integer.parseInt(e.getValue()) - 1;
      } else if (e instanceof DateLazyDayEditor) {
        day = Integer.parseInt(e.getValue());
      }
    }

    cal.set(year, month, day);
    return cal.getTime();
  }

  @Override
  public final void setDate(final Date d) {
    try {
      remove(0, getLength());
    } catch (final BadLocationException e) {
      Log.error(e);
    }

    final Calendar cal = new GregorianCalendar();
    cal.setTime(d);
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY, cal.get(Calendar.DAY_OF_MONTH),
                                                  cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
  }
}
