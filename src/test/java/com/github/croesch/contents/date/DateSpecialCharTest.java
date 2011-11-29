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
package com.github.croesch.contents.date;

import static com.github.croesch.contents.date.ModificationType.CONSTANT;
import static com.github.croesch.contents.date.ModificationType.INCREMENT;
import static com.github.croesch.contents.date.ModificationType.OFFSET;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link DateSpecialChar}
 * 
 * @author croesch
 * @since Date: Apr 5, 2011
 */
public class DateSpecialCharTest extends FestSwingJUnitTestCase {

  /**
   * Test method for
   * {@link DateSpecialChar#DateSpecialChar(char, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int)}
   */
  @Test
  public void testConstructor() {
    final DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);

    assertThat(dsc.getChar()).isEqualTo('c');

    assertThat(dsc.getYearValueType()).isSameAs(CONSTANT);
    assertThat(dsc.getYearValue()).isZero();

    assertThat(dsc.getMonthValueType()).isSameAs(INCREMENT);
    assertThat(dsc.getMonthValue()).isEqualTo(-1);

    assertThat(dsc.getDayValueType()).isSameAs(OFFSET);
    assertThat(dsc.getDayValue()).isEqualTo(7);
  }

  /**
   * Test method for
   * {@link DateSpecialChar#DateSpecialChar(char, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int)}
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE1() {
    new DateSpecialChar('c', null, 0, INCREMENT, -1, OFFSET, 7);
  }

  /**
   * Test method for
   * {@link DateSpecialChar#DateSpecialChar(char, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int)}
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE2() {
    new DateSpecialChar('c', INCREMENT, 0, null, -1, OFFSET, 7);
  }

  /**
   * Test method for
   * {@link DateSpecialChar#DateSpecialChar(char, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int, com.github.croesch.contents.date.DateSpecialChar.ModificationType, int)}
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIAE3() {
    new DateSpecialChar('c', OFFSET, 0, INCREMENT, -1, null, 7);
  }

  /**
   * Test method for {@link DateSpecialChar#getChar()}
   */
  @Test
  public void testGetChar() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getChar()).isEqualTo('c');

    dsc = new DateSpecialChar('d', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getChar()).isEqualTo('d');

    dsc = new DateSpecialChar('e', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getChar()).isEqualTo('e');
  }

  /**
   * Test method for {@link DateSpecialChar#getYearValueType()}
   */
  @Test
  public void testGetYearValueType() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getYearValueType()).isEqualTo(CONSTANT);

    dsc = new DateSpecialChar('c', INCREMENT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getYearValueType()).isEqualTo(INCREMENT);

    dsc = new DateSpecialChar('c', OFFSET, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getYearValueType()).isEqualTo(OFFSET);
  }

  /**
   * Test method for {@link DateSpecialChar#getMonthValueType()}
   */
  @Test
  public void testGetMonthValueType() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getMonthValueType()).isEqualTo(INCREMENT);

    dsc = new DateSpecialChar('c', INCREMENT, 0, CONSTANT, -1, OFFSET, 7);
    assertThat(dsc.getMonthValueType()).isEqualTo(CONSTANT);

    dsc = new DateSpecialChar('c', OFFSET, 0, OFFSET, -1, OFFSET, 7);
    assertThat(dsc.getMonthValueType()).isEqualTo(OFFSET);
  }

  /**
   * Test method for {@link DateSpecialChar#getDayValueType()}
   */
  @Test
  public void testGetDayValueType() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getDayValueType()).isEqualTo(OFFSET);

    dsc = new DateSpecialChar('c', INCREMENT, 0, INCREMENT, -1, CONSTANT, 7);
    assertThat(dsc.getDayValueType()).isEqualTo(CONSTANT);

    dsc = new DateSpecialChar('c', OFFSET, 0, INCREMENT, -1, INCREMENT, 7);
    assertThat(dsc.getDayValueType()).isEqualTo(INCREMENT);
  }

  /**
   * Test method for {@link DateSpecialChar#getYearValue()}
   */
  @Test
  public void testGetYearValue() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getYearValue()).isEqualTo(0);

    dsc = new DateSpecialChar('c', INCREMENT, 1, INCREMENT, -1, CONSTANT, 6);
    assertThat(dsc.getYearValue()).isEqualTo(1);

    dsc = new DateSpecialChar('c', OFFSET, -2, INCREMENT, -1, INCREMENT, -5);
    assertThat(dsc.getYearValue()).isEqualTo(-2);
  }

  /**
   * Test method for {@link DateSpecialChar#getMonthValue()}
   */
  @Test
  public void testGetMonthValue() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getMonthValue()).isEqualTo(-1);

    dsc = new DateSpecialChar('c', INCREMENT, 0, INCREMENT, -2, CONSTANT, 6);
    assertThat(dsc.getMonthValue()).isEqualTo(-2);

    dsc = new DateSpecialChar('c', OFFSET, 0, INCREMENT, 3, INCREMENT, -5);
    assertThat(dsc.getMonthValue()).isEqualTo(3);
  }

  /**
   * Test method for {@link DateSpecialChar#getDayValue()}
   */
  @Test
  public void testGetDayValue() {
    DateSpecialChar dsc = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc.getDayValue()).isEqualTo(7);

    dsc = new DateSpecialChar('c', INCREMENT, 0, INCREMENT, -1, CONSTANT, 6);
    assertThat(dsc.getDayValue()).isEqualTo(6);

    dsc = new DateSpecialChar('c', OFFSET, 0, INCREMENT, -1, INCREMENT, -5);
    assertThat(dsc.getDayValue()).isEqualTo(-5);
  }

  /**
   * Test method for {@link DateSpecialChar#equals(Object)}
   */
  @Test
  public void testEqualsAndHashCode() {
    DateSpecialChar dsc1 = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    DateSpecialChar dsc2 = new DateSpecialChar('c', CONSTANT, 0, INCREMENT, -1, OFFSET, 7);
    assertThat(dsc1).isEqualTo(dsc2);
    assertThat(dsc2).isEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isEqualTo(dsc2.hashCode());

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    assertThat(dsc1).isEqualTo(dsc2);
    assertThat(dsc2).isEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isEqualTo(dsc2.hashCode());

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 18);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, INCREMENT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 2, CONSTANT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, -14, CONSTANT, 1, CONSTANT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', INCREMENT, 14, OFFSET, 1, CONSTANT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('e', OFFSET, -14, OFFSET, 1, CONSTANT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    dsc1 = new DateSpecialChar('e', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    dsc2 = new DateSpecialChar('f', INCREMENT, -14, OFFSET, 1, CONSTANT, 19);
    assertThat(dsc1).isNotEqualTo(dsc2);
    assertThat(dsc2).isNotEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isNotEqualTo(dsc2.hashCode()); // not useful to ensure functionality

    final String s = "...";
    assertThat(dsc1).isNotEqualTo(s);
    assertThat(s.equals(dsc1)).isFalse();
    assertThat(dsc1.hashCode()).isNotEqualTo(s.hashCode()); // not useful to ensure functionality

    assertThat(dsc1).isNotEqualTo(null);
    assertThat(dsc2).isNotEqualTo(null);
    assertThat(dsc1).isNotEqualTo((DateSpecialChar) null);
    assertThat(dsc2).isNotEqualTo((DateSpecialChar) null);

    assertThat(dsc1).isEqualTo(dsc1);
    assertThat(dsc1.hashCode()).isEqualTo(dsc1.hashCode());
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
