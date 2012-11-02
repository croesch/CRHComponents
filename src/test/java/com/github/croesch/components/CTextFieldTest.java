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
 * Provides test cases for {@link CTextField}.
 * 
 * @author croesch
 * @since Date: Mar 9, 2012
 */
public class CTextFieldTest extends DefaultTestCase {

  public static CTextField getTF(final String name, final String text) {
    return GuiActionRunner.execute(new GuiQuery<CTextField>() {
      @Override
      protected CTextField executeInEDT() {
        return new CTextField(name, text);
      }
    });
  }

  public static CTextField getTF(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CTextField>() {
      @Override
      protected CTextField executeInEDT() {
        return new CTextField(name);
      }
    });
  }

  @Test
  public void testTextField() {
    JTextComponentFixture tfFixture = new JTextComponentFixture(robot(), getTF("tf", "text"));
    tfFixture.requireText("text");
    assertThat(tfFixture.component().getName()).isEqualTo("tf");

    tfFixture = new JTextComponentFixture(robot(), getTF("", "12"));
    tfFixture.requireText("12");
    assertThat(tfFixture.component().getName()).isEmpty();

    tfFixture = new JTextComponentFixture(robot(), getTF(null, ""));
    tfFixture.requireText("");
    assertThat(tfFixture.component().getName()).isNull();

    tfFixture = new JTextComponentFixture(robot(), getTF("", null));
    tfFixture.requireText("");
    assertThat(tfFixture.component().getName()).isEmpty();

    tfFixture = new JTextComponentFixture(robot(), getTF(""));
    tfFixture.requireText("");
    assertThat(tfFixture.component().getName()).isEmpty();
  }
}
