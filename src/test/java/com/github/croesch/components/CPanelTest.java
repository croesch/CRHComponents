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

import java.awt.LayoutManager;

import javax.swing.JPanel;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CPanel}.
 * 
 * @author croesch
 * @since Date: Apr 8, 2012
 */
public class CPanelTest extends DefaultTestCase {

  public static CPanel getPanel(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CPanel>() {
      @Override
      protected CPanel executeInEDT() {
        return new CPanel(name);
      }
    });
  }

  public static CPanel getPanel(final String name, final boolean doubleBuffered) {
    return GuiActionRunner.execute(new GuiQuery<CPanel>() {
      @Override
      protected CPanel executeInEDT() {
        return new CPanel(name, doubleBuffered);
      }
    });
  }

  public static CPanel getPanel(final String name, final LayoutManager manager) {
    return GuiActionRunner.execute(new GuiQuery<CPanel>() {
      @Override
      protected CPanel executeInEDT() {
        return new CPanel(name, manager);
      }
    });
  }

  public static CPanel getPanel(final String name, final LayoutManager manager, final boolean doubleBuffered) {
    return GuiActionRunner.execute(new GuiQuery<CPanel>() {
      @Override
      protected CPanel executeInEDT() {
        return new CPanel(name, manager, doubleBuffered);
      }
    });
  }

  @Test
  public void testPanel_String() {
    JPanel panel = getPanel("panel");
    assertThat(panel.getName()).isEqualTo("panel");

    panel = getPanel(" .. .. ");
    assertThat(panel.getName()).isEqualTo(" .. .. ");

    panel = getPanel("");
    assertThat(panel.getName()).isEmpty();

    panel = getPanel(null);
    assertThat(panel.getName()).isNull();
  }

  @Test
  public void testPanel_StringBoolean() {
    JPanel panel = getPanel("$panel$", true);
    assertThat(panel.getName()).isEqualTo("$panel$");
    assertThat(panel.isDoubleBuffered()).isTrue();

    panel = getPanel(" .... ", false);
    assertThat(panel.getName()).isEqualTo(" .... ");
    assertThat(panel.isDoubleBuffered()).isFalse();
  }

  @Test
  public void testPanel_StringLayoutManager() {
    MigLayout manager = new MigLayout();
    JPanel panel = getPanel("$panel$", manager);
    assertThat(panel.getName()).isEqualTo("$panel$");
    assertThat(panel.getLayout()).isEqualTo(manager);

    manager = new MigLayout("fill", "[]0p[][]", "[]");
    panel = getPanel("-> the panel <-", manager);
    assertThat(panel.getName()).isEqualTo("-> the panel <-");
    assertThat(panel.getLayout()).isEqualTo(manager);
  }

  @Test
  public void testPanel_StringLayoutManagerBoolean() {
    MigLayout manager = new MigLayout(new LC());
    JPanel panel = getPanel("PANEL", manager, true);
    assertThat(panel.getName()).isEqualTo("PANEL");
    assertThat(panel.getLayout()).isEqualTo(manager);
    assertThat(panel.isDoubleBuffered()).isTrue();

    manager = new MigLayout(new LC(), new AC().fill());
    panel = getPanel("ppPanel", manager, false);
    assertThat(panel.getName()).isEqualTo("ppPanel");
    assertThat(panel.getLayout()).isEqualTo(manager);
    assertThat(panel.isDoubleBuffered()).isFalse();
  }
}
