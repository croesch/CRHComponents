package com.github.croesch.contents;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import javax.swing.text.BadLocationException;

import org.junit.Before;
import org.junit.Test;

import com.github.croesch.listener.ErrorListener;

/**
 * Provides test cases for {@link CContent}
 * 
 * @author croesch
 * @since Date: 09.02.2011 21:26:46
 */
@SuppressWarnings("nls")
public class CContentTest {

  private CContent cont;

  private boolean bool;

  /**
   * Initialises the {@link CContent} and the {@link #bool}
   * 
   * @author croesch
   * @since Date: 10.02.2011 18:48:12
   */
  @Before
  public void setUp() {
    this.cont = new CContent();
    this.bool = false;
  }

  /**
   * Tests the {@link CContent#remove(int, int)}
   * 
   * @author croesch
   * @since Date: 10.02.2011 18:47:46
   */
  @Test
  public void testRemove() {
    try {
      this.cont.insertString(0, "Not yet implemented", null);
      this.cont.remove(0, 8);
    } catch (final BadLocationException e) {
      fail();
    }
    assertThat(this.cont.getText()).isEqualTo("implemented");
  }

  /**
   * Tests the {@link CContent#isValidInput(int, String)}
   * 
   * @author croesch
   * @since Date: 09.02.2011 21:42:07
   * @throws BadLocationException if test fails
   */
  @Test
  public void testIsValidInputIntString() throws BadLocationException {
    assertThat(this.cont.isValidInput(0, "alfred")).isTrue();
    assertThat(this.cont.isValidInput(0, null)).isTrue();
    this.cont.insertString(0, null, null);
    this.cont.setMaximumInputLength(5);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
    this.cont.insertString(0, "alf", null);
    assertThat(this.cont.isValidInput(0, "alf")).isFalse();
    this.cont.insertString(0, "alf", null);
    this.cont.setMaximumInputLength(6);
    assertThat(this.cont.isValidInput(0, "alf")).isFalse();
    this.cont.setMaximumInputLength(9);
    assertThat(this.cont.isValidInput(0, "alf")).isTrue();
  }

  /**
   * Ensures that {@link CContent#isValidInput(int, String)} throws a {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 09.02.2011 21:42:07
   */
  @Test(expected = BadLocationException.class)
  public void testIsValidInputIntString_BLE1() throws BadLocationException {
    this.cont.isValidInput(-1, "badlocation");
  }

  /**
   * Ensures that {@link CContent#isValidInput(int, String)} throws a {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 09.02.2011 21:42:07
   */
  @Test(expected = BadLocationException.class)
  public void testIsValidInputIntString_BLE2() throws BadLocationException {
    this.cont.isValidInput(1, "badlocation");
  }

  /**
   * Tests the {@link CContent#isValid()}
   * 
   * @author croesch
   * @since Date: 10.02.2011 18:41:38
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testIsValid() throws BadLocationException {
    assertThat(this.cont.isValid()).isTrue();
    this.cont.insertString(0, "text", null);
    assertThat(this.cont.isValid()).isTrue();
    this.cont.setMaximumInputLength(3);
    assertThat(this.cont.isValid()).isFalse();
    this.cont.remove(3, 1);
    assertThat(this.cont.isValid()).isTrue();
  }

  /**
   * Tests the {@link CContent#isValidInput(String)}
   */
  @Test
  public void testIsValidInputString() {
    assertThat(this.cont.isValidInput("text")).isTrue();
    this.cont.setMaximumInputLength(10);
    assertThat(this.cont.isValidInput("text")).isTrue();
    this.cont.setMaximumInputLength(4);
    assertThat(this.cont.isValidInput("text")).isTrue();
    this.cont.setMaximumInputLength(2);
    assertThat(this.cont.isValidInput("text")).isFalse();
    this.cont.setMaximumInputLength(-5);
    assertThat(this.cont.isValidInput(null)).isFalse();
    this.cont.setMaximumInputLength(0);
    assertThat(this.cont.isValidInput(null)).isFalse();
    this.cont.setMaximumInputLength(10);
    assertThat(this.cont.isValidInput(null)).isFalse();
  }

  /**
   * Tests whether false is always returned by {@link CContent#isValidSpecialInput(String)}
   */
  @Test
  public void testIsValidSpecialInput() {
    assertThat(this.cont.isValidSpecialInput(null)).isFalse();
    assertThat(this.cont.isValidSpecialInput("f")).isFalse();
    assertThat(this.cont.isValidSpecialInput("d")).isFalse();
  }

