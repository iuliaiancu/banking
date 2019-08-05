package com.sandbox.banking.service;

import static org.mockito.ArgumentMatchers.any;
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
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Transaction;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.TransactionRepository;
import com.sandbox.banking.service.impl.TransactionServiceImpl;

public class TransactionServiceTest {

	private TransactionDTO transactionDTO;

	@Mock
	private AccountRepository accountRepository;
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private TransactionConverter transactionConverter;
	@Mock
	public Function<Transaction, TransactionDTO> convertToDTO;
	@InjectMocks
	private TransactionServiceImpl victim;

	@Before
	public void setUp() {
		initMocks(this);
		ReflectionTestUtils.setField(transactionConverter, "convertToDTO", convertToDTO);
		transactionDTO = new TransactionDTO(1, new BigDecimal(10));
		given(accountRepository.findById(transactionDTO.getAccountId())).willReturn(Optional.of(new Account()));
	}

	@Test
	public void createTransaction() {
		victim.createTransaction(transactionDTO);
		verify(transactionRepository, times(1)).save(any(Transaction.class));
	}

}
