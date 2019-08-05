package com.sandbox.banking.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Optional;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import com.sandbox.banking.converter.CustomerConverter;
import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.exception.CustomerNotFoundException;
import com.sandbox.banking.model.Customer;
import com.sandbox.banking.repository.CustomerRepository;
import com.sandbox.banking.service.impl.CustomerServiceImpl;

public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private CustomerConverter customerConverter;
	@Mock
	private Function<Customer, CustomerDTO> convertToDTO;
	@InjectMocks
	private CustomerServiceImpl victim;

	@Before
	public void setUp() {
		initMocks(this);
		ReflectionTestUtils.setField(customerConverter, "convertToDTO", convertToDTO);
	}

	@Test
	public void getCustomerInformation() throws CustomerNotFoundException {
		Customer customer = new Customer(1, "Diane", "Riley");
		given(customerRepository.findById(1l)).willReturn(Optional.of(customer));
		given(convertToDTO.apply(customer)).willReturn(any(CustomerDTO.class));
		victim.getCustomerInformation(1l);
		verify(customerRepository, times(1)).findById(any());

	}

	@Test(expected = CustomerNotFoundException.class)
	public void getCustomerInformationWhenCustomerNotFound() throws CustomerNotFoundException {
		given(customerRepository.findById(1l)).willReturn(Optional.empty());
		victim.getCustomerInformation(1l);
	}

}
