package com.github.croesch.contents;

/**
 * An <i>lazy</i> editor for any part of a date. It will allow only entries that contain valid characters and characters
 * that are valid at that position. May use the other parts of date for a validity check.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
interface IDatePartEditor extends IDateLazyPartEditor {

  /**
   * Updates the intern value of the date to the given values.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   * @param day the current day value
   * @param month the current month value
   * @param year the current year value
   */
  void setCurrentValue(int day, int month, int year);

}
