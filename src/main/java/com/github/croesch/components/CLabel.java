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

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.api.IInvertable;

/**
 * An extension of {@link JLabel} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Mar 3, 2012
 */
public class CLabel extends JLabel implements IInvertable {

  /** generated serial version UID */
  private static final long serialVersionUID = -3518553239851224436L;

  /**
   * Initially the background color for inverted labels, when inverted once this contains the background color,
   * currently not visible. For instance, if the label is currently inverted this contains the normal background color.
   */
  @NotNull
  private Color otherColor = UIManager.getColor("Label.background").darker();

  /**
   * Constructs a {@link CLabel} with the given name and the given text.
   * 
   * @since Date: Mar 3, 2012
   * @param name the name of this {@link CLabel}.
   * @param text the text of the label
   * @see #setName(String)
   */
  public CLabel(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Constructs a {@link CLabel} with the given name, the given image and the horizontal alignment as specified.
   * 
   * @since Date: Apr 12, 2012
   * @param name the name of this {@link CLabel}.
   * @param image the image to be displayed by the label
   * @param horizontalAlignment the horizontal alignment of this label, one of the following:
   *        {@link javax.swing.SwingConstants#LEFT}, {@link javax.swing.SwingConstants#CENTER},
   *        {@link javax.swing.SwingConstants#RIGHT}, {@link javax.swing.SwingConstants#LEADING} or
   *        {@link javax.swing.SwingConstants#TRAILING}
   */
  public CLabel(final String name, final Icon image, final int horizontalAlignment) {
    super(image, horizontalAlignment);
    setName(name);
  }

  /**
   * Constructs a {@link CLabel} with the given name and the given image.
   * 
   * @since Date: Apr 12, 2012
   * @param name the name of this {@link CLabel}.
   * @param image the image to be displayed by the label
   */
  public CLabel(final String name, final Icon image) {
    super(image);
    setName(name);
  }

  /**
   * Constructs a {@link CLabel} with the given name, the given text, the given image and the horizontal alignment as
   * specified.
   * 
   * @since Date: Apr 12, 2012
   * @param name the name of this {@link CLabel}.
   * @param text the text of the label
   * @param image the image to be displayed by the label
   * @param horizontalAlignment the horizontal alignment of this label, one of the following:
   *        {@link javax.swing.SwingConstants#LEFT}, {@link javax.swing.SwingConstants#CENTER},
   *        {@link javax.swing.SwingConstants#RIGHT}, {@link javax.swing.SwingConstants#LEADING} or
   *        {@link javax.swing.SwingConstants#TRAILING}
   */
  public CLabel(final String name, final String text, final Icon image, final int horizontalAlignment) {
    super(text, image, horizontalAlignment);
    setName(name);
  }

  /**
   * Constructs a {@link CLabel} with the given name, the given text and the horizontal alignment as specified.
   * 
   * @since Date: Apr 12, 2012
   * @param name the name of this {@link CLabel}.
   * @param text the text of the label
   * @param horizontalAlignment the horizontal alignment of this label, one of the following:
   *        {@link javax.swing.SwingConstants#LEFT}, {@link javax.swing.SwingConstants#CENTER},
   *        {@link javax.swing.SwingConstants#RIGHT}, {@link javax.swing.SwingConstants#LEADING} or
   *        {@link javax.swing.SwingConstants#TRAILING}
   */
  public CLabel(final String name, final String text, final int horizontalAlignment) {
    super(text, horizontalAlignment);
    setName(name);
  }

  /**
   * Constructs a {@link CLabel} with the given name.
   * 
   * @since Date: Apr 12, 2012
   * @param name the name of this {@link CLabel}.
   */
  public CLabel(final String name) {
    super();
    setName(name);
  }

  @Override
  public final void invert() {
    final Color newColor = this.otherColor;
    this.otherColor = getBackground();
    setBackground(newColor);
  }
}
