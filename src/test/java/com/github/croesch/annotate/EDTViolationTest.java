package com.github.croesch.annotate;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

import com.github.croesch.components.CDateField;
import com.github.croesch.util.MnemonicUtil;

/**
 * Tests different methods that are declared to be able to be invoked from EDT and from other threads.
 * 
 * @author croesch
 * @since Date: Sep 10, 2011
 */
public class EDTViolationTest extends FestSwingJUnitTestCase {

  @Override
  protected void onSetUp() {
    // nothing needed to be set up
  }

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
