package com.github.croesch.components;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CButton:<br>
 * <li>the mnemonic-behaviour</li><li>constructors</li>
 * 
 * @author croesch
 * @since Date: 2010/12/24 09:56:39
 */
@SuppressWarnings("nls")
public class CButtonTest {

  private CButton button;

  /**
   * initializes this button
   */
  @Before
  public void setUp() {
    this.button = new CButton();
  }

  /**
   * sets this button to null
   */
  @After
  public void tearDown() {
    this.button = null;
  }

  /**
   * Test method for {@link CButton#CButton(Icon)}.
   */
  @Test
  public void testCButtonIcon() {
    final Icon icon = new Icon(){

      @Override
      public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        //stupid stub
      }

      @Override
      public int getIconWidth() {
        return 0;
      }

      @Override
      public int getIconHeight() {
        return 0;
      }
    };

    this.button = new CButton(icon);
    Assert.assertThat(this.button.getIcon(), CoreMatchers.sameInstance(icon));

    this.button = new CButton((Icon)null);
    Assert.assertThat(this.button.getIcon(), CoreMatchers.nullValue());
  }

  /**
   * Test method for {@link CButton#CButton(java.lang.String)}.
   */
  @Test
  public void testCButtonString() {
    this.button = new CButton("St[a]rt");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button = new CButton("St[ar]t");
    Assert.assertThat(this.button.getText(), CoreMatchers.is("St[ar]t"));

    this.button = new CButton("Star[t]");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button = new CButton("[S]tart");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));
  }

  /**
   * Test method for {@link CButton#CButton(Action)}.
   */
  @Test
  public void testCButtonAction() {
    final Action act = new AbstractAction(){
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    this.button = new CButton(act);
    Assert.assertThat(this.button.getAction(), CoreMatchers.sameInstance(act));

    this.button = new CButton((AbstractAction)null);
    Assert.assertThat(this.button.getAction(), CoreMatchers.nullValue());
  }

  /**
   * Test method for {@link CButton#CButton(java.lang.String, javax.swing.Icon)}.
   */
  @Test
  public void testCButtonStringIcon() {
    this.button = new CButton("St[a]rt", null);
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button = new CButton("St[ar]t", null);
    Assert.assertThat(this.button.getText(), CoreMatchers.is("St[ar]t"));

    this.button = new CButton("Star[t]", null);
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button = new CButton("[S]tart", null);
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));
  }

  /**
   * Test method for {@link CButton#setText(java.lang.String)}.
   */
  @Test
  public void testSetTextString() {
    this.button.setText("St[a]rt");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button.setText("St[ar]t");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_A));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("St[ar]t"));

    this.button.setText("Star[t]");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_T));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button.setText("[S]tart");
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.button.getText(), CoreMatchers.is("Start"));

    this.button.setText(null);
    Assert.assertThat(this.button.getMnemonic(), CoreMatchers.is(KeyEvent.VK_S));
    Assert.assertThat(this.button.getText(), CoreMatchers.nullValue());
  }
}
