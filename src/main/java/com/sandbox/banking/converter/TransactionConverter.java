package com.sandbox.banking.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.model.Transaction;

@Component("transactionConverter")
public class TransactionConverter {

	public Function<Transaction, TransactionDTO> converter = (transaction) -> {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setId(transaction.getId());
		transactionDTO.setFromAccountId(transaction.getFromAccount().getId());
		transactionDTO.setToAccountId(transaction.getToAccount().getId());
		transactionDTO.setSum(transaction.getSum());
		return transactionDTO;
	};

}
