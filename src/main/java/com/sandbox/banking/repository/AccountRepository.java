package com.sandbox.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.banking.model.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

}
