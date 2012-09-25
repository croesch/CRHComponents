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
package com.github.croesch.logging;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test methods for {@link Log}.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
public class LogTest extends DefaultTestCase {

  private Throwable t = null;

  /**
   * Test method for {@link Log#setLogger(Logger)}.
   */
  @Test
  public final void testSetLogger() {
    Log.setLogger(new Logger() {

      @Override
      public void printThrowable(final Throwable t) {
        LogTest.this.t = t;
      }
    });

    Log.error(new AssertionError());
    assertThat(this.t).isNotNull();

    Log.setLogger(null);

    Log.error(new AssertionError());
    assertThat(this.t).isNotNull();
  }
}
