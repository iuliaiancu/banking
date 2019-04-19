package com.sandbox.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.banking.model.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
