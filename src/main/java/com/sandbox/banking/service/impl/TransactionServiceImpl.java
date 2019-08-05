package com.sandbox.banking.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.banking.converter.TransactionConverter;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.TransactionService;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	private AccountRepository accountRepository;
	private TransactionConverter transactionConverter;

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			TransactionConverter transactionConverter, AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.transactionConverter = transactionConverter;
		this.accountRepository = accountRepository;
	}

	/**
	 * account: the newly created account returns: the transaction that was made
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();
		transaction.setAccount(accountRepository.findById(transactionDTO.getAccountId()).get());
		transaction.setSum(transactionDTO.getSum());
		transaction.setDate(LocalDateTime.now());
		transactionRepository.save(transaction);
		return transactionConverter.convertToDTO.apply(transaction);
	}

}
