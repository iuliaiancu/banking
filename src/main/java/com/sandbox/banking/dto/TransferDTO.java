package com.sandbox.banking.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Positive;

public class TransferDTO {

	@Positive(message = "The account identifier from which money gets transfered it's a positive integer")
	private long idAccountFrom;

	@Positive(message = "The account identifier to which money gets transfered it's a positive integer")
	private long idAccountTo;

	@Positive(message = "Sum must be greater than 0")
	private BigDecimal sum;

	@Positive(message = "The customer that makes the transaction it's identified though a positive integer customerId")
	private int customerId;

	public long getIdAccountFrom() {
		return idAccountFrom;
	}

	public void setIdAccountFrom(long idAccountFrom) {
		this.idAccountFrom = idAccountFrom;
	}

	public long getIdAccountTo() {
		return idAccountTo;
	}

	public void setIdAccountTo(long idAccountTo) {
		this.idAccountTo = idAccountTo;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "TransferDTO [idAccountFrom=" + idAccountFrom + ", idAccountTo=" + idAccountTo + ", sum=" + sum
				+ ", customerId=" + customerId + "]";
	}

}
