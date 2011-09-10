package com.github.croesch.contents.date;

import static org.fest.assertions.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.StringReader;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

import com.github.croesch.contents.date.DateSpecialChar.ValueType;

/**
 * Provides test methods for {@link DateSpecialCharInterpreter}
 * 
 * @author croesch
 * @since Date: Apr 5, 2011 9:41:21 AM
 */
public class DateSpecialCharInterpreterTest extends FestSwingJUnitTestCase {

  /**
   * Test method for {@link DateSpecialCharInterpreter#DateSpecialCharInterpreter()}
   */
  @Test
  public void testConstructor() {
    new DateSpecialCharInterpreter(new BufferedReader(new StringReader("..")));
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testEmptyList() {
    DateSpecialCharInterpreter interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment ")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("...\n\n...")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("asd\n")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("#|c11|i12|o13")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("a|c1|i1|p3")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("a|c1|p1|i3")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("a|p1|i1|c3")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("a|O1|i1|c3")));
    assertThat(interpreter.getSpecialCharsMap()).isEmpty();
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testListIsNotEmpty() {
    DateSpecialCharInterpreter interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("t|o0|o0|o0")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment \nt|o0|o0|o0")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment \nt|o0|o0|o0 # today")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment \nt|o0|o0|o0\t#\ttoday")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment \n t|o0|o0|o0\t#\ttoday")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("# no content\n # comment \n t|o0|o0|o0\t#\ttoday ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tt|o0|o0|o0 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tc|o1|o2|o3 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tc|c1|i2|o3 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tc|c11|i12|o13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c11|i12|o13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\t Z||i12|o13 \t")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c11||o13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c11|i12| ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c11|i12|o-13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c11|i-12|o13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();

    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("\tZ|c-11|i12|o13 ")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testFirstListEntry() {
    DateSpecialChar specialChar = new DateSpecialChar('t',
                                                      ValueType.OFFSET,
                                                      0,
                                                      ValueType.OFFSET,
                                                      0,
                                                      ValueType.OFFSET,
                                                      0);
    DateSpecialCharInterpreter interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("t|o0|o0|o0")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().get("t")).isEqualTo(specialChar);

    specialChar = new DateSpecialChar('i', ValueType.INCREMENT, 1, ValueType.INCREMENT, 2, ValueType.INCREMENT, -3);
    interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader("i|i1|i2|i-3")));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().get("i")).isEqualTo(specialChar);
  }

  /**
   * Test method for {@link DateSpecialCharInterpreter#getSpecialCharsMap()}
   */
  @Test
  public void testListEntries() {
    final String testString = "# key for today\n" + "t|o1|o2|o3\n" + "#another key\n" + "m|o0|o0|o1 # (tomorrow)\n"
                              + "...\n" + "# duplicate key \n" + "t|o0|o0|o0\n" + "# no duplicate key\n" + "T|c1|c2|c3";

    final DateSpecialChar sc1 = new DateSpecialChar('t', ValueType.OFFSET, 0, ValueType.OFFSET, 0, ValueType.OFFSET, 0);
    final DateSpecialChar sc2 = new DateSpecialChar('m', ValueType.OFFSET, 0, ValueType.OFFSET, 0, ValueType.OFFSET, 1);
    final DateSpecialChar sc3 = new DateSpecialChar('T',
                                                    ValueType.CONSTANT,
                                                    1,
                                                    ValueType.CONSTANT,
                                                    2,
                                                    ValueType.CONSTANT,
                                                    3);
    final DateSpecialCharInterpreter interpreter = new DateSpecialCharInterpreter(new BufferedReader(new StringReader(testString)));
    assertThat(interpreter.getSpecialCharsMap()).isNotEmpty();
    assertThat(interpreter.getSpecialCharsMap().values()).containsOnly(sc1, sc2, sc3);
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
