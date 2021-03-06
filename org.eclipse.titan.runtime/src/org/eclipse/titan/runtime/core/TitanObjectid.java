/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Runtime class for object identifiers (objid)
 *
 * @author Gergo Ujhelyi
 * */
public class TitanObjectid extends Base_Type {

	public static final int MIN_COMPONENTS = 2;

	private int n_components; // number of elements in components_ptr (min. 2)
	private int overflow_idx; // index of the first overflow, or -1
	private List<TitanInteger> components_ptr;

	public TitanObjectid() {

	}

	public TitanObjectid(final int init_n_components, final TitanInteger... values) {
		if (init_n_components < 0) {
			throw new TtcnError("Initializing an objid value with a negative number of components.");
		}

		// TODO check n_components >= 2
		n_components = init_n_components;
		overflow_idx = -1;
		components_ptr = new ArrayList<TitanInteger>(values.length);
		for (int i = 0; i < values.length; i++) {
			components_ptr.add(values[i]);
		}
	}

	public TitanObjectid(final int init_n_components, final TitanInteger init_components) {
		if (init_n_components < 0) {
			throw new TtcnError("Initializing an objid value with a negative number of components.");
		}

		// TODO check n_components >= 2
		n_components = init_n_components;
		overflow_idx = -1;
		components_ptr = new ArrayList<TitanInteger>();
		components_ptr.add(init_components);
	}

	public TitanObjectid(final TitanObjectid otherValue) {
		if (otherValue.components_ptr == null) {
			throw new TtcnError("Copying an unbound objid value.");
		}

		components_ptr = new ArrayList<TitanInteger>();
		components_ptr.addAll(otherValue.components_ptr);
		n_components = otherValue.n_components;
		overflow_idx = otherValue.overflow_idx;
	}

	public void cleanUp() {
		components_ptr = null;
	}

	// originally operator=
	public TitanObjectid assign(final TitanObjectid otherValue) {
		if (otherValue.components_ptr == null) {
			throw new TtcnError("Assignment of an unbound objid value.");
		}

		cleanUp();
		components_ptr = new ArrayList<TitanInteger>();
		components_ptr.addAll(otherValue.components_ptr);
		n_components = otherValue.n_components;
		overflow_idx = otherValue.overflow_idx;

		return this;
	}

	// originally operator=
	@Override
	public Base_Type assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanObjectid) {
			return assign((TitanObjectid) otherValue);
		} else {
			throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to objectid", otherValue));
		}
	}

	// originally operator==
	public boolean operatorEquals(final TitanObjectid otherValue) {
		if (components_ptr == null) {
			throw new TtcnError("The left operand of comparison is an unbound objid value.");
		}
		if (otherValue.components_ptr == null) {
			throw new TtcnError("The right operand of comparison is an unbound objid value.");
		}

		if (n_components != otherValue.n_components) {
			return false;
		}
		if (overflow_idx != otherValue.overflow_idx) {
			return false;
		}

		for (int i = 0; i < components_ptr.size(); i++) {
			if (!components_ptr.get(i).operatorEquals(otherValue.components_ptr.get(i))) {
				return false;
			}
		}
		return true;
	}

	// originally operator==
	@Override
	public boolean operatorEquals(final Base_Type otherValue) {
		if (otherValue instanceof TitanObjectid) {
			return operatorEquals((TitanObjectid) otherValue);
		} else {
			return false;
		}
	}

	@Override
	public boolean isPresent() {
		return components_ptr != null;
	}

	@Override
	public boolean isBound() {
		return components_ptr != null;
	}

	// originally operator[]
	public final TitanInteger constGetAt(final int index_value) {
		if (components_ptr == null) {
			if (index_value != 0) {
				throw new TtcnError("Accessing a component of an unbound objid value.");
			}
			n_components = 1;
			overflow_idx = -1;
			components_ptr = new ArrayList<TitanInteger>();
			components_ptr.add(new TitanInteger(0));

			return components_ptr.get(0);
		}
		if (index_value < 0) {
			throw new TtcnError(MessageFormat.format("Accessing an objid component using a negative index {0}.", index_value));
		}
		if (index_value > n_components) {
			throw new TtcnError(MessageFormat.format("Index overflow when accessing an objid component: the index is {0}, but the value has only {1} components.", index_value, n_components));
		} else if (index_value == n_components) {
			n_components++;
			components_ptr.add(new TitanInteger(0));
		}

		return components_ptr.get(index_value);
	}

	// originally operator[]
	public final TitanInteger constGetAt(final TitanInteger index_value) {
		index_value.mustBound("Indexing a objid component with an unbound integer value.");

		return constGetAt(index_value.getInt());
	}

	// originally operator[]
	public TitanInteger getAt(final int index_value) {
		if (components_ptr == null) {
			if (index_value != 0) {
				throw new TtcnError("Accessing a component of an unbound objid value.");
			}
			n_components = 1;
			overflow_idx = -1;
			components_ptr = new ArrayList<TitanInteger>();
			components_ptr.add(new TitanInteger(0));

			return components_ptr.get(0);
		}
		if (index_value < 0) {
			throw new TtcnError(MessageFormat.format("Accessing an objid component using a negative index {0}.", index_value));
		}
		if (index_value > n_components) {
			throw new TtcnError(MessageFormat.format("Index overflow when accessing an objid component: the index is {0}, but the value has only {1} components.", index_value, n_components));
		} else if (index_value == n_components) {
			n_components++;
			components_ptr.add(new TitanInteger(0));
		}

		return components_ptr.get(index_value);
	}

	// originally operator[]
	public TitanInteger getAt(final TitanInteger index_value) {
		index_value.mustBound("Indexing a objid component with an unbound integer value.");

		return getAt(index_value.getInt());
	}

	public TitanInteger sizeOf() {
		if (components_ptr == null) {
			throw new TtcnError("Getting the size of an unbound objid value.");
		}

		return new TitanInteger(n_components);
	}

	public static TitanInteger from_integer(final TitanInteger p_int) {
		if (p_int.isLessThan(0)) {
			throw new TtcnError("An OBJECT IDENTIFIER component cannot be negative");
		}

		return new TitanInteger(p_int);
	}

	public void log() {
		if (components_ptr != null) {
			TtcnLogger.log_event_str("objid { ");
			for (int i = 0; i < n_components; i++) {
				if (i == overflow_idx) {
					TtcnLogger.log_event_str("overflow:");
				}
				TtcnLogger.log_event_str(components_ptr.get(i).toString());
				TtcnLogger.log_char(' ');
			}
			TtcnLogger.log_char('}');
		} else {
			TtcnLogger.log_event_unbound();
		}
	}

	@Override
	/** {@inheritDoc} */
	public void encode_text(final Text_Buf text_buf) {
		if (components_ptr == null) {
			throw new TtcnError("Text encoder: Encoding an unbound objid value.");
		}

		text_buf.push_int(n_components);
		for (int i = 0; i < n_components; i++) {
			text_buf.push_int(components_ptr.get(i));
		}
	}

	@Override
	/** {@inheritDoc} */
	public void decode_text(final Text_Buf text_buf) {
		cleanUp();

		n_components = text_buf.pull_int().getInt();
		if (n_components < 0) {
			throw new TtcnError("Text decoder: Negative number of components was received for an objid value.");
		}
		components_ptr = new ArrayList<TitanInteger>(n_components);
		for (int i = 0; i < n_components; i++) {
			components_ptr.add(text_buf.pull_int());
		}
	}
}
