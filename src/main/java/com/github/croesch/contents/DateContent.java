package com.github.croesch.contents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * This document provides the mechanism to accept only date values.
 * 
 * @author croesch
 * @since Date: 27.01.2011 18:39:41
 */
public class DateContent extends RegexContent {

  /** generated serial version UID */
  private static final long serialVersionUID = -5689505532696673515L;

  private final Calendar cal = new GregorianCalendar();

  private String dateFormat = "%04d-%02d-%02d";

  /** the number of the current year */
  private final int currentYear = this.cal.get(Calendar.YEAR);

  /** the number of the current month */
  private final int currentMonth = this.cal.get(Calendar.MONTH) + 1;

  /** the number of the current day */
  private final int currentDay = this.cal.get(Calendar.DAY_OF_MONTH);

  /** the string representation of the current date */
  private String today = formatDate(this.currentYear, this.currentMonth, this.currentDay);

  /** the written content */
  private String date = null;

  /**
   * Constructs a document with maximum input length >10< and that is not displaying errors
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:48:31
   */
  public DateContent() {
    super("[0-9\\-]{0,10}"); //$NON-NLS-1$
    final int maxInput = 10;

    setErrosNotifying(false);
    setMaximumInputLength(maxInput);
  }

  /**
   * Constructs a date document and inserts the given value. If it's not a valid value nothing will be inserted
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:48:57
   * @param initial the value to insert
   */
  public DateContent(final String initial) {
    this();
    try {
      insertString(0, initial, null);
    } catch (final BadLocationException e) {
      throw new AssertionError(); // should never happen
    }
  }

  /**
   * Formats the given values to the format of the date.
   * 
   * @author croesch
   * @since Date: Mar 30, 2011 9:19:13 PM
   * @param day the number of the day of the generated date
   * @param month the number of month of the generated date
   * @param year the number of the year of the generated date
   * @return a string representation of the formated date
   * @see #setDateFormat(String)
   */
  public final String formatDate(final int year, final int month, final int day) {
    return String.format(this.dateFormat, year, month, day);
  }

  /**
   * Sets the format to format the date.
   * 
   * @author croesch
   * @since Date: Mar 30, 2011 9:23:42 PM
   * @param format the new format of the date.
   * @see #formatDate(int, int, int)
   */
  protected final void setDateFormat(final String format) {
    if (format != null) {
      this.dateFormat = format;
      this.today = formatDate(this.currentYear, this.currentMonth, this.currentDay);
    }
  }

  /**
   * Returns the current value of the document. There is the special behaviour that it will return a String with length
   * >10< if the current input is not long enough the string will be filled up with the characters from the current
   * date.
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:50:13
   * @return a String with length: 10
   */
  public final String getDate() {
    if (this.date != null) {
      if (this.date.length() < getMaximumInputLength()) {
        return this.date + this.today.substring(this.today.length() + this.date.length() - getMaximumInputLength());
      }
      return this.date;
    }
    return this.today;
  }

  /**
   * @return {@code true}, if {@link #getDate()} returns a valid date.
   */
  @Override
  public final boolean isValid() {
    final String txt = getDate();
    if (txt == null) {
      return false;
    }
    try {
      final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$
      final Date parsed = sdf.parse(txt);
      return txt.equals(sdf.format(parsed));
    } catch (final ParseException e) {
      return false;
    }
  }

  @Override
  protected final boolean isValidSpecialInput(final String str) {
    return "d".equals(str);
  }

  @Override
  public final void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    if (isValidInput(offs, str)) {
      if ("d".equals(str)) {
        super.remove(0, getLength());
        super.insertString(0, this.today, a);
      } else {
        super.insertString(offs, str, a);
      }
      this.date = getText();
    }
  }

  @Override
  public final void remove(final int offs, final int len) throws BadLocationException {
    super.remove(offs, len);
    this.date = getText();
  }

  @Override
  public final void replace(final int offset, final int length, final String text, final AttributeSet attrs)
                                                                                                            throws BadLocationException {
    final String newText = getText(0, offset) + text + getText(offset + length, getLength() - offset - length);
    if (isValidInput(newText)) {
      super.replace(offset, length, text, attrs);
    }
  }
}
