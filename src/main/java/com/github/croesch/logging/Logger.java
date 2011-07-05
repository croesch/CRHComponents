package com.github.croesch.logging;

/**
 * Interface to add a custom logger to this library.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
public interface Logger {

  /**
   * Performs logging of an internal error with the given {@link Throwable}.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @param t the {@link Throwable} that has been thrown
   */
  void printThrowable(Throwable t);

}
