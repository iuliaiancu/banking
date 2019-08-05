package com.sandbox.banking.converter;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;

public class AccountConverterTest {

	private AccountConverter victim;
	private Account account;

	@Before
	public void setup() {
		victim = new AccountConverter();
		Customer customer = new Customer(2, "Diane", "Riley");
		account = new Account(2, customer, new BigDecimal(30), Type.CURRENT_ACCOUNT);
	}

	@Test
	public void convertToDTO() {
		AccountDTO result = victim.convertToDTO.apply(account);
		assertEquals(2, result.getId());
		assertEquals(new BigDecimal(30), result.getInitialCredit());
	}

}
