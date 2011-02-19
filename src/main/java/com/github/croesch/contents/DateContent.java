package com.github.croesch.contents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * This document provides the mechanism to accept online date values.
 * 
 * @author croesch
 * @since Date: 27.01.2011 18:39:41
 */
public class DateContent extends RegexContent {

  /** generated serial version UID */
  private static final long serialVersionUID = -5689505532696673515L;

  /** the string representation of the current date */
  private final String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //$NON-NLS-1$

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
  public final void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    if (isValidInput(offs, str)) {
      super.insertString(offs, str, a);
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
