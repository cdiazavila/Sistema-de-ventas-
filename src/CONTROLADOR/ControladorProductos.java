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

/**
 *
 * @author IESJOBE
 */
public class ControladorProductos implements ActionListener, MouseListener {
   private VistaProducto vistaPro;
   private producto modeloproducto;
   private productoDAO modeloproductoDAO;
   private formularioIngresarPro vistaIngreso;
  
   
   
    public ControladorProductos(VistaProducto vistaPro, producto modeloproducto, productoDAO modeloproductoDAO
    ,formularioIngresarPro vistaIngreso) {
        this.vistaPro = vistaPro;
        this.modeloproducto = modeloproducto;
        this.modeloproductoDAO = modeloproductoDAO;
        this.vistaIngreso=vistaIngreso;
        
        
        //botones de addActionlistener
        this.vistaPro.btnIngresar.addActionListener(this);
        this.vistaPro.btnBuscarPro.addActionListener(this);
        this.vistaIngreso.btnCancelar.addActionListener(this);
        this.vistaIngreso.btnGuardar.addActionListener(this);
        this.vistaPro.btnActualizar.addActionListener(this);
        this.vistaPro.btneliminar.addActionListener(this);
        this.vistaPro.btnCancelar.addActionListener(this);
        // botones de addmouseListener
        this.vistaPro.jMenuSalir.addMouseListener(this);
        this.vistaPro.jMenuGenerarVentas.addMouseListener(this);
        this.vistaPro.menuAdministrarEmpleado.addMouseListener(this);
        this.vistaPro.Menuconsultarventas.addMouseListener(this);
        
    }

        public void IniciarVistaProducto() {
       vistaPro.setLocationRelativeTo(null);
       vistaPro.setTitle("Ventana Productos");
    vistaPro.menuProductos.setEnabled(false);
    }
       
