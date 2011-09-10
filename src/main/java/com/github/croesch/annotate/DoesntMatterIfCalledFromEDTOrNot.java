/*
 * Copyright (C) 2011  Christian Roesch
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
