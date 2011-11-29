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

/**
 * Container that stores the values of a definition of a special char. Represents the values given by a single < line >
 * defined in {@link DateSpecialCharInterpreter}.
 * 
 * @author croesch
 * @since Date: Apr 5, 2011
 * @see DateSpecialCharInterpreter
 */
final class DateSpecialChar {

  /** the type of the value, if it should be interpreted as a constant, offset or incremental value. */
  public static enum ValueType {
    // TODO #7 extract to own class
    /** constant to interpret the value as a constant. */
    CONSTANT {
      @Override
      public String toString() {
        // TODO #7 define as constant(s)
        return "c";
      }
    },
    /** constant to interpret the value as an offset from the current date. */
    OFFSET {
      @Override
      public String toString() {
        return "o";
      }
    },
    /** constant to interpret the value as an increment of the current date value */
    INCREMENT {
      @Override
      public String toString() {
        return "i";
      }
    };
  }

  /** the special character to access this definition */
  private final char specialChar;

  /** the type of the year value */
  private final ValueType yearValueType;

  /** the value for the year */
  private final int yearValue;

  /** the type of the month value */
  private final ValueType monthValueType;

  /** the value for the month */
  private final int monthValue;

  /** the type of the day value */
  private final ValueType dayValueType;

  /** the value for the day */
  private final int dayValue;

  /**
   * Constructs a {@link DateSpecialChar} with the given values.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @param c the character to access this definition
   * @param yearType the type of the year value, not <code>null</code>
   * @param yearVal the year value
   * @param monthType the type of the month value, not <code>null</code>
   * @param monthVal the month value
   * @param dayType the type of the day value, not <code>null</code>
   * @param dayVal the day value
   */
  public DateSpecialChar(final char c,
                         final ValueType yearType,
                         final int yearVal,
                         final ValueType monthType,
                         final int monthVal,
                         final ValueType dayType,
                         final int dayVal) {
    if (yearType == null || monthType == null || dayType == null) {
      throw new IllegalArgumentException(this.toString());
    }
    this.specialChar = c;
    this.yearValueType = yearType;
    this.yearValue = yearVal;
    this.monthValueType = monthType;
    this.monthValue = monthVal;
    this.dayValueType = dayType;
    this.dayValue = dayVal;
  }

  /**
   * Returns the character to access this definition of a special date.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the special character that need to be entered to access this definition.
   */
  public char getChar() {
    return this.specialChar;
  }

  /**
   * Returns the type of the year value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the enum that represents the type of the year value
   */
  public ValueType getYearValueType() {
    return this.yearValueType;
  }

  /**
   * Returns the year value
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the year value
   */
  public int getYearValue() {
    return this.yearValue;
  }

  /**
   * Returns the type of the month value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the enum that represents the type of the month value.
   */
  public ValueType getMonthValueType() {
    return this.monthValueType;
  }

  /**
   * Returns the month value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the month value.
   */
  public int getMonthValue() {
    return this.monthValue;
  }

  /**
   * Returns the type of the day value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the enum that represents the type of the day value.
   */
  public ValueType getDayValueType() {
    return this.dayValueType;
  }

  /**
   * Returns the day value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @return the day value.
   */
  public int getDayValue() {
    return this.dayValue;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.dayValue;
    result = prime * result + this.dayValueType.hashCode();
    result = prime * result + this.monthValue;
    result = prime * result + this.monthValueType.hashCode();
    result = prime * result + this.yearValue;
    result = prime * result + this.yearValueType.hashCode();
    result = prime * result + this.specialChar;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DateSpecialChar)) {
      return false;
    }
    final DateSpecialChar other = (DateSpecialChar) obj;
    return areFieldsEqual(other);
  }

  /**
   * Returns whether all of the internal fields are the same as the fields of the given {@link DateSpecialChar}
   * 
   * @author croesch
   * @since Date: Apr 5, 2011
   * @param other the other {@link DateSpecialChar} to compare with this
   * @return <code>true</code>, if all the fields are the same
   */
  private boolean areFieldsEqual(final DateSpecialChar other) {
    return this.specialChar == other.specialChar && areValuesAndTypesEqual(other);
  }

  /**
   * Returns whether all values and types of this special char are equal to the ones fetched from 'other'.
   * 
   * @since Date: Aug 24, 2011
   * @param other the {@link DateSpecialChar} to compare the values and types with.
   * @return <code>true</code>, if other has same values and types for month, day and year.
   */
  private boolean areValuesAndTypesEqual(final DateSpecialChar other) {
    return isDayEqual(other) && isMonthEqual(other) && isYearEqual(other);
  }

  /**
   * Returns whether value and type of day to the ones of 'other'.
   * 
   * @since Date: Aug 24, 2011
   * @param other the {@link DateSpecialChar} to compare the value and type of day with.
   * @return <code>true</code>, if other has same value and type for day.
   */
  private boolean isDayEqual(final DateSpecialChar other) {
    return this.dayValue == other.dayValue && this.dayValueType == other.dayValueType;
  }

  /**
   * Returns whether value and type of month to the ones of 'other'.
   * 
   * @since Date: Aug 24, 2011
   * @param other the {@link DateSpecialChar} to compare the value and type of month with.
   * @return <code>true</code>, if other has same value and type for month.
   */
  private boolean isMonthEqual(final DateSpecialChar other) {
    return this.monthValue == other.monthValue && this.monthValueType == other.monthValueType;
  }

  /**
   * Returns whether value and type of year to the ones of 'other'.
   * 
   * @since Date: Aug 24, 2011
   * @param other the {@link DateSpecialChar} to compare the value and type of year with.
   * @return <code>true</code>, if other has same value and type for year.
   */
  private boolean isYearEqual(final DateSpecialChar other) {
    return this.yearValue == other.yearValue && this.yearValueType == other.yearValueType;
  }

  @Override
  public String toString() {
    // TODO #9 comment
    return this.specialChar + "|" + this.yearValueType + this.yearValue + "|" + this.monthValueType + this.monthValue
           + "|" + this.dayValueType + this.dayValue;
  }
}
