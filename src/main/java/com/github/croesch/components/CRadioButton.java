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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

/**
 * An extension of {@link JRadioButton} that contains some default behavior that not each client should have to
 * implement.
 * 
 * @author croesch
 * @since Date: May 27, 2012
 */
public class CRadioButton extends JRadioButton {

  /** generated serial version UID */
  private static final long serialVersionUID = -6820607738475514802L;

  /**
   * Creates an unselected {@link CRadioButton} button with the given name, no text and no icon.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   */
  public CRadioButton(final String name) {
    super();
    setName(name);
  }

  /**
   * Creates an unselected {@link CRadioButton} button with the given name, the given icon but no text.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param icon the image to display
   */
  public CRadioButton(final String name, final Icon icon) {
    super(icon);
    setName(name);
  }

  /**
   * Creates an unselected {@link CRadioButton} button with the given name, the given text but no icon.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param text the text of this {@link CRadioButton}
   */
  public CRadioButton(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Creates an unselected {@link CRadioButton} button with the given name, and other properties are fetched from the
   * given {@link Action}.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param a the {@link Action} to fetch some properties from
   */
  public CRadioButton(final String name, final Action a) {
    super(a);
    setName(name);
  }

  /**
   * Creates a {@link CRadioButton} button with the given name, the given icon but no text.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param icon the image to display
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CRadioButton(final String name, final Icon icon, final boolean selected) {
    super(icon, selected);
    setName(name);
  }

  /**
   * Creates a {@link CRadioButton} button with the given name, the given text but no icon.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param text the text of this {@link CRadioButton}
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CRadioButton(final String name, final String text, final boolean selected) {
    super(text, selected);
    setName(name);
  }

  /**
   * Creates an unselected {@link CRadioButton} button with the given name, the given text and the given icon.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param text the text of this {@link CRadioButton}
   * @param icon the image to display
   */
  public CRadioButton(final String name, final String text, final Icon icon) {
    super(text, icon);
    setName(name);
  }

  /**
   * Creates a {@link CRadioButton} button with the given name, the given text and the given icon.
   * 
   * @since Date: May 27, 2012
   * @param name the name of this {@link CRadioButton}
   * @param text the text of this {@link CRadioButton}
   * @param icon the image to display
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CRadioButton(final String name, final String text, final Icon icon, final boolean selected) {
    super(text, icon, selected);
    setName(name);
  }
}
