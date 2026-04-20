package com.example.microservicio_citas.service;

import com.example.microservicio_citas.model.CitaMedica;
import com.example.microservicio_citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public List<CitaMedica> obtenerTodas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<CitaMedica> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public CitaMedica programarCita(CitaMedica cita) {
        // Tu validación: Comprobamos si la fecha y hora ya están ocupadas por una cita que NO esté "Cancelada"
        List<CitaMedica> conflictos = citaRepository.findByFechaAndHoraAndEstadoNot(cita.getFecha(), cita.getHora(), "Cancelada");

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("El horario en la fecha " + cita.getFecha() + " a las " + cita.getHora() + " ya se encuentra ocupado.");
        }

        cita.setEstado("Programada");
        return citaRepository.save(cita);
    }

    @Override
    public CitaMedica cancelarCita(Long id) {
        if (citaRepository.existsById(id)) {
            CitaMedica citaExistente = citaRepository.findById(id).get();
            citaExistente.setEstado("Cancelada");
            return citaRepository.save(citaExistente);
        }
        throw new RuntimeException("Cita no encontrada con el ID proporcionado");
    }

    @Override
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
