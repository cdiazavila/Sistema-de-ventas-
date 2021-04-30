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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IESJOBE
 */
public class LoginDAO {
    
    
     public boolean Registra(Login l) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO `empleado`(`idE`, `nombre`, `user`,"
                + " `clave`, `tipo` ) VALUES (?,?,?,?,?)";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, l.getIdE());
            pst.setString(2, l.getNombreE());
            pst.setString(3, l.getUser());
            pst.setString(4, l.getClave());
            pst.setString(5, l.getTipo());
            
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }  
     
      public boolean Modificar(Login l) {
          PreparedStatement pst = null;
           Connection conn = Conexion.getConexion();
        String sql = "UPDATE `empleado` SET `nombre`=?, `user`=?,`clave`=? WHERE `idE`=?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, l.getNombreE());
            pst.setString(2, l.getUser());
            pst.setString(3, l.getClave());
            pst.setString(4, l.getTipo());
            pst.setInt(4, l.getIdE());

            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
      
  public boolean Buscar(Login l) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM empleado WHERE idE=?";

        try {

            pst = conexion.prepareStatement(sql);
            pst.setInt(1, l.getIdE());
            rs = pst.executeQuery();

            if (rs.next()) {
                l.setIdE(Integer.parseInt(rs.getString(1)));
                l.setNombreE(rs.getString(2));
                l.setUser(rs.getString(3));
                l.setClave(rs.getString(4));
                l.setTipo(rs.getString(5));
               
              
                return true;

              }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
  
  
  
  public boolean Eliminar(Login l) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM `empleado` WHERE idE=?";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, l.getIdE());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
             conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

          }
  }
  
  
      public boolean ValidarLoginEmpleado(Login l) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql = "SELECT * FROM empleado WHERE ( user = ?) AND (clave = ?) AND (tipo = ?)";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setString(1, l.getUser());
            pst.setString(2, l.getClave());
            pst.setString(3, l.getTipo());
            rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    } 
      
       public ArrayList<Login> buscaridE(String ValorBusqueda) {
        ArrayList ListaIde= new ArrayList();

        Statement st = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql;


           sql = "SELECT idE FROM empleado WHERE empleado.user  ='" + ValorBusqueda + "'";


        Login L;

        try {

            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

               L = new Login();

                L.setIdE(Integer.parseInt(rs.getString(1)));
                ListaIde.add(L);

            }
            conexion.close();
        } catch (SQLException ex) {

            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return ListaIde;

    }
   }
