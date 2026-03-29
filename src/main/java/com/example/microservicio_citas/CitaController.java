package com.example.microservicio_citas;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CitaController {

    private List<CitaMedica> citas = new ArrayList<>();

    public CitaController() {
        // Inicializar con 3 registros en memoria
        citas.add(new CitaMedica(1, "2026-04-05", "10:00", "Programada", new Paciente("11111111-1", "Andrea Silva")));
        citas.add(new CitaMedica(2, "2026-04-05", "11:00", "Disponible", null));
        citas.add(new CitaMedica(3, "2026-04-06", "09:30", "Programada", new Paciente("22222222-2", "Roberto Gomez")));
    }

    // 1. Consultar disponibilidad de horarios
    @GetMapping("/citas")
    public List<CitaMedica> getDisponibilidad() {
        return citas;
    }

    // 2. Buscar cita por ID
    @GetMapping("/citas/{id}")
    public CitaMedica getCitaById(@PathVariable("id") int id) {
        for (CitaMedica cita : citas) {
            if (cita.getId() == id) {
                return cita;
            }
        }
        return null;
    }

    // 3. Programar una nueva cita usando Variables de Ruta
    @GetMapping(path = "/citas/programar/{id}/{fecha}/{hora}/{rut}/{nombre}")
    public Object programarCita(
            @PathVariable("id") int id,
            @PathVariable("fecha") String fecha,
            @PathVariable("hora") String hora,
            @PathVariable("rut") String rut,
            @PathVariable("nombre") String nombre) {

        // Comprobar si ya existe una cita programada en esa fecha y hora
        for (CitaMedica cita : citas) {
            if (cita.getFecha().equals(fecha) && cita.getHora().equals(hora) && !cita.getEstado().equals("Cancelada")) {
                return "{\"error\": \"El horario en la fecha " + fecha + " a las " + hora + " ya se encuentra ocupado.\"}";
            }
        }

        // Si pasa la validación, creamos la nueva cita
        Paciente nuevoPaciente = new Paciente(rut, nombre);
        CitaMedica nuevaCita = new CitaMedica(id, fecha, hora, "Programada", nuevoPaciente);
        citas.add(nuevaCita);

        return nuevaCita;
    }

    // 4. Cancelar una cita médica
    @GetMapping("/citas/cancelar/{id}")
    public CitaMedica cancelarCita(@PathVariable("id") int id) {
        for (CitaMedica cita : citas) {
            if (cita.getId() == id) {
                cita.setEstado("Cancelada");
                return cita;
            }
        }
        return null;
    }
}
