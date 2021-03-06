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

import java.io.StringReader;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test methods for {@link DateSpecialCharInterpreter}
 * 
 * @author croesch
 * @since Date: Apr 5, 2011
 */
public class DateSpecialCharInterpreterTest extends DefaultTestCase {

  /**
   * Test method for {@link DateSpecialCharInterpreter#DateSpecialCharInterpreter()}
   */
  @Test
  public void testConstructor() {
    DateSpecialCharInterpreter.createFrom(new StringReader(".."));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_IAE() {
    DateSpecialCharInterpreter.createFrom(null);
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testEmptyList() {
    DateSpecialCharInterpreter interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment "));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader(""));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("...\n\n..."));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("asd\n"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("#|c11|i12|o13"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("a|c1|i1|p3"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("a|c1|p1|i3"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("a|p1|i1|c3"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("a|O1|i1|c3"));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testListIsNotEmpty() {
    DateSpecialCharInterpreter interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("t|o0|o0|o0"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment \nt|o0|o0|o0"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment \nt|o0|o0|o0 # today"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment \nt|o0|o0|o0\t#\ttoday"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment \n t|o0|o0|o0\t#\ttoday"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("# no content\n # comment \n t|o0|o0|o0\t#\ttoday "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tt|o0|o0|o0 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tc|o1|o2|o3 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tc|c1|i2|o3 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tc|c11|i12|o13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c11|i12|o13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\t Z||i12|o13 \t"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c11||o13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c11|i12| "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c11|i12|o-13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c11|i-12|o13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("\tZ|c-11|i12|o13 "));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testFirstListEntry() {
    DateSpecialChar specialChar = new DateSpecialChar('t',
                                                      ModificationType.OFFSET,
                                                      0,
                                                      ModificationType.OFFSET,
                                                      0,
                                                      ModificationType.OFFSET,
                                                      0);
    DateSpecialCharInterpreter interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("t|o0|o0|o0"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().get("t")).isEqualTo(specialChar);

    specialChar = new DateSpecialChar('i',
                                      ModificationType.INCREMENT,
                                      1,
                                      ModificationType.INCREMENT,
                                      2,
                                      ModificationType.INCREMENT,
                                      -3);
    interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("i|i1|i2|i-3"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().get("i")).isEqualTo(specialChar);
  }

  @Test
  public void testEmptySpecialCharacter() {
    final DateSpecialChar specialChar = new DateSpecialChar('i',
                                                            ModificationType.INCREMENT,
                                                            0,
                                                            ModificationType.INCREMENT,
                                                            0,
                                                            ModificationType.INCREMENT,
                                                            0);
    final DateSpecialCharInterpreter interpreter = DateSpecialCharInterpreter.createFrom(new StringReader("i|||"));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().get("i")).isEqualTo(specialChar);
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testListEntries() {
    final String testString = "# key for today\n" + "t|o1|o2|o3\n" + "#another key\n" + "m|o0|o0|o1 # (tomorrow)\n"
                              + "...\n" + "# duplicate key \n" + "t|o0|o0|o0\n" + "# no duplicate key\n" + "T|c1|c2|c3";

    final DateSpecialChar sc1 = new DateSpecialChar('t',
                                                    ModificationType.OFFSET,
                                                    0,
                                                    ModificationType.OFFSET,
                                                    0,
                                                    ModificationType.OFFSET,
                                                    0);
    final DateSpecialChar sc2 = new DateSpecialChar('m',
                                                    ModificationType.OFFSET,
                                                    0,
                                                    ModificationType.OFFSET,
                                                    0,
                                                    ModificationType.OFFSET,
                                                    1);
    final DateSpecialChar sc3 = new DateSpecialChar('T',
                                                    ModificationType.CONSTANT,
                                                    1,
                                                    ModificationType.CONSTANT,
                                                    2,
                                                    ModificationType.CONSTANT,
                                                    3);
    final DateSpecialCharInterpreter interpreter = DateSpecialCharInterpreter.createFrom(new StringReader(testString));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().values()).containsOnly(sc1, sc2, sc3);
  }
}
