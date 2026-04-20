package com.example.microservicio_citas.controller;

import com.example.microservicio_citas.model.CitaMedica;
import com.example.microservicio_citas.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // 1. Consultar todas las citas (incluye disponibilidad) (GET)
    @GetMapping
    public List<CitaMedica> getAllCitas() {
        return citaService.obtenerTodas();
    }

    // 2. Buscar cita por ID (GET)
    @GetMapping("/{id}")
    public Optional<CitaMedica> getCitaById(@PathVariable Long id) {
        return citaService.obtenerPorId(id);
    }

    // 3. Programar una nueva cita (POST)
    @PostMapping("/programar")
    public CitaMedica programarCita(@Valid @RequestBody CitaMedica cita) {
        return citaService.programarCita(cita);
    }

    // 4. Cancelar una cita médica (PUT)
    @PutMapping("/cancelar/{id}")
    public CitaMedica cancelarCita(@PathVariable Long id) {
        return citaService.cancelarCita(id);
    }

    // 5. Eliminar (DELETE) - Extra para tener el CRUD completo
    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
    }
}
