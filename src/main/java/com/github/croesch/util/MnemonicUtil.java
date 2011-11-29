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
package com.github.croesch.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;

import com.github.croesch.annotate.DoesntMatterIfCalledFromEDTOrNot;
import com.github.croesch.logging.Log;

/**
 * Utility class for Mnemonic operations
 * 
 * @author croesch
 * @since Date: Dec 20, 2010
 */
public final class MnemonicUtil {

  /**
   * Hides the default constructor, cause this is a utility class. Don't call this constructor.
   * 
   * @author croesch
   * @since Date: Feb 08, 2011
   */
  private MnemonicUtil() {
    throw new AssertionError();
  }

  /**
   * Filters the mnemonic definition from the given string. If a mnemonic definition ([m]) is found, it will be set to
   * the given AbstractButton.
   * 
   * @param s the String that contains the mnemonic definition
   * @param b the AbstractButton that should get the mnemonic
   * @return the String without the [] of the definition
   */
  @DoesntMatterIfCalledFromEDTOrNot
  public static String filterMnemonic(final String s, final AbstractButton b) {
    if (s != null) {
      String tmpS = s;
      final Matcher m = Pattern.compile("\\[(.)\\]").matcher(tmpS);
      if (m.find()) {
        // define runnable to set the mnemonic
        final Runnable r = new Runnable() {
          @Override
          public void run() {
            b.setMnemonic(m.group(1).charAt(0));
          }
        };
        // run the runnable, either on EDT or on the current thread, depending on what thread we are running in
        if (SwingUtilities.isEventDispatchThread()) {
          r.run();
        } else {
          try {
            SwingUtilities.invokeAndWait(r);
          } catch (final Exception e) {
            Log.error(e);
          }
        }
        tmpS = m.replaceFirst(m.group(1));
      }
      return tmpS;
    }
    return null;
  }

}
