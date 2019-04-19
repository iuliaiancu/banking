package com.sandbox.banking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.banking.converter.TransactionConverter;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.MoneyService;

@Service("moneyService")
public class MoneyServiceImpl implements MoneyService {

	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private TransactionConverter transactionConverter;

	@Autowired
	public MoneyServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
			TransactionConverter transactionConverter) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.transactionConverter = transactionConverter;
	}

	/**
	 * transferDTO: a structure used for transferring money between two different
	 * accounts returns: the transaction that was made
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TransactionDTO sendMoneyBetweenTwoAccounts(TransferDTO transferDTO) {
		Account fromAccount = accountRepository.findById(transferDTO.getIdAccountFrom()).orElse(null);
		Account toAccount = accountRepository.findById(transferDTO.getIdAccountTo()).orElse(null);
		BigDecimal fromAccountNewBalance = fromAccount.getBalance().subtract(transferDTO.getSum());
		fromAccount.setBalance(fromAccountNewBalance);
		BigDecimal toAccountNewBalance = toAccount.getBalance().add(transferDTO.getSum());
		toAccount.setBalance(toAccountNewBalance);
		fromAccount = accountRepository.save(fromAccount);
		toAccount = accountRepository.save(toAccount);
		Transaction transaction = createTransaction(fromAccount, toAccount, transferDTO.getSum());
		return transactionConverter.converter.apply(transaction);
	}

	private Transaction createTransaction(Account fromAccount, Account toAccount, BigDecimal sum) {
		Transaction transaction = new Transaction();
		transaction.setFromAccount(fromAccount);
		transaction.setToAccount(toAccount);
		transaction.setSum(sum);
		transaction.setDate(LocalDateTime.now());
		transactionRepository.save(transaction);
		return transaction;
	}

}
