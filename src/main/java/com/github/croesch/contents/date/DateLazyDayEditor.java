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

/**
 * An <i>lazy</i> editor for the day part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyDayEditor extends DateLazyMonAndDayEditor {

  /** constant of the highest value */
  private static final int HIGHEST = 31;

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
    super(initial, HIGHEST);
  }

  @Override
  protected int getDefaultValue() {
    return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
  }

  @Override
  protected String getValidForBoth() {
    return "0123";
  }

  @Override
  protected String getValidOnlyForSecond() {
    return "456789";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getValue().hashCode();
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
    return getValue().equals(other.getValue());
  }
}
