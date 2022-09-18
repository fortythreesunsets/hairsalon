package com.fortythreesunsets.hairsalon.service.impl;

import com.fortythreesunsets.hairsalon.entities.Appointment;
import com.fortythreesunsets.hairsalon.entities.Treatment;
import com.fortythreesunsets.hairsalon.repositories.TreatmentRepository;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import com.fortythreesunsets.hairsalon.service.TreatmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    private final AppointmentService appointmentService;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository, AppointmentService appointmentService) {
        this.treatmentRepository = treatmentRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public Optional<Treatment> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return treatmentRepository.findById(id);
    }

    @Override
    public List<Treatment> findAll() { return treatmentRepository.findAll(); }

    @Override
    public Treatment save(Treatment treatment) {
        if (treatment ==  null)
            throw new IllegalArgumentException("Datos incorrectos. No se ha creado el servicio");

        return treatmentRepository.save(treatment);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !treatmentRepository.existsById(id))
            return false;

        // Eliminar asociación de citas (fk_appointment_treatment) antes de borrar el servicio
        List<Appointment> appointmentsToUpdate = appointmentService.findAllByTreatmentId(id);
        appointmentsToUpdate.forEach(appointment -> appointment.setTreatment(null));
        appointmentService.saveAll(appointmentsToUpdate);

        treatmentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        treatmentRepository.deleteAll();
        return true;
    }
}
