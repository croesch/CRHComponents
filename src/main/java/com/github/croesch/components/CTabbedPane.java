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

import javax.swing.JTabbedPane;

/**
 * An extension of {@link JTabbedPane} that contains some default behavior that not each client should have to
 * implement.
 * 
 * @author croesch
 * @since Date: May 10, 2012
 */
public class CTabbedPane extends JTabbedPane {

  /** generated serial version UID */
  private static final long serialVersionUID = -4308900315432849168L;

  /**
   * Creates an empty <code>CTabbedPane</code> with tab placement <code>JTabbedPane.TOP</code>.
   * 
   * @since Date: May 10, 2012
   * @param name the name of this {@link CTabbedPane}
   */
  public CTabbedPane(final String name) {
    super();
    setName(name);
  }

  /**
   * Creates an empty <code>TabbedPane</code> with the specified tab placement.
   * 
   * @since Date: May 10, 2012
   * @param name the name of this {@link CTabbedPane}
   * @param tabPlacement the placement for the tabs relative to the content
   */
  public CTabbedPane(final String name, final int tabPlacement) {
    super(tabPlacement);
    setName(name);
  }

  /**
   * Creates an empty <code>TabbedPane</code> with the specified tab placement and tab layout policy.
   * 
   * @since Date: May 10, 2012
   * @param name the name of this {@link CTabbedPane}
   * @param tabPlacement the placement for the tabs relative to the content
   * @param tabLayoutPolicy the policy for laying out tabs when all tabs will not fit on one run
   */
  public CTabbedPane(final String name, final int tabPlacement, final int tabLayoutPolicy) {
    super(tabPlacement, tabLayoutPolicy);
    setName(name);
  }
}
