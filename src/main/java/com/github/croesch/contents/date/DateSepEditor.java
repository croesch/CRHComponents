package com.github.croesch.contents.date;

/**
 * The editor for the separator part of a date. It will allow characters that are valid separator chars at the position
 * they are entered.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateSepEditor implements IDatePartEditor {

  /** the current value of the separator - won't change */
  private final String value;

  /**
   * Constructs a new lazy separator editor that won't accept any other chars than contained in the separator at the
   * given position.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param sep the separator to represent
   */
  public DateSepEditor(final String sep) {
    this.value = sep;
  }

  @Override
  public int getSize() {
    return this.value.length();
  }

  @Override
  public int enterValue(final String s, final int position) {
    // you can only enter a single character
    // and if the position is valid
    // and if the character is correct for the given position
    if (isASingleCharacter(s) && isPositionValid(position) && s.charAt(0) == this.value.charAt(position)) {
      return 1;
    }
    return -1;
  }

  /**
   * Returns whether you can find a character in this separator at the given position.
   * 
   * @since Date: Aug 24, 2011
   * @param position the position to check the validity of
   * @return <code>true</code>, if you could call {@link String#charAt(int)} without any problems. Or in other words:
   *         <code>true</code>, if the position is at least zero and less than the number of characters of this
   *         separator.
   */
  private boolean isPositionValid(final int position) {
    return position >= 0 && position < getSize();
  }

  /**
   * Returns whether the given {@link String} contains only a single character.
   * 
   * @since Date: Aug 24, 2011
   * @param s the string to test.
   * @return <code>true</code>, if the given {@link String} is not <code>null</code> and consists only of one character.
   */
  private static boolean isASingleCharacter(final String s) {
    return s != null && s.length() == 1;
  }

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return getValue();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    return prime + this.value.hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof DateSepEditor)) {
      return false;
    }
    final DateSepEditor other = (DateSepEditor) obj;
    return this.value.equals(other.value);
  }

  @Override
  public void setCurrentValue(final int day, final int month, final int year) {
    // do nothing, because value of others are not important
  }
}
