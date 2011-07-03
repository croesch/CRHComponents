package com.github.croesch.components;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JFrame;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.fest.swing.testing.FestSwingTestCaseTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Jul 3, 2011
 */
public class CDateFieldTest extends FestSwingTestCaseTemplate {

  private JTextComponentFixture field;

  @Before
  public void setUp() {
    setUpRobot();
    final FrameFixture f = new FrameFixture(robot(), GuiActionRunner.execute(new GuiQuery<JFrame>() {

      @Override
      protected JFrame executeInEDT() throws Throwable {
        final JFrame f = new JFrame();
        f.add(new CDateField(Locale.GERMAN));
        f.setPreferredSize(new Dimension(100, 50));
        return f;
      }
    }));
    f.show();
    this.field = f.textBox();
  }

  @After
  public void tearDown() {
    cleanUp();
  }

  @Test
  public final void testEnterDate1() {
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(1);
    this.field.enterText("2");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText(".");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(3);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText(".");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(6);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("9");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.enterText("7");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(9);
    this.field.enterText("6");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(10);
    this.field.requireText("12.01.1976");
  }

  @Test
  public final void testEnterDate2() {
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(1);
    this.field.enterText("2");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("9");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.enterText("7");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(9);
    this.field.enterText("6");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(10);
    this.field.requireText("12.01.1976");
  }

  @Test
  public final void testEnterDate3() {
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(1);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(4);
    this.field.enterText("1");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(7);
    this.field.enterText("0");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(8);
    this.field.requireText("01.01.2000");

    this.field.deleteText();

    this.field.enterText("4");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(2);
    this.field.enterText("4");
    assertThat(this.field.target.getCaretPosition()).isEqualTo(5);
    this.field.enterText("4");
    this.field.requireText("04.04.2004");
  }
}
