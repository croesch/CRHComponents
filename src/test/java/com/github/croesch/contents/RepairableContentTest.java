/*
 * Copyright (C) 2011  Christian Roesch
 * 
 * This file is part of crhcomponents.
 * 
 * crhcomponents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * crhcomponents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with crhcomponents.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.croesch.contents;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.text.BadLocationException;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides test methods for {@link RepairableContent}
 * 
 * @author croesch
 * @since Date: Feb 10, 2011
 */
@SuppressWarnings("nls")
public class RepairableContentTest extends FestSwingJUnitTestCase {

  private RepairableContent cont;

  /**
   * Creates an instance of the {@link RepairableContent}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Override
  public void onSetUp() {
    this.cont = new RepairableContent();
  }

  /**
   * Test method for {@link RepairableContent#remove(int, int)}.
   */
  @Test
  public void testRemove() throws BadLocationException {
    this.cont.insertString(0, "Not yet implemented", null);
    this.cont.remove(0, -1);
    this.cont.remove(0, 8);
    assertThat(this.cont.getText()).isEqualTo("implemented");
  }

  /**
   * Test method for {@link RepairableContent#isValidInput(java.lang.String)}.
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testIsValidInputString() throws BadLocationException {
    assertThat(this.cont.isValidInput(0, "alfred")).isTrue();
    assertThat(this.cont.isValidInput(0, null)).isTrue();
    this.cont.insertString(0, null, null);
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
    this.cont.insertString(0, "alf", null);
    assertThat(this.cont.isValidInput(0, "alf")).isFalse();
    this.cont.insertString(0, "alf", null);
    this.cont.setMaximumInputLength(6);
    assertThat(this.cont.isValidInput(0, "alf")).isFalse();
    this.cont.setMaximumInputLength(9);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
  }

  /**
   * Test method for {@link RepairableContent#isValidInput(java.lang.String)}.
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testIsValidInputString_AutoRepair() throws BadLocationException {
    this.cont.setAutoRepairContent(true);
    assertThat(this.cont.isValidInput(0, "alfred")).isTrue();
    assertThat(this.cont.isValidInput(0, null)).isTrue();
    this.cont.insertString(0, null, null);
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
    this.cont.insertString(0, "alf", null);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
    this.cont.insertString(0, "alf", null);
    this.cont.setMaximumInputLength(6);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
    this.cont.setMaximumInputLength(9);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
  }

  /**
   * Test method for {@link RepairableContent#insertString(int, java.lang.String, javax.swing.text.AttributeSet)} .
   * 
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testInsertStringIntStringAttributeSet() throws BadLocationException {
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.getText()).isEmpty();
    this.cont.insertString(0, "chj", null);
    assertThat(this.cont.getText()).isEqualTo("chj");
    this.cont.insertString(0, "ab", null);
    assertThat(this.cont.getText()).isEqualTo("abchj");
    this.cont.insertString(3, "deg", null);
    assertThat(this.cont.getText()).isEqualTo("abcdeghj");
    this.cont.insertString(5, "f", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghj");
    this.cont.insertString(5, null, null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghj");
    this.cont.insertString(8, "i", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghij");
    this.cont.insertString(10, "k", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghijk");
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
    assertThat(this.cont.getText()).isEmpty();
    this.cont.insertString(0, "chj", null);
    assertThat(this.cont.getText()).isEqualTo("chj");
    this.cont.insertString(0, "ab", null);
    assertThat(this.cont.getText()).isEqualTo("abchj");
    this.cont.insertString(3, "deg", null);
    assertThat(this.cont.getText()).isEqualTo("abcde");
    this.cont.insertString(5, "f", null);
    assertThat(this.cont.getText()).isEqualTo("abcde");
    this.cont.insertString(5, null, null);
    assertThat(this.cont.getText()).isEqualTo("abcde");
    this.cont.setMaximumInputLength(0);
    this.cont.insertString(5, "fghij", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghij");
    this.cont.insertString(10, "k", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghijk");
  }

  /**
   * Tests that {@link RepairableContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Test(expected = BadLocationException.class)
  public void testInsertStringIntStringAttributeSet_BLE1() throws BadLocationException {
    this.cont.insertString(1, "k", null);
  }

  /**
   * Tests that {@link RepairableContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Test(expected = BadLocationException.class)
  public void testInsertStringIntStringAttributeSet_BLE2() throws BadLocationException {
    this.cont.insertString(-1, "k", null);
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
    assertThat(this.cont.getText()).isEqualTo("hotcat");
    this.cont.replace(0, 3, "cold", null);
    assertThat(this.cont.getText()).isEqualTo("coldcat");
    this.cont.replace(3, 2, null, null);
    assertThat(this.cont.getText()).isEqualTo("colat");
    this.cont.replace(4, 1, "", null);
    assertThat(this.cont.getText()).isEqualTo("cola");
    this.cont.replace(0, 1, "To", null);
    assertThat(this.cont.getText()).isEqualTo("Toola");
    this.cont.replace(3, 2, "l", null);
    assertThat(this.cont.getText()).isEqualTo("Tool");
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
    assertThat(this.cont.getText()).isEqualTo("hotca");
    this.cont.replace(0, 3, "cold", null);
    assertThat(this.cont.getText()).isEqualTo("coldc");
    this.cont.replace(3, 2, null, null);
    assertThat(this.cont.getText()).isEqualTo("col");
    this.cont.replace(1, 0, "", null);
    assertThat(this.cont.getText()).isEqualTo("col");
    this.cont.replace(1, 0, "o", null);
    assertThat(this.cont.getText()).isEqualTo("cool");
    this.cont.replace(0, 1, "T", null);
    assertThat(this.cont.getText()).isEqualTo("Tool");
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE1() throws BadLocationException {
    this.cont.replace(-1, 0, "k", null);
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE2() throws BadLocationException {
    this.cont.replace(1, 0, "k", null);
  }

  /**
   * Tests that {@link RepairableContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: Feb 10, 2011
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE3() throws BadLocationException {
    this.cont.replace(0, 1, "k", null);
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
    assertThat(this.cont.getText()).isEqualTo("string");
    this.cont.repair();
    assertThat(this.cont.getText()).isEqualTo("strin");
    this.cont.repair();
    assertThat(this.cont.getText()).isEqualTo("strin");
    this.cont.remove(3, 2);
    assertThat(this.cont.getText()).isEqualTo("str");
    this.cont.repair();
    assertThat(this.cont.getText()).isEqualTo("str");
  }

  /**
   * Test method for {@link RepairableContent#isAutoRepairContent()} and
   * {@link RepairableContent#setAutoRepairContent(boolean)}.
   */
  @Test
  public void testSetIsAutoRepairContent() {
    assertThat(this.cont.isAutoRepairContent()).isFalse();
    this.cont.setAutoRepairContent(false);
    assertThat(this.cont.isAutoRepairContent()).isFalse();
    this.cont.setAutoRepairContent(true);
    assertThat(this.cont.isAutoRepairContent()).isTrue();
    this.cont.setAutoRepairContent(true);
    assertThat(this.cont.isAutoRepairContent()).isTrue();
    this.cont.setAutoRepairContent(false);
    assertThat(this.cont.isAutoRepairContent()).isFalse();
  }
}
