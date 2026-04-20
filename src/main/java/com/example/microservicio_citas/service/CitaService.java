package com.example.microservicio_citas.service;

import com.example.microservicio_citas.model.CitaMedica;
import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<CitaMedica> obtenerTodas();
    Optional<CitaMedica> obtenerPorId(Long id);
    CitaMedica programarCita(CitaMedica cita);
    CitaMedica cancelarCita(Long id);
    void eliminarCita(Long id);
}
