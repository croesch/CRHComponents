package com.github.croesch.components;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CMenu:<br>
 * - the mnemonic-behavior
 * 
 * @author croesch
 * @since Date: 2010/12/24 09:56:39
 */
@SuppressWarnings("nls")
public class CMenuTest {

  private CMenu menu;

  /**
   * initializes this menu
   */
  @Before
  public void setUp() {
    this.menu = new CMenu();
  }

  /**
   * sets this menu to null
   */
  @After
  public void tearDown() {
    this.menu = null;
  }

  /**
   * Test method for {@link CMenu#CMenu(String)}.
   */
  @Test
  public void testCMenuString() {
    this.menu = new CMenu("St[a]rt");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu = new CMenu("St[ar]t");
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    this.menu = new CMenu("Star[t]");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu = new CMenu("[S]tart");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenu#CMenu(String, boolean)}.
   */
  @Test
  public void testCMenuStringBoolean() {
    this.menu = new CMenu("St[a]rt", false);
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu = new CMenu("St[ar]t", false);
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    this.menu = new CMenu("Star[t]", false);
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu = new CMenu("[S]tart", false);
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenu#CMenu(Action)}.
   */
  @Test
  public void testCMenuAction() {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    this.menu = new CMenu(act);
    assertThat(this.menu.getAction()).isSameAs(act);

    this.menu = new CMenu((AbstractAction) null);
    assertThat(this.menu.getAction()).isNull();
  }

  /**
   * Test method for {@link CMenu#setText(String)}.
   */
  @Test
  public void testSetTextString() {
    this.menu.setText("St[a]rt");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu.setText("St[ar]t");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    this.menu.setText("Star[t]");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu.setText("[S]tart");
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");

    this.menu.setText(null);
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isNull();
  }
}
