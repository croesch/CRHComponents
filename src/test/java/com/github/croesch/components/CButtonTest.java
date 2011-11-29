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
 * Tests the CButton:<br>
 * <li>the mnemonic-behaviour</li><li>constructors</li>
 * 
 * @author croesch
 * @since Date: Dec 24, 2010
 */
public class CButtonTest extends FestSwingJUnitTestCase {

  private CButton button;

  /**
   * initializes this button
   */
  @Override
  public void onSetUp() {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {

        @Override
        public void run() {
          CButtonTest.this.button = new CButton();
        }
      });
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * sets this button to null
   */
  @Override
  public void onTearDown() {
    this.button = null;
  }

  /**
   * Test method for {@link CButton#CButton(Icon)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCButtonIcon() throws InterruptedException, InvocationTargetException {
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
        CButtonTest.this.button = new CButton(icon);
      }
    });

    assertThat(this.button.getIcon()).isSameAs(icon);

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton((Icon) null);
      }
    });

    assertThat(this.button.getIcon()).isNull();
  }

  /**
   * Test method for {@link CButton#CButton(java.lang.String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCButtonString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("St[a]rt");
      }
    });

    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("St[ar]t");
      }
    });
    assertThat(this.button.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("Star[t]");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("[S]tart");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.button.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CButton#CButton(Action)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCButtonAction() throws InterruptedException, InvocationTargetException {
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
        CButtonTest.this.button = new CButton(act);
      }
    });
    assertThat(this.button.getAction()).isSameAs(act);

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton((AbstractAction) null);
      }
    });
    assertThat(this.button.getAction()).isNull();
  }

  /**
   * Test method for {@link CButton#CButton(java.lang.String, javax.swing.Icon)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testCButtonStringIcon() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("St[a]rt", null);
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("St[ar]t", null);
      }
    });
    assertThat(this.button.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("Star[t]", null);
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton("[S]tart", null);
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.button.getText()).isEqualTo("Start");
  }

  /**
   * Test method for {@link CButton#setText(java.lang.String)}.
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Test
  public void testSetTextString() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button.setText("St[a]rt");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button.setText("St[ar]t");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(this.button.getText()).isEqualTo("St[ar]t");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button.setText("Star[t]");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button.setText("[S]tart");
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.button.getText()).isEqualTo("Start");

    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button.setText(null);
      }
    });
    assertThat(this.button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(this.button.getText()).isNull();
  }
}
