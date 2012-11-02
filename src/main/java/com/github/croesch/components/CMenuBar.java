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

import javax.swing.JMenuBar;

/**
 * An extension of {@link JMenuBar} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CMenuBar extends JMenuBar {

  /** generated serial version UID */
  private static final long serialVersionUID = 5083976735067236201L;

  /**
   * Constructs a new menu bar with the given name.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   */
  public CMenuBar(final String name) {
    setName(name);
  }
}
