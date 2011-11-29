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
package com.github.croesch.contents.date;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A utility class that is able to create the editors for a date of a specific locale.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
final class DateComposition {

  /**
   * Hide constructor of utility class.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  private DateComposition() {
    // TODO #10
    // not needed
  }

  /**
   * Returns a list of editors that can edit a date from the given locale, the initial value will be the current date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param l the locale to fetch the {@link IDateLazyPartEditor}s from
   * @param mode the {@link DateContent.MODE} for the editors
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date of the given locale, if the date
   *         of the locale cannot be parsed correctly then it returns a list of editors that are able to edit YYYY-MM-DD
   *         date.
   */
  static List<IDateLazyPartEditor> getComposition(final Locale l, final DateContent.MODE mode) {
    return getComposition(l, mode, -1, -1, -1);
  }

  /**
   * Returns a list of editors that can edit a date from the given locale, the initial value is read from the
   * parameters.
   * 
   * @author croesch
   * @since Date: Jul 5, 2011
   * @param l the locale to fetch the {@link IDateLazyPartEditor}s from TODO l is no good name (#7 general)
   * @param mode the {@link DateContent.MODE} for the editors
   * @param day the initial day value to pass to the editor
   * @param month the initial month value to pass to the editor
   * @param year the initial year value to pass to the editor
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date of the given locale, if the date
   *         of the locale cannot be parsed correctly then it returns a list of editors that are able to edit YYYY-MM-DD
   *         date.
   */
  static List<IDateLazyPartEditor> getComposition(final Locale l,
                                                  final DateContent.MODE mode,
                                                  final int day,
                                                  final int month,
                                                  final int year) {

    final int testYear = 3333;
    final int testMonth = 10;
    final int testDay = 22;

    // TODO #12 in own method
    final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, l);
    final Calendar cal = Calendar.getInstance();
    cal.set(testYear, testMonth, testDay);
    final String formated = df.format(cal.getTime());

    // TODO #13 joda-time
    if (!formated.contains("22") || !formated.contains("11") || !formated.contains("33")) {
      // TODO #12 own method
      final List<IDateLazyPartEditor> list = new ArrayList<IDateLazyPartEditor>();
      list.add(new DateLazyYearEditor(year));
      list.add(new DateSepEditor("-"));
      list.add(new DateLazyMonEditor(month));
      list.add(new DateSepEditor("-"));
      list.add(new DateLazyDayEditor(day));
      return list;
    }

    return getEditorListOfFormatedDate(formated, day, month, year);
  }

  /**
   * Recursively creates a list of editors that are able to edit the given formated date. <br>
   * TODO #7 formatted <br>
   * TODO #9 improve comments!
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param formated the {@link String} that contains the formated date
   * @param day the initial day to pass to the editor
   * @param month the initial month to pass to the editor
   * @param year the initial year to pass to the editor
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date as the given formated one.
   */
  private static List<IDateLazyPartEditor> getEditorListOfFormatedDate(final String formated,
                                                                       final int day,
                                                                       final int month,
                                                                       final int year) {
    final List<IDateLazyPartEditor> list = new ArrayList<IDateLazyPartEditor>();
    if (formated == null || formated.length() == 0) {
      return list;
    }
    if (formated.startsWith("2")) {
      list.add(new DateLazyDayEditor(day));
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("2", ""), day, month, year));
    } else if (formated.startsWith("1")) {
      list.add(new DateLazyMonEditor(month));
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("1", ""), day, month, year));
    } else if (formated.startsWith("3")) {
      list.add(new DateLazyYearEditor(year));
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("3", ""), day, month, year));
    } else {
      list.add(new DateSepEditor(formated.substring(0, 1)));
      list.addAll(getEditorListOfFormatedDate(formated.substring(1), day, month, year));
    }
    return list;
  }
}
