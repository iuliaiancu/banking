package com.sandbox.banking.enums;

public enum Type {

	DEFAULT_ACCOUNT(0), CURRENT_ACCOUNT(1), DEBIT(2), CREDIT(3);

	private Integer ordinal;

	private Type(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

}
