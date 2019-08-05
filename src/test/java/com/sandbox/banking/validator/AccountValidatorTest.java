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

import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.CustomerRepository;

public class AccountValidatorTest {

	private AccountDTO accountDTO;

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ConstraintValidatorContext context;
	@InjectMocks
	private AccountValidator victim;

	@Before
	public void setUp() {
		initMocks(this);
		accountDTO = new AccountDTO();
		accountDTO.setCustomerId(1);
		accountDTO.setInitialCredit(new BigDecimal(19));
	}

	@Test
	public void isValidTrue() {
		Customer customer = new Customer(1, "Diane", "Riley");
		given(customerRepository.findById(accountDTO.getCustomerId())).willReturn(Optional.of(customer));
		assertTrue(victim.isValid(accountDTO, context));
	}

	@Test
	public void isValidFalse() {
		given(customerRepository.findById(accountDTO.getCustomerId())).willReturn(Optional.empty());
		assertFalse(victim.isValid(accountDTO, context));
	}

}
