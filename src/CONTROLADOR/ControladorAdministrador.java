/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.*;
import VISTA.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IESJOBE
 */
public class ControladorAdministrador  implements ActionListener, MouseListener {
           
           private VistaAdministrador vistaAdmi;
           private VentaDAO modeloVentaDAO;
           private ventas medoloventas;
           private venta_Detalle  ModeloVenta_Detalle;
           private Venta_DetalleDAO ModeloVenta_DetalleDAO;
           
           private Impresionn panel;
       
      
         
    public ControladorAdministrador(VistaAdministrador vistaAdmi, VentaDAO modeloVentaDAO, ventas medoloventas
    ,venta_Detalle  ModeloVenta_Detalle,Venta_DetalleDAO ModeloVenta_DetalleDAO,Impresionn panel) {
        
        this.vistaAdmi = vistaAdmi;
        this.modeloVentaDAO = modeloVentaDAO;
        this.medoloventas = medoloventas;
        this.ModeloVenta_Detalle=ModeloVenta_Detalle;
        this.ModeloVenta_DetalleDAO=ModeloVenta_DetalleDAO;
         this.panel = panel;
        
        this.vistaAdmi.btnagregar.addActionListener(this);
        this.vistaAdmi.btngenerarVentas.addActionListener(this);
        this.vistaAdmi.btnNueva.addActionListener(this);
        this.vistaAdmi.btnEliminar.addActionListener(this);
        this.vistaAdmi.btnFactura.addActionListener(this);
       
        // botones de addmouseListener
        this.vistaAdmi.jMenuSalir.addMouseListener(this);
        this.vistaAdmi.menuProductos.addMouseListener(this);
        this.vistaAdmi.menuAdmistrarEmpleado.addMouseListener(this);
        this.vistaAdmi.Menuconsultarventas.addMouseListener(this);
   
    }
    
      public void IniciarVistaAdmin() {
       vistaAdmi.setLocationRelativeTo(null);
       vistaAdmi.setTitle("Ventana generar compra administrador");
        this.MostrarTablaVenta();
        vistaAdmi.labelNumeroventas.setText(String.valueOf((ultimo()+1))); 
             
        
    }
      
       public void MostrarTablaVenta(){
             DefaultTableModel modelTable = new DefaultTableModel();
             vistaAdmi.tablaRegistroventas.setModel(modelTable);
             modelTable.addColumn("Id de Venta ");
             modelTable.addColumn("Nombre");
             modelTable.addColumn("Caracteristica");
             modelTable.addColumn("precio neto");
             modelTable.addColumn("Cantidad vendida");
             modelTable.addColumn("precio total");
             modelTable.addColumn("Fecha");
            Object columna[] = new Object[7];
            
       }
       

       
       public int   ultimo(){
            int Numero = modeloVentaDAO.ultimo_registro().size();
            
            int ultimo=modeloVentaDAO.ultimo_registro().get(0).getIdV();
          
          return ultimo;
       }
       

        public int  ultimoventa(){
            int Numero = ModeloVenta_DetalleDAO.ultimo_registroventa().size();
            
            int ultimo=ModeloVenta_DetalleDAO.ultimo_registroventa().get(0).getIdventa_detalle();
           return ultimo+1; 
          
       } 
        
