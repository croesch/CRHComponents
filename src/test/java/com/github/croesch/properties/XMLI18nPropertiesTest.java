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

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link XMLI18nProperties}.
 * 
 * @author croesch
 * @since Date: Jan 24, 2012
 */
public class XMLI18nPropertiesTest extends DefaultTestCase {

  @Test
  public void testEqualsAndHashCode() {
    assertThat(new XMLI18nProperties("asd", Locale.getDefault())).isEqualTo(new XMLI18nProperties("asd",
                                                                                                  Locale.getDefault()));

    assertThat(new XMLI18nProperties("asd", Locale.getDefault())).isEqualTo(new XMLI18nProperties("",
                                                                                                  Locale.getDefault()));

    assertThat(new XMLI18nProperties("asd", Locale.getDefault()).hashCode()).isEqualTo(new XMLI18nProperties("asd",
                                                                                                             Locale.getDefault()).hashCode());

    assertThat(new XMLI18nProperties("asd", Locale.getDefault()).hashCode()).isEqualTo(new XMLI18nProperties("",
                                                                                                             Locale.getDefault()).hashCode());
  }

  @Test
  public void testGetProperty_WrongFormat() {
    final String file = "lang/false-format";
    assertThat(new XMLI18nProperties(file, Locale.getDefault()).getProperty("border")).isEqualTo("!!missing-key=border!!");
  }

  @Test
  public void testXyGetProperty() {
    final String file = "xy";
    assertThat(new XMLI18nProperties(file, Locale.getDefault()).getProperty("border")).isEqualTo("!!missing-key=border!!");
    assertThat(new XMLI18nProperties(file, Locale.getDefault()).propertyNames().hasMoreElements()).isFalse();
  }

  @Test
  public void testNullGetProperty() {
    assertThat(new XMLI18nProperties(null, Locale.getDefault()).getProperty("border")).isEqualTo("!!missing-key=border!!");
    assertThat(new XMLI18nProperties(null, Locale.getDefault()).propertyNames().hasMoreElements()).isFalse();
  }

  @Test
  public void testLangTextGetProperty() {
    final String file = "lang/text";
    assertThat(new XMLI18nProperties(file, new Locale("test", "tst", "asd")).getProperty("border")).isEqualTo("b o r d e r");
    assertThat(new XMLI18nProperties(file, new Locale("test", "tst", "asd")).getProperty("BORDER")).isEqualTo("!!missing-key=BORDER!!");
    assertThat(new XMLI18nProperties(file, new Locale("test")).getProperty("try-help")).isEqualTo("..no one will ever see..");
    assertThat(new XMLI18nProperties(file, new Locale("test", "tst")).getProperty("try-help")).isEqualTo("OVERRIDDEN");
    assertThat(new XMLI18nProperties(file, new Locale("")).getProperty("try-help")).isNotEqualTo("OVERRIDDEN");
    assertThat(new XMLI18nProperties(file, new Locale("")).getProperty("try-help")).isNotEqualTo("..no one will ever see..");
    assertThat(new XMLI18nProperties(file, new Locale("pdf")).getProperty("try-help")).isNotEqualTo("OVERRIDDEN");
    assertThat(new XMLI18nProperties(file, new Locale("pdf")).getProperty("try-help")).isNotEqualTo("..no one will ever see..");
  }
}
