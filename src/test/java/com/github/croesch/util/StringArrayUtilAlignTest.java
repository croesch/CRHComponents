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
 * Provides test cases for:<br>
 * <li>{@link StringArrayUtil#toCentreAlignedArray()}</li> <li>{@link StringArrayUtil#toLeftAlignedArray()}</li> <li>
 * {@link StringArrayUtil#toRightAlignedArray()}</li>
 * 
 * @author croesch
 * @since Date: Feb 15, 2011
 */
public class StringArrayUtilAlignTest extends DefaultTestCase {

  /**
   * Tests the behaviour of left alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignLeft() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$ " };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph " };
    expected = new String[] { "Alph$a", "Alph  " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { "Alph ", "Alpha" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph ", "Alpha", "   ", ".  " };
    expected = new String[] { "Alph ", "Alpha", "     ", ".    " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "A  ", "b ", "c  ", "delta" };
    expected = new String[] { "A    ", "b    ", "c    ", "delta" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of right alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignRight() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "\tAlph$" };
    expected = new String[] { "Alph$a", "\tAlph$" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "\nAlph" };
    expected = new String[] { "Alph$a", " \nAlph" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "  ", "\t\n." };
    expected = new String[] { " Alph", "Alpha", "     ", "  \t\n." };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  A", "    b", "   c", "delta" };
    expected = new String[] { "    A", "    b", "    c", "delta" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of centre alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignCentre() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph " };
    expected = new String[] { "Alph$a", " Alph " };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "", "." };
    expected = new String[] { " Alph", "Alpha", "     ", "  .  " };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "A ", " b ", " c  ", "delta" };
    expected = new String[] { "  A  ", "  b  ", "  c  ", "delta" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "    A", "b", "c", "delta", "länger" };
    expected = new String[] { "     A", "   b  ", "   c  ", " delta", "länger" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of left alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignLeft_Trim() {
    String[] text = { " \t\t Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a   ", "   Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "   Alph$a", "Alph$   " };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "              Alph $a  ", "Alph  " };
    expected = new String[] { "Alph $a", "Alph   " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "     Alpha     " };
    expected = new String[] { "Alph ", "Alpha" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "                                       ", "." };
    expected = new String[] { "Alph ", "Alpha", "     ", ".    " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "A ", " b ", "                               c ", "  delta " };
    expected = new String[] { "A    ", "b    ", "c    ", "delta" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of right alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignRight_Trim() {
    String[] text = { "   Alpha   " };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  Alph$a  ", "  Alph$a  " };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { " Alp h$a", " Alph $ " };
    expected = new String[] { "Alp h$a", " Alph $" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a                   ", "\t\t\t\t\tAlph   " };
    expected = new String[] { "Alph$a", "  Alph" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "                  Alph", "Alpha " };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  Alph   ", "    Alpha", "  \t\t\t\t\t\t                 ", "." };
    expected = new String[] { " Alph", "Alpha", "     ", "    ." };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { " A ", "b", "c", "delta" };
    expected = new String[] { "    A", "    b", "    c", "delta" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of centre alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: Feb 16, 2011
   */
  @Test
  public void testAlignCentre_Trim() {
    String[] text = { "    Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a    ", "   Alph$a " };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a   ", "Alph$   " };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a ", " Alph" };
    expected = new String[] { "Alph$a", " Alph " };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph   ", "Alpha   ", "", ". " };
    expected = new String[] { " Alph", "Alpha", "     ", "  .  " };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "A", "b", "c    ", "delta" };
    expected = new String[] { "  A  ", "  b  ", "  c  ", "delta" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "     A", "             b ", "c", "delta   ", "länger" };
    expected = new String[] { "   A  ", "   b  ", "   c  ", " delta", "länger" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);
  }
}
