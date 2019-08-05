package com.sandbox.banking.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.sandbox.banking.dto.AccountTransactionDTO;
import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.model.Transaction;

public class CustomerConverterTest {

	@Mock
	private AccountTransactionConverter accountTransactionConverter;
	@Mock
	private Function<Set<Account>, Set<AccountTransactionDTO>> converterSet;
	@Mock
	private Function<Account, AccountTransactionDTO> converter;
	@InjectMocks
	private CustomerConverter victim;
	private Customer customer;

	@Before
	public void setup() {
		initMocks(this);
		ReflectionTestUtils.setField(accountTransactionConverter, "convertToDTOList", converterSet);
		customer = new Customer(2, "Diane", "Riley");
		Account account = new Account(2, customer, new BigDecimal(30), Type.CURRENT_ACCOUNT);
		Set<Account> accounts = Stream.of(account).collect(Collectors.toSet());
		AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
		given(converterSet.apply(accounts)).willReturn(Stream.of(accountTransactionDTO).collect(Collectors.toSet()));
		customer.setAccounts(accounts);
		Transaction transaction = new Transaction(1l, new BigDecimal(10), account, LocalDateTime.now());
		account.setAccountTransactions(Stream.of(transaction).collect(Collectors.toList()));
	}

	@Test
	public void convertToDTO() {
		CustomerDTO result = victim.convertToDTO.apply(customer);
		assertEquals(1, result.getAccountTransactions().size());
		assertEquals("Diane", result.getName());
		assertEquals("Riley", result.getSurname());
	}

}
