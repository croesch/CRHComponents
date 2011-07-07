package com.github.croesch.contents;

/**
 * This is a document to define a regular expression for its content
 * 
 * @author croesch
 * @since (Date: 2011/01/13 21:12:39)
 */
public class RegexContent extends CContent {

  /** generated version UID */
  private static final long serialVersionUID = -7202917896917725985L;

  /** the regular expression for the content, that should be matched */
  private String regularExpression = ".*"; //$NON-NLS-1$

  /**
   * Constructs a document with the given regular expression
   * 
   * @since (Date: 13.01.2011 22:08:17)
   * @param regex initial regular expression
   */
  public RegexContent(final String regex) {
    setRegularExpression(regex);
  }

  /**
   * The current regular expression
   * 
   * @since (Date: 13.01.2011 22:08:17)
   * @return a String that contains the regular expression
   */
  public final String getRegularExpression() {
    return this.regularExpression;
  }

  /**
   * Sets the regular expression for this document
   * 
   * @since (Date: 13.01.2011 22:08:17)
   * @param regex the regular expression to set
   */
  public final void setRegularExpression(final String regex) {
    this.regularExpression = regex;
    checkForErrors();
  }

  @Override
  public final boolean isValidInput(final String text) {
    return text.matches(getRegularExpression());
  }
}
