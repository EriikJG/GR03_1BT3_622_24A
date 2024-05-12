package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Cita {
    @Override
    public String toString() {
        return "Cita{" +
                "cliente=" + cliente +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", estado=" + estado +
                '}';
    }

    private Usuario cliente;
    private LocalDate fecha;
    private LocalTime hora;

    private Estado estado;


    public Cita(Usuario cliente, LocalDate fecha, LocalTime hora) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = Estado.DISPONIBLE;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


    // Getters y setters según sea necesario
}
