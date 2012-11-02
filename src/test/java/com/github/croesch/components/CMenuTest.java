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

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
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

  @Test
  public void testMenu_String() throws InterruptedException, InvocationTargetException {
    CMenu menu = getMenu("St[a]rt");

    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(menu.getText()).isEqualTo("Start");

    menu = getMenu("St[ar]t");
    assertThat(menu.getText()).isEqualTo("St[ar]t");

    menu = getMenu("Star[t]");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(menu.getText()).isEqualTo("Start");

    menu = getMenu("[S]tart");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(menu.getText()).isEqualTo("Start");
  }

  @Test
  public void testMenu_StringBoolean() throws InterruptedException, InvocationTargetException {
    CMenu menu = getMenu("St[a]rt", false);
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(menu.getText()).isEqualTo("Start");

    menu = getMenu("St[ar]t", false);
    assertThat(menu.getText()).isEqualTo("St[ar]t");

    menu = getMenu("Star[t]", false);
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(menu.getText()).isEqualTo("Start");

    menu = getMenu("[S]tart", false);
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(menu.getText()).isEqualTo("Start");
  }

  @Test
  public void testMenu_Action() throws InterruptedException, InvocationTargetException {
    final Action act = new AbstractAction() {
      /** serial version UID */
      private static final long serialVersionUID = -2312442386490910405L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        // do nothing, stupid stub
      }
    };
    CMenu menu = getMenu(act);
    assertThat(menu.getAction()).isSameAs(act);

    menu = getMenu((AbstractAction) null);
    assertThat(menu.getAction()).isNull();
  }

  @Test
  public void testSetText() throws InterruptedException, InvocationTargetException {
    final CMenu menu = getMenu();
    setText(menu, "St[a]rt");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(menu.getText()).isEqualTo("Start");

    setText(menu, "St[ar]t");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_A);
    assertThat(menu.getText()).isEqualTo("St[ar]t");

    setText(menu, "Star[t]");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_T);
    assertThat(menu.getText()).isEqualTo("Start");

    setText(menu, "[S]tart");
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(menu.getText()).isEqualTo("Start");

    setText(menu, null);
    assertThat(menu.getMnemonic()).isEqualTo(KeyEvent.VK_S);
    assertThat(menu.getText()).isNull();
  }

  public static CMenu getMenu() {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu();
      }
    });
  }

  public static CMenu getMenu(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(name);
      }
    });
  }

  public static CMenu getMenu(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(name, text);
      }
    });
  }

  public static CMenu getMenu(final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(a);
      }
    });
  }

  public static CMenu getMenu(final String name, final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(name, a);
      }
    });
  }

  public static CMenu getMenu(final String name, final String text, final boolean tornOff) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(name, text, tornOff);
      }
    });
  }

  public static CMenu getMenu(final String text, final boolean tornOff) {
    return GuiActionRunner.execute(new GuiQuery<CMenu>() {
      @Override
      protected CMenu executeInEDT() {
        return new CMenu(text, tornOff);
      }
    });
  }

  @Test
  public void testMenu_StringString() {

    CMenu menu = getMenu("menu", (String) null);
    assertThat(menu.getName()).isEqualTo("menu");
    assertThat(menu.getText()).isEmpty();

    menu = getMenu("", "txt");
    assertThat(menu.getName()).isEmpty();
    assertThat(menu.getText()).isEqualTo("txt");

    menu = getMenu(null, "");
    assertThat(menu.getName()).isNull();
    assertThat(menu.getText()).isEmpty();

    menu = getMenu(" .. ", "12 <-> 34");
    assertThat(menu.getName()).isEqualTo(" .. ");
    assertThat(menu.getText()).isEqualTo("12 <-> 34");
  }

  @Test
  public void testMenu_StringStringBoolean() {
    // TODO uncomment if 'tear off' is implemented

    CMenu menu = getMenu("menu", (String) null, true);
    assertThat(menu.getName()).isEqualTo("menu");
    assertThat(menu.getText()).isEmpty();
    //    assertThat(menu.isTearOff()).isTrue();

    menu = getMenu("", "txt", false);
    assertThat(menu.getName()).isEmpty();
    assertThat(menu.getText()).isEqualTo("txt");
    //    assertThat(menu.isTearOff()).isFalse();

    menu = getMenu(null, "", true);
    assertThat(menu.getName()).isNull();
    assertThat(menu.getText()).isEmpty();
    //    assertThat(menu.isTearOff()).isTrue();

    menu = getMenu(" .. ", "12 <-> 34", false);
    assertThat(menu.getName()).isEqualTo(" .. ");
    assertThat(menu.getText()).isEqualTo("12 <-> 34");
    //    assertThat(menu.isTearOff()).isFalse();
  }

  @Test
  public void testMenu_StringAction() {

    CMenu menu = getMenu("menu", (Action) null);
    assertThat(menu.getName()).isEqualTo("menu");
    assertThat(menu.getAction()).isNull();

    menu = getMenu("", new AbstractAction("ACTION") {
      /** ... */
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent e) {}
    });
    assertThat(menu.getName()).isEmpty();
    assertThat(menu.getText()).isEqualTo("ACTION");
    assertThat(menu.getAction()).isInstanceOf(AbstractAction.class);
  }

  private void setText(final CMenu button, final String text) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        button.setText(text);
      }
    });
  }
}
