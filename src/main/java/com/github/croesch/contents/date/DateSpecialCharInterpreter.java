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
 * @since Date: Apr 5, 2011
 */
final class DateSpecialCharInterpreter {

  /** the map that contains the definitions found in the input stream */
  private final Map<String, DateSpecialChar> specialCharsMap = new HashMap<String, DateSpecialChar>();

  /**
   * Constructs a new {@link DateSpecialCharInterpreter} with the definition given by the given {@link BufferedReader}.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @param in the {@link BufferedReader} to read the definitions from
   * @throws IllegalArgumentException if the given reader is <code>null</code>
   */
  //TODO #7 extract to method, make constructor private and create static method
  public DateSpecialCharInterpreter(final BufferedReader in) throws IllegalArgumentException {
    if (in == null) {
      throw new IllegalArgumentException(">null< is not allowed.");
    }

    try {
      String line = in.readLine();
      while (line != null) {
        // TODO #12 extract method
        line = line.trim().replaceFirst("[ \t]*#.*", "");
        // TODO #12 extract method
        if (line.matches(".(\\|([oic]-?[0-9]+)?){3,3}")) {
          addLine(line);
        }
        line = in.readLine();
      }
    } catch (final IOException e) {
      /*
       * We don't want the caller to notice an IO-Error, because this might be not relevant. The date field should
       * simply work, but it wouldn't contain any special characters. So the impact of a wrong configuration file is
       * that there are no special characters working. And that impact would be logged.
       */
      Log.error(e);
    }
  }

  /**
   * Returns the type of the value, given by the {@link String}.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < type > < value >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @param s the string that contains < type > < value >
   * @return the enum that represents the < type >
   * @throws IllegalArgumentException if the < type > is unknown
   */
  private ModificationType getValueType(final String s) throws IllegalArgumentException {
    return ModificationType.typeOfUniqueChar(s.charAt(0));
  }

  /**
   * Returns the < value >, given by the {@link String}.<br>
   * <b>Note:</b> The string mustn't contain any invalid value, ensure that it contains < type > < value >!
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
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
   * @since Date: Apr 5, 2011
   * @param line the {@link String} that contains < line >
   * @return an array that contains the parts of the < line > splitted by the < sep >. Optional values that are omitted
   *         are replaced with default values.
   */
  private String[] splitLine(final String line) {
    // initialize default values (default, increment current value by zero)
    final String defaultValue = ModificationType.INCREMENT.uniqueCharacter() + "0";
    final String[] splitted = new String[] { "", defaultValue, defaultValue, defaultValue };

    // is ensured to return exactly four values, because the given string must be a valid < line >
    final String[] tmpArray = line.split("\\|");
    for (int i = 0; i < tmpArray.length; ++i) {
      // copy each value that is not empty
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
   * @since Date: Apr 5, 2011
   * @param line the {@link String} that contains < line >
   */
  private void addLine(final String line) {
    final String[] arr = splitLine(line);

    final String yearPartOfDefinition = arr[1];
    final String monthPartOfDefinition = arr[2];
    final String dayPartOfDefinition = arr[3];
    // construct the special character
    final DateSpecialChar specialChar = new DateSpecialChar(line.charAt(0),
                                                            getValueType(yearPartOfDefinition),
                                                            getValue(yearPartOfDefinition),
                                                            getValueType(monthPartOfDefinition),
                                                            getValue(monthPartOfDefinition),
                                                            getValueType(dayPartOfDefinition),
                                                            getValue(dayPartOfDefinition));
    // put the special character into the map of special characters
    this.specialCharsMap.put(line.substring(0, 1), specialChar);
  }

  /**
   * Returns the map of the definitions found in the input stream.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return a map that contains all found valid definitions of special chars with their values.
   */
  public Map<String, DateSpecialChar> getSpecialCharsMap() {
    return this.specialCharsMap;
  }
}
