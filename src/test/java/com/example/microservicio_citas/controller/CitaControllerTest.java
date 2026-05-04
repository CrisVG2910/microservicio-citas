package com.example.microservicio_citas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.microservicio_citas.model.CitaMedica;
import com.example.microservicio_citas.service.CitaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CitaController.class)
public class CitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitaService service;

    @Autowired
    private ObjectMapper mapper;

    // PRUEBA 4: Verifica el endpoint POST para programar una cita
    @Test
    void testProgramarCitaEndpoint() throws Exception {
        CitaMedica cita = new CitaMedica();
        cita.setFecha("2026-06-01");
        cita.setHora("10:00");
        cita.setEstado("Programada");

        when(service.programarCita(any(CitaMedica.class))).thenReturn(cita);

        mockMvc.perform(post("/api/citas/programar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cita)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fecha").value("2026-06-01"));
    }
}
