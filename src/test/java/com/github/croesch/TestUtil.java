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
