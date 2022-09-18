package com.fortythreesunsets.hairsalon.service;

import com.fortythreesunsets.hairsalon.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    Customer save(Customer customer);

    boolean deleteById(Long id);

    boolean deleteAll();

}
