package com.sandbox.banking.converter;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.model.Transaction;

public class TransactionConverterTest {

	private TransactionConverter victim;
	private Transaction transaction;
	private List<Transaction> transactions;

	@Before
	public void setup() {
		victim = new TransactionConverter();
		Customer customer = new Customer(2, "Diane", "Riley");
		Account account = new Account(2, customer, new BigDecimal(30), Type.CURRENT_ACCOUNT);
		transaction = new Transaction(1l, new BigDecimal(10), account, LocalDateTime.now());
		transactions = Stream.of(transaction).collect(Collectors.toList());
	}

	@Test
	public void convertToDTO() {
		TransactionDTO result = victim.convertToDTO.apply(transaction);
		assertEquals(1, result.getTransactionId());
		assertEquals(new BigDecimal(10), result.getSum());
	}

	@Test
	public void convertToDTOList() {
		List<TransactionDTO> result = victim.convertToDTOList.apply(transactions);
		assertEquals(1, result.size());
	}

}
