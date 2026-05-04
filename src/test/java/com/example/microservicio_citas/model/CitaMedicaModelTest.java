package com.example.microservicio_citas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CitaMedicaModelTest {

    // PRUEBA 1: Verifica la asignación y la relación con Paciente
    @Test
    void testRelacionCitaPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Rex");

        CitaMedica cita = new CitaMedica();
        cita.setEstado("Programada");
        cita.setPaciente(paciente);

        assertEquals("Programada", cita.getEstado());
        assertEquals("Rex", cita.getPaciente().getNombre());
    }
}
