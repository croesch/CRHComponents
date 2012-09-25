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
package com.github.croesch.annotate;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.junit.Test;

import com.github.croesch.DefaultTestCase;
import com.github.croesch.components.CDateField;
import com.github.croesch.util.MnemonicUtil;

/**
 * Tests different methods that are declared to be able to be invoked from EDT and from other threads.
 * 
 * @author croesch
 * @since Date: Sep 10, 2011
 */
public class EDTViolationTest extends DefaultTestCase {

  private CDateField field;

  private JButton button;

  /**
   * Test method for {@link CDateField#setDateAndDisplay(Date)}.
   */
  @Test
  public void testCDateField_SetDateAndDisplay() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        EDTViolationTest.this.field = new CDateField();
      }
    });

    this.field.setDateAndDisplay(new Date());

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        EDTViolationTest.this.field.setDateAndDisplay(new Date());
      }
    });
  }

  /**
   * Test method for {@link MnemonicUtil#filterMnemonic(String, javax.swing.AbstractButton)}.
   */
  @Test
  public void testMnemonicUtil_FilterMnemonic() throws InterruptedException, InvocationTargetException {
    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        EDTViolationTest.this.button = new JButton();
      }
    });

    MnemonicUtil.filterMnemonic("as[d]", this.button);

    SwingUtilities.invokeAndWait(new Runnable() {
      @Override
      public void run() {
        MnemonicUtil.filterMnemonic("as[d]", EDTViolationTest.this.button);
      }
    });
  }
}
