package com.github.croesch.contents.date;

import java.util.Calendar;

/**
 * An <i>lazy</i> editor for the month part of a date. It will allow all entries except invalid characters or characters
 * that are not allowed at that position. But it won't use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
class DateLazyMonEditor extends DateLazyMonAndDayEditor {

  /** constant of the highest value */
  private static final int HIGHEST = 12;

  /**
   * Constructs a new lazy date editor with the value of the current month.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @see #DateLazyDayEditor(int)
   */
  DateLazyMonEditor() {
    this(0);
  }

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than 12 the current month will be the
   *        initial value.
   * @see #DateLazyDayEditor()
   */
  DateLazyMonEditor(final int initial) {
    super(initial, HIGHEST);
  }

  @Override
  protected int getDefaultValue() {
    return Calendar.getInstance().get(Calendar.MONTH) + 1;
  }

  @Override
  protected String getValidForBoth() {
    return "01";
  }

  @Override
  protected String getValidOnlyForSecond() {
    return "23456789";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getValue().hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof DateLazyMonEditor)) {
      return false;
    }
    final DateLazyMonEditor other = (DateLazyMonEditor) obj;
    return getValue().equals(other.getValue());
  }
}
