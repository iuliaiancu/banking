package com.sandbox.banking.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sandbox.banking.annotation.ValidTransaction;
import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.CustomerRepository;

public class TransactionValidator implements ConstraintValidator<ValidTransaction, TransferDTO> {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	/**
	 * transferDTO: the structure used for sending money between two different
	 * accounts returns: if the structure is valid or not meaning the specified
	 * accounts should exists and be different, the sum should be a positive integer
	 * and there should be enough money in the account form which the transfer is
	 * made
	 */
	public boolean isValid(TransferDTO transferDTO, ConstraintValidatorContext context) {
		return checkAccountExists(transferDTO.getIdAccountFrom()) && checkAccountExists(transferDTO.getIdAccountTo())
				&& checkSumCanBeTransfered(transferDTO) && checkCustomerExists(transferDTO.getCustomerId())
				&& checkFromAccountIsDifferentThanToAccount(transferDTO);
	}

	private boolean checkAccountExists(long accountId) {
		Account account = accountRepository.findById(accountId).orElse(null);
		return account != null ? true : false;
	}

	private boolean checkFromAccountIsDifferentThanToAccount(TransferDTO transferDTO) {
		return transferDTO.getIdAccountFrom() != transferDTO.getIdAccountTo();
	}

	private boolean checkSumCanBeTransfered(TransferDTO transferDTO) {
		Account fromAccount = accountRepository.findById(transferDTO.getIdAccountFrom()).orElse(null);
		if (transferDTO.getSum().compareTo(fromAccount.getBalance()) > 0) {
			return false;
		}
		return true;
	}

	private boolean checkCustomerExists(long customerId) {
		Customer account = customerRepository.findById(customerId).orElse(null);
		return account != null ? true : false;
	}

}
