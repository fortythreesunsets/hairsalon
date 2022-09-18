package com.fortythreesunsets.hairsalon.service;

import com.fortythreesunsets.hairsalon.entities.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    Optional<Address> findById(Long id);

    List<Address> findAll();

    Address save(Address address);

    boolean deleteById(Long id);

    boolean deleteAll();

}
