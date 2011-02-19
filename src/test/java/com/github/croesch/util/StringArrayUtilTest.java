package com.github.croesch.util;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Provides some basic test methods for {@link StringArrayUtil}
 * 
 * @author croesch
 * @since Date: 19.02.2011 15:22:35
 */
@SuppressWarnings("nls")
public class StringArrayUtilTest {
  private static String[] arr1_1 = new String[] {};

  private static String[] arr1_2 = new String[] {};

  private static String[] arr2_1 = new String[] {""};

  private static String[] arr2_2 = new String[] {""};

  private static String[] arr3_1 = new String[] {"a", "b", "c", "d"};

  private static String[] arr3_2 = new String[] {"a", "b", "c", "d"};

  /**
   * Test method for {@link StringArrayUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Equal() {
    StringArrayUtil su1 = new StringArrayUtil(arr1_1);
    StringArrayUtil su2 = new StringArrayUtil(arr1_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isTrue();

    su1 = new StringArrayUtil(arr2_1);
    su2 = new StringArrayUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isTrue();

    su1 = new StringArrayUtil(arr3_1);
    su2 = new StringArrayUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isTrue();
  }

  /**
   * Test method for {@link StringArrayUtil#hashCode()}.
   */
  @Test
  public void testHashCode_Unequal() {
    StringArrayUtil su1 = new StringArrayUtil(arr1_1);
    StringArrayUtil su2 = new StringArrayUtil(arr2_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isFalse();

    su1 = new StringArrayUtil(arr1_1);
    su2 = new StringArrayUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isFalse();

    su1 = new StringArrayUtil(arr2_1);
    su2 = new StringArrayUtil(arr3_2);
    assertThat(su1.equals(su2)).isEqualTo(su2.hashCode() == su1.hashCode());
    assertThat(su1.equals(su2)).isFalse();
  }

  /**
   * Test method for {@link StringArrayUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Reflexive() {
    StringArrayUtil sau = new StringArrayUtil(arr1_1);
    assertThat(sau.equals(sau)).isTrue();

    sau = new StringArrayUtil(arr2_1);
    assertThat(sau.equals(sau)).isTrue();

    sau = new StringArrayUtil(arr3_1);
    assertThat(sau.equals(sau)).isTrue();
  }

  /**
   * Test method for {@link StringArrayUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Symmetric() {
    StringArrayUtil sau1 = new StringArrayUtil(arr1_1);
    StringArrayUtil sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau1));
    assertThat(sau1.equals(sau2)).isTrue();
  }

  /**
   * Test method for {@link StringArrayUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Transitivity() {
    StringArrayUtil sau1 = new StringArrayUtil(arr1_1);
    StringArrayUtil sau2 = new StringArrayUtil(arr1_1);
    StringArrayUtil sau3 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_1);
    sau3 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_1);
    sau3 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr1_2);
    sau3 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_2);
    sau3 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_2);
    sau3 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau2.equals(sau3)).isEqualTo(sau1.equals(sau3));
    assertThat(sau1.equals(sau2)).isTrue();
  }

  /**
   * Test method for {@link StringArrayUtil#equals(Object)}.
   */
  @Test
  public void testEquals_Consistency() {
    StringArrayUtil sau1 = new StringArrayUtil(arr1_1);
    StringArrayUtil sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr1_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr2_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_2);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr1_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2))
      .isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2))
      .isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2))
      .isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();

    sau1 = new StringArrayUtil(arr2_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr1_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr2_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isFalse();

    sau1 = new StringArrayUtil(arr3_1);
    sau2 = new StringArrayUtil(arr3_1);
    assertThat(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2))
      .isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2))
      .isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2)).isEqualTo(sau1.equals(sau2));
    assertThat(sau1.equals(sau2)).isTrue();
  }

  /**
   * Test method for {@link StringArrayUtil#equals(Object)}.
   */
  @Test
  public void testEquals_NullFalse() {
    StringArrayUtil sau = new StringArrayUtil(arr1_1);
    assertThat(sau.equals(null)).isFalse();

    sau = new StringArrayUtil(arr1_2);
    assertThat(sau.equals(null)).isFalse();

    sau = new StringArrayUtil(arr2_1);
    assertThat(sau.equals(null)).isFalse();

    sau = new StringArrayUtil(arr2_2);
    assertThat(sau.equals(null)).isFalse();

    sau = new StringArrayUtil(arr3_1);
    assertThat(sau.equals(null)).isFalse();

    sau = new StringArrayUtil(arr3_2);
    assertThat(sau.equals(null)).isFalse();
  }
}
