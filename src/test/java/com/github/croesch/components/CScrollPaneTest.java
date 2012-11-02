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

import java.awt.Component;

import javax.swing.JScrollPane;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.JScrollPaneFixture;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CScrollPane}.
 * 
 * @author croesch
 * @since Date: Apr 7, 2012
 */
public class CScrollPaneTest extends DefaultTestCase {

  public static CScrollPane getScrollPane(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CScrollPane>() {
      @Override
      protected CScrollPane executeInEDT() {
        return new CScrollPane(name);
      }
    });
  }

  public static CScrollPane getScrollPane(final String name, final Component view, final int vPolicy, final int hPolicy) {
    return GuiActionRunner.execute(new GuiQuery<CScrollPane>() {
      @Override
      protected CScrollPane executeInEDT() {
        return new CScrollPane(name, view, vPolicy, hPolicy);
      }
    });
  }

  public static CScrollPane getScrollPane(final String name, final int vPolicy, final int hPolicy) {
    return GuiActionRunner.execute(new GuiQuery<CScrollPane>() {
      @Override
      protected CScrollPane executeInEDT() {
        return new CScrollPane(name, vPolicy, hPolicy);
      }
    });
  }

  public static CScrollPane getScrollPane(final String name, final Component view) {
    return GuiActionRunner.execute(new GuiQuery<CScrollPane>() {
      @Override
      protected CScrollPane executeInEDT() {
        return new CScrollPane(name, view);
      }
    });
  }

  private void assertHorizontalScrollbarPolicy(final JScrollPaneFixture scrollPaneFixture, final int horizontalPolicy) {
    assertThat(scrollPaneFixture.component().getHorizontalScrollBarPolicy()).isEqualTo(horizontalPolicy);
  }

  private void assertVerticalScrollbarPolicy(final JScrollPaneFixture scrollPaneFixture, final int verticalPolicy) {
    assertThat(scrollPaneFixture.component().getVerticalScrollBarPolicy()).isEqualTo(verticalPolicy);
  }

  @Test
  public void testScrollPane_String() {

    JScrollPaneFixture scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane("pane"));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("pane");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isNull();
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane(""));
    assertThat(scrollPaneFixture.component().getName()).isEmpty();
    assertThat(scrollPaneFixture.component().getViewport().getView()).isNull();
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane(null));
    assertThat(scrollPaneFixture.component().getName()).isNull();
    assertThat(scrollPaneFixture.component().getViewport().getView()).isNull();
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }

  @Test
  public void testScrollPane_StringComponentIntInt() {

    Component view = getScrollPane("innerPane");
    JScrollPaneFixture scrollPaneFixture = new JScrollPaneFixture(robot(),
                                                                  getScrollPane("pane",
                                                                                view,
                                                                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("pane");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isEqualTo(view);
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    view = CLabelTest.getLabel("innerThing", "...");
    scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane("$the-pane$", view,
                                                                      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                                      JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("$the-pane$");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isEqualTo(view);
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  }

  @Test
  public void testScrollPane_StringIntInt() {

    JScrollPaneFixture scrollPaneFixture = new JScrollPaneFixture(robot(),
                                                                  getScrollPane("pane",
                                                                                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                                                                                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("pane");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isNull();
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane("$the-pane$",
                                                                      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                                      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("$the-pane$");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isNull();
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }

  @Test
  public void testScrollPane_StringComponent() {

    Component view = CTextAreaTest.getTA("%view%");
    JScrollPaneFixture scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane("%pane~", view));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("%pane~");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isEqualTo(view);
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    view = CButtonTest.getButton("THE BUTTON", "hello world");
    scrollPaneFixture = new JScrollPaneFixture(robot(), getScrollPane("_the-pane*", view));
    assertThat(scrollPaneFixture.component().getName()).isEqualTo("_the-pane*");
    assertThat(scrollPaneFixture.component().getViewport().getView()).isEqualTo(view);
    assertVerticalScrollbarPolicy(scrollPaneFixture, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    assertHorizontalScrollbarPolicy(scrollPaneFixture, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  }
}
