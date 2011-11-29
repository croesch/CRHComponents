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

import java.util.Locale;

/**
 * Defines different types of modification for values.
 * 
 * @author croesch
 * @since Date: Nov 29, 2011
 */
enum ModificationType {

  /** interpret the value as a constant. */
  CONSTANT,
  /** interpret the value as an offset from the current date. */
  OFFSET,
  /** interpret the value as an increment of the current date value */
  INCREMENT;

  /**
   * Returns a <b>unique</b> character to describe this type.
   * 
   * @since Date: Nov 29, 2011
   * @return a character that represents that type, in the way it could be defined in a configuration file.
   * @see #typeOfUniqueChar(char)
   */
  char uniqueCharacter() {
    // This returns the first letter of the name, so ensure that there is no duplicate.
    return name().toLowerCase(Locale.GERMANY).charAt(0);
  }

  @Override
  public String toString() {
    return String.valueOf(uniqueCharacter());
  }

  /**
   * Returns the {@link ModificationType} for the given unique character.
   * 
   * @since Date: Nov 29, 2011
   * @param uniqueChar the unique character, as returned from {@link #uniqueCharacter()}
   * @return the {@link ModificationType} the description belongs to
   * @see ModificationType#uniqueCharacter()
   */
  static ModificationType typeOfUniqueChar(final char uniqueChar) {
    for (final ModificationType t : values()) {
      if (uniqueChar == t.uniqueCharacter()) {
        return t;
      }
    }
    throw new IllegalArgumentException("unknown unique character >" + uniqueChar + "<");
  }
}
