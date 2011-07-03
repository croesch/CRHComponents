package com.github.croesch.contents.date;

import java.util.List;
import java.util.Locale;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.github.croesch.contents.CContent;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateContent extends CContent {

  /** generated */
  private static final long serialVersionUID = 530985421120602593L;

  /** list of editors that will edit the different parts of the date */
  private final List<IDateLazyPartEditor> editors;

  /** text component to set the cursor */
  private final JTextComponent textComponent;

  /**
   * Creates a new {@link DateContent} that gives special support for date values. The given text component is used to
   * set the cursor to the correct position. The locale is used to fetch the format for the date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param tc the text component for this document
   * @param loc the locale to fetch the date format from
   */
  public DateContent(final JTextComponent tc, final Locale loc) {
    this.textComponent = tc;
    this.editors = DateComposition.getComposition(loc);
  }

  @Override
  public final void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    if (str != null && str.length() > 0) {
      if (str.length() > 1) {
        for (int i = 0; i < str.length(); ++i) {
          insertString(offs + i, str.substring(i, i + 1), a);
        }
      } else {
        int tmpOfss = offs;
        boolean inserted = false;
        for (int i = 0; i < this.editors.size() && !inserted; ++i) {
          final IDateLazyPartEditor editor = this.editors.get(i);
          tmpOfss -= editor.getSize();
          if (tmpOfss < 0) {
            if (editor.enterValue(str, tmpOfss + editor.getSize()) == -1) {
              tmpOfss = 0; // pass to next editor
            } else {
              final StringBuilder sb = new StringBuilder();
              for (final IDateLazyPartEditor e : this.editors) {
                sb.append(e.getValue());
              }
              remove(0, getLength());
              super.insertString(0, sb.toString(), a);

              inserted = true;
            }
          }
        }
      }
    }
  }

}
