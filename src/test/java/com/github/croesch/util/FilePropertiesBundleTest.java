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

import static com.github.croesch.util.FilePropertiesBundle.getMessageFromSingleBundle;
import static com.github.croesch.util.FilePropertiesBundle.getText;
import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.util.Locale;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Provides test methods for {@link FilePropertiesBundle}
 * 
 * @author croesch
 * @since Date: 12.02.2011 20:19:16
 */
public class FilePropertiesBundleTest extends FestSwingJUnitTestCase {

  private static final String SINGLE = "testfiles/single";

  /**
   * Sets the Locale to German
   * 
   * @author croesch
   * @since Date: 12.02.2011 20:44:47
   */
  @BeforeClass
  public static void init() {
    Locale.setDefault(new Locale("de", "DE", "var"));
  }

  /**
   * Test method for
   * {@link com.github.croesch.util.FilePropertiesBundle#getMessageFromSingleBundle(java.lang.String, java.lang.String)}
   * .
   */
  @Test
  public void testGetMessageFromSingleBundle() {
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.all")).isEqualTo("value ");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.onethree")).isEqualTo("value ");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.onetwo")).isEqualTo("value");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.one")).isEqualTo("value");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.twothree")).isEqualTo("value ");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.three")).isEqualTo("value ");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.more")).isEqualTo("value");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.tabs")).isEqualTo("value\t\t");
    assertThat(getMessageFromSingleBundle(SINGLE, "test.last.line")).isEqualTo("doesn't end with \n");
    assertThat(getMessageFromSingleBundle("invalid", "test")).isNull();
    assertThat(getMessageFromSingleBundle(SINGLE, "test")).isNull();
    assertThat(getMessageFromSingleBundle(null, "test")).isNull();
    assertThat(getMessageFromSingleBundle(null, null)).isNull();
    assertThat(getMessageFromSingleBundle(SINGLE, null)).isNull();
    assertThat(getMessageFromSingleBundle("invalid", null)).isNull();
  }

  /**
   * Test method for {@link com.github.croesch.util.FilePropertiesBundle#getText(java.lang.String, java.lang.String)}.
   */
  @Test
  public void testGetText() {
    assertThat(getText(SINGLE, "test.spaces.all")).isEqualTo("LEER");
    assertThat(getText(SINGLE, "test.spaces.onethree")).isEqualTo("");
    assertThat(getText(SINGLE, "test.spaces.onetwo")).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.one")).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.twothree")).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.three")).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.more")).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.tabs")).isEqualTo("");
    assertThat(getText(SINGLE, "test.last.line")).isEqualTo("doesn't end with \n");
    assertThat(getText("invalid2", "test")).isNull();
    assertThat(getText(SINGLE, "test")).isNull();
    assertThat(getText(null, "test")).isNull();
    assertThat(getText(null, null)).isNull();
    assertThat(getText(SINGLE, null)).isNull();
    assertThat(getText("invalid", null)).isNull();
  }

  /**
   * Test method for {@link FilePropertiesBundle#getText(String, String, Locale)}.
   */
  @Test
  public void testGetTextWithDifferentLocales() {
    assertThat(getText(SINGLE, "test.spaces.all", new Locale("de", "DE"))).isEqualTo("LEER");
    assertThat(getText(SINGLE, "test.spaces.onethree", new Locale("de", "DE", ""))).isEqualTo("");
    assertThat(getText(SINGLE, "test.spaces.onetwo", new Locale("de", "", "DE"))).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.one", new Locale("de", "AT", "DE"))).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.twothree", Locale.GERMAN)).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.spaces.three", Locale.getDefault())).isEqualTo("EMPTY");
    assertThat(getText(SINGLE, "test.tabs", Locale.GERMANY)).isEqualTo("");
    assertThat(getText(SINGLE, "test.last.line", Locale.CANADA)).isEqualTo("doesn't end with \n");
    assertThat(getText(SINGLE, "test.last.line", Locale.ENGLISH)).isEqualTo("doesn't end with \n");
    assertThat(getText(SINGLE, "test.last.line", new Locale(""))).isEqualTo("doesn't end with \n");
    assertThat(getText(SINGLE, "test.last.line", new Locale("", ""))).isEqualTo("doesn't end with \n");
    assertThat(getText(SINGLE, "test.last.line", new Locale("", "", ""))).isEqualTo("doesn't end with \n");
  }

  /**
   * Test method for {@link FilePropertiesBundle#getText(String, String, Locale)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetTextWithDifferentLocales_IAE() {
    getText(SINGLE, "test.spaces.more", null);
  }

  /**
   * Tests that the constructor {@link FilePropertiesBundle} throws an exception to avoid being called
   * 
   * @author croesch
   * @since Date: 19.02.2011 19:57:30
   */
  @Test
  public void testConstructorException() {
    for (final Constructor<?> c : FilePropertiesBundle.class.getDeclaredConstructors()) {
      assertThat(c.getModifiers()).as(c.toString()).isEqualTo(2);
    }
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
