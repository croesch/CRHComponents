package com.github.croesch.util;

/**
 * Provides some utility methods that constructs utility classes for the given object.
 * 
 * @author croesch
 * @since Date: 15.02.2011 18:54:22
 */
public final class Util {

  /**
   * Don't call it! Hide constructor of utility class.
   * 
   * @since Date: 15.02.2011
   */
  private Util() {
    throw new AssertionError();
  }

  /**
   * Returns a {@link StringUtil} to access utilities for {@link String}
   * 
   * @since Date: 15.02.2011
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
   * @since Date: 15.02.2011
   * @param arr the {@link String} array that should be used for the utilities
   * @return the instance of {@link StringArrayUtil}
   * @throws IllegalArgumentException if the array or one entry is {@code null}
   * @see StringArrayUtil#StringArrayUtil(String[])
   */
  public static StringArrayUtil of(final String[] arr) throws IllegalArgumentException {
    return new StringArrayUtil(arr);
  }
}
