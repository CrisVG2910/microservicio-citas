package com.example.microservicio_citas;

public class Paciente {
    private String rut;
    private String nombre;

    public Paciente(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }
}
