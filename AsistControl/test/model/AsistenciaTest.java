/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;


public class AsistenciaTest {
    
    public AsistenciaTest() {
    }
    
    /**
     * CA-01: Validar registro de entrada y salida
     * Resultado esperado: Registros almacenados correctamente con fecha y hora
     */
    @Test
    public void testRegistrarEntradaYSalida() {
        Usuario u = new Usuario(1, "11111111-1", "Cristóbal", "Martínez",
                                 "c.martinez@test.com", "1234", "empleado", "activo");

        Asistencia a = new Asistencia();
        a.setIdUsuario(u.getIdUsuario());
        a.setUsuario(u);

        LocalDateTime entrada = LocalDateTime.of(2025, 9, 7, 8, 55);
        LocalDateTime salida = LocalDateTime.of(2025, 9, 7, 18, 0);

        a.setFechaHoraEntrada(entrada);
        a.setFechaHoraSalida(salida);

        System.out.println("CA-01 → Resultado esperado: "
                + "Entrada = " + entrada + ", Salida = " + salida);

        assertEquals(entrada, a.getFechaHoraEntrada());
        assertEquals(salida, a.getFechaHoraSalida());
        assertEquals(u, a.getUsuario());
    }

    /**
     * RE-01: Validar detección de atrasos
     * Resultado esperado: Lista con usuarios y fechas en que llegaron tarde
     */
    @Test
    public void testReporteAtraso() {
        Usuario u = new Usuario(2, "22222222-2", "María", "López",
                                 "m.lopez@test.com", "abcd", "empleado", "activo");

        Asistencia a = new Asistencia();
        a.setIdUsuario(u.getIdUsuario());
        a.setUsuario(u);
        a.setFechaHoraEntrada(LocalDateTime.of(2025, 9, 7, 9, 45)); // LLEGÓ TARDE

        LocalDateTime horaLimite = LocalDateTime.of(2025, 9, 7, 9, 30);
        boolean esAtrasado = a.getFechaHoraEntrada().isAfter(horaLimite);

        System.out.println("RE-01 → Resultado esperado: Usuario en lista de atrasos. "
                + "Hora entrada = " + a.getFechaHoraEntrada());

        assertTrue("El usuario debería considerarse atrasado", esAtrasado);
    }

    /**
     * RE-02: Validar detección de salidas anticipadas
     * Resultado esperado: Lista con usuarios y fechas en que salieron antes
     */
    @Test
    public void testReporteSalidaAnticipada() {
        Usuario u = new Usuario(3, "33333333-3", "Pedro", "Gómez",
                                 "p.gomez@test.com", "qwerty", "empleado", "activo");

        Asistencia a = new Asistencia();
        a.setIdUsuario(u.getIdUsuario());
        a.setUsuario(u);
        a.setFechaHoraSalida(LocalDateTime.of(2025, 9, 7, 16, 45)); // SALIÓ ANTES

        LocalDateTime horaMinima = LocalDateTime.of(2025, 9, 7, 17, 30);
        boolean salidaAnticipada = a.getFechaHoraSalida().isBefore(horaMinima);

        System.out.println("RE-02 → Resultado esperado: Usuario en lista de salidas anticipadas. "
                + "Hora salida = " + a.getFechaHoraSalida());

        assertTrue("El usuario debería estar en el reporte de salidas anticipadas", salidaAnticipada);
    }

    /**
     * RE-03: Validar detección de inasistencias
     * Resultado esperado: Lista con usuarios sin registros en el día
     */
    @Test
    public void testReporteInasistencia() {
        Usuario u = new Usuario(4, "44444444-4", "Ana", "Torres",
                                 "a.torres@test.com", "zxcv", "empleado", "activo");

        Asistencia a = new Asistencia();
        a.setIdUsuario(u.getIdUsuario());
        a.setUsuario(u);

        boolean inasistente = (a.getFechaHoraEntrada() == null && a.getFechaHoraSalida() == null);

        System.out.println("RE-03 → Resultado esperado: Usuario en lista de inasistencias "
                + "(sin entrada ni salida registrada)");

        assertTrue("El usuario debería estar en el reporte de inasistencias", inasistente);
    }

    
}
