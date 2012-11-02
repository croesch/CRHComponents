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
 * Tests the CMenuItem:<br>
 * - the mnemonic-behavior
 * 
 * @author croesch
 * @since Date: Dec 24, 2010
 */
public class CMenuItemTest extends DefaultTestCase {

  @Test
  public void testMenu_Icon() throws InterruptedException, InvocationTargetException {
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

    CMenuItem item = getMenuItem(icon);
    assertThat(item.getIcon()).isSameAs(icon);

    item = getMenuItem((Icon) null);
    assertThat(item.getIcon()).isNull();
  }

  @Test
  public void testMenuItem_String() throws InterruptedException, InvocationTargetException {
    CMenuItem item = getMenuItem("St[a]rt");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("St[ar]t");
    assertThat(item.getText()).isEqualTo("St[ar]t");

    item = getMenuItem("Star[t]");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("[S]tart");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(item.getText()).isEqualTo("Start");
  }

  @Test
  public void testMenuItem_Action() throws InterruptedException, InvocationTargetException {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    CMenuItem item = getMenuItem(act);
    assertThat(item.getAction()).isSameAs(act);

    item = getMenuItem((AbstractAction) null);
    assertThat(item.getAction()).isNull();
  }

  @Test
  public void testMenuItem_StringMnemonic() throws InterruptedException, InvocationTargetException {
    CMenuItem item = getMenuItem("St[a]rt", KeyEvent.VK_R);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_R);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("St[ar]t", KeyEvent.VK_A);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(item.getText()).isEqualTo("St[ar]t");

    item = getMenuItem("Star[t]", KeyEvent.VK_S);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("[S]tart", -19011989);
    assertThat(item.getMnemonic()).isEqualTo(-19011989);
    assertThat(item.getText()).isEqualTo("Start");
  }

  @Test
  public void testMenuItem_StringIcon() throws InterruptedException, InvocationTargetException {
    CMenuItem item = getMenuItem("St[a]rt", (Icon) null);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("St[ar]t", (Icon) null);
    assertThat(item.getText()).isEqualTo("St[ar]t");

    item = getMenuItem("Star[t]", (Icon) null);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(item.getText()).isEqualTo("Start");

    item = getMenuItem("[S]tart", (Icon) null);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(item.getText()).isEqualTo("Start");
  }

  @Test
  public void testSetTextString() throws InterruptedException, InvocationTargetException {
    final CMenuItem item = getMenuItem();
    setText(item, "St[a]rt");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(item.getText()).isEqualTo("Start");

    setText(item, "St[ar]t");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(item.getText()).isEqualTo("St[ar]t");

    setText(item, "Star[t]");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(item.getText()).isEqualTo("Start");

    setText(item, "[S]tart");
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(item.getText()).isEqualTo("Start");

    setText(item, null);
    assertThat(item.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(item.getText()).isNull();
  }

  public static CMenuItem getMenuItem(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name);
      }
    });
  }

  public static CMenuItem getMenuItem(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name, text);
      }
    });
  }

  public static CMenuItem getMenuItem(final String name, final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name, a);
      }
    });
  }

  public static CMenuItem getMenuItem(final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(a);
      }
    });
  }

  public static CMenuItem getMenuItem(final String name, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name, icon);
      }
    });
  }

  public static CMenuItem getMenuItem(final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(icon);
      }
    });
  }

  public static CMenuItem getMenuItem() {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem();
      }
    });
  }

  public static CMenuItem getMenuItem(final String name, final int mnemonic) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name, mnemonic);
      }
    });
  }

  public static CMenuItem getMenuItem(final String name, final String text, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CMenuItem>() {
      @Override
      protected CMenuItem executeInEDT() {
        return new CMenuItem(name, text, icon);
      }
    });
  }

  @Test
  public void testMenuItem_StringString() {

    CMenuItem item = getMenuItem("item", (String) null);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getText()).isEmpty();

    item = getMenuItem("", "txt");
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("txt");

    item = getMenuItem(null, "");
    assertThat(item.getName()).isNull();
    assertThat(item.getText()).isEmpty();

    item = getMenuItem(" .. ", "12 <-> 34");
    assertThat(item.getName()).isEqualTo(" .. ");
    assertThat(item.getText()).isEqualTo("12 <-> 34");
  }

  @Test
  public void testMenuItem_StringStringIcon() {

    Icon icon = new ImageIcon(new byte[214]);
    CMenuItem item = getMenuItem("item", (String) null, icon);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getText()).isEmpty();
    assertThat(item.getIcon()).isEqualTo(icon);

    item = getMenuItem("", "txt", icon);
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("txt");
    assertThat(item.getIcon()).isEqualTo(icon);

    icon = new ImageIcon(new byte[42]);
    item = getMenuItem(null, "", icon);
    assertThat(item.getName()).isNull();
    assertThat(item.getText()).isEmpty();
    assertThat(item.getIcon()).isEqualTo(icon);

    icon = new ImageIcon();
    item = getMenuItem(" .. ", "12 <-> 34", icon);
    assertThat(item.getName()).isEqualTo(" .. ");
    assertThat(item.getText()).isEqualTo("12 <-> 34");
    assertThat(item.getIcon()).isEqualTo(icon);
  }

  @Test
  public void testMenuItem_StringAction() {

    CMenuItem item = getMenuItem("item", (Action) null);
    assertThat(item.getName()).isEqualTo("item");
    assertThat(item.getAction()).isNull();

    item = getMenuItem("", new AbstractAction("ACTION") {
      /** ... */
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent e) {}
    });
    assertThat(item.getName()).isEmpty();
    assertThat(item.getText()).isEqualTo("ACTION");
    assertThat(item.getAction()).isInstanceOf(AbstractAction.class);
  }

  private void setText(final CMenuItem item, final String text) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        item.setText(text);
      }
    });
  }
}
