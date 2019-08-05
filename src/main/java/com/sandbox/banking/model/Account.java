package com.sandbox.banking.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.sandbox.banking.enums.Type;

@Entity(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "balance")
	@NotNull(message = "Initial credit shouldn't be null")
	private BigDecimal balance;

	@Column(name = "type", columnDefinition = "smallint")
	@NotNull
	private Type type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotNull
	private Customer customer;

	@OneToMany(mappedBy = "account")
	private List<Transaction> accountTransactions;

	public Account() {

	}

	public Account(Customer customer, BigDecimal balance, Type type) {
		this.customer = customer;
		this.balance = balance;
		this.type = type;
		accountTransactions = new ArrayList<>();
	}

	public Account(long id, Customer customer, BigDecimal balance, Type type) {
		this(customer, balance, type);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(List<Transaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", type=" + type + ", customer=" + customer
				+ ", accountTransactions=" + accountTransactions + "]";
	}

}
