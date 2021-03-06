/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.util.ArrayList;
import java.util.List;

/**
 * ASN.1 teletex string
 *
 * @author Kristof Szabados
 *
 * TODO this and template version might need to overwrite some functions
 */
public class TitanTeletexString extends TitanUniversalCharString {
	public static TitanTeletexString TTCN_ISO2022_2_TeletexString(final TitanOctetString p_os) {
		final char osstr[] = p_os.getValue();
		final int len = osstr.length;
		final ArrayList<TitanUniversalChar> ucstr = new ArrayList<TitanUniversalChar>(len);

		for (int i = 0; i < len; i++) {
			ucstr.add(new TitanUniversalChar((char) 0, (char) 0, (char) 0, osstr[i]));
		}

		return new TitanTeletexString(ucstr);
	}

	public TitanTeletexString(final List<TitanUniversalChar> aOtherValue) {
		super(aOtherValue);
	}
}
