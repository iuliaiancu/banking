package com.sandbox.banking.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sandbox.banking.dto.TransactionDTO;
import com.sandbox.banking.dto.TransferDTO;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.repository.AccountRepository;
import com.sandbox.banking.repository.CustomerRepository;
import com.sandbox.banking.service.MoneyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMoneyTest {

	@Autowired
	private MoneyService moneyService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	private Account fromAccount, toAccount;

	@Before
	public void setUp() {
		fromAccount = accountRepository.findById(1l).get();
		toAccount = accountRepository.findById(2l).get();
	}

	@Test
	public void sendMoney() {
		TransferDTO transfer = createTransfer();
		TransactionDTO transaction = moneyService.sendMoneyBetweenTwoAccounts(transfer);
		assertNotNull(transaction);
		assertEquals(new BigDecimal(190.00).doubleValue(),
				accountRepository.findById(fromAccount.getId()).get().getBalance().doubleValue(), 0);
		assertEquals(new BigDecimal(360.00).doubleValue(),
				accountRepository.findById(toAccount.getId()).get().getBalance().doubleValue(), 0);
	}

	private TransferDTO createTransfer() {
		TransferDTO transfer = new TransferDTO();
		transfer.setIdAccountFrom(1);
		transfer.setIdAccountTo(2);
		transfer.setSum(new BigDecimal(10));
		transfer.setCustomerId(1);
		return transfer;
	}

}
