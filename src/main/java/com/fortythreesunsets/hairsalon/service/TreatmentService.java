package com.fortythreesunsets.hairsalon.service;

import com.fortythreesunsets.hairsalon.entities.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentService {

    Optional<Treatment> findById(Long id);

    List<Treatment> findAll();

    Treatment save(Treatment treatment);

    boolean deleteById(Long id);

    boolean deleteAll();

}
