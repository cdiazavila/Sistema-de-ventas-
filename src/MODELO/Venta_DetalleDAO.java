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
public class Venta_DetalleDAO {
    
    
     public boolean Registra(venta_Detalle vd) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "INSERT INTO `venta_detalle`(`idV`, `idP`, `cantidad`, `precio`) VALUES (?,?,?,?)";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, vd.getIdV());
            pst.setInt(2, vd.getIdP());
            pst.setInt(3, vd.getCantidad());
            pst.setInt(4, vd.getPrecio());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Venta_DetalleDAO .class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Venta_DetalleDAO .class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    } 
     
  
      public ArrayList<venta_Detalle> ultimo_registroventa() {
        ArrayList ultimo= new ArrayList();

        Statement st = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql;


           sql = "SELECT MAX(idVentas_detalle) FROM `venta_detalle`";


         venta_Detalle vd;

        try {

            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

               vd = new venta_Detalle();

                vd.setIdventa_detalle(Integer.parseInt(rs.getString(1)));
                ultimo.add(vd);

            }
            conexion.close();
        } catch (SQLException ex) {

            Logger.getLogger(Venta_DetalleDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return ultimo;

    }  
      
       public boolean Eliminar(venta_Detalle vd) {
        PreparedStatement pst = null;
        Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM `venta_detalle` WHERE idVentas_detalle=?";

        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, vd.getIdventa_detalle());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Venta_DetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Venta_DetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

          }
       }
       
       
      public ArrayList<venta_Detalle> ListarVentas(String ValorBusqueda, String TipoBusquedad) {
        ArrayList ListaVentas= new ArrayList();

        Statement st = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql;

        if (ValorBusqueda.equals("")) {
            sql = "SELECT empleado.idE,empleado.nombre,productos.Nombre,productos.caracteristica,"
                    + "productos.precio,venta_detalle.cantidad,venta_detalle.precio,ventas.fecha "
                    + "FROM venta_detalle,ventas,productos,empleado WHERE (ventas.idV=venta_detalle.idV "
                    + "AND venta_detalle.idP=productos.idP AND ventas.idE=empleado.idE) LIMIT 15";
        } else {
           sql = "SELECT empleado.idE,empleado.nombre,productos.Nombre,productos.caracteristica,"
                   + "productos.precio,venta_detalle.cantidad,venta_detalle.precio,ventas.fecha "
                   + "FROM venta_detalle,ventas,productos,empleado WHERE (ventas.idV=venta_detalle.idV "
                   + "AND venta_detalle.idP=productos.idP AND ventas.idE=empleado.idE)  AND "
                    + TipoBusquedad + " = '" + ValorBusqueda + "'";
       }

        venta_Detalle vd;

        try {

            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                vd= new venta_Detalle();

                vd.setIdE(Integer.parseInt(rs.getString(1)));
                vd.setNombreE(rs.getString(2));
                vd.setNombre(rs.getString(3));
                vd.setCaracteristica(rs.getString(4));
                vd.setPrecio(Integer.parseInt(rs.getString(5)));
                vd.setCantidad(Integer.parseInt(rs.getString(6)));
                vd.setPrecioT(Integer.parseInt(rs.getString(7)));
                vd.setFecha(rs.getString(8));
                
                ListaVentas.add(vd);
            }
            conexion.close();
        } catch (SQLException ex) {

            Logger.getLogger(Venta_DetalleDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return ListaVentas;

    } 
      
      
      
       public ArrayList<venta_Detalle> calcularP() {
        ArrayList Lista= new ArrayList();

        Statement st = null;
        ResultSet rs = null;
        Connection conexion = Conexion.getConexion();
        String sql;


           sql = "SELECT SUM(precio) FROM `venta_detalle`";


        venta_Detalle vd;

        try {

            st = conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

               vd = new venta_Detalle();

                vd.setCantidadvendida(Float.parseFloat(rs.getString(1)));
                Lista.add(vd);

            }
            conexion.close();
        } catch (SQLException ex) {

            Logger.getLogger(Venta_DetalleDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return Lista;

    }
}
