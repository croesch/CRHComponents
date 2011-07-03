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
    if (s != null && s.length() == 1 && position >= 0 && position < this.value.length()
        && s.charAt(0) == this.value.charAt(position)) {
      return 1;
    }
    return -1;
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
