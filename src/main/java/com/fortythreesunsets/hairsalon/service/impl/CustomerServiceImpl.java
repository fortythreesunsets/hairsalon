package com.fortythreesunsets.hairsalon.service.impl;

import com.fortythreesunsets.hairsalon.entities.Appointment;
import com.fortythreesunsets.hairsalon.entities.Customer;
import com.fortythreesunsets.hairsalon.repositories.CustomerRepository;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import com.fortythreesunsets.hairsalon.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AppointmentService appointmentService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AppointmentService appointmentService) {
        this.customerRepository = customerRepository;
        this.appointmentService = appointmentService;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() { return customerRepository.findAll(); }

    @Override
    public Customer save(Customer customer) {
        if (customer ==  null || !StringUtils.hasLength(customer.getEmail())  && customer.getEmail().contains("@"))
            throw new IllegalArgumentException("Datos incorrectos. No se ha creado el cliente");

        // 1. Guardar cliente
        Customer customerSave = customerRepository.save(customer);

        /* 2. Actualizar citas
        * Si se requiere que desde el controlador /api/customers se puedan
        * asociar citas a un cliente, entonces se deben guardar desde el
        * lado del propietario de la relación (Appointment)
        * */
        // Sacar los IDs de las citas asociadas a cliente
        List<Long> appointmentIds = customer.getAppointments().stream().map(appointment -> appointment.getId()).toList();
        // Extraer las citas a partir de sus IDs
        List<Appointment> customerAppointments = appointmentService.findAllById(appointmentIds);
        // Cambiar el cliente asociado a las citas
        customerAppointments.forEach(appointment -> appointment.setCustomer(customerSave));
        // Es lo mismo que:
        /*for (Appointment appointment : customerAppointments) {
            appointment.setCustomer(customerSave);
        }*/
        // Guardar el cliente con la asociación de citas asociada
        customerSave.setAppointments(appointmentService.saveAll(customerAppointments));

        // Eliminar asociación de citas que no vienen en el update (petición PUT)
        List<Appointment> appointmentsToUpdate = appointmentService.findAllByIdNotInAndCustomerId(appointmentIds, customerSave.getId());
        appointmentsToUpdate.forEach(appointment -> appointment.setCustomer(null));
        appointmentService.saveAll(appointmentsToUpdate);

        return customerSave;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !customerRepository.existsById(id))
            return false;

        // Eliminar asociación de citas (fk_appointment_customer) antes de borrar el cliente
        List<Appointment> appointmentsToUpdate = appointmentService.findAllByCustomerId(id);
        appointmentsToUpdate.forEach(appointment -> appointment.setCustomer(null));

        appointmentService.saveAll(appointmentsToUpdate);

        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        customerRepository.deleteAll();
        return true;
    }
}
