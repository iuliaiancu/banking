package com.sandbox.banking.service;

import com.sandbox.banking.dto.AccountStatementDTO;
import com.sandbox.banking.exception.AccountNotFoundException;

public interface AccountStatementService {

	public AccountStatementDTO getAccountStatement(long accountId) throws AccountNotFoundException;

}
