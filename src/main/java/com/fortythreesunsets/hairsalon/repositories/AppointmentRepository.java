package com.fortythreesunsets.hairsalon.repositories;

import com.fortythreesunsets.hairsalon.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByCustomerEmail(String email);

    List<Appointment> findAllByCustomerId(Long id);

    List<Appointment> findAllByCustomerPhone(String phone);

    List<Appointment>findAllByDateBetween(LocalDateTime min, LocalDateTime max);

    List<Appointment>findAllByEmployeeId(Long id);

    List<Appointment>findAllByEmployeeRfc(String rfc);

    List<Appointment>findAllByTreatmentPriceLessThanEqual(Double price);

    List<Appointment> findAllByTreatmentId(Long id);

    List<Appointment> findAllByIdNotInAndCustomerId(List<Long> ids, Long id);

}
