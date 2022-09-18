package com.fortythreesunsets.hairsalon.controllers;

import com.fortythreesunsets.hairsalon.dto.EarningsDTO;
import com.fortythreesunsets.hairsalon.entities.Appointment;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Métodos CRUD

    /**
     * Consulta de todas las citas
     * GET http://localhost:8080/api/appointments
     */
    @GetMapping("/appointments")
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    /**
     * Consulta de una cita por ID
     * GET http://localhost:8080/api/appointments/1
     * @param id de cita
     * @return 200 OK si se encuentra la cita
     * @return 404 Not Found si no se encuentra la cita
     */
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable Long id) {

        Optional<Appointment> appointmentOptional = appointmentService.findById(id);
        if (appointmentOptional.isPresent())
            return ResponseEntity.ok(appointmentOptional.get());
        return ResponseEntity.notFound().build();

        // Con programación funcional
        /*  return appointmentService.findById(id)
                .map(appointment -> ResponseEntity.ok(appointment))
                .orElseGet(() -> ResponseEntity.notFound().build()); */
    }

    /**
     * Buscar todas las citas que estén asociadas con el email del cliente
     * GET http://localhost:8080/api/appointments/search/customer/email/example@example.com
     * @param email del cliente
     * @return cita(s) que coincida con el email
     */
    @GetMapping("/appointments/search/customer/email/{email}")
    public List<Appointment> findAllByCustomerEmail(@PathVariable String email) {
        return appointmentService.findAllByCustomerEmail(email);
    }

    /**
     * Buscar todas las citas que estén asociadas con el teléfono del cliente
     * GET http://localhost:8080/api/appointments/search/customer/phone/5555555555
     * @param phone del cliente
     * @return cita(s) que coincida con el teléfono
     */
    @GetMapping("/appointments/search/customer/phone/{phone}")
    public List<Appointment> findAllByCustomerPhone(@PathVariable String phone) {
        return appointmentService.findAllByCustomerPhone(phone);
    }

    /**
     * Buscar todas las citas que estén asociadas con el RFC del empleado
     * GET http://localhost:8080/api/appointments/search/employee/rfc/ABCD000000EF1
     * @param rfc del empleado
     * @return cita(s) que coincida con el rfc
     */
    @GetMapping("/appointments/search/employee/rfc/{rfc}")
    public List<Appointment> findAllByEmployeeRfc(@PathVariable String rfc) {
        return appointmentService.findAllByEmployeeRfc(rfc);
    }

    /**
     * Buscar todas las citas que estén entre un rango de precios
     * GET http://localhost:8080/api/appointments/search/treatment/price/300
     * @param price del servicio
     * @return cita(s) que coincida con el rango de precio
     */
    @GetMapping("/appointments/search/treatment/price/{price}")
    public List<Appointment> findAllByPriceLessThanEqual(@PathVariable Double price) {
            return appointmentService.findAllByPriceLessThanEqual(price);
    }

    /**
     * Buscar todas las citas que contengan un servicio
     * GET http://localhost:8080/api/appointments/search/treatment/id/1
     * @param id del servicio
     * @return cita(s) que coincida con el ID del servicio
     */
    @GetMapping("/appointments/search/treatment/id/{id}")
    public List<Appointment> findAllByTreatmentId(@PathVariable Long id) {
        return appointmentService.findAllByTreatmentId(id);
    }

    /**
     * Crea una cita
     * POST http://localhost:8080/api/appointments
     * @param appointment
     * @return 400 Bad Request si no se crea la cita
     * @return 200 OK si se crea la cita
     */
    @PostMapping("/appointments")
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        if (appointment.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(appointmentService.save(appointment));
    }

    /**
     * Actualizar una cita
     * PUT http://localhost:8080/api/appointments
     * @param appointment
     * @return 400 Bad Request si no se actualiza la cita
     * @return 200 OK si se actualiza la cita
     */
    @PutMapping("/appointments")
    public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) {
        if (appointment.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(appointmentService.save(appointment));
    }

    /**
     * Eliminar una cita
     * DELETE http://localhost:8080/api/appointments/1
     * @param id de cita
     * @return 204 No Content si se elimina la cita
     * @return 500 Internal Server Error si no se encuentra la cita
     */
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean result = appointmentService.deleteById(id);
        if (result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }

    /**
     * Calcular ingresos de un día
     * GET http://localhost:8080/api/appointments/earnings/2022/1/1
     * @param year
     * @param month
     * @param day
     * @return 200 OK si se realiza el cálculo
     */
    @GetMapping("/appointments/earnings/{year}/{month}/{day}")
    public ResponseEntity<EarningsDTO> calculateEarningsByDate(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        double earnings = appointmentService.calculateEarningsByDate(LocalDate.of(year, month, day));
        EarningsDTO earningsDTO = new EarningsDTO(earnings);
        return ResponseEntity.ok(earningsDTO);
    }

    /**
     * Calcular ingresos de un mes
     * GET http://localhost:8080/api/appointments/earnings/2022/1
     * @param year
     * @param month
     * @return 200 OK si se realiza el cálculo
     */
    @GetMapping("/appointments/earnings/{year}/{month}")
    public ResponseEntity<EarningsDTO> calculateEarningsByMonth(@PathVariable int year, @PathVariable int month) {
        double earnings = appointmentService.calculateEarningsByMonth(year, Month.of(month));
        EarningsDTO earningsDTO = new EarningsDTO(earnings);
        return ResponseEntity.ok(earningsDTO);

    }

    /**
     * Calcular ingresos de un mes
     * GET http://localhost:8080/api/appointments/earnings/2022
     * @param year
     * @return 200 OK si se realiza el cálculo
     */
    @GetMapping("/appointments/earnings/{year}")
    public ResponseEntity<EarningsDTO> calculateEarningsByYear(@PathVariable int year) {
        double earnings = appointmentService.calculateEarningsByYear(year);
        EarningsDTO earningsDTO = new EarningsDTO(earnings);
        return ResponseEntity.ok(earningsDTO);
    }
}
