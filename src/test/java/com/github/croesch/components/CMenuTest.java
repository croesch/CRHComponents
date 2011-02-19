package com.github.croesch.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
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
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu = new CMenu("St[ar]t");
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("St[ar]t"));

    this.menu = new CMenu("Star[t]");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu = new CMenu("[S]tart");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));
  }

  /**
   * Test method for {@link CMenu#CMenu(String, boolean)}.
   */
  @Test
  public void testCMenuStringBoolean() {
    this.menu = new CMenu("St[a]rt", false);
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu = new CMenu("St[ar]t", false);
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("St[ar]t"));

    this.menu = new CMenu("Star[t]", false);
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu = new CMenu("[S]tart", false);
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));
  }

  /**
   * Test method for {@link CMenu#CMenu(Action)}.
   */
  @Test
  public void testCMenuAction() {
    final Action act = new AbstractAction(){
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    this.menu = new CMenu(act);
    Assert.assertThat(this.menu.getAction(), CoreMatchers.sameInstance(act));

    this.menu = new CMenu((AbstractAction)null);
    Assert.assertThat(this.menu.getAction(), CoreMatchers.nullValue());
  }

  /**
   * Test method for {@link CMenu#setText(String)}.
   */
  @Test
  public void testSetTextString() {
    this.menu.setText("St[a]rt");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu.setText("St[ar]t");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("St[ar]t"));

    this.menu.setText("Star[t]");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu.setText("[S]tart");
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.menu.getText(), CoreMatchers.is("Start"));

    this.menu.setText(null);
    Assert.assertThat(this.menu.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.menu.getText(), CoreMatchers.nullValue());
  }
}
