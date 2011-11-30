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

/**
 * Provides test cases for {@link IntUtil}.
 * 
 * @author croesch
 * @since Date: Nov 30, 2011
 */
public class IntUtilTest {

  @Test
  public void testGetValue() {
    int value = -1;
    assertThat(new IntUtil(value).getValue()).isEqualTo(value);

    value = 1;
    assertThat(new IntUtil(value).getValue()).isEqualTo(value);

    value = 0;
    assertThat(new IntUtil(value).getValue()).isEqualTo(value);

    value = Integer.MAX_VALUE;
    assertThat(new IntUtil(value).getValue()).isEqualTo(value);

    value = Integer.MIN_VALUE;
    assertThat(new IntUtil(value).getValue()).isEqualTo(value);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDigit_IAE() {
    final IntUtil util = new IntUtil(123456);
    util.getDigit(-1);
  }

  @Test
  public void testGetDigit() {
    IntUtil util = new IntUtil(123456);
    assertThat(util.getDigit(0)).isEqualTo(6);
    assertThat(util.getDigit(1)).isEqualTo(5);
    assertThat(util.getDigit(2)).isEqualTo(4);
    assertThat(util.getDigit(3)).isEqualTo(3);
    assertThat(util.getDigit(4)).isEqualTo(2);
    assertThat(util.getDigit(5)).isEqualTo(1);
    assertThat(util.getDigit(6)).isEqualTo(0);
    assertThat(util.getDigit(7)).isEqualTo(0);
    assertThat(util.getDigit(8)).isEqualTo(0);

    util = new IntUtil(9876541);
    assertThat(util.getDigit(0)).isEqualTo(1);
    assertThat(util.getDigit(1)).isEqualTo(4);
    assertThat(util.getDigit(2)).isEqualTo(5);
    assertThat(util.getDigit(3)).isEqualTo(6);
    assertThat(util.getDigit(4)).isEqualTo(7);
    assertThat(util.getDigit(5)).isEqualTo(8);
    assertThat(util.getDigit(6)).isEqualTo(9);
    assertThat(util.getDigit(7)).isEqualTo(0);
    assertThat(util.getDigit(8)).isEqualTo(0);
  }

  @Test
  public void testGetDigit_Negative() {
    final IntUtil util = new IntUtil(-123456);
    assertThat(util.getDigit(0)).isEqualTo(6);
    assertThat(util.getDigit(1)).isEqualTo(5);
    assertThat(util.getDigit(2)).isEqualTo(4);
    assertThat(util.getDigit(3)).isEqualTo(3);
    assertThat(util.getDigit(4)).isEqualTo(2);
    assertThat(util.getDigit(5)).isEqualTo(1);
    assertThat(util.getDigit(6)).isEqualTo(0);
    assertThat(util.getDigit(7)).isEqualTo(0);
    assertThat(util.getDigit(8)).isEqualTo(0);
  }

  @Test
  public void testNotEquals() {
    IntUtil util1 = new IntUtil(1);
    IntUtil util2 = new IntUtil(-1);
    assertThat(util1).isNotEqualTo(util2);
    assertThat(util2).isNotEqualTo(util1);
    assertThat(util1.hashCode()).isNotEqualTo(util2.hashCode());

    util1 = new IntUtil(2);
    util2 = new IntUtil(-2);
    assertThat(util1).isNotEqualTo(util2);
    assertThat(util1.hashCode()).isNotEqualTo(util2.hashCode());

    util1 = new IntUtil(0);
    util2 = new IntUtil(1);
    assertThat(util1).isNotEqualTo(util2);
    assertThat(util1.hashCode()).isNotEqualTo(util2.hashCode());

    util1 = new IntUtil(Integer.MAX_VALUE);
    util2 = new IntUtil(Integer.MIN_VALUE);
    assertThat(util1).isNotEqualTo(util2);
    assertThat(util1.hashCode()).isNotEqualTo(util2.hashCode());
  }

  @Test
  public void testEquals() {
    IntUtil util1 = new IntUtil(1);
    IntUtil util2 = new IntUtil(1);
    assertThat(util1).isEqualTo(util2);
    assertThat(util2).isEqualTo(util1);
    assertThat(util1).isEqualTo(util1);
    assertThat(util1).isNotEqualTo(1);
    assertThat(util1).isNotEqualTo(null);
    assertThat(util1).isNotEqualTo("String");
    assertThat(util1.hashCode()).isEqualTo(util2.hashCode());

    util1 = new IntUtil(-1);
    util2 = new IntUtil(-1);
    assertThat(util1).isEqualTo(util2);
    assertThat(util1.hashCode()).isEqualTo(util2.hashCode());

    util1 = new IntUtil(0);
    util2 = new IntUtil(0);
    assertThat(util1).isEqualTo(util2);
    assertThat(util1.hashCode()).isEqualTo(util2.hashCode());

    util1 = new IntUtil(Integer.MAX_VALUE);
    util2 = new IntUtil(Integer.MAX_VALUE);
    assertThat(util1).isEqualTo(util2);
    assertThat(util1.hashCode()).isEqualTo(util2.hashCode());

    util1 = new IntUtil(Integer.MIN_VALUE);
    util2 = new IntUtil(Integer.MIN_VALUE);
    assertThat(util1).isEqualTo(util2);
    assertThat(util1.hashCode()).isEqualTo(util2.hashCode());
  }
}
