package com.github.croesch.components;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the CMenuItem:<br>
 * - the mnemonic-behavior
 * 
 * @author croesch
 * @since Date: 2010/12/24 09:56:39
 */
@SuppressWarnings("nls")
public class CMenuItemTest {

  private CMenuItem item;

  /**
   * initializes this item
   */
  @Before
  public void setUp() {
    this.item = new CMenuItem();
  }

  /**
   * sets this item to null
   */
  @After
  public void tearDown() {
    this.item = null;
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(javax.swing.Icon)}.
   */
  @Test
  public void testCMenuIcon() {
    final Icon icon = new Icon() {

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

    this.item = new CMenuItem(icon);
    assertThat(this.item.getIcon()).isSameAs(icon);

    this.item = new CMenuItem((Icon) null);
    assertThat(this.item.getIcon()).isNull();
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(java.lang.String)}.
   */
  @Test
  public void testCMenuItemString() {
    this.item = new CMenuItem("St[a]rt");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("St[ar]t");
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    this.item = new CMenuItem("Star[t]");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("[S]tart");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(javax.swing.Action)}.
   */
  @Test
  public void testCMenuItemAction() {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    this.item = new CMenuItem(act);
    assertThat(this.item.getAction()).isSameAs(act);

    this.item = new CMenuItem((AbstractAction) null);
    assertThat(this.item.getAction()).isNull();
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(String, int)}.
   */
  @Test
  public void testCMenuItemStringMnemonic() {
    this.item = new CMenuItem("St[a]rt", KeyEvent.VK_R);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_R);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("St[ar]t", KeyEvent.VK_A);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    this.item = new CMenuItem("Star[t]", KeyEvent.VK_S);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("[S]tart", -19011989);
    assertThat(this.item.getMnemonic()).isEqualTo(-19011989);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(java.lang.String, javax.swing.Icon)}.
   */
  @Test
  public void testCMenuItemStringIcon() {
    this.item = new CMenuItem("St[a]rt", null);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("St[ar]t", null);
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    this.item = new CMenuItem("Star[t]", null);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item = new CMenuItem("[S]tart", null);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#setText(java.lang.String)}.
   */
  @Test
  public void testSetTextString() {
    this.item.setText("St[a]rt");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item.setText("St[ar]t");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    this.item.setText("Star[t]");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item.setText("[S]tart");
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");

    this.item.setText(null);
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isNull();
  }
}
