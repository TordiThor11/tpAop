package proyecto.model;

import java.time.LocalDate;

public class Concurso {
    private int id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Concurso(int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaAbierto() {
        return (LocalDate.now().isAfter(fechaInicio)) && (LocalDate.now().isBefore(fechaFin));
    }

    public boolean estaCerrado() {
        return !this.estaAbierto();
    }

    @Override
    public String toString() {
        return id + nombre + fechaInicio + fechaFin;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
