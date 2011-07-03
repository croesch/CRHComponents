package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link DateContent}.
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class DateContentTest {

  private DateContent content;

  /**
   * Sets up the {@link DateContent}.
   * 
   * @author croesch
   * @since Date: Jul 3, 2011
   */
  @Before
  public void setUp() {
    this.content = new DateContent(null, Locale.GERMAN);
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
  }

}
