package com.github.croesch.contents.date;

import java.util.Arrays;
import java.util.Calendar;

/**
 * An <i>lazy</i> editor for the day part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyDayEditor implements IDateLazyPartEditor {

  /** constant for number ten */
  private static final int TEN = 10;

  /** constant of the highest value */
  private static final int HIGHEST = 31;

  /** the current value */
  private final char[] value = { '0', '0' };

  /**
   * Constructs a new lazy date editor with the value of the current day.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @see #DateLazyDayEditor(int)
   */
  DateLazyDayEditor() {
    this(0);
  }

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than 31 the current day will be the
   *        initial value.
   * @see #DateLazyDayEditor()
   */
  DateLazyDayEditor(final int initial) {
    int day = initial;
    if (day <= 0 || day > HIGHEST) {
      day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    final int first = day / TEN;
    final int second = day % TEN;
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

      if ("4".equals(s) || "5".equals(s) || "6".equals(s) || "7".equals(s) || "8".equals(s) || "9".equals(s)) {
        if (position == 0) {
          this.value[1] = s.charAt(0);
          return 2;
        }
        if (position == 1) {
          this.value[1] = s.charAt(0);
          return 1;
        }
      }
      if ("0".equals(s) || "1".equals(s) || "2".equals(s) || "3".equals(s)) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(this.value);
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof DateLazyDayEditor)) {
      return false;
    }
    final DateLazyDayEditor other = (DateLazyDayEditor) obj;
    if (!Arrays.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }
}
