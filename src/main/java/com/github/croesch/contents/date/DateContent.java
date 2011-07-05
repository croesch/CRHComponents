package com.github.croesch.contents.date;

import java.util.Date;

import javax.swing.text.BadLocationException;

import com.github.croesch.contents.CContent;
import com.github.croesch.logging.Log;

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

  /**
   * Set the current value of the date field to the given {@link Date}. Will remove the text in the field.
   * 
   * @author croesch
   * @since Date: Jul 5, 2011
   * @param d the {@link Date} to fetch the new values from.
   * @see DateContent#setDateAndDisplay(Date)
   */
  public abstract void setDate(Date d);

  /**
   * Set the current value of the date field to the given {@link Date}. Will set the text in the field to the current
   * values.
   * 
   * @author croesch
   * @since Date: Jul 5, 2011
   * @param d the {@link Date} to fetch the new values from.
   * @see DateContent#setDate(Date)
   */
  public void setDateAndDisplay(final Date d) {
    setDate(d);
    try {
      insertString(0, getDateContent(), null);
    } catch (final BadLocationException e) {
      Log.error(e);
    }
  }

  /**
   * Returns the {@link Date} that is represented by the current value of the date field.
   * 
   * @author croesch
   * @since Date: Jul 5, 2011
   * @return {@link Date} that is equal to the values currently present in the date field.
   */
  public abstract Date getDate();

}
