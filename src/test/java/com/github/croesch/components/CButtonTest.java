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
import javax.swing.ImageIcon;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Tests the CButton:<br>
 * <li>the mnemonic-behaviour</li><li>constructors</li>
 * 
 * @author croesch
 * @since Date: Dec 24, 2010
 */
public class CButtonTest extends DefaultTestCase {

  public static CButton getButton() {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      @SuppressWarnings("deprecation")
      protected CButton executeInEDT() {
        return new CButton();
      }
    });
  }

  public static CButton getButton(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      @SuppressWarnings("deprecation")
      protected CButton executeInEDT() {
        return new CButton(name);
      }
    });
  }

  public static CButton getButton(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      protected CButton executeInEDT() {
        return new CButton(name, text);
      }
    });
  }

  public static CButton getButton(final String name, final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      protected CButton executeInEDT() {
        return new CButton(name, a);
      }
    });
  }

  public static CButton getButton(final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      @SuppressWarnings("deprecation")
      protected CButton executeInEDT() {
        return new CButton(a);
      }
    });
  }

  public static CButton getButton(final String name, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      @SuppressWarnings("deprecation")
      protected CButton executeInEDT() {
        return new CButton(name, icon);
      }
    });
  }

  public static CButton getButton(final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      @SuppressWarnings("deprecation")
      protected CButton executeInEDT() {
        return new CButton(icon);
      }
    });
  }

  public static CButton getButton(final String name, final String text, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CButton>() {
      @Override
      protected CButton executeInEDT() {
        return new CButton(name, text, icon);
      }
    });
  }

  @Test
  public void testCButton_Icon() throws InterruptedException, InvocationTargetException {
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

    CButton button = getButton(icon);
    assertThat(button.getIcon()).isSameAs(icon);

    button = getButton((Icon) null);
    assertThat(button.getIcon()).isNull();
  }

  @Test
  public void testCButton_String() throws InterruptedException, InvocationTargetException {
    CButton button = getButton("St[a]rt");

    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(button.getText()).isEqualTo("Start");

    button = getButton("St[ar]t");
    assertThat(button.getText()).isEqualTo("St[ar]t");

    button = getButton("Star[t]");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(button.getText()).isEqualTo("Start");

    button = getButton("[S]tart");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(button.getText()).isEqualTo("Start");
  }

  @Test
  public void testCButton_Action() throws InterruptedException, InvocationTargetException {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    CButton button = getButton(act);
    assertThat(button.getAction()).isSameAs(act);

    button = getButton((AbstractAction) null);
    assertThat(button.getAction()).isNull();
  }

  @Test
  public void testCButton_StringIcon() throws InterruptedException, InvocationTargetException {
    CButton button = getButton("St[a]rt", (Icon) null);
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(button.getText()).isEqualTo("Start");

    button = getButton("St[ar]t", (Icon) null);
    assertThat(button.getText()).isEqualTo("St[ar]t");

    button = getButton("Star[t]", (Icon) null);
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(button.getText()).isEqualTo("Start");

    button = getButton("[S]tart", (Icon) null);
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(button.getText()).isEqualTo("Start");
  }

  @Test
  public void testSetTextString() throws InterruptedException, InvocationTargetException {
    final CButton button = getButton();
    setText(button, "St[a]rt");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(button.getText()).isEqualTo("Start");

    setText(button, "St[ar]t");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(button.getText()).isEqualTo("St[ar]t");

    setText(button, "Star[t]");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(button.getText()).isEqualTo("Start");

    setText(button, "[S]tart");
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(button.getText()).isEqualTo("Start");

    setText(button, null);
    assertThat(button.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(button.getText()).isNull();
  }

  private void setText(final CButton button, final String text) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        button.setText(text);
      }
    });
  }

  @Test
  public void testCButton_StringString() {
    CButton item = getButton("item", (String) null);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getText()).isEmpty();

    item = getButton("", "txt");
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("txt");

    item = getButton(null, "");
    assertThat(item.getName()).isNull();
    assertThat(item.getText()).isEmpty();

    item = getButton(" .. ", "12 <-> 34");
    assertThat(item.getName()).isEqualTo(" .. ");
    assertThat(item.getText()).isEqualTo("12 <-> 34");
  }

  @Test
  public void testCButton_StringStringIcon() {
    Icon icon = new ImageIcon(new byte[214]);
    CButton item = getButton("item", (String) null, icon);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getText()).isEmpty();
    assertThat(item.getIcon()).isEqualTo(icon);

    item = getButton("", "txt", icon);
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("txt");
    assertThat(item.getIcon()).isEqualTo(icon);

    icon = new ImageIcon(new byte[42]);
    item = getButton(null, "", icon);
    assertThat(item.getName()).isNull();
    assertThat(item.getText()).isEmpty();
    assertThat(item.getIcon()).isEqualTo(icon);

    icon = new ImageIcon();
    item = getButton(" .. ", "12 <-> 34", icon);
    assertThat(item.getName()).isEqualTo(" .. ");
    assertThat(item.getText()).isEqualTo("12 <-> 34");
    assertThat(item.getIcon()).isEqualTo(icon);
  }

  @Test
  public void testCButton_StringAction() {
    CButton item = getButton("item", (Action) null);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getAction()).isNull();

    item = getButton("", new AbstractAction("ACTION") {
      /** ... */
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent e) {}
    });
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("ACTION");
    assertThat(item.getAction()).isInstanceOf(AbstractAction.class);
  }
}
