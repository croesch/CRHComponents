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

import com.github.croesch.util.IntUtil;
import com.github.croesch.util.Util;

/**
 * Class that contains logic for both, lazy day and lazy month editor.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
abstract class DateLazyMonAndDayEditor implements IDateLazyPartEditor {

  /** the current value */
  private final char[] value = { '0', '0' };

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than <code>highest</code> the current
   *        value will be the initial value.
   * @param highest the highest possible value of this editor
   */
  DateLazyMonAndDayEditor(int initial, final int highest) {
    if (initial <= 0 || initial > highest) {
      initial = getDefaultValue();
    }

    // extract single digits from the initial value
    final IntUtil util = Util.of(initial);
    final int first = util.getDigit(1);
    final int second = util.getDigit(0);

    // store the calculated value, but with offset of character '0' because we store ASCII/char-values
    this.value[0] = (char) ('0' + first);
    this.value[1] = (char) ('0' + second);
  }

  /**
   * Returns the {@link String} that contains all characters that are only valid for the second position.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return {@link String} containing all chars being valid only for the second position.
   */
  protected abstract String getValidOnlyForSecond();

  /**
   * Returns the {@link String} that contains all characters that are valid for the both positions.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return {@link String} containing all chars being valid for the both positions.
   */
  protected abstract String getValidForBoth();

  /**
   * Returns the default value of this editor, this means the current value of the field in the current date.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return the current value of the specific field in the current date.
   */
  protected abstract int getDefaultValue();

  @Override
  public final int getSize() {
    return 2;
  }

  @Override
  public final int enterValue(final char c, final int position) {
    // first position: left character; second position: right character

    if (getValidOnlyForSecond().indexOf(c) >= 0) {
      // we have a character that is only valid at the second position in the day/month

      if (position == 0) {
        // the character should be inserted at the first position, invalid --> change the second character
        this.value[1] = c;
        // so return that we changed two characters
        return 2;
      }
      if (position == 1) {
        // the character should be inserted at the second position --> fine, change the second character
        this.value[1] = c;
        // and return that we successfully wrote one character
        return 1;
      }
    }

    if (getValidForBoth().indexOf(c) >= 0 && (position == 0 || position == 1)) {
      // we have a character that is valid at both positions in the day/month
      // and we want to insert it at one of the two positions, so do it
      this.value[position] = c;
      // and return that we successfully wrote one character
      return 1;
    }

    // either the character is not valid for month/day (no digit), or the position is invalid
    // so return, that we couldn't insert the character
    return -1;
  }

  @Override
  public final String getValue() {
    return String.valueOf(this.value, 0, 2);
  }

  @Override
  public final String toString() {
    return getValue();
  }

}
