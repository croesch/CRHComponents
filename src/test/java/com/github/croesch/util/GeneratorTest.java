package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;

import com.github.croesch.util.generator.LocaleGenerator;

/**
 * Provides test methods for {@link Generator}
 * 
 * @author croesch
 * @since Date: 10.03.2011 19:05:15
 */
public class GeneratorTest {

  @Test(expected = IllegalArgumentException.class)
  public void testGenerateFileNames_IAE() {
    Generator.generateFrom(null);
  }

  @Test
  public void testGenerateFileNames() {
    Locale loc = new Locale("DE", "de");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("DE", "de", "rlp");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("DE");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = Locale.getDefault();
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = Locale.GERMAN;
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("", "");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
    loc = new Locale("", "", "");
    assertThat(Generator.generateFrom(loc)).isEqualTo(new LocaleGenerator(loc));
  }

}
