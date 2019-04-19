package com.sandbox.banking.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.sandbox.banking.converter.TransactionConverter;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.impl.MoneyServiceImpl;

public class MoneyServiceTest {

	@Mock
	private AccountRepository accountRepository;
	@Mock
	private Account account;
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private TransactionConverter transactionConverter;
	@Mock
	public Function<Transaction, TransactionDTO> converter;
	@InjectMocks
	private MoneyServiceImpl victim;
	private Optional<Account> fromAccount;
	private Optional<Account> toAccount;
	private Account account1;
	private Account account2;

	@Before
	public void setUp() {
		initMocks(this);
		ReflectionTestUtils.setField(transactionConverter, "converter", converter);
		account1 = new Account();
		account1.setBalance(new BigDecimal(200));
		account2 = new Account();
		account2.setBalance(new BigDecimal(250));
		fromAccount = Optional.of(account1);
		toAccount = Optional.of(account2);
	}

	@Test
	public void sendMoneyBetweenTwoAccounts() {
		TransferDTO transfer = new TransferDTO();
		transfer.setCustomerId(1);
		transfer.setIdAccountFrom(1);
		transfer.setIdAccountTo(2);
		transfer.setSum(new BigDecimal(10));
		given(accountRepository.findById(1l)).willReturn(fromAccount);
		given(accountRepository.findById(2l)).willReturn(toAccount);
		victim.sendMoneyBetweenTwoAccounts(transfer);
		verify(accountRepository, times(2)).save(account1);
		assertEquals(new BigDecimal(190), account1.getBalance());
		assertEquals(new BigDecimal(260), account2.getBalance());
	}

}
