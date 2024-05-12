package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Cita> citas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Calendario() {
        this.citas = new ArrayList<>();
        // Inicializar el calendario con citas disponibles para todo el año
        this.fechaInicio = LocalDate.now();
        this.fechaFin = fechaInicio.plusYears(1); // Obtener la fecha de dentro de un año
        LocalDate fechaInicio1 = fechaInicio;

        while (!fechaInicio1.isAfter(fechaFin)) {
            // Agregar citas para cada hora del día
            horaInicio = LocalTime.parse("09:00");
            horaFin = LocalTime.parse("17:00");
            LocalTime horaInicio1 = horaInicio;
            while (!horaInicio1.isAfter(horaFin)) {
                citas.add(new Cita(null, fechaInicio1, horaInicio1));
                horaInicio1 = horaInicio1.plusHours(1);
            }

            fechaInicio1 = fechaInicio1.plusDays(1);
        }
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public boolean reservarCita(Cita cita, Usuario cliente) {
        boolean fechaValida = !cita.getFecha().isBefore(fechaInicio) && !cita.getFecha().isAfter(fechaFin);
        boolean horaValida = !cita.getHora().isBefore(horaInicio) && !cita.getHora().isAfter(horaFin);
        if (fechaValida && horaValida && esCitaDisponible(cita)) {
            // Reservar la cita
            cita.setCliente(cliente);
            cita.setEstado(Estado.NO_DISPONIBLE);
            return true;
        }
        return false;
    }

    private boolean esCitaDisponible(Cita cita) {
        return citas.stream().anyMatch(c -> c.equals(cita) && c.getEstado().equals(Estado.DISPONIBLE));
    }

    public boolean eliminarCita(Cita cita) {
        boolean removed = citas.removeIf(c -> c.equals(cita) && c.getEstado().equals(Estado.NO_DISPONIBLE));
        if (removed) {
            cita.setEstado(Estado.DISPONIBLE);
            cita.setCliente(null);
        }
        return removed;
    }
}
