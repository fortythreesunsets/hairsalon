package com.fortythreesunsets.hairsalon.repositories;

import com.fortythreesunsets.hairsalon.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
