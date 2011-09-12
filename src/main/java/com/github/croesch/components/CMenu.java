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
   * Simply calls {@link JMenu#JMenu()}
   */
  public CMenu() {
    super();
  }

  /**
   * Simply calls {@link JMenu#JMenu(Action)}
   * 
   * @param a parameter Action that is given to {@link JMenu#JMenu(Action)}
   */
  public CMenu(final Action a) {
    super(a);
  }

  /**
   * Simply calls {@link JMenu#JMenu(String, boolean)}
   * 
   * @param s parameter String that is given to {@link JMenu#JMenu(String, boolean)}
   * @param b parameter boolean that is given to {@link JMenu#JMenu(String, boolean)}
   */
  public CMenu(final String s, final boolean b) {
    super(s, b);
  }

  /**
   * Simply calls {@link JMenu#JMenu(String)}
   * 
   * @param s parameter that is given to {@link JMenu#JMenu(String)}
   */
  public CMenu(final String s) {
    super(s);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
