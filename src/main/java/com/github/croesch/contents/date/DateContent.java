package com.github.croesch.contents.date;

import com.github.croesch.contents.CContent;

/**
 * Superclass for all date documents in this library.
 * 
 * @author croesch
 * @since Date: Jul 4, 2011
 */
abstract class DateContent extends CContent {

  /** generated */
  private static final long serialVersionUID = 6501226934436567798L;

  /** the mode for entering date values */
  public enum MODE {
    /**
     * Lazy mode:<br>
     * <ul>
     * <li>no manipulation of user input</li>
     * <li>least validation of user input</li>
     * <li>most compliance with normal text fields</li>
     * <li>least automatic input of text field</li>
     * </ul>
     * Normal comfortability but best understandability, least validity.
     */
    LAZY,
    /**
     * Strict mode:<br>
     * <ul>
     * <li>no manipulation of user input</li>
     * <li>highest validation of user input</li>
     * <li>Not a normal text field but not fully automatically.</li>
     * <li>some automatic input of text field</li>
     * </ul>
     * Least comfortability, normal understandability, but best validity.
     */
    STRICT,
    /**
     * Auto mode:<br>
     * <ul>
     * <li>most manipulation of user input</li>
     * <li>good validation of user input</li>
     * <li>Most of automation, might be confusing.</li>
     * <li>automatic input of text field</li>
     * </ul>
     * Aim is good comfortability and validity, but least and understandability.
     */
    AUTO;
  }

  /**
   * Returns the {@link String} representation of the current value of the date field. Is ensured to be not
   * <code>null</code> and almost every time a fully date.
   * 
   * @author croesch
   * @since Date: Jul 4, 2011
   * @return {@link String} representation of the date currently stored in the content
   */
  public abstract String getDateContent();

}