  /**
   * Tests the {@link CContent#isErrorsNotifying()} and {@link CContent#setErrosNotifying(boolean)}
   * 
   * @author croesch
   * @since Date: 09.02.2011 21:44:28
   */
  @Test
  public void testSetIsDisplayingErrors() {
    assertThat(this.cont.isErrorsNotifying()).isTrue();
    this.cont.setErrosNotifying(false);
    assertThat(this.cont.isErrorsNotifying()).isFalse();
    this.cont.setErrosNotifying(true);
    assertThat(this.cont.isErrorsNotifying()).isTrue();
    this.cont.setErrosNotifying(true);
    assertThat(this.cont.isErrorsNotifying()).isTrue();
    this.cont.setErrosNotifying(false);
    assertThat(this.cont.isErrorsNotifying()).isFalse();
    this.cont.setErrosNotifying(true);
    this.cont.setErrosNotifying(true);
    assertThat(this.cont.isErrorsNotifying()).isTrue();
    this.cont.setErrosNotifying(false);
    this.cont.setErrosNotifying(true);
    this.cont.setErrosNotifying(false);
    assertThat(this.cont.isErrorsNotifying()).isFalse();
  }

  /**
   * Tests adding and removing a listener
   * 
   * @author croesch
   * @since Date: 09.02.2011 22:02:08
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testAddErrorListeners() throws BadLocationException {
    this.bool = false;
    final ErrorListener l1 = new ErrorListener() {
      private boolean invoked = false;

      @Override
      public void errorStateChanged(final boolean error) {
        // should be invoked only once
        if (this.invoked) {
          fail();
        }
        this.invoked = true;
      }
    };
    final ErrorListener l2 = new ErrorListener() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void errorStateChanged(final boolean error) {
        CContentTest.this.bool = error;
      }
    };
    this.cont.addErrorListeners(l1);
    this.cont.addErrorListeners(l1); // should not change anything
    this.cont.addErrorListeners(l2);
    this.cont.insertString(0, "blablablablublabla", null);
    this.cont.removeErrorListeners(l1);

    this.cont.setMaximumInputLength(5); // will inform listeners
    assertThat(this.bool).isTrue(); // value changed by listener

    this.cont.insertString(0, "bl", null);
    assertThat(this.bool).isTrue(); // should still be the same

    this.cont.setMaximumInputLength(0); // will inform listeners
    assertThat(this.bool).isFalse(); // value changed by listener
    this.cont.setMaximumInputLength(20); // will inform listeners
    assertThat(this.bool).isFalse(); // should still be the same

    this.cont.insertString(0, "b", null);
    assertThat(this.bool).isTrue(); // value changed by listener

    this.cont.remove(1, 1); // will inform listeners
    assertThat(this.bool).isFalse(); // value changed by listener

    this.cont.removeErrorListeners(l1);
    this.cont.replace(5, 2, "foo", null); // will inform listeners
    assertThat(this.bool).isTrue(); // value changed by listener

    this.cont.removeErrorListeners(l2);
    this.cont.remove(1, 1); // will inform listeners, but there are non
    assertThat(this.bool).isTrue(); // shouldn't be changed

    this.cont.setMaximumInputLength(0);
    assertThat(this.cont.isValid()).isTrue();
    this.cont.addErrorListeners(l2);
    assertThat(this.bool).isFalse(); // should be changed
  }

  /**
   * Tests the {@link CContent#removeErrorListeners(ErrorListener...)}
   * 
   * @author croesch
   * @since Date: 10.02.2011 18:51:50
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testRemoveErrorListeners() throws BadLocationException {
    final ErrorListener el = new ErrorListener() {
      private boolean invoked = false;

      @Override
      public void errorStateChanged(final boolean error) {
        // should be invoked only once
        if (this.invoked) {
          fail();
        }
        this.invoked = true;
      }
    };
    this.cont.insertString(0, "string", null);

    this.cont.removeErrorListeners(el, el, el, el);
    this.cont.addErrorListeners(el);
    this.cont.addErrorListeners(el);
    this.cont.removeErrorListeners(el);

    this.cont.setMaximumInputLength(1);
  }

  /**
   * Tests the {@link CContent#getMaximumInputLength()} and {@link CContent#setMaximumInputLength(int)}
   * 
   * @author croesch
   * @since Date: 09.02.2011 21:47:33
   */
  @Test
  public void testGetSetMaximumInputLength() {
    assertThat(this.cont.getMaximumInputLength()).isZero();
    this.cont.setMaximumInputLength(-12);
    assertThat(this.cont.getMaximumInputLength()).isZero();
    this.cont.setMaximumInputLength(12);
    assertThat(this.cont.getMaximumInputLength()).isEqualTo(12);
    this.cont.setMaximumInputLength(12);
    assertThat(this.cont.getMaximumInputLength()).isEqualTo(12);
    this.cont.setMaximumInputLength(-12);
    assertThat(this.cont.getMaximumInputLength()).isZero();
    this.cont.setMaximumInputLength(11);
    assertThat(this.cont.getMaximumInputLength()).isEqualTo(11);
    this.cont.setMaximumInputLength(-5);
    assertThat(this.cont.getMaximumInputLength()).isZero();
  }

