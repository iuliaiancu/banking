package com.sandbox.banking.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.banking.annotation.ValidTransaction;
import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.service.MoneyService;

@RestController
@RequestMapping("/sendMoney")
@Validated
public class SendMoneyController {

	@Autowired
	private MoneyService moneyService;

	@PostMapping
	public Map<String, ResponseEntity<String>> sendMoney(
			@RequestBody @Valid @ValidTransaction TransferDTO transferDTO) {
		Map<String, ResponseEntity<String>> response = new HashMap<>();
		TransactionDTO transactionDTO = moneyService.sendMoneyBetweenTwoAccounts(transferDTO);
		response.put("message", new ResponseEntity<String>(
				"Transaction succesfull. Moved " + transactionDTO.getSum() + " from account with id "
						+ transactionDTO.getFromAccountId() + " to account with id " + transactionDTO.getToAccountId(),
				HttpStatus.OK));
		return response;
	}

}
