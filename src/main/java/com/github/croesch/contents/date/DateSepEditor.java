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

/**
 * The editor for the separator part of a date. It will allow characters that are valid separator chars at the position
 * they are entered.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateSepEditor implements IDatePartEditor {

  /** the current value of the separator - won't change */
  private final String value;

  /**
   * Constructs a new lazy separator editor that won't accept any other chars than contained in the separator at the
   * given position.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param sep the separator to represent
   */
  public DateSepEditor(final String sep) {
    this.value = sep;
  }

  @Override
  public int getSize() {
    return this.value.length();
  }

  @Override
  public int enterValue(final char c, final int position) {
    // you can only enter a character if the position is valid
    // and if the character is correct for the given position
    if (isPositionValid(position) && c == this.value.charAt(position)) {
      return 1;
    }
    return -1;
  }

  /**
   * Returns whether you can find a character in this separator at the given position.
   * 
   * @since Date: Aug 24, 2011
   * @param position the position to check the validity of
   * @return <code>true</code>, if you could call {@link String#charAt(int)} without any problems. Or in other words:
   *         <code>true</code>, if the position is at least zero and less than the number of characters of this
   *         separator.
   */
  private boolean isPositionValid(final int position) {
    return position >= 0 && position < getSize();
  }

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return getValue();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + this.value.hashCode();
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
    final DateSepEditor other = (DateSepEditor) obj;
    return this.value.equals(other.value);
  }

  @Override
  public void setCurrentValue(final int day, final int month, final int year) {
    /*
     * We needn't to do anything here, since the value of a separating character is not depending on the values of
     * either day, month or year. So this is just an empty implementation.
     */
  }
}