       public void ingresar(){
        DefaultTableModel modelo =( DefaultTableModel) vistaAdmi.tablaRegistroventas.getModel();
           Calendar c =Calendar.getInstance();
           int ano,mes,diaDelmes,cantidad;
           diaDelmes =c.get(Calendar.DAY_OF_MONTH);
           mes=c.get(Calendar.MONTH);
           ano=c.get(Calendar.YEAR);
           cantidad=Integer.parseInt(vistaAdmi.txtcantidad.getText());
              Object[] Tabla = new Object[7];;
           
           Tabla[0]=ultimoventa();
           Tabla[1]=medoloventas.getNombre();
           Tabla[2]=medoloventas.getCaracteristica();
           Tabla[3]=medoloventas.getPrecio();
           Tabla[4]=cantidad;
           Tabla[5]=(cantidad*medoloventas.getPrecio());
           Tabla[6]=ano+"-"+(mes+1)+"-"+diaDelmes;
           modelo.addRow(Tabla);
           vistaAdmi.tablaRegistroventas.setModel(modelo);
           int ultimaVenta= Integer.parseInt(vistaAdmi.labelNumeroventas.getText());
           
           if(ultimaVenta!=ultimo()){
           medoloventas.setIdE(Integer.parseInt(vistaAdmi.txtidvendedor.getText()));
           medoloventas.setFecha(ano+"-"+(mes+1)+"-"+diaDelmes);
           modeloVentaDAO.Registra(medoloventas);
           
           ModeloVenta_Detalle.setIdV(Integer.parseInt(vistaAdmi.labelNumeroventas.getText()));
           ModeloVenta_Detalle.setIdP(Integer.parseInt(vistaAdmi.txtbuscar.getText()));
           ModeloVenta_Detalle.setCantidad(Integer.parseInt(vistaAdmi.txtcantidad.getText()));
           ModeloVenta_Detalle.setPrecio(cantidad*medoloventas.getPrecio());
           ModeloVenta_DetalleDAO.Registra(ModeloVenta_Detalle);
           }else{
           ModeloVenta_Detalle.setIdV(Integer.parseInt(vistaAdmi.labelNumeroventas.getText()));
           ModeloVenta_Detalle.setIdP(Integer.parseInt(vistaAdmi.txtbuscar.getText()));
           ModeloVenta_Detalle.setCantidad(Integer.parseInt(vistaAdmi.txtcantidad.getText()));
           ModeloVenta_Detalle.setPrecio(cantidad*medoloventas.getPrecio());
           ModeloVenta_DetalleDAO.Registra(ModeloVenta_Detalle);  
           }
           
          
       }
       
       
       public  int tatalpago(){
        int fila=0;
        int total=0;
           for (int i = 0; i < vistaAdmi.tablaRegistroventas.getRowCount(); i++) {
               fila=Integer.parseInt(vistaAdmi.tablaRegistroventas.getValueAt(i,5).toString());
               total+=fila;
               
           }
           return total;
              
}
     
 
       public void limpiar(){
           vistaAdmi.txtbuscar.setText("");
           vistaAdmi.txtcantidad.setText("");
           vistaAdmi.txtpago.setText("");
           vistaAdmi.labelCantidadApagar.setText("");
           vistaAdmi.labelvalorCanbio.setText("");
              
       }
       



    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==vistaAdmi.btnagregar){
               // si el campo esta vacio debe llenarse 
       if(!vistaAdmi.txtbuscar.getText().equalsIgnoreCase("")){
           if(vistaAdmi.txtcantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Debe  llenar el campo cantidad a vender ","ERROR",
                   JOptionPane.ERROR_MESSAGE);
       }else if(vistaAdmi.txtidvendedor.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Debe  llenar el campo id del vendedor ","ERROR",
                   JOptionPane.ERROR_MESSAGE); 
       }else{
           medoloventas.setIdP(Integer.parseInt(vistaAdmi.txtbuscar.getText()));
           if(modeloVentaDAO.Buscar(medoloventas)){
              
               this.ingresar();
               vistaAdmi.labelCantidadApagar.setText(String.valueOf(this.tatalpago())); 
               vistaAdmi.txtbuscar.setText("");
               vistaAdmi.txtcantidad.setText("");
              
           }else {
               JOptionPane.showMessageDialog(null," El producto no esta registardo","Error",JOptionPane.ERROR_MESSAGE); 
//               this.Limpiar();
           }
           }
       }else{
             JOptionPane.showMessageDialog(null,"Ingrese el id del producto agregar a la compra");
       }
       
   }
       
       if(e.getSource()==vistaAdmi.btngenerarVentas){
           int cambio=0;
           int pago,valorpagado;
           valorpagado=Integer.parseInt(vistaAdmi.txtpago.getText());
           pago=Integer.parseInt(vistaAdmi.labelCantidadApagar.getText());
           cambio=valorpagado-pago;
           vistaAdmi.labelvalorCanbio.setText(String.valueOf(cambio));
           
       }
       
       if(e.getSource()==vistaAdmi.btnNueva){
           DefaultTableModel modelo =( DefaultTableModel) vistaAdmi.tablaRegistroventas.getModel();
           int fila=vistaAdmi.tablaRegistroventas.getRowCount();
           for (int i = fila-1; i >= 0; i--) {
              modelo.removeRow(i);
           }
           this.limpiar();
             vistaAdmi.labelNumeroventas.setText(String.valueOf((ultimo()+1))); 
       }
       
       
       if(e.getSource()==vistaAdmi.btnEliminar){
           DefaultTableModel modelo =( DefaultTableModel) vistaAdmi.tablaRegistroventas.getModel();
           int fila=vistaAdmi.tablaRegistroventas.getSelectedRow();
           if(fila>=0){
             modelo.removeRow(fila); 
               if(!vistaAdmi.txteliminarventa.getText().equalsIgnoreCase("")){
               ModeloVenta_Detalle.setIdventa_detalle(Integer.parseInt(vistaAdmi.txteliminarventa.getText()));
             if(ModeloVenta_DetalleDAO.Eliminar(ModeloVenta_Detalle)){
            
             JOptionPane.showMessageDialog(null, "LA VENTA SE ELIMINO CORRECTAMENTE!! ");
             vistaAdmi.labelCantidadApagar.setText(String.valueOf(this.tatalpago())); 
               vistaAdmi.txteliminarventa.setText("");
           }else {
               JOptionPane.showMessageDialog(null," la venta no esta registrada","Error",JOptionPane.ERROR_MESSAGE); 
               vistaAdmi.txteliminarventa.setText("");
           }
                 }else{
                 JOptionPane.showMessageDialog(null,"Ingrese el id de la venta a eliminar");
              
                 }            
           }else 
               JOptionPane.showMessageDialog(null,"Seleccionar una fila ","ERROR",JOptionPane.ERROR_MESSAGE);
       }
         if(e.getSource()==vistaAdmi.btnFactura){
         Factura fac = new Factura();
         int totalp=0;
         fac.setVisible(true);
         fac.setLocationRelativeTo(null);
             for (int i = 0; i < vistaAdmi.tablaRegistroventas.getRowCount(); i++) {
                 String Dato[]=new String[7];
                 Dato[0]=vistaAdmi.tablaRegistroventas.getValueAt(i, 1).toString();
                 Dato[1]=vistaAdmi.tablaRegistroventas.getValueAt(i, 2).toString();
                 Dato[2]=vistaAdmi.tablaRegistroventas.getValueAt(i, 3).toString();
                 Dato[3]=vistaAdmi.tablaRegistroventas.getValueAt(i, 4).toString();
                 Dato[4]=vistaAdmi.tablaRegistroventas.getValueAt(i, 5).toString();
                 Dato[5]=vistaAdmi.tablaRegistroventas.getValueAt(i, 6).toString();
                 panel.model2.addRow(Dato);
                 
                totalp+=Integer.parseInt(vistaAdmi.tablaRegistroventas.getValueAt(i, 5).toString());
             }
            panel.jTextField1.setText(vistaAdmi.txtidvendedor.getText());
            panel.jTextField1.setEditable(false);
            panel.labelPagoTotal.setText(vistaAdmi.labelCantidadApagar.getText());
            panel.labelCambio.setText(vistaAdmi.labelvalorCanbio.getText());
            panel.labelfe.setText(vistaAdmi.tablaRegistroventas.getValueAt(0, 6).toString());
           
            
       }
       
       
       }
       
    

    @Override
    public void mouseClicked(MouseEvent e) {
      if(e.getSource()==vistaAdmi.jMenuSalir)
      {
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
       vistaLogin vistalogin=new vistaLogin();
       ControladorLogin login = new ControladorLogin(modelologin, modeloLoginDAO, vistalogin);
       login.InicarVistaLogin();
       vistalogin.setVisible(true);
       vistaAdmi.setVisible(false);
      }
      
      if(e.getSource()== vistaAdmi.menuProductos){
             producto modeloproducto = new producto();
             productoDAO modeloproductoDAO = new productoDAO();
              VistaProducto vistaP=new  VistaProducto();
              formularioIngresarPro vistaIngreso=new  formularioIngresarPro();
           ControladorProductos controlPro=new ControladorProductos(vistaP, modeloproducto, modeloproductoDAO, vistaIngreso);
              controlPro.IniciarVistaProducto();
              vistaP.setVisible(true);
              vistaAdmi.setVisible(false);
      }
      
      if(e.getSource()== vistaAdmi.menuAdmistrarEmpleado){
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
          FormularioEmpleado formulatio= new FormularioEmpleado();
       VistaEmplado vistaE= new VistaEmplado();
       ControladorRegistroEmpleado contrlE = new ControladorRegistroEmpleado(modelologin, modeloLoginDAO, 
               formulatio, vistaE);
      contrlE.IniciarVistaEmpleado();
       vistaE.setVisible(true);
       vistaAdmi.setVisible(false);
      }
      
      if (e.getSource()==vistaAdmi.Menuconsultarventas){
           venta_Detalle modeloVentaDetalle=new venta_Detalle();
           Venta_DetalleDAO ModeloDetalleDAO=new Venta_DetalleDAO();
            VistaReporte vistaR = new VistaReporte();
             controladorGenerarReporte controlR=new controladorGenerarReporte(modeloVentaDetalle, ModeloDetalleDAO,
                     vistaR);
             controlR.InicioListaVentas();
             vistaR.setVisible(true);
             vistaAdmi.setVisible(false);

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
