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

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * An extension of {@link JPanel} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Apr 8, 2012
 */
public class CPanel extends JPanel {

  /** generated serial version UID */
  private static final long serialVersionUID = 4720214109287024970L;

  /**
   * Constructs an empty {@link CPanel} with the given name.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CPanel}
   */
  public CPanel(final String name) {
    super();
    setName(name);
  }

  /**
   * Constructs an empty {@link CPanel} with the given name.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CPanel}
   * @param isDoubleBuffered <code>true</code> for using double-buffering,<br>
   *        or <code>false</code> otherwise
   */
  public CPanel(final String name, final boolean isDoubleBuffered) {
    super(isDoubleBuffered);
    setName(name);
  }

  /**
   * Constructs an empty {@link CPanel} with the given name and the given layout manager.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CPanel}
   * @param layout the {@link LayoutManager} for the panel
   * @param isDoubleBuffered <code>true</code> for using double-buffering,<br>
   *        or <code>false</code> otherwise
   */
  public CPanel(final String name, final LayoutManager layout, final boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
    setName(name);
  }

  /**
   * Constructs an empty {@link CPanel} with the given name and the given layout manager.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CPanel}
   * @param layout the {@link LayoutManager} for the panel
   */
  public CPanel(final String name, final LayoutManager layout) {
    super(layout);
    setName(name);
  }
}
