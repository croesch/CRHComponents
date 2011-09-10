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
 * An <i>lazy</i> editor for any part of a date. It will allow all entries except invalid characters or characters that
 * are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
interface IDateLazyPartEditor {

  /**
   * Returns the number of chars that this editor edits.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @return number of chars that this editor is editing.
   */
  int getSize();

  /**
   * Performs to enter the given string into that editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param s the string to enter.
   * @param position the position to enter the {@link String} at.
   * @return <code>-1</code>, if the given string cannot be inserted<br>
   *         the number of characters written / the cursor should be moved
   */
  int enterValue(String s, int position);

  /**
   * Returns the current value of the editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @return the {@link String} that represents the current value of the editor
   */
  String getValue();

}
