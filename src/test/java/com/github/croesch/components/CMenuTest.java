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

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingUtilities;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Tests the CMenu:<br>
 * - the mnemonic-behavior
 * 
 * @author croesch
 * @since Date: Dec 24, 2010
 */
public class CMenuTest extends DefaultTestCase {

  private CMenu menu;

  /**
   * initializes this menu
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Override
  public void setUpDetails() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          CMenuTest.this.menu = new CMenu();
        }
      });
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * sets this menu to null
   */
  @Override
  public void onTearDown() {
    this.menu = null;
  }

  /**
   * Test method for {@link CMenu#CMenu(String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("St[a]rt");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("St[ar]t");
      }
    });
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("Star[t]");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("[S]tart");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenu#CMenu(String, boolean)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuStringBoolean() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("St[a]rt", false);
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("St[ar]t", false);
      }
    });
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("Star[t]", false);
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu("[S]tart", false);
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CMenu#CMenu(Action)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCMenuAction() throws InterruptedException, InvocationTargetException {
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
        CMenuTest.this.menu = new CMenu(act);
      }
    });
    assertThat(this.menu.getAction()).isSameAs(act);

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CMenuTest.this.menu = new CMenu((AbstractAction) null);
      }
    });
    assertThat(this.menu.getAction()).isNull();
  }

  /**
   * Test method for {@link CMenu#setText(String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testSetTextString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuTest.this.menu.setText("St[a]rt");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuTest.this.menu.setText("St[ar]t");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.menu.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuTest.this.menu.setText("Star[t]");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuTest.this.menu.setText("[S]tart");
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        CMenuTest.this.menu.setText(null);
      }
    });
    assertThat(this.menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.menu.getText()).isNull();
  }
}
