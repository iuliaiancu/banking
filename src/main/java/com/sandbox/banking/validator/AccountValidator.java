package com.sandbox.banking.validator;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sandbox.banking.annotation.ValidAccount;
import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.CustomerRepository;

public class AccountValidator implements ConstraintValidator<ValidAccount, AccountDTO> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	/**
	 * accountDTO: the structure used for creating account returns true if the
	 * customer of the account exists and initialCredit is greater or equal to 0,
	 * false otherwise
	 */
	public boolean isValid(AccountDTO accountDTO, ConstraintValidatorContext context) {
		Optional<Customer> customer = customerRepository.findById(accountDTO.getCustomerId());
		boolean isValid = customer.isPresent()
				&& accountDTO.getInitialCredit().doubleValue() >= new BigDecimal(0).doubleValue();
		return isValid;
	}

}
