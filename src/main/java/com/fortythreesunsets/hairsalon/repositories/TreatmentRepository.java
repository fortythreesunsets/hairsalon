package com.fortythreesunsets.hairsalon.repositories;

import com.fortythreesunsets.hairsalon.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
