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

import com.sandbox.banking.converter.AccountConverter;
import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.CustomerRepository;
import com.sandbox.banking.service.impl.AccountServiceImpl;

public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	@Mock
	private AccountConverter accountConverter;
	@Mock
	private Function<Account, AccountDTO> convertToDTO;
	@Mock
	private TransactionService transactionService;
	@Mock
	private CustomerRepository customerRepository;
	@InjectMocks
	private AccountServiceImpl victim;
	private AccountDTO accountDTO;

	@Before
	public void setUp() {
		initMocks(this);
		ReflectionTestUtils.setField(accountConverter, "convertToDTO", convertToDTO);
		accountDTO = new AccountDTO();
		accountDTO.setCustomerId(1);
		accountDTO.setInitialCredit(new BigDecimal(1000));
	}

	@Test
	public void createAccountAndMakeTransaction() {
		Customer customer = new Customer(1, "Diane", "Riley");
		given(customerRepository.findById(accountDTO.getCustomerId())).willReturn(Optional.of(customer));
		Account account = new Account(customer, accountDTO.getInitialCredit(), Type.CURRENT_ACCOUNT);
		given(accountRepository.findByCustomerAndType(customer, Type.CURRENT_ACCOUNT)).willReturn(Optional.of(account));
		given(accountRepository.save(any())).willReturn(account);
		given(transactionService.createTransaction(new TransactionDTO())).willReturn(any());
		given(convertToDTO.apply(account)).willReturn(accountDTO);
		victim.createAccountAndMakeTransaction(accountDTO);
		verify(transactionService, times(1)).createTransaction(any());
		verify(accountRepository, times(1)).save(any(Account.class));
	}

}