  /**
   * Tests the {@link CContent#getText()}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:09:10
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testGetText() throws BadLocationException {
    assertThat(this.cont.getText()).isEmpty();
    this.cont.insertString(0, "foo", null);
    assertThat(this.cont.getText()).isEqualTo("foo");
    this.cont.insertString(0, "bar", null);
    assertThat(this.cont.getText()).isEqualTo("barfoo");
    this.cont.insertString(0, null, null);
    assertThat(this.cont.getText()).isEqualTo("barfoo");
    this.cont.remove(0, 6);
    assertThat(this.cont.getText()).isEmpty();
  }

  /**
   * Tests the {@link CContent#checkForErrors()}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:08:48
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testCheckForErrors() throws BadLocationException {
    this.bool = true;
    final ErrorListener el = new ErrorListener() {
      private boolean invoked = false;

      @SuppressWarnings("synthetic-access")
      @Override
      public void errorStateChanged(final boolean error) {
        // should be invoked only once
        if (this.invoked) {
          fail();
        }
        this.invoked = true;
        CContentTest.this.bool = error;
      }
    };
    this.cont.addErrorListeners(el);
    assertThat(this.bool).isFalse();
    this.cont.insertString(0, "text", null);
    this.cont.setErrosNotifying(false);
    this.cont.setMaximumInputLength(3);
    this.cont.checkForErrors();
    this.cont.setErrosNotifying(true);
    this.cont.setMaximumInputLength(4);
  }

  /**
   * Tests the {@link CContent#insertString(int, String, javax.swing.text.AttributeSet)}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   * @throws BadLocationException if something went wrong
   */
  @Test
  public void testInsertStringIntStringAttributeSet() throws BadLocationException {
    assertThat(this.cont.getText()).isEmpty();
    this.cont.insertString(0, "chj", null);
    assertThat(this.cont.getText()).isEqualTo("chj");
    this.cont.insertString(0, "ab", null);
    assertThat(this.cont.getText()).isEqualTo("abchj");
    this.cont.insertString(3, "deg", null);
    assertThat(this.cont.getText()).isEqualTo("abcdeghj");
    this.cont.insertString(5, "f", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghj");
    this.cont.insertString(5, null, null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghj");
    this.cont.insertString(8, "i", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghij");
    this.cont.insertString(10, "k", null);
    assertThat(this.cont.getText()).isEqualTo("abcdefghijk");
    try {
      this.cont.insertString(12, "k", null);
      fail();
    } catch (final BadLocationException e) {
      // expected
    }
  }

  /**
   * Tests that {@link CContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test(expected = BadLocationException.class)
  public void testInsertStringIntStringAttributeSet_BLE1() throws BadLocationException {
    this.cont.insertString(1, "k", null);
  }

  /**
   * Tests that {@link CContent#insertString(int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test(expected = BadLocationException.class)
  public void testInsertStringIntStringAttributeSet_BLE2() throws BadLocationException {
    this.cont.insertString(-1, "k", null);
  }

  /**
   * Tests the {@link CContent#replace(int, int, String, javax.swing.text.AttributeSet)}
   * 
   * @author croesch
   * @throws BadLocationException if something went wrong
   * @since Date: 10.02.2011 19:20:40
   */
  @Test
  public void testReplaceIntIntStringAttributeSet() throws BadLocationException {
    this.cont.insertString(0, "hotdog", null);
    this.cont.replace(3, 3, "cat", null);
    assertThat(this.cont.getText()).isEqualTo("hotcat");
    this.cont.replace(0, 3, "cold", null);
    assertThat(this.cont.getText()).isEqualTo("coldcat");
    this.cont.replace(3, 2, null, null);
    assertThat(this.cont.getText()).isEqualTo("colat");
    this.cont.replace(4, 1, "", null);
    assertThat(this.cont.getText()).isEqualTo("cola");
    this.cont.replace(0, 1, "To", null);
    assertThat(this.cont.getText()).isEqualTo("Toola");
    this.cont.replace(3, 2, "l", null);
    assertThat(this.cont.getText()).isEqualTo("Tool");
  }

  /**
   * Tests that {@link CContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE1() throws BadLocationException {
    this.cont.replace(-1, 0, "k", null);
  }

  /**
   * Tests that {@link CContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE2() throws BadLocationException {
    this.cont.replace(1, 0, "k", null);
  }

  /**
   * Tests that {@link CContent#replace(int, int, String, javax.swing.text.AttributeSet)} throws a
   * {@link BadLocationException}
   * 
   * @author croesch
   * @since Date: 10.02.2011 19:13:48
   */
  @Test(expected = BadLocationException.class)
  public void testReplaceIntIntStringAttributeSet_BLE3() throws BadLocationException {
    this.cont.replace(0, 1, "k", null);
  }
}
