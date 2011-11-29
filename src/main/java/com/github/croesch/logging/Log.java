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
   * @since Date: Jul 6, 2011
   */
  private Log() {
    throw new AssertionError("Invocation of utility class constructor.");
  }

  /**
   * Replaces the current {@link Logger} with the given one if it's not <code>null</code>.
   * 
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
   * @since Date: Jul 6, 2011
   * @param t the {@link Throwable} that occured.
   */
  public static void error(final Throwable t) {
    logger.printThrowable(t);
  }

}
