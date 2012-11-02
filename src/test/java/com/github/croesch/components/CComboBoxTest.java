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

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.JComboBoxFixture;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CComboBox}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CComboBoxTest extends DefaultTestCase {

  public static CComboBox getComboBox(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CComboBox>() {
      @Override
      protected CComboBox executeInEDT() throws Throwable {
        return new CComboBox(name);
      }
    });
  }

  public static CComboBox getComboBox(final String name, final ComboBoxModel model) {
    return GuiActionRunner.execute(new GuiQuery<CComboBox>() {
      @Override
      protected CComboBox executeInEDT() throws Throwable {
        return new CComboBox(name, model);
      }
    });
  }

  public static CComboBox getComboBox(final String name, final Object[] items) {
    return GuiActionRunner.execute(new GuiQuery<CComboBox>() {
      @Override
      protected CComboBox executeInEDT() throws Throwable {
        return new CComboBox(name, items);
      }
    });
  }

  @Test
  public void testCstr_String() {
    JComboBoxFixture cb = new JComboBoxFixture(robot(), getComboBox("cb"));
    assertThat(cb.component().getName()).isEqualTo("cb");

    cb = new JComboBoxFixture(robot(), getComboBox(""));
    assertThat(cb.component().getName()).isEqualTo("");

    cb = new JComboBoxFixture(robot(), getComboBox(null));
    assertThat(cb.component().getName()).isNull();
  }

  @Test(expected = NullPointerException.class)
  public void testCstr_NPXNullModel() {
    getComboBox("cb", (ComboBoxModel) null);
  }

  @Test(expected = NullPointerException.class)
  public void testCstr_NPXNullObjectArray() {
    getComboBox("cb", (Object[]) null);
  }

  @Test
  public void testCstr_StringComboBoxModel() {
    JComboBoxFixture cb = new JComboBoxFixture(robot(), getComboBox("cb", new DefaultComboBoxModel()));
    assertThat(cb.component().getName()).isEqualTo("cb");
    cb.requireItemCount(0);

    cb = new JComboBoxFixture(robot(), getComboBox("", new DefaultComboBoxModel(new Object[12])));
    assertThat(cb.component().getName()).isEqualTo("");
    cb.requireItemCount(12);
    for (int i = 0; i < cb.component().getModel().getSize(); ++i) {
      assertThat(cb.valueAt(i)).isEqualTo("");
    }

    cb = new JComboBoxFixture(robot(),
                              getComboBox(null, new DefaultComboBoxModel(new Object[] { "Peter", 15, "Hans" })));
    assertThat(cb.component().getName()).isNull();
    cb.requireItemCount(3);
    assertThat(cb.valueAt(0)).isEqualTo("Peter");
    assertThat(cb.valueAt(1)).isEqualTo("15");
    assertThat(cb.valueAt(2)).isEqualTo("Hans");
  }

  @Test
  public void testCstr_StringObject() {
    JComboBoxFixture cb = new JComboBoxFixture(robot(), getComboBox("cb", new Object[0]));
    assertThat(cb.component().getName()).isEqualTo("cb");
    cb.requireItemCount(0);

    cb = new JComboBoxFixture(robot(), getComboBox("", new Object[12]));
    assertThat(cb.component().getName()).isEqualTo("");
    cb.requireItemCount(12);
    for (int i = 0; i < cb.component().getModel().getSize(); ++i) {
      assertThat(cb.valueAt(i)).isEqualTo("");
    }

    cb = new JComboBoxFixture(robot(), getComboBox(null, new Object[] { "Peter", 15, "Hans" }));
    assertThat(cb.component().getName()).isNull();
    cb.requireItemCount(3);
    assertThat(cb.valueAt(0)).isEqualTo("Peter");
    assertThat(cb.valueAt(1)).isEqualTo("15");
    assertThat(cb.valueAt(2)).isEqualTo("Hans");
  }
}
