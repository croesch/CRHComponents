package com.github.croesch.listener;

/**
 * Listener, that listens to error changes
 * 
 * @author croesch
 * @since Date: 2011/01/13
 */
public abstract class ErrorListener {

  /**
   * Will be invoked when the state of error changes
   * 
   * @param error {@code true}, if there is an error now
   * @since Date: 13.01.2011
   */
  public abstract void errorStateChanged(boolean error);
}
