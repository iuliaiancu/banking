package com.sandbox.banking.dto;

import java.math.BigDecimal;

public class TransactionDTO {

	private long id;
	private long fromAccountId;
	private long toAccountId;
	private BigDecimal sum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "TransactionDTO [id=" + id + ", fromAccountId=" + fromAccountId + ", toAccountId=" + toAccountId
				+ ", sum=" + sum + "]";
	}

}
