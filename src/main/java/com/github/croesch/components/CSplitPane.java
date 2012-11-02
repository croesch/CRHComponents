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

import java.awt.Component;

import javax.swing.JSplitPane;

/**
 * An extension of {@link JSplitPane} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Apr 8, 2012
 */
public class CSplitPane extends JSplitPane {

  /** generated serial version UID */
  private static final long serialVersionUID = -3599522291129844359L;

  /**
   * Creates a new {@link CSplitPane} with the given name. It will be horizontally splitted and contain two buttons.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CSplitPane}
   */
  public CSplitPane(final String name) {
    super();
    setName(name);
  }

  /**
   * Creates a new {@link CSplitPane} with the given name, the given orientation and the given components.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CSplitPane}
   * @param newOrientation if horizontally or vertically orientated
   * @param newContinuousLayout <code>true</code> if the components should continuously be drawn when divider position
   *        changes,<br>
   *        or <code>false</code> to redraw only when devider position has stopped changing.
   * @param newLeftComponent the component for the left/top side
   * @param newRightComponent the component for the right/bottom side
   */
  public CSplitPane(final String name,
                    final int newOrientation,
                    final boolean newContinuousLayout,
                    final Component newLeftComponent,
                    final Component newRightComponent) {
    super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
    setName(name);
  }

  /**
   * Creates a new {@link CSplitPane} with the given name and the given orientation. Without components.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CSplitPane}
   * @param newOrientation if horizontally or vertically orientated
   * @param newContinuousLayout <code>true</code> if the components should continuously be drawn when divider position
   *        changes,<br>
   *        or <code>false</code> to redraw only when devider position has stopped changing.
   */
  public CSplitPane(final String name, final int newOrientation, final boolean newContinuousLayout) {
    super(newOrientation, newContinuousLayout);
    setName(name);
  }

  /**
   * Creates a new {@link CSplitPane} with the given name, the given orientation and the given components.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CSplitPane}
   * @param newOrientation if horizontally or vertically orientated
   * @param newLeftComponent the component for the left/top side
   * @param newRightComponent the component for the right/bottom side
   */
  public CSplitPane(final String name,
                    final int newOrientation,
                    final Component newLeftComponent,
                    final Component newRightComponent) {
    super(newOrientation, newLeftComponent, newRightComponent);
    setName(name);
  }

  /**
   * Creates a new {@link CSplitPane} with the given name, the given orientation. Without components.
   * 
   * @since Date: Apr 8, 2012
   * @param name the name of this {@link CSplitPane}
   * @param newOrientation if horizontally or vertically orientated
   */
  public CSplitPane(final String name, final int newOrientation) {
    super(newOrientation);
    setName(name);
  }
}
