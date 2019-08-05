package com.sandbox.banking.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.model.Transaction;

public class AccountTransactionConverterTest {

	@Mock
	private TransactionConverter transactionConverter;
	@Mock
	private Function<Transaction, TransactionDTO> converter;
	@Mock
	private Function<List<Transaction>, List<TransactionDTO>> converterList;
	@InjectMocks
	private AccountTransactionConverter victim;
	private Account account;
	private Set<Account> accounts;

	@Before
	public void setup() {
		initMocks(this);
		ReflectionTestUtils.setField(transactionConverter, "convertToDTOList", converterList);
		Customer customer = new Customer(2, "Diane", "Riley");
		account = new Account(2, customer, new BigDecimal(30), Type.CURRENT_ACCOUNT);
		Transaction transaction = new Transaction(1l, new BigDecimal(10), account, LocalDateTime.now());
		List<Transaction> transactions = Stream.of(transaction).collect(Collectors.toList());
		account.setAccountTransactions(transactions);
		accounts = Stream.of(account).collect(Collectors.toSet());
		customer.setAccounts(accounts);
		given(converterList.apply(transactions))
				.willReturn(Stream.of(new TransactionDTO()).collect(Collectors.toList()));

	}

	@Test
	public void convertToDTO() {
		AccountTransactionDTO result = victim.convertToDTO.apply(account);
		assertEquals(new BigDecimal(30), result.getBalance());
		assertEquals(1, result.getTransactions().size());
	}

	@Test
	public void convertToDTOList() {
		Set<AccountTransactionDTO> result = victim.convertToDTOList.apply(accounts);
		assertEquals(1, result.size());
	}

}
