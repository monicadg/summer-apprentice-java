package org.example.summer.apprentice.repository;

import org.example.summer.apprentice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
}
