package com.fortythreesunsets.hairsalon.service.impl;

import com.fortythreesunsets.hairsalon.entities.Appointment;
import com.fortythreesunsets.hairsalon.repositories.AppointmentRepository;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    // Acceso a la BD
    // Inyectar repositorio para hacer llamadas a los métodos (a través de @Autowired o del constructor)
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllById(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids))
            return new ArrayList<>();

        return appointmentRepository.findAllById(ids);
    }

    @Override
    public List<Appointment> findAllByCustomerId(Long id) {
        if (id == null || id <= 0)
            return new ArrayList<>();

        return appointmentRepository.findAllByCustomerId(id);
    }

    @Override
    public List<Appointment> findAllByIdNotInAndCustomerId(List<Long> ids, Long id) {
        // Inicialiar lista
        if (id == null || id <= 0)
            return new ArrayList<>();

        if (ids == null)
            ids = new ArrayList<>();

        return appointmentRepository.findAllByIdNotInAndCustomerId(ids, id);
    }

    @Override
    public List<Appointment> findAllByCustomerEmail(String customerEmail) throws IllegalArgumentException {
        if (!StringUtils.hasLength(customerEmail) && customerEmail.contains("@"))
            throw new IllegalArgumentException("Email incorrecto");

        return appointmentRepository.findAllByCustomerEmail(customerEmail);
    }

    @Override
    public List<Appointment> findAllByCustomerPhone(String customerPhone) {
        if (!StringUtils.hasLength(customerPhone))
            throw new IllegalArgumentException("Teléfono incorrecto");

        return appointmentRepository.findAllByCustomerPhone(customerPhone);
    }

    @Override
    public List<Appointment> findAllByEmployeeId(Long id) {
        if (id == null || id <= 0)
            return new ArrayList<>();

        return appointmentRepository.findAllByEmployeeId(id);
    }

    @Override
    public List<Appointment> findAllByEmployeeRfc(String rfc) {
        if (!StringUtils.hasLength(rfc)) // Format checking
            throw new IllegalArgumentException("RFC incorrecto");

        return appointmentRepository.findAllByEmployeeRfc(rfc);
    }

    @Override
    public List<Appointment> findAllByPriceLessThanEqual(Double price) {
        if (price == null || price <= 0)
            throw new IllegalArgumentException("Precio incorrecto");

        return appointmentRepository.findAllByTreatmentPriceLessThanEqual(price);
    }

    @Override
    public List<Appointment> findAllByTreatmentId(Long id) {
        if (id == null || id <= 0) {
            return new ArrayList<>();
        }

        return appointmentRepository.findAllByTreatmentId(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        if (appointment == null || appointment.getDate() == null)
            throw new IllegalArgumentException("Cita incorrecta");

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> saveAll(List<Appointment> appointments) {
        if (!CollectionUtils.isEmpty(appointments))
            return appointmentRepository.saveAll(appointments);

        return new ArrayList<>();
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !appointmentRepository.existsById(id))
            return false;

        appointmentRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        appointmentRepository.deleteAll();
        return true;
    }

    @Override
    public double calculateEarningsByDate(LocalDate date) {
        if (date == null)
        return 0;

        LocalDateTime min = date.atTime(0, 0);
        LocalDateTime max = date.atTime(23,59);
        List<Appointment> appointments = appointmentRepository.findAllByDateBetween(min, max);
        //return getEarnings(appointmentRepository.findAllByDateBetween(min, max));
        return getEarnings(appointments);
        /*  El return sustituye:
        double earnings = 0;
        for (Appointment appointment : appointments) {
            if (appointment.getTreatment() == null)
                continue;
            earnings += appointment.getTreatment().getPrice();
        }
        return earnings;*/
    }

    @Override
    public double calculateEarningsByMonth(int year, Month month) {
        LocalDateTime min = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime max = min.plusMonths(1);
		//LocalDateTime max = min.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59);
        List<Appointment> appointments = appointmentRepository.findAllByDateBetween(min, max);

        return getEarnings(appointments);
        //return getEarnings(appointmentRepository.findAllByDateBetween(min, max));
    }

    @Override
    public double calculateEarningsByYear(int year) {
        LocalDateTime min = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime max = min.plusYears(1);
        List<Appointment> appointments = appointmentRepository.findAllByDateBetween(min, max);

        return getEarnings(appointments);
        //return getEarnings(appointmentRepository.findAllByDateBetween(min, max));
    }

    /**
     * Extrae el precio cobrado por cada cita y los suma para obtener los ingresos totales
     * @param appointments lista de citas
     * @return ingresos totales
     */
    private static Double getEarnings(List<Appointment> appointments) {
        return appointments.stream()
                .filter(appointment -> appointment.getTreatment() != null)
                .map(s -> s.getTreatment().getPrice())
                .reduce(Double::sum)
                .orElse(0d);
    }
}
