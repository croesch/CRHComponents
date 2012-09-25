package com.github.croesch;

import java.util.Locale;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.BeforeClass;
import org.junit.Ignore;

/*
 * Copyright (C) 2011-2012  Christian Roesch
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

/**
 * Default test case to be extended by all test classes.
 * 
 * @author croesch
 * @since Date: Jan 22, 2012
 */
@Ignore("Just default case")
public class DefaultTestCase extends FestSwingJUnitTestCase {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    Locale.setDefault(new Locale("test", "tst", " "));
  }

  @Override
  protected final void onSetUp() {
    setUpDetails();
  }

  protected void setUpDetails() {
    // let that be defined by subclasses
  }
}
