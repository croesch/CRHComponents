package com.github.croesch.contents.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.github.croesch.logging.Log;

/**
 * {@link DateContent.MODE#LAZY} implementation of {@link DateContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateLazyContent extends DateContent {

  /** generated */
  private static final long serialVersionUID = 530985421120602593L;

  /** list of editors that will edit the different parts of the date */
  private List<IDateLazyPartEditor> editors;

  /** text component to set the cursor */
  private final JTextComponent textComponent;

  /** the locale of the date content */
  private final Locale locale;

  /**
   * Creates a new {@link DateLazyContent} that gives special support for date values. The given text component is used
   * to set the cursor to the correct position. The locale is used to fetch the format for the date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param tc the text component for this document
   * @param loc the locale to fetch the date format from
   */
  public DateLazyContent(final JTextComponent tc, final Locale loc) {
    this.textComponent = tc;
    this.locale = loc;
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY);
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
        performInsert(offs, str, a);
      }
    }
  }

  /**
   * Performs the insertion of a string not <code>null</code> and being one char long.
   * 
   * @author croesch
   * @since Date: Jul 4, 2011
   * @param offs the offset where to insert basically
   * @param str the string to insert
   * @param a the {@link AttributeSet}.
   * @throws BadLocationException if inserted on an invalid position
   */
  private void performInsert(final int offs, final String str, final AttributeSet a) throws BadLocationException {
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
          remove(0, getLength());
          super.insertString(0, getDateContent(), a);

          if (this.textComponent != null) {
            this.textComponent.setCaretPosition(tmpOfss + startPos + z);
          }
          inserted = true;
        }
      }

      tmpOfss -= editor.getSize();
      startPos += editor.getSize();
    }
  }

  @Override
  public final String getDateContent() {
    final StringBuilder sb = new StringBuilder();
    for (final IDateLazyPartEditor e : this.editors) {
      sb.append(e.getValue());
    }
    return sb.toString();
  }

  @Override
  public final Date getDate() {
    final Calendar cal = new GregorianCalendar();
    int day = 1;
    int month = 1;
    int year = 1;
    for (final IDateLazyPartEditor e : this.editors) {
      if (e instanceof DateLazyYearEditor) {
        year = Integer.parseInt(e.getValue());
      } else if (e instanceof DateLazyMonEditor) {
        month = Integer.parseInt(e.getValue()) - 1;
      } else if (e instanceof DateLazyDayEditor) {
        day = Integer.parseInt(e.getValue());
      }
    }

    cal.set(year, month, day);
    return cal.getTime();
  }

  @Override
  public final void setDate(final Date d) {
    try {
      remove(0, getLength());
    } catch (final BadLocationException e) {
      Log.error(e);
    }

    final Calendar cal = new GregorianCalendar();
    cal.setTime(d);
    this.editors = DateComposition.getComposition(this.locale, MODE.LAZY, cal.get(Calendar.DAY_OF_MONTH),
                                                  cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
  }
}
