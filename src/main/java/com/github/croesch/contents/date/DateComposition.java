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
    // not needed
  }

  /**
   * Returns a list of editors that can edit a date from the given locale.
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

    final int testYear = 3333;
    final int testMonth = 10;
    final int testDay = 22;

    final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, l);
    final Calendar cal = Calendar.getInstance();
    cal.set(testYear, testMonth, testDay);
    final String formated = df.format(cal.getTime());

    if (!formated.contains("22") || !formated.contains("11") || !formated.contains("33")) {
      final List<IDateLazyPartEditor> list = new ArrayList<IDateLazyPartEditor>();
      list.add(new DateLazyYearEditor());
      list.add(new DateSepEditor("-"));
      list.add(new DateLazyMonEditor());
      list.add(new DateSepEditor("-"));
      list.add(new DateLazyDayEditor());
      return list;
    }

    return getEditorListOfFormatedDate(formated);
  }

  /**
   * Recursively creates a list of editors that are able to edit the given formated date.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   * @param formated the {@link String} that contains the formated date
   * @return a {@link List} of {@link IDateLazyPartEditor} that are able to edit a date as the given formated one.
   */
  private static List<IDateLazyPartEditor> getEditorListOfFormatedDate(final String formated) {
    final List<IDateLazyPartEditor> list = new ArrayList<IDateLazyPartEditor>();
    if (formated == null || formated.length() == 0) {
      return list;
    }
    if (formated.startsWith("2")) {
      list.add(new DateLazyDayEditor());
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("2", "")));
    } else if (formated.startsWith("1")) {
      list.add(new DateLazyMonEditor());
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("1", "")));
    } else if (formated.startsWith("3")) {
      list.add(new DateLazyYearEditor());
      list.addAll(getEditorListOfFormatedDate(formated.replaceAll("3", "")));
    } else {
      list.add(new DateSepEditor(formated.substring(0, 1)));
      list.addAll(getEditorListOfFormatedDate(formated.substring(1)));
    }
    return list;
  }
}
