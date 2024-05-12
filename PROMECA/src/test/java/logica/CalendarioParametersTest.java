package logica;

import logica.Calendario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalendarioParametersTest {
    private Calendario calendario;
    private Cita cita;
    private Usuario cliente;
    private boolean expected;

    // Constructor para inicializar los parámetros
    public CalendarioParametersTest(Cita cita, Usuario cliente, boolean expected) {
        this.cita = cita;
        this.cliente = cliente;
        this.expected = expected;
    }

    // Método que proporciona los datos de prueba
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Caso de prueba 1: Cita disponible para reservar
                {new Cita(null, LocalDate.now(), LocalTime.parse("09:00")), new Usuario(), false},
                // Caso de prueba 2: Cita no disponible para reservar
                {new Cita(null, LocalDate.now().plusDays(1), LocalTime.parse("10:00")), new Usuario(), false},
                // Caso de prueba 3: Cita fuera del horario
                {new Cita(null, LocalDate.now(), LocalTime.parse("08:00")), new Usuario(), false},
                // Caso de prueba 4: Eliminar cita disponible
                {new Cita(null, LocalDate.now(), LocalTime.parse("11:00")), new Usuario(), false},
                // Caso de prueba 5: Eliminar cita no disponible
                {new Cita(new Usuario(), LocalDate.now(), LocalTime.parse("12:00")), new Usuario(), false}
        });
    }


    // Prueba para la reserva de cita y eliminación de cita
    @Test
    public void dadoParametros_cuandoReservar_entoncesResultadoEsperado() {
        calendario = new Calendario();
        // Prueba de reserva de cita
        assertEquals(expected, calendario.reservarCita(cita, cliente));

    }
    @Test
    public void dadoParametros_cuandoEliminar_entoncesResultadoEsperado() {
        calendario = new Calendario();
        // Prueba de eliminación de cita
        assertEquals(expected, calendario.eliminarCita(cita));
    }




}
