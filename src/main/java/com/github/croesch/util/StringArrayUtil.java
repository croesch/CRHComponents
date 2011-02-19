package com.github.croesch.util;

import java.util.Arrays;

/**
 * Provides utilities for {@link String} arrays
 * 
 * @author croesch
 * @since Date: 17.02.2011 20:58:03
 */
class StringArrayUtil {

  /** the array to work with */
  private final String[] lines;

  /** performance reason to store whether this already has been trimmed */
  private boolean trimmed = false;

  /**
   * Constructs the utility class with the given array
   * 
   * @author croesch
   * @since Date: 17.02.2011 21:04:20
   * @param s the array of {@link String}s
   * @throws IllegalArgumentException if the array or one entry is {@code null}
   * @see com.github.croesch.util.Util#of(String[])
   */
  protected StringArrayUtil(final String[] s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException();
    }
    for (String str : s) {
      if (str == null) {
        throw new IllegalArgumentException();
      }
    }
    this.lines = s;
  }

  /**
   * Trims the stored lines of the array and returns the instance of this analyser.
   * 
   * @author croesch
   * @since Date: 17.02.2011 21:19:25
   * @return this instance of the analyser
   * @see String#trim()
   * @see #isTrimmed()
   */
  public final StringArrayUtil trim() {
    if (this.lines == null) {
      throw new IllegalArgumentException();
    }
    if (isTrimmed()) {
      return this;
    }

    for (int i = 0; i < this.lines.length; ++i) {
      if (this.lines[i] == null) {
        throw new IllegalArgumentException();
      }
      this.lines[i] = this.lines[i].trim();
    }

    this.trimmed = true;
    return this;
  }

  /**
   * Returns whether the array of lines already had been trimmed.
   * 
   * @author croesch
   * @since Date: 17.02.2011 21:21:23
   * @return {@code true}, if the {@link #trim()} method already has been called
   * @see #trim()
   */
  public final boolean isTrimmed() {
    return this.trimmed;
  }

  /**
   * Returns the length of the longest line in the array of lines.
   * 
   * @author croesch
   * @since Date: 17.02.2011 19:11:37
   * @return the length of the longest line
   */
  public final int getMaxLineLength() {
    if (this.lines == null) {
      throw new IllegalArgumentException();
    }

    int maxLength = -1;
    for (String ln : this.lines) {
      if (ln == null) {
        throw new IllegalArgumentException();
      }
      if (ln.length() > maxLength) {
        maxLength = ln.length();
      }
    }
    return maxLength;
  }

  public String[] toLeftAlignedArray() {
    int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      this.lines[i] += Util.of(" ").times(maxLength - this.lines[i].length()); //$NON-NLS-1$
    }
    return this.lines;
  }

  public String[] toRightAlignedArray() {
    int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      this.lines[i] = Util.of(" ").times(maxLength - this.lines[i].length()) + this.lines[i]; //$NON-NLS-1$
    }
    return this.lines;
  }

  public String[] toCentreAlignedArray() {
    int maxLength = getMaxLineLength();

    for (int i = 0; i < this.lines.length; ++i) {
      int right = (maxLength - this.lines[i].length()) / 2;
      this.lines[i] = Util.of(" ").times(maxLength - this.lines[i].length() - right) + this.lines[i]; //$NON-NLS-1$
      this.lines[i] += Util.of(" ").times(right); //$NON-NLS-1$
    }
    return this.lines;
  }

  @Override
  public final int hashCode() {
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
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StringArrayUtil other = (StringArrayUtil)obj;
    if (!Arrays.equals(this.lines, other.lines)) {
      return false;
    }
    if (this.trimmed != other.trimmed) {
      return false;
    }
    return true;
  }
}
