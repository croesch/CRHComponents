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

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Test methods for {@link DateUtil}
 * 
 * @author croesch
 * @since Date: Sep 11, 2011
 */
public class DateUtilTest extends DefaultTestCase {

  private DateUtil util;

  @Override
  protected void setUpDetails() {
    this.util = new DateUtil(new Date());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_IAE() {
    new DateUtil(null);
  }

  @Test
  public void testIsEqualTo() {
    final Date d = new Date(1234567890);
    this.util = new DateUtil(d);

    assertThat(this.util.isEqualTo(d)).isTrue();
    assertThat(this.util.isEqualTo(null)).isFalse();
    assertThat(this.util.isEqualTo(new Date(1234567890))).isTrue();

    // test immutability of date in constructor
    d.setTime(123456789);
    assertThat(this.util.isEqualTo(new Date(1234567890))).isTrue();
  }

  @Test
  public void testGetDateWithoutTime_IsZero() {
    final Calendar cal = new GregorianCalendar();
    cal.setTime(this.util.getDateWithoutTime());
    assertThat(cal.get(Calendar.HOUR_OF_DAY)).isZero();
    assertThat(cal.get(Calendar.MINUTE)).isZero();
    assertThat(cal.get(Calendar.SECOND)).isZero();
    assertThat(cal.get(Calendar.MILLISECOND)).isZero();
  }

  @Test
  public void testGetDateWithoutTime_IsNewInstance() {
    assertThat(this.util.getDateWithoutTime()).isNotSameAs(this.util.getDateWithoutTime());
    final Date d = this.util.getDateWithoutTime();
    final Date d2 = new Date(d.getTime());
    d.setTime(1238947362);
    assertThat(this.util.getDateWithoutTime()).isEqualTo(d2);
  }

  @Test
  public void testIsEqualIgnoreTimeTo_MS() {
    this.util = new DateUtil(new Date(1234567890));
    final Date reference = new Date(1234567891);
    assertThat(this.util.isEqualTo(reference)).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(reference)).isTrue();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Seconds() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4, 54);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.SECOND, 45);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isTrue();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Minutes() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.MINUTE, 3);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isTrue();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Hours() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.HOUR_OF_DAY, 21);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isTrue();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Day() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.DAY_OF_MONTH, 30);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isFalse();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Month() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.MONTH, 1);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isFalse();
  }

  @Test
  public void testIsEqualIgnoreTimeTo_Year() {
    final Calendar cal = new GregorianCalendar();
    cal.set(2004, 10, 03, 12, 4);

    this.util = new DateUtil(cal.getTime());
    cal.set(Calendar.YEAR, 1940);
    assertThat(this.util.isEqualTo(cal.getTime())).isFalse();
    assertThat(this.util.isEqualIgnoreTimeTo(cal.getTime())).isFalse();
  }
}
