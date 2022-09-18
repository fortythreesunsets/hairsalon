package com.fortythreesunsets.hairsalon.repositories;

import com.fortythreesunsets.hairsalon.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
