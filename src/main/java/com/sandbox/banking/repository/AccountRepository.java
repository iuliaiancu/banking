package com.sandbox.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.banking.enums.Type;
import com.sandbox.banking.model.Account;
import com.sandbox.banking.model.Customer;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByCustomerAndType(Customer customer, Type type);

}
