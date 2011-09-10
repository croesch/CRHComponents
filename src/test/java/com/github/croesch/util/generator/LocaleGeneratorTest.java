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

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

import com.github.croesch.util.Generator;

/**
 * Provides test methods for {@link LocaleGenerator}
 * 
 * @author croesch
 * @since Date: 10.03.2011 19:19:56
 */
public class LocaleGeneratorTest extends FestSwingJUnitTestCase {

  private static Locale loc1_1 = new Locale("");

  private static Locale loc1_2 = new Locale("");

  private static Locale loc2_1 = new Locale("DE", "de");

  private static Locale loc2_2 = new Locale("DE", "de");

  private static Locale loc3_1 = new Locale("d", "bla", "rlp");

  private static Locale loc3_2 = new Locale("d", "bla", "rlp");

  /**
   * Test method for {@link LocaleGenerator#FileNameGenerator(String)}
   */
  @Test(expected = IllegalArgumentException.class)
  public void testFileNameGenerator_IAE() {
    new LocaleGenerator(null);
  }

  /**
   * Test method for {@link LocaleGenerator#defaultNamesStartingWith(String)}
   */
  @Test
  public void testDefaultNamesStartingWithPrefixNull_de_DE() {
    final Locale locale = new Locale("de", "DE");
    final String prefix = null;
    final LocaleGenerator fng = new LocaleGenerator(locale);
    final String[] result = fng.defaultNamesStartingWith(prefix);
    final String[] result2 = Generator.generateFrom(locale).defaultNamesStartingWith(prefix);

    assertThat(result).isEqualTo(result2);
    assertThat(result).containsOnly(null, null, null, null);
  }

  /**
   * Test method for {@link LocaleGenerator#defaultNamesStartingWith(String)}
   */
  @Test
  public void testDefaultNamesStartingWithEmptyPrefix_de_DE() {
    final Locale locale = new Locale("de", "DE");
    final String prefix = "";
    final LocaleGenerator fng = new LocaleGenerator(locale);
    final String[] result = fng.defaultNamesStartingWith(prefix);
    final String[] result2 = Generator.generateFrom(locale).defaultNamesStartingWith(prefix);

    assertThat(result).isEqualTo(result2);
    assertThat(result).containsOnly("", "_de", "_de_DE", null);
    assertThat(result).doesNotHaveDuplicates();
  }

  /**
   * Test method for {@link LocaleGenerator#defaultNamesStartingWith(String)}
   */
  @Test
  public void testDefaultNamesStartingWithEmptyPrefix_de_DE_rpl() {
    final Locale locale = new Locale("de", "DE", "rpl");
    final String prefix = "";
    final LocaleGenerator fng = new LocaleGenerator(locale);
    final String[] result = fng.defaultNamesStartingWith(prefix);
    final String[] result2 = Generator.generateFrom(locale).defaultNamesStartingWith(prefix);

    assertThat(result).isEqualTo(result2);
    assertThat(result).containsOnly("", "_de", "_de_DE", "_de_DE_rpl");
    assertThat(result).doesNotHaveDuplicates();
  }

  /**
   * Test method for {@link LocaleGenerator#defaultNamesStartingWith(String)}
   */
  @Test
  public void testDefaultNamesStartingWithEmptyPrefix_de() {
    final Locale locale = new Locale("de");
    final String prefix = "";
    final LocaleGenerator fng = new LocaleGenerator(locale);
    final String[] result = fng.defaultNamesStartingWith(prefix);
    final String[] result2 = Generator.generateFrom(locale).defaultNamesStartingWith(prefix);

    assertThat(result).isEqualTo(result2);
    assertThat(result).containsOnly("", "_de", null, null);
  }

  /**
   * Test method for {@link LocaleGenerator#defaultNamesStartingWith(String)}
   */
  @Test
  public void testDefaultNamesStartingWithPrefix_en_EN_gb() {
    final Locale locale = new Locale("en", "EN", "gb");
    final String prefix = "prefix";
    final LocaleGenerator fng = new LocaleGenerator(locale);
    final String[] result = fng.defaultNamesStartingWith(prefix);
    final String[] result2 = Generator.generateFrom(locale).defaultNamesStartingWith(prefix);

    assertThat(result).isEqualTo(result2);
    assertThat(result).containsOnly("prefix", "prefix_en", "prefix_en_EN", "prefix_en_EN_gb");
    assertThat(result).doesNotHaveDuplicates();
  }

  /**
   * Test method for {@link LocaleGenerator#hashCode()}.
   */
  @Test
  public void testHashCode_Equal() {
    LocaleGenerator su1 = new LocaleGenerator(loc1_1);
    LocaleGenerator su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link LocaleGenerator#hashCode()}.
   */
  @Test
  public void testHashCode_Unequal() {
    LocaleGenerator su1 = new LocaleGenerator(loc1_1);
    LocaleGenerator su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_Reflexive() {
    LocaleGenerator su = new LocaleGenerator(loc1_1);
    assertThat(su).isEqualTo(su);

    su = new LocaleGenerator(loc2_1);
    assertThat(su).isEqualTo(su);

    su = new LocaleGenerator(loc3_1);
    assertThat(su).isEqualTo(su);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_Symmetric() {
    LocaleGenerator su1 = new LocaleGenerator(loc1_1);
    LocaleGenerator su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_Transitivity() {
    LocaleGenerator su1 = new LocaleGenerator(loc1_1);
    LocaleGenerator su2 = new LocaleGenerator(loc1_1);
    LocaleGenerator su3 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_1);
    su3 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_1);
    su3 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc1_2);
    su3 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_2);
    su3 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_2);
    su3 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_Consistency() {
    LocaleGenerator su1 = new LocaleGenerator(loc1_1);
    LocaleGenerator su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc1_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new LocaleGenerator(loc2_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new LocaleGenerator(loc3_1);
    su2 = new LocaleGenerator(loc3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_NullFalse() {
    LocaleGenerator su = new LocaleGenerator(loc1_1);
    assertThat(su).isNotEqualTo(null);

    su = new LocaleGenerator(loc1_2);
    assertThat(su).isNotEqualTo(null);

    su = new LocaleGenerator(loc2_1);
    assertThat(su).isNotEqualTo(null);

    su = new LocaleGenerator(loc2_2);
    assertThat(su).isNotEqualTo(null);

    su = new LocaleGenerator(loc3_1);
    assertThat(su).isNotEqualTo(null);

    su = new LocaleGenerator(loc3_2);
    assertThat(su).isNotEqualTo(null);
  }

  /**
   * Test method for {@link LocaleGenerator#equals(Object)}.
   */
  @Test
  public void testEquals_OtherClassFalse() {
    LocaleGenerator su = new LocaleGenerator(loc1_1);
    assertThat(su).isNotEqualTo(loc1_1);

    su = new LocaleGenerator(loc1_2);
    assertThat(su).isNotEqualTo(loc1_2);

    su = new LocaleGenerator(loc2_1);
    assertThat(su).isNotEqualTo(loc2_1);

    su = new LocaleGenerator(loc2_2);
    assertThat(su).isNotEqualTo(loc2_2);

    su = new LocaleGenerator(loc3_1);
    assertThat(su).isNotEqualTo(loc3_1);

    su = new LocaleGenerator(loc3_2);
    assertThat(su).isNotEqualTo(loc3_2);
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
