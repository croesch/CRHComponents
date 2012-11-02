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

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

/**
 * An extension of {@link JFrame} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CFrame extends JFrame {

  /** generated serial version UID */
  private static final long serialVersionUID = 5565102209049402595L;

  /**
   * Constructs a new frame with the given name.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @throws HeadlessException if {@link java.awt.GraphicsEnvironment#isHeadless()} returns <code>true</code>.
   */
  public CFrame(final String name) throws HeadlessException {
    setName(name);
  }

  /**
   * Constructs a new frame with the given name and the given {@link GraphicsConfiguration}.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param gc the {@link GraphicsConfiguration} that is used to construct the new frame; if it is <code>null</code>,
   *        the system default {@link GraphicsConfiguration} is assumed
   */
  public CFrame(final String name, final GraphicsConfiguration gc) {
    super(gc);
    setName(name);
  }

  /**
   * Constructs a new frame with the given name and the given title.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param title the title of the frame
   * @throws HeadlessException if {@link java.awt.GraphicsEnvironment#isHeadless()} returns <code>true</code>.
   */
  public CFrame(final String name, final String title) throws HeadlessException {
    super(title);
    setName(name);
  }

  /**
   * Constructs a new frame with the given name, the given title and the given {@link GraphicsConfiguration}.
   * 
   * @since Date: Nov 2, 2012
   * @param name the name of this component
   * @param title the title of the frame
   * @param gc the {@link GraphicsConfiguration} that is used to construct the new frame; if it is <code>null</code>,
   *        the system default {@link GraphicsConfiguration} is assumed
   */
  public CFrame(final String name, final String title, final GraphicsConfiguration gc) {
    super(title, gc);
    setName(name);
  }
}
