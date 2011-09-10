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
import javax.swing.JButton;

import com.github.croesch.util.MnemonicUtil;

/**
 * This button is able to set its mnemonic key automatically.
 * 
 * @author croesch
 * @since (Date: 2010/12/20 17:40:58)
 */
public class CButton extends JButton {

  /** generated version UID */
  private static final long serialVersionUID = 2951525102559877588L;

  /**
   * Simply calls {@link JButton#JButton()}
   * 
   * @see JButton#JButton()
   */
  public CButton() {
    super();
  }

  /**
   * Simply calls {@link JButton#JButton(Icon)}
   * 
   * @see JButton#JButton(Icon)
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CButton(final Icon icon) {
    super(icon);
  }

  /**
   * Simply calls {@link JButton#JButton(String)}
   * 
   * @see JButton#JButton(String)
   * @param text the parameter String that is given to superclass constructor
   */
  public CButton(final String text) {
    super(text);
  }

  /**
   * Simply calls {@link JButton#JButton(Action)}
   * 
   * @see JButton#JButton(Action)
   * @param a the parameter Action that is given to superclass constructor
   */
  public CButton(final Action a) {
    super(a);
  }

  /**
   * Simply calls {@link JButton#JButton(String, Icon)}
   * 
   * @see JButton#JButton(String, Icon)
   * @param text the parameter String that is given to superclass constructor
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CButton(final String text, final Icon icon) {
    super(text, icon);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
