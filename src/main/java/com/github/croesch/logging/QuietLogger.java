package com.github.croesch.logging;

/**
 * This is a very dumb implementation of a logger. It does only catch the messages and will not give any output.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
final class QuietLogger implements Logger {

  @Override
  public void printThrowable(final Throwable t) {
    // do nothing, just be quiet
  }

}
