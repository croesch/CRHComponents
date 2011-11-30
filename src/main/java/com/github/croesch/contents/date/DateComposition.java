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
    throw new AssertionError("Invocation of utility class constructor.");
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
   * @param loc the locale to fetch the {@link IDateLazyPartEditor}s from
   * @param mode the {@link DateContent.MODE} for the editors
   * @param day the initial day value to pass to the editor
   * @param month the initial month value to pass to the editor
   * @param year the initial year value to pass to the editor
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date of the given locale, if the date
   *         of the locale cannot be parsed correctly then it returns a list of editors that are able to edit YYYY-MM-DD
   *         date.
   */
  static List<IDateLazyPartEditor> getComposition(final Locale loc,
                                                  final DateContent.MODE mode,
                                                  final int day,
                                                  final int month,
                                                  final int year) {

    final int testYear = 3333;
    final int testMonth = 11;
    final int testDay = 22;

    final String formatted = constructFormattedTestDate(loc, testYear, testMonth, testDay);

    // TODO #13 joda-time
    if (!formatted.contains("22") || !formatted.contains("11") || !formatted.contains("33")) {
      return createDefaultEditorList(day, month, year);
    }

    return getEditorListOfFormattedDate(new ArrayList<IDateLazyPartEditor>(), formatted, day, month, year);
  }

  /**
   * Constructs the default list of editors for a date. The editors are some to edit ISO-formatted dates.
   * 
   * @since Date: Nov 30, 2011
   * @param day the initial value of the day
   * @param month the initial value of the month
   * @param year the initial value of the year
   * @return the {@link List} that contains the editors to edit ISO-formatted dates.
   */
  private static List<IDateLazyPartEditor> createDefaultEditorList(final int day, final int month, final int year) {
    final List<IDateLazyPartEditor> list = new ArrayList<IDateLazyPartEditor>();

    list.add(new DateLazyYearEditor(year));
    list.add(new DateSepEditor("-"));
    list.add(new DateLazyMonEditor(month));
    list.add(new DateSepEditor("-"));
    list.add(new DateLazyDayEditor(day));

    return list;
  }

  /**
   * Uses the given {@link Locale} to format a test date to identify the different parts of the date and their order.
   * 
   * @since Date: Nov 30, 2011
   * @param loc the {@link Locale} to use for construction
   * @param year the year that the formatted date should have.
   * @param month the month the formatted date should have (1=January)
   * @param day the day the formatted date should have
   * @return the date formatted in the way of the {@link Locale}
   */
  private static String constructFormattedTestDate(final Locale loc, final int year, final int month, final int day) {

    final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, loc);
    final Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, day);

    return df.format(cal.getTime());
  }

  /**
   * Recursively creates a list of editors that are able to edit the given formatted date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param list the {@link List} of editors previously converted
   * @param formatted the {@link String} that contains the formatted date (day=22, month=11, year=3333)
   * @param day the initial day to pass to the editor
   * @param month the initial month to pass to the editor
   * @param year the initial year to pass to the editor
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date as the given formatted one.
   */
  private static List<IDateLazyPartEditor> getEditorListOfFormattedDate(final List<IDateLazyPartEditor> list,
                                                                        final String formatted,
                                                                        final int day,
                                                                        final int month,
                                                                        final int year) {
    if (formatted == null || formatted.length() == 0) {
      // stop condition, we have no more text to convert into editors
      return list;
    }

    // contains the string for the following iteration
    String nextFormatted;
    if (formatted.startsWith("2")) {
      // the current part to convert into an editor is the day part
      list.add(new DateLazyDayEditor(day));
      // remove day information from the string for following iterations
      nextFormatted = formatted.replaceAll("2", "");
    } else if (formatted.startsWith("1")) {
      // the current part to convert into an editor is the month part
      list.add(new DateLazyMonEditor(month));
      // remove month information from the string for following iterations
      nextFormatted = formatted.replaceAll("1", "");
    } else if (formatted.startsWith("3")) {
      // the current part to convert into an editor is the year part
      list.add(new DateLazyYearEditor(year));
      // remove year information from the string for following iterations
      nextFormatted = formatted.replaceAll("3", "");
    } else {
      // the current part to convert into an editor is a separating part
      list.add(new DateSepEditor(formatted.substring(0, 1)));
      // remove the character converted into an editor from the string for following iterations
      nextFormatted = formatted.substring(1);
    }
    // recursively convert the rest of the string and return the full generated list
    return getEditorListOfFormattedDate(list, nextFormatted, day, month, year);
  }
}
