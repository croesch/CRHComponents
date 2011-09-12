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

import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test cases for {@link MnemonicUtil}
 * 
 * @author croesch
 * @since Date: Feb 09, 2011
 */
public class MnemonicUtilTest extends FestSwingJUnitTestCase {

  private JButton button;

  /**
   * Creates a JButton to use for testing
   * 
   * @author croesch
   * @throws InvocationTargetException
   * @throws InterruptedException
   * @since Date: Feb 09, 2011
   */
  @Override
  public void onSetUp() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          MnemonicUtilTest.this.button = new JButton();
        }
      });
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests to pass an empty {@link String}
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Empty() {
    final String s = MnemonicUtil.filterMnemonic("", this.button);
    assertThat(s).isEqualTo("");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests to pass a {@code null} value
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Null() {
    final String s = MnemonicUtil.filterMnemonic(null, this.button);
    assertThat(s).isNull();
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests a single bracket
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_SingleBracket1() {
    final String s = MnemonicUtil.filterMnemonic("[", this.button);
    assertThat(s).isEqualTo("[");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests a single bracket
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_SingleBracket2() {
    final String s = MnemonicUtil.filterMnemonic("]", this.button);
    assertThat(s).isEqualTo("]");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests to pass a {@code $} sign
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Dollar() {
    final String s = MnemonicUtil.filterMnemonic("$", this.button);
    assertThat(s).isEqualTo("$");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests to mark a space as mnemonic
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Space() {
    final String s = MnemonicUtil.filterMnemonic("Pete[ ]", this.button);
    assertThat(s).isEqualTo("Pete ");
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_SPACE);
  }

  /**
   * Tests empty brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_EmptyBracket() {
    final String s = MnemonicUtil.filterMnemonic("Pete[]", this.button);
    assertThat(s).isEqualTo("Pete[]");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests two bracket pairs
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_TwoBracketPairs() {
    final String s = MnemonicUtil.filterMnemonic("D[o]u[b]le", this.button);
    assertThat(s).isEqualTo("Dou[b]le");
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_O);
  }

  /**
   * Tests a non closed bracket
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_NotClosed() {
    final String s = MnemonicUtil.filterMnemonic("Ha[lf", this.button);
    assertThat(s).isEqualTo("Ha[lf");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests a non opened bracket
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_NotOpened() {
    final String s = MnemonicUtil.filterMnemonic("Ha]lf", this.button);
    assertThat(s).isEqualTo("Ha]lf");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests some brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Brackets1() {
    final String s = MnemonicUtil.filterMnemonic("D]o[u]b[le", this.button);
    assertThat(s).isEqualTo("D]oub[le");
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_U);
  }

  /**
   * Tests some brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_Brackets2() {
    final String s = MnemonicUtil.filterMnemonic("Do[u]b[[][][][][][][le", this.button);
    assertThat(s).isEqualTo("Doub[[][][][][][][le");
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_U);
  }

  /**
   * Tests brackets in brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilterMnemonic_BracketsInBrackets() {
    final String s = MnemonicUtil.filterMnemonic("Do[[u]]ble", this.button);
    assertThat(s).isEqualTo("Do[u]ble");
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_U);
  }

  /**
   * Tests too many characters in brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilerMnemonic_TooManyCharacters1() {
    final String s = MnemonicUtil.filterMnemonic("ma[nn]y", this.button);
    assertThat(s).isEqualTo("ma[nn]y");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests too many characters in brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilerMnemonic_TooManyCharacters2() {
    final String s = MnemonicUtil.filterMnemonic("[ma]nny", this.button);
    assertThat(s).isEqualTo("[ma]nny");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests too many characters in brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilerMnemonic_TooManyCharacters3() {
    final String s = MnemonicUtil.filterMnemonic("man[ny]", this.button);
    assertThat(s).isEqualTo("man[ny]");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests too many characters in brackets
   * 
   * @author croesch
   * @since Date: Feb 09, 2011
   */
  @Test
  public void testFilerMnemonic_TooManyCharacters4() {
    final String s = MnemonicUtil.filterMnemonic("[manny]", this.button);
    assertThat(s).isEqualTo("[manny]");
    assertThat(this.button.getMnemonic()).isZero();
  }

  /**
   * Tests that the constructor {@link MnemonicUtil} throws an exception to avoid being called
   * 
   * @author croesch
   * @since Date: Feb 19, 2011
   */
  @Test
  public void testConstructorException() {
    for (final Constructor<?> c : MnemonicUtil.class.getDeclaredConstructors()) {
      assertThat(c.getModifiers()).as(c.toString()).isEqualTo(2);
    }
  }
}
