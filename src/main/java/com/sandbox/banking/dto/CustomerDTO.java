package com.sandbox.banking.dto;

import java.util.Set;

public class CustomerDTO {

	private String name;
	private String surname;
	private Set<AccountTransactionDTO> accountTransactions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<AccountTransactionDTO> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(Set<AccountTransactionDTO> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	@Override
	public String toString() {
		return "CustomerDTO [name=" + name + ", surname=" + surname + ", accounts=" + accountTransactions + "]";
	}

}
