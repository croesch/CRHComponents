package com.github.croesch.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicates that it doesn't matter if you call this from the EDT or not. This type should be used to annotate methods
 * that can either be invoked from EDT or from another thread. How the method ensures this doesn't matter. If you
 * consequently use this type, it will be much easier for clients to decide when they have to be careful.<br>
 * But <b>note</b>: It wouldn't be useful to mark methods with this type that are by default uninteresting, if called
 * from EDT or not. So in most cases this should only be used for methods that manipulate elements of gui.
 * 
 * @author croesch
 * @since Date: Sep 10, 2011
 */
@Documented
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface DoesntMatterIfCalledFromEDTOrNot {
}
