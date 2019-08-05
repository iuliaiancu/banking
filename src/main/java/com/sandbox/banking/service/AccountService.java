package com.sandbox.banking.service;

import com.sandbox.banking.dto.AccountDTO;

public interface AccountService {

	AccountDTO createAccountAndMakeTransaction(AccountDTO accountDTO);

}
