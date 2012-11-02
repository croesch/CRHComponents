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

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * An extension of {@link JComboBox} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CComboBox extends JComboBox {

  /** generated serial version UID */
  private static final long serialVersionUID = 6060299403463833180L;

  /**
   * Constructs a new combobox with the given name.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the component
   */
  public CComboBox(final String name) {
    setName(name);
  }

  /**
   * Constructs a new combobox with the given name and the given model.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the component
   * @param aModel the model providing the list of items for the combobox
   */
  public CComboBox(final String name, final ComboBoxModel aModel) {
    super(aModel);
    setName(name);
  }

  /**
   * Constructs a new combobox with the given name and the given objects for the list.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the component
   * @param items the items for the combobox
   */
  public CComboBox(final String name, final Object[] items) {
    super(items);
    setName(name);
  }
}
