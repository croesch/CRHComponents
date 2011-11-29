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
 * Provides utilities for {@link String}s.
 * 
 * @author croesch
 * @since Date: Feb 17, 2011
 */
public final class StringUtil {

  /** the {@link String} to work with - ensured to be not {@code null} */
  private final String string;

  /**
   * Constructs a multiplier with the given {@link String}
   * 
   * @since Date: Feb 17, 2011
   * @param s the {@link String} to prepare for being multiplied
   */
  StringUtil(final String s) {
    if (s == null) {
      // important, because we want to ensure that this.string is not null
      throw new IllegalArgumentException();
    }
    this.string = s;
  }

  /**
   * Multiplies the {@link String} of this multiplier the given number of times and returns the result.
   * 
   * @since Date: Feb 15, 2011
   * @param n how often the {@link String} should be repeated
   * @return the {@link String} repeated n times
   * @throws IllegalArgumentException if n is lower than zero
   */
  public String toStringMultipliedWith(final int n) throws IllegalArgumentException {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if ("".equals(this.string)) {
      return this.string;
    }
    final StringBuilder sb = new StringBuilder();
    for (int j = 0; j < n; ++j) {
      sb.append(this.string);
    }
    return sb.toString();
  }

  @Override
  public String toString() {
    return this.string;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + this.string.hashCode();
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
    final StringUtil other = (StringUtil) obj;
    if (!this.string.equals(other.string)) {
      return false;
    }
    return true;
  }
}
