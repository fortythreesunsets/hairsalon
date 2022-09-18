package com.fortythreesunsets.hairsalon.repositories;

import com.fortythreesunsets.hairsalon.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByAddressId(Long id);

}
