/******************************************************************************
 * Copyright (c) 2000-2017 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

/**
 * TTCN-3 boolean
 * @author Kristof Szabados
 */
public abstract class Base_Type {

	public abstract TitanBoolean isPresent();
	public abstract TitanBoolean isBound();

	public TitanBoolean isValue() {
		return isBound();
	}

	public boolean isOptional() {
		return false;
	}

	public abstract TitanBoolean operatorEquals(final Base_Type otherValue);

	public abstract Base_Type assign( final Base_Type otherValue );
	public abstract void log();
}
