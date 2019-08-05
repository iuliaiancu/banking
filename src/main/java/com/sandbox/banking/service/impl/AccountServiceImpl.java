package com.sandbox.banking.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.banking.converter.AccountConverter;
import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.CustomerRepository;
import com.sandbox.banking.service.AccountService;
import com.sandbox.banking.service.TransactionService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private AccountConverter accountConverter;
	private TransactionService transactionService;
	private CustomerRepository customerRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, AccountConverter accountConverter,
			TransactionService moneyService, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.accountConverter = accountConverter;
		this.transactionService = moneyService;
		this.customerRepository = customerRepository;
	}

	/**
	 * returns the new created account
	 */
	@Override
	public AccountDTO createAccountAndMakeTransaction(AccountDTO accountDTO) {
		Customer customer = customerRepository.findById(accountDTO.getCustomerId()).orElse(null);
		Account account = accountRepository.findByCustomerAndType(customer, accountDTO.getAccountType()).orElse(null);
		if (account == null) {
			account = new Account(customer, accountDTO.getInitialCredit(), accountDTO.getAccountType());
		} else {
			account.setBalance(account.getBalance().add(accountDTO.getInitialCredit()));
		}
		account = accountRepository.save(account);
		if (accountDTO.getInitialCredit().compareTo(new BigDecimal(0)) > 0) {
			transactionService.createTransaction(new TransactionDTO(account.getId(), accountDTO.getInitialCredit()));
		}
		return accountConverter.convertToDTO.apply(account);
	}

}
