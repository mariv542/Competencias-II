/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

import db.DataManager;
import java.time.LocalDateTime;
import model.Asistencia;
import model.Usuario;

/**
 *
 * @author cristobalO.O
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     try {
            // 1. Crear instancia de DataManager (esto ya crea la conexión y los DAO)
            DataManager dm = new DataManager();

            // 2. Crear un nuevo usuario de prueba
            Usuario nuevo = new Usuario();
            nuevo.setRut("11.111.111-1");
            nuevo.setNombre("Cristobal");
            nuevo.setApellido("Orellana");
            nuevo.setCorreo("cristobal@test.com");
            nuevo.setContraseña("12345");
            nuevo.setRol("administrador");
            nuevo.setEstado("activo");

            // 3. Guardar en la base de datos usando el DaoUsuario
            dm.getDataUsuario().agregar(nuevo);

            System.out.println("✅ Usuario agregado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
     /*
     //para poder elimar el usuario 1  sin precarga 
     try {
            DataManager dm = new DataManager();

            // 🔹 Eliminar un usuario con id_usuario = 3 (ejemplo)
            int idEliminar = 1;
            dm.getDataUsuario().delete(idEliminar);

            System.out.println(" Usuario con ID " + idEliminar + " eliminado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    
*/
             try {
            DataManager dm = new DataManager();

            // Creamos una nueva asistencia
                 Asistencia a = new Asistencia();
            a.setIdUsuario(1); // asume que el usuario con id 1 existe
            a.setFechaHoraEntrada(LocalDateTime.now());
            a.setFechaHoraSalida(null); // todavía no salió
            a.setEstado("Presente");

            // Insertamos usando el DAO
            dm.getDataAsistencia().agregar(a);

            System.out.println("Asistencia insertada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
    
    
        
}
    

