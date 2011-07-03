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
        int o = offs;
        for (int i = 0; i < str.length(); ++i) {
          if (this.textComponent != null) {
            insertString(o, str.substring(i, i + 1), a);
            o = this.textComponent.getCaretPosition();
          } else {
            insertString(o + i, str.substring(i, i + 1), a);
          }
        }
      } else {
        int startPos = 0;
        int tmpOfss = offs;
        boolean inserted = false;

        for (int i = 0; i < this.editors.size() && !inserted; ++i) {
          final IDateLazyPartEditor editor = this.editors.get(i);

          if (tmpOfss - editor.getSize() < 0) {
            final int z = editor.enterValue(str, tmpOfss);
            if (z == -1) {
              tmpOfss = editor.getSize(); // pass to next editor
            } else {
              final StringBuilder sb = new StringBuilder();
              for (final IDateLazyPartEditor e : this.editors) {
                sb.append(e.getValue());
              }
              remove(0, getLength());
              super.insertString(0, sb.toString(), a);

              this.textComponent.setCaretPosition(tmpOfss + startPos + z);
              inserted = true;
            }
          }

          tmpOfss -= editor.getSize();
          startPos += editor.getSize();
        }
      }
    }
  }

}
