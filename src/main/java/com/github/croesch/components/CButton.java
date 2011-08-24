package com.github.croesch.components;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import com.github.croesch.util.MnemonicUtil;

/**
 * This button is able to set its mnemonic key automatically.
 * 
 * @author croesch
 * @since (Date: 2010/12/20 17:40:58)
 */
public class CButton extends JButton {

  /** generated version UID */
  private static final long serialVersionUID = 2951525102559877588L;

  /**
   * Simply calls {@link JButton#JButton()}
   * 
   * @see JButton#JButton()
   */
  public CButton() {
    super();
  }

  /**
   * Simply calls {@link JButton#JButton(Icon)}
   * 
   * @see JButton#JButton(Icon)
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CButton(final Icon icon) {
    super(icon);
  }

  /**
   * Simply calls {@link JButton#JButton(String)}
   * 
   * @see JButton#JButton(String)
   * @param text the parameter String that is given to superclass constructor
   */
  public CButton(final String text) {
    super(text);
  }

  /**
   * Simply calls {@link JButton#JButton(Action)}
   * 
   * @see JButton#JButton(Action)
   * @param a the parameter Action that is given to superclass constructor
   */
  public CButton(final Action a) {
    super(a);
  }

  /**
   * Simply calls {@link JButton#JButton(String, Icon)}
   * 
   * @see JButton#JButton(String, Icon)
   * @param text the parameter String that is given to superclass constructor
   * @param icon the parameter Icon that is given to superclass constructor
   */
  public CButton(final String text, final Icon icon) {
    super(text, icon);
  }

  @Override
  public final void setText(final String text) {
    super.setText(MnemonicUtil.filterMnemonic(text, this));
  }
}
