package com.sandbox.banking.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.sandbox.banking.enums.Type;

public class AccountDTO {

	private long id;

	private BigDecimal initialCredit;

	private Type accountType;

	@NotNull(message = "Please provide the customer identification number")
	private long customerId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getInitialCredit() {
		return initialCredit;
	}

	public void setInitialCredit(BigDecimal initialCredit) {
		this.initialCredit = initialCredit;
	}

	public Type getAccountType() {
		return accountType;
	}

	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", initialCredit=" + initialCredit + ", accountType=" + accountType
				+ ", customerId=" + customerId + "]";
	}

}
