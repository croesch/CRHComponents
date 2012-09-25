/*
 * Copyright (C) 2011-2012  Christian Roesch
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
package com.github.croesch.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;

/**
 * Properties that are filled with the properties fetched from the lang/testX.xml files, where X is defined by the
 * attributes of the {@link Locale}.
 * 
 * @author croesch
 * @since Date: Jan 25, 2012
 */
final class XMLI18nProperties extends Properties {

  /** generated serial version uid */
  private static final long serialVersionUID = -4384001694719486867L;

  /** logger for this class */
  private static final Logger LOGGER = Logger.getLogger(XMLI18nProperties.class.getName());

  /** the path to the file to read the properties from, doesn't contain APPENDIX nor file extension */
  @MayBeNull
  private String file;

  /**
   * Properties that are filled with the properties fetched from the lang/testX.xml files, where X is defined by the
   * attributes of the {@link Locale}.
   * 
   * @since Date: Jan 25, 2012
   * @param f path to the base file, <b>without</b> file extension!
   * @param loc the {@link Locale} to fetch the language, country and variant from
   */
  public XMLI18nProperties(final String f, final Locale loc) {
    this.file = f;

    final String language = loc.getLanguage();
    final String country = loc.getCountry();
    final String variant = loc.getVariant();

    final StringBuilder temp = new StringBuilder();
    loadProperties(buildResourceName(temp));

    if (language.length() == 0) {
      return;
    }
    temp.append('_').append(language);
    loadProperties(buildResourceName(temp));

    if (country.length() == 0) {
      return;
    }
    temp.append('_').append(country);
    loadProperties(buildResourceName(temp));

    if (variant.length() == 0) {
      return;
    }
    temp.append('_').append(variant);
    loadProperties(buildResourceName(temp));
  }

  /**
   * Builds the resource name with the given appendix. The name is built from the file name + the file ending. The file
   * name is built from the parameter file and the appendix. So the resource name will be:<br>
   * <code>fileAPPENDIX.xml</code>
   * 
   * @since Date: Feb 3, 2012
   * @param appendix the APPENDIX in the code above, or simply the string to append to the base file name
   * @return the full resource name
   */
  @NotNull
  private String buildResourceName(final StringBuilder appendix) {
    return this.file + appendix.toString() + ".xml";
  }

  /**
   * Fills the given properties with the key value pairs fetched from the file with the given name.
   * 
   * @since Date: Jan 24, 2012
   * @param resourceName the name of the resource file to load the properties from
   */
  private void loadProperties(final String resourceName) {
    try {
      final InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourceName);
      if (resourceStream != null) {
        loadFromXML(resourceStream);
      } else {
        LOGGER.fine("resource not found: " + resourceName);
      }
    } catch (final InvalidPropertiesFormatException e) {
      ExceptionLogger.logException(e);
    } catch (final IOException e) {
      ExceptionLogger.logException(e);
    } catch (final RuntimeException e) {
      ExceptionLogger.logException(e);
    }
  }

  @Override
  @NotNull
  public String getProperty(final String key) {
    final String ret = super.getProperty(key);
    if (ret == null) {
      LOGGER.warning("missing key=" + key + " in file=" + this.file);
      return "!!missing-key=" + key + "!!";
    }
    return ret;
  }

  @Override
  public synchronized boolean equals(final Object o) {
    return super.equals(o);
  }

  @Override
  public synchronized int hashCode() {
    return super.hashCode();
  }
}
