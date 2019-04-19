package com.sandbox.banking.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.CustomerRepository;

public class TransactionValidatorTest {

	@Mock
	private AccountRepository accountRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ConstraintValidatorContext context;
	@InjectMocks
	private TransactionValidator victim;
	private Optional<Customer> customer;
	private Optional<Account> fromAccount;
	private Optional<Account> toAccount;

	@Before
	public void setUp() {
		initMocks(this);
		customer = Optional.of(new Customer());
		fromAccount = Optional.of(new Account());
		toAccount = Optional.of(new Account());
	}

	@Test
	public void isValidTrue() {
		TransferDTO transfer = new TransferDTO();
		transfer.setCustomerId(1);
		transfer.setIdAccountFrom(1);
		transfer.setIdAccountTo(2);
		transfer.setSum(new BigDecimal(10));
		given(customerRepository.findById(1l)).willReturn(customer);
		given(accountRepository.findById(1l)).willReturn(fromAccount);
		fromAccount.get().setBalance(new BigDecimal(100));
		given(accountRepository.findById(2l)).willReturn(toAccount);
		assertTrue(victim.isValid(transfer, context));
	}

	@Test
	public void isValidFalse() {
		TransferDTO transfer = new TransferDTO();
		transfer.setCustomerId(1);
		transfer.setIdAccountFrom(1);
		transfer.setIdAccountTo(2);
		transfer.setSum(new BigDecimal(10));
		given(customerRepository.findById(1l)).willReturn(customer);
		given(accountRepository.findById(1l)).willReturn(fromAccount);
		fromAccount.get().setBalance(new BigDecimal(5));
		given(accountRepository.findById(2l)).willReturn(toAccount);
		assertFalse(victim.isValid(transfer, context));
	}

}
