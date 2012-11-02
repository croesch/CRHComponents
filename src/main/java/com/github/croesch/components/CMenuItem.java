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
import javax.swing.Icon;
import javax.swing.JMenuItem;

import com.github.croesch.util.MnemonicUtil;

/**
 * This menu item is able to set its mnemonic key automatically.
 * 
 * @author croesch
 * @since Date: Dec 20, 2010
 */
public class CMenuItem extends JMenuItem {

  /** serial version UID */
  private static final long serialVersionUID = 3614702957338710920L;

  /**
   * Constructs a new menu item.
   * 
   * @deprecated use another constructor instead - in a future release this should be replaced by
   *             {@link #CMenuItem(String)}, to set the name of the menu item. But currently this constructor is used to
   *             set the text of the menu item. So choose a good alternative to this constructor.
   */
  @Deprecated
  public CMenuItem() {
    this(null, (String) null);
  }

  /**
   * Constructs a new menu item with the given icon.
   * 
   * @param icon the icon for this menu item
   * @deprecated use another constructor instead - in a future release this should be replaced by
   *             {@link #CMenuItem(String,Icon)}, to set the name of the menu item. But currently this constructor is
   *             used to set the text of the menu item. So choose a good alternative to this constructor.
   */
  @Deprecated
  public CMenuItem(final Icon icon) {
    this(null, icon);
  }

  /**
   * Constructs a new menu item with the given text.
   * 
   * @param text the text of the menu item
   * @deprecated <b>CAUTION!</b> will change behavior!<br>
   *             Use {@link #CMenuItem(String, String)} instead!
   */
  @Deprecated
  public CMenuItem(final String text) {
    this(null, text);
  }

  /**
   * Constructs a new menu item with the given action.
   * 
   * @param a the {@link Action} to fetch properties from
   * @deprecated use {@link #CMenuItem(String, Action)} instead
   */
  @Deprecated
  public CMenuItem(final Action a) {
    this(null, a);
  }

  /**
   * Constructs a new menu item with the given text and icon.
   * 
   * @param text the text of the menu item
   * @param icon the icon for this menu item
   * @deprecated <b>CAUTION!</b> will change behavior!<br>
   *             use {@link #CMenuItem(String, String, Icon)} instead
   */
  @Deprecated
  public CMenuItem(final String text, final Icon icon) {
    this(null, text, icon);
  }

  /**
   * Constructs a new menu item with the given text and mnemonic.
   * 
   * @param text the text of the menu item
   * @param mnemonic the mnemonic for this menu item
   * @deprecated use {@link #CMenuItem(String, String, int)} instead
   */
  @Deprecated
  public CMenuItem(final String text, final int mnemonic) {
    this(null, text, mnemonic);
  }

  /**
   * Creates a new {@link CMenuItem} with the given name and the given text.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link MDMenu}.
   * @param text the text of the menu item
   */
  public CMenuItem(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Creates a new {@link CMenuItem} with the given name and other properties're fetched from the given {@link Action}.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link MDMenu}.
   * @param a the {@link Action} to fetch properties from
   */
  public CMenuItem(final String name, final Action a) {
    super(a);
    setName(name);
  }

  /**
   * Creates a new {@link CMenuItem} with the given name, text and icon.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link MDMenu}.
   * @param text the text for this menu item
   * @param icon the icon for this menu item
   */
  public CMenuItem(final String name, final String text, final Icon icon) {
    super(text, icon);
    setName(name);
  }

  /**
   * Creates a new {@link CMenuItem} with the given name, text and mnemonic.
   * 
   * @since Date: May 14, 2012
   * @param name the name of this {@link MDMenu}.
   * @param text the text for this menu item
   * @param mnemonic the mnemonic for this menu item
   */
  public CMenuItem(final String name, final String text, final int mnemonic) {
    super(text, mnemonic);
    setName(name);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
