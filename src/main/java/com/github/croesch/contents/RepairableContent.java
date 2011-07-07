package com.github.croesch.contents;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Provides methods to repair its content and to repair it automatically
 * 
 * @author croesch
 * @since (Date: 2011/01/13 21:12:39 )
 */
public class RepairableContent extends CContent {

  /** serial version UID */
  private static final long serialVersionUID = 6430263687389818360L;

  /** if the content is auto reparable - default: {@code false} */
  private boolean autoRepair = false;

  /**
   * Repairs the content
   * 
   * @since (Date: 13.01.2011 22:08:17)
   * @throws BadLocationException if {@link #getLength()} returns an invalid value
   */
  public final void repair() throws BadLocationException {
    if (getMaximumInputLength() > 0 && getLength() > getMaximumInputLength()) {
      final boolean auto = isAutoRepairContent();
      if (auto) {
        setAutoRepairContent(false);
      }
      remove(getMaximumInputLength(), getLength() - getMaximumInputLength());
      if (auto) {
        setAutoRepairContent(true);
      }
    }
  }

  @Override
  public final boolean isValidInput(final String text) {
    return isAutoRepairContent() || super.isValidInput(text);
  }

  /**
   * @since (Date: 13.01.2011 22:08:17)
   * @return {@code true}, if it repairs its content automatically
   */
  public final boolean isAutoRepairContent() {
    return this.autoRepair;
  }

  /**
   * Sets whether the content should be repaired automatically
   * 
   * @since (Date: 2011/01/13 21:12:39)
   * @param auto {@code true}, if it should be repaired automatically
   */
  public final void setAutoRepairContent(final boolean auto) {
    this.autoRepair = auto;
  }

  @Override
  public final void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
    super.insertString(offs, str, a);
    if (isAutoRepairContent()) {
      repair();
    }
  }

  @Override
  public final void remove(final int offs, final int len) throws BadLocationException {
    super.remove(offs, len);
    if (isAutoRepairContent()) {
      repair();
    }
  }

  @Override
  public final void replace(final int offset, final int length, final String text, final AttributeSet attrs)
                                                                                                            throws BadLocationException {
    super.replace(offset, length, text, attrs);
    if (isAutoRepairContent()) {
      repair();
    }
  }
}
