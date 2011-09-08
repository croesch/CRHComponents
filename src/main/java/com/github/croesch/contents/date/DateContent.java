package com.github.croesch.contents.date;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.text.JTextComponent;

import com.github.croesch.contents.CContent;

/**
 * Superclass for all date documents in this library.
 * 
 * @author croesch
 * @since Date: Jul 4, 2011
 */
public abstract class DateContent extends CContent {

  /** the name of the config file for the special characters */
  private static final String DATECHARS_CFG_FILE = "datechars.cfg";

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
    //    STRICT,
    /**
     * Auto mode:<br>
     * <ul>
     * <li>most manipulation of user input</li>
     * <li>good validation of user input</li>
     * <li>Most of automation, might be confusing.</li>
     * <li>automatic input of text field</li>
     * </ul>
     * Aim is good comfortability and validity, but least understandability.
     */
    /* AUTO */;
  }

  /** the map of special characters fetched from the config file */
  private static Map<String, DateSpecialChar> specialCharsMap;

  /**
   * Creates a new instance of this {@link DateContent}. Will instantiate the specific subclass and return it.
   * 
   * @since Date: Jul 6, 2011
   * @param mode the edit mode for the {@link DateContent} to create
   * @param tf the text field that is the owner of the {@link DateContent}
   * @param loc the {@link Locale} to fetch the date format from.
   * @return an instance of {@link DateContent} that is able to edit a date of the given locale in the given mode.
   */
  public static final DateContent createDateContent(final MODE mode, final JTextComponent tf, final Locale loc) {
    return new DateLazyContent(tf, loc, getSpecialCharsMap());
  }

  /**
   * Returns the map of special characters.
   * 
   * @since Date: Sep 8, 2011
   * @return a map of {@link String}s as keys and {@link DateSpecialChar}s to enter with the key.
   */
  private static synchronized Map<String, DateSpecialChar> getSpecialCharsMap() {
    if (specialCharsMap == null) {
      specialCharsMap = new DateSpecialCharInterpreter(new BufferedReader(new InputStreamReader(DateContent.class
        .getClassLoader().getResourceAsStream(DATECHARS_CFG_FILE)))).getSpecialCharsMap();
    }
    return new HashMap<String, DateSpecialChar>(specialCharsMap);

  }

  /**
   * Returns the {@link String} representation of the current value of the date field. Is ensured to be not
   * <code>null</code> and almost every time a fully date.
   * 
   * @since Date: Jul 4, 2011
   * @return {@link String} representation of the date currently stored in the content
   */
  public abstract String getDateContent();

  /**
   * Set the current value of the date field to the given {@link Date}. Will remove the text in the field.
   * 
   * @since Date: Jul 5, 2011
   * @param d the {@link Date} to fetch the new values from.
   */
  public abstract void setDate(Date d);

  /**
   * Returns the {@link Date} that is represented by the current value of the date field.
   * 
   * @since Date: Jul 5, 2011
   * @return {@link Date} that is equal to the values currently present in the date field.
   */
  public abstract Date getDate();

}
