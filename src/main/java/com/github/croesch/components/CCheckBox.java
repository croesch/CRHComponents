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

import java.awt.Color;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.api.IInvertable;

/**
 * An extension of {@link JCheckBox} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Apr 9, 2012
 */
public class CCheckBox extends JCheckBox implements IInvertable {

  /** generated serial version UID */
  private static final long serialVersionUID = -4966283337378730078L;

  /**
   * Initially the background color for inverted check boxes, when inverted once this contains the background color,
   * currently not visible. For instance, if the check box is currently inverted this contains the normal background
   * color.
   */
  @NotNull
  private Color otherColor = UIManager.getColor("CheckBox.background").darker();

  /**
   * Creates an unselected {@link CCheckBox} button with the given name, no text and no icon.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   */
  public CCheckBox(final String name) {
    super();
    setName(name);
  }

  /**
   * Creates an unselected {@link CCheckBox} button with the given name, the given icon but no text.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param icon the image to display
   */
  public CCheckBox(final String name, final Icon icon) {
    super(icon);
    setName(name);
  }

  /**
   * Creates an unselected {@link CCheckBox} button with the given name, the given text but no icon.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param text the text of this {@link CCheckBox}
   */
  public CCheckBox(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Creates an unselected {@link CCheckBox} button with the given name, and other properties are fetched from the
   * given {@link Action}.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param a the {@link Action} to fetch some properties from
   */
  public CCheckBox(final String name, final Action a) {
    super(a);
    setName(name);
  }

  /**
   * Creates a {@link CCheckBox} button with the given name, the given icon but no text.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param icon the image to display
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CCheckBox(final String name, final Icon icon, final boolean selected) {
    super(icon, selected);
    setName(name);
  }

  /**
   * Creates a {@link CCheckBox} button with the given name, the given text but no icon.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param text the text of this {@link CCheckBox}
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CCheckBox(final String name, final String text, final boolean selected) {
    super(text, selected);
    setName(name);
  }

  /**
   * Creates an unselected {@link CCheckBox} button with the given name, the given text and the given icon.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param text the text of this {@link CCheckBox}
   * @param icon the image to display
   */
  public CCheckBox(final String name, final String text, final Icon icon) {
    super(text, icon);
    setName(name);
  }

  /**
   * Creates a {@link CCheckBox} button with the given name, the given text and the given icon.
   * 
   * @since Date: Apr 9, 2012
   * @param name the name of this {@link CCheckBox}
   * @param text the text of this {@link CCheckBox}
   * @param icon the image to display
   * @param selected the initial selection state, <code>true</code> if selected,<br>
   *        or <code>false</code> otherwise
   */
  public CCheckBox(final String name, final String text, final Icon icon, final boolean selected) {
    super(text, icon, selected);
    setName(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void invert() {
    final Color newColor = this.otherColor;
    this.otherColor = getBackground();
    setBackground(newColor);
  }
}
