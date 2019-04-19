package com.sandbox.banking.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountStatementDTO {

	private BigDecimal balance;

	private List<TransactionDTO> transactions;

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
		return "AccountStatementDTO [balance=" + balance + ", transactions=" + transactions + "]";
	}

}
