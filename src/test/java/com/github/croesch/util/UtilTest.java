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

import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.Constructor;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link Util}
 * 
 * @author croesch
 * @since Date: 17.02.2011 20:43:57
 */
public class UtilTest extends FestSwingJUnitTestCase {

  /**
   * Tests that {@link Util#of(String)} returns an object that is equal to the self created object
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:50:42
   */
  @Test
  public void testMultiply() {
    assertThat(Util.of("")).isEqualTo(new StringUtil(""));
    assertThat(Util.of("")).isNotEqualTo(new StringUtil("a"));
    assertThat(Util.of("a")).isEqualTo(new StringUtil("a"));
    assertThat(Util.of("a")).isNotEqualTo(new StringUtil(""));
  }

  /**
   * Tests that {@link Util#of(String[])} returns an object that is equal to the self created object
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:50:42
   */
  @Test
  @SuppressWarnings("nls")
  public void testAnalyseLengthOf() {
    assertThat(Util.of(new String[] { "" })).isEqualTo(new StringArrayUtil(new String[] { "" }));
    assertThat(Util.of(new String[] { "" })).isNotEqualTo(new StringArrayUtil(new String[] { "a" }));
    assertThat(Util.of(new String[] { "a" })).isEqualTo(new StringArrayUtil(new String[] { "a" }));
    assertThat(Util.of(new String[] { "a" })).isNotEqualTo(new StringArrayUtil(new String[] { "" }));
    assertThat(Util.of(new String[] { "", "b" })).isEqualTo(new StringArrayUtil(new String[] { "", "b" }));
    assertThat(Util.of(new String[] { "a", "" })).isNotEqualTo(new StringArrayUtil(new String[] { "", "a" }));
    assertThat(Util.of(new String[] { "a", "bb", "ccc" })).isEqualTo(new StringArrayUtil(new String[] { "a",
                                                                                                       "bb",
                                                                                                       "ccc" }));
    assertThat(Util.of(new String[] { "a" })).isNotEqualTo(new StringArrayUtil(new String[] { "a" }).trim());
  }

  /**
   * Tests that the constructor {@link Util} throws an exception to avoid being called
   * 
   * @author croesch
   * @since Date: 19.02.2011 19:57:30
   */
  @Test
  public void testConstructorException() {
    for (final Constructor<?> c : Util.class.getDeclaredConstructors()) {
      assertThat(c.getModifiers()).as(c.toString()).isEqualTo(2);
    }
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
