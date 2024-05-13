package logica;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CalendarioTest {

    private Calendario calendario;
    private Usuario cliente;

    @Before
    public void setUp() {
        calendario = new Calendario();
        cliente = new Usuario(17121,"Juan", "Perez", "09393993","juan@gmail.com", "La ecuatoriana", new ArrayList<>());
    }



    @Test
    public void givenFechaNoDisponible_whenReservarCita_thenNoSeReserva() {

        Cita citaNoDisponible = calendario.getCitas().get(2);
        citaNoDisponible.setFecha(LocalDate.now().minusDays(1));

        assertFalse(calendario.reservarCita(citaNoDisponible, cliente));
    }

    @Test
    public void  givenFechaDisponible_whenReservarCita_thenSeReserva() {

        Cita citaDisponible = calendario.getCitas().get(9);

        assertTrue(calendario.reservarCita(citaDisponible, cliente));


    }


    @Test
    public void givenCitaYaAsignada_whenReservarCita_thenNoSeReservaNuevaCita() {
        Cita citaDisponible = calendario.getCitas().get(2);
        calendario.reservarCita(citaDisponible, cliente);

        assertFalse(calendario.reservarCita(citaDisponible, cliente));
    }



    @Test
    public void givenCitaReservada_whenEliminarCita_thenCitaEliminadaCorrectamente() {

        Cita citaReservada = calendario.getCitas().get(2);

        calendario.reservarCita(citaReservada, cliente);

        assertEquals( true,calendario.eliminarCita(citaReservada));


    }

    @Test
    public void givenCitaNoReservada_whenEliminarCita_thenNoSeEliminaCita() {

        Cita citaNoReservada = calendario.getCitas().get(2);

        assertEquals( false,calendario.eliminarCita(citaNoReservada));

    }

    @Test
    public void givenClienteReservaCita_whenVerificarClienteSeaElMismo_thenClienteEsCorrecto() {

        Cita citaDisponible = calendario.getCitas().get(2);


        calendario.reservarCita(citaDisponible, cliente);

        assertEquals(cliente, citaDisponible.getCliente());
    }

    @Test
    public void givenHoraNoValida_whenReservarCita_thenNoSeReserva() {

        Cita citaNoDisponible = calendario.getCitas().get(2);
        citaNoDisponible.setHora(LocalTime.parse("18:00"));

        assertFalse(calendario.reservarCita(citaNoDisponible, cliente));
    }

    @Test
    public void givenEditarHoraDeCita_whenReservarCita_thenHoraEditadaCorrectamente() {

        Cita citaNoDisponible = calendario.getCitas().get(2);
        citaNoDisponible.setHora(LocalTime.parse("14:30"));

        assertTrue(calendario.reservarCita(citaNoDisponible, cliente));
    }


}
