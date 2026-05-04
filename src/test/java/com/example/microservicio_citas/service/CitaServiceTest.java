package com.example.microservicio_citas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.microservicio_citas.model.CitaMedica;
import com.example.microservicio_citas.repository.CitaRepository;

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock
    private CitaRepository repository;

    @InjectMocks
    private CitaServiceImpl service;

    // PRUEBA 2: Verifica la validación al programar (sin conflictos de horario)
    @Test
    void testProgramarCitaExito() {
        CitaMedica cita = new CitaMedica();
        cita.setFecha("2026-05-10");
        cita.setHora("10:00");

        when(repository.findByFechaAndHoraAndEstadoNot("2026-05-10", "10:00", "Cancelada"))
            .thenReturn(new ArrayList<>());
        when(repository.save(any(CitaMedica.class))).thenReturn(cita);

        CitaMedica resultado = service.programarCita(cita);
        assertNotNull(resultado);
        verify(repository, times(1)).save(cita);
    }

    // PRUEBA 3: Verifica el cambio de estado al cancelar
    @Test
    void testCancelarCita() {
        CitaMedica cita = new CitaMedica();
        cita.setEstado("Programada");

        when(repository.existsById(1L)).thenReturn(true);
        when(repository.findById(1L)).thenReturn(Optional.of(cita));
        when(repository.save(any(CitaMedica.class))).thenReturn(cita);

        CitaMedica cancelada = service.cancelarCita(1L);
        assertEquals("Cancelada", cancelada.getEstado());
    }
}
