package com.sandbox.banking.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.banking.annotation.ValidAccount;
import com.sandbox.banking.dto.AccountDTO;
import com.sandbox.banking.service.AccountService;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	public Map<String, ResponseEntity<AccountDTO>> sendMoney(@RequestBody @Valid @ValidAccount AccountDTO accountDTO) {
		Map<String, ResponseEntity<AccountDTO>> response = new HashMap<>();
		response.put("message",
				new ResponseEntity<AccountDTO>(accountService.createAccountAndMakeTransaction(accountDTO), HttpStatus.OK));
		return response;
	}

}
