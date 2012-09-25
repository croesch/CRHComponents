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
import java.util.Properties;

import com.github.croesch.annotate.NotNull;

/**
 * Provides properties in properties format.
 * 
 * @author croesch
 * @since Date: Feb 23, 2012
 */
public final class PropertiesProvider extends APropertiesProvider {

  /**
   * Hidden constructor to be able to create a singleton.
   * 
   * @since Date: Feb 23, 2012
   */
  private PropertiesProvider() {
    super('.');
  }

  @Override
  @NotNull
  protected Properties createNewProperties(final String file) {
    final Properties props = new Properties();
    try {
      final InputStream inputStream = ClassLoader.getSystemResourceAsStream(file + ".properties");
      if (inputStream != null) {
        props.load(inputStream);
      }
    } catch (final IOException e) {
      ExceptionLogger.logException(e);
    }
    return props;
  }

  /**
   * Provides the instance of this {@link PropertiesProvider}.
   * 
   * @since Date: Feb 23, 2012
   * @return the single instance of this class.
   */
  @NotNull
  public static PropertiesProvider getInstance() {
    return LazyHolder.INSTANCE;
  }

  /**
   * Initialization on Demand Holder.
   * 
   * @author croesch
   * @since Date: Feb 23, 2012
   */
  private static final class LazyHolder {
    /** instance of {@link PropertiesProvider} */
    public static final PropertiesProvider INSTANCE = new PropertiesProvider();

    /**
     * Hidden constructor..
     * 
     * @since Date: Feb 23, 2012
     */
    private LazyHolder() {
      throw new AssertionError();
    }
  }
}
