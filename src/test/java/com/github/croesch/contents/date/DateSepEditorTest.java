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

import java.util.Random;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test methods for {@link DateSepEditor}.
 * 
 * @author croesch
 * @since Date: Jul 2, 2011
 */
public class DateSepEditorTest extends DefaultTestCase {

  private DateSepEditor editor;

  /**
   * Initialises the editor.
   * 
   * @author croesch
   * @since Date: Jul 2, 2011
   */
  @Override
  public void setUpDetails() {
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
   * Test method for {@link DateSepEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_First() {
    assertThat(this.editor.enterValue('0', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('1', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('a', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('b', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('.', 0)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    this.editor = new DateSepEditor(" .");

    assertThat(this.editor.enterValue('.', 0)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(" .");

    assertThat(this.editor.enterValue(' ', 0)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(" .");
  }

  /**
   * Test method for {@link DateSepEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Second() {
    assertThat(this.editor.enterValue('0', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('1', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('2', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('a', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('b', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    assertThat(this.editor.enterValue('.', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(".");

    this.editor = new DateSepEditor(" .");

    assertThat(this.editor.enterValue('.', 1)).isEqualTo(1);
    assertThat(this.editor.getValue()).isEqualTo(" .");

    assertThat(this.editor.enterValue(' ', 1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(" .");
  }

  /**
   * Test method for {@link DateSepEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_SingleCharacter_Invalid() {
    final String val = this.editor.getValue();
    assertThat(this.editor.enterValue('.', -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', -1)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', -2)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', -3)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 10)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
    assertThat(this.editor.enterValue('.', 11)).isEqualTo(-1);
    assertThat(this.editor.getValue()).isEqualTo(val);
  }

  /**
   * Test method for {@link DateLazyDayEditor#enterValue(char, int)}.
   */
  @Test
  public final void testEnterValue_Nothing() {
    assertThat(this.editor.enterValue(' ', 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue(' ', 1)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 2)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\t', 7)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 0)).isEqualTo(-1);
    assertThat(this.editor.enterValue('\n', 3)).isEqualTo(-1);
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
    assertThat(new DateSepEditor("xyz")).isNotEqualTo("xy");

    assertThat(new DateSepEditor("")).isEqualTo(new DateSepEditor(""));
    assertThat(new DateSepEditor("").hashCode()).isEqualTo(new DateSepEditor("").hashCode());

    assertThat(new DateSepEditor("20")).isNotEqualTo(new FakeEditor("20"));
  }

  private static class FakeEditor extends DateSepEditor {
    public FakeEditor(final String initial) {
      super(initial);
    }
  }
}
