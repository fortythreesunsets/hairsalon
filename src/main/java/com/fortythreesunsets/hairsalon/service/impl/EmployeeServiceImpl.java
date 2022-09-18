package com.fortythreesunsets.hairsalon.service.impl;

import com.fortythreesunsets.hairsalon.entities.Address;
import com.fortythreesunsets.hairsalon.entities.Appointment;
import com.fortythreesunsets.hairsalon.entities.Employee;
import com.fortythreesunsets.hairsalon.repositories.EmployeeRepository;
import com.fortythreesunsets.hairsalon.service.AddressService;
import com.fortythreesunsets.hairsalon.service.AppointmentService;
import com.fortythreesunsets.hairsalon.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final AppointmentService appointmentService;

    private final AddressService addressService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AppointmentService appointmentService, AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.appointmentService = appointmentService;
        this.addressService = addressService;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() { return employeeRepository.findAll(); }

    /**
     * Casos:
     * 1. Crear un empleado sin dirección
     * 2. Crear un empleado con dirección sin ID
     * 3. Crear un empleado con ID de dirección falso
     * 4. Crear un empleado con el ID de una dirección creada previamente con todos los atributos y valores y sin asignar
     * 5. Crear un empleado con el ID de una dirección creada previamente sin atributos y sin asignar
     * 6. Crear un empleado con el ID de una dirección creada previamente y asignada a otro empleado
     * @param employee
     * @return
     */

    @Override
    public Employee save(Employee employee) {
        // Si el empleado está vacío o el email es incorrecto, lanza excepción y no guarda
        if (employee ==  null || !StringUtils.hasLength(employee.getEmail()))
            throw new IllegalArgumentException("Datos incorrectos. No se ha creado el empleado");

        // Si la dirección está vacía o el ID de la dirección está vacío, guarda los null
        if (employee.getAddress() == null || employee.getAddress().getId() == null)
            return employeeRepository.save(employee);

        // Si no existe la dirección (ID incorrecto), o ya está asignada a algún empleado, se cambia a null y se guarda
        Optional<Address> addressOptional = addressService.findById(employee.getAddress().getId());
        if (addressOptional.isEmpty() || employeeRepository.existsByAddressId(employee.getAddress().getId())) {
            employee.setAddress(null);
            return employeeRepository.save(employee);
        }

        // La dirección existe y no está asignada a ningún empleado, se actualizan los valores, se asocia a un empleado y se guarda
        //1.  Recuperar de la BD una dirección, mantener su ID y cambiar los demás atributos
        Address address = addressOptional.get();

        // 2. Verificar que los campos no estén vacíos:
        // 2.1 Si employee.getAddress().getStreet() tiene tamaño, entonces(?) asocia a employee.getAddress().getStreet(), sino(:) se queda como estaba
        address.setStreet(StringUtils.hasLength(employee.getAddress().getStreet()) ? employee.getAddress().getStreet() : address.getStreet());

        // 2.2 Si employee.getAddress().getPostalCode() tiene tamaño, entonces(?) asocia a employee.getAddress().getPostalCode(), sino(:) se queda como estaba
        address.setPostalCode(StringUtils.hasLength(employee.getAddress().getPostalCode()) ? employee.getAddress().getPostalCode() : address.getPostalCode());

        // 2.3 Si employee.getAddress().getState() tiene tamaño, entonces(?) asocia a employee.getAddress().getState(), sino(:) se queda como estaba
        address.setState(StringUtils.hasLength(employee.getAddress().getState()) ? employee.getAddress().getState() : address.getState());

        employee.setAddress(address);

        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || !employeeRepository.existsById(id))
            return false;

        // Eliminar asociación de citas (fk_appointment_customer) antes de borrar el cliente
        List<Appointment> appointmentsToUpdate = appointmentService.findAllByEmployeeId(id);
        appointmentsToUpdate.forEach(appointment -> appointment.setEmployee(null));
        appointmentService.saveAll(appointmentsToUpdate);

        employeeRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        employeeRepository.deleteAll();
        return true;
    }
}
