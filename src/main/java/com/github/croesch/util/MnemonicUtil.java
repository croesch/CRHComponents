package com.github.croesch.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;

/**
 * Utility class for Mnemonic operations
 * 
 * @author croesch
 * @since (Date: 2010/12/20 17:40:57 )
 */
public final class MnemonicUtil {

  /**
   * Hides the default constructor, cause this is a utility class. Don't call this constructor.
   * 
   * @author croesch
   * @since Date: 08.02.2011 21:36:27
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
  public static String filterMnemonic(final String s, final AbstractButton b) {
    if (s != null) {
      String tmpS = s;
      final Matcher m = Pattern.compile("\\[(.)\\]").matcher(tmpS); //$NON-NLS-1$
      if (m.find()) {
        b.setMnemonic(m.group(1).charAt(0));
        tmpS = m.replaceFirst(m.group(1));
      }
      return tmpS;
    }
    return null;
  }

}
