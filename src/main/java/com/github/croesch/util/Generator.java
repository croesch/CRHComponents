package com.github.croesch.util;

import java.util.Locale;

import com.github.croesch.util.generator.LocaleGenerator;

/**
 * Provides some methods to generate different things.
 * 
 * @author croesch
 * @since Date: 10.03.2011 19:07:17
 */
public final class Generator {

  /**
   * Don't call this! This hides the constructor of a utility class.
   * 
   * @since Date: 10.03.2011
   */
  private Generator() {
    throw new AssertionError();
  }

  /**
   * Generates a {@link LocaleGenerator} to be able to generate some things it provides.
   * 
   * @since Date: 10.03.2011
   * @param locale the {@link Locale} to generate the things from
   * @return a {@link LocaleGenerator} to generate things with
   */
  public static LocaleGenerator generateFrom(final Locale locale) {
    return new LocaleGenerator(locale);
  }
}
