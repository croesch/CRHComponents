package com.github.croesch.components;

import java.util.Locale;

import javax.swing.JTextField;

import com.github.croesch.contents.date.DateContent;

/**
 * A text field that is specially made for date values.
 * 
 * @author croesch
 * @since Date: Mar 31, 2011 2:28:14 PM
 */
public class CDateField extends JTextField {

  /** generated */
  private static final long serialVersionUID = -8281302986312084742L;

  /**
   * Generates a text field that is specially made for date values. The format of the date will be fetched from the
   * default locale of the system.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  public CDateField() {
    this(Locale.getDefault());
  }

  /**
   * Generates a text field that is specially made for date values. The format of the date will be fetched from the
   * given {@link Locale}.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param locale the {@link Locale} to fetch the format of the date from
   */
  public CDateField(final Locale locale) {
    setDocument(new DateContent(this, locale));
  }
}
