package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link DateSepEditor}.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
public class DateSepEditorTest {

  private DateSepEditor editor;

  /**
   * Initialises the editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   */
  @Before
  public void setUp() {
    this.editor = new DateSepEditor(".");
    final Random r = new Random();
    this.editor.setCurrentValue(r.nextInt(), r.nextInt(), r.nextInt());
  }

  /**
   * Test method for {@link DateSepEditor#getSize()}.
   */
  @Test
  public final void testGetSize() {
    assertThat(this.editor.getSize()).isEqualTo(1);
    this.editor = new DateSepEditor(".");
    assertThat(this.editor.getSize()).isEqualTo(1);
    this.editor = new DateSepEditor(" .");
    assertThat(this.editor.getSize()).isEqualTo(2);
    this.editor = new DateSepEditor("-");
    assertThat(this.editor.getSize()).isEqualTo(1);
  }

  /**
   * Test method for {@link DateSepEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_First() {
    assertThat(this.editor.enterValue("0", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("1", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("b", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue(".", 0)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    this.editor = new DateSepEditor(" .");

    assertThat(this.editor.enterValue(".", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(" .");

    assertThat(this.editor.enterValue(" ", 0)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(" .");
  }

  /**
   * Test method for {@link DateSepEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Second() {
    assertThat(this.editor.enterValue("0", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("1", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("2", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("a", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue("b", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue(".", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    this.editor = new DateSepEditor(" .");

    assertThat(this.editor.enterValue(".", 1)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(" .");

    assertThat(this.editor.enterValue(" ", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(" .");
  }

  /**
   * Test method for {@link DateSepEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Invalid() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue(".", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 10)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".", 11)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_MultiCharacter() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue("01", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("01", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("02", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("03", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("04", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("05", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("06", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("07", 7)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0.", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".0", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue(".a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0.", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_Nothing() {
    assertThat(this.editor.enterValue("", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("", 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(" ", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue(" ", 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\t", 7)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, -1)).isEqualTo(-1);
    assertThat(this.editor.enterValue(null, 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue("\n", 3)).isEqualTo(-1);
  }

  /**
   * Test method for {@link DateSepEditor#getValue()}
   */
  @Test
  public final void testGetValue() {
    assertThat(this.editor.getValue()).isEqualTo(".");
    this.editor = new DateSepEditor(".");
    assertThat(this.editor.getValue()).isEqualTo(".");
    this.editor = new DateSepEditor(" .");
    assertThat(this.editor.getValue()).isEqualTo(" .");
    this.editor = new DateSepEditor("-");
    assertThat(this.editor.getValue()).isEqualTo("-");
  }

  /**
   * Test method for {@link DateSepEditor#toString()}
   */
  @Test
  public final void testToString() {
    assertThat(this.editor.getValue()).isEqualTo(".");
    assertThat(this.editor.toString()).isEqualTo(".");
    this.editor = new DateSepEditor(".");
    assertThat(this.editor.getValue()).isEqualTo(".");
    assertThat(this.editor.toString()).isEqualTo(".");
    this.editor = new DateSepEditor(" .");
    assertThat(this.editor.getValue()).isEqualTo(" .");
    assertThat(this.editor.toString()).isEqualTo(" .");
    this.editor = new DateSepEditor("-");
    assertThat(this.editor.getValue()).isEqualTo("-");
    assertThat(this.editor.toString()).isEqualTo("-");
  }

  /**
   * Test method for {@link DateSepEditor#equals(Object)}.
   */
  @Test
  public final void testEquals() {
    assertThat(new DateSepEditor("xyz")).isEqualTo(new DateSepEditor("xyz"));
    assertThat(new DateSepEditor("xyz").hashCode()).isEqualTo(new DateSepEditor("xyz").hashCode());

    assertThat(this.editor).isEqualTo(this.editor);
    assertThat(this.editor.hashCode()).isEqualTo(this.editor.hashCode());

    assertThat(this.editor).isNotEqualTo(null);

    assertThat(new DateSepEditor("xyz")).isNotEqualTo("xyz");

    assertThat(new DateSepEditor("")).isEqualTo(new DateSepEditor(""));
    assertThat(new DateSepEditor("").hashCode()).isEqualTo(new DateSepEditor("").hashCode());
  }
}
