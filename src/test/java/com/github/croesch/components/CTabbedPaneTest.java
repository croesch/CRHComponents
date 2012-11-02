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

import javax.swing.JTabbedPane;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CTabbedPane}.
 * 
 * @author croesch
 * @since Date: May 10, 2012
 */
public class CTabbedPaneTest extends DefaultTestCase {

  public static CTabbedPane getTabbedPane(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CTabbedPane>() {
      @Override
      protected CTabbedPane executeInEDT() {
        return new CTabbedPane(name);
      }
    });
  }

  public static CTabbedPane getTabbedPane(final String name, final int tabPlacement) {
    return GuiActionRunner.execute(new GuiQuery<CTabbedPane>() {
      @Override
      protected CTabbedPane executeInEDT() {
        return new CTabbedPane(name, tabPlacement);
      }
    });
  }

  public static CTabbedPane getTabbedPane(final String name, final int tabPlacement, final int tabLayoutPolicy) {
    return GuiActionRunner.execute(new GuiQuery<CTabbedPane>() {
      @Override
      protected CTabbedPane executeInEDT() {
        return new CTabbedPane(name, tabPlacement, tabLayoutPolicy);
      }
    });
  }

  @Test
  public void testTabbedPane_String() {
    JTabbedPane pane = getTabbedPane("pane");
    assertThat(pane.getName()).isEqualTo("pane");

    pane = getTabbedPane("");
    assertThat(pane.getName()).isEmpty();

    pane = getTabbedPane(null);
    assertThat(pane.getName()).isNull();

    pane = getTabbedPane(".*+*.");
    assertThat(pane.getName()).isEqualTo(".*+*.");
  }

  @Test
  public void testTabbedPane_StringInt() {
    JTabbedPane pane = getTabbedPane("pane", JTabbedPane.TOP);
    assertThat(pane.getName()).isEqualTo("pane");
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.TOP);

    pane = getTabbedPane("", JTabbedPane.BOTTOM);
    assertThat(pane.getName()).isEmpty();
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.BOTTOM);

    pane = getTabbedPane(null, JTabbedPane.LEFT);
    assertThat(pane.getName()).isNull();
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.LEFT);

    pane = getTabbedPane(".*+*.", JTabbedPane.RIGHT);
    assertThat(pane.getName()).isEqualTo(".*+*.");
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.RIGHT);
  }

  @Test
  public void testTabbedPane_StringIntInt() {
    JTabbedPane pane = getTabbedPane("pane", JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
    assertThat(pane.getName()).isEqualTo("pane");
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.TOP);
    assertThat(pane.getTabLayoutPolicy()).isEqualTo(JTabbedPane.WRAP_TAB_LAYOUT);

    pane = getTabbedPane("", JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
    assertThat(pane.getName()).isEmpty();
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.BOTTOM);
    assertThat(pane.getTabLayoutPolicy()).isEqualTo(JTabbedPane.SCROLL_TAB_LAYOUT);

    pane = getTabbedPane(null, JTabbedPane.LEFT, JTabbedPane.WRAP_TAB_LAYOUT);
    assertThat(pane.getName()).isNull();
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.LEFT);
    assertThat(pane.getTabLayoutPolicy()).isEqualTo(JTabbedPane.WRAP_TAB_LAYOUT);

    pane = getTabbedPane(".*+*.", JTabbedPane.RIGHT, JTabbedPane.SCROLL_TAB_LAYOUT);
    assertThat(pane.getName()).isEqualTo(".*+*.");
    assertThat(pane.getTabPlacement()).isEqualTo(JTabbedPane.RIGHT);
    assertThat(pane.getTabLayoutPolicy()).isEqualTo(JTabbedPane.SCROLL_TAB_LAYOUT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTabPlacement_Cstr1() {
    getTabbedPane("name", -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTabPlacement_Cstr2() {
    getTabbedPane("name", -1, JTabbedPane.SCROLL_TAB_LAYOUT);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalTabLayoutPolicy() {
    getTabbedPane("name", JTabbedPane.TOP, -2);
  }
}
