package com.github.croesch.logging;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides test methods for {@link Log}.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
public class LogTest {

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
