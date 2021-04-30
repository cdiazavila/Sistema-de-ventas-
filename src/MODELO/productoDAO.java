/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IESJOBE
 */
public class productoDAO {
    public boolean Registra(producto p) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO `productos`(`idP`, `Nombre`, `caracteristica`,"
                + " `precio`) VALUES (?,?,?,?)";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, p.getIdP());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getCaracteristica());
            pst.setInt(4, p.getPrecio());
            
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }  
    
    
     public boolean Buscar(producto p) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM productos WHERE idP=?";

        try {

            pst = conexion.prepareStatement(sql);
            pst.setInt(1, p.getIdP());
            rs = pst.executeQuery();

            if (rs.next()) {
                p.setIdP(Integer.parseInt(rs.getString(1)));
                p.setNombre(rs.getString(2));
                p.setCaracteristica(rs.getString(3));
                p.setPrecio(Integer.parseInt(rs.getString(4)));
               
              
                return true;

              }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
       
    
    
     public boolean Modificar(producto p) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "UPDATE `productos` SET `Nombre`= ?,`caracteristica`= ?,"
                + "`precio`= ? WHERE `idP`=?";

        try {
            pst = conexion.prepareStatement(sql);
       
        pst.setString(1, p.getNombre());
        pst.setString(2, p.getCaracteristica());
        pst.setInt(3, p.getPrecio());
         pst.setInt(4, p.getIdP());

            pst.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
 


      public boolean Eliminar(producto p) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM `productos` WHERE idP=?";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, p.getIdP());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(productoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

          }
       }
     
}
