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
package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Test methods for {@link DateLazyMonEditor}
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
public class DateLazyMonEditorTest extends FestSwingJUnitTestCase {

  private DateLazyMonEditor editor;

  /**
   * Constructs the editor
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   */
  @Override
  public void onSetUp() {
    this.editor = new DateLazyMonEditor();
  }

  /**
   * Test method for {@link DateLazyMonEditor#DateLazyMonEditor()}.
   */
  @Test
  public final void testDateLazyMonEditor() {
    final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));

    this.editor = new DateLazyMonEditor();
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));

    this.editor = new DateLazyMonEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("12");

    this.editor = new DateLazyMonEditor(10);
    assertThat(this.editor.getValue()).isEqualTo("10");

    this.editor = new DateLazyMonEditor(02);
    assertThat(this.editor.getValue()).isEqualTo("02");

    this.editor = new DateLazyMonEditor(3);
    assertThat(this.editor.getValue()).isEqualTo("03");

    this.editor = new DateLazyMonEditor(1);
    assertThat(this.editor.getValue()).isEqualTo("01");

    this.editor = new DateLazyMonEditor(9);
    assertThat(this.editor.getValue()).isEqualTo("09");

    this.editor = new DateLazyMonEditor(0);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));

    this.editor = new DateLazyMonEditor(32);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));

    this.editor = new DateLazyMonEditor(132);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));

    this.editor = new DateLazyMonEditor(-7);
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));
  }

  /**
   * Test method for {@link DateLazyMonEditor#getSize()}.
   */
  @Test
  public final void testGetSize() {
    assertThat(this.editor.getSize()).isEqualTo(2);
  }

  /**
   * Test method for {@link DateLazyMonEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_First() {
    assertThat(this.editor.enterValue("0", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('0');

    assertThat(this.editor.enterValue("1", 0)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(0)).isEqualTo('1');

    assertThat(this.editor.enterValue("2", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('2');

    assertThat(this.editor.enterValue("3", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('3');

    assertThat(this.editor.enterValue("4", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('4');

    assertThat(this.editor.enterValue("5", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('5');

    assertThat(this.editor.enterValue("6", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('6');

    assertThat(this.editor.enterValue("7", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('7');

    assertThat(this.editor.enterValue("8", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('8');

    assertThat(this.editor.enterValue("9", 0)).isEqualTo(2);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("b", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

  }

  /**
   * Test method for {@link DateLazyMonEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Second() {
    assertThat(this.editor.enterValue("0", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('0');

    assertThat(this.editor.enterValue("1", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('1');

    assertThat(this.editor.enterValue("2", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('2');

    assertThat(this.editor.enterValue("3", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('3');

    assertThat(this.editor.enterValue("4", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('4');

    assertThat(this.editor.enterValue("5", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('5');

    assertThat(this.editor.enterValue("6", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('6');

    assertThat(this.editor.enterValue("7", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('7');

    assertThat(this.editor.enterValue("8", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('8');

    assertThat(this.editor.enterValue("9", 1)).isEqualTo(1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("a", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');

    assertThat(this.editor.enterValue("b", 1)).isEqualTo(-1);
    assertThat(this.editor.getValue().charAt(1)).isEqualTo('9');
  }

  /**
   * Test method for {@link DateLazyMonEditor#enterValue(String, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Invalid() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue("0", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("1", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("2", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("3", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("4", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("5", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("6", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("7", -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("8", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("9", -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("a", 10)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("b", 11)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyMonEditor#enterValue(String, int)}.
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
    assertThat(this.editor.enterValue("08", -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("09", 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0a", 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue("0b", 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyMonEditor#enterValue(String, int)}.
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
   * Test method for {@link DateLazyMonEditor#equals(Object)}.
   */
  @Test
  public final void testEquals() {
    assertThat(this.editor).isEqualTo(this.editor);

    assertThat(new DateLazyMonEditor(2)).isEqualTo(new DateLazyMonEditor(2));
    assertThat(new DateLazyMonEditor(12)).isEqualTo(new DateLazyMonEditor(12));
    assertThat(new DateLazyMonEditor(1)).isEqualTo(new DateLazyMonEditor(1));
    assertThat(new DateLazyMonEditor(7)).isEqualTo(new DateLazyMonEditor(7));
    assertThat(new DateLazyMonEditor(32)).isEqualTo(new DateLazyMonEditor(31));

    assertThat(new DateLazyMonEditor(2)).isNotEqualTo(null);
    assertThat(new DateLazyMonEditor(2)).isNotEqualTo("02");
    assertThat(new DateLazyMonEditor(2)).isNotEqualTo(new DateLazyMonEditor(1));
    assertThat(new DateLazyMonEditor(10)).isNotEqualTo(new DateLazyMonEditor(11));
    assertThat(new DateLazyMonEditor(9)).isNotEqualTo(new DateLazyMonEditor(10));
    assertThat(new DateLazyMonEditor(7)).isNotEqualTo(new DateLazyMonEditor(8));
    assertThat(new DateLazyMonEditor(4)).isNotEqualTo(new DateLazyMonEditor(3));
  }

  /**
   * Test method for {@link DateLazyMonEditor#toString()}
   */
  @Test
  public final void testToString() {
    final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    assertThat(this.editor.getValue()).isEqualTo(String.format("%02d", month));
    assertThat(this.editor.toString()).isEqualTo(String.format("%02d", month));
    this.editor = new DateLazyMonEditor(12);
    assertThat(this.editor.getValue()).isEqualTo("12");
    assertThat(this.editor.toString()).isEqualTo("12");
    this.editor = new DateLazyMonEditor(2);
    assertThat(this.editor.getValue()).isEqualTo("02");
    assertThat(this.editor.toString()).isEqualTo("02");
    this.editor = new DateLazyMonEditor(10);
    assertThat(this.editor.getValue()).isEqualTo("10");
    assertThat(this.editor.toString()).isEqualTo("10");
  }
}
