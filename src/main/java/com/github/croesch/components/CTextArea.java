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

import javax.swing.JTextArea;

/**
 * An extension of {@link JTextArea} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Mar 14, 2012
 */
public class CTextArea extends JTextArea {

  /** generated serial version UID */
  private static final long serialVersionUID = 1088518150231835733L;

  /**
   * Constructs the text area with the given name and the given text.
   * 
   * @since Date: Mar 14, 2012
   * @param name the name of the component to set via {@link #setName(String)}
   * @param text the {@link Object} thats {@link String} representation will be set as the text of the text area
   * @see #setName(String)
   */
  public CTextArea(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Constructs the text area with the given name.
   * 
   * @since Date: Mar 14, 2012
   * @param name the name of the component to set via {@link #setName(String)}
   * @see #setName(String)
   */
  public CTextArea(final String name) {
    this(name, null);
  }
}
