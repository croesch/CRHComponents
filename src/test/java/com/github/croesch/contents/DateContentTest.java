package com.github.croesch.contents;

import static org.hamcrest.CoreMatchers.is;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.BadLocationException;

import org.junit.Assert;
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
    Assert.assertThat(Boolean.valueOf(this.dc.isValid()), is(Boolean.TRUE));
  }

  private void assertThatIsNotValid() {
    Assert.assertThat(Boolean.valueOf(this.dc.isValid()), is(Boolean.FALSE));
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
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput("2011-01-27")), is(Boolean.TRUE));
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(0, "2011-01-27")), is(Boolean.TRUE));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));

    this.dc.setErrosNotifying(true);
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.TRUE));
  }

  /**
   * Tests the default input length of the document
   * 
   * @author croesch
   * @since Date: 28.01.2011 20:44:01
   */
  @Test
  public void testInputLength() {
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput("2011-01-27")), is(Boolean.TRUE));
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput("2011-01-271")), is(Boolean.FALSE));
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput("2011-01-2712")), is(Boolean.FALSE));
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
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
    Assert.assertThat(this.dc.getDate(), is(date));

    this.dc = new DateContent("2005-02-26");

    assertThatIsValid();
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is("2005-02-26"));

    this.dc = new DateContent("2005-02-26-34");

    assertThatIsValid();
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is(date));

    this.dc = new DateContent(null);

    assertThatIsValid();
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is(date));

    this.dc = new DateContent("2005" + date.substring(4));

    assertThatIsValid();
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is("2005" + date.substring(4)));
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
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is("2005" + date));

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
    Assert.assertThat(Integer.valueOf(this.dc.getMaximumInputLength()), is(Integer.valueOf(10)));
    Assert.assertThat(Boolean.valueOf(this.dc.isErrorsNotifying()), is(Boolean.FALSE));
    Assert.assertThat(this.dc.getDate(), is("2005-08" + date));
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

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(0, "2003")), is(Boolean.TRUE));
    this.dc.insertString(0, "2003", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2003" + monthDay));

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(2, "05-")), is(Boolean.TRUE));
    this.dc.insertString(2, "05-", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005-03" + day));

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(7, "-19")), is(Boolean.TRUE));
    this.dc.insertString(7, "-19", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005-03-19"));

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(4, "-07")), is(Boolean.FALSE));
    this.dc.insertString(4, "-07", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005-03-19"));

    this.dc.remove(4, 6);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005" + monthDay));

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(4, "-07")), is(Boolean.TRUE));
    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(4, "abc")), is(Boolean.FALSE));
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005" + monthDay));

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(4, "d")), is(Boolean.TRUE));
    this.dc.insertString(3, "d", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is(today));
  }

  /**
   * Tests whether a special character is contained by {@link DateContent#isValidSpecialInput(String)}
   */
  @Test
  public void testIsValidSpecialInput() {
    final String[] wrongInputs = { "a", "b", "c", "z", null, "", "dd" };
    final String[] validInputs = { "d" };

    for (final String s : wrongInputs) {
      Assert.assertThat(Boolean.valueOf(this.dc.isValidSpecialInput(s)), is(Boolean.FALSE));
    }
    for (final String s : validInputs) {
      Assert.assertThat(Boolean.valueOf(this.dc.isValidSpecialInput(s)), is(Boolean.TRUE));
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

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(0, "2003-05-17")), is(Boolean.TRUE));
    this.dc.insertString(0, "2003-05-17", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2003-05-17"));

    this.dc.remove(7, 3);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2003-05" + day));

    this.dc.remove(2, 3);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2005" + monthDay));
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

    Assert.assertThat(Boolean.valueOf(this.dc.isValidInput(0, "2003-05-17")), is(Boolean.valueOf(true)));
    this.dc.insertString(0, "2003-05-17", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2003-05-17"));

    this.dc.replace(0, 4, "2011", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2011-05-17"));

    this.dc.replace(2, 2, "20-11", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2011-05-17"));

    this.dc.replace(2, 7, "0", null);
    assertThatIsValid();
    Assert.assertThat(this.dc.getDate(), is("2007" + monthDay));

    this.dc.replace(4, 0, "-18", null);
    assertThatIsNotValid();
    Assert.assertThat(this.dc.getDate(), is("2007-18" + day));
  }
}
