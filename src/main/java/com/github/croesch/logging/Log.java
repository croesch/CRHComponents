package com.github.croesch.logging;

/**
 * This is the class that is responsible for all logging events of this library. It will pass all messages to the
 * current logger.
 * 
 * @author croesch
 * @since Date: Jul 6, 2011
 */
public final class Log {

  /** the logger that will handle the messages that arrive hear */
  private static Logger logger = new QuietLogger();

  /**
   * Hidden constructor.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   */
  private Log() {
    // hide utility class constructor
  }

  /**
   * Replaces the current {@link Logger} with the given one if it's not <code>null</code>.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @param log the new {@link Logger}.
   */
  public static void setLogger(final Logger log) {
    if (log != null) {
      logger = log;
    }
  }

  /**
   * Passes the given message to the current logger to handle it.
   * 
   * @author croesch
   * @since Date: Jul 6, 2011
   * @param t the {@link Throwable} that occured.
   */
  public static void error(final Throwable t) {
    logger.printThrowable(t);
  }

}
