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

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CTextArea}.
 * 
 * @author croesch
 * @since Date: Mar 14, 2012
 */
public class CTextAreaTest extends DefaultTestCase {

  public static CTextArea getTA(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CTextArea>() {
      @Override
      protected CTextArea executeInEDT() {
        return new CTextArea(name, text);
      }
    });
  }

  public static CTextArea getTA(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CTextArea>() {
      @Override
      protected CTextArea executeInEDT() {
        return new CTextArea(name);
      }
    });
  }

  @Test
  public void testTextArea() {
    JTextComponentFixture taFixture = new JTextComponentFixture(robot(), getTA("ta", "text"));
    taFixture.requireText("text");
    assertThat(taFixture.component().getName()).isEqualTo("ta");

    taFixture = new JTextComponentFixture(robot(), getTA("", "12"));
    taFixture.requireText("12");
    assertThat(taFixture.component().getName()).isEmpty();

    taFixture = new JTextComponentFixture(robot(), getTA(null, ""));
    taFixture.requireText("");
    assertThat(taFixture.component().getName()).isNull();

    taFixture = new JTextComponentFixture(robot(), getTA("", null));
    taFixture.requireText("");
    assertThat(taFixture.component().getName()).isEmpty();

    taFixture = new JTextComponentFixture(robot(), getTA(""));
    taFixture.requireText("");
    assertThat(taFixture.component().getName()).isEmpty();
  }
}
