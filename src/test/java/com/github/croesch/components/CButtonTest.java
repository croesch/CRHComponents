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

import org.junit.After;
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
   * 
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  @Before
  public void setUp() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {

      @Override
      public void run() {
        CButtonTest.this.button = new CButton();
      }
    });
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
