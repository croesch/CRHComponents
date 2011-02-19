package com.github.croesch.contents;

import javax.swing.text.PlainDocument;

/**
 * Extends the {@link PlainDocument} and provides new functionality
 * 
 * @author croesch
 * @since Date: 09.02.2011 22:11:36
 */
public abstract class CDocument extends PlainDocument {

  /** generated serial version UID */
  private static final long serialVersionUID = -4847569004597891757L;

  /**
   * Calculates whether the content is currently valid
   * 
   * @author croesch
   * @since 13.01.2011 22:08:17
   * @return {@code true}, if the content is valid
   */
  public abstract boolean isValid();

  /**
   * Calculates whether the given {@link String} would be a valid content.
   * 
   * @author croesch
   * @since 13.01.2011 22:08:17
   * @param text the string to check, {@code null} will return {@code false}
   * @return {@code true}, if {@code text} is a valid content
   */
  public abstract boolean isValidInput(final String text);

  /**
   * Returns the complete text of this document or null if an exception occurred
   * 
   * @author croesch
   * @since 28.01.2011 22:30:37
   * @return the complete text of this document or {@code null} if an exception occurred
   */
  public abstract String getText();

}