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

import java.util.Locale;

import com.github.croesch.util.generator.LocaleGenerator;

/**
 * Provides some methods to generate different things.
 * 
 * @author croesch
 * @since Date: Mar 10, 2011
 */
public final class Generator {

  /**
   * Don't call this! This hides the constructor of a utility class.
   * 
   * @since Date: Mar 10, 2011
   */
  private Generator() {
    throw new AssertionError("Invocation of utility class constructor.");
  }

  /**
   * Generates a {@link LocaleGenerator} to be able to generate some things it provides.
   * 
   * @since Date: Mar 10, 2011
   * @param locale the {@link Locale} to generate the things from
   * @return a {@link LocaleGenerator} to generate things with
   */
  public static LocaleGenerator generateFrom(final Locale locale) {
    return new LocaleGenerator(locale);
  }
}
