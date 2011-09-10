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
 * An <i>lazy</i> editor for any part of a date. It will allow only entries that contain valid characters and characters
 * that are valid at that position. May use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
interface IDatePartEditor extends IDateLazyPartEditor {

  /**
   * Updates the intern value of the date to the given values.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param day the current day value
   * @param month the current month value
   * @param year the current year value
   */
  void setCurrentValue(int day, int month, int year);

}
