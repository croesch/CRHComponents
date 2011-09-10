package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test cases for:<br>
 * <li>{@link StringArrayUtil#toCentreAlignedArray()}</li> <li>{@link StringArrayUtil#toLeftAlignedArray()}</li> <li>
 * {@link StringArrayUtil#toRightAlignedArray()}</li>
 * 
 * @author croesch
 * @since Date: 15.02.2011 20:31:01
 */
public class StringArrayUtilAlignTest extends FestSwingJUnitTestCase {

  /**
   * Tests the behaviour of left alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignLeft() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$ " };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph " };
    expected = new String[] { "Alph$a", "Alph  " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { "Alph ", "Alpha" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph ", "Alpha", "   ", ".  " };
    expected = new String[] { "Alph ", "Alpha", "     ", ".    " };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "A  ", "b ", "c  ", "delta" };
    expected = new String[] { "A    ", "b    ", "c    ", "delta" };
    assertThat(Util.of(text).toLeftAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of right alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignRight() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "\tAlph$" };
    expected = new String[] { "Alph$a", "\tAlph$" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "\nAlph" };
    expected = new String[] { "Alph$a", " \nAlph" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "  ", "\t\n." };
    expected = new String[] { " Alph", "Alpha", "     ", "  \t\n." };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  A", "    b", "   c", "delta" };
    expected = new String[] { "    A", "    b", "    c", "delta" };
    assertThat(Util.of(text).toRightAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of centre alignment without trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignCentre() {
    String[] text = { "Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph$" };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a", "Alph " };
    expected = new String[] { "Alph$a", " Alph " };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "", "." };
    expected = new String[] { " Alph", "Alpha", "     ", "  .  " };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "A ", " b ", " c  ", "delta" };
    expected = new String[] { "  A  ", "  b  ", "  c  ", "delta" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "    A", "b", "c", "delta", "länger" };
    expected = new String[] { "     A", "   b  ", "   c  ", " delta", "länger" };
    assertThat(Util.of(text).toCentreAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of left alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignLeft_Trim() {
    String[] text = { " \t\t Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a   ", "   Alph$a" };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "   Alph$a", "Alph$   " };
    expected = new String[] { "Alph$a", "Alph$ " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "              Alph $a  ", "Alph  " };
    expected = new String[] { "Alph $a", "Alph   " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "     Alpha     " };
    expected = new String[] { "Alph ", "Alpha" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha", "                                       ", "." };
    expected = new String[] { "Alph ", "Alpha", "     ", ".    " };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);

    text = new String[] { "A ", " b ", "                               c ", "  delta " };
    expected = new String[] { "A    ", "b    ", "c    ", "delta" };
    assertThat(Util.of(text).trim().toLeftAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of right alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignRight_Trim() {
    String[] text = { "   Alpha   " };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  Alph$a  ", "  Alph$a  " };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { " Alp h$a", " Alph $ " };
    expected = new String[] { "Alp h$a", " Alph $" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a                   ", "\t\t\t\t\tAlph   " };
    expected = new String[] { "Alph$a", "  Alph" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "                  Alph", "Alpha " };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { "  Alph   ", "    Alpha", "  \t\t\t\t\t\t                 ", "." };
    expected = new String[] { " Alph", "Alpha", "     ", "    ." };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);

    text = new String[] { " A ", "b", "c", "delta" };
    expected = new String[] { "    A", "    b", "    c", "delta" };
    assertThat(Util.of(text).trim().toRightAlignedArray()).isEqualTo(expected);
  }

  /**
   * Tests the behaviour of centre alignment with trimming the lines.
   * 
   * @author croesch
   * @since Date: 16.02.2011 21:57:26
   */
  @Test
  public void testAlignCentre_Trim() {
    String[] text = { "    Alpha" };
    String[] expected = { "Alpha" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a    ", "   Alph$a " };
    expected = new String[] { "Alph$a", "Alph$a" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a   ", "Alph$   " };
    expected = new String[] { "Alph$a", " Alph$" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph$a ", " Alph" };
    expected = new String[] { "Alph$a", " Alph " };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph", "Alpha" };
    expected = new String[] { " Alph", "Alpha" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "Alph   ", "Alpha   ", "", ". " };
    expected = new String[] { " Alph", "Alpha", "     ", "  .  " };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "A", "b", "c    ", "delta" };
    expected = new String[] { "  A  ", "  b  ", "  c  ", "delta" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);

    text = new String[] { "     A", "             b ", "c", "delta   ", "länger" };
    expected = new String[] { "   A  ", "   b  ", "   c  ", " delta", "länger" };
    assertThat(Util.of(text).trim().toCentreAlignedArray()).isEqualTo(expected);
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
