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

/**
 * This is a document to define a regular expression for its content
 * 
 * @author croesch
 * @since Date: Jan 13, 2011
 */
public class RegexContent extends CContent {

  /** generated version UID */
  private static final long serialVersionUID = -7202917896917725985L;

  /** the regular expression for the content, that should be matched */
  private String regularExpression = ".*"; //$NON-NLS-1$

  /**
   * Constructs a document with the given regular expression
   * 
   * @since Date: Jan 13, 2011
   * @param regex initial regular expression
   */
  public RegexContent(final String regex) {
    setRegularExpression(regex);
  }

  /**
   * The current regular expression
   * 
   * @since Date: Jan 13, 2011
   * @return a String that contains the regular expression
   */
  public final String getRegularExpression() {
    return this.regularExpression;
  }

  /**
   * Sets the regular expression for this document
   * 
   * @since Date: Jan 13, 2011
   * @param regex the regular expression to set
   */
  public final void setRegularExpression(final String regex) {
    this.regularExpression = regex;
    checkForErrors();
  }

  @Override
  public final boolean isValidInput(final String text) {
    return text.matches(getRegularExpression());
  }
}
