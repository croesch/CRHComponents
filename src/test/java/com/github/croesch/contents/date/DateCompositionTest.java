package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.github.croesch.contents.date.DateContent.MODE;

/**
 * Provides test methods for {@link DateComposition}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateCompositionTest {

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_D_Point_M_POINT_Y() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("mk"));
    locales.add(new Locale("no", "NO", ""));
    locales.add(new Locale("mk", "MK", ""));
    locales.add(new Locale("de", "CH", ""));
    locales.add(new Locale("fi", "FI", ""));
    locales.add(new Locale("is", "", ""));
    locales.add(new Locale("cs", "", ""));
    locales.add(new Locale("sl", "SI", ""));
    locales.add(new Locale("sk", "SK", ""));
    locales.add(new Locale("tr", "TR", ""));
    locales.add(new Locale("no", "", ""));
    locales.add(new Locale("ro", "", ""));
    locales.add(new Locale("no", "NO", "NY"));
    locales.add(new Locale("et", "EE", ""));
    locales.add(new Locale("ru", "", ""));
    locales.add(new Locale("et", "", ""));
    locales.add(new Locale("fr", "CH", ""));
    locales.add(new Locale("fi", "", ""));
    locales.add(new Locale("de", "AT", ""));
    locales.add(new Locale("be", "", ""));
    locales.add(new Locale("is", "IS", ""));
    locales.add(new Locale("uk", "UA", ""));
    locales.add(new Locale("pl", "PL", ""));
    locales.add(new Locale("uk", "", ""));
    locales.add(new Locale("ru", "RU", ""));
    locales.add(new Locale("be", "BY", ""));
    locales.add(new Locale("sl", "", ""));
    locales.add(new Locale("cs", "CZ", ""));
    locales.add(new Locale("it", "CH", ""));
    locales.add(new Locale("ro", "RO", ""));
    locales.add(new Locale("de", "DE", ""));
    locales.add(new Locale("de", "LU", ""));
    locales.add(new Locale("de", "", ""));
    locales.add(new Locale("sk", "", ""));
    locales.add(new Locale("tr", "", ""));

    System.out.println("Testing D.M.Y");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyDayEditor(), new DateSepEditor("."), new DateLazyMonEditor(),
                                       new DateSepEditor("."), new DateLazyYearEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Slash_M_Slash_D() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("ja", "JP", ""));
    locales.add(new Locale("zh", "TW", ""));
    locales.add(new Locale("en", "ZA", ""));
    locales.add(new Locale("ja", "", ""));
    locales.add(new Locale("in", "", ""));
    locales.add(new Locale("ga", "", ""));
    locales.add(new Locale("ms", "", ""));

    System.out.println("Testing Y/M/D");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("/"), new DateLazyMonEditor(),
                                       new DateSepEditor("/"), new DateLazyDayEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_D_Slash_M_Slash_Y() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("es", "PE", ""));
    locales.add(new Locale("es", "GT", ""));
    locales.add(new Locale("ar", "AE", ""));
    locales.add(new Locale("ar", "IQ", ""));
    locales.add(new Locale("ar", "YE", ""));
    locales.add(new Locale("el", "CY", ""));
    locales.add(new Locale("ar", "QA", ""));
    locales.add(new Locale("en", "MT", ""));
    locales.add(new Locale("it", "", ""));
    locales.add(new Locale("ar", "SA", ""));
    locales.add(new Locale("en", "GB", ""));
    locales.add(new Locale("en", "NZ", ""));
    locales.add(new Locale("ga", "IE", ""));
    locales.add(new Locale("fr", "BE", ""));
    locales.add(new Locale("es", "ES", ""));
    locales.add(new Locale("ar", "LB", ""));
    locales.add(new Locale("ar", "KW", ""));
    locales.add(new Locale("es", "MX", ""));
    locales.add(new Locale("ar", "SD", ""));
    locales.add(new Locale("in", "ID", ""));
    locales.add(new Locale("es", "UY", ""));
    locales.add(new Locale("iw", "", ""));
    locales.add(new Locale("pt", "BR", ""));
    locales.add(new Locale("ar", "SY", ""));
    locales.add(new Locale("es", "VE", ""));
    locales.add(new Locale("ar", "BH", ""));
    locales.add(new Locale("ar", "TN", ""));
    locales.add(new Locale("es", "", ""));
    locales.add(new Locale("es", "EC", ""));
    locales.add(new Locale("ar", "JO", ""));
    locales.add(new Locale("es", "CO", ""));
    locales.add(new Locale("es", "CR", ""));
    locales.add(new Locale("ar", "EG", ""));
    locales.add(new Locale("el", "GR", ""));
    locales.add(new Locale("it", "IT", ""));
    locales.add(new Locale("ca", "", ""));
    locales.add(new Locale("fr", "", ""));
    locales.add(new Locale("en", "IE", ""));
    locales.add(new Locale("fr", "LU", ""));
    locales.add(new Locale("nl", "BE", ""));
    locales.add(new Locale("en", "IN", ""));
    locales.add(new Locale("ca", "ES", ""));
    locales.add(new Locale("ar", "MA", ""));
    locales.add(new Locale("en", "AU", ""));
    locales.add(new Locale("zh", "SG", ""));
    locales.add(new Locale("vi", "", ""));
    locales.add(new Locale("ar", "DZ", ""));
    locales.add(new Locale("vi", "VN", ""));
    locales.add(new Locale("ar", "LY", ""));
    locales.add(new Locale("ar", "", ""));
    locales.add(new Locale("iw", "IL", ""));
    locales.add(new Locale("mt", "MT", ""));
    locales.add(new Locale("es", "PY", ""));
    locales.add(new Locale("fr", "FR", ""));
    locales.add(new Locale("en", "CA", ""));
    locales.add(new Locale("es", "AR", ""));
    locales.add(new Locale("ms", "MY", ""));
    locales.add(new Locale("mt", "", ""));
    locales.add(new Locale("ar", "OM", ""));
    locales.add(new Locale("el", "", ""));

    System.out.println("Testing D/M/Y");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyDayEditor(), new DateSepEditor("/"), new DateLazyMonEditor(),
                                       new DateSepEditor("/"), new DateLazyYearEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_M_Slash_D_Slash_Y() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("en", "", ""));
    locales.add(new Locale("es", "PA", ""));
    locales.add(new Locale("en", "US", ""));
    locales.add(new Locale("th", "", ""));
    locales.add(new Locale("es", "US", ""));
    locales.add(new Locale("es", "DO", ""));
    locales.add(new Locale("en", "PH", ""));
    locales.add(new Locale("en", "SG", ""));

    System.out.println("Testing M/D/Y");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyMonEditor(), new DateSepEditor("/"), new DateLazyDayEditor(),
                                       new DateSepEditor("/"), new DateLazyYearEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Minus_M_Minus_D() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("ja", "JP", "JP"));
    locales.add(new Locale("sr", "BA", ""));
    locales.add(new Locale("sq", "AL", ""));
    locales.add(new Locale("bg", "", ""));
    locales.add(new Locale("sv", "", ""));
    locales.add(new Locale("zh", "", ""));
    locales.add(new Locale("fr", "CA", ""));
    locales.add(new Locale("sq", "", ""));
    locales.add(new Locale("zh", "CN", ""));
    locales.add(new Locale("bg", "BG", ""));
    locales.add(new Locale("pl", "", ""));
    locales.add(new Locale("th", "TH", "TH"));
    locales.add(new Locale("sv", "SE", ""));
    locales.add(new Locale("hi", "IN", ""));
    locales.add(new Locale("th", "TH", ""));

    System.out.println("Testing Y-M-D");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("-"), new DateLazyMonEditor(),
                                       new DateSepEditor("-"), new DateLazyDayEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Point_M_Point_D_Point() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("hu", "", ""));
    locales.add(new Locale("hu", "HU", ""));

    System.out.println("Testing Y.M.D.");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(6);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("."), new DateLazyMonEditor(),
                                       new DateSepEditor("."), new DateLazyDayEditor(), new DateSepEditor("."));
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_D_Minus_M_Minus_Y() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("pt", "PT", ""));
    locales.add(new Locale("nl", "", ""));
    locales.add(new Locale("nl", "NL", ""));
    locales.add(new Locale("es", "CL", ""));
    locales.add(new Locale("es", "BO", ""));
    locales.add(new Locale("pt", "", ""));
    locales.add(new Locale("da", "", ""));
    locales.add(new Locale("da", "DK", ""));

    System.out.println("Testing D-M-Y");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyDayEditor(), new DateSepEditor("-"), new DateLazyMonEditor(),
                                       new DateSepEditor("-"), new DateLazyYearEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_D_Point_M_Point_Y_Point() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("sr", "CS", ""));
    locales.add(new Locale("sr", "RS", ""));
    locales.add(new Locale("sr", "", ""));
    locales.add(new Locale("sr", "ME", ""));

    System.out.println("Testing D.M.Y.");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(6);
      assertThat(list).containsExactly(new DateLazyDayEditor(), new DateSepEditor("."), new DateLazyMonEditor(),
                                       new DateSepEditor("."), new DateLazyYearEditor(), new DateSepEditor("."));
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Point_M_Point_D() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("lt", "", ""));
    locales.add(new Locale("lt", "LT", ""));
    locales.add(new Locale("hr", "", ""));
    locales.add(new Locale("hr", "HR", ""));

    System.out.println("Testing Y.M.D");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("."), new DateLazyMonEditor(),
                                       new DateSepEditor("."), new DateLazyDayEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Point_D_Point_M() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("lv", "LV", ""));
    locales.add(new Locale("lv", "", ""));

    System.out.println("Testing Y.D.M");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("."), new DateLazyDayEditor(),
                                       new DateSepEditor("."), new DateLazyMonEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_M_Minus_D_Minus_Y() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("es", "NI", ""));
    locales.add(new Locale("es", "SV", ""));
    locales.add(new Locale("es", "PR", ""));
    locales.add(new Locale("es", "HN", ""));

    System.out.println("Testing M-D-Y");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(5);
      assertThat(list).containsExactly(new DateLazyMonEditor(), new DateSepEditor("-"), new DateLazyDayEditor(),
                                       new DateSepEditor("-"), new DateLazyYearEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_Point_Space_M_Point_Space_D() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("ko", "", ""));
    locales.add(new Locale("ko", "KR", ""));

    System.out.println("Testing Y. M. D");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(7);
      assertThat(list).containsExactly(new DateLazyYearEditor(), new DateSepEditor("."), new DateSepEditor(" "),
                                       new DateLazyMonEditor(), new DateSepEditor("."), new DateSepEditor(" "),
                                       new DateLazyDayEditor());
    }
  }

  /**
   * Test method for {@link DateComposition#getComposition(Locale, MODE)}.
   */
  @Test
  public final void testGetComposition_Y_X_M_X_D_X() {
    final List<Locale> locales = new ArrayList<Locale>();
    locales.add(new Locale("zh", "HK", ""));

    System.out.println("Testing YXMXDX");
    for (final Locale loc : locales) {
      System.out.print("\t" + loc.getDisplayName());
      System.out
        .println(" (\"" + loc.getLanguage() + "\", \"" + loc.getCountry() + "\", \"" + loc.getVariant() + "\")");

      final List<IDateLazyPartEditor> list = DateComposition.getComposition(loc, MODE.LAZY);
      assertThat(list).hasSize(6);
      assertThat(list).contains(new DateLazyYearEditor(), new DateLazyMonEditor(), new DateLazyDayEditor());
    }
  }

}
