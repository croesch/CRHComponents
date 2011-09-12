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

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link StringUtil}
 * 
 * @author croesch
 * @since Date: Feb 17, 2011
 */
public class StringUtilMultiplyTest extends FestSwingJUnitTestCase {

  /**
   * Tests the constructor {@link StringUtil#StringUtil(String)}
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   * @see com.github.croesch.util.Util#of(String)
   */
  @Test
  public void testConstructor() {
    assertThat(new StringUtil("a").toString()).isEqualTo("a");
    assertThat(new StringUtil("").toString()).isEqualTo("");
    assertThat(new StringUtil("constructor").toString()).isEqualTo("constructor");
    assertThat(new StringUtil("a").toStringMultipliedWith(1)).isEqualTo("a");
    assertThat(new StringUtil("").toStringMultipliedWith(1)).isEqualTo("");
    assertThat(new StringUtil("constructor").toStringMultipliedWith(1)).isEqualTo("constructor");
  }

  /**
   * Ensures that {@link StringUtil#StringUtil(String)} throws an exception if {@code null} is passed
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThrowsIAE() {
    new StringUtil(null);
  }

  /**
   * Ensures that {@link StringUtil#toStringMultipliedWith(int)} throws an exception if a value less than zero is passed
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiplyThrowsIAE1() {
    new StringUtil("").toStringMultipliedWith(-1);
  }

  /**
   * Ensures that {@link StringUtil#toStringMultipliedWith(int)} throws an exception if a value less than zero is passed
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiplyThrowsIAE2() {
    new StringUtil("").toStringMultipliedWith(-2);
  }

  /**
   * Ensures that {@link StringUtil#toStringMultipliedWith(int)} returns an empty string if an empty string has been
   * passed
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMultiplyingString_Empty() {
    final StringUtil sm = new StringUtil("");
    assertThat(sm.toStringMultipliedWith(0)).isEqualTo("");
    assertThat(sm.toStringMultipliedWith(1)).isEqualTo("");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("");
    assertThat(sm.toStringMultipliedWith(10)).isEqualTo("");
    assertThat(sm.toStringMultipliedWith(Integer.MAX_VALUE)).isEqualTo("");
  }

  /**
   * Ensures that a given char is correctly multiplied
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMultiplyingString_A() {
    StringUtil sm = new StringUtil("a");
    assertThat(sm.toStringMultipliedWith(0)).isEqualTo("");
    sm = new StringUtil("a");
    assertThat(sm.toStringMultipliedWith(1)).isEqualTo("a");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("aaa");
    assertThat(sm.toStringMultipliedWith(9)).isEqualTo("aaaaaaaaa");
  }

  /**
   * Ensures that two given chars are correctly multiplied
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMultiplyingString_BC() {
    StringUtil sm = new StringUtil("bc");
    assertThat(sm.toStringMultipliedWith(0)).isEqualTo("");
    sm = new StringUtil("bc");
    assertThat(sm.toStringMultipliedWith(1)).isEqualTo("bc");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("bcbcbc");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("bcbcbc");
    assertThat(sm.toStringMultipliedWith(9)).isEqualTo("bcbcbcbcbcbcbcbcbc");
  }

  /**
   * Ensures that two given words are correctly multiplied
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMultiplyingString_TwoWords() {
    StringUtil sm = new StringUtil("two words");
    assertThat(sm.toStringMultipliedWith(0)).isEqualTo("");
    sm = new StringUtil("two words");
    assertThat(sm.toStringMultipliedWith(1)).isEqualTo("two words");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("two wordstwo wordstwo words");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("two wordstwo wordstwo words");
    assertThat(sm.toStringMultipliedWith(9))
      .isEqualTo("two wordstwo wordstwo words" + "two wordstwo wordstwo wordstwo wordstwo wordstwo words");
  }

  /**
   * Ensures that a given string is correctly multiplied without being trimmed
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMultiplyingString_NotTrimmed() {
    StringUtil sm = new StringUtil("  Not trimmed\t  \n\t  ");
    assertThat(sm.toStringMultipliedWith(0)).isEqualTo("");
    sm = new StringUtil("  Not trimmed\t  \n\t  ");
    assertThat(sm.toStringMultipliedWith(1)).isEqualTo("  Not trimmed\t  \n\t  ");
    assertThat(sm.toStringMultipliedWith(3)).isEqualTo("  Not trimmed\t  \n\t  "
                                                               + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t  ");
    assertThat(sm.toStringMultipliedWith(9))
      .isEqualTo("  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  "
                         + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  "
                         + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  ");
  }

  /**
   * Tests that high multiplications return the correct length of the string
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   */
  @Test
  public void testMulitplyingString_Often() {
    final StringUtil sm = new StringUtil(" ");
    assertThat(sm.toStringMultipliedWith(100000)).hasSize(100000);
  }

  /**
   * Tests that two parallel executing threads return the same result
   * 
   * @author croesch
   * @since Date: Feb 17, 2011
   * @throws InterruptedException if something went wrong
   */
  @Test
  public void testMultiplyingStringTwoThreads_Equals() throws InterruptedException {
    final StringUtil sm = new StringUtil("b");
    final MyRunnable r1 = new MyRunnable(sm);
    final MyRunnable r2 = new MyRunnable(sm);
    r1.run();
    r2.run();
    while (!r1.isFinished() || !r2.isFinished()) {
      Thread.sleep(500);
    }
    assertThat(r1.getResult()).isEqualTo(r2.getResult());
  }

  private static class MyRunnable implements Runnable {
    private boolean finished = false;

    private String result = null;

    private final StringUtil sm;

    MyRunnable(final StringUtil sm) {
      this.sm = sm;
    }

    @Override
    public void run() {
      this.result = this.sm.toStringMultipliedWith(100000);
      this.result = this.sm.toStringMultipliedWith(100000);
      this.finished = true;
    }

    public boolean isFinished() {
      return this.finished;
    }

    public String getResult() {
      return this.result;
    }
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
