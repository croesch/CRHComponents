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

import java.util.Arrays;
import java.util.Calendar;

/**
 * An <i>lazy</i> editor for the year part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyYearEditor implements IDateLazyPartEditor {

  /** constant for number ten */
  private static final int TEN = 10;

  /** constant of the highest value */
  private static final int HIGHEST = 9999;

  /** the current value */
  private final char[] value = { '0', '0', '0', '0' };

  /**
   * Constructs a new lazy date editor with the value of the current year.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @see #DateLazyDayEditor(int)
   */
  DateLazyYearEditor() {
    this(0);
  }

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than 9999 the current year will be the
   *        initial value.
   * @see #DateLazyDayEditor()
   */
  DateLazyYearEditor(final int initial) {
    int year = initial;
    if (year <= 0 || year > HIGHEST) {
      year = Calendar.getInstance().get(Calendar.YEAR);
    }
    final int first = year / (TEN * TEN * TEN);
    final int second = (year % (TEN * TEN * TEN)) / (TEN * TEN);
    final int third = (year % (TEN * TEN)) / TEN;
    final int fourth = year % TEN;
    this.value[0] = (char) ('0' + first);
    this.value[1] = (char) ('0' + second);
    this.value[2] = (char) ('0' + third);
    this.value[3] = (char) ('0' + fourth);
  }

  @Override
  public int getSize() {
    return 4;
  }

  @Override
  public int enterValue(final String s, final int position) {
    if (s == null || s.length() != 1) {
      return -1;
    }
    if ("0123456789".indexOf(s) < 0) {
      return -1;
    }
    switch (position) {
      case 0:
        this.value[3] = s.charAt(0);
        return 1;
      case 1:
        if ("0123".indexOf(this.value[3]) >= 0) {
          this.value[0] = '2';
          this.value[1] = '0';
        } else {
          this.value[0] = '1';
          this.value[1] = '9';
        }
        this.value[2] = this.value[3];
        this.value[3] = s.charAt(0);
        return 1;
      case 2:
        this.value[0] = '1';
        this.value[1] = this.value[2];
        this.value[2] = this.value[3];
        this.value[3] = s.charAt(0);
        return 1;
      case 3:
        this.value[0] = this.value[1];
        this.value[1] = this.value[2];
        this.value[2] = this.value[3];
        this.value[3] = s.charAt(0);
        return 1;
      default:
        return -1;
    }
  }

  @Override
  public String getValue() {
    return String.valueOf(this.value, 0, 4);
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
    if (!(obj instanceof DateLazyYearEditor)) {
      return false;
    }
    final DateLazyYearEditor other = (DateLazyYearEditor) obj;
    if (!Arrays.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }
}
