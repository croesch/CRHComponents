package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;


/**
 * Provides test methods for {@link Util}
 * 
 * @author croesch
 * @since Date: 17.02.2011 20:43:57
 */
public class UtilTest {

  /**
   * Tests that {@link Util#of(String)} returns an object that is equal to the self created object
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:50:42
   */
  @Test
  @SuppressWarnings("nls")
  public void testMultiply() {
    assertThat(Util.of("")).isEqualTo(new StringUtil(""));
    assertThat(Util.of("")).isNotEqualTo(new StringUtil("a"));
    assertThat(Util.of("a")).isEqualTo(new StringUtil("a"));
    assertThat(Util.of("a")).isNotEqualTo(new StringUtil(""));
  }

  /**
   * Tests that {@link Util#of(String[])} returns an object that is equal to the self created object
   * 
   * @author croesch
   * @since Date: 17.02.2011 20:50:42
   */
  @Test
  @SuppressWarnings("nls")
  public void testAnalyseLengthOf() {
    assertThat(Util.of(new String[] {""})).isEqualTo(new StringArrayUtil(new String[] {""}));
    assertThat(Util.of(new String[] {""})).isNotEqualTo(new StringArrayUtil(new String[] {"a"}));
    assertThat(Util.of(new String[] {"a"})).isEqualTo(new StringArrayUtil(new String[] {"a"}));
    assertThat(Util.of(new String[] {"a"})).isNotEqualTo(new StringArrayUtil(new String[] {""}));
    assertThat(Util.of(new String[] {"", "b"}))
      .isEqualTo(new StringArrayUtil(new String[] {"", "b"}));
    assertThat(Util.of(new String[] {"a", ""})).isNotEqualTo(new StringArrayUtil(new String[] {"",
                                                                                                                 "a"}));
    assertThat(Util.of(new String[] {"a", "bb", "ccc"}))
      .isEqualTo(new StringArrayUtil(new String[] {"a", "bb", "ccc"}));
    assertThat(Util.of(new String[] {"a"})).isNotEqualTo(new StringArrayUtil(new String[] {"a"})
                                                                              .trim());
  }

}
