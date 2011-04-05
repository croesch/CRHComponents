package com.github.croesch.contents.date;

/**
 * Container that stores the values of a definition of a special char. Represents the values given by a single < line >
 * defined in {@link DateSpecialCharInterpreter}.
 * 
 * @author croesch
 * @since Date: Apr 5, 2011 10:14:24 AM
 * @see DateSpecialCharInterpreter
 */
public class DateSpecialChar {

  /** the type of the value, if it should be interpreted as a constant, offset or incremental value. */
  public static enum valueType {
    /** constant to interpret the value as a constant. */
    CONSTANT {
      @Override
      public String toString() {
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
  private final valueType yearValueType;

  /** the value for the year */
  private final int yearValue;

  /** the type of the month value */
  private final valueType monthValueType;

  /** the value for the month */
  private final int monthValue;

  /** the type of the day value */
  private final valueType dayValueType;

  /** the value for the day */
  private final int dayValue;

  /**
   * Constructs a {@link DateSpecialChar} with the given values.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:02:36 PM
   * @param c the character to access this definition
   * @param yearType the type of the year value, not <code>null</code>
   * @param yearVal the year value
   * @param monthType the type of the month value, not <code>null</code>
   * @param monthVal the month value
   * @param dayType the type of the day value, not <code>null</code>
   * @param dayVal the day value
   */
  public DateSpecialChar(final char c,
                         final valueType yearType,
                         final int yearVal,
                         final valueType monthType,
                         final int monthVal,
                         final valueType dayType,
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
   * @since Date: Apr 5, 2011 9:03:55 PM
   * @return the special character that need to be entered to access this definition.
   */
  public char getChar() {
    return this.specialChar;
  }

  /**
   * Returns the type of the year value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:04:28 PM
   * @return the enum that represents the type of the year value
   */
  public valueType getYearValueType() {
    return this.yearValueType;
  }

  /**
   * Returns the year value
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:04:55 PM
   * @return the year value
   */
  public int getYearValue() {
    return this.yearValue;
  }

  /**
   * Returns the type of the month value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:05:14 PM
   * @return the enum that represents the type of the month value.
   */
  public valueType getMonthValueType() {
    return this.monthValueType;
  }

  /**
   * Returns the month value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:05:41 PM
   * @return the month value.
   */
  public int getMonthValue() {
    return this.monthValue;
  }

  /**
   * Returns the type of the day value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:05:57 PM
   * @return the enum that represents the type of the day value.
   */
  public valueType getDayValueType() {
    return this.dayValueType;
  }

  /**
   * Returns the day value.
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:06:22 PM
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
    result = prime * result + ((this.dayValueType == null) ? 0 : this.dayValueType.hashCode());
    result = prime * result + this.monthValue;
    result = prime * result + ((this.monthValueType == null) ? 0 : this.monthValueType.hashCode());
    result = prime * result + this.yearValue;
    result = prime * result + ((this.yearValueType == null) ? 0 : this.yearValueType.hashCode());
    result = prime * result + this.specialChar;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof DateSpecialChar)) {
      return false;
    }
    final DateSpecialChar other = (DateSpecialChar) obj;
    return equals(other);
  }

  /**
   * Returns whether all of the internal fields are the same as the fields of the given {@link DateSpecialChar}
   * 
   * @author croesch
   * @since Date: Apr 5, 2011 9:06:47 PM
   * @param other the other {@link DateSpecialChar} to compare with this
   * @return <code>true</code>, if all the fields are the same
   */
  private boolean equals(final DateSpecialChar other) {
    if (this.specialChar == other.specialChar && this.dayValue == other.dayValue
        && this.dayValueType == other.dayValueType && this.monthValue == other.monthValue
        && this.monthValueType == other.monthValueType && this.yearValue == other.yearValue
        && this.yearValueType == other.yearValueType) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    return this.specialChar + "|" + this.yearValueType + this.yearValue + "|" + this.monthValueType + this.monthValue
           + "|" + this.dayValueType + this.dayValue;
  }
}
