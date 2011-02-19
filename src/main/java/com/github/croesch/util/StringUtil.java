package com.github.croesch.util;

/**
 * A multiplier that is able to multiply a {@link String} n times
 * 
 * @author croesch
 * @since Date: 17.02.2011 19:45:31
 */
class StringUtil {

  /** the {@link String} to multiply */
  private final String string;

  /**
   * Constructs a multiplier with the given {@link String}
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:32:39
   * @param s the {@link String} to prepare for being multiplied
   */
  protected StringUtil(final String s) {
    if (s == null) {
      throw new IllegalArgumentException();
    }
    this.string = s;
  }

  /**
   * Multiplies the {@link String} of this multiplier the given number of times and returns the result.
   * 
   * @author croesch
   * @since Date: 15.02.2011 19:18:04
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
    int result = 1;
    result = prime * result;
    if (this.string != null) {
      result += this.string.hashCode();
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
    StringUtil other = (StringUtil)obj;
    if (this.string == null) {
      if (other.string != null) {
        return false;
      }
    } else if (!this.string.equals(other.string)) {
      return false;
    }
    return true;
  }
}
