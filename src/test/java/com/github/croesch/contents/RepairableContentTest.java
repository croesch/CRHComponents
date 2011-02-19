package com.github.croesch.contents;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

/**
 * Provides test methods for {@link RepairableContent}
 * 
 * @author croesch
 * @since Date: 10.02.2011 20:54:44
 */
@SuppressWarnings("nls")
public class RepairableContentTest {

  private RepairableContent cont;

  /**
   * Creates an instance of the {@link RepairableContent}
   * 
   * @author croesch
   * @since Date: 10.02.2011 20:54:44
   */
  @Before
  public void setUp() {
    this.cont = new RepairableContent();
  }

  /**
   * Test method for {@link RepairableContent#remove(int, int)}.
   */
  @Test
  public void testRemove() {
    try {
      this.cont.insertString(0, "Not yet implemented", null);
      this.cont.remove(0, -1);
      this.cont.remove(0, 8);
    } catch (final BadLocationException e) {
      fail();
    }
    assertThat(this.cont.getText(), is("implemented"));

  }

  /**
   * Test method for {@link RepairableContent#isValidInput(java.lang.String)}.
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testIsValidInputString() throws BadLocationException {
    assertTrue(this.cont.isValidInput(0, "alfred"));
    assertTrue(this.cont.isValidInput(0, null));
    this.cont.insertString(0, null, null);
    this.cont.setMaximumInputLength(5);
    assertTrue(this.cont.isValidInput(0, "alf"));
    this.cont.insertString(0, "alf", null);
    assertFalse(this.cont.isValidInput(0, "alf"));
    this.cont.insertString(0, "alf", null);
    this.cont.setMaximumInputLength(6);
    assertFalse(this.cont.isValidInput(0, "alf"));
    this.cont.setMaximumInputLength(9);
    assertTrue(this.cont.isValidInput(0, "alf"));
  }

  /**
   * Test method for {@link RepairableContent#isValidInput(java.lang.String)}.
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testIsValidInputString_AutoRepair() throws BadLocationException {
    this.cont.setAutoRepairContent(true);
    assertTrue(this.cont.isValidInput(0, "alfred"));
    assertTrue(this.cont.isValidInput(0, null));
    this.cont.insertString(0, null, null);
    this.cont.setMaximumInputLength(5);
    assertTrue(this.cont.isValidInput(0, "alf"));
    this.cont.insertString(0, "alf", null);
    assertTrue(this.cont.isValidInput(0, "alf"));
    this.cont.insertString(0, "alf", null);
    this.cont.setMaximumInputLength(6);
    assertTrue(this.cont.isValidInput(0, "alf"));
    this.cont.setMaximumInputLength(9);
    assertTrue(this.cont.isValidInput(0, "alf"));
  }

  /**
   * Test method for {@link RepairableContent#insertString(int, java.lang.String, javax.swing.text.AttributeSet)} .
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testInsertStringIntStringAttributeSet() throws BadLocationException {
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.getText(), is(""));
    this.cont.insertString(0, "chj", null);
    assertThat(this.cont.getText(), is("chj"));
    this.cont.insertString(0, "ab", null);
    assertThat(this.cont.getText(), is("abchj"));
    this.cont.insertString(3, "deg", null);
    assertThat(this.cont.getText(), is("abcdeghj"));
    this.cont.insertString(5, "f", null);
    assertThat(this.cont.getText(), is("abcdefghj"));
    this.cont.insertString(5, null, null);
    assertThat(this.cont.getText(), is("abcdefghj"));
    this.cont.insertString(8, "i", null);
    assertThat(this.cont.getText(), is("abcdefghij"));
    this.cont.insertString(10, "k", null);
    assertThat(this.cont.getText(), is("abcdefghijk"));
  }

  /**
   * Test method for {@link RepairableContent#insertString(int, String, javax.swing.text.AttributeSet)} .
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testInsertStringIntStringAttributeSet_AutoRepair() throws BadLocationException {
    this.cont.setAutoRepairContent(true);
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.getText(), is(""));
    this.cont.insertString(0, "chj", null);
    assertThat(this.cont.getText(), is("chj"));
    this.cont.insertString(0, "ab", null);
    assertThat(this.cont.getText(), is("abchj"));
    this.cont.insertString(3, "deg", null);
    assertThat(this.cont.getText(), is("abcde"));
    this.cont.insertString(5, "f", null);
    assertThat(this.cont.getText(), is("abcde"));
    this.cont.insertString(5, null, null);
    assertThat(this.cont.getText(), is("abcde"));
    this.cont.setMaximumInputLength(0);
    this.cont.insertString(5, "fghij", null);
    assertThat(this.cont.getText(), is("abcdefghij"));
    this.cont.insertString(10, "k", null);
    assertThat(this.cont.getText(), is("abcdefghijk"));
  }

  /**
   * Tests that {@link RepairableContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test
  public void testInsertStringIntStringAttributeSet_BLE1() {
    try {
      this.cont.insertString(1, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Tests that {@link RepairableContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test
  public void testInsertStringIntStringAttributeSet_BLE2() {
    try {
      this.cont.insertString(-1, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Test method for {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} .
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testReplaceIntIntStringAttributeSet() throws BadLocationException {
    this.cont.setMaximumInputLength(5);
    this.cont.insertString(0, "hotdog", null);
    this.cont.replace(3, 3, "cat", null);
    assertThat(this.cont.getText(), is("hotcat"));
    this.cont.replace(0, 3, "cold", null);
    assertThat(this.cont.getText(), is("coldcat"));
    this.cont.replace(3, 2, null, null);
    assertThat(this.cont.getText(), is("colat"));
    this.cont.replace(4, 1, "", null);
    assertThat(this.cont.getText(), is("cola"));
    this.cont.replace(0, 1, "To", null);
    assertThat(this.cont.getText(), is("Toola"));
    this.cont.replace(3, 2, "l", null);
    assertThat(this.cont.getText(), is("Tool"));
  }

  /**
   * Test method for {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} .
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testReplaceIntIntStringAttributeSet_AutoRepair() throws BadLocationException {
    this.cont.setAutoRepairContent(true);
    this.cont.setMaximumInputLength(5);
    this.cont.insertString(0, "hotdog", null);
    this.cont.replace(3, 2, "cat", null);
    assertThat(this.cont.getText(), is("hotca"));
    this.cont.replace(0, 3, "cold", null);
    assertThat(this.cont.getText(), is("coldc"));
    this.cont.replace(3, 2, null, null);
    assertThat(this.cont.getText(), is("col"));
    this.cont.replace(1, 0, "", null);
    assertThat(this.cont.getText(), is("col"));
    this.cont.replace(1, 0, "o", null);
    assertThat(this.cont.getText(), is("cool"));
    this.cont.replace(0, 1, "T", null);
    assertThat(this.cont.getText(), is("Tool"));
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test
  public void testReplaceIntIntStringAttributeSet_BLE1() {
    try {
      this.cont.replace(-1, 0, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test
  public void testReplaceIntIntStringAttributeSet_BLE2() {
    try {
      this.cont.replace(1, 0, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test
  public void testReplaceIntIntStringAttributeSet_BLE3() {
    try {
      this.cont.replace(0, 1, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Test method for {@link RepairableContent#repair()}.
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testRepair() throws BadLocationException {
    this.cont.setMaximumInputLength(5);
    this.cont.insertString(0, "string", null);
    assertThat(this.cont.getText(), is("string"));
    this.cont.repair();
    assertThat(this.cont.getText(), is("strin"));
    this.cont.repair();
    assertThat(this.cont.getText(), is("strin"));
    this.cont.remove(3, 2);
    assertThat(this.cont.getText(), is("str"));
    this.cont.repair();
    assertThat(this.cont.getText(), is("str"));
  }

  /**
   * Test method for {@link RepairableContent#isAutoRepairContent()} and
   * {@link RepairableContent#setAutoRepairContent(boolean)}.
   */
  @Test
  public void testSetIsAutoRepairContent() {
    assertFalse(this.cont.isAutoRepairContent());
    this.cont.setAutoRepairContent(false);
    assertFalse(this.cont.isAutoRepairContent());
    this.cont.setAutoRepairContent(true);
    assertTrue(this.cont.isAutoRepairContent());
    this.cont.setAutoRepairContent(true);
    assertTrue(this.cont.isAutoRepairContent());
    this.cont.setAutoRepairContent(false);
    assertFalse(this.cont.isAutoRepairContent());
  }

  private final static void assertTrue(final boolean expect) {
    assertThat(Boolean.valueOf(expect), is(Boolean.TRUE));
  }

  private final static void assertFalse(final boolean expect) {
    assertThat(Boolean.valueOf(expect), is(Boolean.FALSE));
  }
}
