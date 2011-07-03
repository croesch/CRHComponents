package com.github.croesch.contents;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import com.github.croesch.listener.ErrorListener;

/**
 * Standard content that allows every input.
 * 
 * @author croesch
 * @since (Date: 2011/01/13 21:12:39)
 */
public class CContent extends CDocument {

  /** generated serial version UID */
  private static final long serialVersionUID = 767243441327463889L;

  /** the current state of error */
  private boolean error = false;

  /** maximum input length - <= 0 equals infinity */
  private int maxLength = 0;

  /**
   * Whether the content would be valid with this input.
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   * @param offs the offset of the input
   * @param str the String to put in
   * @return {@code true}, if it would be valid
   * @throws BadLocationException if the given {@code offs} is invalid
   */
  public final boolean isValidInput(final int offs, final String str) throws BadLocationException {
    return isValidInput(getText(0, offs) + str + getText(offs, getLength() - offs));
  }

  @Override
  public boolean isValid() {
    try {
      return isValidInput(getText(0, getLength()));
    } catch (final BadLocationException e) {
      return false;
    }
  }

  @Override
  public boolean isValidInput(final String text) {
    return text != null && (getMaximumInputLength() <= 0 || text.length() <= getMaximumInputLength());
  }

  /** the list of listeners that listens on changes of the error state */
  private final List<ErrorListener> listeners = new ArrayList<ErrorListener>();

  /** whether listeners should be informed on changes of the error state */
  private boolean notifyErrors = true;

  /**
   * Sets whether {@link ErrorListener}s are informed about error changes
   * 
   * @author croesch
   * @since 13.01.2011 22:08:17
   * @param notify whether {@link ErrorListener}s should be informed when error changes
   */
  public final void setErrorsNotifying(final boolean notify) {
    this.notifyErrors = notify;
  }

  /**
   * Whether {@link ErrorListener}s are informed about changes
   * 
   * @author croesch
   * @since 13.01.2011 22:08:17
   * @return {@code true} if {@link ErrorListener}s are informed when error changes
   */
  public final boolean isErrorsNotifying() {
    return this.notifyErrors;
  }

  /**
   * Add listeners to this document
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   * @param listener the {@link ErrorListener}s to add
   */
  public final void addErrorListeners(final ErrorListener ... listener) {
    for (final ErrorListener e : listener) {
      if (this.listeners.contains(e)) {
        continue;
      }
      // to inform the listener about the initial state
      e.errorStateChanged(this.error);
      this.listeners.add(e);
    }
  }

  /**
   * Remove these error listeners from this document
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   * @param listener the {@link ErrorListener}s to remove
   */
  public final void removeErrorListeners(final ErrorListener ... listener) {
    for (final ErrorListener e : listener) {
      this.listeners.remove(e);
    }
  }

  /**
   * The maximum number of chars in this document
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   * @return the number of maximum chars, or {@code 0} if there is no maximum
   */
  public final int getMaximumInputLength() {
    if (this.maxLength < 0) {
      return 0;
    }
    return this.maxLength;
  }

  /**
   * Set the maximum number of chars in this document, <= 0 equals no maximum
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   * @param max the maximum number of chars, or {@code 0} for no maximum
   */
  public final void setMaximumInputLength(final int max) {
    this.maxLength = max;
    checkForErrors();
  }

  @Override
  public final String getText() {
    try {
      return getText(0, getLength());
    } catch (final BadLocationException e) {
      return null;
    }
  }

  /**
   * Checks for errors and if the state has changed it informs the error listeners
   * 
   * @author croesch
   * @since (Date: 13.01.2011 22:08:17)
   */
  public final void checkForErrors() {
    if (this.error == isValid() && isErrorsNotifying()) {
      this.error = !this.error;
      for (final ErrorListener l : this.listeners) {
        l.errorStateChanged(this.error);
      }
    }
  }

  @Override
  public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    if (offs > getLength()) {
      throw new BadLocationException(str, offs);
    }
    super.insertString(offs, str, a);
    checkForErrors();
  }

  @Override
  public void remove(final int offs, final int len) throws BadLocationException {
    super.remove(offs, len);
    checkForErrors();
  }

  @Override
  public void replace(final int offset, final int length, final String text, final AttributeSet attrs)
                                                                                                      throws BadLocationException {
    super.replace(offset, length, text, attrs);
    checkForErrors();
  }
}
