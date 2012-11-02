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

import java.awt.Rectangle;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;

import com.github.croesch.logging.Log;

/**
 * An extension of {@link JTextPane} that contains some default behavior that not each client should have to implement.
 * 
 * @author croesch
 * @since Date: Mar 31, 2012
 */
public class CTextPane extends JTextPane {

  /** generated serial version UID */
  private static final long serialVersionUID = -1439369119660645723L;

  /**
   * Constructs the text pane and sets the given name.
   * 
   * @since Date: Mar 31, 2012
   * @param name the name of the component to set.
   */
  public CTextPane(final String name) {
    super();
    setName(name);
  }

  /**
   * Constructs the text pane and sets the given name.
   * 
   * @since Date: Mar 31, 2012
   * @param name the name of the component to set.
   * @param doc the document model
   */
  public CTextPane(final String name, final StyledDocument doc) {
    super(doc);
    setName(name);
  }

  /**
   * Returns the line from the given offset. Implementation is based on
   * {@link javax.swing.JTextArea#getLineOfOffset(int)}.
   * 
   * @since Date: Mar 21, 2012
   * @param offset the offset to determine the line from
   * @return the line that contains the given offset,<br>
   *         or <code>-1</code> if the given offset was invalid.
   */
  public final int getLineOfOffset(final int offset) {
    if (offset < 0 || offset > getDocument().getLength()) {
      return -1;
    } else {
      return getDocument().getDefaultRootElement().getElementIndex(offset);
    }
  }

  /**
   * Returns the current line numbers of this text component. Based on {@link javax.swing.JTextArea#getLineCount()}.
   * 
   * @since Date: Mar 20, 2012
   * @return the number of lines this text component has
   */
  public final int getLineCount() {
    return getDocument().getDefaultRootElement().getElementCount();
  }

  /**
   * Returns the line height of the text component.
   * 
   * @since Date: Mar 21, 2012
   * @return the height of a single line in the text component.
   */
  public final int getLineHeight() {
    if (getLineCount() > 1) {
      try {
        final Rectangle lineOne = modelToView(0);
        final Rectangle lineTwo = modelToView(getLineStartOffset(1));
        return lineTwo.y - lineOne.y;
      } catch (final BadLocationException blex) {
        Log.error(blex);
      }
    }
    return getFont().getSize() + 2;
  }

  /**
   * Determines the offset of the start of the given line. Implementation based on
   * {@link javax.swing.JTextArea#getLineStartOffset(int)}.
   * 
   * @since Date: Mar 21, 2012
   * @param line the line number to translate
   * @return the offset >= 0,<br>
   *         or <code>-1</code> if the given line was invalid.
   */
  public final int getLineStartOffset(final int line) {
    if (line < 0 || line >= getLineCount()) {
      return -1;
    } else {
      final Element lineElem = getDocument().getDefaultRootElement().getElement(line);
      return lineElem.getStartOffset();
    }
  }
}
