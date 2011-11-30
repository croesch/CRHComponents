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

import com.github.croesch.util.IntUtil;
import com.github.croesch.util.Util;

/**
 * An <i>lazy</i> editor for the year part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyYearEditor implements IDateLazyPartEditor {

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
  DateLazyYearEditor(int initial) {
    if (initial <= 0 || initial > HIGHEST) {
      initial = Calendar.getInstance().get(Calendar.YEAR);
    }

    // extract single digits from the initial value
    final IntUtil util = Util.of(initial);
    final int first = util.getDigit(3);
    final int second = util.getDigit(2);
    final int third = util.getDigit(1);
    final int fourth = util.getDigit(0);

    // store the calculated value, but with offset of character '0' because we store ASCII/char-values
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
  public int enterValue(final char c, final int position) {
    if ("0123456789".indexOf(c) < 0) {
      // the character is invalid, so return that we cannot insert it
      return -1;
    }

    // the character is valid, so the behavior depends on the position where to insert
    switch (position) {
    // The position is numbered from the left to the right (0 is the left character, 3 the right ch.).
    // position: yyyy -> [0123]
    /**
     * The characters won't be written from the left to the right! Normally one inserts four characters successive. Each
     * character will be pushed from the right into the year-value. This is hard to understand from the code, so check
     * the behavior of the input field, if in doubt.<br>
     * Example: Inserting 1,2,3,4 will result in XXX1, 1912, 1123, 1234
     */
      case 0:
        // push the character at the right position
        this.value[3] = c;
        return 1;
      case 1:
        if ("0123".indexOf(this.value[3]) >= 0) {
          // change the century to 20 if decade < 40
          this.value[0] = '2';
          this.value[1] = '0';
        } else {
          // change the century to 19 if decade >= 40
          this.value[0] = '1';
          this.value[1] = '9';
        }
        // move last character one to the left
        this.value[2] = this.value[3];
        // set last (right) character
        this.value[3] = c;
        return 1;
      case 2:
        // set first (left) character to one
        this.value[0] = '1';
        // move each position one to the left
        this.value[1] = this.value[2];
        this.value[2] = this.value[3];
        // set last (right) character
        this.value[3] = c;
        return 1;
      case 3:
        // move each position one to the left
        this.value[0] = this.value[1];
        this.value[1] = this.value[2];
        this.value[2] = this.value[3];
        // set last (right) character
        this.value[3] = c;
        return 1;
      default:
        // the position is invalid, so return that we cannot insert the character
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
    return prime + Arrays.hashCode(this.value);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final DateLazyYearEditor other = (DateLazyYearEditor) obj;
    if (!Arrays.equals(this.value, other.value)) {
      return false;
    }
    return true;
  }
}
