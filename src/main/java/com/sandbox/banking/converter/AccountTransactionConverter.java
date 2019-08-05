package com.sandbox.banking.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sandbox.banking.dto.AccountTransactionDTO;
import com.sandbox.banking.model.Account;

@Component("accountTransactionConverter")
public class AccountTransactionConverter {

	@Autowired
	private TransactionConverter transactionConverter;

	public Function<Account, AccountTransactionDTO> convertToDTO = (account) -> {
		AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
		accountTransactionDTO.setBalance(account.getBalance());
		accountTransactionDTO
				.setTransactions(transactionConverter.convertToDTOList.apply(account.getAccountTransactions()));
		return accountTransactionDTO;
	};

	public Function<Set<Account>, Set<AccountTransactionDTO>> convertToDTOList = (accounts) -> {
		return accounts.stream().map(item -> convertToDTO.apply(item)).collect(Collectors.toSet());
	};

}
