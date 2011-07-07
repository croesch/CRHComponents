package com.github.croesch.util;

/**
 * A multiplier that is able to multiply a {@link String} n times
 * 
 * @author croesch
 * @since Date: 17.02.2011 19:45:31
 */
public final class StringUtil {

  /** the {@link String} to work with - ensured to be not {@code null} */
  private final String string;

  /**
   * Constructs a multiplier with the given {@link String}
   * 
   * @since Date: 17.02.2011
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
   * @since Date: 15.02.2011
   * @param n how often the {@link String} should be repeated
   * @return the {@link String} repeated n times
   * @throws IllegalArgumentException if n is lower than zero
   */
  public String toStringMultipliedWith(final int n) throws IllegalArgumentException {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if ("".equals(this.string)) { //$NON-NLS-1$
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
