
package dao;

import db.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Usuario;

/**
 *
 * @author cristobalO.O
 */
public class DaoUsuario {
    private Conexion conexion;

    public DaoUsuario(Conexion conexion) {
        this.conexion = conexion;
    }
    
        public void agregar(Usuario u) throws SQLException {
        int nuevaId = getUltimaId();

        String sql = "INSERT INTO Usuarios(id_usuario, rut, nombre, apellido, correo, contraseña, rol, estado) VALUES("
                + nuevaId + ", '"
                + u.getRut() + "', '"
                + u.getNombre() + "', '"
                + u.getApellido() + "', '"
                + u.getCorreo() + "', '"
                + u.getContraseña() + "', '"
                + u.getRol() + "', '"
                + u.getEstado() + "')";

        conexion.ejecutar(sql);
    }

    public void update(Usuario u) throws SQLException {
    String sql = "UPDATE Usuarios SET "
                + "rut = '" + u.getRut() + "', "
                + "nombre = '" + u.getNombre() + "', "
                + "apellido = '" + u.getApellido() + "', "
                + "correo = '" + u.getCorreo() + "', "
                + "contraseña = '" + u.getContraseña() + "', "
                + "rol = '" + u.getRol() + "', "
                + "estado = '" + u.getEstado() + "' "
                + "WHERE id_usuario = " + u.getIdUsuario();

        System.out.println(sql);
        conexion.ejecutar(sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = " + id;// siempre  con WHERE  
        conexion.ejecutar(sql);

    }

    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        ResultSet rs = conexion.ejecutar(sql);

        List<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setRut(rs.getString("rut"));
            u.setNombre(rs.getString("nombre"));
            u.setApellido(rs.getString("apellido"));
            u.setCorreo(rs.getString("correo"));
            u.setContraseña(rs.getString("contraseña"));
            u.setRol(rs.getString("rol"));
            u.setEstado(rs.getString("estado"));
            
            lista.add(u);
            //System.out.println(lista);
        }
        conexion.close();
        return lista;
    }

     public List<Usuario> getAll(String filtro) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE nombre LIKE'" + filtro + "%'";
        ResultSet rs = conexion.ejecutar(sql);

        List<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setRut(rs.getString("rut"));
            u.setNombre(rs.getString("nombre"));
            u.setApellido(rs.getString("apellido"));
            u.setCorreo(rs.getString("correo"));
            u.setContraseña(rs.getString("contraseña"));
            u.setRol(rs.getString("rol"));
            u.setEstado(rs.getString("estado"));
            
            lista.add(u);
            //System.out.println(lista);
        }
        conexion.close();
        return lista;
    }
     
    public Usuario getOne(int id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id_usuario = " + id;
        ResultSet rs = conexion.ejecutar(sql);
        if (rs.next()) {
            return new Usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("rut"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("contraseña"),
                    rs.getString("rol"),
                    rs.getString("estado")
            );
        }
        conexion.close();
        return null;
    }
    
    
    public int getUltimaId() throws SQLException {
        String sql = "SELECT MAX(id_usuario) AS ultimaId FROM Usuarios";
        ResultSet rs = conexion.ejecutar(sql);
        int nuevaId = 0;
        if (rs.next()) {
            nuevaId = rs.getInt("ultimaId");
        }
        return nuevaId + 1;
    }
    
    
}
