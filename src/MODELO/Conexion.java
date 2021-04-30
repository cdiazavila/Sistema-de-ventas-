/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author IESJOBE
 */
public class Conexion {
   public static Connection conn= null;

    private Conexion() {

    }

    public static Connection getConexion() {
        try {

            //cargar nuestro driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bd_ventas", "root", "");
            System.out.println("conexion establecida");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error de conexion");
            JOptionPane.showMessageDialog(null, "error de conexion " + e);
        }

        return conn;

    }   
}
