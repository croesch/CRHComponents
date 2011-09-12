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
   * Simply calls {@link JMenuItem#JMenuItem()}
   * 
   * @see JMenuItem#JMenuItem()
   */
  public CMenuItem() {
    super();
  }

  /**
   * Simply calls {@link JMenuItem#JMenuItem(Icon)}
   * 
   * @see JMenuItem#JMenuItem(Icon)
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CMenuItem(final Icon icon) {
    super(icon);
  }

  /**
   * Simply calls {@link JMenuItem#JMenuItem(String)}
   * 
   * @see JMenuItem#JMenuItem(String)
   * @param text the parameter String that is given to superclass constructor
   */
  public CMenuItem(final String text) {
    super(text);
  }

  /**
   * Simply calls {@link JMenuItem#JMenuItem(Action)}
   * 
   * @see JMenuItem#JMenuItem(Action)
   * @param a the parameter Action that is given to superclass constructor
   */
  public CMenuItem(final Action a) {
    super(a);
  }

  /**
   * Simply calls {@link JMenuItem#JMenuItem(String, Icon)}
   * 
   * @see JMenuItem#JMenuItem(String, Icon)
   * @param text the parameter String that is given to superclass constructor
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CMenuItem(final String text, final Icon icon) {
    super(text, icon);
  }

  /**
   * Simply calls {@link JMenuItem#JMenuItem(String, int)}
   * 
   * @see JMenuItem#JMenuItem(String, int)
   * @param text the parameter String that is given to superclass constructor
   * @param mnemonic the parameter int that is given to superclass constructor
   */
  public CMenuItem(final String text, final int mnemonic) {
    super(text, mnemonic);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
