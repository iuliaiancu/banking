package com.sandbox.banking.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.model.Customer;

@Component("customerConverter")
public class CustomerConverter {

	@Autowired
	private AccountTransactionConverter accountTransactionConverter;

	public Function<Customer, CustomerDTO> convertToDTO = (customer) -> {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName(customer.getName());
		customerDTO.setSurname(customer.getSurname());
		customerDTO.setAccountTransactions(accountTransactionConverter.convertToDTOList.apply(customer.getAccounts()));
		return customerDTO;
	};

}
