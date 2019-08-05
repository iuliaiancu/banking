package com.sandbox.banking.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sandbox.banking.enums.Type;

public class AccountTransactionDTO {

	private long accountId;

	private Type accountType;

	private BigDecimal balance;

	private List<TransactionDTO> transactions;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Type getAccountType() {
		return accountType;
	}

	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "AccountTransactionDTO [balance=" + balance + ", transactions=" + transactions + "]";
	}

}
