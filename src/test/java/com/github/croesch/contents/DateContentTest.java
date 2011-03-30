package com.github.croesch.contents;

import static org.fest.assertions.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods of the {@link DateContent}-class.
 * 
 * @author croesch
 * @since Date: 27.01.2011 18:38:22
 */
@SuppressWarnings("nls")
public class DateContentTest {

  private DateContent dc = new DateContent();

  /**
   * Constructs a new {@link DateContent}
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:45:21
   */
  @Before
  public void setUp() {
    this.dc = new DateContent();
  }

  private void assertThatIsValid() {
    assertThat(this.dc.isValid()).isTrue();
  }

  private void assertThatIsNotValid() {
    assertThat(this.dc.isValid()).isFalse();
  }

  /**
   * Tests the default values of the standard constructor
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:44:42
   * @throws BadLocationException if bad location is wanted
   */
  @Test
  public void testDateCreation() throws BadLocationException {
    assertThatIsValid();
    assertThat(this.dc.isValidInput("2011-01-27")).isTrue();
    assertThat(this.dc.isValidInput(0, "2011-01-27")).isTrue();
    assertThat(this.dc.isErrorsNotifying()).isFalse();

    this.dc.setErrosNotifying(true);
    assertThat(this.dc.isErrorsNotifying()).isTrue();
  }

  /**
   * Test method for {@link DateContent#formatDate(int, int, int)} and {@link DateContent#setDateFormat(String)}
   */
  @Test
  public void testFormatDate() {
    final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    assertThat(this.dc.getDate()).isEqualTo(date);
    assertThat(this.dc.formatDate(1903, 02, 1)).isEqualTo("1903-02-01");
    this.dc.setDateFormat(null);
    assertThat(this.dc.getDate()).isEqualTo(date);
    assertThat(this.dc.formatDate(1903, 2, 01)).isEqualTo("1903-02-01");
    this.dc.setDateFormat("");
    assertThat(this.dc.getDate()).isEmpty();
    assertThat(this.dc.formatDate(1903, 2, 1)).isEqualTo("");
    this.dc.setDateFormat("%3$02d.%2$02d.%1$04d");
    assertThat(this.dc.getDate()).isEqualTo(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    assertThat(this.dc.formatDate(1904, 3, 2)).isEqualTo("02.03.1904");
  }

  /**
   * Tests the default input length of the document
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:44:01
   */
  @Test
  public void testInputLength() {
    assertThat(this.dc.isValidInput("2011-01-27")).isTrue();
    assertThat(this.dc.isValidInput("2011-01-271")).isFalse();
    assertThat(this.dc.isValidInput("2011-01-2712")).isFalse();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
  }

  /**
   * Tests the {@link DateContent#DateContent(String)}
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:43:10
   */
  @Test
  public void testStringConstructor() {
    final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    assertThat(this.dc.getDate()).isEqualTo(date);

    this.dc = new DateContent("2005-02-26");

    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo("2005-02-26");

    this.dc = new DateContent("2005-02-26-34");

    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo(date);

    this.dc = new DateContent(null);

    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo(date);

    this.dc = new DateContent("2005" + date.substring(4));

    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo("2005" + date.substring(4));
  }

  /**
   * Tests the value if only year is entered
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:42:55
   */
  @Test
  public void testYearDate() {
    final String date = new SimpleDateFormat("-MM-dd").format(new Date());

    this.dc = new DateContent("2005");
    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo("2005" + date);

  }

  /**
   * Tests the value if only year and month is given
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:42:37
   */
  @Test
  public void testYearMonthDate() {
    final String date = new SimpleDateFormat("-dd").format(new Date());

    this.dc = new DateContent("2005-08");
    assertThatIsValid();
    assertThat(this.dc.getMaximumInputLength()).isEqualTo(10);
    assertThat(this.dc.isErrorsNotifying()).isFalse();
    assertThat(this.dc.getDate()).isEqualTo("2005-08" + date);
  }

  /**
   * Tests inserting values into the document
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:42:15
   * @throws BadLocationException if a bad location is wanted
   */
  @Test
  public void testInsertString() throws BadLocationException {
    final String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    final String monthDay = new SimpleDateFormat("-MM-dd").format(new Date());
    final String day = new SimpleDateFormat("-dd").format(new Date());

    assertThat(this.dc.isValidInput(0, "2003")).isTrue();
    this.dc.insertString(0, "2003", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2003" + monthDay);

    assertThat(this.dc.isValidInput(2, "05-")).isTrue();
    this.dc.insertString(2, "05-", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2005-03" + day);

    assertThat(this.dc.isValidInput(7, "--9")).isTrue();
    this.dc.insertString(7, "--9", null);
    assertThatIsNotValid();
    assertThat(this.dc.getDate()).isEqualTo("2005-03--9");

    assertThat(this.dc.isValidInput(4, "-07")).isFalse();
    this.dc.insertString(4, "-07", null);
    assertThatIsNotValid();
    assertThat(this.dc.getDate()).isEqualTo("2005-03--9");

    this.dc.remove(4, 6);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2005" + monthDay);

    assertThat(this.dc.isValidInput(4, "-07")).isTrue();
    assertThat(this.dc.isValidInput(4, "abc")).isFalse();
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2005" + monthDay);

    assertThat(this.dc.isValidInput(4, "d")).isTrue();
    this.dc.insertString(3, "d", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo(today);
  }

  /**
   * Tests whether a special character is contained by {@link DateContent#isValidSpecialInput(String)}
   */
  @Test
  public void testIsValidSpecialInput() {
    final String[] wrongInputs = { "a", "b", "c", "z", null, "", "dd" };
    final String[] validInputs = { "d" };

    for (final String s : wrongInputs) {
      assertThat(this.dc.isValidSpecialInput(s)).isFalse();
    }
    for (final String s : validInputs) {
      assertThat(this.dc.isValidSpecialInput(s)).isTrue();
    }
  }

  /**
   * Tests removing values from document
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:41:53
   * @throws BadLocationException if a bad location is wanted
   */
  @Test
  public void testRemove() throws BadLocationException {
    final String monthDay = new SimpleDateFormat("-MM-dd").format(new Date());
    final String day = new SimpleDateFormat("-dd").format(new Date());

    assertThat(this.dc.isValidInput(0, "2003-05-17")).isTrue();
    this.dc.insertString(0, "2003-05-17", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2003-05-17");

    this.dc.remove(7, 3);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2003-05" + day);

    this.dc.remove(2, 3);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2005" + monthDay);
  }

  /**
   * Test replacing values in the document
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:41:24
   * @throws BadLocationException if a bad location is wanted
   */
  @Test
  public void testReplace() throws BadLocationException {
    final String monthDay = new SimpleDateFormat("-MM-dd").format(new Date());
    final String day = new SimpleDateFormat("-dd").format(new Date());

    assertThat(this.dc.isValidInput(0, "2003-05-17")).isTrue();
    this.dc.insertString(0, "2003-05-17", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2003-05-17");

    this.dc.replace(0, 4, "2011", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2011-05-17");

    this.dc.replace(2, 2, "20-11", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2011-05-17");

    this.dc.replace(2, 7, "0", null);
    assertThatIsValid();
    assertThat(this.dc.getDate()).isEqualTo("2007" + monthDay);

    this.dc.replace(4, 0, "-18", null);
    assertThatIsNotValid();
    assertThat(this.dc.getDate()).isEqualTo("2007-18" + day);
  }
}
