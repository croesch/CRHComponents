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
 * @since Date: Dec 20, 2010
 */
public class CButton extends JButton {

  /** generated version UID */
  private static final long serialVersionUID = 2951525102559877588L;

  /**
   * Constructs the button.
   * 
   * @deprecated use another constructor instead - in a future release this should be replaced by
   *             {@link #CButton(String)}, to set the name of the button. But currently this constructor is used to set
   *             the text of the button. So choose a good alternative to this constructor.
   */
  @Deprecated
  public CButton() {
    /* TODO move this to {@link #CButton(String)} */
    super();
    setName(null);
  }

  /**
   * Constructs the button with the given icon.
   * 
   * @param icon the icon of the button
   * @deprecated use another constructor instead - in a future release this should be replaced by
   *             {@link #CButton(String, Icon)}, to set the name and icon of the button. But currently this constructor
   *             is used to set the text and icon of the button. So choose a good alternative to this constructor.
   */
  @Deprecated
  public CButton(final Icon icon) {
    /* TODO move this to {@link #CButton(String,Icon)} */
    super(icon);
    setName(null);
  }

  /**
   * Constructs the button with the given name and the given text.
   * 
   * @param text the text of the button
   * @deprecated <b>CAUTION!</b> will change behavior!<br>
   *             use {@link #CButton(String, String)} instead
   */
  @Deprecated
  public CButton(final String text) {
    this(null, text);
  }

  /**
   * Constructs the button with the given action.
   * 
   * @param action the {@link Action} for this button
   * @deprecated use {@link #CButton(String, Action)} instead
   */
  @Deprecated
  public CButton(final Action action) {
    this(null, action);
  }

  /**
   * Construct the button with the given text and icon.
   * 
   * @param text the text of the button
   * @param icon the icon of the button
   * @deprecated <b>CAUTION!</b> will change behavior!<br>
   *             use {@link #CButton(String, String, Icon)} instead
   */
  @Deprecated
  public CButton(final String text, final Icon icon) {
    this(null, text, icon);
  }

  /**
   * Constructs the button with the given name, text and icon.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of the component to set via {@link #setName(String)}
   * @param text the text of the button
   * @param icon the icon of the button
   * @see #setName(String)
   */
  public CButton(final String name, final String text, final Icon icon) {
    super(text, icon);
    setName(name);
  }

  /**
   * Constructs the button with the given name and the given text.
   * 
   * @since Date: Mar 9, 2012
   * @param name the name of the component to set via {@link #setName(String)}
   * @param text the text of the button
   * @see #setName(String)
   */
  public CButton(final String name, final String text) {
    super(text);
    setName(name);
  }

  /**
   * Constructs the button with the given name and the given action.
   * 
   * @since Date: Mar 9, 2012
   * @param name the name of the component to set via {@link #setName(String)}
   * @param action the {@link Action} for this button
   * @see #setName(String)
   */
  public CButton(final String name, final Action action) {
    super(action);
    setName(name);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
