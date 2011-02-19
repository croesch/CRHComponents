package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides methods to test {@link StringArrayUtil#getMaxLineLength()}
 * 
 * @author croesch
 * @since Date: 17.02.2011 20:59:00
 */
@SuppressWarnings("nls")
public class StringArrayUtilLengthTest {

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test
  public void testLengthAnalyser() {
    new StringArrayUtil(new String[] {});
    new StringArrayUtil(new String[] {"a"});
    new StringArrayUtil(new String[] {"", "a"});
  }

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLengthAnalyser_IAE1() {
    new StringArrayUtil(null);
  }

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLengthAnalyser_IAE2() {
    new StringArrayUtil(new String[] {null});
  }

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLengthAnalyser_IAE3() {
    new StringArrayUtil(new String[] {"", null});
  }

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLengthAnalyser_IAE4() {
    new StringArrayUtil(new String[] {null, ""});
  }

  /**
   * Test method for {@link StringArrayUtil#StringArrayUtil(String[])}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLengthAnalyser_IAE5() {
    new StringArrayUtil(new String[] {"", null, ""});
  }

  /**
   * Tests the method {@link StringArrayUtil#getMaxLineLength()} with some valid input
   * 
   * @author croesch
   * @since Date: 17.02.2011 18:44:08
   */
  @Test
  public void testGetMaxLineLength1() {
    assertThat(new StringArrayUtil(new String[] {}).getMaxLineLength()).isEqualTo(-1);
    assertThat(new StringArrayUtil(new String[] {""}).getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {"a"}).getMaxLineLength()).isEqualTo(1);
    assertThat(new StringArrayUtil(new String[] {"ab"}).getMaxLineLength()).isEqualTo(2);
    assertThat(new StringArrayUtil(new String[] {"abc"}).getMaxLineLength()).isEqualTo(3);
    assertThat(new StringArrayUtil(new String[] {"abcd"}).getMaxLineLength()).isEqualTo(4);
    assertThat(new StringArrayUtil(new String[] {"abcde"}).getMaxLineLength()).isEqualTo(5);
    assertThat(new StringArrayUtil(new String[] {"abcdef"}).getMaxLineLength()).isEqualTo(6);
  }

  /**
   * Tests the method {@link StringArrayUtil#getMaxLineLength()} with some valid input
   * 
   * @author croesch
   * @since Date: 17.02.2011 18:44:08
   */
  @Test
  public void testGetMaxLineLength2() {
    assertThat(new StringArrayUtil(new String[] {"", ""}).getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {"", "", "", "", ""}).getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {"a", "", "", "", ""}).getMaxLineLength()).isEqualTo(1);
    assertThat(new StringArrayUtil(new String[] {",", "ab", "bb", "."}).getMaxLineLength()).isEqualTo(2);
    assertThat(new StringArrayUtil(new String[] {"a", "ab", "abc", " "}).getMaxLineLength()).isEqualTo(3);
    assertThat(new StringArrayUtil(new String[] {"null", "0", "$", "%", "€€€€", "abcd"}).getMaxLineLength())
      .isEqualTo(4);
    assertThat(new StringArrayUtil(new String[] {"!!!!!", "????", "...", "''", "-", ""}).getMaxLineLength())
      .isEqualTo(5);
    assertThat(new StringArrayUtil(new String[] {"", "", "", "", "", "abcdef"}).getMaxLineLength()).isEqualTo(6);
    assertThat(new StringArrayUtil(new String[] {"", "", "", "", "", "abcdef", "", "", ""}).getMaxLineLength())
      .isEqualTo(6);
  }

  /**
   * Tests the method {@link StringArrayUtil#getMaxLineLength()} with some valid input
   * 
   * @author croesch
   * @since Date: 17.02.2011 18:44:08
   */
  @Test
  public void testGetMaxLineLength_Trimmed1() {
    assertThat(new StringArrayUtil(new String[] {}).trim().getMaxLineLength()).isEqualTo(-1);
    assertThat(new StringArrayUtil(new String[] {"      \t\n\t      "}).trim().getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {"   a"}).trim().getMaxLineLength()).isEqualTo(1);
    assertThat(new StringArrayUtil(new String[] {"ab       "}).trim().getMaxLineLength()).isEqualTo(2);
    assertThat(new StringArrayUtil(new String[] {"\t\t\tabc"}).trim().getMaxLineLength()).isEqualTo(3);
    assertThat(new StringArrayUtil(new String[] {"abcd\t\t\t"}).trim().getMaxLineLength()).isEqualTo(4);
    assertThat(new StringArrayUtil(new String[] {"\n\n\nabcde"}).trim().getMaxLineLength()).isEqualTo(5);
    assertThat(new StringArrayUtil(new String[] {"abcdef\n\n\n"}).trim().getMaxLineLength()).isEqualTo(6);
  }

  /**
   * Tests the method {@link StringArrayUtil#getMaxLineLength()} with some valid input
   * 
   * @author croesch
   * @since Date: 17.02.2011 18:44:08
   */
  @Test
  public void testGetMaxLineLength_Trimmed2() {
    assertThat(new StringArrayUtil(new String[] {"\t\t\t\t\t\t\t\t", "  "}).trim().getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {" ", "\t", "\n", "\r", ""}).trim().getMaxLineLength()).isZero();
    assertThat(new StringArrayUtil(new String[] {"a", "", "  ", "\n", "\r\n"}).trim().getMaxLineLength()).isEqualTo(1);
    assertThat(new StringArrayUtil(new String[] {" , ", "\tab\n", "\rbb ", "  .  "}).trim().getMaxLineLength())
      .isEqualTo(2);
    assertThat(new StringArrayUtil(new String[] {"a", "ab   ", "abc ", "  "}).trim().getMaxLineLength()).isEqualTo(3);
    assertThat(
               new StringArrayUtil(new String[] {"null\n\n", "\n0 ", "\t\r$\n", "%   ", "  €€€€  ", " abcd "}).trim()
                 .getMaxLineLength()).isEqualTo(4);
    assertThat(
               new StringArrayUtil(new String[] {"  !!!!!", "  ????", "   ...", "''  ", "- ", "   "}).trim()
                 .getMaxLineLength()).isEqualTo(5);
    assertThat(
               new StringArrayUtil(new String[] {"          ", "", "       ", "", " ", "abcdef"}).trim()
                 .getMaxLineLength()).isEqualTo(6);
    assertThat(
               new StringArrayUtil(new String[] {"",
                                                 "  \r  ",
                                                 "  \t  ",
                                                 "  \n  ",
                                                 "       ",
                                                 "abcdef",
                                                 "",
                                                 "   ",
                                                 "   "}).trim().getMaxLineLength()).isEqualTo(6);
  }
}
