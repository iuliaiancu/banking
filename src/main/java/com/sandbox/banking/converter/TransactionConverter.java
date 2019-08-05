package com.sandbox.banking.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.model.Transaction;

@Component("transactionConverter")
public class TransactionConverter {

	public Function<Transaction, TransactionDTO> convertToDTO = (transaction) -> {
		return new TransactionDTO(transaction.getId(), transaction.getAccount().getId(), transaction.getSum(),
				transaction.getDate());
	};

	public Function<List<Transaction>, List<TransactionDTO>> convertToDTOList = (transactions) -> {
		return transactions.stream().map(transaction -> convertToDTO.apply(transaction)).collect(Collectors.toList());
	};

}
