/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author IESJOBE
 */
public class venta_Detalle extends ventas {
    private int idventa_detalle;
    private int idP;
    private int cantidad;
    private int precioT;
    private float cantidadvendida;

    public int getIdventa_detalle() {
        return idventa_detalle;
    }

    public void setIdventa_detalle(int idventa_detalle) {
        this.idventa_detalle = idventa_detalle;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioT() {
        return precioT;
    }

    public void setPrecioT(int precioT) {
        this.precioT = precioT;
    }

    public float getCantidadvendida() {
        return cantidadvendida;
    }

    public void setCantidadvendida(float cantidadvendida) {
        this.cantidadvendida = cantidadvendida;
    }
    

   

  
    
    
}
