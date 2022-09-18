package com.fortythreesunsets.hairsalon.service;

import com.fortythreesunsets.hairsalon.entities.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    Optional<Appointment> findById(Long id);

    List<Appointment> findAll();

    List<Appointment> findAllById(List<Long> ids);

    List<Appointment> findAllByCustomerId(Long id);

    List<Appointment> findAllByIdNotInAndCustomerId(List<Long> ids, Long id);

    List<Appointment> findAllByCustomerEmail(String customerEmail) throws IllegalArgumentException;

    List<Appointment> findAllByCustomerPhone(String customerPhone);

    List<Appointment>findAllByEmployeeId(Long id);

    List<Appointment> findAllByEmployeeRfc(String rfc);

    List<Appointment> findAllByPriceLessThanEqual(Double price);

    List<Appointment> findAllByTreatmentId(Long id);

    Appointment save(Appointment appointment);

    List<Appointment> saveAll(List<Appointment> appointments);

    boolean deleteById(Long id);

    boolean deleteAll();

    double calculateEarningsByDate(LocalDate date);

    double calculateEarningsByMonth(int year, Month month);

    double calculateEarningsByYear(int year);
    // Métodos por defecto: permiten crear la implementación en la interfaz para no obligar a las clases Impl a implementarlos
    /*default double calculateEarningsByYear(int year) {
        // Código
        return 0;
    }*/

}
