package com.github.croesch.contents;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.croesch.contents.DateContent;

import net.miginfocom.swing.MigLayout;

/**
 * TODO Comment here ???
 * 
 * @author croesch
 * @since Date: 28.01.2011 20:37:51
 */
public class DateTestFrame extends JFrame {

  private final JPanel contentPane;

  private final JTextField textField;

  /**
   * Launch the application.
   */
  public static void main(final String[] args) {
    EventQueue.invokeLater(new Runnable(){
      public void run() {
        try {
          final DateTestFrame frame = new DateTestFrame();
          frame.setVisible(true);
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public DateTestFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(new MigLayout("", "[]", "[]"));

    this.textField = new JTextField();
    this.textField.setDocument(new DateContent());
    this.contentPane.add(this.textField, "cell 0 0,grow");
    this.textField.setColumns(10);
  }

}
