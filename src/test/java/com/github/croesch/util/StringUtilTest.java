package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.junit.testcase.FestSwingJUnitTestCase;
import org.junit.Test;

/**
 * Provides some basic test methods for {@link StringUtil}
 * 
 * @author croesch
 * @since Date: 19.02.2011 15:22:35
 */
public class StringUtilTest extends FestSwingJUnitTestCase {
  private static String arr1_1 = "";

  private static String arr1_2 = "";

  private static String arr2_1 = "String 2";

  private static String arr2_2 = "String 2";

  private static String arr3_1 = "yet another string";

  private static String arr3_2 = "yet another string";

  /**
   * Test method for {@link StringUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Equal() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Unequal() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1).isNotEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Reflexive() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isEqualTo(su);

    su = new StringUtil(arr2_1);
    assertThat(su).isEqualTo(su);

    su = new StringUtil(arr3_1);
    assertThat(su).isEqualTo(su);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Symmetric() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su1));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Transitivity() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_1);
    StringUtil su3 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    su3 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    su3 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_2);
    su3 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    su3 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    su3 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.equals(su3)).isEqualTo(su1.equals(su3));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Consistency() {
    StringUtil su1 = new StringUtil(arr1_1);
    StringUtil su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr1_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);

    su1 = new StringUtil(arr2_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr1_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr2_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2));
    assertThat(su1).isNotEqualTo(su2);

    su1 = new StringUtil(arr3_1);
    su2 = new StringUtil(arr3_1);
    assertThat(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2)).isEqualTo(su1.equals(su2))
      .isEqualTo(su1.equals(su2));
    assertThat(su1).isEqualTo(su2);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_NullFalse() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr1_2);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr2_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr2_2);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr3_1);
    assertThat(su).isNotEqualTo(null);

    su = new StringUtil(arr3_2);
    assertThat(su).isNotEqualTo(null);
  }

  /**
   * Test method for {@link StringUtil#equals(Object)}.
   */
  @Test
  public void testEquals_OtherFalse() {
    StringUtil su = new StringUtil(arr1_1);
    assertThat(su).isNotEqualTo(arr1_1);

    su = new StringUtil(arr1_2);
    assertThat(su).isNotEqualTo(arr1_2);

    su = new StringUtil(arr2_1);
    assertThat(su).isNotEqualTo(arr2_1);

    su = new StringUtil(arr2_2);
    assertThat(su).isNotEqualTo(arr2_2);

    su = new StringUtil(arr3_1);
    assertThat(su).isNotEqualTo(arr3_1);

    su = new StringUtil(arr3_2);
    assertThat(su).isNotEqualTo(arr3_2);
  }

  @Override
  protected void onSetUp() {
    // nothing to be set up
  }
}
