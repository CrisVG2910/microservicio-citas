package com.example.microservicio_citas;

public class CitaMedica {
    private int id;
    private String fecha;
    private String hora;
    private String estado;
    private Paciente paciente;

    public CitaMedica(int id, String fecha, String hora, String estado, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
