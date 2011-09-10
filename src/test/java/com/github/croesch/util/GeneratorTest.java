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
package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

import com.github.croesch.util.generator.LocaleGenerator;

/**
 * Provides test methods for {@link Generator}
 * 
 * @author croesch
 * @since Date: 10.03.2011 19:05:15
 */
public class GeneratorTest extends FestSwingJUnitTestCase {

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateFileNames_IAE() {
    Generator.generateFrom(null);
  }

  @Test
  public void testGenerateFileNames() {
    Locale loc = new Locale("DE", "de");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("DE", "de", "rlp");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("DE");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = Locale.getDefault();
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = Locale.GERMAN;
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("", "");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("", "", "");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }

}
