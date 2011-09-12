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
package com.github.croesch.contents;

import javax.swing.text.PlainDocument;

/**
 * Extends the {@link PlainDocument} and provides new functionality
 * 
 * @author croesch
 * @since Date: Feb 09, 2011
 */
public abstract class CDocument extends PlainDocument {

  /** generated serial version UID */
  private static final long serialVersionUID = -4847569004597891757L;

  /**
   * Calculates whether the content is currently valid
   * 
   * @since Date: Jan 13, 2011
   * @return {@code true}, if the content is valid
   */
  public abstract boolean isValid();

  /**
   * Calculates whether the given {@link String} would be a valid content.
   * 
   * @since Date: Jan 13, 2011
   * @param text the string to check, {@code null} will return {@code false}
   * @return {@code true}, if {@code text} is a valid content
   */
  public abstract boolean isValidInput(final String text);

  /**
   * Returns the complete text of this document or null if an exception occurred
   * 
   * @since Date: Jan 28, 2011
   * @return the complete text of this document or {@code null} if an exception occurred
   */
  public abstract String getText();

}
