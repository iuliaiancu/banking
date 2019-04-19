package com.sandbox.banking.service;

import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.dto.TransferDTO;

public interface MoneyService {

	public TransactionDTO sendMoneyBetweenTwoAccounts(TransferDTO transferDTO);

}
