package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.util.StringUtil;

/**
 * Provides test methods for {@link StringUtil}
 * 
 * @author croesch
 * @since Date: 17.02.2011 19:50:44
 */
@SuppressWarnings("nls")
public class StringUtilTest {

  /**
   * Tests the constructor {@link StringUtil#StringMultiplier(String)}
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:34:16
   * @see com.github.croesch.util.Util#of(String)
   */
  @Test
  public void testConstructor() {
    assertThat(new StringUtil("a").toString()).isEqualTo("a");
    assertThat(new StringUtil("").toString()).isEqualTo("");
    assertThat(new StringUtil("constructor").toString()).isEqualTo("constructor");
    assertThat(new StringUtil("a").times(1)).isEqualTo("a");
    assertThat(new StringUtil("").times(1)).isEqualTo("");
    assertThat(new StringUtil("constructor").times(1)).isEqualTo("constructor");
  }

  /**
   * Ensures that {@link StringUtil#StringMultiplier(String)} throws an exception if {@code null} is passed
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:34:51
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorThrowsIAE() {
    new StringUtil(null);
  }

  /**
   * Ensures that {@link StringUtil#times(int)} throws an exception if a value less than zero is passed
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:35:27
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiplyThrowsIAE1() {
    new StringUtil("").times(-1);
  }

  /**
   * Ensures that {@link StringUtil#times(int)} throws an exception if a value less than zero is passed
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:35:27
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiplyThrowsIAE2() {
    new StringUtil("").times(-2);
  }

  /**
   * Ensures that {@link StringUtil#times(int)} returns an empty string if an empty string has been passed
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:35:27
   */
  @Test
  public void testMultiplyingString_Empty() {
    StringUtil sm = new StringUtil("");
    assertThat(sm.times(0)).isEqualTo("");
    assertThat(sm.times(1)).isEqualTo("");
    assertThat(sm.times(3)).isEqualTo("");
    assertThat(sm.times(10)).isEqualTo("");
    assertThat(sm.times(Integer.MAX_VALUE)).isEqualTo("");
  }

  /**
   * Ensures that a given char is correctly multiplied
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:36:31
   */
  @Test
  public void testMultiplyingString_A() {
    StringUtil sm = new StringUtil("a");
    assertThat(sm.times(0)).isEqualTo("");
    sm = new StringUtil("a");
    assertThat(sm.times(1)).isEqualTo("a");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(3)).isEqualTo("aaa");
    assertThat(sm.times(9)).isEqualTo("aaaaaaaaa");
  }

  /**
   * Ensures that two given chars are correctly multiplied
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:36:31
   */
  @Test
  public void testMultiplyingString_BC() {
    StringUtil sm = new StringUtil("bc");
    assertThat(sm.times(0)).isEqualTo("");
    sm = new StringUtil("bc");
    assertThat(sm.times(1)).isEqualTo("bc");
    assertThat(sm.times(3)).isEqualTo("bcbcbc");
    assertThat(sm.times(3)).isEqualTo("bcbcbc");
    assertThat(sm.times(9)).isEqualTo("bcbcbcbcbcbcbcbcbc");
  }

  /**
   * Ensures that two given words are correctly multiplied
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:36:31
   */
  @Test
  public void testMultiplyingString_TwoWords() {
    StringUtil sm = new StringUtil("two words");
    assertThat(sm.times(0)).isEqualTo("");
    sm = new StringUtil("two words");
    assertThat(sm.times(1)).isEqualTo("two words");
    assertThat(sm.times(3)).isEqualTo("two wordstwo wordstwo words");
    assertThat(sm.times(3)).isEqualTo("two wordstwo wordstwo words");
    assertThat(sm.times(9)).isEqualTo("two wordstwo wordstwo words"
                                              + "two wordstwo wordstwo wordstwo wordstwo wordstwo words");
  }

  /**
   * Ensures that a given string is correctly multiplied without being trimmed
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:36:31
   */
  @Test
  public void testMultiplyingString_NotTrimmed() {
    StringUtil sm = new StringUtil("  Not trimmed\t  \n\t  ");
    assertThat(sm.times(0)).isEqualTo("");
    sm = new StringUtil("  Not trimmed\t  \n\t  ");
    assertThat(sm.times(1)).isEqualTo("  Not trimmed\t  \n\t  ");
    assertThat(sm.times(3)).isEqualTo("  Not trimmed\t  \n\t  " + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t  ");
    assertThat(sm.times(9))
      .isEqualTo("  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  "
                         + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  "
                         + "  Not trimmed\t  \n\t    Not trimmed\t  \n\t    Not trimmed\t  \n\t  ");
  }

  /**
   * Tests that high multiplications return the correct length of the string
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:37:28
   */
  @Test
  public void testMulitplyingString_Often() {
    StringUtil sm = new StringUtil(" ");
    assertThat(sm.times(100000)).hasSize(100000);
  }

  /**
   * Tests that two parallel executing threads return the same result
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:37:52
   * @throws InterruptedException if something went wrong
   */
  @Test
  public void testMultiplyingStringTwoThreads_Equals() throws InterruptedException {
    StringUtil sm = new StringUtil("b");
    MyRunnable r1 = new MyRunnable(sm);
    MyRunnable r2 = new MyRunnable(sm);
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
      this.result = this.sm.times(100000);
      this.result = this.sm.times(100000);
      this.finished = true;
    }

    public boolean isFinished() {
      return this.finished;
    }

    public String getResult() {
      return this.result;
    }
  }
}