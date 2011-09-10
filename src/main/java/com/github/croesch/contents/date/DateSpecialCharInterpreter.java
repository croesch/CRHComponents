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
package com.github.croesch.contents.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.croesch.contents.date.DateSpecialChar.ValueType;
import com.github.croesch.logging.Log;

/**
 * This interpreter is able to read an input stream and fetch all special chars and their definition out of the stream.
 * Syntax for the stream is as follows:<br>
 * < file > ::= < lines ><br>
 * < lines > ::= < line > [ "\n" < lines > ]<br>
 * < line > ::= < comment > | < definition > [ < comment > ]<br>
 * < comment > ::= [ < blank > ] "#" < text ><br>
 * < blank > ::= " " | "\t"<br>
 * < definition > ::= < key > < sep > [ < type > < value > ] < sep > [ < type > < value > ] < sep > [ < type > < value >
 * ]<br>
 * < type > ::= "c" | "i" | "o"<br>
 * < value > ::= [ "-" ] < numbers ><br>
 * < numbers > ::= < number > [ < numbers > ]<br>
 * < sep > ::= "|"<br>
 * < text > ::= any string without '\n'<br>
 * < key > ::= [a-zA-Z]<br>
 * < number > ::= [0-9]
 * 
 * @author croesch
 * @since Date: Apr 5, 2011 9:40:29 AM
 */
final class DateSpecialCharInterpreter {

  /** the map that contains the definitions found in the input stream */
  private final Map<String, DateSpecialChar> specialCharsMap = new HashMap<String, DateSpecialChar>();

  /**
   * Constructs a new {@link DateSpecialCharInterpreter} with the definition given by the given {@link BufferedReader}.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:46:33 PM
   * @param in the {@link BufferedReader} to read the definitions from
   */
  public DateSpecialCharInterpreter(final BufferedReader in) {
    try {
      String line = in.readLine();
      while (line != null) {
        line = line.trim().replaceFirst("[ \t]*#.*", "");
        if (line.matches(".(\\|([oic]-?[0-9]+)?){3,3}")) {
          addLine(line);
        }
        line = in.readLine();
      }
    } catch (final IOException e) {
      Log.error(e);
    }
  }

  /**
   * Returns the type of the value, given by the {@link String}.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < type > < value >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:47:52 PM
   * @param s the string that contains < type > < value >
   * @return the enum that represents the < type >
   * @throws AssertionError if the < type > is unknown
   */
  private ValueType getValueType(final String s) throws AssertionError {
    switch (s.charAt(0)) {
      case 'c':
        return ValueType.CONSTANT;
      case 'i':
        return ValueType.INCREMENT;
      case 'o':
        return ValueType.OFFSET;
      default:
        throw new AssertionError();
    }
  }

  /**
   * Returns the < value >, given by the {@link String}.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < type > < value >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:47:52 PM
   * @param s the string that contains < type > < value >
   * @return the int that represents the < value >
   */
  private int getValue(final String s) {
    return Integer.valueOf(s.substring(1)).intValue();
  }

  /**
   * Splits the given {@link String} that is expected to be < line > and returns an array that contains the values
   * splitted by the < sep >. Empty values are replaced with default values.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < line >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:51:04 PM
   * @param line the {@link String} that contains < line >
   * @return an array that contains the parts of the < line > splitted by the < sep >. Optional values that are omitted
   *         are replaced with default values.
   */
  private String[] splitLine(final String line) {
    final String[] splitted = new String[] { "", "i0", "i0", "i0" };
    final String[] tmpArray = line.split("\\|");
    for (int i = 0; i < tmpArray.length; ++i) {
      if (tmpArray[i] != null && !tmpArray[i].trim().equals("")) {
        splitted[i] = tmpArray[i];
      }
    }
    return splitted;
  }

  /**
   * Adds the given {@link String} that is expected to be < line > to the internal map of definitions.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < line >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:55:39 PM
   * @param line the {@link String} that contains < line >
   */
  private void addLine(final String line) {
    final String[] arr = splitLine(line);
    this.specialCharsMap.put(line.substring(0, 1), new DateSpecialChar(line.charAt(0),
                                                                       getValueType(arr[1]),
                                                                       getValue(arr[1]),
                                                                       getValueType(arr[2]),
                                                                       getValue(arr[2]),
                                                                       getValueType(arr[3]),
                                                                       getValue(arr[3])));
  }

  /**
   * Returns the map of the definitions found in the input stream.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 8:56:48 PM
   * @return a map that contains all found valid definitions of special chars with their values.
   */
  public Map<String, DateSpecialChar> getSpecialCharsMap() {
    return this.specialCharsMap;
  }
}
