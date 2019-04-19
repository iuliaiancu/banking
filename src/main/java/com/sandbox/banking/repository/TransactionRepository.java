package com.sandbox.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findTransactionsByFromAccount(Account account);

}
