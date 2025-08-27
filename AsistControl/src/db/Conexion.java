/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristobalO.O
 */
public class Conexion {
    
    private Statement sen;
    private Connection con;
    private static Conexion conexion = null;

    public static Conexion getConnection(String bd) {
        if (null == conexion) {
            try {
                conexion = new Conexion(bd);
            } catch (SQLException ex) {
                System.err.println("ERROR: No se pudo intanciar la conexion");
            }
        }
        return conexion;
    }

    private Conexion(String bd) throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/" + bd + "?user=root&password=";
            System.out.println(url);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet ejecutar(String query) throws SQLException {
        System.out.println("[*] Ejecutando: " + query);
        sen = con.createStatement();

        if (query.toLowerCase().startsWith("select")) {
            return sen.executeQuery(query);
        } else {
            sen.executeUpdate(query);
            close();
            return null;
        }
    }
    public void close() throws SQLException {
        sen.close();
    }
}
