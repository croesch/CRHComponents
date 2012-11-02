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

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * An extension of {@link JTable} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CTable extends JTable {

  /** generated serial version UID */
  private static final long serialVersionUID = 6103465327724249242L;

  /**
   * Constructs a new table with the given name.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   */
  public CTable(final String name) {
    setName(name);
  }

  /**
   * Constructs a new table with the given name and the given data model.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param dm the data model
   */
  public CTable(final String name, final TableModel dm) {
    super(dm);
    setName(name);
  }

  /**
   * Constructs a new table with the given name, the given data model and the given column model.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param dm the data model
   * @param cm the column model
   */
  public CTable(final String name, final TableModel dm, final TableColumnModel cm) {
    super(dm, cm);
    setName(name);
  }

  /**
   * Constructs a new table with the given name, the given number of rows and columns.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param numRows the number of rows for this table
   * @param numColumns the number of columns for this table
   */
  public CTable(final String name, final int numRows, final int numColumns) {
    super(numRows, numColumns);
    setName(name);
  }

  /**
   * Constructs a new table with the given name, the given rows and names of columns.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param rowData the data for the rows
   * @param columnNames the names of the table columns
   */
  public CTable(final String name, final Vector<?> rowData, final Vector<?> columnNames) {
    super(rowData, columnNames);
    setName(name);
  }

  /**
   * Constructs a new table with the given name, the given rows and names of columns.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param rowData the data for the rows
   * @param columnNames the names of the table columns
   */
  public CTable(final String name, final Object[][] rowData, final Object[] columnNames) {
    super(rowData, columnNames);
    setName(name);
  }

  /**
   * Constructs a new table with the given name, the given data model, the given column model and the given selection
   * model.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param dm the data model
   * @param cm the column model
   * @param sm the selection model
   */
  public CTable(final String name, final TableModel dm, final TableColumnModel cm, final ListSelectionModel sm) {
    super(dm, cm, sm);
    setName(name);
  }

}
