package com.fortythreesunsets.hairsalon.service;

import com.fortythreesunsets.hairsalon.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    Employee save(Employee employee);

    boolean deleteById(Long id);

    boolean deleteAll();

}
