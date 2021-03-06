/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;

/**
 * Part of the representation of the ASN.1 unrestricted string (CHARACTER STRING) type.
 *
 * @author Kristof Szabados
 */
public class TitanCharacter_String_identification_context__negotiation extends Base_Type {
	TitanInteger presentation__context__id; //ASN1_Integer_Type
	TitanObjectid transfer__syntax; //ObjectID_Type

	public TitanCharacter_String_identification_context__negotiation() {
		presentation__context__id = new TitanInteger();
		transfer__syntax = new TitanObjectid();
	}

	public TitanCharacter_String_identification_context__negotiation( final TitanInteger aPresentation__context__id, final TitanObjectid aTransfer__syntax ) {
		presentation__context__id = new TitanInteger( aPresentation__context__id );
		transfer__syntax = new TitanObjectid( aTransfer__syntax );
	}

	public TitanCharacter_String_identification_context__negotiation( final TitanCharacter_String_identification_context__negotiation aOtherValue ) {
		this();
		assign( aOtherValue );
	}

	public TitanCharacter_String_identification_context__negotiation assign( final TitanCharacter_String_identification_context__negotiation aOtherValue ) {
		if ( !aOtherValue.isBound() ) {
			throw new TtcnError( "Assignment of an unbound value of type CHARACTER STRING.identification.context-negotiation" );
		}

		if (aOtherValue != this) {
			if ( aOtherValue.getPresentation__context__id().isBound() ) {
				this.presentation__context__id.assign( aOtherValue.getPresentation__context__id() );
			} else {
				this.presentation__context__id.cleanUp();
			}
			if ( aOtherValue.getTransfer__syntax().isBound() ) {
				this.transfer__syntax.assign( aOtherValue.getTransfer__syntax() );
			} else {
				this.transfer__syntax.cleanUp();
			}
		}


		return this;
	}

	@Override
	public TitanCharacter_String_identification_context__negotiation assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanCharacter_String_identification_context__negotiation ) {
			return assign((TitanCharacter_String_identification_context__negotiation) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to CHARACTER STRING.identification.context-negotiation", otherValue));
	}

	public void cleanUp() {
		presentation__context__id.cleanUp();
		transfer__syntax.cleanUp();
	}

	public boolean isBound() {
		if ( presentation__context__id.isBound() ) { return true; }
		if ( transfer__syntax.isBound() ) { return true; }
		return false;
	}

	public boolean isPresent() {
		return isBound();
	}

	public boolean isValue() {
		if ( !presentation__context__id.isValue() ) { return false; }
		if ( !transfer__syntax.isValue() ) { return false; }
		return true;
	}

	public boolean operatorEquals( final TitanCharacter_String_identification_context__negotiation aOtherValue ) {
		if ( !this.presentation__context__id.operatorEquals( aOtherValue.presentation__context__id ) ) { return false; }
		if ( !this.transfer__syntax.operatorEquals( aOtherValue.transfer__syntax ) ) { return false; }
		return true;
	}

	@Override
	public boolean operatorEquals(final Base_Type otherValue) {
		if (otherValue instanceof TitanCharacter_String_identification_context__negotiation ) {
			return operatorEquals((TitanCharacter_String_identification_context__negotiation) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to CHARACTER STRING.identification.context-negotiation", otherValue));		}

	public TitanInteger getPresentation__context__id() {
		return presentation__context__id;
	}

	public TitanInteger constGetPresentation__context__id() {
		return presentation__context__id;
	}

	public TitanObjectid getTransfer__syntax() {
		return transfer__syntax;
	}

	public TitanObjectid constGetTransfer__syntax() {
		return transfer__syntax;
	}

	public TitanInteger sizeOf() {
		int sizeof = 0;
		sizeof += 2;
		return new TitanInteger(sizeof);
	}
	public void log() {
		if (!isBound()) {
			TtcnLogger.log_event_unbound();
			return;
		}
		TtcnLogger.log_char('{');
		TtcnLogger.log_event_str(" presentation-context-id := ");
		presentation__context__id.log();
		TtcnLogger.log_char(',');
		TtcnLogger.log_event_str(" transfer-syntax := ");
		transfer__syntax.log();
		TtcnLogger.log_event_str(" }");
	}

	@Override
	public void encode_text(final Text_Buf text_buf) {
		presentation__context__id.encode_text(text_buf);
		transfer__syntax.encode_text(text_buf);
	}

	@Override
	public void decode_text(final Text_Buf text_buf) {
		presentation__context__id.decode_text(text_buf);
		transfer__syntax.decode_text(text_buf);
	}
}