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
package com.github.croesch.util.generator;

import java.util.Locale;

/**
 * Generator for file names.
 * 
 * @author croesch
 * @since Date: Mar 10, 2011
 */
public final class LocaleGenerator {

  /**
   * the {@link Locale} to generate things from, ensured to be not <code>null</code>
   */
  private final Locale loc;

  /**
   * Constructs a {@link LocaleGenerator} with the given {@link Locale}
   * 
   * @since Date: Mar 10, 2011
   * @param locale the {@link Locale} that is used to generate things.
   */
  public LocaleGenerator(final Locale locale) {
    if (locale == null) {
      throw new IllegalArgumentException(">null< is not allowed.");
    }
    this.loc = locale;
  }

  /**
   * Generates file names from a given {@link Locale} with the given prefix. These filenames will be:<br>
   * <ul>
   * <li><code>prefix</code></li>
   * <li><code>prefix_language</code></li>
   * <li><code>prefix_language_country</code></li>
   * <li><code>prefix_language_country_variant</code></li>
   * </ul>
   * 
   * @param prefix the prefix of the generated file names. If prefix is <code>null</code> this will return an array that
   *        contains only <code>null</code>-values.
   * @return an array that contains the generated file names
   */
  public String[] defaultNamesStartingWith(final String prefix) {
    // the number of variations that'll be generated
    final int numOfVariations = 4;
    // the position of the 'prefix'
    final int posOfName = 3;
    // the position of the 'prefix_language'
    final int posOfNameLng = 2;
    // the position of the 'prefix_language_country'
    final int posOfNameLngCountry = 1;
    // the position of the 'prefix_language_country_variant'
    final int posOfNameLngCountryVar = 0;

    final String[] names = new String[numOfVariations];

    if (prefix == null) {
      // if we have null as prefix, return the empty array
      return names;
    }

    final String lang = this.loc.getLanguage();
    final String country = this.loc.getCountry();
    final String variant = this.loc.getVariant();

    // put the pure 'prefix' into the array
    names[posOfName] = prefix;

    if (!"".equals(lang)) {
      // put the 'prefix_language' into the array, if the necessary information exists
      names[posOfNameLng] = prefix + "_" + lang;
    }
    if (!"".equals(lang) && !"".equals(country)) {
      // put the 'prefix_language_country' into the array, if the necessary information exists
      names[posOfNameLngCountry] = prefix + "_" + lang + "_" + country;
    }
    if (!"".equals(lang) && !"".equals(country) && !"".equals(variant)) {
      // put the 'prefix_language_country_variant' into the array, if the necessary information exists
      names[posOfNameLngCountryVar] = prefix + "_" + lang + "_" + country + "_" + variant;
    }
    return names;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.loc.hashCode();
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
    final LocaleGenerator other = (LocaleGenerator) obj;
    if (!this.loc.equals(other.loc)) {
      return false;
    }
    return true;
  }

}
