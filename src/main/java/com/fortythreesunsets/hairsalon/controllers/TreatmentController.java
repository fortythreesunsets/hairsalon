package com.fortythreesunsets.hairsalon.controllers;

import com.fortythreesunsets.hairsalon.entities.Treatment;
import com.fortythreesunsets.hairsalon.service.TreatmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    // Métodos CRUD

    /**
     * Consulta de todos los servicios
     * GET http://localhost:8080/api/treatments
     */
    @GetMapping("/treatments")
    public List<Treatment> findAll() {
        return treatmentService.findAll();
    }

    /**
     * Consulta de un servicio por ID
     * GET http://localhost:8080/api/treatments/1
     * @param id de servicio
     * @return 200 OK si se encuentra el servicio
     * @return 404 Not Found si no se encuentra el servicio
     */
    @GetMapping("/treatments/{id}")
    public ResponseEntity<Treatment> findById(@PathVariable Long id) {

        Optional<Treatment> treatmentOptional = treatmentService.findById(id);
        if (treatmentOptional.isPresent())
            return ResponseEntity.ok(treatmentOptional.get());
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un servicio
     * POST http://localhost:8080/api/treatments
     * @param treatment
     * @return 400 Bad Request si no se crea el servicio
     * @return 200 OK si se crea el servicio
     */
    @PostMapping("/treatments")
    public ResponseEntity<Treatment> create(@RequestBody Treatment treatment) {
        if (treatment.getId() != null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(treatmentService.save(treatment));
    }

    /**
     * Actualizar un servicio
     * PUT http://localhost:8080/api/treatments
     * @param treatment
     * @return 400 Bad Request si no se actualiza el servicio
     * @return 200 OK si se actualiza el servicio
     */
    @PutMapping("/treatments")
    public ResponseEntity<Treatment> update(@RequestBody Treatment treatment) {
        if (treatment.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(treatmentService.save(treatment));
    }

    /**
     * Eliminar un servicio
     * DELETE http://localhost:8080/api/treatments/1
     * @param id de servicio
     * @return 204 No Content si se elimina el servicio
     * @return 500 Internal Server Error si no se encuentra el servicio
     */
    @DeleteMapping("/treatments/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean result = treatmentService.deleteById(id);
        if (result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }

}
