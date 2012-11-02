/*
 * Copyright (C) 2011-2012  Christian Roesch
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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.JRadioButtonFixture;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CRadioButton}.
 * 
 * @author croesch
 * @since Date: May 27, 2012
 */
public class CRadioButtonTest extends DefaultTestCase {

  public static CRadioButton getRadioButton(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final Action a) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, a);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, icon);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, text);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final Icon icon, final boolean selected) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, icon, selected);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final String text, final boolean selected) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, text, selected);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name, final String text, final Icon icon) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, text, icon);
      }
    });
  }

  public static CRadioButton getRadioButton(final String name,
                                            final String text,
                                            final Icon icon,
                                            final boolean selected) {
    return GuiActionRunner.execute(new GuiQuery<CRadioButton>() {
      @Override
      protected CRadioButton executeInEDT() throws Throwable {
        return new CRadioButton(name, text, icon, selected);
      }
    });
  }

  @Test
  public void testCstr_String() {

    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb"));
    assertThat(rb.component().getName()).isEqualTo("rb");
    rb.requireNotSelected();

    rb = new JRadioButtonFixture(robot(), getRadioButton(""));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("");

    rb = new JRadioButtonFixture(robot(), getRadioButton(null));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isNull();
  }

  @Test
  public void testCstr_StringAction() {

    final Action a = new AbstractAction() {
      /** default */
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent e) {}
    };
    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", a));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("rb");
    assertThat(rb.component().getAction()).isEqualTo(a);

    rb = new JRadioButtonFixture(robot(), getRadioButton("", a));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("");
    assertThat(rb.component().getAction()).isEqualTo(a);

    rb = new JRadioButtonFixture(robot(), getRadioButton(null, a));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isNull();
    assertThat(rb.component().getAction()).isEqualTo(a);
  }

  @Test
  public void testCstr_StringIcon() {

    Icon i = new ImageIcon();
    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", i));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("rb");
    assertThat(rb.component().getIcon()).isEqualTo(i);

    rb = new JRadioButtonFixture(robot(), getRadioButton("", (Icon) null));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("");
    assertThat(rb.component().getIcon()).isNull();

    i = new ImageIcon(new byte[27]);
    rb = new JRadioButtonFixture(robot(), getRadioButton(null, i));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isNull();
    assertThat(rb.component().getIcon()).isEqualTo(i);
  }

  @Test
  public void testCstr_StringIconBoolean() {

    Icon i = new ImageIcon();
    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", i, false));
    rb.requireNotSelected();
    assertThat(rb.component().getName()).isEqualTo("rb");
    assertThat(rb.component().getIcon()).isEqualTo(i);

    rb = new JRadioButtonFixture(robot(), getRadioButton("", (Icon) null, true));
    rb.requireSelected();
    assertThat(rb.component().getName()).isEqualTo("");
    assertThat(rb.component().getIcon()).isNull();

    i = new ImageIcon(new byte[27]);
    rb = new JRadioButtonFixture(robot(), getRadioButton(null, i, true));
    rb.requireSelected();
    assertThat(rb.component().getName()).isNull();
    assertThat(rb.component().getIcon()).isEqualTo(i);
  }

  @Test
  public void testCstr_StringString() {

    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", ""));
    assertThat(rb.component().getName()).isEqualTo("rb");
    rb.requireNotSelected();
    rb.requireText("");

    rb = new JRadioButtonFixture(robot(), getRadioButton("", (String) null));
    rb.requireNotSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isEqualTo("");

    rb = new JRadioButtonFixture(robot(), getRadioButton(null, "txt"));
    rb.requireNotSelected();
    rb.requireText("txt");
    assertThat(rb.component().getName()).isNull();
  }

  @Test
  public void testCstr_StringStringBoolean() {

    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", "", true));
    assertThat(rb.component().getName()).isEqualTo("rb");
    rb.requireSelected();
    rb.requireText("");

    rb = new JRadioButtonFixture(robot(), getRadioButton("", (String) null, true));
    rb.requireSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isEqualTo("");

    rb = new JRadioButtonFixture(robot(), getRadioButton(null, "txt", false));
    rb.requireNotSelected();
    rb.requireText("txt");
    assertThat(rb.component().getName()).isNull();
  }

  @Test
  public void testCstr_StringStringIcon() {

    Icon i = new ImageIcon();
    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", null, i));
    rb.requireNotSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isEqualTo("rb");
    assertThat(rb.component().getIcon()).isEqualTo(i);

    rb = new JRadioButtonFixture(robot(), getRadioButton("", "txt", null));
    rb.requireNotSelected();
    rb.requireText("txt");
    assertThat(rb.component().getName()).isEqualTo("");
    assertThat(rb.component().getIcon()).isNull();

    i = new ImageIcon(new byte[27]);
    rb = new JRadioButtonFixture(robot(), getRadioButton(null, "", i));
    rb.requireNotSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isNull();
    assertThat(rb.component().getIcon()).isEqualTo(i);
  }

  @Test
  public void testCstr_StringStringIconBoolean() {

    Icon i = new ImageIcon();
    JRadioButtonFixture rb = new JRadioButtonFixture(robot(), getRadioButton("rb", null, i, true));
    rb.requireSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isEqualTo("rb");
    assertThat(rb.component().getIcon()).isEqualTo(i);

    rb = new JRadioButtonFixture(robot(), getRadioButton("", "txt", null, false));
    rb.requireNotSelected();
    rb.requireText("txt");
    assertThat(rb.component().getName()).isEqualTo("");
    assertThat(rb.component().getIcon()).isNull();

    i = new ImageIcon(new byte[27]);
    rb = new JRadioButtonFixture(robot(), getRadioButton(null, "", i, true));
    rb.requireSelected();
    rb.requireText("");
    assertThat(rb.component().getName()).isNull();
    assertThat(rb.component().getIcon()).isEqualTo(i);
  }
}
