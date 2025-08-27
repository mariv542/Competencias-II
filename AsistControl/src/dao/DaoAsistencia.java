/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.Conexion;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Asistencia;

/**
 *
 * @author cristobalO.O
 */
public class DaoAsistencia {

    private Conexion conexion;

    public DaoAsistencia(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public void agregar(Asistencia a) throws SQLException {
        int nuevaId = getUltimaId();

        String entrada = "NULL";
        if (a.getFechaHoraEntrada() != null) {
            entrada = "'" + Timestamp.valueOf(a.getFechaHoraEntrada()) + "'";
        }

        String salida = "NULL";
        if (a.getFechaHoraSalida() != null) {
            salida = "'" + Timestamp.valueOf(a.getFechaHoraSalida()) + "'";
        }

        String sql = "INSERT INTO Asistencias(id_asistencia, id_usuario, fecha_hora_entrada, fecha_hora_salida, estado) VALUES("
                + nuevaId + ", "
                + a.getIdUsuario() + ", "
                + entrada + ", "
                + salida + ", "
                + "'" + a.getEstado() + "')";

        System.out.println(sql);
        conexion.ejecutar(sql);
    }
    public void update(Asistencia a) throws SQLException {
        String entrada = "NULL";
        if (a.getFechaHoraEntrada() != null) {
            entrada = "'" + Timestamp.valueOf(a.getFechaHoraEntrada()) + "'";
        }

        String salida = "NULL";
        if (a.getFechaHoraSalida() != null) {
            salida = "'" + Timestamp.valueOf(a.getFechaHoraSalida()) + "'";
        }

        String sql = "UPDATE Asistencias SET "
                + "id_usuario = " + a.getIdUsuario() + ", "
                + "fecha_hora_entrada = " + entrada + ", "
                + "fecha_hora_salida = " + salida + ", "
                + "estado = '" + a.getEstado() + "' "
                + "WHERE id_asistencia = " + a.getIdAsistencia();

        System.out.println(sql);
        conexion.ejecutar(sql);
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Asistencias WHERE id_asistencia = " + id;
        conexion.ejecutar(sql);
    }
    
public List<Asistencia> getAll() throws SQLException {
    String sql = "SELECT * FROM Asistencias";
    ResultSet rs = conexion.ejecutar(sql);

    List<Asistencia> lista = new ArrayList<>();
    while (rs.next()) {
        Asistencia a = new Asistencia();
        a.setIdAsistencia(rs.getInt("id_asistencia"));
        a.setIdUsuario(rs.getInt("id_usuario"));

        Timestamp entrada = rs.getTimestamp("fecha_hora_entrada");
        if (entrada != null) a.setFechaHoraEntrada(entrada.toLocalDateTime());

        Timestamp salida = rs.getTimestamp("fecha_hora_salida");
        if (salida != null) a.setFechaHoraSalida(salida.toLocalDateTime());

        a.setEstado(rs.getString("estado"));

        lista.add(a);
    }
    return lista;
}
    
    public Asistencia getOne(int id) throws SQLException {
        String sql = "SELECT * FROM Asistencias WHERE id_asistencia = " + id;
        ResultSet rs = conexion.ejecutar(sql);
        
        if (rs.next()) {
            return new Asistencia(
                rs.getInt("id_asistencia"),
                rs.getInt("id_usuario"),
                rs.getTimestamp("fecha_hora_entrada").toLocalDateTime(),
                rs.getTimestamp("fecha_hora_salida") != null ? rs.getTimestamp("fecha_hora_salida").toLocalDateTime() : null,
                rs.getString("estado")
            );
        }
        return null;
    }
    
    public int getUltimaId() throws SQLException {
        String sql = "SELECT MAX(id_asistencia) AS ultimaId FROM Asistencias";
        ResultSet rs = conexion.ejecutar(sql);
        int nuevaId = 0;
        if (rs.next()) {
            nuevaId = rs.getInt("ultimaId");
        }
        return nuevaId + 1;
    }

}
