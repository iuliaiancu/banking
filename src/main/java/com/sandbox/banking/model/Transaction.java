package com.sandbox.banking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "sum")
	@NotNull
	private BigDecimal sum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_account")
	@NotNull
	private Account account;

	@Column(name = "date")
	@NotNull
	private LocalDateTime date;

	public Transaction() {
	};

	public Transaction(BigDecimal sum, Account account, LocalDateTime date) {
		this.sum = sum;
		this.account = account;
		this.date = date;
	}

	public Transaction(long id, BigDecimal sum, Account account, LocalDateTime date) {
		this(sum, account, date);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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
		Transaction other = (Transaction) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", sum=" + sum + ", account=" + account + ", date=" + date + "]";
	}

}
