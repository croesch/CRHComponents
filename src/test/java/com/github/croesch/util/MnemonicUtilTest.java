package com.github.croesch.util;

import static java.lang.Integer.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import com.github.croesch.util.MnemonicUtil;

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
    assertThat(s, is(""));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, CoreMatchers.nullValue());
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("["));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("]"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("$"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("Pete "));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(KeyEvent.VK_SPACE)));
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
    assertThat(s, is("Pete[]"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("Dou[b]le"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(KeyEvent.VK_O)));
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
    assertThat(s, is("Ha[lf"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("Ha]lf"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("D]oub[le"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(KeyEvent.VK_U)));
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
    assertThat(s, is("Doub[[][][][][][][le"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(KeyEvent.VK_U)));
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
    assertThat(s, is("Do[u]ble"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(KeyEvent.VK_U)));
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
    assertThat(s, is("ma[nn]y"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("[ma]nny"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("man[ny]"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
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
    assertThat(s, is("[manny]"));
    assertThat(valueOf(this.button.getMnemonic()), is(valueOf(0)));
  }
}