        public void Limpiar(){
        vistaIngreso.txtidPro.setText("");
        vistaIngreso.txtnombre.setText("");
        vistaIngreso.txtcaracte.setText("");
        vistaIngreso.txtprecio.setText("");
        }
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==vistaPro.btnIngresar){
             vistaIngreso.setTitle("FORMULARIO");
            vistaIngreso.setVisible(true);
            vistaPro.setEnabled(false);
            this.Limpiar();
             vistaIngreso.txtidPro.setEditable(true);
             vistaIngreso.setLocationRelativeTo(null);
        }
        
        
        
         if(e.getSource()==vistaIngreso.btnCancelar){
               vistaPro.setEnabled(true);
            vistaIngreso.setVisible(false);
          
     
         }
         
         
         
       if (e.getSource()==vistaIngreso.btnGuardar)
       {
           
           if(vistaIngreso.txtidPro.getText().isEmpty()|| vistaIngreso.txtnombre.getText().isEmpty() || 
                   vistaIngreso.txtcaracte.getText().isEmpty() || vistaIngreso.txtprecio.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Algunos campos estan vacios ");
           }else{
         int Respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro Que Quieres Registra este producto?");
               if (Respuesta == 0) {
                   
               
                    modeloproducto.setIdP(Integer.parseInt(vistaIngreso.txtidPro.getText()));
                if(modeloproductoDAO.Buscar(modeloproducto)==false){
             
             
              modeloproducto.setNombre(vistaIngreso.txtnombre.getText());
              modeloproducto.setCaracteristica(vistaIngreso.txtcaracte.getText());
              modeloproducto.setPrecio(Integer.parseInt(vistaIngreso.txtprecio.getText()));
                               
                
                  
             
             if( modeloproductoDAO.Registra(modeloproducto)){
                this.Limpiar();
             JOptionPane.showMessageDialog(null ,"Se registro un producto");  
               
             }else {
                 JOptionPane.showMessageDialog(null," datos no se registraron","Error",JOptionPane.ERROR_MESSAGE);
             }
          }else{
             modeloproducto.setIdP(Integer.parseInt(vistaIngreso.txtidPro.getText()));
             modeloproducto.setNombre(vistaIngreso.txtnombre.getText());
             modeloproducto.setCaracteristica(vistaIngreso.txtcaracte.getText());
             modeloproducto.setPrecio(Integer.parseInt(vistaIngreso.txtprecio.getText()));
              
                if (modeloproductoDAO.Modificar(modeloproducto)) {
                JOptionPane.showMessageDialog(null, "El producto  se  Modifico Correctamente");
                Limpiar();
               
                

            } else {
                JOptionPane.showMessageDialog(null, "Al  Modificar los Datos","ERROR",JOptionPane.ERROR_MESSAGE);
            }
                }
               }
           }
          
       }
       
       
       
       
          if(e.getSource()==vistaPro.btnBuscarPro){
       // si el campo esta vacio debe llenarse 
       if(!vistaPro.txtidPro.getText().equalsIgnoreCase("")){
           modeloproducto.setIdP(Integer.parseInt(vistaPro.txtidPro.getText()));
           if(modeloproductoDAO.Buscar(modeloproducto)){
              
               vistaPro.txtNombre.setText(modeloproducto.getNombre());
               vistaPro.txtCarac.setText(modeloproducto.getCaracteristica());
               vistaPro.txtPrecio.setText(String.valueOf(modeloproducto.getPrecio()));
           }else {
               JOptionPane.showMessageDialog(null," El producto no esta registardo","Error",JOptionPane.ERROR_MESSAGE); 
               this.Limpiar();
           }
       }else{
             JOptionPane.showMessageDialog(null,"Ingrese el id del producto a buscar");
       }
       
   }
          
          
          
          if(e.getSource()==vistaPro.btnActualizar){
            
              if(!vistaPro.txtidPro.getText().equalsIgnoreCase("")){
                   vistaIngreso.setVisible(true);
                   vistaIngreso.setLocationRelativeTo(null);
                   vistaPro.setEnabled(false);
                  modeloproducto.setIdP(Integer.parseInt(vistaPro.txtidPro.getText()));
           if(modeloproductoDAO.Buscar(modeloproducto)){
              vistaIngreso.txtidPro.setText(String.valueOf(modeloproducto.getIdP()));
               vistaIngreso.txtnombre.setText(modeloproducto.getNombre());
               vistaIngreso.txtcaracte.setText(modeloproducto.getCaracteristica());
               vistaIngreso.txtprecio.setText(String.valueOf(modeloproducto.getPrecio())); 
          }
          vistaIngreso.txtidPro.setEditable(false); 
              }else{
             JOptionPane.showMessageDialog(null,"Ingrese el id del producto que quiere actualizar");
       }
             
       
            
    }
          
          
          if(e.getSource()==vistaPro.btneliminar){
                 if(!vistaPro.txtidPro.getText().equalsIgnoreCase("")){
               modeloproducto.setIdP(Integer.parseInt(vistaPro.txtidPro.getText()));
             if(modeloproductoDAO.Buscar(modeloproducto)){
                  int Respuesta = JOptionPane.showConfirmDialog(null, "SEGURO QUE QUIERE ELIMINAR EL PRODUCTO: \n"
                         + " "+ modeloproducto.getNombre()+" ?");

                    if (Respuesta == 0) {
                        modeloproducto.setIdP(Integer.parseInt(vistaPro.txtidPro.getText()));
                        modeloproductoDAO.Eliminar(modeloproducto);
                        JOptionPane.showMessageDialog(null, "EL PRODUCTO SE ELIMINO CORRECTAMENTE!! ");
                        this.Limpiar();
                    }
                     this.Limpiar();
              
           }else {
               JOptionPane.showMessageDialog(null," El producto no esta registardo","Error",JOptionPane.ERROR_MESSAGE); 
               this.Limpiar();
           }
                 }else{
                 JOptionPane.showMessageDialog(null,"Ingrese el id del producto a eliminar");
              
                 }
             
          }
          
            if(e.getSource()==vistaPro.btnCancelar){
               VistaAdministrador vistaAdmi=new VistaAdministrador();
               VentaDAO modeloVentaDAO =new VentaDAO();
               ventas medoloventas = new ventas();
               venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
               Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
               Impresionn panel = new Impresionn();
               ControladorAdministrador control = new ControladorAdministrador(vistaAdmi, modeloVentaDAO, medoloventas,
               ModeloVenta_Detalle,ModeloVenta_DetalleDAO,panel);
               control.IniciarVistaAdmin();
               vistaAdmi.setVisible(true);
               vistaPro.setVisible(false);
           }
    }
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vistaPro.jMenuSalir)
      {
       Login modelologin = new Login();
       LoginDAO modeloLoginDAO= new LoginDAO();
       vistaLogin vistalogin=new vistaLogin();
       ControladorLogin login = new ControladorLogin(modelologin, modeloLoginDAO, vistalogin);
       login.InicarVistaLogin();
       vistalogin.setVisible(true);
       vistaPro.setVisible(false);
      }
        
        
        
         if(e.getSource()==vistaPro.jMenuGenerarVentas)
      {
       VistaAdministrador vistaAdmi =new VistaAdministrador();
       VentaDAO modeloVentaDAO = new VentaDAO();
       ventas medoloventas = new ventas();
       venta_Detalle  ModeloVenta_Detalle = new venta_Detalle() ;
       Venta_DetalleDAO ModeloVenta_DetalleDAO = new Venta_DetalleDAO();
       Impresionn panel = new Impresionn();
       ControladorAdministrador Admin = new ControladorAdministrador(vistaAdmi, modeloVentaDAO, medoloventas, 
               ModeloVenta_Detalle, ModeloVenta_DetalleDAO,panel);
       Admin.IniciarVistaAdmin();
       vistaAdmi.setVisible(true);
       vistaPro.setVisible(false);
        vistaAdmi.txtidvendedor.setText(String.valueOf(vector[0]));
        vistaAdmi.txtidvendedor.setEditable(false);
      } 
         
         
         
         if(e.getSource()== vistaPro.menuAdministrarEmpleado){
           Login modelologin = new Login();
            LoginDAO modeloLoginDAO= new LoginDAO();
            FormularioEmpleado formulatio= new FormularioEmpleado();
                VistaEmplado vistaE= new VistaEmplado();
                ControladorRegistroEmpleado contrlE = new ControladorRegistroEmpleado(modelologin, modeloLoginDAO, 
               formulatio, vistaE);
               contrlE.IniciarVistaEmpleado();
                vistaE.setVisible(true);
                 vistaPro.setVisible(false);
      }
         
         if(e.getSource()==vistaPro.Menuconsultarventas){
             venta_Detalle modeloVentaDetalle=new venta_Detalle();
           Venta_DetalleDAO ModeloDetalleDAO=new Venta_DetalleDAO();
            VistaReporte vistaR = new VistaReporte();
             controladorGenerarReporte controlR=new controladorGenerarReporte(modeloVentaDetalle, ModeloDetalleDAO,
                     vistaR);
             controlR.InicioListaVentas();
             vistaR.setVisible(true);
             vistaPro.setVisible(false);
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
