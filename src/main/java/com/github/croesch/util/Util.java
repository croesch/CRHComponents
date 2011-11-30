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

import java.util.Date;

/**
 * Provides some utility methods that constructs utility classes for the given object.
 * 
 * @author croesch
 * @since Date: Feb 15, 2011
 */
public final class Util {

  /**
   * Don't call it! Hide constructor of utility class.
   * 
   * @since Date: Feb 15, 2011
   */
  private Util() {
    throw new AssertionError("Invocation of utility class constructor.");
  }

  /**
   * Returns a {@link StringUtil} to access utilities for {@link String}
   * 
   * @since Date: Feb 15, 2011
   * @param str the {@link String} that should be used for the utilities
   * @return the instance of {@link StringUtil}
   * @throws IllegalArgumentException if the given string is {@code null}
   * @see StringUtil#StringUtil(String)
   */
  public static StringUtil of(final String str) throws IllegalArgumentException {
    return new StringUtil(str);
  }

  /**
   * Returns a {@link StringArrayUtil} to access utilities for an array of {@link String}s
   * 
   * @since Date: Feb 15, 2011
   * @param arr the {@link String} array that should be used for the utilities
   * @return the instance of {@link StringArrayUtil}
   * @throws IllegalArgumentException if the array or one entry is {@code null}
   * @see StringArrayUtil#StringArrayUtil(String[])
   */
  public static StringArrayUtil of(final String[] arr) throws IllegalArgumentException {
    return new StringArrayUtil(arr);
  }

  /**
   * Returns a {@link DateUtil} to access utilities for a {@link Date}s
   * 
   * @since Date: Sep 11, 2011
   * @param d the {@link Date} that should be used for the utilities
   * @return the instance of {@link DateUtil}
   * @throws IllegalArgumentException if the given date is {@code null}
   * @see DateUtil#DateUtil(Date)
   */
  public static DateUtil of(final Date d) throws IllegalArgumentException {
    return new DateUtil(d);
  }

  /**
   * Returns a {@link IntUtil} to access utilities for a integers
   * 
   * @since Date: Nov 30, 2011
   * @param i the integer that should be used for the utilities
   * @return the instance of {@link IntUtil}
   * @see IntUtil#IntUtil(int)
   */
  public static IntUtil of(final int i) {
    return new IntUtil(i);
  }
}
