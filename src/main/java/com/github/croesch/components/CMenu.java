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

import javax.swing.Action;
import javax.swing.JMenu;

import com.github.croesch.util.MnemonicUtil;

/**
 * This menu is able to set its mnemonic key automatically.
 * 
 * @author croesch
 * @since Date: Dec 20, 2010
 */
public class CMenu extends JMenu {

  /** version UID */
  private static final long serialVersionUID = -8285015108385978148L;

  /**
   * Constructs a new menu.
   * 
   * @deprecated use a constructor with name attribute, such as {@link #CMenu(String, String)}.
   */
  @Deprecated
  public CMenu() {
    this(null, (String) null);
  }

  /**
   * Constructs a new menu with the given action.
   * 
   * @param action the {@link Action} that provides some properties for this menu.
   * @deprecated use {@link #CMenu(String, Action)} instead.
   */
  @Deprecated
  public CMenu(final Action action) {
    this(null, action);
  }

  /**
   * Constructs a new menu with the given text.
   * 
   * @param s the text for the menu label
   * @param b <code>true</code>, if the menu can be torn off
   * @deprecated use {@link #CMenu(String, String, boolean)} instead.
   */
  @Deprecated
  public CMenu(final String s, final boolean b) {
    this(null, s, b);
  }

  /**
   * Constructs a new menu with the given text.
   * 
   * @param s the text for the menu label
   * @deprecated use {@link #CMenu(String, String)} instead.
   */
  @Deprecated
  public CMenu(final String s) {
    this(null, s);
  }

  /**
   * Constructs a new {@link CMenu} with the given name and the given text.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link CMenu}.
   * @param text the text for the menu label
   */
  public CMenu(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Constructs a new {@link CMenu} with the given name and other properties fetched from the given {@link Action}.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link CMenu}.
   * @param a the {@link Action} that provides some properties for this menu.
   */
  public CMenu(final String name, final Action a) {
    super(a);
    setName(name);
  }

  /**
   * Constructs a new {@link CMenu} with the given name and the given text.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link CMenu}.
   * @param text the text for the menu label
   * @param b <code>true</code>, if the menu can be torn off
   */
  public CMenu(final String name, final String text, final boolean b) {
    super(text, b);
    setName(name);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
