package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link DateLazyContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateLazyContentTest {

  private DateLazyContent content;

  /**
   * Sets up the {@link DateLazyContent}.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  @Before
  public void setUp() {
    this.content = new DateLazyContent(null, Locale.GERMAN);
  }

  @Test
  public void testInsertString() throws BadLocationException {
    this.content.insertString(0, "2", null);
    this.content.insertString(1, "2", null);
    this.content.insertString(2, "1", null);
    this.content.insertString(4, "1", null);
    this.content.insertString(5, "4", null);
    this.content.insertString(7, "4", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");

    this.content.insertString(7, null, null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");

    this.content.insertString(7, "", null);
    assertThat(this.content.getText()).isEqualTo("22.11.1944");

    this.content.insertString(0, "12.10.2010", null);
    assertThat(this.content.getText()).isEqualTo("12.10.2010");
  }

}
