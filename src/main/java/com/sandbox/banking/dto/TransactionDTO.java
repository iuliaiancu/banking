package com.sandbox.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

	private long transactionId;
	private long accountId;
	private BigDecimal sum;
	private LocalDateTime date;

	public TransactionDTO() {
	}

	public TransactionDTO(long accountId, BigDecimal sum) {
		this.accountId = accountId;
		this.sum = sum;
	}

	public TransactionDTO(long transactionId, long accountId, BigDecimal sum, LocalDateTime date) {
		this(accountId, sum);
		this.transactionId = transactionId;
		this.date = date;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TransactionDTO [transactionId=" + transactionId + ", accountId=" + accountId + ", sum=" + sum
				+ ", date=" + date + "]";
	}

}
