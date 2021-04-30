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
public class VentaDAO  extends productoDAO{
    
     public boolean Registra(ventas v) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO `ventas`(`idE`, `fecha`) VALUES (?,?)";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, v.getIdE());
            pst.setString(2, v.getFecha());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }  
   
     
    public ArrayList<ventas> ultimo_registro() {
        ArrayList ultimo= new ArrayList();

        Statement st = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql;


           sql = "SELECT MAX(idV) FROM `ventas`";


         ventas v;

        try {

            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

               v = new ventas();

                v.setIdV(Integer.parseInt(rs.getString(1)));
                ultimo.add(v);

            }
            conexion.close();
        } catch (SQLException ex) {

            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return ultimo;

    }  
     
}
