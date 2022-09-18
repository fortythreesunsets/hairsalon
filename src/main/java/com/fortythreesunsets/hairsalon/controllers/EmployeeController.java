package com.fortythreesunsets.hairsalon.controllers;

import com.fortythreesunsets.hairsalon.entities.Employee;
import com.fortythreesunsets.hairsalon.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Métodos CRUD

    /**
     * Consulta de todos los empleados
     * GET http://localhost:8080/api/employees
     */
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * Consulta de un empleado por ID
     * GET http://localhost:8080/api/employees/1
     * @param id de empleado
     * @return 200 OK si se encuentra el empleado
     * @return 404 Not Found si no se encuentra el empleado
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if (employeeOptional.isPresent())
            return ResponseEntity.ok(employeeOptional.get());
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un empleado
     * POST http://localhost:8080/api/employees
     * @param employee
     * @return 400 Bad Request si no se crea el empleado
     * @return 200 OK si se crea el empleado
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        if (employee.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(employeeService.save(employee));
    }

    /**
     * Actualizar un empleado
     * PUT http://localhost:8080/api/employees
     * @param employee
     * @return 400 Bad Request si no se actualiza el empleado
     * @return 200 OK si se actualiza el empleado
     */
    @PutMapping("/employees")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        if (employee.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(employeeService.save(employee));
    }

    /**
     * Eliminar un empleado
     * DELETE http://localhost:8080/api/employees/1
     * @param id de empleado
     * @return 204 No Content si se elimina el empleado
     * @return 500 Internal Server Error si no se encuentra el empleado
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean result = employeeService.deleteById(id);
        if (result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }

}

