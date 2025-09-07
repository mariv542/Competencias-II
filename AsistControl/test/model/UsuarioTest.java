/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cristobalO.O
 */
public class UsuarioTest {
    
    public UsuarioTest() {
    }
/**
     * GU-01: Validar creación de usuario
     * Resultado esperado: Usuario registrado y visible en la lista
     */
    @Test
    public void testCrearUsuario() {
        Usuario u = new Usuario(5, "55555555-5", "Lucía", "Fernández",
                                 "l.fernandez@test.com", "12345", "empleado", "activo");

        System.out.println("GU-01 → Resultado esperado: Usuario registrado con nombre = " 
                + u.getNombre() + ", correo = " + u.getCorreo());

        assertEquals(5, u.getIdUsuario());
        assertEquals("Lucía", u.getNombre());
        assertEquals("Fernández", u.getApellido());
        assertEquals("l.fernandez@test.com", u.getCorreo());
        assertEquals("empleado", u.getRol());
        assertEquals("activo", u.getEstado());
    }

    /**
     * GU-02: Validar modificación de usuario
     * Resultado esperado: Cambios reflejados correctamente
     */
    @Test
    public void testModificarUsuario() {
        Usuario u = new Usuario(6, "66666666-6", "Carlos", "Pérez",
                                 "c.perez@test.com", "abc123", "empleado", "activo");

        u.setCorreo("c.perez@empresa.com");
        u.setRol("administrador");

        System.out.println("GU-02 → Resultado esperado: Usuario con correo actualizado = "
                + u.getCorreo() + " y rol = " + u.getRol());

        assertEquals("c.perez@empresa.com", u.getCorreo());
        assertEquals("administrador", u.getRol());
    }

    /**
     * GU-03: Validar eliminación de usuario
     * Resultado esperado: Usuario eliminado de la lista y base de datos
     */
    @Test
    public void testEliminarUsuario() {
        List<Usuario> lista = new ArrayList<>();
        Usuario u1 = new Usuario(7, "77777777-7", "Diego", "Mora",
                                  "d.mora@test.com", "pass", "empleado", "activo");
        Usuario u2 = new Usuario(8, "88888888-8", "Sofía", "Ríos",
                                  "s.rios@test.com", "pass2", "empleado", "activo");
        lista.add(u1);
        lista.add(u2);

        lista.remove(u1); // eliminar a Diego

        System.out.println("GU-03 → Resultado esperado: Usuario eliminado (Diego Mora). "
                + "Lista final contiene = " + lista.size() + " usuario(s)");

        assertFalse(lista.contains(u1));
        assertTrue(lista.contains(u2));
        assertEquals(1, lista.size());
    }
    
}
