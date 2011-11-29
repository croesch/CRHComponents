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
package com.github.croesch.components;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Tests the CMenuItem:<br>
 * - the mnemonic-behavior
 * 
 * @author croesch
 * @since Date: Dec 24, 2010
 */
public class CMenuItemTest extends FestSwingJUnitTestCase {

  private CMenuItem item;

  /**
   * initializes this item
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Override
  public void onSetUp() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          CMenuItemTest.this.item = new CMenuItem();
        }
      });
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * sets this item to null
   */
  @Override
  public void onTearDown() {
    this.item = null;
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(javax.swing.Icon)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuIcon() throws InterruptedException, InvocationTargetException {
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

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem(icon);
      }
    });
    assertThat(this.item.getIcon()).isSameAs(icon);

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem((Icon) null);
      }
    });
    assertThat(this.item.getIcon()).isNull();
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(java.lang.String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuItemString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[a]rt");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[ar]t");
      }
    });
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("Star[t]");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("[S]tart");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(javax.swing.Action)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuItemAction() throws InterruptedException, InvocationTargetException {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem(act);
      }
    });
    assertThat(this.item.getAction()).isSameAs(act);

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem((AbstractAction) null);
      }
    });
    assertThat(this.item.getAction()).isNull();
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(String, int)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuItemStringMnemonic() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[a]rt", KeyEvent.VK_R);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_R);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[ar]t", KeyEvent.VK_A);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("Star[t]", KeyEvent.VK_S);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("[S]tart", -19011989);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(-19011989);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#CMenuItem(java.lang.String, javax.swing.Icon)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuItemStringIcon() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[a]rt", null);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("St[ar]t", null);
      }
    });
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("Star[t]", null);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item = new CMenuItem("[S]tart", null);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenuItem#setText(java.lang.String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testSetTextString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item.setText("St[a]rt");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item.setText("St[ar]t");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.item.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item.setText("Star[t]");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item.setText("[S]tart");
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuItemTest.this.item.setText(null);
      }
    });
    assertThat(this.item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.item.getText()).isNull();
  }
}
