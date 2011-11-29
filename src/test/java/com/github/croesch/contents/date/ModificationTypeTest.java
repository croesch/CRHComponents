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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Contains test cases for {@link ModificationType}.
 * 
 * @author croesch
 * @since Date: Nov 29, 2011
 */
public class ModificationTypeTest {

  @Test
  public void testUniqueCharacter() {
    final List<Character> firstLetters = new ArrayList<Character>();
    for (final ModificationType t : ModificationType.values()) {
      firstLetters.add(t.uniqueCharacter());
    }
    assertThat(firstLetters).doesNotHaveDuplicates();
  }

  @Test
  public void testTypeOfUniqueChar() {
    for (final ModificationType t : ModificationType.values()) {
      assertThat(ModificationType.typeOfUniqueChar(t.uniqueCharacter())).isSameAs(t);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTypeUniqueChar_MinusOne() {
    ModificationType.typeOfUniqueChar((char) -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTypeOfUniqueChar_Unknown() {
    ModificationType.typeOfUniqueChar('?');
  }
}
