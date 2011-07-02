package com.github.croesch.contents.date;

/**
 * An <i>lazy</i> editor for any part of a date. It will allow all entries except invalid characters or characters that
 * are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
interface IDateLazyPartEditor {

  /**
   * Returns the number of chars that this editor edits.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @return number of chars that this editor is editing.
   */
  int getSize();

  /**
   * Performs to enter the given string into that editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param s the string to enter.
   * @param position the position to enter the {@link String} at.
   * @return <code>-1</code>, if the given string cannot be inserted<br>
   *         the number of characters written / the cursor should be moved
   */
  int enterValue(String s, int position);

  /**
   * Returns the current value of the editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @return the {@link String} that represents the current value of the editor
   */
  String getValue();

}
