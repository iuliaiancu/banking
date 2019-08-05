package com.sandbox.banking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.banking.dto.CustomerDTO;
import com.sandbox.banking.exception.CustomerNotFoundException;
import com.sandbox.banking.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/{id}")
	public Map<String, ResponseEntity<CustomerDTO>> getCustomerInformation(@PathVariable("id") long id)
			throws CustomerNotFoundException {
		Map<String, ResponseEntity<CustomerDTO>> response = new HashMap<>();
		response.put("message",
				new ResponseEntity<CustomerDTO>(customerService.getCustomerInformation(id), HttpStatus.OK));
		return response;
	}

}
