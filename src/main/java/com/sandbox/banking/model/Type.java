package com.sandbox.banking.model;

public enum Type {

	CREDIT(0), DEBIT(1);

	private Integer ordinal;

	private Type(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

}
