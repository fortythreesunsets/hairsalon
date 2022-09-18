package com.fortythreesunsets.hairsalon.controllers;

import com.fortythreesunsets.hairsalon.entities.Customer;
import com.fortythreesunsets.hairsalon.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Métodos CRUD

    /**
     * Consulta de todos los clientes
     * GET http://localhost:8080/api/customers
     */
    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    /**
     * Consulta de un cliente por ID
     * GET http://localhost:8080/api/customers/1
     * @param id de cliente
     * @return 200 OK si se encuentra el cliente
     * @return 404 Not Found si no se encuentra el cliente
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {

        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent())
            return ResponseEntity.ok(customerOptional.get());
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un cliente
     * POST http://localhost:8080/api/customers
     * @param customer
     * @return 400 Bad Request si no se crea el cliente
     * @return 200 OK si se crea el cliente
     */
    @PostMapping("/customers")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        if (customer.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(customerService.save(customer));
    }

    /**
     * Actualizar un cliente
     * PUT http://localhost:8080/api/customers
     * @param customer
     * @return 400 Bad Request si no se actualiza el cliente
     * @return 200 OK si se actualiza el cliente
     */
    @PutMapping("/customers")
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        if (customer.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(customerService.save(customer));
    }

    /**
     * Eliminar un cliente
     * DELETE http://localhost:8080/api/customers/1
     * @param id de cliente
     * @return 204 No Content si se elimina el cliente
     * @return 500 Internal Server Error si no se encuentra el cliente
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean result = customerService.deleteById(id);
        if (result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }

}
