package com.sandbox.banking.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.banking.converter.TransactionConverter;
import com.sandbox.banking.dto.AccountStatementDTO;
import com.sandbox.banking.exception.AccountNotFoundException;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.AccountStatementService;

@Service("accountStatementService")
public class AccountStatementServiceImpl implements AccountStatementService {

	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private TransactionConverter transactionConverter;

	@Autowired
	public AccountStatementServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
			TransactionConverter transactionConverter) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.transactionConverter = transactionConverter;
	}

	/**
	 * accountId: the account identified for which you wish to obtain the statement
	 * returns: a data structure containing the account balance and the list of
	 * transactions if any
	 */
	@Override
	public AccountStatementDTO getAccountStatement(long accountId) throws AccountNotFoundException {
		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if (!optionalAccount.isPresent()) {
			throw new AccountNotFoundException("Account doesn't exists");
		}
		Account account = optionalAccount.get();
		AccountStatementDTO statementDTO = new AccountStatementDTO();
		statementDTO.setBalance(account.getBalance());
		List<Transaction> transactions = transactionRepository.findTransactionsByFromAccount(account);
		statementDTO.setTransactions(transactions.stream()
				.map(transaction -> transactionConverter.converter.apply(transaction)).collect(Collectors.toList()));
		return statementDTO;
	}

}
