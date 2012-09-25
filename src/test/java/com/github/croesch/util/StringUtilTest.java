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

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides some basic test methods for {@link StringUtil}
 * 
 * @author croesch
 * @since Date: Feb 19, 2011
 */
public class StringUtilTest extends DefaultTestCase {
  private static String arr1_1 = "";

  private static String arr1_2 = "";

  private static String arr2_1 = "String 2";

  private static String arr2_2 = "String 2";

  private static String arr3_1 = "yet another string";

  private static String arr3_2 = "yet another string";

  /**
   * Test method for {@link StringUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Equal() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Unequal() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Reflexive() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isEqualTo(su);

    su = new StringUtil(arr2_1);
    assertThat(su).isEqualTo(su);

    su = new StringUtil(arr3_1);
    assertThat(su).isEqualTo(su);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Symmetric() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Transitivity() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_1);
    StringUtil su3 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    su3 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    su3 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_2);
    su3 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    su3 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    su3 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Consistency() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2))
                               .isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_NullFalse() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr1_2);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr2_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr2_2);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr3_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr3_2);
    assertThat(su).isNotEqualTo(null);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_OtherFalse() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isNotEqualTo(arr1_1);

    su = new StringUtil(arr1_2);
    assertThat(su).isNotEqualTo(arr1_2);

    su = new StringUtil(arr2_1);
    assertThat(su).isNotEqualTo(arr2_1);

    su = new StringUtil(arr2_2);
    assertThat(su).isNotEqualTo(arr2_2);

    su = new StringUtil(arr3_1);
    assertThat(su).isNotEqualTo(arr3_1);

    su = new StringUtil(arr3_2);
    assertThat(su).isNotEqualTo(arr3_2);
  }
}
