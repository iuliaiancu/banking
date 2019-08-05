package com.sandbox.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sandbox.banking.converter.CustomerConverter;
import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.exception.CustomerNotFoundException;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.CustomerRepository;
import com.sandbox.banking.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerConverter customerConverter;

	@Override
	public CustomerDTO getCustomerInformation(long customerId) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer doesn't exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return customerConverter.convertToDTO.apply(customer);
	}

}
