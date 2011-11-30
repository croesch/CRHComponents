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
package com.github.croesch.util;

/**
 * An util for int values, offers special operations on integer values.
 * 
 * @author croesch
 * @since Date: Nov 30, 2011
 */
public final class IntUtil {

  /** stupid constant for the number ten */
  private static final int TEN = 10;

  /** the integer value to operate on */
  private final int value;

  /**
   * Constructs an utility for the given integer value.
   * 
   * @since Date: Nov 30, 2011
   * @param val the integer to get the utility for
   */
  public IntUtil(final int val) {
    this.value = val;
  }

  /**
   * Returns the integer value this utility operates on.
   * 
   * @since Date: Nov 30, 2011
   * @return the integer this utility operates on.
   */
  public int getValue() {
    return this.value;
  }

  /**
   * Returns the digit at the given position in the value to operate on. Negative positions are not possible and
   * negative values will be ignored. So basically returns a number between zero and ten.<br>
   * Example for value <code>1420815</code>:
   * <table>
   * <tr>
   * <th>position</th>
   * <th>digit</th>
   * </tr>
   * <tr>
   * <td>0</td>
   * <td>5</td>
   * </tr>
   * <tr>
   * <td>1</td>
   * <td>1</td>
   * </tr>
   * <tr>
   * <td>2</td>
   * <td>8</td>
   * </tr>
   * <tr>
   * <td>3</td>
   * <td>0</td>
   * </tr>
   * <tr>
   * <td>4</td>
   * <td>2</td>
   * </tr>
   * <tr>
   * <td>5</td>
   * <td>4</td>
   * </tr>
   * <tr>
   * <td>6</td>
   * <td>1</td>
   * </tr>
   * <tr>
   * <td>&gt; 6</td>
   * <td>0</td>
   * </tr>
   * </table>
   * 
   * @since Date: Nov 30, 2011
   * @param position the position of the digit to return
   * @return the digit at the given position in the value to operate on.
   * @throws IllegalArgumentException if the given position is less than zero
   */
  public int getDigit(final int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("position less than zero: >" + position + "<");
    }

    int digit = Math.abs(this.value);
    for (int i = 0; i < position; ++i) {
      digit /= TEN;
    }
    return digit % TEN;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + this.value;
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
    final IntUtil other = (IntUtil) obj;
    if (this.value != other.value) {
      return false;
    }
    return true;
  }
}
