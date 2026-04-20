package com.example.microservicio_citas.repository;

import com.example.microservicio_citas.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<CitaMedica, Long> {
    List<CitaMedica> findByFechaAndHoraAndEstadoNot(String fecha, String hora, String estado);
}
