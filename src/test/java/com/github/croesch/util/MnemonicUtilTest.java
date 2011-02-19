package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test cases for {@link MnemonicUtil}
 * 
 * @author croesch
 * @since Date: 09.02.2011 20:27:00
 */
@SuppressWarnings("nls")
public class MnemonicUtilTest {

  private JButton button;

  /**
   * Creates a JButton to use for testing
   * 
   * @author croesch
   * @since Date: 09.02.2011 20:57:19
   */
  @Before
  public void setUp() {
    this.button = new JButton();
  }

  /**
   * Tests to pass an empty {@link String}
   * 
   * @author croesch
   * @since Date: 09.02.2011 20:58:24
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
   * @since Date: 09.02.2011 20:58:49
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
   * @since Date: 09.02.2011 20:59:09
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
   * @since Date: 09.02.2011 20:59:09
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
   * @since Date: 09.02.2011 21:00:13
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
   * @since Date: 09.02.2011 21:00:39
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
   * @since Date: 09.02.2011 21:01:08
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
   * @since Date: 09.02.2011 21:01:23
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
   * @since Date: 09.02.2011 21:01:43
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
   * @since Date: 09.02.2011 21:02:02
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
   * @since Date: 09.02.2011 21:02:23
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
   * @since Date: 09.02.2011 21:02:23
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
   * @since Date: 09.02.2011 21:02:54
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
   * @since Date: 09.02.2011 21:05:46
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
   * @since Date: 09.02.2011 21:05:46
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
   * @since Date: 09.02.2011 21:05:46
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
   * @since Date: 09.02.2011 21:05:46
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
   * @since Date: 19.02.2011 19:57:30
   */
  @Test
  public void testConstructorException() {
    for (Constructor<?> c : MnemonicUtil.class.getDeclaredConstructors()) {
      assertThat(c.getModifiers()).as(c.toString()).isEqualTo(2);
    }
  }
}
