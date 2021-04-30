/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import static CONTROLADOR.ControladorLogin.vector;
import MODELO.*;
import VISTA.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IESJOBE
 */
public class controladorGenerarReporte  implements ActionListener, MouseListener {

    private venta_Detalle modeloVentaDetalle;
    private Venta_DetalleDAO ModeloDetalleDAO;
    private VistaReporte vistaR;

    public controladorGenerarReporte(venta_Detalle modeloVentaDetalle, Venta_DetalleDAO ModeloDetalleDAO, VistaReporte vistaR) {
        this.modeloVentaDetalle = modeloVentaDetalle;
        this.ModeloDetalleDAO = ModeloDetalleDAO;
        this.vistaR = vistaR;
        
        vistaR.btnBuscar.addActionListener(this);
        this.vistaR.btnActualizar.addActionListener(this);
        this.vistaR.btnCalcular.addActionListener(this);
        
        this.vistaR.jMenuSalir.addMouseListener(this);
        this.vistaR.menuAdmistrarEmpleado.addMouseListener(this);
         this.vistaR.menuProductos.addMouseListener(this);
          this.vistaR.menuGenerarVenta.addMouseListener(this);
    }
    
    
     public void InicioListaVentas(){
        vistaR.setLocationRelativeTo(null);
        vistaR.setTitle("Ventana Lista de ventas ");
       
       MostrarListaVentas("","");
        combox();
        this.calcularpago();
       
    }
    
    // este metodo llamo las opciones de busquedas 
      public void combox(){
        vistaR.ComboBoxReporte.addItem("ID Empleado");
        vistaR.ComboBoxReporte.addItem("Fecha de venta");
        vistaR.ComboBoxReporte.addItem("ID Producto");
        
      }
     
      
      public void   MostrarListaVentas(String ValorBusqueda,String TipoBusquedad){
        DefaultTableModel modelTable = new DefaultTableModel();
        vistaR.tablaListarventas.setModel(modelTable);
        modelTable.addColumn("Id empleado");
        modelTable.addColumn("Nombre Empleado");
        modelTable.addColumn("Nombre Producto");
        modelTable.addColumn("Caracteristica");
        modelTable.addColumn("Precio");
        modelTable.addColumn("Cantidad");
        modelTable.addColumn("Precio Total");
        modelTable.addColumn("Fecha");
       
        Object Columna[]=new Object[8];
        int Numeroventas = ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).size();
             
             for (int i = 0; i < Numeroventas; i++) {
                 Columna[0]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getIdE();
                 Columna[1]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getNombreE();
                 Columna[2]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getNombre();
                 Columna[3]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getCaracteristica();
                 Columna[4]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getPrecio();
                 Columna[5]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getCantidad();
                 Columna[6]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getPrecioT();
                 Columna[7]=ModeloDetalleDAO.ListarVentas(ValorBusqueda,TipoBusquedad).get(i).getFecha();
                
                 modelTable.addRow(Columna);
               
           }
             vistaR.tablaListarventas.setModel(modelTable);
 
    }
       
      
      
       public void  calcularpago(){
            int Numero = ModeloDetalleDAO.calcularP().size();
            
            int pago=(int) ModeloDetalleDAO.calcularP().get(0).getCantidadvendida();
            vistaR.labelCantidadtotal.setText(String.valueOf((pago))); 
          
       }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistaR.btnBuscar){
              //capturamos el dato seleccionado del comboBox Buscar
            String ComboBoxBuscar = vistaR.ComboBoxReporte.getSelectedItem().toString();
             //ingresamos las varaibles Que utilizaremos para buscar
            String ValorBusquedad = vistaR.txtBuscar.getText();
            String TipoBusquedad = "";
            
             if (ComboBoxBuscar == "ID Empleado") {
                TipoBusquedad = "empleado.idE";
            } else if (ComboBoxBuscar == "Fecha de venta") {
                TipoBusquedad = "ventas.fecha";
            }
              else if (ComboBoxBuscar == "ID Producto") {
                TipoBusquedad = "venta_detalle.idP";
            } 
              if (ValorBusquedad.equals("")) {
                ValorBusquedad = "";
                TipoBusquedad = "";
                JOptionPane.showMessageDialog(null, "Por favor Dijite El Dato A Buscar");
                
            }
              MostrarListaVentas(ValorBusquedad,TipoBusquedad);
             vistaR.txtBuscar.setText("");
             

         }
        
        
        if(e.getSource()==vistaR.btnActualizar){
            this.MostrarListaVentas("","");
        }
        
        
        if(e.getSource()==vistaR.btnCalcular){
              int fila=0;
        int total=0;
           for (int i = 0; i < vistaR.tablaListarventas.getRowCount(); i++) {
               fila=Integer.parseInt(vistaR.tablaListarventas.getValueAt(i,6).toString());
               total+=fila;
               
           }
           vistaR.labelCantidadApagar.setText(String.valueOf(total));     
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vistaR.jMenuSalir)
      {
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
       vistaLogin vistalogin=new vistaLogin();
       ControladorLogin login = new ControladorLogin(modelologin, modeloLoginDAO, vistalogin);
       login.InicarVistaLogin();
       vistalogin.setVisible(true);
       vistaR.setVisible(false);
      }
      
      if(e.getSource()== vistaR.menuProductos){
             producto modeloproducto = new producto();
             productoDAO modeloproductoDAO = new productoDAO();
              VistaProducto vistaP=new  VistaProducto();
              formularioIngresarPro vistaIngreso=new  formularioIngresarPro();
           ControladorProductos controlPro=new ControladorProductos(vistaP, modeloproducto, modeloproductoDAO, vistaIngreso);
              controlPro.IniciarVistaProducto();
              vistaP.setVisible(true);
              vistaR.setVisible(false);
      }
      
      if(e.getSource()== vistaR.menuAdmistrarEmpleado){
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
          FormularioEmpleado formulatio= new FormularioEmpleado();
       VistaEmplado vistaE= new VistaEmplado();
       ControladorRegistroEmpleado contrlE = new ControladorRegistroEmpleado(modelologin, modeloLoginDAO, 
               formulatio, vistaE);
      contrlE.IniciarVistaEmpleado();
       vistaE.setVisible(true);
       vistaR.setVisible(false);
      }
      
      if(e.getSource()==vistaR.menuGenerarVenta)
      {
       VistaAdministrador vistaAdmi =new VistaAdministrador();
       VentaDAO modeloVentaDAO = new VentaDAO();
       ventas medoloventas = new ventas();
       venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
      Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
       Impresionn panel = new Impresionn();
       ControladorAdministrador Admin = new ControladorAdministrador(vistaAdmi, modeloVentaDAO, medoloventas
       , ModeloVenta_Detalle,ModeloVenta_DetalleDAO,panel);
       Admin.IniciarVistaAdmin();
       vistaAdmi.setVisible(true);
       vistaR.setVisible(false);
        vistaAdmi.txtidvendedor.setText(String.valueOf(vector[0]));
        vistaAdmi.txtidvendedor.setEditable(false);
      }  
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
