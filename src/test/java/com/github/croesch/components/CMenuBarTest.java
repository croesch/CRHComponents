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
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CMenuBar}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CMenuBarTest extends DefaultTestCase {

  public static CMenuBar getMenuBar(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CMenuBar>() {
      @Override
      protected CMenuBar executeInEDT() {
        return new CMenuBar(name);
      }
    });
  }

  @Test
  public void testPanel_String() {
    CMenuBar menuBar = getMenuBar("bar");
    assertThat(menuBar.getName()).isEqualTo("bar");

    menuBar = getMenuBar(" .. menu bar .. ");
    assertThat(menuBar.getName()).isEqualTo(" .. menu bar .. ");

    menuBar = getMenuBar("");
    assertThat(menuBar.getName()).isEmpty();

    menuBar = getMenuBar(null);
    assertThat(menuBar.getName()).isNull();
  }
}
