package com.github.croesch.contents.date;

/**
 * Class that contains logic for both, lazy day and lazy month editor.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
abstract class DateLazyMonAndDayEditor implements IDateLazyPartEditor {

  /** constant for number ten */
  private static final int TEN = 10;

  /** the current value */
  private final char[] value = { '0', '0' };

  /**
   * Constructs a new lazy date editor with the given value.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param initial the initial value. If it's less or equal than zero or greater than <code>highest</code> the current
   *        value will be the initial value.
   * @param highest the highest possible value of this editor
   */
  DateLazyMonAndDayEditor(final int initial, final int highest) {
    int val = initial;
    if (val <= 0 || val > highest) {
      val = getDefaultValue();
    }
    final int first = val / TEN;
    final int second = val % TEN;
    this.value[0] = (char) ('0' + first);
    this.value[1] = (char) ('0' + second);
  }

  /**
   * Returns the {@link String} that contains all characters that are only valid for the second position.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return {@link String} containing all chars being valid only for the second position.
   */
  protected abstract String getValidOnlyForSecond();

  /**
   * Returns the {@link String} that contains all characters that are valid for the both positions.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return {@link String} containing all chars being valid for the both positions.
   */
  protected abstract String getValidForBoth();

  /**
   * Returns the default value of this editor, this means the current value of the field in the current date.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @return the current value of the specific field in the current date.
   */
  protected abstract int getDefaultValue();

  @Override
  public final int getSize() {
    return 2;
  }

  @Override
  public final int enterValue(final String s, final int position) {
    if (s != null && s.length() == 1) {

      if (getValidOnlyForSecond().indexOf(s) >= 0) {
        if (position == 0) {
          this.value[1] = s.charAt(0);
          return 2;
        }
        if (position == 1) {
          this.value[1] = s.charAt(0);
          return 1;
        }
      }
      if (getValidForBoth().indexOf(s) >= 0 && (position == 0 || position == 1)) {
        this.value[position] = s.charAt(0);
        return 1;
      }
    }
    return -1;
  }

  @Override
  public final String getValue() {
    return String.valueOf(this.value, 0, 2);
  }

  @Override
  public final String toString() {
    return getValue();
  }

}
