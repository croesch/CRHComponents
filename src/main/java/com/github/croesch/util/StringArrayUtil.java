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

import java.util.Arrays;

/**
 * Provides utilities for {@link String} arrays
 * 
 * @author croesch
 * @since Date: Feb 17, 2011
 */
public final class StringArrayUtil {

  /** the array to work with */
  private final String[] lines;

  /** performance reason to store whether this already has been trimmed */
  private boolean trimmed = false;

  /**
   * Constructs the utility class with the given array
   * 
   * @since Date: Feb 17, 2011
   * @param s the array of {@link String}s
   * @throws IllegalArgumentException if the array or one entry is {@code null}
   * @see com.github.croesch.util.Util#of(String[])
   */
  StringArrayUtil(final String[] s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException();
    }
    for (final String str : s) {
      if (str == null) {
        throw new IllegalArgumentException();
      }
    }
    this.lines = s.clone();
  }

  /**
   * Constructs the utility class with the given array
   * 
   * @since Date: Feb 17, 2011
   * @param s the array of {@link String}s
   * @param t if the given array is already trimmed
   * @throws IllegalArgumentException if the array or one entry is {@code null}
   * @see com.github.croesch.util.Util#of(String[])
   */
  private StringArrayUtil(final String[] s, final boolean t) throws IllegalArgumentException {
    this(s);
    this.trimmed = t;
  }

  /**
   * Trims the stored lines of the array and returns the instance of this analyser.
   * 
   * @since Date: Feb 17, 2011
   * @return this instance of the analyser
   * @see String#trim()
   */
  public StringArrayUtil trim() {

    if (this.trimmed) {
      return this;
    }

    final String[] lns = new String[this.lines.length];
    for (int i = 0; i < this.lines.length; ++i) {
      lns[i] = this.lines[i].trim();
    }

    // if nothing has changed then return this and store the information, that this array don't need to be trimmed.
    if (Arrays.equals(lns, this.lines)) {
      this.trimmed = true;
      return this;
    }

    return new StringArrayUtil(lns, true);
  }

  /**
   * Returns the length of the longest line in the array of lines.
   * 
   * @since Date: Feb 17, 2011
   * @return the length of the longest line
   */
  public int getMaxLineLength() {

    int maxLength = -1;
    for (final String ln : this.lines) {
      if (ln.length() > maxLength) {
        maxLength = ln.length();
      }
    }
    return maxLength;
  }

  /**
   * Returns the array stored in this utility class. But each row will have the same length and they will be filled up
   * with spaces, so that each entry is left aligned.
   * 
   * @since Date: Feb 19, 2011
   * @return the array left aligned, each row has the same number of chars
   * @see #toCentreAlignedArray()
   * @see #toRightAlignedArray()
   */
  public String[] toLeftAlignedArray() {
    final int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      this.lines[i] += new StringUtil(" ").toStringMultipliedWith(maxLength - this.lines[i].length());
    }
    return this.lines.clone();
  }

  /**
   * Returns the array stored in this utility class. But each row will have the same length and they will be filled up
   * with spaces, so that each entry is right aligned.
   * 
   * @since Date: Feb 19, 2011
   * @return the array right aligned, each row has the same number of chars
   * @see #toCentreAlignedArray()
   * @see #toLeftAlignedArray()
   */
  public String[] toRightAlignedArray() {
    final int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      this.lines[i] = new StringUtil(" ").toStringMultipliedWith(maxLength - this.lines[i].length()) + this.lines[i];
    }
    return this.lines.clone();
  }

  /**
   * Returns the array stored in this utility class. But each row will have the same length and they will be filled up
   * with spaces, so that each entry is centre aligned.
   * 
   * @since Date: Feb 19, 2011
   * @return the array centre aligned, each row has the same number of chars
   * @see #toLeftAlignedArray()
   * @see #toRightAlignedArray()
   */
  public String[] toCentreAlignedArray() {
    final int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      final int right = (maxLength - this.lines[i].length()) / 2;
      final String spaces = new StringUtil(" ").toStringMultipliedWith(maxLength - this.lines[i].length() - right);
      this.lines[i] = spaces + this.lines[i];
      this.lines[i] += new StringUtil(" ").toStringMultipliedWith(right);
    }
    return this.lines.clone();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(this.lines);
    result = prime * result;
    if (this.trimmed) {
      result += prime;
    }
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    final StringArrayUtil other = (StringArrayUtil) obj;
    if (!Arrays.equals(this.lines, other.lines)) {
      return false;
    }
    if (this.trimmed != other.trimmed) {
      return false;
    }
    return true;
  }
}
