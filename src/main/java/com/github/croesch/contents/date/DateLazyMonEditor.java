package com.github.croesch.contents.date;

import java.util.Calendar;

/**
 * An <i>lazy</i> editor for the month part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyMonEditor implements IDateLazyPartEditor {

  /** constant for number ten */
  private static final int TEN = 10;

  /** constant of the highest value */
  private static final int HIGHEST = 12;

  /** the current value */
  private final char[] value = { '0', '0' };

  /**
   * Constructs a new lazy date editor with the value of the current month.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @see #DateLazyDayEditor(int)
   */
  DateLazyMonEditor() {
    this(0);
  }

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than 12 the current month will be the
   *        initial value.
   * @see #DateLazyDayEditor()
   */
  DateLazyMonEditor(final int initial) {
    int month = initial;
    if (month <= 0 || month > HIGHEST) {
      month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    }
    final int first = month / TEN;
    final int second = month % TEN;
    this.value[0] = (char) ('0' + first);
    this.value[1] = (char) ('0' + second);
  }

  @Override
  public int getSize() {
    return 2;
  }

  @Override
  public int enterValue(final String s, final int position) {
    if (s != null && s.length() == 1) {

      if ("2".equals(s) || "3".equals(s) || "4".equals(s) || "5".equals(s) || "6".equals(s) || "7".equals(s)
          || "8".equals(s) || "9".equals(s)) {
        if (position == 0) {
          this.value[1] = s.charAt(0);
          return 2;
        }
        if (position == 1) {
          this.value[1] = s.charAt(0);
          return 1;
        }
      }
      if ("0".equals(s) || "1".equals(s)) {
        if (position == 0 || position == 1) {
          this.value[position] = s.charAt(0);
          return 1;
        }
      }
    }
    return -1;
  }

  @Override
  public String getValue() {
    return String.valueOf(this.value, 0, 2);
  }

  @Override
  public String toString() {
    return getValue();
  }
}
