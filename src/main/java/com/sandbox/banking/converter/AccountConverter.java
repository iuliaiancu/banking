package com.sandbox.banking.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.model.Account;

@Component("accountConverter")
public class AccountConverter {

	public Function<Account, AccountDTO> convertToDTO = (account) -> {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setAccountType(account.getType());
		accountDTO.setCustomerId(account.getCustomer().getId());
		accountDTO.setInitialCredit(account.getBalance());
		return accountDTO;
	};

}
