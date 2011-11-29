/*
 * Copyright (C) 2011  Christian Roesch
 * 
 * This file is part of crhcomponents.
 * 
 * crhcomponents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * crhcomponents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with crhcomponents.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * An util for date objects, offers special operations on date objects.
 * 
 * @author croesch
 * @since Date: Sep 11, 2011
 */
public final class DateUtil {

  /** the {@link Date} to work with - ensured to be not {@code null} */
  private final Date date;

  /** the {@link Date} to work with, but without time information */
  private Date dateWithoutTime = null;

  /**
   * Constructs an util for date objects with the given {@link Date}.
   * 
   * @since Date: Sep 11, 2011
   * @param d the {@link Date} to operate with
   */
  public DateUtil(final Date d) {
    if (d == null) {
      // important, because we want to ensure that this.date is not null
      throw new IllegalArgumentException(">null< is not allowed.");
    }
    this.date = new Date(d.getTime());
  }

  /**
   * Returns whether the date of this util is equal to the given date.
   * 
   * @since Date: Sep 11, 2011
   * @param other the date to compare the current one with
   * @return <code>true</code>, if this date is equal to the given date
   * @see Date#equals(Object)
   * @see #isEqualIgnoreTimeTo(Date)
   */
  public boolean isEqualTo(final Date other) {
    return this.date.equals(other);
  }

  /**
   * Returns whether the date of this util is equal to the given date, if you ignore the time values.
   * 
   * @since Date: Sep 11, 2011
   * @param other the date to compare the current one with
   * @return <code>true</code>, if this date is equal to the given date. Means that the years, months and dates are
   *         equal.
   * @see #isEqualTo(Date)
   */
  public boolean isEqualIgnoreTimeTo(final Date other) {
    if (other == null) {
      // avoid IllegalArgumentException and return directly false
      return false;
    }
    return getDateWithoutTime().equals(new DateUtil(other).getDateWithoutTime());
  }

  /**
   * Returns the date with erased time information.
   * 
   * @since Date: Sep 11, 2011
   * @return the {@link Date} but without time information, so hour, minute, second and millisecond will be zero.
   */
  public Date getDateWithoutTime() {
    // cache the calculated value to be able to deliver that a lot of times
    if (this.dateWithoutTime == null) {
      // put the date into a calendar to be able to fetch fields from
      final Calendar calHelp = new GregorianCalendar();
      calHelp.setTime(this.date);

      // build calendar that will create the date object later
      final Calendar calRet = new GregorianCalendar();
      // set time to zero
      calRet.setTimeInMillis(0);
      // set date information and reset hour - because this might have a value other than zero
      calRet.set(calHelp.get(Calendar.YEAR), calHelp.get(Calendar.MONTH), calHelp.get(Calendar.DAY_OF_MONTH), 0, 0);
      this.dateWithoutTime = calRet.getTime();
    }
    return new Date(this.dateWithoutTime.getTime());
  }

}
