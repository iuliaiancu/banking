package com.sandbox.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "balance")
	@NotNull(message = "Balance shouldn't be null")
	private BigDecimal balance;

	@Column(name = "type", columnDefinition = "smallint")
	@NotNull(message = "Account type should be specified: either credit or debit")
	private Type accountType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotNull(message = "Customer id shouldn't be null")
	private Customer customer;

	public Account() {
		super();
	}

	public Account(Long id, @NotNull(message = "Balance shouldn't be null") BigDecimal balance,
			@NotNull(message = "Customer id shouldn't be null") Customer customer) {
		this();
		this.id = id;
		this.balance = balance;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Type getAccountType() {
		return accountType;
	}

	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", accountType=" + accountType + ", customer=" + customer
				+ "]";
	}

}
