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
package com.github.croesch;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Ignore;

/**
 * Provides utility methods for test cases.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
@Ignore
public class TestUtil {

  public static final void assertDateHasValues(final Date date, final int day, final int month, final int year) {
    final Calendar cal = new GregorianCalendar();
    cal.setTime(date);

    assertThat(cal.get(Calendar.DAY_OF_MONTH)).isEqualTo(day);
    assertThat(cal.get(Calendar.MONTH) + 1).isEqualTo(month);
    assertThat(cal.get(Calendar.YEAR)).isEqualTo(year);
  }

}
