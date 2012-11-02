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

import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CTable}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CTableTest extends DefaultTestCase {

  public static CTable getTable(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name);
      }
    });
  }

  public static CTable getTable(final String name, final TableModel model) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, model);
      }
    });
  }

  public static CTable getTable(final String name, final TableModel model, final TableColumnModel cModel) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, model, cModel);
      }
    });
  }

  public static CTable getTable(final String name,
                                final TableModel model,
                                final TableColumnModel cModel,
                                final ListSelectionModel sModel) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, model, cModel, sModel);
      }
    });
  }

  public static CTable getTable(final String name, final int rows, final int columns) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, rows, columns);
      }
    });
  }

  public static CTable getTable(final String name, final Vector<?> rows, final Vector<?> columns) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, rows, columns);
      }
    });
  }

  public static CTable getTable(final String name, final Object[][] rows, final Object[] columns) {
    return GuiActionRunner.execute(new GuiQuery<CTable>() {
      @Override
      protected CTable executeInEDT() {
        return new CTable(name, rows, columns);
      }
    });
  }

  @Test
  public void testTable_String() {
    CTable table = getTable("hugo");
    assertThat(table.getName()).isEqualTo("hugo");

    table = getTable("tabelle");
    assertThat(table.getName()).isEqualTo("tabelle");

    table = getTable(null);
    assertThat(table.getName()).isNull();
  }

  @Test
  public void testTable_StringTableModel() {
    CTable table = getTable("hugo", null);
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getModel()).isNotNull();

    final DefaultTableModel model = new DefaultTableModel();

    table = getTable("tabelle", model);
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getModel()).isEqualTo(model);

    table = getTable(null, model);
    setRowCount(model, 12);
    assertThat(table.getName()).isNull();
    assertThat(table.getModel()).isEqualTo(model);
  }

  @Test
  public void testTable_StringTableModelColumnModel() {
    final DefaultTableColumnModel cModel = new DefaultTableColumnModel();
    CTable table = getTable("hugo", null, cModel);
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getModel()).isNotNull();
    assertThat(table.getColumnModel()).isEqualTo(cModel);

    final DefaultTableModel model = new DefaultTableModel();
    table = getTable("tabelle", model, null);
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getModel()).isEqualTo(model);
    assertThat(table.getColumnModel()).isNotNull();

    table = getTable(null, model, cModel);
    setRowCount(model, 12);
    cModel.setColumnSelectionAllowed(true);
    assertThat(table.getName()).isNull();
    assertThat(table.getModel()).isEqualTo(model);
    assertThat(table.getColumnModel()).isEqualTo(cModel);
  }

  @Test
  public void testTable_StringTableModelColumnModelSelectionModel() {
    final DefaultTableColumnModel cModel = new DefaultTableColumnModel();
    CTable table = getTable("hugo", null, cModel, null);
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getModel()).isNotNull();
    assertThat(table.getColumnModel()).isEqualTo(cModel);
    assertThat(table.getSelectionModel()).isNotNull();

    final DefaultTableModel model = new DefaultTableModel();
    final ListSelectionModel sModel = new DefaultListSelectionModel();
    table = getTable("tabelle", model, null, sModel);
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getModel()).isEqualTo(model);
    assertThat(table.getColumnModel()).isNotNull();
    assertThat(table.getSelectionModel()).isEqualTo(sModel);

    table = getTable(null, model, cModel, sModel);
    setRowCount(model, 12);
    cModel.setColumnSelectionAllowed(true);
    sModel.setValueIsAdjusting(false);
    assertThat(table.getName()).isNull();
    assertThat(table.getModel()).isEqualTo(model);
    assertThat(table.getColumnModel()).isEqualTo(cModel);
    assertThat(table.getSelectionModel()).isEqualTo(sModel);
  }

  @Test
  public void testTable_StringIntInt() {
    CTable table = getTable("hugo", 12, 42);
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getRowCount()).isEqualTo(12);
    assertThat(table.getColumnCount()).isEqualTo(42);

    table = getTable("tabelle", 0, 7);
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getRowCount()).isEqualTo(0);
    assertThat(table.getColumnCount()).isEqualTo(7);

    table = getTable(null, 13, 0);
    assertThat(table.getName()).isNull();
    assertThat(table.getRowCount()).isEqualTo(13);
    assertThat(table.getColumnCount()).isEqualTo(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTable_LessThanZeroRowsNotAllowed() {
    getTable("table", -1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTable_LessThanZeroColumnsNotAllowed() {
    getTable("table", 7, -1);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testTable_StringVectorVector() {
    CTable table = getTable("hugo", null, new Vector<Object>());
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getRowCount()).isEqualTo(0);
    assertThat(table.getColumnCount()).isEqualTo(0);

    table = getTable("tabelle", new Vector<Object>(), null);
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getRowCount()).isEqualTo(0);
    assertThat(table.getColumnCount()).isEqualTo(0);

    table = getTable(null,
                     new Vector<Vector<String>>(Arrays.asList(new Vector<String>(Arrays.asList("tach")),
                                                              new Vector<String>(Arrays.asList("hi")))),
                     new Vector<Object>(Arrays.asList("meldung")));
    assertThat(table.getName()).isNull();
    assertThat(table.getRowCount()).isEqualTo(2);
    assertThat(table.getColumnCount()).isEqualTo(1);
  }

  @Test
  public void testTable_StringObjectObject() {
    CTable table = getTable("hugo", null, new Object[0]);
    assertThat(table.getName()).isEqualTo("hugo");
    assertThat(table.getColumnCount()).isEqualTo(0);

    table = getTable("tabelle", new Object[12][42], new Object[] { "Spalte" });
    assertThat(table.getName()).isEqualTo("tabelle");
    assertThat(table.getRowCount()).isEqualTo(12);
    assertThat(table.getColumnCount()).isEqualTo(1);
    assertThat(table.getColumnName(0)).isEqualTo("Spalte");

    table = getTable(null, new Object[][] { new Object[] { "tach" }, new Object[] { "hi" } },
                     new Object[] { "meldung" });
    assertThat(table.getName()).isNull();
    assertThat(table.getRowCount()).isEqualTo(2);
    assertThat(table.getColumnCount()).isEqualTo(1);
  }

  @Test(expected = NullPointerException.class)
  public void testTable_ColumnsWithoutNamesNotAllowed() {
    getTable("table", new Object[42][12], new Object[12]);
  }

  @Test(expected = NullPointerException.class)
  public void testTable_NullRowsNotAllowedWhenAskingRowCount() {
    final CTable table;
    try {
      table = getTable("table", null, new Object[0]);
    } catch (final NullPointerException e) {
      // make sure this statement doesn't throw exception!
      throw new AssertionError("Exception occured, but wasn't expected");
    }
    table.getRowCount();
  }

  @Test(expected = NullPointerException.class)
  public void testTable_NullColumnsNotAllowed() {
    getTable("table", new Object[42][12], null);
  }

  private void setRowCount(final DefaultTableModel model, final int rowCount) {
    GuiActionRunner.execute(new GuiTask() {
      @Override
      protected void executeInEDT() throws Throwable {
        model.setNumRows(rowCount);
      }
    });
  }
}
