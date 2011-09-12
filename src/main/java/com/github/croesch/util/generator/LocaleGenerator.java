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
      throw new IllegalArgumentException();
    }
    this.loc = locale;
  }

  /**
   * Generates file names from a given {@link Locale} with the given prefix. These filenames will be:<br>
   * <li><code>prefix</code></li> <li><code>prefix_language</code></li> <li>
   * <code>prefix_language_country</code></li>
   * <li>
   * <code>prefix_language_country_variant</code></li>
   * 
   * @param prefix the prefix of the generated file names. If prefix is <code>null</code> this will return an array that
   *        contains only <code>null</code>-values.
   * @return an array that contains the generated file names
   */
  public String[] defaultNamesStartingWith(final String prefix) {
    final int numOfVariations = 4;
    final int posOfName = 3;
    final int posOfNameLng = 2;
    final int posOfNameLngCountry = 1;
    final int posOfNameLngCountryVar = 0;

    final String lang = this.loc.getLanguage();
    final String coun = this.loc.getCountry();
    final String vari = this.loc.getVariant();

    final String[] names = new String[numOfVariations];

    if (prefix == null) {
      return names;
    }

    names[posOfName] = prefix;
    if (!"".equals(lang)) {
      names[posOfNameLng] = prefix + "_" + lang;
    }
    if (!"".equals(lang) && !"".equals(coun)) {
      names[posOfNameLngCountry] = prefix + "_" + lang + "_" + coun;
    }
    if (!"".equals(lang) && !"".equals(coun) && !"".equals(vari)) {
      names[posOfNameLngCountryVar] = prefix + "_" + lang + "_" + coun + "_" + vari;
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
