package logica;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarioTestMockito {
    private Calendario calendario;
    private List<Cita> citasMock;
    private Usuario clienteMock;
    private Cita citaMock;

    @Before
    public void setUp() {
        calendario = new Calendario();
        citasMock = mock(ArrayList.class);
        clienteMock = mock(Usuario.class);
        citaMock = mock(Cita.class);
    }


    @Test
    public void reservarCita_FechaInvalida_RetornaFalso() {
        when(citaMock.getFecha()).thenReturn(LocalDate.now().minusDays(1));
        when(citaMock.getHora()).thenReturn(LocalTime.parse("10:00"));
        when(citaMock.getEstado()).thenReturn(Estado.DISPONIBLE);

        assertFalse(calendario.reservarCita(citaMock, clienteMock));
    }


    @Test
    public void reservarCita_CitaNoDisponible_RetornaFalso() {
        when(citaMock.getFecha()).thenReturn(LocalDate.now().plusDays(1));
        when(citaMock.getHora()).thenReturn(LocalTime.parse("10:00"));
        when(citaMock.getEstado()).thenReturn(Estado.NO_DISPONIBLE);

        assertFalse(calendario.reservarCita(citaMock, clienteMock));
    }


}
