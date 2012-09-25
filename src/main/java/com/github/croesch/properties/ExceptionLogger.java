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
package com.github.croesch.properties;

import java.util.logging.Logger;

/**
 * Logs exceptions.
 * 
 * @author croesch
 * @since Date: Feb 23, 2012
 */
final class ExceptionLogger {

  /** logger for this class */
  private static final Logger LOGGER = Logger.getLogger(ExceptionLogger.class.getName());

  /**
   * Hide constructor from being invoked.
   * 
   * @since Date: Feb 23, 2012
   */
  private ExceptionLogger() {
    throw new AssertionError();
  }

  /**
   * Logs the exception that happened in critical part of the program.
   * 
   * @since Date: Feb 3, 2012
   * @param e the {@link Throwable} that was thrown
   */
  public static void logException(final Throwable e) {
    final String className = Thread.currentThread().getStackTrace()[2].getClassName();
    final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    LOGGER.severe(e.getMessage());
    LOGGER.throwing(className, methodName, e);
  }
}
