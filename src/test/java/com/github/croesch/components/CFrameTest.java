/*
 * Copyright (C) 2011-2012  Christian Roesch
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
package com.github.croesch.components;

import static org.fest.assertions.Assertions.assertThat;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.junit.Test;

import com.github.croesch.DefaultTestCase;

/**
 * Provides test cases for {@link CFrame}.
 * 
 * @author croesch
 * @since Date: Nov 2, 2012
 */
public class CFrameTest extends DefaultTestCase {

  public static CFrame getFrame(final String name) {
    return GuiActionRunner.execute(new GuiQuery<CFrame>() {
      @Override
      protected CFrame executeInEDT() {
        return new CFrame(name);
      }
    });
  }

  public static CFrame getFrame(final String name, final GraphicsConfiguration config) {
    return GuiActionRunner.execute(new GuiQuery<CFrame>() {
      @Override
      protected CFrame executeInEDT() {
        return new CFrame(name, config);
      }
    });
  }

  public static CFrame getFrame(final String name, final String title, final GraphicsConfiguration config) {
    return GuiActionRunner.execute(new GuiQuery<CFrame>() {
      @Override
      protected CFrame executeInEDT() {
        return new CFrame(name, title, config);
      }
    });
  }

  public static CFrame getFrame(final String name, final String title) {
    return GuiActionRunner.execute(new GuiQuery<CFrame>() {
      @Override
      protected CFrame executeInEDT() {
        return new CFrame(name, title);
      }
    });
  }

  @Test
  public void testFrame_String() {
    CFrame frame = getFrame("hugo");
    assertThat(frame.getName()).isEqualTo("hugo");

    frame = getFrame("fenster");
    assertThat(frame.getName()).isEqualTo("fenster");

    frame = getFrame(null);
    assertThat(frame.getName()).isNull();
  }

  @Test
  public void testFrame_StringGraphicsConfiguration() {
    CFrame frame = getFrame("hugo", (GraphicsConfiguration) null);
    assertThat(frame.getName()).isEqualTo("hugo");
    assertThat(frame.getGraphicsConfiguration()).isNotNull();

    final GraphicsConfiguration[] configurations = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                                                      .getDefaultScreenDevice()
                                                                      .getConfigurations();
    frame = getFrame("fenster", configurations[configurations.length - 1]);
    assertThat(frame.getName()).isEqualTo("fenster");
    assertThat(frame.getGraphicsConfiguration()).isEqualTo(configurations[configurations.length - 1]);

    frame = getFrame(null, configurations[0]);
    assertThat(frame.getName()).isNull();
    assertThat(frame.getGraphicsConfiguration()).isEqualTo(configurations[0]);
  }

  @Test
  public void testFrame_StringString() {
    CFrame frame = getFrame("hugo", (String) null);
    assertThat(frame.getName()).isEqualTo("hugo");
    assertThat(frame.getTitle()).isNull();

    frame = getFrame("fenster", "window");
    assertThat(frame.getName()).isEqualTo("fenster");
    assertThat(frame.getTitle()).isEqualTo("window");

    frame = getFrame(null, "Fenster");
    assertThat(frame.getName()).isNull();
    assertThat(frame.getTitle()).isEqualTo("Fenster");
  }

  @Test
  public void testFrame_StringStringGraphicsConfiguration() {
    CFrame frame = getFrame("hugo", "title", null);
    assertThat(frame.getName()).isEqualTo("hugo");
    assertThat(frame.getGraphicsConfiguration()).isNotNull();
    assertThat(frame.getTitle()).isEqualTo("title");

    final GraphicsConfiguration[] configurations = GraphicsEnvironment.getLocalGraphicsEnvironment()
                                                                      .getDefaultScreenDevice()
                                                                      .getConfigurations();
    frame = getFrame("fenster", null, configurations[configurations.length - 1]);
    assertThat(frame.getName()).isEqualTo("fenster");
    assertThat(frame.getGraphicsConfiguration()).isEqualTo(configurations[configurations.length - 1]);
    assertThat(frame.getTitle()).isNull();

    frame = getFrame(null, "Fenster", configurations[0]);
    assertThat(frame.getName()).isNull();
    assertThat(frame.getGraphicsConfiguration()).isEqualTo(configurations[0]);
    assertThat(frame.getTitle()).isEqualTo("Fenster");
  }
}
