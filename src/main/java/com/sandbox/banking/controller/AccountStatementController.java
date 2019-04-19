package com.sandbox.banking.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.banking.dto.AccountStatementDTO;
import com.sandbox.banking.exception.AccountNotFoundException;
import com.sandbox.banking.service.AccountStatementService;

@RestController
@RequestMapping("/accountStatement")
public class AccountStatementController {

	@Autowired
	private AccountStatementService statementService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Map<String, ResponseEntity<AccountStatementDTO>> sendMoney(@PathVariable("id") long id)
			throws AccountNotFoundException {
		Map<String, ResponseEntity<AccountStatementDTO>> response = new HashMap<>();
		response.put("message",
				new ResponseEntity<AccountStatementDTO>(statementService.getAccountStatement(id), HttpStatus.OK));
		return response;
	}

}
