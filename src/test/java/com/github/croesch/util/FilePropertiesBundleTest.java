package com.github.croesch.util;

import static com.github.croesch.util.FilePropertiesBundle.getMessageFromSingleBundle;
import static com.github.croesch.util.FilePropertiesBundle.getText;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Provides test methods for {@link FilePropertiesBundle}
 * 
 * @author croesch
 * @since Date: 12.02.2011 20:19:16
 */
@SuppressWarnings("nls")
public class FilePropertiesBundleTest {

  private static final String SINGLE = "testfiles/single";

  /**
   * Sets the Locale to German
   * 
   * @author croesch
   * @since Date: 12.02.2011 20:44:47
   */
  @BeforeClass
  public static void init() {
    Locale.setDefault(new Locale("de", "DE", "var"));
  }

  /**
   * Test method for
   * {@link com.github.croesch.util.FilePropertiesBundle#getMessageFromSingleBundle(java.lang.String, java.lang.String)}
   * .
   */
  @Test
  public void testGetMessageFromSingleBundle() {
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.all"), is("value "));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.onethree"), is("value "));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.onetwo"), is("value"));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.one"), is("value"));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.twothree"), is("value "));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.three"), is("value "));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.spaces.more"), is("value"));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.tabs"), is("value\t\t"));
    assertThat(getMessageFromSingleBundle(SINGLE, "test.last.line"), is("doesn't end with \n"));
    assertThat(getMessageFromSingleBundle("invalid", "test"), CoreMatchers.nullValue());
    assertThat(getMessageFromSingleBundle(SINGLE, "test"), CoreMatchers.nullValue());
    assertThat(getMessageFromSingleBundle(null, "test"), CoreMatchers.nullValue());
    assertThat(getMessageFromSingleBundle(null, null), CoreMatchers.nullValue());
    assertThat(getMessageFromSingleBundle(SINGLE, null), CoreMatchers.nullValue());
    assertThat(getMessageFromSingleBundle("invalid", null), CoreMatchers.nullValue());
  }

  /**
   * Test method for {@link com.github.croesch.util.FilePropertiesBundle#getText(java.lang.String, java.lang.String)}.
   */
  @Test
  public void testGetText() {
    assertThat(getText(SINGLE, "test.spaces.all"), is("LEER"));
    assertThat(getText(SINGLE, "test.spaces.onethree"), is(""));
    assertThat(getText(SINGLE, "test.spaces.onetwo"), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.one"), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.twothree"), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.three"), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.more"), is("EMPTY"));
    assertThat(getText(SINGLE, "test.tabs"), is(""));
    assertThat(getText(SINGLE, "test.last.line"), is("doesn't end with \n"));
    assertThat(getText("invalid2", "test"), CoreMatchers.nullValue());
    assertThat(getText(SINGLE, "test"), CoreMatchers.nullValue());
    assertThat(getText(null, "test"), CoreMatchers.nullValue());
    assertThat(getText(null, null), CoreMatchers.nullValue());
    assertThat(getText(SINGLE, null), CoreMatchers.nullValue());
    assertThat(getText("invalid", null), CoreMatchers.nullValue());
  }

  /**
   * Test method for {@link FilePropertiesBundle#getText(String, String, Locale)}.
   */
  @Test
  public void testGetTextWithDifferentLocales() {
    assertThat(getText(SINGLE, "test.spaces.all", new Locale("de", "DE")), is("LEER"));
    assertThat(getText(SINGLE, "test.spaces.onethree", new Locale("de", "DE", "")), is(""));
    assertThat(getText(SINGLE, "test.spaces.onetwo", new Locale("de", "", "DE")), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.one", new Locale("de", "AT", "DE")), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.twothree", Locale.GERMAN), is("EMPTY"));
    assertThat(getText(SINGLE, "test.spaces.three", Locale.getDefault()), is("EMPTY"));
    assertThat(getText(SINGLE, "test.tabs", Locale.GERMANY), is(""));
    assertThat(getText(SINGLE, "test.last.line", Locale.CANADA), is("doesn't end with \n"));
    assertThat(getText(SINGLE, "test.last.line", Locale.ENGLISH), is("doesn't end with \n"));
    assertThat(getText(SINGLE, "test.last.line", new Locale("")), is("doesn't end with \n"));
    assertThat(getText(SINGLE, "test.last.line", new Locale("", "")), is("doesn't end with \n"));
    assertThat(getText(SINGLE, "test.last.line", new Locale("", "", "")), is("doesn't end with \n"));
  }

  /**
   * Test method for {@link FilePropertiesBundle#getText(String, String, Locale)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetTextWithDifferentLocales_IAE() {
    assertThat(getText(SINGLE, "test.spaces.more", null), is("EMPTY"));
  }

}
