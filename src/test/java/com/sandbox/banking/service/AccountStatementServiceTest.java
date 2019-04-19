package com.sandbox.banking.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.sandbox.banking.converter.TransactionConverter;
import com.sandbox.banking.dto.AccountStatementDTO;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.exception.AccountNotFoundException;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.impl.AccountStatementServiceImpl;

public class AccountStatementServiceTest {

	@Mock
	private AccountRepository accountRepository;
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private TransactionConverter transactionConverter;
	@Mock
	public Function<Transaction, TransactionDTO> converter;
	@Mock
	private Transaction transaction;
	@InjectMocks
	private AccountStatementServiceImpl victim;
	private Optional<Account> account;
	private Account account1;

	@Before
	public void setUp() {
		initMocks(this);
		ReflectionTestUtils.setField(transactionConverter, "converter", converter);
		account1 = new Account();
		account1.setBalance(new BigDecimal(200));
		account = Optional.of(account1);
	}

	@Test
	public void getAccountStatement() throws AccountNotFoundException {
		given(accountRepository.findById(1l)).willReturn(account);
		given(transactionRepository.findTransactionsByFromAccount(account1))
				.willReturn(Stream.of(transaction).collect(Collectors.toList()));
		AccountStatementDTO response = victim.getAccountStatement(1l);
		assertEquals(new BigDecimal(200), response.getBalance());
		assertEquals(1, response.getTransactions().size());
	}

	@Test(expected = AccountNotFoundException.class)
	public void getAccountStatementWhenAccountIsNotFound() throws AccountNotFoundException {
		given(accountRepository.findById(4l)).willReturn(null);
		victim.getAccountStatement(1l);
	}

}
