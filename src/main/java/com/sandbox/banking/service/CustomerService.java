package com.sandbox.banking.service;

import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.exception.CustomerNotFoundException;

public interface CustomerService {

	CustomerDTO getCustomerInformation(long customerId) throws CustomerNotFoundException;

}
